package com.enzen.waterMdm.controller;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.enzen.waterMdm.model.LoginMaster;
import com.enzen.waterMdm.model.Notification;
import com.enzen.waterMdm.model.Organization;
import com.enzen.waterMdm.model.Roles;
import com.enzen.waterMdm.model.UnitSummary;
import com.enzen.waterMdm.repo.LoginMasterRepository;
import com.enzen.waterMdm.repo.NotificationRepository;
import com.enzen.waterMdm.repo.OrganizationRepository;
import com.enzen.waterMdm.repo.RolesRepository;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	LoginMasterRepository lmr;
	
	@Autowired
	private HttpServletRequest context;
	
	@Autowired
	OrganizationRepository or;
	
	@Autowired
	RolesRepository rr;
	
	@Autowired
	NotificationRepository nr;
	
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getDivRealTimeData/{divid}")
    public String getDivRealTimeData(@PathVariable Integer divid) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getDivRealTimeData");
        
        try {
        	if(divid!=null) {
        		ob = lmr.getRealTimeData(divid);
        		if(ob.size()>0) {
	        		Iterator itr = ob.iterator();
	    			while(itr.hasNext()){
	    				JSONObject json = new JSONObject();
	    			    Object[] obj2 = (Object[]) itr.next();
		    			if(obj2[0]!=null)
		    				json.put("unitid", String.valueOf(obj2[0]));
		    			else
		    				json.put("unitid", "");
		    			if(obj2[1]!=null)
		    				json.put("meterno", String.valueOf(obj2[1]));
		    			else
		    				json.put("meterno", "");
		    			if(obj2[2]!=null)
		    				json.put("division", String.valueOf(obj2[2]));
		    			else
		    				json.put("division", "");
		    			if(obj2[3]!=null)
		    				json.put("subdivision", String.valueOf(obj2[3]));
		    			else
		    				json.put("subdivision", "");
		    			if(obj2[4]!=null)
		    				json.put("cur", String.valueOf(obj2[4]));
		    			else
		    				json.put("cur", "");
		    			if(obj2[5]!=null)
		    				json.put("prev", String.valueOf(obj2[5]));
		    			else
		    				json.put("prev", "");
		    			if(obj2[6]!=null)
		    				json.put("consumption", String.valueOf(obj2[6]));
		    			else
		    				json.put("consumption", "");
		    			if(obj2[7]!=null)
		    				json.put("recived", String.valueOf(obj2[7]));
		    			else
		    				json.put("recived", "");
		    			if(obj2[8]!=null)
		    				json.put("battery", String.valueOf(obj2[8]));
		    			else
		    				json.put("battery", "");
		    			ja.add(json);
	    			}
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getSubDivRealTimeData/{subdivid}")
    public String getSubDivRealTimeData(@PathVariable Integer subdivid) throws java.text.ParseException{
        JSONObject json = new JSONObject();
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        log.debug("Inside get getSubDivRealTimeData");
        
        try {
        	if(subdivid!=null) {
        		ob = lmr.getSubDivRealTimeData(subdivid);
        		if(ob.size()>0) {
	        		Iterator itr = ob.iterator();
	    			while(itr.hasNext()){
	    			    Object[] obj2 = (Object[]) itr.next();
		    			if(obj2[0]!=null)
		    				json.put("unitid", String.valueOf(obj2[0]));
		    			else
		    				json.put("unitid", "");
		    			if(obj2[1]!=null)
		    				json.put("meterno", String.valueOf(obj2[1]));
		    			else
		    				json.put("meterno", "");
		    			if(obj2[2]!=null)
		    				json.put("division", String.valueOf(obj2[2]));
		    			else
		    				json.put("division", "");
		    			if(obj2[3]!=null)
		    				json.put("subdivision", String.valueOf(obj2[3]));
		    			else
		    				json.put("subdivision", "");
		    			if(obj2[5]!=null)
		    				json.put("location", String.valueOf(obj2[5]).trim());
		    			else
		    				json.put("location", "");
		    			if(obj2[5]!=null)
		    				json.put("consname", String.valueOf(obj2[5]).trim());
		    			else
		    				json.put("consname", "");
		    			if(obj2[6]!=null)
		    				json.put("consumption", String.valueOf(obj2[6]));
		    			else
		    				json.put("consumption", "");
		    			if(obj2[7]!=null)
		    				json.put("recived", String.valueOf(obj2[7]));
		    			else
		    				json.put("recived", "");
		    			if(obj2[8]!=null)
		    				json.put("oem", String.valueOf(obj2[8]));
		    			else
		    				json.put("oem", "");
		    			if(obj2[9]!=null)
		    				json.put("bore", String.valueOf(obj2[9]));
		    			else
		    				json.put("bore", "");
		    			if(obj2[10]!=null)
		    				json.put("battery", String.valueOf(obj2[10]));
		    			else
		    				json.put("battery", "");
	    			}
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", json);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getHistoricalData/{unitid}")
    public String getHistoricaleData(@PathVariable String unitid) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getHistoricalData");
        
        try {
        	if(unitid!=null) {
        		ob = lmr.getHistoricalData(unitid);
        		if(ob.size()>0) {
	        		Iterator itr = ob.iterator();
	    			while(itr.hasNext()){
	    				JSONObject json = new JSONObject();
	    			    Object[] obj2 = (Object[]) itr.next();
		    			if(obj2[0]!=null)
		    				json.put("oem", String.valueOf(obj2[0]));
		    			else
		    				json.put("oem", "");
		    			if(obj2[1]!=null)
		    				json.put("date", String.valueOf(obj2[1]));
		    			else
		    				json.put("date", "");
		    			if(obj2[2]!=null)
		    				json.put("time", String.valueOf(obj2[2]));
		    			else
		    				json.put("time", "");
		    			if(obj2[3]!=null)
		    				json.put("battery", String.valueOf(obj2[3]));
		    			else
		    				json.put("battery", "");
		    			if(obj2[4]!=null)
		    				json.put("reading", String.valueOf(obj2[4]));
		    			else
		    				json.put("reading", "");
		    			if(obj2[5]!=null)
		    				json.put("tamper", String.valueOf(obj2[5]));
		    			else
		    				json.put("tamper", "");
		    			if(obj2[6]!=null)
		    				json.put("unitid", String.valueOf(obj2[6]));
		    			else
		    				json.put("unitid", "");
		    			if(obj2[8]!=null)
		    				json.put("cur", String.valueOf(obj2[8]));
		    			else
		    				json.put("cur", "");
		    			if(obj2[9]!=null)
		    				json.put("prev", String.valueOf(obj2[9]));
		    			else
		    				json.put("prev", "");
		    			
		    			String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
		    	        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		    	        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		    	        SimpleDateFormat isoFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		    	        isoFormat.setTimeZone(TimeZone.getTimeZone("GMT+4"));
		    	        Date dt = isoFormat.parse(String.valueOf(obj2[1]) +" "+String.valueOf(obj2[2]));
		    	        //json.put("servertime", sdf.format(dt));
		    	        json.put("servertime", String.valueOf(obj2[1]) +" "+String.valueOf(obj2[2]));
		    	        
		    			ja.add(json);
	    			}
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getHistoricalDataNew/{unitid}/{duration}")
    public String getHistoricaleDataNew(@PathVariable String unitid, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getHistoricalData");
        
        try {
        	if(unitid!=null) {
        		if("day".equalsIgnoreCase(duration)) {
        			ob = lmr.getDayHistoryData(duration, unitid);
        		}
        		else if("week".equalsIgnoreCase(duration)) {
        			ob = lmr.getWeekHistoryData(duration, unitid);
        		}
        		else if("month".equalsIgnoreCase(duration)) {
        			ob = lmr.getWeekHistoryData(duration, unitid);
        		}
				else if("year".equalsIgnoreCase(duration)) {
					ob = lmr.getYearHistoryData(duration, unitid);
				}
        		
        		if(ob.size()>0) {
	        		Iterator itr = ob.iterator();
	    			while(itr.hasNext()){
	    				JSONObject json = new JSONObject();
	    			    Object[] obj2 = (Object[]) itr.next();
	    			    if(obj2[0]!=null)
		    				json.put("unitid", String.valueOf(obj2[0]));
		    			else
		    				json.put("unitid", "");
		    			if(obj2[1]!=null)
		    				json.put("c1", String.valueOf(obj2[1])+"");
		    			else
		    				json.put("c1", "");
		    			if(obj2[2]!=null)
		    				json.put("c2", String.valueOf(obj2[2]));
		    			else
		    				json.put("c2", "");
		    			if(obj2[3]!=null)
		    				json.put("reading", String.valueOf(obj2[3]));
		    			else
		    				json.put("reading", "");
		    			
		    			ja.add(json);
	    			}
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getCategoryConsumptionDatas/{duration}")
    public String getDailyConsumptionData(@PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getCategoryConsumptionData");
        Integer val=1;
        try {
        	if("quarter".equalsIgnoreCase(duration))
        		val = 90;
        	else if("day".equalsIgnoreCase(duration))
        		val = 1;
        	else if("month".equalsIgnoreCase(duration))
        		val = 30;
        	else if("year".equalsIgnoreCase(duration))
        		val = 365;
    		ob = lmr.getCategoryWiseConsumptionData(val);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("type", String.valueOf(obj2[0]));
	    			else
	    				json.put("type", "");
	    			if(obj2[1]!=null)
	    				json.put("val", String.valueOf(obj2[1]));
	    			else
	    				json.put("val", "");
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getDivisionConsumptionDatas/{duration}")
    public String getDivisionConsumptionData(@PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getDivisionConsumptionData");
        
        try {
        	Integer val=1;
        	if("quarter".equalsIgnoreCase(duration))
        		val = 90;
        	else if("day".equalsIgnoreCase(duration))
        		val = 1;
        	else if("month".equalsIgnoreCase(duration))
        		val = 30;
        	else if("year".equalsIgnoreCase(duration))
        		val = 365;
    		ob = lmr.getDivisionWiseConsumptionData(val);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("type", String.valueOf(obj2[0]));
	    			else
	    				json.put("type", "");
	    			if(obj2[1]!=null)
	    				json.put("val", String.valueOf(obj2[1]));
	    			else
	    				json.put("val", "");
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getComplaintStatus/{duration}")
    public String getComplaintStatus(@PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getComplaintStatus");
        
        try {
    		ob = lmr.getComplaintStatus(duration);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("type", String.valueOf(obj2[0]));
	    			else
	    				json.put("type", "");
	    			if(obj2[1]!=null)
	    				json.put("val", String.valueOf(obj2[1]));
	    			else
	    				json.put("val", "");
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getNotification/{userid}")
    public String getNotification(@PathVariable String userid) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getNotification");
        
        try {
    		ob = nr.getNotificationList(userid);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("id", String.valueOf(obj2[0]));
	    			else
	    				json.put("id", "");
	    			if(obj2[1]!=null)
	    				json.put("event", String.valueOf(obj2[1]));
	    			else
	    				json.put("event", "");
	    			if(obj2[2]!=null)
	    				json.put("message", String.valueOf(obj2[2]));
	    			else
	    				json.put("message", "");
	    			if(obj2[3]!=null)
	    				json.put("unit_id", String.valueOf(obj2[3]));
	    			else
	    				json.put("unit_id", "");
	    			if(obj2[4]!=null)
	    				json.put("status", String.valueOf(obj2[4]));
	    			else
	    				json.put("status", "");
	    			if(obj2[5]!=null)
	    				json.put("date", String.valueOf(obj2[5]));
	    			else
	    				json.put("date", "");
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/upateNotification/{id}")
    public String upateNotification(@PathVariable Integer id) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside upateNotification");
        Notification n = new Notification();
        
        try {
    		n = nr.findById(id);
    		if(n!=null) {
        		n.setStatus(true);
        		n = nr.save(n) ;
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getCoordinates")
    public String getCoordinates() throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getCoordinates");
        
        try {
    		ob = lmr.getCoordinates();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("id", String.valueOf(obj2[0]));
	    			else
	    				json.put("id", "");
	    			if(obj2[1]!=null)
	    				json.put("lon", String.valueOf(obj2[1]));
	    			else
	    				json.put("lon", "");
	    			if(obj2[2]!=null)
	    				json.put("lat", String.valueOf(obj2[2]));
	    			else
	    				json.put("lat", "");
	    			if(obj2[3]!=null)
	    				json.put("name", String.valueOf(obj2[3]));
	    			else
	    				json.put("name", "");
	    			if(obj2[4]!=null)
	    				json.put("type", String.valueOf(obj2[4]));
	    			else
	    				json.put("type", "");
	    			if(obj2[5]!=null)
	    				json.put("slno", String.valueOf(obj2[5]));
	    			else
	    				json.put("slno", "");
	    			if(obj2[6]!=null)
	    				json.put("org", String.valueOf(obj2[6]));
	    			else
	    				json.put("org", "");
	    			if(obj2[7]!=null)
	    				json.put("cat", String.valueOf(obj2[7]));
	    			else
	    				json.put("cat", "");
	    			if(obj2[8]!=null)
	    				json.put("cons", String.valueOf(obj2[8]));
	    			else
	    				json.put("cons", "");
	    			if(obj2[9]!=null)
	    				json.put("dat", String.valueOf(obj2[9]));
	    			else
	    				json.put("dat", "");
	    			if(obj2[10]!=null)
	    				json.put("bat", String.valueOf(obj2[10]));
	    			else
	    				json.put("bat", "");
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getCommDatas/{duration}")
    public String getCommData(@PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getCommData");
        
        try {
        	//duration = "1 day";
        	if("quarter".equalsIgnoreCase(duration))
        		duration = "3 month";
        	else
        		duration = "1 "+duration;
    		ob = lmr.getCommData(duration);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("type", String.valueOf(obj2[0]));
	    			else
	    				json.put("type", "");
	    			if(obj2[1]!=null)
	    				json.put("val", String.valueOf(obj2[1]));
	    			else
	    				json.put("val", "");
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getAvGConsDatas/{duration}")
    public String getAvGConsData(@PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getAvGConsData");
        
        try {
        	if("quarter".equalsIgnoreCase(duration))
        		duration = "3 month";
        	else
        		duration = "1 "+duration;
    		ob = lmr.getAvGConsData(duration);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null) {
	    				json.put("type", "Yesterday");
	    				json.put("val", String.valueOf(obj2[0]));
	    			}
	    			else {
	    				json.put("type", "Yesterday");
	    				json.put("val", "0");
	    			}
	    			ja.add(json);
	    			json = new JSONObject();
	    			if(obj2[1]!=null) {
	    				json.put("type", "Today");
	    				json.put("val", String.valueOf(obj2[1]));
	    			}
	    			else {
	    				json.put("type", "Today");
	    				json.put("val", "0");
	    			}
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getGprsData/{readDate}")
    public String getGprsData(@PathVariable String readDate) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getGprsData");
        
        try {
        	if(readDate!=null) {
        		ob = lmr.getGprsData(readDate);
        		if(ob.size()>0) {
	        		Iterator itr = ob.iterator();
	    			while(itr.hasNext()){
	    				JSONObject json = new JSONObject();
	    			    Object[] obj2 = (Object[]) itr.next();
		    			/*if(obj2[0]!=null)
		    				json.put("id", String.valueOf(obj2[0]));
		    			else
		    				json.put("id", "");*/
		    			if(obj2[1]!=null)
		    				json.put("div", String.valueOf(obj2[1]));
		    			else
		    				json.put("div", "");
		    			if(obj2[2]!=null)
		    				json.put("subdiv", String.valueOf(obj2[2]));
		    			else
		    				json.put("subdiv", "");
		    			if(obj2[3]!=null)
		    				json.put("station", String.valueOf(obj2[3]));
		    			else
		    				json.put("station", "");
		    			if(obj2[4]!=null)
		    				json.put("loc", String.valueOf(obj2[4]));
		    			else
		    				json.put("loc", "");
		    			if(obj2[5]!=null)
		    				json.put("oem", String.valueOf(obj2[5]));
		    			else
		    				json.put("oem", "");
		    			/*if(obj2[6]!=null)
		    				json.put("pipe", String.valueOf(obj2[6]));
		    			else
		    				json.put("pipe", "");*/
		    			if(obj2[7]!=null)
		    				json.put("cap", String.valueOf(obj2[7]));
		    			else
		    				json.put("cap", "");
		    			if(obj2[8]!=null)
		    				json.put("dt", String.valueOf(obj2[8]));
		    			else
		    				json.put("dt", "");
		    			if(obj2[9]!=null)
		    				json.put("flow", String.valueOf(obj2[9]));
		    			else
		    				json.put("flow", "");
		    			/*if(obj2[10]!=null)
		    				json.put("total1", String.valueOf(obj2[10]));
		    			else
		    				json.put("total1", "");
		    			if(obj2[11]!=null)
		    				json.put("total2", String.valueOf(obj2[11]));
		    			else
		    				json.put("total2", "");*/
		    			if(obj2[12]!=null)
		    				json.put("unitid", String.valueOf(obj2[12]));
		    			else
		    				json.put("unitid", "");
		    			/*if(obj2[13]!=null)
		    				json.put("aip1", String.valueOf(obj2[13]));
		    			else
		    				json.put("aip1", "");
		    			if(obj2[14]!=null)
		    				json.put("aip2", String.valueOf(obj2[14]));
		    			else
		    				json.put("aip2", "");
		    			if(obj2[15]!=null)
		    				json.put("total3", String.valueOf(obj2[15]));
		    			else
		    				json.put("total3", "");
		    			if(obj2[16]!=null)
		    				json.put("txpt", String.valueOf(obj2[16]));
		    			else
		    				json.put("txpt", "");
		    			if(obj2[17]!=null)
		    				json.put("mtrsl", String.valueOf(obj2[17]));
		    			else
		    				json.put("mtrsl", "");*/
		    	        
		    			ja.add(json);
	    			}
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/getUnitReportData")
    public String getUnitReportData(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject jo = new JSONObject();
        JSONObject data = new JSONObject();
        UnitSummary us = new UnitSummary();
        
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getUnitReportData");
        System.out.println("Inside get getUnitReportData");
        try {
        	if(stringToParse!=null) {
        		data = (JSONObject) parser.parse(stringToParse);
        		String mtrType = "";
        		String oem = "";
        		String div = "";
        		String subdiv = "";
        		String stn = "";
        		String loc = "";
        		String cons = "";
        		String fromdt = "";
        		String todt = "";
        		if(data.get("mtrType")!=null)
        			mtrType = data.get("mtrType").toString();
        		if(data.get("oem")!=null)
        			oem = data.get("oem").toString();
        		if(data.get("div")!=null)
        			div = data.get("div").toString();
        		if(data.get("subdiv")!=null)
        			subdiv = data.get("subdiv").toString();
        		if(data.get("stn")!=null)
        			stn = data.get("stn").toString();
        		if(data.get("loc")!=null)
        			loc = data.get("loc").toString();
        		if(data.get("cons")!=null)
        			cons = data.get("cons").toString();
        		if(data.get("fromdt")!=null)
        			fromdt = data.get("fromdt").toString();
        		if(data.get("todt")!=null)
        			todt = data.get("todt").toString();
        		
        		ob = lmr.getUnitData(mtrType,oem,div,subdiv,stn,loc,cons,fromdt,todt);
        		
        		if(ob.size()>0) {
	        		Iterator itr = ob.iterator();
	    			while(itr.hasNext()){
	    				JSONObject json = new JSONObject();
	    			    Object[] obj2 = (Object[]) itr.next();
		    			if(obj2[0]!=null)
		    				json.put("id", String.valueOf(obj2[0]));
		    			else
		    				json.put("id", "");
		    			if(obj2[1]!=null)
		    				json.put("cons", String.valueOf(obj2[1]));
		    			else
		    				json.put("cons", "");
		    			if(obj2[2]!=null)
		    				json.put("div", String.valueOf(obj2[2]));
		    			else
		    				json.put("div", "");
		    			if(obj2[3]!=null)
		    				json.put("subdiv", String.valueOf(obj2[3]));
		    			else
		    				json.put("subdiv", "");
		    			if(obj2[4]!=null)
		    				json.put("stn", String.valueOf(obj2[4]));
		    			else
		    				json.put("stn", "");
		    			if(obj2[5]!=null)
		    				json.put("loc", String.valueOf(obj2[5]));
		    			else
		    				json.put("loc", "");
		    			if(obj2[6]!=null)
		    				json.put("consp", String.valueOf(obj2[6]));
		    			else
		    				json.put("consp", "");
		    			if(obj2[7]!=null)
		    				json.put("daily", String.valueOf(obj2[7]));
		    			else
		    				json.put("daily", "");
		    			if(obj2[8]!=null)
		    				json.put("dt", String.valueOf(obj2[8]));
		    			else
		    				json.put("dt", "");
		    			if(obj2[9]!=null)
		    				json.put("oem", String.valueOf(obj2[9]));
		    			else
		    				json.put("oem", "");
		    			if(obj2[10]!=null)
		    				json.put("unitid", String.valueOf(obj2[10]));
		    			else
		    				json.put("unitid", "");
		    			if(obj2[11]!=null)
		    				json.put("installon", String.valueOf(obj2[11]));
		    			else
		    				json.put("installon", "");
		    			if(obj2[12]!=null)
		    				json.put("volt", String.valueOf(obj2[12]));
		    			else
		    				json.put("volt", "");
		    	        
		    			ja.add(json);
	    			}
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getDivConsumption/{div}/{duration}")
    public String getDivConsumption(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getDivConsumption");
        ArrayList<String> dom = new ArrayList<>();
        ArrayList<String> ndom = new ArrayList<>();
        ArrayList<String> ind = new ArrayList<>();
        ArrayList<String> l = new ArrayList<>();
        JSONObject json = new JSONObject();
        int i=0;
        try {
        	Integer val=1;
        	if("quarter".equalsIgnoreCase(duration))
        		val = 90;
        	else if("day".equalsIgnoreCase(duration))
        		val = 1;
        	else if("month".equalsIgnoreCase(duration))
        		val = 30;
        	else if("year".equalsIgnoreCase(duration))
        		val = 365;
        	String[] dt = duration.split(",");
    		ob = lmr.getDivWiseConsumptionData(dt[0], dt[1], div);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				ArrayList<String> list = l;
    			    Object[] obj2 = (Object[]) itr.next();
    			    dom = new ArrayList<>();
    			    dom.add(String.valueOf(obj2[1]));
    			    dom.add(String.valueOf(obj2[2]));
    			    dom.add(String.valueOf(obj2[3]));
    			    json = new JSONObject();
    		    	json.put("label", String.valueOf(obj2[0]));
    		    	json.put("data", dom);
    		    	ja.add(json);
    			}
    		}
    		json = new JSONObject();
    		json.put("div", ja);
    		List<String> uniqueList = new ArrayList<String>();
    		uniqueList.add(0,"Domestic");
    		uniqueList.add(1,"Non Domestic");
    		uniqueList.add(2,"Industrial");
	    	json.put("val", uniqueList);
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", json);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getDivConsDetail/{div}/{duration}")
    public String getDivConsDetail(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getDivConsDetail");
        JSONObject json = new JSONObject();
        int i=0;
        try {
        	Integer val=1;
        	if("quarter".equalsIgnoreCase(duration))
        		val = 90;
        	else if("day".equalsIgnoreCase(duration))
        		val = 1;
        	else if("month".equalsIgnoreCase(duration))
        		val = 30;
        	else if("year".equalsIgnoreCase(duration))
        		val = 365;
        	String[] dt = duration.split(",");
    		ob = lmr.getDivWiseConsDetData(dt[0], dt[1], div);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("org", String.valueOf(obj2[0]));
	    			else
	    				json.put("org", "");
	    			if(obj2[1]!=null)
	    				json.put("unitid", String.valueOf(obj2[1]));
	    			else
	    				json.put("unitid", "");
	    			if(obj2[2]!=null)
	    				json.put("cat", String.valueOf(obj2[2]));
	    			else
	    				json.put("cat", "");
	    			if(obj2[3]!=null)
	    				json.put("cons", String.valueOf(obj2[3]));
	    			else
	    				json.put("cons", "0");
	    			
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	obj.put("issuccess", false);
        	e.printStackTrace();
        }
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getSubDivConsDetail/{subdiv}/{duration}")
    public String getSubDivConsDetail(@PathVariable String subdiv, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getDivConsDetail");
        JSONObject json = new JSONObject();
        int i=0;
        try {
        	Integer val=1;
        	if("quarter".equalsIgnoreCase(duration))
        		val = 90;
        	else if("day".equalsIgnoreCase(duration))
        		val = 1;
        	else if("month".equalsIgnoreCase(duration))
        		val = 30;
        	else if("year".equalsIgnoreCase(duration))
        		val = 365;
        	String[] dt = duration.split(",");
    		ob = lmr.getSubDivWiseConsDetData(dt[0], dt[1], subdiv);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("org", String.valueOf(obj2[0]));
	    			else
	    				json.put("org", "");
	    			if(obj2[1]!=null)
	    				json.put("unitid", String.valueOf(obj2[1]));
	    			else
	    				json.put("unitid", "");
	    			if(obj2[2]!=null)
	    				json.put("cat", String.valueOf(obj2[2]));
	    			else
	    				json.put("cat", "");
	    			if(obj2[3]!=null)
	    				json.put("cons", String.valueOf(obj2[3]));
	    			else
	    				json.put("cons", "0");
	    			
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	obj.put("issuccess", false);
        	e.printStackTrace();
        }
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getDmaConsDetail/{dma}/{duration}")
    public String getDmaConsDetail(@PathVariable String dma, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getDivConsDetail");
        JSONObject json = new JSONObject();
        int i=0;
        try {
        	Integer val=1;
        	if("quarter".equalsIgnoreCase(duration))
        		val = 90;
        	else if("day".equalsIgnoreCase(duration))
        		val = 1;
        	else if("month".equalsIgnoreCase(duration))
        		val = 30;
        	else if("year".equalsIgnoreCase(duration))
        		val = 365;
        	String[] dt = duration.split(",");
    		ob = lmr.getDmaWiseConsDetData(dt[0], dt[1], dma);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("org", String.valueOf(obj2[0]));
	    			else
	    				json.put("org", "");
	    			if(obj2[1]!=null)
	    				json.put("unitid", String.valueOf(obj2[1]));
	    			else
	    				json.put("unitid", "");
	    			if(obj2[2]!=null)
	    				json.put("cat", String.valueOf(obj2[2]));
	    			else
	    				json.put("cat", "");
	    			if(obj2[3]!=null)
	    				json.put("cons", String.valueOf(obj2[3]));
	    			else
	    				json.put("cons", "0");
	    			
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	obj.put("issuccess", false);
        	e.printStackTrace();
        }
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getActiveConsumerCount/{div}/{duration}")
    public String getActiveConsumerCount(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getActiveConsumerCount");
        ArrayList<String> dom = new ArrayList<>();
        ArrayList<String> ndom = new ArrayList<>();
        ArrayList<String> ind = new ArrayList<>();
        ArrayList<String> l = new ArrayList<>();
        String label = "";
        JSONObject json = new JSONObject();
        int i=0;
        try {
        	Integer val=1;
        	if("quarter".equalsIgnoreCase(duration))
        		val = 90;
        	else if("day".equalsIgnoreCase(duration))
        		val = 1;
        	else if("month".equalsIgnoreCase(duration))
        		val = 30;
        	else if("year".equalsIgnoreCase(duration))
        		val = 365;
    		ob = lmr.getDivWiseConsumerData(div);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				ArrayList<String> list = l;
    			    Object[] obj2 = (Object[]) itr.next();
    			    if("Domestic".equalsIgnoreCase(String.valueOf(obj2[1]))){
    			    	dom.add(String.valueOf(obj2[2]));
    			    }
    			    else if("Non Domestic".equalsIgnoreCase(String.valueOf(obj2[1]))){
    			    	ndom.add(String.valueOf(obj2[2]));
    			    }
    			    else if("Industrial".equalsIgnoreCase(String.valueOf(obj2[1]))){
    			    	ind.add(String.valueOf(obj2[2]));
    			    }
    			    l.add(i++, String.valueOf(obj2[0]));
    			}
    		}
    		json = new JSONObject();
	    	json.put("label", "Domestic");
	    	json.put("data", dom);
	    	ja.add(json);
	    	json = new JSONObject();
	    	json.put("label", "Non Domestic");
	    	json.put("data", ndom);
	    	ja.add(json);
	    	json = new JSONObject();
	    	json.put("label", "Industrial");
	    	json.put("data", ind);
	    	ja.add(json);
	    	
    		List<String> uniqueList = new ArrayList<String>(new LinkedHashSet<String>(l));
    		json = new JSONObject();
    		json.put("div", ja);
	    	json.put("val", uniqueList);
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", json);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getActiveConsCt/{div}")
    public String getActiveConsCt(@PathVariable Integer div) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getActiveConsCt");
        ArrayList<String> l = new ArrayList<>();
        String label = "";
        JSONObject json = new JSONObject();
        int i=0;
        try {
    		ob = lmr.getDivActConsumerData(div);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("active", String.valueOf(obj2[0]));
	    			else
	    				json.put("active", "0");
	    			if(obj2[1]!=null)
	    				json.put("inactive", String.valueOf(obj2[1]));
	    			else
	    				json.put("inactive", "0");
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	obj.put("issuccess", false);
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@GetMapping("/getActiveConsDt/{div}")
	public String getActiveConsDt(@PathVariable Integer div) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getActiveConsDt");
        ArrayList<String> l = new ArrayList<>();
        String label = "";
        JSONObject json = new JSONObject();
        int i=0;
        try {
    		ob = lmr.getDivActConsDet(div);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("org", String.valueOf(obj2[0]));
	    			else
	    				json.put("org", "");
	    			if(obj2[1]!=null)
	    				json.put("unitid", String.valueOf(obj2[1]));
	    			else
	    				json.put("unitid", "");
	    			if(obj2[2]!=null)
	    				json.put("category", String.valueOf(obj2[2]));
	    			else
	    				json.put("category", "");
	    			if(obj2[3]!=null) {
	    				if("true".equalsIgnoreCase(String.valueOf(obj2[3])))
	    					json.put("status", "Active");
	    				else
	    					json.put("status", "InActive");
	    			}
	    			else
	    				json.put("status", "");
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getDivConsumption1/{div}/{duration}")
    public String getDivConsumption1(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getDivConsumption");
        ArrayList<String> d = new ArrayList<>();
        ArrayList<String> n = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        ArrayList<String> l = new ArrayList<>();
        String label = "";
        JSONObject json = new JSONObject();
        int i=0;
        String[] dt = duration.split(",");
        
        try {
        	Integer val=1;
        	if("quarter".equalsIgnoreCase(duration))
        		val = 90;
        	else if("day".equalsIgnoreCase(duration))
        		val = 1;
        	else if("month".equalsIgnoreCase(duration))
        		val = 30;
        	else if("year".equalsIgnoreCase(duration))
        		val = 365;
    		ob = lmr.getDivWiseConsumptionData(dt[0], dt[1], div);
    		
    		/*if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				ArrayList<String> list = l;
    			    Object[] obj2 = (Object[]) itr.next();
    			    dom = new ArrayList<>();
    			    dom.add(String.valueOf(obj2[1]));
    			    dom.add(String.valueOf(obj2[2]));
    			    dom.add(String.valueOf(obj2[3]));
    			    json = new JSONObject();
    		    	json.put("label", String.valueOf(obj2[0]));
    		    	json.put("data", dom);
    		    	ja.add(json);
    			}
    		}
    		json = new JSONObject();
    		json.put("div", ja);
    		List<String> uniqueList = new ArrayList<String>();
    		uniqueList.add(0,"Domestic");
    		uniqueList.add(1,"Non Domestic");
    		uniqueList.add(2,"Industrial");
	    	json.put("val", uniqueList);
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", json);*/
			
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    			    Object[] obj2 = (Object[]) itr.next();
    			    d.add(String.valueOf(obj2[1]));
    			    n.add(String.valueOf(obj2[2]));
    			    s.add(String.valueOf(obj2[3]));
    			    l.add(String.valueOf(obj2[0])+"("+String.valueOf(obj2[4]).replaceAll("\\s", "")+")");
    			}
    		}
    		json = new JSONObject();
	    	json.put("label", "Domestic");
	    	json.put("data", d);
	    	ja.add(json);
	    	json = new JSONObject();
	    	json.put("label", "Non Domestic");
	    	json.put("data", n);
	    	ja.add(json);
	    	json = new JSONObject();
	    	json.put("label", "Industrial");
	    	json.put("data", s);
	    	ja.add(json);
    		json = new JSONObject();
    		json.put("div", ja);
	    	json.put("val", l);
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", json);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getSubDivCons/{div}/{duration}/{subdiv}")
    public String getSubDivCons(@PathVariable Integer div, @PathVariable String duration, @PathVariable String subdiv) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getSubDivCons");
        ArrayList<String> d = new ArrayList<>();
        ArrayList<String> n = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        ArrayList<String> l = new ArrayList<>();
        ArrayList<String> t = new ArrayList<>();
        String label = "";
        JSONObject json = new JSONObject();
        int i=0;
        try {
        	Integer val=1;
        	if("quarter".equalsIgnoreCase(duration))
        		val = 90;
        	else if("day".equalsIgnoreCase(duration))
        		val = 1;
        	else if("month".equalsIgnoreCase(duration))
        		val = 30;
        	else if("year".equalsIgnoreCase(duration))
        		val = 365;
        	String[] dt = duration.split(",");
    		ob = lmr.getSubDivWiseConsData(dt[0], dt[1], subdiv);
    		
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    			    Object[] obj2 = (Object[]) itr.next();
    			    d.add(String.valueOf(obj2[1]));
    			    n.add(String.valueOf(obj2[2]));
    			    s.add(String.valueOf(obj2[3]));
    			    l.add(String.valueOf(obj2[0])+"("+String.valueOf(obj2[4]).replaceAll("\\s", "")+")");
    			}
    		}
    		json = new JSONObject();
	    	json.put("label", "Domestic");
	    	json.put("data", d);
	    	ja.add(json);
	    	json = new JSONObject();
	    	json.put("label", "Non Domestic");
	    	json.put("data", n);
	    	ja.add(json);
	    	json = new JSONObject();
	    	json.put("label", "Industrial");
	    	json.put("data", s);
	    	ja.add(json);
    		json = new JSONObject();
    		json.put("div", ja);
	    	json.put("val", l);
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", json);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getDmaCons/{dma}/{duration}")
    public String getDmaCons(@PathVariable String dma, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getDmaCons");
        ArrayList<String> d = new ArrayList<>();
        ArrayList<String> n = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        ArrayList<String> l = new ArrayList<>();
        String label = "";
        JSONObject json = new JSONObject();
        int i=0;
        try {
        	Integer val=1;
        	if("quarter".equalsIgnoreCase(duration))
        		val = 90;
        	else if("day".equalsIgnoreCase(duration))
        		val = 1;
        	else if("month".equalsIgnoreCase(duration))
        		val = 30;
        	else if("year".equalsIgnoreCase(duration))
        		val = 365;
        	String[] dt = duration.split(",");
    		ob = lmr.getDmaWiseConsData(dt[0], dt[1], dma);
    		
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    			    Object[] obj2 = (Object[]) itr.next();
    			    d.add(String.valueOf(obj2[1]));
    			    n.add(String.valueOf(obj2[2]));
    			    s.add(String.valueOf(obj2[3]));
    			    l.add(String.valueOf(obj2[0])+"("+String.valueOf(obj2[4]).replaceAll("\\s", "")+")");
    			}
    		}
    		json = new JSONObject();
	    	json.put("label", "Domestic");
	    	json.put("data", d);
	    	ja.add(json);
	    	json = new JSONObject();
	    	json.put("label", "Non Domestic");
	    	json.put("data", n);
	    	ja.add(json);
	    	json = new JSONObject();
	    	json.put("label", "Industrial");
	    	json.put("data", s);
	    	ja.add(json);
    		json = new JSONObject();
    		json.put("div", ja);
	    	json.put("val", l);
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", json);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getAlertData/{div}/{duration}")
    public String getAlertData(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        JSONObject json = new JSONObject();
        log.debug("Inside get getAlertData");
        JSONArray ja = new JSONArray();
        String[] dt = duration.split(",");
        
        try {
        	if(div!=null) {
        		Integer t = lmr.getCounsumerCount(div);
        		Integer c = lmr.getDivCounsumerCount(div, dt[0], dt[1]);
        		if(t != null && c != null) {
        			json.put("counsumer",c);
        			json.put("total", t);
        		}
        		
        	}
        	ja.add(json);
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getPeakConsumption/{div}/{duration}")
    public String getPeakConsumption(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        JSONArray ja1 = new JSONArray();
        log.debug("Inside get getPeakConsumption");
        
        try {
        	if(div!=null) {
        		if("day".equalsIgnoreCase(duration) || "week".equalsIgnoreCase(duration) || "month".equalsIgnoreCase(duration) ||  "year".equalsIgnoreCase(duration)) {
	        		/*ja.add(0,100);
	        		ja.add(1,90);
	        		ja.add(2,80);
	        		ja.add(3,76);
	        		json.put("data", ja);
	        		json.put("label", "This Day");
	        		ja1.add(json);
	        		
	        		ja = new JSONArray();
	        		json = new JSONObject();
	        		ja.add(0,105);
	        		ja.add(1,95);
	        		ja.add(2,85);
	        		ja.add(3,75);
	        		json.put("data", ja);
	        		json.put("label", "Last Day");
	        		ja1.add(json);*/
	        		
	        		ja.add(0,lmr.getDivTotCons(div, duration));
	        		if("day".equalsIgnoreCase(duration)){
	        			ja.add(1,lmr.getPeakHourCons(div, duration));
	        			ja.add(2,lmr.getMinHourCons(div, duration));
		        		ja.add(3,lmr.getAvgHourCons(div, duration));
	        		}
	        		else if("week".equalsIgnoreCase(duration)){
	        			ja.add(1,lmr.getPeakWeekCons(div, duration));
	        			ja.add(2,lmr.getMinWeekCons(div, duration));
		        		ja.add(3,lmr.getAvgWeekCons(div, duration));
	        		}
	        		else if("month".equalsIgnoreCase(duration)){
	        			ja.add(1,lmr.getPeakMonthCons(div, duration));
	        			ja.add(2,lmr.getMinMonthCons(div, duration));
		        		ja.add(3,lmr.getAvgMonthCons(div, duration));
	        		}
	        		else if("year".equalsIgnoreCase(duration)){
	        			ja.add(1,lmr.getPeakYearCons(div, duration));
	        			ja.add(2,lmr.getMinYearCons(div, duration));
		        		ja.add(3,lmr.getAvgYearCons(div, duration));
	        		}
	        		/*ja.add(1,lmr.getPeakCons(div, duration));
	        		ja.add(2,lmr.getMinCons(div, duration));
	        		ja.add(3,lmr.getAvgCons(div, duration));*/
	        		json.put("data", ja);
	        		json.put("label", "This "+duration.substring(0, 1).toUpperCase()+duration.substring(1, duration.length())+"(L)");
	        		ja1.add(json);
	        		
	        		ja = new JSONArray();
	        		json = new JSONObject();
	        		ja.add(0,lmr.gePrevtDivTotCons(div, duration, "1 "+duration));
	        		if("day".equalsIgnoreCase(duration)){
	        			ja.add(1,lmr.getPrevPeakHourCons(div, duration, "1 "+duration));
	        			ja.add(2,lmr.getPrevMinHourCons(div, duration, "1 "+duration));
		        		ja.add(3,lmr.getPrevAvgHourCons(div, duration, "1 "+duration));
	        		}
	        		else if("week".equalsIgnoreCase(duration)){
	        			ja.add(1,lmr.getPrevPeakWeekCons(div, duration, "1 "+duration));
	        			ja.add(2,lmr.getPrevMinWeekCons(div, duration, "1 "+duration));
		        		ja.add(3,lmr.getPrevAvgWeekCons(div, duration, "1 "+duration));
	        		}
	        		else if("month".equalsIgnoreCase(duration)){
	        			ja.add(1,lmr.getPrevPeakMonthCons(div, duration, "1 "+duration));
	        			ja.add(2,lmr.getPrevMinMonthCons(div, duration, "1 "+duration));
		        		ja.add(3,lmr.getPrevAvgMonthCons(div, duration, "1 "+duration));
	        		}
	        		else if("year".equalsIgnoreCase(duration)){
	        			ja.add(1,lmr.getPrevPeakYearCons(div, duration, "1 "+duration));
	        			ja.add(2,lmr.getPrevMinYearCons(div, duration, "1 "+duration));
		        		ja.add(3,lmr.getPrevAvgYearCons(div, duration, "1 "+duration));
	        		}
	        		/*ja.add(1,lmr.getPrevPeakCons(div, duration, "1 "+duration));
	        		ja.add(2,lmr.getPrevMinCons(div, duration, "1 "+duration));
	        		ja.add(3,lmr.getPrevAvgCons(div, duration, "1 "+duration));*/
	        		json.put("data", ja);
	        		json.put("label", "Previous "+duration.substring(0, 1).toUpperCase()+duration.substring(1, duration.length())+"(L)");
	        		ja1.add(json);
        		}
	    	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja1);
        }
        catch(Exception e) {
        	obj.put("issuccess", false);
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getPeakConsumptionNew/{div}/{duration}")
    public String getPeakConsumptionNew(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        JSONArray ja1 = new JSONArray();
        log.debug("Inside get getPeakConsumption");
        
        try {
        	if(div!=null) {
        		if("day".equalsIgnoreCase(duration) || "week".equalsIgnoreCase(duration) || "month".equalsIgnoreCase(duration) ||  "year".equalsIgnoreCase(duration)) {
	        		ja.add(0,lmr.getDivTotCons(div, duration));
	        		ja.add(1,lmr.getPeakMonthCons(div, duration));
	        		ja.add(2,lmr.getMinCons(div, duration));
	        		ja.add(3,lmr.getAvgCons(div, duration));
	        		json.put("data", ja);
	        		json.put("label", "This "+duration.substring(0, 1).toUpperCase()+duration.substring(1, duration.length()));
	        		ja1.add(json);
	        		
	        		ja = new JSONArray();
	        		json = new JSONObject();
	        		ja.add(0,lmr.gePrevtDivTotCons(div, duration, "1 "+duration));
	        		ja.add(1,lmr.getPrevPeakCons(div, duration, "1 "+duration));
	        		ja.add(2,lmr.getPrevMinCons(div, duration, "1 "+duration));
	        		ja.add(3,lmr.getPrevAvgCons(div, duration, "1 "+duration));
	        		json.put("data", ja);
	        		json.put("label", "Previous "+duration.substring(0, 1).toUpperCase()+duration.substring(1, duration.length()));
	        		ja1.add(json);
        		}
	    	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja1);
        }
        catch(Exception e) {
        	obj.put("issuccess", false);
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getDetConsumption/{div}/{duration}")
    public String getDetConsumption(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        JSONObject json = new JSONObject();
        JSONArray ja = new JSONArray();
        List<Object> ob = new ArrayList<>();
        log.debug("Inside get getDetConsumption");
        
        try {
        	if("day".equalsIgnoreCase(duration))
        		ob = lmr.getDayCons(div);
    		if("week".equalsIgnoreCase(duration))
    			ob = lmr.getWeekCons(div,duration);
			if("month".equalsIgnoreCase(duration))
				ob = lmr.getMonthCons(div,duration);
			if("year".equalsIgnoreCase(duration))
				ob = lmr.getYearCons(div,duration);
			
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("name", String.valueOf(obj2[0]));
	    			else
	    				json.put("name", "");
	    			if(obj2[1]!=null)
	    				json.put("val", String.valueOf(obj2[1]));
	    			else
	    				json.put("val", "");
	    	        
	    			ja.add(json);
    			}
    		}
    		
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	obj.put("issuccess", false);
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getCommData/{div}/{duration}")
    public String getCommData(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getCommData");
        String[] str_array = {"NotCommunicated","PartialyCommunicated","FullyCommunicated"};
        List<String> list = new ArrayList<String>(Arrays.asList(str_array));
        JSONObject json = new JSONObject();
        String[] dt = duration.split(",");
        /*json.put("type", "NotCommunicated");
        json.put("val", "0");
        ja.add(json);
        json = new JSONObject();
        json.put("type", "PartialyCommunicated");
        json.put("val", "0");
        ja.add(json);
        json = new JSONObject();getCommDetails
        json.put("type", "FullyCommunicated");
        json.put("val", "0");
        ja.add(json);*/
        try {
        	if("quarter".equalsIgnoreCase(duration))
        		duration = "3 month";
        	else
        		duration = "1 "+duration;
    		ob = lmr.getCommDataOld(div);
    		
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("type", String.valueOf(obj2[0]));
	    			else
	    				json.put("type", "");
	    			if(obj2[1]!=null)
	    				json.put("val", String.valueOf(obj2[1]));
	    			else
	    				json.put("val", "");
	    			list.remove(String.valueOf(obj2[0]));
	    	        
	    			ja.add(json);
    			}
    		}
    		if(list.size()>0) {
    			for(int i=0; i<list.size(); i++) {
    				json = new JSONObject();
    		        json.put("type", list.get(i));
    		        json.put("val", "0");
    		        ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	obj.put("issuccess", false);
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getCommDetails/{div}/{duration}/{commType}")
    public String getCommDetails(@PathVariable Integer div, @PathVariable String duration, @PathVariable String commType) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getCommDetails");
        
        try {
        	if(div!=null) {
        		ob = lmr.getCommDet(div, commType);
        		
        		if(ob.size()>0) {
	        		Iterator itr = ob.iterator();
	    			while(itr.hasNext()){
	    				JSONObject json = new JSONObject();
	    			    Object[] obj2 = (Object[]) itr.next();
	    			    if(obj2[0]!=null)
		    				json.put("unitid", String.valueOf(obj2[0]));
		    			else
		    				json.put("unitid", "");
		    			if(obj2[1]!=null)
		    				json.put("subdiv", String.valueOf(obj2[1])+"");
		    			else
		    				json.put("subdiv", "");
		    			if(obj2[2]!=null)
		    				json.put("category", String.valueOf(obj2[2])+"");
		    			else
		    				json.put("category", "");
		    			if(obj2[3]!=null)
		    				json.put("status", String.valueOf(obj2[3])+"");
		    			else
		    				json.put("status", "");
		    			
		    			ja.add(json);
	    			}
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        	
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getDemandData/{div}/{duration}")
    public String getDemandData(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getDemandData");
        String[] dt = duration.split(",");
        
        try {
        	if(div!=null) {
        		ob = lmr.getWaterDemandData(div, duration);
        		
        		if(ob.size()>0) {
	        		Iterator itr = ob.iterator();
	    			while(itr.hasNext()){
	    				JSONObject json = new JSONObject();
	    			    Object[] obj2 = (Object[]) itr.next();
	    			    if(obj2[0]!=null)
		    				json.put("val", String.valueOf(obj2[0]));
		    			else
		    				json.put("val", "");
		    			if(obj2[1]!=null)
		    				json.put("day", String.valueOf(obj2[1])+"");
		    			else
		    				json.put("day", "");
		    			
		    			ja.add(json);
	    			}
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getComplaintData/{div}/{duration}")
    public String getComplaintData(@PathVariable String div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getComplaintData");
        String[] dt = duration.split(",");
        
        try {
    		ob = lmr.getComplaintStatus1(dt[0], dt[1], Integer.parseInt(div));
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("type", String.valueOf(obj2[0]));
	    			else
	    				json.put("type", "");
	    			if(obj2[1]!=null)
	    				json.put("val", String.valueOf(obj2[1]));
	    			else
	    				json.put("val", "");
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getComplaintDetails/{div}/{duration}")
    public String getComplaintDetails(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getComplaintDetails");
        String[] dt = duration.split(",");
        
        try {
    		ob = lmr.getComplaintDet(div, dt[0], dt[1]);
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("name", String.valueOf(obj2[0]));
	    			else
	    				json.put("name", "");
	    			if(obj2[1]!=null)
	    				json.put("subdiv", String.valueOf(obj2[1]));
	    			else
	    				json.put("subdiv", "");
	    			if(obj2[2]!=null)
	    				json.put("status", String.valueOf(obj2[2]));
	    			else
	    				json.put("status", "");
	    			if(obj2[3]!=null)
	    				json.put("cdt", String.valueOf(obj2[3]));
	    			else
	    				json.put("cdt", "");
	    			if(obj2[4]!=null)
	    				json.put("rdt", String.valueOf(obj2[4]));
	    			else
	    				json.put("rdt", "");
	    			if(obj2[5]!=null)
	    				json.put("type", String.valueOf(obj2[5]));
	    			else
	    				json.put("type", "");
	    	        
	    			ja.add(json);
    			}
    		}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getMNFData/{div}/{duration}")
    public String getMNFData(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getMNFData");
        
        try {
        	if(div!=null) {
    		ob = lmr.getMnfData(div, duration);
    		
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
    			    if(obj2[0]!=null)
	    				json.put("val", String.valueOf(obj2[0]));
	    			else
	    				json.put("val", "");
	    			if(obj2[1]!=null)
	    				json.put("day", String.valueOf(obj2[1])+"");
	    			else
	    				json.put("day", "");
	    			
	    			ja.add(json);
    			}
    		}
    	}
		obj.put("issuccess", true);
		obj.put("error", null);
		obj.put("data", ja);}
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getMassBalData/{div}/{duration}")
    public String getMassBalData(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getMassBalData");
        JSONObject json = new JSONObject();
        ArrayList<String> d = new ArrayList<>();
        ArrayList<String> l = new ArrayList<>();
        String[] dt = duration.split(",");
        
        try {
        	if(div!=null) {
    			ob = lmr.getMassData(div,dt[0], dt[1]);
    		
    			if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        
                        Object[] obj2 = (Object[]) it.next();
                        d = new ArrayList<>();
                        d.add(String.valueOf(obj2[0]));
                        d.add(String.valueOf(obj2[1]));
                        d.add(String.valueOf(obj2[2]));
                        d.add(String.valueOf(obj2[3]));

                        ja.add(d);
                    }
                }
        	}
        	l = new ArrayList<>();
        	l.add("Consumption");
        	l.add("Total");
        	l.add("Actual");
        	l.add("Loss");
        	json = new JSONObject();
        	json.put("data", ja);
        	json.put("div", l);
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", json);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getMassBalData1/{div}/{duration}")
    public String getMassBalData1(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getMassBalData");
        
        try {
        	if(div!=null) {
    			ob = lmr.getMassData1(div);
    		
    			if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        JSONObject json = new JSONObject();
                        Object[] obj2 = (Object[]) it.next();

                        if (obj2[0] != null)
                            json.put("subdiv", String.valueOf(obj2[0]));
                        else
                            json.put("subdiv", "");
                        if (obj2[1] != null)
                            json.put("actual", String.valueOf(obj2[1]));
                        else
                            json.put("actual", "");
                        if (obj2[2] != null)
                            json.put("total", String.valueOf(obj2[2]));
                        else
                            json.put("total", "");
                        if (obj2[3] != null)
                            json.put("loss", String.valueOf(obj2[3]));
                        else
                            json.put("loss", "");
                        if (obj2[4] != null)
                            json.put("dt", String.valueOf(obj2[4]));
                        else
                            json.put("dt", "");
                        if (obj2[5] != null)
                            json.put("duration", String.valueOf(obj2[5]));
                        else
                            json.put("duration", "");

                        ja.add(json);
                    }
                }
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getWaterQualityData/{div}/{duration}")
    public String getWaterQualityData(@PathVariable Integer div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getWaterQualityData");
        String[] dt = duration.split(",");
        
        try {
        	if(div!=null) {
        			ob = lmr.getQualityData(div, dt[0], dt[1]);
        		
        		if(ob.size()>0) {
	        		Iterator itr = ob.iterator();
	    			while(itr.hasNext()){
	    				JSONObject json = new JSONObject();
	    			    Object[] obj2 = (Object[]) itr.next();
	    			    if(obj2[0]!=null)
		    				json.put("unitid", String.valueOf(obj2[0]));
		    			else
		    				json.put("unitid", "");
		    			if(obj2[1]!=null)
		    				json.put("param", String.valueOf(obj2[1]));
		    			else
		    				json.put("param", "");
		    			if(obj2[2]!=null)
		    				json.put("cond", String.valueOf(obj2[2]).substring(0,9));
		    			else
		    				json.put("cond", "");
		    			if(obj2[2]!=null)
		    				json.put("value", String.valueOf(obj2[2]).substring(10));
		    			else
		    				json.put("value", "");
		    			if(obj2[3]!=null)
		    				json.put("ref", String.valueOf(obj2[3]));
		    			else
		    				json.put("ref", "");
		    			if(obj2[4]!=null)
		    				json.put("actual", String.valueOf(obj2[4]));
		    			else
		    				json.put("actual", "");
		    			
		    			
		    			ja.add(json);
	    			}
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getMarqueData/{div}/{duration}")
    public String getMarqueData(@PathVariable String div, @PathVariable String duration) throws java.text.ParseException{
        JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside get getMarqueData");
        
        try {
        	if(div!=null) {
        			ob = lmr.getDayHistoryData(duration, div);
        		
        		if(ob.size()>0) {
	        		Iterator itr = ob.iterator();
	    			while(itr.hasNext()){
	    				JSONObject json = new JSONObject();
	    			    Object[] obj2 = (Object[]) itr.next();
	    			    if(obj2[0]!=null)
		    				json.put("msg", String.valueOf(obj2[0]));
		    			else
		    				json.put("msg", "");
		    			
		    			ja.add(json);
	    			}
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
}
