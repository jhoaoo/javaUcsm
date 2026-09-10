class DivisionPorCeroException extends ArithmeticException {
    public DivisionPorCeroException(String mensaje) { super(mensaje); }
}

class Calculadora {
    public double sumar(double a, double b) { return a + b; }
    public double restar(double a, double b) { return a - b; }
    public double multiplicar(double a, double b) { return a * b; }

    public double dividir(double a, double b) {
        if (b == 0) throw new DivisionPorCeroException("No se puede dividir entre cero.");
        if (!Double.isFinite(a) || !Double.isFinite(b)) {
            throw new IllegalArgumentException("Los operandos deben ser numeros finitos.");
        }
        return a / b;
    }
}

public class Ejercicio2 {
    public static void main(String[] args) {
        Calculadora c = new Calculadora();
        System.out.println("Suma: " + c.sumar(8, 2));
        System.out.println("Resta: " + c.restar(8, 2));
        System.out.println("Multiplicacion: " + c.multiplicar(8, 2));

        try {
            System.out.println("Division: " + c.dividir(8, 0));
        } catch (IllegalArgumentException e) {
            System.out.println("Argumento invalido: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Error aritmetico: " + e.getMessage());
        }
    }
}
