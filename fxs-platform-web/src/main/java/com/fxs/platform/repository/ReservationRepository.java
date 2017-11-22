package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Reservation;

@Repository
public interface ReservationRepository extends FxsRepository<Reservation> {
	@Query("SELECT o FROM Reservation o where o.userId=?1 ORDER BY researvationDatetime DESC")
	List<Reservation> queryAll(String userId);
}
