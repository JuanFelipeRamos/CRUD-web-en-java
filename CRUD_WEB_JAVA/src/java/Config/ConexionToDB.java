package Config;

// importación de las clases necesarias para la conexión con la base de datos y manejo de excepciones
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.DriverManager;

public class ConexionToDB {

    // variable para almacenar la conexión con la base de datos
    Connection con;

    // datos para la conexión con la base de datos
    private static final String db = "db_crud_java_web";
    private static final String url = "jdbc:mysql://localhost:3306/"
            + db
            + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String passw = "juan25sql";
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    // método para establecer la conexión con la base de datos
    public Connection ConectarBaseDeDatos() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, passw);
            System.out.println("Conexión exitosa con la base de datos " + db);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se pudo conectar a la base de datos " + db);
            Logger.getLogger(ConexionToDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con; // Retorna la conexión
    }

    // método principal para probar la conexión
    public static void main(String[] args) {
        ConexionToDB conexion = new ConexionToDB();
        conexion.ConectarBaseDeDatos();
    }

    public Connection getConnection() {
        if (con == null) {
            con = ConectarBaseDeDatos();
        }
        return con;
    }

}
