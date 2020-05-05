package eksamen;

import java.io.BufferedReader;
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
            stmt.close();
            rs.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void searchResult (int ID_give, int phone) {
        try {
            String sql = "SELECT * FROM Users WHERE ID="+ID_give;
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql); 
            int userID = getLogged(phone);
            while(rs.next()) {
                String name = rs.getString("name");
                String phoneString = rs.getString("phone");
                GUI.searchAction(name, phoneString);
            }
            stmt.close();
        }
        catch (Exception e)
            {
              System.err.println("EXCEPTION");
              System.err.println(e.getMessage());
            }
    }
    public int getLogged(int phone){
        int userID = 0;

        try {
            String sql = "";
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql); 
            
            while(rs.next()) {
                
            }
            stmt.close();
        }
        catch (Exception e){

        }
        return userID;
    }
    public void fillTable(int minAge, int maxAge, String sex, int phone) throws SQLException {

        List<Integer> ageList = new ArrayList<Integer>();
        List<String> sexList = new ArrayList<String>();
        List<String> interest1List = new ArrayList<String>();
        List<String> interest2List = new ArrayList<String>();
        List<String> interest3List = new ArrayList<String>();
        List<Integer> idList = new ArrayList<Integer>();
        
        String sql = "SELECT Age, Sex, Interest1, Interest2, Interest3, ID FROM Users WHERE Age BETWEEN " + minAge + " AND " + maxAge + " AND Sex="+"'"+ sex +"'";
        Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        try {
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
                ageList.add(rs.getInt(1));
                sexList.add(rs.getString(2));
                interest1List.add(rs.getString(3));
                interest2List.add(rs.getString(4));
                interest3List.add(rs.getString(5));
                idList.add(rs.getInt(6));
            }
            List<Integer> ratingList = getInterests(phone, interest1List, interest2List, interest3List);
            GUI.matchSearch(ageList, sexList, interest1List, interest2List, interest3List, idList, phone, ratingList);
        }finally {
            stmt.close();}
        }
    public List<Integer> getInterests(int phone, List<String> interest1List, List<String> interest2List, List<String> interest3List) throws SQLException {
        List<String> userInterests = new ArrayList<String>();
        List<Integer> ratingList = new ArrayList<Integer>();
        String sql = "SELECT Interest1, Interest2, Interest3 FROM Users Where phone="+phone;
        Connection conn = this.connect();
        Statement stmt  = conn.createStatement();
        try {
            ResultSet rs    = stmt.executeQuery(sql);
            while (rs.next()) {
                userInterests.add(rs.getString(1));
                userInterests.add(rs.getString(2));
                userInterests.add(rs.getString(3));

            }
            System.out.println(userInterests);
            int ratingPoint=0;
        for(int i = 0; i<interest1List.size(); i++){
            String firstInterest = new String(interest1List.get(i));
            String secondInterest = new String(interest2List.get(i));
            String thirdInterest = new String(interest3List.get(i));
            boolean first = firstInterest.contentEquals(userInterests.get(0));
            boolean second = secondInterest.contentEquals(userInterests.get(0));
            boolean third = thirdInterest.contentEquals(userInterests.get(0));
            boolean fourth = firstInterest.contentEquals(userInterests.get(1));
            boolean fifth = secondInterest.contentEquals(userInterests.get(1));
            boolean sixth = thirdInterest.contentEquals(userInterests.get(1));
            boolean seventh = firstInterest.contentEquals(userInterests.get(2));
            boolean eighth = secondInterest.contentEquals(userInterests.get(2));
            boolean last = thirdInterest.contentEquals(userInterests.get(2));
          
            System.out.println(last);
            System.out.println(thirdInterest + " " + userInterests.get(2));

            if(first == true)
                ratingPoint=ratingPoint+12;
            if(second == true)
                ratingPoint=ratingPoint+9;
            if(third == true)
                ratingPoint=ratingPoint+6;
            if(fourth == true)
                ratingPoint=ratingPoint+9;
            if(fifth == true)
                ratingPoint=ratingPoint+6;
            if(sixth == true)
                ratingPoint=ratingPoint+3;
            if(seventh == true)
                ratingPoint=ratingPoint+6;
            if(eighth == true)
                ratingPoint=ratingPoint+3;
            if(last == true)
                ratingPoint=ratingPoint+1;

            ratingList.add(ratingPoint);
            System.out.println(ratingPoint);
            ratingPoint=0;

        }
        }finally {
            stmt.close();
        }

        return ratingList;

    }

    public void getID(int phone){
        String line;
            int logsId;
            String logsName;
            String sql;
        try {
          
            BufferedReader in = new BufferedReader(new FileReader(phone+".txt")); 
               while((line = in.readLine()) != null){
               
                sql = "SELECT INFO_reciveID FROM info_exchange Where INFO_giveID ="+line;
               
               
                    Connection conn = this.connect();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                        logsId = rs.getInt("INFO_reciveID");
                    
                    
                    String sql2 = "SELECT name FROM users Where ID="+logsId;
                    Statement stmt2 = conn.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(sql2);
                        logsName = rs2.getString("name");
                    GUI.logsAction(logsName);    
                 conn.close();
                }
                in.close();
                 
               
                
            }catch (Exception e){
                System.out.println(e.getMessage());
               }
               
            }    
        }     



