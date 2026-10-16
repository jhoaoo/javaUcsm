import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Path archivo = Paths.get("datos.txt");
        System.out.println("Ingrese líneas de texto. Escriba FIN para terminar:");
        try (BufferedWriter salida = Files.newBufferedWriter(archivo,
                StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            while (true) {
                String linea = teclado.nextLine();
                if (linea.equalsIgnoreCase("FIN")) break;
                salida.write(linea);
                salida.newLine();
            }
            System.out.println("Datos añadidos al archivo: " + archivo.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
}
