package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
public class CaseType {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;

	private Set<CaseType> childCaseTypes;
	
	private int level;
	
	private String key;
	
	private String description;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
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

    public Set<CaseType> getChildCaseTypes() {
        return childCaseTypes;
    }

    public void setChildCaseTypes(Set<CaseType> childCaseTypes) {
        this.childCaseTypes = childCaseTypes;
    }
}
