public class EnviadorCorreo implements CanalNotificacion {
    @Override
    public void enviarNotificacion(String destino, String mensaje) {
        System.out.println("Correo para " + destino + ": " + mensaje);
    }
}
