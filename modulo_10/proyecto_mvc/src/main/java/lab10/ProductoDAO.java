package lab10;

import java.sql.*;
import java.util.ArrayList;

public class ProductoDAO {
    private Connection conectar() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:productos.db");
    }

    public void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS productos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre TEXT NOT NULL, categoria TEXT NOT NULL, " +
            "precio REAL NOT NULL, stock INTEGER NOT NULL)";
        try (Connection cn = conectar(); Statement st = cn.createStatement()) {
            st.execute(sql);
        }
    }

    public void guardar(Producto p) throws SQLException {
        String sql = "INSERT INTO productos(nombre, categoria, precio, stock) VALUES(?,?,?,?)";
        try (Connection cn = conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCategoria());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
        }
    }

    public ArrayList<Producto> listar() throws SQLException {
        ArrayList<Producto> lista = new ArrayList<>();
        try (Connection cn = conectar(); Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM productos ORDER BY id")) {
            while (rs.next()) {
                lista.add(new Producto(rs.getInt("id"), rs.getString("nombre"),
                    rs.getString("categoria"), rs.getDouble("precio"), rs.getInt("stock")));
            }
        }
        return lista;
    }

    public void eliminar(int id) throws SQLException {
        try (Connection cn = conectar(); PreparedStatement ps =
                cn.prepareStatement("DELETE FROM productos WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
