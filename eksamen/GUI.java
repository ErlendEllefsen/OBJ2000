package eksamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
 
public class GUI extends Application {
   // private static String url = "jdbc:sqlite:./db/DateMe.db";
   // private static Connection conn = null;

    public static void main(String[] args) {
      //  kobleOpp();
      //  kobleNed();
        launch(args);    
    }

    private static BorderPane pane;
    static String title = "DateMe";
    static String info = "DateMe. For You By Us.";

    @Override
    public void start(Stage primaryStage) {
        pane = new BorderPane();
        pane.setTop(new HeaderPane(title, 50, 50));
        pane.setCenter(new OptionsHeaderPane("Register", "Login"));
        pane.setBottom(new FooterHeaderPane(info));

        Scene scene = new Scene(pane, 700, 700);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
  /*  private static void kobleOpp() {  
    try {
        System.out.println("KOBLET OPP");
        conn = DriverManager.getConnection(url);
    }
    catch (SQLException e) {
        System.out.println("Oppkobling til databasen" + url + " feilet." + "\n" + e.toString() );
    }
}
    private static void kobleNed() {
    try {
        System.out.println("KOBLET NED");
        conn.close();
    }
    catch (SQLException e) { }
    }
*/
    public static BorderPane registerAction() {
        ((BorderPane) pane).setTop(new HeaderPane(title + " - Register", 50, 50));
        ((BorderPane) pane).setCenter(new RegisterPane());
        ((BorderPane) pane).setBottom(new BackPane("Go Back"));

        return null;
    }

    public static BorderPane loginAction() {
        ((BorderPane) pane).setTop(new HeaderPane(title + " - Login", 50, 50));
        ((BorderPane) pane).setCenter(new LoginPane());
        ((BorderPane) pane).setBottom(new BackPane("Go Back"));
        return null;
    }

    public static BorderPane backAction() {
        ((BorderPane) pane).setTop(new HeaderPane(title, 50, 50));
        ((BorderPane) pane).setCenter(new OptionsHeaderPane("Register", "Login"));
        ((BorderPane) pane).setBottom((new FooterHeaderPane(info)));
        ((BorderPane) pane).setLeft(null);
		return null;
    }
    public static BorderPane registerDone(int phone){
        ((BorderPane) pane).setTop(new HeaderPane(title + " - Home Page", 25, 30));
        ((BorderPane) pane).setLeft(new Menu(phone));
        ((BorderPane) pane).setBottom((new FooterMenuPane()));
        return null;
    }

    public static BorderPane searchAction(String name, String phone, Integer yourID, Integer ID_give, Connection conn) {
        ((BorderPane) pane).setRight(new SearchResult(name, phone, yourID, ID_give, conn));
        return null; 
    }
    public static BorderPane logsAction(List<String> logsName){
        ((BorderPane) pane).setRight((new LogsPane(logsName)));
        return null;
    }
    public static BorderPane matchSearch(List<Integer> ageList, List<String> sexList, List<String> interest1List, List<String> interes2List, List<String> interest3List, List<Integer> idList, int phone, List<Integer> ratingList){
        ((BorderPane) pane).setCenter((new Output(ageList, sexList, interest1List, interes2List, interest3List, idList, phone, ratingList)));
        return null;
    }


    
}