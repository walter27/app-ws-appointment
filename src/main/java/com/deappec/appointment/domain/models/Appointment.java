package com.deappec.appointment.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

	private Long id;
	private String name;
	private LocalDateTime dateRegister;
	private LocalDate dateAppointment;
	private TimeDay hour;
	private Integer avaible;

}
