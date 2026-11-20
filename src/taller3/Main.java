package taller3;

// Millaray Zepeda - 22.063.994-0 - ICCI
// Silvana Campillay - 21.862.853-2 - ICI

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Clase principal con lectura de archivos y menus por consola
public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        System.out.println("Cargando archivos...");

        // 1. Cargar Monstruos.txt intentando abrirlo directamente
        File fMonstruos = new File("Monstruos.txt");
        try {
            Scanner lectorM = new Scanner(fMonstruos);
            int contM = 0;
            while (lectorM.hasNextLine()) {
                String linea = lectorM.nextLine();
                if (linea.length() > 0) {
                    String[] partes = linea.split(";");
                    boolean valido = sistema.agregarMonstruo(partes);
                    if (valido) {
                        contM = contM + 1;
                    } else {
                        System.out.println("[!] Linea descartada en Monstruos.txt: " + linea);
                    }
                }
            }
            lectorM.close();
            System.out.println(" > " + contM + " monstruos cargados desde Monstruos.txt");

            if (contM == 0) {
                System.out.println("[!] No hay ningun monstruo valido. Terminando programa.");
                return;
            }
        } catch (FileNotFoundException e) {
            System.out.println("[!] El archivo Monstruos.txt no existe. El programa terminara.");
            return;
        }

        // 2. Cargar Registros.txt intentando abrirlo directamente
        File fRegistros = new File("Registros.txt");
        try {
            Scanner lectorR = new Scanner(fRegistros);
            int contR = 0;
            while (lectorR.hasNextLine()) {
                String linea = lectorR.nextLine();
                if (linea.length() > 0) {
                    String[] partes = linea.split(";");
                    if (partes.length == 2) {
                        try {
                            String nom = partes[0];
                            int ptos = Integer.parseInt(partes[1]);
                            if (sistema.agregarJugadorRegistro(nom, ptos)) {
                                contR = contR + 1;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("[!] Linea descartada en Registros.txt: " + linea);
                        }
                    }
                }
            }
            lectorR.close();
            System.out.println(" > " + contR + " jugadores cargados desde Registros.txt");
        } catch (FileNotFoundException e) {
            System.out.println(" > Registros.txt no existe, se creara al guardar.");
        }

        Scanner scanner = new Scanner(System.in);
        boolean appActiva = true;

        // Ciclo principal para pedir usuario e iniciar sesion
        while (appActiva) {
            System.out.println();
            System.out.println("=========================================");
            System.out.println("             SPOOKY SEASON");
            System.out.println("=========================================");
            System.out.print("Ingrese su nombre de usuario: ");
            String inputNombre = scanner.nextLine();

            int estadoSesion = sistema.iniciarSesion(inputNombre);
            if (estadoSesion == -1) {
                System.out.println("[!] El nombre no puede estar vacio ni contener ';'.");
            } else {
                Jugador jActual = sistema.getJugadorActual();
                if (estadoSesion == 2) {
                    System.out.println();
                    System.out.println("¡Bienvenido de vuelta, " + jActual.getNombre() + "! Tu record actual es de " + jActual.getPuntajeMaximo() + " puntos.");
                } else {
                    System.out.println();
                    System.out.println("¡Bienvenido, " + jActual.getNombre() + "! Es tu primera vez jugando.");
                }

                boolean sesionActiva = true;
                // Menu principal mientras dure la sesion
                while (sesionActiva) {
                    System.out.println();
                    System.out.println("------ MENU PRINCIPAL (" + jActual.getNombre() + ") ------");
                    System.out.println("1) Jugar un minijuego");
                    System.out.println("2) Ver catalogo de monstruos");
                    System.out.println("3) Ver historial de la sesion");
                    System.out.println("4) Ver ranking de jugadores");
                    System.out.println("5) Terminar sesion");
                    System.out.println("6) Salir");
                    System.out.print("> ");

                    String opStr = scanner.nextLine();
                    int opcion = -1;
                    try {
                        opcion = Integer.parseInt(opStr);
                    } catch (NumberFormatException e) {
                        System.out.println("[!] Debe ingresar un numero. Intente nuevamente.");
                    }

                    if (opcion == 1) {
                        menuMinijuegos(sistema, scanner);
                    } else {
                        if (opcion == 2) {
                            verCatalogo(sistema);
                        } else {
                            if (opcion == 3) {
                                verHistorial(sistema);
                            } else {
                                if (opcion == 4) {
                                    verRanking(sistema);
                                } else {
                                    if (opcion == 5) {
                                        System.out.println();
                                        System.out.println(sistema.finalizarSesion());
                                        if (sistema.haJugadoAlMenosUno()) {
                                            guardarRegistros(sistema.getListaJugadores());
                                            System.out.println("Registros guardados en Registros.txt.");
                                        }
                                        sesionActiva = false;
                                    } else {
                                        if (opcion == 6) {
                                            System.out.println();
                                            System.out.println(sistema.finalizarSesion());
                                            if (sistema.haJugadoAlMenosUno()) {
                                                guardarRegistros(sistema.getListaJugadores());
                                                System.out.println("Registros guardados en Registros.txt.");
                                            }
                                            sesionActiva = false;
                                            appActiva = false;
                                        } else {
                                            if (opcion != -1) {
                                                System.out.println("[!] Opcion fuera de rango. Intente nuevamente.");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        scanner.close();
    }

    // Menu y flujo para jugar uno de los minijuegos
    private static void menuMinijuegos(Sistema sistema, Scanner scanner) {
        String[] nombresMinijuegos = {
            "Carrera del Cementerio",
            "Caza de Calabazas",
            "Lanzamiento de Lapidas",
            "Sobrevivir al Amanecer"
        };
        String[] descripciones = {
            "recorre el cementerio de punta a punta antes de que el sepulturero te vea.",
            "muerde la mayor cantidad de calabazas del huerto en 60 segundos.",
            "lanza una lapida lo mas lejos posible.",
            "aguanta en pie cuando salgan los primeros rayos de sol."
        };
        String[] habilidadesPremiadas = { "Volar", "Morder", "Enfurecerse", "Regenerable" };

        boolean enMenuJuegos = true;
        while (enMenuJuegos) {
            boolean[] jugados = sistema.getMinijuegosJugados();
            int[] puntajes = sistema.getPuntajesMinijuegos();
            System.out.println();
            System.out.println("------ MINIJUEGOS ------");
            for (int i = 0; i < 4; i++) {
                String estado = "[PENDIENTE]";
                if (jugados[i]) {
                    estado = "[JUGADO - " + puntajes[i] + " pts]";
                }
                System.out.println((i + 1) + ") " + nombresMinijuegos[i] + "   " + estado);
            }
            System.out.println("0) Volver");
            System.out.print("> ");

            String opStr = scanner.nextLine();
            int opM = -1;
            try {
                opM = Integer.parseInt(opStr);
            } catch (NumberFormatException e) {
                System.out.println("[!] Debe ingresar un numero. Intente nuevamente.");
            }

            if (opM == 0) {
                enMenuJuegos = false;
            } else {
                if (opM >= 1 && opM <= 4) {
                    int indexJuego = opM - 1;
                    if (jugados[indexJuego]) {
                        System.out.println("[!] Ya jugaste " + nombresMinijuegos[indexJuego] + " en esta sesion.");
                    } else {
                        // Busca monstruos que aun no se han usado en la sesion
                        ArrayList<Monstruo> disponibles = new ArrayList<>();
                        for (int i = 0; i < sistema.getCatalogoMonstruos().size(); i++) {
                            Monstruo m = sistema.getCatalogoMonstruos().get(i);
                            if (!m.isUsado()) {
                                disponibles.add(m);
                            }
                        }

                        if (disponibles.size() == 0) {
                            System.out.println("[!] No quedan monstruos disponibles en esta sesion.");
                            enMenuJuegos = false;
                        } else {
                            boolean eligiendoMonstruo = true;
                            while (eligiendoMonstruo) {
                                System.out.println();
                                System.out.println(nombresMinijuegos[indexJuego] + ": " + descripciones[indexJuego]);
                                System.out.println();
                                System.out.println("Elige el monstruo en el que te transformaras:");
                                for (int i = 0; i < disponibles.size(); i++) {
                                    System.out.print((i + 1) + ") ");
                                    disponibles.get(i).mostrarAtributos();
                                }
                                System.out.println("0) Volver");
                                System.out.print("> ");

                                String opMonStr = scanner.nextLine();
                                int opMon = -1;
                                try {
                                    opMon = Integer.parseInt(opMonStr);
                                } catch (NumberFormatException e) {
                                    System.out.println("[!] Debe ingresar un numero. Intente nuevamente.");
                                }

                                if (opMon == 0) {
                                    eligiendoMonstruo = false;
                                } else {
                                    if (opMon >= 1 && opMon <= disponibles.size()) {
                                        Monstruo elegido = disponibles.get(opMon - 1);

                                        // Calcula el potencial segun el minijuego elegido
                                        double potencial = 0.0;
                                        if (indexJuego == 0) {
                                            potencial = elegido.calcularPotencialCarrera();
                                        } else {
                                            if (indexJuego == 1) {
                                                potencial = elegido.calcularPotencialCalabazas();
                                            } else {
                                                if (indexJuego == 2) {
                                                    potencial = elegido.calcularPotencialLapidas();
                                                } else {
                                                    if (indexJuego == 3) {
                                                        potencial = elegido.calcularPotencialAmanecer();
                                                    }
                                                }
                                            }
                                        }

                                        System.out.println();
                                        System.out.println("Te comes un dulce de dudosa procedencia...");

                                        double potencialRedondeado = Math.round(potencial * 100.0) / 100.0;
                                        System.out.println("Potencial para " + nombresMinijuegos[indexJuego] + ": " + potencialRedondeado);

                                        // Revisa que habilidades tiene el monstruo elegido
                                        ArrayList<String> nombresHabs = new ArrayList<>();
                                        if (elegido instanceof Mordedor) {
                                            nombresHabs.add("Morder");
                                        }
                                        if (elegido instanceof Volador) {
                                            nombresHabs.add("Volar");
                                        }
                                        if (elegido instanceof Regenerable) {
                                            nombresHabs.add("Regenerarse");
                                        }
                                        if (elegido instanceof Furioso) {
                                            nombresHabs.add("Enfurecerse");
                                        }

                                        boolean eligiendoHabilidad = true;
                                        while (eligiendoHabilidad) {
                                            System.out.println();
                                            System.out.println("¿Quieres usar una habilidad?");
                                            for (int i = 0; i < nombresHabs.size(); i++) {
                                                System.out.println((i + 1) + ") " + nombresHabs.get(i));
                                            }
                                            System.out.println("0) No usar habilidad");
                                            System.out.print("> ");

                                            String habStr = scanner.nextLine();
                                            int opHab = -1;
                                            try {
                                                opHab = Integer.parseInt(habStr);
                                            } catch (NumberFormatException e) {
                                                System.out.println("[!] Debe ingresar un numero. Intente nuevamente.");
                                            }

                                            if (opHab >= 0 && opHab <= nombresHabs.size()) {
                                                String habilidadUsadaNombre = "Ninguna";
                                                if (opHab > 0) {
                                                    habilidadUsadaNombre = nombresHabs.get(opHab - 1);

                                                    if (habilidadUsadaNombre.equals("Morder")) {
                                                        System.out.println(((Mordedor) elegido).morder());
                                                    } else {
                                                        if (habilidadUsadaNombre.equals("Volar")) {
                                                            System.out.println(((Volador) elegido).volar());
                                                        } else {
                                                            if (habilidadUsadaNombre.equals("Regenerarse")) {
                                                                System.out.println(((Regenerable) elegido).regenerarse());
                                                            } else {
                                                                if (habilidadUsadaNombre.equals("Enfurecerse")) {
                                                                    System.out.println(((Furioso) elegido).enfurecerse());
                                                                }
                                                            }
                                                        }
                                                    }

                                                    if (habilidadUsadaNombre.equals(habilidadesPremiadas[indexJuego])) {
                                                        System.out.println("¡La habilidad fue efectiva! Puntaje x1.5");
                                                    } else {
                                                        System.out.println("La habilidad no tuvo efecto en este minijuego.");
                                                    }
                                                }

                                                int puntajeFinal = sistema.calcularYRegistrarPuntaje(
                                                    indexJuego, elegido, habilidadUsadaNombre,
                                                    habilidadesPremiadas[indexJuego], nombresMinijuegos[indexJuego], potencial
                                                );

                                                System.out.println();
                                                System.out.println(">> Puntaje obtenido: " + puntajeFinal + " puntos");
                                                eligiendoHabilidad = false;
                                                eligiendoMonstruo = false;
                                                enMenuJuegos = false;
                                            } else {
                                                if (opHab != -1) {
                                                    System.out.println("[!] Opcion fuera de rango. Intente nuevamente.");
                                                }
                                            }
                                        }
                                    } else {
                                        if (opMon != -1) {
                                            System.out.println("[!] Opcion fuera de rango. Intente nuevamente.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (opM != -1) {
                        System.out.println("[!] Opcion fuera de rango. Intente nuevamente.");
                    }
                }
            }
        }
    }

    // Muestra todos los monstruos y si estan disponibles o usados
    private static void verCatalogo(Sistema sistema) {
        System.out.println();
        System.out.println("------ CATALOGO DE MONSTRUOS ------");
        for (int i = 0; i < sistema.getCatalogoMonstruos().size(); i++) {
            Monstruo m = sistema.getCatalogoMonstruos().get(i);
            String estado = "[DISPONIBLE]";
            if (m.isUsado()) {
                estado = "[USADO]     ";
            }
            System.out.print(estado + " ");
            m.mostrarAtributos();
        }
    }

    // Muestra el historial de la sesion actual
    private static void verHistorial(Sistema sistema) {
        System.out.println();
        System.out.println("------ HISTORIAL DE LA SESION ------");
        if (sistema.getHistorialSesion().size() == 0) {
            System.out.println("Aun no has jugado ningun minijuego en esta sesion.");
        } else {
            for (int i = 0; i < sistema.getHistorialSesion().size(); i++) {
                System.out.println((i + 1) + ". " + sistema.getHistorialSesion().get(i));
            }
            System.out.println("Puntaje acumulado: " + sistema.getPuntajeAcumuladoSesion() + " pts");
        }
    }

    // Muestra el ranking ordenado
    private static void verRanking(Sistema sistema) {
        System.out.println();
        System.out.println("------ RANKING ------");
        ArrayList<Jugador> ranking = sistema.getListaJugadores();
        for (int i = 0; i < ranking.size(); i++) {
            Jugador j = ranking.get(i);
            System.out.println((i + 1) + ". " + j.getNombre() + "         " + j.getPuntajeMaximo());
        }
    }

    // Guarda los registros en Registros.txt
    private static void guardarRegistros(ArrayList<Jugador> jugadores) {
        try {
            FileWriter writer = new FileWriter("Registros.txt");
            for (int i = 0; i < jugadores.size(); i++) {
                Jugador j = jugadores.get(i);
                writer.write(j.getNombre() + ";" + j.getPuntajeMaximo() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("[!] Error al guardar Registros.txt");
        }
    }
}