package com.deappec.appointment.infraestructure.models;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftAssignedCreateDto {

	private LocalDateTime dateRegister;
	@NotNull(message = "customerId is required")
	@Positive(message = "customerId must be greater than 0")
	private Long customerId;
	@NotNull(message = "appointmentId is required")
	@Positive(message = "appointmentId must be greater than 0")
	private Long appointmentId;
	private MultipartFile attached;

}
