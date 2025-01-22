package com.test.controller;

import com.doc.controllers.PatientController;
import com.doc.models.Patient;
import com.doc.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PatientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
    }

    @Test
    void testGetAllPatients() throws Exception {
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetPatientById_Existing() throws Exception {
        int id = 1;
        Patient patient = new Patient();
        patient.setUserId(id);
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));

        mockMvc.perform(get("/patients/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(id));

        verify(patientRepository, times(1)).findById(id);
    }

    @Test
    void testGetPatientById_NotFound() throws Exception {
        int id = 1;
        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/patients/{id}", id))
                .andExpect(status().isNotFound());

        verify(patientRepository, times(1)).findById(id);
    }

    @Test
    void testGetPatientsByStatus() throws Exception {
        String status = "Active";
        mockMvc.perform(get("/patients/status/{status}", status))
                .andExpect(status().isOk());
    }

    @Test
    void testCreatePatient() throws Exception {
        Patient patient = new Patient();
        patient.setStatus("Active");

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        mockMvc.perform(post("/patients")
                        .contentType("application/json")
                        .content("{ \"status\": \"Active\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Active"));

        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void testUpdatePatient_Existing() throws Exception {
        int id = 1;
        Patient existingPatient = new Patient();
        existingPatient.setUserId(id);
        existingPatient.setStatus("Active");

        Patient updatedPatient = new Patient();
        updatedPatient.setStatus("Inactive");

        when(patientRepository.findById(id)).thenReturn(Optional.of(existingPatient));
        when(patientRepository.save(any(Patient.class))).thenReturn(updatedPatient);

        mockMvc.perform(put("/patients/{id}", id)
                        .contentType("application/json")
                        .content("{ \"status\": \"Inactive\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("Inactive"));

        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void testUpdatePatient_NotFound() throws Exception {
        int id = 1;
        Patient updatedPatient = new Patient();
        updatedPatient.setStatus("Inactive");

        when(patientRepository.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(put("/patients/{id}", id)
                        .contentType("application/json")
                        .content("{ \"status\": \"Inactive\" }"))
                .andExpect(status().isNotFound());

        verify(patientRepository, times(0)).save(any(Patient.class));
    }

    @Test
    void testDeletePatient_Existing() throws Exception {
        int id = 1;
        when(patientRepository.existsById(id)).thenReturn(true);

        mockMvc.perform(delete("/patients/{id}", id))
                .andExpect(status().isOk());

        verify(patientRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeletePatient_NotFound() throws Exception {
        int id = 1;
        when(patientRepository.existsById(id)).thenReturn(false);

        mockMvc.perform(delete("/patients/{id}", id))
                .andExpect(status().isNotFound());

        verify(patientRepository, times(0)).deleteById(id);
    }
}
