package com.deappec.appointment.util;

import lombok.Getter;

@Getter
public enum ErrorCatalog {

	EMPLOYEE_NOT_FOUND("ERR_EMP_001", "Employee not found."),
	INVALID_EMPLOYEE("ERR_ENP_002", "Invalid employee parameters."),
	CUSTOMER_ERROR("ERR_CUS_001", "Invalid customer parameters."),
	DOCUMENT_TYPE_NOT_FOUND("ERR_DCT_001", "Document type not found."),
	ENTITY_TYPE_NOT_FOUND("ERR_ENT_001", "Entity type not found."),
	GENERIC_ERROR("ERR_GEN_001", "An unexpected error occurred."),
	APPOINTMENT_ERROR("ERR_APP_001","Error registering shift");

	private final String code;
	private final String message;

	ErrorCatalog(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
