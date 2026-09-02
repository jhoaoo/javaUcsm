import java.time.LocalDate;

public class PromocionTemporada implements Promocion {
    private LocalDate inicio;
    private LocalDate fin;
    private double descuento;

    public PromocionTemporada(LocalDate inicio, LocalDate fin,
                              double descuento) {
        this.inicio = inicio;
        this.fin = fin;
        this.descuento = descuento;
    }

    @Override
    public double aplicar(double subtotal, Habitacion habitacion,
                          Cliente cliente, LocalDate fechaEntrada) {
        boolean dentroDeTemporada = !fechaEntrada.isBefore(inicio)
                && !fechaEntrada.isAfter(fin);
        if (dentroDeTemporada) {
            return subtotal * (1 - descuento);
        }
        return subtotal;
    }
}
