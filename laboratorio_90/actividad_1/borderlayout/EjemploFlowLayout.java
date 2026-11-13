import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class EjemploFlowLayout {
    public static void main(String[] args) {
        JFrame ventana = new JFrame(
            "FlowLayout - Jhoaoo Llerena");
        ventana.setSize(500, 250);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new FlowLayout());

        ventana.add(new JButton("Boton 1"));
        ventana.add(new JButton("Boton 2"));
        ventana.add(new JButton("Boton 3"));
        ventana.add(new JButton("Boton 4"));
        ventana.add(new JButton("Boton 5"));

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
