# Ejericicio 5 — transcripción del Word original

> Código y explicación reproducidos del informe original sin cambios deliberados. Esta transcripción se conserva como referencia; las clases públicas deben organizarse en archivos .java independientes para compilar.

```java
Ejericicio 5

Libro.java

public class Libro {

    private int codigo;
    private String titulo;
    private String autor;
    private boolean prestado;
    public Libro(int codigo, String titulo, String autor) {
      this.codigo = codigo;
      this.titulo = titulo;
      this.autor = autor;
      this.prestado = false;
    }

    public int getCodigo() {
      return codigo;
    }

    public String getTitulo() {
      return titulo;
    }

    public boolean estaPrestado() {
      return prestado;
    }

    public void setPrestado(boolean prestado) {
      this.prestado = prestado;
    }

    public String mostrarInfo() {
      String estado = "Disponible";

        if (prestado) {
           estado = "Prestado";
        }

        return codigo + " - " + titulo + " - " + autor
            + " - " + estado;
    }
}

LibroDigital.java

public class LibroDigital extends Libro {

    private double tamanoMB;

    public LibroDigital(int codigo, String titulo,
                String autor, double tamanoMB) {
      super(codigo, titulo, autor);
      this.tamanoMB = tamanoMB;
    }

    @Override
    public String mostrarInfo() {
      return super.mostrarInfo()
           + " - Digital (" + tamanoMB + " MB)";
    }
}

Repositorio.java

import java.util.ArrayList;

public class Repositorio<T> {

    private ArrayList<T> elementos = new ArrayList<>();

    public void agregar(T elemento) {
      elementos.add(elemento);
    }

    public void eliminar(T elemento) {
      elementos.remove(elemento);
    }

    public ArrayList<T> obtenerTodos() {
      return elementos;
    }
}


LibroFactory.java


public class LibroFactory {

    public static Libro crearLibro(int tipo, int codigo,
                       String titulo, String autor,
                       double tamano) {

        if (tipo == 1) {
           return new Libro(codigo, titulo, autor);
        } else if (tipo == 2) {
           return new LibroDigital(codigo, titulo, autor, tamano);
        }

        throw new IllegalArgumentException("Tipo de libro incorrecto");
    }
}

BibliotecaModelo.java
import java.util.ArrayList;

public class BibliotecaModelo {

  private Repositorio<Libro> libros = new Repositorio<>();

  public void registrar(Libro libro) {
    if (buscar(libro.getCodigo()) != null) {
       throw new IllegalArgumentException("El codigo ya existe");
    }

      libros.agregar(libro);
  }

  public Libro buscar(int codigo) {
    for (Libro libro : libros.obtenerTodos()) {
      if (libro.getCodigo() == codigo) {
         return libro;
      }
    }
    return null;
  }

  public void prestar(int codigo) {
    Libro libro = buscar(codigo);

      if (libro == null) {
         throw new IllegalArgumentException("Libro no encontrado");
      }

      if (libro.estaPrestado()) {
         throw new IllegalArgumentException("Ya esta prestado");
      }

      libro.setPrestado(true);
  }

  public void devolver(int codigo) {
    Libro libro = buscar(codigo);

      if (libro == null) {
         throw new IllegalArgumentException("Libro no encontrado");
      }

      if (!libro.estaPrestado()) {
         throw new IllegalArgumentException("El libro no esta prestado");
      }
        libro.setPrestado(false);
    }

    public void eliminar(int codigo) {
      Libro libro = buscar(codigo);

        if (libro == null) {
           throw new IllegalArgumentException("Libro no encontrado");
        }

        libros.eliminar(libro);
    }

    public int contarDisponibles() {
      int contador = 0;

        for (Libro libro : libros.obtenerTodos()) {
          if (!libro.estaPrestado()) {
             contador++;
          }
        }

        return contador;
    }

    public int contarPrestados() {
      int contador = 0;

        for (Libro libro : libros.obtenerTodos()) {
          if (libro.estaPrestado()) {
             contador++;
          }
        }

        return contador;
    }

    public ArrayList<Libro> getLibros() {
      return libros.obtenerTodos();
    }
}

BibliotecaVista.java

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaVista {
    Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
      System.out.println("\n===== BIBLIOTECA =====");
      System.out.println("1. Registrar libro");
      System.out.println("2. Mostrar libros");
      System.out.println("3. Buscar libro");
      System.out.println("4. Prestar libro");
      System.out.println("5. Devolver libro");
      System.out.println("6. Eliminar libro");
      System.out.println("7. Contar libros");
      System.out.println("0. Salir");
    }

    public int pedirNumero(String mensaje) {
      System.out.print(mensaje);
      return Integer.parseInt(sc.nextLine());
    }

    public String pedirTexto(String mensaje) {
      System.out.print(mensaje);
      return sc.nextLine();
    }

    public double pedirDecimal(String mensaje) {
      System.out.print(mensaje);
      return Double.parseDouble(sc.nextLine());
    }

    public void mostrarMensaje(String mensaje) {
      System.out.println(mensaje);
    }

    public void mostrarLibros(ArrayList<Libro> libros) {
      if (libros.isEmpty()) {
         System.out.println("No hay libros registrados");
      } else {
         for (Libro libro : libros) {
             System.out.println(libro.mostrarInfo());
         }
      }
    }
}

BibliotecaControlador.java

public class BibliotecaControlador {

    private BibliotecaModelo modelo;
private BibliotecaVista vista;

public BibliotecaControlador(BibliotecaModelo modelo,
                  BibliotecaVista vista) {
  this.modelo = modelo;
  this.vista = vista;
}

public void iniciar() {
  int opcion = -1;

  do {
    vista.mostrarMenu();

     try {
       opcion = vista.pedirNumero("Elige una opcion: ");

       switch (opcion) {
         case 1:
           registrarLibro();
           break;

          case 2:
            vista.mostrarLibros(modelo.getLibros());
            break;

          case 3:
            buscarLibro();
            break;

          case 4:
            int codigoP = vista.pedirNumero("Codigo: ");
            modelo.prestar(codigoP);
            vista.mostrarMensaje("Libro prestado correctamente");
            break;

          case 5:
            int codigoD = vista.pedirNumero("Codigo: ");
            modelo.devolver(codigoD);
            vista.mostrarMensaje("Libro devuelto correctamente");
            break;

          case 6:
            int codigoE = vista.pedirNumero("Codigo: ");
            modelo.eliminar(codigoE);
            vista.mostrarMensaje("Libro eliminado");
            break;

          case 7:
              vista.mostrarMensaje("Disponibles: "
                   + modelo.contarDisponibles());
              vista.mostrarMensaje("Prestados: "
                   + modelo.contarPrestados());
              break;

            case 0:
              vista.mostrarMensaje("Saliendo del programa...");
              break;

            default:
              vista.mostrarMensaje("Opcion incorrecta");
        }

      } catch (NumberFormatException e) {
         vista.mostrarMensaje("Ingresa un numero valido");

      } catch (IllegalArgumentException e) {
         vista.mostrarMensaje("Error: " + e.getMessage());
      }

    } while (opcion != 0);
}

public void registrarLibro() {
  int tipo = vista.pedirNumero(
        "1. Libro fisico\n2. Libro digital\nTipo: ");

    if (tipo != 1 && tipo != 2) {
       vista.mostrarMensaje("Tipo incorrecto");
       return;
    }

    int codigo = vista.pedirNumero("Codigo: ");
    String titulo = vista.pedirTexto("Titulo: ");
    String autor = vista.pedirTexto("Autor: ");

    if (titulo.trim().isEmpty() || autor.trim().isEmpty()) {
       vista.mostrarMensaje("Completa el titulo y el autor");
       return;
    }

    double tamano = 0;

    if (tipo == 2) {
       tamano = vista.pedirDecimal("Tamano en MB: ");

      if (tamano <= 0) {
         vista.mostrarMensaje("El tamano debe ser mayor a cero");
                return;
            }
        }

        Libro libro = LibroFactory.crearLibro(
             tipo, codigo, titulo, autor, tamano);

        modelo.registrar(libro);
        vista.mostrarMensaje("Libro registrado correctamente");
    }

    public void buscarLibro() {
      int codigo = vista.pedirNumero("Codigo a buscar: ");
      Libro libro = modelo.buscar(codigo);

        if (libro == null) {
           vista.mostrarMensaje("No se encontro el libro");
        } else {
           vista.mostrarMensaje(libro.mostrarInfo());
        }
    }
}

Main.java

public class Main {

    public static void main(String[] args) {

        BibliotecaModelo modelo = new BibliotecaModelo();
        BibliotecaVista vista = new BibliotecaVista();

        BibliotecaControlador controlador =
             new BibliotecaControlador(modelo, vista);

        controlador.iniciar();
    }
}

El proyecto consiste en desarrollar un sistema básico de biblioteca en Java utilizando el patrón
arquitectónico MVC. El programa permite registrar libros físicos y digitales, buscar libros por su código,
prestarlos, devolverlos, eliminarlos y consultar cuántos se encuentran disponibles o prestados. Para su
desarrollo se utilizaron los conceptos aprendidos durante el curso, como clases, objetos, constructores,
encapsulamiento, herencia, polimorfismo, colecciones, genéricos y manejo de excepciones. El Modelo se
encarga de administrar la información de los libros, la Vista muestra el menú y recibe los datos del usuario,
y el Controlador comunica ambas partes para realizar las operaciones correspondientes. También se
agregó una fábrica simple para crear los distintos tipos de libros. De esta manera, se consiguió desarrollar
una aplicación sencilla y organizada, aplicando los conocimientos de programación orientada a objetos.
```
