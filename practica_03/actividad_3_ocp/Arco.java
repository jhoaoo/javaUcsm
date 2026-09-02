public class Arco implements Arma {
    private int danioBase;

    public Arco(int danioBase) {
        this.danioBase = danioBase;
    }

    @Override
    public String getNombre() {
        return "Arco";
    }

    @Override
    public int calcularDanio(int nivel) {
        return danioBase + nivel * 3;
    }
}
