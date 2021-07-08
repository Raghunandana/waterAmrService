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
@Table(name = "employee")
public class Employee implements Serializable{

	private static final long serialVersionUID = -723583058586873479L;
	
	@Id
	@GeneratedValue(generator = "employee_employeeid_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "employee_employeeid_seq", sequenceName = "employee_employeeid_seq",allocationSize=1)
	@Column(name = "employeeid", nullable = false)
	private Integer empId;
	
	@Column(name = "firstname", nullable = true)
	private String firstName;
	
	@Column(name = "midname", nullable = false)
	private String midName;
	
	@Column(name = "lastname", nullable = false)
	private String lastName;
	
	@Column(name = "logintype", nullable = true)
	private Integer loginType;
	
	@Column(name = "photograph", nullable = true)
	private String photograph;
	
	@Column(name = "dateofbirth")
	private Date dob;
	
	@Column(name = "maritalstatus")
	private Integer maritalStatus;
	
	@Column(name = "sex")
	private Integer sex;
	
	@Column(name = "isapproved")
	private Integer isApproved;
	
	@Column(name = "datecreated")
	private Date crDt;
	
	@Column(name = "usercreated")
	private Integer userCr;
	
	@Column(name = "datelastmodified")
	private Date modDt;
	
	@Column(name = "userlastmodified")
	private Integer userMod;
	
	@Column(name = "rrnumber")
	private String rrNum;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "mobilenumber")
	private String mobNum;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "oem")
	private String oem;
	
	@Column(name = "heads")
	private String heads;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "divisionid")
	private Integer divId;
	
	@Column(name = "subdivisionid")
	private Integer subDivId;
	
	@Column(name = "landingpage")
	private String landingPage;
	
	@Column(name = "empaddress")
	private String empAdd;
	
	@Column(name = "empage")
	private Integer empAge;
	
	@Column(name = "salary")
	private Integer salary;
	
	@Column(name = "orgid")
	private Integer orgId;
	
	public Employee() {
		
	}

	public Employee(Integer empId, String firstName, String midName, String lastName, Integer loginType, String photograph, Date dob, Integer maritalStatus,
			Integer sex, Integer isApproved, Date crDt, Integer userCr, Date modDt, Integer userMod, String rrNum, String address, String mobNum, 
			String message, String email, String oem, String heads, String designation, Integer divId, Integer subDivId, String landingPage, String empAdd, 
			Integer empAge, Integer salary, Integer orgId) {
		
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.midName = midName;
		this.lastName = lastName;
		this.loginType = loginType;
		this.photograph = photograph;
		this.dob = dob;
		this.maritalStatus = maritalStatus;
		this.sex = sex;
		this.isApproved = isApproved;
		this.crDt = crDt;
		this.userCr = userCr;
		this.modDt = modDt;
		this.userMod = userMod;
		this.rrNum = rrNum;
		this.address = address;
		this.mobNum = mobNum;
		this.message = message;
		this.email = email;
		this.oem = oem;
		this.heads = heads;
		this.designation = designation;
		this.divId = divId;
		this.subDivId = subDivId;
		this.landingPage = landingPage;
		this.empAdd = empAdd;
		this.empAge = empAge;
		this.salary = salary;
		this.orgId = orgId;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getPhotograph() {
		return photograph;
	}

	public void setPhotograph(String photograph) {
		this.photograph = photograph;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Integer isApproved) {
		this.isApproved = isApproved;
	}

	public Date getCrDt() {
		return crDt;
	}

	public void setCrDt(Date crDt) {
		this.crDt = crDt;
	}

	public Integer getUserCr() {
		return userCr;
	}

	public void setUserCr(Integer userCr) {
		this.userCr = userCr;
	}

	public Date getModDt() {
		return modDt;
	}

	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}

	public Integer getUserMod() {
		return userMod;
	}

	public void setUserMod(Integer userMod) {
		this.userMod = userMod;
	}

	public String getRrNum() {
		return rrNum;
	}

	public void setRrNum(String rrNum) {
		this.rrNum = rrNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobNum() {
		return mobNum;
	}

	public void setMobNum(String mobNum) {
		this.mobNum = mobNum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOem() {
		return oem;
	}

	public void setOem(String oem) {
		this.oem = oem;
	}

	public String getHeads() {
		return heads;
	}

	public void setHeads(String heads) {
		this.heads = heads;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Integer getDivId() {
		return divId;
	}

	public void setDivId(Integer divId) {
		this.divId = divId;
	}

	public Integer getSubDivId() {
		return subDivId;
	}

	public void setSubDivId(Integer subDivId) {
		this.subDivId = subDivId;
	}

	public String getLandingPage() {
		return landingPage;
	}

	public void setLandingPage(String landingPage) {
		this.landingPage = landingPage;
	}

	public String getEmpAdd() {
		return empAdd;
	}

	public void setEmpAdd(String empAdd) {
		this.empAdd = empAdd;
	}

	public Integer getEmpAge() {
		return empAge;
	}

	public void setEmpAge(Integer empAge) {
		this.empAge = empAge;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}



}
