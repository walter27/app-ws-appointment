package com.deappec.appointment.infraestructure.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deappec.appointment.infraestructure.persistence.entities.CustomerEntity;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {
	
	Optional<CustomerEntity> findByDni(String dni);

}
