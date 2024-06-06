package delete_case;

import appointment_management.AppointmentManagement;
import case_management.CaseManagement;
import common.RestUtil;
import dashboard.Dashboard;
import dto.AddCaseResponse;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import patient_management.PatientManagement;
import user_login.UserLogin;
import user_management.UserManagement;

public class DeleteCaseController {

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

    try {
      String searchUrl =
          "http://localhost:8080/api/v1/case/search/caseNumber/" + caseNumber.getText();

      AddCaseResponse response = RestUtil.sendGetRequest(searchUrl, AddCaseResponse.class);

      if (response.getStatus().equals("Fail")) {
        System.out.println("F");
        labelMessage.setText("Case Data Not Found !!");

      } else {
        patientNameEnglish.setText(response.getPatientName());
        patientId.setText(response.getPatientId());
        caseNumber1.setText(response.getCaseNumber());
        symptoms.setText(response.getSymptoms());
        prescription.setText(response.getPrescription());
        examinationDate.setText(response.getExaminationDate());
        labelMessage.setText("Case Data Successfully Found !!");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void deleteData(ActionEvent event) {

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this case?");

    java.util.Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
      try {
        String deleteUrl = "http://localhost:8080/api/v1/case/delete/" + caseNumber.getText();

        AddCaseResponse status = RestUtil.sendDeleteRequest(deleteUrl, AddCaseResponse.class);

        if (status.getStatus().equals("Success")) {
          System.out.println("DS");
          labelMessage.setText("Case Data Delete Successfully !!");
        } else {
          labelMessage.setText("Failed to delete case data.");
          System.out.println("F");
        }
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        labelMessage.setText("An error occurred while deleting case data.");
      }
    } else {
      labelMessage.setText("Case deletion canceled.");
    }
  }
}
