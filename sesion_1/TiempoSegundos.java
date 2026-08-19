import java.util.Scanner;

public class TiempoSegundos {

    public static int convertirASegundos(int horas, int minutos, int segundos) {

        int totalSegundos;

        totalSegundos = horas * 3600;
        totalSegundos = totalSegundos + minutos * 60;
        totalSegundos = totalSegundos + segundos;

        return totalSegundos;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese las horas: ");
        int horas = scanner.nextInt();

        System.out.print("Ingrese los minutos: ");
        int minutos = scanner.nextInt();

        System.out.print("Ingrese los segundos: ");
        int segundos = scanner.nextInt();

        int resultado = convertirASegundos(horas, minutos, segundos);

        System.out.println("El tiempo equivalente es: " + resultado + " segundos");

        scanner.close();
    }
}