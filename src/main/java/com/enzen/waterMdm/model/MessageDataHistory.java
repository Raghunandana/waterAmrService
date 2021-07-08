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
@Table(name = "message_data_history")
public class MessageDataHistory implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "message_data_history_historyid_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "message_data_history_historyid_seq", sequenceName = "message_data_history_historyid_seq",allocationSize=1)
	@Column(name = "historyid", nullable = false)
	private Integer historyId;
	
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "unitid", nullable = true)
	private Integer unitId;
	
	@Column(name = "hourlydataoflast24hrs", nullable = false)
	private String last24Hrs;
	
	@Column(name = "cumconsumpofprevday", nullable = false)
	private Integer prevDay;
	
	@Column(name = "cumconsumpofprevtoprevday")
	private Integer prevToPrevDay;
	
	@Column(name = "tamperstatus")
	private Integer tampStat;
	
	@Column(name = "batteryvoltage")
	private Double battVolt;
	
	@Column(name = "temperature")
	private Double tempr;
	
	@Column(name = "last24hrsreversepulsecount")
	private Integer pulseCount;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "time")
	private Date time;
	
	public MessageDataHistory() {
		
	}

	public MessageDataHistory(Integer historyId, Integer id, Integer unitId, String last24Hrs, Integer prevDay, Integer prevToPrevDay, Integer tampStat,
			Double battVolt, Double tempr, Integer pulseCount, Date date, Date time) {
		
		super();
		this.historyId = historyId;
		this.id = id;
		this.unitId = unitId;
		this.last24Hrs = last24Hrs;
		this.prevDay = prevDay;
		this.prevToPrevDay = prevToPrevDay;
		this.tampStat = tampStat;
		this.battVolt = battVolt;
		this.tempr = tempr;
		this.pulseCount = pulseCount;
		this.date = date;
		this.time = time;
		
	}

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getLast24Hrs() {
		return last24Hrs;
	}

	public void setLast24Hrs(String last24Hrs) {
		this.last24Hrs = last24Hrs;
	}

	public Integer getPrevDay() {
		return prevDay;
	}

	public void setPrevDay(Integer prevDay) {
		this.prevDay = prevDay;
	}

	public Integer getPrevToPrevDay() {
		return prevToPrevDay;
	}

	public void setPrevToPrevDay(Integer prevToPrevDay) {
		this.prevToPrevDay = prevToPrevDay;
	}

	public Integer getTampStat() {
		return tampStat;
	}

	public void setTampStat(Integer tampStat) {
		this.tampStat = tampStat;
	}

	public Double getBattVolt() {
		return battVolt;
	}

	public void setBattVolt(Double battVolt) {
		this.battVolt = battVolt;
	}

	public Double getTempr() {
		return tempr;
	}

	public void setTempr(Double tempr) {
		this.tempr = tempr;
	}

	public Integer getPulseCount() {
		return pulseCount;
	}

	public void setPulseCount(Integer pulseCount) {
		this.pulseCount = pulseCount;
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



}
