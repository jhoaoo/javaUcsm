public class Main {
    public static void main(String[] args) {
        NotificationManager correo =
                new NotificationManager(new EmailNotification());
        NotificationManager sms =
                new NotificationManager(new SMSNotification());

        correo.enviar("Su reserva fue registrada.");
        sms.enviar("Su codigo de verificacion es 1234.");
    }
}
