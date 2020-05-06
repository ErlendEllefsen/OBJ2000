package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/*
** FooterheaderPane oppretter panelet som ligger i bunnen av boderpane på første side av applikasjonen
** enkel label og styling av den.
*/
class FooterHeaderPane extends StackPane {
    public FooterHeaderPane(String info){
        Label label = new Label(info);
        label.setTextFill(Color.web("#3b323f", 0.8));
        getChildren().add(label);
        setStyle(
        "-fx-background-color: #de5c6e;" + 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 20;"
        );
        
        setPadding(new Insets(50, 0, 50, 0));
    }
}