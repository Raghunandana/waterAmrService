package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.MessageData;

public interface MessageDataRepository extends CrudRepository<MessageData, Long> {

	String lastMonthSql = "select id, unitid, hourlydataoflast24hrs, cumconsumpofprevday, cumconsumpofprevtoprevday,\r\n" + 
			"tamperstatus, batteryvoltage, temperature, last24hrsreversepulsecount, date, time\r\n" + 
			"from message_data\r\n" + 
			"where cast(date + interval '1 month' as date) <= cast(now() as date)";
	@Query(value = lastMonthSql, nativeQuery = true)
	public List<Object> getLastMonthData();
	
	public void deleteById(Integer id);
	
	String monthDataSql = "select id, unitid, hourlydataoflast24hrs, cumconsumpofprevday, cumconsumpofprevtoprevday,\r\n" + 
			"tamperstatus, batteryvoltage, temperature, last24hrsreversepulsecount, date, time\r\n" + 
			"from message_data\r\n" + 
			"where date > now() + interval '3 months ago'";
	@Query(value = monthDataSql, nativeQuery = true)
	public List<Object> getLast3MonthData();
	
}

