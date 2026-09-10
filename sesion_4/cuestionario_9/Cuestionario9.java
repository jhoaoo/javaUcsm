class RecursoPrueba implements AutoCloseable {
    @Override
    public void close() throws Exception {
        throw new Exception("Excepcion producida al cerrar el recurso");
    }
}

public class Cuestionario9 {
    public static void main(String[] args) {
        try (RecursoPrueba recurso = new RecursoPrueba()) {
            throw new Exception("Excepcion original del bloque try");
        } catch (Exception e) {
            System.out.println("Capturada: " + e.getMessage());
            for (Throwable suprimida : e.getSuppressed()) {
                System.out.println("Suprimida: " + suprimida.getMessage());
            }
        }
    }
}
