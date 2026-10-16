import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

// MODELO: entidad con los tipos exigidos por la guía.
class Producto {
    private int id;
    private String nombre;
    private double precio;
    Producto(int id, String nombre, double precio) { setId(id); setNombre(nombre); setPrecio(precio); }
    int getId() { return id; }
    String getNombre() { return nombre; }
    double getPrecio() { return precio; }
    void setId(int id) { if (id <= 0) throw new IllegalArgumentException("ID positivo"); this.id = id; }
    void setNombre(String nombre) { if (nombre.isBlank() || nombre.contains(";")) throw new IllegalArgumentException("Nombre inválido"); this.nombre = nombre.trim(); }
    void setPrecio(double precio) { if (!Double.isFinite(precio) || precio < 0) throw new IllegalArgumentException("Precio inválido"); this.precio = precio; }
    public String toString() { return id + " | " + nombre + " | S/ " + String.format(Locale.US, "%.2f", precio); }
}

// MODELO: administra la persistencia en un archivo secuencial de texto.
class ProductoModelo {
    private final Path archivo = Paths.get("productos.txt");
    private final List<Producto> productos = new ArrayList<>();
    void leerRegistros() throws IOException {
        productos.clear();
        if (!Files.exists(archivo)) return;
        for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {
            if (linea.isBlank()) continue;
            String[] datos = linea.split(";", -1);
            if (datos.length != 3) throw new IOException("Registro incompleto");
            try {
                Producto p = new Producto(Integer.parseInt(datos[0]), datos[1], Double.parseDouble(datos[2]));
                if (buscarRegistro(p.getId()) != null) throw new IOException("ID repetido en archivo");
                productos.add(p);
            } catch (IllegalArgumentException e) { throw new IOException("Registro inválido: " + linea, e); }
        }
    }
    private void guardar() throws IOException {
        List<String> lineas = new ArrayList<>();
        for (Producto p : productos) lineas.add(p.getId() + ";" + p.getNombre() + ";" + p.getPrecio());
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }
    void agregarRegistro(Producto producto) throws IOException {
        if (buscarRegistro(producto.getId()) != null) throw new IllegalArgumentException("Identificador duplicado");
        productos.add(producto);
        guardar();
    }
    Producto buscarRegistro(int id) {
        for (Producto p : productos) if (p.getId() == id) return p;
        return null;
    }
    void eliminarRegistro(int id) throws IOException {
        Producto producto = buscarRegistro(id);
        if (producto == null) throw new IllegalArgumentException("Identificador no encontrado");
        productos.remove(producto);
        guardar();
    }
    List<Producto> obtenerTodos() { return Collections.unmodifiableList(productos); }
}

// VISTA: muestra el menú y lee los datos.
class ProductoVista {
    private final Scanner teclado = new Scanner(System.in);
    String pedirTexto(String pregunta) { System.out.print(pregunta); return teclado.nextLine(); }
    int pedirInt(String pregunta) { return Integer.parseInt(pedirTexto(pregunta)); }
    double pedirDouble(String pregunta) { return Double.parseDouble(pedirTexto(pregunta)); }
    void mensaje(String texto) { System.out.println(texto); }
    void listar(List<Producto> lista) {
        if (lista.isEmpty()) mensaje("No hay productos registrados.");
        for (Producto p : lista) mensaje(p.toString());
    }
}

// CONTROLADOR: comunica la vista y el modelo.
class ProductoControlador {
    private final ProductoModelo modelo;
    private final ProductoVista vista;
    ProductoControlador(ProductoModelo modelo, ProductoVista vista) { this.modelo = modelo; this.vista = vista; }
    void iniciar() {
        try { modelo.leerRegistros(); vista.mensaje("Archivo cargado correctamente."); }
        catch (IOException e) { vista.mensaje("Error al leer archivo: " + e.getMessage()); return; }
        vista.listar(modelo.obtenerTodos());
        while (true) {
            try {
                vista.mensaje("\n1 Listar | 2 Agregar | 3 Buscar | 4 Eliminar | 0 Salir");
                String opcion = vista.pedirTexto("Opción: ");
                if (opcion.equals("0")) { vista.mensaje("Fin del programa."); break; }
                switch (opcion) {
                    case "1": vista.listar(modelo.obtenerTodos()); break;
                    case "2": modelo.agregarRegistro(new Producto(vista.pedirInt("ID: "),
                               vista.pedirTexto("Nombre: "), vista.pedirDouble("Precio: ")));
                              vista.mensaje("Registro guardado."); break;
                    case "3": Producto p = modelo.buscarRegistro(vista.pedirInt("ID: "));
                              vista.mensaje(p == null ? "No encontrado" : p.toString()); break;
                    case "4": modelo.eliminarRegistro(vista.pedirInt("ID: "));
                              vista.mensaje("Registro eliminado."); break;
                    default: vista.mensaje("Opción no válida");
                }
            } catch (IOException | IllegalArgumentException e) {
                vista.mensaje("Error: " + e.getMessage());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new ProductoControlador(new ProductoModelo(), new ProductoVista()).iniciar();
    }
}
