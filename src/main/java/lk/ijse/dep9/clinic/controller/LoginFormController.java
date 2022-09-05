package lk.ijse.dep9.clinic.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dep9.clinic.security.SecurityContextHolder;
import lk.ijse.dep9.clinic.security.User;
import lk.ijse.dep9.clinic.security.UserRole;

import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

public class LoginFormController {
    public TextField txtUserName;
    public TextField txtPassword;
    public Button btnLogin;

    public void initialize(){
        btnLogin.setDefaultButton(true);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException, IOException {
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
        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_clinic", "root", "Dasun@1996");){
//            String sql = "SELECT role FROM User WHERE username='%s' AND password='%s'";
//            sql = String.format(sql, username, password);
//
//            Statement stm = connection.createStatement();
//            ResultSet rst = stm.executeQuery(sql);
            String sql = "SELECT role FROM User WHERE username=? AND password=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,username);
            stm.setString(2,password);
            ResultSet rst = stm.executeQuery();

            Scene scene = null;
            if(rst.next()){
                String role = rst.getString("role");
                switch (role){
                    case "Admin":
                        System.out.println("gggg");
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/AdminDashBoard.fxml")));
                        break;
                    case "Doctor":
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/DoctorDashBoard.fxml")));
                        break;
                    default:
                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/ReceptionistDashBoard.fxml")));
                }
                Stage stage = new Stage();
                stage.setTitle("Open Source Medical Clinic");
                stage.setScene(scene);
                stage.show();
                stage.centerOnScreen();

                btnLogin.getScene().getWindow().hide();

            }else{
                new Alert(Alert.AlertType.ERROR, "Invalid login credentials").show();
                txtUserName.requestFocus();
                txtUserName.selectAll();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to connect with the database, try again").show();
        }
    }
}


//        try(Connection connection = DriverManager.
//                getConnection("jdbc:mysql://localhost:3306/medical_clinic", "root", "Dasun@1996")){
//            String sql = "SELECT role FROM User WHERE username='%s' AND password='%s'";
//            sql = String.format(sql, username, password);
//
//            Statement stm = connection.createStatement();
//            ResultSet rst = stm.executeQuery(sql);
//



//            if (rst.next()){
//                String role = rst.getString("role");
////                SecurityContextHolder.setPrincipal(new User(username, UserRole.valueOf(role)));
//                Scene scene = null;
//                System.out.println("here");
//                switch (role){
//                    case "Admin":
//                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/AdminDashboard.fxml")));
//                        break;
//                    case "Doctor":
//                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/DoctorDashboard.fxml")));
//                        break;
//                    default:
//                        scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/ReceptionistDashboard.fxml")));
//                }
//                Stage stage = new Stage();
//                stage.setTitle("Open Source Medical Clinic");
//                stage.setScene(scene);
//                stage.show();
//                stage.centerOnScreen();
//
//                btnLogin.getScene().getWindow().hide();
//            }else{
//                new Alert(Alert.AlertType.ERROR, "Invalid login credentials").show();
//                txtUserName.requestFocus();
//                txtUserName.selectAll();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            new Alert(Alert.AlertType.ERROR, "Failed to connect with the database, try again").show();
//        }
//        catch (IOException e) {
//            throw new RuntimeException(e);
//        }

