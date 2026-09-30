package propuestos;

import java.util.ArrayList;

/** Almacena pares y protege la estructura de la lista interna. */
public class Contenedor<F, S> {
    private final ArrayList<Par<F, S>> pares = new ArrayList<>();

    public void agregarPar(F primero, S segundo) {
        pares.add(new Par<>(primero, segundo));
    }

    public Par<F, S> obtenerPar(int indice) { return pares.get(indice); }

    public ArrayList<Par<F, S>> obtenerTodosLosPares() {
        // Copia superficial: otra lista que contiene los mismos objetos Par.
        return new ArrayList<>(pares);
    }

    public void mostrarPares() {
        for (Par<F, S> par : pares) System.out.println(par);
    }
}
