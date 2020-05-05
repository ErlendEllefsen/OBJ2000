package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

class RegisterPane extends StackPane {
    RegisterPane(){

        String[] sex = {"Woman", "Man"};

        Label nameLabel = new Label("Name");
        setMargin(nameLabel, new Insets(0, 200, 300, 0));
        TextField nameInput = new TextField();
        setMargin(nameInput, new Insets(0, 200, 250, 0));
        ComboBox<String> sexCombo = new ComboBox<String>();
        sexCombo.getItems().addAll(sex);
        sexCombo.setEditable(false);  
        setMargin(sexCombo, new Insets(0, 0, 250, 200));
        Label ageLabel = new Label("Age");
        setMargin(ageLabel, new Insets(0, 200, 200, 0));
        TextField ageInput = new TextField();
        setMargin(ageInput, new Insets(0, 200, 150, 0));
        Label cityLabel = new Label("City");
        setMargin(cityLabel, new Insets(0, 0, 200, 0));
        TextField cityInput = new TextField();
        setMargin(cityInput, new Insets(0, 0, 150, 0));
        Label phoneLabel = new Label("Phone Number");
        setMargin(phoneLabel, new Insets(0, 0, 200, 200));
        TextField phoneInput = new TextField();
        setMargin(phoneInput, new Insets(0, 0, 150, 200));
        Label intrest1Label = new Label("Intrest 1");
        setMargin(intrest1Label, new Insets(0, 200, 100, 0));
        ComboBox<String> intrest1Combo = new ComboBox<String>();
        intrest1Combo.getItems().addAll("Tur", "Seiling", "Fiske", "Hest", "Sykle", "Trening", 
                                        "Gaming", "Fotografering", "Klatring", "Matlaging", 
                                        "Strikking/Hekling", "Musikk", "Lesing", "Sjakk", 
                                        "Dans/sang", "Seiling", "Hiking", "Drivhus", "Motorsport", "Sport" );
        setMargin(intrest1Combo, new Insets(0, 200, 50, 0));
        Label intrest2Label = new Label("Intrest 2");
        setMargin(intrest2Label, new Insets(0, 0, 100, 0));
        ComboBox<String> intrest2Combo = new ComboBox<String>();
        intrest2Combo.getItems().addAll("Tur", "Seiling", "Fiske", "Hest", "Sykle", "Trening", 
                                        "Gaming", "Fotografering", "Klatring", "Matlaging", 
                                        "Strikking/Hekling", "Musikk", "Lesing", "Sjakk", 
                                        "Dans/sang", "Seiling", "Hiking", "Drivhus", "Motorsport", "Sport");
        setMargin(intrest2Combo, new Insets(0, 0, 50, 0));
        Label intrest3Label = new Label("Intrest 3");
        setMargin(intrest3Label, new Insets(0, 0, 100, 200));
        ComboBox<String> intrest3Combo = new ComboBox<String>();
        intrest3Combo.getItems().addAll("Tur", "Seiling", "Fiske", "Hest", "Sykle", "Trening", 
                                        "Gaming", "Fotografering", "Klatring", "Matlaging", 
                                        "Strikking/Hekling", "Musikk", "Lesing", "Sjakk", 
                                        "Dans/sang", "Seiling", "Hiking", "Drivhus", "Motorsport", "Sport");
        setMargin(intrest3Combo, new Insets(0, 0, 50, 200));
        Label sexLabel = new Label("Sex");
        setMargin(sexLabel, new Insets(0, 0, 300, 200));

        Button regBtn = new Button("Register");
        setMargin(regBtn, new Insets(200, 0, 0, 0));
        regBtn.setMinWidth(100);
        regBtn.setMinHeight(50);

        getChildren().addAll(nameLabel, nameInput, ageLabel, ageInput, cityLabel, cityInput, phoneLabel, phoneInput, sexCombo,
                             sexLabel, intrest1Label, intrest1Combo, intrest2Label, intrest2Combo, intrest3Label, 
                             intrest3Combo, regBtn);
        nameInput.setPrefWidth(80);
        nameInput.setMaxWidth(80);
        ageInput.setPrefWidth(80);
        ageInput.setMaxWidth(80);
        cityInput.setPrefWidth(80);
        cityInput.setMaxWidth(80);
        phoneInput.setPrefWidth(80);
        phoneInput.setMaxWidth(80);
        sexCombo.setPrefWidth(80);
        sexCombo.setMaxWidth(80);
        intrest1Combo.setPrefWidth(80);
        intrest1Combo.setMaxWidth(80);
        intrest2Combo.setPrefWidth(80);
        intrest2Combo.setMaxWidth(80);
        intrest3Combo.setPrefWidth(80);
        intrest3Combo.setMaxWidth(80);
        setStyle(
            "-fx-background-color: orange;"+ 
            "-fx-font-family: Courier New;"+
            "-fx-font-weight: bold;"+
            "-fx-font-size: 15;"
            );

        regBtn.setOnAction(e -> {
            String name = nameInput.getText();
            String sexV = sexCombo.getValue();
            String ageString = ageInput.getText().trim();
            int age = Integer.parseInt(ageString);
            String city = cityInput.getText();
            String phoneString = phoneInput.getText();
            int phone = Integer.parseInt(phoneString);
            String intrest1 = intrest1Combo.getValue();
            String intrest2 = intrest2Combo.getValue();
            String intrest3 = intrest3Combo.getValue();
            Query query = new Query();
            query.insert(name, age, phone, sexV, intrest1, intrest2, intrest3, city);
            GUI.registerDone(phone);
        });
    }
}