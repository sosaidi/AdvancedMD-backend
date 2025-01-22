package com.test.controller;



import com.doc.controllers.DoctorController;
import com.doc.models.Doctor;
import com.doc.repository.DoctorRepository;
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

public class DoctorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
    }

    @Test
    void testGetAllDoctors() throws Exception {
        mockMvc.perform(get("/api/doctor"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetDoctorById_Existing() throws Exception {
        int id = 1;
        Doctor doctor = new Doctor();
        doctor.setUserId(id);
        when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));

        mockMvc.perform(get("/api/doctor/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(id));

        verify(doctorRepository, times(1)).findById(id);
    }

    @Test
    void testGetDoctorById_NotFound() throws Exception {
        int id = 1;
        when(doctorRepository.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/doctor/{id}", id))
                .andExpect(status().isNotFound());

        verify(doctorRepository, times(1)).findById(id);
    }

    @Test
    void testGetDoctorsBySpecialization() throws Exception {
        String specialization = "Cardiology";
        mockMvc.perform(get("/api/doctor/specialization/{specialization}", specialization))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateDoctor() throws Exception {
        Doctor doctor = new Doctor();
        doctor.setSpecialization("Cardiology");

        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        mockMvc.perform(post("/api/doctor")
                        .contentType("application/json")
                        .content("{ \"specialization\": \"Cardiology\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialization").value("Cardiology"));

        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }

    @Test
    void testUpdateDoctor_Existing() throws Exception {
        int id = 1;
        Doctor existingDoctor = new Doctor();
        existingDoctor.setUserId(id);
        existingDoctor.setSpecialization("Cardiology");

        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setSpecialization("Neurology");

        when(doctorRepository.findById(id)).thenReturn(Optional.of(existingDoctor));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(updatedDoctor);

        mockMvc.perform(put("/api/doctor/{id}", id)
                        .contentType("application/json")
                        .content("{ \"specialization\": \"Neurology\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.specialization").value("Neurology"));

        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }

    @Test
    void testUpdateDoctor_NotFound() throws Exception {
        int id = 1;
        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setSpecialization("Neurology");

        when(doctorRepository.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/doctor/{id}", id)
                        .contentType("application/json")
                        .content("{ \"specialization\": \"Neurology\" }"))
                .andExpect(status().isNotFound());

        verify(doctorRepository, times(0)).save(any(Doctor.class));
    }

    @Test
    void testDeleteDoctor_Existing() throws Exception {
        int id = 1;
        when(doctorRepository.existsById(id)).thenReturn(true);

        mockMvc.perform(delete("/api/doctor/{id}", id))
                .andExpect(status().isOk());

        verify(doctorRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteDoctor_NotFound() throws Exception {
        int id = 1;
        when(doctorRepository.existsById(id)).thenReturn(false);

        mockMvc.perform(delete("/api/doctor/{id}", id))
                .andExpect(status().isNotFound());

        verify(doctorRepository, times(0)).deleteById(id);
    }
}
