package com.enzen.waterMdm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enzen.waterMdm.model.MnfData;

public interface MnfDataRepository extends CrudRepository<MnfData, Long>{
	

	public MnfData findById(Integer id);
	
	String mnfDataListSql = "select org.organizationname div, org1.organizationname subdiv,  \r\n" + 
			"md.loss, to_char(md.date, 'DD/mm/YYYY') || ' ' || md.time as dt\r\n" + 
			"from mnf_data md\r\n" + 
			"left join organization org on md.divisionid=org.organizationid\r\n" + 
			"left join organization org1 on md.subdivisionid=org1.organizationid \r\n" + 
			"where md.divisionid = :divid \r\n" + 
			"and md.subdivisionid = :subdiv\r\n" + 
			"and md.date >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"order by 1";       
	@Query(value = mnfDataListSql, nativeQuery = true)
	public List<Object> getMnfDataList(Integer divid, Integer subdiv, String duration);
	
	
}
