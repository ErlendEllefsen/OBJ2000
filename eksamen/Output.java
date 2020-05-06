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
    
    Output(List<Integer> ageList, List<String> sexList, List<String> interestList1, List<String> interestList2, List<String> interestList3, List<Integer> idList, int phone, List<Integer> ratingList, int amountofMatches, List<String> cityList){
        TableView<Person> table = new TableView<Person>();
        
        ObservableList<Person> data = FXCollections.observableArrayList();
        for (int element = 0; element<ageList.size();element++) {
            if(ratingList.get(element)==0){
                System.out.print("ingen match");
            }
            else{
            data.addAll(FXCollections.observableArrayList(new Person(idList.get(element), ageList.get(element),sexList.get(element), cityList.get(element), interestList1.get(element), interestList2.get(element), interestList3.get(element), ratingList.get(element))));
            }
        }
       
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(25);
        ageCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("age"));

        TableColumn<Person, String> sexCol = new TableColumn<>("Sex");
        sexCol.setMinWidth(50);
        sexCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("sex"));

        TableColumn<Person, String> cityCol = new TableColumn<>("City");
        cityCol.setMinWidth(75);
        cityCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("city"));

        TableColumn<Person, String> interest1Col = new TableColumn<>("Interest 1");
        interest1Col.setMinWidth(92);
        interest1Col.setCellValueFactory(
                new PropertyValueFactory<Person, String>("interest1"));
        
        TableColumn<Person, String> interest2Col = new TableColumn<>("Interest 2");
        interest2Col.setMinWidth(92);
        interest2Col.setCellValueFactory(
                new PropertyValueFactory<Person, String>("interest2"));

        TableColumn<Person, String> interest3Col = new TableColumn<>("Interest 3");
        interest3Col.setMinWidth(92);
        interest3Col.setCellValueFactory(
                new PropertyValueFactory<Person, String>("interest3"));

        TableColumn<Person, Integer> ratingCol = new TableColumn<>("Match Rating");
        ratingCol.setMinWidth(60);
        ratingCol.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("rating"));

        table.setItems(data);
        
        // Kompilatoren klager på neste linje ettersom vi ikke adder alle kolonnene so vi har deklarert, men
        // vi skal ikke vise id kollonnen og andre. (Type Safety)
        table.getColumns().addAll(ageCol, sexCol, cityCol, interest1Col, interest2Col, interest3Col, ratingCol);
        ratingCol.setComparator(ratingCol.getComparator().reversed());
        table.getSortOrder().add(ratingCol);
        table.setFixedCellSize(setRowSize(amountofMatches));
        table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(300));
        
        getChildren().addAll(table);
        setStyle(
        "-fx-background-color: #f2b09f;");
        table.setRowFactory(tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setStyle("-fx-background-color: #f2b09f");
            //Kjører ID searchResult query og henter ID.
            row.setOnMouseClicked(event -> {
                int ID_give = table.getSelectionModel().getSelectedItem().getId();
                Query query = new Query();
                query.searchResult(ID_give, phone);
            });
            return row;
        });

    }
    //Setter høyde på radene. 
    private double setRowSize(int amountofMatches) {
        int rows = 52;
        if(amountofMatches==10)
            rows = 52;
        else
            rows = 25;
        return rows;
    }

    public static class Person {
        private final SimpleIntegerProperty id;
        private final SimpleIntegerProperty age;
        private final SimpleStringProperty sex;
        private final SimpleStringProperty city;
        private final SimpleStringProperty interest1;
        private final SimpleStringProperty interest2;
        private final SimpleStringProperty interest3;
        private final SimpleIntegerProperty rating;
     
        private Person(Integer id, Integer age, String sex, String city, String interest1, String interest2, String interest3, Integer rating) {
            this.id = new SimpleIntegerProperty(id);
            this.age = new SimpleIntegerProperty(age);
            this.sex = new SimpleStringProperty(sex);
            this.city = new SimpleStringProperty(city);
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

        public String getCity() {
            return city.get();
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