package com.lead.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "lead_table")
public class Lead implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lead_id", unique = true, nullable = false)
    private Integer leadId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;


    @Column(name = "last_name", nullable = false)
    private String lastName;


    @Column(name = "mobile_number", nullable = false)
    private Long mobileNumber;


    @Column(name = "gender", nullable = false)
    private String gender;


    @Column(name = "dob", nullable = false)
    private Date dob;


    @Column(name = "email", nullable = false)
    private String email;

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
        return "Lead{" +
                "leadId=" + leadId +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber=" + mobileNumber +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lead lead = (Lead) o;
        return Objects.equals(leadId, lead.leadId) && Objects.equals(firstName, lead.firstName) && Objects.equals(middleName, lead.middleName) && Objects.equals(lastName, lead.lastName) && Objects.equals(mobileNumber, lead.mobileNumber) && Objects.equals(gender, lead.gender) && Objects.equals(dob, lead.dob) && Objects.equals(email, lead.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leadId, firstName, middleName, lastName, mobileNumber, gender, dob, email);
    }
}
