package com.enzen.waterMdm.model;

import java.io.Serializable;
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
@Table(name = "consumer")
public class Consumer implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "consumer_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "consumer_id_seq", sequenceName = "consumer_id_seq",allocationSize=1)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = true)
	private String name;
	
	@Column(name = "address", nullable = false)
	private String address;
	
	@Column(name = "unitid", nullable = false)
	private String unitid;
	
	@Column(name = "mobileno", nullable = true)
	private Integer mobileno;
	
	@Column(name = "cr_date", nullable = true)
	private Date crDate;
	
	public Consumer() {
		
	}

	public Consumer(Integer id, String name, String address, String unitid, Integer mobileno, Date crDate) {
		
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.unitid = unitid;
		this.mobileno = mobileno;
		this.crDate = crDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUnitid() {
		return unitid;
	}

	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}

	public Integer getMobileno() {
		return mobileno;
	}

	public void setMobileno(Integer mobileno) {
		this.mobileno = mobileno;
	}

	public Date getCrDate() {
		return crDate;
	}

	public void setCrDate(Date crDate) {
		this.crDate = crDate;
	}

}
