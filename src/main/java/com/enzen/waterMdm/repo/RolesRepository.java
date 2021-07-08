package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.Roles;

public interface RolesRepository extends CrudRepository<Roles, Long> {
	
	
	public Roles findByRoleId(Integer roleId);

	String roleListSql = "select r.roleid, r. rolename , r.sysrole, r.active , r.description  \r\n" + 
			"from roles r where roleid = :roleid\r\n" + 
			"order by 1;";
	@Query(value = roleListSql, nativeQuery = true)	
	public List<Object> getRoleDataList(Integer roleid);
	
	
	


	
}

