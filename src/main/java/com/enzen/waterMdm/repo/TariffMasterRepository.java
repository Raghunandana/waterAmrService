package com.enzen.waterMdm.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.enzen.waterMdm.model.MasterValue;
import com.enzen.waterMdm.model.TariffMaster;

public interface TariffMasterRepository extends CrudRepository<TariffMaster, Long>{
	
	TariffMaster findById(Integer id);
	
	String tariffListSql = "select tm.tariffid, tm.categoryid, tm.slabmin, tm.slabmax, tm.tariff, tm.sanitary, tm.sanitarytype,\r\n" + 
			"tm.borewell, tm.metercost,  tm.year, o1.organizationname divName from tariff_master tm \r\n" + 
			"inner join organization o1 on tm.divisionid = o1.organizationid" ;
	@Query(value = tariffListSql, nativeQuery = true)
	public List<Object> getTariffList();
	
	@Query("SELECT t FROM TariffMaster t where t.categoryId = ?1 AND t.years = ?2 AND divisionId = ?3")
    public List<TariffMaster> findByCategoryIdAndYearsAndDivisionId(String categoryId, Integer years, Integer divisionId);
	
	@Query("SELECT t FROM TariffMaster t where t.tariffId = ?1")
    public List<TariffMaster> findByTariffId(Integer tariffId);
	
	@Query("SELECT coalesce(max(t.tariffId), 0)+1 FROM TariffMaster t")
	public Integer getMaxTariffId();
	
	@Modifying
	@Query("delete from TariffMaster t where t.tariffId=:tariffId")
	void deleteByTariffId(@Param("tariffId") Integer tariffId);
	
	
}
