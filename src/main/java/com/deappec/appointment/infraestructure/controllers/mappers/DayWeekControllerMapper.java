package com.deappec.appointment.infraestructure.controllers.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deappec.appointment.domain.models.DayWeek;
import com.deappec.appointment.infraestructure.models.DayWeekDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DayWeekControllerMapper {

	DayWeekDto toDayWeek(DayWeek dayWeek);

	DayWeek toDayWeekDto(DayWeekDto dayWeekDto);

	List<DayWeekDto> toDaysWeek(List<DayWeek> daysWeek);

}
