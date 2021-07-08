package com.enzen.waterMdm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "event_master")
public class EventMaster implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "type", nullable = true)
	private String type;
	
	@Column(name = "parameter")
	private String parameter;
	
	@Column(name = "min")
	private Double min;
	
	@Column(name = "max")
	private Double max;
	
	public EventMaster() {
		
	}

	public EventMaster(Integer id, String type, String parameter, Double min, Double max) {
		
		super();
		this.id = id;
		this.type = type;
		this.parameter = parameter;
		this.min = min;
		this.max = max;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}


}
