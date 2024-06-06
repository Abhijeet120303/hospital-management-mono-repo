package delete_user;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddUserResponse;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_login.UserLogin;
import user_management.UserManagement;

public class DeleteUserController {
  @FXML private Button dashboard;
  @FXML private Button appointments;
  @FXML private Button logout;
  @FXML private Button patient;
  @FXML private Button users;
  @FXML private Button cases;
  @FXML private Button cancel;
  @FXML private Button delete;
  @FXML private Button search;

  @FXML private TextField userName;
  @FXML private TextField email;
  @FXML private TextField role;
  @FXML private TextField mobileNumber;
  @FXML private TextField password;
  @FXML private TextField confirmPassword;
  @FXML private TextField userId;
  @FXML private Label labelMesssage;
  @FXML private Label labelMesssage1;

  @FXML
  public void dashboardMain(ActionEvent event) {
    new Dashboard().show();
  }

  @FXML
  public void appointmentManagement(ActionEvent event) {
    new AppointmentManagement().show();
  }

  @FXML
  public void logoutManagement(ActionEvent event) {
    new UserLogin().show();
  }

  @FXML
  public void patientManagement(ActionEvent event) {
    new PatientManagement().show();
  }

  @FXML
  public void userManagement(ActionEvent event) {
    new UserManagement().show();
  }

  @FXML
  public void caseManagement(ActionEvent event) {
    new CaseManagement().show();
  }

  @FXML
  public void cancelData(ActionEvent event) {
    new UserManagement().show();
  }

  @FXML
  public void serachData(ActionEvent event) throws InterruptedException {

    try {
      String searchUrl = "http://localhost:8080/api/v1/directory/search/" + userId.getText();

      AddUserResponse response = RestUtil.sendGetRequest(searchUrl, AddUserResponse.class);

      if (response.getStatus().equals("Fail")) {
        labelMesssage1.setText("User Not Found !!");

        System.out.println("F");
      } else {
        userName.setText(response.getUserName());
        email.setText(response.getEmail());
        mobileNumber.setText(response.getMobileNumber());
        role.setText(response.getRole());
        password.setText(response.getPassword());
        confirmPassword.setText(response.getPassword());

        labelMesssage1.setText("User Data Found Successfully !!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void deleteData(ActionEvent event) {

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this user?");

    java.util.Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      try {
        String deleteUrl = "http://localhost:8080/api/v1/directory/delete/" + userId.getText();

        AddUserResponse status = RestUtil.sendDeleteRequest(deleteUrl, AddUserResponse.class);

        if (status.getStatus().equals("Success")) {
          labelMesssage1.setText("User Deleted Successfully !!");
          System.out.println("DS");
        } else {
          labelMesssage1.setText("Failed to delete user.");
          System.out.println("F");
        }
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        labelMesssage1.setText("An error occurred while deleting the user.");
      }
    } else {
      labelMesssage1.setText("User deletion canceled.");
    }
  }
}
