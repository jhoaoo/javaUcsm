import java.io.Serializable;

public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nif;
    private String nombre;
    private int edad;

    public Persona(String nif, String nombre, int edad) {
        this.nif = nif;
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nif + " | " + nombre + " | " + edad;
    }
}
