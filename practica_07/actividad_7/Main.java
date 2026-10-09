import java.io.IOException;
import java.nio.file.*;
import javax.swing.*;

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
        SwingUtilities.invokeLater(() -> {
            JFileChooser selector = new JFileChooser();
            selector.setFileSelectionMode(
                    JFileChooser.FILES_AND_DIRECTORIES);
            int resultado = selector.showOpenDialog(null);
            if (resultado != JFileChooser.APPROVE_OPTION) {
                return;
            }
            try {
                Path ruta = selector.getSelectedFile().toPath();
                JTextArea area = new JTextArea(informar(ruta), 20, 65);
                area.setEditable(false);
                JFrame ventana = new JFrame("Informacion del archivo");
                ventana.add(new JScrollPane(area));
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventana.pack();
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
