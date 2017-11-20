package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 *
 */
@Entity
public class Questionnaire {
	/**
	 * 主键,根据案件类型以及子类型生成
	 */
	@Id
	@GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
	private String id;

	/**
	 * 问卷标题
	 */
	private String title;

	/**
	 * 默认一个问题最多提供四个可选答案
	 */
	private String answer1;

	private String answer2;

	private String answer3;

	private String answer4;

	/**
	 * 父问题，整个问卷是树形结构,引导式
	 */
	private String parentId;
	
	/**
	 * 显示条件
	 */
	//private String condition;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/*public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}*/
}