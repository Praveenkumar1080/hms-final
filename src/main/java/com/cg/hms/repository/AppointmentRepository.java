package com.cg.hms.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hms.entity.Appointment;
import com.cg.hms.entity.Nurse;
import com.cg.hms.entity.Patient;
import com.cg.hms.entity.Physician;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

	public List<Appointment> findByStartTime(Timestamp startdate);

	public List<Appointment> findByPatient(Patient pat);
	
	public List<Appointment> findByPhysician(Physician phy);

	public List<Appointment> findByNurse(Nurse nur); 

}
