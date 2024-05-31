package org.dnyanyog.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
@Component
public class Cases {

  @Id
  @Column(name = "case_id", unique = true, nullable = false)
  private String caseId;

  @Column(name = "patient_name", nullable = false)
  private String patientName;

  @Column(name = "patient_id", nullable = false)
  private String patientId;

  @Column(name = "case_number", nullable = false)
  private long caseNumber;

  @Column(name = "examination_date", nullable = false)
  private LocalDate examinationDate;

  @Column(name = "Symptoms", nullable = false)
  private String Symptoms;

  @Column(name = "Prescription", nullable = false)
  private String Prescription;

  @Column(name = "data_status", nullable = false)
  private String dataStatus;

  public static Cases getInstance() {
    return new Cases();
  }

  public String getCaseId() {
    return caseId;
  }

  public String getDataStatus() {
    return dataStatus;
  }

  public Cases setDataStatus(String dataStatus) {
    this.dataStatus = dataStatus;
    return this;
  }

  public Cases setCaseId(String caseId) {
    this.caseId = caseId;
    return this;
  }

  public String getPatientName() {
    return patientName;
  }

  public Cases setPatientName(String patientName) {
    this.patientName = patientName;
    return this;
  }

  public String getPatientId() {
    return patientId;
  }

  public Cases setPatientId(String patientId) {
    this.patientId = patientId;
    return this;
  }

  public long getCaseNumber() {
    return caseNumber;
  }

  public Cases setCaseNumber(long caseNumber) {
    this.caseNumber = caseNumber;
    return this;
  }

  public LocalDate getExaminationDate() {
    return examinationDate;
  }

  public Cases setExaminationDate(LocalDate examinationDate) {
    this.examinationDate = examinationDate;
    return this;
  }

  public String getSymptoms() {
    return Symptoms;
  }

  public Cases setSymptoms(String symptoms) {
    Symptoms = symptoms;
    return this;
  }

  public String getPrescription() {
    return Prescription;
  }

  public Cases setPrescription(String prescription) {
    Prescription = prescription;
    return this;
  }
}
