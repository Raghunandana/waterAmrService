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
@Table(name = "roles")
public class Roles implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "roles_roleid_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "roles_roleid_seq", sequenceName = "roles_roleid_seq",allocationSize=1)
	@Column(name = "roleid", nullable = false)
	private Integer roleId;
	
	@Column(name = "rolename", nullable = true)
	private String roleName;
	
	@Column(name = "description", nullable = false)
	private String desc;
	
	@Column(name = "active", nullable = false)
	private boolean active;
	
	@Column(name = "sysrole", nullable = true)
	private boolean sysRole;
	
	@Column(name = "orgid", nullable = true)
	private Integer orgId;
	
	public Roles() {
		
	}

	public Roles(Integer roleId, String roleName, String desc, boolean active, boolean sysRole, Integer orgId) {
		
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.desc = desc;
		this.active = active;
		this.sysRole = sysRole;
		this.orgId = orgId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isSysRole() {
		return sysRole;
	}

	public void setSysRole(boolean sysRole) {
		this.sysRole = sysRole;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


}
