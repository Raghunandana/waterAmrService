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
@Table(name = "unit_summary")
public class UnitSummary implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "unit_summary_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "unit_summary_id_seq", sequenceName = "unit_summary_id_seq",allocationSize=1)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "unitid")
	private String unitId;
	
	@Column(name = "installon")
	private Date installOn;
	
	@Column(name = "pipesize")
	private String pipesize;
	
	@Column(name = "totalflow")
	private Double totalFlow;
	
	@Column(name = "subdivisionid")
	private Integer subdivId;
	
	@Column(name = "divisionid")
	private Integer divId;
	
	@Column(name = "servicestnid")
	private Integer serStnId;
	
	@Column(name = "locationid")
	private Integer locId;
	
	@Column(name = "divisionhead")
	private String divHead;
	
	@Column(name = "subdivisionhead")
	private String subDivHead;
	
	@Column(name = "messagetime")
	private Date msgTime;
	
	@Column(name = "metercalibfactor")
	private Double mtrCalibFactor;
	
	@Column(name = "metercalibrationdate")
	private Date  mtrCalibDate;
	
	@Column(name = "batterylimitvalue")
	private String bttryLmtVal;
	
	@Column(name = "initialmeterreading")
	private Double intMtrReading;
	
	@Column(name = "considerinitmtrfornextsms")
	private boolean consInitMtrForNextSms;
	
	@Column(name = "ltrperpulse")
	private Double ltrPerPulse;
	
	@Column(name = "fieldpicture")
	private String fldPicture;
	
	@Column(name = "tariffperltr")
	private Double trfPerLtr;
	
	@Column(name = "mobilenumberofalarmmessagerecipient1")
	private String mobRcpt1;
	
	@Column(name = "mobilenumberofalarmmessagerecipient2")
	private String mobRcpt2;
	
	@Column(name = "mobilenumberofalarmmessagerecipient3")
	private String mobRcpt3;
	
	@Column(name = "mailidofalarmmessagerecipient1")
	private String mailiRcpt1;
	
	@Column(name = "mailidofalarmmessagerecipient2")
	private String mailiRcpt2;
	
	@Column(name = "mailidofalarmmessagerecipient3")
	private String mailiRcpt3;
	
	@Column(name = "latitude")
	private Double lat;
	
	@Column(name = "longitude")
	private Double lon;
	
	@Column(name = "consumerid")
	private Integer consId;
	
	@Column(name = "consumeraddress")
	private String consAddr;
	
	@Column(name = "consumercontactnumber")
	private String consContNum;
	
	@Column(name = "meterslno")
	private String mtrSlNo;
	
	@Column(name = "oemname")
	private String oemName;
	
	@Column(name = "metertype")
	private String meterType;
	
	@Column(name = "meter_unit")
	private String meterUnit;
	
	@Column(name = "meterflowtype")
	private String meterflowType;
	
	@Column(name = "meterbillingtype")
	private String mtrBillType;
	
	@Column(name = "gsmsignalstrength")
	private String gsmSglStrgh;
	
	@Column(name = "metermodelnumber")
	private String mtrMdlNum;
	
	@Column(name = "modeofcommuniction")
	private String modOfComm;
	
	@Column(name = "gprstype")
	private String gprsType;
	
	@Column(name = "gprstypeurl")
	private String gprsTypeUrl;
	
	@Column(name = "communicationperiod")
	private String commPeriod;
	
	@Column(name = "rechargedate")
	private Date rechaDate;
	
	@Column(name = "serviceprovider")
	private String srvProvider;
	
	@Column(name = "daysofwaterflow")
	private String daysOfWaterFlow;
	
	@Column(name = "fromwaterflow")
	private String fromWaterFlow;
	
	@Column(name = "towaterflow")
	private String toWaterFlow;
	
	@Column(name = "averagepressure")
	private Double avgPressure;
	
	@Column(name = "averageflowrate")
	private Double avgFlowRate;
	
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "billgroupid")
	private Integer billGroupId;
	
	@Column(name = "firstreading")
	private Double firstReading;
	
	@Column(name = "mapvalue")
	private Double mapVal;
	
	@Column(name = "firstpulse")
	private Double firstPulse;
	
	@Column(name = "mappulse")
	private Double mapPulse;
	
	@Column(name = "meter_type")
	private Integer meterHType;
	
	@Column(name = "billing_day")
	private Integer billingDay;
	
	@Column(name = "due_day")
	private Integer dueDay;
	
	@Column(name = "rrno")
	private String rrno;
	
	public UnitSummary() {
		
	}

	public UnitSummary(Integer id, String unitid, Date installon, String pipesize, Double totalflow, Integer subdivId, Integer divId, Integer serStnId, Integer locId, 
			String divHead, String subDivHead, Date msgTime, Double mtrCalibFactor, Date mtrCalibDate, String bttryLmtVal, Double intMtrReading, boolean consInitMtrForNextSms,
			Double ltrPerPulse, String fldPicture, Double trfPerLtr, String mobRcpt1, String mobRcpt2, String mobRcpt3, String mailiRcpt1, String mailiRcpt2, String mailiRcpt3,
			Double lat,  Double lon, Integer consId, String consAddr, String consContNum, String mtrSlNo, String oemName, String meterType, String meterflowType, 
			String mtrBillType, String gsmSglStrgh, String mtrMdlNum, String modOfComm, String gprsType, String gprsTypeUrl, String commPeriod, Date rechaDate, 
			String srvProvider, String daysOfWaterFlow, String fromWaterFlow, String toWaterFlow, Double avgPressure, Double avgFlowRate, boolean active,
			Integer billGroupId, Double firstReading, Double mapVal, Double firstPulse, Double mapPulse, String meterUnit, Integer meterHType, Integer billingDay, 
			Integer dueDay, String rrno) {
		
		super();
		this.id = id;
		this.unitId = unitid;
		this.installOn = installon;
		this.pipesize = pipesize;
		this.totalFlow = totalflow;
		this.subdivId = subdivId;
		this.divId = divId;
		this.serStnId = serStnId;
		this.locId = locId;
		this.divHead = divHead;
		this.subDivHead = subDivHead;
		this.msgTime = msgTime;
		this.mtrCalibFactor = mtrCalibFactor;
		this.mtrCalibDate = mtrCalibDate;
		this.bttryLmtVal = bttryLmtVal;
		this.intMtrReading = intMtrReading;
		this.consInitMtrForNextSms = consInitMtrForNextSms;
		this.ltrPerPulse = ltrPerPulse;
		this.fldPicture = fldPicture;
		this.trfPerLtr = trfPerLtr;
		this.mobRcpt1 = mobRcpt1;
		this.mobRcpt2 = mobRcpt2;
		this.mobRcpt3 = mobRcpt3;
		this.mailiRcpt1 = mailiRcpt1;
		this.mailiRcpt2 = mailiRcpt2;
		this.mailiRcpt3 = mailiRcpt3;
		this.lat = lat;
		this.lon = lon;
		this.consId = consId;
		this.consAddr = consAddr;
		this.consContNum = consContNum;
		this.mtrSlNo = mtrSlNo;
		this.oemName = oemName;
		this.meterType = meterType;
		this.meterflowType = meterflowType;
		this.mtrBillType = mtrBillType;
		this.gsmSglStrgh = gsmSglStrgh;
		this.mtrMdlNum = mtrMdlNum;
		this.modOfComm = modOfComm;
		this.gprsType = gprsType;
		this.gprsTypeUrl = gprsTypeUrl;
		this.commPeriod = commPeriod;
		this.rechaDate = rechaDate;
		this.srvProvider = srvProvider;
		this.daysOfWaterFlow = daysOfWaterFlow;
		this.fromWaterFlow = fromWaterFlow;
		this.toWaterFlow = toWaterFlow;
		this.avgPressure = avgPressure;
		this.avgFlowRate = avgFlowRate;
		this.active = active;
		this.billGroupId = billGroupId;
		this.firstReading = firstReading;
		this.mapVal = mapVal;
		this.firstPulse = firstPulse;
		this.mapPulse = mapPulse;
		this.meterUnit = meterUnit;
		this.meterHType = meterHType;
		this.billingDay = billingDay;
		this.dueDay = dueDay;
		this.rrno = rrno;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public Date getInstallOn() {
		return installOn;
	}

	public void setInstallOn(Date installOn) {
		this.installOn = installOn;
	}

	public String getPipesize() {
		return pipesize;
	}

	public void setPipesize(String pipesize) {
		this.pipesize = pipesize;
	}

	public Double getTotalFlow() {
		return totalFlow;
	}

	public void setTotalFlow(Double totalFlow) {
		this.totalFlow = totalFlow;
	}

	public Integer getSubdivId() {
		return subdivId;
	}

	public void setSubdivId(Integer subdivId) {
		this.subdivId = subdivId;
	}

	public Integer getDivId() {
		return divId;
	}

	public void setDivId(Integer divId) {
		this.divId = divId;
	}

	public Integer getSerStnId() {
		return serStnId;
	}

	public void setSerStnId(Integer serStnId) {
		this.serStnId = serStnId;
	}

	public Integer getLocId() {
		return locId;
	}

	public void setLocId(Integer locId) {
		this.locId = locId;
	}

	public String getDivHead() {
		return divHead;
	}

	public void setDivHead(String divHead) {
		this.divHead = divHead;
	}

	public String getSubDivHead() {
		return subDivHead;
	}

	public void setSubDivHead(String subDivHead) {
		this.subDivHead = subDivHead;
	}

	public Date getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Date msgTime) {
		this.msgTime = msgTime;
	}

	public Double getMtrCalibFactor() {
		return mtrCalibFactor;
	}

	public void setMtrCalibFactor(Double mtrCalibFactor) {
		this.mtrCalibFactor = mtrCalibFactor;
	}

	public Date getMtrCalibDate() {
		return mtrCalibDate;
	}

	public void setMtrCalibDate(Date mtrCalibDate) {
		this.mtrCalibDate = mtrCalibDate;
	}

	public String getBttryLmtVal() {
		return bttryLmtVal;
	}

	public void setBttryLmtVal(String bttryLmtVal) {
		this.bttryLmtVal = bttryLmtVal;
	}

	public Double getIntMtrReading() {
		return intMtrReading;
	}

	public void setIntMtrReading(Double intMtrReading) {
		this.intMtrReading = intMtrReading;
	}

	public boolean isConsInitMtrForNextSms() {
		return consInitMtrForNextSms;
	}

	public void setConsInitMtrForNextSms(boolean consInitMtrForNextSms) {
		this.consInitMtrForNextSms = consInitMtrForNextSms;
	}

	public Double getLtrPerPulse() {
		return ltrPerPulse;
	}

	public void setLtrPerPulse(Double ltrPerPulse) {
		this.ltrPerPulse = ltrPerPulse;
	}

	public String getFldPicture() {
		return fldPicture;
	}

	public void setFldPicture(String fldPicture) {
		this.fldPicture = fldPicture;
	}

	public Double getTrfPerLtr() {
		return trfPerLtr;
	}

	public void setTrfPerLtr(Double trfPerLtr) {
		this.trfPerLtr = trfPerLtr;
	}

	public String getMobRcpt1() {
		return mobRcpt1;
	}

	public void setMobRcpt1(String mobRcpt1) {
		this.mobRcpt1 = mobRcpt1;
	}

	public String getMobRcpt2() {
		return mobRcpt2;
	}

	public void setMobRcpt2(String mobRcpt2) {
		this.mobRcpt2 = mobRcpt2;
	}

	public String getMobRcpt3() {
		return mobRcpt3;
	}

	public void setMobRcpt3(String mobRcpt3) {
		this.mobRcpt3 = mobRcpt3;
	}

	public String getMailiRcpt1() {
		return mailiRcpt1;
	}

	public void setMailiRcpt1(String mailiRcpt1) {
		this.mailiRcpt1 = mailiRcpt1;
	}

	public String getMailiRcpt2() {
		return mailiRcpt2;
	}

	public void setMailiRcpt2(String mailiRcpt2) {
		this.mailiRcpt2 = mailiRcpt2;
	}

	public String getMailiRcpt3() {
		return mailiRcpt3;
	}

	public void setMailiRcpt3(String mailiRcpt3) {
		this.mailiRcpt3 = mailiRcpt3;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Integer getConsId() {
		return consId;
	}

	public void setConsId(Integer consId) {
		this.consId = consId;
	}

	public String getConsAddr() {
		return consAddr;
	}

	public void setConsAddr(String consAddr) {
		this.consAddr = consAddr;
	}

	public String getConsContNum() {
		return consContNum;
	}

	public void setConsContNum(String consContNum) {
		this.consContNum = consContNum;
	}

	public String getMtrSlNo() {
		return mtrSlNo;
	}

	public void setMtrSlNo(String mtrSlNo) {
		this.mtrSlNo = mtrSlNo;
	}

	public String getOemName() {
		return oemName;
	}

	public void setOemName(String oemName) {
		this.oemName = oemName;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getMeterflowType() {
		return meterflowType;
	}

	public void setMeterflowType(String meterflowType) {
		this.meterflowType = meterflowType;
	}

	public String getMtrBillType() {
		return mtrBillType;
	}

	public void setMtrBillType(String mtrBillType) {
		this.mtrBillType = mtrBillType;
	}

	public String getGsmSglStrgh() {
		return gsmSglStrgh;
	}

	public void setGsmSglStrgh(String gsmSglStrgh) {
		this.gsmSglStrgh = gsmSglStrgh;
	}

	public String getMtrMdlNum() {
		return mtrMdlNum;
	}

	public void setMtrMdlNum(String mtrMdlNum) {
		this.mtrMdlNum = mtrMdlNum;
	}

	public String getModOfComm() {
		return modOfComm;
	}

	public void setModOfComm(String modOfComm) {
		this.modOfComm = modOfComm;
	}

	public String getGprsType() {
		return gprsType;
	}

	public void setGprsType(String gprsType) {
		this.gprsType = gprsType;
	}

	public String getGprsTypeUrl() {
		return gprsTypeUrl;
	}

	public void setGprsTypeUrl(String gprsTypeUrl) {
		this.gprsTypeUrl = gprsTypeUrl;
	}

	public String getCommPeriod() {
		return commPeriod;
	}

	public void setCommPeriod(String commPeriod) {
		this.commPeriod = commPeriod;
	}

	public Date getRechaDate() {
		return rechaDate;
	}

	public void setRechaDate(Date rechaDate) {
		this.rechaDate = rechaDate;
	}

	public String getSrvProvider() {
		return srvProvider;
	}

	public void setSrvProvider(String srvProvider) {
		this.srvProvider = srvProvider;
	}

	public String getDaysOfWaterFlow() {
		return daysOfWaterFlow;
	}

	public void setDaysOfWaterFlow(String daysOfWaterFlow) {
		this.daysOfWaterFlow = daysOfWaterFlow;
	}

	public String getFromWaterFlow() {
		return fromWaterFlow;
	}

	public void setFromWaterFlow(String fromWaterFlow) {
		this.fromWaterFlow = fromWaterFlow;
	}

	public String getToWaterFlow() {
		return toWaterFlow;
	}

	public void setToWaterFlow(String toWaterFlow) {
		this.toWaterFlow = toWaterFlow;
	}

	public Double getAvgPressure() {
		return avgPressure;
	}

	public void setAvgPressure(Double avgPressure) {
		this.avgPressure = avgPressure;
	}

	public Double getAvgFlowRate() {
		return avgFlowRate;
	}

	public void setAvgFlowRate(Double avgFlowRate) {
		this.avgFlowRate = avgFlowRate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getBillGroupId() {
		return billGroupId;
	}

	public void setBillGroupId(Integer billGroupId) {
		this.billGroupId = billGroupId;
	}

	public Double getFirstReading() {
		return firstReading;
	}

	public void setFirstReading(Double firstReading) {
		this.firstReading = firstReading;
	}

	public Double getMapVal() {
		return mapVal;
	}

	public void setMapVal(Double mapVal) {
		this.mapVal = mapVal;
	}

	public Double getFirstPulse() {
		return firstPulse;
	}

	public void setFirstPulse(Double firstPulse) {
		this.firstPulse = firstPulse;
	}

	public Double getMapPulse() {
		return mapPulse;
	}

	public void setMapPulse(Double mapPulse) {
		this.mapPulse = mapPulse;
	}

	public String getMeterUnit() {
		return meterUnit;
	}

	public void setMeterUnit(String meterUnit) {
		this.meterUnit = meterUnit;
	}

	public Integer getMeterHType() {
		return meterHType;
	}

	public void setMeterHType(Integer meterHType) {
		this.meterHType = meterHType;
	}

	public Integer getBillingDay() {
		return billingDay;
	}

	public void setBillingDay(Integer billingDay) {
		this.billingDay = billingDay;
	}

	public Integer getDueDay() {
		return dueDay;
	}

	public void setDueDay(Integer dueDay) {
		this.dueDay = dueDay;
	}

	public String getRrno() {
		return rrno;
	}

	public void setRrno(String rrno) {
		this.rrno = rrno;
	}

	

}
