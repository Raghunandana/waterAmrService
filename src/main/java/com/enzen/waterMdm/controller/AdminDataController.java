package com.enzen.waterMdm.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.TimeZone;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.crypto.Data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.enzen.waterMdm.repo.ApplicationLogsRepository;
import com.enzen.waterMdm.controller.ApplicationLogsController;
import com.enzen.waterMdm.model.Employee;
import com.enzen.waterMdm.model.EmployeeLoginMapping;
import com.enzen.waterMdm.model.LoginMaster;
import com.enzen.waterMdm.model.MasterValue;
import com.enzen.waterMdm.model.Menu;
import com.enzen.waterMdm.model.MessageData;
import com.enzen.waterMdm.model.MessageRejected;
import com.enzen.waterMdm.model.MeterMaster;
import com.enzen.waterMdm.model.Organization;
import com.enzen.waterMdm.model.QualityData;
import com.enzen.waterMdm.model.QualityParameter;
import com.enzen.waterMdm.model.Roles;
import com.enzen.waterMdm.model.TariffMaster;
import com.enzen.waterMdm.model.TempMessageData;
import com.enzen.waterMdm.model.UnitConsumptionDetails;
import com.enzen.waterMdm.model.UnitSummary;
import com.enzen.waterMdm.repo.EmployeeLoginMappingRepository;
import com.enzen.waterMdm.repo.EmployeeRepository;
import com.enzen.waterMdm.repo.LoginMasterRepository;
import com.enzen.waterMdm.repo.MasterValueRepository;
import com.enzen.waterMdm.repo.MenuRepository;
import com.enzen.waterMdm.repo.MessageDataRepository;
import com.enzen.waterMdm.repo.MessageRejectedRepository;
import com.enzen.waterMdm.repo.MeterMasterRepository;
import com.enzen.waterMdm.repo.OrganizationRepository;
import com.enzen.waterMdm.repo.QualityDataRepository;
import com.enzen.waterMdm.repo.QualityParameterRepository;
import com.enzen.waterMdm.repo.RolesRepository;
import com.enzen.waterMdm.repo.TariffMasterRepository;
import com.enzen.waterMdm.repo.TempMessageDataRepository;
import com.enzen.waterMdm.repo.UnitSummaryRepository;
import com.enzen.waterMdm.repo.UnitsConsumptionDetailsRepository;

@RestController
@RequestMapping("/admin")
public class AdminDataController {
	
	@Autowired
	UnitSummaryRepository usr;
	
	@Autowired
	OrganizationRepository or;
	
	@Autowired
	RolesRepository rr;
	
	@Autowired
	EmployeeRepository er;
	
	@Autowired
	LoginMasterRepository lmr;
	
	@Autowired
	MenuRepository mr;
	
	@Autowired
	MessageDataRepository mdr;
	
	@Autowired
	TempMessageDataRepository tmdr;
	
	@Autowired
	MessageRejectedRepository mrr;
	
	@Autowired
	MasterValueRepository mvr;
	
	@Autowired
	EmployeeLoginMappingRepository elmpr;
	
	@Autowired
	UnitsConsumptionDetailsRepository ucdr;
	
	@Autowired
	MeterMasterRepository mmr;
	
	@Autowired
	ApplicationLogsRepository alr;
	
	@Autowired
	TariffMasterRepository tmr;
	
	@Autowired
	QualityParameterRepository qpr;
	
	@Autowired
	QualityDataRepository qdr;
	
	private final Logger log = LogManager.getLogger(this.getClass());
	
	static ResourceBundle config = ResourceBundle.getBundle("config");
	
	private static String UPLOADED_FOLDER = "D://temp//";
	ApplicationLogsController ac = new ApplicationLogsController();
	
	@SuppressWarnings("unchecked")
	@PostMapping("/insertUnitSummary")
    public JSONObject insertUnitSummary(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        UnitSummary us = new UnitSummary();
        try {
        	if(stringToParse!=null) {
	        	json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	if(data.get("unitId")!=null)
	        		us.setUnitId(data.get("unitId").toString());
	        	if(data.get("meterHType")!=null)
	        		us.setMeterHType(Integer.parseInt(data.get("meterHType").toString()));
	        	if(data.get("installOn")!=null)
	        		us.setInstallOn(new SimpleDateFormat("yyyy-MM-dd").parse(data.get("installOn").toString()));
	        	if(data.get("pipesize")!=null)
	        		us.setPipesize(data.get("pipesize").toString());
	        	if(data.get("totalFlow")!=null)
	        		us.setTotalFlow(Double.parseDouble(data.get("totalFlow").toString()));
	        	if(data.get("avgFlowRate")!=null)
	        		us.setAvgFlowRate(Double.parseDouble(data.get("avgFlowRate").toString()));
	        	
	        	if(data.get("consId")!=null)
	        		us.setConsId(Integer.parseInt(data.get("consId").toString()));
	        	if(data.get("consAddr")!=null)
	        		us.setConsAddr(data.get("consAddr").toString());
	        	if(data.get("consContNum")!=null)
	        		us.setConsContNum(data.get("consContNum").toString());
	        	if(data.get("mtrSlNo")!=null)
	        		us.setMtrSlNo(data.get("mtrSlNo").toString());
	        	if(data.get("oemName")!=null)
	        		us.setOemName(data.get("oemName").toString());
	        	if(data.get("meterType")!=null)
	        		us.setMeterType(data.get("meterType").toString());
	        	if(data.get("meterflowType")!=null)
	        		us.setMeterflowType(data.get("meterflowType").toString());
	        	if(data.get("unit")!=null)
	        		us.setMeterUnit(data.get("unit").toString());
	        	if(data.get("lat")!=null)
	        		us.setLat(Double.parseDouble(data.get("lat").toString()));
	        	if(data.get("lon")!=null)
	        		us.setLon(Double.parseDouble(data.get("lon").toString()));
	        	
	        	if(data.get("subdivId")!=null)
	        		us.setSubdivId(Integer.parseInt(data.get("subdivId").toString()));
	        	if(data.get("divId")!=null)
	        		us.setDivId(Integer.parseInt(data.get("divId").toString()));
	        	if(data.get("serStnId")!=null)
	        		us.setSerStnId(Integer.parseInt(data.get("serStnId").toString()));
	        	if(data.get("locId")!=null)
	        		us.setLocId(Integer.parseInt(data.get("locId").toString()));
	        	if(data.get("divHead")!=null)
	        		us.setDivHead(data.get("divHead").toString());
	        	if(data.get("subDivHead")!=null)
	        		us.setSubDivHead(data.get("subDivHead").toString());
	        	if(data.get("msgTime")!=null) {
	        		DateFormat df = new SimpleDateFormat("HH:mm:ss");
	        		df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        		String strTime = data.get("msgTime").toString();
	        		Date d = df.parse(strTime);
	        		us.setMsgTime(d);
	        	}
	        	if(data.get("mtrCalibFactor")!=null)
	        		us.setMtrCalibFactor(Double.parseDouble(data.get("mtrCalibFactor").toString()));
	        	if(data.get("mtrCalibDate")!=null)
	        		us.setMtrCalibDate(new SimpleDateFormat("dd/MM/yyyy").parse(data.get("mtrCalibDate").toString()));
	        	if(data.get("bttryLmtVal")!=null)
	        		us.setBttryLmtVal(data.get("bttryLmtVal").toString());
	        	if(data.get("intMtrReading")!=null)
	        		us.setIntMtrReading(Double.parseDouble(data.get("intMtrReading").toString()));
	        	if(data.get("consInitMtrForNextSms")!=null)
	        		us.setConsInitMtrForNextSms(Boolean.parseBoolean(data.get("consInitMtrForNextSms").toString()));
	        	if(data.get("ltrPerPulse")!=null)
	        		us.setLtrPerPulse(Double.parseDouble(data.get("ltrPerPulse").toString()));
	        	if(data.get("fldPicture")!=null)
	        		us.setFldPicture(data.get("fldPicture").toString());
	        	if(data.get("trfPerLtr")!=null)
	        		us.setTrfPerLtr(Double.parseDouble(data.get("trfPerLtr").toString()));
	        	if(data.get("mobRcpt1")!=null)
	        		us.setMobRcpt1(data.get("mobRcpt1").toString());
	        	if(data.get("mobRcpt2")!=null)
	        		us.setMobRcpt2(data.get("mobRcpt2").toString());
	        	if(data.get("mobRcpt3")!=null)
	        		us.setMobRcpt3(data.get("mobRcpt3").toString());
	        	if(data.get("mailiRcpt1")!=null)
	        		us.setMailiRcpt1(data.get("mailiRcpt1").toString());
	        	if(data.get("mailiRcpt2")!=null)
	        		us.setMailiRcpt2(data.get("mailiRcpt2").toString());
	        	if(data.get("mailiRcpt3")!=null)
	        		us.setMailiRcpt3(data.get("mailiRcpt3").toString());
	        	
	        	if(data.get("mtrBillType")!=null)
	        		us.setMtrBillType(data.get("mtrBillType").toString());
	        	if(data.get("gsmSglStrgh")!=null)
	        		us.setGsmSglStrgh(data.get("gsmSglStrgh").toString());
	        	if(data.get("mtrMdlNum")!=null)
	        		us.setMtrMdlNum(data.get("mtrMdlNum").toString());
	        	if(data.get("modOfComm")!=null)
	        		us.setModOfComm(data.get("modOfComm").toString());
	        	if(data.get("gprsType")!=null)
	        		us.setGprsType(data.get("gprsType").toString());
	        	if(data.get("gprsTypeUrl")!=null)
	        		us.setGprsTypeUrl(data.get("gprsTypeUrl").toString());
	        	if(data.get("commPeriod")!=null)
	        		us.setCommPeriod(data.get("commPeriod").toString());
	        	if(data.get("rechaDate")!=null)
	        		us.setRechaDate(new SimpleDateFormat("dd/MM/yyyy").parse(data.get("rechaDate").toString()));
	        	if(data.get("srvProvider")!=null)
	        		us.setSrvProvider(data.get("srvProvider").toString());
	        	if(data.get("daysOfWaterFlow")!=null)
	        		us.setDaysOfWaterFlow(data.get("daysOfWaterFlow").toString());
	        	if(data.get("fromWaterFlow")!=null)
	        		us.setFromWaterFlow(data.get("fromWaterFlow").toString());
	        	if(data.get("toWaterFlow")!=null)
	        		us.setToWaterFlow(data.get("toWaterFlow").toString());
	        	if(data.get("avgPressure")!=null)
	        		us.setAvgPressure(Double.parseDouble(data.get("avgPressure").toString()));
	        	
	        	if(data.get("active")!=null)
	        		us.setActive(Boolean.parseBoolean(data.get("active").toString()));
	        	if(data.get("billGroupId")!=null)
	        		us.setBillGroupId(Integer.parseInt(data.get("billGroupId").toString()));
	        	if(data.get("firstReading")!=null)
	        		us.setFirstReading(Double.parseDouble(data.get("firstReading").toString()));
	        	if(data.get("mapVal")!=null)
	        		us.setMapVal(Double.parseDouble(data.get("mapVal").toString()));
	        	if(data.get("firstPulse")!=null)
	        		us.setFirstPulse(Double.parseDouble(data.get("firstPulse").toString()));
	        	if(data.get("mapPulse")!=null)
	        		us.setMapPulse(Double.parseDouble(data.get("mapPulse").toString()));
	        	
	        	us = usr.save(us);
	        	json.put("data", us);
	        	
	        	ac.writeLog("Unit is created", "Unit is : "+us.getUnitId(), alr);
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/updateUnitSummary")
    public JSONObject updateUnitSummary(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        UnitSummary us = new UnitSummary();
        try {
        	if(stringToParse!=null) {
	        	json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	if(data.get("id")!=null)
	        		us.setId(Integer.parseInt(data.get("id").toString()));
	        	us = usr.findById(us.getId());
	        	if(data.get("unitId")!=null)
	        		us.setUnitId(data.get("unitId").toString());
	        	if(data.get("meterHType")!=null)
	        		us.setMeterHType(Integer.parseInt(data.get("meterHType").toString()));
	        	if(data.get("installOn")!=null && !"".equalsIgnoreCase(data.get("installOn").toString()))
	        		us.setInstallOn(new SimpleDateFormat("yyyy-MM-dd").parse(data.get("installOn").toString()));
	        	if(data.get("pipesize")!=null)
	        		us.setPipesize(data.get("pipesize").toString());
	        	if(data.get("totalFlow")!=null)
	        		us.setTotalFlow(Double.parseDouble(data.get("totalFlow").toString()));
	        	if(data.get("subdivId")!=null)
	        		us.setSubdivId(Integer.parseInt(data.get("subdivId").toString()));
	        	if(data.get("divId")!=null)
	        		us.setDivId(Integer.parseInt(data.get("divId").toString()));
	        	if(data.get("serStnId")!=null)
	        		us.setSerStnId(Integer.parseInt(data.get("serStnId").toString()));
	        	if(data.get("locId")!=null)
	        		us.setLocId(Integer.parseInt(data.get("locId").toString()));
	        	if(data.get("divHead")!=null)
	        		us.setDivHead(data.get("divHead").toString());
	        	if(data.get("subDivHead")!=null)
	        		us.setSubDivHead(data.get("subDivHead").toString());
	        	if(data.get("msgTime")!=null) {
	        		DateFormat df = new SimpleDateFormat("HH:mm:ss");
	        		df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        		String strTime = data.get("msgTime").toString();
	        		Date d = df.parse(strTime);
	        		us.setMsgTime(d);
	        	}
	        	if(data.get("mtrCalibFactor")!=null)
	        		us.setMtrCalibFactor(Double.parseDouble(data.get("mtrCalibFactor").toString()));
	        	if(data.get("mtrCalibDate")!=null && !"".equalsIgnoreCase(data.get("mtrCalibDate").toString()))
	        		us.setMtrCalibDate(new SimpleDateFormat("yyyy-MM-dd").parse(data.get("mtrCalibDate").toString()));
	        	if(data.get("bttryLmtVal")!=null)
	        		us.setBttryLmtVal(data.get("bttryLmtVal").toString());
	        	if(data.get("intMtrReading")!=null)
	        		us.setIntMtrReading(Double.parseDouble(data.get("intMtrReading").toString()));
	        	if(data.get("consInitMtrForNextSms")!=null)
	        		us.setConsInitMtrForNextSms(Boolean.parseBoolean(data.get("consInitMtrForNextSms").toString()));
	        	if(data.get("ltrPerPulse")!=null)
	        		us.setLtrPerPulse(Double.parseDouble(data.get("ltrPerPulse").toString()));
	        	if(data.get("fldPicture")!=null)
	        		us.setFldPicture(data.get("fldPicture").toString());
	        	if(data.get("trfPerLtr")!=null)
	        		us.setTrfPerLtr(Double.parseDouble(data.get("trfPerLtr").toString()));
	        	if(data.get("mobRcpt1")!=null)
	        		us.setMobRcpt1(data.get("mobRcpt1").toString());
	        	if(data.get("mobRcpt2")!=null)
	        		us.setMobRcpt2(data.get("mobRcpt2").toString());
	        	if(data.get("mobRcpt3")!=null)
	        		us.setMobRcpt3(data.get("mobRcpt3").toString());
	        	if(data.get("mailiRcpt1")!=null)
	        		us.setMailiRcpt1(data.get("mailiRcpt1").toString());
	        	if(data.get("mailiRcpt2")!=null)
	        		us.setMailiRcpt2(data.get("mailiRcpt2").toString());
	        	if(data.get("mailiRcpt3")!=null)
	        		us.setMailiRcpt3(data.get("mailiRcpt3").toString());
	        	if(data.get("lat")!=null)
	        		us.setLat(Double.parseDouble(data.get("lat").toString()));
	        	if(data.get("lon")!=null)
	        		us.setLon(Double.parseDouble(data.get("lon").toString()));
	        	if(data.get("consId")!=null)
	        		us.setConsId(Integer.parseInt(data.get("consId").toString()));
	        	if(data.get("consAddr")!=null)
	        		us.setConsAddr(data.get("consAddr").toString());
	        	if(data.get("consContNum")!=null)
	        		us.setConsContNum(data.get("consContNum").toString());
	        	if(data.get("mtrSlNo")!=null)
	        		us.setMtrSlNo(data.get("mtrSlNo").toString());
	        	if(data.get("oemName")!=null)
	        		us.setOemName(data.get("oemName").toString());
	        	if(data.get("meterType")!=null)
	        		us.setMeterType(data.get("meterType").toString());
	        	if(data.get("meterflowType")!=null)
	        		us.setMeterflowType(data.get("meterflowType").toString());
	        	if(data.get("mtrBillType")!=null)
	        		us.setMtrBillType(data.get("mtrBillType").toString());
	        	if(data.get("gsmSglStrgh")!=null)
	        		us.setGsmSglStrgh(data.get("gsmSglStrgh").toString());
	        	if(data.get("mtrMdlNum")!=null)
	        		us.setMtrMdlNum(data.get("mtrMdlNum").toString());
	        	if(data.get("modOfComm")!=null)
	        		us.setModOfComm(data.get("modOfComm").toString());
	        	if(data.get("gprsType")!=null)
	        		us.setGprsType(data.get("gprsType").toString());
	        	if(data.get("gprsTypeUrl")!=null)
	        		us.setGprsTypeUrl(data.get("gprsTypeUrl").toString());
	        	if(data.get("commPeriod")!=null)
	        		us.setCommPeriod(data.get("commPeriod").toString());
	        	if(data.get("rechaDate")!=null && !"".equalsIgnoreCase(data.get("rechaDate").toString()))
	        		us.setRechaDate(new SimpleDateFormat("yyyy-MM-dd").parse(data.get("rechaDate").toString()));
	        	if(data.get("srvProvider")!=null)
	        		us.setSrvProvider(data.get("srvProvider").toString());
	        	if(data.get("daysOfWaterFlow")!=null)
	        		us.setDaysOfWaterFlow(data.get("daysOfWaterFlow").toString());
	        	if(data.get("fromWaterFlow")!=null)
	        		us.setFromWaterFlow(data.get("fromWaterFlow").toString());
	        	if(data.get("toWaterFlow")!=null)
	        		us.setToWaterFlow(data.get("toWaterFlow").toString());
	        	if(data.get("avgPressure")!=null)
	        		us.setAvgPressure(Double.parseDouble(data.get("avgPressure").toString()));
	        	if(data.get("avgFlowRate")!=null)
	        		us.setAvgFlowRate(Double.parseDouble(data.get("avgFlowRate").toString()));
	        	if(data.get("active")!=null)
	        		us.setActive(Boolean.parseBoolean(data.get("active").toString()));
	        	if(data.get("billGroupId")!=null)
	        		us.setBillGroupId(Integer.parseInt(data.get("billGroupId").toString()));
	        	if(data.get("firstReading")!=null)
	        		us.setFirstReading(Double.parseDouble(data.get("firstReading").toString()));
	        	if(data.get("mapVal")!=null)
	        		us.setMapVal(Double.parseDouble(data.get("mapVal").toString()));
	        	if(data.get("firstPulse")!=null)
	        		us.setFirstPulse(Double.parseDouble(data.get("firstPulse").toString()));
	        	if(data.get("mapPulse")!=null)
	        		us.setMapPulse(Double.parseDouble(data.get("mapPulse").toString()));
	        	if(data.get("unit")!=null)
	        		us.setMeterUnit(data.get("unit").toString());
	        	
	        	us = usr.save(us);
	        	json.put("data", us);
	        	
	        	ac.writeLog("Unit is upated", "Unit is : "+us.getUnitId(), alr);
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	json.put("issuccess", false);
        	json.put("code", 500);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/getUnitSummary/{id}")
	public String getUnitSummary(@PathVariable Integer id) throws java.text.ParseException {
		JSONObject obj = new JSONObject();
		log.debug("Inside getUnitSummary");

		try {
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			UnitSummary e = new UnitSummary();
			e = usr.findById(id);
			if (e != null) {
				JSONObject json = new JSONObject();
				json.put("id", e.getId());
				json.put("meterHType", e.getMeterHType());
				json.put("unitId", e.getUnitId());
				if (e.getInstallOn() != null) {
					String a = "" + e.getInstallOn();
					a = a.substring(0, 10);
					Date date = format1.parse(a);
					json.put("installOn", format.format(date));
				} else
					json.put("installOn", "");
				json.put("pipesize", e.getPipesize());
				json.put("totalFlow", e.getTotalFlow());
				if (e.getSubdivId() != null)
					json.put("subdivId", e.getSubdivId().toString());
				else
					json.put("subdivId", "");
				if (e.getDivId() != null)
					json.put("divId", e.getDivId().toString());
				else
					json.put("divId", "");
				if (e.getSerStnId() != null)
					json.put("serStnId", e.getSerStnId().toString());
				else
					json.put("serStnId", "");
				if (e.getLocId() != null)
					json.put("locId", e.getLocId().toString());
				else
					json.put("locId", "");
				json.put("divHead", e.getDivHead());
				json.put("subDivHead", e.getSubDivHead());
				json.put("msgTime", e.getMsgTime());
				json.put("mtrCalibFactor", e.getMtrCalibFactor());
				if (e.getMtrCalibDate() != null) {
					String a = "" + e.getMtrCalibDate();
					a = a.substring(0, 10);
					Date date = format1.parse(a);
					json.put("mtrCalibDate", format.format(date));
				} else
					json.put("mtrCalibDate", "");
				json.put("bttryLmtVal", e.getBttryLmtVal());
				json.put("intMtrReading", e.getIntMtrReading());
				json.put("consInitMtrForNextSms", e.getConsContNum());
				json.put("ltrPerPulse", e.getLtrPerPulse());
				json.put("fldPicture", e.getFldPicture());
				json.put("trfPerLtr", e.getTrfPerLtr());
				json.put("mobRcpt1", e.getMobRcpt1());
				json.put("mobRcpt2", e.getMobRcpt2());
				json.put("mobRcpt3", e.getMobRcpt3());
				json.put("mailiRcpt1", e.getMailiRcpt1());
				json.put("mailiRcpt2", e.getMailiRcpt2());
				json.put("mailiRcpt3", e.getMailiRcpt3());
				json.put("lat", e.getLat());
				json.put("lon", e.getLon());
				json.put("consId", e.getConsId());
				json.put("consAddr", e.getConsAddr());
				json.put("consContNum", e.getConsContNum());
				json.put("mtrSlNo", e.getMtrSlNo());
				json.put("oemName", e.getOemName());
				json.put("meterType", e.getMeterType());
				json.put("meterflowType", e.getMeterflowType());
				json.put("mtrBillType", e.getMtrBillType());
				json.put("gsmSglStrgh", e.getGsmSglStrgh());
				json.put("mtrMdlNum", e.getMtrMdlNum());
				json.put("modOfComm", e.getModOfComm());
				json.put("gprsType", e.getGprsType());
				json.put("gprsTypeUrl", e.getGprsTypeUrl());
				json.put("commPeriod", e.getCommPeriod());
				json.put("rechaDate", e.getRechaDate());
				json.put("srvProvider", e.getSrvProvider());
				json.put("daysOfWaterFlow", e.getDaysOfWaterFlow());
				json.put("fromWaterFlow", e.getFromWaterFlow());
				json.put("toWaterFlow", e.getToWaterFlow());
				json.put("avgPressure", e.getAvgPressure());
				json.put("avgFlowRate", e.getAvgFlowRate());
				json.put("active", e.isActive());
				json.put("billGroupId", e.getBillGroupId());
				json.put("firstReading", e.getFirstReading());
				json.put("mapVal", e.getMapVal());
				json.put("firstPulse", e.getFirstPulse());
				json.put("mapPulse", e.getMapPulse());
				json.put("unit", e.getMeterUnit());

				obj.put("issuccess", true);
				obj.put("error", null);
				obj.put("data", json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/deleteUnitSummary")
    public JSONObject deleteUnitSummary(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        UnitSummary us = new UnitSummary();
        try {
        	if(stringToParse!=null) {
	        	json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	if(data.get("id")!=null)
	        		us.setId(Integer.parseInt(data.get("id").toString()));
	        	us = usr.findById(us.getId());
	        	if(data.get("active")!=null)
	        		us.setActive(Boolean.parseBoolean(data.get("active").toString()));
	        	
	        	us = usr.save(us);
	        	json.put("data", us);
	        	
	        	ac.writeLog("Unit is deleted", "Unit is : "+us.getUnitId(), alr);
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/insertOrganization")
    public JSONObject insertOrganization(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        Organization org = new Organization();
        try {
        	if(stringToParse!=null) {
	        	json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	if(data.get("code")!=null)
	        		org.setCode(data.get("code").toString());
	        	if(data.get("orgName")!=null)
	        		org.setOrgName(data.get("orgName").toString());
	        	if(data.get("orgTypeId")!=null)
	        		org.setOrgTypeId(Integer.parseInt(data.get("orgTypeId").toString()));
	        	if(data.get("parentId")!=null)
	        		org.setParentId(Integer.parseInt(data.get("parentId").toString()));
	        	if(data.get("createdby")!=null)
	        		org.setCrBy(data.get("createdby").toString());
	        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        	dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	        	Date date = new Date();
	        	org.setCrTime(dateFormat.parse(dateFormat.format(date)));
	        	
	        	org = or.save(org);
	        	
	        	ac.writeLog("Organization is inserted", "Unit is : "+org.getOrgId(), alr);
        	}
        } catch (Exception e) {
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/insertRole")
    public JSONObject insertRole(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        Roles role = new Roles();
        try {
        	if(stringToParse!=null) {
	        	json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	if(data.get("roleName")!=null)
	        		role.setRoleName(data.get("roleName").toString());
	        	if(data.get("desc")!=null)
	        		role.setDesc(data.get("desc").toString());
	        	if(data.get("orgId")!=null)
	        		role.setOrgId(Integer.parseInt(data.get("orgId").toString()));
	        	if(data.get("active")!=null)
	        		role.setActive(Boolean.parseBoolean(data.get("active").toString()));
	        	if(data.get("sysRole")!=null)
	        		role.setSysRole(Boolean.parseBoolean(data.get("sysRole").toString()));
	        	
	        	role = rr.save(role);
	        	
	        	ac.writeLog("Role is inserted", "Role id is : "+role.getRoleId(), alr);
        	}
        } catch (Exception e) {
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/insertEmployee")
    public JSONObject insertEmployee(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        LoginMaster lm = new LoginMaster();
        Employee emp = new Employee();
        EmployeeLoginMapping elmp = new EmployeeLoginMapping();
        try {
        	if(stringToParse!=null) {
	        	json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	
	        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        	dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	        	Date date = new Date();
	        	
	        	if(data.get("firstName")!=null)
	        		emp.setFirstName(data.get("firstName").toString());
	        	if(data.get("midName")!=null)
	        		emp.setMidName(data.get("midName").toString());
	        	if(data.get("lastName")!=null)
	        		emp.setLastName(data.get("lastName").toString());
	        	if(data.get("loginType")!=null)
	        		emp.setLoginType(Integer.parseInt(data.get("loginType").toString()));
	        	else
	        		emp.setLoginType(2);
	        	if(data.get("photograph")!=null)
	        		emp.setPhotograph(data.get("photograph").toString());
	        	if(data.get("dob")!=null)
	        		emp.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(data.get("dob").toString()));
	        	if(data.get("maritalStatus")!=null)
	        		emp.setMaritalStatus(Integer.parseInt(data.get("maritalStatus").toString()));
	        	if(data.get("sex")!=null)
	        		emp.setSex(Integer.parseInt(data.get("sex").toString()));
	        	if(data.get("isApproved")!=null)
	        		emp.setIsApproved(Integer.parseInt(data.get("isApproved").toString()));
	        	emp.setCrDt(dateFormat.parse(dateFormat.format(date)));
	        	if(data.get("userCr")!=null)
	        		emp.setUserCr(Integer.parseInt(data.get("userCr").toString()));
	        	if(data.get("rrNum")!=null)
	        		emp.setRrNum(data.get("rrNum").toString());
	        	if(data.get("address")!=null)
	        		emp.setAddress(data.get("address").toString());
	        	if(data.get("mobNum")!=null)
	        		emp.setMobNum(data.get("mobNum").toString());
	        	if(data.get("message")!=null)
	        		emp.setMessage(data.get("message").toString());
	        	if(data.get("email")!=null)
	        		emp.setEmail(data.get("email").toString());
	        	if(data.get("oem")!=null)
	        		emp.setOem(data.get("oem").toString());
	        	if(data.get("heads")!=null)
	        		emp.setHeads(data.get("heads").toString());
	        	if(data.get("designation")!=null)
	        		emp.setDesignation(data.get("designation").toString());
	        	if(data.get("divId")!=null)
	        		emp.setDivId(Integer.parseInt(data.get("divId").toString()));
	        	if(data.get("divId")!=null)
	        		emp.setOrgId(Integer.parseInt(data.get("divId").toString()));
	        	Organization o = or.findByOrgId(Integer.parseInt(data.get("subDivId").toString()));
	        	emp.setDivId(o.getParentId());
	        	emp.setOrgId(o.getParentId());
	        	if(data.get("subDivId")!=null)
	        		emp.setSubDivId(Integer.parseInt(data.get("subDivId").toString()));
	        	if(data.get("landingPage")!=null)
	        		emp.setLandingPage(data.get("landingPage").toString());
	        	if(data.get("empAdd")!=null)
	        		emp.setEmpAdd(data.get("empAdd").toString());
	        	if(data.get("empAge")!=null)
	        		emp.setEmpAge(Integer.parseInt(data.get("empAge").toString()));
	        	if(data.get("salary")!=null)
	        		emp.setSalary(Integer.parseInt(data.get("salary").toString()));
	        	
	        	if(data.get("loginName")!=null)
	        		lm.setLoginName(data.get("loginName").toString());
	        	if(data.get("password")!=null)
	        		lm.setPassword(getPasswordHash(data.get("password").toString()));
	        	if(data.get("roles")!=null)
	        		lm.setRoles(Integer.parseInt(data.get("roles").toString()));
	        	lm.setActive(true);
	        	lm.setLoginFailedCount(0);
	        	lm.setFirstLogininNew(true);
	        	lm.setDefaultRole(1);
	        	/*if(data.get("active")!=null)
	        		lm.setActive(Boolean.parseBoolean(data.get("active").toString()));
	        	if(data.get("loginFailedCount")!=null)
	        		lm.setLoginFailedCount(Integer.parseInt(data.get("loginFailedCount").toString()));
	        	if(data.get("firstLogin")!=null)
	        		lm.setFirstLogininNew(Boolean.parseBoolean(data.get("firstLogin").toString()));
	        	if(data.get("defaultRole")!=null)
	        		lm.setDefaultRole(Integer.parseInt(data.get("defaultRole").toString()));*/
	        	
	        	emp = er.save(emp);
	        	lm = lmr.save(lm);
	        	
	        	elmp.setEmployeeId(emp.getEmpId());
	        	elmp.setLoginName(lm.getLoginName());
	        	elmpr.save(elmp);
	        	
	        	ac.writeLog("Employee is inserted", "Employee id is : "+emp.getEmpId(), alr);
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/updateEmployee")
    public JSONObject updateEmployee(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        LoginMaster lm = new LoginMaster();
        Employee emp = new Employee();
        EmployeeLoginMapping elmp = new EmployeeLoginMapping();
        try {
        	if(stringToParse!=null) {
	        	json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	
	        	if(data.get("userName")!=null) {
	        		lm = lmr.findByLoginName(data.get("loginName").toString());
        			EmployeeLoginMapping elm = new EmployeeLoginMapping();
        			elm = elmpr.findByLoginName(lm.getLoginName());
        			emp = er.findByEmpId(elm.getEmployeeId());
	        	}
	        	
	        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        	dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	        	Date date = new Date();
	        	
	        	if(data.get("firstName")!=null)
	        		emp.setFirstName(data.get("firstName").toString());
	        	if(data.get("midName")!=null)
	        		emp.setMidName(data.get("midName").toString());
	        	if(data.get("lastName")!=null)
	        		emp.setLastName(data.get("lastName").toString());
	        	if(data.get("loginType")!=null)
	        		emp.setLoginType(Integer.parseInt(data.get("loginType").toString()));
	        	else
	        		emp.setLoginType(1);
	        	if(data.get("photograph")!=null)
	        		emp.setPhotograph(data.get("photograph").toString());
	        	if(data.get("dob")!=null)
	        		emp.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(data.get("dob").toString()));
	        	if(data.get("maritalStatus")!=null)
	        		emp.setMaritalStatus(Integer.parseInt(data.get("maritalStatus").toString()));
	        	if(data.get("sex")!=null)
	        		emp.setSex(Integer.parseInt(data.get("sex").toString()));
	        	if(data.get("isApproved")!=null)
	        		emp.setIsApproved(Integer.parseInt(data.get("isApproved").toString()));
	        	emp.setCrDt(dateFormat.parse(dateFormat.format(date)));
	        	if(data.get("userCr")!=null)
	        		emp.setUserCr(Integer.parseInt(data.get("userCr").toString()));
	        	if(data.get("rrNum")!=null)
	        		emp.setRrNum(data.get("rrNum").toString());
	        	if(data.get("address")!=null)
	        		emp.setAddress(data.get("address").toString());
	        	if(data.get("mobNum")!=null)
	        		emp.setMobNum(data.get("mobNum").toString());
	        	if(data.get("message")!=null)
	        		emp.setMessage(data.get("message").toString());
	        	if(data.get("email")!=null)
	        		emp.setEmail(data.get("email").toString());
	        	if(data.get("oem")!=null)
	        		emp.setOem(data.get("oem").toString());
	        	if(data.get("heads")!=null)
	        		emp.setHeads(data.get("heads").toString());
	        	if(data.get("designation")!=null)
	        		emp.setDesignation(data.get("designation").toString());
	        	if(data.get("divId")!=null)
	        		emp.setDivId(Integer.parseInt(data.get("divId").toString()));
	        	if(data.get("subDivId")!=null)
	        		emp.setSubDivId(Integer.parseInt(data.get("subDivId").toString()));
	        	if(data.get("landingPage")!=null)
	        		emp.setLandingPage(data.get("landingPage").toString());
	        	if(data.get("empAdd")!=null)
	        		emp.setEmpAdd(data.get("empAdd").toString());
	        	if(data.get("empAge")!=null)
	        		emp.setEmpAge(Integer.parseInt(data.get("empAge").toString()));
	        	if(data.get("salary")!=null)
	        		emp.setSalary(Integer.parseInt(data.get("salary").toString()));
	        	
	        	if(data.get("loginName")!=null)
	        		lm.setLoginName(data.get("loginName").toString());
	        	if(data.get("password")!=null)
	        		lm.setPassword(getPasswordHash(data.get("password").toString()));
	        	if(data.get("roles")!=null)
	        		lm.setRoles(Integer.parseInt(data.get("roles").toString()));
	        	lm.setActive(true);
	        	lm.setLoginFailedCount(0);
	        	lm.setFirstLogininNew(true);
	        	lm.setDefaultRole(1);
	        	/*if(data.get("active")!=null)
	        		lm.setActive(Boolean.parseBoolean(data.get("active").toString()));
	        	if(data.get("loginFailedCount")!=null)
	        		lm.setLoginFailedCount(Integer.parseInt(data.get("loginFailedCount").toString()));
	        	if(data.get("firstLogin")!=null)
	        		lm.setFirstLogininNew(Boolean.parseBoolean(data.get("firstLogin").toString()));
	        	if(data.get("defaultRole")!=null)
	        		lm.setDefaultRole(Integer.parseInt(data.get("defaultRole").toString()));*/
	        	
	        	emp = er.save(emp);
	        	lm = lmr.save(lm);
	        	
	        	elmp.setEmployeeId(emp.getEmpId());
	        	elmp.setLoginName(lm.getLoginName());
	        	elmpr.save(elmp);
	        	
	        	ac.writeLog("Employee is inserted", "Employee id is : "+emp.getEmpId(), alr);
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/insertMeterData")
    public JSONObject insertMeterData(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        TempMessageData md = new TempMessageData();
        UnitConsumptionDetails uc = new UnitConsumptionDetails();
        
        try {
        	if(stringToParse!=null) {
		        json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	
	        	if(data.get("unitId")!=null)
	        		md.setUnitId(Integer.parseInt(data.get("unitId").toString()));
	        	if(data.get("last24HrsData")!=null)
	        		md.setLast24Hrs(data.get("last24HrsData").toString());
	        	if(data.get("prevDayCons")!=null)
	        		md.setPrevDay(Integer.parseInt(data.get("prevDayCons").toString()));
	        	if(data.get("prevToPrevDayCons")!=null)
	        		md.setPrevToPrevDay(Integer.parseInt(data.get("prevToPrevDayCons").toString()));
	        	if(data.get("tamperStat")!=null)
	        		md.setTampStat(Integer.parseInt(data.get("tamperStat").toString()));
	        	if(data.get("battVolt")!=null)
	        		md.setBattVolt(Double.parseDouble(data.get("battVolt").toString()));
	        	if(data.get("temperature")!=null)
	        		md.setTempr(Double.parseDouble(data.get("temperature").toString()));
	        	if(data.get("reversePulseCount")!=null)
	        		md.setPulseCount(Integer.parseInt(data.get("reversePulseCount").toString()));
	        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        	df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        	String strTime = data.get("date").toString() + " " + data.get("time").toString();
	        	Date d = df.parse(strTime);
	        	if(data.get("date")!=null)
	        		md.setDate(d);
	        	if(data.get("time")!=null)
	        		md.setTime(d);
	        	md = tmdr.save(md);
	        	ac.writeLog("Message data is inserted", "Message id is : "+md.getId(), alr);
        	}
        } catch (Exception e) {
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/meterDataReceiver")
    public JSONObject meterDataReceiver(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        MessageData md = new MessageData();
        MessageRejected mr = new MessageRejected();
        UnitConsumptionDetails uc = new UnitConsumptionDetails();
        
        try {
        	if(stringToParse!=null) {
		        json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	
	        	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	            Date date1 = sdf.parse(data.get("date").toString());
	            Date date = new Date();
	            
	        	if(data.get("unitId")!=null &&
	        		date1.compareTo(date) == 0 &&
	        		data.get("last24HrsData") !=null && Integer.parseInt(data.get("last24HrsData").toString()) > 0 &&
	        		Integer.parseInt(data.get("prevDayCons").toString()) != Integer.parseInt(data.get("prevToPrevDayCons").toString()) && 
	        		usr.findByUnitId(data.get("unitId").toString()) != null
	        		
	        			) {
		        	if(data.get("unitId")!=null)
		        		md.setUnitId(Integer.parseInt(data.get("unitId").toString()));
		        	if(data.get("last24HrsData")!=null)
		        		md.setLast24Hrs(data.get("last24HrsData").toString());
		        	if(data.get("prevDayCons")!=null)
		        		md.setPrevDay(Integer.parseInt(data.get("prevDayCons").toString()));
		        	if(data.get("prevToPrevDayCons")!=null)
		        		md.setPrevToPrevDay(Integer.parseInt(data.get("prevToPrevDayCons").toString()));
		        	if(data.get("tamperStat")!=null)
		        		md.setTampStat(Integer.parseInt(data.get("tamperStat").toString()));
		        	if(data.get("battVolt")!=null)
		        		md.setBattVolt(Double.parseDouble(data.get("battVolt").toString()));
		        	if(data.get("temperature")!=null)
		        		md.setTempr(Double.parseDouble(data.get("temperature").toString()));
		        	if(data.get("reversePulseCount")!=null)
		        		md.setPulseCount(Integer.parseInt(data.get("reversePulseCount").toString()));
		        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		        	df.setTimeZone(TimeZone.getTimeZone("UTC"));
		        	String strTime = data.get("date").toString() + " " + data.get("time").toString();
		        	Date d = df.parse(strTime);
		        	if(data.get("date")!=null)
		        		md.setDate(d);
		        	if(data.get("time")!=null)
		        		md.setTime(d);
		        	md.setTampStat(0);
		        	md = mdr.save(md);
		        	
		        	uc.setAddeDat(md.getDate());
		        	uc.setDateRecorded(md.getDate());
		        	uc.setConsInMcube(Double.valueOf(md.getPrevDay()));
		        	uc.setDayCons(Double.valueOf(md.getPrevToPrevDay()));
		        	uc.setMsgDataId(md.getId());
		        	uc.setUnitId(md.getUnitId());
		        	uc = ucdr.save(uc);
		        	
		        	ac.writeLog("Message data is inserted", "Message id is : "+md.getId(), alr);
		        	
	        	}
	        	else {
	        		if(data.get("unitId")!=null)
		        		mr.setUnitId(data.get("unitId").toString());
		        	mr.setMessage(data.get("unitId").toString());
		        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		        	df.setTimeZone(TimeZone.getTimeZone("UTC"));
		        	String strTime = data.get("date").toString() + " " + data.get("time").toString();
		        	Date d = df.parse(strTime);
		        	if(data.get("date")!=null)
		        		mr.setSentDate(d);
		        	if(data.get("time")!=null)
		        		mr.setTime(d);
		        	
		        	mr = mrr.save(mr);
		        	
		        	ac.writeLog("Rejected Message data is inserted", "Message id is : "+mr.getId(), alr);
	        	}
        	}
        } catch (Exception e) {
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/meterDataCsv")
    public JSONObject meterDataCsv(@RequestParam("file") MultipartFile file) throws java.text.ParseException{
		
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        TempMessageData md = new TempMessageData();
        MessageRejected mr = new MessageRejected();
        UnitConsumptionDetails uc = new UnitConsumptionDetails();
        try {
        	String COMMA_DELIMITER = ",";
        	List<List<String>> records = new ArrayList<>();
        	
        	/*try (BufferedReader br = new BufferedReader(new FileReader("D:\\gprsdata.csv"))) {
        	    String line;
        	    while ((line = br.readLine()) != null) {
        	        String[] values = line.split(COMMA_DELIMITER);
        	        records.add(Arrays.asList(values));
        	    }
        	}*/
        	//this is also works 
        	String a = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
    		String b = file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf('.'));
    		Path tempFile = Files.createTempFile(b, a);
    		
    		Files.write(tempFile, file.getBytes());
    		System.out.println("uploading: " + tempFile);
    		File file1 = tempFile.toFile();
        	//try (Scanner scanner = new Scanner(new File("D:\\gprsdata.csv"));) {
        	System.out.println(getUserFileResource(file).getFilename());
        	try (Scanner scanner = new Scanner(new File(tempFile.toString()));) {	
        	    while (scanner.hasNextLine()) {
        	        records.add(getRecordFromLine(scanner.nextLine()));
        	    }
        	}
        	
        	if(records.size()>1) {
		        json.put("issuccess", true);
	        	json.put("code", 200);
	        	for(int i=1; i<records.size(); i++) {
	        		md = new TempMessageData();
	        		if(records.get(i).get(0)!=null)
	        			md.setUnitId(Integer.parseInt(records.get(i).get(0)));
	        		if(records.get(i).get(1)!=null)
		        		md.setLast24Hrs(records.get(i).get(1));
	        		if(records.get(i).get(2)!=null)
		        		md.setPrevDay(Integer.parseInt(records.get(i).get(2)));
	        		if(records.get(i).get(3)!=null)
		        		md.setPrevToPrevDay(Integer.parseInt(records.get(i).get(3)));
	        		if(records.get(i).get(4)!=null)
		        		md.setTampStat(Integer.parseInt(records.get(i).get(4)));
	        		if(records.get(i).get(5)!=null)
		        		md.setBattVolt(Double.parseDouble(records.get(i).get(5)));
	        		if(records.get(i).get(6)!=null)
		        		md.setTempr(Double.parseDouble(records.get(i).get(6)));
	        		if(records.get(i).get(7)!=null)
		        		md.setPulseCount(Integer.parseInt(records.get(i).get(7)));
	        		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	            	df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        		String strTime = records.get(i).get(8) + " " +records.get(i).get(9);
        	        Date d = df.parse(strTime);
        	        md.setDate(d);
        	        md.setTime(d);
		        	md = tmdr.save(md);
		        	ac.writeLog("Message data is inserted", "Message id is : "+md.getId(), alr);
	        	}
        	}
        } catch (Exception e) {
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/unitsConsumptionDataCsv")
    public JSONObject unitsConsumptionDataCsv() throws java.text.ParseException{
		
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        TempMessageData md = new TempMessageData();
        UnitConsumptionDetails uc = new UnitConsumptionDetails();
        try {
        	String COMMA_DELIMITER = ",";
        	List<List<String>> records = new ArrayList<>();
        	
        	try (BufferedReader br = new BufferedReader(new FileReader("D:\\gprsdata.csv"))) {
        	    String line;
        	    while ((line = br.readLine()) != null) {
        	        String[] values = line.split(COMMA_DELIMITER);
        	        records.add(Arrays.asList(values));
        	    }
        	}
        	
        	if(records.size()>1) {
		        json.put("issuccess", true);
	        	json.put("code", 200);
	        	for(int i=1; i<records.size(); i++) {
	        		md = new TempMessageData();
	        		if(records.get(i).get(0)!=null)
	        			md.setUnitId(Integer.parseInt(records.get(i).get(0)));
	        		if(records.get(i).get(1)!=null)
		        		md.setLast24Hrs(records.get(i).get(1));
	        		if(records.get(i).get(2)!=null)
		        		md.setPrevDay(Integer.parseInt(records.get(i).get(2)));
	        		if(records.get(i).get(3)!=null)
		        		md.setPrevToPrevDay(Integer.parseInt(records.get(i).get(3)));
	        		if(records.get(i).get(4)!=null)
		        		md.setTampStat(Integer.parseInt(records.get(i).get(4)));
	        		if(records.get(i).get(5)!=null)
		        		md.setBattVolt(Double.parseDouble(records.get(i).get(5)));
	        		if(records.get(i).get(6)!=null)
		        		md.setTempr(Double.parseDouble(records.get(i).get(6)));
	        		if(records.get(i).get(7)!=null)
		        		md.setPulseCount(Integer.parseInt(records.get(i).get(7)));
	        		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	            	df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        		String strTime = records.get(i).get(8) + " " +records.get(i).get(9);
        	        Date d = df.parse(strTime);
        	        md.setDate(d);
        	        md.setTime(d);
		        	md = tmdr.save(md);
		        	ac.writeLog("Message data is inserted", "Message id is : "+md.getId(), alr);
	        	}
        	}
        } catch (Exception e) {
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/meterDataCsv1")
    public JSONObject meterDataCsv1() throws java.text.ParseException{
		
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        TempMessageData md = new TempMessageData();
        MessageRejected mr = new MessageRejected();
        UnitConsumptionDetails uc = new UnitConsumptionDetails();
        try {
        	String COMMA_DELIMITER = ",";
        	List<List<String>> records = new ArrayList<>();
        	//try (BufferedReader br = new BufferedReader(new FileReader("D:\\gprsdata.csv"))) {
        	try (BufferedReader br = new BufferedReader(new FileReader("D:\\ApacheTomcatServer\\webapps\\gprsdata.csv"))) {
        	    String line;
        	    while ((line = br.readLine()) != null) {
        	        String[] values = line.split(COMMA_DELIMITER);
        	        records.add(Arrays.asList(values));
        	    }
        	}
        	/*//this is also works 
        	String a = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
    		String b = file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf('.'));
    		Path tempFile = Files.createTempFile(b, a);
    		
    		Files.write(tempFile, file.getBytes());
    		System.out.println("uploading: " + tempFile);
    		File file1 = tempFile.toFile();
        	//try (Scanner scanner = new Scanner(new File("D:\\gprsdata.csv"));) {
        	System.out.println(getUserFileResource(file).getFilename());
        	try (Scanner scanner = new Scanner(new File(tempFile.toString()));) {	
        	    while (scanner.hasNextLine()) {
        	        records.add(getRecordFromLine(scanner.nextLine()));
        	    }
        	}*/
        	
        	if(records.size()>1) {
		        json.put("issuccess", true);
	        	json.put("code", 200);
	        	for(int i=1; i<records.size(); i++) {
	        		md = new TempMessageData();
	        		if(records.get(i).get(0)!=null)
	        			md.setUnitId(Integer.parseInt(records.get(i).get(0)));
	        		if(records.get(i).get(1)!=null)
		        		md.setLast24Hrs(records.get(i).get(1));
	        		if(records.get(i).get(2)!=null)
		        		md.setPrevDay(Integer.parseInt(records.get(i).get(2)));
	        		if(records.get(i).get(3)!=null)
		        		md.setPrevToPrevDay(Integer.parseInt(records.get(i).get(3)));
	        		if(records.get(i).get(4)!=null)
		        		md.setTampStat(Integer.parseInt(records.get(i).get(4)));
	        		if(records.get(i).get(5)!=null)
		        		md.setBattVolt(Double.parseDouble(records.get(i).get(5)));
	        		if(records.get(i).get(6)!=null)
		        		md.setTempr(Double.parseDouble(records.get(i).get(6)));
	        		if(records.get(i).get(7)!=null)
		        		md.setPulseCount(Integer.parseInt(records.get(i).get(7)));
	        		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	            	df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        		String strTime = records.get(i).get(8) + " " +records.get(i).get(9);
        	        Date d = df.parse(strTime);
        	        md.setDate(d);
        	        md.setTime(d);
		        	md = tmdr.save(md);
		        	ac.writeLog("Message data is inserted", "Message id is : "+md.getId(), alr);
	        	}
        	}
        } catch (Exception e) {
        	json.put("issuccess", false);
        	json.put("code", e.toString());
		}
        
		return json;
	}
	
	@GetMapping("/taskForTempMsgMoveToMsgData")
	public void taskForTempMsgMoveToMsgData() throws java.text.ParseException {
    	try {
	    	List<TempMessageData> msg = new ArrayList<>();
			tmdr.findAll().forEach(msg::add);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	        Date date = new Date();
	        
			for (TempMessageData m : msg) {
				
				if(m.getUnitId()!=null && m.getDate()!=null && m.getLast24Hrs()!=null// && Integer.parseInt(m.getLast24Hrs()) > 0 &&
		        	//(m.getPrevDay() != m.getPrevToPrevDay()) && usr.findByUnitId(m.getUnitId().toString()) != null
		        	) {
					
					MessageData md = new MessageData();
					md.setBattVolt(m.getBattVolt());
					md.setDate(m.getDate());
					md.setLast24Hrs(m.getLast24Hrs());
					md.setPrevDay(m.getPrevDay());
					md.setPrevToPrevDay(m.getPrevToPrevDay());
					md.setPulseCount(m.getPulseCount());
					md.setTime(m.getTime());
					md.setUnitId(m.getUnitId());
					md.setTampStat(0);
					mdr.save(md);
					
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					//df.setTimeZone(TimeZone.getTimeZone("UTC"));
					//df.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
					String strTime = sdf.format(m.getDate()) + " " + time.format(m.getTime());
					Date d = df.parse(strTime);
					d = new Date(d.getTime() - 3600 * 100*55);
					strTime = sdf.format(m.getDate()) + " " + time.format(d.getTime());
					d = df.parse(strTime);
					
					UnitConsumptionDetails uc = new UnitConsumptionDetails();
					uc.setAddeDat(d);
		        	uc.setDateRecorded(md.getDate());
		        	uc.setConsInMcube(Double.valueOf(md.getPrevDay()));
		        	uc.setDayCons(Double.valueOf(md.getPrevToPrevDay()));
		        	uc.setMsgDataId(md.getId());
		        	uc.setUnitId(md.getUnitId());
		        	uc = ucdr.save(uc);
				}
				else {
					MessageRejected mr = new MessageRejected();
		        	mr.setUnitId(m.getUnitId().toString());
		        	mr.setMessage(m.getUnitId().toString());
		        	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					df.setTimeZone(TimeZone.getTimeZone("UTC"));
		        	if(m.getDate()!=null)
		        		mr.setSentDate(m.getDate());
		        	if(m.getTime()!=null)
		        		mr.setTime(m.getTime());
		        	
		        	mr = mrr.save(mr);
				}
				
				tmdr.delete(m);
			}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@GetMapping("/taskForInsertMsgData")
	public void taskForInsertMsgData() throws java.text.ParseException {
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		Date d=null;
		Date d1=null;
		String date="";
		String df = "yyyy-MM-dd";
		String tf = "HH";
		Integer Min = 0;
		Integer Max = 30;
		Double cons = 0.0;
		Double pcons = 0.0;
		DateFormat dateFormat = new SimpleDateFormat(df);
		DateFormat timeFormat = new SimpleDateFormat(tf);
		String strDate = dateFormat.format(new Date());
		String strTime = timeFormat.format(new Date());
		Integer t1 = 0;
		Integer t2 = Integer.parseInt(strTime);
		Calendar c = Calendar.getInstance();
		
    	try {
    		List<Object> ob = new ArrayList<>();
    		ob = ucdr.getMaxConsCons();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    			    Object[] obj2 = (Object[]) itr.next();
	    			cons = Double.parseDouble(String.valueOf(obj2[1]));
	    			pcons = Double.parseDouble(String.valueOf(obj2[1]));
	    			date = String.valueOf(obj2[2]);
	    			t1 = Integer.parseInt(String.valueOf(obj2[4]))+1;
	    			d = new SimpleDateFormat(df).parse(date);
	    			d1 = new SimpleDateFormat(df).parse(strDate);
	    			c.setTime(d);
	    			c.add(Calendar.DATE, 1);
	    			Integer unitid = Integer.parseInt(String.valueOf(obj2[0]));
	    			
	    			while(d.compareTo(d1) <= 0){
	    				if(d.compareTo(d1) < 0){
	    					while(t1<24) {
	    						cons = cons + Min + (int)(Math.random() * ((Max - Min) + 1));
	    						MessageData md = new MessageData();
	    						md.setBattVolt(5.0);
	    						md.setLast24Hrs("0");
	    						md.setPrevDay(new java.lang.Double(cons).intValue());
	    						md.setPrevToPrevDay(new java.lang.Double(pcons).intValue());
	    						md.setPulseCount(0);
	    						md.setUnitId(unitid);
	    						md.setTampStat(0);
	    						DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		            	df1.setTimeZone(TimeZone.getTimeZone("UTC"));
	    		            	String td = sdf.format(d) + " " +t1+":15:32";
	    	        	        Date d3 = df1.parse(td);
	    	        	        md.setDate(d3);
	    	        	        md.setTime(d3);
	    						md = mdr.save(md);
	    						
	    						DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    						SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	    						strTime = sdf.format(md.getDate()) + " " + time.format(md.getTime());
	    						Date d4 = df4.parse(strTime);
	    						d4 = new Date(d4.getTime() - 3600 * 100*55);
	    						strTime = sdf.format(md.getDate()) + " " + time.format(d4.getTime());
	    						d4 = df4.parse(strTime);
	    						
	    						UnitConsumptionDetails uc = new UnitConsumptionDetails();
	    						uc.setAddeDat(d4);
	    			        	uc.setDateRecorded(md.getDate());
	    			        	uc.setConsInMcube(Double.valueOf(md.getPrevDay()));
	    			        	uc.setDayCons(Double.valueOf(md.getPrevToPrevDay()));
	    			        	uc.setMsgDataId(md.getId());
	    			        	uc.setUnitId(md.getUnitId());
	    			        	uc.setConsumption(Double.valueOf(md.getPrevDay())-Double.valueOf(md.getPrevToPrevDay()));
	    			        	uc = ucdr.save(uc);
	    						t1++;
	    						pcons = cons;
	    					}
	    				} else if(d.compareTo(d1)==0){
	    					while(t2>=t1) {
	    						cons = cons + Min + (int)(Math.random() * ((Max - Min) + 1));
	    						MessageData md = new MessageData();
	    						md.setBattVolt(5.0);
	    						md.setDate(d);
	    						md.setLast24Hrs("0");
	    						md.setPrevDay(new java.lang.Double(cons).intValue());
	    						md.setPrevToPrevDay(new java.lang.Double(pcons).intValue());
	    						md.setPulseCount(0);
	    						md.setUnitId(unitid);
	    						md.setTampStat(0);
	    						DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    		            	df1.setTimeZone(TimeZone.getTimeZone("UTC"));
	    		            	String td = sdf.format(d) + " " +t1+":15:32";
	    	        	        Date d3 = df1.parse(td);
	    	        	        md.setDate(d3);
	    	        	        md.setTime(d3);
	    						md = mdr.save(md);
	    						
	    						DateFormat df4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    						SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	    						strTime = sdf.format(md.getDate()) + " " + time.format(md.getTime());
	    						Date d4 = df4.parse(strTime);
	    						d4 = new Date(d4.getTime() - 3600 * 100*55);
	    						strTime = sdf.format(md.getDate()) + " " + time.format(d4.getTime());
	    						d4 = df4.parse(strTime);
	    						
	    						UnitConsumptionDetails uc = new UnitConsumptionDetails();
	    						uc.setAddeDat(d4);
	    			        	uc.setDateRecorded(md.getDate());
	    			        	uc.setConsInMcube(Double.valueOf(md.getPrevDay()));
	    			        	uc.setDayCons(Double.valueOf(md.getPrevToPrevDay()));
	    			        	uc.setConsumption(Double.valueOf(md.getPrevDay())-Double.valueOf(md.getPrevToPrevDay()));
	    			        	uc.setMsgDataId(md.getId());
	    			        	uc.setUnitId(md.getUnitId());
	    			        	uc = ucdr.save(uc);
	    						t1++;
	    						pcons = cons;
	    					}
	    				}
	    				t1 = 0;
	    				c.setTime(d);
	    				c.add(Calendar.DATE, 1);
	    				d = c.getTime();
	    			}
    			}
    			ucdr.updateConsRecordDate();
    		}
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@GetMapping("/taskForInsertDMAData")
	public void taskForInsertDMAData() throws java.text.ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
    		List<Object> ob = new ArrayList<>();
    		ob = ucdr.getDmaMaxCons();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    			    Object[] obj2 = (Object[]) itr.next();
    			    MessageData md = new MessageData();
    			    String dt = String.valueOf(obj2[2]);
    			    String tm = "23:15:30";
    			    Integer cons = Integer.parseInt(String.valueOf(obj2[1])) + (int)(Math.random() * 1);
					md.setBattVolt(5.0);
					md.setLast24Hrs("0");
					md.setPrevDay(cons);
					md.setPrevToPrevDay(Integer.parseInt(String.valueOf(obj2[1])));
					md.setPulseCount(0);
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        		String strTime = dt + " " +tm;
        	        Date d = df.parse(strTime);
        	        md.setDate(d);
        	        md.setTime(d);
					md.setUnitId(Integer.parseInt(String.valueOf(obj2[0])));
					md.setTampStat(0);
					mdr.save(md);
	    			
	    			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					d = df.parse(strTime);
					
					UnitConsumptionDetails uc = new UnitConsumptionDetails();
					uc.setAddeDat(d);
		        	uc.setDateRecorded(md.getDate());
		        	uc.setConsInMcube(Double.valueOf(md.getPrevDay()));
		        	uc.setDayCons(Double.valueOf(md.getPrevToPrevDay()));
		        	uc.setConsumption(Double.valueOf(md.getPrevDay()));
		        	uc.setMsgDataId(md.getId());
		        	uc.setUnitId(md.getUnitId());
		        	uc = ucdr.save(uc);
    			}
    			ucdr.updateConsRecordDate();
    		}
	        
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/*@PostMapping("/meterDataCsvFromDrive")
    public JSONObject meterDataCsvFromDrive() throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        MessageData md = new MessageData();
        MessageRejected mr = new MessageRejected();
        try {
        	String COMMA_DELIMITER = ",";
        	List<List<String>> records = new ArrayList<>();
        	
        	try (BufferedReader br = new BufferedReader(new FileReader("D:\\gprsdata.csv"))) {
        	    String line;
        	    while ((line = br.readLine()) != null) {
        	        String[] values = line.split(COMMA_DELIMITER);
        	        records.add(Arrays.asList(values));
        	    }
        	}
        	//this is also works 
        	String a = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf('.'));
    		String b = file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf('.'));
    		Path tempFile = Files.createTempFile(b, a);
    		
    		Files.write(tempFile, file.getBytes());
    		System.out.println("uploading: " + tempFile);
    		File file1 = tempFile.toFile();
        	//try (Scanner scanner = new Scanner(new File("D:\\gprsdata.csv"));) {
        	System.out.println(getUserFileResource(file).getFilename());
        	try (Scanner scanner = new Scanner(new File(tempFile.toString()));) {	
        	    while (scanner.hasNextLine()) {
        	        records.add(getRecordFromLine(scanner.nextLine()));
        	    }
        	}
        	
        	if(records.size()>1) {
		        json.put("issuccess", true);
	        	json.put("code", 200);
	        	for(int i=1; i<records.size(); i++) {
	        		md = new MessageData();
	        		if(records.get(i).get(0)!=null)
	        			md.setUnitId(Integer.parseInt(records.get(i).get(0)));
	        		if(records.get(i).get(1)!=null)
		        		md.setLast24Hrs(records.get(i).get(1));
	        		if(records.get(i).get(2)!=null)
		        		md.setPrevDay(Integer.parseInt(records.get(i).get(2)));
	        		if(records.get(i).get(3)!=null)
		        		md.setPrevToPrevDay(Integer.parseInt(records.get(i).get(3)));
	        		if(records.get(i).get(4)!=null)
		        		md.setTampStat(Integer.parseInt(records.get(i).get(4)));
	        		if(records.get(i).get(5)!=null)
		        		md.setBattVolt(Double.parseDouble(records.get(i).get(5)));
	        		if(records.get(i).get(6)!=null)
		        		md.setTempr(Double.parseDouble(records.get(i).get(6)));
	        		if(records.get(i).get(7)!=null)
		        		md.setPulseCount(Integer.parseInt(records.get(i).get(7)));
	        		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	            	df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        		String strTime = records.get(i).get(8) + " " +records.get(i).get(9);
        	        Date d = df.parse(strTime);
        	        md.setDate(d);
        	        md.setTime(d);
		        	
		        	md = mdr.save(md);
	        	}
        	}
        } catch (Exception e) {
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}*/
	
	public static Resource getUserFileResource(MultipartFile file1) throws IOException {
		String a = file1.getOriginalFilename().substring(file1.getOriginalFilename().indexOf('.'));
		String b = file1.getOriginalFilename().substring(0,file1.getOriginalFilename().indexOf('.'));
		Path tempFile = Files.createTempFile(b, a);
		
		Files.write(tempFile, file1.getBytes());
		System.out.println("uploading: " + tempFile);
		File file = tempFile.toFile();
		return new FileSystemResource(file);
	}
	
	private List<String> getRecordFromLine(String line) {
		String COMMA_DELIMITER = ",";
	    List<String> values = new ArrayList<String>();
	    try (Scanner rowScanner = new Scanner(line)) {
	        rowScanner.useDelimiter(COMMA_DELIMITER);
	        while (rowScanner.hasNext()) {
	            values.add(rowScanner.next());
	        }
	    }
	    return values;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getAllOrgList")
    public String getAllOrgList() throws java.text.ParseException{
		List<Organization> org = new ArrayList<>();
		or.findAll().forEach(org::add);
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("issuccess", true);
		obj.put("code", 200);
		obj1.put("error", obj);
		int val = 0;
        for (Organization o : org) {
        	obj = new JSONObject();
            obj.put("id", o.getOrgId());
            obj.put("name", o.getOrgName());
            obj.put("code", o.getCode());
            obj.put("type", o.getOrgTypeId());
            obj.put("parentId", o.getParentId());
            obj.put("crBy", o.getCrBy());
            
            ja.add(val++,obj);
        }
        obj1.put("data", ja);
		return obj1.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getOrgList")
	public String getOrgList() throws java.text.ParseException {
		JSONObject obj = new JSONObject();
		List<Object> ob = new ArrayList<>();
		JSONArray ja = new JSONArray();
		log.debug("Inside getOrgList");
		try {
			ob = or.getOrgList();
			if (ob.size() > 0) {
				Iterator itr = ob.iterator();
				while (itr.hasNext()) {
					JSONObject json = new JSONObject();
					Object[] obj2 = (Object[]) itr.next();
					if (obj2[0] != null)
						json.put("id", String.valueOf(obj2[0]));
					else
						json.put("id", "");
					if (obj2[1] != null)
						json.put("name", String.valueOf(obj2[1]));
					else
						json.put("name", "");
					if (obj2[2] != null)
						json.put("code", String.valueOf(obj2[2]));
					else
						json.put("code", "");
					if (obj2[3] != null)
						json.put("type", String.valueOf(obj2[3]));
					else
						json.put("type", "");
					if (obj2[4] != null)
						json.put("active", String.valueOf(obj2[4]));
					else
						json.put("active", false);
					ja.add(json);
				}
			}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }catch (Exception e) {
        	e.printStackTrace();
		}
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getOrgTypeList")
    public String getOrgTypeList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getOrgTypeList");
        try {
        	ob = or.getOrgTypeList();
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
	    				json.put("name", String.valueOf(obj2[1]));
	    			else
	    				json.put("name", "");
	    			ja.add(json);
    			}
    		}
    		obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
        }catch (Exception e) {
        	e.printStackTrace();
		}
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getRoleList")
    public String getRoleList() throws java.text.ParseException{
		List<Roles> role = new ArrayList<>();
		rr.findAll().forEach(role::add);
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("issuccess", true);
		obj.put("code", 200);
		obj1.put("error", obj);
		int val = 0;
        for (Roles r : role) {
        	obj = new JSONObject();
            obj.put("id", r.getRoleId());
            obj.put("name", r.getRoleName());
            obj.put("desc", r.getDesc());
            obj.put("active", r.isActive());
            obj.put("sysRole", r.isSysRole());
            obj.put("orgId", r.getOrgId());
            if(r.getOrgId()!=0)
            	obj.put("orgName", or.findByOrgId(r.getOrgId()).getOrgName());
            else
            	obj.put("orgName", "");
            
            ja.add(val++,obj);
        }
        obj1.put("data", ja);
		return obj1.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getMenuList")
    public String getMenuList() throws java.text.ParseException{
		List<Menu> menu = new ArrayList<>();
		mr.findAllByOrderByMenuOrderAsc().forEach(menu::add);
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("issuccess", true);
		obj.put("code", 200);
		obj1.put("error", obj);
		int val = 0;
        for (Menu m : menu) {
        	obj = new JSONObject();
            obj.put("order", m.getMenuOrder());
            obj.put("code", m.getCode());
            obj.put("desc", m.getDesc());
            obj.put("parent", m.getParentId());
            obj.put("id", m.getId());
            
            ja.add(val++,obj);
        }
        obj1.put("data", ja);
		return obj1.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getRoleMenuList/{roleid}")
    public String getRoleMenuList(@PathVariable Integer roleid) throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getRoleMenuList");
        
        try {
        	if(roleid!=null) {
        		ob = lmr.getRoleMenuList(roleid);
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
		    				json.put("code", String.valueOf(obj2[1]));
		    			else
		    				json.put("code", "");
		    			if(obj2[2]!=null)
		    				json.put("desc", String.valueOf(obj2[2]));
		    			else
		    				json.put("desc", "");
		    			if(obj2[3]!=null)
		    				json.put("order", String.valueOf(obj2[3]));
		    			else
		    				json.put("order", "");
		    			if(obj2[4]!=null)
		    				json.put("parent", String.valueOf(obj2[4]));
		    			else
		    				json.put("parent", "");
		    			if(obj2[5]!=null)
		    				json.put("status", Boolean.parseBoolean(String.valueOf(obj2[5])));
		    			else
		    				json.put("status", false);
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
	@GetMapping("/getMeterList")
    public String getMeterList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getMeterList");
        
        try {
        	JSONObject json = new JSONObject();
			json.put("id", "1");
			json.put("code", "Consumer");
			ja.add(json);
			json = new JSONObject();
			json.put("id", "2");
			json.put("code", "DMA");
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
	@GetMapping("/getMeterList1")
    public String getMeterList1() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getMeterList");
        
        try {
    		ob = lmr.getMtrList();
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
	    				json.put("code", String.valueOf(obj2[1]));
	    			else
	    				json.put("code", "");
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
	@GetMapping("/getOemList")
    public String getOemList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getOemList");
        
        try {
    		ob = lmr.getOemList();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[2]!=null)
	    				json.put("id", String.valueOf(obj2[2]));
	    			else
	    				json.put("id", "");
	    			if(obj2[0]!=null)
	    				json.put("code", String.valueOf(obj2[0]));
	    			else
	    				json.put("code", "");
	    			if(obj2[1]!=null)
	    				json.put("desc", String.valueOf(obj2[1]));
	    			else
	    				json.put("desc", "");
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
	@GetMapping("/getDivList")
    public String getDivList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getDivList");
        
        try {
    		ob = lmr.getDivList();
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
	    				json.put("code", String.valueOf(obj2[1]));
	    			else
	    				json.put("code", "");
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
	@GetMapping("/getSubDivList/{divid}")
    public String getSubDivList(@PathVariable Integer divid) throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getSubDivList");
        
        try {
        	if(divid!=null) {
        		ob = lmr.getSubDivList(divid);
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
		    				json.put("code", String.valueOf(obj2[1]));
		    			else
		    				json.put("code", "");
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
	@GetMapping("/getStationList/{divid}")
    public String getStationList(@PathVariable Integer divid) throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getStationList");
        
        try {
        	if(divid!=null) {
        		ob = lmr.getStnList(divid);
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
		    				json.put("code", String.valueOf(obj2[1]));
		    			else
		    				json.put("code", "");
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
	@GetMapping("/getUnitTypeList")
    public String getUnitTypeList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getUnitTypeList");
        
        try {
    		ob = usr.getUnitTypeList();
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
	    				json.put("code", String.valueOf(obj2[1]));
	    			else
	    				json.put("code", "");
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
	@GetMapping("/getLocationList/{divid}")
    public String getLocationList(@PathVariable Integer divid) throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getLocationList");
        
        try {
        	if(divid!=null) {
        		ob = lmr.getLocList(divid);
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
		    				json.put("code", String.valueOf(obj2[1]));
		    			else
		    				json.put("code", "");
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
	@GetMapping("/getConsumerList/{divid}")
    public String getConsumerList(@PathVariable Integer divid) throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getConsumerList");
        
        try {
        	if(divid!=null) {
        		ob = lmr.getConsList(divid);
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
		    				json.put("code", String.valueOf(obj2[1]));
		    			else
		    				json.put("code", "");
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
	@GetMapping("/getRejMsgList")
    public String getRejMsgList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getRejMsgList");
        
        try {
    		ob = lmr.getRejMsgList();
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
	    				json.put("msg", String.valueOf(obj2[1]));
	    			else
	    				json.put("msg", "");
	    			if(obj2[2]!=null)
	    				json.put("dt", String.valueOf(obj2[2]));
	    			else
	    				json.put("dt", "");
	    			if(obj2[3]!=null)
	    				json.put("reason", String.valueOf(obj2[3]));
	    			else
	    				json.put("reason", "");
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
	
	@GetMapping("/UpdateQualityDate")
    public String UpdateQualityDate() throws java.text.ParseException{
        log.debug("Inside UpdateQualityDate");
        try {
        	List<QualityData> ql = new ArrayList<>();
    		qdr.findAll().forEach(ql::add);
            for (QualityData q : ql) {
            	q.setDate(new Date());
            	qdr.save(q);
            }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return "Ok";
	}
	
	@GetMapping("/UpdateFtpDate")
    public String UpdateFtpDate() throws java.text.ParseException{
        log.debug("Inside UpdateFtpDate");
        try {
        	ucdr.updateFtpRecordDate();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return "Ok";
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getTariffCategoryList")
    public String getTariffCategoryList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getTariffCategoryList");
        
        try {
    		ob = lmr.getCategoryList();
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
	    				json.put("code", String.valueOf(obj2[1]));
	    			else
	    				json.put("code", "");
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
	@GetMapping("/getAllOemList")
    public String getAllOemList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getAllOemList");
        
        try {
    		ob = lmr.getAllOemList();
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
	    				json.put("code", String.valueOf(obj2[1]));
	    			else
	    				json.put("code", "");
	    			if(obj2[2]!=null)
	    				json.put("desc", String.valueOf(obj2[2]));
	    			else
	    				json.put("desc", "");
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
	@GetMapping("/getOemDetails/{id}")
    public String getOemDetails(@PathVariable Integer id) throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        log.debug("Inside getOemDetails");
        
        try {
        	MasterValue mv = mvr.findById(id);
        	if(mv!=null) {
        		JSONObject json = new JSONObject();
        		json.put("id", mv.getId());
        		json.put("code", mv.getCode());
        		json.put("desc", mv.getDesc());
        		json.put("orgid", mv.getOrgId());
        		obj.put("issuccess", true);
    			obj.put("error", null);
    			obj.put("data", json);
        	}
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@PutMapping("/updateOemData")
    public JSONObject updateOemData(@RequestBody String stringToParse) throws java.text.ParseException{
        JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		
        try {
            json = (JSONObject) parser.parse(stringToParse);
        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        	dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	Date date = new Date();
        	MasterValue mv = new MasterValue();
        	mv = mvr.findById(Integer.parseInt(json.get("id").toString()));
        	mv.setId(Integer.parseInt(json.get("id").toString()));
        	mv.setCode(json.get("code").toString());
        	mv.setDesc(json.get("desc").toString());
        	mv.setModTime(dateFormat.parse(dateFormat.format(date)));
        	mv.setMastersId(5);
        	mv = mvr.save(mv);
        	ac.writeLog("OEM data is updated", "Oem id is : "+mv.getId(), alr);
        	
        	obj1.put("issuccess", true);
    		obj.put("code", 200);
    		obj1.put("error", obj);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj1;
    }
	
	@SuppressWarnings("unchecked")
	@PostMapping("/insertOemData")
    public JSONObject insertOemData(@RequestBody String stringToParse) throws java.text.ParseException{
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		
        try {
            json = (JSONObject) parser.parse(stringToParse);
        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        	dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	Date date = new Date();
        	MasterValue mv = new MasterValue();
        	mv.setCode(json.get("code").toString());
        	mv.setDesc(json.get("desc").toString());
        	mv.setCrTime(dateFormat.parse(dateFormat.format(date)));
        	mv.setMastersId(5);
        	mv.setOrgId(Integer.parseInt(json.get("orgid").toString()));
        	mv.setActive(true);
        	mv.setDeleted(false);
        	mv = mvr.save(mv);
        	ac.writeLog("OEM data is inserted", "Oem id is : "+mv.getId(), alr);
        	
        	obj1.put("issuccess", true);
    		obj.put("code", 200);
    		obj1.put("error", obj);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj1;
    }
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getUnitConfigList")
    public String getUnitConfigList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getUnitConfigList");
        
        try {
    		ob = lmr.getUnitConfList();
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
	    				json.put("unitid", String.valueOf(obj2[1]));
	    			else
	    				json.put("unitid", "");
	    			if(obj2[2]!=null)
	    				json.put("installon", String.valueOf(obj2[2]));
	    			else
	    				json.put("installon", "");
	    			if(obj2[3]!=null)
	    				json.put("size", String.valueOf(obj2[3]));
	    			else
	    				json.put("size", "");
	    			if(obj2[4]!=null)
	    				json.put("cali", String.valueOf(obj2[4]));
	    			else
	    				json.put("cali", "");
	    			if(obj2[5]!=null)
	    				json.put("mob", String.valueOf(obj2[5]));
	    			else
	    				json.put("mob", "");
	    			if(obj2[6]!=null)
	    				json.put("stn", String.valueOf(obj2[6]));
	    			else
	    				json.put("stn", "");
	    			if(obj2[7]!=null)
	    				json.put("loc", String.valueOf(obj2[7]));
	    			else
	    				json.put("loc", "");
	    			if(obj2[8]!=null)
	    				json.put("div", String.valueOf(obj2[8]));
	    			else
	    				json.put("div", "");
	    			if(obj2[9]!=null)
	    				json.put("subdiv", String.valueOf(obj2[9]));
	    			else
	    				json.put("subdiv", "");
	    			if(obj2[10]!=null)
	    				json.put("meter", String.valueOf(obj2[10]));
	    			else
	    				json.put("meter", "");
	    			if(obj2[11]!=null)
	    				json.put("active", String.valueOf(obj2[11]));
	    			else
	    				json.put("active", "");
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
	@GetMapping("/getEmpList")
    public String getEmpList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getEmpList");
        
        try {
    		ob = lmr.getEmpList();
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
	    				json.put("loginName", String.valueOf(obj2[1]));
	    			else
	    				json.put("loginName", "");
	    			if(obj2[2]!=null)
	    				json.put("name", String.valueOf(obj2[2]));
	    			else
	    				json.put("name", "");
	    			if(obj2[3]!=null)
	    				json.put("rrnum", String.valueOf(obj2[3]));
	    			else
	    				json.put("rrnum", "");
	    			if(obj2[4]!=null)
	    				json.put("email", String.valueOf(obj2[4]));
	    			else
	    				json.put("email", "");
	    			if(obj2[5]!=null)
	    				json.put("mob", String.valueOf(obj2[5]));
	    			else
	    				json.put("mob", "");
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
	@GetMapping("/getEmpDetails/{empid}")
    public String getEmpDetails(@PathVariable Integer empid) throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getEmpDetails");
        
        try {
        	JSONObject json = new JSONObject();
        	
        	Employee e = new Employee();
        	e = er.findByEmpId(empid);
        	
        	json.put("firstName", e.getFirstName());
        	json.put("midName", e.getMidName());
        	json.put("lastName", e.getLastName());
        	json.put("loginType", e.getLoginType());
        	json.put("photograph", e.getPhotograph());
        	json.put("dob", e.getDob());
        	json.put("maritalStatus", e.getMaritalStatus());
        	json.put("sex", e.getSex());
        	json.put("isApproved", e.getIsApproved());
        	json.put("rrNum", e.getRrNum());
        	json.put("address", e.getAddress());
        	json.put("mobNum", e.getMobNum());
        	json.put("message", e.getMessage());
        	json.put("email", e.getEmail());
        	json.put("oem", e.getOem());
        	json.put("heads", e.getHeads());
        	json.put("designation", e.getDesignation());
        	json.put("divId", e.getDivId());
        	json.put("subDivId", e.getSubDivId());
        	json.put("landingPage", e.getLandingPage());
        	json.put("empAdd", e.getEmpAdd());
        	json.put("empAge", e.getEmpAge());
        	json.put("salary", e.getSalary());
        	json.put("role", e.getLoginType());
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", json);
        }
        catch(Exception e) {
        	e.printStackTrace();

			obj.put("issuccess", false);
			obj.put("error", null);
        }
        
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getMeterTypeList")
    public String getMeterTypeList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getMeterTypeList");
        
        try {
    		ob = mvr.getMtrTypeList();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("code", String.valueOf(obj2[0]));
	    			else
	    				json.put("code", "");
	    			if(obj2[1]!=null)
	    				json.put("desc", String.valueOf(obj2[1]));
	    			else
	    				json.put("desc", "");
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
	@GetMapping("/getMeterFlowTypeList")
    public String getMeterFlowTypeList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getMeterFlowTypeList");
        
        try {
    		ob = mvr.getMtrFlowTypeList();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("code", String.valueOf(obj2[0]));
	    			else
	    				json.put("code", "");
	    			if(obj2[1]!=null)
	    				json.put("desc", String.valueOf(obj2[1]));
	    			else
	    				json.put("desc", "");
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
	@GetMapping("/getMtrBillingTypeList")
    public String getMtrBillingTypeList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getMtrBillingTypeList");
        
        try {
    		ob = mvr.getMtrBillingTypeList();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("code", String.valueOf(obj2[0]));
	    			else
	    				json.put("code", "");
	    			if(obj2[1]!=null)
	    				json.put("desc", String.valueOf(obj2[1]));
	    			else
	    				json.put("desc", "");
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
	@GetMapping("/getModeOfCommList")
    public String getModeOfCommList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getModeOfCommList");
        
        try {
    		ob = mvr.getModeOfCommList();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("code", String.valueOf(obj2[0]));
	    			else
	    				json.put("code", "");
	    			if(obj2[1]!=null)
	    				json.put("desc", String.valueOf(obj2[1]));
	    			else
	    				json.put("desc", "");
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
	@GetMapping("/getGprsTypeList")
    public String getGprsTypeList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getGprsTypeList");
        
        try {
    		ob = mvr.getGprsTypeList();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("code", String.valueOf(obj2[0]));
	    			else
	    				json.put("code", "");
	    			if(obj2[1]!=null)
	    				json.put("desc", String.valueOf(obj2[1]));
	    			else
	    				json.put("desc", "");
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
	@GetMapping("/getBillingGroupList")
    public String getBillingGroupList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getBillingGroupList");
        
        try {
    		ob = mvr.getBillingGroupList();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				JSONObject json = new JSONObject();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				json.put("code", String.valueOf(obj2[0]));
	    			else
	    				json.put("code", "");
	    			if(obj2[1]!=null)
	    				json.put("desc", String.valueOf(obj2[1]));
	    			else
	    				json.put("desc", "");
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
	@PostMapping("/forgotPassword")
    public String forgotPassword(@RequestBody String stringToParse) throws java.text.ParseException{
        JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject obj = new JSONObject();
        LoginMaster lm = new LoginMaster();
        
        try {
        	json = (JSONObject) parser.parse(stringToParse);
        	if(json.get("userName")!=null) {
        		lm = lmr.findByLoginName(json.get("userName").toString());
        		String email = "";
        		if(lm==null) {
        			obj.put("data", "User does not exist.");
        		}
        		else {
        			Employee emp = new Employee();
        			EmployeeLoginMapping elm = new EmployeeLoginMapping();
        			elm = elmpr.findByLoginName(lm.getLoginName());
        			emp = er.findByEmpId(elm.getEmployeeId());
        			email = emp.getEmail();
        			String password = newPassword();
        			lm.setPassword(getPasswordHash(password));
        			lmr.save(lm);
        			sendMail("New password is "+password, email, emp.getFirstName() + " " + emp.getLastName(), "Water AMR Forgot Password");
        			obj.put("data", "New password is sent to your registered email id");
        			
        			ac.writeLog("New Password generated for the User", "User id is : "+elm.getEmployeeId(), alr);
        		}
        	}
			obj.put("issuccess", true);
			obj.put("error", null);
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
	
	public String newPassword() {
		int count = 10;
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
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
	
	public static boolean sendMail(String msg, String email, String name, String subject){
		boolean status = true;
		final String username = config.getString("username");
		final String password = config.getString("password");

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", config.getString("host"));
		props.put("mail.smtp.port", config.getString("port"));

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText("Dear "+name+","
					+"\n\n"	
					+msg);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			//throw new RuntimeException(e);
			return status;
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/getOrganization/{orgId}")
	public String getOrganization(@PathVariable Integer orgId) throws java.text.ParseException {
		JSONObject obj = new JSONObject();
		log.debug("Inside getOrganization");

		try {
			Organization org = new Organization();
			org = or.findByOrgId(orgId);
			if(org!=null) {
				JSONObject json = new JSONObject();
				json.put("orgName", org.getOrgName());
				json.put("code", org.getCode());
				json.put("orgTypeId", org.getOrgTypeId().toString());
				json.put("isDeleted" , String.valueOf(org.isDeleted()));
				//json.put("parentId", org.getParentId().toString());
				int type = org.getOrgTypeId();
				int div = org.getOrgId();
				int subdiv = 0;
				int dma = 0;
				int service = 0;
				if(type == 2) {
					subdiv = org.getOrgId();
					div = org.getParentId();
				}
				else if(type == 3) {
					dma = org.getOrgId();
					subdiv = org.getParentId();
					org = or.findByOrgId(subdiv);
					div = org.getParentId();
				}
				else if(type == 4) {
					service = org.getOrgId();
					dma = org.getParentId();
					org = or.findByOrgId(dma);
					subdiv = org.getParentId();
					org = or.findByOrgId(subdiv);
					div = org.getParentId();
				}
				
				json.put("div", div+"");
				json.put("subdiv", subdiv+"");
				json.put("dma", dma+"");
				json.put("service", service+"");
				
				obj.put("issuccess", true);
				obj.put("error", null);
				obj.put("data", json);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}


		return obj.toString();
	}


	@SuppressWarnings("unchecked")
	@PutMapping("/updateOrganizationData")
	public JSONObject updateOrganizationData(@RequestBody String stringToParse) throws java.text.ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		Organization org = new Organization();
		log.debug("Update getOrganization");
		try {
			if(stringToParse !=null) {
				data = (JSONObject) parser.parse(stringToParse);
				if(data.get("id")!=null)
					org.setOrgId(Integer.parseInt(data.get("id").toString()));
				org = or.findByOrgId(org.getOrgId());
				boolean isDeleted = false;
				Integer orgType = Integer.parseInt(data.get("orgTypeId").toString());
				if(data.get("isDeleted")!=null)
					isDeleted = Boolean.parseBoolean(data.get("isDeleted").toString());
				if(isDeleted!=org.isDeleted()) {
					
					List<Organization> orglist = new ArrayList<Organization>();
					List<Organization> subdiv = new ArrayList<Organization>();
					List<Organization> dma = new ArrayList<Organization>();
					List<Organization> stn = new ArrayList<Organization>();
					if(orgType==1) {
						subdiv = or.findByParentId(org.getOrgId());
						if(subdiv!=null) {
							orglist.addAll(subdiv);
							for(Organization o : subdiv){
								dma = or.findByParentId(o.getOrgId());
								if(dma!=null) {
									orglist.addAll(dma);
									for(Organization d : dma){
										stn = or.findByParentId(d.getOrgId());
										if(stn!=null) {
											orglist.addAll(stn);
										}
									}
								}
							}
						}
					}
					if(orgType==2) {
						dma = or.findByParentId(org.getOrgId());
						if(dma!=null) {
							orglist.addAll(dma);
							for(Organization d : dma){
								stn = or.findByParentId(d.getOrgId());
								if(stn!=null) {
									orglist.addAll(stn);
								}
								
							}
						}
					}
					if(orgType==3) {
						stn = or.findByParentId(org.getOrgId());
						if(stn!=null) {
							orglist.addAll(stn);
						}
					}
					for(Organization o : orglist) {
						o.setDeleted(isDeleted);
						or.save(o);
					}
				}
				if(data.get("code")!=null)
					org.setCode(data.get("code").toString());
				if(data.get("orgName")!=null)
					org.setOrgName(data.get("orgName").toString());
				if(data.get("orgTypeId")!=null)
					org.setOrgTypeId(orgType);
				if(data.get("parentId")!=null)
					org.setParentId(Integer.parseInt(data.get("parentId").toString()));
				if(data.get("createdby")!=null)
					org.setCrBy(data.get("createdby").toString());
				if(data.get("isDeleted")!=null)
					org.setDeleted(isDeleted);
				org = or.save(org);

				json.put("issuccess", true);
				json.put("code", 200);
				json.put("data", org);

				ac.writeLog("Organization is updated", "Organization id is : "+org.getOrgId(), alr);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
			json.put("issuccess", false);
			json.put("code", 500);
		}

		return json;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/getAllMeterTypeList")
    public String getAllMeterTypeList() throws java.text.ParseException{
		List<MeterMaster> mm = new ArrayList<>();
		mmr.findAll().forEach(mm::add);
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("issuccess", true);
		obj.put("code", 200);
		obj1.put("error", obj);
		int val = 0;
        for (MeterMaster o : mm) {
        	obj = new JSONObject();
            obj.put("id", o.getId());
            obj.put("name", o.getName());
            
            ja.add(val++,obj);
        }
        obj1.put("data", ja);
		return obj1.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getServiceProvider")
    public String getServiceProvider() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
		List<Object> ob = new ArrayList();
		JSONArray ja = new JSONArray();
		 log.debug("Inside getServiceProvider");
		 try {
			 ob = lmr.getServicePro();
			 if(ob.size()>0) {
				 Iterator it = ob.iterator();
				 while(it.hasNext()) {
				  JSONObject json = new JSONObject();
				  Object[] obj2 = (Object[])it.next();
				  if(obj2[0]!=null)
	    				json.put("id", String.valueOf(obj2[0]));
	    			else
	    				json.put("id", "");
				  if(obj2[0] != null) 
					  json.put("code", String.valueOf(obj2[1]));
				  else
					  json.put("code"," ");
				  if(obj2[1] != null) 
					  json.put("desc", String.valueOf(obj2[2]));
				  else
					  json.put("desc"," ");
				  ja.add(json);
				  
				 }
			 }
			 obj.put("issuccess", true);
			 obj.put("error", null);
			 obj.put("data", ja);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return obj.toString();
	}
	

	@SuppressWarnings("unchecked")
	@PostMapping("/uploadDocFile")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
    public String uploadDocFile(@RequestParam("file") MultipartFile file) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		JSONObject obj = new JSONObject();
		try {
			String fileName = file.getOriginalFilename();
			
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			
			/*
			 * MultiValueMap<String, Object> body = new LinkedMultiValueMap<>(); HttpHeaders
			 * headers = new HttpHeaders();
			 * headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			 * restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin",
			 * "enzen")); body.add("filedata",getUserFileResource(file));
			 * body.add("fileName", fileName); body.add("overwrite", "false");
			 * body.add("mimetype", "MediaType.MULTIPART_FORM_DATA");
			 * HttpEntity<MultiValueMap<String, Object>> requestEntity = new
			 * HttpEntity<>(body, headers); final String fileUploadUrl =
			 * config.getString("fileUploadUrl");
			 * 
			 * ResponseEntity<String> response =
			 * restTemplate.exchange(fileUploadUrl,HttpMethod.POST,requestEntity,String.
			 * class); String respbody = response.getBody().toString(); JSONObject json =
			 * new JSONObject(); JSONParser parser = new JSONParser(); json = (JSONObject)
			 * parser.parse(respbody); System.out.println(respbody);
			 */
			
			obj.put("issuccess", true);
			obj.put("error", null);
		}	
		catch(Exception e) {
        	e.printStackTrace();
        }
		return obj.toString();
    }


	
	@SuppressWarnings("unchecked")
	@GetMapping("/getHiearachyList")
    public String getHiearachyList() throws java.text.ParseException{
		List<Organization> org = new ArrayList<>();
		or.findAll().forEach(org::add);
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("issuccess", true);
		obj.put("code", 200);
		obj1.put("error", obj);
		int val = 0;
        for (Organization o : org) {
        	if(!o.isDeleted()) {
	        	obj = new JSONObject();
	        	obj.put("id", o.getOrgId().toString());
	            obj.put("description", o.getOrgName());
	            if(o.getParentId()!=null)
	            	obj.put("parent", o.getParentId().toString());
	            else
	            	obj.put("parent", "1");
	            
	            ja.add(val++,obj);
        	}
        }
        obj = new JSONObject();
        obj.put("id", "1");
        obj.put("description", "Water Source");
        ja.add(val++,obj);
        obj1.put("data", ja);
		return obj1.toString();
	}
	
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getTariffMaster")
	public String getTariffMaster() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
		List<Object> ob = new ArrayList<>();
		JSONArray ja = new JSONArray();
		log.debug("Inside getTariffMaster");
		try {
			ob = tmr.getTariffList();
			if(ob.size() >0) {
				Iterator it = ob.iterator();
				while(it.hasNext()) {
					JSONObject json  = new JSONObject();
					Object[] obj2  = (Object[])it.next();
					if(obj2[0] != null) 
						json.put("id", String.valueOf(obj2[0]));
						else
						json.put("id", "");	
					if(obj2[1] != null) 
						json.put("categoryid", String.valueOf(obj2[1]));
						else
						json.put("categoryid", "");
					if(obj2[2] != null) 
						json.put("slabmin", String.valueOf(obj2[2]));
						else
						json.put("slabmin", "");
					if(obj2[3] != null) 
						json.put("slabmax", String.valueOf(obj2[3]));
						else
						json.put("slabmax", "");
					if(obj2[4] != null) 
						json.put("tariff", String.valueOf(obj2[4]));
						else
						json.put("tariff", "");
					if(obj2[5] != null) 
						json.put("sanitary", String.valueOf(obj2[5]));
						else
						json.put("sanitary", "");
					if(obj2[6] != null) 
						json.put("sanitarytype", String.valueOf(obj2[6]));
						else
						json.put("sanitarytype", "");
					if(obj2[7] != null) 
						json.put("borewell", String.valueOf(obj2[7]));
						else
						json.put("borewell", "");
					if(obj2[8] != null) 
						json.put("metercost", String.valueOf(obj2[8]));
						else
						json.put("metercost", "");
					if(obj2[9] != null) 
						json.put("year", String.valueOf(obj2[9]));
						else
						json.put("year", "");
					if(obj2[10] != null) 
						json.put("divName", String.valueOf(obj2[10]));
						else
						json.put("divName", "");
					ja.add(json);
				}
			}
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getTariffList/{id}")
	public String getTariffList(@PathVariable Integer id) throws java.text.ParseException{
	
		JSONObject obj = new JSONObject();
		log.debug("Inside getTariffList");
		JSONArray ja = new JSONArray();
		TariffMaster t = new TariffMaster();
		JSONObject jo = new JSONObject();
		try {
			List<TariffMaster> tml = tmr.findByTariffId(id);
			if(tml != null && tml.size()>0) {
				for(TariffMaster tm: tml) {
					JSONObject json = new JSONObject();
					t = tm;
					json.put("slabMin" , tm.getSlabMin());
					json.put("slabMax" , tm.getSlabMax());
					json.put("sanitary" , tm.getSanitay()+"");
					json.put("sanitaryType" , tm.getSanitaryType()+"");
					json.put("tariff" , tm.getTariff());
					ja.add(json);
				}
				
				jo.put("id", t.getId());
				jo.put("categoryId", t.getCategoryId());
				jo.put("boreWell", t.getBoreWell().toString());
				jo.put("divisionId", t.getDivisionId().toString());
				jo.put("meterCost", t.getMeterCost().toString());
				jo.put("year", t.getYears().toString());
				jo.put("tarifflist", ja);
			}
			obj.put("data", jo);
			obj.put("isSuccess", true);
			obj.put("error", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getSanitaryTypeList")
	public String getSanitaryTypeList() throws java.text.ParseException {
		JSONObject obj = new JSONObject();
		List<Object> ob = new ArrayList<>();
		JSONArray ja = new JSONArray();
		log.debug("Inside getSanitoryTypeList");
		
		try {
			 ob = lmr.getSanitarytypeList();
			 if(ob.size()>0) {
				 Iterator it = ob.iterator();
				 while(it.hasNext()) {
					 JSONObject json = new JSONObject();
					 Object[] obj2 = (Object[])it.next();
					 
					 if(obj2[0] != null)
						json.put("id" , String.valueOf(obj2[0]));
					else
						json.put("id", "");
					 
					if(obj2[1] != null)
					   json.put("name" , String.valueOf(obj2[1]));
					else
						json.put("name", "");
					ja.add(json);
				 }
			 }
			 obj.put("issuccess" , true);
			 obj.put("error", null);
			 obj.put("data", ja);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@PutMapping("/updateTariffMaster")
	public JSONObject updateTariffMaster(@RequestBody String stringToParse) {
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		TariffMaster tm = new TariffMaster();
		log.debug("Update updateTariffMaster");
		try {
			if(stringToParse != null) {
				data = (JSONObject)  parser.parse(stringToParse);
				Integer tariffId = Integer.parseInt(data.get("id").toString());
				tmr.deleteByTariffId(tariffId);
				
				JSONArray tariffList = (JSONArray) data.get("tarifflist");
				if(tariffList!=null && tariffList.size()>0) {
					for(int i=0; i<tariffList.size(); i++) {
						JSONObject t = (JSONObject) tariffList.get(i);
						if(data.get("categoryId")!= null)
							tm.setCategoryId(data.get("categoryId").toString());
					    if(data.get("boreWell")!= null)
							tm.setBoreWell(Double.parseDouble(data.get("boreWell").toString()));
						if(data.get("year")!= null)
							tm.setYears(Integer.parseInt(data.get("year").toString()));
						if(data.get("divisionId")!= null)
							tm.setDivisionId(Integer.parseInt(data.get("divisionId").toString()));
						if(data.get("meterCost")!= null)
							tm.setMeterCost(Double.parseDouble(data.get("meterCost").toString()));
						tm.setSlabMin(Double.parseDouble(t.get("slabMin").toString()));
						tm.setSlabMax(Double.parseDouble(t.get("slabMax").toString()));
						tm.setTariff(Double.parseDouble(t.get("tariff").toString()));
						tm.setSanitay(Double.parseDouble(t.get("sanitary").toString()));
						tm.setSanitaryType(t.get("sanitaryType").toString());
						tm.setAddedat(new Date());
						tm.setTariffId(tariffId);
						tm = tmr.save(tm);
					}
				}
				
				json.put("issuccess", true);
				json.put("code", 200);
			    json.put("data", tm);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			json.put("issuccess", false);
			json.put("code", 500);
		}
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/createTariffMaster")
	public JSONObject createTariffMaster(@RequestBody String stringToParse) throws java.text.ParseException{
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		JSONObject data = new JSONObject();
		TariffMaster tm = new TariffMaster();
		
		try {
			if(stringToParse != null) {
				json.put("issuccess", true);
				json.put("code", 200);
				data = (JSONObject)parser.parse(stringToParse);
				Integer tariffId = tmr.getMaxTariffId();
				JSONArray tariffList = (JSONArray) data.get("tarifflist");
				if(tariffList!=null && tariffList.size()>0) {
					for(int i=0; i<tariffList.size(); i++) {
						JSONObject t = (JSONObject) tariffList.get(i);
						if(data.get("categoryId")!= null)
							tm.setCategoryId(data.get("categoryId").toString());
					    if(data.get("boreWell")!= null)
							tm.setBoreWell(Double.parseDouble(data.get("boreWell").toString()));
						if(data.get("year")!= null)
							tm.setYears(Integer.parseInt(data.get("year").toString()));
						if(data.get("divisionId")!= null)
							tm.setDivisionId(Integer.parseInt(data.get("divisionId").toString()));
						if(data.get("meterCost")!= null)
							tm.setMeterCost(Double.parseDouble(data.get("meterCost").toString()));
						tm.setSlabMin(Double.parseDouble(t.get("slabMin").toString()));
						tm.setSlabMax(Double.parseDouble(t.get("slabMax").toString()));
						tm.setTariff(Double.parseDouble(t.get("tariff").toString()));
						tm.setSanitay(Double.parseDouble(t.get("sanitary").toString()));
						tm.setSanitaryType(t.get("sanitaryType").toString());
						tm.setAddedat(new Date());
						tm.setTariffId(tariffId);
						tm = tmr.save(tm);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("issuccess", false);
			json.put("code", 500);
		}
		
		
		return json;
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/updateActiveOrgStatus")
	public JSONObject updateActiveOrgStatus(@RequestBody String stringToParse) throws java.text.ParseException{
		JSONParser parser = new JSONParser();
		 JSONObject json = new JSONObject();
	     JSONObject data = new JSONObject();
	     Organization org = new Organization();
	     log.debug("updateActiveOrgStatus");
	     try {
	    	 if(stringToParse != null) {
	    		 json.put("issuccess", true);
	    		 json.put("code", 200);
		        	data = (JSONObject) parser.parse(stringToParse);
		        	if(data.get("orgId")!= null)
		        	org.setOrgId(Integer.parseInt(data.get("orgId").toString()));
	                org = or.findByOrgId(org.getOrgId());
	                if(data.get("isDeleted")!= null)
	                	org.setDeleted(Boolean.parseBoolean(data.get("isDeleted").toString()));
	                or.save(org);
	                json.put("data", org);
		        	ac.writeLog("Organization is deleted", "Organization is : "+org.getOrgId(), alr);
	    	 }
			
		} catch (Exception e) {
			e.printStackTrace();
	    	json.put("issuccess", false);
	    	json.put("code", 500);
		}
		
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/updateActiveRole")
	public JSONObject updateActiveRole(@RequestBody String stringToParse) throws java.text.ParseException{
	
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
	    JSONObject data = new JSONObject();
	    Roles roles = new Roles();
		try {
			if(stringToParse != null) {
			json.put("issuccess" ,true);
			json.put("code", 200);
			data = (JSONObject)parser.parse(stringToParse);
			if(data.get("roleId")!= null)
				roles.setRoleId(Integer.parseInt(data.get("roleId").toString()));
			roles = rr.findByRoleId(roles.getRoleId());
			if(data.get("active")!= null)
				roles.setActive(Boolean.parseBoolean(data.get("active").toString()));
			
			rr.save(roles);
	        json.put("data", roles);
	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			json.put("issuccess", false);
	    	json.put("code", 500);
		}
		return json;
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getRoleDataList/{roleId}")
	public String getRoleDataList(@PathVariable Integer roleId) throws java.text.ParseException{
		JSONObject obj = new JSONObject();
		List<Object> ob = new ArrayList<>();
	    JSONArray ja = new JSONArray();
	    log.debug("Inside getRoleDataList");
	    
	    try {
	    	if(roleId != null) {
	    		ob = rr.getRoleDataList(roleId);
	    	if(ob.size() >0) {
	    		Iterator it = ob.iterator();
	    		while(it.hasNext()) {
	    			JSONObject json = new JSONObject();
	    			Object[] obj2 = (Object[]) it.next();
	    			if(obj2[0] != null)
	    				json.put("roleId", String.valueOf(obj2[0]));
	    			else
	    				json.put("roleId", "");
	    			
	    			if(obj2[1] != null)
	    				json.put("roleName", String.valueOf(obj2[1]));
	    			else
	    				json.put("roleName", "");
	    			
	    			if(obj2[2] != null)
	    				json.put("sysRole", String.valueOf(obj2[2]));
	    			else
	    				json.put("sysRole", "");
	    			
	    			if(obj2[3] != null)
	    				json.put("active", String.valueOf(obj2[3]));
	    			else
	    				json.put("active", "");
	    			
	    			if(obj2[4] != null)
	    				json.put("description", String.valueOf(obj2[4]));
	    			else
	    				json.put("description", "");
	    			ja.add(json);
	    			
	    		}
	    	}
	    	}
	    	obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
			
		} catch (Exception e) {
	    	e.printStackTrace();
		}
		return obj.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getQualityParamList")
	public String getQualityParamList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
	    List<Object> ob = new ArrayList<>();
	    JSONArray ja = new JSONArray();
	    log.debug("Inside getQualityParamList");
	    
	    try {
	    	List<QualityParameter> qlt = new ArrayList<>();
			qpr.findAll().forEach(qlt::add);
			for (QualityParameter q : qlt) {
				JSONObject json = new JSONObject();
				json.put("id", q.getId());
				json.put("name", q.getParameter());
				json.put("value", q.getValue());
				json.put("max", q.getMaxValue());
				if(q.getRemarks()!=null)
					json.put("remarks", q.getRemarks());
				else
					json.put("remarks", "");
				json.put("category", "Water Quality");
				ja.add(json);
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
	@PostMapping("/insertQualityParam")
    public JSONObject insertQualityParam(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        QualityParameter q = new QualityParameter();
        try {
        	if(stringToParse!=null) {
	        	json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	
	        	if(data.get("category")!=null && "Water Quality".equalsIgnoreCase(data.get("name").toString())) {
		        	if(data.get("name")!=null)
		        		q.setParameter(data.get("name").toString());
		        	if(data.get("value")!=null)
		        		q.setValue(Double.parseDouble(data.get("value").toString()));
		        	if(data.get("max")!=null)
		        		q.setMaxValue(Double.parseDouble(data.get("max").toString()));
		        	if(data.get("remarks")!=null)
		        		q.setRemarks(data.get("remarks").toString());
		        	
		        	qpr.save(q);
	        	}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@PostMapping("/updateQualityParam")
    public JSONObject updateQualityParam(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        QualityParameter q = new QualityParameter();
        try {
        	if(stringToParse!=null) {
	        	json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
		        	if(data.get("category")!=null && "Water Quality".equalsIgnoreCase(data.get("name").toString())) {
		        	if(data.get("id")!=null)
		        		q = qpr.findById(Integer.parseInt(data.get("id").toString()));
		        	if(data.get("name")!=null)
		        		q.setParameter(data.get("name").toString());
		        	if(data.get("value")!=null)
		        		q.setValue(Double.parseDouble(data.get("value").toString()));
		        	if(data.get("max")!=null)
		        		q.setMaxValue(Double.parseDouble(data.get("max").toString()));
		        	if(data.get("remarks")!=null)
		        		q.setRemarks(data.get("remarks").toString());
		        	
		        	qpr.save(q);
	        	}
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	json.put("issuccess", false);
        	json.put("code", 500);
		}
        
		return json;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getQualityParam/{id}")
    public String getQualityParam(@PathVariable Integer id) throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        log.debug("Inside getQualityParam");
        
        try {
        	QualityParameter q = qpr.findById(id);
        	if(q!=null) {
        		JSONObject json = new JSONObject();
        		json.put("id", q.getId());
				json.put("name", q.getParameter());
				json.put("value", q.getValue());
				json.put("max", q.getMaxValue());
				if(q.getRemarks()!=null)
					json.put("remarks", q.getRemarks());
				else
					json.put("remarks", "");
				json.put("category", "Water Quality");
        		obj.put("issuccess", true);
    			obj.put("error", null);
    			obj.put("data", json);
        	}
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        
		return obj.toString();
	}
}

