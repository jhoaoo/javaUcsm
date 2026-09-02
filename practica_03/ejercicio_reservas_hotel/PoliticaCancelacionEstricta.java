public class PoliticaCancelacionEstricta implements PoliticaCancelacion {
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        return false;
    }

    @Override
    public double calcularPenalizacion(double importeReserva) {
        return importeReserva;
    }

    @Override
    public String getNombre() {
        return "Estricta";
    }
}
