package com.test.controller;

import com.doc.controllers.PrescriptionController;
import com.doc.models.Prescription;
import com.doc.repository.PrescriptionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PrescriptionControllerTest {

    @Mock
    private PrescriptionRepository prescriptionRepository;

    @InjectMocks
    private PrescriptionController prescriptionController;

    public PrescriptionControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreatePrescription() {
        // Arrange
        Prescription prescription = new Prescription();
        prescription.setRecordId(1);
        prescription.setMedicationName("Ibuprofen");
        prescription.setDosage("200mg");
        prescription.setInstructions("Take one tablet every 6 hours");

        when(prescriptionRepository.save(prescription)).thenReturn(prescription);

        // Act
        ResponseEntity<Prescription> response = prescriptionController.createPrescription(prescription);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(prescription);
        verify(prescriptionRepository, times(1)).save(prescription);
    }

    @Test
    void shouldGetPrescriptionById() {
        // Arrange
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(1);
        prescription.setRecordId(1);
        prescription.setMedicationName("Paracetamol");

        when(prescriptionRepository.findById(1)).thenReturn(Optional.of(prescription));

        // Act
        ResponseEntity<Prescription> response = prescriptionController.getPrescriptionById(1);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(prescription);
        verify(prescriptionRepository, times(1)).findById(1);
    }

    @Test
    void shouldReturnNotFoundWhenPrescriptionDoesNotExist() {
        // Arrange
        when(prescriptionRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Prescription> response = prescriptionController.getPrescriptionById(1);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(prescriptionRepository, times(1)).findById(1);
    }

    @Test
    void shouldGetAllPrescriptions() {
        // Arrange
        Prescription prescription1 = new Prescription();
        prescription1.setPrescriptionId(1);
        prescription1.setRecordId(1);
        prescription1.setMedicationName("Ibuprofen");

        Prescription prescription2 = new Prescription();
        prescription2.setPrescriptionId(2);
        prescription2.setRecordId(2);
        prescription2.setMedicationName("Paracetamol");

        List<Prescription> prescriptions = Arrays.asList(prescription1, prescription2);
        when(prescriptionRepository.findAll()).thenReturn(prescriptions);

        // Act
        List<Prescription> response = prescriptionController.getAllPrescriptions();

        // Assert
        assertThat(response).hasSize(2).containsExactly(prescription1, prescription2);
        verify(prescriptionRepository, times(1)).findAll();
    }

    @Test
    void shouldUpdatePrescription() {
        // Arrange
        Prescription existingPrescription = new Prescription();
        existingPrescription.setPrescriptionId(1);
        existingPrescription.setRecordId(1);
        existingPrescription.setMedicationName("Ibuprofen");

        Prescription updatedDetails = new Prescription();
        updatedDetails.setRecordId(2);
        updatedDetails.setMedicationName("Paracetamol");
        updatedDetails.setDosage("500mg");
        updatedDetails.setInstructions("Take one tablet every 8 hours");

        when(prescriptionRepository.findById(1)).thenReturn(Optional.of(existingPrescription));
        when(prescriptionRepository.save(existingPrescription)).thenReturn(existingPrescription);

        // Act
        ResponseEntity<Prescription> response = prescriptionController.updatePrescription(1, updatedDetails);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getMedicationName()).isEqualTo("Paracetamol");
        verify(prescriptionRepository, times(1)).findById(1);
        verify(prescriptionRepository, times(1)).save(existingPrescription);
    }

    @Test
    void shouldDeletePrescription() {
        // Arrange
        when(prescriptionRepository.existsById(1)).thenReturn(true);

        // Act
        ResponseEntity<Void> response = prescriptionController.deletePrescription(1);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(prescriptionRepository, times(1)).deleteById(1);
    }

    @Test
    void shouldReturnNotFoundWhenDeletingNonExistentPrescription() {
        // Arrange
        when(prescriptionRepository.existsById(1)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = prescriptionController.deletePrescription(1);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(prescriptionRepository, never()).deleteById(1);
    }
}
