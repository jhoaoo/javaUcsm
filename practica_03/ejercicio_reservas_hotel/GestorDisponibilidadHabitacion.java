import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorDisponibilidadHabitacion {
    private List<Reserva> reservas = new ArrayList<>();

    public boolean estaDisponible(Habitacion habitacion,
                                  LocalDate entrada, LocalDate salida) {
        for (Reserva reserva : reservas) {
            boolean mismaHabitacion =
                    reserva.getHabitacion().getNumero() == habitacion.getNumero();
            boolean fechasSeCruzan = entrada.isBefore(reserva.getFechaSalida())
                    && salida.isAfter(reserva.getFechaEntrada());

            if (reserva.isActiva() && mismaHabitacion && fechasSeCruzan) {
                return false;
            }
        }
        return true;
    }

    public void registrarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void liberarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }
}
