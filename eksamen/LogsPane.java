package eksamen;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class LogsPane extends StackPane{
    LogsPane(List<String> logsName, List<Integer> logsPhone){

        String input= "";
        for(int i = 0; i<logsName.size();i++){
            String inputP = Integer.toString(logsPhone.get(i));
            input = input.concat("Navn: " + logsName.get(i)+ "\n" + "Tlfnr: " + inputP + "\n" + "\n");
        }
        //String logsIdString = Integer.toString(logsID);
        Label label = new Label(input);
        getChildren().addAll(label);
        setStyle(
        "-fx-background-color: white;" + 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 20;"
        );
    }
}