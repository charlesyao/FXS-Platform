package com.fxs.platform.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.DisputeInfo;


@Repository
public interface DisputeInfoRepository extends FxsRepository<DisputeInfo> {
	@Query("SELECT o FROM DisputeInfo o where o.id=?1")
	DisputeInfo findOne(String dId);
}
