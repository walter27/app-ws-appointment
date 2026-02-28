package com.deappec.appointment.infraestructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deappec.appointment.infraestructure.persistence.entities.DocumentTypeEntity;

@Repository
public interface DocumentTypeEntityRepository extends JpaRepository<DocumentTypeEntity, Long> {

}
