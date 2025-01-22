package com.test.model;


import com.doc.models.ToDos;
import com.doc.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDosTest {

    private ToDos toDo;
    private User doctor;

    @BeforeEach
    void setUp() {
        // Initialize the ToDos object before each test
        toDo = new ToDos();

        // Initialize the User object (doctor)
        doctor = new User(); // assuming a simple constructor or mock this class
    }

    @Test
    void testGetSetId() {
        // Test ID getter and setter
        toDo.setId(1L);
        assertEquals(1L, toDo.getId());
    }

    @Test
    void testGetSetTask() {
        // Test task getter and setter
        String task = "Complete patient report";
        toDo.setTask(task);
        assertEquals(task, toDo.getTask());
    }

    @Test
    void testGetSetPriority() {
        // Test priority getter and setter
        String priority = "High";
        toDo.setPriority(priority);
        assertEquals(priority, toDo.getPriority());
    }

    @Test
    void testGetSetCompleted() {
        // Test completed getter and setter
        toDo.setCompleted(true);
        assertEquals(true, toDo.isCompleted());
    }

    @Test
    void testGetSetDueDate() {
        // Test dueDate getter and setter
        LocalDate dueDate = LocalDate.of(2025, 1, 25);
        toDo.setDueDate(dueDate);
        assertEquals(dueDate, toDo.getDueDate());
    }

    @Test
    void testGetSetDoctor() {
        // Test doctor getter and setter
        toDo.setDoctor(doctor);
        assertEquals(doctor, toDo.getDoctor());
    }
}
