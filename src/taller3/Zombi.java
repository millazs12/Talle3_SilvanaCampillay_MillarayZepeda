package taller3;

// Subclase Zombi con habilidad de morder y regenerarse
public class Zombi extends Monstruo implements Mordedor, Regenerable {
    private int fuerzaDeMordida;

    public Zombi(int vida, int velocidad, int fuerza, int resistencia, int fuerzaDeMordida) {
        super("Zombi", vida, velocidad, fuerza, resistencia);
        this.fuerzaDeMordida = fuerzaDeMordida;
    }

    @Override
    public String morder() {
        return "¡El zombi lanza una mordida feroz!";
    }

    @Override
    public String regenerarse() {
        return "¡El zombi regenera parte de su cuerpo!";
    }

    @Override
    public double calcularPotencialCarrera() {
        return (double) (velocidad * resistencia) / 100.0;
    }

    @Override
    public double calcularPotencialCalabazas() {
        return (double) (fuerzaDeMordida * vida) / 100.0 + (double) resistencia / 2.0;
    }

    @Override
    public double calcularPotencialLapidas() {
        return (double) (fuerza * resistencia) / 100.0;
    }

    @Override
    public double calcularPotencialAmanecer() {
        return (double) (resistencia * vida) / 100.0;
    }

    @Override
    public void mostrarAtributos() {
        System.out.println("Zombi      | Vida " + vida + " | Vel " + velocidad + " | Fue " + fuerza + " | Res " + resistencia + " | Mordida " + fuerzaDeMordida);
    }
}