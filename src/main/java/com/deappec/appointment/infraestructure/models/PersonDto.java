package com.deappec.appointment.infraestructure.models;

import jakarta.validation.constraints.NotBlank;
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
public class PersonDto {

	private Long id;
	@NotBlank(message = "dni is required")
	private String dni;
	private String name;
	private String lastName;
	private String email;
	private String phone;
	@NotNull(message = "typeDocumentId is required")
	@Positive(message = "typeDocumentId must be greater than 0")
	private Long typeDocumentId;

}
