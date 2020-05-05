package eksamen;

import java.sql.Connection;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

class LoginPane extends StackPane {
    LoginPane(){
        Label label = new Label("Whats your name?");
        setMargin(label, new Insets(0, 0, 200, 0));
        Button btnLogin = new Button("Login");
        setMargin(btnLogin, new Insets(200, 0, 0, 0));
        TextField name = new TextField();
        name.setPrefWidth(180);
        name.setMaxWidth(180);
        getChildren().addAll(label, btnLogin, name);
        setStyle(
        "-fx-background-color: pink;"+ 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 20;"
        );
        btnLogin.setOnAction(e -> {
            InsertTest test = new InsertTest();
        // insert three new rows
            test.insert("Erlend");
            test.insert("Robin");
            test.insert("Jon");
        });
    }
}