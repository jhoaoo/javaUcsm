public class Personaje {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;

    public Personaje(String nombre, int vida, int ataque,
            int defensa, int alcance) {
        setNombre(nombre);
        setVida(vida);
        setAtaque(ataque);
        setDefensa(defensa);
        setAlcance(alcance);
    }

    private int positivo(int valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Atributos mayores que cero.");
        }
        return valor;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAlcance() { return alcance; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()
                || nombre.contains(";") || nombre.contains("\n")
                || nombre.contains("\r")) {
            throw new IllegalArgumentException("Nombre no valido.");
        }
        this.nombre = nombre.trim();
    }

    public void setVida(int vida) { this.vida = positivo(vida); }
    public void setAtaque(int ataque) { this.ataque = positivo(ataque); }
    public void setDefensa(int defensa) { this.defensa = positivo(defensa); }
    public void setAlcance(int alcance) { this.alcance = positivo(alcance); }

    @Override
    public String toString() {
        return nombre + ";" + vida + ";" + ataque + ";"
                + defensa + ";" + alcance;
    }
}
