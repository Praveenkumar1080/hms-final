package com.cg.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hms.entity.Physician;
import com.cg.hms.entity.Procedures;
import com.cg.hms.entity.TrainedId;
import com.cg.hms.entity.Trainedin;


@Repository
public interface TrainedinRepository extends JpaRepository<Trainedin, TrainedId>{

	public List<Trainedin> findByPhysician(Physician phy);

	public List<Trainedin> findByTreatment(Procedures phy);
	
	

}
