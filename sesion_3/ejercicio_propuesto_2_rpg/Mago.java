public class Mago extends PersonajeBase implements IAtaqueMagico {
    public Mago(String nombre, int nivel, Inventario inventario) {
        super(nombre, nivel, inventario);
        agregarHabilidad("Bola de fuego", 30);
    }

    public void atacar(PersonajeBase objetivo) {
        System.out.println(nombre + " ataca con magia.");
        objetivo.recibirDanio(12);
    }

    public void hechizo(PersonajeBase objetivo) {
        System.out.println(nombre + " lanza un hechizo.");
        objetivo.recibirDanio(22);
    }
}
