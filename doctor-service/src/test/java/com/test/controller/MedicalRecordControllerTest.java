package com.test.controller;

import com.doc.controllers.MedicalRecordController;
import com.doc.models.MedicalRecord;
import com.doc.repository.MedicalRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MedicalRecordControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordController medicalRecordController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordController).build();
    }

    @Test
    void testGetAllMedicalRecords() throws Exception {
        // Assume that we return some records from the repository
        mockMvc.perform(get("/api/doctor/medicalRecord"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMedicalRecordById_Existing() throws Exception {
        int id = 1;
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setRecordId(id);
        when(medicalRecordRepository.findById(id)).thenReturn(Optional.of(medicalRecord));

        mockMvc.perform(get("/api/doctor/medicalRecord/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recordId").value(id));

        verify(medicalRecordRepository, times(1)).findById(id);
    }

    @Test
    void testGetMedicalRecordById_NotFound() throws Exception {
        int id = 1;
        when(medicalRecordRepository.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/doctor/medicalRecord/{id}", id))
                .andExpect(status().isNotFound());

        verify(medicalRecordRepository, times(1)).findById(id);
    }

    @Test
    void testGetMedicalRecordsByPatientId() throws Exception {
        int patientId = 1;
        mockMvc.perform(get("/api/doctor/medicalRecord/patient/{patientId}", patientId))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateMedicalRecord() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setDiagnosis("Test Diagnosis");

        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(medicalRecord);

        mockMvc.perform(post("/api/doctor/medicalRecord")
                        .contentType("application/json")
                        .content("{ \"diagnosis\": \"Test Diagnosis\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.diagnosis").value("Test Diagnosis"));

        verify(medicalRecordRepository, times(1)).save(any(MedicalRecord.class));
    }

    @Test
    void testUpdateMedicalRecord_Existing() throws Exception {
        int id = 1;
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setRecordId(id);
        medicalRecord.setDiagnosis("Updated Diagnosis");

        when(medicalRecordRepository.existsById(id)).thenReturn(true);
        when(medicalRecordRepository.save(any(MedicalRecord.class))).thenReturn(medicalRecord);

        mockMvc.perform(put("/api/doctor/medicalRecord/{id}", id)
                        .contentType("application/json")
                        .content("{ \"diagnosis\": \"Updated Diagnosis\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.diagnosis").value("Updated Diagnosis"));

        verify(medicalRecordRepository, times(1)).save(any(MedicalRecord.class));
    }

    @Test
    void testUpdateMedicalRecord_NotFound() throws Exception {
        int id = 1;
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setRecordId(id);

        when(medicalRecordRepository.existsById(id)).thenReturn(false);

        mockMvc.perform(put("/api/doctor/medicalRecord/{id}", id)
                        .contentType("application/json")
                        .content("{ \"diagnosis\": \"Updated Diagnosis\" }"))
                .andExpect(status().isNotFound());

        verify(medicalRecordRepository, times(1)).existsById(id);
    }

    @Test
    void testDeleteMedicalRecord_Existing() throws Exception {
        int id = 1;
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setRecordId(id);

        // Mock the repository to simulate the record being found
        when(medicalRecordRepository.findById(id)).thenReturn(Optional.of(medicalRecord));
        when(medicalRecordRepository.existsById(id)).thenReturn(true);  // Ensure existsById returns true for the test

        mockMvc.perform(delete("/api/doctor/medicalRecord/{id}", id))
                .andExpect(status().isNoContent());  // Expecting 204 for successful deletion

        // Verify that deleteById was called once with the correct id
        verify(medicalRecordRepository, times(1)).deleteById(id);
    }


    @Test
    void testDeleteMedicalRecord_NotFound() throws Exception {
        int id = 1;
        when(medicalRecordRepository.existsById(id)).thenReturn(false);  // Mock existsById to return false

        mockMvc.perform(delete("/api/doctor/medicalRecord/{id}", id))
                .andExpect(status().isNotFound());

        // Verify that existsById was called once
        verify(medicalRecordRepository, times(1)).existsById(id);
    }

}
