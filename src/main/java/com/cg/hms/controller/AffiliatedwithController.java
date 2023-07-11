package com.cg.hms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hms.entity.Affiliatedwith;
import com.cg.hms.entity.Department;
import com.cg.hms.entity.Physician;
import com.cg.hms.service.AffiliatedServiceImpl;

 


@RestController
@RequestMapping(value="/api/affiliated_with")
public class AffiliatedwithController{
 private AffiliatedServiceImpl service;
 @Autowired
 public void setService(AffiliatedServiceImpl service) {
	 this.service = service;
 }
@PostMapping(value="/post")
    public ResponseEntity<String> saveApp(@RequestParam("id") int id) {
	  service.saveAffiliatedwith(id);
      ResponseEntity<String>res = new ResponseEntity<String>("Successfully Created",HttpStatus.CREATED);
      return res;
}
@GetMapping(value="/physicians/{depid}")
     public ResponseEntity <List<Physician>>getsearchphysician(@PathVariable int depid) {
	 List<Physician> list = service.getPhysiciansByDepartment(depid);
     ResponseEntity<List<Physician>>res = new ResponseEntity<>(list,HttpStatus.OK);
     return res;
}
@GetMapping(value="/department/{physicianid}")
    public ResponseEntity<List<Department>>searchdepartment(@PathVariable int physicianid) {
	  List<Department> list = service.getDepartmentsByPhysician(physicianid);
      ResponseEntity<List<Department>>res = new ResponseEntity<>(list,HttpStatus.OK);
      return res;
}
@GetMapping(value="/countphysician/{depid}")
    public ResponseEntity<Integer> countphysician(@PathVariable int depid) {
      Integer count = service.countPhysiciansByDepartment(depid);
      ResponseEntity<Integer> res = new ResponseEntity<Integer>(count,HttpStatus.OK);
      return res;
}
@GetMapping(value="/{physicianid}")
    public ResponseEntity<Boolean> searchafiliation(@PathVariable int physicianid) {
    Boolean b = service.getPrimaryAffiliation(physicianid); 
	ResponseEntity<Boolean>res = new ResponseEntity<>(b,HttpStatus.OK);
	return res;
}
@RequestMapping(method = RequestMethod.PUT,value="/primary")
    public ResponseEntity <Boolean> updateafiliation(@RequestBody Affiliatedwith aff,@RequestParam("physicianid")int physicianid) {
    Boolean b = service.updatePrimaryAffiliation(physicianid, aff);
	ResponseEntity<Boolean> res = new ResponseEntity<>(b,HttpStatus.OK);
	return res;
  }
}