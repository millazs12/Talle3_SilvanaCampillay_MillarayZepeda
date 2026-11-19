package taller3;

// Guarda una jugada hecha dentro de la sesion actual
public class HistorialItem {
    private String minijuego;
    private String tipoMonstruo;
    private String habilidad;
    private int puntaje;

    public HistorialItem(String minijuego, String tipoMonstruo, String habilidad, int puntaje) {
        this.minijuego = minijuego;
        this.tipoMonstruo = tipoMonstruo;
        this.habilidad = habilidad;
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return minijuego + " | " + tipoMonstruo + " | Habilidad: " + habilidad + " | " + puntaje + " pts";
    }
}