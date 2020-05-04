package eksamen;

import java.util.Timer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
 
public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String title = "DateMe";
        String info = "Made by Erlend Ellefsen, Robin Ellingsen and Jon Stareng";
        BorderPane pane = new BorderPane();
        OptionsHeaderPane options = new OptionsHeaderPane("Register", "Login");
        pane.setTop(new HeaderPane(title));
        pane.setCenter(options);
        pane.setBottom(new FooterHeaderPane(info));
        Scene scene = new Scene(pane, 700, 700);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();       
    }    
}