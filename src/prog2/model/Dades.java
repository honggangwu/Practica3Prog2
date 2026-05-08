package prog2.model;

import prog2.vista.BiblioException;
import java.util.ArrayList;

/**
 * Classe Dades que gestiona la lògica de negoci de la biblioteca.
 * Implementa la interfície InDades.
 */
public class Dades implements InDades {

    private LlistaExemplars llistaExemplars;
    private LlistaUsuaris llistaUsuaris;
    private LlistaPrestecs llistaPrestecs;

    /**
     * Constructor per defecte que inicialitza les llistes de dades.
     */
    public Dades() {
        llistaExemplars = new LlistaExemplars();
        llistaUsuaris = new LlistaUsuaris();
        llistaPrestecs = new LlistaPrestecs();
    }

    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        Exemplar nouExemplar = new Exemplar(id, titol, autor, admetPrestecLlarg);
        // El mètode afegir de LlistaExemplars hauria de llançar excepció si l'ID ja existeix
        llistaExemplars.afegir(nouExemplar);
    }

    @Override
    public ArrayList<Exemplar> recuperaExemplars() {
        return llistaExemplars.getArrayList();
    }

    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        Usuari nouUsuari;
        if (esEstudiant) {
            nouUsuari = new Estudiant(email, nom, adreca);
        } else {
            nouUsuari = new Professor(email, nom, adreca);
        }
        // El mètode afegir de LlistaUsuaris hauria de llançar excepció si l'email ja existeix
        llistaUsuaris.afegir(nouUsuari);
    }

    @Override
    public ArrayList<Usuari> recuperaUsuaris() {
        return llistaUsuaris.getArrayList();
    }

    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        Exemplar exemplar = llistaExemplars.getAt(exemplarPos);
        Usuari usuari = llistaUsuaris.getAt(usuariPos);

        // 1. Comprovar disponibilitat de l'exemplar
        if (!exemplar.isDisponible()) {
            throw new BiblioException("L'exemplar no està disponible.");
        }

        // 2. Comprovar si l'usuari té préstecs endarrerits
        if (tePrestecsEndarrerits(usuari)) {
            throw new BiblioException("L'usuari té préstecs endarrerits i no pot realitzar nous préstecs.");
        }

        Prestec nouPrestec;
        if (esLlarg) {
            // 3. Comprovar si l'exemplar admet préstec llarg
            if (!exemplar.isAdmetPrestecLlarg()) {
                throw new BiblioException("Aquest exemplar no admet préstecs de llarg termini.");
            }
            // 4. Comprovar límit de préstecs llargs de l'usuari
            if (comptarPrestecsActius(usuari, true) >= usuari.getMaxPrestecsLlargs()) {
                throw new BiblioException("L'usuari ha superat el límit de préstecs llargs.");
            }
            nouPrestec = new PrestecLlarg(exemplar, usuari);
        } else {
            // 5. Comprovar límit de préstecs normals de l'usuari
            if (comptarPrestecsActius(usuari, false) >= usuari.getMaxPrestecsNormals()) {
                throw new BiblioException("L'usuari ha superat el límit de préstecs normals.");
            }
            nouPrestec = new PrestecNormal(exemplar, usuari);
        }

        // Realitzar el préstec: l'exemplar passa a no disponible automàticament
        llistaPrestecs.afegir(nouPrestec);
        exemplar.setDisponible(false);
    }

    @Override
    public void retornarPrestec(int position) throws BiblioException {
        Prestec prestec = llistaPrestecs.getAt(position);

        if (prestec.isRetornat()) {
            throw new BiblioException("Aquest préstec ja ha estat retornat anteriorment.");
        }

        prestec.setRetornat(true);
        prestec.getExemplar().setDisponible(true);
    }

    @Override
    public ArrayList<Prestec> recuperaPrestecs() {
        return llistaPrestecs.getArrayList();
    }

    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats() {
        ArrayList<Prestec> noRetornats = new ArrayList<>();
        for (Prestec p : llistaPrestecs.getArrayList()) {
            if (!p.isRetornat()) {
                noRetornats.add(p);
            }
        }
        return noRetornats;
    }

    // Mètodes auxiliars privats per a les validacions de negoci

    private int comptarPrestecsActius(Usuari usuari, boolean esLlarg) {
        int count = 0;
        for (Prestec p : llistaPrestecs.getArrayList()) {
            if (!p.isRetornat() && p.getUsuari().equals(usuari)) {
                if (esLlarg && p instanceof PrestecLlarg) count++;
                if (!esLlarg && p instanceof PrestecNormal) count++;
            }
        }
        return count;
    }

    private boolean tePrestecsEndarrerits(Usuari usuari) {
        long ara = System.currentTimeMillis();
        for (Prestec p : llistaPrestecs.getArrayList()) {
            if (!p.isRetornat() && p.getUsuari().equals(usuari)) {
                if (ara > p.getDataLimitRetorn().getTime()) {
                    return true;
                }
            }
        }
        return false;
    }
}