package com.deappec.appointment.infraestructure.persistence.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.deappec.appointment.domain.models.DocumentType;
import com.deappec.appointment.domain.models.Employee;
import com.deappec.appointment.infraestructure.persistence.entities.DocumentTypeEntity;
import com.deappec.appointment.infraestructure.persistence.entities.EmployeeEntity;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeePersistenceMapper {

	Employee toEmployee(EmployeeEntity employeeEntity);

	EmployeeEntity toEmployeeEntity(Employee employee);

	List<Employee> toEmployeeList(List<EmployeeEntity> employeeEntities);

	DocumentType toDocumentType(DocumentTypeEntity documentTypeEntity);

	DocumentTypeEntity toDocumentTypeEntity(DocumentType documentType);

}
