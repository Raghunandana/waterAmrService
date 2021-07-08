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
@Table(name="tariff_master")
public class TariffMaster implements Serializable{
	
	
	@Id
	@GeneratedValue(generator = "tariff_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "tariff_id_seq", sequenceName = "tariff_id_seq",allocationSize=1)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "tariffid")
	private Integer tariffId;
	
	@Column(name = "categoryid")
	private String categoryId;
	
	@Column(name = "slabmin")
	private Double slabMin;
	
	@Column(name = "slabmax")
	private Double slabMax;
	
	@Column(name = "tariff")
	private Double tariff;
	
	@Column(name = "sanitary")
	private Double sanitay;
	
	@Column(name = "sanitarytype")
	private String sanitaryType;
	
	@Column(name = "borewell")
	private Double boreWell;
	
	@Column(name = "metercost")
	private Double meterCost;
	
	@Column(name = "year")
	private Integer years;
	
	@Column(name = "divisionid")
	private Integer divisionId;
	
	@Column(name = "addedat")
	private Date addedat;
	
	public TariffMaster() {
		
	}


	public TariffMaster(Integer id, String categoryId, Double slabMin, Double slabMax, Double tariff, Double sanitay,
			String sanitaryType, Double boreWell, Double meterCost, Integer years, Integer divisionId, Date addedat, Integer tariffId) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.slabMin = slabMin;
		this.slabMax = slabMax;
		this.tariff = tariff;
		this.sanitay = sanitay;
		this.sanitaryType = sanitaryType;
		this.boreWell = boreWell;
		this.meterCost = meterCost;
		this.years = years;
		this.divisionId = divisionId;
		this.addedat = addedat;
		this.tariffId = tariffId;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}


	public Double getSlabMin() {
		return slabMin;
	}


	public void setSlabMin(Double slabMin) {
		this.slabMin = slabMin;
	}


	public Double getSlabMax() {
		return slabMax;
	}


	public void setSlabMax(Double slabMax) {
		this.slabMax = slabMax;
	}


	public Double getTariff() {
		return tariff;
	}


	public void setTariff(Double tariff) {
		this.tariff = tariff;
	}


	public Double getSanitay() {
		return sanitay;
	}


	public void setSanitay(Double sanitay) {
		this.sanitay = sanitay;
	}


	public String getSanitaryType() {
		return sanitaryType;
	}


	public void setSanitaryType(String sanitaryType) {
		this.sanitaryType = sanitaryType;
	}


	public Double getBoreWell() {
		return boreWell;
	}


	public void setBoreWell(Double boreWell) {
		this.boreWell = boreWell;
	}


	public Double getMeterCost() {
		return meterCost;
	}


	public void setMeterCost(Double meterCost) {
		this.meterCost = meterCost;
	}


	public Integer getYears() {
		return years;
	}


	public void setYears(Integer years) {
		this.years = years;
	}


	public Integer getDivisionId() {
		return divisionId;
	}


	public void setDivisionId(Integer divisionId) {
		this.divisionId = divisionId;
	}


	public Date getAddedat() {
		return addedat;
	}


	public void setAddedat(Date addedat) {
		this.addedat = addedat;
	}


	public Integer getTariffId() {
		return tariffId;
	}


	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}


	
}
	