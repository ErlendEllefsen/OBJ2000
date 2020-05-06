package eksamen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.Optional;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class SearchResult extends StackPane {
    SearchResult(String name, String phone, Integer yourID, Integer ID_give, Connection conn)
            throws FileNotFoundException {
//Vindu som kommer opp som en "sikkerhet" for brukeren om brukeren trykker feil.
        Alert infoAlert = new Alert(AlertType.CONFIRMATION);
        infoAlert.setTitle("Get information");
        infoAlert.setHeaderText("Get information about user");
        infoAlert.setContentText("Are you sure you want to get this persons info?" + "\n" + "Remember that his person can see this action!");

        Optional<ButtonType> result = infoAlert.showAndWait();
            if (result.get() == ButtonType.OK){
                //Query som sender brukeren sin ID til database for å ha kontroll på hvem som har fått informasjon om hvem. 
                Query sendID = new Query();
                sendID.sendId(yourID, ID_give, conn); 
                {
                    //Vindu som viser informasjonen til den brukeren som ble trykket på i tabellen. 
                    BorderPane bp = new BorderPane();
                    Button xButton = new Button("Remove window");
                    xButton.setTextFill(Color.web("#3b323f", 0.8));
                    Label headerLabel = new Label("User Info");
                    headerLabel.setTextFill(Color.web("#3b323f", 0.8));
                    String infoName = new String (name);
                    String nameToLabel = "Name: " + infoName;

                    String infoPhone = new String (phone);
                    String phoneToLabel = "Phone: " + infoPhone;

                    Label nameLabel = new Label(nameToLabel);
                    nameLabel.setTextFill(Color.web("#3b323f", 0.8));
                    Label phoneLabel = new Label(phoneToLabel);
                    phoneLabel.setTextFill(Color.web("#3b323f", 0.8));

                    FileInputStream imageInput = new FileInputStream("./eksamen/image/pofile.jpg"); 
                    Image image = new Image(imageInput); 
                    ImageView imageView2 = new ImageView(image);
                    imageView2.setFitHeight(150);
                    imageView2.setFitWidth(150);
    
                    FlowPane flowInfo = new FlowPane(Orientation.VERTICAL, 15.0,5.0,headerLabel, nameLabel, phoneLabel, imageView2);
                    flowInfo.setMaxSize(200, 200);
                    
                    
                    xButton.setMinWidth(400);
                    bp.setTop(xButton);
                    bp.setCenter(flowInfo);


                    getChildren().add(bp);  
                    
                    xButton.setOnAction(e ->{
                        getChildren().remove(bp);

                    });
                
                setStyle(
                "-fx-background-color: #de5c6e;" + 
                "-fx-font-family: Courier New;"+
                "-fx-font-weight: bold;"+
                "-fx-font-size: 20;"

            
                );
                }
            }

         else {
            //Gå tilbake til tabellen.
         }
    }
}


