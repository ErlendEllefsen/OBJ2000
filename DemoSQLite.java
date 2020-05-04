import static java.lang.System.*;
import java.sql.*;


/**
  *  Programmet demonstrerer oppkobling til en SQLite-database.
  *  Databasefilen vare.db vil bli opprettet, hvis den ikke finnes.
  *  
  */
public class DemoSQLite {
 
  public static void main(String[] args) {
    String jdbcDriver = "org.sqlite.JDBC";
    String url = "jdbc:sqlite:vare.db";
    
    Connection conn = null;
    
    try {
      Class.forName(jdbcDriver);
      conn = DriverManager.getConnection(url);
      
      if (conn != null)
        out.println("Oppkobling til databasen " + url + " var vellykket.");
        
    } catch (ClassNotFoundException e) {
      out.println("Fant ikke JDBC-driver " + jdbcDriver + "\n" + e.toString());
    } catch (SQLException e) {
      out.println("Oppkobling til databasen " + url + " feilet.\n" + e.toString());
    }
    finally {
      try {
        conn.close();
      }
      catch (SQLException e) {
        out.println("Klarte ikke Ã¥ lukke forbindelsen til databasen " + url + "\n" + e.toString());
      }
    }
  }
  
}