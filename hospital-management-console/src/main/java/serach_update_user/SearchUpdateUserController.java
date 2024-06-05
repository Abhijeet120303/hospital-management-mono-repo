package serach_update_user;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddUserRequest;
import dto.AddUserResponse;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_login.UserLogin;
import user_management.UserManagement;

public class SearchUpdateUserController {

  @FXML private Button dashboard;
  @FXML private Button appointments;
  @FXML private Button logout;
  @FXML private Button patient;
  @FXML private Button users;
  @FXML private Button cases;
  @FXML private Button cancel;
  @FXML private Button save;
  @FXML private Button search;

  @FXML private TextField userName;
  @FXML private TextField email;
  @FXML private TextField role;
  @FXML private TextField mobileNumber;
  @FXML private TextField password;
  @FXML private TextField confirmPassword;
  @FXML private TextField userId;
  @FXML private Label labelMessage;

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
        System.out.println("F");
        labelMessage.setText("User Data Not Found !!");

      } else {
        userName.setText(response.getUserName());
        email.setText(response.getEmail());
        mobileNumber.setText(response.getMobileNumber());
        role.setText(response.getRole());
        password.setText(response.getPassword());

        confirmPassword.setText(response.getPassword());

        labelMessage.setText("User Data Successfully Found !!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void saveData(ActionEvent event) throws InterruptedException {

    AddUserRequest table = new AddUserRequest();

    table.setUserName(userName.getText());
    table.setEmail(email.getText());
    table.setMobileNumber(mobileNumber.getText());
    table.setRole(role.getText());
    table.setPassword(password.getText());
    table.setConfirmPassword(confirmPassword.getText());

    try {
      System.out.println(userId.getText());
      String updateUrl = "http://localhost:8080/api/v1/directory/edit/" + userId.getText();

      AddUserResponse response = RestUtil.sendPostRequest(updateUrl, AddUserResponse.class, table);

      if (response.getStatus().equals("Fail")) {
        System.out.println("F");
      } else {
        System.out.println("S");
        labelMessage.setText("User Data Successfully Updated !!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
