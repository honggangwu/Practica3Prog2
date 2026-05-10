package prog2.model;

import prog2.vista.BiblioException;
import java.io.Serializable;
import java.util.Iterator;

public class LlistaUsuaris extends Llista<Usuari> implements Serializable {

    @Override
    public void afegir(Usuari usuari) throws BiblioException {
        if (contains(usuari.getEmail())) {
            throw new BiblioException("Ja existeix un usuari amb aquest correu electrònic.");
        }
        super.afegir(usuari);
    }

    public boolean contains(String email) {
        Iterator<Usuari> it = llista.iterator();
        while (it.hasNext()) {
            Usuari u = it.next();
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
}