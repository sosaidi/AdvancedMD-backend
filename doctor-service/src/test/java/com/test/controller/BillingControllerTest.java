package com.test.controller;

import com.doc.controllers.BillingController;
import com.doc.models.Billing;
import com.doc.repository.BillingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BillingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private BillingController billingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(billingController).build();
    }

    @Test
    void testGetAllBillingRecords() throws Exception {
        mockMvc.perform(get("/api/doctor/bill"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBillingRecordById_Existing() throws Exception {
        int id = 1;
        Billing billing = new Billing();
        billing.setBillingId(id);
        when(billingRepository.findById(id)).thenReturn(Optional.of(billing));

        mockMvc.perform(get("/api/doctor/bill/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.billingId").value(id));

        verify(billingRepository, times(1)).findById(id);
    }

    @Test
    void testGetBillingRecordById_NotFound() throws Exception {
        int id = 1;
        when(billingRepository.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/doctor/bill/{id}", id))
                .andExpect(status().isNotFound());

        verify(billingRepository, times(1)).findById(id);
    }

    @Test
    void testGetBillingRecordsByPatientId() throws Exception {
        int patientId = 1;
        mockMvc.perform(get("/api/doctor/bill/patient/{patientId}", patientId))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateBillingRecord() throws Exception {
        Billing billing = new Billing();
        billing.setServiceCost(100.0);

        when(billingRepository.save(any(Billing.class))).thenReturn(billing);

        mockMvc.perform(post("/api/doctor/bill")
                        .contentType("application/json")
                        .content("{ \"serviceCost\": 100.0 }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.serviceCost").value(100.0));

        verify(billingRepository, times(1)).save(any(Billing.class));
    }

    @Test
    void testUpdateBillingRecord_Existing() throws Exception {
        int id = 1;
        Billing billing = new Billing();
        billing.setBillingId(id);
        billing.setServiceCost(150.0);

        when(billingRepository.existsById(id)).thenReturn(true);
        when(billingRepository.save(any(Billing.class))).thenReturn(billing);

        mockMvc.perform(put("/api/doctor/bill/{id}", id)
                        .contentType("application/json")
                        .content("{ \"serviceCost\": 150.0 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serviceCost").value(150.0));

        verify(billingRepository, times(1)).save(any(Billing.class));
    }

    @Test
    void testUpdateBillingRecord_NotFound() throws Exception {
        int id = 1;
        when(billingRepository.existsById(id)).thenReturn(false);

        mockMvc.perform(put("/api/doctor/bill/{id}", id)
                        .contentType("application/json")
                        .content("{ \"serviceCost\": 150.0 }"))
                .andExpect(status().isNotFound());

        verify(billingRepository, times(0)).save(any(Billing.class));
    }

    @Test
    void testDeleteBillingRecord_Existing() throws Exception {
        int id = 1;
        when(billingRepository.existsById(id)).thenReturn(true);

        mockMvc.perform(delete("/api/doctor/bill/{id}", id))
                .andExpect(status().isNoContent());

        verify(billingRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteBillingRecord_NotFound() throws Exception {
        int id = 1;
        when(billingRepository.existsById(id)).thenReturn(false);

        mockMvc.perform(delete("/api/doctor/bill/{id}", id))
                .andExpect(status().isNotFound());

        verify(billingRepository, times(0)).deleteById(id);
    }
}
