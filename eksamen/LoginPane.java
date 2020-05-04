package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class LoginPane extends StackPane {
    LoginPane(){
        Label label = new Label("Login");
        getChildren().add(label);
    }
}