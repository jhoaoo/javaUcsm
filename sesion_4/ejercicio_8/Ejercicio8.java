import java.util.ArrayList;
import java.util.List;

class EquipoNoDisponibleException extends Exception {
    public EquipoNoDisponibleException(String mensaje) { super(mensaje); }
}
class ReservaNoEncontradaException extends Exception {
    public ReservaNoEncontradaException(String mensaje) { super(mensaje); }
}

class Equipo {
    private int id;
    private String nombre;
    private boolean disponible = true;
    public Equipo(int id, String nombre) { this.id = id; this.nombre = nombre; }
    public int getId() { return id; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public String toString() { return id + " - " + nombre + " - " + (disponible ? "Disponible" : "Reservado"); }
}

class Estudiante {
    private String codigo;
    private String nombre;
    public Estudiante(String codigo, String nombre) { this.codigo = codigo; this.nombre = nombre; }
    public String toString() { return codigo + " - " + nombre; }
}

class Reserva {
    private int id;
    private Equipo equipo;
    private Estudiante estudiante;
    public Reserva(int id, Equipo equipo, Estudiante estudiante) {
        this.id = id; this.equipo = equipo; this.estudiante = estudiante;
    }
    public int getId() { return id; }
    public Equipo getEquipo() { return equipo; }
    public String toString() { return "Reserva " + id + " | " + equipo + " | " + estudiante; }
}

class SistemaLaboratorio {
    private List<Equipo> equipos = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();
    private int siguienteReserva = 1;

    public void agregarEquipo(Equipo equipo) { equipos.add(equipo); }

    public Equipo buscarEquipo(int id) {
        for (Equipo e : equipos) if (e.getId() == id) return e;
        throw new IllegalArgumentException("Equipo inexistente: " + id);
    }

    public Reserva reservar(int idEquipo, Estudiante estudiante) throws EquipoNoDisponibleException {
        Equipo equipo = buscarEquipo(idEquipo);
        if (!equipo.isDisponible()) throw new EquipoNoDisponibleException("El equipo ya esta reservado.");
        equipo.setDisponible(false);
        Reserva reserva = new Reserva(siguienteReserva++, equipo, estudiante);
        reservas.add(reserva);
        return reserva;
    }

    public void cancelar(int idReserva) throws ReservaNoEncontradaException {
        Reserva encontrada = null;
        for (Reserva r : reservas) if (r.getId() == idReserva) encontrada = r;
        if (encontrada == null) throw new ReservaNoEncontradaException("No existe la reserva " + idReserva);
        encontrada.getEquipo().setDisponible(true);
        reservas.remove(encontrada);
    }

    public void listarEquipos() { for (Equipo e : equipos) System.out.println(e); }
}

public class Ejercicio8 {
    public static void main(String[] args) {
        SistemaLaboratorio sistema = new SistemaLaboratorio();
        sistema.agregarEquipo(new Equipo(1, "PC-LAB-01"));
        sistema.agregarEquipo(new Equipo(2, "PC-LAB-02"));
        Estudiante estudiante = new Estudiante("2025000074", "Jhoaoo Llerena");

        try {
            Reserva r = sistema.reservar(1, estudiante);
            System.out.println(r);
            sistema.listarEquipos();
            sistema.reservar(1, estudiante);
        } catch (EquipoNoDisponibleException e) {
            System.out.println("Reserva rechazada: " + e.getMessage());
        }

        try {
            sistema.cancelar(1);
            System.out.println("Reserva 1 cancelada.");
            sistema.listarEquipos();
            sistema.cancelar(99);
        } catch (ReservaNoEncontradaException e) {
            System.out.println("Cancelacion rechazada: " + e.getMessage());
        }
    }
}
