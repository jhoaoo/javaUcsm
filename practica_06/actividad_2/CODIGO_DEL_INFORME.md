# Actividad 2 — transcripción fiel del Word original

El siguiente código procede del informe Word aportado. Se conserva como documentación del trabajo existente, sin corregirlo ni afirmar que se ha compilado.

```java
Actividad 2:

Deben modificar la clase pedido para que los platos no tengan solo un nombre sino también un tipo y
agreguen nuevas funcionalidades a la aplicación • Eliminar un Pedido: Permitir que los usuarios eliminen
un pedido de la lista.

      Actualizar un Pedido: Permitir que los usuarios actualicen el nombre de un pedido existente.
      Buscar un Pedido: Permitir que los usuarios busquen un pedido por su nombre o tipo.
      Contar Pedidos: Mostrar la cantidad total de pedidos en la lista total y según los tipos.


   Estas nuevas funcionalidades deben respetar el patrón MVC

Modelo

public class Pedido {
  private String nombrePlato;
  private String tipo;

  public Pedido(String nombrePlato, String tipo) {
    this.nombrePlato = nombrePlato;
    this.tipo = tipo;
  }

  public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
      this.nombrePlato = nombrePlato;
    }

    public String getTipo() {
      return tipo;
    }

    public void setTipo(String tipo) {
      this.tipo = tipo;
    }
}




PedidoModelo

import java.util.ArrayList;
import java.util.List;

public class PedidoModelo {
  private List<Pedido> pedidos;

    public PedidoModelo() {
      pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
      pedidos.add(pedido);
    }

    public void eliminarPedido(int index) {
      if(index >= 0 && index < pedidos.size()) {
         pedidos.remove(index);
      }
    }

    public void actualizarPedido(int index, String nuevoNombre) {
      if(index >= 0 && index < pedidos.size()) {
         pedidos.get(index).setNombrePlato(nuevoNombre);
      }
    }

    public Pedido buscarPedido(String nombre) {
      for(Pedido p : pedidos) {
        if(p.getNombrePlato().equalsIgnoreCase(nombre)) {
           return p;
        }
      }
      return null;
    }

    public int contarPedidos() {
      return pedidos.size();
    }

    public List<Pedido> getPedidos() {
      return pedidos;
    }
}

Pedido Vista

public class PedidoVista {
  public void mostrarPedidos(List<Pedido> pedidos) {
    if (pedidos.isEmpty()) {
       System.out.println("No hay pedidos en la lista.");
    } else {
       System.out.println("Lista de Pedidos:");
       for (int i = 0; i < pedidos.size(); i++) {
         Pedido p = pedidos.get(i);
         System.out.println(i + ". " + p.getNombrePlato() + " - Tipo: " + p.getTipo());
       }
    }
  }

    public void mostrarMensaje(String mensaje) {
      System.out.println(mensaje);
    }
}

PedidoControlador

public class PedidoControlador {
  private PedidoModelo modelo;
  private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
      this.modelo = modelo;
      this.vista = vista;
    }

    public void agregarPedido(String nombre, String tipo) {
      if (!nombre.isEmpty() && !tipo.isEmpty()) {
         modelo.agregarPedido(new Pedido(nombre, tipo));
         vista.mostrarMensaje("Pedido agregado: " + nombre + " (" + tipo + ")");
      } else {
         vista.mostrarMensaje("El nombre y tipo no pueden estar vacíos.");
      }
    }

    public void eliminarPedido(int index) {
        modelo.eliminarPedido(index);
        vista.mostrarMensaje("Pedido eliminado en posición: " + index);
    }

    public void actualizarPedido(int index, String nuevoNombre) {
      modelo.actualizarPedido(index, nuevoNombre);
      vista.mostrarMensaje("Pedido actualizado en posición: " + index);
    }

    public void buscarPedido(String nombre) {
      Pedido p = modelo.buscarPedido(nombre);
      if (p != null) {
         vista.mostrarMensaje("Pedido encontrado: " + p.getNombrePlato() + " - Tipo: " + p.getTipo());
      } else {
         vista.mostrarMensaje("Pedido no encontrado.");
      }
    }

    public void mostrarPedidos() {
      vista.mostrarPedidos(modelo.getPedidos());
    }

    public void contarPedidos() {
      vista.mostrarMensaje("Total de pedidos: " + modelo.contarPedidos());
    }
}



Main

public class Main {
  public static void main(String[] args) {
    PedidoModelo modelo = new PedidoModelo();
    PedidoVista vista = new PedidoVista();
    PedidoControlador controlador = new PedidoControlador(modelo, vista);

        controlador.agregarPedido("Pizza", "Comida rápida");
        controlador.agregarPedido("Ceviche", "Mariscos");

        controlador.mostrarPedidos();

        controlador.actualizarPedido(0, "Pizza Familiar");
        controlador.mostrarPedidos();

        controlador.eliminarPedido(1);
        controlador.mostrarPedidos();

        System.out.println("Total de pedidos: " + modelo.contarPedidos());
    }
}
```
