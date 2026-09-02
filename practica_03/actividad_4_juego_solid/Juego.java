public class Juego {
    public static void main(String[] args) {
        Jugador jugador = new Jugador("Guerrero", 300, 10, new Espada());
        Enemigo enemigo = new Enemigo(
                "Ogro de hielo", 260, 6, "fuego", new Hacha());

        Combate combate = new Combate();
        combate.iniciar(jugador, enemigo);
    }
}
