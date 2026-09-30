package actividades;

/** Indica que el intervalo solicitado no cumple las reglas de la guía. */
public class InvalidSubscriptException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public InvalidSubscriptException(String mensaje) { super(mensaje); }
}
