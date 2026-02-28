package com.deappec.appointment.infraestructure.persistence.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deappec.appointment.domain.models.DayWeek;
import com.deappec.appointment.infraestructure.persistence.entities.DayWeekEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DayWeekPesistenceMapper {

	List<DayWeek> toDayWeekEntities(List<DayWeekEntity> daysWeek);

}
