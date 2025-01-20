package com.pat.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patient_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @NotBlank
    @Size(max = 255)
    private String firstname;

    @NotBlank
    @Size(max = 255)
    private String lastname;

    @NotBlank
    private Date dob;

    @NotBlank
    @Size(max = 50)
    private String gender;

    @Size(max = 255)
    private String address;

    @Size(max = 50)
    private String phone_number;

    private Date admission_date;

    private Date discharge_date;

    @Size(max = 50)
    private String status;

    public Patient() {
    }

    public Patient(int user_id, String firstname, String lastname, Date dob, String gender, String address,
                   String phone_number, Date admission_date, Date discharge_date, String status) {
        this.patient_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phone_number = phone_number;
        this.admission_date = admission_date;
        this.discharge_date = discharge_date;
        this.status = status;
    }

    public int getPatientId() {
        return patient_id;
    }

    public void setPatientId(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getUserId() {
        return user.getId();
    }

    public void setUserId(int user_id) {
        this.patient_id = user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getAdmissionDate() {
        return admission_date;
    }

    public void setAdmissionDate(Date admission_date) {
        this.admission_date = admission_date;
    }

    public Date getDischargeDate() {
        return discharge_date;
    }

    public void setDischargeDate(Date discharge_date) {
        this.discharge_date = discharge_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
