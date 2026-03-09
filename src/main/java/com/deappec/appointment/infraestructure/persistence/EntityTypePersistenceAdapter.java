package com.deappec.appointment.infraestructure.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.EntityTypeRepository;
import com.deappec.appointment.domain.models.EntityType;
import com.deappec.appointment.infraestructure.persistence.mappers.EntityTypePersistenceMapper;
import com.deappec.appointment.infraestructure.persistence.repositories.EntityTypeEntityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EntityTypePersistenceAdapter implements EntityTypeRepository {

	private final EntityTypeEntityRepository persistence;
	private final EntityTypePersistenceMapper mapper;

	@Override
	public Optional<EntityType> findById(Long id) {
		return persistence.findById(id).map(mapper::toEntityType);
	}

	@Override
	public List<EntityType> findAll() {
		return mapper.toEntityTypeList(persistence.findAll());
	}
}
