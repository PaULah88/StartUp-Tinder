package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.RangeInvester;

public interface RangeInvesterRepository extends JpaRepository<RangeInvester, Integer>, JpaSpecificationExecutor<RangeInvester> {


}
