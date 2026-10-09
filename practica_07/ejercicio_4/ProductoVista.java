import java.util.List;
import java.util.Scanner;

public class ProductoVista {
    private final Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        mensaje("\n1. Listar  2. Agregar  3. Buscar  4. Eliminar  0. Salir");
    }

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public int pedirEntero(String mensaje) {
        return Integer.parseInt(pedirTexto(mensaje));
    }

    public double pedirDecimal(String mensaje) {
        return Double.parseDouble(pedirTexto(mensaje));
    }

    public void mensaje(String texto) {
        System.out.println(texto);
    }

    public void mostrarRegistros(List<Producto> registros) {
        if (registros.isEmpty()) {
            mensaje("No hay registros.");
        }
        for (Producto p : registros) {
            mensaje(p.toString());
        }
    }
}
