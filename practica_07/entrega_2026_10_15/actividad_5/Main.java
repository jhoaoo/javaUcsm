import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

class Persona {
    private final String nombre, telefono, direccion;
    Persona(String nombre, String telefono, String direccion) {
        this.nombre = nombre; this.telefono = telefono; this.direccion = direccion;
    }
    String getNombre() { return nombre; }
    public String toString() { return nombre + " | " + telefono + " | " + direccion; }
}

class Agenda {
    private final List<Persona> contactos = new ArrayList<>();
    void cargar(Path archivo) throws IOException {
        contactos.clear();
        try (BufferedReader entrada = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            while (true) {
                String nombre = entrada.readLine();
                if (nombre == null) break;
                if (nombre.isBlank()) continue;
                String telefono = entrada.readLine();
                String direccion = entrada.readLine();
                if (telefono == null || direccion == null) throw new IOException("Contacto incompleto.");
                contactos.add(new Persona(nombre, telefono, direccion));
            }
        }
    }
    Persona buscar(String nombre) {
        for (Persona persona : contactos)
            if (persona.getNombre().equalsIgnoreCase(nombre)) return persona;
        return null;
    }
    int total() { return contactos.size(); }
}

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Agenda agenda = new Agenda();
        Path archivo = Paths.get("agenda.txt");
        if (!Files.exists(archivo)) {
            try {
                Files.write(archivo, Arrays.asList("Juan", "999111222", "Calle Uno 123",
                        "María", "988777666", "Av. Arequipa 200"), StandardCharsets.UTF_8);
                System.out.println("Se creó agenda.txt con dos contactos de ejemplo.");
            } catch (IOException e) { System.out.println("Error: " + e.getMessage()); return; }
        }
        try {
            agenda.cargar(archivo);
            System.out.println("Contactos cargados: " + agenda.total());
            System.out.print("Nombre a buscar: ");
            Persona encontrado = agenda.buscar(teclado.nextLine().trim());
            System.out.println(encontrado == null ? "Contacto no encontrado." : encontrado);
        } catch (IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
    }
}
