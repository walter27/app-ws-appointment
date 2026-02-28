package com.deappec.appointment.infraestructure.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deappec.appointment.application.services.ShiftAssignedService;
import com.deappec.appointment.infraestructure.controllers.mappers.ShiftAssignedControllerMapper;
import com.deappec.appointment.infraestructure.models.ShiftAssignedDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shift-assigned")
public class ShiftAssignedControllerAdapter {

	private final ShiftAssignedService service;
	private final ShiftAssignedControllerMapper mapper;

	@PostMapping
	public ResponseEntity<ShiftAssignedDto> save(@RequestBody ShiftAssignedDto shiftAssignedDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(mapper.toShiftAssigned(service.save(mapper.toShiftAssignedDto(shiftAssignedDto))));
	}

}
