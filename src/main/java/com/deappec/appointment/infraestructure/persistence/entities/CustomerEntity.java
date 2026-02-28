package com.deappec.appointment.infraestructure.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity extends PersonEntity {
}
