package com.deappec.appointment.domain.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftAssigned {

	private Long id;
	private LocalDateTime dateRegister;
	private Customer customer;
	private Employee employee;
	private Appointment appointment;

}
