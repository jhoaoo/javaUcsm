public class Espada implements Arma {
    private int danioBase;

    public Espada(int danioBase) {
        this.danioBase = danioBase;
    }

    @Override
    public String getNombre() {
        return "Espada";
    }

    @Override
    public int calcularDanio(int nivel) {
        return danioBase + nivel * 5;
    }
}
