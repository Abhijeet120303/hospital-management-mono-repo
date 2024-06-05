package user_management;

import add_user.AddUser;
import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import dashboard.Dashboard;
import delete_user.DeleteUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import patient_management.PatientManagement;
import serach_update_user.SearchUpdateUser;
import user_login.UserLogin;

public class UserManagementController {

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML private Button addUser;

  @FXML private Button searchUser;

  @FXML private Button deleteUser;

  @FXML private Button editUser;

  @FXML private Button quit;

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
  public void addUser(ActionEvent event) {
    new AddUser().show();
  }

  @FXML
  public void searchUser(ActionEvent event) {
    new SearchUpdateUser().show();
  }

  @FXML
  public void editUser(ActionEvent event) {
    new SearchUpdateUser().show();
  }

  @FXML
  public void deleteUser(ActionEvent event) {
    new DeleteUser().show();
  }

  @FXML
  public void quitUser(ActionEvent event) {
    new Dashboard().show();
  }
}
