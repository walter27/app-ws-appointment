package com.deappec.appointment.infraestructure.controllers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.deappec.appointment.domain.models.Customer;
import com.deappec.appointment.infraestructure.models.CustomerDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerControllerMapper {

	@Mapping(target = "typeDocumentId", source = "documentType.id")
	@Mapping(target = "entityTypeId", source = "entityType.id")
	CustomerDto toCustomer(Customer customer);

	@Mapping(target = "documentType.id", source = "typeDocumentId")
	@Mapping(target = "entityType.id", source = "entityTypeId")
	Customer toCustomerDto(CustomerDto customerDto);

}
