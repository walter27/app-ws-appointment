package com.deappec.appointment.application.repositories;

import java.util.List;

import com.deappec.appointment.domain.models.DayWeek;

public interface DayWeekRepository {

	List<DayWeek> findAll();

}
