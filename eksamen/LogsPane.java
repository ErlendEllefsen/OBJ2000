package eksamen;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class LogsPane extends StackPane{
    LogsPane(String logsName){
        //String logsIdString = Integer.toString(logsID);
        Label label = new Label(logsName);
        getChildren().addAll(label);
        setStyle(
        "-fx-background-color: blue;" + 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 20;"
        );
    }
}