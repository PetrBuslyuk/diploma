
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
    
    protected void insertUser(user u) throws SQLException{
        int i = 0;ResultSet rows = getUserByImail(u.get_email());
        while(rows.next()){++i;}
        if(i == 0){
            String prstmt= "INSERT INTO users (name,secondname,tel,email) "
                    + "VALUES(?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(prstmt);
            stmt.setString(1, u.get_name());
            stmt.setString(2, u.get_secondname());
            stmt.setString(3, u.get_telephone());
            stmt.setString(4, u.get_email());
            stmt.executeUpdate();
            stmt.close();
        }        
    }
}
