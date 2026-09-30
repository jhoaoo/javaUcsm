package actividades;

public class PruebaPila {
    public static void main(String[] args) {
        Pila<Integer> pila = new Pila<>(3);
        pila.push(10);
        pila.push(20);
        pila.push(30);
        System.out.println("Pila [fondo -> tope]: " + pila);
        System.out.println("contains(20): " + pila.contains(20));
        System.out.println("contains(99): " + pila.contains(99));
        System.out.println("Después de buscar: " + pila + "; tamaño=" + pila.size());
        try { pila.push(40); }
        catch (ExcepcionPilaLlena e) {
            System.out.println("ExcepcionPilaLlena: " + e.getMessage());
        }
        System.out.println("Extracción LIFO: " + pila.pop() + ", "
                + pila.pop() + ", " + pila.pop());
        try { pila.pop(); }
        catch (ExcepcionPilaVacia e) {
            System.out.println("ExcepcionPilaVacia: " + e.getMessage());
        }
        Pila<String> textos = new Pila<>();
        textos.push("Java");
        textos.push(null);
        System.out.println("Pila String: " + textos);
        System.out.println("contains(null): " + textos.contains(null));
        Pila<Double> decimales = new Pila<>(2);
        decimales.push(1.1);
        decimales.push(2.2);
        System.out.println("Pila Double: " + decimales);
    }
}
