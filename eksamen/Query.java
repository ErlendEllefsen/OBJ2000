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
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Query {
    // Etablerer connection til sqlite databasen
    private Connection connect() {
        String url = "jdbc:sqlite:./db/DateMe.db";
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Registrerer data om brukeren i databasen
    public void insert(String name, int age, int phone, String sex, String intrest1, String intrest2, String intrest3,
            String city) {
        String sql = "INSERT INTO users(name, age, phone, sex, interest1, interest2, interest3, city) VALUES(?,?,?,?,?,?,?,?)";
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
            // Om inserten blir avslått av databasen
            ErrorMessage error = new ErrorMessage("User is already registered or you have empty fields");
            System.out.println(e.getMessage() + " " + error);
        }finally {
            if (conn != null) {
              try {
                conn.close(); 
              } catch (SQLException e) {
              }
            }
        }
    }
    // Skriver tekstfil
    public void writeFile(int Id, int phone) {
        try {
            String phoneString = new Integer(phone).toString();
            // Sletter tekstfilen om en allerede er laget i samme session
            Files.deleteIfExists(Paths.get(phoneString + ".txt"));
        } catch (NoSuchFileException e) {
            System.out.println("No such file/directory exists");
        } catch (IOException e) {
            System.out.println("Invalid permissions.");
        }

        try {
            // Skriver ID i teksfilen
            FileWriter writer = new FileWriter(phone + ".txt", true);
            writer.write(new Integer(Id).toString());
            writer.close();
            GUI.registerDone(phone);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Logger deg inn med telefonnummer
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
            //Om telefonnummeret ikke finnes i databasen
            ErrorMessage error = new ErrorMessage("Bruker finnes ikke");
            System.out.println(e.getMessage() + error);
        }finally {
            if (conn != null) {
              try {
                conn.close(); 
              } catch (SQLException e) {
              }
            }
        }

    }
    //Henter ID fra tabellen ved å trykke på tabellen. 
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
                    conn.close(); 
                  } catch (SQLException e) {
                  }
                }
            }
    }
    //Finner ID en til brukeren ved å bruken det unike telefon nr. til brukeren. 
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
                conn.close(); 
              } catch (SQLException e) {
              }
            }
        }
        return yourID;
        
    }
    
    //Sender brukeren sin ID og ID til personen brukeren trykker på i tabellen til en tabell i databasen
    //for å holde styr på hvem som har fått informasjon om hvem. 
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
                conn.close(); 
              } catch (SQLException e) {
              }
            }
        }
    }
    // Fyllet tabellen etter hvilke parametre du har lagt inn i søket
    public void fillTable(int minAge, int maxAge, String sex, int phone, int amountofMatches) throws SQLException {

        // All info som trengs settes inn i tabeller
        List<Integer> ageList = new ArrayList<Integer>();
        List<String> sexList = new ArrayList<String>();
        List<String> cityList = new ArrayList<String>();
        List<String> interest1List = new ArrayList<String>();
        List<String> interest2List = new ArrayList<String>();
        List<String> interest3List = new ArrayList<String>();
        List<Integer> idList = new ArrayList<Integer>();
        String sql = "SELECT Age, Sex, Interest1, Interest2, Interest3, Phone, ID, City FROM Users WHERE Age BETWEEN " + minAge + " AND " + maxAge + " AND Sex="+"'"+ sex +"'";
        Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        try {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                // Slik at du ikke kan finne deg selv
                if(rs.getInt(6)==phone)
                    System.out.println("Deg selv");
                else{
                ageList.add(rs.getInt(1));
                sexList.add(rs.getString(2));
                cityList.add(rs.getString(8));
                interest1List.add(rs.getString(3));
                interest2List.add(rs.getString(4));
                interest3List.add(rs.getString(5));
                idList.add(rs.getInt(7));
                }
            }
            stmt.close();
            rs.close();
            List<Integer> ratingList = getInterests(phone, interest1List, interest2List, interest3List);
            GUI.matchSearch(ageList, sexList, interest1List, interest2List, interest3List, idList, phone, ratingList, amountofMatches, cityList);
        }finally {
            if (conn != null) {
              try {
                conn.close(); 
              } catch (SQLException e) {
              }
            }
        }
    }
    // Henter interessens til en påloggede brukeren og sammenligner dem med alle brukere i tabellen
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
            /*
             * For å rangere matcher ut i fra interesser sammenlignes først stringene i tabellene opp mot hverandre
             * Deretter gis hver bruker i tabellen en rating som vises i tabellen
             * Mest poeng gis om interesse 1 matcher for begge parter
             * Minst poeng gis om interesse 2 matcher for begge parter
             * Om ingen interesser matcher blir det ikke gitt noen poeng
             */
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
                conn.close();
              } catch (SQLException e) {
              }
            }
        }

        return ratingList;

    }
    /*
     * useId bruker logsId som blir hentet fra getID metoden. 
     * for løkken pakker ut verdiene i logsId arraylisten slik at alle verdiene utfører en spørring. 
     * Videre blir  narv og tlfnr på matchene lagt inn to forskjellige arraylist og sendt videre til GUI.java
    */
    public void useID(List<Integer> logsId){
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
           
            logsName.add(rs.getString("Name")); 
            logsPhone.add(rs.getInt("Phone")); 
                }
                
            GUI.logsAction(logsName, logsPhone);
            rs.close();
            stmt.close();
            conn.close();
       } 
    
        catch (SQLException e){
            System.out.println("feil" + e);
        } 
    }

    }

    /*
    ** getID metoden sin funksjon er å hente ut ID fra txtfil og matche den iden med INFOgiveId dra databasen
    ** INFOgiveID er id til den personen som er logget inn i applikasjonen når den trykker for å få mer informasjon om en bruker
    ** Derfor matcher vi INFOgiveID med lineInt som har verdien fra txtfil (altså den som er logget inn) 
    ** Videre blir ID på de som har blitt søkt opp altså INFOreciveID lagret i en arraylist og tatt med videre til useID metoden
    ** nederst i denne metoden er useID fordi getID er koblet til logs knappen i gui. 
    ** getID blir kjørt igjenom ved trykk på knappen og useID
    */

    public void getID(int phone)  {
            
        List<Integer> logsId = new ArrayList<Integer>();
        String line;
        String bla = Integer.toString(phone);         
        
        Connection conn = this.connect();
        try {
            BufferedReader in = new BufferedReader(new FileReader(bla+".txt"));
            
            while((line = in.readLine()) != null){
            int lineInt = Integer.parseInt(line);
            String sql = "SELECT INFOreciveID FROM InfoExchange WHERE INFOgiveID ="+lineInt;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
          
            while (rs.next()){
                logsId.add(rs.getInt("INFOreciveID"));
    } 
        if(logsId.size() == 0){
            ErrorMessage error = new ErrorMessage("No matches!");
            System.out.print(error);
        }
          rs.close();
          stmt.close(); 
        }
        in.close();

    }
        catch (SQLException Sex){
            System.out.println("feil" + Sex);
        }
        catch (Exception e){
            System.out.println("feil e" + e);
        }finally {
            if (conn != null) {
              try {
                conn.close(); 
              } catch (SQLException e) {
              }
            }
        }   
        useID(logsId);

    }  
    }
         
            
