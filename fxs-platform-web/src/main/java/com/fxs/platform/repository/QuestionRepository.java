package com.fxs.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fxs.platform.domain.Question;

@Repository
public interface QuestionRepository extends FxsRepository<Question> {
	@Query("SELECT o FROM Question o WHERE o.isRootQuestion='Y'")
	public Question findRootQuestion();
	
	@Query("SELECT o FROM Question o WHERE o.questionType IS NOT NULL")
	public List<Question> findOptionalQuestions();
}
