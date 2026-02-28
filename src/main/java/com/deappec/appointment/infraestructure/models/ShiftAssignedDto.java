package com.deappec.appointment.infraestructure.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftAssignedDto {

	private Long id;
	private LocalDateTime dateRegister;
	private Long customerId;
	private Long employeeId;
	private Long appointmentId;
}
