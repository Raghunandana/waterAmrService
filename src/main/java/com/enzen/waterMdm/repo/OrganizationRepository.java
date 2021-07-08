package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.Organization;
import com.enzen.waterMdm.model.UnitSummary;
import com.enzen.waterMdm.model.MasterValue;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {

	String orgListSql = "select o.orgId, o.code, o.orgName, m.desc, o.isDeleted\r\n" + 
			"from Organization o\r\n" + 
			"left join MasterValue m on m.id = o.orgTypeId\r\n" + 
			// "where o.isDeleted = false\r\n" + 
			"order by 2";
	@Query(value = orgListSql)
	public List<Object> getOrgList();
	
	String orgTypeListSql = "select distinct  o.orgTypeId, m.desc\r\n" + 
			"from Organization o\r\n" + 
			"left join MasterValue m on m.id = o.orgTypeId\r\n" + 
			// "where o.isDeleted = false\r\n" + 
			"order by 2";
	@Query(value = orgTypeListSql)
	public List<Object> getOrgTypeList();
	
	public Organization findByOrgId(Integer orgId);
	
	public List<Organization> findByParentId(Integer parentId);
	
}

