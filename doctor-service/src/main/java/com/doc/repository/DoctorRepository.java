package com.doc.repository;



import com.doc.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    // Find a doctor by user ID
    List<Doctor> findByUserId(int userId);

    // Find doctors by specialization
    List<Doctor> findBySpecialization(String specialization);

    // Find all active doctors
    List<Doctor> findByStatus(String status);
}
