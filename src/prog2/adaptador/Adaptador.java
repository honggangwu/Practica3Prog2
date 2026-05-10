package prog2.adaptador;

import prog2.model.*;
import prog2.vista.BiblioException;
import java.io.*;
import java.util.ArrayList;

public class Adaptador {

    private Dades dades;

    public Adaptador() {
        dades = new Dades();
    }

    // Afegeix un exemplar nou a la biblioteca
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

    // Recupera tots els exemplars i els converteix a String
    public ArrayList<String> recuperaExemplars() {
        ArrayList<String> resultat = new ArrayList<>();

        for (Exemplar e : dades.recuperaExemplars()) {
            resultat.add(e.toString());
        }

        return resultat;
    }

    // Afegeix un usuari nou
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    // Recupera tots els usuaris en format text
    public ArrayList<String> recuperaUsuaris() {
        ArrayList<String> resultat = new ArrayList<>();

        for (Usuari u : dades.recuperaUsuaris()) {
            resultat.add(u.toString());
        }

        return resultat;
    }

    // Crea un préstec entre un usuari i un exemplar
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    // Marca un préstec com retornat
    public void retornarPrestec(int posicio) throws BiblioException {
        dades.retornarPrestec(posicio);
    }

    // Recupera tots els préstecs en format String
    public ArrayList<String> recuperaPrestecs() {
        ArrayList<String> resultat = new ArrayList<>();

        for (Prestec p : dades.recuperaPrestecs()) {
            resultat.add(p.toString());
        }

        return resultat;
    }

    // Recupera només els préstecs que encara no s'han retornat
    public ArrayList<String> recuperaPrestecsNoRetornats() {
        ArrayList<String> resultat = new ArrayList<>();

        for (Prestec p : dades.recuperaPrestecsNoRetornats()) {
            resultat.add(p.toString());
        }

        return resultat;
    }

    // Guarda totes les dades en un fitxer mitjançant serialització
    public void guardaDades(String camiDesti) throws BiblioException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(camiDesti))) {
            oos.writeObject(dades);

        } catch (IOException e) {
            throw new BiblioException("Error guardant les dades.");
        }
    }

    // Carrega les dades d'un fitxer i substitueix les actuals
    public void carregaDades(String camiOrigen) throws BiblioException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(camiOrigen))) {
            dades = (Dades) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new BiblioException("Error carregant les dades.");
        }
    }
}