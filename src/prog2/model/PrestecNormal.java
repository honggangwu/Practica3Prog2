package prog2.model;

public class PrestecNormal extends Prestec {

    public PrestecNormal(Exemplar exemplar, Usuari usuari) {
        super(exemplar, usuari);
    }

    @Override
    public String tipusPrestec() {
        return "Normal";
    }

    @Override
    public long duradaPrestec() {
        // 70 segons convertits a mil·lisegons
        return 70L * 1000;
    }
}
