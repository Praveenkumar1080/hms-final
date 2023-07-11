package com.cg.hms.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hms.entity.Nurse;
import com.cg.hms.exception.DuplicateEntryException;
import com.cg.hms.exception.NoRecordsException;
import com.cg.hms.exception.NoSuchElementException;
import com.cg.hms.repository.NurseRepository;
@Service
public class NurseServiceImp implements NurseService {

    public NurseRepository nurseRepository;

    @Autowired
    public void setNurseRepository(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

 

    @Override
    public Nurse saveNurse(Nurse nurse) {
    	 Nurse nurse1 = nurseRepository.findById(nurse.getEmployeeId()).get();
    	 if(nurse1!=null)
    	 throw new DuplicateEntryException("");
         return nurseRepository.save(nurse);
    }

 

    @Override
    public List<Nurse> getallNurses() {
         List<Nurse> nurse = nurseRepository.findAll();
         if(nurse.isEmpty())
         throw new NoRecordsException("");
         return nurse;

    }

 

    @Override
    public Nurse getDetailOfNurseByemployeeId(Integer employeeid) {
        return nurseRepository.findById(employeeid).orElseThrow(()-> new NoSuchElementException(""));
    }

 

    @Override
    public String getPositionOfNurseByemployeeId(Integer employeeid) {
        return nurseRepository.findById(employeeid).orElseThrow(()-> new NoSuchElementException("")).getPosition();
    }


 

    @Override
    public Boolean NurseIsRegisteredOrNot(Integer employeeid) {
         return nurseRepository.findById(employeeid).orElseThrow(()-> new NoSuchElementException("")).isRegistered();
    }

 

    @Override
    public Nurse updateValueOfregistred(Integer employeeid, Nurse nur) {
         Nurse nurse = nurseRepository.findById(employeeid).orElseThrow(()-> new NoSuchElementException(""));
         nurse.setRegistered(nur.isRegistered());
         return nurseRepository.save(nurse);
        }
    @Override
    public Nurse updateValueOfSSN(Integer employeeid, Nurse nur) {
         Nurse nurse = nurseRepository.findById(employeeid).orElseThrow(()-> new NoSuchElementException(""));
         nurse.setSsn(nur.getSsn());
         return nurseRepository.save(nurse);
            
        }
    }
