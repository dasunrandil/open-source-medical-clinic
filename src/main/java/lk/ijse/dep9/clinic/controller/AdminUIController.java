package lk.ijse.dep9.clinic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lk.ijse.dep9.clinic.security.SecurityContextHolder;

import java.io.IOException;

public class AdminUIController {

    public Button btnProfileManagement;
    public Button btnViewRecords;
    public Button btnSettings;
    public Button btnLogOut;

    public void initialize (){
        System.out.println(SecurityContextHolder.getPrincipal());
    }

    public void btnProfileManagementOnAction(ActionEvent actionEvent) {

    }

    public void btnViewRecordsOnAction(ActionEvent actionEvent) {
    }

    public void btnSettingsOnAction(ActionEvent actionEvent) {
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/AdminUI.fxml"))));
        stage.setTitle("Open Source MEDICARE");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
        btnLogOut.getScene().getWindow().hide();

    }
}
