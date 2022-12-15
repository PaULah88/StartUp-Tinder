package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ProfessionalProfileDTO;

public interface IProfessionalProfileService{

	List<ProfessionalProfileDTO> findAll();
}