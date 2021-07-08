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
@Table(name = "paymentdetails")
public class PaymentDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "paymentdetails_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "paymentdetails_id_seq", sequenceName = "paymentdetails_id_seq", allocationSize = 1)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "billno")
	private Integer billNo;
	@Column(name = "modeofpay")
	private String modeOfPay;
	@Column(name = "amount")
	private Double amount;
	@Column(name = "chequeno")
	private String chequeNo;
	@Column(name = "bankname")
	private String bankName;
	@Column(name = "branchname")
	private String branchName;
	@Column(name = "bankaddress")
	private String bankAddress;
	@Column(name = "addedby")
	private String addedBy;
	@Column(name = "addedat")
	private Date addedAt;
	@Column(name = "canceled")
	private boolean canceled;
	@Column(name = "canceledby")
	private String canceledBy;
	@Column(name = "canceledat")
	private Date canceledAt;
	@Column(name = "authenticated")
	private boolean authenticated;
	@Column(name = "authby")
	private String authBy;
	@Column(name = "authat")
	private Date authat;
	
	public PaymentDetails(){
		
	}

	public PaymentDetails(Integer id, Integer billNo, String modeOfPay, Double amount, String chequeNo, String bankName,
			String branchName, String bankAddress, String addedBy, Date addedAt, boolean canceled, String canceledBy,
			Date canceledAt, boolean authenticated, String authBy, Date authat) {
		super();
		this.id = id;
		this.billNo = billNo;
		this.modeOfPay = modeOfPay;
		this.amount = amount;
		this.chequeNo = chequeNo;
		this.bankName = bankName;
		this.branchName = branchName;
		this.bankAddress = bankAddress;
		this.addedBy = addedBy;
		this.addedAt = addedAt;
		this.canceled = canceled;
		this.canceledBy = canceledBy;
		this.canceledAt = canceledAt;
		this.authenticated = authenticated;
		this.authBy = authBy;
		this.authat = authat;
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

	public String getModeOfPay() {
		return modeOfPay;
	}

	public void setModeOfPay(String modeOfPay) {
		this.modeOfPay = modeOfPay;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(Date addedAt) {
		this.addedAt = addedAt;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public String getCanceledBy() {
		return canceledBy;
	}

	public void setCanceledBy(String canceledBy) {
		this.canceledBy = canceledBy;
	}

	public Date getCanceledAt() {
		return canceledAt;
	}

	public void setCanceledAt(Date canceledAt) {
		this.canceledAt = canceledAt;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public String getAuthBy() {
		return authBy;
	}

	public void setAuthBy(String authBy) {
		this.authBy = authBy;
	}

	public Date getAuthat() {
		return authat;
	}

	public void setAuthat(Date authat) {
		this.authat = authat;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}
