package search_update_case;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddCaseRequest;
import dto.AddCaseResponse;
import dto.AddUserResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_login.UserLogin;
import user_management.UserManagement;

public class SearchUpdateCaseController {

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
  @FXML private TextField caseId;
  @FXML private TextField patientId;

  @FXML private TextField caseNumber;
  @FXML private TextField caseNumber1;

  @FXML private TextField symptoms;
  @FXML private TextField examinationDate;
  @FXML private TextField prescription;
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
    new CaseManagement().show();
  }

  @FXML
  public void serachData(ActionEvent event) throws InterruptedException {

    String casenumber = caseNumber.getText().trim();
    String patientid = caseId.getText().trim();

    AddCaseResponse response = null;
    try {
      if (!casenumber.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8080/api/v1/case/search/caseNumber/" + casenumber,
                AddCaseResponse.class);
        labelMessage.setText("Case Data Successfully Found !!");
      } else if (!patientid.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8080/api/v1/case/search/patientId/" + patientid,
                AddCaseResponse.class);
        labelMessage.setText("Case Data Successfully Found !!");
      }
      if (response != null && response.getStatus().equals("Success")) {
        patientNameEnglish.setText(response.getPatientName());
        patientId.setText(response.getPatientId());
        caseNumber1.setText(response.getCaseNumber());
        symptoms.setText(response.getSymptoms());
        prescription.setText(response.getPrescription());
        examinationDate.setText(response.getExaminationDate());

      } else {
        labelMessage.setText("Case Data Not Found !!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void saveData(ActionEvent event) throws InterruptedException {

    String inputPatientNameEnglish = patientNameEnglish.getText().trim();
    String inputPatientId = patientId.getText().trim();
    String inputSymptoms = symptoms.getText().trim();
    String inputExaminationDate = examinationDate.getText().trim();
    String inputPrescription = prescription.getText().trim();

    if (inputPatientNameEnglish.isEmpty()) {
      labelMessage.setText("Patient name in English is required!");
      return;
    }

    if (inputPatientId.isEmpty()) {
      labelMessage.setText("Patient ID is required!");
      return;
    }

    if (inputExaminationDate.isEmpty()) {
      labelMessage.setText("Examination date is required!");
      return;
    }

    try {
      LocalDate.parse(inputExaminationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    } catch (DateTimeParseException e) {
      labelMessage.setText("Invalid examination date format! Use yyyy-MM-dd.");
      return;
    }

    if (inputSymptoms.isEmpty()) {
      labelMessage.setText("Symptoms are required!");
      return;
    }

    if (inputPrescription.isEmpty()) {
      labelMessage.setText("Prescription is required!");
      return;
    }

    AddCaseRequest table = new AddCaseRequest();

    table.setPatientName(patientNameEnglish.getText());
    table.setPatientId(patientId.getText());
    table.setExaminationDate(examinationDate.getText());
    table.setSymptoms(symptoms.getText());
    table.setPrescription(prescription.getText());

    try {
      String updateUrl = "http://localhost:8080/api/v1/case/edit/" + caseNumber.getText();

      AddUserResponse response = RestUtil.sendPostRequest(updateUrl, AddUserResponse.class, table);

      if (response.getStatus().equals("Success")) {
        System.out.println("S");
        labelMessage.setText("Case Data Successfully Updated !!");

      } else {
        labelMessage.setText("Case Data Not Updated !!");

        System.out.println("F");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
