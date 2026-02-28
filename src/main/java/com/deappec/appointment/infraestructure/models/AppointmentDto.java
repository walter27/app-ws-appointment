package com.deappec.appointment.infraestructure.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.deappec.appointment.domain.models.TimeDay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {

	private Long id;
	private String name;
	private LocalDate dateAppointment;
	private LocalDateTime dateRegister;
	private TimeDay hour;
	private Integer avaible;

}
