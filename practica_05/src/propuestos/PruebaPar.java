package propuestos;

public class PruebaPar {
    public static void main(String[] args) {
        Par<String, Integer> a = new Par<>("Java", 17);
        Par<String, Integer> b = new Par<>(new String("Java"), 17);
        System.out.println("A: " + a);
        System.out.println("B: " + b);
        System.out.println("Mismos valores: " + a.esIgual(b));
        b.setSegundo(21);
        System.out.println("Segundo distinto: " + a.esIgual(b));
        System.out.println("Otro par null: " + a.esIgual(null));
        Par<String, Integer> n = new Par<>(null, 5);
        System.out.println("Componentes null iguales: "
                + n.esIgual(new Par<>(null, 5)));
        Par<String, String> orden = new Par<>("A", "B");
        System.out.println("Valores invertidos: "
                + orden.esIgual(new Par<>("B", "A")));
    }
}
