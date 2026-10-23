import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             Connection con = DriverManager.getConnection(
                 "jdbc:sqlite:biblioteca.db")) {
            GestorUsuarios gestor = new GestorUsuarios();
            gestor.cargar(con);
            System.out.println("Campos: idUsuario, nombre, correo");
            System.out.print("Campos a mostrar separados por coma: ");
            String[] campos = sc.nextLine().split(",", -1);
            for (int i = 0; i < campos.length; i++) {
                campos[i] = campos[i].trim();
            }
            System.out.print("Campo del filtro (Enter para ninguno): ");
            String campoFiltro = sc.nextLine().trim();
            String operador = "";
            String valor = "";
            if (!campoFiltro.isEmpty()) {
                System.out.print("Operador (=, !=, >, <, >=, <=): ");
                operador = sc.nextLine().trim();
                System.out.print("Valor: ");
                valor = sc.nextLine();
            }
            System.out.print("Campo para ordenar: ");
            String campoOrden = sc.nextLine().trim();
            System.out.print("Orden (ASC o DESC): ");
            String orden = sc.nextLine().trim();
            if (!orden.equalsIgnoreCase("ASC")
                    && !orden.equalsIgnoreCase("DESC")) {
                throw new IllegalArgumentException("Orden incorrecto");
            }
            System.out.print("Limite de registros (0 para todos): ");
            int limite = Integer.parseInt(sc.nextLine().trim());
            gestor.consultar(campos, campoFiltro, operador, valor,
                campoOrden, orden.equalsIgnoreCase("ASC"), limite);
        } catch (SQLException e) {
            System.out.println("Error de base de datos: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Dato incorrecto: " + e.getMessage());
        }
    }
}