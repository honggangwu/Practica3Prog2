package prog2.vista;

/**
 * Classe principal per iniciar l'aplicació
 */
public class Prog2 {

    public static void main(String[] args) {
        // 1. Inicialitzem la vista (que crearà l'adaptador i les dades)
        BiblioUB biblioteca = new BiblioUB();

        // 2. Cridem al mètode que gestiona els menús
        biblioteca.gestioBiblioUB();
    }
}