package com.deappec.appointment.infraestructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.DocumentTypeRepository;
import com.deappec.appointment.domain.models.DocumentType;
import com.deappec.appointment.infraestructure.persistence.mappers.DocumentTypePersistenceMapper;
import com.deappec.appointment.infraestructure.persistence.repositories.DocumentTypeEntityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DocumentTypePersitenceAdapter implements DocumentTypeRepository {

	private final DocumentTypeEntityRepository persistence;
	private final DocumentTypePersistenceMapper mapper;

	@Override
	public Optional<DocumentType> findById(Long id) {
		return persistence.findById(id).map(mapper::toDocumentType);
	}

	@Override
	public List<DocumentType> findAll() {
		return mapper.toDocumentTypeList(persistence.findAll());
	}

}
