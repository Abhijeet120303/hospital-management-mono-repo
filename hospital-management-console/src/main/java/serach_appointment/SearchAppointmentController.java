package serach_appointment;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddAppointmentRequest;
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

public class SearchAppointmentController {

  @FXML private Button dashboard;
  @FXML private Button appointments;
  @FXML private Button logout;
  @FXML private Button patient;
  @FXML private Button users;
  @FXML private Button cases;
  @FXML private Button cancel;
  @FXML private Button save;
  @FXML private Button search;

  @FXML private TextField patientNameEnglish;
  @FXML private TextField patientId;
  @FXML private TextField patientId1;
  @FXML private TextField appointmentId;
  @FXML private TextField appointmentId1;
  @FXML private TextField appointmentTime;
  @FXML private TextField examinationDate;
  @FXML private Label labelMessage;

  @FXML
  public void dashboardMain(ActionEvent event) {
    new Dashboard().show();
  }

  @FXML
  public void appointmentManagement(ActionEvent event) {
    new AppointmentManagement().show();
  }

  @FXML
  public void logoutManagement(ActionEvent event) {
    new UserLogin().show();
  }

  @FXML
  public void patientManagement(ActionEvent event) {
    new PatientManagement().show();
  }

  @FXML
  public void userManagement(ActionEvent event) {
    new UserManagement().show();
  }

  @FXML
  public void caseManagement(ActionEvent event) {
    new CaseManagement().show();
  }

  @FXML
  public void cancelData(ActionEvent event) {
    new AppointmentManagement().show();
  }

  @FXML
  public void serachData(ActionEvent event) {
    String patientid = patientId.getText().trim();
    String appointmentid = appointmentId.getText().trim();

    AddAppointmentResponse response = null;
    try {
      if (!appointmentid.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8080/api/v1/appointment/search/appointmentId/" + appointmentid,
                AddAppointmentResponse.class);
      } else if (!patientid.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8080/api/v1/appointment/search/patientId/" + patientid,
                AddAppointmentResponse.class);
      }
      if (response != null && response.getStatus().equals("Success")) {
        patientNameEnglish.setText(response.getPatientName());
        patientId1.setText(response.getPatientId());
        appointmentId1.setText(response.getAppointmentId());
        appointmentTime.setText(response.getAppointmentTime());
        examinationDate.setText(response.getExaminationDate());
        labelMessage.setText("Appointment Data Successfully Found !!");

      } else {
        labelMessage.setText("Appointment Data Not Found !!");

        System.out.println("F");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void saveData(ActionEvent event) throws InterruptedException {

    AddAppointmentRequest table = new AddAppointmentRequest();

    table.setPatientName(patientNameEnglish.getText());
    table.setPatientId(patientId1.getText());
    table.setAppointmentTime(appointmentTime.getText());
    table.setAppointmentId(appointmentId1.getText());
    table.setExaminationDate(examinationDate.getText());

    try {
      String updateUrl = "http://localhost:8080/api/v1/appointment/edit/" + appointmentId.getText();

      AddAppointmentResponse response =
          RestUtil.sendPostRequest(updateUrl, AddAppointmentResponse.class, table);

      if (response.getStatus().equals("Success")) {
        System.out.println("S");
        labelMessage.setText("Appointment Data Successfully Updated !!");

      } else {
        System.out.println("F");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
