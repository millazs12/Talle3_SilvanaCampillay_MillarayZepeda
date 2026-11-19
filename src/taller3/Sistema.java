package taller3;

import java.util.ArrayList;
import java.util.LinkedList;

// Clase con la logica del juego, validaciones y manejo de listas
public class Sistema {
    private ArrayList<Monstruo> catalogoMonstruos;
    private ArrayList<Jugador> listaJugadores;
    private Jugador jugadorActual;

    private boolean[] minijuegosJugados;
    private int[] puntajesMinijuegos;
    private LinkedList<HistorialItem> historialSesion;
    private int puntajeAcumuladoSesion;

    // Inicializa las listas y variables de control
    public Sistema() {
        this.catalogoMonstruos = new ArrayList<>();
        this.listaJugadores = new ArrayList<>();
        this.minijuegosJugados = new boolean[4];
        this.puntajesMinijuegos = new int[4];
        this.historialSesion = new LinkedList<>();
        this.puntajeAcumuladoSesion = 0;
    }

    // Revisa letra por letra si el texto tiene ';'
    private boolean tienePuntoYComa(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            String letra = texto.substring(i, i + 1);
            if (letra.equals(";")) {
                return true;
            }
        }
        return false;
    }

    // Revisa si el texto esta vacio o solo tiene espacios
    private boolean esSoloEspacios(String texto) {
        if (texto.length() == 0) {
            return true;
        }
        for (int i = 0; i < texto.length(); i++) {
            String letra = texto.substring(i, i + 1);
            if (!letra.equals(" ")) {
                return false;
            }
        }
        return true;
    }

    // Valida una linea de Monstruos.txt y agrega el monstruo si todo esta bien
    public boolean agregarMonstruo(String[] partes) {
        try {
            String tipo = partes[0];
            int vida = Integer.parseInt(partes[1]);
            int vel = Integer.parseInt(partes[2]);
            int fue = Integer.parseInt(partes[3]);
            int res = Integer.parseInt(partes[4]);

            // Revisa rangos de atributos base
            if (vida < 1 || vida > 200 || vel < 1 || vel > 100 || fue < 1 || fue > 100 || res < 1 || res > 100) {
                return false;
            }

            Monstruo m = null;
            // Revisa cantidad de datos y rangos propios segun el tipo
            if (tipo.equals("Zombi") && partes.length == 6) {
                int mordida = Integer.parseInt(partes[5]);
                if (mordida >= 1 && mordida <= 100) {
                    m = new Zombi(vida, vel, fue, res, mordida);
                }
            } else {
                if (tipo.equals("Vampiro") && partes.length == 7) {
                    int vuelo = Integer.parseInt(partes[5]);
                    int sol = Integer.parseInt(partes[6]);
                    if (vuelo >= 1 && vuelo <= 100 && sol >= 1 && sol <= 100) {
                        m = new Vampiro(vida, vel, fue, res, vuelo, sol);
                    }
                } else {
                    if (tipo.equals("Momia") && partes.length == 6) {
                        int vendajes = Integer.parseInt(partes[5]);
                        if (vendajes >= 1 && vendajes <= 300) {
                            m = new Momia(vida, vel, fue, res, vendajes);
                        }
                    } else {
                        if (tipo.equals("HombreLobo") && partes.length == 7) {
                            int fero = Integer.parseInt(partes[5]);
                            int luna = Integer.parseInt(partes[6]);
                            if (fero >= 1 && fero <= 100 && luna >= 1 && luna <= 4) {
                                m = new HombreLobo(vida, vel, fue, res, fero, luna);
                            }
                        }
                    }
                }
            }

            if (m != null) {
                catalogoMonstruos.add(m);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    // Agrega un jugador valido leido desde Registros.txt
    public boolean agregarJugadorRegistro(String nombre, int puntaje) {
        if (nombre == null || esSoloEspacios(nombre) || puntaje < 0) {
            return false;
        }
        if (tienePuntoYComa(nombre)) {
            return false;
        }
        listaJugadores.add(new Jugador(nombre, puntaje));
        return true;
    }

    // Inicia sesion (-1 si nombre no sirve, 1 si es nuevo, 2 si ya existia)
    public int iniciarSesion(String nombre) {
        if (nombre == null || esSoloEspacios(nombre)) {
            return -1;
        }
        if (tienePuntoYComa(nombre)) {
            return -1;
        }

        jugadorActual = null;
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (listaJugadores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                jugadorActual = listaJugadores.get(i);
            }
        }

        int estado = 1;
        if (jugadorActual == null) {
            jugadorActual = new Jugador(nombre, 0);
        } else {
            estado = 2;
        }

        // Limpia datos de la sesion anterior y deja todos los monstruos libres
        minijuegosJugados = new boolean[4];
        puntajesMinijuegos = new int[4];
        historialSesion.clear();
        puntajeAcumuladoSesion = 0;

        for (int i = 0; i < catalogoMonstruos.size(); i++) {
            catalogoMonstruos.get(i).setUsado(false);
        }
        return estado;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public ArrayList<Monstruo> getCatalogoMonstruos() {
        return catalogoMonstruos;
    }

    public boolean[] getMinijuegosJugados() {
        return minijuegosJugados;
    }

    public int[] getPuntajesMinijuegos() {
        return puntajesMinijuegos;
    }

    // Calcula el puntaje final con multiplicador y guarda en historial y sesion
    public int calcularYRegistrarPuntaje(int indexMinijuego, Monstruo monstruo, String habilidadUsada, String habilidadPremiada, String nombreMinijuego, double potencial) {
        double multiplicador = 1.0;
        if (habilidadUsada.equals(habilidadPremiada)) {
            multiplicador = 1.5;
        }

        int puntaje = (int) Math.round(potencial * 10.0 * multiplicador);
        minijuegosJugados[indexMinijuego] = true;
        puntajesMinijuegos[indexMinijuego] = puntaje;
        monstruo.setUsado(true);
        puntajeAcumuladoSesion = puntajeAcumuladoSesion + puntaje;
        historialSesion.add(new HistorialItem(nombreMinijuego, monstruo.getTipo(), habilidadUsada, puntaje));
        return puntaje;
    }

    public int getPuntajeAcumuladoSesion() {
        return puntajeAcumuladoSesion;
    }

    public LinkedList<HistorialItem> getHistorialSesion() {
        return historialSesion;
    }

    // Revisa si se jugo al menos un minijuego en la sesion
    public boolean haJugadoAlMenosUno() {
        for (int i = 0; i < minijuegosJugados.length; i++) {
            if (minijuegosJugados[i]) {
                return true;
            }
        }
        return false;
    }

    // Actualiza record si corresponde y retorna mensaje al cerrar sesion
    public String finalizarSesion() {
        if (!haJugadoAlMenosUno()) {
            return "No jugaste ningun minijuego, no se guardo registro.";
        }

        boolean existeEnLista = false;
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (listaJugadores.get(i) == jugadorActual) {
                existeEnLista = true;
            }
        }

        if (!existeEnLista) {
            jugadorActual.setPuntajeMaximo(puntajeAcumuladoSesion);
            listaJugadores.add(jugadorActual);
            return "Sesion terminada. Puntaje total: " + puntajeAcumuladoSesion + " puntos.";
        } else {
            int recordAnterior = jugadorActual.getPuntajeMaximo();
            if (puntajeAcumuladoSesion > recordAnterior) {
                jugadorActual.setPuntajeMaximo(puntajeAcumuladoSesion);
                return "Sesion terminada. Puntaje total: " + puntajeAcumuladoSesion + " puntos. ¡Superaste tu record anterior (" + recordAnterior + ")!";
            } else {
                return "Sesion terminada. Puntaje total: " + puntajeAcumuladoSesion + " puntos. No superaste tu record (" + recordAnterior + ").";
            }
        }
    }

    // Ordena el ranking de mayor a menor puntaje y por nombre si empatan
    public void ordenarRanking() {
        for (int i = 0; i < listaJugadores.size() - 1; i++) {
            for (int j = 0; j < listaJugadores.size() - i - 1; j++) {
                Jugador j1 = listaJugadores.get(j);
                Jugador j2 = listaJugadores.get(j + 1);

                boolean cambiar = false;
                if (j2.getPuntajeMaximo() > j1.getPuntajeMaximo()) {
                    cambiar = true;
                } else if (j2.getPuntajeMaximo() == j1.getPuntajeMaximo()) {
                    if (j2.getNombre().compareToIgnoreCase(j1.getNombre()) < 0) {
                        cambiar = true;
                    }
                }

                if (cambiar) {
                    listaJugadores.set(j, j2);
                    listaJugadores.set(j + 1, j1);
                }
            }
        }
    }

    public ArrayList<Jugador> getListaJugadores() {
        ordenarRanking();
        return listaJugadores;
    }
}