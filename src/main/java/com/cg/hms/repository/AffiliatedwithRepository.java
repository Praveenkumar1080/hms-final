package com.cg.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hms.entity.AffId;
import com.cg.hms.entity.Affiliatedwith;
import com.cg.hms.entity.Department;
import com.cg.hms.entity.Physician;

@Repository
public interface AffiliatedwithRepository extends JpaRepository<Affiliatedwith,AffId> {
public List<Affiliatedwith> findByDepartment(Department dep);
public List<Affiliatedwith> findByPhysician(Physician dep);
public Integer countByDepartment(Department dep);

@Query("SELECT a.physician from Affiliatedwith a WHERE a.department.departmentId = :depid")
public List<Physician> findByDepId(int depid);
}
