import java.io.*;
import java.util.Scanner;

public class Main {
    private static int pedirDimension(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(sc.nextLine());
                if (valor > 0) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un entero.");
            }
            System.out.println("La dimension debe ser mayor que cero.");
        }
    }

    private static double pedirValor(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(sc.nextLine());
                if (Double.isFinite(valor)) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un numero.");
            }
            System.out.println("Ingresa un valor finito.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int filas = pedirDimension(sc, "Filas: ");
        int columnas = pedirDimension(sc, "Columnas: ");
        double[][] matriz = new double[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = pedirValor(sc,
                        "matriz[" + i + "][" + j + "]: ");
            }
        }
        try (DataOutputStream salida = new DataOutputStream(
                new FileOutputStream("matriz.dat"))) {
            // Las dimensiones se escriben una sola vez.
            salida.writeInt(filas);
            salida.writeInt(columnas);
            for (double[] fila : matriz) {
                for (double valor : fila) {
                    salida.writeDouble(valor);
                }
            }
            System.out.println("Matriz guardada en matriz.dat.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
