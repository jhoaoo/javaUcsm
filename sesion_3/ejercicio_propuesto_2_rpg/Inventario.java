public class Inventario {
    private ObjetoJuego[] objetos = new ObjetoJuego[5];
    private int cantidad = 0;

    public void agregarObjeto(ObjetoJuego objeto) {
        if (cantidad < objetos.length) {
            objetos[cantidad] = objeto;
            cantidad++;
        }
    }

    public void mostrar() {
        System.out.print("Inventario: ");
        for (int i = 0; i < cantidad; i++) {
            System.out.print(objetos[i].getNombre() + " ");
        }
        System.out.println();
    }
}
