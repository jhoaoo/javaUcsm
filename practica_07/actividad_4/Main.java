import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.*;

public class Main {
    public static String leerArchivo(String ruta) throws IOException {
        StringBuilder texto = new StringBuilder();
        // El Reader interpreta los bytes usando UTF-8.
        try (Reader entrada = new InputStreamReader(
                new FileInputStream(ruta), StandardCharsets.UTF_8)) {
            char[] bloque = new char[1024];
            int cantidad;
            while ((cantidad = entrada.read(bloque)) != -1) {
                texto.append(bloque, 0, cantidad);
            }
        }
        return texto.toString();
    }

    public static void main(String[] args) {
        String ruta = args.length > 0 ? args[0] : "TestFile.java";
        try {
            String contenido = leerArchivo(ruta);
            SwingUtilities.invokeLater(() -> {
                JFrame ventana = new JFrame("Contenido del archivo");
                JTextArea area = new JTextArea(contenido, 20, 65);
                area.setEditable(false);
                ventana.add(new JScrollPane(area));
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventana.pack();
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            });
        } catch (IOException e) {
            System.out.println("No se pudo leer: " + e.getMessage());
        }
    }
}
