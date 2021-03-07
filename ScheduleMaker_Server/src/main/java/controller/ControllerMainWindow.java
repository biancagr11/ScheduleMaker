package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import mainApp.MainApp;
import observer.Observer;
import service.Service;

import java.io.IOException;

public class ControllerMainWindow implements Observer {

    private Service service;
    private double xOffset;
    private double yOffset;


    @FXML
    Button closeIcon;
    @FXML
    Button minusIcon;
    @FXML
    Button circleIcon;

    @FXML
    Button employeeButton;
    @FXML
    HBox barBox;


    @Override
    public void update() {
       //load ceva

    }

    public void initialize(){
    closeIcon.setOnMouseClicked(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
        @Override
        public void handle(javafx.scene.input.MouseEvent mouseEvent) {
            Stage stage=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.close();
        }
    });
    circleIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Stage stage=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            if(stage.isFullScreen())
            {
                stage.setFullScreen(false);
                stage.setHeight(472);
                stage.setWidth(759);
            }
            else
            {
                stage.setFullScreen(true);
            }

        }
    });
    minusIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Stage stage=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setIconified(true);
        }
    });
    barBox.setOnMouseDragged(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Stage stage=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setX(mouseEvent.getScreenX()+xOffset);
            stage.setY(mouseEvent.getScreenY()+yOffset);
        }
    });
    barBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            Stage stage=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            xOffset=stage.getX()-mouseEvent.getScreenX();
            yOffset=stage.getY()-mouseEvent.getScreenY();
        }
    });

    }

    public void setService(Service service){
        this.service=service;
    }

    public void handleEmployeeButton() throws IOException {
       //Main.showEmployeeStage();
    }
    public void handleScheduleButton()throws IOException{
        //mainApp.MainApp.showScheduleStage();
        MainApp.showEmployeeStage();
    }

}
