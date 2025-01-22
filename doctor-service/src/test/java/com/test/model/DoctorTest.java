package com.test.model;


import com.doc.models.Doctor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoctorTest {

    private Doctor doctor;

    @BeforeEach
    void setUp() {
        // Initialize the Doctor object before each test
        doctor = new Doctor();
    }

    @Test
    void testGetSetStaffId() {
        // Test staffId getter and setter
        doctor.setStaffId(1);
        assertEquals(1, doctor.getStaffId());
    }

    @Test
    void testGetSetUserId() {
        // Test userId getter and setter
        doctor.setUserId(1001);
        assertEquals(1001, doctor.getUserId());
    }

    @Test
    void testGetSetName() {
        // Test name getter and setter
        String name = "Dr. John Doe";
        doctor.setName(name);
        assertEquals(name, doctor.getName());
    }

    @Test
    void testGetSetSpecialization() {
        // Test specialization getter and setter
        String specialization = "Cardiology";
        doctor.setSpecialization(specialization);
        assertEquals(specialization, doctor.getSpecialization());
    }

    @Test
    void testGetSetPhoneNumber() {
        // Test phoneNumber getter and setter
        String phoneNumber = "+1234567890";
        doctor.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, doctor.getPhoneNumber());
    }

    @Test
    void testGetSetStatus() {
        // Test status getter and setter
        String status = "Active";
        doctor.setStatus(status);
        assertEquals(status, doctor.getStatus());
    }
}
