import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static Persona[] cargarAgenda(Path archivo) throws IOException {
        Persona[] personas = new Persona[128];
        int cantidad = 0;
        try (BufferedReader entrada = Files.newBufferedReader(
                archivo, StandardCharsets.UTF_8)) {
            String nombre;
            while ((nombre = entrada.readLine()) != null) {
                String telefono = entrada.readLine();
                String direccion = entrada.readLine();
                if (telefono == null || direccion == null) {
                    throw new IOException("Contacto incompleto: " + nombre);
                }
                if (cantidad == personas.length) {
                    throw new IOException("La agenda admite 128 contactos.");
                }
                personas[cantidad++] =
                        new Persona(nombre, telefono, direccion);
            }
        }
        return personas;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Persona[] personas = cargarAgenda(Paths.get("agenda.txt"));
            while (true) {
                System.out.print("Nombre a buscar (Enter para salir): ");
                String nombre = sc.nextLine().trim();
                if (nombre.isEmpty()) {
                    break;
                }
                boolean encontrado = false;
                for (Persona persona : personas) {
                    if (persona != null &&
                            persona.getNombre().equalsIgnoreCase(nombre)) {
                        System.out.println(persona);
                        encontrado = true;
                    }
                }
                if (!encontrado) {
                    System.out.println("Contacto no encontrado.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
