package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Invester;
import com.example.demo.entity.Startup;

@Repository
public interface StartupRepository extends JpaRepository<Startup, Integer>, JpaSpecificationExecutor<Startup> {

}