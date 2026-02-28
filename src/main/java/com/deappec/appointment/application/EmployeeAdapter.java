package com.deappec.appointment.application;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.EmployeeRepository;
import com.deappec.appointment.application.services.EmployeeService;
import com.deappec.appointment.domain.exceptions.EmployeeException;
import com.deappec.appointment.domain.models.Employee;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeeAdapter implements EmployeeService {

	private final EmployeeRepository repository;

	@Override
	public Employee findById(Long id) {
		return repository.findById(id).orElseThrow(EmployeeException::new);
	}

}
