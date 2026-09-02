import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CalculadoraPrecioHabitacion {
    private List<Promocion> promociones = new ArrayList<>();

    public void agregarPromocion(Promocion promocion) {
        promociones.add(promocion);
    }

    public double calcular(Habitacion habitacion, Cliente cliente,
                           LocalDate entrada, LocalDate salida) {
        long noches = ChronoUnit.DAYS.between(entrada, salida);
        double total = noches * habitacion.getPrecioBase();

        for (Promocion promocion : promociones) {
            total = promocion.aplicar(total, habitacion, cliente, entrada);
        }
        return total;
    }
}
