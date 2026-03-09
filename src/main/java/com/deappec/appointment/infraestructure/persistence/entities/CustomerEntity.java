package com.deappec.appointment.infraestructure.persistence.entities;

import jakarta.persistence.FetchType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity extends PersonEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entity_type_id")
	private EntityTypeEntity entityType;

	public EntityTypeEntity getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityTypeEntity entityType) {
		this.entityType = entityType;
	}
}
