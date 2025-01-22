package com.doc.repository;

import com.doc.models.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Prescription Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    // Find all prescriptions by record ID
    List<Prescription> findByRecordId(int recordId);

    // By medication name (case insensitive)
    List<Prescription> findByMedicationNameContainingIgnoreCase(String medicationName);
}
