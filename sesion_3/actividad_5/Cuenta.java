public abstract class Cuenta {
    private int numero;
    private double saldo;

    public Cuenta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public void depositar(double monto) {
        saldo = saldo + monto;
    }

    public void retirar(double monto) {
        if (monto <= saldo) {
            saldo = saldo - monto;
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public int getNumero() { return numero; }
    public double getSaldo() { return saldo; }

    public abstract void consultar();
}
