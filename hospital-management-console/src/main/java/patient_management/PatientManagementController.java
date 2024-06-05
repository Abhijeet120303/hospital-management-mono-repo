package patient_management;

import add_patient.AddPatient;
import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import dashboard.Dashboard;
import delete_patient.DeletePatient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import search_update_patient.SearchUpdatePatient;
import user_login.UserLogin;
import user_management.UserManagement;

public class PatientManagementController {

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML private Button addPatient;

  @FXML private Button searchPatient;

  @FXML private Button editPatient;

  @FXML private Button deletePatient;

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
  public void addPatients(ActionEvent event) {
    new AddPatient().show();
  }

  @FXML
  public void searchPatients(ActionEvent event) {
    new SearchUpdatePatient().show();
  }

  @FXML
  public void editPatients(ActionEvent event) {
    new SearchUpdatePatient().show();
  }

  @FXML
  public void deletePatients(ActionEvent event) {
    new DeletePatient().show();
  }

  @FXML
  public void quitPatients(ActionEvent event) {
    new Dashboard().show();
  }
}
