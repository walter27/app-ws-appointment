package com.deappec.appointment.infraestructure.persistence.entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "day_week")
public class DayWeekEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "start_date")
	private LocalTime startDate;

	@Column(name = "end_date")
	private LocalTime endDate;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "day_week_time_hour", joinColumns = @JoinColumn(name = "day_week_id"), inverseJoinColumns = @JoinColumn(name = "time_hour_id"))
	private List<TimeHourEntity> timeHours = new ArrayList<>();

}
