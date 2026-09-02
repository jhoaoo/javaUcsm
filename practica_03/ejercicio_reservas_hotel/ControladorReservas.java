import java.time.LocalDate;

public class ControladorReservas {
    private GestorDisponibilidadHabitacion gestorDisponibilidad;
    private CalculadoraPrecioHabitacion calculadoraPrecio;
    private NotificadorReserva notificador;
    private int siguienteCodigo = 1;

    public ControladorReservas(
            GestorDisponibilidadHabitacion gestorDisponibilidad,
            CalculadoraPrecioHabitacion calculadoraPrecio,
            NotificadorReserva notificador) {
        this.gestorDisponibilidad = gestorDisponibilidad;
        this.calculadoraPrecio = calculadoraPrecio;
        this.notificador = notificador;
    }

    public Reserva crearReserva(Cliente cliente, Habitacion habitacion,
                                LocalDate entrada, LocalDate salida,
                                PoliticaCancelacion politica) {
        if (!habitacion.estaDisponible(entrada, salida)) {
            System.out.println("La habitacion " + habitacion.getNumero()
                    + " no esta disponible.");
            return null;
        }

        double total = calculadoraPrecio.calcular(
                habitacion, cliente, entrada, salida);
        Reserva reserva = new Reserva(siguienteCodigo++, cliente, habitacion,
                entrada, salida, politica, total);

        gestorDisponibilidad.registrarReserva(reserva);
        cliente.agregarReserva(reserva);
        notificador.notificar(cliente, "Reserva " + reserva.getCodigo()
                + " confirmada por S/ " + String.format("%.2f", total));
        return reserva;
    }

    public boolean cancelarReserva(Reserva reserva) {
        boolean cancelada = reserva.cancelar();
        if (cancelada) {
            gestorDisponibilidad.liberarReserva(reserva);
            notificador.notificar(reserva.getCliente(), "Reserva "
                    + reserva.getCodigo() + " cancelada. Penalizacion: S/ "
                    + String.format("%.2f", reserva.getPenalizacion()));
        }
        return cancelada;
    }
}
