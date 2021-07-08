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
@Table(name = "counter_data")
public class CounterData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "counter_seq_id", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "counter_seq_id", sequenceName = "counter_seq_id", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "userid")
	private String userId;
	@Column(name = "counterno")
	private String counterNo;
	@Column(name = "shift")
	private String shift;
	@Column(name = "collection_type")
	private String collectionType;
	@Column(name = "cash")
	private Double cash;
	@Column(name = "cr_dt")
	private Date crDt;
	@Column(name = "mod_dt")
	private Date modDt;
	
	public CounterData(){
		
	}

	public CounterData(Integer id, String userId, String counterNo, String shift, String collectionType, Double cash,
			Date crDt, Date modDt) {
		super();
		this.id = id;
		this.userId = userId;
		this.counterNo = counterNo;
		this.shift = shift;
		this.collectionType = collectionType;
		this.cash = cash;
		this.crDt = crDt;
		this.modDt = modDt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCounterNo() {
		return counterNo;
	}

	public void setCounterNo(String counterNo) {
		this.counterNo = counterNo;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public String getCollectionType() {
		return collectionType;
	}

	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Date getCrDt() {
		return crDt;
	}

	public void setCrDt(Date crDt) {
		this.crDt = crDt;
	}

	public Date getModDt() {
		return modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
