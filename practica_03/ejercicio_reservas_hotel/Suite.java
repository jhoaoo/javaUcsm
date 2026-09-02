public class Suite extends Habitacion
        implements ServicioLimpieza, ServicioComida, ServicioLavanderia {
    public Suite(int numero, double precioBase,
                 GestorDisponibilidadHabitacion gestor) {
        super(numero, "Suite", precioBase, gestor);
    }

    @Override
    public void solicitarLimpieza() {
        System.out.println("Limpieza premium solicitada para la suite " + getNumero());
    }

    @Override
    public void solicitarComida(String pedido) {
        System.out.println("Pedido para la suite " + getNumero() + ": " + pedido);
    }

    @Override
    public void solicitarLavanderia() {
        System.out.println("Lavanderia solicitada para la suite " + getNumero());
    }
}
