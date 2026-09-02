public class HabitacionPremium extends Habitacion
        implements ServicioLimpieza, ServicioComida, ServicioLavanderia {
    public HabitacionPremium(int numero, double precioBase,
                             GestorDisponibilidadHabitacion gestor) {
        super(numero, "Premium", precioBase, gestor);
    }

    @Override
    public void solicitarLimpieza() {
        System.out.println("Limpieza solicitada para la habitacion premium " + getNumero());
    }

    @Override
    public void solicitarComida(String pedido) {
        System.out.println("Pedido premium para la habitacion " + getNumero()
                + ": " + pedido);
    }

    @Override
    public void solicitarLavanderia() {
        System.out.println("Lavanderia solicitada para la habitacion premium "
                + getNumero());
    }
}
