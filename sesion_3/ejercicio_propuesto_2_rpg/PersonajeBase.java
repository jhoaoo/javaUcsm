public abstract class PersonajeBase {
    protected String nombre;
    private int salud;
    protected int nivel;
    private Inventario inventario;
    private Habilidad[] habilidades = new Habilidad[3];
    private int cantidadHabilidades = 0;
    private static int contadorPersonajes = 0;
    public static final int SALUD_MAXIMA = 100;
    public static final String MUNDO = "Mundo RPG UCSM";

    public PersonajeBase(String nombre, int nivel, Inventario inventario) {
        this.nombre = nombre;
        this.salud = SALUD_MAXIMA;
        this.nivel = nivel;
        this.inventario = inventario;
        contadorPersonajes++;
    }

    public abstract void atacar(PersonajeBase objetivo);

    public void usarHabilidad(PersonajeBase objetivo) {
        usarHabilidad(0, objetivo);
    }

    public void usarHabilidad(int indice, PersonajeBase objetivo) {
        if (indice >= 0 && indice < cantidadHabilidades) {
            Habilidad habilidad = habilidades[indice];
            System.out.println(nombre + " usa " + habilidad.getNombre());
            objetivo.recibirDanio(habilidad.getDanio());
        }
    }

    protected void agregarHabilidad(String nombre, int danio) {
        if (cantidadHabilidades < habilidades.length) {
            habilidades[cantidadHabilidades] = new Habilidad(nombre, danio);
            cantidadHabilidades++;
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

    public int getSalud() { return salud; }

    public void setSalud(int salud) {
        if (salud >= 0 && salud <= SALUD_MAXIMA) {
            this.salud = salud;
        }
    }

    public Inventario getInventario() { return inventario; }
    public static int getContadorPersonajes() { return contadorPersonajes; }
}
