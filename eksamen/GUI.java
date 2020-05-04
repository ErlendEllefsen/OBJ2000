package eksamen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
 
public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static BorderPane pane;
    static String title = "DateMe";
    static String info = "Made by Erlend Ellefsen, Robin Ellingsen and Jon Stareng";

    @Override
    public void start(Stage primaryStage) {
        pane = new BorderPane();
        pane.setTop(new HeaderPane(title));
        pane.setCenter(new OptionsHeaderPane("register", "login"));
        pane.setBottom(new FooterHeaderPane(info));

        Scene scene = new Scene(pane, 700, 700);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();

        // boolean test = OptionsHeaderPane.registerPress();

    }

    public static BorderPane registerAction() {
        ((BorderPane) pane).setTop(new HeaderPane(title + " - Register"));
        ((BorderPane) pane).setCenter(new RegisterPane());
        ((BorderPane) pane).setBottom(new BackPane("Go Back"));
        return null;
    }

    public static BorderPane loginAction() {
        ((BorderPane) pane).setTop(new HeaderPane(title + " - Login"));
        ((BorderPane) pane).setCenter(new LoginPane());
        ((BorderPane) pane).setBottom(new BackPane("Go Back"));
        return null;
    }

    public static BorderPane backAction() {
        ((BorderPane) pane).setTop(new HeaderPane(title));
        ((BorderPane) pane).setCenter(new OptionsHeaderPane("register", "login"));
        ((BorderPane) pane).setBottom((new FooterHeaderPane(info)));
		return null;
	}

    
}