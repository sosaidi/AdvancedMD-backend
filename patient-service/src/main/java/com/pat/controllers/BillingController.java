package com.pat.controllers;


import com.pat.models.Billing;
import com.pat.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/patient/bill")
@PreAuthorize("hasRole('ROLE_PATIENT')")
public class BillingController {

    @Autowired
    private BillingRepository billingRepository;

    // Get all billing records
    @GetMapping
    public List<Billing> getAllBillingRecords() {
        return billingRepository.findAll();
    }

    // Get a billing record by ID
    @GetMapping("/{id}")
    public ResponseEntity<Billing> getBillingRecordById(@PathVariable int id) {
        Optional<Billing> billing = billingRepository.findById(id);
        return billing.map(record -> new ResponseEntity<>(record, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get billing records by patient ID
    @GetMapping("/{patientId}")
    public List<Billing> getBillingRecordsByPatientId(@PathVariable int patientId) {
        return billingRepository.findByPatientPatientId(patientId);
    }


    // Update an existing billing record
    @PutMapping("/{id}")
    public ResponseEntity<Billing> updateBillingRecord(@PathVariable int id, @RequestBody Billing billing) {
        if (!billingRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        billing.setBillingId(id);
        Billing updatedBilling = billingRepository.save(billing);
        return new ResponseEntity<>(updatedBilling, HttpStatus.OK);
    }



}

