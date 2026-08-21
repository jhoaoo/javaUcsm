public class Estudiante extends Persona {
    private int ciclo;

    public Estudiante(String nombre, String codigo, int ciclo) {
        super(nombre, codigo);
        this.ciclo = ciclo;
    }

    public int getCiclo() { return ciclo; }
    public String getRol() { return "Estudiante"; }
}
