package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Long> {

	Notification findById(Integer id);
	
	String notificationlistSql = "select n.id, n.event, n.message, n.unit_id, n.status, n.cr_date\r\n" + 
			"from notification n\r\n" + 
			"inner join unit_summary u on n.unit_id = u.unitid\r\n" + 
			"left join employee e on e.subdivisionid = u.subdivisionid\r\n" + 
			"inner join employeeloginmapping em on em.employeeid = e.employeeid\r\n" + 
			"where em.loginname = :userid\r\n" ;
			
	@Query(value = notificationlistSql, nativeQuery = true)
	public List<Object> getNotificationList(String userid);
	
	
}

