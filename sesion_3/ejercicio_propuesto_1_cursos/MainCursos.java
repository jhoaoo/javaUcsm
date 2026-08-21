public class MainCursos {
    public static void main(String[] args) {
        Profesor profesor = new Profesor("Karim Guevara", "P001", "Programacion");
        Estudiante e1 = new Estudiante("Ana Torres", "E001", 4);
        Estudiante e2 = new Estudiante("Luis Perez", "E002", 4);

        Curso curso = new Curso("LP3", "Lenguajes de Programacion III", "Programacion",
                profesor, "Viernes", "09:00");
        curso.inscribirEstudiante(e1);
        curso.inscribirEstudiante(e2);

        SistemaGestion sistema = new SistemaGestion();
        sistema.registrarCurso(curso);

        Persona[] personas = {profesor, e1, e2};
        for (Persona persona : personas) {
            persona.mostrarInformacion();
        }

        System.out.println();
        sistema.mostrarCursos();
        SistemaGestion.mostrarResumen();
    }
}
