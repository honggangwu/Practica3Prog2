package prog2.model;

public abstract class Usuari implements InUsuari {
    private String email;
    private String nom;
    private String adreca;
    private int numPrestecsNormals;
    private int numPrestecsLlargs;

    // Constructor que inicialitza els atributs principals
    public Usuari(String email, String nom, String adreca) {
        this.email = email;
        this.nom = nom;
        this.adreca = adreca;
        this.numPrestecsNormals = 0;
        this.numPrestecsLlargs = 0;
    }

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

    // El toString utilitza el mètode abstracte tipusUsuari() per saber quin tipus imprimir
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