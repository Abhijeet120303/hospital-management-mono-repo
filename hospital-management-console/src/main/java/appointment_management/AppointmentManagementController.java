package appointment_management;

import add_appointment.AddAppointment;
import case_management.CaseManagement;
import dashboard.Dashboard;
import delete_appointment.DeleteAppointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import patient_management.PatientManagement;
import serach_appointment.SearchAppointment;
import user_login.UserLogin;
import user_management.UserManagement;

public class AppointmentManagementController {

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML private Button appointmentAdd;

  @FXML private Button appointmentSearch;

  @FXML private Button appointmentDelete;

  @FXML private Button appointmentEdit;

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
  public void addAppointment(ActionEvent event) {
    new AddAppointment().show();
  }

  @FXML
  public void serachAppointment(ActionEvent event) {
    new SearchAppointment().show();
  }

  @FXML
  public void editAppointment(ActionEvent event) {
    new SearchAppointment().show();
  }

  @FXML
  public void deleteAppointment(ActionEvent event) {
    new DeleteAppointment().show();
  }

  @FXML
  public void quiteAppointment(ActionEvent event) {
    new Dashboard().show();
  }
}
