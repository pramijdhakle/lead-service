package com.lead.controller;

import com.lead.dto.Data;
import com.lead.dto.LeadFetchResponse;
import com.lead.dto.LeadRequest;
import com.lead.dto.LeadSaveResponse;
import com.lead.service.LeadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeadControllerTest {

    @InjectMocks
    private LeadController leadController;
    @Mock
    private LeadService leadService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveLeadData() throws Exception {
        LeadRequest request = new LeadRequest();
        request.setLeadId(123);
        request.setFirstName("Abc");
        request.setMiddleName("Bca");
        request.setLastName("Cef");
        request.setEmail("ch@gmail.com");
        request.setGender("Male");
        request.setMobileNumber(8989898989l);
        request.setDob(new SimpleDateFormat("dd-MM-yyyy").parse("07-09-1997"));
        LeadSaveResponse mockLeadSaveResponse = new LeadSaveResponse();
        mockLeadSaveResponse.setStatus("jhg");
        mockLeadSaveResponse.setData("fghjk");
        when(leadService.createLead(request)).thenReturn(mockLeadSaveResponse);
        ResponseEntity<LeadSaveResponse> leadSaveResponseResponseEntity = leadController.saveLeadData(request);
        assertNotNull(leadSaveResponseResponseEntity);

    }

    @Test
    void getLeadDataTest(){
        Long mobileNumber =89898989l;
        LeadFetchResponse leadFetchResponse = new LeadFetchResponse();
        List<Data> data = new ArrayList<>();
        Data data1 = new Data();
        data1.setLeadId(2345);
        data1.setMobileNumber(mobileNumber);
        data.add(data1);
        leadFetchResponse.setStatus("success");
        leadFetchResponse.setData(data);
        when(leadService.fechAllLeads(mobileNumber)).thenReturn(leadFetchResponse);
        ResponseEntity<LeadFetchResponse> leadData = leadController.getLeadData(mobileNumber);
        assertNotNull(leadData);
    }

}
