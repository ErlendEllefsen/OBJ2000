package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class RegisterPane extends StackPane {
    RegisterPane(String test){
        Label label = new Label(test);
        getChildren().add(label);
    }
}