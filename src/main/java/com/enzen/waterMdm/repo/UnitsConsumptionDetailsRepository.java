package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.UnitConsumptionDetails;

public interface UnitsConsumptionDetailsRepository extends CrudRepository<UnitConsumptionDetails, Long> {

	String lastDaySql = "select unitid, \r\n" + 
			"sum(case when date_part('hour',addedat) = 0 then consumption else 0 end) h0,\r\n" +
			"sum(case when date_part('hour',addedat) = 1 then consumption else 0 end) h1,\r\n" + 
			"sum(case when date_part('hour',addedat) = 2 then consumption else 0 end) h2,\r\n" + 
			"sum(case when date_part('hour',addedat) = 3 then consumption else 0 end) h3,\r\n" + 
			"sum(case when date_part('hour',addedat) = 4 then consumption else 0 end) h4,\r\n" + 
			"sum(case when date_part('hour',addedat) = 5 then consumption else 0 end) h5,\r\n" + 
			"sum(case when date_part('hour',addedat) = 6 then consumption else 0 end) h6,\r\n" + 
			"sum(case when date_part('hour',addedat) = 7 then consumption else 0 end) h7,\r\n" + 
			"sum(case when date_part('hour',addedat) = 8 then consumption else 0 end) h8,\r\n" + 
			"sum(case when date_part('hour',addedat) = 9 then consumption else 0 end) h9,\r\n" + 
			"sum(case when date_part('hour',addedat) = 10 then consumption else 0 end) h10,\r\n" + 
			"sum(case when date_part('hour',addedat) = 11 then consumption else 0 end) h11,\r\n" + 
			"sum(case when date_part('hour',addedat) = 12 then consumption else 0 end) h12,\r\n" + 
			"sum(case when date_part('hour',addedat) = 13 then consumption else 0 end) h13,\r\n" + 
			"sum(case when date_part('hour',addedat) = 14 then consumption else 0 end) h14,\r\n" + 
			"sum(case when date_part('hour',addedat) = 15 then consumption else 0 end) h15,\r\n" + 
			"sum(case when date_part('hour',addedat) = 16 then consumption else 0 end) h16,\r\n" + 
			"sum(case when date_part('hour',addedat) = 17 then consumption else 0 end) h17,\r\n" + 
			"sum(case when date_part('hour',addedat) = 18 then consumption else 0 end) h18,\r\n" + 
			"sum(case when date_part('hour',addedat) = 19 then consumption else 0 end) h19,\r\n" + 
			"sum(case when date_part('hour',addedat) = 20 then consumption else 0 end) h20,\r\n" + 
			"sum(case when date_part('hour',addedat) = 21 then consumption else 0 end) h21,\r\n" + 
			"sum(case when date_part('hour',addedat) = 22 then consumption else 0 end) h22,\r\n" + 
			"sum(case when date_part('hour',addedat) = 23 then consumption else 0 end) h23\r\n" + 
			"from unitsconsumptiondetails \r\n" + 
			"where daterecorded = current_date - 1\r\n" + 
			"and unitid = :unitid\r\n" + 
			"group by unitid\r\n" + 
			"order by unitid";
	@Query(value = lastDaySql, nativeQuery = true)
	public List<Object> getLastDayConsumptionData(Integer unitid);
	
	String previousDaySql = "select id, daterecorded, unitid, consumptioninmcube, dayconsumption, date_part('hour',addedat), messagedataid\r\n" + 
			"from unitsconsumptiondetails\r\n" + 
			"where unitid = :id\r\n" + 
			"and daterecorded = current_date - 1\r\n" + 
			"order by 1 desc ";
	@Query(value = previousDaySql, nativeQuery = true)
	public List<Object> getPrevDayConsData(Integer id);
	
	String previousToPrevDaySql = "select id, daterecorded, unitid, consumptioninmcube, dayconsumption, date_part('hour',addedat), messagedataid\r\n" + 
			"from unitsconsumptiondetails\r\n" + 
			"where unitid = :id\r\n" + 
			"and daterecorded = current_date - 2\r\n" + 
			"and date_part('hour',addedat) = 23 "+
			"order by 1 desc ";
	@Query(value = previousToPrevDaySql, nativeQuery = true)
	public List<Object> getPrevToPrevDayConsData(Integer id);
	
	String maxConsSql = "SELECT coalesce(consumptioninmcube,0) consumption, cast(addedat as date)\r\n" + 
			"FROM \r\n" + 
			"unitsconsumptiondetails\r\n" + 
			"where cast(addedat as date) < cast(date_trunc('month', current_date) as date)\r\n" + 
			"and unitid = :id\r\n" + 
			"order by 1 desc\r\n" + 
			"limit 1";
	@Query(value = maxConsSql, nativeQuery = true)
	public List<Object> getMaxCons(Integer id);
	
	String minConsSql = "SELECT coalesce(consumptioninmcube,0) consumption, cast(addedat as date)\r\n" + 
			"FROM \r\n" + 
			"unitsconsumptiondetails\r\n" + 
			"where cast(addedat as date) >= cast(date_trunc('month', now()) - interval '1 month' as date)\r\n" + 
			"and unitid = :id\r\n" + 
			"order by 1\r\n" + 
			"limit 1";       
	@Query(value = minConsSql, nativeQuery = true)
	public List<Object> getMinCons(Integer id);
	
	String maxCommSql = "SELECT cast(addedat as date), count(cast(addedat as date))\r\n" + 
			"FROM \r\n" + 
			"unitsconsumptiondetails\r\n" + 
			"where cast(addedat as date) < cast(date_trunc('month', current_date) as date)\r\n" + 
			"and cast(addedat as date) >= cast(date_trunc('month', now()) - interval '1 month' as date)\r\n" + 
			"and unitid = :id\r\n" + 
			"GROUP BY 1\r\n" + 
			"order by 1";       
	@Query(value = maxCommSql, nativeQuery = true)
	public List<Object> getMaxComm(Integer id);
	
	String maxEstConsSql = "SELECT coalesce(uc.consumptioninmcube,0) consumption, cast(uc.addedat as date)\r\n" + 
			"FROM \r\n" + 
			"est_unitsconsumptiondetails uc\r\n" + 
			"inner join unit_summary us on uc.unitid = us.unitid \r\n" + 
			"where cast(uc.addedat as date) < cast(date_trunc('month', current_date) as date)\r\n" + 
			"and us.id = :id\r\n" + 
			"order by 1 desc\r\n" + 
			"limit 1";
	@Query(value = maxEstConsSql, nativeQuery = true)
	public List<Object> getMaxEstCons(Integer id);
	
	String minEstConsSql = "SELECT coalesce(uc.consumptioninmcube,0) consumption, cast(uc.addedat as date)\r\n" + 
			"FROM \r\n" + 
			"est_unitsconsumptiondetails uc\r\n" + 
			"inner join unit_summary us on uc.unitid = us.unitid \r\n" + 
			"where cast(uc.addedat as date) >= cast(date_trunc('month', now()) - interval '1 month' as date)\r\n" + 
			"and us.id = :id\r\n" + 
			"order by 1\r\n" + 
			"limit 1";       
	@Query(value = minEstConsSql, nativeQuery = true)
	public List<Object> getMinEstCons(Integer id);
	
	String updateDateSql = "update est_unitsconsumptiondetails set daterecorded = date(addedat) \r\n" + 
			"where daterecorded <> date(addedat) ";
	@Query(value = updateDateSql, nativeQuery = true)
	public void updateRecordDate();
	
	String maxUnitConsSql = "select u.unitid, u.consumptioninmcube, u.daterecorded, u.addedat, extract(hour from u.addedat)\\:\\: bigint\r\n" + 
			"from unitsconsumptiondetails u\r\n" + 
			"inner join (\r\n" + 
			"	select u.id, max(uc.addedat) dt\r\n" + 
			"	from unitsconsumptiondetails uc\r\n" + 
			"	inner join unit_summary u on u.id = uc.unitid\r\n" + 
			//"	where u.divisionid = 117\r\n" + 
			"	and u.meterflowtype = 'Inlet'\r\n" + 
			"	group by 1\r\n" + 
			"	order by 1\r\n" + 
			") t on t.id = u.unitid and u.addedat = t.dt";       
	@Query(value = maxUnitConsSql, nativeQuery = true)
	public List<Object> getMaxConsCons();
	
	String updateConsDateSql = "update unitsconsumptiondetails set addedat = (daterecorded||' '||(addedat\\:\\:time))\\:\\:timestamp \r\n" + 
			"where daterecorded <> (addedat\\:\\:date) ";
	@Query(value = updateConsDateSql, nativeQuery = true)
	public void updateConsRecordDate();
	
	String maxDmaConsSql = "select u.id, t.cons\\:\\:bigint, to_char(t.daterecorded, 'YYYY-MM-DD')\r\n" + 
			"from (\r\n" + 
			"	SELECT us.locationid, u.daterecorded, sum(u.consumption)+5 cons  \r\n" + 
			"	FROM    \r\n" + 
			"	unitsconsumptiondetails u\r\n" + 
			"	inner join unit_summary us on u.unitid=us.id\r\n" + 
			"	where us.meterflowtype = 'Inlet'\r\n" + 
			"	GROUP BY 1, 2   \r\n" + 
			"	order by 1, 2 \r\n" + 
			") t\r\n" + 
			"inner join unit_summary u on u.locationid = t.locationid\r\n" + 
			"inner join (\r\n" + 
			"	SELECT u.unitid, max(u.daterecorded) codt  \r\n" + 
			"	FROM    \r\n" + 
			"	unitsconsumptiondetails u\r\n" + 
			"	inner join unit_summary us on u.unitid=us.id\r\n" + 
			"	where us.meterflowtype = 'Outlet'\r\n" + 
			"	GROUP BY 1   \r\n" + 
			"	order by 1\r\n" + 
			") s on s.unitid = u.id\r\n" +
			"where u.meterflowtype = 'Outlet'\r\n" + 
			"and u.servicestnid is null\r\n" + 
			"and s.codt < t.daterecorded \r\n" + 
			"order by 1";       
	@Query(value = maxDmaConsSql, nativeQuery = true)
	public List<Object> getDmaMaxCons();
	
	String updateFtpDateSql = "update ftp_data set readdatetime = now() ";
	@Query(value = updateFtpDateSql, nativeQuery = true)
	public void updateFtpRecordDate();
	
	
}

