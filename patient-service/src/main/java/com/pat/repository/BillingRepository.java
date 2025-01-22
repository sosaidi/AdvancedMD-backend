package com.pat.repository;



import com.pat.models.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Integer> {

    List<Billing> findByPatientPatientId(int patientId);
}

