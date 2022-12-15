package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StartupState;

@Repository
public interface StartupStateRepository extends JpaRepository<StartupState, Integer>, JpaSpecificationExecutor<StartupState> {


}

