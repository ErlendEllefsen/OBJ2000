package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
            "-fx-background-color: #f2b09f;"+ 
            "-fx-font-family: Courier New;"+
            "-fx-font-weight: bold;"+
            "-fx-font-size: 20;"
            );
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