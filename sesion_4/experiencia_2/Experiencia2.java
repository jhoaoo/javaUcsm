class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) { super(mensaje); }
}
class CuentaNoEncontradaException extends Exception {
    public CuentaNoEncontradaException(String mensaje) { super(mensaje); }
}
class SaldoNoCeroException extends Exception {
    public SaldoNoCeroException(String mensaje) { super(mensaje); }
}

class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private boolean activa = true;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        if (saldo < 0) throw new IllegalArgumentException("Saldo inicial invalido.");
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void transferir(CuentaBancaria destino, double monto)
            throws CuentaNoEncontradaException, SaldoInsuficienteException {
        if (destino == null) {
            throw new CuentaNoEncontradaException("La cuenta destino no existe.");
        }
        if (monto <= 0) throw new IllegalArgumentException("Monto invalido.");
        if (monto > saldo) {
            throw new SaldoInsuficienteException("No hay saldo suficiente para transferir.");
        }
        saldo -= monto;
        destino.saldo += monto;
    }

    public void cerrarCuenta() throws SaldoNoCeroException {
        if (saldo != 0) {
            throw new SaldoNoCeroException("La cuenta solo puede cerrarse con saldo cero.");
        }
        activa = false;
    }

    public double getSaldo() { return saldo; }
    public boolean isActiva() { return activa; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTitular() { return titular; }
}

public class Experiencia2 {
    public static void main(String[] args) {
        CuentaBancaria origen = new CuentaBancaria("1001", "Carlos Rios", 600);
        CuentaBancaria destino = new CuentaBancaria("1002", "Elena Ruiz", 100);

        try {
            origen.transferir(destino, 250);
            System.out.println("Transferencia correcta.");
            System.out.println("Saldo origen: S/ " + origen.getSaldo());
            System.out.println("Saldo destino: S/ " + destino.getSaldo());

            origen.transferir(null, 20);
        } catch (CuentaNoEncontradaException | SaldoInsuficienteException e) {
            System.out.println("Transferencia rechazada: " + e.getMessage());
        }

        try {
            origen.cerrarCuenta();
        } catch (SaldoNoCeroException e) {
            System.out.println("Cierre rechazado: " + e.getMessage());
        }

        CuentaBancaria vacia = new CuentaBancaria("1003", "Rosa Vega", 0);
        try {
            vacia.cerrarCuenta();
            System.out.println("Cuenta 1003 cerrada: " + !vacia.isActiva());
        } catch (SaldoNoCeroException e) {
            System.out.println(e.getMessage());
        }
    }
}
