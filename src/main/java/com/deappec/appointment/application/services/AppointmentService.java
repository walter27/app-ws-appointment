package com.deappec.appointment.application.services;

import java.time.LocalDate;
import java.util.List;

import com.deappec.appointment.domain.models.Appointment;

public interface AppointmentService {

	Appointment save(Appointment appointment);

	List<Appointment> findByDateAppointmentBetween(LocalDate start, LocalDate end);

}
