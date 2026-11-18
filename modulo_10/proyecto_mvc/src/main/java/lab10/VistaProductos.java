package lab10;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaProductos extends JFrame {
    JTextField txtNombre = new JTextField();
    JTextField txtCategoria = new JTextField();
    JTextField txtPrecio = new JTextField();
    JTextField txtStock = new JTextField();
    JButton btnGuardar = new JButton("Guardar");
    JButton btnListar = new JButton("Actualizar lista");
    JButton btnEliminar = new JButton("Eliminar seleccionado");
    DefaultTableModel tablaModelo = new DefaultTableModel(
        new Object[]{"ID", "Nombre", "Categoria", "Precio", "Stock"}, 0);
    JTable tabla = new JTable(tablaModelo);

    public VistaProductos() {
        setTitle("Inventario de tienda - Practica 10");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 480);
        setLocationRelativeTo(null);
        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.add(new JLabel("Nombre:")); formulario.add(txtNombre);
        formulario.add(new JLabel("Categoria:")); formulario.add(txtCategoria);
        formulario.add(new JLabel("Precio (S/):")); formulario.add(txtPrecio);
        formulario.add(new JLabel("Stock:")); formulario.add(txtStock);
        JPanel botones = new JPanel();
        botones.add(btnGuardar); botones.add(btnListar); botones.add(btnEliminar);
        JPanel superior = new JPanel(new BorderLayout());
        superior.add(formulario, BorderLayout.CENTER);
        superior.add(botones, BorderLayout.SOUTH);
        add(superior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
