package com.doc.repository;

import com.doc.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    // Find a patient by user ID
    List<Patient> findByUserId(int userId);

    // Find all patients by status
    List<Patient> findByStatus(String status);

    // Find patients admitted between two dates
    List<Patient> findByAdmissionDateBetween(java.time.LocalDate startDate, java.time.LocalDate endDate);
}
