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
@Table(name = "quality_data")
public class QualityData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "quality_data_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "quality_data_id_seq", sequenceName = "quality_data_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "unitid")
	private Integer unitId;
	@Column(name = "quality_id")
	private Integer qualityId;
	@Column(name = "value")
	private Double value;
	@Column(name = "date")
	private Date date;
	@Column(name = "time")
	private Date time;
	
	public QualityData(){
		
	}

	public QualityData(Integer id, Integer unitId, Integer qualityId, Double value, Date date, Date time) {
		super();
		this.id = id;
		this.unitId = unitId;
		this.qualityId = qualityId;
		this.value = value;
		this.date = date;
		this.time = time;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getQualityId() {
		return qualityId;
	}

	public void setQualityId(Integer qualityId) {
		this.qualityId = qualityId;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	

}
