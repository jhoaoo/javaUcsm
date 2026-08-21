public class Persona {
    private int id;
    private String nombre;
    private String apellido;
    private Cuenta cuenta;

    public Persona(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        int numero = 1000 + id;
        this.cuenta = new Cuenta(numero);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public Cuenta getCuenta() { return cuenta; }

    public String toString() {
        return "Persona: " + id + " - " + nombre + " " + apellido + ", " + cuenta;
    }
}
