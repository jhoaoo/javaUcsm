package actividades;

import java.util.Arrays;
import java.util.Objects;

/** Pila LIFO de capacidad fija. Admite null como elemento. */
public class Pila<E> {
    private final E[] elementos;
    private int superior = -1;

    public Pila() { this(10); }

    @SuppressWarnings("unchecked")
    public Pila(int capacidad) {
        int tamanio = capacidad > 0 ? capacidad : 10;
        // El arreglo permanece privado y solo recibe valores de tipo E.
        elementos = (E[]) new Object[tamanio];
    }

    public void push(E valor) {
        if (superior == elementos.length - 1) {
            throw new ExcepcionPilaLlena("No se puede insertar: " + valor);
        }
        elementos[++superior] = valor;
    }

    public E pop() {
        if (superior == -1) {
            throw new ExcepcionPilaVacia("No se puede extraer de una pila vacía");
        }
        E valor = elementos[superior];
        elementos[superior--] = null; // Libera la referencia retirada.
        return valor;
    }

    public int size() { return superior + 1; }
    public int capacidad() { return elementos.length; }

    /** Busca desde el tope hacia el fondo, sin retirar elementos. */
    public boolean contains(E elemento) {
        for (int i = superior; i >= 0; i--) {
            if (Objects.equals(elementos[i], elemento)) return true;
        }
        return false;
    }

    /** Compara cantidad y orden lógico, no la capacidad disponible. */
    public boolean esIgual(Pila<E> otraPila) {
        if (otraPila == null || size() != otraPila.size()) return false;
        for (int i = superior; i >= 0; i--) {
            if (!Objects.equals(elementos[i], otraPila.elementos[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(elementos, size()));
    }
}
