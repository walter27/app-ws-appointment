package com.deappec.appointment.application.repositories;

import java.util.Optional;

import com.deappec.appointment.domain.models.Customer;

public interface CustomerRepository {

	Customer save(Customer customer);
	
	Optional<Customer> findByDni(String dni);

	Optional<Customer> findById(Long id);

}
