package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long> {

	public List<Menu> findAllByOrderByMenuOrderAsc();
	
}

