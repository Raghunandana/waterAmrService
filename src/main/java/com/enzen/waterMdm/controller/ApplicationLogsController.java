package com.enzen.waterMdm.controller;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.enzen.waterMdm.model.ApplicationLogs;
import com.enzen.waterMdm.repo.ApplicationLogsRepository;

@RestController
@RequestMapping("/log")
public class ApplicationLogsController {
	
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@Autowired
	ApplicationLogsRepository alr;
	
	@PostMapping("/writeLog")
    public String writeLog(String name, String description, ApplicationLogsRepository alr) {
        try {
        	ApplicationLogs al = new ApplicationLogs();
			al.setDescription(description);
			al.setName(name);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	    	Date date = new Date();
	    	al.setCreatedDate(dateFormat.parse(dateFormat.format(date)));
	    	alr.save(al);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return "";
	}
	
}
