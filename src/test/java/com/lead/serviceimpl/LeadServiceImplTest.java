package com.lead.serviceimpl;

import com.lead.dto.Data;
import com.lead.dto.LeadFetchResponse;
import com.lead.dto.LeadRequest;
import com.lead.dto.LeadSaveResponse;
import com.lead.entity.Lead;
import com.lead.exception.LeadAlreadyExistException;
import com.lead.exception.LeadNotFoundException;
import com.lead.repository.LeadRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeadServiceImplTest {

    @InjectMocks
    private LeadServiceImpl leadService;

    @Mock
    private LeadRepository leadRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createLeadTest_success() {
        Integer leadId = 1234;
        LeadRequest leadRequest = new LeadRequest();
        leadRequest.setLeadId(leadId);
        Lead lead = new Lead();
        lead.setLeadId(leadId);
        LeadSaveResponse leadSaveResponse = new LeadSaveResponse();
        when(leadRepository.findById(leadId)).thenReturn(Optional.empty());
        LeadSaveResponse response = leadService.createLead(leadRequest);
        assertNotNull(response);


    }

    @Test
    void createLeadTest_exception() {
        Integer leadId = 1234;
        LeadRequest leadRequest = new LeadRequest();
        leadRequest.setLeadId(leadId);
        Lead lead = new Lead();
        lead.setLeadId(leadId);
        LeadSaveResponse leadSaveResponse = new LeadSaveResponse();
        when(leadRepository.findById(leadId)).thenReturn(Optional.of(lead));
        Assertions.assertThrows(LeadAlreadyExistException.class, () -> leadService.createLead(leadRequest));


    }

    @Test
    void fechAllLeadsTest() {
        Long mobileNumber = 8989898989l;
        LeadFetchResponse leadFetchResponse = new LeadFetchResponse();
        List<Data> data = new ArrayList<>();
        Data data1 = new Data();
        data1.setLeadId(2345);
        data1.setMobileNumber(mobileNumber);
        data.add(data1);
        leadFetchResponse.setStatus("success");
        leadFetchResponse.setData(data);
        List<Lead> leadList = new ArrayList<>();
        Lead lead = new Lead();
        lead.setLeadId(2345);
        lead.setMobileNumber(mobileNumber);
        leadList.add(lead);
        when(leadRepository.findByMobileNumber(any())).thenReturn(leadList);
        assertNotNull(leadService.fechAllLeads(mobileNumber));
    }
    @Test
    void fechAllLeadsTest_exception() {
        Long mobileNumber = 8989898989l;
        List<Lead> leadList = new ArrayList<>();
        Lead lead = new Lead();
        lead.setLeadId(2345);
        lead.setMobileNumber(mobileNumber);
        leadList.add(lead);
        when(leadRepository.findByMobileNumber(any())).thenReturn(Collections.EMPTY_LIST);
        assertThrows(LeadNotFoundException.class,()-> leadService.fechAllLeads(mobileNumber));
    }
}
