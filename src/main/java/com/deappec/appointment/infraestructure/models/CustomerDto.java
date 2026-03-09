package com.deappec.appointment.infraestructure.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDto extends PersonDto {

	@NotNull(message = "entityTypeId is required")
	@Positive(message = "entityTypeId must be greater than 0")
	private Long entityTypeId;
}
