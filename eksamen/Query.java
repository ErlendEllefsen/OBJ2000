package eksamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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
    public void insert(String name, int age, int phone, String sex, String intrest1, String intrest2, String intrest3, String city) {
        String sql = "INSERT INTO users(name, age, phone, sex, interest1, interest2, interest3, city) VALUES(?,?,?,?,?,?,?, ?)";

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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}