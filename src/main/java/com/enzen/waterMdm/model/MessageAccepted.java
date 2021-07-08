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
@Table(name = "message_accepted")
public class MessageAccepted implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "message", nullable = true)
	private String message;
	
	@Column(name = "sentby", nullable = false)
	private String sentBy;
	
	@Column(name = "sentdate", nullable = false)
	private Date sentDate;
	
	@Column(name = "smsheader")
	private String smsHeader;
	
	@Column(name = "senttime")
	private Date sentTime;
	
	public MessageAccepted() {
		
	}

	public MessageAccepted(Integer id, String message, String sentBy, Date sentDate, String smsHeader, Date sentTime) {
		
		super();
		this.id = id;
		this.message = message;
		this.sentBy = sentBy;
		this.sentDate = sentDate;
		this.smsHeader = smsHeader;
		this.sentTime = sentTime;
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

	public String getSmsHeader() {
		return smsHeader;
	}

	public void setSmsHeader(String smsHeader) {
		this.smsHeader = smsHeader;
	}

	public Date getSentTime() {
		return sentTime;
	}

	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}



}
