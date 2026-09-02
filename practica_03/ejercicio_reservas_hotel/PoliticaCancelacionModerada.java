import java.time.LocalDateTime;

public class PoliticaCancelacionModerada implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        LocalDateTime limite = reserva.getFechaEntrada()
                .atStartOfDay().minusHours(72);
        return !LocalDateTime.now().isAfter(limite);
    }

    @Override
    public double calcularPenalizacion(double importeReserva) {
        return importeReserva * 0.50;
    }

    @Override
    public String getNombre() {
        return "Moderada";
    }
}
