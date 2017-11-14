package com.fxs.platform.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 回答领域类
 */
@Entity
public class Answer {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String key;

    private String description;

    private DisputeInfo nextDisputeInfo;

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

    public DisputeInfo getNextDisputeInfo() {
        return nextDisputeInfo;
    }

    public void setNextDisputeInfo(DisputeInfo nextDisputeInfo) {
        this.nextDisputeInfo = nextDisputeInfo;
    }
}
