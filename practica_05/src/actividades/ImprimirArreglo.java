package actividades;

import java.util.Objects;

public class ImprimirArreglo {
    public static <E> void imprimirArreglo(E[] arreglo) {
        Objects.requireNonNull(arreglo, "El arreglo no puede ser null");
        for (E elemento : arreglo) System.out.print(elemento + " ");
        System.out.println();
    }

    /** Imprime el intervalo inclusivo y devuelve su cantidad de elementos. */
    public static <E> int imprimirArreglo(E[] arreglo, int inferior,
                                         int superior) {
        Objects.requireNonNull(arreglo, "El arreglo no puede ser null");
        if (inferior < 0 || superior < 0 || inferior >= arreglo.length
                || superior >= arreglo.length || superior <= inferior) {
            throw new InvalidSubscriptException(
                "Intervalo inválido: [" + inferior + ", " + superior + "]");
        }
        for (int i = inferior; i <= superior; i++) {
            System.out.print(arreglo[i] + " ");
        }
        System.out.println();
        return superior - inferior + 1;
    }

    private static <E> void demostrar(String nombre, E[] arreglo) {
        System.out.println(nombre + " completo:");
        imprimirArreglo(arreglo);
        System.out.println("Intervalo [1, 3]:");
        int cantidad = imprimirArreglo(arreglo, 1, 3);
        System.out.println("Elementos impresos: " + cantidad);
    }

    public static void main(String[] args) {
        Integer[] enteros = {1, 2, 3, 4, 5, 6};
        Double[] decimales = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        Character[] letras = {'H', 'O', 'L', 'A'};
        demostrar("Integer", enteros);
        demostrar("Double", decimales);
        demostrar("Character", letras);
        int[][] casos = {{-1, 2}, {0, 6}, {2, 2}, {3, 1}};
        for (int[] caso : casos) {
            try { imprimirArreglo(enteros, caso[0], caso[1]); }
            catch (InvalidSubscriptException e) {
                System.out.println(e.getClass().getSimpleName() + ": "
                        + e.getMessage());
            }
        }
    }
}
