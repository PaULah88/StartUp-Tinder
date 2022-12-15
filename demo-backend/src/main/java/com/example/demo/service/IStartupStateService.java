package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.StartupStateDTO;

public interface IStartupStateService{

	List<StartupStateDTO> findAll();
}