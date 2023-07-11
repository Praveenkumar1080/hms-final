package com.cg.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hms.entity.Block;
import com.cg.hms.entity.BlockId;

@Repository
public interface BlockRepository extends JpaRepository<Block, BlockId>{

}
