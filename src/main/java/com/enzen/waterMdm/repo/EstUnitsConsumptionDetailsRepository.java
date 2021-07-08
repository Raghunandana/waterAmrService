package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.EstUnitConsumptionDetails;
import com.enzen.waterMdm.model.UnitSummary;

public interface EstUnitsConsumptionDetailsRepository extends CrudRepository<EstUnitConsumptionDetails, Long> {

	String estimatedListSql = "select id, daterecorded, unitid, consumption, dayconsumption, addedat, messagedataid\r\n" + 
			"from est_unitsconsumptiondetails\r\n" + 
			"where unitid = :id\r\n" + 
			"and daterecorded = cast(:dat as date)\r\n" + 
			"and date_part('hour',addedat) = cast(:hr as double precision)";       
	@Query(value = estimatedListSql, nativeQuery = true)
	public List<Object> getEstimatedData(String id, String dat, String hr);
	
	public EstUnitConsumptionDetails findById(Integer id);
	
	String prevEstimatedListSql = "select id, daterecorded, unitid, consumption, dayconsumption, addedat, messagedataid\r\n" + 
			"from est_unitsconsumptiondetails\r\n" + 
			"where unitid = :id\r\n" + 
			"and daterecorded = current_date - 2\r\n" + 
			"and date_part('hour',addedat) = 23";       
	@Query(value = prevEstimatedListSql, nativeQuery = true)
	public List<Object> getPrevEstimatedData(String id);
}

