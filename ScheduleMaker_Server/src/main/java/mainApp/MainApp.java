package mainApp;

import controller.ControllerEmployeeScene;
import controller.ControllerMainWindow;
import controller.ControllerScheduleScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import repo.EmployeeDatabaseRepository;
import service.Service;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private static BorderPane mainLayout;
    private static Service service=new Service(new EmployeeDatabaseRepository("jdbc:mariadb://localhost:3306/ScheduleMaker","root","kalamaska23"));
    @Override
    public void start(Stage primaryStage) throws Exception{

        this.primaryStage=primaryStage;
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //showMainView();
        //showLogoScene();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void showMainView() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/views/mainWindow.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        ControllerMainWindow controllerMainWindow=loader.getController();
        controllerMainWindow.setService(service);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void showLogoScene() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/views/logoStage.fxml"));
        AnchorPane pane=loader.load();
        mainLayout.setCenter(pane);

    }

    public static void showEmployeeStage() throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/views/employeeScene.fxml"));
        AnchorPane pane=loader.load();
        mainLayout.setCenter(pane);
        ControllerEmployeeScene controllerEmployeeScene=loader.getController();
        controllerEmployeeScene.setService(service);
    }
    public static void showScheduleStage() throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/views/scheduleScene.fxml"));
        AnchorPane pane=loader.load();
        mainLayout.setCenter(pane);
        ControllerScheduleScene controllerEmployeeScene=loader.getController();
        controllerEmployeeScene.setService(service);
    }
}
