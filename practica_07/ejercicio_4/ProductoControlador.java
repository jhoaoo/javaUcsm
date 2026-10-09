import java.io.IOException;

public class ProductoControlador {
    private final ProductoModelo modelo;
    private final ProductoVista vista;

    public ProductoControlador(ProductoModelo modelo, ProductoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        try {
            modelo.leerRegistros();
            vista.mensaje("Registros almacenados al iniciar:");
            vista.mostrarRegistros(modelo.listar());
        } catch (IOException e) {
            vista.mensaje("No se pudo cargar: " + e.getMessage());
            return;
        }
        int opcion = -1;
        do {
            vista.mostrarMenu();
            try {
                opcion = vista.pedirEntero("Opcion: ");
                switch (opcion) {
                    case 1:
                        vista.mostrarRegistros(modelo.listar());
                        break;
                    case 2:
                        int id = vista.pedirEntero("ID: ");
                        String nombre = vista.pedirTexto("Nombre: ");
                        double precio = vista.pedirDecimal("Precio: ");
                        modelo.agregarRegistro(new Producto(id, nombre, precio));
                        vista.mensaje("Registro agregado y guardado.");
                        break;
                    case 3:
                        Producto p = modelo.buscarRegistro(
                                vista.pedirEntero("ID a buscar: "));
                        vista.mensaje(p == null ? "Registro no encontrado."
                                : "Registro encontrado: " + p);
                        break;
                    case 4:
                        modelo.eliminarRegistro(
                                vista.pedirEntero("ID a eliminar: "));
                        vista.mensaje("Registro eliminado y archivo actualizado.");
                        break;
                    case 0:
                        vista.mensaje("Programa finalizado.");
                        break;
                    default:
                        vista.mensaje("Opcion no valida.");
                }
            } catch (IOException | IllegalArgumentException e) {
                vista.mensaje("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}
