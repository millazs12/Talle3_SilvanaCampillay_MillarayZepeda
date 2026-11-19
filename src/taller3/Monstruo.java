package taller3;

// Clase abstracta base con los atributos que comparten todos los monstruos
public abstract class Monstruo {
    protected String tipo;
    protected int vida;
    protected int velocidad;
    protected int fuerza;
    protected int resistencia;
    protected boolean usado;

    // Constructor con los datos comunes de cada monstruo
    public Monstruo(String tipo, int vida, int velocidad, int fuerza, int resistencia) {
        this.tipo = tipo;
        this.vida = vida;
        this.velocidad = velocidad;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.usado = false;
    }

    public String getTipo() {
        return tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getResistencia() {
        return resistencia;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    // Metodos que cada monstruo calcula segun su formula en cada minijuego
    public abstract double calcularPotencialCarrera();
    public abstract double calcularPotencialCalabazas();
    public abstract double calcularPotencialLapidas();
    public abstract double calcularPotencialAmanecer();

    // Muestra los atributos del monstruo en consola
    public abstract void mostrarAtributos();
}