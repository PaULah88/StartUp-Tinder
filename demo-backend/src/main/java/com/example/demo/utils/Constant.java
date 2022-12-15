package com.example.demo.utils;

public class Constant {
	// Contact error
	public static final String NAME_REQUIRED = "NAME_REQUIRED";
	public static final String ID_REQUIRED = "ID_REQUIRED";
	public static final String SURNAME1_REQUIRED = "SURNAME1_REQUIRED";
	public static final String SURNAME2_REQUIRED = "SURNAME2_REQUIRED";
	public static final String PHONE_REQUIRED = "PHONE_REQUIRED";
	public static final String PHONE_INVALID = "PHONE_INVALID";
	public static final String PHONE_ALREADY_EXISTS = "PHONE_ALREADY_EXISTS";
	public static final String EMAIL_REQUIRED = "EMAIL_REQUIRED";
	public static final String EMAIL_INVALID = "EMAIL_INVALID";
	public static final String CONTACT_NOT_EXISTS = "CONTACT_NOT_EXISTS";
	public static final String CONTACT_ALREADY_EXISTS = "CONTACT_ALREADY_EXISTS";
	public static final String CONTACT_CONSTRAINT_VIOLATION = "CONTACT_CONSTRAINT_VIOLATION";
	public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";

	// Contact message
	public static final String CONTACT_CREATE_SUCCESS = "CONTACT_CREATE_SUCCESS";
	public static final String CONTACT_NOT_CREATED = "CONTACT_NOT_CREATED";
	public static final String CONTACT_EDIT_SUCCESS = "CONTACT_EDIT_SUCCESS";
	public static final String CONTACT_NOT_EDIT = "CONTACT_NOT_EDIT";
	public static final String CONTACT_DELETE_SUCCESS = "CONTACT_DELETE_SUCCESS";
	public static final String CONTACT_NOT_DELETE = "CONTACT_NOT_DELETE";

	// User message
	public static final String USER_CREATE_SUCCESS = "USER_CREATE_SUCCESS";
	public static final String USER_EDIT_SUCCESS = "USER_EDIT_SUCCESS";
	public static final String USER_DELETE_SUCCESS = "USER_DELETE_SUCCESS";

	// User error
	public static final String LOGIN_REQUIRED = "LOGIN_REQUIRED";
	public static final String NIF_REQUIRED = "NIF_REQUIRED";
	public static final String PASSWORD_REQUIRED = "PASSWORD_REQUIRED";
	public static final String LOGIN_EXISTS = "LOGIN_EXISTS";
	public static final String NIF_MALFORMED = "NIF_MALFORMED";
	public static final String USER_NOT_EXISTS = "USER_NOT_EXISTS";
	public static final String NO_SECTIONS_ACCESS = "NO_SECTIONS_ACCESS";
	public static final String USER_CONSTRAINT_VIOLATION = "USER_CONSTRAINT_VIOLATION";
	public static final String USER_NOT_CREATED = "USER_NOT_CREATED";

	// Profile error
	public static final String PROFILE_CONSTRAINT_VIOLATION = "PROFILE_CONSTRAINT_VIOLATION";

	// Common error
	public static final String ID_NOT_EXISTS = "ID_NOT_EXISTS";
	public static final String UNAUTHORIZED_USER = "UNAUTHORIZED_USER";
	public static final String SIGNATURE_NOT_PENDING = "SIGNATURE_NOT_PENDING";
	public static final String DATABASE_QUERY_ERROR = "DATABASE_QUERY_ERROR";

	// pagination error
	public static final String PAGE_INDEX_REQUIRED = "PAGE_INDEX_REQUIRED";
	public static final String PAGE_SIZE_REQUIRED = "PAGE_SIZE_REQUIRED";

	public static final String MESSAGE = "responseMessage";
	public static final String RESPONSE_CODE = "responseCode";
	public static final String ERROR = "errors";
	public static final String PHONE_ERROR = "contacts_phone_key";

	// pagination error invester
	public static final String INVESTER_ID_ERROR = "INVESTER_ID_ERROR";
	public static final String INVESTER_EMAIL_ERROR = "INVESTER_EMAIL_ERROR";

	// Invester error
	public static final String INVESTER_ID_REQUIRED = "INVESTER_ID_REQUIRED";
	public static final String INVESTER_NAME_REQUIRED = "INVESTER_NAME_REQUIRED";
	public static final String INVESTER_EMAIL_REQUIRED = "INVESTER_EMAIL_REQUIRED";
	public static final String INVESTER_RANGE_REQUIRED = "INVESTER_RANGE_REQUIRED";
	public static final String INVESTER_BUSINESS_SECTOR_REQUIRED = "INVESTER_BUSINESS_SECTOR_REQUIRED";
	public static final String INVESTER_STARTUP_STATE_REQUIRED = "INVESTER_STARTUP_STATE_REQUIRED";
	public static final String INVESTER_NOT_EXISTS = "INVESTER_NOT_EXISTS";
	public static final String INVESTER_ALREADY_EXISTS = "INVESTER_ALREADY_EXISTS";
	public static final String INVESTER_EMAIL_INVALID = "EMAIL_INVALID";

	// Invester message
	public static final String INVESTER_CREATE_SUCCESS = "INVESTER_CREATE_SUCCESS";
	public static final String INVESTER_NOT_CREATED = "INVESTER_NOT_CREATED";
	public static final String INVESTER_EDIT_SUCCESS = "INVESTER_EDIT_SUCCESS";
	public static final String INVESTER_NOT_EDIT = "INVESTER_NOT_EDIT";
	public static final String INVESTER_DELETE_SUCCESS = "INVESTER_DELETE_SUCCESS";
	public static final String INVESTER_NOT_DELETE = "INVESTER_NOT_DELETE";

	// Startup error
	public static final String STARTUP_ID_REQUIRED = "STARTUP_ID_REQUIRED";
	public static final String STARTUP_NAME_REQUIRED = "STARTUP_NAME_REQUIRED";
	public static final String STARTUP_EMAIL_REQUIRED = "STARTUP_EMAIL_REQUIRED";
	public static final String STARTUP_DESCRIPTION_REQUIRED = "STARTUP_DESCRIPTION_REQUIRED";
	public static final String STARTUP_BUSINESS_SECTOR_REQUIRED = "STARTUP_BUSINESS_SECTOR_REQUIRED";
	public static final String STARTUP_STATE_REQUIRED = "STARTUP_STATE_REQUIRED";
	public static final String STARTUP_ENTREPRENEUR_REQUIRED = "STARTUP_ENTREPRENEUR_REQUIRED";
	public static final String STARTUP_ANUAL_INVOICING_REQUIRED = "STARTUP_ANUAL_INVOICING_REQUIRED";
	public static final String STARTUP_FUNDATION_YEAR_REQUIRED = "STARTUP_FUNDATION_YEAR_REQUIRED";
	public static final String STARTUP_NOT_EXISTS = "STARTUP_NOT_EXISTS";
	public static final String STARTUP_ALREADY_EXISTS = "STARTUP_ALREADY_EXISTS";
	public static final String STARTUP_EMAIL_INVALID = "STARTUP_EMAIL_INVALID";

	// Startup message
	public static final String STARTUP_CREATE_SUCCESS = "STARTUP_CREATE_SUCCESS";
	public static final String STARTUP_NOT_CREATED = "STARTUP_NOT_CREATED";
	public static final String STARTUP_EDIT_SUCCESS = "STARTUP_EDIT_SUCCESS";
	public static final String STARTUP_NOT_EDIT = "STARTUP_NOT_EDIT";
	public static final String STARTUP_DELETE_SUCCESS = "STARTUP_DELETE_SUCCESS";
	public static final String STARTUP_NOT_DELETE = "STARTUP_NOT_DELETE";

	// Startup pagination error entrepreneur
	public static final String STARTUP_ID_ERROR = "STARTUP_ID_ERROR";
	public static final String STARTUP_EMAIL_ERROR = "STARTUP_EMAIL_ERROR";

	// Entrepreneur pagination error entrepreneur
	public static final String ENTREPRENEUR_ID_ERROR = "ENTREPRENEUR_ID_ERROR";
	public static final String ENTREPRENEUR_EMAIL_ERROR = "ENTREPRENEUR_EMAIL_ERROR";

	// Entrepreneur error
	public static final String ENTREPRENEUR_ID_REQUIRED = "ENTREPRENEUR_ID_REQUIRED";
	public static final String ENTREPRENEUR_FIRST_NAME_REQUIRED = "ENTREPRENEUR_FIRST_NAME_REQUIRED";
	public static final String ENTREPRENEUR_LAST_NAME_REQUIRED = "ENTREPRENEUR_LAST_NAME_REQUIRED";
	public static final String ENTREPRENEUR_EMAIL_REQUIRED = "ENTREPRENEUR_EMAIL_REQUIRED";
	public static final String ENTREPRENEUR_PROFESSIONAL_PROFILE_REQUIRED = "ENTREPRENEUR_PROFESSIONAL_PROFILE_REQUIRED";
	public static final String ENTREPRENEUR_LINKEDIN_PROFILE_REQUIRED = "ENTREPRENEUR_LINKEDIN_PROFILE_REQUIRED";
	public static final String ENTREPRENEUR_NOT_EXISTS = "ENTREPRENEUR_NOT_EXISTS";
	public static final String ENTREPRENEUR_ALREADY_EXISTS = "ENTREPRENEUR_ALREADY_EXISTS";
	public static final String ENTREPRENEUR_EMAIL_INVALID = "ENTREPRENEUR_EMAIL_INVALID";

	// Entrepreneur message
	public static final String ENTREPRENEUR_CREATE_SUCCESS = "ENTREPRENEUR_CREATE_SUCCESS";
	public static final String ENTREPRENEUR_NOT_CREATED = "ENTREPRENEUR_NOT_CREATED";
	public static final String ENTREPRENEUR_EDIT_SUCCESS = "ENTREPRENEUR_EDIT_SUCCESS";
	public static final String ENTREPRENEUR_NOT_EDIT = "ENTREPRENEUR_NOT_EDIT";
	public static final String ENTREPRENEUR_DELETE_SUCCESS = "ENTREPRENEUR_DELETE_SUCCESS";
	public static final String ENTREPRENEUR_NOT_DELETE = "ENTREPRENEUR_NOT_DELETE";

}
