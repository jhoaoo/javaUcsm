public class Bicicleta extends Vehiculo {
    public Bicicleta(String nombre) {
        super(nombre);
    }

    @Override
    public void acelerar() {
        System.out.println(getNombre() + " acelera pedaleando.");
    }
}
