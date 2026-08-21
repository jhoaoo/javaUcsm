import java.util.Scanner;

public class AppBanco {
    public static void main(String[] args) {
        Cuenta[] cuentas = new Cuenta[10];

        for (int i = 0; i < 5; i++) {
            cuentas[i] = new CuentaAhorro(i, 1000, 2);
        }
        for (int i = 5; i < 10; i++) {
            cuentas[i] = new CuentaCorriente(i, 1000);
        }

        Scanner scanner = new Scanner(System.in);
        String opcion = "";

        while (!opcion.equals("S")) {
            System.out.print("D)epositar R)etirar C)onsultar S)alir: ");
            opcion = scanner.next().toUpperCase();

            if (opcion.equals("D") || opcion.equals("R")) {
                System.out.print("Numero de cuenta: ");
                int numero = scanner.nextInt();
                System.out.print("Monto: ");
                double monto = scanner.nextDouble();

                if (numero >= 0 && numero < cuentas.length) {
                    if (opcion.equals("D")) {
                        cuentas[numero].depositar(monto);
                    } else {
                        cuentas[numero].retirar(monto);
                    }
                    System.out.println("Saldo: S/ " + cuentas[numero].getSaldo());
                } else {
                    System.out.println("Cuenta no encontrada.");
                }
            } else if (opcion.equals("C")) {
                for (int i = 0; i < cuentas.length; i++) {
                    cuentas[i].consultar();
                    System.out.println("Cuenta " + i + ": S/ " + cuentas[i].getSaldo());
                }
            }
        }
        scanner.close();
    }
}
