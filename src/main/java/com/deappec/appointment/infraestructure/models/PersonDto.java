package com.deappec.appointment.infraestructure.models;

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
	private String dni;
	private String name;
	private String lastName;
	private String email;
	private String phone;
	private Long typeDocumentId;

}
