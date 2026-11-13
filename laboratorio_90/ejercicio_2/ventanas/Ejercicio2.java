
package ventanas;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Ejercicio2 extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtDni;
    private JTextField txtFecha;

    private JComboBox<String> cbOrigen;
    private JComboBox<String> cbDestino;

    private JCheckBox chkAudifonos;
    private JCheckBox chkManta;
    private JCheckBox chkRevistas;

    private JRadioButton rbPrimerPiso;
    private JRadioButton rbSegundoPiso;

    private JList<String> listaServicio;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Ejercicio2 frame = new Ejercicio2();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

  public Ejercicio2() {

        setTitle("Sistema de Compra de Pasajes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 650);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("COMPRA DE PASAJES");
        lblTitulo.setBounds(190, 15, 200, 25);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre completo:");
        lblNombre.setBounds(40, 60, 130, 25);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(190, 60, 280, 25);
        contentPane.add(txtNombre);

JLabel lblDni = new JLabel("DNI:");
        lblDni.setBounds(40, 100, 130, 25);
        contentPane.add(lblDni);

        txtDni = new JTextField();
        txtDni.setBounds(190, 100, 280, 25);
        contentPane.add(txtDni);

        JLabel lblFecha = new JLabel("Fecha de viaje:");
        lblFecha.setBounds(40, 140, 130, 25);
        contentPane.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(190, 140, 280, 25);
        contentPane.add(txtFecha);

        JLabel lblOrigen = new JLabel("Origen:");
        lblOrigen.setBounds(40, 180, 130, 25);
        contentPane.add(lblOrigen);

        String[] ciudades = {
            "Seleccione", "Arequipa", "Lima",
            "Cusco", "Tacna", "Puno"
        };

        cbOrigen = new JComboBox<>(ciudades);
        cbOrigen.setBounds(190, 180, 280, 25);
        contentPane.add(cbOrigen);

        JLabel lblDestino = new JLabel("Destino:");
        lblDestino.setBounds(40, 220, 130, 25);
        contentPane.add(lblDestino);

        cbDestino = new JComboBox<>(ciudades);
        cbDestino.setBounds(190, 220, 280, 25);
        contentPane.add(cbDestino);

JLabel lblPiso = new JLabel("Piso del bus:");
        lblPiso.setBounds(40, 260, 130, 25);
        contentPane.add(lblPiso);

        rbPrimerPiso = new JRadioButton("1er piso");
        rbPrimerPiso.setBounds(190, 260, 100, 25);
        contentPane.add(rbPrimerPiso);

        rbSegundoPiso = new JRadioButton("2do piso");
        rbSegundoPiso.setBounds(300, 260, 100, 25);
        contentPane.add(rbSegundoPiso);

        ButtonGroup grupoPiso = new ButtonGroup();
        grupoPiso.add(rbPrimerPiso);
        grupoPiso.add(rbSegundoPiso);

        JLabel lblExtras = new JLabel("Servicios adicionales:");
        lblExtras.setBounds(40, 300, 150, 25);
        contentPane.add(lblExtras);

        chkAudifonos = new JCheckBox("Audífonos");
        chkAudifonos.setBounds(190, 300, 110, 25);
        contentPane.add(chkAudifonos);

        chkManta = new JCheckBox("Manta");
        chkManta.setBounds(300, 300, 90, 25);
        contentPane.add(chkManta);

        chkRevistas = new JCheckBox("Revistas");
        chkRevistas.setBounds(390, 300, 100, 25);
        contentPane.add(chkRevistas);

       JLabel lblServicio = new JLabel("Tipo de servicio:");
        lblServicio.setBounds(40, 350, 130, 25);
        contentPane.add(lblServicio);

        String[] servicios = {
            "Económico", "Estándar", "VIP"
        };

        listaServicio = new JList<>(servicios);
        listaServicio.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scroll = new JScrollPane(listaServicio);
        scroll.setBounds(190, 350, 280, 75);
        contentPane.add(scroll);

       JButton btnResumen = new JButton("Mostrar resumen");
        btnResumen.setBounds(90, 470, 160, 35);
        contentPane.add(btnResumen);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(290, 470, 140, 35);
        contentPane.add(btnLimpiar);

        btnResumen.addActionListener(e -> {

String nombre = txtNombre.getText().trim();
            String dni = txtDni.getText().trim();
            String fecha = txtFecha.getText().trim();

            String origen = cbOrigen.getSelectedItem().toString();
            String destino = cbDestino.getSelectedItem().toString();

            if (nombre.isEmpty() || dni.isEmpty() ||
                fecha.isEmpty() ||
                !dni.matches("\\d{8}") ||
                cbOrigen.getSelectedIndex() == 0 ||
                cbDestino.getSelectedIndex() == 0 ||
                origen.equals(destino) ||
                (!rbPrimerPiso.isSelected() &&
                 !rbSegundoPiso.isSelected()) ||
                listaServicio.isSelectionEmpty()) {

                JOptionPane.showMessageDialog(
                    this,
                    "Complete correctamente todos los datos.\n"
                    + "El DNI debe tener 8 dígitos,\n"
                    + "y el origen debe ser distinto al destino.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String piso = rbPrimerPiso.isSelected()
                    ? "Primer piso" : "Segundo piso";

            String extras = "";

            if (chkAudifonos.isSelected()) {
                extras += "Audífonos, ";
            }

            if (chkManta.isSelected()) {
                extras += "Manta, ";
            }

            if (chkRevistas.isSelected()) {
                extras += "Revistas, ";
            }

            if (extras.isEmpty()) {
                extras = "Ninguno";
            } else {
                extras = extras.substring(0, extras.length() - 2);
            }

            String resumen =
                "RESUMEN DE COMPRA\n\n" +
                "Pasajero: " + nombre + "\n" +
                "DNI: " + dni + "\n" +
                "Fecha de viaje: " + fecha + "\n" +
                "Origen: " + origen + "\n" +
                "Destino: " + destino + "\n" +
                "Ubicación: " + piso + "\n" +
                "Servicio: " +
                    listaServicio.getSelectedValue() + "\n" +
                "Adicionales: " + extras;

            JOptionPane.showMessageDialog(
                this,
                resumen,
                "Resumen del pasaje",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        btnLimpiar.addActionListener(e -> {

            txtNombre.setText("");
            txtDni.setText("");
            txtFecha.setText("");

            cbOrigen.setSelectedIndex(0);
            cbDestino.setSelectedIndex(0);

            grupoPiso.clearSelection();

            chkAudifonos.setSelected(false);
            chkManta.setSelected(false);
            chkRevistas.setSelected(false);

            listaServicio.clearSelection();
            txtNombre.requestFocusInWindow();
        });
    }
}