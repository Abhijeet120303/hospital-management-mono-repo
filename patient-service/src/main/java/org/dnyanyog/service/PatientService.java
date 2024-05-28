package org.dnyanyog.service;

import org.dnyanyog.dto.AddPatientRequest;
import org.dnyanyog.dto.AddPatientResponse;

public interface PatientService {

	public AddPatientResponse addPatient(AddPatientRequest request);

	public AddPatientResponse searchPatient(Long patientId);
	
	public AddPatientResponse updatePatient(Long patientId,AddPatientRequest request);
	
	public AddPatientResponse deletePatient(Long patientId);



}
