package prog2.model;

import java.io.Serializable;

public abstract class Usuari implements InUsuari, Serializable {
    private String email;
    private String nom;
    private String adreca;
    private int numPrestecsNormals;
    private int numPrestecsLlargs;

    public Usuari(String email, String nom, String adreca) {
        this.email = email;
        this.nom = nom;
        this.adreca = adreca;
        this.numPrestecsNormals = 0;
        this.numPrestecsLlargs = 0;
    }

    public abstract String tipusUsuari();
    public abstract int getMaxPrestecsNormals();
    public abstract int getMaxPrestecsLlargs();

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    @Override
    public String getAdreca() {
        return this.adreca;
    }

    @Override
    public void setNumPrestecsNormals(int numPrestecsNormals) {
        this.numPrestecsNormals = numPrestecsNormals;
    }

    @Override
    public int getNumPrestecsNormals() {
        return this.numPrestecsNormals;
    }

    @Override
    public void setNumPrestecsLlargs(int numPrestecsLlargs) {
        this.numPrestecsLlargs = numPrestecsLlargs;
    }

    @Override
    public int getNumPrestecsLlargs() {
        return this.numPrestecsLlargs;
    }

    @Override
    public String toString() {
        return "Tipus=" + tipusUsuari() +
                ", Email=" + email +
                ", Nom=" + nom +
                ", Adreca=" + adreca +
                ", Num. prestecs normals=" + numPrestecsNormals +
                ", Num. prestecs llargs=" + numPrestecsLlargs;
    }
}