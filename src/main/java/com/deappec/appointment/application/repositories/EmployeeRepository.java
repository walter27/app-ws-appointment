package com.deappec.appointment.application.repositories;

import java.util.List;
import java.util.Optional;

import com.deappec.appointment.domain.models.Employee;

public interface EmployeeRepository {

	Optional<Employee> findById(Long id);

	List<Employee> findAll();

	long count();

	Optional<Employee> findAvailableByAppointmentId(Long appointmentId);

	boolean existsAvailableByAppointmentId(Long appointmentId);

}
