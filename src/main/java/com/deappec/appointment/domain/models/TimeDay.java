package com.deappec.appointment.domain.models;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TimeDay {

	private Long id;
	private LocalTime startTime;
	private LocalTime endTime;

}
