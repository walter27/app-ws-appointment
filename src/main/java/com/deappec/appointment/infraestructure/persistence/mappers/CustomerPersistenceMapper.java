package com.deappec.appointment.infraestructure.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deappec.appointment.domain.models.Customer;
import com.deappec.appointment.domain.models.DocumentType;
import com.deappec.appointment.domain.models.EntityType;
import com.deappec.appointment.infraestructure.persistence.entities.CustomerEntity;
import com.deappec.appointment.infraestructure.persistence.entities.DocumentTypeEntity;
import com.deappec.appointment.infraestructure.persistence.entities.EntityTypeEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerPersistenceMapper {

	Customer toCustomerEntity(CustomerEntity customerEntity);

	CustomerEntity toCustomer(Customer customer);

	DocumentType toDocumentType(DocumentTypeEntity documentTypeEntity);

	DocumentTypeEntity toDocumentTypeEntity(DocumentType documentType);
	
	EntityType toEntityType(EntityTypeEntity entityTypeEntity);
	
	EntityTypeEntity toEntityTypeEntity(EntityType entityType);

}
