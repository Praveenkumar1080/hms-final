package com.cg.hms.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hms.entity.Appointment;
import com.cg.hms.entity.Nurse;
import com.cg.hms.entity.Patient;
import com.cg.hms.entity.Physician;
import com.cg.hms.entity.Room;
import com.cg.hms.entity.Stay;
import com.cg.hms.exception.NoSuchElementException;
import com.cg.hms.repository.AppointmentRepository;
import com.cg.hms.repository.NurseRepository;
import com.cg.hms.repository.PatientRepository;
import com.cg.hms.repository.PhysicianRepository;
import com.cg.hms.repository.StayRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	private AppointmentRepository appointmentRepository;
	private PatientRepository patientRepository;
	private PhysicianRepository physicianRepository;
	private NurseRepository nurseRepository;
	private StayRepository stayRepository;
	@Autowired
	public void setRepo(StayRepository stayRepo) {
		this.stayRepository = stayRepo;
	}
	@Autowired
	public void setNurrepo(NurseRepository nurrepo) {
		this.nurseRepository = nurrepo;
	}
	@Autowired
	public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}
	@Autowired
	public void setService(PatientRepository patientrepo) {
		this.patientRepository = patientrepo;
	}
	@Autowired
	public void setService(PhysicianRepository phyrepo) {
		this.physicianRepository = phyrepo;
	}

	@Override
	public Appointment saveAppointment(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public List<Appointment> getallAppointments() {
		
		return appointmentRepository.findAll();
    }
	
	@Override
	public List<Appointment> getallAppointmentsByStartDate(Timestamp startdate) {
		
		
		List<Appointment> appointments = appointmentRepository.findByStartTime(startdate);
		return appointments;
    }
    

	@Override
	public Patient getPatientInfoByAppointmentId(Integer appointementId) {
		 return appointmentRepository.findById(appointementId).orElseThrow(()-> new NoSuchElementException("n")).getPatient();
	    }

	@Override
	public Physician getPhysicianDetailByAppointmentId(int appointmentId) {
		return appointmentRepository.findById(appointmentId).orElseThrow(()-> new NoSuchElementException("")).getPhysician();
    }
	
	@Override
	public Nurse getNurseDeatailByAppointmentId(int appointmentId) {
		 Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
	        if (appointment != null) {
	            return appointment.getNurse();
	        }
	        return null;
	    }

	@Override
	public String getExaminationRoomByAppointmentId(int appointmentId) {
		 return appointmentRepository.findById(appointmentId).orElseThrow(()-> new NoSuchElementException("")).getExaminationRoom();
	    }
	

	@Override
	public List<Appointment> getPhysiciansByPatientId(int patientId) {
		Patient pat = patientRepository.findById(patientId).get();
		/*
		List<Appointment> app = appointmentRepository.findByPatient(pat);
		List<Physician> physicians = new ArrayList<Physician>();
		for(Appointment i:app) {
			physicians.add(i.getPhysician());
		}
		List<Physician> physicians = app.stream().map(p -> p.getPhysician()).collect(Collectors.toList());
        return physicians;*/
		return appointmentRepository.findByPatient(pat);
	}

	@Override
	public Physician getPhysicianDetailByPatientIdAndDate(int patientId, Timestamp date) {
		Patient pat = patientRepository.findById(patientId).get();
		List<Appointment> app = appointmentRepository.findByPatient(pat);
		/*for(Appointment i:app) {
			if(i.getStartTime().compareTo(date)==0) {
				phy = i.getPhysician();
				break;
			}
		}*/
		Physician physician = app.stream().filter(p -> p.getStartTime().compareTo(date)==0)
				.map(p -> p.getPhysician()).findFirst().get(); 
		return physician;
    }

	@Override
	public List<Nurse> getNursesByPatientId(int patientId) {
		Patient pat = patientRepository.findById(patientId).get();
		List<Appointment> appointments = appointmentRepository.findByPatient(pat);
		return appointments.stream().map(x->x.getNurse()).collect(Collectors.toList());
    }
	

	@Override
	public Nurse getPhysicianDetailByNurseIdAndDate(int patientId, Timestamp date) {
		Patient pat = patientRepository.findById(patientId).get();
		List<Appointment> appointments = appointmentRepository.findByPatient(pat);
		return appointments.stream().filter(x->x.getStartTime().compareTo(date)==0).map(x->x.getNurse()).findFirst().get();
	    }
	

	@Override
	public List<Timestamp> getAppointmentDatesByPatientId(int patientId) {
		Patient patient = patientRepository.findById(patientId).get();
		List<Appointment> appointments = appointmentRepository.findByPatient(patient);
        return appointments.stream().map(x->x.getStartTime()).collect(Collectors.toList());
    }
		

	@Override
	public List<Patient> getPatientsCheckedByPhysician(int physicianId) {
		Physician physician = physicianRepository.findById(physicianId).get();
		List<Appointment> appointments = appointmentRepository.findByPhysician(physician);
		return appointments.stream().map(x->x.getPatient()).collect(Collectors.toList());
    }

	@Override
	public List<Patient> getPatientsCheckedByPhysicianOnDate(int physicianId, Timestamp date) {
		Physician phy = physicianRepository.findById(physicianId).get();
		List<Appointment> app = appointmentRepository.findByPhysician(phy);
		List<Patient> patients = new ArrayList<Patient>();
		for(Appointment i:app) {
			if(i.getStartTime().compareTo(date)==0) {
			patients.add(i.getPatient());
		}
		}
        return null;
	}
	

	@Override
	public Patient getPatientCheckedByPhysician(int patientId, Integer physicianId) {
		Physician phy = physicianRepository.findById(physicianId).get();
		Patient pat = patientRepository.findById(patientId).get();
		List<Appointment> app = appointmentRepository.findByPhysician(phy);
		Patient patient = null;
		for(Appointment i:app) {
			if(i.getPatient()==pat) {
			patient = i.getPatient();
			break;
		}
		}
        return patient;
    }
	

	@Override
	public List<Patient> getPatientsCheckedByNurse(int nurseId) {
		Nurse nur = nurseRepository.findById(nurseId).get();
		List<Appointment> app = appointmentRepository.findByNurse(nur);
		List<Patient> patients = new ArrayList<>();
		for(Appointment i:app) {
			patients.add(i.getPatient());
		}
        return patients;
    }
	@Override
	public Patient getPatientCheckedByNurse(int nurseid,int patientId) {
		Nurse nur = nurseRepository.findById(nurseid).get();
		Patient pat = patientRepository.findById(patientId).get();
		List<Appointment> app = appointmentRepository.findByNurse(nur);
		Patient patient = null;
		for(Appointment i:app) {
			if(i.getPatient()==pat) {
			patient = i.getPatient();
			break;
		}
		}
        return patient;
    }
	

	@Override
	public List<Patient> getPatientsCheckedByNurseOnDate(int nurseId, Timestamp date) {
		Nurse nur = nurseRepository.findById(nurseId).get();
		List<Appointment> app = appointmentRepository.findByNurse(nur);
		List<Patient> patient = new ArrayList<>();
		for(Appointment i:app) {
			if(i.getStartTime().compareTo(date)==0) {
             patient.add(i.getPatient());
		}
		}
        return patient;
    }
	
	@Override
	public Room getRoomDetailsByPatientIdAndDate(int patientId, Timestamp date) {
		Patient patient = patientRepository.findById(patientId).get();
		List<Stay> stay = stayRepository.findByPatient(patient);
		return stay.stream().filter(x->x.getStartTime().compareTo(date)==0).map(x->x.getRoom()).findFirst().get();
		
    
	}

	@Override
	public List<Room> getRoomDetailsByPhysicianIdAndDate(int physicianId, Timestamp date) {
		Physician phy = physicianRepository.findById(physicianId).get();
		List<Appointment> appointments = appointmentRepository.findByPhysician(phy);
		Patient patient = appointments.stream().filter(x->x.getStartTime().compareTo(date)==0).map(x->x.getPatient()).findAny().get();
		List<Stay> stay = stayRepository.findByPatient(patient);
		return stay.stream().filter(x->x.getStartTime().compareTo(date)==0).map(x->x.getRoom()).collect(Collectors.toList());
    }
	

	@Override
	public List<Room> getRoomDetailsByNurseIdAndDate(int nurseId,Timestamp date) {
		Nurse nurse = nurseRepository.findById(nurseId).get(); 
		List<Appointment> appointments = appointmentRepository.findByNurse(nurse);
		Patient patient = appointments.stream().filter(x->x.getStartTime().compareTo(date)==0).map(x->x.getPatient()).findAny().get();
		List<Stay> stay = stayRepository.findByPatient(patient);
		return stay.stream().filter(x->x.getStartTime().compareTo(date)==0).map(x->x.getRoom()).collect(Collectors.toList());
    }
	

	@Override
	public Appointment updateExaminationRoomByAppointmentId(int appointmentId, String newExaminationRoom) {
		Appointment appointment = appointmentRepository.findById(appointmentId).get();
		appointment.setExaminationRoom(newExaminationRoom);
		return appointmentRepository.save(appointment);
    }
	@Override
	public Patient getPatientCheckedByPhysician(Integer physicianId) {
		Physician phy = physicianRepository.findById(physicianId).get();
		List<Appointment> appointments = appointmentRepository.findByPhysician(phy);
		return appointments.stream().findFirst().get().getPatient();
	}
	
	
	
	

}
