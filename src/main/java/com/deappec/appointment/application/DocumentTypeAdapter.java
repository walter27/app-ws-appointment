package com.deappec.appointment.application;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.DocumentTypeRepository;
import com.deappec.appointment.application.services.DocumentTypeService;
import com.deappec.appointment.domain.exceptions.DocumentTypeException;
import com.deappec.appointment.domain.models.DocumentType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DocumentTypeAdapter implements DocumentTypeService {

	private final DocumentTypeRepository repository;

	@Override
	public DocumentType findById(Long id) {
		return repository.findById(id).orElseThrow(DocumentTypeException::new);
	}

	@Override
	public List<DocumentType> findAll() {
		return repository.findAll();
	}

}
