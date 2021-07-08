package com.enzen.waterMdm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enzen.waterMdm.model.QualityData;

public interface QualityDataRepository extends CrudRepository<QualityData, Long>{
	

	public QualityData findById(Integer id);
	
	String qualityDataListSql = "select u.unitid, org.organizationname div, org1.organizationname subdiv,  \r\n" + 
			"qp.parameter, qd.value, qp.value as min, qp.max_value, coalesce(qp.remarks,'-') as remark, \r\n" + 
			"to_char(qd.date, 'DD/mm/YYYY') || ' ' || qd.time as dt\r\n" + 
			"from quality_data qd\r\n" + 
			"inner join unit_summary u on u.id = qd.unitid\r\n" + 
			"inner join quality_parameter qp on qp.id = qd.quality_id\r\n" + 
			"left join organization org on u.divisionid=org.organizationid\r\n" + 
			"left join organization org1 on u.subdivisionid=org1.organizationid \r\n" + 
			"where u.divisionid = :divid \r\n" + 
			"and u.subdivisionid = :subdiv\r\n" + 
			"and qd.date >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"order by 1";       
	@Query(value = qualityDataListSql, nativeQuery = true)
	public List<Object> getQualityDataList(Integer divid, Integer subdiv, String duration);
	
	String massBalanceListSql = "SELECT '50' as actual, \r\n" + 
			"'100' as total,\r\n" + 
			"'0' as loss,\r\n" + 
			"u.addedat, 'a' as duration\r\n" + 
			"from unitsconsumptiondetails u\r\n" + 
			"inner join unit_summary us on us.id = u.unitid\r\n";
	@Query(value = massBalanceListSql, nativeQuery = true)
	public List<Object> getMassBalanceList(Integer divid, Integer subdiv, String duration);
	
	String qualityDataListSql1 = "select a.org, a.org1, sum(a.cons) tot, sum(b.cons) act, sum((a.cons-b.cons)) loss, a.adat, a.adat  dt\r\n" + 
			"from  \r\n" + 
			"(  \r\n" + 
			"	select o.organizationname org, o1.organizationname org1, sum(u.consumption) cons, to_char(u.addedat, 'DD/mm/yyyy')  adat\r\n" + 
			"	from  \r\n" + 
			"	unitsconsumptiondetails u  \r\n" + 
			"	inner join unit_summary us on us.id = u.unitid  \r\n" + 
			"	inner join organization o on o.organizationid = us.locationid  \r\n" + 
			"	inner join organization o1 on us.subdivisionid=o1.organizationid  \r\n" + 
			"	where o.organizationtypeid = 3  \r\n" + 
			"	and us.divisionid = :divid  \r\n" + 
			"	and us.subdivisionid = :subdiv \r\n" + 
			"	and u.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.servicestnid is null  \r\n" + 
			"	group by 1, 2, 4 \r\n" + 
			"	order by 1 \r\n" + 
			") as a  \r\n" + 
			"inner join  \r\n" + 
			"(  \r\n" + 
			"	select o.organizationname org, o1.organizationname org1, sum(u.consumption) cons, u.addedat\r\n" + 
			"	from  \r\n" + 
			"	unitsconsumptiondetails u  \r\n" + 
			"	inner join unit_summary us on us.id = u.unitid  \r\n" + 
			"	inner join organization o on o.organizationid = us.locationid\r\n" + 
			"	inner join organization o1 on us.subdivisionid=o1.organizationid  \r\n" + 
			"	and us.divisionid = :divid  \r\n" + 
			"	and us.subdivisionid = :subdiv \r\n" + 
			"	and u.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.servicestnid is not null  \r\n" + 
			"	and us.meterflowtype = 'Inlet'  \r\n" + 
			"	group by 1, 2, 4 \r\n" + 
			"	order by 1 \r\n" + 
			") as b on a.org = b.org \r\n" + 
			"group by 1,2,6,7 \r\n" + 
			"order by 1";       
	@Query(value = qualityDataListSql1, nativeQuery = true)
	public List<Object> getQualityDataList1(Integer divid, Integer subdiv, String duration);
	
}
