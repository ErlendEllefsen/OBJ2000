package eksamen;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/* Bruk dette for å kjøre

    javac RegisterQuery.java
    java -classpath ".;sqlite-jdbc-3.23.1.jar" SearchMatch

*/

public class RegisterQurey {
    private static Connection conn = null;
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
        }
        catch (Exception e)
{
  System.err.println("EXCEPTION");
  System.err.println(e.getMessage());
}
    } 
}
