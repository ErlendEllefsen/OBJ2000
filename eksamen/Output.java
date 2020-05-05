package eksamen;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

class Output extends StackPane { 
    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );
    Output(){
       

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
        TableColumn<Person, String> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(100);
        ageCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("age"));
        table.setItems(data);

        table.getColumns().addAll(firstNameCol, lastNameCol, ageCol);

        getChildren().addAll(table);
        
        setStyle(
        "-fx-background-color: white;");
    }
    public static class Person {
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty age;
     
        private Person(String fName, String lName, String age) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.age = new SimpleStringProperty(age);
        }
     
        public String getFirstName() {
            return firstName.get();
        }
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
            
        public String getLastName() {
            return lastName.get();
        }
        public void setLastName(String fName) {
            lastName.set(fName);
        }
        
        public String getAge() {
            return age.get();
        }
        public void setEmail(String fName) {
            age.set(fName);
        }
            
    }
}