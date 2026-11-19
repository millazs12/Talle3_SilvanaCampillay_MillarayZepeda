package taller3;

// Subclase Momia con habilidad de regenerarse y enfurecerse
public class Momia extends Monstruo implements Regenerable, Furioso {
    private int longitudVendajes;

    public Momia(int vida, int velocidad, int fuerza, int resistencia, int longitudVendajes) {
        super("Momia", vida, velocidad, fuerza, resistencia);
        this.longitudVendajes = longitudVendajes;
    }

    @Override
    public String regenerarse() {
        return "¡La momia envuelve sus heridas con vendajes nuevos!";
    }

    @Override
    public String enfurecerse() {
        return "¡La momia desata una maldicion de furia ancestral!";
    }

    @Override
    public double calcularPotencialCarrera() {
        return (double) (velocidad * 100) / (100.0 + longitudVendajes);
    }

    @Override
    public double calcularPotencialCalabazas() {
        return (double) (longitudVendajes * fuerza) / 200.0;
    }

    @Override
    public double calcularPotencialLapidas() {
        return (double) fuerza + (double) longitudVendajes / 10.0;
    }

    @Override
    public double calcularPotencialAmanecer() {
        return ((double) (resistencia + vida) / 2.0) * (1.0 + (double) longitudVendajes / 300.0);
    }

    @Override
    public void mostrarAtributos() {
        System.out.println("Momia      | Vida " + vida + " | Vel " + velocidad + " | Fue " + fuerza + " | Res " + resistencia + " | Vendajes " + longitudVendajes);
    }
}