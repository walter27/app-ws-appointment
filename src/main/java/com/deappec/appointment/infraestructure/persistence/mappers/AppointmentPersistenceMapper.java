package com.deappec.appointment.infraestructure.persistence.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.deappec.appointment.domain.models.Appointment;
import com.deappec.appointment.infraestructure.persistence.entities.AppointmentEntity;

@Mapper(componentModel = "spring")
public interface AppointmentPersistenceMapper {

	Appointment toAppointmentEntity(AppointmentEntity appointmentEntity);

	AppointmentEntity toAppointment(Appointment appointment);

	List<Appointment> toAppointmentEntities(List<AppointmentEntity> appointmentEntities);

}
