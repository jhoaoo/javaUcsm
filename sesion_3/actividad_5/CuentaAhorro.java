public class CuentaAhorro extends Cuenta {
    private double tasaInteres;
    private double minSaldo;

    public CuentaAhorro(int numero, double saldo, double tasaInteres) {
        super(numero, saldo);
        this.tasaInteres = tasaInteres;
        this.minSaldo = saldo;
    }

    public void retirar(double monto) {
        super.retirar(monto);
        if (getSaldo() < minSaldo) {
            minSaldo = getSaldo();
        }
    }

    public void consultar() {
        double interes = minSaldo * tasaInteres / 100;
        depositar(interes);
        minSaldo = getSaldo();
    }
}
