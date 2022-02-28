//public class H2jdbcRecordDemo {
//}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2jdbcReadDemo {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();

            //Employees with birthday today
            String sql = "select first, last from E where(DAY(DOB) = day (CURRENT_DATE) AND MONTH(DOB)= MONTH(CURRENT_DATE))";
            ResultSet rs = stmt.executeQuery(sql);


            // STEP 4: Extract data from result set for Query 1
            while(rs.next()) {
                // Retrieve by column name

                String first = rs.getString("first");
                String last = rs.getString("last");
                System.out.print("NAME: " + first);
                System.out.println(" " + last);
            }

            // Employees with birthdays in coming week
            String sql2="select first, last, DOB from E where ( (DAY_OF_YEAR(DOB) > DAY_OF_YEAR(CURRENT_DATE)) AND (DAY_OF_YEAR(DOB) < (DAY_OF_YEAR(CURRENT_DATE) + 7))) ";
            ResultSet rs2= stmt.executeQuery(sql2);
            //Extract data from result set of Query 2
            while(rs2.next()){
                String DOB = rs2.getString("DOB");
                String first = rs2.getString("first");
                String last = rs2.getString("last");
                System.out.print("NAME: " + first);
                System.out.println(" " + last);
                System.out.println("Day"+ DOB);
                }
            // STEP 5: Clean-up environment
            rs.close();
            rs2.close();
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
