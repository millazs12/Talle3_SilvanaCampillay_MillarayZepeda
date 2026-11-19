package taller3;

// Subclase Vampiro con habilidad de morder y volar
public class Vampiro extends Monstruo implements Mordedor, Volador {
    private int velocidadDeVuelo;
    private int resistenciaAlSol;

    public Vampiro(int vida, int velocidad, int fuerza, int resistencia, int velocidadDeVuelo, int resistenciaAlSol) {
        super("Vampiro", vida, velocidad, fuerza, resistencia);
        this.velocidadDeVuelo = velocidadDeVuelo;
        this.resistenciaAlSol = resistenciaAlSol;
    }

    @Override
    public String morder() {
        return "¡El vampiro clava sus colmillos!";
    }

    @Override
    public String volar() {
        return "¡El vampiro despliega sus alas y alza el vuelo!";
    }

    @Override
    public double calcularPotencialCarrera() {
        return (velocidad + 2.0 * velocidadDeVuelo) / 2.0;
    }

    @Override
    public double calcularPotencialCalabazas() {
        return (double) (fuerza + velocidad) / 3.0;
    }

    @Override
    public double calcularPotencialLapidas() {
        return (double) fuerza + (double) velocidadDeVuelo / 4.0;
    }

    @Override
    public double calcularPotencialAmanecer() {
        return (double) (resistencia * resistenciaAlSol) / 100.0;
    }

    @Override
    public void mostrarAtributos() {
        System.out.println("Vampiro    | Vida " + vida + " | Vel " + velocidad + " | Fue " + fuerza + " | Res " + resistencia + " | Vuelo " + velocidadDeVuelo + " | Sol " + resistenciaAlSol);
    }
}