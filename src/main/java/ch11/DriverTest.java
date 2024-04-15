package ch11;
 
import java.sql.*;
 
public class DriverTest{
    public static void main(String args[]){
        Connection con = null;
 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/?user=user", 
                    "user", "1234"); 
            System.out.println("Success");
        }
        catch(SQLException ex){ 
            System.out.println("SQLException" + ex);
            ex.printStackTrace();
        }
        catch(Exception ex){ 
            System.out.println("Exception:" + ex);
            ex.printStackTrace();
        }
    }
}