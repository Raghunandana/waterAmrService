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
@Table(name = "meter_master")
public class MeterMaster implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "name", nullable = true)
	private String name;
	
	public MeterMaster() {
		
	}

	public MeterMaster(Integer id, String name) {
		
		super();
		this.id = id;
		this.name = name;
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


}
