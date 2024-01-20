package com.lead.service;

import com.lead.dto.LeadFetchResponse;
import com.lead.dto.LeadRequest;
import com.lead.dto.LeadSaveResponse;

public interface LeadService {

    LeadSaveResponse createLead(LeadRequest leadRequest);

   LeadFetchResponse fechAllLeads(Long mobileNumber);


}
