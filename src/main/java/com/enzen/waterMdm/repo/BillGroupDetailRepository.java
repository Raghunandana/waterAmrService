package com.enzen.waterMdm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enzen.waterMdm.model.BillGroup;
import com.enzen.waterMdm.model.BillGroupDetail;
import com.enzen.waterMdm.model.BillingReportMaster;

public interface BillGroupDetailRepository extends CrudRepository<BillGroupDetail, Long>{
	
	@Query("SELECT b FROM BillGroupDetail b where b.billMonth = ?1 AND b.billYear = ?2 AND b.divisionId = ?3 AND b.authorize = ?4")
    public List<BillGroupDetail> findByBillMonthAndBillYearAndDivisionIdAndAuthorize(Integer billMonth, Integer billYear, Integer divisionId, boolean authorize);
	
}
