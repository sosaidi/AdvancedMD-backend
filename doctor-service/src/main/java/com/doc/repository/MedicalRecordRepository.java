package com.doc.repository;



import com.doc.models.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {

    List<MedicalRecord> findByPatientPatientId(int patientId);



    MedicalRecord getMedicalRecordByRecordId(int i);
}
