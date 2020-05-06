package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class FooterMenuPane extends StackPane {
    FooterMenuPane(){
        Button btnLogOut = new Button("Log Out");
        btnLogOut.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(btnLogOut, new Insets(0,0,0,500));
        getChildren().add(btnLogOut);
        setPadding(new Insets(20));
        setStyle(
        "-fx-background-color: #de5c6e;" +
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 20;"
        );

        btnLogOut.setOnAction(e -> GUI.backAction());
    }
}