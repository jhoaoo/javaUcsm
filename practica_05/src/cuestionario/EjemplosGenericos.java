package cuestionario;

import java.util.ArrayList;
import java.util.List;

public class EjemplosGenericos {
    public static <T extends Number> double duplicar(T numero) {
        return numero.doubleValue() * 2;
    }

    public static <T extends Comparable<T>> T maximo(T a, T b) {
        return a.compareTo(b) >= 0 ? a : b;
    }

    public static <T> T primero(List<T> lista) { return lista.get(0); }

    public static void mostrar(List<?> lista) {
        for (Object elemento : lista) System.out.print(elemento + " ");
        System.out.println();
    }

    public static double sumar(List<? extends Number> numeros) {
        double total = 0;
        for (Number numero : numeros) total += numero.doubleValue();
        return total;
    }

    public static void agregarEntero(List<? super Integer> destino) {
        destino.add(10);
    }

    public static void main(String[] args) {
        System.out.println("duplicar(7): " + duplicar(7));
        System.out.println("maximo(4, 9): " + maximo(4, 9));
        System.out.println("maximo(Ana, Luis): " + maximo("Ana", "Luis"));
        List<Integer> enteros = List.of(1, 2, 3);
        Integer primero = primero(enteros);
        System.out.println("Primero como Integer: " + primero);
        System.out.print("Wildcard ?: ");
        mostrar(enteros);
        System.out.println("Suma con extends: " + sumar(enteros));
        List<Number> destino = new ArrayList<>();
        agregarEntero(destino);
        System.out.println("Inserción con super: " + destino);
    }
}
