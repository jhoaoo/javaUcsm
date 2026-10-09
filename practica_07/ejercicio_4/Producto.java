public class Producto {
    private int id;
    private String nombre;
    private double precio;

    public Producto(int id, String nombre, double precio) {
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID mayor que cero.");
        }
        this.id = id;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()
                || nombre.contains(";") || nombre.contains("\n")
                || nombre.contains("\r")) {
            throw new IllegalArgumentException("Nombre no valido.");
        }
        this.nombre = nombre.trim();
    }

    public void setPrecio(double precio) {
        if (!Double.isFinite(precio) || precio < 0) {
            throw new IllegalArgumentException("Precio finito y no negativo.");
        }
        this.precio = precio;
    }

    @Override
    public String toString() {
        return id + " | " + nombre + " | S/ "
                + String.format(java.util.Locale.ROOT, "%.2f", precio);
    }
}
