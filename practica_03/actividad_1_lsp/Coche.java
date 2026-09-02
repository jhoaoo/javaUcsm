public class Coche extends Vehiculo {
    public Coche(String nombre) {
        super(nombre);
    }

    @Override
    public void acelerar() {
        System.out.println(getNombre() + " acelera usando el motor.");
    }
}
