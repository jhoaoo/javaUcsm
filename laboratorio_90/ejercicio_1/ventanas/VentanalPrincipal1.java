package ventanas;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class VentanalPrincipal1 extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField txtTxtnombre;
    private JTextField txtIngreseApellido;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanalPrincipal1 frame = new VentanalPrincipal1();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public VentanalPrincipal1() {

        setTitle("Registro de estudiantes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Registro de estudiantes");
        lblTitulo.setBounds(145, 33, 190, 20);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(28, 72, 80, 20);
        contentPane.add(lblNombre);

        txtTxtnombre = new JTextField();
        txtTxtnombre.setBounds(140, 69, 169, 23);
        contentPane.add(txtTxtnombre);
        txtTxtnombre.setColumns(10);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(28, 100, 80, 20);
        contentPane.add(lblApellido);

        txtIngreseApellido = new JTextField();
        txtIngreseApellido.setBounds(140, 97, 169, 23);
        contentPane.add(txtIngreseApellido);
        txtIngreseApellido.setColumns(10);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(135, 145, 95, 25);
        contentPane.add(btnRegistrar);


  btnRegistrar.addActionListener(e -> {

            String nombre = txtTxtnombre.getText().trim();
            String apellido = txtIngreseApellido.getText().trim();

            if (nombre.isEmpty() || apellido.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Por favor, ingrese su nombre y apellido.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Estudiante registrado correctamente:\n"
                    + "Nombre: " + nombre + "\n"
                    + "Apellido: " + apellido,
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(245, 145, 90, 25);
        contentPane.add(btnLimpiar);

        btnLimpiar.addActionListener(e -> {

            txtTxtnombre.setText("");
            txtIngreseApellido.setText("");
            txtTxtnombre.requestFocusInWindow();
        });
    }
}