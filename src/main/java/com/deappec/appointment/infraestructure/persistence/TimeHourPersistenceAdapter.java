package com.deappec.appointment.infraestructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.TimeDayRepository;
import com.deappec.appointment.domain.models.TimeDay;
import com.deappec.appointment.infraestructure.persistence.mappers.TimeHourPersistenceMapper;
import com.deappec.appointment.infraestructure.persistence.repositories.TimeDayEntityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TimeHourPersistenceAdapter implements TimeDayRepository {

	private final TimeDayEntityRepository persistence;
	private final TimeHourPersistenceMapper mapper;

	@Override
	public Optional<TimeDay> findById(Long id) {
		return persistence.findById(id).map(mapper::toTimeDay);
	}

}
