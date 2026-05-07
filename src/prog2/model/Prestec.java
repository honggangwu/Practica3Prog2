package prog2.model;

import java.util.Date;

public abstract class Prestec implements InPrestec {
    private Exemplar exemplar;
    private Usuari usuari;
    private Date dataCreacio;
    private Date dataLimitRetorn;
    private boolean retornat;

    // Constructor
    public Prestec(Exemplar exemplar, Usuari usuari) {
        this.exemplar = exemplar;
        this.usuari = usuari;
        this.dataCreacio = new Date(); // Emmagatzema la data actual automàticament

        // Calculem la data límit sumant la durada (en mil·lisegons) a la data de creació
        long tempsLimit = this.dataCreacio.getTime() + duradaPrestec();
        this.dataLimitRetorn = new Date(tempsLimit);

        this.retornat = false;
    }

    // --- Getters i Setters ---

    @Override
    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    @Override
    public Exemplar getExemplar() {
        return this.exemplar;
    }

    @Override
    public void setUsuari(Usuari usuari) {
        this.usuari = usuari;
    }

    @Override
    public Usuari getUsuari() {
        return this.usuari;
    }

    @Override
    public void setDataCreacio(Date data) {
        this.dataCreacio = data;
    }

    @Override
    public Date getDataCreacio() {
        return this.dataCreacio;
    }

    @Override
    public void setDataLimitRetorn(Date data) {
        this.dataLimitRetorn = data;
    }

    @Override
    public Date getDataLimitRetorn() {
        return this.dataLimitRetorn;
    }

    @Override
    public void setRetornat(boolean retornat) {
        this.retornat = retornat;
    }

    @Override
    public boolean getRetornat() {
        return this.retornat;
    }

    // --- Lògica del préstec ---

    @Override
    public void retorna() {
        this.retornat = true;
    }

    @Override
    public boolean prestecEndarrerit() {
        // Si ja està retornat, no està endarrerit
        if (this.retornat) {
            return false;
        }
        // Si la data actual és posterior a la data límit, està endarrerit
        Date dataActual = new Date();
        return dataActual.after(this.dataLimitRetorn);
    }

    // --- Mètode toString ---

    @Override
    public String toString() {
        String nomExemplar = (exemplar != null) ? exemplar.getTitol() : "Desconegut";
        String nomUsuari = (usuari != null) ? usuari.getNom() : "Desconegut";

        return "Tipus=" + tipusPrestec() +
                ", Exemplar=" + nomExemplar +
                ", Usuari=" + nomUsuari +
                ", Data de creacio=" + dataCreacio.toString() +
                ", Data límit retorn=" + dataLimitRetorn.toString() +
                ", Retornat=" + retornat;
    }
}