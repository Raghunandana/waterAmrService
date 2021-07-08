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
import javax.persistence.Table;

@Entity
@Table(name = "employeeloginmapping")
public class EmployeeLoginMapping implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@Column(name = "loginname", nullable = false)
	private String loginName;
	
	@Column(name = "employeeid", nullable = false)
	private Integer employeeId;
	
	public EmployeeLoginMapping() {
		
	}

	public EmployeeLoginMapping(String loginName, Integer employeeId) {
		
		super();
		this.loginName = loginName;
		this.employeeId = employeeId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	
}
