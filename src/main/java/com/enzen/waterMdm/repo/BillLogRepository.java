package com.enzen.waterMdm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enzen.waterMdm.model.BillGroup;
import com.enzen.waterMdm.model.BillLog;
import com.enzen.waterMdm.model.BillingReportMaster;

public interface BillLogRepository extends CrudRepository<BillLog, Long>{
	

	
}
