public class Usuario {
    int idUsuario;
    String nombre;
    String correo;

    public Usuario(int idUsuario, String nombre, String correo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String obtenerCampo(String campo) {
        switch (campo) {
            case "idUsuario": return String.valueOf(idUsuario);
            case "nombre": return nombre;
            case "correo": return correo;
            default: throw new IllegalArgumentException("Campo incorrecto");
        }
    }
}