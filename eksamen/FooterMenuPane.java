package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

class FooterMenuPane extends StackPane {
    FooterMenuPane(){
        Button btnLogOut = new Button("Log Out");
        setMargin(btnLogOut, new Insets(0,0,0,500));
        getChildren().add(btnLogOut);
        setPadding(new Insets(20));
        setStyle(
        "-fx-background-color: blue;");

        btnLogOut.setOnAction(e -> GUI.backAction());
    }
}