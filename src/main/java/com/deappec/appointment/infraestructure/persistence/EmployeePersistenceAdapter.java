package com.deappec.appointment.infraestructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.EmployeeRepository;
import com.deappec.appointment.domain.models.Employee;
import com.deappec.appointment.infraestructure.persistence.mappers.EmployeePersistenceMapper;
import com.deappec.appointment.infraestructure.persistence.repositories.EmployeeEntityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements EmployeeRepository {

	private final EmployeeEntityRepository persistence;
	private final EmployeePersistenceMapper mapper;

	@Override
	public Optional<Employee> findById(Long id) {
		return persistence.findById(id).map(mapper::toEmployee);
	}

	@Override
	public List<Employee> findAll() {
		return mapper.toEmployeeList(persistence.findAll());
	}

	@Override
	public long count() {
		return persistence.count();
	}

	@Override
	public Optional<Employee> findAvailableByAppointmentId(Long appointmentId) {
		return persistence.findAvailableByAppointmentId(appointmentId, PageRequest.of(0, 1)).stream().findFirst()
				.map(mapper::toEmployee);
	}

	@Override
	public boolean existsAvailableByAppointmentId(Long appointmentId) {
		return persistence.existsAvailableByAppointmentId(appointmentId);
	}

}
