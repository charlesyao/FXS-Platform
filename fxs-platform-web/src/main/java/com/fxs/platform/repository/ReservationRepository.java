package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Reservation;

@Repository
public interface ReservationRepository extends FxsRepository<Reservation> {
	@Query("SELECT o FROM Reservation o where o.userId=?1 ORDER BY researvationDatetime DESC")
	Page<Reservation> queryAll(String userId, Pageable pageable);
	
	
	@Query("SELECT o FROM Reservation o where o.status != '5' AND o.expiredDate=CURDATE()")
	List<Reservation> findByStatus();
	
	@Query("UPDATE Reservation o SET o.status =?1 where o.id=?2")
	@Modifying
	void updateStatus(String statusCode, String reservationId);
}
