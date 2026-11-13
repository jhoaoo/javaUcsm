import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;

public class EjemploGridLayout {
    public static void main(String[] args) {
        JFrame ventana = new JFrame(
            "GridLayout - Jhoaoo Llerena");
        ventana.setSize(500, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(2, 3));

        ventana.add(new JButton("Boton 1"));
        ventana.add(new JButton("Boton 2"));
        ventana.add(new JButton("Boton 3"));
        ventana.add(new JButton("Boton 4"));
        ventana.add(new JButton("Boton 5"));
        ventana.add(new JButton("Boton 6"));

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}

