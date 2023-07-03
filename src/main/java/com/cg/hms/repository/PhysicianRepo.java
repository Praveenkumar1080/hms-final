package com.cg.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.hms.Entity.Physician;

public interface PhysicianRepo extends JpaRepository<Physician,Integer>{

}
