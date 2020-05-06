package eksamen;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

class LogsPane extends StackPane{
    LogsPane(List<String> logsName, List<Integer> logsPhone){

        String input= "";
        for(int i = 0; i<logsName.size();i++){
            String inputP = Integer.toString(logsPhone.get(i));
            input = input.concat("Navn: " + logsName.get(i)+ "\n" + "Tlfnr: " + inputP + "\n" + "\n");
        }
        //String logsIdString = Integer.toString(logsID);
        Label logsLabel = new Label("Mulig match: " + "\n" + "\n" + input);
        BorderPane bp = new BorderPane();
                    ScrollPane scrollPane = new ScrollPane(logsLabel);
                    scrollPane.setPrefSize(250,250);
        Button xButton = new Button("Remove logs tab");
        xButton.setMinWidth(250);
        bp.setTop(xButton);
        bp.setCenter(scrollPane);
        getChildren().addAll(bp);  
        setStyle(
        "-fx-background-color: pink;" + 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 20;"
        );
        xButton.setOnAction(e ->{
            getChildren().remove(bp);
        });
    }
}