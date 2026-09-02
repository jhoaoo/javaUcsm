public class HabitacionEstandar extends Habitacion implements ServicioLimpieza {
    public HabitacionEstandar(int numero, double precioBase,
                              GestorDisponibilidadHabitacion gestor) {
        super(numero, "Estandar", precioBase, gestor);
    }

    @Override
    public void solicitarLimpieza() {
        System.out.println("Limpieza solicitada para la habitacion " + getNumero());
    }
}
