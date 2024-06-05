package delete_appointment;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddAppointmentResponse;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_login.UserLogin;
import user_management.UserManagement;

public class DeleteAppointmentController {

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML private Button cancel;

  @FXML private Button delete;

  @FXML private Button search;

  @FXML private TextField patientNameEnglish;

  @FXML private TextField patientId;

  @FXML private TextField appointmentId;

  @FXML private TextField appointmentId1;

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
  public void searchData(ActionEvent event) throws InterruptedException {
    try {
      String searchUrl =
          "http://localhost:8080/api/v1/appointment/search/appointmentId/"
              + appointmentId.getText();

      AddAppointmentResponse response =
          RestUtil.sendGetRequest(searchUrl, AddAppointmentResponse.class);

      if (response.getStatus().equals("Fail")) {
        System.out.println("F");
        labelMessage.setText("Appointment Data Not Found !!");

      } else {
        patientNameEnglish.setText(response.getPatientName());
        patientId.setText(response.getPatientId());
        appointmentId1.setText(response.getAppointmentId());
        appointmentTime.setText(response.getAppointmentTime());
        examinationDate.setText(response.getExaminationDate());

        labelMessage.setText("Appointment Data Successfully Found !!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void deleteData(ActionEvent event) {
    try {
      String deleteUrl =
          "http://localhost:8080/api/v1/appointment/delete/" + appointmentId.getText();

      AddAppointmentResponse status =
          RestUtil.sendDeleteRequest(deleteUrl, AddAppointmentResponse.class);

      if (status.getStatus().equals("Success")) {
        System.out.println("DS");
        labelMessage.setText("Appointment Data Successfully Deleted !!");

      } else {
        System.out.println("F");
      }
    } catch (IOException | InterruptedException e) {
    }
  }
}
