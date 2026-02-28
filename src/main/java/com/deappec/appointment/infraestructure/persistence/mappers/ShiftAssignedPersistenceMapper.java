package com.deappec.appointment.infraestructure.persistence.mappers;

import org.mapstruct.Mapper;

import com.deappec.appointment.domain.models.ShiftAssigned;
import com.deappec.appointment.infraestructure.persistence.entities.ShiftAssignedEntity;

@Mapper(componentModel = "spring")
public interface ShiftAssignedPersistenceMapper {

	ShiftAssigned toShiftAssignedEntity(ShiftAssignedEntity shiftAssignedtEntity);

	ShiftAssignedEntity toShiftAssigned(ShiftAssigned shiftAssigned);

}
