package ModeloDAO;

import Config.ConexionToDB;
import Interfaces.CRUD;
import Modelo.Usuarios;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDAO implements CRUD {

    ConexionToDB cn = new ConexionToDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Usuarios us = new Usuarios();

    @Override
    public List<Usuarios> listar() {
        ArrayList<Usuarios> list = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try {
            con = cn.getConnection();
            if (con == null) {
                System.out.println("Error: No se pudo establecer conexión con la base de datos.");
                return list;
            }

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Usuarios us = new Usuarios();
                us.setId(rs.getInt("id"));
                us.setNombre(rs.getString("nombre"));
                us.setEdad(rs.getInt("edad"));
                us.setCelular(rs.getString("celular"));
                list.add(us);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Usuarios recuperados:");
        for (Usuarios u : list) {
            System.out.println("ID: " + u.getId() + ", Nombre: " + u.getNombre() + ", Edad: " + u.getEdad() + ", Celular: " + u.getCelular());
        }

        return list;
    }

    @Override
    public Usuarios list(int Id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean crear(Usuarios user) {
        String sql = "INSERT INTO usuarios(nombre, edad, celular) VALUES(?, ?, ?)";
        try {
            con = cn.getConnection();

            if (con == null) {
                System.out.println("Error: No se pudo establecer conexión con la base de datos.");
                return false;
            }

            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setInt(2, user.getEdad());
            ps.setString(3, user.getCelular());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;
        } catch (SQLException e) {
            System.out.println("Error al intentar crear un nuevo usuario (UsuariosDAO): " + e);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();  // 🔹 Aquí cerramos la conexión
                }
            } catch (SQLException e) {
                System.out.println("Error cerrando la conexión: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean editar(Usuarios user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean elimiar(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
