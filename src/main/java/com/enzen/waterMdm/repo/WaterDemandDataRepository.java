package com.enzen.waterMdm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enzen.waterMdm.model.WaterDemandData;

public interface WaterDemandDataRepository extends CrudRepository<WaterDemandData, Long>{
	

	public WaterDemandData findById(Integer id);
	
	String waterDemandDataListSql = "select org.organizationname div, org1.organizationname subdiv, \r\n" + 
			"w.demand_type, w.usage_type, w.variation_type, w.value, w.date\r\n" + 
			"from water_demand_data w\r\n" + 
			"left join organization org on w.divisionid=org.organizationid\r\n" + 
			"left join organization org1 on w.subdivisionid=org1.organizationid \r\n" + 
			"where w.divisionid = :divid \r\n" + 
			"and w.subdivisionid = :subdiv\r\n" + 
			"and w.date >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"order by 1";       
	@Query(value = waterDemandDataListSql, nativeQuery = true)
	public List<Object> getWaterDemandDataList(Integer divid, Integer subdiv, String duration);
	
	
}
