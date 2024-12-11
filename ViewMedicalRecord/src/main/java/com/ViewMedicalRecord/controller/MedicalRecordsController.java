package com.ViewMedicalRecord.controller;

import com.ViewMedicalRecord.model.MedicalRecord;
import com.ViewMedicalRecord.service.MedicalRecordsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordsController {

    private final MedicalRecordsService service;

    public MedicalRecordsController(MedicalRecordsService service) {
        this.service = service;
    }

    /**
     * Fetch all medical records for a specific patient.
     *
     * @param patientId ID of the patient requesting their records
     * @param requesterId ID of the requester (validated for secure access)
     * @return List of Medical Records
     */
    @GetMapping("/{patientId}")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecords(
            @PathVariable Integer patientId,
            @RequestParam("requesterId") Integer requesterId) {

        // Ensure the requester is the same as the patient for security
        if (!patientId.equals(requesterId)) {
            return ResponseEntity.status(403).build(); // Forbidden
        }

        List<MedicalRecord> records = service.getMedicalRecordsByPatientId(patientId);
        return ResponseEntity.ok(records);
    }
}
