public class ContadorTest {
    public static void main(String[] args) {
        System.out.println("Acumulador inicial: " + Contador.acumulador());

        Contador c1 = new Contador(3);
        Contador c2 = new Contador();

        c1.inc();
        c2.inc();

        System.out.println("Valor c1: " + c1.getValor());
        System.out.println("Valor c2: " + c2.getValor());
        System.out.println("Acumulador: " + Contador.acumulador());
        System.out.println("Contadores creados: " + Contador.getNContadores());
        System.out.println("Ultimo valor inicial: " + Contador.getUltimoContador());
    }
}
