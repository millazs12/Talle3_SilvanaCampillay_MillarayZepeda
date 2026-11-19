package taller3;

// Subclase HombreLobo con habilidad de morder y enfurecerse
public class HombreLobo extends Monstruo implements Mordedor, Furioso {
    private int ferocidad;
    private int faseLunar;

    public HombreLobo(int vida, int velocidad, int fuerza, int resistencia, int ferocidad, int faseLunar) {
        super("HombreLobo", vida, velocidad, fuerza, resistencia);
        this.ferocidad = ferocidad;
        this.faseLunar = faseLunar;
    }

    @Override
    public String morder() {
        return "¡El hombre lobo da un mordisco salvaje!";
    }

    @Override
    public String enfurecerse() {
        return "¡El hombre lobo aulla a la luna y entra en frenesi!";
    }

    @Override
    public double calcularPotencialCarrera() {
        return (velocidad + (double) ferocidad / 5.0) * ((double) faseLunar / 4.0);
    }

    @Override
    public double calcularPotencialCalabazas() {
        return ((double) ferocidad * faseLunar) / 4.0 + (double) velocidad / 4.0;
    }

    @Override
    public double calcularPotencialLapidas() {
        return (double) fuerza + ((double) ferocidad * faseLunar) / 8.0;
    }

    @Override
    public double calcularPotencialAmanecer() {
        return ((double) (resistencia + vida) / 2.0) * ((5.0 - (double) faseLunar) / 4.0);
    }

    @Override
    public void mostrarAtributos() {
        System.out.println("HombreLobo | Vida " + vida + " | Vel " + velocidad + " | Fue " + fuerza + " | Res " + resistencia + " | Ferocidad " + ferocidad + " | Luna " + faseLunar);
    }
}