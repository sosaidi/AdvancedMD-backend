package com.doc.controllers;

import com.doc.models.MedicalRecord;
import com.doc.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/doctor/medicalRecord")
@PreAuthorize("hasRole('ROLE_STAFF')")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    // Get all medical records
    @GetMapping
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }

    // Get a medical record by ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecord> getMedicalRecordById(@PathVariable int id) {
        Optional<MedicalRecord> medicalRecord = medicalRecordRepository.findById(id);
        return medicalRecord.map(record -> new ResponseEntity<>(record, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Get medical records by patient ID
    @GetMapping("/patient/{patientId}")
    public List<MedicalRecord> getMedicalRecordsByPatientId(@PathVariable int patientId) {
        return medicalRecordRepository.findByPatientPatientId(patientId);
    }

    // Create a new medical record
    @PostMapping
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord savedRecord = medicalRecordRepository.save(medicalRecord);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    // Update an existing medical record
    @PutMapping("/{id}")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@PathVariable int id, @RequestBody MedicalRecord medicalRecord) {
        if (!medicalRecordRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        medicalRecord.setRecordId(id);
        MedicalRecord updatedRecord = medicalRecordRepository.save(medicalRecord);
        return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    }

    // Delete a medical record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicalRecord(@PathVariable int id) {
        if (!medicalRecordRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        medicalRecordRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
