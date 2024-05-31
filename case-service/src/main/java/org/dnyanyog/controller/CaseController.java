package org.dnyanyog.controller;

import org.dnyanyog.dto.AddCaseRequest;
import org.dnyanyog.dto.AddCaseResponse;
import org.dnyanyog.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class CaseController {

  @Autowired CaseService service;

  @PostMapping(
      path = "/api/v1/case/add",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AddCaseResponse addPatient(@Valid @RequestBody AddCaseRequest request) {
    return service.addCase(request);
  }

  @GetMapping(path = "/api/v1/case/search/caseId/{caseId}")
  public AddCaseResponse searchCase(@PathVariable String caseId) {
    return service.searchCase(caseId);
  }

  @GetMapping(path = "/api/v1/case/search/patientId/{patientId}")
  public AddCaseResponse searchPatientUsingPatientId(@PathVariable String patientId) {
    return service.searchCaseUsingPatientId(patientId);
  }

  @PostMapping(
      path = "/api/v1/case/edit/{caseId}",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AddCaseResponse updateCase(
      @PathVariable String caseId, @RequestBody AddCaseRequest request) {
    return service.updateCase(caseId, request);
  }

  @DeleteMapping(
      path = "/api/v1/case/delete/{caseId}",
      consumes = {"application/json", "application/xml"},
      produces = {"application/json", "application/xml"})
  public AddCaseResponse deleteCase(@PathVariable String caseId) {
    return service.deleteCase(caseId);
  }
}
