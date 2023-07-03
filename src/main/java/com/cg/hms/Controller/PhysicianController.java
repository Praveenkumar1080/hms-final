/*package com.cg.hms.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.hms.Entity.Physician;
@Controller
@RequestMapping(value="/api/physician")
public class PhysicianController {
private String str = null;
@GetMapping(value="/{pid}")
public ResponseEntity<String> savePhysician(@RequestBody Physician obj){
	str = "Record Created Successfully";
	return new ResponseEntity<String>(str,HttpStatus.OK);
}
@PostMapping
public ResponseEntity<String> savePhysician(@RequestBody Physician obj){
	str = "Record Created Successfully";
	return new ResponseEntity<String>(str,HttpStatus.OK);
}
@PostMapping
public ResponseEntity<String> savePhysician(@RequestBody Physician obj){
	str = "Record Created Successfully";
	return new ResponseEntity<String>(str,HttpStatus.OK);
}

}*/
