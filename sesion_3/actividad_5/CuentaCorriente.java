public class CuentaCorriente extends Cuenta {
    private int retiros;
    private static final int LIBRE_RETIROS = 3;
    private static final double TARIFA_TRANSACCION = 3.0;

    public CuentaCorriente(int numero, double saldo) {
        super(numero, saldo);
        retiros = 0;
    }

    public void retirar(double monto) {
        retiros++;
        if (retiros > LIBRE_RETIROS) {
            super.retirar(monto + TARIFA_TRANSACCION);
        } else {
            super.retirar(monto);
        }
    }

    public void consultar() {
        retiros = 0;
    }
}
