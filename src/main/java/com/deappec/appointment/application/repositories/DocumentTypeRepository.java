package com.deappec.appointment.application.repositories;

import java.util.List;
import java.util.Optional;

import com.deappec.appointment.domain.models.DocumentType;

public interface DocumentTypeRepository {

	Optional<DocumentType> findById(Long id);

	List<DocumentType> findAll();

}
