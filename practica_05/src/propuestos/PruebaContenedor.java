package propuestos;

import java.util.ArrayList;

public class PruebaContenedor {
    public static void main(String[] args) {
        // Temática: inventario de equipos de un laboratorio de redes.
        Contenedor<String, Integer> inventario = new Contenedor<>();
        inventario.agregarPar("Router", 4);
        inventario.agregarPar("Switch", 8);
        inventario.agregarPar("Punto de acceso", 6);
        System.out.println("Inventario del laboratorio:");
        inventario.mostrarPares();
        System.out.println("obtenerPar(1): " + inventario.obtenerPar(1));
        inventario.obtenerPar(1).setSegundo(10);
        System.out.println("Switch actualizado: " + inventario.obtenerPar(1));
        ArrayList<Par<String, Integer>> copia = inventario.obtenerTodosLosPares();
        System.out.println("Total de pares devueltos: " + copia.size());
        copia.clear();
        System.out.println("Pares internos después de limpiar copia: "
                + inventario.obtenerTodosLosPares().size());
        try { inventario.obtenerPar(8); }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Índice 8: IndexOutOfBoundsException controlada");
        }
    }
}
