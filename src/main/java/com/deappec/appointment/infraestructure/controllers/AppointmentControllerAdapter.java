package com.deappec.appointment.infraestructure.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deappec.appointment.application.services.AppointmentService;
import com.deappec.appointment.infraestructure.controllers.mappers.AppointmentControllerMapper;
import com.deappec.appointment.infraestructure.models.AppointmentDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
public class AppointmentControllerAdapter {

	private final AppointmentService service;
	private final AppointmentControllerMapper mapper;

	@PostMapping
	public ResponseEntity<AppointmentDto> save(@RequestBody AppointmentDto appointmentDto) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(mapper.toAppointment(service.save(mapper.toAppointmentDto(appointmentDto))));
	}

	@GetMapping
	public List<AppointmentDto> findByDateAppointmentBetween(@RequestParam("start") LocalDate start,
			@RequestParam("end") LocalDate end) {
		return mapper.toListAppointmentDto(service.findByDateAppointmentBetween(start, end));
	}

}
