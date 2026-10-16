import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Ruta de archivo o directorio: ");
        Path ruta = Paths.get(teclado.nextLine().trim());
        if (!Files.exists(ruta)) {
            System.out.println("La ruta no existe.");
            return;
        }
        try {
            System.out.println("Nombre: " + ruta.getFileName());
            System.out.println("Ruta absoluta: " + ruta.toAbsolutePath());
            System.out.println("¿Directorio?: " + Files.isDirectory(ruta));
            System.out.println("¿Ruta absoluta?: " + ruta.isAbsolute());
            System.out.println("Última modificación: " + Files.getLastModifiedTime(ruta));
            System.out.println("Tamaño (bytes): " + Files.size(ruta));
            if (Files.isDirectory(ruta)) {
                System.out.println("Contenido del directorio:");
                try (DirectoryStream<Path> elementos = Files.newDirectoryStream(ruta)) {
                    for (Path elemento : elementos) System.out.println(" - " + elemento.getFileName());
                }
            }
        } catch (IOException | SecurityException e) {
            System.out.println("No se pudo consultar la ruta: " + e.getMessage());
        }
    }
}
