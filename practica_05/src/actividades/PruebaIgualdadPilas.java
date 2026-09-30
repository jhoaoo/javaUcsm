package actividades;

public class PruebaIgualdadPilas {
    private static Pila<Integer> crear(int capacidad, Integer... valores) {
        Pila<Integer> pila = new Pila<>(capacidad);
        for (Integer valor : valores) pila.push(valor);
        return pila;
    }

    public static void main(String[] args) {
        Pila<Integer> a = crear(3, 10, 20, 30);
        Pila<Integer> b = crear(8, 10, 20, 30);
        Pila<Integer> c = crear(3, 30, 20, 10);
        Pila<Integer> d = crear(3, 10, 20);
        System.out.println("A: " + a + "; capacidad=" + a.capacidad());
        System.out.println("B: " + b + "; capacidad=" + b.capacidad());
        System.out.println("Mismos valores y orden: " + a.esIgual(b));
        System.out.println("Orden distinto: " + a.esIgual(c));
        System.out.println("Tamaño distinto: " + a.esIgual(d));
        System.out.println("Comparación con null: " + a.esIgual(null));
        System.out.println("Dos pilas vacías: " + crear(2).esIgual(crear(5)));
        System.out.println("Pilas con null: "
                + crear(2, 10, null).esIgual(crear(4, 10, null)));
        System.out.println("Después: A=" + a + "; B=" + b);
        System.out.println("Tope conservado A/B: " + a.pop() + "/" + b.pop());
    }
}
