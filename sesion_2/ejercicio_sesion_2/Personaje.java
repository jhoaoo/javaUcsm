public class Personaje {

    private String nombre;
    private int vida;
    private boolean infectado;
    private double velocidad;

    static int cantidadPersonajes = 0;

    final String JUEGO = "Left 4 Dead 2";

    public Personaje(String nombre, int vida, boolean infectado, double velocidad) {
        this.nombre = nombre;
        this.vida = vida;
        this.infectado = infectado;
        this.velocidad = velocidad;

        cantidadPersonajes++;
    }

    public void mostrarEstado() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Vida: " + vida);
        System.out.println("Velocidad: " + velocidad);
        System.out.println();
    }

    public void atacar(Personaje enemigo) {
        System.out.println(nombre + " dispara a " + enemigo.nombre);

        enemigo.recibirDanio(20);
    }

    public void saltar(Personaje enemigo) {
        System.out.println(nombre + " salta sobre " + enemigo.nombre);

        enemigo.recibirDanio(15);
    }

    protected void recibirDanio(int danio) {
        vida = vida - danio;

        if (vida <= 0) {
            vida = 0;
            System.out.println(nombre + " ha muerto.");
        } else {
            System.out.println(nombre + " tiene " + vida + " de vida.");
        }

        System.out.println();
    }

    private boolean estaVivo() {
        return vida > 0;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public static void main(String[] args) {

        Personaje coach = new Personaje(
                "Coach",
                100,
                false,
                5.0
        );

        Personaje hunter = new Personaje(
                "Hunter",
                40,
                true,
                8.0
        );

        System.out.println("=== LEFT 4 DEAD 2 ===");
        System.out.println();

        coach.mostrarEstado();
        hunter.mostrarEstado();

        hunter.saltar(coach);

        coach.atacar(hunter);

        coach.atacar(hunter);

        System.out.println("Vida de Coach: " + coach.getVida());

        coach.setVida(100);

        System.out.println("Coach usa un botiquin.");
        System.out.println("Vida de Coach: " + coach.getVida());

        System.out.println();
        System.out.println("Personajes creados: " + cantidadPersonajes);
    }
}
