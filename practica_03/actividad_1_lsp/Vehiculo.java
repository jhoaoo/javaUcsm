public abstract class Vehiculo {
    private String nombre;

    public Vehiculo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void acelerar();
}
