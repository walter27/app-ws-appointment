package com.deappec.appointment.infraestructure.controllers.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deappec.appointment.domain.models.EntityType;
import com.deappec.appointment.infraestructure.models.EntityTypeDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityTypeControllerMapper {

	EntityTypeDto toEntityType(EntityType entityType);

	List<EntityTypeDto> toEntityTypeList(List<EntityType> entityTypes);
}
