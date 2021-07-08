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
@Table(name = "bills")
public class BillingReportMaster implements Serializable {

	@Id
	@GeneratedValue(generator = "bills_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "bills_id_seq", sequenceName = "bills_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "monthyear")
	private String monthYear;
	@Column(name = "unitid")
	private Integer unitId;
	@Column(name = "firstreading")
	private Double firstReading;
	@Column(name = "lastreading")
	private Double lastReading;
	@Column(name = "consumption")
	private Double consumption;
	@Column(name = "watercharge")
	private Double watercharge;
	@Column(name = "metercharge")
	private Double metercharge;
	@Column(name = "sanitarycharge")
	private Double sanitarycharge;
	@Column(name = "borewellcharge")
	private Double borewellcharge;
	@Column(name = "othercharge")
	private Double othercharge;
	@Column(name = "arrears")
	private Double arrears;
	@Column(name = "interestonarrears")
	private Double interestonarrears;
	@Column(name = "totalamount")
	private Double totalamount;
	@Column(name = "addedat")
	private Date addedat;
	@Column(name = "duedate")
	private Date duedate;
	@Column(name = "billdate")
	private Date billdate;
	@Column(name = "firstdate")
	private Date firstdate;
	@Column(name = "lastdate")
	private Date lastdate;
	@Column(name = "missingdays")
	private Integer missings;
	@Column(name = "extrapaid")
	private Double extrapaid;
	@Column(name = "previousbill")
	private Double previousbill;
	
	
	public BillingReportMaster(){
		
	}
	public BillingReportMaster(Integer id, String monthYear, Integer unitId, Double firstReading, Double lastReading,
			Double consumption, Double watercharge, Double metercharge, Double sanitarycharge, Double borewellcharge,
			Double othercharge, Double arrears, Double interestonarrears, Double totalamount, Date addedat,
			Date duedate, Date billdate, Date firstdate, Date lastdate, Integer missings, Double extrapaid,
			Double previousbill) {
		super();
		this.id = id;
		this.monthYear = monthYear;
		this.unitId = unitId;
		this.firstReading = firstReading;
		this.lastReading = lastReading;
		this.consumption = consumption;
		this.watercharge = watercharge;
		this.metercharge = metercharge;
		this.sanitarycharge = sanitarycharge;
		this.borewellcharge = borewellcharge;
		this.othercharge = othercharge;
		this.arrears = arrears;
		this.interestonarrears = interestonarrears;
		this.totalamount = totalamount;
		this.addedat = addedat;
		this.duedate = duedate;
		this.billdate = billdate;
		this.firstdate = firstdate;
		this.lastdate = lastdate;
		this.missings = missings;
		this.extrapaid = extrapaid;
		this.previousbill = previousbill;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Double getFirstReading() {
		return firstReading;
	}
	public void setFirstReading(Double firstReading) {
		this.firstReading = firstReading;
	}
	public Double getLastReading() {
		return lastReading;
	}
	public void setLastReading(Double lastReading) {
		this.lastReading = lastReading;
	}
	public Double getConsumption() {
		return consumption;
	}
	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}
	public Double getWatercharge() {
		return watercharge;
	}
	public void setWatercharge(Double watercharge) {
		this.watercharge = watercharge;
	}
	public Double getMetercharge() {
		return metercharge;
	}
	public void setMetercharge(Double metercharge) {
		this.metercharge = metercharge;
	}
	public Double getSanitarycharge() {
		return sanitarycharge;
	}
	public void setSanitarycharge(Double sanitarycharge) {
		this.sanitarycharge = sanitarycharge;
	}
	public Double getBorewellcharge() {
		return borewellcharge;
	}
	public void setBorewellcharge(Double borewellcharge) {
		this.borewellcharge = borewellcharge;
	}
	public Double getOthercharge() {
		return othercharge;
	}
	public void setOthercharge(Double othercharge) {
		this.othercharge = othercharge;
	}
	public Double getArrears() {
		return arrears;
	}
	public void setArrears(Double arrears) {
		this.arrears = arrears;
	}
	public Double getInterestonarrears() {
		return interestonarrears;
	}
	public void setInterestonarrears(Double interestonarrears) {
		this.interestonarrears = interestonarrears;
	}
	public Double getTotalamount() {
		return totalamount;
	}
	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}
	public Date getAddedat() {
		return addedat;
	}
	public void setAddedat(Date addedat) {
		this.addedat = addedat;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public Date getBilldate() {
		return billdate;
	}
	public void setBilldate(Date billdate) {
		this.billdate = billdate;
	}
	public Date getFirstdate() {
		return firstdate;
	}
	public void setFirstdate(Date firstdate) {
		this.firstdate = firstdate;
	}
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	public Integer getMissings() {
		return missings;
	}
	public void setMissings(Integer missings) {
		this.missings = missings;
	}
	public Double getExtrapaid() {
		return extrapaid;
	}
	public void setExtrapaid(Double extrapaid) {
		this.extrapaid = extrapaid;
	}
	public Double getPreviousbill() {
		return previousbill;
	}
	public void setPreviousbill(Double previousbill) {
		this.previousbill = previousbill;
	}
	
	

}
