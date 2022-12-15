package com.example.demo.service;

import com.borjaglez.springify.repository.filter.IPageFilter;
import com.example.demo.entity.Contact;
import com.example.demo.entity.Entrepreneur;
import com.example.demo.entity.Invester;
import com.example.demo.entity.Startup;
import com.example.demo.entity.User;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.model.QuerySortPaginationRequest;
import com.example.demo.utils.Constant;

public class AbstractDemoService {
	protected void checkInputParams(IPageFilter pageFilter) {
		if (pageFilter.getPageNumber() == null) {
			throw new DemoException(Constant.PAGE_INDEX_REQUIRED);
		}
		if (pageFilter.getPageSize() == null) {
			throw new DemoException(Constant.PAGE_SIZE_REQUIRED);
		}
	}

	protected void checkInputParams(QuerySortPaginationRequest pageFilter) {
		if (pageFilter.getPageIndex() == null) {
			throw new DemoException(Constant.PAGE_INDEX_REQUIRED);
		}
		if (pageFilter.getPageSize() == null) {
			throw new DemoException(Constant.PAGE_SIZE_REQUIRED);
		}
	}

	public Contact fromEditContactRequest(Contact contactRequest) {
		return new Contact(contactRequest.getId(), contactRequest.getName(), contactRequest.getSurname1(),
				contactRequest.getSurname2(), contactRequest.getPhone(), contactRequest.getEmail());
	}

	public Contact fromCreateContactRequest(Contact contactRequest) {
		return new Contact(contactRequest.getName(), contactRequest.getSurname1(), contactRequest.getSurname2(),
				contactRequest.getPhone(), contactRequest.getEmail());
	}

	public Invester fromEditInvesterRequest(Invester investerRequest) {
		return new Invester(investerRequest.getId(), investerRequest.getName(), investerRequest.getEmail(),
				investerRequest.getIdInvesterRange(), investerRequest.getIdBusinessSector(),
				investerRequest.getIdStartUpState());
	}

	public Invester fromCreateInvesterRequest(Invester investerRequest) {
		return new Invester(investerRequest.getName(), investerRequest.getEmail(), investerRequest.getIdInvesterRange(),
				investerRequest.getIdBusinessSector(), investerRequest.getIdStartUpState());
	}

	public Startup fromEditStartupRequest(Startup startupRequest) {
		return new Startup(startupRequest.getId(), startupRequest.getName(), startupRequest.getEmail(),
				startupRequest.getDescription(), startupRequest.getIdBusinessSector(),
				startupRequest.getIdStartupState(), startupRequest.getAnualInvoicing(),
				startupRequest.getFundationYear(), startupRequest.getIdEntrepreneur());
	}

	public Startup fromCreateStartupRequest(Startup startupRequest) {
		return new Startup(startupRequest.getName(), startupRequest.getEmail(), startupRequest.getDescription(),
				startupRequest.getIdBusinessSector(), startupRequest.getIdStartupState(),
				startupRequest.getAnualInvoicing(), startupRequest.getFundationYear(),
				startupRequest.getIdEntrepreneur());
	}

	//Se usa.
	public User fromCreateUserRequest(User userRequest) {
		return new User(userRequest.getName(), userRequest.getSurname1(), userRequest.getSurname2(),
				userRequest.getLogin(), userRequest.getPassword());
	}
	//Por ahora el edit no se usa.
	public User fromEditUserRequest(User userRequest) {
		return new User(userRequest.getId(),userRequest.getName(), userRequest.getSurname1(), userRequest.getSurname2(),
				userRequest.getLogin(), userRequest.getPassword());
	}
	
	//Se usa.
	public Entrepreneur fromCreateEntrepreneurRequest(Entrepreneur entrepreneurRequest) {
		return new Entrepreneur(entrepreneurRequest.getFirstName(), entrepreneurRequest.getLastName(), entrepreneurRequest.getEmail(),
				entrepreneurRequest.getIdProfessionalProfile(), entrepreneurRequest.getLinkedinProfile(),entrepreneurRequest.getEntrepreneurs());
	}
	//Por ahora el edit no se usa.
	public Entrepreneur fromEditEntrepreneurRequest(Entrepreneur entrepreneurRequest) {
		return new Entrepreneur(entrepreneurRequest.getId(),entrepreneurRequest.getFirstName(), entrepreneurRequest.getLastName(), entrepreneurRequest.getEmail(),
				entrepreneurRequest.getIdProfessionalProfile(), entrepreneurRequest.getLinkedinProfile(),entrepreneurRequest.getEntrepreneurs());
	}
}
