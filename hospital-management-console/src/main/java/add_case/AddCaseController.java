package add_case;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddCaseRequest;
import dto.AddCaseResponse;
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
