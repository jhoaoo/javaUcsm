public class Menor {
    public static void main(String[] args) {

        double resultado = menor(5.5, 2.3, 8.1);

        System.out.println("El menor es: " + resultado);
    }

    public static double menor(double a, double b, double c) {

        double menor = a;

        if (b < menor) {
            menor = b;
        }

        if (c < menor) {
            menor = c;
        }

        return menor;
    }
}