package com.deappec.appointment.infraestructure.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.deappec.appointment.infraestructure.persistence.entities.EmployeeEntity;

@Repository
public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Long> {

	@Query("""
			select e
			from EmployeeEntity e, AppointmentEntity target
			where target.id = :appointmentId
			and e.id not in (
				select sa.employee.id
				from ShiftAssignedEntity sa
				where sa.appointment.dateAppointment = target.dateAppointment
				and sa.appointment.hour.id = target.hour.id
			)
			order by e.id
			""")
	List<EmployeeEntity> findAvailableByAppointmentId(@Param("appointmentId") Long appointmentId, Pageable pageable);

	@Query("""
			select (count(e) > 0)
			from EmployeeEntity e, AppointmentEntity target
			where target.id = :appointmentId
			and e.id not in (
				select sa.employee.id
				from ShiftAssignedEntity sa
				where sa.appointment.dateAppointment = target.dateAppointment
				and sa.appointment.hour.id = target.hour.id
			)
			""")
	boolean existsAvailableByAppointmentId(@Param("appointmentId") Long appointmentId);

}
