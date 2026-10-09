import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // true permite conservar el texto escrito anteriormente.
        try (PrintWriter salida = new PrintWriter(
                new FileWriter("datos.txt", StandardCharsets.UTF_8, true))) {
            System.out.println("Introduce texto. FIN termina la escritura.");
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                if (linea.equalsIgnoreCase("FIN")) {
                    break;
                }
                salida.println(linea);
            }
            if (salida.checkError()) {
                throw new IOException("No se pudo escribir el archivo.");
            }
            System.out.println("Texto agregado a datos.txt.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
