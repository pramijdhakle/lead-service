package com.lead.serviceimpl;

import com.lead.dto.*;
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

    /**
     * This is Create Lead method
     *
     * @param leadRequest
     * @return
     */
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

    @Override
    public LeadDataUpdateResponse updateLeadData(LeadUpdateRequest leadRequest, Integer leadId) {
        Optional<Lead> byLeadId = leadRepository.findByLeadId(leadId);
        if (byLeadId.isPresent()) {
            Lead lead = byLeadId.get();
            saveUpdatedLeadData(lead, leadRequest);
            return leadDataUpdatedResponse();
        } else {
            throw new LeadNotFoundException(ErrorCodes.LEAD_DATA_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public List<Data> getAllLeads() {
        List<Data> leadDataList = new ArrayList<>();
        Optional<List<Lead>> allLeads = Optional.ofNullable(leadRepository.findAll());
        if (allLeads.isPresent()) {
            for (Lead lead : allLeads.get()) {
                Data data = new Data();
                data.setLeadId(lead.getLeadId());
                data.setFirstName(lead.getFirstName());
                data.setMiddleName(lead.getMiddleName());
                data.setLastName(lead.getLastName());
                data.setEmail(lead.getEmail());
                data.setGender(lead.getGender());
                data.setDob(lead.getDob());
                data.setMobileNumber(lead.getMobileNumber());
                leadDataList.add(data);
            }
            return leadDataList;
        } else {
            throw new LeadNotFoundException(ErrorCodes.LEAD_DATA_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

    }

    private void saveUpdatedLeadData(Lead lead, LeadUpdateRequest leadRequest) {
        lead.setFirstName(leadRequest.getFirstName() != null ? leadRequest.getFirstName() : lead.getFirstName());
        lead.setMiddleName(leadRequest.getMiddleName() != null ? leadRequest.getMiddleName() : lead.getMiddleName());
        lead.setLastName(leadRequest.getLastName() != null ? leadRequest.getLastName() : lead.getLastName());
        lead.setEmail(leadRequest.getEmail() != null ? leadRequest.getEmail() : lead.getEmail());
        lead.setDob(leadRequest.getDob() != null ? leadRequest.getDob() : lead.getDob());
        lead.setGender(leadRequest.getGender() != null ? leadRequest.getGender() : lead.getGender());
        lead.setMobileNumber(leadRequest.getMobileNumber() != null ? leadRequest.getMobileNumber() : lead.getMobileNumber());
        try {
            leadRepository.save(lead);
        } catch (Exception e) {
            throw new LeadServiceException(ErrorCodes.FAILED_TO_SAVE_DATA_IN_RDB, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    private LeadDataUpdateResponse leadDataUpdatedResponse() {
        LeadDataUpdateResponse response = new LeadDataUpdateResponse();
        response.setStatus(LeadConstants.SUCCESS);
        response.setMessage(LeadConstants.DATA_UPDATED_MESSAGE);
        return response;
    }


}
