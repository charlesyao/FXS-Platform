package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class DetailedInquiry {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;

	private String firstComments;

	private String secondComments;

	private String thirdComments;

	@ManyToOne
	@JoinColumn(name = "cases_id")
	private Cases Cases;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstComments() {
		return firstComments;
	}

	public void setFirstComments(String firstComments) {
		this.firstComments = firstComments;
	}

	public String getSecondComments() {
		return secondComments;
	}

	public void setSecondComments(String secondComments) {
		this.secondComments = secondComments;
	}

	public String getThirdComments() {
		return thirdComments;
	}

	public void setThirdComments(String thirdComments) {
		this.thirdComments = thirdComments;
	}

}
