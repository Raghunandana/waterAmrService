package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.MasterValue;

public interface MasterValueRepository extends CrudRepository<MasterValue, Long> {

	
	MasterValue findById(Integer id);
	
	String mtrListSql = "select code, description\r\n" + 
			"from mastervalue\r\n" + 
			"where mastersid = 2\r\n" + 
			"order by 1";
	@Query(value = mtrListSql, nativeQuery = true)
	public List<Object> getMtrTypeList();
	
	String flowListSql = "select code, description\r\n" + 
			"from mastervalue\r\n" + 
			"where mastersid = 3\r\n" + 
			"order by 1";
	@Query(value = flowListSql, nativeQuery = true)
	public List<Object> getMtrFlowTypeList();
	
	String billTypeSql = "select code, description\r\n" + 
			"from mastervalue\r\n" + 
			"where mastersid = 8\r\n" + 
			"order by 1";
	@Query(value = billTypeSql, nativeQuery = true)
	public List<Object> getMtrBillingTypeList();
	
	String commTypeSql = "select code, description\r\n" + 
			"from mastervalue\r\n" + 
			"where mastersid = 11\r\n" + 
			"order by 1";
	@Query(value = commTypeSql, nativeQuery = true)
	public List<Object> getModeOfCommList();
	
	String gprsTypeSql = "select distinct gprstype as gp, gprstype \r\n" + 
			"from unit_summary \r\n" + 
			"where gprstype is not null\r\n" + 
			"order by 1";
	@Query(value = gprsTypeSql, nativeQuery = true)
	public List<Object> getGprsTypeList();
	
	String billingGroupTypeSql = "select distinct id, description\r\n" + 
			"from billgroup\r\n" + 
			"order by 1";
	@Query(value = billingGroupTypeSql, nativeQuery = true)
	public List<Object> getBillingGroupList();
}

