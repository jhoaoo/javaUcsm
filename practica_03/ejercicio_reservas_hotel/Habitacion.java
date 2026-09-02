import java.time.LocalDate;

public abstract class Habitacion {
    private int numero;
    private String tipo;
    private double precioBase;
    private GestorDisponibilidadHabitacion gestorDisponibilidad;

    public Habitacion(int numero, String tipo, double precioBase,
                      GestorDisponibilidadHabitacion gestorDisponibilidad) {
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.gestorDisponibilidad = gestorDisponibilidad;
    }

    public boolean estaDisponible(LocalDate entrada, LocalDate salida) {
        return gestorDisponibilidad.estaDisponible(this, entrada, salida);
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    @Override
    public String toString() {
        return "Habitacion " + numero + " - " + tipo
                + " - S/ " + String.format("%.2f", precioBase) + " por noche";
    }
}
