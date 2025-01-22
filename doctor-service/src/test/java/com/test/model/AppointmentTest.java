package com.test.model;


import com.doc.models.Appointment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class AppointmentTest {

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        // Initialize the Appointment object
        appointment = new Appointment(1L, 101L, 201L, LocalDateTime.of(2025, 1, 10, 14, 0), "Scheduled");
    }

    @Test
    void testConstructorAndGetters() {
        // Test constructor and getters
        assertThat(appointment.getPatientId()).isEqualTo(1L);
        assertThat(appointment.getDoctorId()).isEqualTo(101L);
        assertThat(appointment.getRoomId()).isEqualTo(201L);
        assertThat(appointment.getAppDate()).isEqualTo(LocalDateTime.of(2025, 1, 10, 14, 0));
        assertThat(appointment.getStatus()).isEqualTo("Scheduled");
    }

    @Test
    void testSetters() {
        // Test setters
        appointment.setPatientId(2L);
        appointment.setDoctorId(102L);
        appointment.setRoomId(202L);
        appointment.setAppDate(LocalDateTime.of(2025, 1, 11, 15, 0));
        appointment.setStatus("Completed");

        assertThat(appointment.getPatientId()).isEqualTo(2L);
        assertThat(appointment.getDoctorId()).isEqualTo(102L);
        assertThat(appointment.getRoomId()).isEqualTo(202L);
        assertThat(appointment.getAppDate()).isEqualTo(LocalDateTime.of(2025, 1, 11, 15, 0));
        assertThat(appointment.getStatus()).isEqualTo("Completed");
    }

    @Test
    void testDefaultConstructor() {
        // Test default constructor
        Appointment defaultAppointment = new Appointment();
        defaultAppointment.setPatientId(3L);
        defaultAppointment.setDoctorId(103L);
        defaultAppointment.setRoomId(203L);
        defaultAppointment.setAppDate(LocalDateTime.of(2025, 1, 12, 16, 0));
        defaultAppointment.setStatus("Canceled");

        assertThat(defaultAppointment.getPatientId()).isEqualTo(3L);
        assertThat(defaultAppointment.getDoctorId()).isEqualTo(103L);
        assertThat(defaultAppointment.getRoomId()).isEqualTo(203L);
        assertThat(defaultAppointment.getAppDate()).isEqualTo(LocalDateTime.of(2025, 1, 12, 16, 0));
        assertThat(defaultAppointment.getStatus()).isEqualTo("Canceled");
    }

    @Test
    void testAppId() {
        // Test appId
        appointment.setAppId(1001L);
        assertThat(appointment.getAppId()).isEqualTo(1001L);
    }

    @Test
    void testAppointmentDate() {
        // Test appointment date
        LocalDateTime newDate = LocalDateTime.of(2025, 1, 13, 16, 30);
        appointment.setAppDate(newDate);

        assertThat(appointment.getAppDate()).isEqualTo(newDate);
    }

    @Test
    void testStatus() {
        // Test status
        appointment.setStatus("Postponed");
        assertThat(appointment.getStatus()).isEqualTo("Postponed");
    }
}
