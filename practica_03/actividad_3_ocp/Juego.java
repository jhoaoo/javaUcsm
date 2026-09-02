public class Juego {
    public static void main(String[] args) {
        Arma[] armas = {
            new Espada(50),
            new Arco(30),
            new Hacha(45)
        };

        CalculadoraDanio calculadora = new CalculadoraDanio();

        for (Arma arma : armas) {
            int danio = calculadora.calcular(arma, 10);
            System.out.println("Danio de " + arma.getNombre() + ": " + danio);
        }
    }
}
