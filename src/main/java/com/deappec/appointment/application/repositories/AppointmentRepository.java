package com.deappec.appointment.application.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.deappec.appointment.domain.models.Appointment;

public interface AppointmentRepository {

	Appointment save(Appointment appointment);

	Optional<Appointment> findById(Long id);

	long countByHourIdAndDateAppointment(Long hourId, LocalDate dateAppointment);

	List<Appointment> findByDateAppointmentBetween(LocalDate start, LocalDate end);

}
