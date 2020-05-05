package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class Menu extends StackPane {
    Menu(int phone){

        String[] sex = {"Woman", "Man"};

        Label label = new Label("Find your Match");
        label.setTextFill(Color.web("#000000", 0.8));
        setStyle(
        "-fx-font-size: 20;"
        );
        setMargin(label, new Insets(0, 0, 400, 0));
        Label sliderLabel = new Label("Age gap");
        label.setTextFill(Color.web("#000000", 0.8));
        setMargin(sliderLabel, new Insets(0, 0, 325, 0));
        Slider minSlider = new Slider(18,40,1);
        minSlider.setShowTickMarks(true);
        minSlider.setShowTickLabels(true);
        minSlider.setMajorTickUnit(1);
        minSlider.setBlockIncrement(1);
        setMargin(minSlider, new Insets(0, 0, 250, 0));
        Slider maxSlider = new Slider(18,40,1);
        maxSlider.setShowTickMarks(true);
        maxSlider.setShowTickLabels(true);
        maxSlider.setMajorTickUnit(1);
        maxSlider.setBlockIncrement(1);
        setMargin(maxSlider, new Insets(0, 0, 175, 0));
        ComboBox<String> sexCombo = new ComboBox<String>();
        sexCombo.getItems().addAll(sex);
        sexCombo.setEditable(false);
        Button btnSearch = new Button("Search");  
        setMargin(btnSearch, new Insets(100,0,0,0));
        Label sexLabel = new Label("Sex");
        label.setTextFill(Color.web("#000000", 0.8));
        setMargin(sexLabel, new Insets(0, 0, 75, 0));
        Button btnLogs = new Button("Logs");
        setMargin(btnLogs, new Insets(300,0,0,0));
        getChildren().addAll(label, sliderLabel, minSlider, maxSlider, sexCombo, btnSearch, sexLabel, btnLogs);
        setStyle(
        "-fx-background-color: pink;"+ 
        "-fx-font-family: Courier New;"+
        "-fx-font-weight: bold;"+
        "-fx-font-size: 15;"
        );
        setPadding(new Insets(0,10,0,10));

        btnSearch.setOnAction(e->{
            int minAge = (int) minSlider.getValue();
            int maxAge = (int) minSlider.getValue();
            String sexV = sexCombo.getValue();
            System.out.println(minAge + maxAge + sexV);
        });
        btnLogs.setOnAction(e->{
            Query gg = new Query();
            gg.getID(phone);
        });

    }
    
}