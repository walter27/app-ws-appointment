package com.deappec.appointment.application.services;

import java.util.List;

import com.deappec.appointment.domain.models.DocumentType;

public interface DocumentTypeService {

	DocumentType findById(Long id);

	List<DocumentType> findAll();

}
