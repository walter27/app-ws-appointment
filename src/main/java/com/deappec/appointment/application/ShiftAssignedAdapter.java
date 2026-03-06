package com.deappec.appointment.application;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import com.deappec.appointment.application.repositories.AppointmentRepository;
import com.deappec.appointment.application.repositories.CustomerRepository;
import com.deappec.appointment.application.repositories.EmployeeRepository;
import com.deappec.appointment.application.repositories.SendEmailRepository;
import com.deappec.appointment.application.repositories.ShiftAssignedRepository;
import com.deappec.appointment.application.services.ShiftAssignedNotificationService;
import com.deappec.appointment.domain.exceptions.AppointmentException;
import com.deappec.appointment.domain.exceptions.CustomerException;
import com.deappec.appointment.domain.models.Appointment;
import com.deappec.appointment.domain.models.Customer;
import com.deappec.appointment.domain.models.Employee;
import com.deappec.appointment.domain.models.SendEmail;
import com.deappec.appointment.application.services.ShiftAssignedService;
import com.deappec.appointment.domain.models.ShiftAssigned;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShiftAssignedAdapter implements ShiftAssignedService {
	
	@Value("${app.url.frontend}")
	private String frontendUrl;

	@Value("${app.mail.from:no-reply@calendar.app}")
	private String mailFrom;

	private final ShiftAssignedRepository shiftAssignedRepository;
	private final AppointmentRepository appointmentRepository;
	private final EmployeeRepository employeeRepository;
	private final CustomerRepository customerRepository;
	private final SendEmailRepository sendEmailRepository;

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

		ShiftAssigned savedShiftAssigned = shiftAssignedRepository.save(shiftAssigned);

		Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(AppointmentException::new);
		Integer avaible = appointment.getAvaible();
		if (avaible == null || avaible <= 0) {
			throw new AppointmentException();
		}
		appointment.setAvaible(avaible - 1);
		appointmentRepository.save(appointment);
		
		SendEmail sendEmailCustomer = sendEmailNotificationCustomer(savedShiftAssigned);
		SendEmail sendEmailEmployee = sendEmailNotificationEmployee(savedShiftAssigned);
		
		

		return savedShiftAssigned;
	}
	

	public SendEmail sendEmailNotificationCustomer(ShiftAssigned shiftAssigned) {
		if (Objects.isNull(shiftAssigned) || Objects.isNull(shiftAssigned.getCustomer())
				|| Objects.isNull(shiftAssigned.getCustomer().getEmail())
				|| shiftAssigned.getCustomer().getEmail().isBlank() || Objects.isNull(shiftAssigned.getAppointment())
				|| Objects.isNull(shiftAssigned.getAppointment().getDateAppointment())
				|| Objects.isNull(shiftAssigned.getAppointment().getHour())
				|| Objects.isNull(shiftAssigned.getAppointment().getHour().getStartTime())) {
			return false;
		}
		SendEmail sendEmail = new SendEmail();
		sendEmail.setFrom(mailFrom);
		sendEmail.setTo(shiftAssigned.getCustomer().getEmail());
		sendEmail.setSubject("Appointment reservation");
		sendEmail.setMessageHtml(buildCustomerHtml(shiftAssigned));
		return sendEmail;
	}

	public SendEmail sendEmailNotificationEmployee(ShiftAssigned shiftAssigned) {
		if (Objects.isNull(shiftAssigned) || Objects.isNull(shiftAssigned.getEmployee())
				|| Objects.isNull(shiftAssigned.getEmployee().getEmail())
				|| shiftAssigned.getEmployee().getEmail().isBlank() || Objects.isNull(shiftAssigned.getAppointment())
				|| Objects.isNull(shiftAssigned.getAppointment().getDateAppointment())
				|| Objects.isNull(shiftAssigned.getAppointment().getHour())
				|| Objects.isNull(shiftAssigned.getAppointment().getHour().getStartTime())) {
			return false;
		}
		SendEmail sendEmail = new SendEmail();
		sendEmail.setFrom(mailFrom);
		sendEmail.setTo(shiftAssigned.getEmployee().getEmail());
		sendEmail.setSubject("Assigned appointment");
		sendEmail.setMessageHtml(buildEmployeeHtml(shiftAssigned));
		return sendEmail;
	}

	private String buildCustomerHtml(ShiftAssigned shiftAssigned) {
		String appointmentDate = shiftAssigned.getAppointment().getDateAppointment().toString();
		String appointmentTime = shiftAssigned.getAppointment().getHour().getStartTime().toString();
		return loadTemplate("templates/appointment-booking.html")
				.replace("{{customer_name}}", defaultIfBlank(shiftAssigned.getCustomer().getName(), "Customer"))
				.replace("{{appointment_date}}", appointmentDate).replace("{{appointment_time}}", appointmentTime)
				.replace("{{confirm_url}}", frontendUrl + "/appointments/confirm")
				.replace("{{reschedule_url}}", frontendUrl + "/appointments/reschedule");
	}

	private String buildEmployeeHtml(ShiftAssigned shiftAssigned) {
		String appointmentDate = shiftAssigned.getAppointment().getDateAppointment().toString();
		String appointmentTime = shiftAssigned.getAppointment().getHour().getStartTime().toString();
		return loadTemplate("templates/appointment-assigned.html")
				.replace("{{employee_name}}", defaultIfBlank(shiftAssigned.getEmployee().getName(), "Employee"))
				.replace("{{appointment_date}}", appointmentDate).replace("{{appointment_time}}", appointmentTime);
	}

	private String loadTemplate(String path) {
		try {
			ClassPathResource resource = new ClassPathResource(path);
			return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
		} catch (IOException exception) {
			throw new IllegalStateException("Error loading email template: " + path, exception);
		}
	}

	private String defaultIfBlank(String value, String fallback) {
		if (Objects.isNull(value) || value.isBlank()) {
			return fallback;
		}
		return value;
	}


}
