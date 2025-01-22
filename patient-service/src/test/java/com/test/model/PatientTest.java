package com.test.model;


import com.pat.models.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        // Initialize the Patient object before each test
        patient = new Patient();
    }

    @Test
    void testGetSetPatientId() {
        // Test patientId getter and setter
        patient.setPatientId(1);
        assertEquals(1, patient.getPatientId());
    }

    @Test
    void testGetSetUserId() {
        // Test userId getter and setter
        patient.setUserId(1001);
        assertEquals(1001, patient.getUserId());
    }

    @Test
    void testGetSetFirstname() {
        // Test firstname getter and setter
        String firstname = "John";
        patient.setFirstname(firstname);
        assertEquals(firstname, patient.getFirstname());
    }

    @Test
    void testGetSetLastname() {
        // Test lastname getter and setter
        String lastname = "Doe";
        patient.setLastname(lastname);
        assertEquals(lastname, patient.getLastname());
    }

    @Test
    void testGetSetDob() {
        // Test dob getter and setter
        LocalDate dob = LocalDate.of(1990, 1, 1);
        patient.setDob(dob);
        assertEquals(dob, patient.getDob());
    }

    @Test
    void testGetSetGender() {
        // Test gender getter and setter
        String gender = "Male";
        patient.setGender(gender);
        assertEquals(gender, patient.getGender());
    }

    @Test
    void testGetSetAddress() {
        // Test address getter and setter
        String address = "123 Main St, City, Country";
        patient.setAddress(address);
        assertEquals(address, patient.getAddress());
    }

    @Test
    void testGetSetPhoneNumber() {
        // Test phoneNumber getter and setter
        String phoneNumber = "+1234567890";
        patient.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, patient.getPhoneNumber());
    }

    @Test
    void testGetSetAdmissionDate() {
        // Test admissionDate getter and setter
        LocalDate admissionDate = LocalDate.of(2022, 1, 1);
        patient.setAdmissionDate(admissionDate);
        assertEquals(admissionDate, patient.getAdmissionDate());
    }

    @Test
    void testGetSetDischargeDate() {
        // Test dischargeDate getter and setter
        LocalDate dischargeDate = LocalDate.of(2022, 1, 10);
        patient.setDischargeDate(dischargeDate);
        assertEquals(dischargeDate, patient.getDischargeDate());
    }

    @Test
    void testGetSetStatus() {
        // Test status getter and setter
        String status = "Discharged";
        patient.setStatus(status);
        assertEquals(status, patient.getStatus());
    }
}
