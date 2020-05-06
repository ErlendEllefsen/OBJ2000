package eksamen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
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

    public void insert(String name, int age, int phone, String sex, String intrest1, String intrest2, String intrest3,
            String city) {
        String sql = "INSERT INTO users(name, age, phone, sex, interest1, interest2, interest3, city) VALUES(?,?,?,?,?,?,?, ?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

    public void writeFile(int Id, int phone) {
        try {
            String phoneString = new Integer(phone).toString();
            Files.deleteIfExists(Paths.get(phoneString + ".txt"));
            System.out.println("Deletion successful.");
        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        } catch (IOException e) {
            System.out.println("Invalid permissions.");
        }

        try {
            FileWriter writer = new FileWriter(phone + ".txt", true);
            writer.write(new Integer(Id).toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(int phone) {
        String sql = "SELECT ID FROM Users WHERE phone=" + phone;
        int id;
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            id = rs.getInt("id");
            conn.close();
            writeFile(id, phone);
            stmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void searchResult(int ID_give) {
        try {
            String sql = "SELECT * FROM Users WHERE ID=" + ID_give;
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                GUI.searchAction(name, phone);
            }
            stmt.close();
        } catch (Exception e) {
            System.err.println("EXCEPTION");
            System.err.println(e.getMessage());
        }
    }

    public void fillTable(int minAge, int maxAge, String sex) throws SQLException {
        List<Integer> ageList = new ArrayList<Integer>();
        List<String> sexList = new ArrayList<String>();
        List<String> interest1List = new ArrayList<String>();
        List<String> interest2List = new ArrayList<String>();
        List<String> interest3List = new ArrayList<String>();
        String sql = "SELECT Age, Sex, Interest1, Interest2, Interest3 FROM Users";
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ageList.add(rs.getInt(1));
                sexList.add(rs.getString(2));
            }
            System.out.println(ageList);
            System.out.println(sexList);
        } finally {
            stmt.close();
        }
    }
    public void useID() throws SQLException {
        List<String> logsName = new ArrayList<String>();
        int logsId = 9;
        String sql2 = "SELECT name FROM users Where ID="+logsId;
        Connection conn = this.connect();
        Statement stmt2 = conn.createStatement();
    try {
        System.out.println(logsId);
        ResultSet rs2 = stmt2.executeQuery(sql2);
        while (rs2.next()){
            logsName.add(rs2.getString("name"));  
        }
        System.out.println(logsName);
        GUI.logsAction(logsName);
    }
    catch (Exception e){
        System.out.println("feil2");
    }
    }

    public void getID(int phone)  {
            
            List<Integer> logsId = new ArrayList<Integer>();
            //List<String> logsName = new ArrayList<String>();
            String line;
            String bla = Integer.toString(phone);
            System.out.println(bla);
             
            //String sql2 = "SELECT name FROM users Where ID="+logsId;
            
            //Statement stmt2 = conn.createStatement();
        try {
            BufferedReader in = new BufferedReader(new FileReader(bla+".txt"));
            Connection conn = this.connect();
            Statement stmt = conn.createStatement();
            while((line = in.readLine()) != null){
                int lineInt = Integer.parseInt(line);
            System.out.println(lineInt);
            String sql = "SELECT INFO_reciveID FROM info_exchange WHERE INFO_giveID = " + lineInt;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
          

            //ResultSet rs2 = stmt2.executeQuery(sql2);
            while (rs.next()){
               System.out.println(line);
                logsId.add(rs.getInt("INFO_reciveID"));
                System.out.println(logsId);
                in.close();

                //logsName.add(rs2.getString("name"));    
    }   
          //useID(logsId);
       
        //stmt2.close();
        //System.out.println(logsName);
        //GUI.logsAction(logsName);
        }
    }
        catch (SQLException Sex){
            System.out.println("feil");
        }
        catch (Exception e){
            System.out.println("feil e" + e);
        }   
    }  
    }
         
            



