package prog2.model;

import prog2.vista.BiblioException;
import java.io.Serializable;
import java.util.ArrayList;

public class Llista<T> implements InLlista<T>, Serializable {

    protected ArrayList<T> llista;

    // Constructor que inicialitza l'ArrayList
    public Llista() {
        this.llista = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return llista.size();
    }

    @Override
    public void afegir(T t) throws BiblioException {
        llista.add(t);
    }

    @Override
    public void esborrar(T t) {
        llista.remove(t);
    }

    @Override
    public T getAt(int position) {
        return llista.get(position);
    }

    @Override
    public void clear() {
        llista.clear();
    }

    @Override
    public boolean isEmpty() {
        return llista.isEmpty();
    }

    @Override
    public ArrayList<T> getArrayList() {
        return llista;
    }
}