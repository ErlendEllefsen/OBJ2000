package eksamen;

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
/*
** Dette er panelet hvor logs blir vist i GUI
** Først pakkes logsName og logsPhone verdiene ut via en for løkke for så å bli gjort om til string verier og plasert i to variabler
** Så blir verdiene vist i GUI ved hjelp av borderpane og scrollpane, også lagt til knapp for å fjerne vinduet
*/
class LogsPane extends StackPane{
    LogsPane(List<String> logsName, List<Integer> logsPhone){

        String input = "";
        String inputP = "";
        for(int i = 0; i<logsName.size();i++){
            inputP = Integer.toString(logsPhone.get(i));
            input = input.concat("Navn: " + logsName.get(i)+ "\n" + "Tlfnr: " + inputP + "\n" + "\n");
        }
        //String logsIdString = Integer.toString(logsID);
        Label logsLabel = new Label("Mulig match: " + "\n" + "\n" + input);
        BorderPane bp = new BorderPane();
                    ScrollPane scrollPane = new ScrollPane(logsLabel);
                    scrollPane.setPrefSize(250,250);
                    //scrollPane.setStyle("-fx-background-color: pink;");
        Button xButton = new Button("Fjern vindu");
        xButton.setMinWidth(250);
        bp.setTop(xButton);
        bp.setCenter(scrollPane);
        getChildren().addAll(bp);  
        scrollPane.setStyle(
            "-fx-background: #de5c6e;" +
            "-fx-background-color: #de5c6e;" 
            );
        logsLabel.setStyle(
        "-fx-font-family: Courier New;" + 
        "-fx-font-weight: bold;"
        + "-fx-font-size: 15;"
        );
        logsLabel.setTextFill(Color.web("#3b323f", 0.8));

        xButton.setOnAction(e ->{
            getChildren().remove(bp);
        });
    }
}