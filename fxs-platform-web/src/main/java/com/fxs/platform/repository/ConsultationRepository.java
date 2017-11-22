package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Reservation;

@Repository
public interface ConsultationRepository extends FxsRepository<Reservation> {
	
	//List<Reservation> findByType(String consultationType);

	@Query("SELECT o FROM Reservation o where o.status=?1")
	List<Reservation> findByStatus(String status);

	@Query("SELECT o FROM Reservation o where o.id=?1")
    Reservation findOne(String consultationId);
}
