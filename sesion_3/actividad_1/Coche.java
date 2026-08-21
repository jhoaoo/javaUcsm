public class Coche {
    public String color;
    private String marca;
    private String modelo;
    private int velocidadMaxima;
    private int potenciaMotor;
    private int anioFabricacion;
    private double precio;
    private boolean enMarcha;

    public Coche(String modelo, int velocidadMaxima, int potenciaMotor) {
        this.modelo = modelo;
        this.velocidadMaxima = velocidadMaxima;
        this.potenciaMotor = potenciaMotor;
        this.enMarcha = false;
    }

    public Coche() {
        this.marca = "Sin marca";
        this.modelo = "Sin modelo";
        this.enMarcha = false;
    }

    public Coche(String marca, String modelo, int anioFabricacion, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.anioFabricacion = anioFabricacion;
        this.precio = precio;
        this.enMarcha = false;
    }

    public boolean aplicarDescuento(double descuento) {
        if (anioFabricacion < 2010) {
            precio = precio - (precio * descuento / 100);
            return true;
        }
        return false;
    }

    public void acelerar() {
        if (enMarcha) {
            System.out.println("El coche " + modelo + " esta acelerando.");
        } else {
            System.out.println("Primero enciende el coche.");
        }
    }

    public void frenar() {
        if (enMarcha) {
            System.out.println("El coche " + modelo + " esta frenando.");
        } else {
            System.out.println("El coche esta apagado, no se puede frenar.");
        }
    }

    public void encender() {
        enMarcha = true;
        System.out.println("El coche " + modelo + " se ha encendido.");
    }

    public void apagar() {
        enMarcha = false;
        System.out.println("El coche " + modelo + " se ha apagado.");
    }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getVelocidadMaxima() { return velocidadMaxima; }
    public void setVelocidadMaxima(int velocidadMaxima) { this.velocidadMaxima = velocidadMaxima; }
    public int getPotenciaMotor() { return potenciaMotor; }
    public void setPotenciaMotor(int potenciaMotor) { this.potenciaMotor = potenciaMotor; }
    public int getAnioFabricacion() { return anioFabricacion; }
    public void setAnioFabricacion(int anioFabricacion) { this.anioFabricacion = anioFabricacion; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public boolean isEnMarcha() { return enMarcha; }
    public void setEnMarcha(boolean enMarcha) { this.enMarcha = enMarcha; }
}
