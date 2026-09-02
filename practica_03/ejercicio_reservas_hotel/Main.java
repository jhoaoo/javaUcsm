import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        GestorDisponibilidadHabitacion gestor =
                new GestorDisponibilidadHabitacion();
        CalculadoraPrecioHabitacion calculadora =
                new CalculadoraPrecioHabitacion();

        LocalDate entrada = LocalDate.now().plusDays(10);
        LocalDate salida = entrada.plusDays(3);
        calculadora.agregarPromocion(
                new PromocionTemporada(entrada.minusDays(1),
                        salida.plusDays(1), 0.10));
        calculadora.agregarPromocion(new PromocionClienteFrecuente());

        NotificadorReserva notificador =
                new NotificadorReserva(new EnviadorCorreo());
        ControladorReservas controlador =
                new ControladorReservas(gestor, calculadora, notificador);

        Habitacion estandar = new HabitacionEstandar(101, 100, gestor);
        Habitacion suite = new Suite(201, 200, gestor);
        Habitacion premium = new HabitacionPremium(301, 300, gestor);

        Cliente ana = new Cliente("Ana Torres", "ana@correo.com");
        Cliente luis = new Cliente("Luis Perez", "luis@correo.com");
        Cliente maria = new Cliente("Maria Soto", "maria@correo.com");

        System.out.println("=== HABITACIONES ===");
        Habitacion[] habitaciones = {estandar, suite, premium};
        for (Habitacion habitacion : habitaciones) {
            System.out.println(habitacion);
        }

        System.out.println("\n=== RESERVAS ===");
        Reserva r1 = controlador.crearReserva(ana, estandar, entrada, salida,
                new PoliticaCancelacionFlexible());
        controlador.crearReserva(luis, estandar, entrada.plusDays(1),
                salida.plusDays(1), new PoliticaCancelacionModerada());
        Reserva r2 = controlador.crearReserva(luis, suite, entrada, salida,
                new PoliticaCancelacionModerada());
        Reserva r3 = controlador.crearReserva(maria, premium, entrada, salida,
                new PoliticaCancelacionEstricta());

        System.out.println("\nPolitica de reserva 1: " + r1.getNombrePolitica());
        System.out.println("Cancelacion flexible: "
                + controlador.cancelarReserva(r1));
        System.out.println("Cancelacion estricta: "
                + controlador.cancelarReserva(r3));
        System.out.println("Habitacion 101 disponible nuevamente: "
                + estandar.estaDisponible(entrada, salida));

        System.out.println("\n=== SERVICIOS ISP ===");
        if (suite instanceof ServicioComida) {
            ((ServicioComida) suite).solicitarComida("Cena vegetariana");
        }
        if (suite instanceof ServicioLavanderia) {
            ((ServicioLavanderia) suite).solicitarLavanderia();
        }

        PersonalLimpieza personal = new PersonalLimpieza("Carlos Ramos");
        personal.asignar(suite);
        System.out.println("Carga de trabajo: "
                + personal.getHabitacionesAsignadas() + " habitacion");

        new NotificadorReserva(new EnviadorSMS()).notificar(
                luis, "Recordatorio de check-in para la reserva "
                        + r2.getCodigo());

        System.out.println();
        new GeneradorInformeOcupacion().generar(gestor.getReservas());
        new GeneradorInformeIngresos().generar(gestor.getReservas());
    }
}
