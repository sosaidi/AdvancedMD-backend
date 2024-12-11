package com.ViewMedicalRecord.service;

import com.ViewMedicalRecord.exception.RecordNotFoundException;
import com.ViewMedicalRecord.model.MedicalRecord;
import com.ViewMedicalRecord.repository.MedicalRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordsService {

    private final MedicalRecordRepository repository;

    public MedicalRecordsService(MedicalRecordRepository repository) {
        this.repository = repository;
    }

    /**
     * Fetch all medical records for a specific patient.
     *
     * @param patientId ID of the patient
     * @return List of Medical Records
     */
    public List<MedicalRecord> getMedicalRecordsByPatientId(Integer patientId) {
        List<MedicalRecord> records = repository.findByPatientId(patientId);
        if (records.isEmpty()) {
            throw new RecordNotFoundException("No medical records found for patient ID: " + patientId);
        }
        return records;
    }
}
