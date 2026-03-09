package com.deappec.appointment.infraestructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deappec.appointment.infraestructure.persistence.entities.EntityTypeEntity;

@Repository
public interface EntityTypeEntityRepository extends JpaRepository<EntityTypeEntity, Long> {

}
