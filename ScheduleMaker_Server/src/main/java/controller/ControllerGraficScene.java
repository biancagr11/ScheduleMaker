package controller;

import domain.Employee;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ControllerGraficScene{

    private Service service;
    private AnchorPane root;
    private Integer days=10;
    private Integer employees=5;

    public void initialize() {
    }

    public void setService(Service service){
        this.service=service;
    }
    public void setRoot(AnchorPane root){
        this.root=root;
        GridPane grid=new GridPane();
        for(int i=0; i<employees;i++){
            List<ComboBox<String>> list=new ArrayList<>();
            for(int j=0;j<days;j++){
                ComboBox<String> comboBox=new ComboBox<String>();
                grid.add(comboBox,i,j);
                list.add(comboBox);
            }
        }
        root.setTopAnchor(grid,1.0);
    }


}
