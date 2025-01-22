package com.pat.controllers;

import com.pat.models.Prescription;
import com.pat.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Prescription Controller
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/patient/prescription")
@PreAuthorize("hasRole('ROLE_PATIENT')")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return new ResponseEntity<>(savedPrescription, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable int id) {
        return prescriptionRepository.findById(id)
                .map(prescription -> new ResponseEntity<>(prescription, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescription> updatePrescription(@PathVariable int id, @RequestBody Prescription prescriptionDetails) {
        return prescriptionRepository.findById(id)
                .map(prescription -> {
                    prescription.setRecordId(prescriptionDetails.getRecordId());
                    prescription.setMedicationName(prescriptionDetails.getMedicationName());
                    prescription.setDosage(prescriptionDetails.getDosage());
                    prescription.setInstructions(prescriptionDetails.getInstructions());
                    Prescription updatedPrescription = prescriptionRepository.save(prescription);
                    return new ResponseEntity<>(updatedPrescription, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable int id) {
        if (prescriptionRepository.existsById(id)) {
            prescriptionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



