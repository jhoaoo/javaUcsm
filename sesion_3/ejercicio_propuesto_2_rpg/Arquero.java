public class Arquero extends PersonajeBase implements IAtaqueFisico {
    public Arquero(String nombre, int nivel, Inventario inventario) {
        super(nombre, nivel, inventario);
        agregarHabilidad("Flecha precisa", 20);
    }

    public void atacar(PersonajeBase objetivo) {
        System.out.println(nombre + " dispara una flecha.");
        objetivo.recibirDanio(14);
    }

    public void golpeFisico(PersonajeBase objetivo) {
        System.out.println(nombre + " golpea con el arco.");
        objetivo.recibirDanio(10);
    }
}
