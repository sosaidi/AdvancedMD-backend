package com.pat.controllers;

import java.util.List;
import java.util.Optional;

import com.pat.models.Appointment;
import com.pat.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/doctor/appointment")
@PreAuthorize("hasRole('ROLE_STAFF')")
public class AppointmentController {

  @Autowired
  private AppointmentRepository appointmentRepository;

  // Get all appointments
  @GetMapping
  public List<Appointment> getAllAppointments() {
    return appointmentRepository.findAll();
  }

  // Get appointment by ID
  @GetMapping("/{id}")
  public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
    Optional<Appointment> appointment = appointmentRepository.findById(id);
    return appointment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Create new appointment
  @PostMapping
  public  ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {

    return ResponseEntity.ok(appointmentRepository.save(appointment));

  }

  // Update appointment
  @PutMapping("/{id}")
  public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppointment) {
    return appointmentRepository.findByAppId(id).map(appointment -> {
      appointment.setPatientId(updatedAppointment.getPatientId());
      appointment.setDoctorId(updatedAppointment.getDoctorId());
      appointment.setRoomId(updatedAppointment.getRoomId());
      appointment.setAppDate(updatedAppointment.getAppDate());
      appointment.setStatus(updatedAppointment.getStatus());
      return ResponseEntity.ok(appointmentRepository.save(appointment));
    }).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Delete appointment
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
    if (appointmentRepository.existsById(id)) {
      appointmentRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
