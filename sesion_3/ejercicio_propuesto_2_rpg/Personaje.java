public class Personaje {
    protected String nombre;
    private int salud;
    protected int nivel;
    private static int contadorPersonajes = 0;
    public static final int SALUD_MAXIMA = 100;
    public static final String MUNDO = "Mundo RPG UCSM";

    public Personaje(String nombre, int nivel) {
        this.nombre = nombre;
        this.salud = SALUD_MAXIMA;
        this.nivel = nivel;
        contadorPersonajes++;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        if (salud >= 0 && salud <= SALUD_MAXIMA) {
            this.salud = salud;
        }
    }

    protected void recibirDanio(int danio) {
        salud = salud - danio;
        if (salud < 0) {
            salud = 0;
        }
        System.out.println(nombre + " tiene " + salud + " de salud.");
    }

    void subirNivel() {
        nivel++;
    }

    private boolean estaVivo() {
        return salud > 0;
    }

    public static int getContadorPersonajes() {
        return contadorPersonajes;
    }
}
