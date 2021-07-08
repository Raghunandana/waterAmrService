package com.enzen.waterMdm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enzen.waterMdm.model.CounterData;

public interface CounterDataRepository extends CrudRepository<CounterData, Long>{
	

	public CounterData findById(Integer id);
}
