package user_login;

import common.RestUtil;
import dashboard.Dashboard;
import dto.LoginRequest;
import dto.LoginResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UserLoginController {

  @FXML private TextField mobileNumber;

  @FXML private PasswordField password;

  @FXML private Label labelMessage;

  @FXML private Button next;

  @FXML
  public void next(ActionEvent event) {
    String url = "http://localhost:8080/api/v1/directory/validate";
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setMobileNumber(mobileNumber.getText());
    loginRequest.setPassword(password.getText());

    try {
      LoginResponse loginResponse =
          RestUtil.sendPostRequest(url, LoginResponse.class, loginRequest);

      if ("Success".equals(loginResponse.getStatus())) {

        System.out.println("Login Sucessfully");
        new Dashboard().show();
      } else {

        labelMessage.setText("Invalid Crendentials !!");

        System.out.println("Invalid crendentials");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
