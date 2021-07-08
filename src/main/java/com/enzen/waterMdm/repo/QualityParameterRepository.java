package com.enzen.waterMdm.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.enzen.waterMdm.model.QualityParameter;

public interface QualityParameterRepository extends CrudRepository<QualityParameter, Long>{
	

	public QualityParameter findById(Integer id);
}
