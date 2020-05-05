package eksamen;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;

class Output extends StackPane { 
    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person(1, 20,"Male", "Fisking", "Soving", "Sport"),
            new Person(2, 25,"Male", "Fisking", "Soving", "Sport")

        );
    Output(){
        TableColumn<Person, Integer> idCol = new TableColumn<>("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("id"));

        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(100);
        ageCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("age"));

        TableColumn<Person, String> sexCol = new TableColumn<>("Sex");
        sexCol.setMinWidth(100);
        sexCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("sex"));

        TableColumn<Person, String> interest1Col = new TableColumn<>("Interest 1");
        interest1Col.setMinWidth(100);
        interest1Col.setCellValueFactory(
                new PropertyValueFactory<Person, String>("interest1"));
        
        TableColumn<Person, String> interest2Col = new TableColumn<>("Interest 2");
        interest2Col.setMinWidth(100);
        interest2Col.setCellValueFactory(
                new PropertyValueFactory<Person, String>("interest2"));

        TableColumn<Person, String> interest3Col = new TableColumn<>("Interest 3");
        interest3Col.setMinWidth(100);
        interest3Col.setCellValueFactory(
                new PropertyValueFactory<Person, String>("interest3"));

        table.setItems(data);

        table.getColumns().addAll(ageCol, sexCol, interest1Col, interest2Col, interest3Col);

        getChildren().addAll(table);
        
        setStyle(
        "-fx-background-color: white;");

        table.setRowFactory(tv -> {

            // Define our new TableRow
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                int ID_give = table.getSelectionModel().getSelectedItem().getId();
                GUI.searchAction(ID_give);
            });
            return row;
        });

    }
    public static class Person {
        private final SimpleIntegerProperty id;
        private final SimpleIntegerProperty age;
        private final SimpleStringProperty sex;
        private final SimpleStringProperty interest1;
        private final SimpleStringProperty interest2;
        private final SimpleStringProperty interest3;
     
        private Person(Integer id, Integer age, String sex, String interest1, String interest2, String interest3) {
            this.id = new SimpleIntegerProperty(id);
            this.age = new SimpleIntegerProperty(age);
            this.sex = new SimpleStringProperty(sex);
            this.interest1 = new SimpleStringProperty(interest1);
            this.interest2 = new SimpleStringProperty(interest2);
            this.interest3 = new SimpleStringProperty(interest3);

        }
     
        public Integer getId() {
            return id.get();
        }

        public Integer getAge() {
            return age.get();
        }
            
        public String getSex() {
            return sex.get();
        }
        
        public String getInterest1() {
            return interest1.get();
        }

        public String getInterest2() {
            return interest2.get();
        }

        public String getInterest3() {
            return interest3.get();
        }
            
    }
}