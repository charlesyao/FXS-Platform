package com.fxs.platform.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Question {

	@Id
	@SequenceGenerator(name = "seq_contacts", sequenceName = "seq_contacts")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_contacts")

	private int id;

	private String name;

	@Transient
	private List<String> answers;

	@ManyToOne
	@JoinColumn(name = "disputeInfo_id")
	private DisputeInfo disputeInfo;

	public Question() {
	}

	public Question(int id, String name, DisputeInfo disputeInfo) {
		super();
		this.id = id;
		this.name = name;
		this.disputeInfo = disputeInfo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public DisputeInfo getDisputeInfo() {
		return disputeInfo;
	}

	public void setDisputeInfo(DisputeInfo disputeInfo) {
		this.disputeInfo = disputeInfo;
	}

}
