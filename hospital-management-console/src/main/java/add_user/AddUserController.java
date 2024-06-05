package add_user;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddUserRequest;
import dto.AddUserResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_login.UserLogin;
import user_management.UserManagement;

public class AddUserController {

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML private Button cancel;

  @FXML private Button save;

  @FXML private TextField userName;

  @FXML private TextField email;

  @FXML private TextField mobileNumber;

  @FXML private TextField role;

  @FXML private TextField password;

  @FXML private TextField confirmPassword;

  @FXML private Label labelMessage;

  @FXML
  public void dashboard(ActionEvent event) {
    new Dashboard().show();
  }

  @FXML
  public void appointments(ActionEvent event) {
    new AppointmentManagement().show();
  }

  @FXML
  public void logout(ActionEvent event) {
    new UserLogin().show();
  }

  @FXML
  public void Patient(ActionEvent event) {
    new PatientManagement().show();
  }

  @FXML
  public void user(ActionEvent event) {
    new UserManagement().show();
  }

  @FXML
  public void cases(ActionEvent event) {
    new CaseManagement().show();
  }

  @FXML
  public void cancelData(ActionEvent event) {
    new UserManagement().show();
  }

  @FXML
  public void saveData(ActionEvent event) {
    AddUserRequest addUserRequest = new AddUserRequest();
    addUserRequest.setUserName(userName.getText());
    addUserRequest.setEmail(email.getText());
    addUserRequest.setMobileNumber(mobileNumber.getText());
    addUserRequest.setRole(role.getText());
    addUserRequest.setPassword(password.getText());
    addUserRequest.setConfirmPassword(confirmPassword.getText());

    try {
      AddUserResponse addUserResponse =
          RestUtil.sendPostRequest(
              "http://localhost:8080/api/v1/directory/add", AddUserResponse.class, addUserRequest);

      if ("Success".equals(addUserResponse.getStatus())) {
        labelMessage.setText("User added successfully !!");

        System.out.println("S");
      } else {
        labelMessage.setText("Input Is Invalid !!");

        System.out.println("F");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
