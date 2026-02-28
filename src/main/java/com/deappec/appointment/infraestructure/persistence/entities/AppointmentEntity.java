package com.deappec.appointment.infraestructure.persistence.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointment")
public class AppointmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "date_appointment")
	private LocalDate dateAppointment;
	
	@Column(name = "date_register")
	private LocalDateTime dateRegister;
	
	@Column(name = "avaible")
	private Integer avaible;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "time_hour_id")
	private TimeHourEntity hour;

}
