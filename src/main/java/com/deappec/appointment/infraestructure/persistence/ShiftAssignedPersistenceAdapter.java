package com.deappec.appointment.infraestructure.persistence;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.ShiftAssignedRepository;
import com.deappec.appointment.domain.models.ShiftAssigned;
import com.deappec.appointment.infraestructure.persistence.mappers.ShiftAssignedPersistenceMapper;
import com.deappec.appointment.infraestructure.persistence.repositories.ShiftAssignedEntityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShiftAssignedPersistenceAdapter implements ShiftAssignedRepository {

	private final ShiftAssignedEntityRepository persistence;
	private final ShiftAssignedPersistenceMapper mapper;

	@Override
	public ShiftAssigned save(ShiftAssigned shiftAssigned) {
		return mapper.toShiftAssignedEntity(persistence.save(mapper.toShiftAssigned(shiftAssigned)));
	}

}
