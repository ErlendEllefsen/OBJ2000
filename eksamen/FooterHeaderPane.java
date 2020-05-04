package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class FooterHeaderPane extends StackPane {
    public FooterHeaderPane(String info){
        Label label = new Label(info);
        label.setTextFill(Color.web("#ff0000", 0.8));
        getChildren().add(label);
        setStyle(
        "-fx-background-color: blue;" + 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 20;"
        );
        
        setPadding(new Insets(50, 0, 50, 0));
    }
}