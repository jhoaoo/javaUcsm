import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    private static Personaje pedirPersonaje() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Vida: ");
        int vida = Integer.parseInt(sc.nextLine());
        System.out.print("Ataque: ");
        int ataque = Integer.parseInt(sc.nextLine());
        System.out.print("Defensa: ");
        int defensa = Integer.parseInt(sc.nextLine());
        System.out.print("Alcance: ");
        int alcance = Integer.parseInt(sc.nextLine());
        return new Personaje(nombre, vida, ataque, defensa, alcance);
    }

    public static void main(String[] args) {
        try {
            Gestor gestor = new Gestor("personajes.txt");
            int opcion = -1;
            do {
                System.out.println("\n1. Agregar  2. Mostrar  3. Modificar");
                System.out.println("4. Borrar  5. Ordenar por ataque  6. Filtrar por vida");
                System.out.println("7. Estadisticas  0. Salir");
                try {
                    opcion = Integer.parseInt(sc.nextLine());
                    switch (opcion) {
                        case 1:
                            gestor.agregar(pedirPersonaje());
                            System.out.println("Personaje agregado.");
                            break;
                        case 2:
                            System.out.println("Nombre;Vida;Ataque;Defensa;Alcance");
                            for (Personaje p : gestor.listar()) {
                                System.out.println(p);
                            }
                            break;
                        case 3:
                            System.out.print("Nombre actual: ");
                            String nombre = sc.nextLine();
                            gestor.modificar(nombre, pedirPersonaje());
                            System.out.println("Personaje modificado.");
                            break;
                        case 4:
                            System.out.print("Nombre a borrar: ");
                            gestor.borrar(sc.nextLine());
                            System.out.println("Personaje eliminado.");
                            break;
                        case 5:
                            for (Personaje p : gestor.ordenarPorAtaque()) {
                                System.out.println(p);
                            }
                            break;
                        case 6:
                            System.out.print("Vida minima: ");
                            int minimo = Integer.parseInt(sc.nextLine());
                            for (Personaje p : gestor.filtrarPorVida(minimo)) {
                                System.out.println(p);
                            }
                            break;
                        case 7:
                            System.out.println(gestor.estadisticas());
                            break;
                        case 0:
                            System.out.println("Programa finalizado.");
                            break;
                        default:
                            System.out.println("Opcion no valida.");
                    }
                } catch (IOException | IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } while (opcion != 0);
        } catch (IOException e) {
            System.out.println("No se pudo cargar: " + e.getMessage());
        }
    }
}
