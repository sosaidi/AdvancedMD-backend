package com.test.controller;

import com.pat.controllers.BillingController;
import com.pat.models.Billing;
import com.pat.repository.BillingRepository;
import org.junit.jupiter.api.BeforeEach;
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

class BillingControllerTest {

    @Mock
    private BillingRepository billingRepository;

    @InjectMocks
    private BillingController billingController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllBillingRecords() {
        // Arrange
        Billing billing1 = new Billing();
        billing1.setBillingId(1);
        billing1.setServiceCost(100);

        Billing billing2 = new Billing();
        billing2.setBillingId(2);
        billing2.setServiceCost(200);

        List<Billing> billingList = Arrays.asList(billing1, billing2);
        when(billingRepository.findAll()).thenReturn(billingList);

        // Act
        List<Billing> result = billingController.getAllBillingRecords();

        // Assert
        assertThat(result).hasSize(2).containsExactly(billing1, billing2);
        verify(billingRepository, times(1)).findAll();
    }

    @Test
    void shouldGetBillingRecordById() {
        // Arrange
        Billing billing = new Billing();
        billing.setBillingId(1);
        billing.setServiceCost(100);

        when(billingRepository.findById(1)).thenReturn(Optional.of(billing));

        // Act
        ResponseEntity<Billing> response = billingController.getBillingRecordById(1);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(billing);
        verify(billingRepository, times(1)).findById(1);
    }

    @Test
    void shouldReturnNotFoundWhenBillingRecordDoesNotExist() {
        // Arrange
        when(billingRepository.findById(1)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Billing> response = billingController.getBillingRecordById(1);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(billingRepository, times(1)).findById(1);
    }





    @Test
    void shouldReturnNotFoundWhenUpdatingNonExistentBillingRecord() {
        // Arrange
        int billingId = 1;
        Billing updatedBillingDetails = new Billing();
        updatedBillingDetails.setServiceCost(200);

        when(billingRepository.existsById(billingId)).thenReturn(false);

        // Act
        ResponseEntity<Billing> response = billingController.updateBillingRecord(billingId, updatedBillingDetails);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        verify(billingRepository, times(1)).existsById(billingId);
        verify(billingRepository, never()).save(any(Billing.class));
    }
}
