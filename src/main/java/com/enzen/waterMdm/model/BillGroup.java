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
@Table(name = "billgroup")
public class BillGroup implements Serializable {

	@Id
	@GeneratedValue(generator = "billgroup_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "billgroup_id_seq", sequenceName = "billgroup_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "code")
	private String code;
	@Column(name = "description")
	private String description;
	@Column(name = "startday")
	private Integer startDay;
	@Column(name = "addedat")
	private Date addedAt;
	@Column(name = "bimonthly")
	private boolean biMonthly;
	@Column(name = "bimonthlyeven")
	private boolean biMonthlyEven;
	@Column(name = "duedays")
	private Integer dueDays;
	@Column(name = "divisionid")
	private Integer divisionId;
	
	public BillGroup(){
		
	}
	
	public BillGroup(Integer id, String code, String description, Integer startDay, Date addedAt, boolean biMonthly,
			boolean biMonthlyEven, Integer dueDays, Integer divisionId) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.startDay = startDay;
		this.addedAt = addedAt;
		this.biMonthly = biMonthly;
		this.biMonthlyEven = biMonthlyEven;
		this.dueDays = dueDays;
		this.divisionId = divisionId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStartDay() {
		return startDay;
	}

	public void setStartDay(Integer startDay) {
		this.startDay = startDay;
	}

	public Date getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(Date addedAt) {
		this.addedAt = addedAt;
	}

	public boolean isBiMonthly() {
		return biMonthly;
	}

	public void setBiMonthly(boolean biMonthly) {
		this.biMonthly = biMonthly;
	}

	public boolean isBiMonthlyEven() {
		return biMonthlyEven;
	}

	public void setBiMonthlyEven(boolean biMonthlyEven) {
		this.biMonthlyEven = biMonthlyEven;
	}

	public Integer getDueDays() {
		return dueDays;
	}

	public void setDueDays(Integer dueDays) {
		this.dueDays = dueDays;
	}

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}


}
