package prog2.model;

import prog2.vista.BiblioException;
import java.io.Serializable;
import java.util.Iterator;

public class LlistaExemplars extends Llista<Exemplar> implements Serializable {

    @Override
    public void afegir(Exemplar exemplar) throws BiblioException {
        Iterator<Exemplar> it = llista.iterator();

        while (it.hasNext()) {
            Exemplar e = it.next();
            // Comprovem si l'identificador ja existeix
            if (e.getId().equals(exemplar.getId())) {
                throw new BiblioException("Ja existeix un exemplar amb aquest identificador.");
            }
        }

        // Si el bucle acaba i no ha saltat l'excepció, cridem el mètode del pare per afegir-lo
        super.afegir(exemplar);
    }
}
