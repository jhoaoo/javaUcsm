package actividades;

public class IgualGenerico {
    /** Versión literal: falla si el primer argumento es null. */
    public static <T> boolean esIgualALiteral(T primero, T segundo) {
        return primero.equals(segundo);
    }

    /** Compara mediante equals y trata explícitamente el caso null. */
    public static <T> boolean esIgualA(T primero, T segundo) {
        return primero == null ? segundo == null : primero.equals(segundo);
    }

    public static void main(String[] args) {
        System.out.println("int 5 y 5 (autoboxing): " + esIgualA(5, 5));
        System.out.println("double 2.5 y 2.5: " + esIgualA(2.5, 2.5));
        System.out.println("char A y A: " + esIgualA('A', 'A'));
        System.out.println("boolean true y false: " + esIgualA(true, false));
        System.out.println("Integer 1000 y 1000: "
                + esIgualA(Integer.valueOf(1000), Integer.valueOf(1000)));
        System.out.println("String de igual contenido: "
                + esIgualA(new String("Java"), new String("Java")));
        Object objeto = new Object();
        System.out.println("Misma referencia Object: " + esIgualA(objeto, objeto));
        System.out.println("Dos Object diferentes: "
                + esIgualA(new Object(), new Object()));
        System.out.println("null y null: " + esIgualA(null, null));
        System.out.println("null y Java: " + esIgualA(null, "Java"));
        System.out.println("Java y null: " + esIgualA("Java", null));
        System.out.println("Integer 5 y Double 5.0: " + esIgualA(5, 5.0));
        try { esIgualALiteral(null, "Java"); }
        catch (NullPointerException e) {
            System.out.println("Versión literal con null: NullPointerException");
        }
    }
}
