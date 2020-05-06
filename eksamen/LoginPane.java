package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

class LoginPane extends StackPane {
<<<<<<< HEAD
    LoginPane() {
        Label label = new Label("Whats your name?");
=======
    LoginPane(){
        Label label = new Label("Enter your Phone number");
>>>>>>> 28744e81a55302f569d7fe2674d78f24e577d5f2
        setMargin(label, new Insets(0, 0, 200, 0));
        Button btnLogin = new Button("Login");
        setMargin(btnLogin, new Insets(200, 0, 0, 0));
        TextField name = new TextField();
        name.setPrefWidth(180);
        name.setMaxWidth(180);
        getChildren().addAll(label, btnLogin, name);
        setStyle("-fx-background-color: pink;" + "-fx-font-family: Courier New;" + "-fx-font-weight: bold;"
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
            GUI.registerDone(phone);
        });
    }
}