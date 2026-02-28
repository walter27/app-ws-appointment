package com.deappec.appointment.application;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.DayWeekRepository;
import com.deappec.appointment.application.services.DayWeekService;
import com.deappec.appointment.domain.models.DayWeek;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DayWeekAdapter implements DayWeekService {

	private final DayWeekRepository repository;

	@Override
	public List<DayWeek> findAll() {
		return repository.findAll();
	}

}
