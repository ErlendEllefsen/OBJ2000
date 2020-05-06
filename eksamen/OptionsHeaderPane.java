package eksamen;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/*
** OptionheaderPane opretter knappene som er på startsiden av applikasjonen
** her blir også styling for knappene gjort
** Action for å fjerne knappene etter den blir brukt også her gjennom en setOnAction
** I GUI.java står hva som skal bli vist i GUI etter at en av knappene blir trykket på
*/
class OptionsHeaderPane extends StackPane {

    public static final String OptionsHeaderPane = null;
	public Object register;
    boolean clicked = false;
    
    public OptionsHeaderPane(String registerText, String loginText){

        Button register = new Button(registerText);
        register.setTextFill(Color.web("#3b323f", 0.8));
        setMargin(register, new Insets(0, 0, 200, 0));
        register.setMinWidth(200);
        register.setMinHeight(100);

        Button login = new Button(loginText);
        login.setTextFill(Color.web("#3b323f", 0.8));
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