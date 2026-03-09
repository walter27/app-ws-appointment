package com.deappec.appointment.infraestructure.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deappec.appointment.application.services.EntityTypeService;
import com.deappec.appointment.infraestructure.controllers.mappers.EntityTypeControllerMapper;
import com.deappec.appointment.infraestructure.models.EntityTypeDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entity-types")
public class EntityTypeControllerAdapter {

	private final EntityTypeService service;
	private final EntityTypeControllerMapper mapper;

	@GetMapping("/{id}")
	public EntityTypeDto findById(@PathVariable Long id) {
		return mapper.toEntityType(service.findById(id));
	}

	@GetMapping
	public List<EntityTypeDto> findAll() {
		return mapper.toEntityTypeList(service.findAll());
	}
}
