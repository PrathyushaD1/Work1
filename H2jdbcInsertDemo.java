//public class H2jdbcInsertDemo {
//}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2jdbcInsertDemo {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Connected database successfully...");

            // STEP 3: Execute a query

            stmt = conn.createStatement();

            String sql = "INSERT INTO E " + "VALUES (100, 'Zara', 'Ali', '2004-12-31')";

            stmt.executeUpdate(sql);
            sql = "INSERT INTO E " + "VALUES (101, 'Mahnaz', 'Fatma','2000-01-01' )";

            stmt.executeUpdate(sql);
            sql = "INSERT INTO E " + "VALUES (102, 'Zaid', 'Khan', '2000-02-01')";

            stmt.executeUpdate(sql);


            String sql4 = "INSERT INTO E " + "VALUES(109, 'Nivan', 'Goel', '1998-10-01')";

            stmt.executeUpdate(sql4);
            System.out.println("Inserted records into the table...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
    }
}
