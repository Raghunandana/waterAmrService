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
@Table(name = "notification")
public class Notification implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "notification_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "notification_id_seq", sequenceName = "notification_id_seq",allocationSize=1)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "event", nullable = true)
	private String event;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "unit_id")
	private String unitId;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "cr_date")
	private Date crDate;
	
	public Notification() {
		
	}

	public Notification(Integer id, String event, String message, String unitId, boolean status, Date crDate) {
		
		super();
		this.id = id;
		this.event = event;
		this.message = message;
		this.unitId = unitId;
		this.status = status;
		this.crDate = crDate;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCrDate() {
		return crDate;
	}

	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

}
