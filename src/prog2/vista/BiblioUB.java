package prog2.vista;

import java.util.List;
import java.util.Scanner;
import prog2.adaptador.Adaptador;

public class BiblioUB {

    static private enum OpcionsMenuPrincipal {
        MENU_PRINCIPAL_EXEMPLARS,
        MENU_PRINCIPAL_USUARIS,
        MENU_PRINCIPAL_PRESTECS,
        MENU_PRINCIPAL_SAVE,
        MENU_PRINCIPAL_LOAD,
        MENU_PRINCIPAL_EXIT};

    static private String[] descMenuPrincipal={"Gestió Exemplars",
            "Gestió Usuaris",
            "Gestió Prestecs",
            "Guardar Dades",
            "Recuperar Dades",
            "Sortir"};

    static private enum OpcionsMenuGestioExemplars {
        MENU_GESTIO_EXEMPLARS_ADD,
        MENU_GESTIO_EXEMPLARS_VIEW,
        MENU_GESTIO_EXEMPLARS_EXIT
    };

    static private String[] descMenuGestioExemplars ={"Afegir Exemplar",
            "Visualitzar Exemplars",
            "Sortir"};

    static private enum OpcionsMenuGestioClients {
        MENU_GESTIO_USUARIS_ADD,
        MENU_GESTIO_USUARIS_VIEW,
        MENU_GESTIO_USUARIS_EXIT
    };

    static private String[] descMenuGestioUsuaris ={"Afegir Usuari",
            "Visualitzar Usuaris",
            "Sortir"};

    static private enum OpcionsMenuGestioPrestecs {
        MENU_GESTIO_PRESTECS_ADD,
        MENU_GESTIO_PRESTECS_REMOVE,
        MENU_GESTIO_PRESTECS_VIEW,
        MENU_GESTIO_PRESTECS_VIEW_URG,
        MENU_GESTIO_PRESTECS_EXIT
    };

    static private String[] descMenuGestioPrestecs ={"Afegir Prestec",
            "Retornar Prestec",
            "Visualitzar Prestecs",
            "Visualitzar Prestecs no Retornats",
            "Sortir"};

    private Adaptador adaptador;

    public BiblioUB() {
        adaptador = new Adaptador();
    }

    public void gestioBiblioUB() {
        Scanner sc = new Scanner(System.in);
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Menu principal", OpcionsMenuPrincipal.values());
        menu.setDescripcions(descMenuPrincipal);

        OpcionsMenuPrincipal opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            switch(opcio) {
                case MENU_PRINCIPAL_EXEMPLARS:
                    menuGestioExemplars(sc);
                    break;
                case MENU_PRINCIPAL_USUARIS:
                    menuGestioUsuaris(sc);
                    break;
                case MENU_PRINCIPAL_PRESTECS:
                    menuGestioPrestecs(sc);
                    break;
                case MENU_PRINCIPAL_SAVE:
                    String dstFile = getFilePath(sc,false);
                    if(dstFile != null) {
                        try {
                            this.adaptador.guardaDades(dstFile);
                            System.err.println("Dades guardades");
                        } catch (BiblioException ex) {
                            System.out.println("Error guardant les dades: " + ex.getMessage());
                        }
                    }
                    break;
                case MENU_PRINCIPAL_LOAD:
                    String srcFile = getFilePath(sc,false);
                    if(srcFile != null) {
                        try {
                            this.adaptador.carregaDades(srcFile);
                            System.err.println("Dades carregades");
                        } catch(BiblioException ex) {
                            System.out.println("Error carregant les dades." + ex.getMessage());
                        }
                    }
                    break;
                case MENU_PRINCIPAL_EXIT:
                    System.err.println("Sortint de l'aplicació...");
                    break;
            }
        } while(opcio != OpcionsMenuPrincipal.MENU_PRINCIPAL_EXIT);
    }

    // --- IMPLEMENTACIÓ DELS MÈTODES QUE FALTAVEN ---

    private void menuGestioExemplars(Scanner sc) {
        Menu<OpcionsMenuGestioExemplars> menu = new Menu<>("Gestió Exemplars", OpcionsMenuGestioExemplars.values());
        menu.setDescripcions(descMenuGestioExemplars);
        OpcionsMenuGestioExemplars opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);
            switch(opcio) {
                case MENU_GESTIO_EXEMPLARS_ADD:
                    afegirExemplar(sc);
                    break;
                case MENU_GESTIO_EXEMPLARS_VIEW:
                    showList("LLISTA D'EXEMPLARS", adaptador.recuperaExemplars());
                    break;
            }
        } while (opcio != OpcionsMenuGestioExemplars.MENU_GESTIO_EXEMPLARS_EXIT);
    }

    private void afegirExemplar(Scanner sc){
        System.out.println("Entra l'identificador:");
        String id = sc.nextLine();
        System.out.println("Entra el títol:");
        String titol = sc.nextLine();
        System.out.println("Entra l'autor:");
        String autor = sc.nextLine();
        System.out.println("Admet préstec llarg? (true/false):");
        boolean admetLlarg = sc.nextBoolean();
        sc.nextLine(); // Neteja de buffer

        try {
            adaptador.afegirExemplar(id, titol, autor, admetLlarg);
            System.out.println("Exemplar afegit correctament.");
        } catch (BiblioException e) {
            System.out.println(e.getMessage());
        }
    }

    private void menuGestioUsuaris(Scanner sc) {
        Menu<OpcionsMenuGestioClients> menu = new Menu<>("Gestió Usuaris", OpcionsMenuGestioClients.values());
        menu.setDescripcions(descMenuGestioUsuaris);
        OpcionsMenuGestioClients opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);
            switch(opcio) {
                case MENU_GESTIO_USUARIS_ADD:
                    afegirUsuari(sc);
                    break;
                case MENU_GESTIO_USUARIS_VIEW:
                    showList("LLISTA D'USUARIS", adaptador.recuperaUsuaris());
                    break;
            }
        } while (opcio != OpcionsMenuGestioClients.MENU_GESTIO_USUARIS_EXIT);
    }

    private void afegirUsuari(Scanner sc){
        System.out.println("Entra el correu electrònic:");
        String email = sc.nextLine();
        System.out.println("Entra el nom:");
        String nom = sc.nextLine();
        System.out.println("Entra l'adreça:");
        String adreca = sc.nextLine();
        System.out.println("És estudiant? (true/false):");
        boolean esEstudiant = sc.nextBoolean();
        sc.nextLine(); // Neteja de buffer

        try {
            adaptador.afegirUsuari(email, nom, adreca, esEstudiant);
            System.out.println("Usuari afegit correctament.");
        } catch (BiblioException e) {
            System.out.println(e.getMessage());
        }
    }

    private void menuGestioPrestecs(Scanner sc) {
        Menu<OpcionsMenuGestioPrestecs> menu = new Menu<>("Gestió Prestecs", OpcionsMenuGestioPrestecs.values());
        menu.setDescripcions(descMenuGestioPrestecs);
        OpcionsMenuGestioPrestecs opcio;
        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);
            switch(opcio) {
                case MENU_GESTIO_PRESTECS_ADD:
                    afegirPrestec(sc);
                    break;
                case MENU_GESTIO_PRESTECS_REMOVE:
                    cancelarPrestec(sc);
                    break;
                case MENU_GESTIO_PRESTECS_VIEW:
                    showList("LLISTA DE TOTS ELS PRESTECS", adaptador.recuperaPrestecs());
                    break;
                case MENU_GESTIO_PRESTECS_VIEW_URG:
                    showList("PRESTECS NO RETORNATS", adaptador.recuperaPrestecsNoRetornats());
                    break;
            }
        } while (opcio != OpcionsMenuGestioPrestecs.MENU_GESTIO_PRESTECS_EXIT);
    }

    private void afegirPrestec(Scanner sc){
        showList("SELECCIONA EXEMPLAR", adaptador.recuperaExemplars());
        System.out.println("Entra la posició de l'exemplar:");
        int exPos = sc.nextInt();

        showList("SELECCIONA USUARI", adaptador.recuperaUsuaris());
        System.out.println("Entra la posició de l'usuari:");
        int usPos = sc.nextInt();

        System.out.println("És un préstec llarg? (true/false):");
        boolean esLlarg = sc.nextBoolean();
        sc.nextLine(); // Neteja de buffer

        try {
            adaptador.afegirPrestec(exPos, usPos, esLlarg);
            System.out.println("Préstec registrat.");
        } catch (BiblioException e) {
            System.out.println(e.getMessage());
        }
    }

    private void cancelarPrestec(Scanner sc){
        showList("SELECCIONA PRESTEC A RETORNAR", adaptador.recuperaPrestecsNoRetornats());
        System.out.println("Entra la posició del préstec:");
        int pos = sc.nextInt();
        sc.nextLine(); // Neteja de buffer

        try {
            adaptador.retornarPrestec(pos);
            System.out.println("Préstec retornat correctament.");
        } catch (BiblioException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showList(String title, List<String> lines) {
        System.out.println("============================================");
        System.out.println(title);
        System.out.println("============================================");
        int i = 0;
        for(String l : lines) {
            System.out.println("\t[" + (i++) + "] " + l);
        }
        System.out.println("============================================");
    }

    private String getFilePath(Scanner sc, boolean mustExist) {
        System.out.println("Entra ruta completa fitxer (o ENTER per ometre):");
        String filePath = sc.nextLine();
        if(filePath.isEmpty()) {
            return null;
        }
        return filePath;
    }
}