package com.enzen.waterMdm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enzen.waterMdm.model.BillingReportMaster;

public interface BillingReportMasterRepository extends CrudRepository<BillingReportMaster, Long>{
	

	String billingListSql = "select distinct b.id, b.monthyear, us.unitid, b.firstreading, b.lastreading, b.consumption, b.watercharge, \r\n" + 
			"b.metercharge, b.sanitarycharge, b.borewellcharge, b.othercharge, b.arrears, b.interestonarrears,\r\n" + 
			"b.totalamount, to_char(b.addedat, 'DD/mm/YYYY HH:MM'), to_char(b.duedate, 'DD/mm/YYYY HH:MM'), to_char(b.billdate, 'DD/mm/YYYY HH:MM'), b.firstdate, b.lastdate, b.missingdays, b.extrapaid, \r\n" + 
			"b.previousbill, org.organizationname div, org1.organizationname subdiv,\r\n" + 
			"us.consumerid, us.consumeraddress  "+
			"from bills b  \r\n" + 
			"inner join unit_summary us on b.unitid = us.id\r\n" + 
			"left join organization org on us.divisionid=org.organizationid\r\n" + 
			"left join organization org1 on us.subdivisionid=org1.organizationid\r\n" + 
			"inner join (\r\n" + 
			"	select unitid, max(billdate) billdate\r\n" + 
			"	from bills\r\n" + 
			"	group by 1\r\n" + 
			") as m on b.unitid = m.unitid and b.billdate = m.billdate\r\n" +
			"where us.divisionid = :divid\r\n" + 
			"and us.subdivisionid = :subdiv\r\n" + 
			"and b.billdate >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"order by 1";       
	@Query(value = billingListSql, nativeQuery = true)
	public List<Object> getBillingList(Integer divid, Integer subdiv, String duration);
	
	String veeDataListSql = "select distinct us.unitid, cast(uc.addedat as date), '0' flag,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 0 then uc.consumption else 0 end) h0,\r\n" +
			"sum(case when date_part('hour',uc.addedat) = 1 then uc.consumption else 0 end) h1,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 2 then uc.consumption else 0 end) h2,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 3 then uc.consumption else 0 end) h3,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 4 then uc.consumption else 0 end) h4,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 5 then uc.consumption else 0 end) h5,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 6 then uc.consumption else 0 end) h6,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 7 then uc.consumption else 0 end) h7,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 8 then uc.consumption else 0 end) h8,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 9 then uc.consumption else 0 end) h9,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 10 then uc.consumption else 0 end) h10,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 11 then uc.consumption else 0 end) h11,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 12 then uc.consumption else 0 end) h12,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 13 then uc.consumption else 0 end) h13,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 14 then uc.consumption else 0 end) h14,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 15 then uc.consumption else 0 end) h15,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 16 then uc.consumption else 0 end) h16,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 17 then uc.consumption else 0 end) h17,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 18 then uc.consumption else 0 end) h18,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 19 then uc.consumption else 0 end) h19,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 20 then uc.consumption else 0 end) h20,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 21 then uc.consumption else 0 end) h21,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 22 then uc.consumption else 0 end) h22,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 23 then uc.consumption else 0 end) h23\r\n" + 
			"from unitsconsumptiondetails uc\r\n" + 
			"inner join unit_summary us on uc.unitid = us.id\r\n" + 
			"where us.divisionid = :divid\r\n" + 
			"and us.subdivisionid = :subdiv\r\n" + 
			//"and uc.daterecorded = '2020-07-09'\r\n" + 
			//"and us.unitid = 'abc10002' "+
			"and uc.addedat >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"group by 1, 2, 3\r\n" + 
			"union\r\n" + 
			"select distinct us.unitid, cast(uc.addedat as date), '1' flag,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 0 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h0,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 1 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h1, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 2 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h2, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 3 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h3, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 4 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h4, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 5 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h5, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 6 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h6, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 7 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h7, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 8 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h8, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 9 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h9, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 10 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h10, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 11 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h11, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 12 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h12, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 13 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h13, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 14 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h14, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 15 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h15, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 16 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h16, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 17 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h17, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 18 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h18, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 19 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h19, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 20 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h20, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 21 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h21, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 22 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h22, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 23 and uc.flag = 1 then uc.consumptioninmcube else 0 end) h23 \r\n" + 
			"from est_unitsconsumptiondetails uc\r\n" + 
			"inner join unit_summary us on uc.unitid = us.unitid\r\n" + 
			"where us.divisionid = :divid\r\n" + 
			"and us.subdivisionid = :subdiv\r\n" + 
			"and uc.addedat >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			//"and uc.daterecorded = '2020-07-09'\r\n" + 
			//"and uc.unitid = 'abc10002' "+
			"group by 1, 2, 3 " +
			"union\r\n" + 
			"select distinct us.unitid, cast(uc.addedat as date), '2' flag,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 0 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h0,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 1 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h1, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 2 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h2, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 3 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h3, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 4 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h4, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 5 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h5, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 6 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h6, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 7 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h7, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 8 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h8, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 9 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h9, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 10 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h10, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 11 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h11, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 12 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h12, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 13 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h13, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 14 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h14, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 15 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h15, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 16 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h16, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 17 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h17, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 18 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h18, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 19 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h19, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 20 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h20, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 21 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h21, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 22 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h22, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 23 and uc.flag = 2 then uc.consumptioninmcube else 0 end) h23 \r\n" + 
			"from est_unitsconsumptiondetails uc\r\n" + 
			"inner join unit_summary us on uc.unitid = us.unitid\r\n" + 
			"where us.divisionid = :divid\r\n" + 
			"and us.subdivisionid = :subdiv\r\n" + 
			"and uc.addedat >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			//"and uc.daterecorded = '2020-07-09'\r\n" + 
			//"and uc.unitid = 'abc10002' "+
			"group by 1, 2, 3 " + 
			"order by 2, 1, 3 desc";       
	@Query(value = veeDataListSql, nativeQuery = true)
	public List<Object> getVeeDataList(Integer divid, Integer subdiv, String duration);
	
	String lastveeDataListSql = "select distinct us.unitid, cast(uc.addedat as date), '0' flag,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 0 then uc.consumption else 0 end) h0,\r\n" +
			"sum(case when date_part('hour',uc.addedat) = 1 then uc.consumption else 0 end) h1,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 2 then uc.consumption else 0 end) h2,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 3 then uc.consumption else 0 end) h3,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 4 then uc.consumption else 0 end) h4,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 5 then uc.consumption else 0 end) h5,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 6 then uc.consumption else 0 end) h6,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 7 then uc.consumption else 0 end) h7,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 8 then uc.consumption else 0 end) h8,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 9 then uc.consumption else 0 end) h9,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 10 then uc.consumption else 0 end) h10,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 11 then uc.consumption else 0 end) h11,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 12 then uc.consumption else 0 end) h12,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 13 then uc.consumption else 0 end) h13,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 14 then uc.consumption else 0 end) h14,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 15 then uc.consumption else 0 end) h15,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 16 then uc.consumption else 0 end) h16,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 17 then uc.consumption else 0 end) h17,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 18 then uc.consumption else 0 end) h18,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 19 then uc.consumption else 0 end) h19,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 20 then uc.consumption else 0 end) h20,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 21 then uc.consumption else 0 end) h21,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 22 then uc.consumption else 0 end) h22,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 23 then uc.consumption else 0 end) h23\r\n" + 
			"from unitsconsumptiondetails uc\r\n" + 
			"inner join unit_summary us on uc.unitid = us.id\r\n" + 
			"where us.divisionid = :divid\r\n" + 
			"and us.subdivisionid = :subdiv\r\n" + 
			"and uc.addedat >= date_trunc('month', CURRENT_DATE) - interval '1 month' and uc.addedat < date_trunc('month', CURRENT_DATE)\r\n" +  
			"group by 1, 2, 3\r\n" + 
			"union\r\n" + 
			"select distinct us.unitid, cast(uc.addedat as date), '1' flag,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 0 and uc.flag = 1 then uc.consumption else 0 end) h0,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 1 and uc.flag = 1 then uc.consumption else 0 end) h1, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 2 and uc.flag = 1 then uc.consumption else 0 end) h2, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 3 and uc.flag = 1 then uc.consumption else 0 end) h3, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 4 and uc.flag = 1 then uc.consumption else 0 end) h4, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 5 and uc.flag = 1 then uc.consumption else 0 end) h5, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 6 and uc.flag = 1 then uc.consumption else 0 end) h6, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 7 and uc.flag = 1 then uc.consumption else 0 end) h7, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 8 and uc.flag = 1 then uc.consumption else 0 end) h8, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 9 and uc.flag = 1 then uc.consumption else 0 end) h9, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 10 and uc.flag = 1 then uc.consumption else 0 end) h10, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 11 and uc.flag = 1 then uc.consumption else 0 end) h11, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 12 and uc.flag = 1 then uc.consumption else 0 end) h12, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 13 and uc.flag = 1 then uc.consumption else 0 end) h13, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 14 and uc.flag = 1 then uc.consumption else 0 end) h14, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 15 and uc.flag = 1 then uc.consumption else 0 end) h15, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 16 and uc.flag = 1 then uc.consumption else 0 end) h16, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 17 and uc.flag = 1 then uc.consumption else 0 end) h17, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 18 and uc.flag = 1 then uc.consumption else 0 end) h18, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 19 and uc.flag = 1 then uc.consumption else 0 end) h19, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 20 and uc.flag = 1 then uc.consumption else 0 end) h20, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 21 and uc.flag = 1 then uc.consumption else 0 end) h21, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 22 and uc.flag = 1 then uc.consumption else 0 end) h22, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 23 and uc.flag = 1 then uc.consumption else 0 end) h23 \r\n" + 
			"from est_unitsconsumptiondetails uc\r\n" + 
			"inner join unit_summary us on uc.unitid = us.unitid\r\n" + 
			"where us.divisionid = :divid\r\n" + 
			"and us.subdivisionid = :subdiv\r\n" + 
			"and uc.addedat >= date_trunc('month', CURRENT_DATE) - interval '1 month' and uc.addedat < date_trunc('month', CURRENT_DATE)\r\n" + 
			"group by 1, 2, 3 " +
			"union\r\n" + 
			"select distinct us.unitid, cast(uc.addedat as date), '2' flag,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 0 and uc.flag = 2 then uc.consumption else 0 end) h0,\r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 1 and uc.flag = 2 then uc.consumption else 0 end) h1, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 2 and uc.flag = 2 then uc.consumption else 0 end) h2, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 3 and uc.flag = 2 then uc.consumption else 0 end) h3, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 4 and uc.flag = 2 then uc.consumption else 0 end) h4, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 5 and uc.flag = 2 then uc.consumption else 0 end) h5, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 6 and uc.flag = 2 then uc.consumption else 0 end) h6, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 7 and uc.flag = 2 then uc.consumption else 0 end) h7, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 8 and uc.flag = 2 then uc.consumption else 0 end) h8, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 9 and uc.flag = 2 then uc.consumption else 0 end) h9, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 10 and uc.flag = 2 then uc.consumption else 0 end) h10, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 11 and uc.flag = 2 then uc.consumption else 0 end) h11, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 12 and uc.flag = 2 then uc.consumption else 0 end) h12, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 13 and uc.flag = 2 then uc.consumption else 0 end) h13, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 14 and uc.flag = 2 then uc.consumption else 0 end) h14, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 15 and uc.flag = 2 then uc.consumption else 0 end) h15, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 16 and uc.flag = 2 then uc.consumption else 0 end) h16, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 17 and uc.flag = 2 then uc.consumption else 0 end) h17, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 18 and uc.flag = 2 then uc.consumption else 0 end) h18, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 19 and uc.flag = 2 then uc.consumption else 0 end) h19, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 20 and uc.flag = 2 then uc.consumption else 0 end) h20, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 21 and uc.flag = 2 then uc.consumption else 0 end) h21, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 22 and uc.flag = 2 then uc.consumption else 0 end) h22, \r\n" + 
			"sum(case when date_part('hour',uc.addedat) = 23 and uc.flag = 2 then uc.consumption else 0 end) h23 \r\n" + 
			"from est_unitsconsumptiondetails uc\r\n" + 
			"inner join unit_summary us on uc.unitid = us.unitid\r\n" + 
			"where us.divisionid = :divid\r\n" + 
			"and us.subdivisionid = :subdiv\r\n" + 
			"and uc.addedat >= date_trunc('month', CURRENT_DATE) - interval '1 month' and uc.addedat < date_trunc('month', CURRENT_DATE)\r\n" + 
			"group by 1, 2, 3 " + 
			"order by 2, 1, 3 desc";       
	@Query(value = lastveeDataListSql, nativeQuery = true)
	public List<Object> getLastVeeDataList(Integer divid, Integer subdiv);

	String unitBillSql = "select org.organizationname div, b.id, us.unitid, \r\n" + 
			"b.firstreading, b.lastreading, b.consumption, b.watercharge, \r\n" + 
			"b.metercharge, b.sanitarycharge, b.borewellcharge, b.othercharge, \r\n" + 
			"b.arrears, b.interestonarrears, b.totalamount, b.billdate, b.duedate,\r\n" + 
			"us.consumerid, us.consumeraddress, org1.organizationname subdiv, us.rrno "+
			"from bills b \r\n" + 
			"inner join unit_summary us on b.unitid = us.id \r\n" + 
			"left join organization org on us.divisionid=org.organizationid\r\n" + 
			"left join organization org1 on us.subdivisionid=org1.organizationid \r\n" + 
			"inner join (\r\n" + 
			"	select unitid, max(billdate) billdate\r\n" + 
			"	from bills\r\n" + 
			"	group by 1\r\n" + 
			") as m on b.unitid = m.unitid and b.billdate = m.billdate\r\n" + 
			"where us.unitid = :unitid";       
	@Query(value = unitBillSql, nativeQuery = true)
	public List<Object> getUnitBill(String unitid);
	
	String unitBillListSql = "select org.organizationname div, b.id, us.unitid, \r\n" + 
			"b.firstreading, b.lastreading, b.consumption, b.watercharge, \r\n" + 
			"b.metercharge, b.sanitarycharge, b.borewellcharge, b.othercharge, \r\n" + 
			"b.arrears, b.interestonarrears, b.totalamount, b.billdate, b.duedate,\r\n" + 
			"us.consumerid, us.consumeraddress, org1.organizationname subdiv "+
			"from bills b \r\n" + 
			"inner join unit_summary us on b.unitid = us.id \r\n" + 
			"left join organization org on us.divisionid=org.organizationid\r\n" + 
			"left join organization org1 on us.subdivisionid=org1.organizationid \r\n" + 
			"inner join (\r\n" + 
			"	select unitid, max(billdate) billdate\r\n" + 
			"	from bills\r\n" + 
			"	group by 1\r\n" + 
			") as m on b.unitid = m.unitid and b.billdate = m.billdate\r\n" +
			"where b.billdate >= date_trunc('month', CURRENT_DATE)";       
	@Query(value = unitBillListSql, nativeQuery = true)
	public List<Object> getUnitBillList();
	
	String unitPaymentSql = "select b.id, b.totalamount, b.billdate,\r\n" + 
			"us.consumerid, us.consumeraddress, us.rrno "+
			"from bills b \r\n" + 
			"inner join unit_summary us on b.unitid = us.id \r\n" + 
			"where us.unitid = :unitid order by b.id desc limit 1";       
	@Query(value = unitPaymentSql, nativeQuery = true)
	public List<Object> getUnitPayment(String unitid);
	
	@Query("SELECT b from BillingReportMaster b where b.unitId = ?1 ORDER BY b.id DESC")
	public List<BillingReportMaster> getBill(Integer unitid);
}
