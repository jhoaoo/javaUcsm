import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        try {
            System.out.print("Filas: ");
            int filas = Integer.parseInt(teclado.nextLine());
            System.out.print("Columnas: ");
            int columnas = Integer.parseInt(teclado.nextLine());
            if (filas <= 0 || columnas <= 0 || filas > 100 || columnas > 100) {
                System.out.println("Dimensiones no válidas (1 a 100).");
                return;
            }
            double[][] matriz = new double[filas][columnas];
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < columnas; j++) {
                    System.out.print("Matriz[" + i + "][" + j + "]: ");
                    matriz[i][j] = Double.parseDouble(teclado.nextLine());
                }
            }
            try (DataOutputStream salida = new DataOutputStream(new FileOutputStream("matriz.dat"))) {
                salida.writeInt(filas);
                salida.writeInt(columnas);
                for (double[] fila : matriz) for (double valor : fila) salida.writeDouble(valor);
            }
            System.out.println("Matriz guardada en matriz.dat");
            try (DataInputStream entrada = new DataInputStream(new FileInputStream("matriz.dat"))) {
                int f = entrada.readInt(), c = entrada.readInt();
                System.out.println("Matriz recuperada:");
                for (int i = 0; i < f; i++) {
                    for (int j = 0; j < c; j++) System.out.print(entrada.readDouble() + "\t");
                    System.out.println();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar números válidos.");
        } catch (IOException e) {
            System.out.println("Error de archivo: " + e.getMessage());
        }
    }
}
