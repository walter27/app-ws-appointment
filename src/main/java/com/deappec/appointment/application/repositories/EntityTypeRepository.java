package com.deappec.appointment.application.repositories;

import java.util.List;
import java.util.Optional;

import com.deappec.appointment.domain.models.EntityType;

public interface EntityTypeRepository {

	Optional<EntityType> findById(Long id);

	List<EntityType> findAll();
}
