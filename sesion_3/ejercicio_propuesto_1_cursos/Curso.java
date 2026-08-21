public class Curso implements Mostrable {
    private String codigo;
    private String nombre;
    private String categoria;
    private Profesor profesor;
    private Estudiante[] estudiantes;
    private int cantidadEstudiantes;
    private Horario horario;
    private static int totalCursos = 0;
    public static final int MAX_ESTUDIANTES = 30;

    public Curso(String codigo, String nombre, String categoria, Profesor profesor, String dia, String hora) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.profesor = profesor;
        this.horario = new Horario(dia, hora);
        this.estudiantes = new Estudiante[MAX_ESTUDIANTES];
        this.cantidadEstudiantes = 0;
        totalCursos++;
    }

    public void inscribirEstudiante(Estudiante estudiante) {
        if (cantidadEstudiantes < MAX_ESTUDIANTES) {
            estudiantes[cantidadEstudiantes] = estudiante;
            cantidadEstudiantes++;
        }
    }

    public boolean estaDisponible() {
        return cantidadEstudiantes < MAX_ESTUDIANTES;
    }

    public static int getTotalCursos() {
        return totalCursos;
    }

    public void mostrarInformacion() {
        System.out.println("Curso: " + codigo + " - " + nombre + " - " + categoria);
        System.out.println("Profesor: " + profesor.getNombre());
        System.out.println("Horario: " + horario);
        System.out.println("Matriculados: " + cantidadEstudiantes);
        System.out.println("Disponible: " + estaDisponible());
    }
}
