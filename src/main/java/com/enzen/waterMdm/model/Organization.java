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
@Table(name = "organization")
public class Organization implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "organizationid_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "organizationid_seq", sequenceName = "organizationid_seq",allocationSize=1)
	@Column(name = "organizationid", nullable = false)
	private Integer orgId;
	
	@Column(name = "code", nullable = true)
	private String code;
	
	@Column(name = "organizationname", nullable = false)
	private String orgName;
	
	@Column(name = "organizationtypeid", nullable = false)
	private Integer orgTypeId;
	
	@Column(name = "parentid", nullable = true)
	private Integer parentId;
	
	@Column(name = "createdby", nullable = true)
	private String crBy;
	
	@Column(name = "creationtime")
	private Date crTime;
	
	@Column(name = "modifiedby", nullable = true)
	private String modBy;
	
	@Column(name = "modifiedtime", nullable = true)
	private Date modTime;
	
	@Column(name = "isdeleted", nullable = true)
	private boolean isDeleted;
	
	public Organization() {
		
	}

	public Organization(Integer orgId, String code, String orgName, Integer orgTypeId, 
			Integer parentId, String crBy, Date crTime, String modBy, Date modTime, 
			boolean isDeleted) {
		
		super();
		this.orgId = orgId;
		this.code = code;
		this.orgName = orgName;
		this.orgTypeId = orgTypeId;
		this.parentId = parentId;
		this.crBy = crBy;
		this.crTime = crTime;
		this.modBy = modBy;
		this.modTime = modTime;
		this.isDeleted = isDeleted;
		
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Integer getOrgTypeId() {
		return orgTypeId;
	}

	public void setOrgTypeId(Integer orgTypeId) {
		this.orgTypeId = orgTypeId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getCrBy() {
		return crBy;
	}

	public void setCrBy(String crBy) {
		this.crBy = crBy;
	}

	public Date getCrTime() {
		return crTime;
	}

	public void setCrTime(Date crTime) {
		this.crTime = crTime;
	}

	public String getModBy() {
		return modBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	public Date getModTime() {
		return modTime;
	}

	public void setModTime(Date modTime) {
		this.modTime = modTime;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
