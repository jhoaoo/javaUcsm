import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccione un archivo de texto");
        while (true) {
            if (selector.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
                System.out.println("Selección cancelada."); return;
            }
            Path archivo = selector.getSelectedFile().toPath();
            if (!Files.isRegularFile(archivo) || !Files.isReadable(archivo)) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un archivo legible.");
                continue;
            }
            try {
                analizar(archivo);
                break;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error de lectura: " + e.getMessage());
            }
        }
    }
    private static void analizar(Path archivo) throws IOException {
        long lineas = 0, palabras = 0, caracteres = 0;
        Map<String, Integer> frecuencias = new HashMap<>();
        try (BufferedReader lector = Files.newBufferedReader(archivo, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas++;
                caracteres += linea.codePointCount(0, linea.length());
                StringBuilder palabra = new StringBuilder();
                for (int i = 0; i < linea.length();) {
                    int cp = linea.codePointAt(i);
                    if (Character.isLetterOrDigit(cp)) {
                        palabra.appendCodePoint(Character.toLowerCase(cp));
                    } else if (palabra.length() > 0) {
                        palabras++;
                        String texto = palabra.toString();
                        frecuencias.put(texto, frecuencias.getOrDefault(texto, 0) + 1);
                        palabra.setLength(0);
                    }
                    i += Character.charCount(cp);
                }
                if (palabra.length() > 0) {
                    palabras++;
                    String texto = palabra.toString();
                    frecuencias.put(texto, frecuencias.getOrDefault(texto, 0) + 1);
                }
            }
        }
        System.out.println("Archivo: " + archivo.toAbsolutePath());
        System.out.println("Total de líneas: " + lineas);
        System.out.println("Total de palabras: " + palabras);
        System.out.println("Total de caracteres (sin saltos): " + caracteres);
        System.out.printf(Locale.US, "Promedio de palabras por línea: %.2f%n", lineas == 0 ? 0 : (double) palabras / lineas);
        System.out.println("Palabras más frecuentes:");
        frecuencias.entrySet().stream()
            .sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue).reversed()
                .thenComparing(Map.Entry::getKey))
            .limit(10)
            .forEach(e -> System.out.println(e.getKey() + " : " + e.getValue()));
    }
}
