import java.time.LocalDateTime;

public class PoliticaCancelacionFlexible implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        LocalDateTime limite = reserva.getFechaEntrada()
                .atStartOfDay().minusHours(24);
        return !LocalDateTime.now().isAfter(limite);
    }

    @Override
    public double calcularPenalizacion(double importeReserva) {
        return 0;
    }

    @Override
    public String getNombre() {
        return "Flexible";
    }
}
