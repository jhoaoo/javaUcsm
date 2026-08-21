public class Profesor extends Persona {
    private String especialidad;

    public Profesor(String nombre, String codigo, String especialidad) {
        super(nombre, codigo);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() { return especialidad; }
    public String getRol() { return "Profesor de " + especialidad; }
}
