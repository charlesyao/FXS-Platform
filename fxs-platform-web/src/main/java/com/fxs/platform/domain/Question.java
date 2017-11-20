package com.fxs.platform.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Question {

	@Id
	@SequenceGenerator(name = "seq_contacts", sequenceName = "seq_contacts")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_contacts")

	private int id;

	private String description;

	private String isRootQuestion;

	/*
	 * @Transient private List<String> answers;
	 */

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Answer> answers;

	@ManyToOne
	@JoinColumn(name = "cases_id")
	private Cases cases;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "disputeInfo_id") private DisputeInfo disputeInfo;
	 */

	public Question() {
	}

	public Question(int id, String description/* , DisputeInfo disputeInfo */) {
		super();
		this.id = id;
		this.description = description;
		// this.disputeInfo = disputeInfo;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	/*
	 * public DisputeInfo getDisputeInfo() { return disputeInfo; }
	 * 
	 * public void setDisputeInfo(DisputeInfo disputeInfo) { this.disputeInfo =
	 * disputeInfo; }
	 */

	public String getIsRootQuestion() {
		return isRootQuestion;
	}

	public void setIsRootQuestion(String isRootQuestion) {
		this.isRootQuestion = isRootQuestion;
	}

	public Cases getCases() {
		return cases;
	}

	public void setCases(Cases cases) {
		this.cases = cases;
	}
	
}
