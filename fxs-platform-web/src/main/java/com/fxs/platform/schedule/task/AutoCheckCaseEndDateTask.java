package com.fxs.platform.schedule.task;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fxs.platform.domain.Cases;
import com.fxs.platform.domain.Reservation;
import com.fxs.platform.repository.CasesRepository;
import com.fxs.platform.repository.ReservationRepository;
import com.fxs.platform.repository.condition.CasesCondition;
import com.fxs.platform.repository.condition.ReservationCondition;
import com.fxs.platform.repository.specification.CaseSpecification;
import com.fxs.platform.repository.specification.ReservationSpecification;
import com.fxs.platform.utils.CaseStatus;

/**
 * 系统自动按照固定周期(默认每天23:55)检查所有案件状态，针对已经到达指定日期还未结束的案件，系统自动将其标记为“结束”
 *
 */
@Component
@Transactional
public class AutoCheckCaseEndDateTask {
	static final Logger logger = LoggerFactory.getLogger(AutoCheckCaseEndDateTask.class);

	@Autowired
	CasesRepository casesRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Scheduled(cron = "${scheduling.job.cron}")
	public void checkEndDate() {
		// 检查所有的法律咨询和打官司案件
		checkCase(casesRepository);
		
		//检查所有电话咨询案件
		checkReservation(reservationRepository);
	}

	@Async
	private void checkCase(CasesRepository casesRepository) {
		CasesCondition condition = new CasesCondition();
		String[] status = {"0", "1", "2", "3", "4"};
		condition.setStatus(status);
		condition.setExpiredDate(LocalDateTime.now());
		
		List<Cases> caseList = casesRepository.findAll(new CaseSpecification(condition));
		
		//List<Cases> caseList1 = casesRepository.findByStatus();

		for (Cases cases : caseList) {
			casesRepository.updateStatus(CaseStatus.END.getStatus(), cases.getId());
		}
	}

	@Async
	private void checkReservation(ReservationRepository reservationRepository) {
		ReservationCondition condition = new ReservationCondition();
		String[] status = {"0", "1", "2", "3", "4"};
		condition.setStatus(status);
		condition.setResearvationDatetime(LocalDateTime.now());
		
		List<Reservation> reservationList = reservationRepository.findAll(new ReservationSpecification(condition));
				
				
		//List<Reservation> reservationList = reservationRepository.findByStatus();
		for (Reservation reservation : reservationList) {
			reservationRepository.updateStatus(CaseStatus.END.getStatus(), reservation.getId());
		}
	}
}
