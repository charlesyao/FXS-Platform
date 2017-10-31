package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Charles
 *
 */
@Entity
public class Lawsuit {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;

	/**
	 * 打官司类型： 找律师打官司/自助打官司
	 * 
	 * @see com.fxs.platform.utils.LawsuitType
	 */
	private String type;

	/**
	 * 案件状态
	 * 
	 * @see com.fxs.platform.utils.CaseStatus
	 */
	private String status;
	
	/**
	 * 案件类型: 劳动纠纷, 刑事案件...
	 */
	private String falltypus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFalltypus() {
		return falltypus;
	}

	public void setFalltypus(String falltypus) {
		this.falltypus = falltypus;
	}

}
