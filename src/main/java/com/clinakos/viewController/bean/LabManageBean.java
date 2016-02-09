package com.clinakos.viewController.bean;

//import java.io.File;
//import java.io.InputStream;
import static com.clinakos.common.util.ClinakosConstant.HEPATITIS_C_CLINIC_NAME;
//import com.clinakos.service.serviceImpl.PatientMedicineServiceImpl;
import static com.clinakos.common.util.ClinakosConstant.PSYCHOPHARM_CLINIC_NAME;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
//import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.DateUtil;
import com.clinakos.data.model.core.EmdeonLabResult;
//import com.clinakos.data.model.patient.AllergyMaster;
import com.clinakos.data.model.patient.GenericMedActionPlan;
import com.clinakos.data.model.patient.LabLineBarChart;
import com.clinakos.data.model.patient.LabResultDetailClinakos;
import com.clinakos.data.model.patient.LoincKeyMaster;
//import com.clinakos.data.model.patient.MedActionPlan;
import com.clinakos.data.model.patient.MedandGenricmed;
import com.clinakos.data.model.patient.ORUPatientLabResult;

import com.clinakos.data.model.patient.EmdeonLabOrders;
//import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.PatientMedicationHistory;
import com.clinakos.data.model.patient.PatientVital;
import com.clinakos.data.model.patient.PharmacogenomicsUserSummary;
import com.clinakos.data.model.patient.PharmacyDetail;
import com.clinakos.data.model.patient.ProcedureResultHistory;
import com.clinakos.data.model.patient.ProcedureType;
import com.clinakos.data.model.patient.SigCode;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.service.ILabService;
import com.clinakos.service.IPatientMedicineService;
import com.clinakos.viewController.webservicMangedBean.NcLabDetailWSBean;
import com.google.gson.Gson;
//import com.google.inject.internal.cglib.proxy.CallbackGenerator.Context;


public class LabManageBean implements Serializable {
	public static final Logger logger = Logger.getLogger("LabManageBean.class");
	/**
	 * 
	 */
	private static final long serialVersionUID = 3508183272713287771L;

	private ILabService labService; 
	private IPatientMedicineService patientMedicineService; 
    private UserLoginDetail userlogindetail;
	private ProcedureResultHistory procedureResultHistory;
	private PatientVital patientVital;
	private LoincKeyMaster loincKeyMaster = new LoincKeyMaster();
	private ProcedureType procedureType=new ProcedureType();
	
	private double labtestMax;
	private String resultMaxMinValue;
	private double labtestMin;
	private String chartPeriod;
	private Date labReminderDate=new DateUtil().AddDate(new Date(), 7); //--calling date util method to get date after 7 days
	
	private DataTable labDataTable; // DataTable object for get selected lab data 
	private CartesianChartModel labtestChart = new CartesianChartModel();
		
	public List<PharmacogenomicsUserSummary> pharmacogenomicsUserSummaryList = null;// new
	private List<ProcedureResultHistory> procedureResultsForPatient;	// ArrayList<PharmacogenomicsUserSummary>();
	private List<PharmacogenomicsUserSummary> pharmacogenomicsSummaryBasedOnMedicineList;
	private List<LoincKeyMaster> loincKeyMasterList;
	private List<ProcedureResultHistory> procedureResultHistoryListForChart;
	private List<ProcedureResultHistory> procedureResultHistoryList;
	//private List<ProcedureResult> procedureResultList=null;
	private List<ProcedureResultHistory> procedureResultListForCurrentLab=null; // List of ProcedureResultHistory for Current Lab Data 
	public List<ProcedureResultHistory>procedureResultListForCreatinine=null;
	private List<MedandGenricmed>labReminderList=null;
	public List<ProcedureResultHistory> labHistoryListForAntiCoag=null;
	//private labChartForAnticoag	
	 private CartesianChartModel labChartForAnticoag; 
	 private List<ProcedureType> labTypeMasterList;
	 int rowIdnumberForLabHistory;
	 int rowIdnumberForCurrentLab;
	 private Date maxLabOrderedDate=new Date();
	 // List
	 private List<ProcedureResultHistory> procedureResultHistoryLineChart=null;
	 int rowIdOfLabHistory;
	 private String labNameForLabHistory;
	 private double labHistoryResult;
	 private DataTable labMasterDatetable=new DataTable();
	 private String labNameForAddingNew="";

	 //private ProcedureType proceduretype=new ProcedureType();
	//private List<ProcedureResult> procedureResultListForAntiCoag=null;
	
	//private DoctorDetail drDetail;// Object of Doctor Detail Pojo
	//private List<PatientMedicationData> medicationHistoryList;// List to fetch Medicine History
	@Autowired
	NcLabDetailWSBean labDetailWsBean;
	Date reportDate=null;
	private Date today;
	private List<ORUPatientLabResult> oruPatientLabResultList;
	private ProcedureResultHistory currentLabValue=new ProcedureResultHistory();
	private String medicineForHighChart;
	//med highchart
   private List<ProcedureResultHistory>	procedureResultHistoryForMedChart;
  private List<GenericMedActionPlan>allGenericMedActionMedicineList;
  private List<GenericMedActionPlan>allGenericMedActionPlanChart;
  private MedandGenricmed medandGenricmed=new MedandGenricmed();
private List<MedandGenricmed>medActionListForChart=new ArrayList<MedandGenricmed>();
private List<ProcedureResultHistory>	procedureResultHistoryForMedActionPlan;
private List<MedandGenricmed>medandGenricmedListForMedicine=new ArrayList<MedandGenricmed>();
private List<PatientMedicationHistory>medandGenricmedListForDisplayList=new ArrayList<PatientMedicationHistory>();
private int searchLabOption=1; // Select option how lab should be searched based on Name or Loinc Code 
private String inputForLabSearch;
private String resultForLabSearch;
private Date dateForLabSearch;
private List<ProcedureType> procedureTypeListForSearchLab= new ArrayList<ProcedureType>();
private List<LabLineBarChart> copymedBarList=new ArrayList<LabLineBarChart>();
private List<ProcedureResultHistory> labresulthistorylistforanticoag=new ArrayList<ProcedureResultHistory>();
private List<PatientMedicationHistory> medicationhistoryforanticoag=new ArrayList<PatientMedicationHistory>();
private List<PatientMedicationData> medicationdataforanticoag=new ArrayList<PatientMedicationData>();

//------------for edit current lab
private Double selectedCurrentLabResultForEdit;
private String selectedCurrentLabNameForEdit;
private int selectedCurrentLabIdForEdit;
List<LabLineBarChart>medBarList=new ArrayList<LabLineBarChart>();
  List<ProcedureResultHistory> psychoPharmLabResultList=null;
  private Map<String, List<LabLineBarChart>> psychoPharmMedLabChartMap=null;
  private  List<ProcedureResultHistory> psychPharmMedActionPlanLab=null;
  private List<ProcedureResultHistory> hepCLabResultList=null;
  private List<ProcedureResultHistory> hepCMedActionPlanLabDataList=null;
  private Map<String, List<LabLineBarChart>> hepatitisCMedLabChartMap=null;
  private String labHtmlMessage;
  private ORUPatientLabResult patientLabResult=null;
  private String exitLocationForLabHtml;

	/*
	 * ********************************** INIT METHOD
	 */

	

	public void init() {
		

	}

	/*
	 * for reseting the value when patient change by doctor...
	 * @author:Gopal Krishna jha
	 */
	public void resetValue()
	{
		labHistoryListForAntiCoag=null;
		labChartForAnticoag=null;
		procedureResultListForCreatinine=null;
		labTypeMasterList=null;
		loincKeyMasterList=null;
		labHistoryListForAntiCoag1=new ArrayList<ProcedureResultHistory>();
		//labHistoryListForAntiCoag=new ArrayList<ProcedureResultHistory>();
		labtestChart=new CartesianChartModel();
		setChartPeriod(null);
		setLoincKeyMasterList(null);
		pharmacogenomicsUserSummaryList =null;
		currentLabValue=new ProcedureResultHistory();
		procedureResultListForCurrentLab=null;
		setDateForLabSearch(null);
        setResultForLabSearch(null);
        setInputForLabSearch(null);
        procedureTypeListForSearchLab=new ArrayList<ProcedureType>();
        psychoPharmLabResultList=null;
        hepCLabResultList=null;
		
	}
	public PatientVital getPatientVital() {
		return patientVital;
	}

	public void setPatientVital(PatientVital patientVital) {
		this.patientVital = patientVital;
	}

	/**
	 * It is used for get current  Lab Result data 
	 * used in genralmedactionplan.jsf ,labResults.jsf page
	 * @return the procedureResultListForCurrentLab
	 */
	public List<ProcedureResultHistory> getProcedureResultListForCurrentLab() {
		logger.info("inside getProcedureResultListForCurrentLab method start:::");
		if (null == procedureResultListForCurrentLab) {
			logger.info("inside if block getProcedureResultListForCurrentLab= null :::");
			procedureResultListForCurrentLab = labService.getAllProcedureResult(); // Get Current Lab Data 
		}
		for (ProcedureResultHistory pr : procedureResultListForCurrentLab) {
			System.out.println(":inside getProcedureResultListForCurrentLab:::"+pr.getId()+"::"+pr.getDoctorName()+"::"+pr.getLabName()
					+":::"+pr.getLabRange()+pr.getLabRange());
		}
		return procedureResultListForCurrentLab;
	}

	/**
	 * @param procedureResultListForCurrentLab the procedureResultListForCurrentLab to set
	 */
	public void setProcedureResultListForCurrentLab(
			List<ProcedureResultHistory> procedureResultListForCurrentLab) {
		this.procedureResultListForCurrentLab = procedureResultListForCurrentLab;
	}


	public ProcedureResultHistory getProcedureResultHistory() {
		if (null == procedureResultHistory) {
			procedureResultHistory = new ProcedureResultHistory();
		}
		return procedureResultHistory;
	}

	public void setProcedureResultHistory(
			ProcedureResultHistory procedureResultHistory) {
		this.procedureResultHistory = procedureResultHistory;
	}

	/**
	 * Getting the value of Procedure Result History
	 * 
	 * @return procedureResultHistoryList
	 */

	public List<ProcedureResultHistory> getProcedureResultHistoryList() {
	
		return procedureResultHistoryList;
	}

	public void setProcedureResultHistoryList(
			List<ProcedureResultHistory> procedureResultHistoryList) {
		this.procedureResultHistoryList = procedureResultHistoryList;
	}

	public CartesianChartModel getLabtestChart() {
		return labtestChart;
	}

	public void setLabtestChart(CartesianChartModel labtestChart) {
		this.labtestChart = labtestChart;
	}

	public String getChartPeriod() {

		return chartPeriod;
	}

	public void setChartPeriod(String chartPeriod) {
		this.chartPeriod = chartPeriod;
	}

	public LoincKeyMaster getLoincKeyMaster() {
		
		return loincKeyMaster;
	}

	public void setLoincKeyMaster(LoincKeyMaster loincKeyMaster) {
		this.loincKeyMaster = loincKeyMaster;
	}

	public List<LoincKeyMaster> getLoincKeyMasterList() {
		if (loincKeyMasterList == null && new ContextUtil().getPatientId()!=0) {
			loincKeyMasterList = new ArrayList<LoincKeyMaster>();
			
	// -------------------METHOD TO LOAD LOINC_MASTER INTO DROUP DOWN OF LAB CHART
			loincKeyMasterList = labService.getloincMasterList();												
		}
		return loincKeyMasterList;
	}

	public void setLoincKeyMasterList(List<LoincKeyMaster> loincKeyMasterList) {
		this.loincKeyMasterList = loincKeyMasterList;
	}

	public List<ProcedureResultHistory> getProcedureResultHistoryListForChart() {
		
		
		return procedureResultHistoryListForChart;
	}

	public void setProcedureResultHistoryListForChart(
			List<ProcedureResultHistory> procedureResultHistoryListForChart) {
		this.procedureResultHistoryListForChart = procedureResultHistoryListForChart;
	}

	public double getLabtestMax() {
		return labtestMax;
	}

	public void setLabtestMax(double labtestMax) {
		this.labtestMax = labtestMax;
	}

	public String getResultMaxMinValue() {
		return resultMaxMinValue;
	}

	public void setResultMaxMinValue(String resultMaxMinValue) {
		this.resultMaxMinValue = resultMaxMinValue;
	}

	public double getLabtestMin() {
		return labtestMin;
	}

	public void setLabtestMin(double labtestMin) {
		this.labtestMin = labtestMin;
	}


	/**
	 * @return the labService
	 */
	public ILabService getLabService() {
		return labService;
	}

	/**
	 * @param labService
	 *            the labService to set
	 */
	public void setLabService(ILabService labService) {
		this.labService = labService;
	}
	

/*
 * **************************** METHOD FOR GETTING TIME DURATION FOR CHARTSSSSSSS
 */

	public Date getTimePeriod() {
		
		logger.info("getTimePeriod called in labmanage bean::::");
		try {
			Calendar cal = Calendar.getInstance();
			Date date = cal.getTime();
			DateFormat format = new SimpleDateFormat("dd/MMM/yyyy");
			String timePeriod = getChartPeriod();
			System.out.println("getChartPeriod inside gettime period method:::"+getChartPeriod());
			if (timePeriod.equals("1y")) {
				cal.add(Calendar.MONTH, -12);
				date = cal.getTime();
				return date;
				} 
			else if (timePeriod.equals("6m")) {
				cal.add(Calendar.MONTH, -6);
				date = cal.getTime();
				return date;
				} 
			else if (timePeriod.equals("3m")) {
				cal.add(Calendar.MONTH, -3);
				date = cal.getTime();
				return date;
				} 
			else if (timePeriod.equals("1m")) {
				cal.add(Calendar.MONTH, -1);
				date = cal.getTime();
				return date;
			}
			System.out.println("date inside gettime period method:::"+date);
		} 
		catch (Exception e) {
			logger.error("exception in getTimePeriod in labmanage bean::::", e);
			e.printStackTrace();
		}
		return null;
	}
	
	

	/*
	 * ********************************METHOd FOR CREATING CHART FOR LAB TEST
	 */

	public void createLabTestChart() {
		logger.info("createLabTestChart method start in Doctor bean::::::");

		try {
	
			if (!loincKeyMasterList.isEmpty()) {
				System.out.println("getValueOfChartName::::::"+loincKeyMaster.getProcedureTypeId());
				System.out.println("getValueOfChartName::::::"+chartPeriod+"::"+getChartPeriod());
				labtestChart = new CartesianChartModel();
				LineChartSeries chartObj = new LineChartSeries();
				Date periodDate = getTimePeriod();
				String labName = "lab";
				procedureResultHistoryListForChart = labService.labResultValueForChart(periodDate,labName,loincKeyMaster.getProcedureTypeId());

				for (ProcedureResultHistory proResultHistory : procedureResultHistoryListForChart) {
					Date date = proResultHistory.getDateResult();
					String month = new SimpleDateFormat("dd-MMM-yy").format(date);
					chartObj.set(month, proResultHistory.getResult());
					System.out.println("getValueOf month::::::"+month+"::"+chartObj.getData().toString());
				}
				 
		//-----------------------------METHOD TO FIND Y-AXIS MAX AND MIN VALUE IN LAB TEST CAHRT
				resultMaxMinValue = labService.findlabTestMaxValue(loincKeyMaster.getProcedureTypeId());
				
			// ----------------------METHOD To SEPRATE	MAX	AND	MIN	VALUE OF LAB TEST RESULT FROM resultMaxMinValue
				if (resultMaxMinValue!=null) {
					
					StringTokenizer st = new StringTokenizer(resultMaxMinValue, "-");
					System.out.println("value of resultMaxMinValue:::"+resultMaxMinValue);
					while (st.hasMoreElements()) {
						labtestMax = Double.parseDouble(st.nextElement().toString());
						labtestMin = Double.parseDouble(st.nextElement().toString());
						System.out.println("value of string tokenizer:::"+labtestMax+":::"+labtestMin);
						
						
				}
				
				}
		// -------------------------------Method to find name of lab chart selected from down @ labresult.xhtml
				for (LoincKeyMaster loincmaster : loincKeyMasterList) 
																		
				{
					if (loincmaster.getProcedureTypeId() == loincKeyMaster.getProcedureTypeId()) {
						loincKeyMaster.setName(loincmaster.getName());
					}
				}
				
		// -------------------------------Setting Lab Chart Label
				chartObj.setLabel(loincKeyMaster.getName()); 
				labtestChart.addSeries(chartObj);
			}
			
		} 
		catch (NullPointerException ne) {
			ne.printStackTrace();
			logger.error("exception in createLabTestChart method bean:::::::::", ne);
		}

		catch (Exception e) {
			e.printStackTrace();
			logger.error("exception in createLabTestChart method bean:::::::::", e);
		}

	}

	/**
	 * @return the labDataTable object  
	 */
	public DataTable getLabDataTable() {
		if (null == labDataTable) {
			labDataTable = new DataTable();
		}
		return labDataTable;
	}

	/**
	 * @param labDataTable
	 *            the labDataTable to set
	 */
	public void setLabDataTable(DataTable labDataTable) {
		this.labDataTable = labDataTable;
	}

	/**
	 * used in labResults.jsf
	 * It will expend the Lab Result data and get history of particular selected lab 
	 */
	public void onRowExpensionProcedureResultHistory(ToggleEvent event) {
		try {

			ProcedureResultHistory pResult = (ProcedureResultHistory) getLabDataTable()
					.getRowData();
			String selectedLabName = pResult.getLabName();
			int rowId=pResult.getId();
			System.out.println("value in pResult.getProcedureType().getid()::::"+pResult.getLabTypeId()+":id:"+rowId);
			procedureResultHistoryList=new ArrayList<ProcedureResultHistory>();
			procedureResultHistoryList = labService.getprocedureResultHistoryListOnRowExpension(selectedLabName,rowId);
			
			for(ProcedureResultHistory proReHis: procedureResultHistoryList)
			{
				System.out.println("ProcedureResultHistory timeZone details::::"+proReHis.getDateResult()+"::::::"+proReHis.getLabTypeId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*public List<PatientMedicationData> getMedicationHistoryList() {
		return medicationHistoryList;
	}

	public void setMedicationHistoryList(
			List<PatientMedicationData> medicationHistoryList) {
		this.medicationHistoryList = medicationHistoryList;
	}*/


	public void setPharmacogenomicsUserSummaryList(
			List<PharmacogenomicsUserSummary> pharmacogenomicsUserSummaryList) {
		this.pharmacogenomicsUserSummaryList = pharmacogenomicsUserSummaryList;
	}

	public List<PharmacogenomicsUserSummary> getPharmacogenomicsUserSummaryList() {

		logger.info("inside getPharmacogenomicsUserSummaryList method::::: "
				+ new ContextUtil().getPatientId());
		if (pharmacogenomicsUserSummaryList == null){
			pharmacogenomicsUserSummaryList =new ArrayList<PharmacogenomicsUserSummary>();
			pharmacogenomicsUserSummaryList = labService.findPharmacogenomicsUserSummaryList(new ContextUtil().getPatientId());
		}
			

		return pharmacogenomicsUserSummaryList;
	}

	/**
	 * @return the procedureResultsForPatient
	 */
	public List<ProcedureResultHistory> getProcedureResultsForPatient() {
		if(procedureResultsForPatient==null){
			procedureResultsForPatient=new ArrayList<ProcedureResultHistory>();
		
			procedureResultsForPatient=labService.getProcedureResultForPatient();
		}
		return procedureResultsForPatient;
	}

	/**
	 * @param procedureResultsForPatient the procedureResultsForPatient to set
	 */
	public void setProcedureResultsForPatient(
			List<ProcedureResultHistory> procedureResultsForPatient) {
		this.procedureResultsForPatient = procedureResultsForPatient;
	}

	/**
	 * @return the pharmacogenomicsSummaryBasedOnMedicineList
	 */
	public List<PharmacogenomicsUserSummary> getPharmacogenomicsSummaryBasedOnMedicineList() {
		if(pharmacogenomicsSummaryBasedOnMedicineList==null){
			pharmacogenomicsSummaryBasedOnMedicineList=new ArrayList<PharmacogenomicsUserSummary>();
		}
		pharmacogenomicsSummaryBasedOnMedicineList=labService.getPharmacogenomicsSummaryBasedOnMedicine();
		return pharmacogenomicsSummaryBasedOnMedicineList;
	}

	/**
	 * @param pharmacogenomicsSummaryBasedOnMedicineList the pharmacogenomicsSummaryBasedOnMedicineList to set
	 */
	public void setPharmacogenomicsSummaryBasedOnMedicineList(
			List<PharmacogenomicsUserSummary> pharmacogenomicsSummaryBasedOnMedicineList) {
		this.pharmacogenomicsSummaryBasedOnMedicineList = pharmacogenomicsSummaryBasedOnMedicineList;
	}
	//private List<ProcedureResultHistory>labHistoryListFor
	
	/*
	 * find labHistory detail according to particular patient and for anticoag
	 * @author: Gopal Krishna jha
	 */
	
	
	private List<ProcedureResultHistory>labHistoryListForAntiCoag1=new ArrayList<ProcedureResultHistory>();
	public List<ProcedureResultHistory> getLabHistoryListForAntiCoag() {
		if(labHistoryListForAntiCoag==null)
		{
			//labHistoryListForAntiCoag1=labService.getProcedureResultHistory("INR");
			/*labHistoryListForAntiCoag1=new ArrayList<ProcedureResultHistory>();*/
			labHistoryListForAntiCoag=labService.getProcedureResultHistory("INR");
			//Iterator it = labHistoryListForAntiCoag.iterator();
			for (ListIterator<ProcedureResultHistory> it = labHistoryListForAntiCoag.listIterator(labHistoryListForAntiCoag.size()); it.hasPrevious(); ) 
			{
				ProcedureResultHistory t = it.previous();
				System.out.println(":::::::::::inside condition:::"+t.getDateOrdered()+"::::::::::result"+t.getResult());
				labHistoryListForAntiCoag1.add(t);
			}
			
		}
	//	System.out.println("QQQQ::::::::::::::"+labHistoryListForAntiCoag1.size());
		return labHistoryListForAntiCoag;
	}

	public void setLabHistoryListForAntiCoag(
			List<ProcedureResultHistory> labHistoryListForAntiCoag) {
		this.labHistoryListForAntiCoag = labHistoryListForAntiCoag;
	}
	/*
	 * find lab chart for anticoag
	 * @author:Gopal Krishna jha..
	 */
	public CartesianChartModel getLabChartForAnticoag() {
		if(labChartForAnticoag==null)
		{
			labChartForAnticoag=new CartesianChartModel();
			//System.out.println("::::::::::::inside iffff");
			ChartSeries lab = new ChartSeries();  
			for(ProcedureResultHistory procHis:labHistoryListForAntiCoag)
			{
				lab.set(new DateUtil().convertDateFormat(procHis.getDateResult()), procHis.getResult());
			}
			
			labChartForAnticoag.addSeries(lab);
		}
		return labChartForAnticoag;
	}

	public void setLabChartForAnticoag(CartesianChartModel labChartForAnticoag) {
		this.labChartForAnticoag = labChartForAnticoag;
	}
	
	/**
	 * @return the labReminderList
	 */
	public List<MedandGenricmed> getLabReminderList() {
		if (labReminderList==null) {
			labReminderList=new ArrayList<MedandGenricmed>();
		//-------------------Method to find Lab Reminders for patient
			labReminderList=labService.findRemindersForLab(new ContextUtil().getLoginId(),new DateUtil().AddDate(0));
			System.out.println("labReminderList.size() :::::::;"+labReminderList.size());
			for(MedandGenricmed med:labReminderList){
				System.out.println("Necx t Lab Date "+med.getNextLabDate()+" Lab Name "+med.getLab()+"Frequqncy ::::::"+med.getLabfrequency()+"Dosing Start Date :::::::"+med.getDosingStartDate());
			}
		}
	
		return labReminderList;
	}

	/**
	 * @param labReminderList the labReminderList to set
	 */
	public void setLabReminderList(List<MedandGenricmed> labReminderList) {
		this.labReminderList = labReminderList;
	}

	/**
	 * @return the labReminderDate
	 */
	public Date getLabReminderDate() {
	
		return labReminderDate;
	}

	/**
	 * @param labReminderDate the labReminderDate to set
	 */
	public void setLabReminderDate(Date labReminderDate) {
		this.labReminderDate = labReminderDate;
	}

	
	
	/*
	 * find the value of getProcedureResultListForCreatinine (using on CR)
	 * @author: Gopal Krishna Jha
	 */
	public List<ProcedureResultHistory> getProcedureResultListForCreatinine() {
		if(procedureResultListForCreatinine==null)
		{
			procedureResultListForCreatinine=new ArrayList<ProcedureResultHistory>();
			getProcedureResultListForCurrentLab();
			System.out.println("<>>>>>>>>>>>>>>>>>>>>>>>>>::::::::: "+procedureResultListForCurrentLab.size());
			for(ProcedureResultHistory procResHistory:procedureResultListForCurrentLab)
			{
				System.out.println("::::::::range::"+procResHistory.getResult()+":::cr::"+procResHistory.getLabName());
				if(procResHistory.getLabName().equalsIgnoreCase("Cr"))
				{
					procedureResultListForCreatinine.add(procResHistory);
				}
					
			}
		}
		return procedureResultListForCreatinine;
	}

	public void setProcedureResultListForCreatinine(
			List<ProcedureResultHistory> procedureResultListForCreatinine) {
		this.procedureResultListForCreatinine = procedureResultListForCreatinine;
	}

	public void findLabName()
	{
		logger.info("findAllergyName::::::::::::::::::::::");
		//labNameForAddingNew= new String();
		//setLabNameForAddingNew(null);
		procedureTypeListForSearchLab = new ArrayList<ProcedureType>();
		procedureType=new ProcedureType();
		//allergyMaster=(AllergyMaster)getAllergyMasterDatatable().getRowData();
		procedureType = (ProcedureType) getLabMasterDatetable().getRowData();
		//setAllergyNameForAddingNew(allergyMaster.getAllergyName());
		setLabNameForAddingNew(procedureType.getLabType());
		//setResultForLabSearch(procedureType.getLabType());
		System.out.println("labSearch:::::::"+labNameForAddingNew);
		inputForLabSearch="";
		//dateForLabSearch = new Date();
		//setDateForLabSearch(null);
		System.out.println(":::::::::::::date::::"+getDateForLabSearch());
		//setInputForLabSearch(procedureType.getLabType());
		
		//System.out.println(patientAllergy.getAllergyName()+"::::selected allergy name::"+getAllergyNameForAddingNew()+"::allergy from master:::"+allergyMaster.getAllergyName());
		//setCheckAllergyValidName(true);
		/*patientAllergy.setPatientId(new ContextUtil().getPatientId());
		//patientAllergy.setAllergytype(allergytype)
		patientAllergy.setAllergyName(allergyMaster.getAllergyName());
		patientAllergy.setal


		patientAllergy.setAllergy(allergyMaster.getAllergyName());

		patientAllergy.setAllergyId(allergyMaster.getCompositeAllergyId());*/

		//RequestContext.getCurrentInstance().execute("add_lab_details.hide()");
		//oncomplete="add_lab_details.hide();"

		RequestContext.getCurrentInstance().execute("add_new_lab.show()");
		//return null;

	}
	
/*
 * used in labResults.jsf page 
 * *********Method to save new lab data in database 
 * ******Author : saurabh
 */
	
	public String addLabdata()
	{
		logger.info("=======addLabdata called in labManageBean============"+dateForLabSearch);
		String msg ="";
		/*int labTypeId=0;
		String labType;
		String loincCode;
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		//labTypeId=Integer.parseInt(params.get("rowIdForLabSearch"));
		labType=params.get("labTypeForLabSearch");
		loincCode=params.get("loincForLabSearch");
		logger.info("=======addLabdata called==========labTypeId="+labTypeId+"==lab=Date="+getDateForLabSearch()+
				"====Lab=Result="+getResultForLabSearch()+"=====labType="+labType+
				"====loincCode="+loincCode);
		*/
		//setLabHistoryResult(0);
		//Date d1=new DateUtil().convertTmezoneFromSystem(getDateForLabSearch());
		Date d1 = new Date();
		System.out.println("::::d1::::"+d1);
		
		if((procedureType.getLabType()!=null) && (d1!=null) && (getResultForLabSearch()!=null))
		{
			System.out.println(d1.getDate()+"1getDate:::::::::::"+d1.getMonth()+":::::::::::"+d1.getYear()+":::::::"+dateForLabSearch);
			
			if(d1.getDate()==dateForLabSearch.getDate())
			{
				System.out.println("::::::date d1 get date"+d1.getDate()+"::::"+dateForLabSearch.getDate());
				d1=new Date();
			}
			else
			{
				d1=dateForLabSearch;
			System.out.println(d1+"getResultForLabSearch::::"+getDateForLabSearch()+":::::::"+getResultForLabSearch()+"::::::::"+procedureType.getLabType());
			}
			labService.saveLabDetailValue(d1,getResultForLabSearch(),procedureType.getId(),procedureType.getLabType(),procedureType.getLoincCode()); // Save Lab Result Data in database 
		
		RequestContext.getCurrentInstance().execute("add_new_lab.hide()");
			
			//msg="Lab save successfully";
			//RequestContext.getCurrentInstance().execute("msgGrol.show([{summary:'', detail: 'Med Action Plan Saved Successfully', severity: 'info'}])");
			//FacesContext.getCurrentInstance().addMessage("messageUpdateForNewAddMeds", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,""));
			//FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
//------calling dashboard manage bean method to update liver kidney function value afetr adding lab 
        FacesContext context = FacesContext	.getCurrentInstance();
		DashBoardManageBean dashBoardManageBean=(DashBoardManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"dashBoardManageBean");
		dashBoardManageBean.updateLiverAndKidneyFunctionValue();
		if ("Weight".equalsIgnoreCase(procedureType.getLabType())) {
			 dashBoardManageBean.updateBmiAndWeightAfterAddingUpdatingWeightInLab();
		   }        
        labNameForAddingNew ="";
        setDateForLabSearch(null);
        setResultForLabSearch(null);
        setInputForLabSearch(null);
        procedureTypeListForSearchLab=new ArrayList<ProcedureType>();
        System.out.println(":::::::getDateForLabSearch::::::::"+getDateForLabSearch());
        setProcedureResultListForCurrentLab(null);
		setProcedureResultHistory(null);
		setLoincKeyMasterList(null);
		loincKeyMaster=new LoincKeyMaster();
		setLabHistoryListForAntiCoag(null);
		labHistoryListForAntiCoag=null;
		labHistoryListForAntiCoag1=new ArrayList<ProcedureResultHistory>();
		procedureResultHistoryListForChart=null;
		labtestChart=new CartesianChartModel();
		setChartPeriod(null);
		setProcedureResultListForCreatinine(null);
		PatientMedicineManageBean patientMedicineManageBeanObj=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		patientMedicineManageBeanObj.setAlertMedActionPlans(null);
		
		//for setting INR LAB By gOpal
		if(procedureType.getLabType().equalsIgnoreCase("INR"))
		{
			//patientMedicineManageBeanObj.setCheckNotesForLab(0);
			patientMedicineManageBeanObj.setShowAnticoagMedactionPlanMessage(0);
		}
	
     }
		return "go_to_LabResult";
	}
	
	

	/**
	 * @return the userlogindetail
	 */
	public UserLoginDetail getUserlogindetail() {
		return userlogindetail;
	}

	/**
	 * @param userlogindetail the userlogindetail to set
	 */
	public void setUserlogindetail(UserLoginDetail userlogindetail) {
		this.userlogindetail = userlogindetail;
	}

	/**
	 * @return the labTypeMasterList
	 */
	public List<ProcedureType> getLabTypeMasterList() {
		if (labTypeMasterList==null) {
			labTypeMasterList=new ArrayList<ProcedureType>();
			labTypeMasterList=labService.getLabTypeMaster();
		}
		return labTypeMasterList;
	}

	/**
	 * @param labTypeMasterList the labTypeMasterList to set
	 */
	public void setLabTypeMasterList(List<ProcedureType> labTypeMasterList) {
		this.labTypeMasterList = labTypeMasterList;
	}

	/**
	 * @return the procedureType
	 */
	public ProcedureType getProcedureType() {
		return procedureType;
	}

	/**
	 * @param procedureType the procedureType to set
	 */
	public void setProcedureType(ProcedureType procedureType) {
		this.procedureType = procedureType;
	}

	public List<ProcedureResultHistory> getLabHistoryListForAntiCoag1() {
		if(labHistoryListForAntiCoag1.isEmpty())
		{
			getLabHistoryListForAntiCoag();
		
		}
		return labHistoryListForAntiCoag1;
	}

	public void setLabHistoryListForAntiCoag1(
			List<ProcedureResultHistory> labHistoryListForAntiCoag1) {
		this.labHistoryListForAntiCoag1 = labHistoryListForAntiCoag1;
	}
	
	
	/*
	 * used in labResults.jsf page 
	 * used for getting ID for deleting lab history based on selection 
	 * method Author @sanket Singh
	 * 
	 */

	public String setidtodeleteLabData()
	{
		logger.info("setidtodeletelabresult method start:::");
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		logger.info("value is::::::::::::::");
		rowIdnumberForLabHistory=Integer.parseInt(params.get("rowIdForLabHistory"));
		  logger.info("value::::::::::::::::::::::::::"+rowIdnumberForLabHistory);
		return null;
	}
/*
 * used in labResult.jsf page 
 * Method used for delete Lab History Data  
 * @uthor : saurabh
 */
	public void deleteLabResultRow()
	{
		logger.info("Delete method start:::::::::::::");
		 labService.deleteLabDetails(rowIdnumberForLabHistory); // Delete Lab history data 
		 procedureResultHistoryList=null;
		 procedureResultHistoryListForChart=null;	
		 labtestChart=new CartesianChartModel();
		 setChartPeriod(null);
		 loincKeyMaster=new LoincKeyMaster();
		 setProcedureResultListForCurrentLab(null);
		 setProcedureResultListForCreatinine(null);
	//------calling dashboard manage bean method to update liver kidney function value afetr adding lab 
		 FacesContext context = FacesContext	.getCurrentInstance();
		 DashBoardManageBean dashBoardManageBean=(DashBoardManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"dashBoardManageBean");
		 dashBoardManageBean.updateLiverAndKidneyFunctionValue();
		  PatientMedicineManageBean patientMedicineManageBeanObj=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
			patientMedicineManageBeanObj.setAlertMedActionPlans(null);
		// patientMedicineManageBeanObj.setCheckNotesForLab(0);
		 patientMedicineManageBeanObj.setShowAnticoagMedactionPlanMessage(0);
		 labHistoryListForAntiCoag1=new ArrayList<ProcedureResultHistory>();
		 labHistoryListForAntiCoag=null;
		 
		 
	}
	
/*	
 *  used in labResults.jsf  
 *  Edit Lab Result Data and save after editing 
 * 
 * */
	public String editLab()
	{
		String msg="";
		logger.info("inside editLab:::rowId="+getSelectedCurrentLabIdForEdit()+"::lab:"+getSelectedCurrentLabNameForEdit()
				  +"::result:"+getSelectedCurrentLabResultForEdit());	
	//procedureResultHistory.setId(rowIdnumberForCurrentLab);
	labService.editLabResult(getSelectedCurrentLabIdForEdit(),getSelectedCurrentLabResultForEdit(),getSelectedCurrentLabNameForEdit()); // Edit Lab Result Data 
	msg="Lab result update successfully";
	FacesContext.getCurrentInstance().addMessage("messageUpdateForNewAddMeds", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,""));
	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	
	System.out.println("value is:msg details:::::"+msg);
	procedureResultListForCurrentLab=null;
	procedureResultHistoryListForChart=null;
	labtestChart=new CartesianChartModel();

	labHistoryListForAntiCoag=null;
	labHistoryListForAntiCoag1=new ArrayList<ProcedureResultHistory>();
	setChartPeriod(null);
	loincKeyMaster=new LoincKeyMaster();
	setProcedureResultListForCreatinine(null);
    //------calling dashboard manage bean method to update liver kidney function value afetr adding lab 
      FacesContext context = FacesContext	.getCurrentInstance();
	  DashBoardManageBean dashBoardManageBean=(DashBoardManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"dashBoardManageBean");
	  dashBoardManageBean.updateLiverAndKidneyFunctionValue();
	  if ("Weight".equalsIgnoreCase(getSelectedCurrentLabNameForEdit())) {
		 dashBoardManageBean.updateBmiAndWeightAfterAddingUpdatingWeightInLab();
	   }
	  PatientMedicineManageBean patientMedicineManageBeanObj=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
	  patientMedicineManageBeanObj.setAlertMedActionPlans(null);
		
		//for setting INR LAB By gOpal
	//	if(procedureType.getId()==2)
	 // patientMedicineManageBeanObj.setCheckNotesForLab(0);
	  patientMedicineManageBeanObj.setShowAnticoagMedactionPlanMessage(0);
	  setSelectedCurrentLabIdForEdit(0);
	  setSelectedCurrentLabNameForEdit(null);
	  setSelectedCurrentLabResultForEdit(null);
	  return "go_to_LabResult";
	
	}
//----------@uthor saurabh: getting rowId for editing current lab
	public void setIdforEditCurrentLab()
	{
		logger.info("value is::::::::::::::");
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		logger.info("inside setIdforEditLab:::rowId="+params.get("rowIdForLab")+"::lab:"+params.get("result")
				  +"::result:"+params.get("labName"));
		setSelectedCurrentLabIdForEdit(Integer.parseInt(params.get("rowIdForLab")));
		setSelectedCurrentLabResultForEdit(Double.parseDouble(params.get("result")));
		setSelectedCurrentLabNameForEdit(params.get("labName"));
		  logger.info("inside setIdforEditLab:::rowId="+getSelectedCurrentLabIdForEdit()+"::lab:"+getSelectedCurrentLabNameForEdit()
				  +"::result:"+getSelectedCurrentLabResultForEdit());
	}
	
	/*getting ID for edit lab history
	 * method Author @sanket Singh
	 * 
	 */

	public void setidtoEditLabHistory()
	{
		logger.info("setidtodeletelabresult method start:::"+rowIdOfLabHistory);
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		
		logger.info("value is::::::::::::::"+Integer.parseInt(params.get("rowIdOfLabHistory")));
		logger.info("result::"+params.get("resultForLabHistory1")+":::lab name:::"+params.get("historylabName1"));
		/*System.out.println("value is::::::::::::::"+Integer.parseInt(params.get("rowIdForLabHistory")));
		rowIdOfLabHistory=Integer.parseInt(params.get("rowIdForLabHistory"));
		
		 logger.info("rowIdOfLabH123istory:"+rowIdOfLabHistory+"::labNameForLabHistory:"+labNameForLabHistory+"::labHistoryResult:"+labHistoryResult);*/
		setLabNameForLabHistory(params.get("historylabName1"));
		setLabHistoryResult(Double.parseDouble(params.get("resultForLabHistory1")));
		setRowIdOfLabHistory(Integer.parseInt(params.get("rowIdOfLabHistory")));
		System.out.println("lab name d::::"+labNameForLabHistory+"::::::resu"+labHistoryResult);
	  logger.info("rowIdOfLabHistory:"+rowIdOfLabHistory+"::labNameForLabHistory:"+labNameForLabHistory+"::labHistoryResult:"+labHistoryResult);
	}
	
/*
 * used in labResults.jsf page 
 * It is used for edit Lab History Data 
 * 	
 */
	public void editHistory()
	{
		logger.info("value in editHistory:::::::id:"+rowIdOfLabHistory+"::labNameForLabHistory:"+labNameForLabHistory+"::labHistoryResult:"+labHistoryResult);
		//rowIdOfLabHistory=740;
		String msg ="";
		labService.editHistory(rowIdOfLabHistory,labHistoryResult); // Edit Lab History data 
		msg="Lab result update successfully";
		FacesContext.getCurrentInstance().addMessage("messageUpdateForNewAddMeds", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,""));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		rowIdOfLabHistory=0;
		labNameForLabHistory=null;
		setLabHistoryResult(0);
		procedureResultHistoryList=null;
		procedureResultHistoryListForChart=null;
		labtestChart=new CartesianChartModel();
		setChartPeriod(null);
	    loincKeyMaster=new LoincKeyMaster();
	    procedureResultListForCurrentLab=null;
	    setProcedureResultListForCreatinine(null);
	    //------calling dashboard manage bean method to update liver kidney function value afetr adding lab 
	      FacesContext context = FacesContext	.getCurrentInstance();
		  DashBoardManageBean dashBoardManageBean=(DashBoardManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"dashBoardManageBean");
		  dashBoardManageBean.updateLiverAndKidneyFunctionValue();
		  PatientMedicineManageBean patMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		  patMedicineManageBean.setAlertMedActionPlans(null);
		
	}
	

	/**
	 * @return the maxLabOrderedDate
	 */
	public Date getMaxLabOrderedDate() {
		return maxLabOrderedDate;
	}

	/**
	 * @param maxLabOrderedDate the maxLabOrderedDate to set
	 */
	public void setMaxLabOrderedDate(Date maxLabOrderedDate) {
		this.maxLabOrderedDate = maxLabOrderedDate;
	}

/*
 * 	*****used to re-open add lab dialog
 * with min date of lab date/result 
 * @uthor:saurabh
 */
	public void againOpenAddLabDialog()
	{
		logger.info("inside againOpenAddLabDialog:::");
		//RequestContext.getCurrentInstance().execute("add_new_lab.hide();");
		RequestContext.getCurrentInstance().execute("add_new_lab.show();");
	}


	/**
	 * @return the labDetailWsBean
	 */
	public NcLabDetailWSBean getLabDetailWsBean() {
		return labDetailWsBean;
	}

	public void setProcedureResultHistoryLineChart(
			List<ProcedureResultHistory> procedureResultHistoryLineChart) {
		this.procedureResultHistoryLineChart = procedureResultHistoryLineChart;
	}



	/**
	 * @param labDetailWsBean the labDetailWsBean to set
	 */
	public void setLabDetailWsBean(NcLabDetailWSBean labDetailWsBean) {
		this.labDetailWsBean = labDetailWsBean;
	}
	
  /**
   * Used in MasterDataManagment.xhtml
   * Get Lab Result Detail from WebServices and Save data in Database 
   * <p>
   * @throws Exception 
   * 
   */
	public void saveLabResltDetailDataFromNewCropToClinakos() throws Exception{
		System.out.println("Lab Saving method started ");
		FacesContext context = FacesContext	.getCurrentInstance();
		UserManageBean userManageBean=(UserManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"userManageBean");
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		
		String strReportDate=dateFormat.format(getReportDate());
		List<LabResultDetailClinakos> labDetailClinakosList=labDetailWsBean.getAllLabResultDetailData(userManageBean.getProviderLocation(),strReportDate);
		
		
		labService.saveLabResltDetailDataFromNewCrop(labDetailClinakosList);
		reportDate=null;
	}

  /**
   * @return the reportDate
   */
    public Date getReportDate() {
	   return reportDate;
   }

   /**
    * @param reportDate the reportDate to set
    * <p>
   */
  public void setReportDate(Date reportDate) {
	 this.reportDate = reportDate;
   }

   /**
   * @return the today
   * <p>
   */
  public Date getToday() {
	  Date date=new Date();
		System.out.println("****************************"+date+":::::"+new ContextUtil().getLoggedUsertimeZone());
		
		
		// DateFormat converter = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
	     //converter.setTimeZone(TimeZone.getTimeZone("PT"));
		 
		// System.out.println("cSchedStartCal:::123::::::::"+converter.format(date));
		 
		Calendar cSchedStartCal = Calendar.getInstance(TimeZone.getTimeZone(new ContextUtil().getLoggedUsertimeZone()));
		System.out.println("cSchedStartCal:::::::::::"+cSchedStartCal);
		date=cSchedStartCal.getTime();
	    System.out.println("date description::::"+date);
	
	return date;
  }

  /**
   * @param today the today to set
   */
  public void setToday(Date today) {
	 this.today = today;
  }
  
  
/**
 * Used in MasterDataManagment.xhtml
 * It will save the decode HL7 message into DB tables
 * <p>
 * @throws Exception 
 * 
 */
  public void getHL7MessageValue() throws Exception{
	  FacesContext context = FacesContext.getCurrentInstance();
		UserManageBean userManageBean=(UserManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"userManageBean");
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		String strReportDate=dateFormat.format(getReportDate());
		List<ORUPatientLabResult> wsPatientLabResultList=labDetailWsBean.getValueFromHL7XmlFormat(userManageBean.getProviderLocation(), strReportDate);
		/*for(ORUPatientLabResult oruPatientLabResult:wsPatientLabResultList){*/
			labService.saveOruPatientLabResultFromNewCropToClinakos(wsPatientLabResultList,getReportDate());
		/*}*/
		labService.integrateEmedonLabDataInClinakos(getReportDate());
		System.out.println("wsPatientLabResultList.size()::::::::"+wsPatientLabResultList.size());
		
	  
  }

 /* 
  * used in labResults.jsf page 
  * This Method use at high chart at Lab Result Page
  * <p>
  * @Author Sanket Singh
  * 
  * */
	
	public String testValue(ActionEvent ae){
		
		String msg="";
		logger.info("getValueOfChartName::::::"+loincKeyMaster.getName());
		logger.info("getValueOfChartName::::::"+chartPeriod+"::"+getChartPeriod()+":period:"+getTimePeriod());
		procedureResultHistoryLineChart=new ArrayList<ProcedureResultHistory>();
		List<LabLineBarChart>lineBarChartList=new ArrayList<LabLineBarChart>();
		Map<String,List<LabLineBarChart>> labLineBarChartMap=new HashMap<String, List<LabLineBarChart>>();
		int labId=1;
		Date periodDate = getTimePeriod();
		procedureResultHistoryListForChart = labService.labResultValueForChart(periodDate,loincKeyMaster.getName(),labId);
     logger.info("size of list=="+procedureResultHistoryListForChart.size());
		for (ProcedureResultHistory proResultHistory : procedureResultHistoryListForChart) {
			/*Date date = proResultHistory.getDateResult();
			String month = new SimpleDateFormat("yyyy-MM-dd").format(date);*/
		
			ProcedureResultHistory procedureHistory=new ProcedureResultHistory();
			procedureHistory.setResult(proResultHistory.getResult());
			procedureHistory.setDateResult(proResultHistory.getDateResult());
			System.out.println("date ::::1::::"+procedureHistory.getDateResult()+procedureHistory.getResult());
			
			LabLineBarChart lineBarChart=new LabLineBarChart();
			lineBarChart.setLongStartDate(proResultHistory.getDateResult().getTime());
			System.out.println("date ::::11::::"+lineBarChart.getLongStartDate()+"::::::::>>>>>"+proResultHistory.getDateResult().getTime());
			System.out.println("date ::::111::::"+proResultHistory.getResult().toString());
			
			lineBarChart.setResult(proResultHistory.getResult().toString());
			lineBarChartList.add(lineBarChart);
			/*labLineBarChartMap.put("lab", lineBarChartList);
			logger.info("list size==="+labLineBarChartMap.size());*/
	
		}
	 //System.out.println("value of list::::::"+lineBarChartList.size());
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("jsonDataSet", new Gson().toJson(lineBarChartList));
		//logger.info("json chart for lab "+new Gson().toJson(lineBarChartList));
		
		logger.info("txt_labSearch::::::lineBarChartList.size"+lineBarChartList.size());
		
		if(lineBarChartList.size()==0)
		{
			msg="No data found";
			FacesContext.getCurrentInstance().addMessage("messageUpdateForNewAddMeds", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,""));
		}
		
		return new Gson().toJson(lineBarChartList);
	}

	/**
	 * @return the labNameForLabHistory
	 */
	public String getLabNameForLabHistory() {
		return labNameForLabHistory;
	}

	/**
	 * @param labNameForLabHistory the labNameForLabHistory to set
	 */
	public void setLabNameForLabHistory(String labNameForLabHistory) {
		this.labNameForLabHistory = labNameForLabHistory;
	}

	/**
	 * @return the labHistoryResult
	 */
	public double getLabHistoryResult() {
		return labHistoryResult;
	}

	/**
	 * @param labHistoryResult the labHistoryResult to set
	 */
	public void setLabHistoryResult(double labHistoryResult) {
		this.labHistoryResult = labHistoryResult;
	}

/*
 * used in labResults.jsf page 
 * Used to clean values after 
 * canceling any operation by user
 * in LAB Result screen
 */
 public String cleanValuesAfterCancel(){
	 logger.info("cleanValuesAfterCancel::::In lab manage bean:::::::");
	 procedureResultHistory=new ProcedureResultHistory();
	// setProcedureResultHistory(null);// Commented By Anjai It was setting null value when cancel button was calling.
	
	return "go_to_LabResult";
	
 }
 

	/*
	used At anticoag High chart
	 * @ Author Sanket Singh
	 * 
	 * */
	/*public String anticogValue(ActionEvent ae){
		
		logger.info("getValueOfChartName::::::"+loincKeyMaster.getProcedureTypeId());
		logger.info("getValueOfChartName::::::"+chartPeriod+"::"+getChartPeriod());
		procedureResultHistoryLineChart=new ArrayList<ProcedureResultHistory>();
		List<LabLineBarChart>lineBarChartList=new ArrayList<LabLineBarChart>();
		List<LabLineBarChart> lineBarChartList1=new ArrayList<LabLineBarChart>();
		Map<String, List<LabLineBarChart>> labLineBarChartMap=new HashMap<String, List<LabLineBarChart>>();
	
	
	
		Date periodDate = getTimePeriod();
		procedureResultHistoryListForChart = labService.labResultValueForChart(periodDate,loincKeyMaster.getProcedureTypeId());
		
		for (ProcedureResultHistory proResultHistory : procedureResultHistoryListForChart) {
			Date date = proResultHistory.getDateResult();
			String month = new SimpleDateFormat("yyyy-MM-dd").format(date);
			LabLineBarChart lineBarChart=new LabLineBarChart();
			ProcedureResultHistory procedureHistory=new ProcedureResultHistory();
			procedureHistory.setResult(proResultHistory.getResult());
			procedureHistory.setDateResult(proResultHistory.getDateResult());
			
		
			lineBarChart.setDateResult(proResultHistory.getDateResult().getTime());
			
		
			lineBarChart.setResult(proResultHistory.getResult().toString());
			
		//	System.out.println("===value=="+lineBarChart.getDateResult()+"Result value=="+lineBarChart.getResult());
			lineBarChartList.add(lineBarChart);
			procedureResultHistoryLineChart.add(procedureHistory);
			
			//
		}
		
		
		fetchMedicationHistoryList = labService.fetchMedicationHistoryList(new ContextUtil().getPatientId(),"Warfarin");
		for(PatientMedicationHistory patHistory:fetchMedicationHistoryList)
		{
			LabLineBarChart lineBarChart1=new LabLineBarChart();
			
			 String actualDose="";
			
			 if(!(((patHistory.getStrength()==null)) || (patHistory.getStrength().isEmpty())))
			 {
				 String fullDose=patHistory.getStrength();
				int length=0;
				if(fullDose.contains("m"))
				{
					length=fullDose.indexOf('m');
				}
				if(fullDose.contains("M"))
				{
					length=fullDose.indexOf('M');
				}
				fullDose=fullDose.substring(0, length);
				 actualDose=fullDose;
				 lineBarChart1.setStartDate(patHistory.getStartDate().getTime());

                 lineBarChart1.setStrength(fullDose);
				
				// System.out.println("value of dose::::::::::::::::::"+lineBarChart1.getStartDate()+""+lineBarChart1.getStrength());
				 lineBarChartList1.add(lineBarChart1);
			 }
		}
	//	System.out.println("value of list::::::"+procedureResultHistoryLineChart.size());
		labLineBarChartMap.put("INR", lineBarChartList);
		labLineBarChartMap.put("Dose", lineBarChartList1);
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("jsonDataSet", new Gson().toJson(labLineBarChartMap));
		//logger.info("json chart for lab "+new Gson().toJson(lineBarChartList));
		System.out.println("JSOn Arrasy "+new Gson().toJson(labLineBarChartMap));
		
		return new Gson().toJson(labLineBarChartMap);
	}*/

	/**
	 * @return the oruPatientLabResultList
	 */
	public List<ORUPatientLabResult> getOruPatientLabResultList() {
		if(oruPatientLabResultList==null){
			oruPatientLabResultList=new ArrayList<ORUPatientLabResult>();
		}
		
		oruPatientLabResultList=labService.getAllWebServicesPatientLabResult();
		
		List<ORUPatientLabResult> patientLabResultList=new ArrayList<ORUPatientLabResult>();
		if(!(oruPatientLabResultList.size()==0)){
			for(ORUPatientLabResult patLabResult:oruPatientLabResultList){
				ORUPatientLabResult patientLabResult=new ORUPatientLabResult();
				patientLabResult.setComment(patLabResult.getComment());
				patientLabResult.setPatientId(patLabResult.getPatientId());
				patientLabResult.setAccessionNo(patLabResult.getAccessionNo());
				patientLabResult.setCollectionDateNTime(patLabResult.getCollectionDateNTime());
				patientLabResult.setPatFirstName(patLabResult.getPatFirstName());
				patientLabResult.setPatLastName(patLabResult.getPatLastName());
				patientLabResult.setRequestReportDate(patLabResult.getRequestReportDate());
				patientLabResult.setRequisitionNo(patLabResult.getRequisitionNo());
				patientLabResult.setHtmlMessage(patLabResult.getHtmlMessage());
				
				patientLabResult.setEmdeonLabResultDetailList(patLabResult.getEmdeonLabResultDetailList());
				
				/*List<ProcedureType> procedureTypeList=patLabResult.getOrderObservationList();
				System.out.println("procedureTypeList.size()"+procedureTypeList.size());
				for(ProcedureType type:procedureTypeList){
					int index=procedureTypeList.indexOf(type);
					if(!(index==0)){
						System.out.println("Procedure Type "+type.getLabType());
						patientLabResult.getOrderObservationList().add(type);
						List<ProcedureResultHistory> histories=type.getObservationLabList();
						for(ProcedureResultHistory history:histories){
							System.out.println("Lab Name "+history.getLabName());
							//patientLabResult.getOrderObservationList().get(patientLabResult.getOrderObservationList().indexOf(type)).getObservationLabList().add(history);
						}
						
					
					}
					
				}*/
				List<EmdeonLabResult> patientLabResultListFromEmedon=patLabResult.getEmdeonLabResultDetailList();
				System.out.println(patientLabResultListFromEmedon.size());
				
				
				patientLabResultList.add(patientLabResult);
					
			}
		}
		
		/*for(ORUPatientLabResult labResult:patientLabResultList){
			System.out.println("labResult.getComment()"+labResult.getComment()+"lab Reslt Size "+labResult.getOrderObservationList().size()+"labResult.getAccessionNo()::::"+labResult.getAccessionNo());
			for(ProcedureType type:labResult.getOrderObservationList()){
				System.out.println("type.getProcedureType()"+type.getLabType());
				for(ProcedureResultHistory history:type.getObservationLabList()){
					System.out.println("history.getLabName()"+history.getLabName());
				}
			}
		}*/
		
		
		return patientLabResultList;
	}

	/**
	 * @param oruPatientLabResultList the oruPatientLabResultList to set
	 */
	public void setOruPatientLabResultList(
			List<ORUPatientLabResult> oruPatientLabResultList) {
		this.oruPatientLabResultList = oruPatientLabResultList;
	}
	
	
	/**
	 * Test Method for print date 
	 * 
	 */
	public void printCuurentDateTime(){
		System.out.println("Current Date "+Calendar.getInstance().getTime());
	}


	public ProcedureResultHistory getCurrentLabValue() {
		//int proc_type_id=2;
		String labName="INR";
		currentLabValue=labService.findCurrentLabValue(new ContextUtil().getPatientId(),new ContextUtil().getProviderId(),new ContextUtil().getLoginId(),labName);
		return currentLabValue;
	}

	public void setCurrentLabValue(ProcedureResultHistory currentLabValue) {
		this.currentLabValue = currentLabValue;
	}

	public void setMedicineForHighChart(String medicineForHighChart) {
		this.medicineForHighChart = medicineForHighChart;
	}

	public String getMedicineForHighChart() {
		return medicineForHighChart;
	}
	
	/*this method is used at High chart GeneralMed Action Plan
	 * @Author Sanket Singh
	 * 
	 * */
	public String medChart(ActionEvent ae)
	{
		
		getAllGenericMedActionPlanChart();
		List<LabLineBarChart>medBarChartList=new ArrayList<LabLineBarChart>();
		List<LabLineBarChart>medBarList=new ArrayList<LabLineBarChart>();
		Map<String, List<LabLineBarChart>>medBarChartMap=new HashMap<String, List<LabLineBarChart>>();
		Date periodDate = getTimePeriod();
		String labName="lab";
		procedureResultHistoryListForChart = labService.labResultValueForChart(periodDate,labName,loincKeyMaster.getProcedureTypeId());
		
	   logger.info("procedureResultListForCurrentLab size"+procedureResultHistoryListForChart.size());
	
		for (ProcedureResultHistory proResultHistory : procedureResultHistoryListForChart) {
			
			LabLineBarChart labBarChartformed=new LabLineBarChart();
			labBarChartformed.setResult(proResultHistory.getResult().toString());
			labBarChartformed.setLongStartDate(proResultHistory.getDateResult().getTime());
			medBarChartList.add(labBarChartformed);
		}
	   int id=0;
		int patientId=new ContextUtil().getPatientId();
   for( GenericMedActionPlan gemed :allGenericMedActionPlanChart)
     {
	medicineForHighChart=gemed.getDrugName();
	id=gemed.getId();
	
	logger.info("name of medicine==>::"+medicineForHighChart);
	
	
       }

		
		if(allGenericMedActionMedicineList==null){
			allGenericMedActionMedicineList=new ArrayList<GenericMedActionPlan>();
			
			
		
		}
		allGenericMedActionMedicineList=labService.getAllGenericMedActionPlan( id);
	System.out.println("size of list"+allGenericMedActionMedicineList.size());
		/*	allGenericMedActionPlan=null;*/
		setMedicineForHighChart(null);
		for(GenericMedActionPlan gMed:allGenericMedActionMedicineList)
		{
			LabLineBarChart lineMedchart=new LabLineBarChart();

		
				lineMedchart.setStrength(gMed.getStrength());
				lineMedchart.setStartDate(gMed.getCreatedDate());

				
			
			logger.info(":::Streanth value::::"+gMed.getStrength());
			
			medBarList.add(lineMedchart);
			lineMedchart=null;
			allGenericMedActionMedicineList=new ArrayList<GenericMedActionPlan>();
			
		}
		
		medBarChartMap.put("Lab", medBarChartList);
		//medBarChartMap.put("loincKeyMaster", medBarChartList);
		medBarChartMap.put("Dose", medBarList);
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("jsonDataSet", new Gson().toJson(medBarChartMap));
		//logger.info("json chart for lab "+new Gson().toJson(lineBarChartList));
		System.out.println("JSOn Arrasy "+new Gson().toJson(medBarChartMap));
		return  new Gson().toJson(medBarChartMap);
	}

	public void setProcedureResultHistoryForMedChart(
			List<ProcedureResultHistory> procedureResultHistoryForMedChart) {
		this.procedureResultHistoryForMedChart = procedureResultHistoryForMedChart;
	}

	public List<ProcedureResultHistory> getProcedureResultHistoryForMedChart() {
		return procedureResultHistoryForMedChart;
	}
	
	

	public void setAllGenericMedActionPlanChart(
			List<GenericMedActionPlan> allGenericMedActionPlanChart) {
		this.allGenericMedActionPlanChart = allGenericMedActionPlanChart;
	}

	public List<GenericMedActionPlan> getAllGenericMedActionPlanChart() {
		int patientId=new ContextUtil().getPatientId();
		if(allGenericMedActionPlanChart==null)
		{
			allGenericMedActionPlanChart=new ArrayList<GenericMedActionPlan>();
			allGenericMedActionPlanChart=labService.getAllGenericMedActionPlan1(patientId);
			logger.info("size of list"+allGenericMedActionPlanChart.size());
		}
		return allGenericMedActionPlanChart;
	}

	public void setAllGenericMedActionMedicineList(
			List<GenericMedActionPlan> allGenericMedActionMedicineList) {
		this.allGenericMedActionMedicineList = allGenericMedActionMedicineList;
	}

	public List<GenericMedActionPlan> getAllGenericMedActionMedicineList() {
		return allGenericMedActionMedicineList;
	}

	/* Method Displaying High chart MAP Page
	 * 
	 * @Author Sanket Singh
	 * 
	 * 
	 * */

	List<LabLineBarChart>medicineDetailForGraphList=null;
	
		public List<LabLineBarChart> getMedicineDetailForGraphList() {
			if(medicineDetailForGraphList==null){
				medicineDetailForGraphList=new ArrayList<LabLineBarChart>();
			}
		return medicineDetailForGraphList;
	}

	public void setMedicineDetailForGraphList(
			List<LabLineBarChart> medicineDetailForGraphList) {
		this.medicineDetailForGraphList = medicineDetailForGraphList;
	}

		@SuppressWarnings("static-access")
		public String medActionPlanHighChart(ActionEvent ae)
		{
			String msg = "";
			logger.info("inside lab BEAN medActionPlanHighChart call::::::");
			 String lab;
			 String lab1=medandGenricmed.getLab();
			 Map<String, List<LabLineBarChart>>medBarChartMap=new HashMap<String, List<LabLineBarChart>>();
			try{
			
			 if (lab1.endsWith(")"))
			 {
			 lab=lab1.substring(0,lab1.indexOf("("));
			 }
			 else
			 {
				 lab=medandGenricmed.getLab(); 
			 }
			
			
			logger.info("lab value in bean "+lab);
			
			 //logger.info("procedureTypeId in bean::::::::::::"+lab+"medandGenricmed medicine"+medandGenricmed.getMedicinename()+"time:::");
			medicineDetailForGraphList=new ArrayList<LabLineBarChart>();
			List<LabLineBarChart>medBarChartList=new ArrayList<LabLineBarChart>();
			List<LabLineBarChart>medBarList=new ArrayList<LabLineBarChart>();
			
			procedureResultHistoryLineChart=new ArrayList<ProcedureResultHistory>();
			
			Date periodDate = getTimePeriod();
			
			/*procedureResultHistoryListForChart = labService.labResultValueForChart(periodDate,loincKeyMaster.getProcedureTypeId());*/
			procedureResultHistoryForMedActionPlan=labService.getProcedureHistoryListForChart(lab,new ContextUtil().getPatientId(),periodDate);
			
			System.out.println("size of list in med=="+procedureResultHistoryForMedActionPlan.size());
			for (ProcedureResultHistory proResultHistory : procedureResultHistoryForMedActionPlan) {
				
			
				//ProcedureResultHistory procedureHistory=new ProcedureResultHistory();
				//procedureHistory.setResult(proResultHistory.getResult());
				//procedureHistory.setDateResult(proResultHistory.getDateResult());
				
				LabLineBarChart lineBarChart=new LabLineBarChart();
				lineBarChart.setLongStartDate(proResultHistory.getDateResult().getTime());
				
			
				lineBarChart.setResult(proResultHistory.getResult().toString());
				medBarChartList.add(lineBarChart);
				/*labLineBarChartMap.put("lab", lineBarChartList);
				logger.info("list size==="+labLineBarChartMap.size());*/
			
			
			//logger.info("id in bean:::::"+loincKeyMaster.getProcedureTypeId());
			
		
			}
			
			for (MedandGenricmed unitDet: medandGenricmedListForMedicine)
			{
				//labUnitDetails=new String();
				//unitDet.getLabUnit();.
				if(unitDet.getLab().equals(medandGenricmed.getLab()))
				{
					System.out.println("labUnitDetails=new String();:::::"+unitDet.getLab()+"::"+medandGenricmed.getLab()+"::::"+unitDet.getLabUnit());
				//setLabUnitDetails(unitDet.getLabUnit());
					medandGenricmed.setLabUnit(unitDet.getLabUnit());
				System.out.println("labUnitDetails:::::"+medandGenricmed.getLabUnit());
				}
			}
			
			String split[]=medandGenricmed.getMedicinename().split("\\+");
			 String medicinename=split[0];
			 medandGenricmed.setMedicinenameforgraph(medicinename);
			double drugid=Double.parseDouble(split[1]);
			logger.info(">>>drug_id_split"+drugid);
			 logger.info("medicinename highchart medthod:::::=="+medicinename+"==periodDate==:::"+periodDate);
			
			 medandGenricmedListForDisplayList=labService. fetchGenericListforDisplay(new ContextUtil().getPatientId(),medicinename,periodDate);
			
			 List<PatientMedicationData> patientmedicationdatalist=labService.fetchpatientmedicationlist(new ContextUtil().getPatientId(),medicinename,periodDate);
			/*FacesContext context = FacesContext.getCurrentInstance();
			 PatientMedicineManageBean patientmedicineManageBean = (PatientMedicineManageBean) context.getApplication()
						.getELResolver()
						.getValue(context.getELContext(), null, "patientmedicineManageBean");*/
			
			 
			 List<PharmacyDetail> pharmcydetail=new ArrayList<PharmacyDetail>();
			 
			 pharmcydetail= labService.findPharmacyDetailHistoryListAccordingToParticularDrugId(drugid, new ContextUtil().getPatientId(),periodDate);
			 
			 
			 System.out.print("<<<<<<<<<<<<<<< phphramcy size"+pharmcydetail.size());
			 for(PharmacyDetail phardet:pharmcydetail)
			 {
				System.out.println("dosage information>>>>"+phardet.getDosage());
				LabLineBarChart labLineBarChart=new LabLineBarChart();
				if(phardet.getDosage()!=null && StringUtils.isNotBlank(phardet.getDosage()))
				{
				
				 Double strength=Double.parseDouble((phardet.getDosage().substring(0, phardet.getDosage().indexOf(" "))));
				
				 
        		  /* String tidstr1=phardet.getDosage().substring(phardet.getDosage().indexOf(" "), phardet.getDosage().indexOf("g")+1);
        		   System.out.println("last index of stength"+tidstr1);*/
        		   /*String tidstr=Double.toString(strength*1);*/
        		   DecimalFormat df = new DecimalFormat("0.00");
        		   df.setMaximumFractionDigits(2);
        		   String tidstr=df.format(strength*1);
        		  
        		   /*String tidstr=tidstr1+tidstr2;*/
        		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< pharmacy strength"+tidstr);
        	   labLineBarChart.setStrength(tidstr);
        	   labLineBarChart.setStartDate(phardet.getDateOfPurchase());
        	   medBarList.add(labLineBarChart);
				}
			 }
			 List<SigCode> sigCodeList=new ArrayList<SigCode>();
			 sigCodeList =labService.fetchsigcodelist();
			 logger.info("sigcode list size"+sigCodeList.size());
				for(PatientMedicationHistory value:medandGenricmedListForDisplayList)
				{
					
					LabLineBarChart labLineBarChart=new LabLineBarChart();
                   if(value.getStrength()!=null && StringUtils.isNotBlank(value.getStrength()))
                   		{
                	   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+value.getStrength());
   					for(SigCode sig1:sigCodeList)
   					{
                	   if(value.getDirection().equalsIgnoreCase(sig1.getSigCode()))
                	   { 
                		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code value"+sig1.getSigCode());
                		
                		   System.out.println("<<<outside if>>>>>"+value.getStrength());
                		   Double strength=Double.parseDouble((value.getStrength().substring(0, value.getStrength().indexOf(" "))));
                		 
                		   /*String tidstr1=value.getStrength().substring(value.getStrength().indexOf(" "), value.getStrength().indexOf("g")+1);
                		   System.out.println("last index of stength"+tidstr1);*/
                		   /*String tidstrvalue=Double.toString(strength*sig1.getSigCodeValue());*/
                		   DecimalFormat df = new DecimalFormat("0.00");
                		   df.setMaximumFractionDigits(2);
                		   String tidstr=df.format(strength*sig1.getSigCodeValue());
                		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< pharmacy strength"+tidstr);
                		   /*String tidstr=tidstr1+tidstr2;*/
                		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TID"+tidstr);
                	   labLineBarChart.setStrength(tidstr);
                	   labLineBarChart.setStartDate(value.getStartDate());
                	   labLineBarChart.setDirection(sig1.getSigCode());
                	   labLineBarChart.setDose(strength);
                	   labLineBarChart.setDirectionvalue(sig1.getSigCodeValue());
                	   medBarList.add(labLineBarChart);
                	   logger.info("med bar size 222>>>");
                	   }
                	 else if(value.getDirection()==null || StringUtils.isBlank(value.getDirection()))
                	   {
                		 System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code for blank field in value"+sig1.getSigCode());
                		
              		   Double strength=Double.parseDouble((value.getStrength().substring(0, value.getStrength().indexOf(" "))));
              		 
              		 
              		 
              		   /*String tidstr1=value.getStrength().substring(value.getStrength().indexOf(" "), value.getStrength().indexOf("g")+1);
              		   System.out.println("last index of stength"+tidstr1);*/
              		  /* String tidstr=Double.toString(strength*1);*/
              		  DecimalFormat df = new DecimalFormat("0.00");
           		   df.setMaximumFractionDigits(2);
           		   String tidstr=df.format(strength*1);
              		   /*String tidstr=tidstr1+tidstr2;*/
              		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TID"+tidstr);
              	   labLineBarChart.setStrength(tidstr);
              	   labLineBarChart.setStartDate(value.getStartDate());
              	 labLineBarChart.setDirection(value.getDirection());
          	   labLineBarChart.setDose(strength);
          	   labLineBarChart.setDirectionvalue(1);
              	   medBarList.add(labLineBarChart); 
                		logger.info("med bar size 222>>>"+medBarList.size());
                	   }
                	  /*else
                	   {
                		   System.out.println("in other cases <<<<<<<<<<");
                		   labLineBarChart.setStrength(value.getStrength());
                    	   labLineBarChart.setStartDate(value.getStartDate().getTime());
                    	   medBarList.add(labLineBarChart);   
                	   }*/
                   		}
                   		}
					//logger.info("medandGenricmedListForDisplayList size:::"+medandGenricmedListForDisplayList.size());
					//logger.info("value "+value.getDrugs()+"::lab "+value.getStrength()+"");
			
				
				}
				
				for(PatientMedicationData value:patientmedicationdatalist)
				{
					
					LabLineBarChart labLineBarChart=new LabLineBarChart();
                   if(value.getStrengths()!=null && StringUtils.isNotBlank(value.getStrengths()))
                   		{
                	   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< for medication data"+value.getStrengths());
   					for(SigCode sig1:sigCodeList)
   					{
                	   if(value.getDirections().equalsIgnoreCase(sig1.getSigCode()))
                	   { 
                		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code value"+sig1.getSigCode());
                		
                		   Double strength=Double.parseDouble((value.getStrengths().substring(0, value.getStrengths().indexOf(" "))));
                		  
                		   /*String tidstr1=value.getStrength().substring(value.getStrength().indexOf(" "), value.getStrength().indexOf("g")+1);
                		   System.out.println("last index of stength"+tidstr1);*/
                		 /*  String tidstr=Double.toString(strength*sig1.getSigCodeValue());*/
                		   /*String tidstr=tidstr1+tidstr2;*/
                		   DecimalFormat df = new DecimalFormat("0.00");
                		   df.setMaximumFractionDigits(2);
                		   String tidstr=df.format(strength*sig1.getSigCodeValue());
                		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TID"+tidstr);
                	   labLineBarChart.setStrength(tidstr);
                	   labLineBarChart.setStartDate(value.getStartDate());
                	   labLineBarChart.setDirection(sig1.getSigCode());
                	   labLineBarChart.setDose(strength);
                	   labLineBarChart.setDirectionvalue(sig1.getSigCodeValue());
                	   medBarList.add(labLineBarChart);
                	   logger.info("med bar size 222>>>");
                	   }
                	 else if(value.getDirections()==null || StringUtils.isBlank(value.getDirections()))
                	   {
                		 System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code for blank field in value for medication data"+sig1.getSigCode());
                		
              		   Double strength=Double.parseDouble((value.getStrengths().substring(0, value.getStrengths().indexOf(" "))));
              		
              		   /*String tidstr1=value.getStrength().substring(value.getStrength().indexOf(" "), value.getStrength().indexOf("g")+1);
              		   System.out.println("last index of stength"+tidstr1);*/
              		 /*  String tidstr=Double.toString(strength*1);*/
              		 DecimalFormat df = new DecimalFormat("0.00");
          		   df.setMaximumFractionDigits(2);
          		   String tidstr=df.format(strength*1);
              		   
              		   /*String tidstr=tidstr1+tidstr2;*/
              		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TID for medication data"+tidstr);
              	   labLineBarChart.setStrength(tidstr);
              	   labLineBarChart.setStartDate(value.getStartDate());
              	 labLineBarChart.setDirection(value.getDirections());
          	   labLineBarChart.setDose(strength);
          	   labLineBarChart.setDirectionvalue(1);
              	   medBarList.add(labLineBarChart); 
                		logger.info("med bar size 222>>>"+medBarList.size());
                	   }
                	 /* else
                	   {
                		   System.out.println("in other cases <<<<<<<<<<");
                		   labLineBarChart.setStrength("20");
                    	   labLineBarChart.setStartDate(new Date());
                    	   medBarList.add(labLineBarChart);   
                	   }*/
                   		}
                   		}
					//logger.info("medandGenricmedListForDisplayList size:::"+medandGenricmedListForDisplayList.size());
					//logger.info("value "+value.getDrugs()+"::lab "+value.getStrength()+"");
			
				
				}
			
				logger.info("method start before sorting:::"+medBarList.size());
				for(LabLineBarChart lab11:medBarList)
				{
					
					lab11.setLongStartDate(lab11.getStartDate().getTime());
					System.out.println(lab11.getLongStartDate()+":11::::date::::"+lab11.getStartDate()+"result:::"+lab11.getResult());
				
					
				}
				
			
				
				Collections.sort(medBarList);
				if(medBarList.size()>1 || medBarList.size()==1)
				{
				LabLineBarChart labLineBarChart=new LabLineBarChart();
				
				LabLineBarChart labLineBarChartObj=medBarList.get(medBarList.size() - 1);
				
				 labLineBarChart.setStrength(labLineBarChartObj.getStrength());
				 labLineBarChart.setStartDate(new DateUtil().getTodayDate());
				 labLineBarChart.setLongStartDate(new DateUtil().getTodayDate().getTime());
				 labLineBarChart.setDirection(labLineBarChartObj.getDirection());
				 labLineBarChart.setDirectionvalue(labLineBarChartObj.getDirectionvalue());
				 labLineBarChart.setDose(labLineBarChartObj.getDose());
          	     medBarList.add(labLineBarChart); 
				}
				medicineDetailForGraphList=medBarList;
				
				/*logger.info("method start after sorting:::"+medBarList.size());
				for(LabLineBarChart lab11:medBarList)
				{
					System.out.println(":222::::date::::"+lab11.getStartDate()+"result:::"+lab11.getResult());
				}*/
				
					medBarChartMap.put("Lab", medBarChartList);
					medBarChartMap.put("Dose", medBarList);
					RequestContext context1 = RequestContext.getCurrentInstance();
					context1.addCallbackParam("jsonDataSet", new Gson().toJson(medBarChartMap));
				//	context1.addCallbackParam("LabAxis", "CR");
					//logger.info("json chart for lab "+new Gson().toJson(lineBarChartList));
					System.out.println("JSOn Arrasy "+new Gson().toJson(medBarChartMap));
					System.out.println("form_chart:::::::"+medBarChartMap.size());
					
					if(medBarChartList.size()==0 && medBarList.size()==0)
					{
						msg="No data found";
						FacesContext.getCurrentInstance().addMessage("messageUpdateForNewAddMeds", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,""));
					}
					
			}catch(NumberFormatException nfe){
				 msg="Dose of medicine is not in Number Format";
					FacesContext.getCurrentInstance().addMessage("messageUpdateForNewAddMeds", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,""));
					nfe.printStackTrace();
				
			}
			return  new Gson().toJson(medBarChartMap);
			
			
		}

		public List<ProcedureResultHistory> getProcedureResultHistoryLineChart() {
			
			return procedureResultHistoryLineChart;
		}

		public MedandGenricmed getMedandGenricmed() {
			return medandGenricmed;
		}

		public void setMedandGenricmed(MedandGenricmed medandGenricmed) {
			this.medandGenricmed = medandGenricmed;
		}
/* //use the list displaying the lab Value in high chart at general med action plan dose response curve
*/		
		public List<MedandGenricmed> getMedActionListForChart() {
			String medicinename=medandGenricmed.getMedicinename();
			 logger.info("medicinename:::"+medicinename);
			 /*if(getTimePeriod()!=null)
			 {
				 Date periodDate = getTimePeriod();
			 }*/
			medActionListForChart=labService. fetchGenericList(new ContextUtil().getPatientId());
			 
			return medActionListForChart;
		}

		public void setMedActionListForChart(List<MedandGenricmed> medActionListForChart) {
			this.medActionListForChart = medActionListForChart;
		}

	

		public void setMedandGenricmedListForMedicine(
				List<MedandGenricmed> medandGenricmedListForMedicine) {
			this.medandGenricmedListForMedicine = medandGenricmedListForMedicine;
		}

	
		/**
		 * used in  genralmedactionplan.jsf page 
		 *  display the lab value in MAP dropDown
		 * @return List of MedandGenricmed
		 */
		public List<MedandGenricmed> getMedandGenricmedListForMedicine() {
			String lab=medandGenricmed.getLab();
			medandGenricmedListForMedicine=labService. fetchGenericListforLab(new ContextUtil().getPatientId(), lab); // Get the Lab Name based on patientId and lab  
			
			return medandGenricmedListForMedicine;
		}

		public List<PatientMedicationHistory> getMedandGenricmedListForDisplayList() {
			return medandGenricmedListForDisplayList;
		}

		public void setMedandGenricmedListForDisplayList(
				List<PatientMedicationHistory> medandGenricmedListForDisplayList) {
			this.medandGenricmedListForDisplayList = medandGenricmedListForDisplayList;
		}

		public void setProcedureResultHistoryForMedActionPlan(
				List<ProcedureResultHistory> procedureResultHistoryForMedActionPlan) {
			this.procedureResultHistoryForMedActionPlan = procedureResultHistoryForMedActionPlan;
		}

		public List<ProcedureResultHistory> getProcedureResultHistoryForMedActionPlan() {
			return procedureResultHistoryForMedActionPlan;
		}

		public int getSearchLabOption() {
			return searchLabOption;
		}

		public void setSearchLabOption(int searchLabOption) {
			this.searchLabOption = searchLabOption;
		}

		public String getInputForLabSearch() {
			return inputForLabSearch;
		}

		public void setInputForLabSearch(String inputForLabSearch) {
			this.inputForLabSearch = inputForLabSearch;
		}
		
		public String getResultForLabSearch() {
			return resultForLabSearch;
		}

		public void setResultForLabSearch(String resultForLabSearch) {
			this.resultForLabSearch = resultForLabSearch;
		}

/*
 * 	
 */

	public List<ProcedureType> getProcedureTypeListForSearchLab() {
		
		return procedureTypeListForSearchLab;
	}

	public void setProcedureTypeListForSearchLab(
			List<ProcedureType> procedureTypeListForSearchLab) {
		this.procedureTypeListForSearchLab = procedureTypeListForSearchLab;
	}

	
/*
 * used in labResults.jsf page 
 * Method used  to search lab according to lab type/loinc code	
 */
	
	public void searchLab() {
		logger.info("=========call searchLab inside labManageBean::::searchLabOption="+searchLabOption+
				"::inputForLabSearch="+inputForLabSearch);
		//set
		//procedureTypeListForSearchLab= new ArrayList<ProcedureType>();
		//if ( !(inputForLabSearch==null))
		if(StringUtils.isNotBlank(inputForLabSearch)) {
			procedureTypeListForSearchLab=new ArrayList<ProcedureType>();
			procedureTypeListForSearchLab=labService.getProcedureTypeAferSearch(searchLabOption,inputForLabSearch); // Search Lab name according to search type based on Loinc Code or Lab and Lab name 
			logger.info("procedureTypeListForSearchLab size:::"+procedureTypeListForSearchLab);
			RequestContext context = RequestContext.getCurrentInstance(); 
			context.addCallbackParam("searchResultSet", true);
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Please enter name to search", ""));  
		}
	}

	public Date getDateForLabSearch() {
		return dateForLabSearch;
	}

	public void setDateForLabSearch(Date dateForLabSearch) {
		this.dateForLabSearch = dateForLabSearch;
	}
/*
 * **************Method to clear feilds after lab search
 * @uthor: saurabh
 */
	public void clearLabSearchData() {
		logger.info("inside clearLabSearchData in lab manage bean::::::::::::::");
		setDateForLabSearch(null);
		//dateForLabSearch= new Date();
		//resultForLabSearch = new String();
		//inputForLabSearch = new String();
        setResultForLabSearch(null);
        setInputForLabSearch(null);
        procedureTypeListForSearchLab=new ArrayList<ProcedureType>();
	}

	public Double getSelectedCurrentLabResultForEdit() {
		return selectedCurrentLabResultForEdit;
	}

	public void setSelectedCurrentLabResultForEdit(
			Double selectedCurrentLabResultForEdit) {
		this.selectedCurrentLabResultForEdit = selectedCurrentLabResultForEdit;
	}

	public String getSelectedCurrentLabNameForEdit() {
		return selectedCurrentLabNameForEdit;
	}

	public void setSelectedCurrentLabNameForEdit(
			String selectedCurrentLabNameForEdit) {
		this.selectedCurrentLabNameForEdit = selectedCurrentLabNameForEdit;
	}

	public int getSelectedCurrentLabIdForEdit() {
		return selectedCurrentLabIdForEdit;
	}

	public void setSelectedCurrentLabIdForEdit(int selectedCurrentLabIdForEdit) {
		this.selectedCurrentLabIdForEdit = selectedCurrentLabIdForEdit;
	}

	public DataTable getLabMasterDatetable() {
		return labMasterDatetable;
	}

	public void setLabMasterDatetable(DataTable labMasterDatetable) {
		this.labMasterDatetable = labMasterDatetable;
	}

	public String getLabNameForAddingNew() {
		return labNameForAddingNew;
	}

	public void setLabNameForAddingNew(String labNameForAddingNew) {
		this.labNameForAddingNew = labNameForAddingNew;
	}

	public String clearLabResultDetails(){
 		logger.info("calling clearLabResultDetails method::::::::::::");
 		procedureType =  new ProcedureType();
 		labNameForAddingNew = "";
 		//setLabNameForAddingNew(null);
 		dateForLabSearch = new Date();
 		setDateForLabSearch(null);
 		resultForLabSearch =  new String(); 
 		setResultForLabSearch(null);
 		//setDateForLabSearch(null);
 		//getDateForLabSearch(),getResultForLabSearch()
		return "go_to_LabResult";
	}
	/*
	 * open pop up for lab search when user add lab
	 * @author: Gopal
	 */
	public void openLabDetailForSearch()
	{
		logger.info("openLabDetailForSearch");
		procedureTypeListForSearchLab=new ArrayList<ProcedureType>();
		searchLabOption=1;
		inputForLabSearch="";
		
	}
	/**
	 * used in labResults.jsf page 
	 * used to clean the data of lab data for add new lab data   
	 */
	public void clearLabDetailsData()
	{
		logger.info("clearLabDetailsData::::");
		labNameForAddingNew= "";
		dateForLabSearch = new Date();
		resultForLabSearch = "";
		inputForLabSearch = "";
	}

	public int getRowIdOfLabHistory() {
		return rowIdOfLabHistory;
	}

	public void setRowIdOfLabHistory(int rowIdOfLabHistory) {
		this.rowIdOfLabHistory = rowIdOfLabHistory;
	}
	
	public void deleteLabName()
	{
		logger.info("clearLab name::::");
		
		labNameForAddingNew="";
		dateForLabSearch = new Date();
		resultForLabSearch ="";		
		
	}
	
	public void clearLabData()
	
	{
		logger.info("clearLab data name::::");
		
		labNameForAddingNew="";
		//dateForLabSearch = new Date();
		//dateForLabSearch = null;
		//resultForLabSearch ="";	
		logger.info("after clear labData::::");
	}

	public List<ProcedureResultHistory> getLabresulthistorylistforanticoag(String lab,int patientid,Date periodDate) {
		
		labresulthistorylistforanticoag=labService.getProcedureHistoryListForChart(lab,new ContextUtil().getPatientId(),periodDate);
		return labresulthistorylistforanticoag;
	}

	public void setLabresulthistorylistforanticoag(
			List<ProcedureResultHistory> labresulthistorylistforanticoag) {
		this.labresulthistorylistforanticoag = labresulthistorylistforanticoag;
	}

	public List<PatientMedicationHistory> getMedicationhistoryforanticoag(int patientid,String medicinename,Date periodDate) {
		
		medicationhistoryforanticoag=labService.fetchGenericListforDisplay(patientid,medicinename,periodDate);
		return medicationhistoryforanticoag;
	}

	public void setMedicationhistoryforanticoag(
			List<PatientMedicationHistory> medicationhistoryforanticoag) {
		this.medicationhistoryforanticoag = medicationhistoryforanticoag;
	}

	public List<PatientMedicationData> getMedicationdataforanticoag(int patientid,String medicinename,Date periodDate) {
		medicationdataforanticoag=labService.fetchpatientmedicationlist(new ContextUtil().getPatientId(),medicinename,periodDate);
		return medicationdataforanticoag;
	}

	public void setMedicationdataforanticoag(
			List<PatientMedicationData> medicationdataforanticoag) {
		this.medicationdataforanticoag = medicationdataforanticoag;
	}

	/**
	 * @return the psychoPharmLabResultList
	 */
	public List<ProcedureResultHistory> getPsychoPharmLabResultList() {
		if(psychoPharmLabResultList==null){
			psychoPharmLabResultList=new ArrayList<ProcedureResultHistory>();
			
		}
		int patientId=new ContextUtil().getPatientId();
		
		psychoPharmLabResultList=labService.getPsychoPharmLabResultData(patientId,PSYCHOPHARM_CLINIC_NAME);
		return psychoPharmLabResultList;
	}

	/**
	 * @param psychoPharmLabResultList the psychoPharmLabResultList to set
	 */
	public void setPsychoPharmLabResultList(
			List<ProcedureResultHistory> psychoPharmLabResultList) {
		this.psychoPharmLabResultList = psychoPharmLabResultList;
	}
  /**
   * Get Value of Lab Result and Drug Dose
   *  for PsychoPharm Med Action Plan Chart 
   */
	public String getPsychoPharmMedActionPlanChartValue(ActionEvent event){
		psychoPharmMedLabChartMap=new HashMap<String, List<LabLineBarChart>>();
		Date timePeriod=getTimePeriod();
		String lab=medandGenricmed.getLab();
		medicineDetailForGraphList=new ArrayList<LabLineBarChart>();
		medBarList=new ArrayList<LabLineBarChart>();
		psychPharmMedActionPlanLab=labService.getProcedureHistoryListForChart(lab,new ContextUtil().getPatientId(),timePeriod);
		System.out.println("psychPharmMedActionPlanLab"+psychPharmMedActionPlanLab.size());
		List<LabLineBarChart> psychoPharmLabChartList=new ArrayList<LabLineBarChart>();
		for(ProcedureResultHistory resultHistory:psychPharmMedActionPlanLab){
			LabLineBarChart psychoPharmLabChart=new LabLineBarChart();
			psychoPharmLabChart.setResult(resultHistory.getResult().toString());
			psychoPharmLabChart.setLongStartDate(resultHistory.getDateResult().getTime());
			psychoPharmLabChartList.add(psychoPharmLabChart);
			
			
		}
		
		for (MedandGenricmed unitDet: medandGenricmedListForMedicine)
		{
			
			if(unitDet.getLab().equals(medandGenricmed.getLab()))
			{
				System.out.println("labUnitDetails=new String();:::::"+unitDet.getLab()+"::"+medandGenricmed.getLab()+"::::"+unitDet.getLabUnit());
			
				medandGenricmed.setLabUnit(unitDet.getLabUnit());
			 System.out.println("labUnitDetails:::::"+medandGenricmed.getLabUnit());
			}
		}
		//Get Medicine name and patientMedicationData and patientMedicationDatahistory
		 String split[]=medandGenricmed.getMedicinename().split("\\+");
		 String medicinename=split[0];
		 medandGenricmed.setMedicinenameforgraph(medicinename);
		double drugid=Double.parseDouble(split[1]);
		logger.info(">>>drug_id_split"+drugid);
		
		
		 medandGenricmedListForDisplayList=labService. fetchGenericListforDisplay(new ContextUtil().getPatientId(),medicinename,timePeriod);
		
		 List<PatientMedicationData> patientmedicationdatalist=labService.fetchpatientmedicationlist(new ContextUtil().getPatientId(),medicinename,timePeriod);
		
		 
		 List<PharmacyDetail> pharmcydetail=new ArrayList<PharmacyDetail>();
		 
		 pharmcydetail= labService.findPharmacyDetailHistoryListAccordingToParticularDrugId(drugid, new ContextUtil().getPatientId(),timePeriod);
		 
		 
		 System.out.print("<<<<<<<<<<<<<<< phphramcy size"+pharmcydetail.size());
		 for(PharmacyDetail phardet:pharmcydetail)
		 {
			System.out.println("dosage information>>>>"+phardet.getDosage());
			LabLineBarChart labLineBarChart=new LabLineBarChart();
			if(phardet.getDosage()!=null && StringUtils.isNotBlank(phardet.getDosage()))
			{
			
			 Double strength=Double.parseDouble((phardet.getDosage().substring(0, phardet.getDosage().indexOf(" "))));
			
			 
   		 
   		   DecimalFormat df = new DecimalFormat("0.00");
   		   df.setMaximumFractionDigits(2);
   		   String tidstr=df.format(strength*1);
   		  
   		   
   		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< pharmacy strength"+tidstr);
   	   labLineBarChart.setStrength(tidstr);
   	   labLineBarChart.setStartDate(phardet.getDateOfPurchase());
   	   medBarList.add(labLineBarChart);
			}
		 }
		 List<SigCode> sigCodeList=new ArrayList<SigCode>();
		 sigCodeList =labService.fetchsigcodelist();
		 logger.info("sigcode list size"+sigCodeList.size());
			for(PatientMedicationHistory value:medandGenricmedListForDisplayList)
			{
				
				LabLineBarChart labLineBarChart=new LabLineBarChart();
              if(value.getStrength()!=null && StringUtils.isNotBlank(value.getStrength()))
              		{
           	   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+value.getStrength());
					for(SigCode sig1:sigCodeList)
					{
           	   if(value.getDirection().equalsIgnoreCase(sig1.getSigCode()))
           	   { 
           		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code value"+sig1.getSigCode());
           		
           		   System.out.println("<<<outside if>>>>>"+value.getStrength());
           		   Double strength=Double.parseDouble((value.getStrength().substring(0, value.getStrength().indexOf(" "))));
           		 
           		  
           		   DecimalFormat df = new DecimalFormat("0.00");
           		   df.setMaximumFractionDigits(2);
           		   String tidstr=df.format(strength*sig1.getSigCodeValue());
           		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< pharmacy strength"+tidstr);
           		  
           		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TID"+tidstr);
           	   labLineBarChart.setStrength(tidstr);
           	   labLineBarChart.setStartDate(value.getStartDate());
           	   labLineBarChart.setDirection(sig1.getSigCode());
           	   labLineBarChart.setDose(strength);
           	   labLineBarChart.setDirectionvalue(sig1.getSigCodeValue());
           	   medBarList.add(labLineBarChart);
           	   logger.info("med bar size 222>>>");
           	   }
           	 else if(value.getDirection()==null || StringUtils.isBlank(value.getDirection()))
           	   {
           		 System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code for blank field in value"+sig1.getSigCode());
           		
         		   Double strength=Double.parseDouble((value.getStrength().substring(0, value.getStrength().indexOf(" "))));
         		 
         		 
         		 
         		  
         		  DecimalFormat df = new DecimalFormat("0.00");
      		   df.setMaximumFractionDigits(2);
      		   String tidstr=df.format(strength*1);
         		  
         		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TID"+tidstr);
         	   labLineBarChart.setStrength(tidstr);
         	   labLineBarChart.setStartDate(value.getStartDate());
         	 labLineBarChart.setDirection(value.getDirection());
     	   labLineBarChart.setDose(strength);
     	   labLineBarChart.setDirectionvalue(1);
         	   medBarList.add(labLineBarChart); 
           		logger.info("med bar size 222>>>"+medBarList.size());
           	   }
           	  
              		}
              		}
				
		
			
			}
			
			for(PatientMedicationData value:patientmedicationdatalist)
			{
				
				LabLineBarChart labLineBarChart=new LabLineBarChart();
              if(value.getStrengths()!=null && StringUtils.isNotBlank(value.getStrengths()))
              		{
           	   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< for medication data"+value.getStrengths());
					for(SigCode sig1:sigCodeList)
					{
           	   if(value.getDirections().equalsIgnoreCase(sig1.getSigCode()))
           	   { 
           		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code value"+sig1.getSigCode());
           		
           		   Double strength=Double.parseDouble((value.getStrengths().substring(0, value.getStrengths().indexOf(" "))));
           		  
           		   
           		   DecimalFormat df = new DecimalFormat("0.00");
           		   df.setMaximumFractionDigits(2);
           		   String tidstr=df.format(strength*sig1.getSigCodeValue());
           		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TID"+tidstr);
           	   labLineBarChart.setStrength(tidstr);
           	   labLineBarChart.setStartDate(value.getStartDate());
           	   labLineBarChart.setDirection(sig1.getSigCode());
           	   labLineBarChart.setDose(strength);
           	   labLineBarChart.setDirectionvalue(sig1.getSigCodeValue());
           	   medBarList.add(labLineBarChart);
           	   logger.info("med bar size 222>>>");
           	   }
           	 else if(value.getDirections()==null || StringUtils.isBlank(value.getDirections()))
           	   {
           		 System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code for blank field in value for medication data"+sig1.getSigCode());
           		
         		   Double strength=Double.parseDouble((value.getStrengths().substring(0, value.getStrengths().indexOf(" "))));
         		
         		  
         		 DecimalFormat df = new DecimalFormat("0.00");
     		   df.setMaximumFractionDigits(2);
     		   String tidstr=df.format(strength*1);
         		   
         		  
         		  
         	   labLineBarChart.setStrength(tidstr);
         	   labLineBarChart.setStartDate(value.getStartDate());
         	 labLineBarChart.setDirection(value.getDirections());
     	   labLineBarChart.setDose(strength);
     	   labLineBarChart.setDirectionvalue(1);
         	   medBarList.add(labLineBarChart); 
           		logger.info("med bar size 222>>>"+medBarList.size());
           	   }
           	 
              		}
              		}
				
			
			}
		
			logger.info("method start before sorting:::"+medBarList.size());
			for(LabLineBarChart lab11:medBarList)
			{
				
				lab11.setLongStartDate(lab11.getStartDate().getTime());
				System.out.println(lab11.getLongStartDate()+":11::::date::::"+lab11.getStartDate()+"result:::"+lab11.getResult());
			
				
			}
			
		
			
			Collections.sort(medBarList);
			if(medBarList.size()>1 || medBarList.size()==1)
			{
			LabLineBarChart labLineBarChart=new LabLineBarChart();
			
			LabLineBarChart labLineBarChartObj=medBarList.get(medBarList.size() - 1);
			
			 labLineBarChart.setStrength(labLineBarChartObj.getStrength());
			 labLineBarChart.setStartDate(new DateUtil().getTodayDate());
			 labLineBarChart.setLongStartDate(new DateUtil().getTodayDate().getTime());
			 labLineBarChart.setDirection(labLineBarChartObj.getDirection());
			 labLineBarChart.setDirectionvalue(labLineBarChartObj.getDirectionvalue());
			 labLineBarChart.setDose(labLineBarChartObj.getDose());
     	     medBarList.add(labLineBarChart); 
			}
			medicineDetailForGraphList=medBarList;
			
			
			
		
		psychoPharmMedLabChartMap.put("Lab", psychoPharmLabChartList);
		psychoPharmMedLabChartMap.put("Dose", medBarList);
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("jsonDataSet", new Gson().toJson(psychoPharmMedLabChartMap));
		
		System.out.println("new Gson().toJson(psychoPharmLabChartList)::::; "+new Gson().toJson(psychoPharmMedLabChartMap));
		return new Gson().toJson(psychoPharmMedLabChartMap);
		
	}

/**
 * @return the psychoPharmMedLabChartMap
 */
public Map<String, List<LabLineBarChart>> getPsychoPharmMedLabChartMap() {
	if(psychoPharmMedLabChartMap==null){
		psychoPharmMedLabChartMap=new HashMap<String, List<LabLineBarChart>>();
	}
	return psychoPharmMedLabChartMap;
}

/**
 * @param psychoPharmMedLabChartMap the psychoPharmMedLabChartMap to set
 */
public void setPsychoPharmMedLabChartMap(
		Map<String, List<LabLineBarChart>> psychoPharmMedLabChartMap) {
	this.psychoPharmMedLabChartMap = psychoPharmMedLabChartMap;
}

/**
 * @return the psychPharmMedActionPlanLab
 */
public List<ProcedureResultHistory> getPsychPharmMedActionPlanLab() {
	if(psychPharmMedActionPlanLab==null){
		psychPharmMedActionPlanLab=new ArrayList<ProcedureResultHistory>();
	}
	return psychPharmMedActionPlanLab;
}

/**
 * @param psychPharmMedActionPlanLab the psychPharmMedActionPlanLab to set
 */
public void setPsychPharmMedActionPlanLab(
		List<ProcedureResultHistory> psychPharmMedActionPlanLab) {
	this.psychPharmMedActionPlanLab = psychPharmMedActionPlanLab;
}

/**
 * @return the hepCLabResultList
 */
public List<ProcedureResultHistory> getHepCLabResultList() {
	if(hepCLabResultList==null){
		hepCLabResultList=new ArrayList<ProcedureResultHistory>();
	}
	int patientId=new ContextUtil().getPatientId();
	
	hepCLabResultList=labService.getHepCLabResultData(patientId,HEPATITIS_C_CLINIC_NAME);
	return hepCLabResultList;
}

/**
 * @param hepCLabResultList the hepCLabResultList to set
 */
public void setHepCLabResultList(List<ProcedureResultHistory> hepCLabResultList) {
	this.hepCLabResultList = hepCLabResultList;
}

/**
 * @return the hepCMedActionPlanLabDataList
 */
public List<ProcedureResultHistory> getHepCMedActionPlanLabDataList() {
	if(hepCMedActionPlanLabDataList==null){
		hepCMedActionPlanLabDataList=new ArrayList<ProcedureResultHistory>();
	}
	return hepCMedActionPlanLabDataList;
}

/**
 * @param hepCMedActionPlanLabDataList the hepCMedActionPlanLabDataList to set
 */
public void setHepCMedActionPlanLabDataList(
		List<ProcedureResultHistory> hepCMedActionPlanLabDataList) {
	this.hepCMedActionPlanLabDataList = hepCMedActionPlanLabDataList;
}
  /**
   * Get Chart Value for Hepatitis C Clinic
   * @param event
   * @return
   */
public String getHepCMedActionPlanChartValue(ActionEvent event){
	logger.info(" getHepatitisCMedActionPlanChartValue method started now ");
	System.out.println(" getHepatitisCMedActionPlanChartValue method started now ");
	 hepatitisCMedLabChartMap=new HashMap<String, List<LabLineBarChart>>();
	Date timePeriod=getTimePeriod();
	String lab=medandGenricmed.getLab();
	medicineDetailForGraphList=new ArrayList<LabLineBarChart>();
	medBarList=new ArrayList<LabLineBarChart>();
	hepCMedActionPlanLabDataList=labService.getProcedureHistoryListForChart(lab,new ContextUtil().getPatientId(),timePeriod);
	
	List<LabLineBarChart> hepCLabChartList=new ArrayList<LabLineBarChart>();
	for(ProcedureResultHistory resultHistory:hepCMedActionPlanLabDataList){
		LabLineBarChart hepCLabChart=new LabLineBarChart();
		hepCLabChart.setResult(resultHistory.getResult().toString());
		hepCLabChart.setLongStartDate(resultHistory.getDateResult().getTime());
		hepCLabChartList.add(hepCLabChart);
		
		
	}
	
	for (MedandGenricmed unitDet: medandGenricmedListForMedicine)
	{
		
		if(unitDet.getLab().equals(medandGenricmed.getLab()))
		{
			System.out.println("labUnitDetails=new String();:::::"+unitDet.getLab()+"::"+medandGenricmed.getLab()+"::::"+unitDet.getLabUnit());
		
			medandGenricmed.setLabUnit(unitDet.getLabUnit());
		 System.out.println("labUnitDetails:::::"+medandGenricmed.getLabUnit());
		}
	}
	//Get Medicine name and patientMedicationData and patientMedicationDatahistory
	 String split[]=medandGenricmed.getMedicinename().split("\\+");
	 String medicinename=split[0];
	 medandGenricmed.setMedicinenameforgraph(medicinename);
	double drugid=Double.parseDouble(split[1]);
	logger.info(">>>drug_id_split"+drugid);
	
	
	 medandGenricmedListForDisplayList=labService. fetchGenericListforDisplay(new ContextUtil().getPatientId(),medicinename,timePeriod);
	
	 List<PatientMedicationData> patientmedicationdatalist=labService.fetchpatientmedicationlist(new ContextUtil().getPatientId(),medicinename,timePeriod);
	
	 
	 List<PharmacyDetail> pharmcydetail=new ArrayList<PharmacyDetail>();
	 
	 pharmcydetail= labService.findPharmacyDetailHistoryListAccordingToParticularDrugId(drugid, new ContextUtil().getPatientId(),timePeriod);
	 
	 
	 System.out.print("<<<<<<<<<<<<<<< phphramcy size"+pharmcydetail.size());
	 for(PharmacyDetail phardet:pharmcydetail)
	 {
		System.out.println("dosage information>>>>"+phardet.getDosage());
		LabLineBarChart labLineBarChart=new LabLineBarChart();
		if(phardet.getDosage()!=null && StringUtils.isNotBlank(phardet.getDosage()))
		{
		
		 Double strength=Double.parseDouble((phardet.getDosage().substring(0, phardet.getDosage().indexOf(" "))));
		
		 
		 
		   DecimalFormat df = new DecimalFormat("0.00");
		   df.setMaximumFractionDigits(2);
		   String tidstr=df.format(strength*1);
		  
		   
		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< pharmacy strength"+tidstr);
	   labLineBarChart.setStrength(tidstr);
	   labLineBarChart.setStartDate(phardet.getDateOfPurchase());
	   medBarList.add(labLineBarChart);
		}
	 }
	 List<SigCode> sigCodeList=new ArrayList<SigCode>();
	 sigCodeList =labService.fetchsigcodelist();
	 logger.info("sigcode list size"+sigCodeList.size());
		for(PatientMedicationHistory value:medandGenricmedListForDisplayList)
		{
			
			LabLineBarChart labLineBarChart=new LabLineBarChart();
          if(value.getStrength()!=null && StringUtils.isNotBlank(value.getStrength()))
          		{
       	   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+value.getStrength());
				for(SigCode sig1:sigCodeList)
				{
       	   if(value.getDirection().equalsIgnoreCase(sig1.getSigCode()))
       	   { 
       		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code value"+sig1.getSigCode());
       		
       		   System.out.println("<<<outside if>>>>>"+value.getStrength());
       		   Double strength=Double.parseDouble((value.getStrength().substring(0, value.getStrength().indexOf(" "))));
       		 
       		  
       		   DecimalFormat df = new DecimalFormat("0.00");
       		   df.setMaximumFractionDigits(2);
       		   String tidstr=df.format(strength*sig1.getSigCodeValue());
       		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< pharmacy strength"+tidstr);
       		  
       		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TID"+tidstr);
       	   labLineBarChart.setStrength(tidstr);
       	   labLineBarChart.setStartDate(value.getStartDate());
       	   labLineBarChart.setDirection(sig1.getSigCode());
       	   labLineBarChart.setDose(strength);
       	   labLineBarChart.setDirectionvalue(sig1.getSigCodeValue());
       	   medBarList.add(labLineBarChart);
       	   logger.info("med bar size 222>>>");
       	   }
       	 else if(value.getDirection()==null || StringUtils.isBlank(value.getDirection()))
       	   {
       		 System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code for blank field in value"+sig1.getSigCode());
       		
     		   Double strength=Double.parseDouble((value.getStrength().substring(0, value.getStrength().indexOf(" "))));
     		 
     		 
     		 
     		  
     		  DecimalFormat df = new DecimalFormat("0.00");
  		   df.setMaximumFractionDigits(2);
  		   String tidstr=df.format(strength*1);
     		  
     		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TID"+tidstr);
     	   labLineBarChart.setStrength(tidstr);
     	   labLineBarChart.setStartDate(value.getStartDate());
     	 labLineBarChart.setDirection(value.getDirection());
 	   labLineBarChart.setDose(strength);
 	   labLineBarChart.setDirectionvalue(1);
     	   medBarList.add(labLineBarChart); 
       		logger.info("med bar size 222>>>"+medBarList.size());
       	   }
       	  
          		}
          		}
			
	
		
		}
		
		for(PatientMedicationData value:patientmedicationdatalist)
		{
			
			LabLineBarChart labLineBarChart=new LabLineBarChart();
          if(value.getStrengths()!=null && StringUtils.isNotBlank(value.getStrengths()))
          		{
       	   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< for medication data"+value.getStrengths());
				for(SigCode sig1:sigCodeList)
				{
       	   if(value.getDirections().equalsIgnoreCase(sig1.getSigCode()))
       	   { 
       		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code value"+sig1.getSigCode());
       		
       		   Double strength=Double.parseDouble((value.getStrengths().substring(0, value.getStrengths().indexOf(" "))));
       		  
       		   
       		   DecimalFormat df = new DecimalFormat("0.00");
       		   df.setMaximumFractionDigits(2);
       		   String tidstr=df.format(strength*sig1.getSigCodeValue());
       		   System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< TID"+tidstr);
       	   labLineBarChart.setStrength(tidstr);
       	   labLineBarChart.setStartDate(value.getStartDate());
       	   labLineBarChart.setDirection(sig1.getSigCode());
       	   labLineBarChart.setDose(strength);
       	   labLineBarChart.setDirectionvalue(sig1.getSigCodeValue());
       	   medBarList.add(labLineBarChart);
       	   logger.info("med bar size 222>>>");
       	   }
       	 else if(value.getDirections()==null || StringUtils.isBlank(value.getDirections()))
       	   {
       		 System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< sig code for blank field in value for medication data"+sig1.getSigCode());
       		
     		   Double strength=Double.parseDouble((value.getStrengths().substring(0, value.getStrengths().indexOf(" "))));
     		
     		  
     		 DecimalFormat df = new DecimalFormat("0.00");
 		   df.setMaximumFractionDigits(2);
 		   String tidstr=df.format(strength*1);
     		   
     		  
     		  
     	   labLineBarChart.setStrength(tidstr);
     	   labLineBarChart.setStartDate(value.getStartDate());
     	   labLineBarChart.setDirection(value.getDirections());
 	       labLineBarChart.setDose(strength);
 	       labLineBarChart.setDirectionvalue(1);
     	   medBarList.add(labLineBarChart); 
       		logger.info("med bar size 222>>>"+medBarList.size());
       	   }
       	 
          		}
          		}
			
		
		}
	
		logger.info("method start before sorting:::"+medBarList.size());
		for(LabLineBarChart lab11:medBarList)
		{
			
			lab11.setLongStartDate(lab11.getStartDate().getTime());
			System.out.println(lab11.getLongStartDate()+":11::::date::::"+lab11.getStartDate()+"result:::"+lab11.getResult());
		
			
		}
		
	
		
		Collections.sort(medBarList);
		if(medBarList.size()>1 || medBarList.size()==1)
		{
		LabLineBarChart labLineBarChart=new LabLineBarChart();
		
		LabLineBarChart labLineBarChartObj=medBarList.get(medBarList.size() - 1);
		
		 labLineBarChart.setStrength(labLineBarChartObj.getStrength());
		 labLineBarChart.setStartDate(new DateUtil().getTodayDate());
		 labLineBarChart.setLongStartDate(new DateUtil().getTodayDate().getTime());
		 labLineBarChart.setDirection(labLineBarChartObj.getDirection());
		 labLineBarChart.setDirectionvalue(labLineBarChartObj.getDirectionvalue());
		 labLineBarChart.setDose(labLineBarChartObj.getDose());
 	     medBarList.add(labLineBarChart); 
		}
		medicineDetailForGraphList=medBarList;
		
		
		
	
		hepatitisCMedLabChartMap.put("Lab", hepCLabChartList);
		hepatitisCMedLabChartMap.put("Dose", medBarList);
	 RequestContext context = RequestContext.getCurrentInstance();
	 context.addCallbackParam("jsonDataSet", new Gson().toJson(hepatitisCMedLabChartMap));
	
	System.out.println("new Gson().toJson(psychoPharmLabChartList)::::; "+new Gson().toJson(hepatitisCMedLabChartMap));
	return new Gson().toJson(hepatitisCMedLabChartMap);
	
}

/**
 * @return the hepatitisCMedLabChartMap
 */
 public Map<String, List<LabLineBarChart>> getHepatitisCMedLabChartMap() {
	 if(hepatitisCMedLabChartMap==null){
		 hepatitisCMedLabChartMap=new HashMap<String, List<LabLineBarChart>>();
	 }
	return hepatitisCMedLabChartMap;
 }

 /**
   * @param hepatitisCMedLabChartMap the hepatitisCMedLabChartMap to set
  */
   public void setHepatitisCMedLabChartMap(
		 Map<String, List<LabLineBarChart>> hepatitisCMedLabChartMap) {
	 this.hepatitisCMedLabChartMap = hepatitisCMedLabChartMap;
 }
  public void openLabHtmlPage(){
	  Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	  String medRecordId=params.get("htmlMessage");
	  System.out.println("medRecordId:::::::::"+medRecordId);
	 labHtmlMessage=new String();
	  patientLabResult=labService.getEmedonLabResult(medRecordId);
	  setLabHtmlMessage(getPatientLabResult().getHtmlMessage());
	  /*labHtmlMessage=oruPatientLabResult.getHtmlMessage();*/
	 System.out.println("labhtml Message"+patientLabResult.getHtmlMessage());
	  
  }

/**
 * @return the labHtmlMessage
 */
public String getLabHtmlMessage() {
	
	return labHtmlMessage;
}

/**
 * @param labHtmlMessage the labHtmlMessage to set
 */
public void setLabHtmlMessage(String labHtmlMessage) {
	this.labHtmlMessage = labHtmlMessage;
}

/**
 * @return the patientLabResult
 */
public ORUPatientLabResult getPatientLabResult() {
	if(patientLabResult==null){
		patientLabResult=new ORUPatientLabResult();
	}
	return patientLabResult;
}

/**
 * @param patientLabResult the patientLabResult to set
 */
public void setPatientLabResult(ORUPatientLabResult patientLabResult) {
	this.patientLabResult = patientLabResult;
}

 /**
  * Used in MasterDataManagment.xhtml
  * Get Data From LabOrderData API 
  * <p>
  * 
  */
 public void getDataFromLabOrderDataAPI(){
    
	 System.out.println("Lab Order Data API METhod Started "+getReportDate());
	 SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
	 String strReportDate=format.format(getReportDate());
	 FacesContext context=FacesContext.getCurrentInstance();
	 UserManageBean userManageBean=(UserManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"userManageBean");
	
	 ArrayList<EmdeonLabOrders> emdeonLabOrdersList =labDetailWsBean.getDataFromGetLabOrderHL7XmlResult(userManageBean.getProviderLocation(),strReportDate);
	 
	 labService.saveEmdeonLabOrderData(emdeonLabOrdersList);
	 
	
	
 }

	
}
