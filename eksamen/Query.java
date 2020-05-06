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
import java.util.List;
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
        Connection conn = this.connect(); 
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setInt(3, phone);
            pstmt.setString(4, sex);
            pstmt.setString(5, intrest1);
            pstmt.setString(6, intrest2);
            pstmt.setString(7, intrest3);
            pstmt.setString(8, city);
            pstmt.executeUpdate();
            pstmt.close();
            login(phone);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if (conn != null) {
              try {
                conn.close(); // <-- This is important
              } catch (SQLException e) {
                /* handle exception */
              }
            }
        }
    }

    public void writeFile(int Id, int phone) {
        try {
            String phoneString = new Integer(phone).toString();
            Files.deleteIfExists(Paths.get(phoneString + ".txt"));
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
        Connection conn = this.connect();
        try (   Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            id = rs.getInt("id");
            writeFile(id, phone);
            stmt.close();
            rs.close();
        }catch (SQLException e) {
            System.out.println("Error i login");
            System.out.println(e.getMessage());
        }finally {
            if (conn != null) {
              try {
                conn.close(); // <-- This is important
              } catch (SQLException e) {
                /* handle exception */
              }
            }
        }

    }
    public void searchResult (int ID_give, int phone) {
        String sql = "SELECT * FROM Users WHERE ID=" + ID_give;
        Connection conn = this.connect(); 
        try {
            Statement stmt  = conn.createStatement();
            
            ResultSet rs    = stmt.executeQuery(sql); 
            int yourID = getLogged(phone);
            while(rs.next()) {
                String name = rs.getString("name");
                String phoneString = rs.getString("phone");
                stmt.close();
                GUI.searchAction(name, phoneString, yourID, ID_give, conn);
            }
            rs.close();
                
            
        }
        catch (Exception e)
            {
              System.err.println("searchResult Error");
              System.err.println(e.getMessage());
            }finally {
                if (conn != null) {
                  try {
                    conn.close(); // <-- This is important
                  } catch (SQLException e) {
                    /* handle exception */
                  }
                }
            }
    }
    public int getLogged(int phone){
        int yourID = 0;
        Connection conn = this.connect();
        try {
            String sql = "SELECT ID FROM Users WHERE phone="+phone;
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql); 
            
            while(rs.next()) {
                yourID = rs.getInt("id");
            }
            stmt.close();
            rs.close();   
        }
        catch (Exception e)
        {
            System.err.println("getLogged Error");
            System.err.println(e.getMessage());
        }finally {
            if (conn != null) {
              try {
                conn.close(); // <-- This is important
              } catch (SQLException e) {
                /* handle exception */
              }
            }
        }
        return yourID;
        
    }

    public void sendId(int yourID, int ID_give, Connection conn) {
        String sql = "INSERT INTO infoexchange(inforeciveid, infogiveid) VALUES(?,?)";
        try (
        PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, yourID);
        stmt.setInt(2, ID_give);
        stmt.executeUpdate();
        stmt.close();
        }  
        catch (SQLException e)
        {
            System.err.println("SendID Error");
            System.out.println(e.getMessage());
        }finally {
            if (conn != null) {
              try {
                conn.close(); // <-- This is important
              } catch (SQLException e) {
                /* handle exception */
              }
            }
        }
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
        Statement stmt = conn.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ageList.add(rs.getInt(1));
                sexList.add(rs.getString(2));
                interest1List.add(rs.getString(3));
                interest2List.add(rs.getString(4));
                interest3List.add(rs.getString(5));
                idList.add(rs.getInt(6));
            }
            stmt.close();
            rs.close();
            List<Integer> ratingList = getInterests(phone, interest1List, interest2List, interest3List);
            GUI.matchSearch(ageList, sexList, interest1List, interest2List, interest3List, idList, phone, ratingList);
        }finally {
            if (conn != null) {
              try {
                conn.close(); // <-- This is important
              } catch (SQLException e) {
                /* handle exception */
              }
            }
        }
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
            stmt.close();
            rs.close();
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
            ratingPoint=0;

        }
        }finally {
            if (conn != null) {
              try {
                conn.close(); // <-- This is important
              } catch (SQLException e) {
                /* handle exception */
              }
            }
        }

        return ratingList;

    }
    public void useID(List<Integer> logsId){
        int input;
        List<String> logsName = new ArrayList<String>();
        List<Integer> logsPhone = new ArrayList<Integer>();

    
        for(int i = 0; i<logsId.size();i++){
            String sql = "SELECT Name, Phone FROM Users Where ID="+logsId.get(i);
            System.out.println(logsId.get(i));
            System.err.println(sql);
            System.err.println("for loop");

        
       try {
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()){
           
            System.err.println("while error1");
            logsName.add(rs.getString("Name")); 
            logsPhone.add(rs.getInt("Phone")); 
            System.out.println(logsName);
            System.err.println("etter while");
                }
                
            GUI.logsAction(logsName, logsPhone);
            rs.close();
            stmt.close();
            conn.close();
       } 
    
        catch (SQLException Sex){
            System.out.println("feil" + Sex);
        } 
    }

    }

    public void getID(int phone)  {
            
        List<Integer> logsId = new ArrayList<Integer>();
        String line;
        String bla = Integer.toString(phone);
        System.out.println(bla);
         
        
        //Statement stmt2 = conn.createStatement();
        Connection conn = this.connect();
        try {
            BufferedReader in = new BufferedReader(new FileReader(bla+".txt"));
            
            Statement stmt = conn.createStatement();
            while((line = in.readLine()) != null){
            int lineInt = Integer.parseInt(line);
            System.out.println(lineInt);
            String sql = "SELECT INFOreciveID FROM infoExchange WHERE INFOgiveID = " + lineInt;
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(sql);
          
            while (rs.next()){
               System.out.println(line);
                logsId.add(rs.getInt("INFOreciveID"));
                //logsName.add(rs2.getString("name"));    
    } 
    rs.close();
    stmt.close();  
          //useID(logsId);
          System.out.println(logsId);
          rs.close();
          stmt.close(); 
          conn.close();
          in.close();



        //stmt2.close();
        //System.out.println(logsName);
        //GUI.logsAction(logsName);
        }
    }
        catch (SQLException Sex){
            System.out.println("feil" + Sex);
        }
        catch (Exception e){
            System.out.println("feil e" + e);
        }finally {
            if (conn != null) {
              try {
                conn.close(); // <-- This is important
              } catch (SQLException e) {
                /* handle exception */
              }
            }
        }   
        useID(logsId);

    }  
    }
         
            



