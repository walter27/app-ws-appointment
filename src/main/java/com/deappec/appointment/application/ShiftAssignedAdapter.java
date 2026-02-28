package com.deappec.appointment.application;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.AppointmentRepository;
import com.deappec.appointment.application.repositories.CustomerRepository;
import com.deappec.appointment.application.repositories.EmployeeRepository;
import com.deappec.appointment.application.repositories.ShiftAssignedRepository;
import com.deappec.appointment.domain.exceptions.AppointmentException;
import com.deappec.appointment.domain.exceptions.CustomerException;
import com.deappec.appointment.domain.models.Appointment;
import com.deappec.appointment.domain.models.Customer;
import com.deappec.appointment.domain.models.Employee;
import com.deappec.appointment.application.services.ShiftAssignedService;
import com.deappec.appointment.domain.models.ShiftAssigned;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShiftAssignedAdapter implements ShiftAssignedService {

	private final ShiftAssignedRepository repository;
	private final AppointmentRepository appointmentRepository;
	private final EmployeeRepository employeeRepository;
	private final CustomerRepository customerRepository;

	@Override
	public ShiftAssigned save(ShiftAssigned shiftAssigned) {
		Long appointmentId = shiftAssigned.getAppointment() != null ? shiftAssigned.getAppointment().getId() : null;
		Long customerId = shiftAssigned.getCustomer() != null ? shiftAssigned.getCustomer().getId() : null;

		if (appointmentId == null) {
			throw new AppointmentException();
		}
		if (customerId == null) {
			throw new CustomerException();
		}

		Customer customer = customerRepository.findById(customerId).orElseThrow(CustomerException::new);
		shiftAssigned.setCustomer(customer);

		Employee employee = employeeRepository.findAvailableByAppointmentId(appointmentId)
				.orElseThrow(AppointmentException::new);
		shiftAssigned.setEmployee(employee);

		ShiftAssigned savedShiftAssigned = repository.save(shiftAssigned);

		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(AppointmentException::new);
		Integer avaible = appointment.getAvaible();
		if (avaible == null || avaible <= 0) {
			throw new AppointmentException();
		}
		appointment.setAvaible(avaible - 1);
		appointmentRepository.save(appointment);

		return savedShiftAssigned;
	}

}
