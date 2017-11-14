package com.fxs.platform.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * 纷争基本信息领域类
 */
@Entity
public class DisputeInfo {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    /**
     * 纷争的提问
     */
    private Question question;

    /**
     * 纷争提问的回答
     */
    private Answer answer;

    /**
     * 下一个纷争的问题和回答
     */
    private Set<DisputeInfo> nextDisputes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Set<DisputeInfo> getNextDisputes() {
        return nextDisputes;
    }

    public void setNextDisputes(Set<DisputeInfo> nextDisputes) {
        this.nextDisputes = nextDisputes;
    }
}
