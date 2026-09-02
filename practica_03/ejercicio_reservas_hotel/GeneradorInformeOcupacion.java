import java.util.List;

public class GeneradorInformeOcupacion {
    public void generar(List<Reserva> reservas) {
        System.out.println("=== INFORME DE OCUPACION ===");
        for (Reserva reserva : reservas) {
            if (reserva.isActiva()) {
                System.out.println(reserva.getHabitacion().getTipo()
                        + " - habitacion " + reserva.getHabitacion().getNumero());
            }
        }
    }
}
