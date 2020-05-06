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

class SearchResult extends StackPane {
    SearchResult(String name, String phone, Integer yourID, Integer ID_give, Connection conn)
            throws FileNotFoundException {

        Alert infoAlert = new Alert(AlertType.CONFIRMATION);
        infoAlert.setTitle("Get information");
        infoAlert.setHeaderText("Get information about user");
        infoAlert.setContentText("Are you sure you want to get this persons info?" + "\n" + "Remember that his person can see this action!");

        Optional<ButtonType> result = infoAlert.showAndWait();
            if (result.get() == ButtonType.OK){
                Query sendID = new Query();
                sendID.sendId(yourID, ID_give, conn); 
                {
                    BorderPane bp = new BorderPane();
                    Button xButton = new Button("Fjern vindu");
                    Label headerLabel = new Label("User Info");
                    String infoName = new String (name);
                    String nameToLabel = "Name: " + infoName;

                    String infoPhone = new String (phone);
                    String phoneToLabel = "Phone: " + infoPhone;

                    Label nameLabel = new Label(nameToLabel);
                    Label phoneLabel = new Label(phoneToLabel);

                    FileInputStream imageInput = new FileInputStream("./eksamen/image/pofile.jpg"); 
                    Image image = new Image(imageInput); 
                    ImageView imageView2 = new ImageView(image);
                    imageView2.setFitHeight(150);
                    imageView2.setFitWidth(150);
    
                    FlowPane flowInfo = new FlowPane(Orientation.VERTICAL, 15.0,5.0,headerLabel, nameLabel, phoneLabel, imageView2);
                    flowInfo.setPrefSize(200,200);
                    
                    
                    xButton.setMinWidth(200);
                    bp.setTop(xButton);
                    bp.setCenter(flowInfo);


                    getChildren().add(bp);  
                    
                    xButton.setOnAction(e ->{
                        getChildren().remove(bp);

                    });
                
                setStyle(
                "-fx-background-color: pink;" + 
                "-fx-font-family: Courier New;"+
                "-fx-font-weight: bold;"+
                "-fx-font-size: 20;"

            
                );
                }
            }

         else {
            //Nothing happens, go back.
         }
    }
}


