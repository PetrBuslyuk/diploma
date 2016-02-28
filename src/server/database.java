
package server;
import java.sql.*;
import java.sql.Connection;
import org.h2.jdbcx.JdbcConnectionPool;

public class database {
    Connection conn;
    database() throws ClassNotFoundException, SQLException,
                InstantiationException, IllegalAccessException {
        JdbcConnectionPool cp = JdbcConnectionPool
                            .create("jdbc:h2:~/myDB", "root", "1111");
        conn = cp.getConnection();
    }
    
    void closeConnection() throws SQLException{
        conn.close();
    }
    
    ResultSet getAllUpdates() throws SQLException{
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("Select * from updates;");
    }
    ResultSet getAllUsers() throws SQLException{
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("Select * from users;");
    }
    ResultSet getUserByImail(String email) throws SQLException{
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("Select * from users where email='"+email+"';");
    }
    ResultSet getUserByTel(String tel) throws SQLException{
        Statement stmt = conn.createStatement();
        return stmt.executeQuery("Select * from users where tel='"+tel+"';");
    }
    void insertUser(user u) throws SQLException{
        //TODO
        Statement stmt = conn.createStatement();
        //return stmt.executeQuery("Select * from users where tel='"++"';");
    }
}
