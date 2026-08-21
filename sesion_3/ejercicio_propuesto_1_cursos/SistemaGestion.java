public class SistemaGestion {
    private Curso[] cursos;
    private int cantidadCursos;

    public SistemaGestion() {
        cursos = new Curso[10];
        cantidadCursos = 0;
    }

    public void registrarCurso(Curso curso) {
        if (cantidadCursos < cursos.length) {
            cursos[cantidadCursos] = curso;
            cantidadCursos++;
        }
    }

    public void mostrarCursos() {
        for (int i = 0; i < cantidadCursos; i++) {
            cursos[i].mostrarInformacion();
            System.out.println();
        }
    }

    public static void mostrarResumen() {
        System.out.println("Personas registradas: " + Persona.getTotalPersonas());
        System.out.println("Cursos creados: " + Curso.getTotalCursos());
    }
}
