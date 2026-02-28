package com.deappec.appointment.infraestructure.controllers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.deappec.appointment.domain.models.ShiftAssigned;
import com.deappec.appointment.infraestructure.models.ShiftAssignedDto;

@Mapper(componentModel = "spring")
public interface ShiftAssignedControllerMapper {

	@Mapping(target = "customerId", source = "customer.id")
	@Mapping(target = "employeeId", source = "employee.id")
	@Mapping(target = "appointmentId", source = "appointment.id")
	ShiftAssignedDto toShiftAssigned(ShiftAssigned shiftAssigned);

	@Mapping(target = "customer.id", source = "customerId")
	@Mapping(target = "employee.id", source = "employeeId")
	@Mapping(target = "appointment.id", source = "appointmentId")
	ShiftAssigned toShiftAssignedDto(ShiftAssignedDto shifAssignedDto);

}
