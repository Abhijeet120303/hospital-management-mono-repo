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

    String inputUserName = userName.getText().trim();
    String inputEmail = email.getText().trim();
    String inputMobileNumber = mobileNumber.getText().trim();
    String inputRole = role.getText().trim();
    String inputPassword = password.getText();
    String inputConfirmPassword = confirmPassword.getText();

    String emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    String mobileNumberPattern = "^[0-9]{10}$";
    String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    if (inputUserName.isEmpty()) {
      labelMessage.setText("Username is required!");
      return;
    }

    if (!inputEmail.matches(emailPattern)) {
      labelMessage.setText("Invalid email format!");
      return;
    }

    if (!inputMobileNumber.matches(mobileNumberPattern)) {
      labelMessage.setText("Mobile number must be exactly 10 digits!");
      return;
    }

    if (inputRole.isEmpty()) {
      labelMessage.setText("Role is required!");
      return;
    }

    if (!inputPassword.equals(inputConfirmPassword)) {
      labelMessage.setText("Password and Confirm Password do not match!");
      return;
    }

    if (!inputPassword.matches(passwordPattern)) {
      labelMessage.setText("Password is not strong.");
      return;
    }

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
