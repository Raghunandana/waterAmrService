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
@Table(name = "water_demand_data")
public class WaterDemandData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "water_demand_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "water_demand_id_seq", sequenceName = "water_demand_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "divisionid")
	private Integer divId;
	@Column(name = "subdivisionid")
	private Integer subdivId;
	@Column(name = "demand_type")
	private String demandType;
	@Column(name = "usage_type")
	private String usageType;
	@Column(name = "variation_type")
	private String variationType;
	@Column(name = "value")
	private Double value;
	@Column(name = "date")
	private Date date;
	@Column(name = "time")
	private Date time;
	
	public WaterDemandData(){
		
	}

	public WaterDemandData(Integer id, Integer divId, Integer subdivId, String demandType, String usageType,
			String variationType, Double value, Date date, Date time) {
		super();
		this.id = id;
		this.divId = divId;
		this.subdivId = subdivId;
		this.demandType = demandType;
		this.usageType = usageType;
		this.variationType = variationType;
		this.value = value;
		this.date = date;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDivId() {
		return divId;
	}

	public void setDivId(Integer divId) {
		this.divId = divId;
	}

	public Integer getSubdivId() {
		return subdivId;
	}

	public void setSubdivId(Integer subdivId) {
		this.subdivId = subdivId;
	}

	public String getDemandType() {
		return demandType;
	}

	public void setDemandType(String demandType) {
		this.demandType = demandType;
	}

	public String getUsageType() {
		return usageType;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	public String getVariationType() {
		return variationType;
	}

	public void setVariationType(String variationType) {
		this.variationType = variationType;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
