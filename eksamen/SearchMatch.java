package eksamen;
import java.sql.ResultSet;
import java.sql.Connection;   
import java.sql.Statement;


public class SearchMatch {
    private static Connection conn = null;
    public static void main(String[] args) {
        {
            try
            {
              
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
              System.err.println("EXCEPTION");
              System.err.println(e.getMessage());
            }
        }
    }
}