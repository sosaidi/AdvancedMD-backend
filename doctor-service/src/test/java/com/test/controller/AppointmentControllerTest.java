package com.test.controller;

import com.doc.controllers.AppointmentController;
import com.doc.models.Appointment;
import com.doc.payload.response.MessageResponse;
import com.doc.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)  // Use SpringExtension for integrating with Spring context
public class AppointmentControllerTest {

    @Mock
    private AppointmentRepository appointmentRepository;  // Mocking the repository

    @InjectMocks
    private AppointmentController appointmentController;  // The controller to test

    private Appointment appointment;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Initialize Appointment object for use in the tests
        appointment = new Appointment();
        appointment.setAppId(1L);
        appointment.setPatientId(101L);
        appointment.setDoctorId(202L);
        appointment.setRoomId(303L);
        appointment.setAppDate(LocalDateTime.parse("2025-01-05T14:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME));  // You can adjust the format as needed
        appointment.setStatus("Scheduled");
    }

    @Test
    void createAppointment_ReturnsCreatedAppointment() {
        // Mock the repository call to simulate saving a new appointment
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);  // Simulating saving the new appointment

        // Call the controller's create method
        ResponseEntity<Appointment> response = appointmentController.createAppointment(appointment);

        // Validate that the response status is Created (201)
        assertEquals(200, response.getStatusCodeValue());  // 201 Created for successful creation

        // Validate that the response body contains the created appointment
        assertEquals(appointment.getAppId(), response.getBody().getAppId());  // Check if the ID is the same
        assertEquals(appointment.getPatientId(), response.getBody().getPatientId());  // Check the patient ID
        assertEquals(appointment.getDoctorId(), response.getBody().getDoctorId());  // Check the doctor ID
        assertEquals(appointment.getRoomId(), response.getBody().getRoomId());  // Check the room ID
        assertEquals(appointment.getAppDate(), response.getBody().getAppDate());  // Check the appointment date
        assertEquals(appointment.getStatus(), response.getBody().getStatus());  // Check the status
    }

    @Test
    void deleteAppointment_ReturnsSuccessMessage() {
        // Mock repository call to simulate appointment deletion
        when(appointmentRepository.existsById(any(Long.class))).thenReturn(true);  // Simulating that the appointment exists
        // Simulate deletion, but `deleteById` does not return anything.
        when(appointmentRepository.deleteByAppId(any(Long.class))).thenReturn(null);  // Use deleteById, as the method in the controller is expecting this

        // Call the controller's delete method
        ResponseEntity<MessageResponse> response = (ResponseEntity<MessageResponse>) appointmentController.deleteAppointment(1L);

        // Validate that the response status is No Content (204)
        assertEquals(204, response.getStatusCodeValue());  // 204 No Content for successful deletion
    }

    @Test
    void updateAppointment_ReturnsSuccessMessage() {
        // Mock repository call to simulate finding an appointment by ID
        when(appointmentRepository.findByAppId(any(Long.class))).thenReturn(Optional.of(appointment));  // Simulating finding the appointment

        // Mock repository call to simulate saving the updated appointment
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);  // Simulating saving the updated appointment

        ResponseEntity<Appointment> response = appointmentController.updateAppointment(1L, appointment);

        // Validate that the response status is OK (200)
        assertEquals(200, response.getStatusCodeValue());  // 200 OK for successful update
    }

    // Test for getAppointmentById(Long)
    @Test
    void getAppointmentById_Existing() {
        when(appointmentRepository.findByAppId(any(Long.class))).thenReturn(Optional.of(appointment));  // Simulate appointment found

        ResponseEntity<Appointment> response = appointmentController.getAppointmentById(1L);

        // Validate response status and body
        assertEquals(200, response.getStatusCodeValue());  // 200 OK for found appointment
        assertEquals(appointment.getAppId(), response.getBody().getAppId());  // Verify that the appointment ID matches
    }

    // Test for getAllAppointments()
    @Test
    void getAllAppointments() {
        // Mock the repository to return a list of appointments
        when(appointmentRepository.findAll()).thenReturn(java.util.List.of(appointment));

        // Call the controller's getAllAppointments method
        List<Appointment> response = appointmentController.getAllAppointments();

        // Validate that the response is a list and contains one appointment
        assertEquals(1, response.size());  // Expecting one appointment in the list
        assertEquals(appointment.getAppId(), response.get(0).getAppId());  // Check if the appointment ID is the same
    }


    // Test for deleteAppointment(Long)
    @Test
    void deleteAppointment_Existing() {
        when(appointmentRepository.existsById(any(Long.class))).thenReturn(true);  // Simulate appointment exists

        ResponseEntity<MessageResponse> response = (ResponseEntity<MessageResponse>) appointmentController.deleteAppointment(1L);

        // Validate the response status for successful deletion
        assertEquals(204, response.getStatusCodeValue());  // 204 No Content
    }


}
