package com.doc.repository;

import com.doc.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

  // Find appointments by patient ID
  List<Appointment> findByPatientId(Long patientId);

  // Find appointments by doctor ID
  List<Appointment> findByDoctorId(Long doctorId);

  Optional<Appointment> findByAppId(Long appId);


  Boolean deleteByAppId(Long appId);

  // Find appointments within a date range
  List<Appointment> findByAppDateBetween(LocalDateTime startDate, LocalDateTime endDate);

  // Find appointments by status
  List<Appointment> findByStatus(String status);

  // Check if an appointment exists by patient ID and date
  boolean existsByPatientIdAndAppDate(Long patientId, LocalDateTime appDate);
}
