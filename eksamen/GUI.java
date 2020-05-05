package eksamen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
 
public class GUI extends Application {
    private static String url = "jdbc:sqlite:./db/DateMe.db";
    private static Connection conn = null;

    public static void main(String[] args) {
        kobleOpp();
        kobleNed();
        launch(args);
        
    }

    private static BorderPane pane;
    static String title = "DateMe";
    static String info = "Made by Erlend Ellefsen, Robin Ellingsen and Jon Stareng";

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
    private static void kobleOpp() {
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
    public static BorderPane registerDone(){
        ((BorderPane) pane).setTop(new HeaderPane(title + " - Home Page", 25, 30));
        ((BorderPane) pane).setLeft(new Menu());
        ((BorderPane) pane).setCenter(new Output());
        ((BorderPane) pane).setBottom((new FooterMenuPane()));
        return null;
    }

    
}