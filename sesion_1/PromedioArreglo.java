import java.util.Scanner;

public class PromedioArreglo {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        System.out.print("¿Cuántos números desea ingresar?: ");
        int cantidad = teclado.nextInt();

        double[] numeros = new double[cantidad];

        double suma = 0;
        for (int i = 0; i < cantidad; i++) {
            System.out.print("Ingrese un numero: ");
            numeros[i] = teclado.nextDouble();
            suma += numeros[i];
        }
        double promedio = suma / cantidad;
        System.out.println("El promedio es: " + promedio);
    }
}