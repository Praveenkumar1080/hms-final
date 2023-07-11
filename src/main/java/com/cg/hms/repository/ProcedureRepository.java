package com.cg.hms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hms.entity.Procedures;

@Repository
public interface ProcedureRepository extends JpaRepository<Procedures, Integer>{
	public Optional<Procedures> findByName(String name);
}
