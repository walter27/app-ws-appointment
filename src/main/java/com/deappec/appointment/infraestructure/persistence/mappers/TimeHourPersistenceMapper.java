package com.deappec.appointment.infraestructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deappec.appointment.domain.models.TimeDay;
import com.deappec.appointment.infraestructure.persistence.entities.TimeHourEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TimeHourPersistenceMapper {

	TimeDay toTimeDay(TimeHourEntity timeHourEntity);

	TimeHourEntity toTimeDayEntity(TimeDay timeDay);
}
