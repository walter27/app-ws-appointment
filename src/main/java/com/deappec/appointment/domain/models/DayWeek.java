package com.deappec.appointment.domain.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.deappec.appointment.infraestructure.persistence.entities.TimeHourEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DayWeek {

	private Long id;
	private String name;
	private LocalTime startDate;
	private LocalTime endDate;
	private List<TimeHourEntity> timeHours = new ArrayList<>();

}
