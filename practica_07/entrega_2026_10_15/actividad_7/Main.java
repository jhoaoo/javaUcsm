import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Información de archivos y directorios");
            JTextArea datos = new JTextArea(17, 65);
            datos.setEditable(false);
            JButton elegir = new JButton("Elegir archivo o directorio");
            elegir.addActionListener(evento -> {
                JFileChooser selector = new JFileChooser();
                selector.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                if (selector.showOpenDialog(ventana) != JFileChooser.APPROVE_OPTION) return;
                Path ruta = selector.getSelectedFile().toPath();
                StringBuilder texto = new StringBuilder();
                try {
                    texto.append("Nombre: ").append(ruta.getFileName()).append('\n');
                    texto.append("Ruta: ").append(ruta.toAbsolutePath()).append('\n');
                    texto.append("Directorio: ").append(Files.isDirectory(ruta)).append('\n');
                    texto.append("Tamaño: ").append(Files.size(ruta)).append(" bytes\n");
                    texto.append("Última modificación: ").append(Files.getLastModifiedTime(ruta)).append('\n');
                    if (Files.isDirectory(ruta)) {
                        texto.append("\nContenido:\n");
                        try (DirectoryStream<Path> lista = Files.newDirectoryStream(ruta)) {
                            for (Path p : lista) texto.append("- ").append(p.getFileName()).append('\n');
                        }
                    }
                } catch (IOException e) { texto.append("Error: ").append(e.getMessage()); }
                datos.setText(texto.toString());
            });
            ventana.add(elegir, BorderLayout.NORTH);
            ventana.add(new JScrollPane(datos), BorderLayout.CENTER);
            ventana.pack();
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
