package com.deappec.appointment.infraestructure.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.DayWeekRepository;
import com.deappec.appointment.domain.models.DayWeek;
import com.deappec.appointment.infraestructure.persistence.mappers.DayWeekPesistenceMapper;
import com.deappec.appointment.infraestructure.persistence.repositories.DayWeekEntityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DayPersistenceAdapter implements DayWeekRepository {

	private final DayWeekEntityRepository persistence;
	private final DayWeekPesistenceMapper mapper;

	@Override
	public List<DayWeek> findAll() {
		return mapper.toDayWeekEntities(persistence.findAll());
	}

}
