public class Alumno extends Persona {
    private static final long serialVersionUID = 1L;
    private Fecha fechaMatricula;

    public Alumno(String nif, String nombre, int edad, Fecha fecha) {
        super(nif, nombre, edad);
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria.");
        }
        fechaMatricula = fecha;
    }

    @Override
    public String toString() {
        return super.toString() + " | Matricula: " + fechaMatricula;
    }
}
