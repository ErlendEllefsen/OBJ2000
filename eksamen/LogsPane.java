package eksamen;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class LogsPane extends StackPane{
    LogsPane(List<String> logsName){

        String input= "";
        for(int i = 0; i<logsName.size();i++){
            input = input.concat(logsName.get(i));
        }
        //String logsIdString = Integer.toString(logsID);
        Label label = new Label(input);
        getChildren().addAll(label);
        setStyle(
        "-fx-background-color: blue;" + 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 20;"
        );
    }
}