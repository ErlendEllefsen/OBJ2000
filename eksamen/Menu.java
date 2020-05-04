package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class Menu extends StackPane {
    Menu(){
        Label label = new Label("Find your Match");
        label.setTextFill(Color.web("#ff0000", 0.8));
        setMargin(label, new Insets(0, 0, 400, 0));
        getChildren().addAll(label);
        setStyle(
        "-fx-background-color: black;");
        setPadding(new Insets(0,100,0,0));
    }
    
}