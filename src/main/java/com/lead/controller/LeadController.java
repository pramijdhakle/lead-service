package com.lead.controller;

import com.lead.dto.*;
import com.lead.exception.ErrorCodeResponse;
import com.lead.service.LeadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/leads")
@Tag(name = "LeadController", description = "APIs for Lead")
public class LeadController {

    @Autowired
    private LeadService leadService;

    @Operation(summary = "Create new Lead", description = "APIs for saving new Lead data in DB", method = "POST", responses = {@ApiResponse(responseCode = "201", description = "Lead details save successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LeadSaveResponse.class))), @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorCodeResponse.class)))})
    @Parameter(in = ParameterIn.HEADER, name = "authToken", required = false, description = "Authentication Token")
    @PostMapping("/save")
    public ResponseEntity<LeadSaveResponse> saveLeadData(@RequestBody @Valid LeadRequest leadRequest) {
        LeadSaveResponse lead = leadService.createLead(leadRequest);
        return new ResponseEntity<>(lead, HttpStatus.CREATED);
    }

    // @Hidden - use to Hide your API from public access
    @Parameter(in = ParameterIn.PATH, name = "mobileNumber", required = true, description = "Mobile Number")
    @GetMapping("/get-leads/{mobileNumber}")
    @Operation(summary = "Fetch Lead", description = "APIs for fetching Lead data from DB based on mobile number")
    public ResponseEntity<LeadFetchResponse> getLeadData(@PathVariable Long mobileNumber) {
        LeadFetchResponse leadFetchResponse = leadService.fechAllLeads(mobileNumber);
        return new ResponseEntity<>(leadFetchResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update existing Lead data", description = "APIs for updating existing Lead data in DB", method = "PUT", responses = {@ApiResponse(responseCode = "200", description = "Lead details updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LeadDataUpdateResponse.class))), @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorCodeResponse.class)))})
    @Parameters({
            @Parameter(in = ParameterIn.HEADER, name = "authToken", required = false, description = "Authentication Token")
         //   @Parameter(in = ParameterIn.QUERY, name = "LeadId", required = true, description = "Lead ID")
    })
    @PutMapping("/update")
    public ResponseEntity<LeadDataUpdateResponse> saveLeadData(
            @RequestParam(value = "leadId", required = true) Integer leadId,
            @RequestBody @Valid LeadUpdateRequest leadRequest) {
        LeadDataUpdateResponse lead = leadService.updateLeadData(leadRequest, leadId);
        return new ResponseEntity<>(lead, HttpStatus.CREATED);
    }

    @Operation(summary = "Fetch all Lead", description = "APIs for fetching all Lead data from DB")
    @GetMapping("/getallleads")
    public ResponseEntity<List<Data>> getLeadData() {
        List<Data> leadFetchResponse = leadService.getAllLeads();
        return new ResponseEntity<>(leadFetchResponse, HttpStatus.OK);
    }
}
