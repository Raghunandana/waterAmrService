package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.MessageAccepted;

public interface MessageAcceptedRepository extends CrudRepository<MessageAccepted, Long> {
	
	String monthDataSql = "SELECT id, message, sentby, sentdate, smsheader, senttime "
			+ "from message_accepted "
			+ "where sentdate > now() + interval '3 months ago'";
	@Query(value = monthDataSql, nativeQuery = true)
	public List<Object> getLast3MonthData();
	
	public void deleteById(Integer id);
}

