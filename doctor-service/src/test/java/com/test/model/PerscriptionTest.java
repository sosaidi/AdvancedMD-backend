package com.test.model;

import com.doc.models.Prescription;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class PrescriptionTest {

    @Test
    void shouldCreatePrescriptionObject() {
        // Create a Prescription instance
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(1);
        prescription.setRecordId(101);
        prescription.setMedicationName("Aspirin");
        prescription.setDosage("100mg");
        prescription.setInstructions("Take one tablet after meals");

        // Assert the fields are set correctly
        assertThat(prescription.getPrescriptionId()).isEqualTo(1);
        assertThat(prescription.getRecordId()).isEqualTo(101);
        assertThat(prescription.getMedicationName()).isEqualTo("Aspirin");
        assertThat(prescription.getDosage()).isEqualTo("100mg");
        assertThat(prescription.getInstructions()).isEqualTo("Take one tablet after meals");
    }

    @Test
    void shouldUpdatePrescriptionFields() {
        // Create a Prescription instance
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(2);
        prescription.setRecordId(202);
        prescription.setMedicationName("Ibuprofen");
        prescription.setDosage("200mg");
        prescription.setInstructions("Take one tablet every 6 hours");

        // Update fields
        prescription.setMedicationName("Paracetamol");
        prescription.setDosage("500mg");

        // Assert the updates
        assertThat(prescription.getMedicationName()).isEqualTo("Paracetamol");
        assertThat(prescription.getDosage()).isEqualTo("500mg");
    }

    @Test
    void shouldHandleNullValues() {
        // Create a Prescription instance with null values
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(3);
        prescription.setRecordId(303);
        prescription.setMedicationName(null);
        prescription.setDosage(null);
        prescription.setInstructions(null);

        // Assert null values
        assertThat(prescription.getMedicationName()).isNull();
        assertThat(prescription.getDosage()).isNull();
        assertThat(prescription.getInstructions()).isNull();
    }
}
