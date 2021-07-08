package com.enzen.waterMdm.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.enzen.waterMdm.model.EstUnitConsumptionDetails;
import com.enzen.waterMdm.model.MessageAccepted;
import com.enzen.waterMdm.model.MessageAcceptedHistory;
import com.enzen.waterMdm.model.MessageData;
import com.enzen.waterMdm.model.MessageDataHistory;
import com.enzen.waterMdm.model.MessageRejected;
import com.enzen.waterMdm.model.MessageRejectedHistory;
import com.enzen.waterMdm.model.Organization;
import com.enzen.waterMdm.model.QualityData;
import com.enzen.waterMdm.model.TempMessageData;
import com.enzen.waterMdm.model.UnitConsumptionDetails;
import com.enzen.waterMdm.model.UnitSummary;
import com.enzen.waterMdm.repo.EstUnitsConsumptionDetailsRepository;
import com.enzen.waterMdm.repo.MessageAcceptedHistoryRepository;
import com.enzen.waterMdm.repo.MessageAcceptedRepository;
import com.enzen.waterMdm.repo.MessageDataHistoryRepository;
import com.enzen.waterMdm.repo.MessageDataRepository;
import com.enzen.waterMdm.repo.MessageRejectedHistoryRepository;
import com.enzen.waterMdm.repo.MessageRejectedRepository;
import com.enzen.waterMdm.repo.QualityDataRepository;
import com.enzen.waterMdm.repo.TempMessageDataRepository;
import com.enzen.waterMdm.repo.UnitSummaryRepository;
import com.enzen.waterMdm.repo.UnitsConsumptionDetailsRepository;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    @Autowired
	MessageDataRepository mdr;
	
	@Autowired
	MessageRejectedRepository mrr;
	
	@Autowired
	MessageAcceptedRepository mar;
	
	@Autowired
	MessageDataHistoryRepository mdhr;
	
	@Autowired
	MessageRejectedHistoryRepository mrhr;
	
	@Autowired
	TempMessageDataRepository tmdr;
	
	@Autowired
	UnitSummaryRepository usr;
	
	@Autowired
	UnitsConsumptionDetailsRepository ucdr;
	
	@Autowired
	MessageAcceptedHistoryRepository mahr;
	
	@Autowired
	UnitsConsumptionDetailsRepository usdr;
	
	@Autowired
	EstUnitsConsumptionDetailsRepository eucdr;

	@Autowired
    EstUnitsConsumptionDetailsRepository ecdr;
	
	@Autowired
	QualityDataRepository qdr;
    
    @Scheduled(cron = "0 0 1 * * ?")
    public void scheduleTaskForMsgMoveToHistory() {
    	List<Object> ob = new ArrayList<>();
        MessageDataHistory mdh = new MessageDataHistory();
        MessageRejectedHistory mrh = new MessageRejectedHistory();
        try {
        	ob = mdr.getLastMonthData();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				mdh.setId(Integer.parseInt(String.valueOf(obj2[0])));
	    			if(obj2[1]!=null)
	    				mdh.setUnitId(Integer.parseInt(String.valueOf(obj2[1])));
	    			if(obj2[2]!=null)
	    				mdh.setLast24Hrs(String.valueOf(obj2[2]));
	    			if(obj2[3]!=null)
	    				mdh.setPrevDay(Integer.parseInt(String.valueOf(obj2[3])));
	    			if(obj2[4]!=null)
	    				mdh.setPrevToPrevDay(Integer.parseInt(String.valueOf(obj2[4])));
	    			if(obj2[5]!=null)
	    				mdh.setTampStat(Integer.parseInt(String.valueOf(obj2[5])));
	    			if(obj2[6]!=null)
	    				mdh.setBattVolt(Double.parseDouble(String.valueOf(obj2[6])));
	    			if(obj2[7]!=null)
	    				mdh.setTempr(Double.parseDouble(String.valueOf(obj2[7])));
	    			if(obj2[8]!=null)
	    				mdh.setPulseCount(Integer.parseInt(String.valueOf(obj2[8])));
	    			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    			df.setTimeZone(TimeZone.getTimeZone("UTC"));
	    			String strTime = String.valueOf(obj2[9]) + " " + String.valueOf(obj2[10]);
	    			Date d = df.parse(strTime);
	    			if(obj2[9]!=null)
	    				mdh.setDate(d);
	    			if(obj2[10]!=null)
	    				mdh.setTime(d);
	    			
	    			mdh = mdhr.save(mdh);
	    			mdr.deleteById(Integer.parseInt(String.valueOf(obj2[0])));
    			}
    		}
    		
    		ob = mrr.getLastMonthData();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				mrh.setId(Integer.parseInt(String.valueOf(obj2[0])));
	    			if(obj2[1]!=null)
	    				mrh.setMessage(String.valueOf(obj2[1]));
	    			if(obj2[2]!=null)
	    				mrh.setSentBy(String.valueOf(obj2[2]));
	    			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    			df.setTimeZone(TimeZone.getTimeZone("UTC"));
	    			String strTime = String.valueOf(obj2[3]) + " " + String.valueOf(obj2[4]);
	    			Date d = df.parse(strTime);
	    			if(obj2[3]!=null)
	    				mrh.setSentDate(d);
	    			if(obj2[4]!=null)
	    				mrh.setTime(d);
	    			if(obj2[5]!=null)
	    				mrh.setSmsHeader(String.valueOf(obj2[5]));
	    			if(obj2[6]!=null)
	    				mrh.setRejectReason(String.valueOf(obj2[6]));
	    			if(obj2[7]!=null)
	    				mrh.setUnitId(String.valueOf(obj2[7]));
	    			
	    			mrh = mrhr.save(mrh);
	    			mrr.deleteById(Integer.parseInt(String.valueOf(obj2[0])));
    			}
    		}
        }catch (Exception e) {
        	e.printStackTrace();
		}
		
    }
    
    @Scheduled(cron = "0 0 * * * ?")
    public void taskForTempMsgMoveToMsgData() {
    	try {
	    	List<TempMessageData> msg = new ArrayList<>();
			tmdr.findAll().forEach(msg::add);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	        Date date = new Date();
	        
			for (TempMessageData m : msg) {
				
				if(m.getUnitId()!=null && m.getDate()!=null && m.getLast24Hrs()!=null && Integer.parseInt(m.getLast24Hrs()) > 0 &&
		        	(m.getPrevDay() != m.getPrevToPrevDay()) && usr.findByUnitId(m.getUnitId().toString()) != null) {
					
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
					
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    			df.setTimeZone(TimeZone.getTimeZone("UTC"));
	    			String strTime = m.getDate() + " " + m.getTime();
					Date d = df.parse(strTime);
	    			
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
		        	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		        	df.setTimeZone(TimeZone.getTimeZone("UTC"));
		        	if(m.getDate()!=null)
		        		mr.setSentDate(m.getDate());
		        	if(m.getTime()!=null)
		        		mr.setTime(m.getTime());
		        	
		        	mr = mrr.save(mr);
				}
				
				tmdr.delete(m);
			}
    	} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    @Scheduled(cron = "0 0 1 * * ?")
    public void taskForMoveMsgDataToHist() {
		List<Object> ob = new ArrayList<>();
		MessageDataHistory mdh = new MessageDataHistory();
        MessageRejectedHistory mrh = new MessageRejectedHistory();
        MessageAcceptedHistory mah = new MessageAcceptedHistory();
        try {
        	ob = mdr.getLast3MonthData();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				mdh = new MessageDataHistory();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				mdh.setId(Integer.parseInt(String.valueOf(obj2[0])));
	    			if(obj2[1]!=null)
	    				mdh.setUnitId(Integer.parseInt(String.valueOf(obj2[1])));
	    			if(obj2[2]!=null)
	    				mdh.setLast24Hrs(String.valueOf(obj2[2]));
	    			if(obj2[3]!=null)
	    				mdh.setPrevDay(Integer.parseInt(String.valueOf(obj2[3])));
	    			if(obj2[4]!=null)
	    				mdh.setPrevToPrevDay(Integer.parseInt(String.valueOf(obj2[4])));
	    			if(obj2[5]!=null)
	    				mdh.setTampStat(Integer.parseInt(String.valueOf(obj2[5])));
	    			if(obj2[6]!=null)
	    				mdh.setBattVolt(Double.parseDouble(String.valueOf(obj2[6])));
	    			if(obj2[7]!=null)
	    				mdh.setTempr(Double.parseDouble(String.valueOf(obj2[7])));
	    			if(obj2[8]!=null)
	    				mdh.setPulseCount(Integer.parseInt(String.valueOf(obj2[8])));
	    			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    			df.setTimeZone(TimeZone.getTimeZone("UTC"));
	    			String strTime = String.valueOf(obj2[9]) + " " + String.valueOf(obj2[10]);
	    			Date d = df.parse(strTime);
	    			if(obj2[9]!=null)
	    				mdh.setDate(d);
	    			if(obj2[10]!=null)
	    				mdh.setTime(d);
	    			
	    			mdh = mdhr.save(mdh);
	    			mdr.deleteById(Integer.parseInt(String.valueOf(obj2[0])));
    			}
    		}
    		
    		ob = mar.getLast3MonthData();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				mah = new MessageAcceptedHistory();
    				Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				mah.setId(Integer.parseInt(String.valueOf(obj2[0])));
	    			if(obj2[1]!=null)
	    				mah.setMessage(String.valueOf(obj2[1]));
	    			if(obj2[2]!=null)
	    				mah.setSentBy(String.valueOf(obj2[2]));
	    			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    			df.setTimeZone(TimeZone.getTimeZone("UTC"));
	    			String strTime = String.valueOf(obj2[3]) + " " + String.valueOf(obj2[5]);
	    			Date d = df.parse(strTime);
	    			if(obj2[3]!=null)
	    				mah.setSentDate(d);
	    			if(obj2[5]!=null)
	    				mah.setSentTime(d);
	    			if(obj2[4]!=null)
	    				mah.setSmsHeader(String.valueOf(obj2[4]));
	    			mah = mahr.save(mah);
	    			mar.deleteById(Integer.parseInt(String.valueOf(obj2[0])));
    			}
    		}
    		
    		ob = mrr.getLast3MonthData();
    		if(ob.size()>0) {
        		Iterator itr = ob.iterator();
    			while(itr.hasNext()){
    				mrh = new MessageRejectedHistory();
    			    Object[] obj2 = (Object[]) itr.next();
	    			if(obj2[0]!=null)
	    				mrh.setId(Integer.parseInt(String.valueOf(obj2[0])));
	    			if(obj2[1]!=null)
	    				mrh.setMessage(String.valueOf(obj2[1]));
	    			if(obj2[2]!=null)
	    				mrh.setSentBy(String.valueOf(obj2[2]));
	    			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	    			df.setTimeZone(TimeZone.getTimeZone("UTC"));
	    			String strTime = String.valueOf(obj2[3]) + " " + String.valueOf(obj2[4]);
	    			Date d = df.parse(strTime);
	    			if(obj2[3]!=null)
	    				mrh.setSentDate(d);
	    			if(obj2[4]!=null)
	    				mrh.setTime(d);
	    			if(obj2[5]!=null)
	    				mrh.setSmsHeader(String.valueOf(obj2[5]));
	    			if(obj2[6]!=null)
	    				mrh.setRejectReason(String.valueOf(obj2[6]));
	    			if(obj2[7]!=null)
	    				mrh.setUnitId(String.valueOf(obj2[7]));
	    			mrh = mrhr.save(mrh);
	    			mrr.deleteById(Integer.parseInt(String.valueOf(obj2[0])));
    			}
    		}
    		
        }catch (Exception e) {
        	e.printStackTrace();
		}
    }
    
    @Scheduled(cron = "0 0 1 * * ?")
    public void generateVEE() {

        JSONObject obj = new JSONObject();
        List <Object> ob = new ArrayList <> ();
        JSONArray ja = new JSONArray();
        Double estVal = 0.0;
        Double lastVal = 0.0;
        try {
    		String yesterdayDate=null; 
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.DATE, -1);
    		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    		yesterdayDate=dateFormat1.format(cal.getTime());
    		Calendar cal1 = Calendar.getInstance();
    		cal1.add(Calendar.DATE, -1);
    		Date yest = cal1.getTime();;
    		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//df.setTimeZone(TimeZone.getTimeZone("UTC"));
			
        	List<UnitSummary> u = new ArrayList<>();
    		usr.findAll().forEach(u::add);
    		for(UnitSummary unit : u) {
    			Integer id = unit.getId(); 
    			String unitid = unit.getUnitId();
    			ob = ucdr.getPrevToPrevDayConsData(id);
				if(ob.size()>0) {
					//previous to previous last record at 23 hours from consumption table 
					Object[] obj2 = (Object[]) ob.get(0);
					estVal = Double.parseDouble(String.valueOf(obj2[3]));
    			}
    			else {
    				//previous to previous last record at 23 hours from estimation table
    				ob = ecdr.getPrevEstimatedData(unitid);
    				if(ob.size()>0) {
        				Object[] obj2 = (Object[]) ob.get(0);
        				estVal = Double.parseDouble(String.valueOf(obj2[3]));
    				}
    			}
    			ob = ucdr.getPrevDayConsData(id);
    			//case 1 never communicated
    			if(ob.size()==0) {
    				//insert into estimation table for the whole day data
    				for(int i=0; i<24; i++) {
    					EstUnitConsumptionDetails e = new EstUnitConsumptionDetails();
    					e.setConsInMcube(estVal);
    					e.setDayCons(estVal);
    					e.setAddeDat(df.parse(String.valueOf(yesterdayDate + " " +i+":01:00")));
    					e.setDateRecorded(yest);
    					//e.setDateRecorded(e.getAddeDat());
    					e.setUnitId(unitid);
    					e.setFlag(1);
    					ecdr.save(e);
    				}
    			}
    			//case 2 only once communicated
    			else if(ob.size()==1) {
    				Object[] obj2 = (Object[]) ob.get(0);
    				Double a = Double.parseDouble(String.valueOf(obj2[5]));
    				int temp = new java.lang.Double(a).intValue();//Integer.parseInt(String.valueOf(obj2[5]));
    				estVal = Double.parseDouble(String.valueOf(obj2[3]));
    				for(int i=0; i<24; i++) {
    					if(i!=temp) {
	    					EstUnitConsumptionDetails e = new EstUnitConsumptionDetails();
	    					e.setConsInMcube(estVal);
	    					e.setDayCons(estVal);
	    					e.setAddeDat(df.parse(String.valueOf(yesterdayDate + " " +i+":01:00")));
	    					e.setDateRecorded(yest);
	    					//e.setDateRecorded(e.getAddeDat());
	    					e.setUnitId(unitid);
	    					e.setFlag(1);
	    					ecdr.save(e);
    					}
    				}
    			}
    			//case 3 fully communicated
    			else if(ob.size()==24) {
    				
    			}
    			//case 4 partially communicated
    			else if(ob.size()<24) {
    				ob = ucdr.getLastDayConsumptionData(id);
    				Object[] obj2 = (Object[]) ob.get(0);
    				for(int i=1; i<25; i++) {
						if(!"0".equalsIgnoreCase(String.valueOf(obj2[i]))) {
							estVal = Double.parseDouble(String.valueOf(obj2[i]));
						}
						else {
							EstUnitConsumptionDetails e = new EstUnitConsumptionDetails();
	    					e.setConsInMcube(estVal);
	    					e.setAddeDat(df.parse(String.valueOf(yesterdayDate + " " +(i-1)+":01:00")));
	    					e.setDayCons(estVal);
	    					e.setDateRecorded(yest);
	    					//e.setDateRecorded(e.getAddeDat());
	    					e.setUnitId(unitid);
	    					e.setFlag(1);
	    					ecdr.save(e);
						}
    				}
    			}
    		}
    		ucdr.updateRecordDate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Scheduled(cron = "0 0 * * * ?")
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
    
    @Scheduled(cron = "0 0 1 * * ?")
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
    
    @Scheduled(cron = "0 0 2 * * ?")
    public void UpdateQualityDate() throws java.text.ParseException{
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
	}
    
    @Scheduled(cron = "0 0 2 * * ?")
    public void UpdateFtpDate() throws java.text.ParseException{
        try {
        	ucdr.updateFtpRecordDate();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
	}
    
}
