package eksamen;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Query {
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:./db/DateMe.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    public void insert(String name, int age, int phone, 
                       String sex, String intrest1, String intrest2, 
                       String intrest3, String city) {
        String sql = 
        "INSERT INTO users(name, age, phone, sex, interest1, interest2, interest3, city) VALUES(?,?,?,?,?,?,?, ?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setInt(3, phone);
            pstmt.setString(4, sex);
            pstmt.setString(5, intrest1);
            pstmt.setString(6, intrest2);
            pstmt.setString(7, intrest3);
            pstmt.setString(8, city);
            pstmt.executeUpdate();
            conn.close();
            login(phone);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void writeFile(int Id, int phone){
        try
        { 
            String phoneString = new Integer(phone).toString();
            Files.deleteIfExists(Paths.get(phoneString+".txt")); 
            System.out.println("Deletion successful."); 
        } 
        catch(NoSuchFileException e) 
        { 
            System.out.println("No such file/directory exists"); 
        } 
        catch(IOException e) 
        { 
            System.out.println("Invalid permissions."); 
        } 
        
        try{
            FileWriter writer = new FileWriter(phone+".txt", true);
            writer.write(new Integer(Id).toString());
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void login(int phone){
        String sql = "SELECT ID FROM Users WHERE phone="+phone;
        int id;
        try (
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql)){
            id = rs.getInt("id");
            conn.close();
            writeFile(id, phone);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void SearchResult (int ID_give) {
        String sql = "SELECT * FROM Users WHERE id="+ID_give;
        String name;
        String phone;
        try ( 
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql)){
                name = rs.getString("name");
                phone = rs.getString("phone");
                conn.close();
                System.out.println(name);
                System.out.println(phone);
            }catch (SQLException e) {
                System.out.println(e.getMessage()); 
            }
    }
}
