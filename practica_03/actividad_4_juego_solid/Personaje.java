public abstract class Personaje {
    private String nombre;
    private int vida;
    private int nivel;
    private String debilidad;
    private Arma arma;

    public Personaje(String nombre, int vida, int nivel,
                     String debilidad, Arma arma) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = nivel;
        this.debilidad = debilidad;
        this.arma = arma;
    }

    public int atacar(Personaje objetivo, boolean golpeCritico) {
        int danio = arma.calcularDanio(nivel, golpeCritico,
                objetivo.getDebilidad());
        objetivo.recibirDanio(danio);
        return danio;
    }

    public void recibirDanio(int danio) {
        vida -= danio;
        if (vida < 0) {
            vida = 0;
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public String getDebilidad() {
        return debilidad;
    }

    public String getNombreArma() {
        return arma.getNombre();
    }
}
