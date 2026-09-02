import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String correo;
    private List<Reserva> historial = new ArrayList<>();

    public Cliente(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public void agregarReserva(Reserva reserva) {
        historial.add(reserva);
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public List<Reserva> getHistorial() {
        return historial;
    }
}
