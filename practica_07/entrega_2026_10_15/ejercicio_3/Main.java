import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        JFileChooser selector = new JFileChooser();
        while (true) {
            if (selector.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) return;

            File archivo = selector.getSelectedFile();
            if (!archivo.isFile() || !archivo.canRead()) {
                JOptionPane.showMessageDialog(null, "Escoge un archivo que se pueda leer.");
                continue;
            }
            try {
                contarPalabras(archivo);
                break;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No se pudo leer: " + e.getMessage());
            }
        }
    }

    static void contarPalabras(File archivo) throws IOException {
        int lineas = 0, palabras = 0, caracteres = 0;
        Map<String, Integer> frecuencia = new HashMap<>();

        try (BufferedReader lector = new BufferedReader(
                new FileReader(archivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas++;
                caracteres += linea.length();
                StringBuilder palabra = new StringBuilder();

                for (int i = 0; i <= linea.length(); i++) {
                    char letra = i < linea.length() ? linea.charAt(i) : ' ';
                    if (Character.isLetterOrDigit(letra)) {
                        palabra.append(Character.toLowerCase(letra));
                    } else if (palabra.length() > 0) {
                        String texto = palabra.toString();
                        frecuencia.put(texto, frecuencia.getOrDefault(texto, 0) + 1);
                        palabras++;
                        palabra.setLength(0);
                    }
                }
            }
        }

        System.out.println("Total de líneas: " + lineas);
        System.out.println("Total de palabras: " + palabras);
        System.out.println("Total de caracteres: " + caracteres);
        double promedio = lineas == 0 ? 0 : (double) palabras / lineas;
        System.out.printf("Promedio de palabras por línea: %.2f%n", promedio);
        System.out.println("Palabras más frecuentes:");

        for (int i = 0; i < 10 && !frecuencia.isEmpty(); i++) {
            String mayor = null;
            for (String palabra : frecuencia.keySet()) {
                if (mayor == null || frecuencia.get(palabra) > frecuencia.get(mayor)) {
                    mayor = palabra;
                }
            }
            System.out.println(mayor + ": " + frecuencia.remove(mayor));
        }
    }
}
