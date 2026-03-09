package com.deappec.appointment.application.services;

import java.util.List;

import com.deappec.appointment.domain.models.EntityType;

public interface EntityTypeService {

	EntityType findById(Long id);

	List<EntityType> findAll();
}
