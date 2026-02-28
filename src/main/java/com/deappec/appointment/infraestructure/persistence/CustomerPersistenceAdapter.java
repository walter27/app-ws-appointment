package com.deappec.appointment.infraestructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.deappec.appointment.application.repositories.CustomerRepository;
import com.deappec.appointment.domain.models.Customer;
import com.deappec.appointment.infraestructure.persistence.mappers.CustomerPersistenceMapper;
import com.deappec.appointment.infraestructure.persistence.repositories.CustomerEntityRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerPersistenceAdapter implements CustomerRepository {

	private final CustomerEntityRepository persitence;
	private final CustomerPersistenceMapper mapper;

	@Override
	public Customer save(Customer customer) {
		return mapper.toCustomerEntity(persitence.save(mapper.toCustomer(customer)));
	}

	@Override
	public Optional<Customer> findByDni(String dni) {
		return persitence.findByDni(dni).map(mapper::toCustomerEntity);
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return persitence.findById(id).map(mapper::toCustomerEntity);
	}

}
