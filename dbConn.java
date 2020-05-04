import java.sql.ResultSet;
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.SQLException;  
import java.sql.Statement;

/* Bruk dette for å kjøre

    javac dbConn.java
    java -classpath ".;sqlite-jdbc-3.23.1.jar" dbConn

*/
public class dbConn{
    private static String url = "jdbc:sqlite:DateMe.db";
    private static Connection conn = null;

    public static void main(String[] args) {
        {
            try
            {
              kobleOpp();
              
              //SQL SELECT query. 

              String query = "SELECT * FROM users";
        
              //java statement
              Statement stmt = conn.createStatement();
              
              // execute query, get resultset
              ResultSet rs = stmt.executeQuery(query);
              
              // iterate resultset
              while (rs.next())
              {
                int id = rs.getInt("id");
                int Age = rs.getInt("age");
                String Sex = rs.getString("sex");
                String Interest1 = rs.getString("interest1");
                String Interest2 = rs.getString("interest2");
                String Interest3 = rs.getString("interest3");
                String City = rs.getString("city");
                
                // print results
                System.out.format("%s, %s, %s, %s, %s, %s\n", Age, Sex, City, Interest1, Interest2, Interest3, id);
              }
              stmt.close();
            }
            catch (Exception e)
            {
              System.err.println("Got an exception! ");
              System.err.println(e.getMessage());
            }
          }
        }
       /* 
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
*/

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