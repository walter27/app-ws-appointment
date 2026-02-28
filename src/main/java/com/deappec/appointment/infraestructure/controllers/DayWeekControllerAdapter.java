package com.deappec.appointment.infraestructure.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deappec.appointment.application.services.DayWeekService;
import com.deappec.appointment.infraestructure.controllers.mappers.DayWeekControllerMapper;
import com.deappec.appointment.infraestructure.models.DayWeekDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/days")
public class DayWeekControllerAdapter {

	private final DayWeekService service;
	private final DayWeekControllerMapper mapper;

	@GetMapping
	public List<DayWeekDto> findAll() {
		return mapper.toDaysWeek(service.findAll());
	}

}
