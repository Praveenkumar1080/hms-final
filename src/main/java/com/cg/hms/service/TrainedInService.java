package com.cg.hms.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.hms.entity.Physician;
import com.cg.hms.entity.Procedures;
import com.cg.hms.entity.Trainedin;

@Service
public interface TrainedInService {
	
	public void saveCertification(Trainedin certification);
	public void getCertification(int physicianId, int treatmentId, LocalDateTime certificationDate, LocalDateTime certificationExpires); 
	public List<Procedures> getProceduresWithCertification();
	public List<Procedures> getTreatmentsByPhysicianId(int physicianId);
	public List<Physician> getPhysiciansByTreatmentId(int treatmentId);
	public List<Procedures> getProceduresWithExpiringCertification();
	boolean updateCertificationExpiryDate(int physicianId, int procedureId, Trainedin obj);
	

}
