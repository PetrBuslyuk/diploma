
package server;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import org.h2.jdbcx.JdbcConnectionPool;

public class Database {
    Connection conn;
    Database() throws ClassNotFoundException, SQLException,
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
    
    protected void insertUser(User u) throws SQLException{
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
            rows.close();
            stmt.close();
        }        
    }
    
    protected void insertCompanyData(client.companiesToSent companies, String email) throws SQLException{    
        Iterator i = companies.getCampanyIterator();
        String queryDelete = "DELETE FROM CLIENTS_AND_COMPANIES WHERE email='"+email+"';";
        PreparedStatement stmt1 = conn.prepareStatement(queryDelete);
        stmt1.execute();
        queryDelete = "DELETE FROM COMPANIES WHERE email='"+email+"';";
        stmt1 = conn.prepareStatement(queryDelete);
        stmt1.execute();
        stmt1.close();
        while(i.hasNext()){
            client.company c = (client.company) i.next();
            String prstmt= "INSERT INTO CLIENTS_AND_COMPANIES (company_name,percents,period,email,depo) VALUES(?,?,?,?,?);";
            PreparedStatement stmt = conn.prepareStatement(prstmt);
            stmt.setString(1, c.get_name());
            stmt.setString(2, c.get_persent());
            stmt.setString(3, c.get_period());
            stmt.setString(4, email);
            stmt.setString(5, c.get_depo());
            stmt.executeUpdate();
            for(int ind=0;ind<Integer.parseInt(c.get_period());ind++){
                String st= "INSERT INTO COMPANIES (period,plus,minuc,reinvesting,company_name,email) VALUES(?,?,?,?,?,?);";
                PreparedStatement s = conn.prepareStatement(st);
                s.setString(1, String.valueOf(ind));
                s.setString(2, c.get_plus(ind));
                s.setString(3, c.get_minus(ind));
                s.setString(4, String.valueOf(c.get_reinvesting(ind)));
                s.setString(5, c.get_name());
                s.setString(6, email);
                s.executeUpdate();
               
            }
        }   
    }
    
    protected client.companiesToSent getObjectToRestore(String email) throws SQLException{
        client.companiesToSent c = new client.companiesToSent();
        Statement stmt = conn.createStatement();
        ResultSet r = stmt.executeQuery("Select * from CLIENTS_AND_COMPANIES where email='"+email+"';");
        
        //stmt.close();
        while(r.next()){
            Statement stmt1 = conn.createStatement();
            ResultSet resData = stmt1.executeQuery("Select * from COMPANIES where email='"+email
                    +"'AND company_name='"+r.getString("company_name")+"';");
            
            ArrayList<String> plus=new ArrayList<>();
            ArrayList<String> minus=new ArrayList<>();
            ArrayList<String> reinvesting=new ArrayList<>();
            while(resData.next()){
                plus.add(resData.getString("plus"));
                minus.add(resData.getString("minuc"));
                reinvesting.add(resData.getString("reinvesting"));
            }
            client.company company = new client.company(r.getString("company_name"),
            r.getString("depo"),r.getString("percents"),r.getString("period"),plus,minus,reinvesting);
            c.companiesToSend.add(company); 
            
        }
        //r.close();
        return c;
    }
}
