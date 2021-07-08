package com.enzen.waterMdm.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enzen.waterMdm.model.BillGroup;
import com.enzen.waterMdm.model.BillGroupDetail;
import com.enzen.waterMdm.model.BillLog;
import com.enzen.waterMdm.model.BillingReportMaster;
import com.enzen.waterMdm.model.CounterData;
import com.enzen.waterMdm.model.EstUnitConsumptionDetails;
import com.enzen.waterMdm.model.MasterValue;
import com.enzen.waterMdm.model.Menu;
import com.enzen.waterMdm.model.Notification;
import com.enzen.waterMdm.model.Organization;
import com.enzen.waterMdm.model.PaymentDetails;
import com.enzen.waterMdm.model.PaymentProcess;
import com.enzen.waterMdm.model.TariffMaster;
import com.enzen.waterMdm.model.UnitSummary;
import com.enzen.waterMdm.repo.ApplicationLogsRepository;
import com.enzen.waterMdm.repo.BillGroupDetailRepository;
import com.enzen.waterMdm.repo.BillGroupRepository;
import com.enzen.waterMdm.repo.BillLogRepository;
import com.enzen.waterMdm.repo.BillingReportMasterRepository;
import com.enzen.waterMdm.repo.CounterDataRepository;
import com.enzen.waterMdm.repo.EstUnitsConsumptionDetailsRepository;
import com.enzen.waterMdm.repo.LoginMasterRepository;
import com.enzen.waterMdm.repo.MnfDataRepository;
import com.enzen.waterMdm.repo.NotificationRepository;
import com.enzen.waterMdm.repo.PaymentDetailsRepository;
import com.enzen.waterMdm.repo.PaymentProcessRepository;
import com.enzen.waterMdm.repo.QualityDataRepository;
import com.enzen.waterMdm.repo.TariffMasterRepository;
import com.enzen.waterMdm.repo.UnitSummaryRepository;
import com.enzen.waterMdm.repo.UnitsConsumptionDetailsRepository;
import com.enzen.waterMdm.repo.WaterDemandDataRepository;

@RestController()
@RequestMapping("/report")
public class ReportDataController {

    @Autowired
    BillingReportMasterRepository bmr;

    @Autowired
	ApplicationLogsRepository alr;
    
    @Autowired
    UnitsConsumptionDetailsRepository ucdr;
    
    @Autowired
    EstUnitsConsumptionDetailsRepository ecdr;
    
    @Autowired
    UnitSummaryRepository usr;
    
    @Autowired
	BillGroupRepository bgr;
    
    @Autowired
	BillGroupDetailRepository bgdr;
    
    @Autowired
	BillLogRepository blr;
    
    @Autowired
	TariffMasterRepository tmr;
    
    @Autowired
    NotificationRepository nr;

    @Autowired
    PaymentDetailsRepository pdr;
    
    @Autowired
    PaymentProcessRepository ppr;
    
    @Autowired
	LoginMasterRepository lmr;
    
    @Autowired
    CounterDataRepository cr;
    
    @Autowired
    QualityDataRepository qdr;
    
    @Autowired
    MnfDataRepository mr;
    
    @Autowired
    WaterDemandDataRepository wr;
    
    private final Logger log = LogManager.getLogger(this.getClass());
    
    ApplicationLogsController ac = new ApplicationLogsController();

    @SuppressWarnings("unchecked")
    @GetMapping("/getBillingreportData/{div}/{subdiv}/{duration}")
    public String getBillingreportData(@PathVariable Integer div,
        @PathVariable Integer subdiv, @PathVariable String duration)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("getBillingreportData");

        try {
            if (div != null && subdiv != null && duration != null) {
                ob = bmr.getBillingList(div, subdiv, duration);
                if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        JSONObject json = new JSONObject();
                        Object[] obj2 = (Object[]) it.next();

                        if (obj2[0] != null)
                            json.put("id", String.valueOf(obj2[0]));
                        else
                            json.put("id", "");
                        if (obj2[1] != null)
                            json.put("monthYear", String.valueOf(obj2[1]));
                        else
                            json.put("monthYear", "");
                        if (obj2[2] != null)
                            json.put("unitId", String.valueOf(obj2[2]));
                        else
                            json.put("unitId", "");
                        if (obj2[3] != null)
                            json.put("firstReading", String.valueOf(obj2[3]));
                        else
                            json.put("firstReading", "");
                        if (obj2[4] != null)
                            json.put("lastreading", String.valueOf(obj2[4]));
                        else
                            json.put("lastreading", "");
                        if (obj2[5] != null)
                            json.put("consumption", String.valueOf(obj2[5]));
                        else
                            json.put("consumption", "");
                        if (obj2[6] != null)
                            json.put("watercharge", String.valueOf(obj2[6]));
                        else
                            json.put("watercharge", "");
                        if (obj2[7] != null)
                            json.put("metercharge", String.valueOf(obj2[7]));
                        else
                            json.put("metercharge", "");
                        if (obj2[8] != null)
                            json.put("sanitarycharge", String.valueOf(obj2[8]));
                        else
                            json.put("sanitarycharge", "");
                        if (obj2[9] != null)
                            json.put("borewellcharge", String.valueOf(obj2[9]));
                        else
                            json.put("borewellcharge", "");
                        if (obj2[10] != null)
                            json.put("othercharge", String.valueOf(obj2[10]));
                        else
                            json.put("othercharge", "");
                        if (obj2[11] != null)
                            json.put("arrears", String.valueOf(obj2[11]));
                        else
                            json.put("arrears", "");
                        if (obj2[12] != null)
                            json.put("interestonarrears", String.valueOf(obj2[12]));
                        else
                            json.put("interestonarrears", "");
                        if (obj2[13] != null)
                            json.put("totalamount", String.valueOf(obj2[13]));
                        else
                            json.put("totalamount", "");
                        if (obj2[14] != null)
                            json.put("addedat", String.valueOf(obj2[14]));
                        else
                            json.put("addedat", "");
                        if (obj2[15] != null)
                            json.put("duedate", String.valueOf(obj2[15]));
                        else
                            json.put("duedate", "");
                        if (obj2[16] != null)
                            json.put("billdate", String.valueOf(obj2[16]));
                        else
                            json.put("billdate", "");
                        if (obj2[17] != null)
                            json.put("firstdate", String.valueOf(obj2[17]));
                        else
                            json.put("firstdate", "");
                        if (obj2[18] != null)
                            json.put("lastdate", String.valueOf(obj2[18]));
                        else
                            json.put("lastdate", "");
                        if (obj2[19] != null)
                            json.put("missings", String.valueOf(obj2[19]));
                        else
                            json.put("missings", "");
                        if (obj2[20] != null)
                            json.put("extrapaid", String.valueOf(obj2[20]));
                        else
                            json.put("extrapaid", "");
                        if (obj2[21] != null)
                            json.put("previousbill", String.valueOf(obj2[21]));
                        else
                            json.put("previousbill", "");
                        if (obj2[22] != null)
                            json.put("div", String.valueOf(obj2[22]));
                        else
                            json.put("div", "");
                        if (obj2[23] != null)
                            json.put("subdiv", String.valueOf(obj2[23]));
                        else
                            json.put("subdiv", "");
                        if (obj2[24] != null)
                            json.put("consumerid", String.valueOf(obj2[24]));
                        else
                            json.put("consumerid", "");
                        if (obj2[25] != null)
                            json.put("consumeraddress", String.valueOf(obj2[25]));
                        else
                            json.put("consumeraddress", "");
                        
                        ja.add(json);
                    }
                }
            }
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/getUnitBill/{unitid}")
    public String getBillingreportData(@PathVariable String unitid)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("getUnitBill");

        try {
            if (unitid != null) {
                ob = bmr.getUnitBill(unitid);
                if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        JSONObject json = new JSONObject();
                        Object[] obj2 = (Object[]) it.next();
                        if (obj2[0] != null)
                            json.put("div", String.valueOf(obj2[0]));
                        else
                            json.put("div", "");
                        if (obj2[1] != null)
                            json.put("id", String.valueOf(obj2[1]));
                        else
                            json.put("id", "");
                        if (obj2[2] != null)
                            json.put("unitId", String.valueOf(obj2[2]));
                        else
                            json.put("unitId", "");
                        if (obj2[3] != null)
                            json.put("firstReading", String.valueOf(obj2[3]));
                        else
                            json.put("firstReading", "");
                        if (obj2[4] != null)
                            json.put("lastreading", String.valueOf(obj2[4]));
                        else
                            json.put("lastreading", "");
                        if (obj2[5] != null)
                            json.put("consumption", String.valueOf(obj2[5]));
                        else
                            json.put("consumption", "");
                        if (obj2[6] != null)
                            json.put("watercharge", String.valueOf(obj2[6]));
                        else
                            json.put("watercharge", "");
                        if (obj2[7] != null)
                            json.put("metercharge", String.valueOf(obj2[7]));
                        else
                            json.put("metercharge", "");
                        if (obj2[8] != null)
                            json.put("sanitarycharge", String.valueOf(obj2[8]));
                        else
                            json.put("sanitarycharge", "");
                        if (obj2[9] != null)
                            json.put("borewellcharge", String.valueOf(obj2[9]));
                        else
                            json.put("borewellcharge", "");
                        if (obj2[10] != null)
                            json.put("othercharge", String.valueOf(obj2[10]));
                        else
                            json.put("othercharge", "");
                        if (obj2[11] != null)
                            json.put("arrears", String.valueOf(obj2[11]));
                        else
                            json.put("arrears", "");
                        if (obj2[12] != null)
                            json.put("interestonarrears", String.valueOf(obj2[12]));
                        else
                            json.put("interestonarrears", "");
                        if (obj2[13] != null)
                            json.put("totalamount", String.valueOf(obj2[13]));
                        else
                            json.put("totalamount", "");
                        if (obj2[14] != null)
                            json.put("billdate", String.valueOf(obj2[14]));
                        else
                            json.put("billdate", "");
                        if (obj2[15] != null)
                            json.put("duedate", String.valueOf(obj2[15]));
                        else
                            json.put("duedate", "");
                        if (obj2[16] != null)
                            json.put("consumerid", String.valueOf(obj2[16]));
                        else
                            json.put("consumerid", "");
                        if (obj2[17] != null)
                            json.put("consumeraddress", String.valueOf(obj2[17]));
                        else
                            json.put("consumeraddress", "");
                        if (obj2[18] != null)
                            json.put("subdiv", String.valueOf(obj2[18]));
                        else
                            json.put("subdiv", "");
                        if (obj2[19] != null)
                            json.put("rrno", String.valueOf(obj2[19]));
                        else
                            json.put("rrno", "");
                        
                        ja.add(json);
                    }
                }
            }
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/getUnitBillBatch/{divid}/{subdiv}")
    public String getUnitBillBatch(@PathVariable Integer divid,
            @PathVariable Integer subdiv)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("getUnitBillBatch");

        try {
            ob = bmr.getUnitBillList();
            if (ob.size() > 0) {
                Iterator it = ob.iterator();
                while (it.hasNext()) {
                    JSONObject json = new JSONObject();
                    Object[] obj2 = (Object[]) it.next();
                    if (obj2[0] != null)
                        json.put("div", String.valueOf(obj2[0]));
                    else
                        json.put("div", "");
                    if (obj2[1] != null)
                        json.put("id", String.valueOf(obj2[1]));
                    else
                        json.put("id", "");
                    if (obj2[2] != null)
                        json.put("unitId", String.valueOf(obj2[2]));
                    else
                        json.put("unitId", "");
                    if (obj2[3] != null)
                        json.put("firstReading", String.valueOf(obj2[3]));
                    else
                        json.put("firstReading", "");
                    if (obj2[4] != null)
                        json.put("lastreading", String.valueOf(obj2[4]));
                    else
                        json.put("lastreading", "");
                    if (obj2[5] != null)
                        json.put("consumption", String.valueOf(obj2[5]));
                    else
                        json.put("consumption", "");
                    if (obj2[6] != null)
                        json.put("watercharge", String.valueOf(obj2[6]));
                    else
                        json.put("watercharge", "");
                    if (obj2[7] != null)
                        json.put("metercharge", String.valueOf(obj2[7]));
                    else
                        json.put("metercharge", "");
                    if (obj2[8] != null)
                        json.put("sanitarycharge", String.valueOf(obj2[8]));
                    else
                        json.put("sanitarycharge", "");
                    if (obj2[9] != null)
                        json.put("borewellcharge", String.valueOf(obj2[9]));
                    else
                        json.put("borewellcharge", "");
                    if (obj2[10] != null)
                        json.put("othercharge", String.valueOf(obj2[10]));
                    else
                        json.put("othercharge", "");
                    if (obj2[11] != null)
                        json.put("arrears", String.valueOf(obj2[11]));
                    else
                        json.put("arrears", "");
                    if (obj2[12] != null)
                        json.put("interestonarrears", String.valueOf(obj2[12]));
                    else
                        json.put("interestonarrears", "");
                    if (obj2[13] != null)
                        json.put("totalamount", String.valueOf(obj2[13]));
                    else
                        json.put("totalamount", "");
                    if (obj2[14] != null)
                        json.put("billdate", String.valueOf(obj2[14]));
                    else
                        json.put("billdate", "");
                    if (obj2[15] != null)
                        json.put("duedate", String.valueOf(obj2[15]));
                    else
                        json.put("duedate", "");
                    if (obj2[16] != null)
                        json.put("consumerid", String.valueOf(obj2[16]));
                    else
                        json.put("consumerid", "");
                    if (obj2[17] != null)
                        json.put("consumeraddress", String.valueOf(obj2[17]));
                    else
                        json.put("consumeraddress", "");

                    if (obj2[18] != null)
                        json.put("subdiv", String.valueOf(obj2[18]));
                    else
                        json.put("subdiv", "");
                    
                    ja.add(json);
                }
            }
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
	@GetMapping("/genearateBill")
    public String genearateBill() throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List <Object> ob = new ArrayList <> ();
        JSONArray ja = new JSONArray();
        log.debug("genearateBill");
        Double total = 0.0;
        try {
        	//get the year of bill
        	int year = Calendar.getInstance().get(Calendar.YEAR);
        	int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        	int month1 = Calendar.getInstance().get(Calendar.MONTH) + 1;
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            String status = "BillProcessed";
            List<BillGroup> bgList = bgr.findByCode("PAEW");
            for(BillGroup bg : bgList) {
            	BillGroupDetail bgd = new BillGroupDetail();
            	BillLog bl = new BillLog();
            	//set due date from configuration
    			c.set(Calendar.DAY_OF_MONTH, bg.getDueDays());
    			//c.add(Calendar.DATE, unit.getDueDay());
                Date dueDate = c.getTime();
                Date startDate = new Date();
                Date endDate = new Date();
                
                List<BillGroupDetail> bgdList = bgdr.findByBillMonthAndBillYearAndDivisionIdAndAuthorize(month,year,bg.getDivisionId(),true);
                if(bgdList!=null && bgdList.size()>0) {
                	obj.put("isSuccess", false);
	                obj.put("error", "Bills are already Authorized and hence cannot generated");
                }
                else {
	                bgdList = bgdr.findByBillMonthAndBillYearAndDivisionIdAndAuthorize(month,year,bg.getDivisionId(),false);
	                if(bgdList!=null && bgdList.size()>0)
	                	status = "ReGenerated";
	                	
	            	List<UnitSummary> u = usr.findByDivId(bg.getDivisionId());
	            
		        	//List<UnitSummary> u = new ArrayList<>();
		    		//usr.findAll().forEach(u::add);
	            	
		    		//iterate all units to calculate bill
		    		for(UnitSummary unit : u) {
		    			
		    			total = 0.0;
		    			Double meter = 0.0;
		    			Double sanitary = 0.0;
		    			Double water = 0.0;
		    			Integer id = unit.getId();
		    			Double consumption = 0.0;
		    			Double consumption1 = 0.0;
		    			BillingReportMaster bill = new BillingReportMaster();
		    			//set unit id
		    			bill.setUnitId(id);
		    			//get the minimum consumption for the month
		    			ob = ucdr.getMinCons(id);
		    			if(ob.size()>0) {
							Object[] obj2 = (Object[]) ob.get(0);
							//set the min consumption and date at recorded
							bill.setFirstReading(Double.parseDouble(String.valueOf(obj2[0])));
							bill.setFirstdate(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(obj2[1])));
							bill.setMonthYear(String.valueOf(obj2[1]).substring(5,7)+""+year);
							month = Integer.parseInt(String.valueOf(obj2[1]).substring(5,7));
							
							//do the validation through VEE
							//if missing data added by VEE the consider that value
							ob = ucdr.getMinEstCons(id);
			    			if(ob.size()>0) {
								obj2 = (Object[]) ob.get(0);
								if(bill.getFirstReading()>Double.parseDouble(String.valueOf(obj2[0]))) {
									bill.setFirstReading(Double.parseDouble(String.valueOf(obj2[0])));
									bill.setFirstdate(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(obj2[1])));
								}
			    			}
			    			
			    			//calculate the number of missing values
							c = Calendar.getInstance();
							c.set(Calendar.YEAR, year);
							c.set(Calendar.MONTH, Integer.parseInt(String.valueOf(obj2[1]).substring(5,7)));
							int numDays = c.getActualMaximum(Calendar.DATE);
							ob = ucdr.getMaxComm(id);
							bill.setMissings(numDays-ob.size());
							
							//get the last consumption in the month
			    			ob = ucdr.getMaxCons(id);
			    			if(ob.size()>0) {
								obj2 = (Object[]) ob.get(0);
								//set the last consumption avlue and date
								bill.setLastReading(Double.parseDouble(String.valueOf(obj2[0])));
								bill.setLastdate(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(obj2[1])));
			    			}
			    			
			    			//do the validation through VEE
							//if missing data added by VEE the consider that value
			    			ob = ucdr.getMaxEstCons(id);
			    			if(ob.size()>0) {
								obj2 = (Object[]) ob.get(0);
								if(bill.getLastReading()<Double.parseDouble(String.valueOf(obj2[0]))) {
									bill.setLastReading(Double.parseDouble(String.valueOf(obj2[0])));
									bill.setLastdate(new SimpleDateFormat("yyyy-MM-dd").parse(String.valueOf(obj2[1])));
								}
			    			}
			    			
			    			//calculate the actual consumption for the month
			    			bill.setConsumption(bill.getLastReading() - bill.getFirstReading());
			    			consumption = bill.getConsumption();
			    			consumption1 = bill.getConsumption();
			    			//calculate charges for consumption, sanitary and meter cost
			    			//get the tariff value for the particular unit by division, year and meter type
			    			List<TariffMaster> tl = tmr.findByCategoryIdAndYearsAndDivisionId(unit.getMtrBillType(), year, unit.getDivId());
			    			for(TariffMaster t : tl) {
			    				Double charge = 0.0;
			    				//if consumption lies within the slab then calculate the charge
			    				if(consumption>=t.getSlabMin() && consumption<t.getSlabMax()+1) {
			    					charge=consumption1/1000*t.getTariff();
			    					water+=charge;
			    					//if sanitary charge is fixed then take as it is 
			    					if("Rs".equalsIgnoreCase(t.getSanitaryType())) {
			    	    				sanitary+=  t.getSanitay();
			    	    			}
			    					//if sanitary charge is in percentage, get it from the water charge
			    	    			else {
			    	    				sanitary+= charge*t.getSanitay()/100;
			    	    			}
			    				}
			    				//if consumption is not in the slab, go for the next slab while take full value
			    				else if(consumption>t.getSlabMax()){
			    					charge=(t.getSlabMax()-t.getSlabMin())/1000*t.getTariff();
			    					consumption1 = consumption1-t.getSlabMax();
			    					water+=charge;
			    					if("Rs".equalsIgnoreCase(t.getSanitaryType())) {
			    	    				sanitary+=  t.getSanitay();
			    	    			}
			    	    			else {
			    	    				sanitary+= charge*t.getSanitay()/100;
			    	    			}
			    				}
			    				//get the meter cost may it is fixed one
			    				meter = t.getMeterCost();
			    			}
			    			//set the charges calculated above
			    			bill.setMetercharge(meter);
			    			bill.setSanitarycharge(sanitary);
			    			bill.setWatercharge(water);
			    			bill.setBorewellcharge(tl.get(0).getBoreWell());
			    			//previous month arrears charges and other charges set to zero because non available data
			    			bill.setOthercharge(0.0);
			    			List<BillingReportMaster> br = bmr.getBill(id);
			    			if(br!=null && br.size()>0) {
				    			PaymentProcess pp = ppr.findByBillNo(br.get(0).getId());
				    			if(pp!=null) {
					    			if(pp.isOverpay()) {
					    				bill.setExtrapaid(pp.getBalance()*-1);
					    				bill.setArrears(0.0);
						    			bill.setInterestonarrears(0.0);
					    			}
					    			else {
					    				bill.setArrears(pp.getBalance());
						    			bill.setInterestonarrears(0.0);
					    				bill.setExtrapaid(0.0);
					    			}
				    			}
				    			else {
				    				bill.setArrears(0.0);
					    			bill.setInterestonarrears(0.0);
				    				bill.setExtrapaid(0.0);
				    			}
			    			}
			    			else {
			    				bill.setArrears(0.0);
				    			bill.setInterestonarrears(0.0);
			    				bill.setExtrapaid(0.0);
			    			}
			    			//total the all values
			    			total = bill.getArrears()+bill.getBorewellcharge()-bill.getExtrapaid()+bill.getInterestonarrears()
			    					+bill.getMetercharge()+bill.getOthercharge()+bill.getSanitarycharge()+bill.getWatercharge();
			    			bill.setTotalamount(total);
			    			//set the added date and bill date for current date, this service scheduled on the bill date
			    			bill.setAddedat(date);
			    			bill.setBilldate(date);
			    			bill.setDuedate(dueDate);
			    			//set previous charges if it available
			    			bill.setPreviousbill(0.0);
			    			startDate = bill.getFirstdate();
			    			endDate = bill.getLastdate();
			    			//save the all values in the database
			    			bill = bmr.save(bill);
			    			
			    			Notification n = new Notification();
			    			n.setCrDate(new Date());
			    			n.setEvent("Bill");
			    			n.setMessage("Bill Generated");
			    			n.setStatus(true);
			    			n.setUnitId(unit.getUnitId());
			    			n = nr.save(n);
			    			
		    			}
		    		}
		    		
		    		if(bgdList!=null && bgdList.size()>0) {
		    			for(BillGroupDetail b : bgdList) {
		    				b.setStatus(status);
		    				b.setBillDate(date);
		    				b = bgdr.save(b);
		    			}
                	}
		    		else {
			    		bgd = new BillGroupDetail();
			    		bgd.setBillMonth(month1);
			    		bgd.setBillYear(year);
			    		bgd.setDivisionId(bg.getDivisionId());
			    		bgd.setBillgroupId(bg.getId());
			    		bgd.setBillDate(date);
		    			bgd.setDueDate(dueDate);
			    		bgd.setAuthorize(false);
			    		bgd.setStartDate(startDate);
			    		bgd.setEndDate(endDate);
			    		bgd.setStatus(status);
			    		bgd = bgdr.save(bgd);
		    		}
		    		
		    		bl.setBillGroupdetailId(bgd.getId());
		    		bl.setChangedAt(new Date());
		    		bl.setStatus(status);
		    		bl = blr.save(bl);
		    		
	                obj.put("isSuccess", true);
	                obj.put("error", null);
                }
            }
        } catch (Exception e) {
        	obj.put("isSuccess", false);
            obj.put("error", "Failure");
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
	@GetMapping("/authorizeBill")
    public String authorizeBill() throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        log.debug("authorizeBill");
        try {
        	//get the year of bill
        	int year = Calendar.getInstance().get(Calendar.YEAR);
        	int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
            Date date = new Date();
            String status = "Closed";
            List<BillGroup> bgList = bgr.findByCode("PAEW");
            for(BillGroup bg : bgList) {
            	BillGroupDetail bgd = new BillGroupDetail();
            	BillLog bl = new BillLog();
                
                List<BillGroupDetail> bgdList = bgdr.findByBillMonthAndBillYearAndDivisionIdAndAuthorize(month,year,bg.getDivisionId(),false);
                if(bgdList!=null && bgdList.size()>0) {
                	
		    		for(BillGroupDetail bd : bgdList) {
			    		bd.setStatus(status);
			    		bd.setAuthorize(true);
			    		bd = bgdr.save(bd);
			    		
			    		bl = new BillLog();
			    		bl.setBillGroupdetailId(bd.getId());
			    		bl.setChangedAt(new Date());
			    		bl.setStatus(status);
			    		bl = blr.save(bl);
		    		}
                }
            }
    		obj.put("isSuccess", true);
            obj.put("error", null);
        } catch (Exception e) {
        	obj.put("isSuccess", false);
            obj.put("error", "Failure");
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/validateEstimateDataOld")
    public String validateEstimateDataOld() throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List <Object> ob = new ArrayList <> ();
        JSONArray ja = new JSONArray();
        log.debug("validateEstimateData");
        Double estVal = 0.0;
        Double estVal2 = 0.0;
        try {
    		String yesterdayDate=null; 
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.DATE, -1);
    		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    		yesterdayDate=dateFormat1.format(cal.getTime());
    		
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            Date yesterday = calendar.getTime();
            String date =  dateFormat.format(yesterday).toString();
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        	dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	List<UnitSummary> u = new ArrayList<>();
        	//System.out.println("Date == "+df.parse(df.format(yesterdayDate + " 0" +(1)+":01:00")));
    		usr.findAll().forEach(u::add);
    		for(UnitSummary unit : u) {
    			Integer id = unit.getId(); 
    			String unitid = unit.getUnitId();
    			ob = ucdr.getPrevDayConsData(id);
    			if(ob.size()>0) {
    				//if previous day meter has communicated and has some value
    				double fval = 0.0;
    				double lval = 0.0;
    				int ct = 0;
    				//System.out.println("datetime === "+df.parse(df.format(date + " 01:01:00")));
    				if(ob.size()!=24) {
    					ob = ucdr.getLastDayConsumptionData(id);
    					Object[] obj2 = (Object[]) ob.get(0);
    					if(!"0".equalsIgnoreCase(String.valueOf(obj2[1])))
    						fval = Double.parseDouble(String.valueOf(obj2[1]));
    					else
    						ct++;
    					for(int i=2; i<obj2.length; i++) {
    						if(fval==0.0 && "0".equalsIgnoreCase(String.valueOf(obj2[i]))) {
    							ct++;
    						}
    						else if(fval==0.0 && !"0".equalsIgnoreCase(String.valueOf(obj2[i])) && ct!=0) {
    							fval = Double.parseDouble(String.valueOf(obj2[i]));
    							estVal = fval/(ct+1);
    							ct = 0;
    						}
    						//first value not zero
    						else if(fval!=0.0 && !"0".equalsIgnoreCase(String.valueOf(obj2[i])) && ct==0) {
    							fval = Double.parseDouble(String.valueOf(obj2[i]));
    						}
    						else if(fval!=0.0 && !"0".equalsIgnoreCase(String.valueOf(obj2[i])) && ct!=0) {
    							lval = Double.parseDouble(String.valueOf(obj2[i]));
    							estVal = (lval- fval)/(ct+1);
    							fval= lval;
    							lval= 0.0;
    							ct = 0;
    						}
    						else if(fval!=0.0 && "0".equalsIgnoreCase(String.valueOf(obj2[i]))) {
    							ct++;
    						}
    						if(fval!=0.0 && ct!=0  && lval==0.0) {
    							estVal = fval/(ct+1);
    							ct = 0;
    						}
							if(estVal<0)
								estVal*=-1;
    					}
    					if(!"0".equalsIgnoreCase(String.valueOf(obj2[1])))
    						fval = Double.parseDouble(String.valueOf(obj2[1]));
    					else
    						fval = estVal;
    					ct=1;
    					for(int i=1; i<obj2.length; i++) {
    						if("0".equalsIgnoreCase(String.valueOf(obj2[i]))) {
    							EstUnitConsumptionDetails e = new EstUnitConsumptionDetails();
    	    					e.setConsInMcube(estVal*ct);
    	    					/*if(i<10)
    	    						e.setAddeDat(df.parse(df.format(yesterdayDate + " 0" +(i-1)+":01:00")));
    	    					else
    	    						e.setAddeDat(df.parse(df.format(yesterdayDate + " " +(i-1)+":01:00")));*/
    	    					e.setDayCons(estVal);
    	    					//e.setDateRecorded(dateFormat.parse(dateFormat.format(date)));
    	    					e.setDateRecorded(new SimpleDateFormat("yyyy-MM-dd").parse(yesterdayDate));
    	    					e.setUnitId(unitid);
    	    					ecdr.save(e);
    	    					ct++;
    						}
    					}
    				}
    				else {
    					
    				}
    			}
    			else {
    				//previous day meter not communicated
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
    				//insert into estimation table for the whole day data
    				for(int i=0; i<24; i++) {
    					EstUnitConsumptionDetails e = new EstUnitConsumptionDetails();
    					e.setConsInMcube(estVal);
    					/*if(i<10)
    						e.setAddeDat(df.parse(df.format(yesterdayDate + " 0" +i+":01:00")));
    					else
    						e.setAddeDat(df.parse(df.format(yesterdayDate + " " +i+":01:00")));*/
    					e.setDayCons(estVal);
    					//e.setDateRecorded(dateFormat.parse(dateFormat.format(date)));
    					e.setDateRecorded(new SimpleDateFormat("yyyy-MM-dd").parse(yesterdayDate));
    					e.setUnitId(unitid);
    					ecdr.save(e);
    				}
    			}
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @GetMapping("/validateEstimateData")
    public String validateEstimateData() throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List <Object> ob = new ArrayList <> ();
        JSONArray ja = new JSONArray();
        log.debug("validateEstimateData");
        Double estVal = 0.0;
        Double lastVal = 0.0;
        try {
    		String yesterdayDate=null; 
    		Calendar cal = Calendar.getInstance();
    		cal.add(Calendar.DATE, -1);
    		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    		yesterdayDate=dateFormat1.format(cal.getTime());
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
    					e.setDateRecorded(new SimpleDateFormat("yyyy-MM-dd").parse(yesterdayDate));
    					e.setDateRecorded(e.getAddeDat());
    					e.setUnitId(unitid);
    					ecdr.save(e);
    				}
    			}
    			//case 2 only once communicated
    			else if(ob.size()==1) {
    				Object[] obj2 = (Object[]) ob.get(0);
    				int temp = Integer.parseInt(String.valueOf(obj2[5]));
    				estVal = Double.parseDouble(String.valueOf(obj2[3]));
    				for(int i=0; i<24; i++) {
    					if(i!=temp) {
	    					EstUnitConsumptionDetails e = new EstUnitConsumptionDetails();
	    					e.setConsInMcube(estVal);
	    					e.setDayCons(estVal);
	    					e.setAddeDat(df.parse(String.valueOf(yesterdayDate + " " +i+":01:00")));
	    					e.setDateRecorded(new SimpleDateFormat("yyyy-MM-dd").parse(yesterdayDate));
	    					e.setDateRecorded(e.getAddeDat());
	    					e.setUnitId(unitid);
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
	    					e.setDateRecorded(new SimpleDateFormat("yyyy-MM-dd").parse(yesterdayDate));
	    					e.setDateRecorded(e.getAddeDat());
	    					e.setUnitId(unitid);
	    					ecdr.save(e);
						}
    				}
    			}
    		}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    
    @SuppressWarnings("unchecked")
    @GetMapping("/getVeeData/{div}/{subdiv}/{duration}")
    public String getVeeData(@PathVariable Integer div,
        @PathVariable Integer subdiv, @PathVariable String duration)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("getVeeData");

        try {
            if (div != null && subdiv != null && duration != null) {
            	if("last".equalsIgnoreCase(duration))
            		ob = bmr.getLastVeeDataList(div, subdiv);
            	else
            		ob = bmr.getVeeDataList(div, subdiv, duration);
            	String id = "";
            	String dt = "";
            	String id1 = "";
            	String dt1 = "";
                if (ob.size() > 0) {
                	Object[] obj2 = null;
                	for(int i = 0; i <= ob.size()-1; )
                	{
                		JSONObject json = new JSONObject();
                		obj2 = (Object[]) ob.get(i);
                        id = String.valueOf(obj2[0]);
                        dt = String.valueOf(obj2[1]);
                        int ct = 0;
                        JSONObject o = new JSONObject();
                        if(String.valueOf(obj2[2]).equalsIgnoreCase("2")) {
                        	System.out.println(String.valueOf(obj2[0]) +","+String.valueOf(obj2[1])+","+String.valueOf(obj2[2]));
                        	json = new JSONObject();
                            json.put("id", String.valueOf(obj2[0]));
                            json.put("day", String.valueOf(obj2[1]));
                            json.put("flag", "0");
                            
                            if (!"0".equalsIgnoreCase(String.valueOf(obj2[3]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[3]));
	                            json.put("h0", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[4]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[4]));
	                            json.put("h1", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[5]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[5]));
	                            json.put("h2", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[6]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[6]));
	                            json.put("h3", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[7]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[7]));
	                            json.put("h4", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[8]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[8]));
	                            json.put("h5", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[9]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[9]));
	                            json.put("h6", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[10]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[10]));
	                            json.put("h7", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[11]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[11]));
	                            json.put("h8", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[12]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[12]));
	                            json.put("h9", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[13]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[13]));
	                            json.put("h10", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[14]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[14]));
	                            json.put("h11", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[15]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[15]));
	                            json.put("h12", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[16]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[16]));
	                            json.put("h13", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[17]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[17]));
	                            json.put("h14", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[18]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[18]));
	                            json.put("h15", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[19]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[19]));
	                            json.put("h16", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[20]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[20]));
	                            json.put("h17", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[21]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[21]));
	                            json.put("h18", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[22]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[22]));
	                            json.put("h19", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[23]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[23]));
	                            json.put("h20", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[24]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[24]));
	                            json.put("h21", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[25]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[25]));
	                            json.put("h22", o);
	                        }
	                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[26]))) {
	                        	o = new JSONObject();
	                        	o.put("flag", "2");
	                            o.put("val", String.valueOf(obj2[26]));
	                            json.put("h23", o);
	                        }
                            if(i < ob.size()-1) {
                            	i++;
                            }
                        	id1 = id;
                        	dt1 = dt;
                        	ct = 2;
                        }
                        System.out.println("i = "+i);
                        obj2 = (Object[]) ob.get(i);
                        if(String.valueOf(obj2[2]).equalsIgnoreCase("1")) {
                        	System.out.println(String.valueOf(obj2[0]) +","+String.valueOf(obj2[1])+","+String.valueOf(obj2[2]));
                        	id = String.valueOf(obj2[0]);
                            dt = String.valueOf(obj2[1]);
                            if(id1.equalsIgnoreCase(id) && dt1.equalsIgnoreCase(dt)) {
                            	json.put("id", String.valueOf(obj2[0]));
		                        json.put("day", String.valueOf(obj2[1]));
		                        json.put("flag", "0");
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[3]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[3]));
		                            json.put("h0", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[4]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[4]));
		                            json.put("h1", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[5]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[5]));
		                            json.put("h2", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[6]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[6]));
		                            json.put("h3", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[7]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[7]));
		                            json.put("h4", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[8]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[8]));
		                            json.put("h5", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[9]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[9]));
		                            json.put("h6", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[10]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[10]));
		                            json.put("h7", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[11]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[11]));
		                            json.put("h8", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[12]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[12]));
		                            json.put("h9", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[13]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[13]));
		                            json.put("h10", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[14]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[14]));
		                            json.put("h11", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[15]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[15]));
		                            json.put("h12", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[16]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[16]));
		                            json.put("h13", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[17]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[17]));
		                            json.put("h14", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[18]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[18]));
		                            json.put("h15", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[19]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[19]));
		                            json.put("h16", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[20]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[20]));
		                            json.put("h17", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[21]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[21]));
		                            json.put("h18", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[22]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[22]));
		                            json.put("h19", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[23]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[23]));
		                            json.put("h20", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[24]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[24]));
		                            json.put("h21", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[25]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[25]));
		                            json.put("h22", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[26]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[26]));
		                            json.put("h23", o);
		                        }
		                        ct = 1;
                            }
		                    else {
		                    	if(ct == 2) {
		                    		ja.add(json);
		                    		ct = 1;
		                    	}	
		                        json = new JSONObject();
		                        json.put("id", String.valueOf(obj2[0]));
		                        json.put("day", String.valueOf(obj2[1]));
		                        //json.put("flag", String.valueOf(obj2[2]));
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[3]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[3]));
		                            json.put("h0", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[4]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[4]));
		                            json.put("h1", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[5]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[5]));
		                            json.put("h2", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[6]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[6]));
		                            json.put("h3", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[7]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[7]));
		                            json.put("h4", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[8]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[8]));
		                            json.put("h5", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[9]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[9]));
		                            json.put("h6", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[10]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[10]));
		                            json.put("h7", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[11]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[11]));
		                            json.put("h8", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[12]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[12]));
		                            json.put("h9", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[13]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[13]));
		                            json.put("h10", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[14]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[14]));
		                            json.put("h11", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[15]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[15]));
		                            json.put("h12", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[16]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[16]));
		                            json.put("h13", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[17]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[17]));
		                            json.put("h14", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[18]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[18]));
		                            json.put("h15", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[19]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[19]));
		                            json.put("h16", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[20]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[20]));
		                            json.put("h17", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[21]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[21]));
		                            json.put("h18", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[22]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[22]));
		                            json.put("h19", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[23]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[23]));
		                            json.put("h20", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[24]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[24]));
		                            json.put("h21", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[25]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[25]));
		                            json.put("h22", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[26]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "1");
		                            o.put("val", String.valueOf(obj2[26]));
		                            json.put("h23", o);
		                        }
		                    }
                            ct = 1;
                            if(i < ob.size()-1) {
                            	i++;
                            }
                        	id1 = id;
                        	dt1 = dt;
                        } 
                        System.out.println("i = "+i);
                        obj2 = (Object[]) ob.get(i);
                        if(String.valueOf(obj2[2]).equalsIgnoreCase("0")) {
                        	System.out.println(String.valueOf(obj2[0]) +","+String.valueOf(obj2[1])+","+String.valueOf(obj2[2]));
                        	id = String.valueOf(obj2[0]);
                            dt = String.valueOf(obj2[1]);
                            if(id1.equalsIgnoreCase(id) && dt1.equalsIgnoreCase(dt)) {
                            	json.put("id", String.valueOf(obj2[0]));
		                        json.put("day", String.valueOf(obj2[1]));
		                        json.put("flag", "0");
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[3]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[3]));
		                            json.put("h0", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[4]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[4]));
		                            json.put("h1", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[5]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[5]));
		                            json.put("h2", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[6]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[6]));
		                            json.put("h3", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[7]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[7]));
		                            json.put("h4", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[8]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[8]));
		                            json.put("h5", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[9]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[9]));
		                            json.put("h6", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[10]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[10]));
		                            json.put("h7", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[11]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[11]));
		                            json.put("h8", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[12]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[12]));
		                            json.put("h9", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[13]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[13]));
		                            json.put("h10", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[14]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[14]));
		                            json.put("h11", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[15]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[15]));
		                            json.put("h12", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[16]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[16]));
		                            json.put("h13", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[17]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[17]));
		                            json.put("h14", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[18]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[18]));
		                            json.put("h15", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[19]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[19]));
		                            json.put("h16", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[20]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[20]));
		                            json.put("h17", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[21]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[21]));
		                            json.put("h18", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[22]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[22]));
		                            json.put("h19", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[23]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[23]));
		                            json.put("h20", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[24]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[24]));
		                            json.put("h21", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[25]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[25]));
		                            json.put("h22", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[26]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[26]));
		                            json.put("h23", o);
		                        }
		                        ct = 0;
                            }
		                    else {
		                    	if(ct == 1) {
		                    		ja.add(json);
		                    		ct = 1;
		                    	}	
		                        json = new JSONObject();
		                        json.put("id", String.valueOf(obj2[0]));
		                        json.put("day", String.valueOf(obj2[1]));
		                        json.put("flag", String.valueOf(obj2[2]));
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[3]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[3]));
		                            json.put("h0", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[4]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[4]));
		                            json.put("h1", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[5]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[5]));
		                            json.put("h2", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[6]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[6]));
		                            json.put("h3", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[7]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[7]));
		                            json.put("h4", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[8]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[8]));
		                            json.put("h5", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[9]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[9]));
		                            json.put("h6", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[10]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[10]));
		                            json.put("h7", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[11]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[11]));
		                            json.put("h8", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[12]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[12]));
		                            json.put("h9", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[13]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[13]));
		                            json.put("h10", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[14]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[14]));
		                            json.put("h11", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[15]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[15]));
		                            json.put("h12", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[16]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[16]));
		                            json.put("h13", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[17]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[17]));
		                            json.put("h14", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[18]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[18]));
		                            json.put("h15", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[19]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[19]));
		                            json.put("h16", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[20]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[20]));
		                            json.put("h17", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[21]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[21]));
		                            json.put("h18", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[22]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[22]));
		                            json.put("h19", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[23]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[23]));
		                            json.put("h20", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[24]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[24]));
		                            json.put("h21", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[25]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[25]));
		                            json.put("h22", o);
		                        }
		                        if (!"0".equalsIgnoreCase(String.valueOf(obj2[26]))) {
		                        	o = new JSONObject();
		                        	o.put("flag", "0");
		                            o.put("val", String.valueOf(obj2[26]));
		                            json.put("h23", o);
		                        }
		                    }
                            ct = 0;
                           //if(i < ob.size()-1) {
                            	i++;
                            //}
                        	id1 = id;
                        	dt1 = dt;
                        }
                        ja.add(json);
                        if(i == ob.size()-1)
                        	break;
                        System.out.println("i = "+i);
                	}
                }
            }
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
	@PostMapping("/updateVee")
    public JSONObject updateVee(@RequestBody String stringToParse) throws java.text.ParseException{
		
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject data = new JSONObject();
        List <Object> ob = new ArrayList <> ();
        try {
        	if(stringToParse!=null) {
	        	json.put("issuccess", true);
	        	json.put("code", 200);
	        	data = (JSONObject) parser.parse(stringToParse);
	        	ob = ecdr.getEstimatedData(data.get("id").toString(),data.get("date").toString(),data.get("hour").toString().substring(1));
	        	EstUnitConsumptionDetails e = new EstUnitConsumptionDetails();
	        	
	        	if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        Object[] obj2 = (Object[]) it.next();
                        e.setId(Integer.parseInt(String.valueOf(obj2[0])));
                    }
                }
	        	e = ecdr.findById(e.getId());
	        	e.setConsInMcube(Double.parseDouble(data.get("val").toString()));
	        	e.setDayCons(Double.parseDouble(data.get("val").toString()));
	        	e.setFlag(2);
	        	
	        	e = ecdr.save(e);
	        	json.put("data", e);
	        	
	        	ac.writeLog("VEE is upated", "User : "+data.get("userid") +" Updated VEE for the ID : "
	        	+data.get("id").toString() +" during "+data.get("date").toString() + " :"+data.get("hour").toString() 
	        	+" value = "+data.get("val").toString(), alr);
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        	json.put("issuccess", false);
        	json.put("code", 500);
		}

		return json;
	}
    
    @SuppressWarnings("unchecked")
    @GetMapping("/getMassBalanceData/{div}/{subdiv}/{duration}")
    public String getMassBalanceData(@PathVariable Integer div,
        @PathVariable Integer subdiv, @PathVariable String duration)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("getMassBalanceData");

        try {
            if (div != null && subdiv != null && duration != null) {
                ob = qdr.getQualityDataList1(div, subdiv, duration);
                if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        JSONObject json = new JSONObject();
                        Object[] obj2 = (Object[]) it.next();

                        if (obj2[0] != null)
                            json.put("div", String.valueOf(obj2[0]));
                        else
                            json.put("div", "");
                        if (obj2[1] != null)
                            json.put("subdiv", String.valueOf(obj2[1]));
                        else
                            json.put("subdiv", "");
                        if (obj2[2] != null)
                            json.put("actual", String.valueOf(obj2[2]));
                        else
                            json.put("actual", "");
                        if (obj2[3] != null)
                            json.put("total", String.valueOf(obj2[3]));
                        else
                            json.put("total", "");
                        if (obj2[4] != null)
                            json.put("loss", String.valueOf(obj2[4]));
                        else
                            json.put("loss", "");
                        if (obj2[5] != null)
                            json.put("dt", String.valueOf(obj2[5]));
                        else
                            json.put("dt", "");
                        if (obj2[6] != null)
                            json.put("duration", String.valueOf(obj2[6]));
                        else
                            json.put("duration", "");

                        ja.add(json);
                    }
                }
            }
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/getQualityData/{div}/{subdiv}/{duration}")
    public String getQualityData(@PathVariable Integer div,
        @PathVariable Integer subdiv, @PathVariable String duration)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("getQualityData");

        try {
            if (div != null && subdiv != null && duration != null) {
                ob = qdr.getQualityDataList(div, subdiv, duration);
                if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        JSONObject json = new JSONObject();
                        Object[] obj2 = (Object[]) it.next();

                        if (obj2[0] != null)
                            json.put("unitId", String.valueOf(obj2[0]));
                        else
                            json.put("unitId", "");
                        if (obj2[1] != null)
                            json.put("div", String.valueOf(obj2[1]));
                        else
                            json.put("div", "");
                        if (obj2[2] != null)
                            json.put("subdiv", String.valueOf(obj2[2]));
                        else
                            json.put("subdiv", "");
                        if (obj2[3] != null)
                            json.put("parameter", String.valueOf(obj2[3]));
                        else
                            json.put("parameter", "");
                        if (obj2[4] != null)
                            json.put("value", String.valueOf(obj2[4]));
                        else
                            json.put("value", "");
                        if (obj2[5] != null)
                            json.put("min", String.valueOf(obj2[5]));
                        else
                            json.put("min", "");
                        if (obj2[6] != null)
                            json.put("max", String.valueOf(obj2[6]));
                        else
                            json.put("max", "");
                        if (obj2[7] != null)
                            json.put("remarks", String.valueOf(obj2[7]));
                        else
                            json.put("remarks", "");
                        if (obj2[8] != null)
                            json.put("dt", String.valueOf(obj2[8]));
                        else
                            json.put("dt", "");

                        ja.add(json);
                    }
                }
            }
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/getEventData/{div}/{subdiv}/{duration}")
    public String getEventData(@PathVariable Integer div,
        @PathVariable Integer subdiv, @PathVariable String duration)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("getEventData");

        try {
            if (div != null && subdiv != null && duration != null) {
                ob = bmr.getBillingList(div, subdiv, duration);
                if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        JSONObject json = new JSONObject();
                        Object[] obj2 = (Object[]) it.next();

                        if (obj2[0] != null)
                            json.put("id", String.valueOf(obj2[0]));
                        else
                            json.put("id", "");
                        if (obj2[1] != null)
                            json.put("monthYear", String.valueOf(obj2[1]));
                        else
                            json.put("monthYear", "");
                        if (obj2[2] != null)
                            json.put("unitId", String.valueOf(obj2[2]));
                        else
                            json.put("unitId", "");
                        if (obj2[3] != null)
                            json.put("firstReading", String.valueOf(obj2[3]));
                        else
                            json.put("firstReading", "");
                        if (obj2[4] != null)
                            json.put("lastreading", String.valueOf(obj2[4]));
                        else
                            json.put("lastreading", "");
                        if (obj2[5] != null)
                            json.put("consumption", String.valueOf(obj2[5]));
                        else
                            json.put("consumption", "");
                        if (obj2[6] != null)
                            json.put("watercharge", String.valueOf(obj2[6]));
                        else
                            json.put("watercharge", "");
                        if (obj2[7] != null)
                            json.put("metercharge", String.valueOf(obj2[7]));
                        else
                            json.put("metercharge", "");
                        if (obj2[8] != null)
                            json.put("sanitarycharge", String.valueOf(obj2[8]));
                        else
                            json.put("sanitarycharge", "");
                        if (obj2[9] != null)
                            json.put("borewellcharge", String.valueOf(obj2[9]));
                        else
                            json.put("borewellcharge", "");
                        if (obj2[10] != null)
                            json.put("othercharge", String.valueOf(obj2[10]));
                        else
                            json.put("othercharge", "");
                        if (obj2[11] != null)
                            json.put("arrears", String.valueOf(obj2[11]));
                        else
                            json.put("arrears", "");
                        if (obj2[12] != null)
                            json.put("interestonarrears", String.valueOf(obj2[12]));
                        else
                            json.put("interestonarrears", "");
                        if (obj2[13] != null)
                            json.put("totalamount", String.valueOf(obj2[13]));
                        else
                            json.put("totalamount", "");
                        if (obj2[14] != null)
                            json.put("addedat", String.valueOf(obj2[14]));
                        else
                            json.put("addedat", "");
                        if (obj2[15] != null)
                            json.put("duedate", String.valueOf(obj2[15]));
                        else
                            json.put("duedate", "");
                        if (obj2[16] != null)
                            json.put("billdate", String.valueOf(obj2[16]));
                        else
                            json.put("billdate", "");
                        if (obj2[17] != null)
                            json.put("firstdate", String.valueOf(obj2[17]));
                        else
                            json.put("firstdate", "");
                        if (obj2[18] != null)
                            json.put("lastdate", String.valueOf(obj2[18]));
                        else
                            json.put("lastdate", "");
                        if (obj2[19] != null)
                            json.put("missings", String.valueOf(obj2[19]));
                        else
                            json.put("missings", "");
                        if (obj2[20] != null)
                            json.put("extrapaid", String.valueOf(obj2[20]));
                        else
                            json.put("extrapaid", "");
                        if (obj2[21] != null)
                            json.put("previousbill", String.valueOf(obj2[21]));
                        else
                            json.put("previousbill", "");
                        if (obj2[22] != null)
                            json.put("div", String.valueOf(obj2[22]));
                        else
                            json.put("div", "");
                        if (obj2[23] != null)
                            json.put("subdiv", String.valueOf(obj2[23]));
                        else
                            json.put("subdiv", "");

                        ja.add(json);
                    }
                }
            }
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/getMNFData/{div}/{subdiv}/{duration}")
    public String getMNFData(@PathVariable Integer div,
        @PathVariable Integer subdiv, @PathVariable String duration)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("getMNFData");

        try {
            if (div != null && subdiv != null && duration != null) {
                ob = mr.getMnfDataList(div, subdiv, duration);
                if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        JSONObject json = new JSONObject();
                        Object[] obj2 = (Object[]) it.next();

                        if (obj2[0] != null)
                            json.put("div", String.valueOf(obj2[0]));
                        else
                            json.put("div", "");
                        if (obj2[1] != null)
                            json.put("subdiv", String.valueOf(obj2[1]));
                        else
                            json.put("subdiv", "");
                        if (obj2[2] != null)
                            json.put("loss", String.valueOf(obj2[2]));
                        else
                            json.put("loss", "");
                        if (obj2[3] != null)
                            json.put("dt", String.valueOf(obj2[3]));
                        else
                            json.put("dt", "");

                        ja.add(json);
                    }
                }
            }
            
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/getWaterDemandData/{div}/{subdiv}/{duration}")
    public String getWaterDemandData(@PathVariable Integer div,
        @PathVariable Integer subdiv, @PathVariable String duration)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("getWaterDemandData");

        try {
            if (div != null && subdiv != null && duration != null) {
                ob = wr.getWaterDemandDataList(div, subdiv, duration);
                if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        JSONObject json = new JSONObject();
                        Object[] obj2 = (Object[]) it.next();

                        if (obj2[0] != null)
                            json.put("div", String.valueOf(obj2[0]));
                        else
                            json.put("div", "");
                        if (obj2[1] != null)
                            json.put("subdiv", String.valueOf(obj2[1]));
                        else
                            json.put("subdiv", "");
                        if (obj2[2] != null)
                            json.put("demand_type", String.valueOf(obj2[2]));
                        else
                            json.put("demand_type", "");
                        if (obj2[3] != null)
                            json.put("usage_type", String.valueOf(obj2[3]));
                        else
                            json.put("usage_type", "");
                        if (obj2[4] != null)
                            json.put("variation_type", String.valueOf(obj2[4]));
                        else
                            json.put("variation_type", "");
                        if (obj2[5] != null)
                            json.put("value", String.valueOf(obj2[5]));
                        else
                            json.put("value", "");
                        if (obj2[6] != null)
                            json.put("dt", String.valueOf(obj2[6]));
                        else
                            json.put("dt", "");

                        ja.add(json);
                    }
                }
            }
            JSONObject json = new JSONObject();
            json.put("div", "Poe");
            json.put("subdiv", "Test");
            json.put("dma", "Abc");
            json.put("location", "Xyz");
            json.put("water", "100");
            json.put("day", "100");
            json.put("day", "01-04-2020");
            ja.add(json);
            
            json = new JSONObject();
            json.put("div", "Poe");
            json.put("subdiv", "Test");
            json.put("dma", "Abc");
            json.put("location", "Xyz");
            json.put("water", "100");
            json.put("day", "02-04-2020");
            ja.add(json);
            
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
	@GetMapping("/getPaymentModeList")
    public String getPaymentModeList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("issuccess", true);
		obj.put("code", 200);
		obj1.put("error", obj);
		int val = 0;
		
    	obj = new JSONObject();
        obj.put("name", "Cash");
        obj.put("id", "Cash");
        ja.add(val++,obj);
        obj1.put("data", ja);
        
        obj = new JSONObject();
        obj.put("name", "Credit Card");
        obj.put("id", "Credit Card");
        ja.add(val++,obj);
        obj1.put("data", ja);
        
        obj = new JSONObject();
        obj.put("name", "Debit Card");
        obj.put("id", "Debit Card");
        ja.add(val++,obj);
        obj1.put("data", ja);
        
        obj = new JSONObject();
        obj.put("name", "Net Banking");
        obj.put("id", "Net Banking");
        ja.add(val++,obj);
        obj1.put("data", ja);
        
        obj = new JSONObject();
        obj.put("name", "Phone Pe");
        obj.put("id", "Phone Pe");
        ja.add(val++,obj);
        obj1.put("data", ja);
        
		return obj1.toString();
	}
    
    @SuppressWarnings("unchecked")
    @GetMapping("/getPaymentData/{unitid}")
    public String getPaymentData(@PathVariable String unitid)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("getUnitBill");

        try {
            if (unitid != null) {
                ob = bmr.getUnitPayment(unitid);
                if (ob.size() > 0) {
                    Iterator it = ob.iterator();
                    while (it.hasNext()) {
                        JSONObject json = new JSONObject();
                        Object[] obj2 = (Object[]) it.next();
                        if (obj2[0] != null)
                            json.put("id", String.valueOf(obj2[0]));
                        else
                            json.put("id", "");
                        if (obj2[1] != null)
                            json.put("amount", String.valueOf(obj2[1]));
                        else
                            json.put("amount", "");
                        if (obj2[2] != null)
                            json.put("billdate", String.valueOf(obj2[2]));
                        else
                            json.put("billdate", "");
                        if (obj2[3] != null)
                            json.put("consumerid", String.valueOf(obj2[3]));
                        else
                            json.put("consumerid", "");
                        if (obj2[4] != null)
                            json.put("consumeraddress", String.valueOf(obj2[4]));
                        else
                            json.put("consumeraddress", "");
                        if (obj2[5] != null)
                            json.put("rrno", String.valueOf(obj2[5]));
                        else
                            json.put("rrno", "");
                        
                        ja.add(json);
                        break;
                    }
                }
            }
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/makePayment/{billno}/{amount}/{counter}")
    public String makePayment(@PathVariable Integer billno, @PathVariable Double amount, @PathVariable Integer counter)
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        log.debug("makePayment");

        try {
        	PaymentDetails pd = new PaymentDetails();
        	pd = pdr.findByBillNo(billno);
        	if(pd!= null && pd.getAmount()!=null)
        		obj.put("data", "Payment already made");
        	else {
	        	pd = new PaymentDetails();
	        	pd.setBillNo(billno);
	        	pd.setAmount(amount);
	        	pd.setModeOfPay("Cash");
	        	pd.setAddedAt(new Date());
	        	pdr.save(pd);
	        	obj.put("data", "Payment made successfull");
	        	
	        	CounterData c = new CounterData();
	        	c = cr.findById(counter);
	        	c.setCash(c.getCash()+amount);
	        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        	dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	        	Date date = new Date();
	        	c.setModDt(dateFormat.parse(dateFormat.format(date)));
	        	c = cr.save(c);
        	}
            
            obj.put("isSuccess", true);
            obj.put("error", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/paymentProcess")
    public String paymentProcess()
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("paymentProcess");

        try {
            ob = ppr.getPaymentDetails();
            if (ob.size() > 0) {
                Iterator it = ob.iterator();
                while (it.hasNext()) {
                	PaymentProcess pp = new PaymentProcess();
                    Object[] obj2 = (Object[]) it.next();
                    pp.setBillNo(Integer.parseInt(String.valueOf(obj2[0])));
                    pp.setAmount(Double.parseDouble(String.valueOf(obj2[1])));
                    pp.setPaid(Double.parseDouble(String.valueOf(obj2[2])));
                    pp.setBalance(Double.parseDouble(String.valueOf(obj2[3])));
                    pp.setOverpay(Boolean.parseBoolean(String.valueOf(obj2[4])));
                    pp.setProcessedAt(new Date());
                    
                    pp = ppr.save(pp);
                }
            }
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
    @GetMapping("/paymentProcessScheduler")
    public String paymentProcessScheduler()
    throws java.text.ParseException {

        JSONObject obj = new JSONObject();
        List < Object > ob = new ArrayList < > ();
        JSONArray ja = new JSONArray();
        log.debug("paymentProcessScheduler");

        try {
            ob = ppr.getPaymentDetailsScd();
            if (ob.size() > 0) {
                Iterator it = ob.iterator();
                while (it.hasNext()) {
                	PaymentProcess pp = new PaymentProcess();
                    Object[] obj2 = (Object[]) it.next();
                    pp.setBillNo(Integer.parseInt(String.valueOf(obj2[0])));
                    pp.setAmount(Double.parseDouble(String.valueOf(obj2[1])));
                    pp.setPaid(Double.parseDouble(String.valueOf(obj2[2])));
                    pp.setBalance(Double.parseDouble(String.valueOf(obj2[3])));
                    pp.setOverpay(Boolean.parseBoolean(String.valueOf(obj2[4])));
                    pp.setProcessedAt(new Date());
                    
                    pp = ppr.save(pp);
                }
            }
            obj.put("isSuccess", true);
            obj.put("error", null);
            obj.put("data", ja);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj.toString();
    }
    
    @SuppressWarnings("unchecked")
	@GetMapping("/getCounterList")
    public String getCounterList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getCounterList");
        
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
	@GetMapping("/getShiftList")
    public String getShiftList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getShiftList");
        
        try {
        	JSONObject json = new JSONObject();
			json.put("id", "07:00-11:59");
			json.put("code", "07:00-11:59");
			ja.add(json);
			json = new JSONObject();
			json.put("id", "12:00-15:59");
			json.put("code", "12:00-15:59");
			ja.add(json);
			json = new JSONObject();
			json.put("id", "16:00-19:59");
			json.put("code", "16:00-19:59");
			ja.add(json);
			json = new JSONObject();
			json.put("id", "20:00-23:59");
			json.put("code", "20:00-23:59");
			ja.add(json);
			obj.put("issuccess", true);
			obj.put("error", null);
			obj.put("data", ja);
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
	@GetMapping("/getCollectionTypeList")
    public String getCollectionTypeList() throws java.text.ParseException{
		JSONObject obj = new JSONObject();
        List<Object> ob = new ArrayList<>();
        JSONArray ja = new JSONArray();
        log.debug("Inside getCollectionTypeList");
        
        try {
			JSONObject json = new JSONObject();
			json.put("id", "Auto");
			json.put("code", "Auto");
			ja.add(json);
			json = new JSONObject();
			json.put("id", "Manual");
			json.put("code", "Manual");
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
	@PostMapping("/insertCounterData")
    public JSONObject insertCounterData(@RequestBody String stringToParse) throws java.text.ParseException{
		JSONParser parser = new JSONParser();
        JSONObject json = new JSONObject();
        JSONObject obj = new JSONObject();
		JSONObject obj1 = new JSONObject();
		
        try {
            json = (JSONObject) parser.parse(stringToParse);
        	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        	dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        	Date date = new Date();
        	CounterData c = new CounterData();
        	c.setUserId(json.get("userid").toString());
        	c.setShift(json.get("shift").toString());
        	c.setCollectionType(json.get("collectiontype").toString());
        	c.setCounterNo(json.get("counterno").toString());
        	c.setCash(0.0);
        	c.setCrDt(dateFormat.parse(dateFormat.format(date)));
        	
        	c = cr.save(c);
        	ac.writeLog("Counter data is inserted", "Counter id is : "+c.getId(), alr);
        	
        	obj1.put("issuccess", true);
    		obj.put("code", 200);
    		obj1.put("error", obj);
    		obj1.put("data", c.getId());
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj1;
    }
    
}