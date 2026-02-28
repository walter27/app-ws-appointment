package com.deappec.appointment.infraestructure.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deappec.appointment.application.services.DocumentTypeService;
import com.deappec.appointment.infraestructure.controllers.mappers.DocumentTypeControllerMapper;
import com.deappec.appointment.infraestructure.models.DocumentTypeDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/document-types")
public class DocumentTypeControllerAdapter {

	private final DocumentTypeService service;
	private final DocumentTypeControllerMapper mapper;

	@GetMapping("/{id}")
	public DocumentTypeDto findById(@PathVariable Long id) {
		return mapper.toDocumentType(service.findById(id));
	}

	@GetMapping
	public List<DocumentTypeDto> findAll() {
		return mapper.toDocumentTypeList(service.findAll());
	}

}
