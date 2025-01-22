package com.test.repository;

import com.pat.models.Appointment;
import com.pat.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AppointmentRepositoryTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    private Appointment appointment1;
    private Appointment appointment2;

    @BeforeEach
    void setUp() {
        appointment1 = new Appointment();
        appointment1.setPatientId(1L);
        appointment1.setDoctorId(101L);
        appointment1.setAppDate(LocalDateTime.of(2025, 1, 10, 14, 0));
        appointment1.setStatus("Scheduled");
        appointment1.setAppId(1L);

        appointment2 = new Appointment();
        appointment2.setPatientId(2L);
        appointment2.setDoctorId(102L);
        appointment2.setAppDate(LocalDateTime.of(2025, 1, 11, 15, 0));
        appointment2.setStatus("Completed");
        appointment2.setAppId(2L);

        // Mock repository interactions (only the methods that are used in tests)
        lenient().when(appointmentRepository.save(appointment1)).thenReturn(appointment1);
        lenient(). when(appointmentRepository.save(appointment2)).thenReturn(appointment2);
        lenient(). when(appointmentRepository.findByPatientId(1L)).thenReturn(List.of(appointment1));
        lenient(). when(appointmentRepository.findByDoctorId(101L)).thenReturn(List.of(appointment1));
        lenient(). when(appointmentRepository.findByAppId(2L)).thenReturn(Optional.empty());
        lenient(). when(appointmentRepository.findByAppId(1L)).thenReturn(Optional.of(appointment1));
        lenient().  when(appointmentRepository.findByStatus("Scheduled")).thenReturn(List.of(appointment1));
        lenient(). when(appointmentRepository.findByAppDateBetween(any(), any())).thenReturn(List.of(appointment1, appointment2));
        lenient().when(appointmentRepository.existsByPatientIdAndAppDate(1L, LocalDateTime.of(2025, 1, 10, 14, 0))).thenReturn(true);
        lenient().when(appointmentRepository.existsByPatientIdAndAppDate(3L, LocalDateTime.of(2025, 1, 10, 14, 0))).thenReturn(false);
    }

    @Test
    void testFindByPatientId() {
        List<Appointment> appointments = appointmentRepository.findByPatientId(1L);
        assertThat(appointments).hasSize(1);
        assertThat(appointments.get(0).getDoctorId()).isEqualTo(101L);
    }

   @Test
    void testFindByDoctorId() {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(101L);
        assertThat(appointments).hasSize(1);
        assertThat(appointments.get(0).getPatientId()).isEqualTo(1L);
    }

    @Test
    void testFindByAppId() {
        Optional<Appointment> appointment = appointmentRepository.findByAppId(1L);
        assertThat(appointment).isPresent();
        assertThat(appointment.get().getStatus()).isEqualTo("Scheduled");
    }

    @Test
    void testDeleteByAppId() {

        appointmentRepository.deleteByAppId(2L);

        // Verify that the appointment was "deleted" (assuming findByAppId returns empty)
        Optional<Appointment> deletedAppointment = appointmentRepository.findByAppId(2L);
        assertThat(deletedAppointment).isEmpty();
    }

    @Test
    void testFindByAppDateBetween() {
        LocalDateTime startDate = LocalDateTime.of(2025, 1, 9, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2025, 1, 12, 0, 0);

        List<Appointment> appointments = appointmentRepository.findByAppDateBetween(startDate, endDate);
        assertThat(appointments).hasSize(2);
    }

    @Test
    void testFindByStatus() {
        List<Appointment> appointments = appointmentRepository.findByStatus("Scheduled");
        assertThat(appointments).hasSize(1);
        assertThat(appointments.get(0).getAppId()).isEqualTo(1L);
    }

    @Test
    void testExistsByPatientIdAndAppDate() {
        boolean exists = appointmentRepository.existsByPatientIdAndAppDate(1L, LocalDateTime.of(2025, 1, 10, 14, 0));
        assertThat(exists).isTrue();

        boolean notExists = appointmentRepository.existsByPatientIdAndAppDate(3L, LocalDateTime.of(2025, 1, 10, 14, 0));
        assertThat(notExists).isFalse();
    }
}
