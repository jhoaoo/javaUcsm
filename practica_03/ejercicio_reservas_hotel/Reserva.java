import java.time.LocalDate;

public class Reserva {
    private int codigo;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private PoliticaCancelacion politicaCancelacion;
    private double importeTotal;
    private double penalizacion;
    private boolean activa = true;

    public Reserva(int codigo, Cliente cliente, Habitacion habitacion,
                   LocalDate fechaEntrada, LocalDate fechaSalida,
                   PoliticaCancelacion politicaCancelacion, double importeTotal) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.politicaCancelacion = politicaCancelacion;
        this.importeTotal = importeTotal;
    }

    public boolean cancelar() {
        if (activa && politicaCancelacion.puedeCancelar(this)) {
            penalizacion = politicaCancelacion.calcularPenalizacion(importeTotal);
            activa = false;
            return true;
        }
        return false;
    }

    public int getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public double getPenalizacion() {
        return penalizacion;
    }

    public boolean isActiva() {
        return activa;
    }

    public String getNombrePolitica() {
        return politicaCancelacion.getNombre();
    }
}
