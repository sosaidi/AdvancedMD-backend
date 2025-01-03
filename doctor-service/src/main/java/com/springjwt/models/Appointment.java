package com.springjwt.models;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long appId;

  @Column(name = "patient_id", nullable = false)
  private Long patientId;

  @Column(name = "doctor_id", nullable = false)
  private Long doctorId;

  @Column(name = "room_id")
  private Long roomId;

  @Column(name = "app_date", nullable = false)
  private LocalDateTime appDate;

  @Column(name = "status", nullable = false)
  private String status;

  // Constructors
  public Appointment() {}

  public Appointment(Long patientId, Long doctorId, Long roomId, LocalDateTime appDate, String status) {
    this.patientId = patientId;
    this.doctorId = doctorId;
    this.roomId = roomId;
    this.appDate = appDate;
    this.status = status;
  }

  // Getters and Setters
  public Long getAppId() {
    return appId;
  }

  public void setAppId(Long appId) {
    this.appId = appId;
  }

  public Long getPatientId() {
    return patientId;
  }

  public void setPatientId(Long patientId) {
    this.patientId = patientId;
  }

  public Long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Long doctorId) {
    this.doctorId = doctorId;
  }

  public Long getRoomId() {
    return roomId;
  }

  public void setRoomId(Long roomId) {
    this.roomId = roomId;
  }

  public LocalDateTime getAppDate() {
    return appDate;
  }

  public void setAppDate(LocalDateTime appDate) {
    this.appDate = appDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
