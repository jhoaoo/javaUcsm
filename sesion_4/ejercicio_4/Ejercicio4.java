class Numero {
    private double valor;

    public Numero(double valor) { setValor(valor); }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo.");
        }
        this.valor = valor;
    }

    public double getValor() { return valor; }
}

public class Ejercicio4 {
    public static void main(String[] args) {
        try {
            Numero numero = new Numero(25.5);
            System.out.println("Valor inicial: " + numero.getValor());
            numero.setValor(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("Validacion: " + e.getMessage());
        }
    }
}
