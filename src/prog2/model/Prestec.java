package prog2.model;

import java.io.Serializable;
import java.util.Date;

public abstract class Prestec implements InPrestec, Serializable {
    private Exemplar exemplar;
    private Usuari usuari;
    private Date dataCreacio;
    private Date dataLimitRetorn;
    private boolean retornat;

    public Prestec(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        this.exemplar = exemplar;
        this.usuari = usuari;
        this.dataCreacio = dataCreacio;
        long tempsLimit = this.dataCreacio.getTime() + duradaPrestec();
        this.dataLimitRetorn = new Date(tempsLimit);
        this.retornat = false;
    }

    public abstract String tipusPrestec();
    public abstract long duradaPrestec();

    // --- Mètodes corregits per als tests ---

    @Override
    public void retorna() {
        // 1. Marcar el préstec com a retornat
        this.retornat = true;

        // 2. Alliberar l'exemplar (Passar a disponible)
        if (this.exemplar != null) {
            this.exemplar.setDisponible(true);
        }

        // 3. Decrementar el comptador de l'usuari segon el tipus de préstec
        if (this.usuari != null) {
            if (this instanceof PrestecNormal) {
                int actual = this.usuari.getNumPrestecsNormals();
                if (actual > 0) this.usuari.setNumPrestecsNormals(actual - 1);
            } else if (this instanceof PrestecLlarg) {
                int actual = this.usuari.getNumPrestecsLlargs();
                if (actual > 0) this.usuari.setNumPrestecsLlargs(actual - 1);
            }
        }
    }

    // --- Resta de Getters i Setters ---

    @Override public void setExemplar(Exemplar exemplar) { this.exemplar = exemplar; }
    @Override public Exemplar getExemplar() { return this.exemplar; }
    @Override public void setUsuari(Usuari usuari) { this.usuari = usuari; }
    @Override public Usuari getUsuari() { return this.usuari; }
    @Override public void setDataCreacio(Date data) { this.dataCreacio = data; }
    @Override public Date getDataCreacio() { return this.dataCreacio; }
    @Override public void setDataLimitRetorn(Date data) { this.dataLimitRetorn = data; }
    @Override public Date getDataLimitRetorn() { return this.dataLimitRetorn; }
    @Override public void setRetornat(boolean retornat) { this.retornat = retornat; }
    @Override public boolean getRetornat() { return this.retornat; }

    @Override
    public boolean prestecEndarrerit() {
        if (this.retornat) return false;
        return new Date().after(this.dataLimitRetorn);
    }

    @Override
    public String toString() {
        return "Tipus=" + tipusPrestec() +
                ", Exemplar=" + (exemplar != null ? exemplar.getTitol() : "null") +
                ", Usuari=" + (usuari != null ? usuari.getNom() : "null") +
                ", Data de creacio=" + dataCreacio.toString() +
                ", Data límit retorn=" + dataLimitRetorn.toString() +
                ", Retornat=" + retornat;
    }
}