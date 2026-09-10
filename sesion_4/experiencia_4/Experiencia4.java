import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class HistorialVacioException extends Exception {
    public HistorialVacioException(String mensaje) { super(mensaje); }
}

class CuentaBancaria {
    private String numeroCuenta;
    private String titular;
    private double saldo;
    private List<String> historial = new ArrayList<>();

    public CuentaBancaria(String numeroCuenta, String titular, double saldo) {
        if (saldo < 0) throw new IllegalArgumentException("Saldo inicial invalido.");
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    public void depositar(double monto) {
        if (monto <= 0) throw new IllegalArgumentException("Monto invalido.");
        saldo += monto;
        historial.add("Deposito: S/ " + monto + " | Saldo: S/ " + saldo);
    }

    public String getNumeroCuenta() { return numeroCuenta; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }
    public List<String> getHistorial() { return historial; }
}

class ReporteTransacciones {
    public static void generarReporte(CuentaBancaria cuenta, String archivo)
            throws IOException, HistorialVacioException {
        if (cuenta.getHistorial().isEmpty()) {
            throw new HistorialVacioException("La cuenta no tiene transacciones registradas.");
        }
        try (PrintWriter out = new PrintWriter(archivo)) {
            out.println("Numero de cuenta: " + cuenta.getNumeroCuenta());
            out.println("Titular: " + cuenta.getTitular());
            out.println("Saldo: S/ " + cuenta.getSaldo());
            out.println("Transacciones:");
            for (String movimiento : cuenta.getHistorial()) {
                out.println("- " + movimiento);
            }
        }
    }

    public static void leerReporte(String archivo) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(archivo))) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        }
    }
}

public class Experiencia4 {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria("R-2026", "Lucia Soto", 300);

        try {
            ReporteTransacciones.generarReporte(cuenta, "reporte.txt");
        } catch (HistorialVacioException e) {
            System.out.println("Reporte rechazado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de escritura: " + e.getMessage());
        }

        cuenta.depositar(150);
        try {
            ReporteTransacciones.generarReporte(cuenta, "reporte.txt");
            System.out.println("Reporte generado. Contenido:");
            ReporteTransacciones.leerReporte("reporte.txt");
        } catch (IOException | HistorialVacioException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            ReporteTransacciones.leerReporte("archivo_inexistente.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Lectura rechazada: archivo inexistente.");
        }
    }
}
