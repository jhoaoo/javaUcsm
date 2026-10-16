import java.io.*;

class Persona implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nombre;
    private final int edad;
    Persona(String nombre, int edad) { this.nombre = nombre; this.edad = edad; }
    public String toString() { return nombre + " (" + edad + " años)"; }
}
class Fecha implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int dia, mes, anio;
    Fecha(int dia, int mes, int anio) { this.dia = dia; this.mes = mes; this.anio = anio; }
    public String toString() { return dia + "/" + mes + "/" + anio; }
}
class Alumno extends Persona {
    private static final long serialVersionUID = 1L;
    private final Fecha matricula;
    Alumno(String nombre, int edad, Fecha matricula) {
        super(nombre, edad); this.matricula = matricula;
    }
    public String toString() { return super.toString() + " - Matrícula: " + matricula; }
}
public class Main {
    public static void main(String[] args) {
        String archivo = "alumnos.dat";
        Alumno[] alumnos = {
            new Alumno("Lucía", 19, new Fecha(15, 3, 2026)),
            new Alumno("Diego", 20, new Fecha(16, 3, 2026)),
            new Alumno("María", 18, new Fecha(17, 3, 2026))
        };
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            for (Alumno alumno : alumnos) salida.writeObject(alumno);
            System.out.println("Tres alumnos guardados.");
        } catch (IOException e) { System.out.println("Error al guardar: " + e.getMessage()); return; }
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            while (true) System.out.println((Alumno) entrada.readObject());
        } catch (EOFException e) {
            System.out.println("Fin de lectura de alumnos.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al recuperar: " + e.getMessage());
        }
    }
}
