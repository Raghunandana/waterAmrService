package com.enzen.waterMdm.controller;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
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

import com.enzen.waterMdm.model.EmployeeLoginMapping;
import com.enzen.waterMdm.model.LoginMaster;
import com.enzen.waterMdm.repo.EmployeeLoginMappingRepository;
import com.enzen.waterMdm.repo.LoginMasterRepository;

@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	LoginMasterRepository lmr;
	
	@Autowired
	EmployeeLoginMappingRepository elmpr;
	
	@Autowired
	private HttpServletRequest context;
	
	private final Logger log = LogManager.getLogger(this.getClass());
	
	@SuppressWarnings("unchecked")
	@PostMapping("/validateUser")
    public String getGeneric(@RequestBody String stringToParse) throws java.text.ParseException{
        JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject obj = new JSONObject();
        LoginMaster user = new LoginMaster();
        List<Object> ob = new ArrayList<>();
        String userName = "";
        String msg = "";
        log.debug("Inside user validation.");
        
        try {
        	json = (JSONObject) parser.parse(stringToParse);
        	if(json.get("userName")!=null && json.get("password")!=null) {
        		userName = json.get("userName").toString();
        		String password = getPasswordHash(json.get("password").toString());
        		user = lmr.findByLoginName(userName);
        		if(user !=null) {
        			user = lmr.findByLoginNameAndPassword(userName, password);
        			
        			if(user !=null) {
        				ob = lmr.getEmpData(userName);
        				
	        			msg = "User logged successfully";
        			}
        			else {
            			msg = "Please enter valid password";
            		}
        		}
        		else {
        			msg = "Please enter valid username";
        		}
        	}
            json = new JSONObject();
    		if(user==null) {
    			obj.put("issuccess", false);
    			obj.put("error", null);
    			obj.put("data", msg);
    		}
    		else {
    			obj.put("issuccess", true);
    			obj.put("error", null);
    			
    			json.put("token", "APIB2920B90A85AC4EAD948C1E40338865C");/////
    			json.put("userid", user.getLoginName());
    			
    			if(ob.size()>0) {
            		Iterator itr = ob.iterator();
        			while(itr.hasNext()){
        			    Object[] obj2 = (Object[]) itr.next();
    	    			if(obj2[3]!=null)
    	    				json.put("name", String.valueOf(obj2[3]));
    	    			else
    	    				json.put("name", "");
    	    			if(obj2[4]!=null)
    	    				json.put("division", String.valueOf(obj2[4]));
    	    			else
    	    				json.put("division", "");
    	    			if(obj2[5]!=null)
    	    				json.put("subdivision", String.valueOf(obj2[5]));
    	    			else
    	    				json.put("subdivision", "");
    	    			if(obj2[10]!=null)
    	    				json.put("org", String.valueOf(obj2[10]));
    	    			else
    	    				json.put("org", "");
    	    			EmployeeLoginMapping elm = new EmployeeLoginMapping();
            			elm = elmpr.findByLoginName(userName);
            			json.put("id", elm.getEmployeeId());
        			}
        		}
    			
    	        obj.put("data", json);
    		}
        	
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	private String getPasswordHash(String password) {
		 StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
	        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
	        for (byte b : hashInBytes) {
	            sb.append(String.format("%02x", b));
	        }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
}
