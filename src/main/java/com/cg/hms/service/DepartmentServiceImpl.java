package com.cg.hms.service;

import java.util.ArrayList;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.cg.hms.entity.Department;
import com.cg.hms.entity.Physician;
import com.cg.hms.entity.Procedures;
import com.cg.hms.entity.Trainedin;
import com.cg.hms.exception.DuplicateEntryException;
import com.cg.hms.exception.NoEntryException;
import com.cg.hms.exception.NoRecordsException;
import com.cg.hms.exception.NoSuchElementException;
import com.cg.hms.repository.DepartmentRepository;
import com.cg.hms.repository.PhysicianRepository;
import com.cg.hms.repository.ProcedureRepository;
import com.cg.hms.repository.TrainedinRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	private DepartmentRepository departmentRepository ;
	private PhysicianRepository physicianRepository ;
	private TrainedinRepository trainedinRepository ;
	
	
   @Autowired
	public void DepartmentServiceI(DepartmentRepository departmentRepository,PhysicianRepository physicianRepository,TrainedinRepository trainedinRepository) {
		this.departmentRepository=departmentRepository;
		this.physicianRepository=physicianRepository;
		this.trainedinRepository=trainedinRepository;
	
	}

	@Override
	//1
	public Department saveDepartment(Department department) {
		if(departmentRepository.findById(department.getDepartmentId())!= null)
		throw new DuplicateEntryException("");
		return departmentRepository.save(department);
	}

	@Override
	//2
	public List<Department> getallDepartments() {
		
		List<Department> departments = departmentRepository.findAll();
		if(departments.isEmpty())
		throw new NoRecordsException("");
		return departments;
	}

	@Override
	//3
	public Department getDepartmentDetailByDeptId(Integer departmentid) {
		
		return departmentRepository.findById(departmentid).orElseThrow(()-> new NoSuchElementException("department not found"+departmentid));
	}

	@Override
	//4
	public Physician getHeadOfDepartmentDetails(Integer depid) {
		return departmentRepository.findbyhe(depid).orElseThrow(()-> new NoSuchElementException(""));
	}

	@Override
	  public List<Trainedin> getHeadCertificationDetailByDeptId(Integer departmentid)
	  { 
	  Physician phy = departmentRepository.findbyhe(departmentid).get();
	  List<Trainedin> certifications=trainedinRepository.findByPhysician(phy);
	  return certifications; 
	  }
	@Override
	//6
	public List<Department> getDepartmentByHeadId(Integer head) {
		Physician phy = physicianRepository.findById(head).get();
		return departmentRepository.findByHead(head);
	}

	@Override
	//7
	public Boolean PhysicianIsHeadOfAnyDepartmentOrNot(Integer physicianid) {
		List<Department> list = getDepartmentByHeadId(physicianid);
		if(list.isEmpty())
		throw new NoEntryException("");
		return true;
	}

	@Override
	//8
	public Department updateDepartmentHeadId(Department dep, Integer departmentid) {
		Department department=departmentRepository.findById(departmentid).orElseThrow(()->new NoSuchElementException("department not found"));
		department.setHead(dep.getHead());
		return departmentRepository.save(department);
		
	}

	@Override
	//9
	public Department updateNameOfDepartment(Integer departmentid,Department dep) {
		Department department=departmentRepository.findById(departmentid).orElseThrow(()->new NoSuchElementException("department not found"));
		department.setName(dep.getName());
		return departmentRepository.save(department);
		
		
	}

	

}
