public class NotificadorReserva {
    private CanalNotificacion canal;

    public NotificadorReserva(CanalNotificacion canal) {
        this.canal = canal;
    }

    public void notificar(Cliente cliente, String mensaje) {
        canal.enviarNotificacion(cliente.getCorreo(), mensaje);
    }
}
