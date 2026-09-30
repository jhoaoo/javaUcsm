package propuestos;

import java.util.Objects;

/** Persona inmutable, con igualdad por nombre y edad. */
public final class Persona {
    private final String nombre;
    private final int edad;

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) return true;
        if (!(objeto instanceof Persona)) return false;
        Persona otra = (Persona) objeto;
        return edad == otra.edad && Objects.equals(nombre, otra.nombre);
    }

    @Override
    public int hashCode() { return Objects.hash(nombre, edad); }

    @Override
    public String toString() { return nombre + " (" + edad + " años)"; }
}
