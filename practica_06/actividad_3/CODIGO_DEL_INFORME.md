# Actividad 3 — transcripción fiel del Word original

El siguiente código procede del informe Word aportado. Se conserva como documentación del trabajo existente, sin corregirlo ni afirmar que se ha compilado.

```java
Actividad 3:

Pedido.java

public class Pedido {
  private int id;
  private String descripcion;
  private String estado;

    public Pedido(int id, String descripcion) {
      this.id = id;
      this.descripcion = descripcion;
      this.estado = "Pendiente";
    }

    public int getId() {
      return id;
    }

    public String getEstado() {
      return estado;
    }

    public void setEstado(String estado) {
      this.estado = estado;
    }

    public String toString() {
      return "Pedido " + id + ": " + descripcion +
          " - " + estado;
    }
}

PedidoModelo.java

import java.util.ArrayList;

public class PedidoModelo {

    ArrayList<Pedido> pedidos = new ArrayList<>();
    ArrayList<Pedido> historial = new ArrayList<>();

    int id = 1;

    public void agregarPedido(String descripcion) {
      Pedido nuevo = new Pedido(id, descripcion);
      pedidos.add(nuevo);
      id++;
    }
public boolean completarPedido(int numero) {
  for (Pedido p : pedidos) {
    if (p.getId() == numero && p.getEstado().equals("Pendiente")) {
       p.setEstado("Completo");
       historial.add(p);
       return true;
    }
  }
  return false;
}

public boolean eliminarPedido(int numero) {
  for (Pedido p : pedidos) {
    if (p.getId() == numero) {
       p.setEstado("Eliminado");
       historial.add(p);
       pedidos.remove(p);
       return true;
    }
  }
  return false;
}

public ArrayList<Pedido> mostrarPorEstado(String estado) {
  ArrayList<Pedido> lista = new ArrayList<>();

    for (Pedido p : pedidos) {
      if (p.getEstado().equals(estado)) {
         lista.add(p);
      }
    }

    return lista;
}

public int contarPendientes() {
  int contador = 0;

    for (Pedido p : pedidos) {
      if (p.getEstado().equals("Pendiente")) {
         contador++;
      }
    }

    return contador;
}

public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public ArrayList<Pedido> getHistorial() {
      return historial;
    }
}

PedidoVista.java

public class PedidoControlador {

    PedidoModelo modelo;
    PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
      this.modelo = modelo;
      this.vista = vista;
    }

    public void iniciar() {
      int opcion = -1;

        do {
          vista.menu();
          vista.mensaje("Elige una opcion:");

          try {
             opcion = vista.leerNumero();
          } catch (NumberFormatException e) {
             vista.mensaje("Ingresa un numero valido.");
             continue;
          }

          switch (opcion) {

            case 1:
              vista.mensaje("Ingrese el pedido:");
              String nombre = vista.leerTexto();
              modelo.agregarPedido(nombre);
              vista.mensaje("Pedido agregado.");
              break;

            case 2:
              vista.mensaje("Numero del pedido:");
              int completar = vista.leerNumero();

              if (modelo.completarPedido(completar)) {
                 vista.mensaje("Pedido completado.");
      } else {
         vista.mensaje("No se pudo completar.");
      }
      break;

    case 3:
      vista.mostrarPedidos(
         modelo.mostrarPorEstado("Pendiente")
      );
      break;

    case 4:
      vista.mostrarPedidos(
         modelo.mostrarPorEstado("Completo")
      );
      break;

    case 5:
      vista.mensaje("Pedidos pendientes: "
           + modelo.contarPendientes());
      break;

    case 6:
      vista.mensaje("Numero del pedido:");
      int eliminar = vista.leerNumero();

      if (modelo.eliminarPedido(eliminar)) {
         vista.mensaje("Pedido eliminado.");
      } else {
         vista.mensaje("No existe el pedido.");
      }
      break;

    case 7:
      vista.mostrarPedidos(modelo.getHistorial());
      break;

    case 8:
      vista.mostrarPedidos(modelo.getPedidos());
      break;

    case 0:
      vista.mensaje("Programa finalizado.");
      break;

    default:
      vista.mensaje("Opcion incorrecta.");
}
        } while (opcion != 0);
    }
}

Main.java

public class Main {

    public static void main(String[] args) {

        PedidoModelo modelo = new PedidoModelo();
        PedidoVista vista = new PedidoVista();

        PedidoControlador controlador =
            new PedidoControlador(modelo, vista);

        controlador.iniciar();
    }
}

En esta actividad se modificó la clase Pedido para agregar un estado que permita saber si un pedido está
pendiente, completo o eliminado. Para desarrollar el programa se utilizó el patrón arquitectónico MVC,
separando las responsabilidades en tres partes: el Modelo, que se encarga de guardar los pedidos,
cambiar sus estados y mantener un historial mediante ArrayList; la Vista, que muestra el menú y permite
al usuario ingresar información; y el Controlador, que conecta ambas partes y ejecuta las opciones
seleccionadas mediante un switch. También se implementaron métodos para agregar pedidos, marcarlos
como completos, eliminarlos, mostrarlos según su estado y contar cuántos siguen pendientes.
Finalmente, la clase Main permite iniciar el programa y conectar todos sus componentes. De esta manera,
se cumplen las funcionalidades solicitadas y se consigue que el código esté más organizado, sea fácil de
entender y pueda modificarse sin afectar las demás partes del sistema.
```
