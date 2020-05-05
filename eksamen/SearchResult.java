package eksamen;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;

class SearchResult extends StackPane {
    SearchResult(String name, String phone){

        Alert infoAlert = new Alert(AlertType.CONFIRMATION);
        infoAlert.setTitle("Get information");
        infoAlert.setHeaderText("Get information about user");
        infoAlert.setContentText("Are you sure you want to get this persons info?" + "\n" + "Remember that his person can see this action!");

        Optional<ButtonType> result = infoAlert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }
}

