package com.deappec.appointment.application;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.AppointmentRepository;
import com.deappec.appointment.application.services.AppointmentService;
import com.deappec.appointment.domain.models.Appointment;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentAdapter implements AppointmentService {

	private final AppointmentRepository repositoryApp;

	@Override
	public Appointment save(Appointment appointment) {
		return repositoryApp.save(appointment);
	}

	@Override
	public List<Appointment> findByDateAppointmentBetween(LocalDate start, LocalDate end) {
		return repositoryApp.findByDateAppointmentBetween(start, end);
	}

}
