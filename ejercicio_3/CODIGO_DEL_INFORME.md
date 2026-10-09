# Ejercicio 3 — transcripción del Word original

> Código y explicación reproducidos del informe original sin cambios deliberados. Esta transcripción se conserva como referencia; las clases públicas deben organizarse en archivos .java independientes para compilar.

```java
Ejercicio 3:

Creen un programa utilizando el patrón MVC en Java para un sistema de gestión de inventarios
utilizando los elementos de la siguiente figura
Clase Item
public class Item {
  private String nombre;
  private int cantidad;
  private String tipo; // Arma, Poción, etc.
  private String descripcion;

    public Item(String nombre, int cantidad, String tipo, String descripcion) {
      this.nombre = nombre;
      this.cantidad = cantidad;
      this.tipo = tipo;
      this.descripcion = descripcion;
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }

    public void usarItem() {
      if(cantidad > 0) cantidad--;
    }
}

InventarioModel

import java.util.ArrayList;
import java.util.List;

public class InventarioModel {
  private List<Item> items;

    public InventarioModel() {
      items = new ArrayList<>();
    }

    public void agregarItem(Item item) {
      items.add(item);
    }

    public void eliminarItem(Item item) {
      items.remove(item);
    }

    public Item buscarItem(String nombre) {
      for(Item i : items) {
        if(i.getNombre().equalsIgnoreCase(nombre)) return i;
      }
      return null;
    }

    public List<Item> obtenerItems() {
      return items;
    }
}

InventarioVista
import java.util.List;

public class InventarioVista {
  public void mostrarInventario(List<Item> items) {
    System.out.println("Inventario:");
    for(Item i : items) {
      System.out.println("- " + i.getNombre() + " (" + i.getCantidad() + ") Tipo: " + i.getTipo());
    }
  }

    public void mostrarMensaje(String mensaje) {
      System.out.println(mensaje);
    }

    public void mostrarDetalles(Item item) {
      if(item != null) {
         System.out.println("Detalles: " + item.getNombre() + " - " + item.getDescripcion());
      } else {
         System.out.println("Item no encontrado.");
      }
    }
}




InventarioControlador

public class InventarioControlador {
  private InventarioModel modelo;
  private InventarioVista vista;

    public InventarioControlador(InventarioModel modelo, InventarioVista vista) {
      this.modelo = modelo;
      this.vista = vista;
    }

    public void agregarItem(Item item) {
      modelo.agregarItem(item);
      vista.mostrarMensaje("Item agregado: " + item.getNombre());
    }

    public void eliminarItem(Item item) {
      modelo.eliminarItem(item);
      vista.mostrarMensaje("Item eliminado: " + item.getNombre());
    }

    public void verInventario() {
        vista.mostrarInventario(modelo.obtenerItems());
    }

    public void buscarItem(String nombre) {
      Item item = modelo.buscarItem(nombre);
      vista.mostrarDetalles(item);
    }
}

Main

public class Main {
  public static void main(String[] args) {
    InventarioModel modelo = new InventarioModel();
    InventarioVista vista = new InventarioVista();
    InventarioControlador controlador = new InventarioControlador(modelo, vista);

        // Agregar items
        controlador.agregarItem(new Item("Espada", 1, "Arma", "Espada de acero afilada"));
        controlador.agregarItem(new Item("Poción de vida", 5, "Poción", "Recupera 50 puntos de salud"));

        // Ver inventario
        controlador.verInventario();

        // Buscar item
        controlador.buscarItem("Espada");

        // Eliminar item
        Item pocion = modelo.buscarItem("Poción de vida");
        controlador.eliminarItem(pocion);

        controlador.verInventario();
    }
}
```
