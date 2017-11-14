package com.fxs.platform.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * 问题领域类
 */
public class Question {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String key;

    private String description;

    private boolean isFirstQuestion;

    private Set<Answer> answers;

    private Answer selectedAnswer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Answer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public boolean isFirstQuestion() {
        return isFirstQuestion;
    }

    public void setFirstQuestion(boolean firstQuestion) {
        isFirstQuestion = firstQuestion;
    }
}
