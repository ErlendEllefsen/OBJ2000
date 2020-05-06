package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class HeaderPane extends StackPane {
    public HeaderPane(String title, int height, int fontsize){
        Label label = new Label(title);
        label.setTextFill(Color.web("#3b323f", 0.8));
        getChildren().add(label);
        setStyle(
        "-fx-background-color: #de5c6e;" + 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: " + fontsize +";"
        );
        
        setPadding(new Insets(height, 0, height, 0));
    }
}