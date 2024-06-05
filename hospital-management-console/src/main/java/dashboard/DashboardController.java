package dashboard;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import patient_management.PatientManagement;
import user_login.UserLogin;
import user_management.UserManagement;

public class DashboardController {

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML
  public void dashboardMain(ActionEvent event) {
    new Dashboard().show();
  }

  @FXML
  public void appointmentManagement(ActionEvent event) {
    new AppointmentManagement().show();
  }

  @FXML
  public void logout(ActionEvent event) {

    new UserLogin().show();
  }

  @FXML
  public void patientManagement(ActionEvent event) {

    new PatientManagement().show();
  }

  @FXML
  public void userManagaement(ActionEvent event) {
    new UserManagement().show();
  }

  @FXML
  public void caseManagemenr(ActionEvent event) {
    new CaseManagement().show();
  }
}
