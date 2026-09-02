import java.time.LocalDate;

public class PromocionClienteFrecuente implements Promocion {
    @Override
    public double aplicar(double subtotal, Habitacion habitacion,
                          Cliente cliente, LocalDate fechaEntrada) {
        if (cliente.getHistorial().size() >= 2) {
            return subtotal * 0.90;
        }
        return subtotal;
    }
}
