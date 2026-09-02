public class Principal {
    public static void hacerAcelerar(Vehiculo vehiculo) {
        vehiculo.acelerar();
    }

    public static void main(String[] args) {
        Vehiculo coche = new Coche("Toyota");
        Vehiculo bicicleta = new Bicicleta("Bicicleta montanera");

        hacerAcelerar(coche);
        hacerAcelerar(bicicleta);
    }
}
