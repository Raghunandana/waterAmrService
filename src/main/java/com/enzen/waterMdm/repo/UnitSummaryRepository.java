package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.UnitSummary;

public interface UnitSummaryRepository extends CrudRepository<UnitSummary, Long> {

	public UnitSummary findById(Integer id);
	
	public UnitSummary findByUnitId(String unitId);
	
	String unitTypeSql = "select distinct meter_unit as code, meter_unit as name \r\n" + 
			"from unit_summary \r\n" + 
			"where meter_unit is not null \r\n";
	@Query(value = unitTypeSql, nativeQuery = true)
	public List<Object> getUnitTypeList();
	
	@Query("SELECT u FROM UnitSummary u where u.divId = ?1")
    public List<UnitSummary> findByDivId(Integer divId);
	
}

