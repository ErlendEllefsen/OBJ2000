package eksamen;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
        // Sletter alle tekstfiler
        deleteFile file = new deleteFile();
        System.out.println(file);
    }
    
    private static BorderPane pane;
    static String title = "DateMe";
    static String info = "DateMe. For You By Us.";


    @Override
    public void start(Stage primaryStage) {
        //Lager første Pane
        pane = new BorderPane();
        pane.setTop(new HeaderPane(title, 50, 50));
        pane.setCenter(new OptionsHeaderPane("Register", "Login"));
        pane.setBottom(new FooterHeaderPane(info));
        pane.setStyle("-fx-background-color: #f2b09f;");

        Scene scene = new Scene(pane, 700, 700);
        primaryStage.setResizable(false);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        // Diverse Actions som skjer når feks buttons blir trykket på.
        // Setter nye panes i top, center, bottom, left eller right
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
        ((BorderPane) pane).setRight(null);
        return null;
    }

    public static BorderPane registerDone(int phone) {
        ((BorderPane) pane).setTop(new HeaderPane(title + " - Home Page", 25, 30));
        ((BorderPane) pane).setLeft(new Menu(phone));
        ((BorderPane) pane).setCenter(null);
        ((BorderPane) pane).setBottom((new FooterMenuPane()));
        return null;
    }

    public static BorderPane searchAction(String name, String phone, Integer yourID, Integer ID_give, Connection conn)
            throws FileNotFoundException {
        ((BorderPane) pane).setRight(new SearchResult(name, phone, yourID, ID_give, conn));
        return null; 
    }
    public static BorderPane logsAction(List<String> logsName, List<Integer> logsPhone){
        ((BorderPane) pane).setRight((new LogsPane(logsName, logsPhone)));
        return null;
    }
    public static BorderPane matchSearch(List<Integer> ageList, List<String> sexList, List<String> interest1List, List<String> interes2List, List<String> interest3List, List<Integer> idList, int phone, List<Integer> ratingList, int amountofMatches, List<String> cityList){
        ((BorderPane) pane).setCenter((new Output(ageList, sexList, interest1List, interes2List, interest3List, idList, phone, ratingList, amountofMatches, cityList)));
        return null;
    }


    
}