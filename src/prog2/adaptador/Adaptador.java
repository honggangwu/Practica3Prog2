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

    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        dades.afegirExemplar(id, titol, autor, admetPrestecLlarg);
    }

    public ArrayList<String> recuperaExemplars() {
        ArrayList<String> resultat = new ArrayList<>();
        for (Exemplar e : dades.recuperaExemplars()) {
            resultat.add(e.toString());
        }
        return resultat;
    }

    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {
        dades.afegirUsuari(email, nom, adreca, esEstudiant);
    }

    public ArrayList<String> recuperaUsuaris() {
        ArrayList<String> resultat = new ArrayList<>();
        for (Usuari u : dades.recuperaUsuaris()) {
            resultat.add(u.toString());
        }
        return resultat;
    }

    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    public void retornarPrestec(int posicio) throws BiblioException {
        dades.retornarPrestec(posicio);
    }

    public ArrayList<String> recuperaPrestecs() {
        ArrayList<String> resultat = new ArrayList<>();
        for (Prestec p : dades.recuperaPrestecs()) {
            resultat.add(p.toString());
        }
        return resultat;
    }

    public ArrayList<String> recuperaPrestecsNoRetornats() {
        ArrayList<String> resultat = new ArrayList<>();
        for (Prestec p : dades.recuperaPrestecsNoRetornats()) {
            resultat.add(p.toString());
        }
        return resultat;
    }

    public void guardaDades(String camiDesti) throws BiblioException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(camiDesti))) {
            oos.writeObject(dades);
        } catch (IOException e) {
            throw new BiblioException("Error guardant les dades.");
        }
    }

    public void carregaDades(String camiOrigen) throws BiblioException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(camiOrigen))) {
            dades = (Dades) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new BiblioException("Error carregant les dades.");
        }
    }
}