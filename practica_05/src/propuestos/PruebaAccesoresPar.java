package propuestos;

public class PruebaAccesoresPar {
    public static void main(String[] args) {
        Par<String, Integer> par = new Par<>("Java", 17);
        System.out.println("Par inicial: " + par);
        System.out.println("getPrimero(): " + par.getPrimero());
        System.out.println("getSegundo(): " + par.getSegundo());
        par.setPrimero("Lenguajes de Programación III");
        par.setSegundo(5);
        System.out.println("Después de los setters: " + par);
    }
}
