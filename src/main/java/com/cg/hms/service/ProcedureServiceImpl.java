package com.cg.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hms.entity.Procedures;
import com.cg.hms.exception.DuplicateEntryException;
import com.cg.hms.exception.NoRecordsException;
import com.cg.hms.repository.AffiliatedwithRepository;
import com.cg.hms.repository.ProcedureRepository;

@Service
public class ProcedureServiceImpl implements ProcedureService{
	
	private ProcedureRepository procedureRepository;
	
	@Autowired
	public void ProcedureService( ProcedureRepository procedureRepository){
		this.procedureRepository=procedureRepository;
	}

	@Override
	public Procedures saveProcedure(Procedures procedure) {
	if(procedureRepository.findById(procedure.getCode()).get()!=null)
	throw new DuplicateEntryException("");
	return procedureRepository.save(procedure);
		
	}

	@Override
	public List<Procedures> getAllProcedures() {
		
		List<Procedures> procedures = procedureRepository.findAll();
		if(procedures.isEmpty())
		throw new NoRecordsException("");
		return procedures;
	}

	@Override
	public Procedures getCostOfProcedureById(Integer id) {
		return procedureRepository.findById(id).orElseThrow(()-> new NoRecordsException(""));
		
	}

	@Override
	public Procedures getCostOfProcedureByName(String name) {
		return procedureRepository.findByName(name).orElseThrow(()-> new NoRecordsException(""));
	}

	@Override
	public Procedures updateCostOfProcedure(Integer id,Procedures pd) {
		Procedures procedure=procedureRepository.findById(id).orElseThrow(()-> new NoRecordsException(""));
		procedure.setCost(pd.getCost());
	    return procedureRepository.save(procedure);
	}

	@Override
	public Procedures updateNameOfProcedureB(Integer procedureId, Procedures pd) {
		Procedures procedure=procedureRepository.findById(procedureId).orElseThrow(()-> new NoRecordsException(""));
	    procedure.setName(pd.getName());
	    return procedureRepository.save(procedure);
	}

}
