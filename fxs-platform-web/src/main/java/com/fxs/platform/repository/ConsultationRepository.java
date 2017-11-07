package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Consultation;

@Repository
public interface ConsultationRepository extends FxsRepository<Consultation> {
	
	List<Consultation> findByType(String consultationType);

	List<Consultation> findByStatus(String status);

	@Query("SELECT o FROM Consultation o where o.id=?1")
	Consultation findOne(String consultationId);
}
