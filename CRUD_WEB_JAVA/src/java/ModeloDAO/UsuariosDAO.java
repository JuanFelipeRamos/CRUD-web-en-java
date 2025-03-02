// UsuariosDAO.java - Implementación de editar y eliminar
package ModeloDAO;

import Config.ConexionToDB;
import Interfaces.CRUD;
import Modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO implements CRUD {

    ConexionToDB cn = new ConexionToDB();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List<Usuarios> listar() {
        List<Usuarios> list = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            con = cn.getConnection();
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
            System.out.println("Usuarios recuperados: " + list.size());
            for (Usuarios u : list) {
                System.out.println("ID: " + u.getId() + ", Nombre: " + u.getNombre());
            }
            System.out.println("Ejecutando consulta: SELECT * FROM usuarios");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Usuarios list(int id) {
        Usuarios us = new Usuarios();
        String sql = "SELECT * FROM usuarios WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setNombre(rs.getString("nombre"));
                us.setEdad(rs.getInt("edad"));
                us.setCelular(rs.getString("celular"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return us;
    }

    @Override
    public boolean crear(Usuarios user) {
        String sql = "INSERT INTO usuarios(nombre, edad, celular) VALUES(?, ?, ?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setInt(2, user.getEdad());
            ps.setString(3, user.getCelular());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editar(Usuarios user) {
        String sql = "UPDATE usuarios SET nombre=?, edad=?, celular=? WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getNombre());
            ps.setInt(2, user.getEdad());
            ps.setString(3, user.getCelular());
            ps.setInt(4, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean elimiar(int id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
