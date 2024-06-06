package search_update_patient;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddPatientRequest;
import dto.AddPatientResponse;
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

public class SearchUpdatePatientController {

  @FXML private Button dashboard;
  @FXML private Button appointments;
  @FXML private Button logout;
  @FXML private Button patient;
  @FXML private Button users;
  @FXML private Button cases;
  @FXML private Button cancel;
  @FXML private Button save;
  @FXML private Button search;

  @FXML private TextField birthDate;
  @FXML private TextField patientNameMarathi;
  @FXML private TextField patientId;
  @FXML private TextField firstExaminationDate;
  @FXML private TextField mobileNumber;
  @FXML private TextField mobileNUmber1;
  @FXML private TextField gender;
  @FXML private TextField address;
  @FXML private Label labelMessage;
  @FXML private Label labelMessage1;

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
    new PatientManagement().show();
  }

  @FXML
  public void serachData(ActionEvent event) {

    String patientid = patientId.getText().trim();
    String mobilenumber = mobileNumber.getText().trim();

    AddPatientResponse response = null;
    try {
      if (!mobilenumber.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8080/api/v1/patient/search/mobileNumber/" + mobilenumber,
                AddPatientResponse.class);

        labelMessage.setText("Patient Data Found Successfully !!");
      } else if (!patientid.isEmpty()) {
        response =
            RestUtil.sendGetRequest(
                "http://localhost:8080/api/v1/patient/search/patientId/" + patientid,
                AddPatientResponse.class);

        labelMessage.setText("Patient Data Found Successfully !!");
      }
      if (response != null && response.getStatus().equals("Success")) {
        patientNameMarathi.setText(response.getPatientNameInMarathi());
        mobileNUmber1.setText(response.getMobileNumber());
        gender.setText(response.getGender());
        firstExaminationDate.setText(response.getFirstExaminationDate());
        birthDate.setText(response.getBirthDate());
        address.setText(response.getAddress());

      } else {
        labelMessage.setText("Patient Data Not Found !!");

        System.out.println("F");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void saveData(ActionEvent event) throws InterruptedException {

    String inputPatientNameMarathi = patientNameMarathi.getText().trim();
    String inputMobileNumber = mobileNUmber1.getText().trim();
    String inputBirthDate = birthDate.getText().trim();
    String inputGender = gender.getText().trim();
    String inputFirstExaminationDate = firstExaminationDate.getText().trim();
    String inputAddress = address.getText().trim();

    if (inputPatientNameMarathi.isEmpty()) {
      labelMessage.setText("Patient name in Marathi is required!");
      return;
    }

    if (!inputMobileNumber.matches("^[0-9]{10}$")) {
      labelMessage.setText("Mobile number must be exactly 10 digits!");
      return;
    }

    if (!(inputGender.equalsIgnoreCase("Male")
        || inputGender.equalsIgnoreCase("Female")
        || inputGender.equalsIgnoreCase("Other"))) {
      labelMessage.setText("Gender must be Male, Female, or Other!");
      return;
    }

    try {
      LocalDate birthDate =
          LocalDate.parse(inputBirthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      if (!birthDate.isBefore(LocalDate.now())) {
        labelMessage.setText("Birth date must be in the past!");
        return;
      }
    } catch (DateTimeParseException e) {
      labelMessage.setText("Invalid birth date format! Use yyyy-MM-dd.");
      return;
    }

    if (inputAddress.isEmpty()) {
      labelMessage.setText("Address is required!");
      return;
    }

    try {
      LocalDate firstExamDate =
          LocalDate.parse(inputFirstExaminationDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    } catch (DateTimeParseException e) {
      labelMessage.setText("Invalid first examination date format! Use yyyy-MM-dd.");
      return;
    }

    AddPatientRequest addPatientRequest = new AddPatientRequest();

    addPatientRequest.setPatientNameInMarathi(patientNameMarathi.getText());
    addPatientRequest.setMobileNumber(mobileNUmber1.getText());
    addPatientRequest.setBirthDate(birthDate.getText());
    addPatientRequest.setGender(gender.getText());
    addPatientRequest.setFirstExaminationDate(firstExaminationDate.getText());
    addPatientRequest.setAddress(address.getText());

    try {
      String updateUrl = "http://localhost:8080/api/v1/patient/edit/" + patientId.getText();

      AddPatientResponse response =
          RestUtil.sendPostRequest(updateUrl, AddPatientResponse.class, addPatientRequest);

      if (response.getStatus().equals("Success")) {
        System.out.println("S");
        labelMessage.setText("Patient Data Successfully Updated !!");
      } else {
        labelMessage.setText("Patient Data Not Found !!");

        System.out.println("F");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
