import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

class Personaje {
    private String nombre;
    private int vida, ataque, defensa, alcance;
    Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        setNombre(nombre); setVida(vida); setAtaque(ataque);
        setDefensa(defensa); setAlcance(alcance);
    }
    String getNombre() { return nombre; }
    int getVida() { return vida; }
    int getAtaque() { return ataque; }
    int getDefensa() { return defensa; }
    int getAlcance() { return alcance; }
    void setNombre(String valor) { if (valor.isBlank() || valor.contains(";")) throw new IllegalArgumentException("Nombre inválido"); nombre = valor.trim(); }
    void setVida(int valor) { vida = positivo(valor); }
    void setAtaque(int valor) { ataque = positivo(valor); }
    void setDefensa(int valor) { defensa = positivo(valor); }
    void setAlcance(int valor) { alcance = positivo(valor); }
    private int positivo(int valor) { if (valor <= 0) throw new IllegalArgumentException("Los atributos deben ser mayores que cero"); return valor; }
    String guardar() { return nombre + ";" + vida + ";" + ataque + ";" + defensa + ";" + alcance; }
    public String toString() { return nombre + " | Vida " + vida + " | Ataque " + ataque + " | Defensa " + defensa + " | Alcance " + alcance; }
}

class Gestor {
    private final List<Personaje> personajes = new ArrayList<>();
    private final Path archivo = Paths.get("personajes.txt");
    Gestor() throws IOException { cargar(); }
    void cargar() throws IOException {
        personajes.clear();
        if (!Files.exists(archivo)) return;
        for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {
            if (linea.isBlank()) continue;
            String[] datos = linea.split(";", -1);
            if (datos.length != 5) throw new IOException("Registro inválido: " + linea);
            try {
                Personaje p = new Personaje(datos[0], Integer.parseInt(datos[1]), Integer.parseInt(datos[2]),
                        Integer.parseInt(datos[3]), Integer.parseInt(datos[4]));
                if (buscar(p.getNombre()) != null) throw new IOException("Nombre duplicado en el archivo");
                personajes.add(p);
            } catch (IllegalArgumentException e) {
                throw new IOException("Datos incorrectos en personajes.txt: " + e.getMessage(), e);
            }
        }
    }
    void guardar() throws IOException {
        List<String> lineas = new ArrayList<>();
        for (Personaje p : personajes) lineas.add(p.guardar());
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }
    Personaje buscar(String nombre) {
        for (Personaje p : personajes) if (p.getNombre().equalsIgnoreCase(nombre.trim())) return p;
        return null;
    }
    void agregar(Personaje p) throws IOException {
        if (buscar(p.getNombre()) != null) throw new IllegalArgumentException("El personaje ya existe");
        personajes.add(p); guardar();
    }
    void modificar(String nombre, int vida, int ataque, int defensa, int alcance) throws IOException {
        Personaje p = buscar(nombre);
        if (p == null) throw new IllegalArgumentException("Personaje no encontrado");
        if (vida <= 0 || ataque <= 0 || defensa <= 0 || alcance <= 0) throw new IllegalArgumentException("Valores mayores que cero");
        p.setVida(vida); p.setAtaque(ataque); p.setDefensa(defensa); p.setAlcance(alcance);
        guardar();
    }
    void borrar(String nombre) throws IOException {
        Personaje p = buscar(nombre);
        if (p == null) throw new IllegalArgumentException("Personaje no encontrado");
        personajes.remove(p); guardar();
    }
    List<Personaje> listar() { return Collections.unmodifiableList(personajes); }
}

public class Main {
    private static final Scanner teclado = new Scanner(System.in);
    private static String leer(String pregunta) { System.out.print(pregunta); return teclado.nextLine(); }
    private static int numero(String pregunta) { return Integer.parseInt(leer(pregunta)); }
    public static void main(String[] args) {
        try {
            Gestor gestor = new Gestor();
            while (true) {
                System.out.println("\n1 Añadir | 2 Mostrar | 3 Modificar | 4 Borrar | 0 Salir");
                String opcion = leer("Opción: ");
                if (opcion.equals("0")) break;
                try {
                    switch (opcion) {
                        case "1": gestor.agregar(new Personaje(leer("Nombre: "), numero("Vida: "),
                                  numero("Ataque: "), numero("Defensa: "), numero("Alcance: "))); break;
                        case "2": for (Personaje p : gestor.listar()) System.out.println(p); break;
                        case "3": gestor.modificar(leer("Nombre: "), numero("Vida: "), numero("Ataque: "),
                                  numero("Defensa: "), numero("Alcance: ")); break;
                        case "4": gestor.borrar(leer("Nombre: ")); break;
                        default: System.out.println("Opción desconocida"); continue;
                    }
                    System.out.println("Operación realizada.");
                } catch (IOException | IllegalArgumentException e) {
                    System.out.println("No se pudo realizar: " + e.getMessage());
                }
            }
        } catch (IOException e) { System.out.println("No se pudo cargar el archivo: " + e.getMessage()); }
    }
}
