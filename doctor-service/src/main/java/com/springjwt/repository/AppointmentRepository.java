package com.springjwt.repository;


import com.springjwt.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

  // Find appointments by patient ID
  List<Appointment> findByPatientId(Long patientId);

  // Find appointments by staff ID
  List<Appointment> findByStaffId(Long staffId);

  // Find appointments within a date range
  List<Appointment> findByAppDateBetween(LocalDateTime startDate, LocalDateTime endDate);

  // Find appointments by status
  List<Appointment> findByStatus(String status);

  // Check if an appointment exists by patient ID and date
  boolean existsByPatientIdAndAppDate(Long patientId, LocalDateTime appDate);
}
