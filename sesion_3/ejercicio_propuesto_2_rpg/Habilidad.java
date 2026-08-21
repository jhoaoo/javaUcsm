public class Habilidad {
    private String nombre;
    private int danio;

    public Habilidad(String nombre, int danio) {
        this.nombre = nombre;
        this.danio = danio;
    }

    public String getNombre() { return nombre; }
    public int getDanio() { return danio; }
}
