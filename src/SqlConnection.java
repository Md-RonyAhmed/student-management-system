
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/stdmgdb", "root", "");
            System.out.print("Database is connected !");
          return con;  
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.print("Do not connect to DB - Error:"+ex);
         
        }
        return null;

    }
}
