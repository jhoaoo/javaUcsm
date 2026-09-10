import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class DatosInvalidosException extends Exception { public DatosInvalidosException(String m) { super(m); } }
class PrecioInferiorException extends Exception { public PrecioInferiorException(String m) { super(m); } }
class ContratoInvalidoException extends Exception { public ContratoInvalidoException(String m) { super(m); } }
class PagoInvalidoException extends Exception { public PagoInvalidoException(String m) { super(m); } }

class Propiedad {
    private String direccion;
    private double precio;
    private double tamanio;
    public Propiedad(String direccion, double precio, double tamanio) throws DatosInvalidosException {
        if (precio <= 0 || tamanio <= 0) throw new DatosInvalidosException("Precio y tamanio deben ser positivos.");
        this.direccion = direccion;
        this.precio = precio;
        this.tamanio = tamanio;
    }
    public double getPrecio() { return precio; }
    public String toString() { return direccion + " | S/ " + precio + " | " + tamanio + " m2"; }
}

class TransaccionInmobiliaria {
    private int id;
    private Propiedad propiedad;
    private double precio;
    public TransaccionInmobiliaria(int id, Propiedad propiedad, double precio) throws PrecioInferiorException {
        if (precio < propiedad.getPrecio()) throw new PrecioInferiorException("El precio de transaccion es menor al de la propiedad.");
        this.id = id;
        this.propiedad = propiedad;
        this.precio = precio;
    }
    public String toString() { return "Transaccion " + id + " por S/ " + precio + " - " + propiedad; }
}

class ContratoAlquiler {
    private int meses;
    private double monto;
    public void configurar(int meses, double monto) throws ContratoInvalidoException {
        if (meses <= 0 || monto <= 0) throw new ContratoInvalidoException("Duracion y monto deben ser positivos.");
        this.meses = meses;
        this.monto = monto;
    }
    public String toString() { return meses + " meses, S/ " + monto + " mensuales"; }
}

class HistorialDePagos {
    private List<String> pagos = new ArrayList<>();
    public void registrar(LocalDate fecha, double monto) throws PagoInvalidoException {
        if (fecha.isAfter(LocalDate.now()) || monto < 0) {
            throw new PagoInvalidoException("Fecha futura o monto negativo.");
        }
        pagos.add(fecha + " - S/ " + monto);
    }
    public void listar() { for (String p : pagos) System.out.println(p); }
}

public class Ejercicio7 {
    public static void main(String[] args) {
        try {
            Propiedad p = new Propiedad("Av. Ejemplo 123", 180000, 95);
            TransaccionInmobiliaria t = new TransaccionInmobiliaria(1, p, 185000);
            System.out.println(t);

            ContratoAlquiler contrato = new ContratoAlquiler();
            contrato.configurar(12, 1400);
            System.out.println("Contrato: " + contrato);

            HistorialDePagos historial = new HistorialDePagos();
            historial.registrar(LocalDate.of(2026, 9, 9), 1400);
            historial.listar();

            new TransaccionInmobiliaria(2, p, 150000);
        } catch (DatosInvalidosException | PrecioInferiorException |
                 ContratoInvalidoException | PagoInvalidoException e) {
            System.out.println("Operacion inmobiliaria rechazada: " + e.getMessage());
        }

        try {
            new Propiedad("Propiedad invalida", -1, 50);
        } catch (DatosInvalidosException e) {
            System.out.println("Propiedad rechazada: " + e.getMessage());
        }
    }
}
