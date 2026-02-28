package com.deappec.appointment.infraestructure.persistence.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deappec.appointment.domain.models.DocumentType;
import com.deappec.appointment.infraestructure.persistence.entities.DocumentTypeEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentTypePersistenceMapper {

	DocumentType toDocumentType(DocumentTypeEntity documentTypeEntity);

	List<DocumentType> toDocumentTypeList(List<DocumentTypeEntity> documentTypeEntities);

}
