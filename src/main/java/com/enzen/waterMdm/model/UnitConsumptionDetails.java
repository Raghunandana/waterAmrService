package com.enzen.waterMdm.model;

import java.io.Serializable;
import java.sql.Time;
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
@Table(name = "unitsconsumptiondetails")
public class UnitConsumptionDetails implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "unitsconsumptiondetails_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "unitsconsumptiondetails_id_seq", sequenceName = "unitsconsumptiondetails_id_seq",allocationSize=1)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "daterecorded")
	private Date dateRecorded;
	
	@Column(name = "unitid")
	private Integer unitId;
	
	@Column(name = "consumptioninmcube")
	private Double consInMcube;
	
	@Column(name = "dayconsumption")
	private Double dayCons;
	
	@Column(name = "addedat")
	private Date addeDat;
	
	@Column(name = "messagedataid")
	private Integer msgDataId;
	
	@Column(name = "consumption")
	private Double consumption;
	
	public UnitConsumptionDetails() {
		
	}

	public UnitConsumptionDetails(Integer id, Date dateRecorded, Integer unitId, Double consInMcube, Double dayCons, 
			Date addeDat, Integer msgDataId, Double consumption) {
		
		super();
		this.id = id;
		this.unitId = unitId;
		this.dateRecorded = dateRecorded;
		this.consInMcube = consInMcube;
		this.dayCons = dayCons;
		this.addeDat = addeDat;
		this.msgDataId = msgDataId;
		this.consumption = consumption;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateRecorded() {
		return dateRecorded;
	}

	public void setDateRecorded(Date dateRecorded) {
		this.dateRecorded = dateRecorded;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Double getConsInMcube() {
		return consInMcube;
	}

	public void setConsInMcube(Double consInMcube) {
		this.consInMcube = consInMcube;
	}

	public Double getDayCons() {
		return dayCons;
	}

	public void setDayCons(Double dayCons) {
		this.dayCons = dayCons;
	}

	public Date getAddeDat() {
		return addeDat;
	}

	public void setAddeDat(Date addeDat) {
		this.addeDat = addeDat;
	}

	public Integer getMsgDataId() {
		return msgDataId;
	}

	public void setMsgDataId(Integer msgDataId) {
		this.msgDataId = msgDataId;
	}

	public Double getConsumption() {
		return consumption;
	}

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}


}
