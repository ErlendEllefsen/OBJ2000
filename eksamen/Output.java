package eksamen;

import java.util.List;

import javafx.beans.binding.Bindings;
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
    
    Output(List<Integer> ageList, List<String> sexList, List<String> interestList1, List<String> interestList2, List<String> interestList3, List<Integer> idList, int phone, List<Integer> ratingList){
        TableView<Person> table = new TableView<Person>();
        
        ObservableList<Person> data = FXCollections.observableArrayList();
        for (int element = 0; element<ageList.size();element++) {
            data.addAll(FXCollections.observableArrayList(new Person(idList.get(element), ageList.get(element),sexList.get(element), interestList1.get(element), interestList2.get(element), interestList3.get(element), ratingList.get(element))));
            if(element==10)
                break;
        }
       
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(50);
        ageCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("age"));

        TableColumn<Person, String> sexCol = new TableColumn<>("Sex");
        sexCol.setMinWidth(75);
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

        TableColumn<Person, Integer> ratingCol = new TableColumn<>("Match Rating");
        ratingCol.setMinWidth(100);
        ratingCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("rating"));

        table.setItems(data);
        
        table.getColumns().addAll(ageCol, sexCol, interest1Col, interest2Col, interest3Col, ratingCol);
        ratingCol.setComparator(ratingCol.getComparator().reversed());
        table.getSortOrder().add(ratingCol);
        table.setFixedCellSize(50);
        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));
        table.maxHeightProperty().bind(table.prefHeightProperty());
        
        getChildren().addAll(table);
        
       
        
        setStyle(
        "-fx-background-color: white;");

        table.setRowFactory(tv -> {

            // Define our new TableRow
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                int ID_give = table.getSelectionModel().getSelectedItem().getId();
                Query query = new Query();
                query.searchResult(ID_give, phone);
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
        private final SimpleIntegerProperty rating;
     
        private Person(Integer id, Integer age, String sex, String interest1, String interest2, String interest3, Integer rating) {
            this.id = new SimpleIntegerProperty(id);
            this.age = new SimpleIntegerProperty(age);
            this.sex = new SimpleStringProperty(sex);
            this.interest1 = new SimpleStringProperty(interest1);
            this.interest2 = new SimpleStringProperty(interest2);
            this.interest3 = new SimpleStringProperty(interest3);
            this.rating = new SimpleIntegerProperty(rating);

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

        public Integer getRating() {
            return rating.get();
        }
            
    }
}