public class NotificationManager {
    private Notificacion notificacion;

    public NotificationManager(Notificacion notificacion) {
        this.notificacion = notificacion;
    }

    public void enviar(String mensaje) {
        notificacion.enviar(mensaje);
    }
}
