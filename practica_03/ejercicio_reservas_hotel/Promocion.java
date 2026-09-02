import java.time.LocalDate;

public interface Promocion {
    double aplicar(double subtotal, Habitacion habitacion,
                   Cliente cliente, LocalDate fechaEntrada);
}
