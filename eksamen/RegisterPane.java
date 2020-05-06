package eksamen;


import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class RegisterPane extends StackPane {
    //Registrerings side for applikasjonen
    RegisterPane(){

        String[] sex = {"Woman", "Man"};

        Label nameLabel = new Label("Name");
        nameLabel.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(nameLabel, new Insets(0, 245, 300, 0));
        TextField nameInput = new TextField();
        setMargin(nameInput, new Insets(0, 245, 250, 0));
        ComboBox<String> sexCombo = new ComboBox<String>();
        sexCombo.getItems().addAll(sex);
        sexCombo.setEditable(false);  
        setMargin(sexCombo, new Insets(0, 0, 250, 245));
        Label ageLabel = new Label("Age");
        ageLabel.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(ageLabel, new Insets(0, 245, 200, 0));
        TextField ageInput = new TextField();
        setMargin(ageInput, new Insets(0, 245, 150, 0));
        Label cityLabel = new Label("City");
        cityLabel.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(cityLabel, new Insets(0, 0, 200, 0));
        TextField cityInput = new TextField();
        setMargin(cityInput, new Insets(0, 0, 150, 0));
        Label phoneLabel = new Label("Phone Number");
        phoneLabel.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(phoneLabel, new Insets(0, 0, 200, 245));
        TextField phoneInput = new TextField();
        setMargin(phoneInput, new Insets(0, 0, 150, 245));
        Label intrest1Label = new Label("Intrest 1");
        intrest1Label.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(intrest1Label, new Insets(0, 245, 100, 0));
        ComboBox<String> intrest1Combo = new ComboBox<String>();
        intrest1Combo.getItems().addAll("Tur", "Seiling", "Fiske", "Hest", "Sykle", "Trening", 
                                        "Gaming", "Fotografering", "Klatring", "Matlaging", 
                                        "Strikking/Hekling", "Musikk", "Lesing", "Sjakk", 
                                        "Dans/sang", "Seiling", "Hiking", "Drivhus", "Motorsport", "Sport" );
        setMargin(intrest1Combo, new Insets(0, 245, 50, 0));
        Label intrest2Label = new Label("Intrest 2");
        intrest2Label.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(intrest2Label, new Insets(0, 0, 100, 0));
        ComboBox<String> intrest2Combo = new ComboBox<String>();
        intrest2Combo.getItems().addAll("Tur", "Seiling", "Fiske", "Hest", "Sykle", "Trening", 
                                        "Gaming", "Fotografering", "Klatring", "Matlaging", 
                                        "Strikking/Hekling", "Musikk", "Lesing", "Sjakk", 
                                        "Dans/sang", "Seiling", "Hiking", "Drivhus", "Motorsport", "Sport");
        setMargin(intrest2Combo, new Insets(0, 0, 50, 0));
        Label intrest3Label = new Label("Intrest 3");
        intrest3Label.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(intrest3Label, new Insets(0, 0, 100, 245));
        ComboBox<String> intrest3Combo = new ComboBox<String>();
        intrest3Combo.getItems().addAll("Tur", "Seiling", "Fiske", "Hest", "Sykle", "Trening", 
                                        "Gaming", "Fotografering", "Klatring", "Matlaging", 
                                        "Strikking/Hekling", "Musikk", "Lesing", "Sjakk", 
                                        "Dans/sang", "Seiling", "Hiking", "Drivhus", "Motorsport", "Sport");
        setMargin(intrest3Combo, new Insets(0, 0, 50, 245));
        Label sexLabel = new Label("Sex");
        sexLabel.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(sexLabel, new Insets(0, 0, 300, 245));


        Button regBtn = new Button("Register");
        regBtn.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(regBtn, new Insets(200, 0, 0, 0));
        regBtn.setMinWidth(100);
        regBtn.setMinHeight(50);

        getChildren().addAll(nameLabel, nameInput, ageLabel, ageInput, cityLabel, cityInput, phoneLabel, phoneInput, sexCombo,
                             sexLabel, intrest1Label, intrest1Combo, intrest2Label, intrest2Combo, intrest3Label, 
                             intrest3Combo, regBtn);
        nameInput.setPrefWidth(120);
        nameInput.setMaxWidth(120);
        ageInput.setPrefWidth(120);
        ageInput.setMaxWidth(120);
        cityInput.setPrefWidth(120);
        cityInput.setMaxWidth(120);
        phoneInput.setPrefWidth(120);
        phoneInput.setMaxWidth(120);
        sexCombo.setPrefWidth(120);
        sexCombo.setMaxWidth(120);
        intrest1Combo.setPrefWidth(120);
        intrest1Combo.setMaxWidth(120);
        intrest2Combo.setPrefWidth(120);
        intrest2Combo.setMaxWidth(120);
        intrest3Combo.setPrefWidth(120);
        intrest3Combo.setMaxWidth(120);
        setStyle(
            "-fx-background-color: #f2b09f;"+ 
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
            //Sjekker for like interesser
            boolean case1 = intrest1.contentEquals(intrest2);
            boolean case2 = intrest1.contentEquals(intrest3);
            boolean case3 = intrest2.contentEquals(intrest3);
            if(case1 == true || case2 == true || case3 == true){
                //gir denne error meldingen om noen interesser matcher.
                ErrorMessage error = new ErrorMessage("Duplicate interests not allowed!");
            }else{
            Query query = new Query();
            query.insert(name, age, phone, sexV, intrest1, intrest2, intrest3, city);
            }
        });
    }
}