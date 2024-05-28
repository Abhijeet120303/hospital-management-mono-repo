package org.dnyanyog.controller;

import org.dnyanyog.dto.AddPatientRequest;
import org.dnyanyog.dto.AddPatientResponse;
import org.dnyanyog.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class PatientController {

	@Autowired
	PatientService service;

	@PostMapping(path = "/api/v1/patient/add", consumes = { "application/json", "application/xml" }, produces = {
			"application/json", "application/xml" })
	public AddPatientResponse addPatient(@Valid @RequestBody AddPatientRequest request) {
		return service.addPatient(request);
	}

	@GetMapping(path = "/api/v1/patient/search/{patientId}")
	public AddPatientResponse searchPatient(@PathVariable Long patientId) {
		return service.searchPatient(patientId);
	}

	@PostMapping(path = "/api/v1/patient/edit/{patientId}", consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public AddPatientResponse updatePatient(@PathVariable Long patientId, @RequestBody AddPatientRequest request) {
		return service.updatePatient(patientId, request);
	}

	@DeleteMapping(path = "/api/v1/patient/delete/{patientId}", consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public AddPatientResponse deletePatient(@PathVariable Long patientId) {
		return service.deletePatient(patientId);

	}

}
