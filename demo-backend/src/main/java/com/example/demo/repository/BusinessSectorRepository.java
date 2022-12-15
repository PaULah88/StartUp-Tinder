package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BusinessSector;
import com.example.demo.entity.RangeInvester;

@Repository
public interface BusinessSectorRepository extends JpaRepository<BusinessSector, Integer>, JpaSpecificationExecutor<BusinessSector> {


}

