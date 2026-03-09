package com.deappec.appointment.infraestructure.persistence.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deappec.appointment.domain.models.EntityType;
import com.deappec.appointment.infraestructure.persistence.entities.EntityTypeEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityTypePersistenceMapper {

	EntityType toEntityType(EntityTypeEntity entityTypeEntity);

	List<EntityType> toEntityTypeList(List<EntityTypeEntity> entityTypeEntities);
}
