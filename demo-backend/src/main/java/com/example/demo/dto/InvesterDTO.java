package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.example.demo.utils.Constant;

import lombok.Data;

@Data
public class InvesterDTO {

	private Integer id;

	@NotNull(message = Constant.INVESTER_NAME_REQUIRED)
	private String name;

	@Email(message= Constant.INVESTER_EMAIL_INVALID)
	@NotNull(message = Constant.INVESTER_EMAIL_REQUIRED)
	private String email;
	
	@NotNull(message = Constant.INVESTER_RANGE_REQUIRED)
	private RangeInvesterDTO idInvesterRange;
	
	@NotNull(message = Constant.INVESTER_BUSINESS_SECTOR_REQUIRED)
	private BusinessSectorDTO idBusinessSector;
	
	@NotNull(message = Constant.INVESTER_STARTUP_STATE_REQUIRED)
	private StartupStateDTO idStartUpState;

}
