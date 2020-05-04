package eksamen;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;

class Output extends StackPane { 
    Output(){

        TableView<Person> table = new TableView<>();
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
        TableColumn<Person, Double> weightCol = new TableColumn<>("Weight");

        table.getColumns().addAll(firstNameCol, lastNameCol, ageCol, weightCol);

        getChildren().addAll(table);
        
        setStyle(
        "-fx-background-color: white;");
    }
    public class Person{
        private StringProperty firstName = new SimpleStringProperty();
        private StringProperty lastName = new SimpleStringProperty();
        private IntegerProperty age = new SimpleIntegerProperty();
        private DoubleProperty weight = new SimpleDoubleProperty();
   }
}