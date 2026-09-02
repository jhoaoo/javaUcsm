public interface PoliticaCancelacion {
    boolean puedeCancelar(Reserva reserva);
    double calcularPenalizacion(double importeReserva);
    String getNombre();
}
