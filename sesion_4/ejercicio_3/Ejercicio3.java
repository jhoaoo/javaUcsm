import java.util.NoSuchElementException;

class Biblioteca {
    private String[] libros = new String[5];
    private int cantidad = 0;

    public void agregarLibro(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El titulo no puede ser nulo o vacio.");
        }
        if (cantidad == libros.length) {
            throw new IllegalStateException("La biblioteca esta llena.");
        }
        libros[cantidad++] = titulo;
    }

    public String buscarLibro(String titulo) {
        for (int i = 0; i < cantidad; i++) {
            if (libros[i].equalsIgnoreCase(titulo)) return libros[i];
        }
        throw new NoSuchElementException("No se encontro el libro: " + titulo);
    }
}

public class Ejercicio3 {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        try {
            biblioteca.agregarLibro("Clean Code");
            biblioteca.agregarLibro("Effective Java");
            System.out.println("Encontrado: " + biblioteca.buscarLibro("Effective Java"));
            biblioteca.agregarLibro("");
        } catch (IllegalArgumentException e) {
            System.out.println("Dato invalido: " + e.getMessage());
        }

        try {
            biblioteca.buscarLibro("Libro inexistente");
        } catch (NoSuchElementException e) {
            System.out.println("Busqueda: " + e.getMessage());
        }
    }
}
