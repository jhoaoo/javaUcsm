import java.util.List;

public class GeneradorInformeIngresos {
    public void generar(List<Reserva> reservas) {
        double total = 0;
        for (Reserva reserva : reservas) {
            if (reserva.isActiva()) {
                total += reserva.getImporteTotal();
            }
        }
        System.out.println("Ingresos por reservas activas: S/ "
                + String.format("%.2f", total));
    }
}
