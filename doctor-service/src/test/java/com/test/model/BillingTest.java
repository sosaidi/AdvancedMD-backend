package com.test.model;

import com.doc.models.Billing;
import com.doc.models.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class BillingTest {

    private Billing billing;
    private Patient patient;

    @BeforeEach
    void setUp() {
        // Initialize the Billing and Patient objects before each test
        billing = new Billing();
        patient = mock(Patient.class); // Mock the Patient object if not testing it directly
    }

    @Test
    void testGetSetBillingId() {
        // Test billingId getter and setter
        billing.setBillingId(1);
        assertEquals(1, billing.getBillingId());
    }

    @Test
    void testGetSetPatient() {
        // Test patient getter and setter
        billing.setPatient(patient);
        assertEquals(patient, billing.getPatient());
    }

    @Test
    void testGetSetServiceDescription() {
        // Test serviceDescription getter and setter
        String serviceDescription = "General Consultation";
        billing.setServiceDescription(serviceDescription);
        assertEquals(serviceDescription, billing.getServiceDescription());
    }

    @Test
    void testGetSetServiceCost() {
        // Test serviceCost getter and setter
        double serviceCost = 150.50;
        billing.setServiceCost(serviceCost);
        assertEquals(serviceCost, billing.getServiceCost());
    }

    @Test
    void testGetSetDateBilled() {
        // Test dateBilled getter and setter
        Date dateBilled = new Date();
        billing.setDateBilled(dateBilled);
        assertEquals(dateBilled, billing.getDateBilled());
    }

    @Test
    void testGetSetStatus() {
        // Test status getter and setter
        String status = "Paid";
        billing.setStatus(status);
        assertEquals(status, billing.getStatus());
    }
}
