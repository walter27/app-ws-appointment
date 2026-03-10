package com.deappec.appointment.application;

import java.util.Objects;

//import com.deappec.appointment.domain.exceptions.DocumentTypeException;
import com.deappec.appointment.domain.exceptions.EntityTypeException;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.CustomerRepository;
//import com.deappec.appointment.application.repositories.DocumentTypeRepository;
import com.deappec.appointment.application.repositories.EntityTypeRepository;
import com.deappec.appointment.application.services.CustomerService;
import com.deappec.appointment.domain.exceptions.CustomerException;
import com.deappec.appointment.domain.models.Customer;
//import com.deappec.appointment.domain.models.DocumentType;
import com.deappec.appointment.domain.models.EntityType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerAdapter implements CustomerService {

	private final CustomerRepository repositoryCus;
	//private final DocumentTypeRepository repositoryDot;
	private final EntityTypeRepository entityTypeRepository;

	@Override
	public Customer save(Customer customer) {
		validateCustomer(customer);

		/*DocumentType documentType = repositoryDot.findById(customer.getDocumentType().getId())
				.orElseThrow(DocumentTypeException::new);*/
		EntityType entityType = entityTypeRepository.findById(customer.getEntityType().getId())
				.orElseThrow(EntityTypeException::new);
		//customer.setDocumentType(documentType);
		customer.setEntityType(entityType);
		if (Objects.isNull(customer.getDni()) || customer.getDni().isBlank()) {
			return repositoryCus.save(customer);
		}

		return repositoryCus.findByDni(customer.getDni()).map(customerSearch -> {
			customerSearch.setEmail(customer.getEmail());
			customerSearch.setLastName(customer.getLastName());
			customerSearch.setName(customer.getName());
			customerSearch.setPhone(customer.getPhone());
			//customerSearch.setDocumentType(documentType);
			customerSearch.setEntityType(entityType);
			return repositoryCus.save(customerSearch);
		}).orElse(repositoryCus.save(customer));

	}

	private void validateCustomer(Customer customer) {
		if (Objects.isNull(customer)) {
			throw new CustomerException();
		}
		/*if (Objects.isNull(customer.getDocumentType()) || Objects.isNull(customer.getDocumentType().getId())) {
			throw new DocumentTypeException();
		}*/
		if (Objects.isNull(customer.getEntityType()) || Objects.isNull(customer.getEntityType().getId())) {
			throw new EntityTypeException();
		}
	}

}
