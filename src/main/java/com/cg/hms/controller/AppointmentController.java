
  package com.cg.hms.controller;
  
  
  
  import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.http.HttpStatus; import
  org.springframework.http.ResponseEntity; import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.PathVariable; import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestBody; import
  org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import
  org.springframework.web.bind.annotation.RequestParam; import
  org.springframework.web.bind.annotation.RestController;
  
  import com.cg.hms.entity.Appointment; import com.cg.hms.entity.Nurse; import
  com.cg.hms.entity.Patient; import com.cg.hms.entity.Physician; import
  com.cg.hms.entity.Room; import com.cg.hms.service.AppointmentServiceImpl;
  
  
  
  
  @RestController
  @RequestMapping(value="/api/appointment")
  public class AppointmentController{
  private AppointmentServiceImpl service;
  
  @Autowired public void setService(AppointmentServiceImpl service) {
  this.service = service;
  } //1
  
  @PostMapping(value="/")
  public ResponseEntity <String>saveApp(@RequestBody Appointment app) {
  service.saveAppointment(app);
  ResponseEntity<String>res = new ResponseEntity<String>("Successfully Created",HttpStatus.OK); return res;
  } //2
  
  @GetMapping public ResponseEntity <List<Appointment>>getAppointment() {
  List<Appointment> list = service.getallAppointments();
  ResponseEntity<List<Appointment>>res = new ResponseEntity<>(list,HttpStatus.OK);
  return res;
  } //3
  
  @GetMapping(value="/{startdate}")
  public ResponseEntity <List<Appointment>> getstartdate(@PathVariable Timestamp startdate) {
  List<Appointment> lis = service.getallAppointmentsByStartDate(startdate);
  ResponseEntity<List<Appointment>> res = new ResponseEntity<>(lis,HttpStatus.OK);
  return res;
  } //4
  @GetMapping(value="/physician/{appointmentid}") public
  ResponseEntity<Physician>searchbyid(@PathVariable int appointmentid) {
  Physician phy = service.getPhysicianDetailByAppointmentId(appointmentid);
  ResponseEntity<Physician>res = new ResponseEntity<>(phy,HttpStatus.OK);
  return res; } //6
  
  @GetMapping(value="/nurse/{appointmentid}")
  public ResponseEntity<Nurse> getNurseDeatailByAppointmentId(@PathVariable int appointmentid) { Nurse nur =
  service.getNurseDeatailByAppointmentId(appointmentid);
  ResponseEntity<Nurse>res = new ResponseEntity<>(nur,HttpStatus.OK); return
  res; } //7
  
  @GetMapping(value="/examinationroom/{appointmentid}") public ResponseEntity
  <String>SearchbyRoom(@PathVariable int appointmentid) { String r =
  service.getExaminationRoomByAppointmentId(appointmentid);
  ResponseEntity<String>res = new ResponseEntity<>(r,HttpStatus.OK); return
  res; } //8
  
  @RequestMapping(value="/physicians", method = RequestMethod.GET) 
  public ResponseEntity<List<Appointment>> getallPhysicianByid(@RequestParam("patientid") int patientid) {
  List<Appointment> list = service.getPhysiciansByPatientId(patientid);
  ResponseEntity<List<Appointment>>
  res = new ResponseEntity<>(list,HttpStatus.OK);
  return res; 
  }
@GetMapping("/patient")
  public ResponseEntity<List<Patient>> patientController(@RequestParam(required = false) int physicianId, 
	        @RequestParam(required = false) Integer nurseid,
	        @RequestParam(required = false) Integer patientid,
	        @RequestParam(required = false) Integer appointmentid,
	        @RequestParam(required = false) Timestamp date) {
	  Integer physicianid = Integer.valueOf(physicianId);
	  if(appointmentid != null ) {
		  Patient patients = service.getPatientInfoByAppointmentId(appointmentid);
		  ResponseEntity<List<Patient>> res = new ResponseEntity<>(Arrays.asList(patients),HttpStatus.OK);
		  return res;
	  }
	  else if(patientid != null && physicianid != null) {
    	  Patient pat = service.getPatientCheckedByPhysician(physicianid,patientid);
    	  ResponseEntity<List<Patient>> res = new ResponseEntity<>(Arrays.asList(pat),HttpStatus.OK); 
    	  return res;
	  }
	  else if(physicianid != null && date != null) {
		  List<Patient> list = service.getPatientsCheckedByPhysicianOnDate(physicianid, date);
		  ResponseEntity<List<Patient>> res = new ResponseEntity<>(list,HttpStatus.OK);
		  return res;  
	  }
	  else if(physicianid != null) {
		  List<Patient> list = service.getPatientsCheckedByPhysician(physicianId);
		  ResponseEntity<List<Patient>> res = new ResponseEntity<>(list,HttpStatus.OK);
		  return res;
	  }
      
      else if(nurseid != null && patientid != null) {
    	  Patient pat = service.getPatientCheckedByNurse(nurseid,patientid);
    	  ResponseEntity<List<Patient>> res = new ResponseEntity<>(Arrays.asList(pat),HttpStatus.OK);
    	  return res;
	  }
      else if(nurseid != null && date != null) {
    	  
    	  List<Patient> list = service.getPatientsCheckedByNurseOnDate(nurseid, date);
    	  ResponseEntity<List<Patient>> res = new ResponseEntity<>(list,HttpStatus.OK);
    	  return res;
	  }
      else if(nurseid != null) {
    	  List<Patient> list = service.getPatientsCheckedByNurse(nurseid);
    	  ResponseEntity<List<Patient>> res = new ResponseEntity<>(list,HttpStatus.OK);
    	  return res; 
	  }
	  return null;
  }
  //9

  @RequestMapping(value = "/physician", method=RequestMethod.GET)
  public ResponseEntity<Physician> getPhysicianbydate(@RequestParam("pid") int pid,@RequestParam("date")
  Timestamp date) { 
  Physician phy = service.getPhysicianDetailByPatientIdAndDate(pid,date);
  ResponseEntity<Physician> res = new ResponseEntity<>(phy,HttpStatus.OK);
  return res;
  } //10
  
  @GetMapping(value="/nurse/{patientid}") public ResponseEntity<List<Nurse>>
  getAllnursebyid(@RequestParam("patientid") int patientid) { List<Nurse> list =
  service.getNursesByPatientId(patientid); ResponseEntity<List<Nurse>> res =
  new ResponseEntity<>(list,HttpStatus.OK); return res;} //11
  
  @RequestMapping(value = "/nurse", method=RequestMethod.GET)
  public ResponseEntity<Nurse> getbypatientId(@RequestParam("physicianid") int physicianid,@RequestParam("date") Timestamp
  date) {
  Nurse nur = service.getPhysicianDetailByNurseIdAndDate(physicianid,date); 
  ResponseEntity<Nurse> res = new ResponseEntity<>(nur,HttpStatus.OK);
  return res;
  } //12
  
  @GetMapping(value="/date/{patientid}") public ResponseEntity<List<Timestamp>>
  listOfdates(@PathVariable int patientid) { List<Timestamp> list =
  service.getAppointmentDatesByPatientId(patientid);
  ResponseEntity<List<Timestamp>> res = new
  ResponseEntity<>(list,HttpStatus.OK); return res; } //13
  /*
  
  @GetMapping(value="/patient/{physicianid}") public
  ResponseEntity<List<Patient>> getbypatientid(@PathVariable int physicianid) {
  List<Patient> list = service.getPatientsCheckedByPhysician(physicianid);
  ResponseEntity<List<Patient>> res = new ResponseEntity<>(list,HttpStatus.OK);
  return res; 
  completed
  } //14
  
  @GetMapping(value="/patient/{pid}") public ResponseEntity<List<Patient>>
  getby(@PathVariable int pid,@RequestParam("date") Timestamp date) {
  List<Patient> list = service.getPatientsCheckedByPhysicianOnDate(pid, date);
  ResponseEntity<List<Patient>> res = new ResponseEntity<>(list,HttpStatus.OK);
  return res; 
  completed.
  } //15
  
  @GetMapping(value="/patient/{physicianid}") public ResponseEntity<Patient>
  getbypatie(@PathVariable int pid,@RequestParam("patientid") int patientid ) {
  Patient pat = service.getPatientCheckedByPhysician(pid,patientid);
  ResponseEntity<Patient> res = new ResponseEntity<>(pat,HttpStatus.OK); return
  res; 
  completed.
  } //16
  
  @GetMapping(value="/patient/{nid}") public ResponseEntity<List<Patient>>
  getby(@PathVariable int nid) { 
  List<Patient> list = service.getPatientsCheckedByNurse(nid);
  ResponseEntity<List<Patient>> res = new ResponseEntity<>(list,HttpStatus.OK);
  return res;
  } //17
  
  @GetMapping(value="/patient/{nid}") public ResponseEntity<Patient>
  getbypatient(@PathVariable int nid,@RequestParam("patientid") int patientid )
  Patient pat = service.getPatientCheckedByNurse(nid,patientid);
  ResponseEntity<Patient> res = new ResponseEntity<>(pat,HttpStatus.OK);
  return res;
  } //18
  
  @GetMapping(value="/patient/{nid}") public ResponseEntity<List<Patient>>
  getbynn(@PathVariable int pid,@RequestParam("date") Timestamp date) {
  List<Patient> list = service.getPatientsCheckedByPhysicianOnDate(pid, date);
  ResponseEntity<List<Patient>> res = new ResponseEntity<>(list,HttpStatus.OK);
  return res;
  } //19*/
  
  
  
  
  
  
  }
 