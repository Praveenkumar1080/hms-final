package com.cg.hms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hms.entity.Affiliatedwith;
import com.cg.hms.entity.Department;
import com.cg.hms.entity.Physician;
import com.cg.hms.exception.NoEntryException;
import com.cg.hms.exception.NoSuchElementException;
import com.cg.hms.repository.AffiliatedwithRepository;
import com.cg.hms.repository.DepartmentRepository;
import com.cg.hms.repository.PhysicianRepository;

@Service
public class AffiliatedServiceImpl implements AffiliatedService{
	
	public AffiliatedwithRepository affiliatedwithRepository;
	public PhysicianRepository physicianRepository;
	public DepartmentRepository departmentRepository;
	
	@Autowired
	public void PhysicianService(PhysicianRepository physicianRepository, AffiliatedwithRepository affiliatedwithRepository,DepartmentRepository departmentRepository) {
		this.physicianRepository=physicianRepository;
		this.affiliatedwithRepository=affiliatedwithRepository;
		this.departmentRepository=departmentRepository;
	}
	
	@Override
	public void saveAffiliatedwith(int id) {
		Affiliatedwith object = new Affiliatedwith();
		Physician phy = physicianRepository.findById(id).orElseThrow(()-> new NoSuchElementException("physician with given id not found"));
		object.setPhysician(phy);
		affiliatedwithRepository.save(object);	
	}

	@Override
	public List<Physician> getPhysiciansByDepartment(int departmentId) {
		/*Department dep = departmentRepository.findById(departmentId).orElseThrow(()-> new NoSuchElementException("department with given id not found"));
		List<Affiliatedwith> affiliations = affiliatedwithRepository.findByDepartment(dep);
		if(affiliations.isEmpty())
		throw new NoEntryException("no record in the table for given department");
		List<Physician> physicians = affiliations.stream().map(x->x.getPhysician()).collect(Collectors.toList());
		return physicians;*/
		return affiliatedwithRepository.findByDepId(departmentId);
		
	}

	@Override
	public List<Department> getDepartmentsByPhysician(Integer physicianId) {
		Physician dep = physicianRepository.findById(physicianId).orElseThrow(()-> new NoSuchElementException("no record with id "+physicianId));
		List<Affiliatedwith> affiliations = affiliatedwithRepository.findByPhysician(dep);
		if(affiliations.isEmpty())
		throw new NoEntryException("no record in the table for given physician");
		return affiliations.stream().map(x->x.getDepartment()).collect(Collectors.toList());
	}

	@Override
	public Integer countPhysiciansByDepartment(Integer departmentId) {
		Department dep = departmentRepository.findById(departmentId).orElseThrow(()-> new NoSuchElementException("physician with given id not found"));
		return affiliatedwithRepository.countByDepartment(dep);
	}

	@Override
	public Boolean getPrimaryAffiliation(Integer physicianId) {
		Physician phy = physicianRepository.findById(physicianId).orElseThrow(()-> new NoSuchElementException("physician with given id not found"));
		List<Affiliatedwith> p = affiliatedwithRepository.findByPhysician(phy);
		if(p.isEmpty())
		throw new NoEntryException("no record in the table for given physician");
	    return p.stream().anyMatch(a-> a.getPrimaryAffiliation());
}
	@Override
	public Boolean updatePrimaryAffiliation(Integer physicianid,Affiliatedwith pff) {
	Physician phy = physicianRepository.findById(physicianid).orElseThrow(()-> new NoSuchElementException("physician with given id not found"));
	List<Affiliatedwith> p = affiliatedwithRepository.findByPhysician(phy);
	if(p.isEmpty())
	throw new NoEntryException("no record in the table for given physician");
	p.stream().filter((a)-> a.getPrimaryAffiliation()).forEach((a)-> a.setPrimaryAffiliation(pff.getPrimaryAffiliation()));
	return true;
		
	}
	
	

}
