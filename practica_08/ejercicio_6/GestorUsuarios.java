import java.sql.*;
import java.util.Arrays;

public class GestorUsuarios {
    Usuario[] usuarios = new Usuario[0];

    public void cargar(Connection con) throws SQLException {
        usuarios = new Usuario[0];
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Usuario")) {
            while (rs.next()) {
                usuarios = Arrays.copyOf(usuarios, usuarios.length + 1);
                usuarios[usuarios.length - 1] = new Usuario(
                    rs.getInt("idUsuario"), rs.getString("nombre"),
                    rs.getString("correo"));
            }
        }
    }

    public static boolean campoValido(String campo) {
        return campo.equals("idUsuario") || campo.equals("nombre")
            || campo.equals("correo");
    }

    private int comparar(String a, String b, String campo) {
        if (a == null && b == null) return 0;
        if (a == null) return -1;
        if (b == null) return 1;
        if (campo.equals("idUsuario")) {
            return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
        }
        return a.compareTo(b);
    }

    public void consultar(String[] campos, String campoFiltro,
            String operador, String valor, String campoOrden,
            boolean ascendente, int limite) {
        for (String campo : campos) {
            if (!campoValido(campo)) {
                throw new IllegalArgumentException("Campo incorrecto");
            }
        }
        if (!campoValido(campoOrden) || limite < 0) {
            throw new IllegalArgumentException("Orden o limite incorrecto");
        }
        if (!campoFiltro.isEmpty()) {
            if (!campoValido(campoFiltro)
                    || !Arrays.asList("=", "!=", ">", "<", ">=", "<=")
                        .contains(operador)) {
                throw new IllegalArgumentException("Filtro incorrecto");
            }
            if (campoFiltro.equals("idUsuario")) Integer.parseInt(valor);
        }

        Usuario[] resultado = new Usuario[usuarios.length];
        int cantidad = 0;
        for (Usuario usuario : usuarios) {
            boolean cumple = true;
            if (!campoFiltro.isEmpty()) {
                String dato = usuario.obtenerCampo(campoFiltro);
                cumple = false;
                if (dato != null) {
                    int comparacion = comparar(dato, valor, campoFiltro);
                    switch (operador) {
                        case "=": cumple = comparacion == 0; break;
                        case "!=": cumple = comparacion != 0; break;
                        case ">": cumple = comparacion > 0; break;
                        case "<": cumple = comparacion < 0; break;
                        case ">=": cumple = comparacion >= 0; break;
                        case "<=": cumple = comparacion <= 0; break;
                    }
                }
            }
            if (cumple) resultado[cantidad++] = usuario;
        }

        for (int i = 0; i < cantidad - 1; i++) {
            for (int j = i + 1; j < cantidad; j++) {
                int comparacion = comparar(
                    resultado[i].obtenerCampo(campoOrden),
                    resultado[j].obtenerCampo(campoOrden), campoOrden);
                if ((ascendente && comparacion > 0)
                        || (!ascendente && comparacion < 0)) {
                    Usuario auxiliar = resultado[i];
                    resultado[i] = resultado[j];
                    resultado[j] = auxiliar;
                }
            }
        }

        int total = cantidad;
        if (limite > 0 && limite < total) total = limite;
        System.out.println(String.join(" | ", campos));
        for (int i = 0; i < total; i++) {
            String[] fila = new String[campos.length];
            for (int j = 0; j < campos.length; j++) {
                fila[j] = String.valueOf(resultado[i].obtenerCampo(campos[j]));
            }
            System.out.println(String.join(" | ", fila));
        }
        System.out.println("Registros mostrados: " + total);
    }
}