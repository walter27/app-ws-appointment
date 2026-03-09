package com.deappec.appointment.application;

import java.util.List;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.EntityTypeRepository;
import com.deappec.appointment.application.services.EntityTypeService;
import com.deappec.appointment.domain.exceptions.EntityTypeException;
import com.deappec.appointment.domain.models.EntityType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EntityTypeAdapter implements EntityTypeService {

	private final EntityTypeRepository repository;

	@Override
	public EntityType findById(Long id) {
		return repository.findById(id).orElseThrow(EntityTypeException::new);
	}

	@Override
	public List<EntityType> findAll() {
		return repository.findAll();
	}
}
