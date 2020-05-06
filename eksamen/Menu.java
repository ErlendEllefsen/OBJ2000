package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/*
** Menu klassen oppretter alt som er i panelet på venstre siden av hoved siden i applikasjonen
** Det blir også satt action events på btsearch og btw logs.
*/
class Menu extends StackPane {

	Menu(int phone) {

        String[] sex = { "Woman", "Man" };

        Label label = new Label("Find your Match");
        label.setTextFill(Color.web("#3b323f", 0.8));
        setStyle("-fx-font-size: 20;");
        setMargin(label, new Insets(0, 0, 400, 0));

        Label sliderLabel = new Label("Age gap");
        label.setTextFill(Color.web("#3b323f", 0.8));
        sliderLabel.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(sliderLabel, new Insets(0, 0, 325, 0));

        Slider minSlider = new Slider(18, 60, 1);
        minSlider.setShowTickMarks(false);
        minSlider.setShowTickLabels(true);
        minSlider.setMajorTickUnit(5);
        minSlider.setBlockIncrement(1);
        setMargin(minSlider, new Insets(0, 0, 250, 0));

        Slider maxSlider = new Slider(18, 60, 1);
        maxSlider.setShowTickMarks(false);
        maxSlider.setShowTickLabels(true);
        maxSlider.setMajorTickUnit(5);
        maxSlider.setBlockIncrement(1);
        setMargin(maxSlider, new Insets(0, 0, 175, 0));

        ComboBox<String> sexCombo = new ComboBox<String>();
        sexCombo.getItems().addAll(sex);
        sexCombo.setEditable(false);

        Button btnSearch = new Button("Search");
        setMargin(btnSearch, new Insets(200, 0, 0, 0));
        btnSearch.setTextFill(Color.web("#3b323f", 0.8));

        Label sexLabel = new Label("Sex");
        sexLabel.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(sexLabel, new Insets(0, 0, 75, 0));

        Button btnLogs = new Button("Logs");
        btnLogs.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(btnLogs, new Insets(300, 0, 0, 0));

        Label rowsLabel = new Label("Rows");
        rowsLabel.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(rowsLabel, new Insets(50, 0, 0, 0));

        ComboBox<Integer> matchesAmount = new ComboBox<Integer>();
        matchesAmount.getItems().addAll(10,20);
        setMargin(matchesAmount, new Insets(100, 0, 0, 0));

        getChildren().addAll(label, sliderLabel, minSlider, maxSlider, sexCombo, btnSearch, sexLabel, btnLogs, matchesAmount, rowsLabel);
        setStyle(
            "-fx-background-color: #de5c6e;" + 
            "-fx-font-family: Courier New;" + 
            "-fx-font-weight: bold;"
                + "-fx-font-size: 15;");
        setPadding(new Insets(0, 10, 0, 10));

        btnSearch.setOnAction(e -> {
            int minAge = (int) minSlider.getValue();
            int maxAge = (int) maxSlider.getValue();
            System.out.println(minAge+ "   "+maxAge);
            String sexV = sexCombo.getValue();
            int amountofRows = matchesAmount.getValue();
            Query query = new Query();
            
                try {
					query.fillTable(minAge, maxAge, sexV, phone, amountofRows);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
           
        });
        // action for å aktivere getID i Query.java ved trykk på btnLogs
        btnLogs.setOnAction(e -> {
            Query gg = new Query();

                gg.getID(phone);
            
             
        
        });

    }
    
}