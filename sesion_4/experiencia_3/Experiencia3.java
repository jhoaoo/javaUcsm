class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) { super(mensaje); }
}
class CuentaNoEncontradaException extends Exception {
    public CuentaNoEncontradaException(String mensaje) { super(mensaje); }
}
class LimiteCreditoExcedidoException extends SaldoInsuficienteException {
    public LimiteCreditoExcedidoException(String mensaje) { super(mensaje); }
}

class CuentaBancaria {
    protected String numeroCuenta;
    protected String titular;
    protected double saldo;

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        if (saldo < 0) throw new IllegalArgumentException("Saldo inicial invalido.");
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto <= 0) throw new IllegalArgumentException("Monto invalido.");
        if (monto > saldo) throw new SaldoInsuficienteException("Saldo insuficiente.");
        saldo -= monto;
    }

    public void depositar(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("Monto invalido.");
        saldo += monto;
    }

    public void transferir(CuentaBancaria destino, double monto)
            throws CuentaNoEncontradaException, SaldoInsuficienteException {
        if (destino == null) throw new CuentaNoEncontradaException("Cuenta destino inexistente.");
        retirar(monto);
        destino.depositar(monto);
    }

    public double getSaldo() { return saldo; }
}

class CuentaCredito extends CuentaBancaria {
    private double limiteCredito;

    public CuentaCredito(String numero, String titular, double saldo, double limiteCredito) {
        super(numero, titular, saldo);
        if (limiteCredito < 0) throw new IllegalArgumentException("Limite de credito invalido.");
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void retirar(double monto) throws LimiteCreditoExcedidoException {
        if (monto <= 0) throw new IllegalArgumentException("Monto invalido.");
        if (saldo - monto < -limiteCredito) {
            throw new LimiteCreditoExcedidoException(
                    "La operacion supera el limite de credito de S/ " + limiteCredito);
        }
        saldo -= monto;
    }

    @Override
    public void transferir(CuentaBancaria destino, double monto)
            throws CuentaNoEncontradaException, LimiteCreditoExcedidoException {
        if (destino == null) throw new CuentaNoEncontradaException("Cuenta destino inexistente.");
        retirar(monto);
        destino.depositar(monto);
    }
}

public class Experiencia3 {
    static void ejecutarRetiro(CuentaCredito cuenta, double monto)
            throws LimiteCreditoExcedidoException {
        cuenta.retirar(monto);
    }

    public static void main(String[] args) {
        CuentaCredito credito = new CuentaCredito("CC-01", "Diego Luna", 200, 500);
        CuentaBancaria destino = new CuentaBancaria("A-01", "Sofia Paz", 50);

        try {
            ejecutarRetiro(credito, 400);
            System.out.println("Retiro con credito permitido. Saldo: S/ " + credito.getSaldo());

            credito.transferir(destino, 200);
            System.out.println("Transferencia con credito permitida.");
            System.out.println("Saldo credito: S/ " + credito.getSaldo());
            System.out.println("Saldo destino: S/ " + destino.getSaldo());

            ejecutarRetiro(credito, 200);
        } catch (LimiteCreditoExcedidoException e) {
            System.out.println("Limite excedido: " + e.getMessage());
        } catch (CuentaNoEncontradaException e) {
            System.out.println("Destino invalido: " + e.getMessage());
        }
    }
}
