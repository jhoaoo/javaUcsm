public class Hacha implements Arma {
    private int danioBase;

    public Hacha(int danioBase) {
        this.danioBase = danioBase;
    }

    @Override
    public String getNombre() {
        return "Hacha";
    }

    @Override
    public int calcularDanio(int nivel) {
        return danioBase + nivel * 4;
    }
}
