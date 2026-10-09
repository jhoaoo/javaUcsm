# Ejercicio 4 — transcripción del Word original

> Código y explicación reproducidos del informe original sin cambios deliberados. Esta transcripción se conserva como referencia; las clases públicas deben organizarse en archivos .java independientes para compilar.

```java
Ejercicio 4:

Ampliar el sistema de gestión de inventarios previamente desarrollado para incluir la lógica de un
jugador y enemigos, estos nuevos elementos deberán utilizar el patrón de diseño Modelo-Vista
Controlador (MVC).
     La clase jugador debe contener un nombre, salud, nivel e inventario, con métodos atacar, usar
       objeto y recibir daño.
     El ataque se debe realizar según el objeto que tenga equipado el jugador.
     La clase enemigo debe contener un nombre, salud, nivel y tipo, con métodos atacar y recibir
       daño.
     Se debe realizar una gestión del combate donde el jugador pueda atacar a los enemigos y los
       enemigos realicen acciones de forma aleatoria, mostrando los mensajes, deben agregar un
       controlador que gestione el combate.
        La vista debe mostrar el estado del combate y mensajes que indiquen lo que está sucediendo.

Clase Jugador

public class Jugador {
  private String nombre;
  private int salud;
  private int nivel;
  private InventarioModel inventario;

    public Jugador(String nombre, int salud, int nivel) {
      this.nombre = nombre;
      this.salud = salud;
      this.nivel = nivel;
      this.inventario = new InventarioModel();
    }

    public void atacar(Enemigo enemigo) {
      enemigo.recibirDanio(10); // daño fijo para ejemplo
    }

    public void recibirDanio(int cantidad) {
      salud -= cantidad;
    }

    public int getSalud() { return salud; }
    public String getNombre() { return nombre; }
}

Clase Enemigo

public class Enemigo {
  private String nombre;
  private int salud;
  private int nivel;
  private String tipo;

    public Enemigo(String nombre, int salud, int nivel, String tipo) {
      this.nombre = nombre;
      this.salud = salud;
      this.nivel = nivel;
      this.tipo = tipo;
    }

    public void atacar(Jugador jugador) {
      jugador.recibirDanio(5); // daño fijo para ejemplo
    }

    public void recibirDanio(int cantidad) {
        salud -= cantidad;
    }

    public int getSalud() { return salud; }
    public String getNombre() { return nombre; }
}

CombateControlador

public class CombateControlador {
  private Jugador jugador;
  private Enemigo enemigo;
  private InventarioView vista;

    public CombateController(Jugador jugador, Enemigo enemigo, InventarioView vista) {
      this.jugador = jugador;
      this.enemigo = enemigo;
      this.vista = vista;
    }

  public void iniciarCombate() {
    vista.mostrarMensaje("Combate iniciado entre " + jugador.getNombre() + " y " +
enemigo.getNombre());

        jugador.atacar(enemigo);
        vista.mostrarMensaje(jugador.getNombre() + " ataca a " + enemigo.getNombre());

        enemigo.atacar(jugador);
        vista.mostrarMensaje(enemigo.getNombre() + " contraataca a " + jugador.getNombre());

        vista.mostrarMensaje("Salud jugador: " + jugador.getSalud());
        vista.mostrarMensaje("Salud enemigo: " + enemigo.getSalud());
    }
}
```
