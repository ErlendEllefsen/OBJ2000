package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class BackPane extends StackPane {
    BackPane(String back){
        Button backBtn = new Button(back);
        backBtn.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(backBtn, new Insets(0, 0, 0, 550));
        backBtn.setMinWidth(100);
        backBtn.setMinHeight(50);
        getChildren().add(backBtn);
        setStyle(
        "-fx-background-color: #de5c6e;" + 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 20;"
        );
        setPadding(new Insets(50, 0, 50, 0));
        backBtn.setOnAction(e -> GUI.backAction());
    }
}