package com.deappec.appointment.application.repositories;

import java.util.Optional;

import com.deappec.appointment.domain.models.TimeDay;

public interface TimeDayRepository {

	Optional<TimeDay> findById(Long id);

}
