package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.LoginMaster;

public interface LoginMasterRepository extends CrudRepository<LoginMaster, Long> {

	LoginMaster findByLoginName(String loginName);
	LoginMaster findByLoginNameAndPassword(String loginName, String password);
	
	String empDetailSql = "select lm.roles, lm.loginname, e.employeeid, concat(e.firstname,' ',e.midname,' ',e.lastname) as name, \r\n" + 
			"e.divisionid, e.subdivisionid, e.rrnumber, e.oem, e.heads, e.designation,  r.description ||' : '||o.organizationname org \r\n" + 
			"from employee e \r\n" + 
			"inner join employeeloginmapping map  on map.employeeid = e.employeeid \r\n" + 
			"inner join loginmaster lm on lm.loginname = map.loginname \r\n" + 
			"inner join roles r on lm.roles = r.roleid \r\n" + 
			"inner join organization o on o.organizationid = e.orgid \r\n" + 
			"where lm.loginname = :username";
	@Query(value = empDetailSql, nativeQuery = true)
	public List<Object> getEmpData(String username);
	
	
	String empListSql = "select e.employeeid, lm.loginname,  concat(e.firstname,' ',e.midname,' ',e.lastname) as name, \r\n" + 
			"e.rrnumber, e.email, e.mobilenumber\r\n" + 
			"from employee e  \r\n" + 
			"inner join employeeloginmapping map  on map.employeeid = e.employeeid \r\n" + 
			"inner join loginmaster lm on lm.loginname = map.loginname";
	@Query(value = empListSql, nativeQuery = true)
	public List<Object> getEmpList();
	
	String realTimeDataSqlOld = "select distinct us.unitid, us.meterslno, o1.organizationname div,  \r\n" + 
			"o2.organizationname subdiv, us.locationid, o3.organizationname dma, \r\n" + 
			"md.consumption, to_char(md.dt, 'DD/mm/YYYY HH:MM') recivedat, us.oemname, us.pipesize, md.batteryvoltage \r\n" + 
			"from  \r\n" + 
			"( \r\n" + 
			"	select unitid, addedat dt, consumption, 5 batteryvoltage   \r\n" + 
			"	from unitsconsumptiondetails  \r\n" + 
			"	where (unitid,addedat)  \r\n" + 
			"	in ( \r\n" + 
			"		select unitid,max(addedat)  \r\n" + 
			"		from unitsconsumptiondetails group by unitid \r\n" + 
			"	) \r\n" + 
			"	order by 1 \r\n" + 
			") as md \r\n" + 
			"inner join unit_summary us on md.unitid = us.id \r\n" + 
			"left join organization o1 on us.divisionid = o1.organizationid \r\n" + 
			"left join organization o2 on us.subdivisionid = o2.organizationid\r\n" + 
			"left join organization o3 on us.locationid = o3.organizationid\r\n" + 
			"where us.divisionid = :divisionid ";
	String realTimeDataSql = "select distinct us.unitid, us.meterslno, o1.organizationname div,\r\n" + 
			"o2.organizationname subdiv, TRUNC(md.consumptioninmcube) cur, TRUNC(md.dayconsumption) prev, TRUNC(md.consumption) act,\r\n" + 
			"to_char(md.dt, 'DD/mm/YYYY HH:MM') recivedat, md.batteryvoltage  \r\n" + 
			"from   \r\n" + 
			"(  \r\n" + 
			"	select unitid, addedat dt, consumptioninmcube, dayconsumption, consumption, 5 batteryvoltage\r\n" + 
			"	from unitsconsumptiondetails\r\n" + 
			"	where (unitid,addedat)\r\n" + 
			"	in (  \r\n" + 
			"		select unitid,max(addedat)\r\n" + 
			"		from unitsconsumptiondetails group by unitid\r\n" + 
			"	)  \r\n" + 
			"	order by 1  \r\n" + 
			") as md  \r\n" + 
			"inner join unit_summary us on md.unitid = us.id \r\n" + 
			"left join organization o1 on us.divisionid = o1.organizationid \r\n" + 
			"left join organization o2 on us.subdivisionid = o2.organizationid\r\n" + 
			"left join organization o3 on us.locationid = o3.organizationid\r\n" + 
			"where us.divisionid = :divisionid ";
	@Query(value = realTimeDataSql, nativeQuery = true)
	public List<Object> getRealTimeData(Integer divisionid);
	
	String subdivRealTimeDataSql = "select distinct us.unitid, us.meterslno, o1.organizationname div, \r\n" + 
			"o2.organizationname subdiv, us.locationid, concat(e.firstname,' ',e.midname,' ',e.lastname) consumer, md.consumption, \r\n" + 
			"md.dt recivedat, us.oemname, us.pipesize, md.batteryvoltage\r\n" + 
			"from \r\n" + 
			"(\r\n" + 
			"	select unitid, addedat dt, consumption, 5 batteryvoltage   \r\n" + 
			"	from unitsconsumptiondetails  \r\n" + 
			"	where (unitid,addedat)  \r\n" + 
			"	in ( \r\n" + 
			"		select unitid,max(addedat)  \r\n" + 
			"		from unitsconsumptiondetails group by unitid \r\n" + 
			"	) \r\n" + 
			"	order by 1 \r\n" + 
			") as md\r\n" + 
			"inner join unit_summary us on md.unitid = us.id\r\n" + 
			"left join organization o1 on us.divisionid = o1.organizationid\r\n" + 
			"left join organization o2 on us.subdivisionid = o2.organizationid " + 
			"inner join employee e on e.employeeid = us.consumerid "+  
			"where us.subdivisionid = :subdivisionid ";
	@Query(value = subdivRealTimeDataSql, nativeQuery = true)
	public List<Object> getSubDivRealTimeData(Integer subdivisionid);
	
	String historicalDataSql = "select us.oemname, to_char(cast(md.daterecorded as date), 'DD-mm-YYYY') , cast(md.addedat as time),\r\n" + 
			"5 batteryvoltage, md.consumption, 0 tamperstatus, \r\n" + 
			"us.unitid, md.addedat ad, TRUNC(md.consumptioninmcube) cur, TRUNC(md.dayconsumption) prev\r\n" + 
			"from unit_summary us\r\n" + 
			"inner join unitsconsumptiondetails md on md.unitid = us.id\r\n" + 
			"where us.unitid = :unitid\r\n" + 
			"order by 8 desc";
	@Query(value = historicalDataSql, nativeQuery = true)
	public List<Object> getHistoricalData(String unitid);
	
	String historicalDayDataSql = "select us.unitid, md.daterecorded, EXTRACT(YEAR FROM md.daterecorded) as yr, EXTRACT(day FROM md.daterecorded) as dt, \r\n" + 
			"TO_CHAR(TO_DATE (EXTRACT(month FROM md.daterecorded)||'', 'MM'), 'Month') as mnt,\r\n" + 
			"sum(md.consumption) as cons\r\n" + 
			"from unit_summary us \r\n" + 
			"inner join unitsconsumptiondetails md on md.unitid = us.id \r\n" + 
			"where us.unitid = :unitid\r\n" + 
			"group by 1, 2, 3, 4, 5\r\n" + 
			"order by 2";
	@Query(value = historicalDayDataSql, nativeQuery = true)
	public List<Object> getHistoricalDayData(String unitid);
	
	
	String historicalDataSqlOld = "select us.oemname, md.date, md.time,  \r\n" + 
			"md.batteryvoltage, md.cumconsumpofprevday, md.tamperstatus, us.unitid\r\n" + 
			"from unit_summary us\r\n" + 
			"inner join message_data md on md.unitid = us.id\r\n" + 
			"where us.unitid = :unitid";
	@Query(value = historicalDataSql, nativeQuery = true)
	public List<Object> getHistoricalDataOld(String unitid);
	
	String dailyConsumptionSql = "select con.unitidd, con.netconsumption, con.tod_yest, con.dailydate, con.todayconsumption, \r\n" + 
			"con.lastdayconsumption, con.meternumber, con.division, con.subdivision, con.category, con.locationname \r\n" + 
			"from (select (dailymeterconsumption()).*) as con";
	@Query(value = dailyConsumptionSql, nativeQuery = true)
	public List<Object> getDailyConsumptionData();
	
	String categoryWiseConsumptionSqlOld = "select con.category, sum(con.todayconsumption)\r\n" + 
			"from (select (dailymeterconsumption()).*) as con\r\n" + 
			"where con.category is not null\r\n" + 
			"and con.dailydate >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"group by 1";
	String categoryWiseConsumptionSql = "select mv.description Category, coalesce(sum(t.consumption - y.consumption),0)\r\n" + 
			"from\r\n" + 
			"(\r\n" + 
			"	SELECT unitid,\r\n" + 
			"	max(consumptioninmcube)consumption\r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails\r\n" + 
			"	where cast(addedat as date) <= date 'today'\r\n" + 
			"	GROUP BY unitid\r\n" + 
			"	order by 1\r\n" + 
			") t\r\n" + 
			"left join (\r\n" + 
			"	SELECT unitid,\r\n" + 
			"	min(consumptioninmcube)consumption\r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails\r\n" + 
			"	where cast(addedat as date) >= CURRENT_DATE - :duration\r\n" + 
			"	GROUP BY unitid\r\n" + 
			"	order by 1\r\n" + 
			") y on t.unitid = y.unitid\r\n" + 
			"left join unit_summary us on t.unitid=us.id\r\n" + 
			"left join organization org on us.locationid=org.organizationid\r\n" + 
			"left join organization org1 on us.divisionid=org1.organizationid\r\n" + 
			"left join organization org2 on us.subdivisionid=org2.organizationid\r\n" + 
			"inner join mastervalue mv on us.meterbillingtype = mv.code\r\n" + 
			"group by 1";
	@Query(value = categoryWiseConsumptionSql, nativeQuery = true)
	public List<Object> getCategoryWiseConsumptionData(Integer duration);
	
	String divisionWiseConsumptionSqlOld = "select con.division, round(sum(con.todayconsumption)/1000,2)\r\n" + 
			"from (select (dailymeterconsumption()).*) as con\r\n" + 
			"where con.division is not null\r\n" + 
			"and con.dailydate >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"group by 1";
	String divisionWiseConsumptionSql = "select org1.organizationname, coalesce(sum(t.consumption - y.consumption),0)\r\n" + 
			"from\r\n" + 
			"(\r\n" + 
			"	SELECT unitid,\r\n" + 
			"	max(consumptioninmcube)consumption\r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails\r\n" + 
			"	where cast(addedat as date) <= date 'today'\r\n" + 
			"	GROUP BY unitid\r\n" + 
			"	order by 1\r\n" + 
			") t\r\n" + 
			"left join (\r\n" + 
			"	SELECT unitid,\r\n" + 
			"	min(consumptioninmcube)consumption\r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails\r\n" + 
			//"	where cast(addedat as date) >= cast(now()- interval '1 month' as date)\r\n" +
			"	where cast(addedat as date) >= CURRENT_DATE - :duration\r\n" +
			"	GROUP BY unitid\r\n" + 
			"	order by 1\r\n" + 
			") y on t.unitid = y.unitid\r\n" + 
			"inner join unit_summary us on t.unitid=us.id\r\n" + 
			"left join organization org on us.locationid=org.organizationid\r\n" + 
			"left join organization org1 on us.divisionid=org1.organizationid\r\n" + 
			"left join organization org2 on us.subdivisionid=org2.organizationid\r\n" + 
			"left join mastervalue mv on us.meterbillingtype = mv.code\r\n" + 
			"group by 1\r\n";
	@Query(value = divisionWiseConsumptionSql, nativeQuery = true)
	public List<Object> getDivisionWiseConsumptionData(Integer duration);
	
	String complaintStatusSql = "select meter_status, count(meter_status)\r\n" + 
			"FROM meter_complain_status_update\r\n" + 
			"WHERE dateregister >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			//"and resolveddate is null\r\n" + 
			"group by 1\r\n" + 
			"order by 1";
	@Query(value = complaintStatusSql, nativeQuery = true)
	public List<Object> getComplaintStatus(String duration);
	
	String complaintStatus1Sql = "select meter_status, count(meter_status)\r\n" + 
			"FROM meter_complain_status_update\r\n" + 
			"WHERE dateregister <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" +
			"and dateregister > TO_DATE(:stdt,'YYY-MM-DD')\r\n" +
			"and divisionid=:div\r\n"+
			//"and resolveddate is null\r\n" + 
			"group by 1\r\n" + 
			"order by 1";
	@Query(value = complaintStatus1Sql, nativeQuery = true)
	public List<Object> getComplaintStatus1(String stdt, String eddt, Integer div);
	
	String complaintDetSql = "select meter_name, subdivision, meter_status, to_char(dateregister,'dd-mm-yyyy') cdt,\r\n" + 
			"coalesce(to_char(resolveddate,'dd-mm-yyyy'),'-') rdt, complainttype\r\n" + 
			"FROM meter_complain_status_update\r\n" + 
			"WHERE dateregister <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" +
			"and dateregister > TO_DATE(:stdt,'YYY-MM-DD')\r\n" +
			"and divisionid=:div\r\n"+ 
			"order by 3";
	@Query(value = complaintDetSql, nativeQuery = true)
	public List<Object> getComplaintDet(Integer div, String stdt, String eddt);
	
	String coordinateSql = "select g.gis_unitid, g.gis_longitude, g.gis_latitude, \r\n" + 
			"g.metername, g.metertype, g.metersino, g.organizationname, g.category,\r\n" + 
			"g.gis_consumptioninmcube, to_char(date(g.gis_addedat), 'DD/MM/YYYY'), g.gis_batteryvoltage \r\n" + 
			"from\r\n" + 
			"(select (gisdata()).*) as g";
	@Query(value = coordinateSql, nativeQuery = true)
	public List<Object> getCoordinates();
	
	String commSqlOld = "select c.f_status, count(c.f_status)\r\n" + 
			"from\r\n" + 
			"(select (meter_y_n()).*) as c\r\n" + 
			"inner join unit_summary u on u.id = c.f_id \r\n" + 
			"where u.divisionid = :div\r\n" + 
			"and u.meterflowtype = 'Inlet'\r\n" + 
			"group by 1\r\n" + 
			"order by 1";
	@Query(value = commSqlOld, nativeQuery = true)
	public List<Object> getCommDataOld(Integer div);
	
	String commDetSql = "select t.f_unitid, t.f_subdivision, t.f_category, t.f_status\r\n" + 
			"from\r\n" + 
			"(select (meter_y_n()).*) as t\r\n" + 
			"inner join unit_summary u on u.id = t.f_id \r\n" + 
			"where u.divisionid = :div " + 
			"and t.f_status= :commType " +
			"order by 1";
	@Query(value = commDetSql, nativeQuery = true)
	public List<Object> getCommDet(Integer div, String commType);
	
	String commSql = "select c.status, count(c.status)\r\n" + 
			"from(\r\n" + 
			"	select unitid,\r\n" + 
			"	CASE\r\n" + 
			"	  WHEN (t.occurence = '0') THEN 'NotCommunicated'\r\n" + 
			"	  WHEN (t.occurence>'1' and t.occurence<7) THEN 'PartialyCommunicated'\r\n" + 
			"	  WHEN (t.occurence>'6') THEN 'FullyCommunicated'\r\n" + 
			"	end as status\r\n" + 
			"	from\r\n" + 
			"	(\r\n" + 
			"		select unitid,count(*) occurence \r\n" + 
			"		from message_data \r\n" + 
			"       where date >= now() - :duration\\:\\:interval \r\n"+
			"		group by unitid\r\n" + 
			"	)t\r\n" + 
			") as c\r\n" + 
			"group by 1";
	@Query(value = commSql, nativeQuery = true)
	public List<Object> getCommData(String duration);
	
	String avgConsSqlOld = "select tod_yest , round(sum(todayconsumption)/1000,2) from\r\n" + 
			"(select (dailymeterconsumption()).*) as d\r\n" + 
			"where d.dailydate >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"group by 1 \r\n" + 
			"order by 1";
	String avgConsSql = "select y.yest, t.tdy\r\n" + 
			"from\r\n" + 
			"(\r\n" + 
			"	select coalesce(sum(w.cons),0) tdy \r\n" + 
			"	from\r\n" + 
			"	(\r\n" + 
			"		SELECT unitid,\r\n" + 
			"		coalesce(max(consumptioninmcube),0) cons\r\n" + 
			"		FROM \r\n" + 
			"		unitsconsumptiondetails\r\n" + 
			"		where cast(addedat as date) = date 'today'\r\n" + 
			"		GROUP BY unitid\r\n" + 
			"		order by 1\r\n" + 
			"	) w\r\n" + 
			") t,\r\n" + 
			"(\r\n" + 
			"	select coalesce(sum(r.consumption),0) yest from(\r\n" + 
			"		SELECT unitid,\r\n" + 
			"		coalesce(max(consumptioninmcube),0) consumption\r\n" + 
			"		FROM \r\n" + 
			"		unitsconsumptiondetails\r\n" + 
			"		where cast(addedat as date) = date 'yesterday'\r\n" + 
			"		GROUP BY unitid\r\n" + 
			"		order by 1\r\n" + 
			"	) r\r\n" + 
			") y";
	@Query(value = avgConsSql, nativeQuery = true)
	public List<Object> getAvGConsData(String duration);
	
	String roleMenuSql = "select m.id, m.code, m.description, m.menuorder, \r\n" + 
			"m.parentmenuid, \r\n" + 
			"case when p.menuid is not null then true else false end status\r\n" + 
			"from menumaster m\r\n" + 
			"left join menuprivileges p on p.menuid = m.id and p.roleid = :roleid \r\n" + 
			"order by 1 ";
	@Query(value = roleMenuSql, nativeQuery = true)
	public List<Object> getRoleMenuList(Integer roleid);
	
	String gprsDataSql = "select id,division,subdivision,servicestation,location,oemname,pipesize,battery_capacity,to_char(TO_DATE(readdatetime,'yyyy-mm-dd'), 'DD-mm-YYYY'),\r\n" + 
			"flowvalue,totalizer1,totalizer2,unitid,analoginput1value,analoginput2value,totalizer3,\r\n" + 
			"transmissiontimefromprotocol,meterslno\r\n" + 
			"from (select (getgprsdata(:readDate)).*) as a";
	@Query(value = gprsDataSql, nativeQuery = true)
	public List<Object> getGprsData(String readDate);
	
	String unitDataSql = "select a.id, a.consumer, a.division, a.subdivision, a.servicestation, a.location, a.consumptioninmcube, a.dayconsumption, \r\n" + 
			"to_char(a.daterecorded, 'DD-mm-YYYY') rdt, a.oemname, a.unitid, to_char(a.installon, 'DD-mm-YYYY') inson, a.batteryvoltage \r\n" + 
			"from \r\n" + 
			"(select (getunitreport( :mtrType, :oem, :div, :subdiv, :stn, :loc, :cons, :fromdt, :todt)).* as a) as a";
	@Query(value = unitDataSql, nativeQuery = true)
	public List<Object> getUnitData(String mtrType, String oem, String div, String subdiv, String stn, String loc, String cons, String fromdt, String todt);
	
	String mtrTypeSql = "select distinct us.metertype, m.description \r\n" + 
			"from unit_summary us\r\n" + 
			"inner join mastervalue m on us.metertype = m.code\r\n" + 
			"order by 1";
	@Query(value = mtrTypeSql, nativeQuery = true)
	public List<Object> getMtrList();
	
	String oemTypeSql = "select distinct us.oemname, m.description, m.mastervalueid \r\n" + 
			"from unit_summary us\r\n" + 
			"inner join mastervalue m on us.oemname = m.code\r\n" + 
			"order by 1";
	@Query(value = oemTypeSql, nativeQuery = true)
	public List<Object> getOemList();
	
	String divSql = "select organizationid, organizationname\r\n" + 
			"from organization\r\n" + 
			"where organizationtypeid = 1\r\n" + 
			"order by 2";
	@Query(value = divSql, nativeQuery = true)
	public List<Object> getDivList();
	
	String subdivSql = "select distinct o.organizationid, o.organizationname \r\n" + 
			"from organization o\r\n" + 
			"where o.parentid = :divid\r\n" + 
			"order by 1";
	@Query(value = subdivSql, nativeQuery = true)
	public List<Object> getSubDivList(Integer divid);
	
	String stnSql1 = "select distinct us.servicestnid, o.organizationname\r\n" + 
			"from unit_summary us\r\n" + 
			"inner join organization o on us.servicestnid = o.organizationid\r\n" + 
			"where o.parentid = :divid\r\n" + 
			"order by 1";
	String stnSql = "select distinct o.organizationid, o.organizationname \r\n" + 
			"from organization o\r\n" + 
			"where o.parentid = :divid\r\n" + 
			"order by 1";
	@Query(value = stnSql, nativeQuery = true)
	public List<Object> getStnList(Integer divid);
	
	String locSql1 = "select distinct us.locationid, o.organizationname\r\n" + 
			"from unit_summary us\r\n" + 
			"inner join organization o on us.locationid = o.organizationid\r\n" + 
			"where o.parentid = :divid\r\n" + 
			"order by 1";
	String locSql = "select distinct o.organizationid, o.organizationname \r\n" + 
			"from organization o\r\n" + 
			"where o.parentid = :divid\r\n" + 
			"order by 1";
	@Query(value = locSql, nativeQuery = true)
	public List<Object> getLocList(Integer divid);
	
	String consSql = "select distinct e.employeeid, concat(e.firstname,' ',e.midname,' ',e.lastname) as name\r\n" + 
			"from employee e\r\n" + 
			"inner join unit_summary us on e.employeeid = us.consumerid\r\n" + 
			"where e.divisionid = :divid\r\n" + 
			"order by 1";
	@Query(value = consSql, nativeQuery = true)
	public List<Object> getConsList(Integer divid);
	
	String rejMsgSql = "select id, message, to_char((sentdate || ' ' || senttime)\\:\\:timestamp, 'DD-mm-YYYY HH:MM') dt, rejectreason\r\n" + 
			"from message_rejected";
	@Query(value = rejMsgSql, nativeQuery = true)
	public List<Object> getRejMsgList();
	
	String oemSql = "select mastervalueid, code, description\r\n" + 
			"from mastervalue\r\n" + 
			"where mastersid = 5\r\n" + 
			"order by 1";
	@Query(value = oemSql, nativeQuery = true)
	public List<Object> getAllOemList();
	
	String catSql = "select distinct categoryid as id, categoryid as name\r\n" + 
			"from tariff_master \r\n" + 
			"order by 1";
	@Query(value = catSql, nativeQuery = true)
	public List<Object> getCategoryList();
	
	String unitConfSql = "select u.id, u.unitid, to_char(u.installon, 'DD/mm/YYYY'), u.pipesize, u.metercalibfactor, u.mobilenumberofunit,\r\n" + 
			"o.organizationname stn, o1.organizationname loc, o2.organizationname div, o3.organizationname subdiv,\r\n" +
			"mm.name meter , u.active from unit_summary u \r\n" + 
			"left join organization o on u.servicestnid = o.organizationid\r\n" + 
			"left join organization o1 on u.locationid = o1.organizationid\r\n" + 
			"left join organization o2 on u.divisionid = o2.organizationid\r\n" + 
			"left join organization o3 on u.subdivisionid = o3.organizationid\r\n" +
			"inner join meter_master mm on mm.id = u.meter_type";
	@Query(value =  unitConfSql, nativeQuery = true)
	public List<Object> getUnitConfList();
	
	
	String serviceproSql="select mastervalueid, code, description\r\n" + 
			"from mastervalue\r\n" + 
			"where mastersid = 4\r\n" + 
			"order by 1";
	@Query(value = serviceproSql, nativeQuery = true)
	public List<Object> getServicePro();
	
	
	String sanitarytypesql="select Distinct tm.sanitarytype id , tm.sanitarytype as name\r\n" + 
			"from tariff_master tm\r\n" + 
			"order by 1";
	@Query(value = sanitarytypesql, nativeQuery = true)
	public List<Object> getSanitarytypeList();
	
	String dayHistorySql = "select us.unitid, EXTRACT(hour FROM md.addedat) as hr, md.daterecorded,\r\n" + 
			"md.consumptioninmcube as cons\r\n" + 
			"from unit_summary us \r\n" + 
			"inner join unitsconsumptiondetails md on md.unitid = us.id \r\n" + 
			"where us.unitid = :unitid\r\n" + 
			"and md.daterecorded >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"order by 2";
	@Query(value = dayHistorySql, nativeQuery = true)
	public List<Object> getDayHistoryData(String duration, String unitid);
	
	String yearHistorySql = "select f.unitid, f.mnt, f.yr, sum(f.cons) \r\n" + 
			"from\r\n" + 
			"(\r\n" + 
			"	select us.unitid, EXTRACT(year FROM md.addedat) as yr, EXTRACT(day FROM md.daterecorded) as dt, \r\n" + 
			"	TO_CHAR(TO_DATE (EXTRACT(month FROM md.daterecorded)||'', 'MM'), 'Month') as mnt,\r\n" + 
			"	max(md.consumptioninmcube)-min(md.consumptioninmcube) as cons\r\n" + 
			"	from unit_summary us \r\n" + 
			"	inner join unitsconsumptiondetails md on md.unitid = us.id \r\n" + 
			"	where us.unitid = :unitid\r\n" + 
			"	and md.daterecorded >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"	group by 1, 2, 3, 4\r\n" + 
			"	order by 2 \r\n" + 
			") as f\r\n" + 
			"group by 1, 2, 3";
	@Query(value = yearHistorySql, nativeQuery = true)
	public List<Object> getYearHistoryData(String duration, String unitid);
	
	String weekHistorySql = "select us.unitid, EXTRACT(day FROM md.daterecorded) as dt, \r\n" + 
			"TO_CHAR(TO_DATE (EXTRACT(month FROM md.daterecorded)||'', 'MM'), 'Month') as mnt,\r\n" + 
			"max(md.consumptioninmcube)-min(md.consumptioninmcube) as cons\r\n" + 
			"from unit_summary us \r\n" + 
			"inner join unitsconsumptiondetails md on md.unitid = us.id \r\n" + 
			"where us.unitid = :unitid\r\n" + 
			"and md.daterecorded >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"group by 1, 2, 3\r\n" + 
			"order by 2";
	@Query(value = weekHistorySql, nativeQuery = true)
	public List<Object> getWeekHistoryData(String duration, String unitid);

	String divWiseConsumptionSql1 = "select org2.organizationname, mv.description Category, coalesce(sum(t.consumption - y.consumption)/1000,0) \r\n" + 
			"from \r\n" + 
			"( \r\n" + 
			"	SELECT unitid, \r\n" + 
			"	max(consumptioninmcube)consumption \r\n" + 
			"	FROM  \r\n" + 
			"	unitsconsumptiondetails \r\n" + 
			"	where cast(addedat as date) <= date 'today' \r\n" + 
			"	GROUP BY unitid \r\n" + 
			"	order by 1 \r\n" + 
			") t \r\n" + 
			"left join ( \r\n" + 
			"	SELECT unitid, \r\n" + 
			"	min(consumptioninmcube)consumption \r\n" + 
			"	FROM  \r\n" + 
			"	unitsconsumptiondetails \r\n" + 
			"	where cast(addedat as date) >= CURRENT_DATE - :duration\r\n" + 
			"	GROUP BY unitid \r\n" + 
			"	order by 1 \r\n" + 
			") y on t.unitid = y.unitid \r\n" + 
			"inner join unit_summary us on t.unitid=us.id \r\n" + 
			//"left join organization org on us.locationid=org.organizationid \r\n" + 
			//"left join organization org1 on us.divisionid=org1.organizationid \r\n" + 
			"left join organization org2 on us.subdivisionid=org2.organizationid \r\n" + 
			"left join mastervalue mv on us.meterbillingtype = mv.code \r\n" + 
			"where us.divisionid = :div\r\n" + 
			"group by 1, 2\r\n" +
			"order by 1, 2";
	String divWiseConsumptionSql = "select f.org, sum(f.dom) d, sum(f.ndom) n, sum (f.ind) i, TO_CHAR(TRUNC(sum(f.dom+f.ndom+f.ind)),'99,99,99,999') t1 from  \r\n" + 
			"( \r\n" + 
			"	select org,  \r\n" + 
			"	case when category='Domestic' then cons else 0 end dom, \r\n" + 
			"	case when category='Non Domestic' then cons else 0 end ndom, \r\n" + 
			"	case when category='Industrial' then cons else 0 end ind \r\n" + 
			"	from ( \r\n" + 
			"		select org2.organizationname org, mv.description category, coalesce(sum(t.consumption),0) cons   \r\n" + 
			"		from unitsconsumptiondetails t\r\n" + 
			"		inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"		left join organization org2 on us.subdivisionid=org2.organizationid  \r\n" + 
			"		left join mastervalue mv on us.meterbillingtype = mv.code  \r\n" + 
			"		where us.divisionid=:div\r\n" + 
			"		and cast(t.addedat as date) <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" + 
			"		and cast(t.addedat as date) > TO_DATE(:stdt,'YYY-MM-DD')\r\n" + 
			"		and us.meterflowtype = 'Inlet'\r\n" + 
			"		group by 1, 2  \r\n" + 
			"		order by 1, 2 \r\n" + 
			"	) as t \r\n" + 
			") as f \r\n" + 
			"group by f.org \r\n" + 
			"order by f.org";
	@Query(value = divWiseConsumptionSql, nativeQuery = true)
	public List<Object> getDivWiseConsumptionData(String stdt, String eddt, Integer div);
	
	String subdivWiseConsSql = "select f.org, sum(f.dom) d, sum(f.ndom) n, sum (f.ind) i, TO_CHAR(TRUNC(sum(f.dom+f.ndom+f.ind)),'99,99,99,999') t1 from  \r\n" + 
			"( \r\n" + 
			"	select org,  \r\n" + 
			"	case when category='Domestic' then cons else 0 end dom, \r\n" + 
			"	case when category='Non Domestic' then cons else 0 end ndom, \r\n" + 
			"	case when category='Industrial' then cons else 0 end ind \r\n" + 
			"	from ( \r\n" + 
			"		select org2.organizationname org, mv.description category, coalesce(sum(y.consumption),0) cons   \r\n" + 
			"		from unitsconsumptiondetails y\r\n" + 
			"		inner join unit_summary us on y.unitid=us.id   \r\n" + 
			"		inner join organization org2 on us.locationid=org2.organizationid \r\n" + 
			"		inner join organization org on us.subdivisionid=org.organizationid   \r\n" + 
			"		left join mastervalue mv on us.meterbillingtype = mv.code   \r\n" + 
			"		where org.organizationname=:subdiv \r\n" + 
			"		and cast(y.addedat as date) <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" + 
			"		and cast(y.addedat as date) > TO_DATE(:stdt,'YYY-MM-DD')\r\n" + 
			"		and us.meterflowtype = 'Inlet'\r\n" + 
			"		group by 1, 2  \r\n" + 
			"		order by 1, 2 \r\n" + 
			"	) as t \r\n" + 
			") as f \r\n" + 
			"group by f.org \r\n" + 
			"order by f.org";
	@Query(value = subdivWiseConsSql, nativeQuery = true)
	public List<Object> getSubDivWiseConsData(String stdt, String eddt, String subdiv);
	
	String dmaWiseConsSql = "select f.org, sum(f.dom) d, sum(f.ndom) n, sum (f.ind) i, TO_CHAR(TRUNC(sum(f.dom+f.ndom+f.ind)),'99,99,99,999') t1 from  \r\n" + 
			"( \r\n" + 
			"	select org,  \r\n" + 
			"	case when category='Domestic' then cons else 0 end dom, \r\n" + 
			"	case when category='Non Domestic' then cons else 0 end ndom, \r\n" + 
			"	case when category='Industrial' then cons else 0 end ind \r\n" + 
			"	from ( \r\n" + 
			"		select org2.organizationname org, mv.description category, coalesce(sum(y.consumption),0) cons   \r\n" + 
			"		from unitsconsumptiondetails y\r\n" + 
			"		inner join unit_summary us on y.unitid=us.id   \r\n" + 
			"		inner join organization org2 on us.servicestnid=org2.organizationid \r\n" + 
			"		inner join organization org on us.locationid=org.organizationid   \r\n" + 
			"		left join mastervalue mv on us.meterbillingtype = mv.code   \r\n" + 
			"		where org.organizationname=:dma \r\n" + 
			"		and cast(y.addedat as date) <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" + 
			"		and cast(y.addedat as date) > TO_DATE(:stdt,'YYY-MM-DD')\r\n" + 
			"		and us.meterflowtype = 'Inlet'\r\n" + 
			"		group by 1, 2  \r\n" + 
			"		order by 1, 2 \r\n" + 
			"	) as t \r\n" + 
			") as f \r\n" + 
			"group by f.org \r\n" + 
			"order by f.org";
	@Query(value = dmaWiseConsSql, nativeQuery = true)
	public List<Object> getDmaWiseConsData(String stdt, String eddt, String dma);
	
	String divWiseConsDetSql = "select org2.organizationname org, us.unitid, mv.description cat, coalesce(sum(y.consumption),0) cons   \r\n" + 
			"from unitsconsumptiondetails y\r\n" + 
			"inner join unit_summary us on y.unitid=us.id   \r\n" + 
			"left join organization org2 on us.subdivisionid=org2.organizationid   \r\n" + 
			"left join mastervalue mv on us.meterbillingtype = mv.code   \r\n" + 
			"where us.divisionid=:div\r\n" + 
			"and cast(y.addedat as date) <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" + 
			"and cast(y.addedat as date) > TO_DATE(:stdt,'YYY-MM-DD')\r\n" + 
			"and us.meterflowtype = 'Inlet'\r\n" + 
			"group by 1, 2, 3 \r\n" + 
			"order by 1, 2";
	@Query(value = divWiseConsDetSql, nativeQuery = true)
	public List<Object> getDivWiseConsDetData(String stdt, String eddt, Integer div);
	
	String subdivWiseConsDetSql = "select org2.organizationname org, us.unitid, mv.description cat, coalesce(sum(y.consumption),0) cons  \r\n" + 
			"from unitsconsumptiondetails y\r\n" + 
			"inner join unit_summary us on y.unitid=us.id \r\n" + 
			"inner join organization org2 on us.locationid=org2.organizationid \r\n" + 
			"inner join organization org on us.subdivisionid=org.organizationid   \r\n" + 
			"left join mastervalue mv on us.meterbillingtype = mv.code \r\n" + 
			"where org.organizationname=:subdiv \r\n" + 
			"and cast(y.addedat as date) <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" + 
			"and cast(y.addedat as date) > TO_DATE(:stdt,'YYY-MM-DD')\r\n" + 
			"and us.meterflowtype = 'Inlet'\r\n" + 
			"group by 1, 2, 3 \r\n" + 
			"order by 1, 2";
	@Query(value = subdivWiseConsDetSql, nativeQuery = true)
	public List<Object> getSubDivWiseConsDetData(String stdt, String eddt, String subdiv);
	
	String dmaWiseConsDetSql = "select org2.organizationname org, us.unitid, mv.description cat, coalesce(sum(y.consumption),0) cons  \r\n" + 
			"from unitsconsumptiondetails y   \r\n" + 
			"inner join unit_summary us on y.unitid=us.id \r\n" + 
			"inner join organization org2 on us.servicestnid=org2.organizationid \r\n" + 
			"inner join organization org on us.locationid=org.organizationid   \r\n" + 
			"left join mastervalue mv on us.meterbillingtype = mv.code \r\n" + 
			"where org.organizationname=:dma \r\n" + 
			"and cast(y.addedat as date) <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" + 
			"and cast(y.addedat as date) > TO_DATE(:stdt,'YYY-MM-DD')\r\n" + 
			"and us.meterflowtype = 'Inlet'\r\n" + 
			"group by 1, 2, 3 \r\n" + 
			"order by 1, 2";
	@Query(value = dmaWiseConsDetSql, nativeQuery = true)
	public List<Object> getDmaWiseConsDetData(String stdt, String eddt, String dma);
	
	String divWiseConsumerql = "select org1.organizationname, mv.description Category, count(us.id) \r\n" + 
			"from \r\n" + 
			"( \r\n" + 
			"	SELECT unitid, \r\n" + 
			"	max(consumptioninmcube)consumption \r\n" + 
			"	FROM  \r\n" + 
			"	unitsconsumptiondetails \r\n" + 
			"	where cast(addedat as date) <= date 'today' \r\n" + 
			"	GROUP BY unitid \r\n" + 
			"	order by 1 \r\n" + 
			") t \r\n" + 
			"left join ( \r\n" + 
			"	SELECT unitid, \r\n" + 
			"	min(consumptioninmcube)consumption \r\n" + 
			"	FROM  \r\n" + 
			"	unitsconsumptiondetails \r\n" + 
			"	where cast(addedat as date) >= CURRENT_DATE - :duration\r\n" + 
			"	GROUP BY unitid \r\n" + 
			"	order by 1 \r\n" + 
			") y on t.unitid = y.unitid \r\n" + 
			"inner join unit_summary us on t.unitid=us.id \r\n" + 
			"left join organization org on us.locationid=org.organizationid \r\n" + 
			"left join organization org1 on us.divisionid=org1.organizationid \r\n" + 
			"left join organization org2 on us.subdivisionid=org2.organizationid \r\n" + 
			"left join mastervalue mv on us.meterbillingtype = mv.code \r\n" + 
			"and us.meterflowtype = 'Inlet'\r\n" +
			"group by 1, 2\r\n" + 
			"order by 1, 2";
	String divWiseConsumerql1 = "select org.organizationname, mv.description Category,\r\n" + 
			"coalesce(sum(case when u.active is true then 1 end),0) active,\r\n" + 
			"coalesce(sum(case when u.active is false then 1 end),0) inactive\r\n" + 
			"from unit_summary u\r\n" + 
			"inner join organization org on u.subdivisionid=org.organizationid\r\n" + 
			"inner join mastervalue mv on u.meterbillingtype = mv.code\r\n" + 
			"where u.divisionid=:div\r\n" + 
			"and u.meterflowtype = 'Inlet'\r\n" +
			"group by 1, 2\r\n" + 
			"order by 1, 2";
	@Query(value = divWiseConsumerql1, nativeQuery = true)
	public List<Object> getDivWiseConsumerData(Integer div);
	
	String divActConsumerql = "select\r\n" + 
			"coalesce(sum(case when active is true then 1 end),0) active,\r\n" + 
			"coalesce(sum(case when active is false then 1 end),0) inactive\r\n" + 
			"from unit_summary \r\n" + 
			"where divisionid=:div";
	@Query(value = divActConsumerql, nativeQuery = true)
	public List<Object> getDivActConsumerData(Integer div);
	
	String divActConsDtql = "select org.organizationname, u.unitid, mv.description Category, u.active\r\n" + 
			"from unit_summary u\r\n" + 
			"inner join organization org on u.subdivisionid=org.organizationid\r\n" + 
			"inner join mastervalue mv on u.meterbillingtype = mv.code\r\n" + 
			"where u.divisionid=:div\r\n" + 
			"order by 1, 2, 3, 4";
	@Query(value = divActConsDtql, nativeQuery = true)
	public List<Object> getDivActConsDet(Integer div);
	
	String consumerCountSql = "select count(distinct us.unitid) \r\n" + 
			"from unit_summary us\r\n" + 
			"left join organization org on us.divisionid=org.organizationid \r\n" + 
			"where us.divisionid = :div";
	@Query(value = consumerCountSql, nativeQuery = true)
	public Integer getCounsumerCount(Integer div);
	
	String divConsumerCountSql = "select count(distinct uc.unitid)\r\n" + 
			"FROM unitsconsumptiondetails uc\r\n" + 
			"left join unit_summary us on uc.unitid=us.id \r\n" + 
			"left join organization org on us.divisionid=org.organizationid \r\n" + 
			"where uc.addedat > TO_DATE(:stdt,'YYY-MM-DD')\r\n" +
			"and uc.addedat <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" +
			"and us.divisionid = :div";
	@Query(value = divConsumerCountSql, nativeQuery = true)
	public Integer getDivCounsumerCount(Integer div, String stdt, String eddt);
	
	String divTotConsSql = "SELECT coalesce(sum(t.consumption),0) consumption \r\n" + 
			"FROM \r\n" + 
			"unitsconsumptiondetails t \r\n" + 
			"inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"inner join organization org on us.divisionid=org.organizationid \r\n" + 
			"where cast(t.addedat as date) <= date 'today' \r\n" + 
			"and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"and us.meterflowtype = 'Inlet'\r\n" +
			"and us.divisionid = :div";
	@Query(value = divTotConsSql, nativeQuery = true)
	public Double getDivTotCons(Integer div, String duration);
	
	String prevdivTotConsSql = "SELECT coalesce(sum(t.consumption),0) consumption  \r\n" + 
			"FROM   \r\n" + 
			"unitsconsumptiondetails t \r\n" + 
			"inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"inner join organization org on us.divisionid=org.organizationid \r\n" + 
			"where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"and us.meterflowtype = 'Inlet'\r\n" +
			"and us.divisionid = :div";
	@Query(value = prevdivTotConsSql, nativeQuery = true)
	public Double gePrevtDivTotCons(Integer div, String duration, String pduration);
	
	String peakMonthConsSql = "select max(a.cons)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = peakMonthConsSql, nativeQuery = true)
	public Double getPeakMonthCons(Integer div, String duration);
	
	String peakHourConsSql = "select max(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(hour from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = peakHourConsSql, nativeQuery = true)
	public Double getPeakHourCons(Integer div, String duration);
	
	String peakWeekConsSql = "select max(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(day from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = peakWeekConsSql, nativeQuery = true)
	public Double getPeakWeekCons(Integer div, String duration);
	
	String peakYearConsSql = "select max(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(month from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = peakYearConsSql, nativeQuery = true)
	public Double getPeakYearCons(Integer div, String duration);
	
	String minMonthConsSql = "select min(a.cons)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = minMonthConsSql, nativeQuery = true)
	public Double getMinMonthCons(Integer div, String duration);
	
	String minHourConsSql = "select min(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(hour from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = minHourConsSql, nativeQuery = true)
	public Double getMinHourCons(Integer div, String duration);
	
	String minWeekConsSql = "select min(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(day from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = minWeekConsSql, nativeQuery = true)
	public Double getMinWeekCons(Integer div, String duration);
	
	String minYearConsSql = "select min(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(month from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = minYearConsSql, nativeQuery = true)
	public Double getMinYearCons(Integer div, String duration);
	
	String avgMonthConsSql = "select round(sum(a.cons)/count(a.dt),2)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = avgMonthConsSql, nativeQuery = true)
	public Double getAvgMonthCons(Integer div, String duration);
	
	String avgHourConsSql = "select round(sum(a.cons)/count(a.dt),2) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(hour from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = avgHourConsSql, nativeQuery = true)
	public Double getAvgHourCons(Integer div, String duration);
	
	String avgWeekConsSql = "select round(sum(a.cons)/count(a.dt),2) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(day from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = avgWeekConsSql, nativeQuery = true)
	public Double getAvgWeekCons(Integer div, String duration);
	
	String avgYearConsSql = "select round(sum(a.cons)/count(a.dt),2) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(month from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = avgYearConsSql, nativeQuery = true)
	public Double getAvgYearCons(Integer div, String duration);
	
	String peakPrevMonthConsSql = "select max(a.cons)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = peakPrevMonthConsSql, nativeQuery = true)
	public Double getPrevPeakMonthCons(Integer div, String duration, String pduration);
	
	String peakPrevHourConsSql = "select max(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(hour from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = peakPrevHourConsSql, nativeQuery = true)
	public Double getPrevPeakHourCons(Integer div, String duration, String pduration);
	
	String peakPrevWeekConsSql = "select max(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(day from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = peakPrevWeekConsSql, nativeQuery = true)
	public Double getPrevPeakWeekCons(Integer div, String duration, String pduration);
	
	String peakPrevYearConsSql = "select max(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(month from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = peakPrevYearConsSql, nativeQuery = true)
	public Double getPrevPeakYearCons(Integer div, String duration, String pduration);
	
	String minPrevMonthConsSql = "select min(a.cons)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = minPrevMonthConsSql, nativeQuery = true)
	public Double getPrevMinMonthCons(Integer div, String duration, String pduration);
	
	String minPrevHourConsSql = "select min(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(hour from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = minPrevHourConsSql, nativeQuery = true)
	public Double getPrevMinHourCons(Integer div, String duration, String pduration);
	
	String minPrevWeekConsSql = "select min(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(day from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = minPrevWeekConsSql, nativeQuery = true)
	public Double getPrevMinWeekCons(Integer div, String duration, String pduration);
	
	String minPrevYearConsSql = "select min(a.cons) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(month from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = minPrevYearConsSql, nativeQuery = true)
	public Double getPrevMinYearCons(Integer div, String duration, String pduration);
	
	String avgPrevMonthConsSql = "select round(sum(a.cons)/count(a.dt),2)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = avgPrevMonthConsSql, nativeQuery = true)
	public Double getPrevAvgMonthCons(Integer div, String duration, String pduration);
	
	String avgPrevHourConsSql = "select round(sum(a.cons)/count(a.dt),2) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(hour from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = avgPrevHourConsSql, nativeQuery = true)
	public Double getPrevAvgHourCons(Integer div, String duration, String pduration);
	
	String avgPrevWeekConsSql = "select round(sum(a.cons)/count(a.dt),2) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(day from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = avgPrevWeekConsSql, nativeQuery = true)
	public Double getPrevAvgWeekCons(Integer div, String duration, String pduration);
	
	String avgPrevYearConsSql = "select round(sum(a.cons)/count(a.dt),2) \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, extract(month from t.addedat) dt \r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" +
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" +
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = avgPrevYearConsSql, nativeQuery = true)
	public Double getPrevAvgYearCons(Integer div, String duration, String pduration);
	
	String prevpeakConsSql = "select max(a.cons)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a ";
	@Query(value = prevpeakConsSql, nativeQuery = true)
	public Double getPrevPeakCons(Integer div, String duration, String pduration);
	
	String minConsSql = "select min(a.cons)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a";
	@Query(value = minConsSql, nativeQuery = true)
	public Double getMinCons(Integer div, String duration);
	
	String prevminConsSql = "select min(a.cons)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as a";
	@Query(value = prevminConsSql, nativeQuery = true)
	public Double getPrevMinCons(Integer div, String duration, String pduration);
	
	String avgConSql = "select round(sum(f.cons)/count(f.dt),2)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as f";
	@Query(value = avgConSql, nativeQuery = true)
	public Double getAvgCons(Integer div, String duration);
	
	String prevavgConSql = "select round(sum(f.cons)/count(f.dt),2)  \r\n" + 
			"from ( \r\n" + 
			"	SELECT sum(t.consumption) cons, cast(t.addedat as date) as dt \r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	left join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) < date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"	and t.addedat >= date_trunc(:duration, now()) - :pduration\\:\\:interval\r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	group by 2 \r\n" + 
			"	order by 1 desc \r\n" + 
			") as f";
	@Query(value = prevavgConSql, nativeQuery = true)
	public Double getPrevAvgCons(Integer div, String duration, String pduration);
	
	String dayConsSql = "SELECT extract(hour from t.addedat) dt, sum(t.consumption) cons\r\n" + 
			"FROM   \r\n" + 
			"unitsconsumptiondetails t \r\n" + 
			"inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"inner join organization org on us.divisionid=org.organizationid \r\n" + 
			"where cast(t.addedat as date) = CURRENT_DATE\r\n" + 
			"and us.divisionid = :div\r\n" + 
			"and us.meterflowtype = 'Inlet' \r\n"+
			"group by 1\r\n" + 
			"order by 1";
	@Query(value = dayConsSql, nativeQuery = true)
	public List<Object> getDayCons(Integer div);
	
	String dayConsSql1 = "SELECT extract(hour from t.addedat) dt, t.consumptioninmcube cons \r\n" + 
			"FROM    \r\n" + 
			"unitsconsumptiondetails t  \r\n" + 
			"inner join unit_summary us on t.unitid=us.id   \r\n" + 
			"inner join organization org on us.divisionid=org.organizationid  \r\n" + 
			"where cast(t.addedat as date) = '2020-05-04' \r\n" + 
			"and us.divisionid = :div\r\n" + 
			"and us.meterflowtype = 'Inlet'\r\n" +
			"group by 1, 2 \r\n" + 
			"order by 1";
	@Query(value = dayConsSql1, nativeQuery = true)
	public List<Object> getDayCons1(Integer div);
	
	String weekConsSql = "SELECT To_Char(t.addedat, 'DAY') dt, coalesce(sum(t.consumption),0)  cons\r\n" + 
			"	FROM   \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	inner join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div\r\n" + 
			"	and us.meterflowtype = 'Inlet'\r\n" +
			"	group by 1\r\n" + 
			"	order by 1";
	@Query(value = weekConsSql, nativeQuery = true)
	public List<Object> getWeekCons(Integer div, String duration);
	
	String monthConsSql = "SELECT "
			+ "to_char(cast(t.addedat as date), 'DD/mm/YYYY') dt, coalesce(sum(t.consumption),0) cons\r\n" + 
			"	FROM \r\n" + 
			"	unitsconsumptiondetails t \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id  \r\n" + 
			"	inner join organization org on us.divisionid=org.organizationid \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE) \r\n" + 
			"	and us.divisionid = :div\r\n" + 
			"	and us.meterflowtype = 'Inlet'\r\n" +
			"	group by 1\r\n" + 
			"	order by 1";
	@Query(value = monthConsSql, nativeQuery = true)
	public List<Object> getMonthCons(Integer div, String duration);
	
	String yearConsSql = "select\r\n" + 
			"	case when t.dt = 1 then 'January'\r\n" + 
			"	 when t.dt = 2 then 'February'\r\n" + 
			"	 when t.dt = 3 then 'March'\r\n" + 
			"	 when t.dt = 4 then 'April'\r\n" + 
			"	 when t.dt = 5 then 'May'\r\n" + 
			"	 when t.dt = 6 then 'June'\r\n" + 
			"	 when t.dt = 7 then 'July'\r\n" + 
			"	 when t.dt = 8 then 'August'\r\n" + 
			"	 when t.dt = 9 then 'September'\r\n" + 
			"	 when t.dt = 10 then 'October'\r\n" + 
			"	 when t.dt = 11 then 'November'\r\n" + 
			"	 when t.dt = 12 then 'December'\r\n" + 
			"	end as dt,\r\n" + 
			"	sum(t.cons)\r\n" + 
			"from\r\n" + 
			"(\r\n" + 
			"	SELECT extract(month from t.addedat) dt, coalesce(sum(t.consumption),0) cons \r\n" + 
			"	FROM    \r\n" + 
			"	unitsconsumptiondetails t  \r\n" + 
			"	inner join unit_summary us on t.unitid=us.id   \r\n" + 
			"	inner join organization org on us.divisionid=org.organizationid  \r\n" + 
			"	where cast(t.addedat as date) <= date 'today' \r\n" + 
			"	and t.addedat >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"	and us.divisionid = :div\r\n" + 
			"	and us.meterflowtype = 'Inlet'\r\n" +
			"	group by 1\r\n" + 
			"	order by 1\r\n" + 
			") t\r\n" + 
			"group by t.dt\r\n" + 
			"order by t.dt";
	@Query(value = yearConsSql, nativeQuery = true)
	public List<Object> getYearCons(Integer div, String duration);
	
	
	String waterDemandDataSql = "SELECT sum(w.value) cons, cast(w.date as date) as dt\r\n" + 
			"FROM  \r\n" + 
			"water_demand_data w\r\n" + 
			"where cast(w.date as date) <= date 'today'\r\n" + 
			"and w.date >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"and w.divisionid = :div\r\n" + 
			"group by 2\r\n" + 
			"order by 2";
	@Query(value = waterDemandDataSql, nativeQuery = true)
	public List<Object> getWaterDemandData(Integer div, String duration);
	
	String mnfDataSql = "SELECT sum(m.loss) cons, cast(m.date as date) as dt\r\n" + 
			"FROM  \r\n" + 
			"mnf_data m\r\n" + 
			"where cast(m.date as date) <= date 'today'\r\n" + 
			"and m.date >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"and m.divisionid = :div\r\n" + 
			"group by 2\r\n" + 
			"order by 2";
	@Query(value = mnfDataSql, nativeQuery = true)
	public List<Object> getMnfData(Integer div, String duration);
	
	String massDataSql11 = "select a.org, a.cons tot, b.cons act, (a.cons-b.cons) loss\r\n" + 
			"from\r\n" + 
			"(\r\n" + 
			"	select t.org, max(t.con) - min(t.con) cons\r\n" + 
			"	from\r\n" + 
			"	(\r\n" + 
			"		select o.organizationname org, u.daterecorded, sum(u.consumptioninmcube) con\r\n" + 
			"		from\r\n" + 
			"		unitsconsumptiondetails u\r\n" + 
			"		inner join unit_summary us on us.id = u.unitid\r\n" + 
			"		inner join organization o on o.organizationid = us.locationid\r\n" + 
			"		where o.organizationtypeid = 3\r\n" + 
			"		and us.divisionid = :div\r\n" + 
			"		and u.addedat > TO_DATE(:stdt,'YYY-MM-DD')\r\n" + 
			"		and u.addedat <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" +
			"		and us.servicestnid is null\r\n" + 
			"		group by 1, 2\r\n" + 
			"		order by 1, 2\r\n" + 
			"	) t\r\n" + 
			"	group by 1\r\n" + 
			") as a\r\n" + 
			"inner join\r\n" + 
			"(\r\n" + 
			"	select t.org, max(t.con) - min(t.con) cons\r\n" + 
			"	from\r\n" + 
			"	(\r\n" + 
			"		select o.organizationname org, u.daterecorded, sum(u.consumptioninmcube) con\r\n" + 
			"		from\r\n" + 
			"		unitsconsumptiondetails u\r\n" + 
			"		inner join unit_summary us on us.id = u.unitid\r\n" + 
			"		inner join organization o on o.organizationid = us.locationid\r\n" + 
			"		and us.divisionid = :div\r\n" + 
			"		and u.addedat > TO_DATE(:stdt,'YYY-MM-DD')\r\n" +
			"		and u.addedat <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" +
			"		and us.servicestnid is not null\r\n" + 
			"		and us.meterflowtype = 'Inlet'\r\n" + 
			"		group by 1, 2\r\n" + 
			"		order by 1, 2\r\n" + 
			"	) t\r\n" + 
			"	group by 1\r\n" + 
			") as b on a.org = b.org\r\n" + 
			"order by 1";
	String massDataSql = "select a.org, a.cons tot, b.cons act, (a.cons-b.cons) loss \r\n" + 
			"from \r\n" + 
			"( \r\n" + 
			"	select o.organizationname org, sum(u.consumption) cons \r\n" + 
			"	from \r\n" + 
			"	unitsconsumptiondetails u \r\n" + 
			"	inner join unit_summary us on us.id = u.unitid \r\n" + 
			"	inner join organization o on o.organizationid = us.locationid \r\n" + 
			"	where o.organizationtypeid = 3 \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	and u.addedat > TO_DATE(:stdt,'YYY-MM-DD') \r\n" + 
			"	and u.addedat <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" + 
			"	and us.servicestnid is null \r\n" + 
			"	group by 1\r\n" + 
			"	order by 1\r\n" + 
			") as a \r\n" + 
			"inner join \r\n" + 
			"( \r\n" + 
			"	select o.organizationname org, sum(u.consumption) cons \r\n" + 
			"	from \r\n" + 
			"	unitsconsumptiondetails u \r\n" + 
			"	inner join unit_summary us on us.id = u.unitid \r\n" + 
			"	inner join organization o on o.organizationid = us.locationid \r\n" + 
			"	and us.divisionid = :div \r\n" + 
			"	and u.addedat > TO_DATE(:stdt,'YYY-MM-DD')\r\n" + 
			"	and u.addedat <= TO_DATE(:eddt,'YYY-MM-DD')\r\n" + 
			"	and us.servicestnid is not null \r\n" + 
			"	and us.meterflowtype = 'Inlet' \r\n" + 
			"	group by 1\r\n" + 
			"	order by 1\r\n" + 
			") as b on a.org = b.org \r\n" + 
			"order by 1";
	@Query(value = massDataSql, nativeQuery = true)
	public List<Object> getMassData(Integer div, String stdt, String eddt);
	
	String massDataSql1 = "select o.organizationname, sum(t.tot) total, sum(t.act) actual\r\n" + //, (t.tot-t.act) loss
			"from\r\n" + 
			"(\r\n" + 
			"	select a.locationid, a.daterecorded, a.con tot, b.con act\r\n" + 
			"	from\r\n" + 
			"	(\r\n" + 
			"		select us.locationid, u.daterecorded, sum(u.consumptioninmcube) con\r\n" + 
			"		from\r\n" + 
			"		unitsconsumptiondetails u\r\n" + 
			"		inner join unit_summary us on us.id = u.unitid\r\n" + 
			"		inner join organization o on o.organizationid = us.locationid\r\n" + 
			"		where o.organizationtypeid = 3\r\n" + 
			"		and us.divisionid = :div\r\n" + 
			//"		and u.addedat >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"		and us.servicestnid is null\r\n" + 
			"		group by 1, 2\r\n" + 
			"		order by 1, 2\r\n" + 
			"	) as a\r\n" + 
			"	inner join (\r\n" + 
			"		select us.locationid, u.daterecorded, sum(u.consumptioninmcube) con\r\n" + 
			"		from\r\n" + 
			"		unitsconsumptiondetails u\r\n" + 
			"		inner join unit_summary us on us.id = u.unitid\r\n" + 
			"		where us.servicestnid is not null\r\n" + 
			"		and us.divisionid = :div\r\n" + 
			//"		and u.addedat >= date_trunc(:duration, CURRENT_DATE)\r\n" + 
			"		group by 1, 2\r\n" + 
			"		order by 1, 2\r\n" + 
			"	) as b on a.locationid = b.locationid and a.daterecorded = b.daterecorded\r\n" + 
			"	group by 1, 2, a.con, b.con\r\n" + 
			") as t\r\n" + 
			"inner join organization o on o.organizationid = t.locationid\r\n" + 
			"group by 1\r\n" + //, 4
			"order by 1";
	@Query(value = massDataSql1, nativeQuery = true)
	public List<Object> getMassData1(Integer div);
	
	String qualityDataSql = "select u.unitid, \r\n" + 
			"qp.parameter, \r\n" + 
			"case when qd.value < qp.value then 'decreased-' || (qp.value - qd.value)\r\n" + 
			"     when qd.value > qp.max_value then 'increased-' || (qd.value - qp.max_value)\r\n" + 
			"     else 'normalval-'||qd.value\r\n" + 
			"end as cond,\r\n" + 
			"qp.value|| ' - ' ||qp.max_value as ref,\r\n" + 
			"qd.value\r\n" + 
			"from quality_data qd \r\n" + 
			"inner join unit_summary u on u.id = qd.unitid \r\n" + 
			"inner join quality_parameter qp on qp.id = qd.quality_id \r\n" + 
			"left join organization org on u.divisionid=org.organizationid\r\n" + 
			"where u.divisionid = :div \r\n" + 
			"and qd.date > TO_DATE(:stdt,'YYY-MM-DD') \r\n" +
			"and qd.date <= TO_DATE(:eddt,'YYY-MM-DD') \r\n" +
			"order by 1";
	@Query(value = qualityDataSql, nativeQuery = true)
	public List<Object> getQualityData(Integer div, String stdt, String eddt);
	
}

