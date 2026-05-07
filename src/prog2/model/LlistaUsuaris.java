package prog2.model;

import prog2.vista.BiblioException;
import java.io.Serializable;
import java.util.Iterator;

public class LlistaUsuaris extends Llista<Usuari> implements Serializable {

    @Override
    public void afegir(Usuari usuari) throws BiblioException {
        Iterator<Usuari> it = llista.iterator();

        while (it.hasNext()) {
            Usuari u = it.next();
            // Comprovem si l'email ja existeix
            if (u.getEmail().equalsIgnoreCase(usuari.getEmail())) {
                throw new BiblioException("Ja existeix un usuari amb aquest correu electrònic.");
            }
        }

        // Si no hi ha duplicats, l'afegim utilitzant el mètode original de Llista
        super.afegir(usuari);
    }
}
