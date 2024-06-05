package delete_patient;

import add_patient.AddPatient;
import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddPatientResponse;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_login.UserLogin;
import user_management.UserManagement;

public class DeletePatientController {

  @FXML private TextField mobileNUmber;
  @FXML private TextField patientNameMarathi;
  @FXML private TextField birthDate;
  @FXML private TextField gender;
  @FXML private TextField firstExaminationDate;
  @FXML private TextField address;
  @FXML private TextField patientId;

  @FXML private Button dashboard;

  @FXML private Button appointments;

  @FXML private Button logout;

  @FXML private Button patient;

  @FXML private Button users;

  @FXML private Button cases;

  @FXML private Button delete;

  @FXML private Button cancel;

  @FXML private Button search;

  @FXML private Label labelMessage;

  @FXML private Label labelMessage1;

  @FXML
  public void patientManagement(ActionEvent event) {
    new AddPatient().show();
  }

  @FXML
  public void appointmentManagement(ActionEvent event) {
    new AppointmentManagement().show();
  }

  @FXML
  public void caseManagement(ActionEvent event) {
    new CaseManagement().show();
  }

  @FXML
  public void userManagement(ActionEvent event) {
    new UserManagement().show();
  }

  @FXML
  public void dashboardMain(ActionEvent event) {
    new Dashboard().show();
  }

  @FXML
  public void deleteData(ActionEvent event) {
    try {
      String deleteUrl = "http://localhost:8080/api/v1/patient/delete/" + patientId.getText();

      AddPatientResponse status = RestUtil.sendDeleteRequest(deleteUrl, AddPatientResponse.class);

      if (status.getStatus().equals("Success")) {
        System.out.println("DS");
        labelMessage.setText("Patient Data Successfully Deleted");
      } else {

        System.out.println("F");
      }
    } catch (IOException | InterruptedException e) {
    }
  }

  @FXML
  public void cancelData(ActionEvent event) {
    new PatientManagement().show();
  }

  @FXML
  public void serachData(ActionEvent event) throws InterruptedException {

    try {
      String searchUrl =
          "http://localhost:8080/api/v1/patient/search/patientId/" + patientId.getText();

      AddPatientResponse response = RestUtil.sendGetRequest(searchUrl, AddPatientResponse.class);

      if (response.getStatus().equals("Fail")) {
        labelMessage.setText("Patient Data Not Found");

        System.out.println("F");
      } else {
        patientNameMarathi.setText(response.getPatientNameInMarathi());
        mobileNUmber.setText(response.getMobileNumber());
        gender.setText(response.getGender());
        firstExaminationDate.setText(response.getFirstExaminationDate());
        birthDate.setText(response.getBirthDate());
        address.setText(response.getAddress());
        labelMessage.setText("Patient Data Found Successfully");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void logoutManagement(ActionEvent event) {
    new UserLogin().show();
  }
}
