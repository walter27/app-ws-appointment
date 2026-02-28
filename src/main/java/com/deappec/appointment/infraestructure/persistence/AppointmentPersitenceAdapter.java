package com.deappec.appointment.infraestructure.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.AppointmentRepository;
import com.deappec.appointment.domain.models.Appointment;
import com.deappec.appointment.infraestructure.persistence.mappers.AppointmentPersistenceMapper;
import com.deappec.appointment.infraestructure.persistence.repositories.AppointmentEntityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppointmentPersitenceAdapter implements AppointmentRepository {

	private final AppointmentEntityRepository persistence;
	private final AppointmentPersistenceMapper mapper;

	@Override
	public Appointment save(Appointment appointment) {
		return mapper.toAppointmentEntity(persistence.save(mapper.toAppointment(appointment)));
	}

	@Override
	public Optional<Appointment> findById(Long id) {
		return persistence.findById(id).map(mapper::toAppointmentEntity);
	}

	@Override
	public long countByHourIdAndDateAppointment(Long hourId, LocalDate dateAppointment) {
		return persistence.countByHourIdAndDateAppointment(hourId, dateAppointment);
	}

	@Override
	public List<Appointment> findByDateAppointmentBetween(LocalDate start, LocalDate end) {
		return mapper.toAppointmentEntities(persistence.findByDateAppointmentBetween(start, end));
	}

}
