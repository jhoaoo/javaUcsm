package actividades;

public class ExcepcionPilaLlena extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ExcepcionPilaLlena(String mensaje) { super(mensaje); }
}
