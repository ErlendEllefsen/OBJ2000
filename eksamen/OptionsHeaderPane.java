package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

class OptionsHeaderPane extends StackPane {
    public static final String OptionsHeaderPane = null;
	public Object register;
	boolean clicked = false;
    public OptionsHeaderPane(String registerText, String loginText){
        Button register = new Button(registerText);
        setMargin(register, new Insets(0, 0, 200, 0));
        register.setMinWidth(200);
        register.setMinHeight(100);
        Button login = new Button(loginText);
        setMargin(login, new Insets(200, 0, 0, 0));
        login.setMinWidth(200);
        login.setMinHeight(100);
        getChildren().addAll(register, login);
        setStyle(
        "-fx-background-color: red;");

        register.setOnAction(e -> {
            getChildren().removeAll(register, login);
        
        });
        register.setOnAction(e -> GUI.registerAction());
        login.setOnAction(e -> {
            getChildren().removeAll(register, login);
        
        });
        login.setOnAction(e -> GUI.loginAction());
    }
    
}