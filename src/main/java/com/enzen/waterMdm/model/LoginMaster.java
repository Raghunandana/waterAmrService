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
@Table(name = "loginmaster")
public class LoginMaster implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@Column(name = "loginname", nullable = false)
	private String loginName;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "roles", nullable = false)
	private Integer roles;
	
	@Column(name = "active", nullable = false)
	private boolean active = true;
	
	@Column(name = "loginfailedcount", nullable = false)
	private Integer loginFailedCount;
	
	@Column(name = "firstlogininnew", nullable = false)
	private boolean firstLogininNew = true;
	
	@Column(name = "defaultrole")
	private long defaultRole;
	
	public LoginMaster() {
		
	}

	public LoginMaster(String loginName, String password, Integer roles, boolean active, 
			Integer loginFailedCount, boolean firstLogininNew, long defaultRole) {
		
		super();
		this.loginName = loginName;
		this.password = password;
		this.roles = roles;
		this.active = active;
		this.loginFailedCount = loginFailedCount;
		this.firstLogininNew = firstLogininNew;
		this.defaultRole = defaultRole;
		
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getRoles() {
		return roles;
	}

	public void setRoles(Integer roles) {
		this.roles = roles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getLoginFailedCount() {
		return loginFailedCount;
	}

	public void setLoginFailedCount(Integer loginFailedCount) {
		this.loginFailedCount = loginFailedCount;
	}

	public boolean isFirstLogininNew() {
		return firstLogininNew;
	}

	public void setFirstLogininNew(boolean firstLogininNew) {
		this.firstLogininNew = firstLogininNew;
	}

	public long getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(long defaultRole) {
		this.defaultRole = defaultRole;
	}
	
	
}
