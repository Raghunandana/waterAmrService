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
@Table(name = "paymentprocess")
public class PaymentProcess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "paymentprocess_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "paymentprocess_id_seq", sequenceName = "paymentprocess_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "billno")
	private Integer billNo;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "paid")
	private Double paid;
	@Column(name = "balance")
	private Double balance;
	@Column(name = "overpay")
	private boolean overpay;
	@Column(name = "processedby")
	private String processedBy;
	@Column(name = "processedat")
	private Date processedAt;
	
	public PaymentProcess(){
		
	}

	public PaymentProcess(Integer id, Integer billNo, Double amount, Double paid, Double balance, boolean overpay,
			String processedBy, Date processedAt) {
		super();
		this.id = id;
		this.billNo = billNo;
		this.amount = amount;
		this.paid = paid;
		this.balance = balance;
		this.overpay = overpay;
		this.processedBy = processedBy;
		this.processedAt = processedAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBillNo() {
		return billNo;
	}

	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPaid() {
		return paid;
	}

	public void setPaid(Double paid) {
		this.paid = paid;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public boolean isOverpay() {
		return overpay;
	}

	public void setOverpay(boolean overpay) {
		this.overpay = overpay;
	}

	public String getProcessedBy() {
		return processedBy;
	}

	public void setProcessedBy(String processedBy) {
		this.processedBy = processedBy;
	}

	public Date getProcessedAt() {
		return processedAt;
	}

	public void setProcessedAt(Date processedAt) {
		this.processedAt = processedAt;
	}

	

}
