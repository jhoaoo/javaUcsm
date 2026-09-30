package propuestos;

public class Main {
    public static <F, S> void imprimirPar(Par<F, S> par) {
        System.out.println(par);
    }

    public static void main(String[] args) {
        Par<String, Integer> curso = new Par<>("Java", 17);
        Par<Double, Boolean> medida = new Par<>(3.5, true);
        Par<Persona, Integer> persona = new Par<>(new Persona("Ana", 20), 101);
        imprimirPar(curso);
        imprimirPar(medida);
        imprimirPar(persona);
        Par<Persona, Integer> copia = new Par<>(new Persona("Ana", 20), 101);
        System.out.println("Personas distintas, mismos datos: "
                + persona.esIgual(copia));
        System.out.println("hashCode coherente: "
                + (persona.getPrimero().hashCode()
                == copia.getPrimero().hashCode()));
    }
}
