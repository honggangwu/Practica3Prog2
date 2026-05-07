package prog2.model;

import java.util.Date;

public class Prestec implements InPrestec{
    @Override
    public void setExemplar(Exemplar exemplar) {

    }

    @Override
    public Exemplar getExemplar() {
        return null;
    }

    @Override
    public void setUsuari(Usuari usuari) {

    }

    @Override
    public Usuari getUsuari() {
        return null;
    }

    @Override
    public void setDataCreacio(Date data) {

    }

    @Override
    public Date getDataCreacio() {
        return null;
    }

    @Override
    public void setDataLimitRetorn(Date data) {

    }

    @Override
    public Date getDataLimitRetorn() {
        return null;
    }

    @Override
    public String tipusPrestec() {
        return "";
    }

    @Override
    public void setRetornat(boolean retornat) {

    }

    @Override
    public boolean getRetornat() {
        return false;
    }

    @Override
    public void retorna() {

    }

    @Override
    public long duradaPrestec() {
        return 0;
    }

    @Override
    public boolean prestecEndarrerit() {
        return false;
    }
}
