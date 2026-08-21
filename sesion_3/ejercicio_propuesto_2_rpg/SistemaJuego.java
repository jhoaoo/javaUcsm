public class SistemaJuego {
    public static void main(String[] args) {
        Inventario inventario1 = new Inventario();
        Inventario inventario2 = new Inventario();
        Inventario inventario3 = new Inventario();

        inventario1.agregarObjeto(new ObjetoJuego("Pocion"));
        inventario2.agregarObjeto(new ObjetoJuego("Mana"));
        inventario3.agregarObjeto(new ObjetoJuego("Flechas"));

        PersonajeBase guerrero = new Guerrero("Thor", 1, inventario1);
        PersonajeBase mago = new Mago("Merlin", 1, inventario2);
        PersonajeBase arquero = new Arquero("Robin", 1, inventario3);

        PersonajeBase[] personajes = {guerrero, mago, arquero};

        guerrero.atacar(mago);
        mago.atacar(guerrero);
        arquero.atacar(mago);

        guerrero.usarHabilidad(mago);
        mago.usarHabilidad(0, guerrero);

        guerrero.getInventario().mostrar();
        mago.getInventario().mostrar();
        arquero.getInventario().mostrar();

        System.out.println("Personajes creados: " + PersonajeBase.getContadorPersonajes());

        for (PersonajeBase personaje : personajes) {
            System.out.println(personaje.getClass().getSimpleName() + " - salud: " + personaje.getSalud());
        }
    }
}
