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
@Table(name = "mnf_data")
public class MnfData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "mnf_data_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "mnf_data_id_seq", sequenceName = "mnf_data_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "divisionid")
	private Integer divId;
	@Column(name = "subdivisionid")
	private Integer subdivId;
	@Column(name = "loss")
	private String loss;
	@Column(name = "date")
	private Date date;
	@Column(name = "time")
	private Date time;
	
	public MnfData(){
		
	}

	public MnfData(Integer id, Integer divId, Integer subdivId, String loss, Date date, Date time) {
		super();
		this.id = id;
		this.divId = divId;
		this.subdivId = subdivId;
		this.loss = loss;
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

	public String getLoss() {
		return loss;
	}

	public void setLoss(String loss) {
		this.loss = loss;
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
