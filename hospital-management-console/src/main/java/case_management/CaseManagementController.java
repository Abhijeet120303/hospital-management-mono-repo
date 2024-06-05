package case_management;

import add_case.AddCase;
import appointment_management.AppointmentManagement;
import dashboard.Dashboard;
import delete_case.DeleteCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import patient_management.PatientManagement;
import search_update_case.SearchUpdateCase;
import user_login.UserLogin;
import user_management.UserManagement;

public class CaseManagementController {

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML private Button addCase;

  @FXML private Button searchCase;

  @FXML private Button deleteCase;

  @FXML private Button editCase;

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
  public void addCase(ActionEvent event) {
    new AddCase().show();
  }

  @FXML
  public void searchCase(ActionEvent event) {
    new SearchUpdateCase().show();
  }

  @FXML
  public void editCase(ActionEvent event) {
    new SearchUpdateCase().show();
  }

  @FXML
  public void deleteCase(ActionEvent event) {

    new DeleteCase().show();
  }

  @FXML
  public void quit(ActionEvent event) {
    new Dashboard().show();
  }
}
