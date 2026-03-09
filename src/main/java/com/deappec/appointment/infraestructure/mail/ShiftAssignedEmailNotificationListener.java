package com.deappec.appointment.infraestructure.mail;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.util.StreamUtils;

import com.deappec.appointment.application.events.ShiftAssignedCreatedEvent;
import com.deappec.appointment.domain.models.SendEmail;
import com.deappec.appointment.domain.models.ShiftAssigned;
import com.deappec.appointment.infraestructure.configuration.AppProperties;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Component
@RequiredArgsConstructor
public class ShiftAssignedEmailNotificationListener {

	private final Resend resend; 
	private final AppProperties appProperties;

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onShiftAssignedCreated(ShiftAssignedCreatedEvent event) {
		if (event == null || event.shiftAssigned() == null) {
			return;
		}
		ShiftAssigned shiftAssigned = event.shiftAssigned();
		sendEmailIfPresent(sendEmailNotificationEmployee(shiftAssigned));
		sendEmailIfPresent(sendEmailNotificationCustomer(shiftAssigned));
	}

	@SneakyThrows(ResendException.class)
	private void sendEmailIfPresent(SendEmail sendEmail) {
		if (sendEmail == null) {
			return;
		}
	
			CreateEmailOptions params = CreateEmailOptions.builder().from(sendEmail.getFrom()).to(List.of(sendEmail.getTo()))
					.subject(sendEmail.getSubject()).html(sendEmail.getMessageHtml()).build();
			String responseId = resend.emails().send(params).getId();
		
	}
	
	

	private SendEmail sendEmailNotificationCustomer(ShiftAssigned shiftAssigned) {
		if (Objects.isNull(shiftAssigned) || Objects.isNull(shiftAssigned.getCustomer())
				|| Objects.isNull(shiftAssigned.getCustomer().getEmail())
				|| shiftAssigned.getCustomer().getEmail().isBlank() || Objects.isNull(shiftAssigned.getAppointment())
				|| Objects.isNull(shiftAssigned.getAppointment().getDateAppointment())
				|| Objects.isNull(shiftAssigned.getAppointment().getHour())
				|| Objects.isNull(shiftAssigned.getAppointment().getHour().getStartTime())) {
			return null;
		}
		SendEmail sendEmail = new SendEmail();
		sendEmail.setFrom(appProperties.getMail().getFrom());
		sendEmail.setTo(shiftAssigned.getCustomer().getEmail());
		sendEmail.setSubject("Appointment reservation");
		sendEmail.setMessageHtml(buildCustomerHtml(shiftAssigned));
		return sendEmail;
	}

	private SendEmail sendEmailNotificationEmployee(ShiftAssigned shiftAssigned) {
		if (Objects.isNull(shiftAssigned) || Objects.isNull(shiftAssigned.getEmployee())
				|| Objects.isNull(shiftAssigned.getEmployee().getEmail())
				|| shiftAssigned.getEmployee().getEmail().isBlank() || Objects.isNull(shiftAssigned.getAppointment())
				|| Objects.isNull(shiftAssigned.getAppointment().getDateAppointment())
				|| Objects.isNull(shiftAssigned.getAppointment().getHour())
				|| Objects.isNull(shiftAssigned.getAppointment().getHour().getStartTime())) {
			return null;
		}
		SendEmail sendEmail = new SendEmail();
		sendEmail.setFrom(appProperties.getMail().getFrom());
		sendEmail.setTo(shiftAssigned.getEmployee().getEmail());
		sendEmail.setSubject("Assigned appointment");
		sendEmail.setMessageHtml(buildEmployeeHtml(shiftAssigned));
		return sendEmail;
	}

	private String buildCustomerHtml(ShiftAssigned shiftAssigned) {
		String frontendUrl = appProperties.getUrl().getFrontend();
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
