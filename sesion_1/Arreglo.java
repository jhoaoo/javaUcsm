import java.util.Scanner;

public class Arreglo {
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        int[] numeros = new int[20000];

        for (int i = 0; i < 20000; i++) {

            System.out.print("Ingrese un numero: ");
            numeros[i] = teclado.nextInt();

            if (i > 0 && numeros[i] <= numeros[i - 1]) {
                System.out.println("Debe ser mayor que el anterior");
                i--;
            }
        }

        teclado.close();
        System.out.println("La lista es:");
        for (int i = 0; i < 10; i++) {
            System.out.println(numeros[i]);
        }
    }
}