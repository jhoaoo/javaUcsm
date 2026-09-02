public interface Arma {
    String getNombre();
    int calcularDanio(int nivel, boolean golpeCritico, String debilidad);
}
