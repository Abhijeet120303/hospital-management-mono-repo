package org.dnyanyog.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Component
public class AddAppointmentRequest {

  @NotBlank(message = "Patient name cannot be blank")
  @Size(max = 100, message = "Patient name cannot exceed 100 characters")
  private String patientName;

  @NotBlank(message = "patientId cannot be blank")
  private String patientId;

  @NotNull(message = "Examination date cannot be null")
  @PastOrPresent(message = "Examination date cannot be in the future")
  private LocalDate examinationDate;

  @NotBlank(message = "Appointment Time cannot be blank")
  public String appointmentTime;

  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getPatientId() {
    return patientId;
  }

  public void setPatientId(String patientId) {
    this.patientId = patientId;
  }

  public LocalDate getExaminationDate() {
    return examinationDate;
  }

  public void setExaminationDate(LocalDate examinationDate) {
    this.examinationDate = examinationDate;
  }

  public String getAppointmentTime() {
    return appointmentTime;
  }

  public void setAppointmentTime(String appointmentTime) {
    this.appointmentTime = appointmentTime;
  }
}
