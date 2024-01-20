package com.lead.serviceimpl;

import com.lead.dto.Data;
import com.lead.dto.LeadFetchResponse;
import com.lead.dto.LeadRequest;
import com.lead.dto.LeadSaveResponse;
import com.lead.entity.Lead;
import com.lead.exception.ErrorCodes;
import com.lead.exception.LeadAlreadyExistException;
import com.lead.exception.LeadNotFoundException;
import com.lead.exception.LeadServiceException;
import com.lead.repository.LeadRepository;
import com.lead.service.LeadService;
import com.lead.utils.LeadConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeadServiceImpl implements LeadService {

    @Autowired
    private LeadRepository leadRepository;

    @Override
    public LeadSaveResponse createLead(LeadRequest leadRequest) {

        Integer leadId = leadRequest.getLeadId();
        Optional<Lead> lead = leadRepository.findByLeadId(leadId);
        if (lead.isPresent()) {
            throw new LeadAlreadyExistException(ErrorCodes.LEAD_DATA_ALREADY_EXISTS, HttpStatus.ALREADY_REPORTED);
        } else {
            Lead lead1 = new Lead();
            lead1.setLeadId(leadId);
            lead1.setFirstName(leadRequest.getFirstName());
            lead1.setMiddleName(leadRequest.getMiddleName());
            lead1.setLastName(leadRequest.getLastName());
            lead1.setGender(leadRequest.getGender());
            lead1.setEmail(leadRequest.getEmail());
            lead1.setDob(leadRequest.getDob());
            lead1.setMobileNumber(leadRequest.getMobileNumber());

            try {
                leadRepository.save(lead1);
                return saveResponse();
            } catch (Exception e) {
                throw new LeadServiceException(ErrorCodes.FAILED_TO_SAVE_DATA_IN_RDB, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

    }

    private LeadSaveResponse saveResponse() {
        LeadSaveResponse leadSaveResponse = new LeadSaveResponse();
        leadSaveResponse.setStatus(LeadConstants.SUCCESS);
        leadSaveResponse.setData(LeadConstants.MESSAGE);
        return leadSaveResponse;
    }

    @Override
    public LeadFetchResponse fechAllLeads(Long mobileNumber) {
        List<Lead> leadList = Optional.ofNullable(leadRepository.findByMobileNumber(mobileNumber)).get();
        LeadFetchResponse leadFetchResponse = new LeadFetchResponse();
        List<Data> dataList = new ArrayList<>(leadList.size());
        if (!CollectionUtils.isEmpty(leadList)) {
            leadFetchResponse.setStatus(LeadConstants.SUCCESS);
            for (Lead lead : leadList) {
                Data leadData = new Data();
                leadData.setLeadId(lead.getLeadId());
                leadData.setFirstName(lead.getFirstName());
                leadData.setMiddleName(lead.getMiddleName());
                leadData.setLastName(lead.getLastName());
                leadData.setMobileNumber(lead.getMobileNumber());
                leadData.setDob(lead.getDob());
                leadData.setGender(lead.getGender());
                leadData.setEmail(lead.getEmail());
                dataList.add(leadData);
            }
            leadFetchResponse.setData(dataList);
            return leadFetchResponse;
        } else {
            throw new LeadNotFoundException(ErrorCodes.LEAD_DATA_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

    }


}
