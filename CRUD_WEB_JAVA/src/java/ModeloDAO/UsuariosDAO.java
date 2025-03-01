package ModeloDAO;

import Config.ConexionToDB;
import Interfaces.CRUD;
import Modelo.Usuarios;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

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
                us.setCelular(rs.getString("celular")); // Cambié a getString porque en la BD es varchar
                list.add(us);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Esto imprimirá cualquier error en la consola
        }

        // Verificamos si la lista tiene datos
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
        throw new UnsupportedOperationException("Not supported yet.");
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
