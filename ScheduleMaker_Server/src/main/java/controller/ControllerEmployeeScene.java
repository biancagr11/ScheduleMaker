package controller;

import domain.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import observer.Observer;
import service.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ControllerEmployeeScene implements Observer {

    private Service service;
    private ObservableList<Employee> employeesModel=FXCollections.observableArrayList();

    @FXML
    TableView<Employee> employeeTableView;
    @FXML
    TableColumn<Employee,String> firstNameColumn;
    @FXML
    TableColumn<Employee,String> lastNameColumn;
    @FXML
    TableColumn<Employee,String> jobColumn;
    @FXML
    TextField searchField;

    public void initialize() {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        jobColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("job"));
        employeeTableView.setItems(employeesModel);

        searchField.textProperty().addListener(x->handleFilter());
        firstNameColumn.getStyleClass().add("foo");
        lastNameColumn.getStyleClass().add("foo");
        jobColumn.getStyleClass().add("foo");
    }

    public void setService(Service service){
        this.service=service;
        employeesModel.setAll(service.findAllEmployes());
    }

    @Override
    public void update() {
        employeesModel.setAll(service.findAllEmployes());
    }

    public void handleFilter() {
        Predicate<Employee> namePredicate= x->((x.getLastName()+" "+x.getFirstName()).startsWith(searchField.getText())
                || (x.getFirstName()+" "+x.getLastName()).startsWith(searchField.getText()) );
       employeesModel.setAll(
               service.findAllEmployes().stream()
                        .filter(namePredicate)
                        .collect(Collectors.toList())
        );

    }

    public void handleDeleteEmployee()
    {
        Employee employee=employeeTableView.getSelectionModel().getSelectedItem();
        if(employee==null) {
            MessageBox.showErrorMessage("No employee was selected!");
        }
        else {
            Optional<ButtonType> result=MessageBox.confirmationMessage("Are you sure that you want to delete "+employee.getLastName()+" "+employee.getFirstName()+"?");
            if(result.isPresent() && result.get()==ButtonType.OK){
                Employee deleteResult=service.deleteEmploye(employee.getId());
                if(deleteResult!=null) {
                    MessageBox.showMessage("Employee deleted!");
                }
                else
                    MessageBox.showErrorMessage("Something is wrong! Could not delete the employee!");
            }

        }
    }

    public void handleAddEmployee() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/views/addEmployeeScene.fxml"));
        AnchorPane root=loader.load();
        ControllerAddEmployeeWindow controllerAddEmployeeWindow =loader.getController();
        controllerAddEmployeeWindow.setService(service);
        service.addObserver(this);
        Stage stage=new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root, 320, 450));
        stage.show();
    }

}
