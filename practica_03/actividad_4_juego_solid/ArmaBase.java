public abstract class ArmaBase implements Arma {
    private String nombre;
    private int danioBase;
    private int aumentoPorNivel;
    private String elemento;

    public ArmaBase(String nombre, int danioBase, int aumentoPorNivel,
                    String elemento) {
        this.nombre = nombre;
        this.danioBase = danioBase;
        this.aumentoPorNivel = aumentoPorNivel;
        this.elemento = elemento;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int calcularDanio(int nivel, boolean golpeCritico, String debilidad) {
        int danio = danioBase + nivel * aumentoPorNivel;

        if (elemento.equalsIgnoreCase(debilidad)) {
            danio += 20;
        }

        if (golpeCritico) {
            danio *= 2;
        }

        return danio;
    }
}
