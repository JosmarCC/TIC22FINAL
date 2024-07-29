package pkg2024ft22;
import java.sql.*;

public class Conexion {
    Connection cnx;
    PreparedStatement ps;
    ResultSet rs;
    
    public Connection conectar(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.cnx = DriverManager.getConnection("jdbc:mariadb://localhost/ce22", "root", "");
            
        }catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return cnx;
    }
    
    public void desconectar() {

    }
}
