package com.enzen.waterMdm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enzen.waterMdm.model.BillGroup;
import com.enzen.waterMdm.model.BillGroupDetail;
import com.enzen.waterMdm.model.BillLog;
import com.enzen.waterMdm.model.BillingReportMaster;
import com.enzen.waterMdm.model.PaymentDetails;
import com.enzen.waterMdm.model.PaymentProcess;

public interface PaymentProcessRepository extends CrudRepository<PaymentProcess, Long>{
	

	String paymentDetailsSql = "select b.id, b.totalamount, coalesce(pd.amount,0) paid, b.totalamount-coalesce(pd.amount,0) balance, \r\n" + 
			"case when b.totalamount-coalesce(pd.amount,0) < 0 then true else false end as overpay\r\n" + 
			"from bills b \r\n" + 
			"left join paymentdetails pd on b.id = pd.billno\r\n" + 
			"where b.addedat >= date_trunc('month', CURRENT_DATE) - interval '1 month'";       
	@Query(value = paymentDetailsSql, nativeQuery = true)
	public List<Object> getPaymentDetails();
	
	String paymentDetailsScdSql = "select b.id, b.totalamount, coalesce(pd.amount,0) paid, b.totalamount-coalesce(pd.amount,0) balance, \r\n" + 
			"case when b.totalamount-coalesce(pd.amount,0) < 0 then true else false end as overpay\r\n" + 
			"from bills b \r\n" + 
			"left join paymentdetails pd on b.id = pd.billno\r\n" + 
			"where b.addedat >= date_trunc('month', CURRENT_DATE) - interval '1 month' and b.addedat < date_trunc('month', CURRENT_DATE)  - interval '1 day'";       
	@Query(value = paymentDetailsScdSql, nativeQuery = true)
	public List<Object> getPaymentDetailsScd();
	
	@Query("SELECT p FROM PaymentProcess p where p.billNo = ?1")
    public PaymentProcess findByBillNo(Integer billNo);
}
