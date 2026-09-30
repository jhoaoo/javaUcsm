package actividades;

public class ExcepcionPilaVacia extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public ExcepcionPilaVacia(String mensaje) { super(mensaje); }
}
