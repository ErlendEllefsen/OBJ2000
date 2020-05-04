package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class BackPane extends StackPane {
    BackPane(String back){
        Button backBtn = new Button(back);
        setMargin(backBtn, new Insets(0, 0, 0, 550));
        backBtn.setMinWidth(100);
        backBtn.setMinHeight(50);
        getChildren().add(backBtn);
        setStyle(
        "-fx-background-color: blue;" + 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 20;"
        );
        setPadding(new Insets(50, 0, 50, 0));
        backBtn.setOnAction(e -> GUI.backAction());
    }
}