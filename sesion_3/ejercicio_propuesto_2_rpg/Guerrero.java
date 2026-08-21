public class Guerrero extends PersonajeBase implements IAtaqueFisico {
    public Guerrero(String nombre, int nivel, Inventario inventario) {
        super(nombre, nivel, inventario);
        agregarHabilidad("Golpe fuerte", 25);
    }

    public void atacar(PersonajeBase objetivo) {
        System.out.println(nombre + " ataca con espada.");
        objetivo.recibirDanio(15);
    }

    public void golpeFisico(PersonajeBase objetivo) {
        System.out.println(nombre + " realiza un golpe fisico.");
        objetivo.recibirDanio(20);
    }
}
