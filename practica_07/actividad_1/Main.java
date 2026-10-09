import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    public static String informar(Path ruta) throws IOException {
        if (!Files.exists(ruta)) {
            return "La ruta no existe: " + ruta;
        }
        StringBuilder texto = new StringBuilder();
        texto.append("Nombre: ").append(ruta.getFileName());
        texto.append("\nDirectorio: ").append(Files.isDirectory(ruta));
        texto.append("\nRuta absoluta: ").append(ruta.isAbsolute());
        texto.append("\nModificacion: ");
        texto.append(Files.getLastModifiedTime(ruta));
        texto.append("\nTamano en bytes: ").append(Files.size(ruta));
        texto.append("\nRuta: ").append(ruta);
        texto.append("\nRuta completa: ").append(ruta.toAbsolutePath());
        if (Files.isDirectory(ruta)) {
            texto.append("\nContenido del directorio:");
            try (DirectoryStream<Path> archivos =
                    Files.newDirectoryStream(ruta)) {
                for (Path archivo : archivos) {
                    texto.append("\n").append(archivo.getFileName());
                }
            }
        }
        return texto.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Archivo o directorio: ");
        try {
            System.out.println(informar(Paths.get(sc.nextLine())));
        } catch (IOException | InvalidPathException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
