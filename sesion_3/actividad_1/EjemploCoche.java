public class EjemploCoche {
    public static void main(String[] args) {
        Coche cocheDeportivo = new Coche("Ferrari", "F430", 2008, 250000);
        Coche cocheTodoTerreno = new Coche("Toyota", "Fortuner", 2022, 55000);

        cocheDeportivo.color = "Rojo";
        cocheTodoTerreno.color = "Negro";
        cocheDeportivo.setVelocidadMaxima(315);
        cocheTodoTerreno.setVelocidadMaxima(180);

        cocheDeportivo.encender();
        cocheDeportivo.acelerar();
        cocheDeportivo.frenar();
        cocheDeportivo.apagar();

        cocheTodoTerreno.encender();
        cocheTodoTerreno.acelerar();
        cocheTodoTerreno.frenar();
        cocheTodoTerreno.apagar();

        boolean descuento1 = cocheDeportivo.aplicarDescuento(10);
        boolean descuento2 = cocheTodoTerreno.aplicarDescuento(10);

        System.out.println("Descuento deportivo: " + descuento1);
        System.out.println("Precio deportivo: " + cocheDeportivo.getPrecio());
        System.out.println("Descuento todoterreno: " + descuento2);
        System.out.println("Precio todoterreno: " + cocheTodoTerreno.getPrecio());
    }
}
