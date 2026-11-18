import javax.swing.*;
import java.awt.*;

public class GraficoTemperaturas extends JPanel {
    private final String[] dias = {"Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom"};
    private final JTextField[] campos = new JTextField[7];
    private final double[] temperaturas = new double[7];
    private boolean mostrar = false;

    public GraficoTemperaturas() {
        setPreferredSize(new Dimension(700, 320));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!mostrar) {
            g.drawString("Ingresa siete temperaturas y pulsa Mostrar grafico.", 70, 90);
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.drawLine(60, 260, 650, 260);
        g2.drawLine(60, 30, 60, 260);
        double min = temperaturas[0], max = temperaturas[0];
        for (double t : temperaturas) { min = Math.min(min, t); max = Math.max(max, t); }
        if (max == min) max = min + 1;
        int anteriorX = 0, anteriorY = 0;
        for (int i = 0; i < 7; i++) {
            int x = 90 + i * 90;
            int y = 240 - (int) ((temperaturas[i] - min) * 180 / (max - min));
            g2.setColor(Color.BLUE);
            if (i > 0) g2.drawLine(anteriorX, anteriorY, x, y);
            g2.fillOval(x - 4, y - 4, 8, 8);
            g2.setColor(Color.BLACK);
            g2.drawString(dias[i], x - 12, 280);
            g2.drawString(temperaturas[i] + " C", x - 15, y - 10);
            anteriorX = x; anteriorY = y;
        }
    }

    public static void main(String[] args) {
        GraficoTemperaturas grafico = new GraficoTemperaturas();
        JFrame ventana = new JFrame("Temperaturas de la semana");
        JPanel entrada = new JPanel(new GridLayout(2, 7, 4, 4));
        for (int i = 0; i < 7; i++)
            entrada.add(new JLabel(grafico.dias[i], SwingConstants.CENTER));
        for (int i = 0; i < 7; i++) {
            grafico.campos[i] = new JTextField("20");
            entrada.add(grafico.campos[i]);
        }
        JButton boton = new JButton("Mostrar grafico");
        boton.addActionListener(e -> {
            try {
                for (int i = 0; i < 7; i++)
                    grafico.temperaturas[i] =
                        Double.parseDouble(grafico.campos[i].getText().trim());
                grafico.mostrar = true;
                grafico.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, "Ingresa solo temperaturas numericas.");
            }
        });
        ventana.setLayout(new BorderLayout(8, 8));
        ventana.add(entrada, BorderLayout.NORTH);
        ventana.add(grafico, BorderLayout.CENTER);
        ventana.add(boton, BorderLayout.SOUTH);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
