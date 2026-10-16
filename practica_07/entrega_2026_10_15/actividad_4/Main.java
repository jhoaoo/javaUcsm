import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Visualizador de archivos de texto");
            JTextArea area = new JTextArea(20, 60);
            area.setEditable(false);
            JButton boton = new JButton("Abrir archivo");
            boton.addActionListener(evento -> {
                JFileChooser selector = new JFileChooser();
                if (selector.showOpenDialog(ventana) != JFileChooser.APPROVE_OPTION) return;
                File archivo = selector.getSelectedFile();
                try (InputStream entrada = new FileInputStream(archivo);
                     Reader lector = new InputStreamReader(entrada, StandardCharsets.UTF_8)) {
                    area.read(lector, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ventana, ex.getMessage(), "Error de lectura", JOptionPane.ERROR_MESSAGE);
                }
            });
            ventana.add(boton, BorderLayout.NORTH);
            ventana.add(new JScrollPane(area), BorderLayout.CENTER);
            ventana.pack();
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
