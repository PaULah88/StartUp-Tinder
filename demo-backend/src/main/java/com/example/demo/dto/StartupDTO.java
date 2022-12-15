package com.example.demo.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.example.demo.utils.Constant;

import lombok.Data;

@Data
public class StartupDTO {

	private Integer id;

	@NotNull(message = Constant.STARTUP_NAME_REQUIRED)
	private String name;

	@NotNull(message = Constant.STARTUP_EMAIL_REQUIRED)
	private String email;

	@NotNull(message = Constant.STARTUP_DESCRIPTION_REQUIRED)
	private String description;

	@NotNull(message = Constant.STARTUP_BUSINESS_SECTOR_REQUIRED)
	private BusinessSectorDTO idBusinessSector;

	@NotNull(message = Constant.STARTUP_STATE_REQUIRED)
	private StartupStateDTO idStartupState;

	@NotNull(message = Constant.STARTUP_ANUAL_INVOICING_REQUIRED)
	private Integer anualInvoicing;

	@NotNull(message = Constant.STARTUP_FUNDATION_YEAR_REQUIRED)
	private Date fundationYear;

	@NotNull(message = Constant.STARTUP_ENTREPRENEUR_REQUIRED)
	private EntrepreneurDTO idEntrepreneur;

}
