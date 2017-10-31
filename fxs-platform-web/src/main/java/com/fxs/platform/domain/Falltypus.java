/**
 * 
 */
package com.fxs.platform.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Charles
 *
 */
@Entity
public class Falltypus {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	private String id;

	private String desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
