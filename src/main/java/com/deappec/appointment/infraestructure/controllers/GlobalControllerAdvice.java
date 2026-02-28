package com.deappec.appointment.infraestructure.controllers;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.deappec.appointment.domain.exceptions.AppointmentException;
import com.deappec.appointment.domain.exceptions.EmployeeException;
import com.deappec.appointment.infraestructure.models.ErrorDto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

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

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		BindingResult result = exception.getBindingResult();

		return ErrorDto.builder().code(INVALID_EMPLOYEE.getCode()).message(INVALID_EMPLOYEE.getMessage())
				.details(result.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
						.collect(Collectors.toList()))
				.timestamp(LocalDateTime.now()).build();
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ErrorDto handleGenericError(Exception exception) {
		return ErrorDto.builder().code(GENERIC_ERROR.getCode()).message(GENERIC_ERROR.getMessage())
				.details(Collections.singletonList(exception.getMessage())).timestamp(LocalDateTime.now()).build();
	}

}
