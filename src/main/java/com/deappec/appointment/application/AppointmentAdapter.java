package com.deappec.appointment.application;

import com.deappec.appointment.domain.exceptions.CustomerException;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.AppointmentRepository;
import com.deappec.appointment.application.repositories.CustomerRepository;
import com.deappec.appointment.application.repositories.EmployeeRepository;
import com.deappec.appointment.application.repositories.TimeDayRepository;
import com.deappec.appointment.application.services.AppointmentService;
import com.deappec.appointment.domain.exceptions.AppointmentException;
import com.deappec.appointment.domain.exceptions.TimeDayException;
import com.deappec.appointment.domain.models.Appointment;
import com.deappec.appointment.domain.models.Customer;
import com.deappec.appointment.domain.models.Employee;
import com.deappec.appointment.domain.models.TimeDay;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentAdapter implements AppointmentService {

	private final AppointmentRepository repositoryApp;
	private final EmployeeRepository repositoryEmp;
	private final CustomerRepository repositoryCus;
	private final TimeDayRepository repositoryDay;

	@Override
	public Appointment save(Appointment appointment) {
		/*long countEmployess = repositoryEmp.count();
		long avaibleEmployee = repositoryApp.countByHourIdAndDateAppointment(appointment.getHour().getId(),
				appointment.getDateAppointment());
		if (avaibleEmployee >= countEmployess) {
			throw new AppointmentException();
		}
		Employee employee = repositoryEmp
				.findAvailableEmployee(appointment.getHour().getId(), appointment.getDateAppointment()).stream()
				.findFirst().orElseThrow(AppointmentException::new);
		TimeDay timeDay = repositoryDay.findById(appointment.getHour().getId()).orElseThrow(TimeDayException::new);
		Customer customer = repositoryCus.findById(appointment.getCustomer().getId())
				.orElseThrow(CustomerException::new);
		appointment.setCustomer(customer);
		appointment.setEmployee(employee);
		appointment.setHour(timeDay);
		return repositoryApp.save(appointment);*/
		return null;
	}

	@Override
	public List<Appointment> findByDateAppointmentBetween(LocalDate start, LocalDate end) {
		return repositoryApp.findByDateAppointmentBetween(start, end);
	}

}
