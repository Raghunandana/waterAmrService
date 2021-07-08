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
@Table(name = "message_rejected_history")
public class MessageRejectedHistory implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "message_rejected_history_historyid_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "message_rejected_history_historyid_seq", sequenceName = "message_rejected_history_historyid_seq",allocationSize=1)
	@Column(name = "historyid", nullable = false)
	private Integer historyId;
	
	@Column(name = "id", nullable = true)
	private Integer id;
	
	@Column(name = "message", nullable = true)
	private String message;
	
	@Column(name = "sentby", nullable = false)
	private String sentBy;
	
	@Column(name = "sentdate", nullable = false)
	private Date sentDate;
	
	@Column(name = "time")
	private Date time;
	
	@Column(name = "smsheader")
	private String smsHeader;
	
	@Column(name = "rejectreason")
	private String rejectReason;
	
	@Column(name = "unitid")
	private String unitId;
	
	
	public MessageRejectedHistory() {
		
	}

	public MessageRejectedHistory(Integer historyId, Integer id, String message, String sentBy, Date sentDate, Date time, String smsHeader,
			String rejectReason, String unitId) {
		
		super();
		this.historyId = historyId;
		this.id = id;
		this.unitId = unitId;
		this.message = message;
		this.sentBy = sentBy;
		this.sentDate = sentDate;
		this.smsHeader = smsHeader;
		this.rejectReason = rejectReason;
		this.time = time;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSentBy() {
		return sentBy;
	}

	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSmsHeader() {
		return smsHeader;
	}

	public void setSmsHeader(String smsHeader) {
		this.smsHeader = smsHeader;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}


}
