package prog2.model;

import prog2.vista.BiblioException;
import java.io.Serializable;
import java.util.Iterator;

public class LlistaExemplars extends Llista<Exemplar> implements Serializable {

    @Override
    public void afegir(Exemplar exemplar) throws BiblioException {
        if (contains(exemplar.getId())) {
            throw new BiblioException("Ja existeix un exemplar amb aquest identificador.");
        }
        super.afegir(exemplar);
    }

    public boolean contains(String id) {
        Iterator<Exemplar> it = llista.iterator();
        while (it.hasNext()) {
            Exemplar e = it.next();
            if (e.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}