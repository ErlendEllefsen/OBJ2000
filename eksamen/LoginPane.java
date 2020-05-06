package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class LoginPane extends StackPane {
    LoginPane(){
        Label label = new Label("Enter your Phone number");
        label.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(label, new Insets(0, 0, 200, 0));
        Button btnLogin = new Button("Login");
        setMargin(btnLogin, new Insets(200, 0, 0, 0));
        TextField name = new TextField();
        name.setPrefWidth(180);
        name.setMaxWidth(180);
        getChildren().addAll(label, btnLogin, name);
        setStyle("-fx-background-color: #f2b09f;" + "-fx-font-family: Courier New;" + "-fx-font-weight: bold;"
                + "-fx-font-size: 20;");
        btnLogin.setOnAction(e -> {
            String phoneString = name.getText();
            int phone = Integer.parseInt(phoneString);
            Query query = new Query();
            try {
                query.login(phone);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
        });
    }
}