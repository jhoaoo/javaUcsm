public abstract class PersonajeBase extends Personaje {
    private Inventario inventario;
    private Habilidad[] habilidades = new Habilidad[3];
    private int cantidadHabilidades = 0;

    public PersonajeBase(String nombre, int nivel, Inventario inventario) {
        super(nombre, nivel);
        this.inventario = inventario;
    }

    public abstract void atacar(PersonajeBase objetivo);

    public void usarHabilidad(PersonajeBase objetivo) {
        usarHabilidad(0, objetivo);
    }

    public void usarHabilidad(int indice, PersonajeBase objetivo) {
        if (indice >= 0 && indice < cantidadHabilidades) {
            Habilidad habilidad = habilidades[indice];
            System.out.println(nombre + " usa " + habilidad.getNombre());
            objetivo.recibirDanio(habilidad.getDanio());
        }
    }

    protected void agregarHabilidad(String nombre, int danio) {
        if (cantidadHabilidades < habilidades.length) {
            habilidades[cantidadHabilidades] = new Habilidad(nombre, danio);
            cantidadHabilidades++;
        }
    }

    public Inventario getInventario() {
        return inventario;
    }
}
