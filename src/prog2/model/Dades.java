package prog2.model;

import prog2.vista.BiblioException;

import java.util.ArrayList;

public class Dades implements InDades {

    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {

    }

    @Override
    public ArrayList<Exemplar> recuperaExemplars() {
        return null;
    }

    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException {

    }

    @Override
    public ArrayList<Usuari> recuperaUsuaris() {
        return null;
    }

    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException {

    }

    @Override
    public void retornarPrestec(int position) throws BiblioException {

    }

    @Override
    public ArrayList<Prestec> recuperaPrestecs() {
        return null;
    }

    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats() {
        return null;
    }
}
