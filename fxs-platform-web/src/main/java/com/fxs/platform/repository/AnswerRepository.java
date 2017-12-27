package com.fxs.platform.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Answer;

@Repository
public interface AnswerRepository extends FxsRepository<Answer> {
	
	@Query("SELECT o FROM Answer o WHERE o.question.id=?1")
	List<Answer> getAllAnswerByQuestionId(String qId);

	@Query("UPDATE Answer o SET o.nextQuestionId =?1 where o.id=?2")
	@Modifying
	void updateNextQuestion(String nextQuestionId, String answerId);
	
	@Query("SELECT o FROM Answer o WHERE o.id=?1")
	Answer findById(String aId);
}
