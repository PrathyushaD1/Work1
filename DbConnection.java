import java.sql.*;
public class DbConnection {
    public static void main(String[] args){
        String url="jdbc:h2:~/test";
        String user="";
        String password="";
        try(Connection con= DriverManager.getConnection(url,user,password)){

            System.out.println("Successful");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
