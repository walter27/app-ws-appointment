package com.deappec.appointment.infraestructure.controllers.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.deappec.appointment.domain.models.Appointment;
import com.deappec.appointment.infraestructure.models.AppointmentDto;

@Mapper(componentModel = "spring")
public interface AppointmentControllerMapper {

	AppointmentDto toAppointment(Appointment appointment);

	Appointment toAppointmentDto(AppointmentDto appointmentDto);

	List<AppointmentDto> toListAppointmentDto(List<Appointment> list);

}
