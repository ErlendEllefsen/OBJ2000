import java.sql.*;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.SQLException;  
/* Bruk dette for å kjøre

    javac dbConn.java
    java -classpath ".;sqlite-jdbc-3.23.1.jar" dbConn

*/
public class dbConn{
    private static String url = "jdbc:sqlite:DateMe.db";
    private static Connection conn = null;

    public static void main(String[] args) {
        kobleOpp();
        
        {
            try
            {
                //Insert stmt
              String query = " insert into users(Name, Age, Phone, Sex, Interests, City)"
                + " values (?, ?, ?, ?, ?, ?)";
        
              //insert preparedstatement
              PreparedStatement preparedStmt = conn.prepareStatement(query);
              preparedStmt.setString (1, "Per");
              preparedStmt.setInt    (2, 22);
              preparedStmt.setInt    (3, 13588552);
              preparedStmt.setString (4, "Male");
              preparedStmt.setString (5, "Sport, Gaming, Spising");
              preparedStmt.setString (6, "Bø i Telemark");
        
              // execute preparedstatement
              preparedStmt.execute();
              kobleNed();
            }
            catch (Exception e)
    {
      System.err.println("Got an exception!");
      System.err.println(e.getMessage());
    }
        } 
    }


    private static void kobleOpp() {
        try {
            conn = DriverManager.getConnection(url);
    }
    catch (SQLException e) {
        System.out.println("Oppkobling til databasen" + url + " feilet." + "\n" + e.toString() );
    }
}
    private static void kobleNed() {
    try {
        conn.close();
    }
    catch (SQLException e) { }
    }
}