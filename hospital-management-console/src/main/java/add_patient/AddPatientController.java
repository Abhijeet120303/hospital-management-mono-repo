package add_patient;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddPatientRequest;
import dto.AddPatientResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_login.UserLogin;
import user_management.UserManagement;

public class AddPatientController {

  @FXML private TextField patientNameEnglish;
  @FXML private TextField patientNameMarathi;
  @FXML private TextField mobileNumber;
  @FXML private TextField birthDate;
  @FXML private TextField gender;
  @FXML private TextField firstExaminationDate;
  @FXML private TextField address;

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML private Button save;

  @FXML private Button cancel;

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
  public void logout(ActionEvent event) {
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
  public void saveData(ActionEvent event) {

    AddPatientRequest addPatientRequest = new AddPatientRequest();

    addPatientRequest.setPatientNameInEnglish(patientNameEnglish.getText());
    addPatientRequest.setPatientNameInMarathi(patientNameMarathi.getText());
    addPatientRequest.setMobileNumber(mobileNumber.getText());
    addPatientRequest.setBirthDate(birthDate.getText());
    addPatientRequest.setGender(gender.getText());
    addPatientRequest.setFirstExaminationDate(firstExaminationDate.getText());
    addPatientRequest.setAddress(address.getText());

    try {
      AddPatientResponse addPatientResponse =
          RestUtil.sendPostRequest(
              "http://localhost:8080/api/v1/patient/add",
              AddPatientResponse.class,
              addPatientRequest);

      if ("Success".equals(addPatientResponse.getStatus())) {
        System.out.println("S");
        labelMessage.setText("Patient Add Successfully !!");
      } else {
        labelMessage.setText("Input Is Invalid !!");

        System.out.println("F");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
