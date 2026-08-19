import java.util.Scanner;

public class Estacionamiento {

    public static double calcularCargo(int horas) {

        double cargo;

        if (horas <= 0) {
            cargo = 0;
        } else if (horas == 1) {
            cargo = 3.00;
        } else {
            cargo = 3.00 + (horas - 1) * 0.50;
        }

        if (cargo > 12.00) {
            cargo = 12.00;
        }

        return cargo;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el numero de horas: ");
        int horas = scanner.nextInt();

        double cargo = calcularCargo(horas);

        System.out.println("El cargo es: S/ " + cargo);

        scanner.close();
    }
}