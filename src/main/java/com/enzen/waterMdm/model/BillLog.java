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
@Table(name = "billlog")
public class BillLog implements Serializable {

	@Id
	@GeneratedValue(generator = "billlog_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "billlog_id_seq", sequenceName = "billlog_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "billgroupdetailid")
	private Integer billGroupdetailId;
	@Column(name = "status")
	private String status;
	@Column(name = "changedby")
	private String changedBy;
	@Column(name = "changedat")
	private Date changedAt;
	
	public BillLog(){
		
	}

	public BillLog(Integer id, Integer billGroupdetailId, String status, String changedBy, Date changedAt) {
		super();
		this.id = id;
		this.billGroupdetailId = billGroupdetailId;
		this.status = status;
		this.changedBy = changedBy;
		this.changedAt = changedAt;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getBillGroupdetailId() {
		return billGroupdetailId;
	}


	public void setBillGroupdetailId(Integer billGroupdetailId) {
		this.billGroupdetailId = billGroupdetailId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getChangedBy() {
		return changedBy;
	}


	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}


	public Date getChangedAt() {
		return changedAt;
	}


	public void setChangedAt(Date changedAt) {
		this.changedAt = changedAt;
	}
	

}
