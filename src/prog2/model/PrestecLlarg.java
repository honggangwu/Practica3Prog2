package prog2.model;

public class PrestecLlarg extends Prestec {

    public PrestecLlarg(Exemplar exemplar, Usuari usuari) {
        super(exemplar, usuari);
    }

    @Override
    public String tipusPrestec() {
        return "Llarg";
    }

    @Override
    public long duradaPrestec() {
        // 140 segons convertits a mil·lisegons
        return 140L * 1000;
    }
}
