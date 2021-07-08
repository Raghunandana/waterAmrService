package com.enzen.waterMdm.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestBody;

import com.enzen.waterMdm.model.TempMessageData;

public interface TempMessageDataRepository extends CrudRepository<TempMessageData, Long> {
	
	public void deleteById(Integer id);
	
}

