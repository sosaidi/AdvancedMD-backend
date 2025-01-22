package com.test.model;


import com.pat.models.Doctor;
import com.pat.models.MedicalRecord;
import com.pat.models.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class MedicalRecordTest {

    private MedicalRecord medicalRecord;
    private Patient patient;
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        // Initialize the MedicalRecord object before each test
        medicalRecord = new MedicalRecord();

        // You can create mocks or real instances of Patient and Doctor here
        patient = new Patient();  // assuming a simple constructor, or you can mock it
        doctor = new Doctor();  // assuming a simple constructor, or you can mock it
    }

    @Test
    void testGetSetRecordId() {
        // Test recordId getter and setter
        medicalRecord.setRecordId(1);
        assertEquals(1, medicalRecord.getRecordId());
    }

    @Test
    void testGetSetPatient() {
        // Test patient getter and setter
        medicalRecord.setPatient(patient);
        assertEquals(patient, medicalRecord.getPatient());
    }

    @Test
    void testGetSetStaff() {
        // Test staff getter and setter
        medicalRecord.setStaff(doctor);
        assertEquals(doctor, medicalRecord.getStaff());
    }

    @Test
    void testGetSetDiagnosis() {
        // Test diagnosis getter and setter
        String diagnosis = "Flu";
        medicalRecord.setDiagnosis(diagnosis);
        assertEquals(diagnosis, medicalRecord.getDiagnosis());
    }

    @Test
    void testGetSetPrescription() {
        // Test prescription getter and setter
        String prescription = "Paracetamol";
        medicalRecord.setPrescription(prescription);
        assertEquals(prescription, medicalRecord.getPrescription());
    }

    @Test
    void testGetSetReport() {
        // Test report getter and setter
        String report = "Patient is recovering well";
        medicalRecord.setReport(report);
        assertEquals(report, medicalRecord.getReport());
    }

    @Test
    void testGetSetCreatedAt() {
        // Test createdAt getter and setter
        Date createdAt = new Date();
        medicalRecord.setCreatedAt(createdAt);
        assertEquals(createdAt, medicalRecord.getCreatedAt());
    }
}
