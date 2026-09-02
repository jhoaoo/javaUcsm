public class PersonalLimpieza {
    private String nombre;
    private int habitacionesAsignadas;

    public PersonalLimpieza(String nombre) {
        this.nombre = nombre;
    }

    public void asignar(Habitacion habitacion) {
        habitacionesAsignadas++;
        System.out.println(nombre + " fue asignado a la habitacion "
                + habitacion.getNumero());
    }

    public int getHabitacionesAsignadas() {
        return habitacionesAsignadas;
    }
}
