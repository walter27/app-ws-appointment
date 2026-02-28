package com.deappec.appointment.infraestructure.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deappec.appointment.infraestructure.persistence.entities.AppointmentEntity;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Long> {

	long countByHourId(Long hourId);

	long countByHourIdAndDateAppointment(Long hourId, LocalDate dateAppointment);

	@Query("""
			select a
			from AppointmentEntity a
			where a.dateAppointment between :start and :end
			and a.avaible >= 1
			and a.avaible <= (select count(e) from EmployeeEntity e)
			""")
	List<AppointmentEntity> findByDateAppointmentBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
