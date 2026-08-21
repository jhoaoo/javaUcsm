public abstract class Persona implements Mostrable {
    private String nombre;
    private String codigo;
    private static int totalPersonas = 0;
    public static final String UNIVERSIDAD = "UCSM";

    public Persona(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        totalPersonas++;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCodigo() { return codigo; }
    public static int getTotalPersonas() { return totalPersonas; }

    public abstract String getRol();

    public void mostrarInformacion() {
        System.out.println(codigo + " - " + nombre + " - " + getRol());
    }
}
