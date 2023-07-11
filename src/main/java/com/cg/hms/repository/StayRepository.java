package com.cg.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hms.entity.Patient;
import com.cg.hms.entity.Stay;

@Repository
public interface StayRepository extends JpaRepository<Stay, Integer>{

	List<Stay> findByPatient(Patient patient);
	
}
