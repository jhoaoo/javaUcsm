import java.util.NoSuchElementException;

class RegistroEstudiantes {
    private String[] estudiantes = new String[6];
    private int cantidad = 0;

    public void agregarEstudiante(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacio.");
        }
        if (cantidad == estudiantes.length) {
            throw new IllegalStateException("No hay espacio para mas estudiantes.");
        }
        estudiantes[cantidad++] = nombre;
    }

    public String buscarEstudiante(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (estudiantes[i].equalsIgnoreCase(nombre)) return estudiantes[i];
        }
        throw new NoSuchElementException("Estudiante no encontrado: " + nombre);
    }
}

public class Ejercicio5 {
    public static void main(String[] args) {
        RegistroEstudiantes registro = new RegistroEstudiantes();
        try {
            registro.agregarEstudiante("Andrea");
            registro.agregarEstudiante("Miguel");
            System.out.println("Encontrado: " + registro.buscarEstudiante("Andrea"));
            registro.agregarEstudiante(" ");
        } catch (IllegalArgumentException e) {
            System.out.println("Registro invalido: " + e.getMessage());
        }

        try {
            registro.buscarEstudiante("Pedro");
        } catch (NoSuchElementException e) {
            System.out.println("Busqueda: " + e.getMessage());
        }
    }
}
