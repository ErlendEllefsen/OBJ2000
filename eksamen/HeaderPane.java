package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class HeaderPane extends StackPane {
    public HeaderPane(String title){
        Label label = new Label(title);
        label.setTextFill(Color.web("#ff0000", 0.8));
        getChildren().add(label);
        setStyle(
        "-fx-background-color: blue;" + 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 50;"
        );
        
        setPadding(new Insets(50, 0, 50, 0));
    }
}