import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class Gestor {
    private final Path archivo;
    private final ArrayList<Personaje> personajes = new ArrayList<>();

    public Gestor(String ruta) throws IOException {
        archivo = Paths.get(ruta);
        cargar();
    }

    private void cargar() throws IOException {
        if (!Files.exists(archivo)) {
            return;
        }
        try (BufferedReader entrada = Files.newBufferedReader(
                archivo, StandardCharsets.UTF_8)) {
            String linea;
            int numero = 0;
            while ((linea = entrada.readLine()) != null) {
                numero++;
                String[] datos = linea.split(";", -1);
                try {
                    if (datos.length != 5) {
                        throw new IllegalArgumentException("Se esperan 5 campos.");
                    }
                    Personaje p = new Personaje(datos[0],
                            Integer.parseInt(datos[1]),
                            Integer.parseInt(datos[2]),
                            Integer.parseInt(datos[3]),
                            Integer.parseInt(datos[4]));
                    if (buscar(p.getNombre()) != null) {
                        throw new IllegalArgumentException("Nombre duplicado.");
                    }
                    personajes.add(p);
                } catch (IllegalArgumentException e) {
                    throw new IOException("Linea " + numero + ": "
                            + e.getMessage(), e);
                }
            }
        }
    }

    public Personaje buscar(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Personaje> listar() {
        return new ArrayList<>(personajes);
    }

    private void guardar() throws IOException {
        Path temporal = Files.createTempFile(
                archivo.toAbsolutePath().getParent(), "personajes-", ".tmp");
        try {
            try (BufferedWriter salida = Files.newBufferedWriter(
                    temporal, StandardCharsets.UTF_8)) {
                for (Personaje p : personajes) {
                    salida.write(p.toString());
                    salida.newLine();
                }
            }
            Files.move(temporal, archivo, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(temporal);
        }
    }

    public void agregar(Personaje p) throws IOException {
        if (buscar(p.getNombre()) != null) {
            throw new IllegalArgumentException("El personaje ya existe.");
        }
        personajes.add(p);
        try {
            guardar();
        } catch (IOException e) {
            personajes.remove(p);
            throw e;
        }
    }

    public void modificar(String nombre, Personaje nuevo) throws IOException {
        Personaje anterior = buscar(nombre);
        if (anterior == null) {
            throw new IllegalArgumentException("Personaje no encontrado.");
        }
        Personaje repetido = buscar(nuevo.getNombre());
        if (repetido != null && repetido != anterior) {
            throw new IllegalArgumentException("El nuevo nombre ya existe.");
        }
        int indice = personajes.indexOf(anterior);
        personajes.set(indice, nuevo);
        try {
            guardar();
        } catch (IOException e) {
            personajes.set(indice, anterior);
            throw e;
        }
    }

    public void borrar(String nombre) throws IOException {
        Personaje p = buscar(nombre);
        if (p == null) {
            throw new IllegalArgumentException("Personaje no encontrado.");
        }
        int indice = personajes.indexOf(p);
        personajes.remove(indice);
        try {
            guardar();
        } catch (IOException e) {
            personajes.add(indice, p);
            throw e;
        }
    }
}
