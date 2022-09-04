package lk.ijse.dep9.clinic.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginFormController {
    public TextField txtUserName;
    public TextField txtPassword;
    public Button btnLogin;

    public void initialize(){
        btnLogin.setDefaultButton(true);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        if (username.isBlank()){
            new Alert(Alert.AlertType.ERROR, "User name can not be empty").show();
            txtUserName.requestFocus();
            txtUserName.selectAll();
            return;
        } else if (password.isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Password can't be empty").show();
            txtPassword.requestFocus();
            txtUserName.selectAll();
            return;
        } else if (!username.matches("^[A-Za-z0-9]+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid login credentials").show();;
            txtPassword.requestFocus();
            txtPassword.selectAll();
            return;
        }
    }
}
