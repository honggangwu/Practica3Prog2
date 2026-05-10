package prog2.model;

import prog2.vista.BiblioException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Dades implements InDades, Serializable {

    private LlistaExemplars llistaExemplars;
    private LlistaUsuaris llistaUsuaris;
    private LlistaPrestecs llistaPrestecs;

    public Dades() {
        llistaExemplars = new LlistaExemplars();
        llistaUsuaris = new LlistaUsuaris();
        llistaPrestecs = new LlistaPrestecs();
    }

    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        Exemplar nouExemplar = new Exemplar(id, titol, autor, admetPrestecLlarg);
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

        if (!exemplar.isDisponible()) {
            throw new BiblioException("L'exemplar no està disponible.");
        }

        if (tePrestecsEndarrerits(usuari)) {
            throw new BiblioException("L'usuari té préstecs endarrerits i no pot realitzar nous préstecs.");
        }

        Prestec nouPrestec;
        Date ara = new Date();

        if (esLlarg) {
            if (!exemplar.getAdmetPrestecLlarg()) {
                throw new BiblioException("Aquest exemplar no admet préstecs de llarg termini.");
            }
            if (comptarPrestecsActius(usuari, true) >= usuari.getMaxPrestecsLlargs()) {
                throw new BiblioException("L'usuari ha superat el límit de préstecs llargs.");
            }
            nouPrestec = new PrestecLlarg(exemplar, usuari, ara);

            // INCREMENTAR COMPTADOR USUARI
            usuari.setNumPrestecsLlargs(usuari.getNumPrestecsLlargs() + 1);

        } else {
            if (comptarPrestecsActius(usuari, false) >= usuari.getMaxPrestecsNormals()) {
                throw new BiblioException("L'usuari ha superat el límit de préstecs normals.");
            }
            nouPrestec = new PrestecNormal(exemplar, usuari, ara);

            // INCREMENTAR COMPTADOR USUARI
            usuari.setNumPrestecsNormals(usuari.getNumPrestecsNormals() + 1);
        }

        llistaPrestecs.afegir(nouPrestec);
        exemplar.setDisponible(false);
    }
    @Override
    public void retornarPrestec(int position) throws BiblioException {
        // Obtenim de la llista de no retornats per coherència amb la vista
        ArrayList<Prestec> noRetornats = recuperaPrestecsNoRetornats();
        if (position < 0 || position >= noRetornats.size()) {
            throw new BiblioException("Posició de préstec no vàlida.");
        }

        Prestec prestec = noRetornats.get(position);
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
            if (!p.getRetornat()) {
                noRetornats.add(p);
            }
        }
        return noRetornats;
    }

    private int comptarPrestecsActius(Usuari usuari, boolean esLlarg) {
        int count = 0;
        for (Prestec p : llistaPrestecs.getArrayList()) {
            if (!p.getRetornat() && p.getUsuari().getEmail().equals(usuari.getEmail())) {
                if (esLlarg && p instanceof PrestecLlarg) count++;
                if (!esLlarg && p instanceof PrestecNormal) count++;
            }
        }
        return count;
    }

    private boolean tePrestecsEndarrerits(Usuari usuari) {
        for (Prestec p : llistaPrestecs.getArrayList()) {
            if (!p.getRetornat() && p.getUsuari().getEmail().equals(usuari.getEmail())) {
                if (p.prestecEndarrerit()) {
                    return true;
                }
            }
        }
        return false;
    }
}