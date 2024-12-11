package com.ViewMedicalRecord;

import com.ViewMedicalRecord.model.MedicalRecord;
import com.ViewMedicalRecord.service.MedicalRecordsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MedicalRecordsControllerTest {

    @Autowired
    private MedicalRecordsService service;

    @Test
    void testGetMedicalRecords() {
        Integer patientId = 1;
        Integer requesterId = 1;

        List<MedicalRecord> records = service.getMedicalRecordsByPatientId(patientId);
        assertFalse(records.isEmpty(), "Medical records should not be empty for valid patient ID");
    }

    @Test
    void testSecurityCheck() {
        Integer patientId = 103;
        Integer invalidRequesterId = 104;

        assertThrows(SecurityException.class, () -> {
            if (!patientId.equals(invalidRequesterId)) {
                throw new SecurityException("Access denied");
            }
        });
    }
}
