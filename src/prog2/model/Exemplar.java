package prog2.model;

public class Exemplar implements InExemplar{

    private String idExemplar; // L'identificador ha de ser numèric
    private String titol;
    private String autor;
    private boolean admetPrestecLlarg;
    private boolean disponible; // Indica si l'exemplar es troba disponible

    /**
     * Constructor per inicialitzar els valors dels atributs[cite: 107].
     * @param idExemplar Identificador numèric de l'exemplar.
     * @param titol Títol del llibre.
     * @param autor Autor del llibre.
     * @param admetPrestecLlarg Booleà que indica si permet préstecs de llarga durada.
     */
    public Exemplar(String idExemplar, String titol, String autor, boolean admetPrestecLlarg) {
        this.idExemplar = idExemplar;
        this.titol = titol;
        this.autor = autor;
        this.admetPrestecLlarg = admetPrestecLlarg;
        this.disponible = true;
    }

    @Override
    public void setId(String id) {
        this.idExemplar = id;
    }

    @Override
    public String getId() {
        return idExemplar;
    }

    @Override
    public void setTitol(String titol) {
        this.titol = titol;
    }

    @Override
    public String getTitol() {
        return titol;
    }

    @Override
    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public void setAdmetPrestecLlarg(boolean admetPrestecLlarg) {
        this.admetPrestecLlarg = admetPrestecLlarg;
    }

    @Override
    public boolean getAdmetPrestecLlarg() {
        return admetPrestecLlarg;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Id=" + idExemplar +
                ", Titol " + titol +
                ", Autor " + autor +
                ", Admet Prestec Llarg " + admetPrestecLlarg +
                ", Disponible=" + disponible;
    }
}