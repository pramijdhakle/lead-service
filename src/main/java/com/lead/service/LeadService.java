package com.lead.service;

import com.lead.dto.*;

import java.util.List;

public interface LeadService {

    LeadSaveResponse createLead(LeadRequest leadRequest);

   LeadFetchResponse fechAllLeads(Long mobileNumber);

    LeadDataUpdateResponse updateLeadData(LeadUpdateRequest leadRequest , Integer leadId);

    List<Data> getAllLeads();

}
