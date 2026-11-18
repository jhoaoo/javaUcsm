import javax.swing.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;

public class ReproductorEfectos {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Efectos de sonido");
        ventana.setLayout(new GridLayout(3, 1, 10, 10));
        String[] nombres = {"Aplausos", "Campana", "Explosion"};
        String[] archivos = {"aplausos.wav", "campana.wav", "explosion.wav"};
        for (int i = 0; i < nombres.length; i++) {
            String ruta = archivos[i];
            JButton boton = new JButton(nombres[i]);
            boton.addActionListener(e -> reproducir(ruta, ventana));
            ventana.add(boton);
        }
        ventana.setSize(330, 230);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

    private static void reproducir(String ruta, JFrame ventana) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(ruta));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            clip.addLineListener(evento -> {
                if (evento.getType() == LineEvent.Type.STOP) clip.close();
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ventana,
                "No se pudo abrir " + ruta + ". Coloca archivos WAV en esta carpeta.");
        }
    }
}
