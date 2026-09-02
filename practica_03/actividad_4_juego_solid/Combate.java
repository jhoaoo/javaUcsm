public class Combate {
    public void iniciar(Jugador jugador, Enemigo enemigo) {
        System.out.println("=== COMBATE ===");
        System.out.println(jugador.getNombre() + " usa " + jugador.getNombreArma());
        System.out.println(enemigo.getNombre() + " usa " + enemigo.getNombreArma());

        int danioJugador = jugador.atacar(enemigo, true);
        System.out.println(jugador.getNombre() + " causa " + danioJugador
                + " de danio critico.");
        System.out.println("Vida de " + enemigo.getNombre() + ": " + enemigo.getVida());

        if (enemigo.estaVivo()) {
            int danioEnemigo = enemigo.atacar(jugador, false);
            System.out.println(enemigo.getNombre() + " causa " + danioEnemigo
                    + " de danio.");
            System.out.println("Vida de " + jugador.getNombre() + ": " + jugador.getVida());
        } else {
            System.out.println(enemigo.getNombre() + " fue derrotado.");
        }
    }
}
