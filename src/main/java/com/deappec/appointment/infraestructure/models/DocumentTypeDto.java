package com.deappec.appointment.infraestructure.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeDto {

	private Long id;
	private String name;
	private String description;

}
