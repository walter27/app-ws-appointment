package com.deappec.appointment.infraestructure.controllers.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deappec.appointment.domain.models.DocumentType;
import com.deappec.appointment.infraestructure.models.DocumentTypeDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DocumentTypeControllerMapper {

	DocumentTypeDto toDocumentType(DocumentType documentType);

	List<DocumentTypeDto> toDocumentTypeList(List<DocumentType> documentTypes);

}
