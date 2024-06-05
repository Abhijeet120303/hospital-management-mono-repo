package add_appointment;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddAppointmentRequest;
import dto.AddAppointmentResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_login.UserLogin;
import user_management.UserManagement;

public class AddAppointmentController {

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML private Button cancel;

  @FXML private Button save;

  @FXML private TextField patientNameEnglish;

  @FXML private TextField patientID;

  @FXML private TextField appointmentId;

  @FXML private TextField appointmentTime;

  @FXML private TextField examinationDate;

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
    new AppointmentManagement().show();
  }

  @FXML
  public void saveData(ActionEvent event) {
    AddAppointmentRequest addAppointmentRequest = new AddAppointmentRequest();

    addAppointmentRequest.setPatientName(patientNameEnglish.getText());
    addAppointmentRequest.setPatientId(patientID.getText());
    addAppointmentRequest.setAppointmentId(appointmentId.getText());
    addAppointmentRequest.setAppointmentTime(appointmentTime.getText());
    addAppointmentRequest.setExaminationDate(examinationDate.getText());

    try {
      AddAppointmentResponse addUserResponse =
          RestUtil.sendPostRequest(
              "http://localhost:8080/api/v1/appointment/add",
              AddAppointmentResponse.class,
              addAppointmentRequest);

      if ("Success".equals(addUserResponse.getStatus())) {
        System.out.println("S");
        labelMessage.setText("Appointment Successfully Added !!");
      } else {
        labelMessage.setText("Input Is Invalid !!");

        System.out.println("F");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
