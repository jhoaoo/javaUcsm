package lab10;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ProductoDAO dao = new ProductoDAO();
                dao.crearTabla();
                VistaProductos vista = new VistaProductos();
                new ProductoControlador(vista, dao);
                vista.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se pudo iniciar: " + e.getMessage());
            }
        });
    }
}
