class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El deposito debe ser mayor que cero.");
        }
        saldo += monto;
    }

    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El retiro debe ser mayor que cero.");
        }
        if (monto > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente. Saldo actual: S/ " + saldo);
        }
        saldo -= monto;
    }

    public double getSaldo() { return saldo; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTitular() { return titular; }
}

public class Experiencia1 {
    public static void main(String[] args) {
        try {
            CuentaBancaria cuenta = new CuentaBancaria("001-2026", "Ana Torres", 500);
            System.out.println("Cuenta creada. Saldo: S/ " + cuenta.getSaldo());

            cuenta.depositar(200);
            System.out.println("Deposito correcto. Saldo: S/ " + cuenta.getSaldo());

            cuenta.retirar(150);
            System.out.println("Retiro correcto. Saldo: S/ " + cuenta.getSaldo());

            cuenta.depositar(-20);
        } catch (IllegalArgumentException e) {
            System.out.println("Validacion: " + e.getMessage());
        } catch (SaldoInsuficienteException e) {
            System.out.println("Operacion rechazada: " + e.getMessage());
        }

        try {
            CuentaBancaria invalida = new CuentaBancaria("002-2026", "Luis Perez", -10);
            System.out.println(invalida.getSaldo());
        } catch (IllegalArgumentException e) {
            System.out.println("Creacion rechazada: " + e.getMessage());
        }

        try {
            CuentaBancaria cuenta = new CuentaBancaria("003-2026", "Maria Diaz", 100);
            cuenta.retirar(250);
        } catch (SaldoInsuficienteException e) {
            System.out.println("Retiro rechazado: " + e.getMessage());
        }
    }
}
