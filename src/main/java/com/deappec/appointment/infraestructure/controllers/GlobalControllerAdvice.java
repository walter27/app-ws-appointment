package com.deappec.appointment.infraestructure.controllers;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deappec.appointment.domain.exceptions.AppointmentException;
import com.deappec.appointment.domain.exceptions.CustomerException;
import com.deappec.appointment.domain.exceptions.DocumentTypeException;
import com.deappec.appointment.domain.exceptions.EmployeeException;
import com.deappec.appointment.domain.exceptions.EntityTypeException;
import com.deappec.appointment.domain.exceptions.FileStorageException;
import com.deappec.appointment.domain.exceptions.InvalidAttachmentException;
import com.deappec.appointment.infraestructure.models.ErrorDto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import static com.deappec.appointment.util.ErrorCatalog.*;

@RestControllerAdvice
public class GlobalControllerAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EmployeeException.class)
	public ErrorDto handleEmployeeNotFoundException() {
		return ErrorDto.builder().code(EMPLOYEE_NOT_FOUND.getCode()).message(EMPLOYEE_NOT_FOUND.getMessage())
				.timestamp(LocalDateTime.now()).build();
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(AppointmentException.class)
	public ErrorDto handleAppointmentException() {
		return ErrorDto.builder().code(APPOINTMENT_ERROR.getCode()).message(APPOINTMENT_ERROR.getMessage())
				.timestamp(LocalDateTime.now()).build();
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(DocumentTypeException.class)
	public ErrorDto handleDocumentTypeException() {
		return ErrorDto.builder().code(DOCUMENT_TYPE_NOT_FOUND.getCode()).message(DOCUMENT_TYPE_NOT_FOUND.getMessage())
				.timestamp(LocalDateTime.now()).build();
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityTypeException.class)
	public ErrorDto handleEntityTypeException() {
		return ErrorDto.builder().code(ENTITY_TYPE_NOT_FOUND.getCode()).message(ENTITY_TYPE_NOT_FOUND.getMessage())
				.timestamp(LocalDateTime.now()).build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CustomerException.class)
	public ErrorDto handleCustomerException() {
		return ErrorDto.builder().code(CUSTOMER_ERROR.getCode()).message(CUSTOMER_ERROR.getMessage())
				.timestamp(LocalDateTime.now()).build();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		BindingResult result = exception.getBindingResult();

		return ErrorDto.builder().code(INVALID_EMPLOYEE.getCode()).message(INVALID_EMPLOYEE.getMessage())
				.details(result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
						.collect(Collectors.toList()))
				.timestamp(LocalDateTime.now()).build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	public ErrorDto handleBindException(BindException exception) {
		BindingResult result = exception.getBindingResult();
		return ErrorDto.builder().code(INVALID_EMPLOYEE.getCode()).message(INVALID_EMPLOYEE.getMessage())
				.details(result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
						.collect(Collectors.toList()))
				.timestamp(LocalDateTime.now()).build();
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(FileStorageException.class)
	public ErrorDto handleFileStorageException(FileStorageException exception) {
		return ErrorDto.builder().code(GENERIC_ERROR.getCode()).message(exception.getMessage())
				.details(Collections.singletonList(exception.getMessage())).timestamp(LocalDateTime.now()).build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidAttachmentException.class)
	public ErrorDto handleInvalidAttachmentException(InvalidAttachmentException exception) {
		return ErrorDto.builder().code(INVALID_EMPLOYEE.getCode()).message(exception.getMessage())
				.details(Collections.singletonList(exception.getMessage())).timestamp(LocalDateTime.now()).build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ErrorDto handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
		return ErrorDto.builder().code(INVALID_EMPLOYEE.getCode()).message("Attached file exceeds the 3MB limit")
				.details(Collections.singletonList(exception.getMessage())).timestamp(LocalDateTime.now()).build();
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorDto handleGenericError(Exception exception) {
		return ErrorDto.builder().code(GENERIC_ERROR.getCode()).message(GENERIC_ERROR.getMessage())
				.details(Collections.singletonList(exception.getMessage())).timestamp(LocalDateTime.now()).build();
	}

}
