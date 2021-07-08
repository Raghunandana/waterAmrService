package com.enzen.waterMdm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "quality_parameter")
public class QualityParameter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "quality_param_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "quality_param_seq", sequenceName = "quality_param_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "parameter")
	private String parameter;
	@Column(name = "value")
	private Double value;
	@Column(name = "max_value")
	private Double maxValue;
	@Column(name = "remarks")
	private String remarks;
	
	public QualityParameter(){
		
	}

	public QualityParameter(Integer id, String parameter, Double value, Double maxValue, String remarks) {
		super();
		this.id = id;
		this.parameter = parameter;
		this.value = value;
		this.maxValue = maxValue;
		this.remarks = remarks;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
