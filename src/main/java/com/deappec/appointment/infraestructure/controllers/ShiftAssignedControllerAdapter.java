package com.deappec.appointment.infraestructure.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deappec.appointment.application.services.ShiftAssignedService;
import com.deappec.appointment.infraestructure.controllers.mappers.ShiftAssignedControllerMapper;
import com.deappec.appointment.infraestructure.models.ShiftAssignedCreateDto;
import com.deappec.appointment.infraestructure.models.ShiftAssignedDto;
import com.deappec.appointment.infraestructure.storage.ShiftAssignedAttachmentStorage;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/shift-assigned")
public class ShiftAssignedControllerAdapter {

	private final ShiftAssignedService service;
	private final ShiftAssignedControllerMapper mapper;
	private final ShiftAssignedAttachmentStorage attachmentStorage;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<ShiftAssignedDto> save(@Valid @ModelAttribute ShiftAssignedCreateDto shiftAssignedDto) {
		var shiftAssigned = mapper.toShiftAssignedCreate(shiftAssignedDto);
		shiftAssigned.setFilePath(attachmentStorage.store(shiftAssignedDto.getAttached()));
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(mapper.toShiftAssigned(service.save(shiftAssigned)));
	}

}
