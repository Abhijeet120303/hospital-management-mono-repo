package org.dnyanyog.service;

import org.dnyanyog.dto.AddCaseRequest;
import org.dnyanyog.dto.AddCaseResponse;

public interface CaseService {

  public AddCaseResponse addCase(AddCaseRequest request);

  public AddCaseResponse searchCase(String caseId);

  public AddCaseResponse searchCaseUsingPatientId(String patientId);

  public AddCaseResponse updateCase(String caseId, AddCaseRequest request);

  public AddCaseResponse deleteCase(String caseId);
}
