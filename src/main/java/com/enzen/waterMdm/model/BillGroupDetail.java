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
@Table(name = "billgroupdetail")
public class BillGroupDetail implements Serializable {

	@Id
	@GeneratedValue(generator = "billgroupdetail_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "billgroupdetail_id_seq", sequenceName = "billgroupdetail_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "billmonth")
	private Integer billMonth;
	@Column(name = "billyear")
	private Integer billYear;
	@Column(name = "divisionid")
	private Integer divisionId;
	@Column(name = "subdivisionid")
	private Integer subDivisionId;
	@Column(name = "billgroupid")
	private Integer billgroupId;
	@Column(name = "startdate")
	private Date startDate;
	@Column(name = "enddate")
	private Date endDate;
	@Column(name = "billdate")
	private Date billDate;
	@Column(name = "duedate")
	private Date dueDate;
	@Column(name = "status")
	private String status;
	@Column(name = "authorize")
	private boolean authorize;
	
	public BillGroupDetail(){
		
	}

	public BillGroupDetail(Integer id, Integer billMonth, Integer billYear, Integer divisionId, Integer subDivisionId,
			Integer billgroupId, Date startDate, Date endDate, Date billDate, Date dueDate, String status, boolean authorize) {
		super();
		this.id = id;
		this.billMonth = billMonth;
		this.billYear = billYear;
		this.divisionId = divisionId;
		this.subDivisionId = subDivisionId;
		this.billgroupId = billgroupId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.billDate = billDate;
		this.dueDate = dueDate;
		this.status = status;
		this.authorize = authorize;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(Integer billMonth) {
		this.billMonth = billMonth;
	}

	public Integer getBillYear() {
		return billYear;
	}

	public void setBillYear(Integer billYear) {
		this.billYear = billYear;
	}

	public Integer getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}

	public Integer getSubDivisionId() {
		return subDivisionId;
	}

	public void setSubDivisionId(Integer subDivisionId) {
		this.subDivisionId = subDivisionId;
	}

	public Integer getBillgroupId() {
		return billgroupId;
	}

	public void setBillgroupId(Integer billgroupId) {
		this.billgroupId = billgroupId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isAuthorize() {
		return authorize;
	}

	public void setAuthorize(boolean authorize) {
		this.authorize = authorize;
	}

}
