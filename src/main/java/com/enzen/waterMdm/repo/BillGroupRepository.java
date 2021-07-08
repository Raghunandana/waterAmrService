package com.enzen.waterMdm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enzen.waterMdm.model.BillGroup;
import com.enzen.waterMdm.model.BillingReportMaster;
import com.enzen.waterMdm.model.TariffMaster;

public interface BillGroupRepository extends CrudRepository<BillGroup, Long>{
	

	@Query("SELECT b FROM BillGroup b where b.code = ?1")
    public List<BillGroup> findByCode(String code);
	
	
}
