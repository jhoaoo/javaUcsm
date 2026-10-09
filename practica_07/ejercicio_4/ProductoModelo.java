import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;

public class ProductoModelo {
    private final Path archivo = Paths.get("productos.txt");
    private ArrayList<Producto> registros = new ArrayList<>();

    public void leerRegistros() throws IOException {
        ArrayList<Producto> cargados = new ArrayList<>();
        if (Files.exists(archivo)) {
            try (BufferedReader entrada = Files.newBufferedReader(
                    archivo, StandardCharsets.UTF_8)) {
                String linea;
                int numero = 0;
                while ((linea = entrada.readLine()) != null) {
                    numero++;
                    String[] datos = linea.split(";", -1);
                    try {
                        if (datos.length != 3) {
                            throw new IllegalArgumentException("Se esperan 3 campos.");
                        }
                        Producto p = new Producto(Integer.parseInt(datos[0]),
                                datos[1], Double.parseDouble(datos[2]));
                        for (Producto existente : cargados) {
                            if (existente.getId() == p.getId()) {
                                throw new IllegalArgumentException("ID duplicado.");
                            }
                        }
                        cargados.add(p);
                    } catch (IllegalArgumentException e) {
                        throw new IOException("Linea " + numero + ": "
                                + e.getMessage(), e);
                    }
                }
            }
        }
        registros = cargados;
    }

    public ArrayList<Producto> listar() {
        return new ArrayList<>(registros);
    }

    public Producto buscarRegistro(int id) {
        for (Producto p : registros) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private void guardar() throws IOException {
        Path temporal = Files.createTempFile(
                archivo.toAbsolutePath().getParent(), "productos-", ".tmp");
        try {
            try (BufferedWriter salida = Files.newBufferedWriter(
                    temporal, StandardCharsets.UTF_8)) {
                for (Producto p : registros) {
                    salida.write(p.getId() + ";" + p.getNombre()
                            + ";" + p.getPrecio());
                    salida.newLine();
                }
            }
            Files.move(temporal, archivo, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            Files.deleteIfExists(temporal);
        }
    }

    public void agregarRegistro(Producto p) throws IOException {
        if (buscarRegistro(p.getId()) != null) {
            throw new IllegalArgumentException("El ID ya existe.");
        }
        registros.add(p);
        try {
            guardar();
        } catch (IOException e) {
            registros.remove(p);
            throw e;
        }
    }

    public void eliminarRegistro(int id) throws IOException {
        Producto p = buscarRegistro(id);
        if (p == null) {
            throw new IllegalArgumentException("Registro no encontrado.");
        }
        int indice = registros.indexOf(p);
        registros.remove(indice);
        try {
            guardar();
        } catch (IOException e) {
            registros.add(indice, p);
            throw e;
        }
    }
}
