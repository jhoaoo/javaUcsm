import java.io.*;

public class Main {
    public static void main(String[] args) {
        Alumno[] alumnos = {
            new Alumno("12345678A", "Lucas Gonzalez", 20,
                    new Fecha(5, 9, 2011)),
            new Alumno("98765432B", "Anacleto Jimenez", 19,
                    new Fecha(7, 9, 2011)),
            new Alumno("78342122Z", "Maria Zapata", 21,
                    new Fecha(8, 9, 2011))
        };
        try {
            try (ObjectOutputStream salida = new ObjectOutputStream(
                    new FileOutputStream("alumnos.dat"))) {
                for (Alumno alumno : alumnos) {
                    salida.writeObject(alumno);
                }
            }
            // La lectura permite comprobar la persistencia y composicion.
            try (ObjectInputStream entrada = new ObjectInputStream(
                    new FileInputStream("alumnos.dat"))) {
                for (int i = 0; i < alumnos.length; i++) {
                    Alumno alumno = (Alumno) entrada.readObject();
                    System.out.println(alumno);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
