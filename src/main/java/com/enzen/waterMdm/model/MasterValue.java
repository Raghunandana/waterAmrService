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
@Table(name = "mastervalue")
public class MasterValue implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "mastervalueid_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "mastervalueid_seq", sequenceName = "mastervalueid_seq",allocationSize=1)
	@Column(name = "mastervalueid", nullable = false)
	private Integer id;
	
	@Column(name = "code", nullable = true)
	private String code;
	
	@Column(name = "description", nullable = false)
	private String desc;
	
	@Column(name = "mastersid", nullable = false)
	private Integer mastersId;
	
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
	
	@Column(name = "isactive", nullable = true)
	private boolean isActive;
	
	@Column(name = "orgid", nullable = true)
	private Integer orgId;
	
	public MasterValue() {
		
	}

	public MasterValue(Integer id, String code, String desc, Integer mastersId, 
			Integer parentId, String crBy, Date crTime, String modBy, Date modTime, 
			boolean isDeleted, boolean isActive, Integer orgId) {
		
		super();
		this.id = id;
		this.code = code;
		this.desc = desc;
		this.mastersId = mastersId;
		this.parentId = parentId;
		this.crBy = crBy;
		this.crTime = crTime;
		this.modBy = modBy;
		this.modTime = modTime;
		this.isDeleted = isDeleted;
		this.isActive = isActive;
		this.orgId = orgId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getMastersId() {
		return mastersId;
	}

	public void setMastersId(Integer mastersId) {
		this.mastersId = mastersId;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}


}
