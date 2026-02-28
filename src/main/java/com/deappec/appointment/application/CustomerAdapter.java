package com.deappec.appointment.application;

import com.deappec.appointment.domain.exceptions.DocumentTypeException;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.CustomerRepository;
import com.deappec.appointment.application.repositories.DocumentTypeRepository;
import com.deappec.appointment.application.services.CustomerService;
import com.deappec.appointment.domain.models.Customer;
import com.deappec.appointment.domain.models.DocumentType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerAdapter implements CustomerService {

	private final CustomerRepository repositoryCus;
	private final DocumentTypeRepository repositoryDot;

	@Override
	public Customer save(Customer customer) {
		DocumentType documentType = repositoryDot.findById(customer.getDocumentType().getId())
				.orElseThrow(DocumentTypeException::new);
		customer.setDocumentType(documentType);
		return repositoryCus.findByDni(customer.getDni()).map(customerSearch -> {
			customerSearch.setEmail(customer.getEmail());
			customerSearch.setLastName(customer.getLastName());
			customerSearch.setName(customer.getName());
			customerSearch.setPhone(customer.getPhone());
			return repositoryCus.save(customerSearch);
		}).orElse(repositoryCus.save(customer));

	}

}
