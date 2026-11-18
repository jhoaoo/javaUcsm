package lab10;

import javax.swing.*;
import java.sql.SQLException;

public class ProductoControlador {
    private final VistaProductos vista;
    private final ProductoDAO dao;

    public ProductoControlador(VistaProductos vista, ProductoDAO dao) {
        this.vista = vista;
        this.dao = dao;
        vista.btnGuardar.addActionListener(e -> guardar());
        vista.btnListar.addActionListener(e -> listar());
        vista.btnEliminar.addActionListener(e -> eliminar());
        listar();
    }

    private void guardar() {
        try {
            String nombre = vista.txtNombre.getText().trim();
            String categoria = vista.txtCategoria.getText().trim();
            double precio = Double.parseDouble(vista.txtPrecio.getText().trim());
            int stock = Integer.parseInt(vista.txtStock.getText().trim());
            if (nombre.isEmpty() || categoria.isEmpty() || precio < 0 || stock < 0) {
                JOptionPane.showMessageDialog(vista, "Completa los datos correctamente.");
                return;
            }
            dao.guardar(new Producto(0, nombre, categoria, precio, stock));
            vista.txtNombre.setText(""); vista.txtCategoria.setText("");
            vista.txtPrecio.setText(""); vista.txtStock.setText("");
            listar();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "Precio y stock deben ser numeros validos.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error de base de datos: " + e.getMessage());
        }
    }

    private void listar() {
        try {
            vista.tablaModelo.setRowCount(0);
            for (Producto p : dao.listar()) {
                vista.tablaModelo.addRow(new Object[]{p.getId(), p.getNombre(),
                        p.getCategoria(), p.getPrecio(), p.getStock()});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage());
        }
    }

    private void eliminar() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona un producto.");
            return;
        }
        try {
            int id = (int) vista.tablaModelo.getValueAt(fila, 0);
            dao.eliminar(id);
            listar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(vista, "Error: " + e.getMessage());
        }
    }
}
