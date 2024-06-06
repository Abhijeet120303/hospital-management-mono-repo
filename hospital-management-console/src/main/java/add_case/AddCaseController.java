package add_case;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddCaseRequest;
import dto.AddCaseResponse;
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

public class AddCaseController {

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML private Button cancel;

  @FXML private Button save;

  @FXML private TextField patientNameEnglish;

  @FXML private TextField patientId;

  @FXML private TextField caseNumber;

  @FXML private TextField symptoms;

  @FXML private TextField examinationDate;

  @FXML private TextField prescription;

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
    new CaseManagement().show();
  }

  @FXML
  public void saveData(ActionEvent event) {

    String inputPatientNameEnglish = patientNameEnglish.getText().trim();
    String inputPatientId = patientId.getText().trim();
    String inputCaseNumber = caseNumber.getText().trim();
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

    if (inputCaseNumber.isEmpty() || !inputCaseNumber.matches("^[0-9]+$")) {
      labelMessage.setText("Case number must be a numeric value and is required!");
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

    AddCaseRequest addCaseRequest = new AddCaseRequest();
    addCaseRequest.setPatientName(patientNameEnglish.getText());
    addCaseRequest.setPatientId(patientId.getText());
    addCaseRequest.setCaseNumber(caseNumber.getText());
    addCaseRequest.setExaminationDate(examinationDate.getText());
    addCaseRequest.setSymptoms(symptoms.getText());
    addCaseRequest.setPrescription(prescription.getText());

    try {
      AddCaseResponse addUserResponse =
          RestUtil.sendPostRequest(
              "http://localhost:8080/api/v1/case/add", AddCaseResponse.class, addCaseRequest);

      if ("Success".equals(addUserResponse.getStatus())) {
        System.out.println("S");
        labelMessage.setText("Case Successfully Added !!");
      } else {
        labelMessage.setText("Input Is Invalid !!");

        System.out.println("F");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
