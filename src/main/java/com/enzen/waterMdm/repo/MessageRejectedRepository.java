package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.MessageRejected;

public interface MessageRejectedRepository extends CrudRepository<MessageRejected, Long> {

	String lastMonthSql = "select id, message, sentby, sentdate, senttime, smsheader, rejectreason, unitid\r\n" + 
			"from message_rejected\r\n" + 
			"where cast(sentdate + interval '1 month' as date) <= cast(now() as date)";
	@Query(value = lastMonthSql, nativeQuery = true)
	public List<Object> getLastMonthData();
	
	public void deleteById(Integer id);
	
	String monthDataSql = "SELECT id, message, sentby, sentdate, senttime, smsheader, rejectreason, unitid "
			+ "from message_rejected "
			+ "where sentdate > now() + interval '3 months ago'";
	@Query(value = monthDataSql, nativeQuery = true)
	public List<Object> getLast3MonthData();
	
}

