package prog2.model;

import java.io.Serializable;
import java.util.Date;

public class PrestecNormal extends Prestec implements Serializable {

    // Constructor amb 3 paràmetres per satisfer el test
    public PrestecNormal(Exemplar exemplar, Usuari usuari, Date dataCreacio) {
        super(exemplar, usuari, dataCreacio);
    }

    @Override
    public String tipusPrestec() {
        return "Normal";
    }

    @Override
    public long duradaPrestec() {
        // 70 segons en mil·lisegons [cite: 25]
        return 70 * 1000L;
    }
}