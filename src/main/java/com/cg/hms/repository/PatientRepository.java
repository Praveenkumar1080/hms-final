package com.cg.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hms.entity.Patient;
import com.cg.hms.entity.Physician;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
public List<Patient> findByPcp(Physician physician);
}
