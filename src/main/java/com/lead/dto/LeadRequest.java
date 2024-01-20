package com.lead.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lead.utils.MobileNumber;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class LeadRequest implements Serializable {
    @NotNull(message = "leadId cannot be null")
    private Integer leadId;

    @NotNull(message = "First Name cannot be null")
    @Pattern(regexp = "^[A-Za-z]+$", message = "firstName should contain only alphabets")
    private String firstName;
    @Pattern(regexp = "^[A-Za-z]*$", message = "middleName should contain only alphabets")
    private String middleName;

    @NotNull(message = "Last Name cannot be null")
    @Pattern(regexp = "^[A-Za-z]+$", message = "lastName should contain only alphabets")
    private String lastName;

    @NotNull(message = "Mobile Number cannot be null")
    @Positive(message = "mobileNumber should be a positive number")
    @Digits(integer = 10, fraction = 0, message = "mobileNumber should be a 10-digit number")
    @MobileNumber(message = "Invalid mobile number")
    private Long mobileNumber;

    @NotNull(message = "Gender cannot be null")
    @Pattern(regexp = "^(Male|Female|Others)$", message = "gender should be Male, Female, or Others")
    private String gender;

    @NotNull(message = "DOB cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, fallbackPatterns = {"dd-MM-yyyy"})
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date dob;

    @NotNull(message = "Email cannot be null")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Invalid email format")
    private String email;

    public LeadRequest() {
        leadId = null;
    }


    public Integer getLeadId() {
        return leadId;
    }

    public void setLeadId(Integer leadId) {
        this.leadId = leadId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LeadRequest{" + "leadId=" + leadId + ", firstName='" + firstName + '\'' + ", middleName='" + middleName + '\'' + ", lastName='" + lastName + '\'' + ", mobileNumber=" + mobileNumber + ", gender='" + gender + '\'' + ", dob=" + dob + ", email='" + email + '\'' + '}';
    }
}
