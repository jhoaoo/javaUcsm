import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

public class ContadorPalabras {
    public static String analizar(Path archivo) throws IOException {
        long lineas = 0, palabras = 0, caracteres = 0;
        Map<String, Long> frecuencias = new TreeMap<>();
        try (BufferedReader entrada = Files.newBufferedReader(
                archivo, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                lineas++;
                caracteres += linea.codePointCount(0, linea.length());
                StringBuilder palabra = new StringBuilder();
                // codePoints evita dividir caracteres Unicode suplementarios.
                int[] puntos = linea.codePoints().toArray();
                for (int punto : puntos) {
                    if (Character.isLetterOrDigit(punto)) {
                        palabra.appendCodePoint(punto);
                    } else if (palabra.length() > 0) {
                        registrar(palabra, frecuencias);
                        palabras++;
                    }
                }
                if (palabra.length() > 0) {
                    registrar(palabra, frecuencias);
                    palabras++;
                }
            }
        }
        double promedio = lineas == 0 ? 0 : (double) palabras / lineas;
        StringBuilder resultado = new StringBuilder(String.format(
                Locale.ROOT,
                "Lineas: %d%nPalabras: %d%nCaracteres: %d%n"
                + "Promedio de palabras por linea: %.2f%n",
                lineas, palabras, caracteres, promedio));
        resultado.append("Palabras con la frecuencia maxima:\n");
        long maxima = 0;
        for (long conteo : frecuencias.values()) {
            maxima = Math.max(maxima, conteo);
        }
        if (maxima == 0) {
            resultado.append("No hay palabras.\n");
        }
        for (Map.Entry<String, Long> entrada : frecuencias.entrySet()) {
            if (entrada.getValue() == maxima) {
                resultado.append(entrada.getKey()).append(": ")
                        .append(entrada.getValue()).append("\n");
            }
        }
        return resultado.toString();
    }

    private static void registrar(StringBuilder palabra,
            Map<String, Long> frecuencias) {
        String clave = palabra.toString().toLowerCase(Locale.ROOT);
        frecuencias.put(clave, frecuencias.getOrDefault(clave, 0L) + 1);
        palabra.setLength(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFileChooser selector = new JFileChooser();
            while (true) {
                int opcion = selector.showOpenDialog(null);
                if (opcion != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                Path archivo = selector.getSelectedFile().toPath();
                try {
                    if (!Files.isRegularFile(archivo)
                            || !Files.isReadable(archivo)) {
                        throw new IOException("Selecciona un archivo legible.");
                    }
                    System.out.println(analizar(archivo));
                    return;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(),
                            "Archivo invalido", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
