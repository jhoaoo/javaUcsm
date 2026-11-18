import javax.swing.*;
import java.awt.*;

public class RegistroProducto {
    static class Producto {
        private String nombre, categoria;
        private double precio;
        private int stock;
        public void setNombre(String nombre) { this.nombre = nombre; }
        public void setCategoria(String categoria) { this.categoria = categoria; }
        public void setPrecio(double precio) { this.precio = precio; }
        public void setStock(int stock) { this.stock = stock; }
        public String getNombre() { return nombre; }
        public String getCategoria() { return categoria; }
        public double getPrecio() { return precio; }
        public int getStock() { return stock; }
    }

    public static void main(String[] args) {
        Producto producto = new Producto();
        JFrame ventana = new JFrame("Producto - Jhoaoo, Gustavo y Mauricio");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(460, 320);
        ventana.setLayout(new GridLayout(6, 2, 8, 8));
        JTextField nombre = new JTextField();
        JTextField precio = new JTextField();
        JTextField stock = new JTextField();
        JTextField categoria = new JTextField();
        JLabel resultado = new JLabel("Sin datos");
        JButton actualizar = new JButton("Actualizar Producto");
        ventana.add(new JLabel("Nombre:")); ventana.add(nombre);
        ventana.add(new JLabel("Precio (S/):")); ventana.add(precio);
        ventana.add(new JLabel("Stock:")); ventana.add(stock);
        ventana.add(new JLabel("Categoria:")); ventana.add(categoria);
        ventana.add(actualizar); ventana.add(new JLabel("Resultado:"));
        ventana.add(resultado); ventana.add(new JLabel(""));

        actualizar.addActionListener(e -> {
            try {
                if (nombre.getText().trim().isEmpty() || categoria.getText().trim().isEmpty())
                    throw new IllegalArgumentException("Escribe el nombre y la categoria.");
                double p = Double.parseDouble(precio.getText().trim());
                int s = Integer.parseInt(stock.getText().trim());
                if (p < 0 || s < 0)
                    throw new IllegalArgumentException("Precio y stock no pueden ser negativos.");
                producto.setNombre(nombre.getText().trim());
                producto.setPrecio(p);
                producto.setStock(s);
                producto.setCategoria(categoria.getText().trim());
                resultado.setText(producto.getNombre() + " | S/ " + producto.getPrecio());
                JOptionPane.showMessageDialog(ventana, "Producto: " + producto.getNombre()
                        + "\nPrecio: S/ " + producto.getPrecio()
                        + "\nStock: " + producto.getStock()
                        + "\nCategoria: " + producto.getCategoria());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ventana, "Precio y stock deben ser numeros validos.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(ventana, ex.getMessage());
            }
        });
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
