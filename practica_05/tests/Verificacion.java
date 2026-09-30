import actividades.*;
import propuestos.*;
import cuestionario.EjemplosGenericos;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/** Pruebas de comportamiento y de conservación del estado. */
public class Verificacion {
    private static int total;
    private static void verificar(boolean condicion, String nombre) {
        if (!condicion) throw new AssertionError(nombre);
        total++;
    }
    private static void excepcion(Class<? extends Throwable> tipo, Runnable accion) {
        try { accion.run(); }
        catch (Throwable e) {
            verificar(tipo.isInstance(e), "Excepción esperada: " + tipo.getSimpleName());
            return;
        }
        throw new AssertionError("Faltó " + tipo.getSimpleName());
    }
    public static void main(String[] args) {
        Integer[] datos = {10, 20, 30, 40};
        PrintStream original = System.out;
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida));
        try {
            verificar(ImprimirArreglo.imprimirArreglo(datos, 1, 3) == 3, "Cantidad inclusiva");
            verificar(salida.toString().trim().equals("20 30 40"), "Contenido del intervalo");
            salida.reset();
            ImprimirArreglo.imprimirArreglo(datos);
            verificar(salida.toString().trim().equals("10 20 30 40"), "Arreglo completo");
        } finally { System.setOut(original); }
        int[][] invalidos = {{-1, 2}, {0, 4}, {2, 2}, {3, 1}, {4, 5}, {0, -1}};
        for (int[] par : invalidos) excepcion(InvalidSubscriptException.class,
            () -> ImprimirArreglo.imprimirArreglo(datos, par[0], par[1]));
        excepcion(InvalidSubscriptException.class,
            () -> ImprimirArreglo.imprimirArreglo(new Integer[0], 0, 1));
        excepcion(NullPointerException.class,
            () -> ImprimirArreglo.imprimirArreglo((Integer[]) null));
        Pila<Integer> pila = new Pila<>(3);
        verificar(pila.size() == 0, "Pila inicialmente vacía");
        verificar(!pila.contains(null), "Pila vacía no contiene null");
        pila.push(10); pila.push(null); pila.push(30);
        verificar(pila.contains(10), "Encuentra fondo");
        verificar(pila.contains(30), "Encuentra tope");
        verificar(pila.contains(null), "Encuentra null activo");
        verificar(!pila.contains(99), "Elemento ausente");
        verificar(pila.size() == 3, "Búsqueda conserva tamaño");
        excepcion(ExcepcionPilaLlena.class, () -> pila.push(40));
        verificar(pila.pop() == 30, "LIFO primer valor");
        verificar(pila.pop() == null, "LIFO null");
        verificar(!pila.contains(null), "No busca celdas inactivas");
        verificar(pila.pop() == 10, "LIFO fondo");
        excepcion(ExcepcionPilaVacia.class, pila::pop);
        verificar(new Pila<>(0).capacidad() == 10, "Capacidad por defecto");
        Pila<Integer> a = new Pila<>(2), b = new Pila<>(5), c = new Pila<>(2);
        verificar(a.esIgual(b), "Vacías iguales");
        a.push(1); a.push(2); b.push(1); b.push(2); c.push(2); c.push(1);
        verificar(a.esIgual(b), "Capacidades distintas no afectan igualdad");
        verificar(!a.esIgual(c), "Importa el orden");
        verificar(!a.esIgual(null), "Pila null");
        verificar(a.size() == 2 && b.size() == 2, "Comparación conserva tamaños");
        verificar(a.pop() == 2 && b.pop() == 2, "Comparación conserva topes");
        b.push(9);
        verificar(!a.esIgual(b), "Tamaños distintos");
        verificar(IgualGenerico.esIgualA(null, null), "Dos null");
        verificar(!IgualGenerico.esIgualA(null, "Java"), "Primer argumento null");
        verificar(!IgualGenerico.esIgualA("Java", null), "Segundo argumento null");
        verificar(IgualGenerico.esIgualA(new String("Java"), new String("Java")), "String por contenido");
        verificar(!IgualGenerico.esIgualA(new Object(), new Object()), "Object por identidad");
        verificar(!IgualGenerico.esIgualA(5, 5.0), "Wrappers de tipos diferentes");
        excepcion(NullPointerException.class, () -> IgualGenerico.esIgualALiteral(null, null));
        Par<String, Integer> par = new Par<>("A", 1);
        verificar(par.getPrimero().equals("A") && par.getSegundo() == 1, "Getters");
        par.setPrimero("B"); par.setSegundo(2);
        verificar(par.toString().equals("(Primero: B, Segundo: 2)"), "Setters y formato");
        verificar(par.esIgual(new Par<>("B", 2)), "Par igual");
        verificar(!par.esIgual(new Par<>("B", 3)), "Par distinto");
        verificar(!par.esIgual(null), "Par null");
        verificar(new Par<String, Integer>(null, null).esIgual(new Par<>(null, null)), "Par con null");
        Persona ana = new Persona("Ana", 20), copia = new Persona("Ana", 20);
        verificar(ana.equals(copia) && copia.equals(ana), "Persona igualdad simétrica");
        verificar(ana.hashCode() == copia.hashCode(), "Contrato hashCode");
        verificar(!ana.equals(null) && !ana.equals("Ana"), "Persona otros tipos");
        Contenedor<String, Integer> contenedor = new Contenedor<>();
        contenedor.agregarPar("Router", 4); contenedor.agregarPar("Switch", 8);
        verificar(contenedor.obtenerPar(0).getSegundo() == 4, "Acceso por índice");
        ArrayList<Par<String, Integer>> lista = contenedor.obtenerTodosLosPares();
        verificar(lista.size() == 2, "Devuelve todos los pares");
        lista.clear();
        verificar(contenedor.obtenerTodosLosPares().size() == 2, "Copia protege lista");
        contenedor.obtenerPar(1).setSegundo(10);
        verificar(contenedor.obtenerPar(1).getSegundo() == 10, "Edición de un par");
        excepcion(IndexOutOfBoundsException.class, () -> contenedor.obtenerPar(-1));
        excepcion(IndexOutOfBoundsException.class, () -> contenedor.obtenerPar(2));
        verificar(EjemplosGenericos.maximo(4, 9) == 9, "Comparable Integer");
        verificar(EjemplosGenericos.maximo("Ana", "Luis").equals("Luis"), "Comparable String");
        verificar(EjemplosGenericos.sumar(List.of(1, 2, 3)) == 6.0, "Wildcard extends");
        System.out.println("RESULTADO: " + total + "/" + total + " verificaciones correctas.");
        System.out.println("Cobertura: intervalos, pilas, igualdad, pares, contenedor y límites genéricos.");
    }
}
