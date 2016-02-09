package com.clinakos.viewController.bean;


import static com.clinakos.common.util.ClinakosConstant.MAINTAINENCE_STAGE;
import static com.clinakos.common.util.ClinakosConstant.SELECTED_PATIENT_SPLIT_SIZE;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.cxf.common.util.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.hibernate.HibernateException;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.MeterGaugeChartModel;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.xml.sax.SAXException;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.RolePrivillegeUtil;
import com.clinakos.data.model.core.AnticoagProviderLocation;
import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.FormularyDetail;
import com.clinakos.data.model.core.InsuranceCompanies;
import com.clinakos.data.model.core.PatientFormularyCompositeCopayInfo;
import com.clinakos.data.model.core.PatientFormularyCompositeDrugDetailInfo;
import com.clinakos.data.model.core.PatientPBMDrugHistoryResult;
import com.clinakos.data.model.core.PatientPBMEligibilityDetailData;
import com.clinakos.data.model.core.PatientPbmDrugHistoryDetail;
import com.clinakos.data.model.core.ProviderDetail;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.core.RoleSecurity;
import com.clinakos.data.model.patient.ChartModel;
import com.clinakos.data.model.patient.ClinicMaster;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.LOVType;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.PatientProviderClinic;
import com.clinakos.data.model.patient.PatientVital;
import com.clinakos.data.model.patient.PharmacyDetail;
import com.clinakos.data.model.patient.UserInsuranceDetails;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.ViewFunctionalDetails;
import com.clinakos.data.model.patient.WSDrug;
import com.clinakos.service.IPasswordEncoderGeneratorService;
import com.clinakos.service.IUserService;
import com.clinakos.viewController.webservicMangedBean.NcFormulary1WSBean;
import com.clinakos.viewController.webservicMangedBean.NcFormulary2WSBean;
import com.clinakos.viewController.webservicMangedBean.NcUpdate1WSBean;
import com.google.gson.Gson;

import https.secure_newcropaccounts_com.v7.webservices.PatientAllergyExtendedDetailV4;

//import net.sf.ehcache.search.aggregator.Count;






import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class UserManageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7783038885323203854L;
	
	public static final Logger logger = Logger.getLogger("UserManageBean.class");
	
	IPasswordEncoderGeneratorService PasswordEncoderService;
	private UserLoginDetail userLoginDetail=new UserLoginDetail();
	private PatientVital patientVital =new PatientVital();
	public String loggedUserName="";
	private InsuranceCompanies insuranceCompanies = new InsuranceCompanies();
	private String medicineNameForFormularySearch="";
	private boolean showFormularyMedicineData;
	private AuditInfo auditInfo = new AuditInfo();
	List<UserInsuranceDetails> UserInsuranceDetailsList = new ArrayList<UserInsuranceDetails>();
	private PatientProviderClinic patientProviderClinic = new PatientProviderClinic();
	List<String> patientProviderClinicList = new ArrayList<String>();
	IUserService userService;
	private List<InsuranceCompanies> insuranceCompaniesList=null;
	private List<FormularyDetail>formularyDetailList=null;
	private List<LOVType>lovTypeList=new ArrayList<LOVType>();
	List<PatientDiagnosesDetails> diagnosisList=new ArrayList<PatientDiagnosesDetails>();
	private List<ClinicMaster>clinicmasterList=null;

	private List<String>kidneyFunctionDetailList=new ArrayList<String>();
	private List<String>liverFunctionList=new ArrayList<String>();
	private List<ViewFunctionalDetails>viewFunctionDetailList=new ArrayList<ViewFunctionalDetails>();
	private List<Integer> numberOfPatientParticularProvider=null;
	private List<AnticoagProviderLocation> numberOfPatientforanticoagclinic=new ArrayList<AnticoagProviderLocation>();
	private List<AnticoagProviderLocation> numberOfPatientforanticoagclinicforintiationphase=new ArrayList<AnticoagProviderLocation>();
	private List<AnticoagProviderLocation> numberofpatientformedactionplan;
	private List<AnticoagProviderLocation> numberofpatientformedactionplanforinitiationphase;
	private List<AnticoagProviderLocation> numberofpatientformedactionplanforgraterrange;
	private List<AnticoagProviderLocation> numberofpatientformedactionplanforhigherendofpatient;
	private List<AnticoagProviderLocation> numberofpatientformedactionplanforhigherendofpatientforintiationphase=new ArrayList<AnticoagProviderLocation>();
	private List<AnticoagProviderLocation> numberofpatientformedactionplanforgraterrangeforintiationphase=new ArrayList<AnticoagProviderLocation>();
	private List<Integer> totalnumberofpatientinrlab=new ArrayList<Integer>();
	private List<AnticoagProviderLocation> totalnumberofpatientinrlabforinitiationphase=new ArrayList<AnticoagProviderLocation>();
	private CartesianChartModel insurancegraph=new CartesianChartModel(); 
	private PieChartModel  clinicPieChart=new 	PieChartModel();	
	private MeterGaugeChartModel meterGaugeModel = null;  
	private BigInteger noOfRedSymbolPatient;
	private BigInteger noOfOrangeSymbolPatient;
	private BigInteger noOfGreenSymbolPatient;
	private String averageMedicine="";	
	private String averageDiagnoses="";
	private String averagePhysicians="";
	private int pharmacogenomicsPercentage=0;
	private int averagePharmacyPerPatient=0;
	private String logedFirstName="";
	private String logedLastName="";
	private String logeMiddleName="";
	private String loggedUsertimeZone="";
	private MeterGaugeChartModel enbrelAdherenceChartModel = null;  
	private List<UserLoginDetail> batchPatientForReportList=null;
	private List<Integer> patientIdList; 
	private String totalNumberOfDrugHistoryRecordsPulled="";//for Pharma Dashboard
	private String numberOfRowsPerPatient="";//Pharma dashboard:Number of Rows Per Patient
	public String currentEnbrelPatientCount="";//pharma dashboard:Number of patients currently on enbrel from last 3 months
	
	public String levelVal;
	JSONArray ja = new JSONArray();
	JSONObject jo = new JSONObject();
	
	public String getLevelVal() {
		return levelVal;
	}


	public void setLevelVal(String levelVal) {
		this.levelVal = levelVal;
	}
	
	private List<PatientAllergyExtendedDetailV4> patientAllergyExtendedList=null;
	
	@Autowired
    NcFormulary1WSBean formulary1WsBean; 
	@Autowired
	NcUpdate1WSBean update1wsBean;
	RolePrivillegeUtil rolePrivillegeUtil= new RolePrivillegeUtil();
	private List<WSDrug> wsDrugList;
	private DataTable drugDoseComboDatatable;
	private String insuranceCompanyForFormularySearch="";
	private List<InsuranceCompanies> insuranceDetailList;
	private DataTable insuranceDetailDatatable;
	private ProviderLocation providerLocation =new ProviderLocation();
	private RoleSecurity roleSecurity = new RoleSecurity();
	Map<String, List<FormularyDetail>> formularyAlternativeDrugInfoMap=new HashMap<String, List<FormularyDetail>>();
	List<FormularyDetail> alternativeFormularyDrugList;
	private boolean showAlternativeFormularyMedicine;
    private List<ClinicMaster>clinicMasterList=null;
	
	private List<ClinicMaster> clinicMasterpiechart;
	private String newCropPatientDetails;
	
	private List<InsuranceCompanies> insuranceDetailListBar=null;
    List<InsuranceCompanies> insuranceDetailListBarList;
    List<InsuranceCompanies> insuranceDetailListBarFull = null;
	
    private String clinicBarChart;
    
    private List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList=null;
    @Autowired
    NcFormulary2WSBean formulary2WsBean;
    
     private ProviderDetail providerDetail;
     
     private int timePeriod=0;
     List<Object> complianceMeterDetailList=new ArrayList<Object>();
    
     
     /*private List<AnticoagProviderLocation> retrivePatientByAnticoagCategoryList=new ArrayList<AnticoagProviderLocation>();*/
     private List<UserLoginDetail> retrivePatientByAnticoagCategoryList=new ArrayList<UserLoginDetail>();
     
     List<Integer> ClinicAndProviderIdList;
     
     //Added By Anjani For Batch Data Pull Process
     private List<UserLoginDetail> patientDataWithPatientConsentForBatchDataPullProcessList=null;
     private UserLoginDetail[] selectedUserLoginDetail;
     private List<Date> patientBatchDateList=null;
     private List<Integer> patientBatchNumberDataList=null;
     private String patientBatchDate ;
     private char patientConsent;
     private Long totalNoOfPatientPulled;
     private Long totalNoOfPatientMedicalRecordPulled;
     private int noOfPatientNotGetRecord=0;
     private int progressBatchProcess=0;
     private int totalNoOfSelectedPatient=0;
     private int completedPerc=0;
//used on batchPatientMedicationLists.xhtml, for selecting range of patient ID, start ID     
     private int fromPatientIdForReport=0;
//used on batchPatientMedicationLists.xhtml, for selecting range of patient ID, end ID 
 	 private int toPatientIdForReport=0;
 	 private Long patientHaveMedications;
     
     
     
     private int batchDataPullTimeDurationCounter=0;
     private String batchDataPullTimeDuration;
//used on batchPatientMedicationLists.xhtml, to save the selected patients details 
     private UserLoginDetail[] selectedUserDetailForReport;
     private List<PatientMedicationData> currentMedicationListForBatchReport=null;
     private List<UserLoginDetail> patientDetailListForBatchReport=null;
     JasperPrint jasperPrint=null;
     private JasperPrint patientDetailJasperPrint=null;
     private JasperPrint patientDetailWithLabJasperPrint=null;
     
     
     
     
	public void init(){
	logger.info("Usermanagebean started:::::::::");
	getLoggedUsertimeZone();
	
	
	
	
	
	
	//createMeterGaugeModel();
	
	}
	
	

	public UserLoginDetail getUserLoginDetail() {
		return userLoginDetail;
	}

	public void setUserLoginDetail(UserLoginDetail userLoginDetail) {
		this.userLoginDetail = userLoginDetail;
	}
	
	
	// FOR HEADER USER NAME..............
	public String getLoggedUserName() {
		if(loggedUserName.length()==0)
		{
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			loggedUserName = user.getUsername();
		}
		return loggedUserName;
		
	}

	public void setUserId(String userId) {
		this.loggedUserName = userId;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public List<FormularyDetail> getFormularyDetailList() {
		if(formularyDetailList==null){
			formularyDetailList=new ArrayList<FormularyDetail>();
		}
		return formularyDetailList;
	}
	public void setFormularyDetailList(List<FormularyDetail> formularyDetailList) {
		this.formularyDetailList = formularyDetailList;
	}
	public boolean isShowFormularyMedicineData() {
		return showFormularyMedicineData;
	}

	public void setShowFormularyMedicineData(boolean showFormularyMedicineData) {
		this.showFormularyMedicineData = showFormularyMedicineData;
	}

	public PatientProviderClinic getPatientProviderClinic() {
		return patientProviderClinic;
	}

	public void setPatientProviderClinic(PatientProviderClinic patientProviderClinic) {
		this.patientProviderClinic = patientProviderClinic;
	}

	public List<String> getPatientProviderClinicList() {
		return patientProviderClinicList;
	}

	public void setPatientProviderClinicList(
			List<String> patientProviderClinicList) {
		this.patientProviderClinicList = patientProviderClinicList;
	}
	public InsuranceCompanies getInsuranceCompanies() {
		return insuranceCompanies;
	}

	public void setInsuranceCompanies(InsuranceCompanies insuranceCompanies) {
		this.insuranceCompanies = insuranceCompanies;
	}

	public List<String> getKidneyFunctionDetailList() {
		return kidneyFunctionDetailList;
	}

	public void setKidneyFunctionDetailList(List<String> kidneyFunctionDetailList) {
		this.kidneyFunctionDetailList = kidneyFunctionDetailList;
	}

	public List<String> getLiverFunctionList() {
		return liverFunctionList;
	}

	public void setLiverFunctionList(List<String> liverFunctionList) {
		this.liverFunctionList = liverFunctionList;
	}

	public List<UserInsuranceDetails> getUserInsuranceDetailsList() {
		return UserInsuranceDetailsList;
	}

	public void setUserInsuranceDetailsList(
			List<UserInsuranceDetails> userInsuranceDetailsList) {
		UserInsuranceDetailsList = userInsuranceDetailsList;
	}

	public PatientVital getPatientVital() {
		return patientVital;
	}

	public void setPatientVital(PatientVital patientVital) {
		this.patientVital = patientVital;
	}

	public List<PatientDiagnosesDetails> getDiagnosisList() {
		return diagnosisList;
	}
	public void setDiagnosisList(List<PatientDiagnosesDetails> diagnosisList) {
		this.diagnosisList = diagnosisList;
	}
	public List<LOVType> getLovTypeList() {
		return lovTypeList;
	}

	public void setLovTypeList(List<LOVType> lovTypeList) {
		this.lovTypeList = lovTypeList;
	}
	
	public List<InsuranceCompanies> getInsuranceCompaniesList() {
		//int clinicProviderId, List<InsuranceCompanies> insuranceCompaniesList, List<Integer> numberOfPatientParticularProvider
		if(insuranceCompaniesList ==null){
			int count = 0;
			count++;
			insuranceCompaniesList=new ArrayList<InsuranceCompanies>();
		 insuranceCompaniesList= userService.insuranceCompaniesList();
		 System.out.println("listcountingInsu::: "+count);
		}
		return insuranceCompaniesList;
	
	}

	public void setInsuranceCompaniesList(List<InsuranceCompanies> insuranceCompaniesList) {
		this.insuranceCompaniesList = insuranceCompaniesList;
	}
	
	

		public AuditInfo getAuditInfo() {
			return auditInfo;
		}
		
		
		public void setAuditInfo(AuditInfo auditInfo) {
			this.auditInfo = auditInfo;
		}
		
		public List<ViewFunctionalDetails> getViewFunctionDetailList() {
			return viewFunctionDetailList;
		}
		
		public void setViewFunctionDetailList(
				List<ViewFunctionalDetails> viewFunctionDetailList) {
			this.viewFunctionDetailList = viewFunctionDetailList;
		}
		/**
		 * Used Method on doctorProfile.jsf
		 * Showing total no Of Patient 
		 * in that Provider 
		 * @return noOfPatientInProvider
		 */
		public List<Integer> getNumberOfPatientParticularProvider() {
			try {
				if(numberOfPatientParticularProvider ==null){
					numberOfPatientParticularProvider = new ArrayList<Integer>();
					System.out.println("!!!! optimization 22 in userManageBean starts:::"+new Date());
					numberOfPatientParticularProvider=userService.findnumberOfPatientParticularProvider(ClinicAndProviderIdList.get(2));
				}
				System.out.println("!!!! optimization 22 in userManageBean ends:::"+new Date());
			} 
			catch (NullPointerException ne){
				numberOfPatientParticularProvider=new ArrayList<Integer>();
				logger.error("nullpointer in numberOfPatientParticularProvider::::", ne);
			}
			catch (Exception e) {
				logger.error("exception in numberOfPatientParticularProvider::::", e);
			}
			
			return numberOfPatientParticularProvider;
		}
		
		public void setNumberOfPatientParticularProvider(
				List<Integer> numberOfPatientParticularProvider) {
			this.numberOfPatientParticularProvider = numberOfPatientParticularProvider;
		}
		
		public CartesianChartModel getInsurancegraph() {
			return insurancegraph;
		}

		public void setInsurancegraph(CartesianChartModel insurancegraph) {
			this.insurancegraph = insurancegraph;
		}

		public PieChartModel getClinicPieChart() {
			return clinicPieChart;
		}

		public void setClinicPieChart(PieChartModel clinicPieChart) {
			this.clinicPieChart = clinicPieChart;
		}

	
		

	public BigInteger getNoOfRedSymbolPatient() {
			return noOfRedSymbolPatient;
		}


		public void setNoOfRedSymbolPatient(BigInteger noOfRedSymbolPatient) {
			this.noOfRedSymbolPatient = noOfRedSymbolPatient;
		}


		public BigInteger getNoOfOrangeSymbolPatient() {
			return noOfOrangeSymbolPatient;
		}


		public void setNoOfOrangeSymbolPatient(BigInteger noOfOrangeSymbolPatient) {
			this.noOfOrangeSymbolPatient = noOfOrangeSymbolPatient;
		}


	public List<ClinicMaster>  getClinicmasterList() {
		if(clinicmasterList==null)
		{
			clinicmasterList=new ArrayList<ClinicMaster>();
			try{
			clinicmasterList=userService.findClinicMasterList();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return clinicmasterList;
		}


		public void setClinicmasterList(List<ClinicMaster> clinicmasterList) {
			this.clinicmasterList = clinicmasterList;
		}


	/**
	 * START OF CODE FOR MEDICIAN SEARCH
	 * @throws Exception 
	 * 
	 */
	public void medicineSearchFormulary() throws Exception {
		  logger.info("Startted medicineSearchFormulary method"+insuranceCompanies.getId()+"::med name::"+medicineNameForFormularySearch);
		  //insuranceCompanies.id
		  setShowAlternativeFormularyMedicine(false);
		  setShowFormularyMedicineData(false);
		  String drugDoseMedc="";
		  if(!(getDrugDoseComboDatatable().getRowCount()==0)){
			  WSDrug selectedDrug=(WSDrug) getDrugDoseComboDatatable().getRowData();
			  drugDoseMedc=selectedDrug.getDrugDetail(); 
		  }
		formularyDetailList=new ArrayList<FormularyDetail>();
		alternativeFormularyDrugList=new ArrayList<FormularyDetail>();
		
		  if((!(insuranceCompanies.getId()==0)) && (!(drugDoseMedc.equals(""))))
			  /*formularyDetailList=userService.findFormularySearchDetail(insuranceCompanies.getId(),medicineNameForFormularySearch);*/ //Commented by Anjani
			  getProviderLocation();
			  formularyAlternativeDrugInfoMap=formulary1WsBean.getFormularyCoverage(insuranceCompanies.getId(),drugDoseMedc,providerLocation);
		  
		  for(Map.Entry entry:formularyAlternativeDrugInfoMap.entrySet() ){
				
				
				if(entry.getKey().equals("formularyDetails")){
					formularyDetailList.addAll((Collection<? extends FormularyDetail>)entry.getValue());
				}
				if(entry.getKey().equals("alternativeFormularyDrugList") && !(entry.getKey()==null) && !(entry.getValue()==null)){
					
					alternativeFormularyDrugList.addAll((Collection<? extends FormularyDetail>)entry.getValue());
					for(FormularyDetail foDetail:alternativeFormularyDrugList){
						System.out.println("Alternativ drug"+foDetail.getAlternateMedicine() +" Formulary Tier "+foDetail.getIsFormulary() +"Medicine Name "+foDetail.getMedicineName());
						setShowAlternativeFormularyMedicine(true);
					}
				}
		  }
		 
		  setShowFormularyMedicineData(true);
		 setMedicineNameForFormularySearch(drugDoseMedc);
		// insuranceCompanyForFormularySearch="";
		// insuranceCompanies.setId(0);
		  
		  System.out.println(":::::::::::::::::::::size of insurance company list"+insuranceCompaniesList.size()+"medicineNameForFormularySearch"+medicineNameForFormularySearch);
		
		}
	
	/*for idle screen message added by umesh*/
	
	public void idleListener() {
		/*FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
										"Your session has timed out", "You have been idle for at least 30 min,Now You will be redirected to login page!"));*/
		FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.invalidate();
		//invalidate session
	}

	 /* public void activeListener() throws InterruptedException, IOException {*/
		 /* FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Welcome Back", "You will be redirected to login page!"));*/
         /* Thread.sleep(2000);
          FacesContext fc = FacesContext.getCurrentInstance();
          ExternalContext ec = fc.getExternalContext();
          HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
          session.invalidate();
          ec.redirect("/clinakos/page/core/login.jsf"); */      
         
         
/*}*/
	
	public List<Integer> getClinicAndProviderIdList() {
		int logedUserId=new ContextUtil().getLoginId();
		logger.info("clinicAndProviderIdList calling:::::::::::::::");
		if(ClinicAndProviderIdList == null){
			ClinicAndProviderIdList= new ArrayList<Integer>();
			ClinicAndProviderIdList=userService.findClinicAndProviderId(logedUserId);
		}
		return ClinicAndProviderIdList;
	}


	public void setClinicAndProviderIdList(List<Integer> clinicAndProviderIdList) {
		ClinicAndProviderIdList = clinicAndProviderIdList;
	}


	

			/*
			 * Get Data for  doctorProfile.jsf 
			 * @author Gopal Krishan jha
			 * 
			 */

	
	public void findDoctorProfileDetail() {
		
		logger.info("findDoctorProfileDetail method in usermanage bean start");
			//insuranceCompaniesList= userService.insuranceCompaniesList();
			//find the provider id and clinic id of particular doctor...
			//int logedUserId=new ContextUtil().getLoginId();
			//System.out.println("::::::::doctor id:::"+logedUserId);
			//ClinicAndProviderIdList= userService.findClinicAndProviderId(logedUserId);
			//getClinicAndProviderIdList();
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
			session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
			session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
			
			//finding the total number of patient 
			 //List<Integer>totalNumberOFPatientBelongParticularProvider=new ArrayList<Integer>();
			//totalNumberOFPatientBelongParticularProvider=userService.findTotalNumb56erOFPatientBelongParticularProvider(ClinicAndProviderIdList.get(2));
			//numberOfPatientParticularProvider=userService.findnumberOfPatientParticularProvider(ClinicAndProviderIdList.get(2));
			System.out.println("::::::::::numberOfPatientParticularProvider:::::"+numberOfPatientParticularProvider.size());
			
			if(!(numberOfPatientParticularProvider.size()==0))
			{
				logger.info("<<<<<<<<<<inside when numberOfPatientParticularProvider is greater than zero>>>>>>>>>>>>>>>");
				getClinicmasterList();
						//finding the insurance graph
						//insurancegraph=userService.findInsuranceGraphDetail(ClinicAndProviderIdList.get(0),numberOfPatientParticularProvider,insuranceCompaniesList);
						//List<Object> userInsuranceDetailsList=userService.findInsuranceGraphDetail(ClinicAndProviderIdList.get(0),numberOfPatientParticularProvider,insuranceCompaniesList);
							/*insurancegraph=findUserInsuranceDetail(ClinicAndProviderIdList.get(0),insuranceCompaniesList,numberOfPatientParticularProvider);*/
						//find bar chart
							/*clinicBarChart=getInsuranceDetailListBar();*/
							
						//finding virtual clinic pie chart...
						
						//clinicPieChart=userService.findClinicPieChart(ClinicAndProviderIdList.get(2));
					clinicPieChart=findClinicPieCharDetail(ClinicAndProviderIdList.get(2));
						
					System.out.println("value in clinic pie chart:::::::::::::"+clinicPieChart.getData());
					//find clinicpiechart
					//clinicMasterpiechart=getClinicMasterList();
				
						//find compliance meter 
						//meterGaugeModel = userService.findAllComplianceDetails ();-
						//meterGaugeModel = findComplianceMeterDetail();
						//System.out.println("Value of meter:"+meterGaugeModel.getValue());
						//find average medicine per patient
					//for testing purpose
					/*numberOfPatientParticularProvider=new ArrayList<Integer>();*/
					System.out.println("!!!! optimization 23 in userManageBean starts:::"+new Date());
					averageMedicine=String.valueOf(userService.findaverageMedicinePerPatient(numberOfPatientParticularProvider));
					System.out.println(averageMedicine+"!!!! optimization 23 in userManageBean ends:::"+new Date());
					
					System.out.println("!!!! optimization 24 in userManageBean starts:::"+new Date());
					averageDiagnoses=String.valueOf(userService.findaverageDiagnoses(numberOfPatientParticularProvider));
					System.out.println(averageDiagnoses+"!!!! optimization 24 in userManageBean ends:::"+new Date());
					
					System.out.println("!!!! optimization 25 in userManageBean starts:::"+new Date());
					averagePhysicians=String.valueOf(userService.findaveragPhysicians(numberOfPatientParticularProvider.size(),ClinicAndProviderIdList.get(2)));
					System.out.println(averagePhysicians+"!!!! optimization 25 in userManageBean ends:::"+new Date());
					
					System.out.println("!!!! optimization 26 in userManageBean starts:::"+new Date());
					pharmacogenomicsPercentage=userService.findPharmacogenomicsPercentage(numberOfPatientParticularProvider);
					System.out.println(pharmacogenomicsPercentage+"!!!! optimization 26 in userManageBean ends:::"+new Date());
					
					System.out.println("!!!! optimization 27 in userManageBean starts:::"+new Date());
					averagePharmacyPerPatient=userService.findAveragePharmacyPerPatient(numberOfPatientParticularProvider);
						
						
						//find  the total no of red compliance symbol and  orange symbol...
					/*	List<BigInteger>noOfPatientAccoringTocompliance=userService.findPatientMedicationDataListAccordingToProvider(ClinicAndProviderIdList.get(2));
						 
						logger.info("noOfPatientAccoringTocompliance:::::::size::"+noOfPatientAccoringTocompliance.size());
							logger.info("red compliance :::"+noOfPatientAccoringTocompliance.get(0));
							noOfRedSymbolPatient =  noOfPatientAccoringTocompliance.get(0);
							
							logger.info("amber compliance :::"+noOfPatientAccoringTocompliance.get(1));
							noOfOrangeSymbolPatient = noOfPatientAccoringTocompliance.get(1);
							
							logger.info("green compliance :::"+noOfPatientAccoringTocompliance.get(2));
							noOfGreenSymbolPatient = noOfPatientAccoringTocompliance.get(2);*/
						
						//Set<Integer> patientListForCompliancepercentage60 = new HashSet<Integer>();
						////Set<Integer> patientListForCompliancepercentage80 = new HashSet<Integer>();
						//<Integer> patientListForCompliancepercentage100 = new HashSet<Integer>();
						/*for (Integer pmd : patientMedicationDataList) {
							logger.info("inside findDoctorProfileDetail in usermanageBean====="+pmd.getId());
								if(pmd.getCompliancePercentage()<60){
									logger.info("inside if condition findPatientMedicationDataListAccordingToProvider in usermanageBean====="+pmd.getId()+pmd.getPatientId()
											+pmd.getCompliancePercentage());
									patientListForCompliancepercentage60.add(pmd.getPatientId());
								}	
								else if(pmd.getCompliancePercentage()>=60 && pmd.getCompliancePercentage()<80){
									logger.info("inside else if 1 findPatientMedicationDataListAccordingToProvider in usermanageBean====="+pmd.getId()+pmd.getPatientId()
												+pmd.getCompliancePercentage());
									patientListForCompliancepercentage80.add(pmd.getPatientId());
								}	
								else if(pmd.getCompliancePercentage()>=80 && pmd.getCompliancePercentage()<=100){
									logger.info("inside else if 2 findPatientMedicationDataListAccordingToProvider in usermanageBean====="+pmd.getId()+pmd.getPatientId()
											+pmd.getCompliancePercentage());
									patientListForCompliancepercentage100.add(pmd.getPatientId());
								}
								
						}*/
					/*	for (Integer s1 : patientListForCompliancepercentage60) {
							noOfRedSymbolPatient++;
						    System.out.println("========patientListForCompliancepercentage60=========="+s1);
						}
						for (Integer s2 : patientListForCompliancepercentage80) {
							noOfOrangeSymbolPatient++;
						    System.out.println("==========patientListForCompliancepercentage80========"+s2);
						}
						for (Integer s3 : patientListForCompliancepercentage100) {
							noOfGreenSymbolPatient++;
						    System.out.println("==========patientListForCompliancepercentage100==========="+s3);
						}*/
						//System.out.println("patientMedicationDataList::::"+patientMedicationDataList.size());
						//System.out.println("noOfRedSymbolPatient::::"+noOfRedSymbolPatient+"noOfOrangeSymbolPatient:::"+noOfOrangeSymbolPatient+"noOfGreenSymbolPatient::"+noOfGreenSymbolPatient);
			}else{
				logger.info("<<<<<<<<<<inside when numberOfPatientParticularProvider is zero>>>>>>>>>>>>>>>");
				averageMedicine=String.valueOf("0");
				averageDiagnoses=String.valueOf("0");
				averagePhysicians=String.valueOf("0");
				pharmacogenomicsPercentage=0;
				averagePharmacyPerPatient=0;
			}
			
		}
	
	
	/**
	 * This method is used to show headers count in pharmacy dashobaord
	 * Used for Pharma Role for background loading after user has logged In
	 * This method is called from dashboardManagageBean from pharmaDashboardChartLoading
	 * 
	 */
	
	public void loadDoctorProfileDetail(){
		RequestContext context=RequestContext.getCurrentInstance();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		try{
		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		
		System.out.println("::::::::::numberOfPatientParticularProvider:::::"+numberOfPatientParticularProvider.size());
		
		if(!(numberOfPatientParticularProvider.size()==0))
		{
			logger.info("<<<<<<<<<<inside when numberOfPatientParticularProvider is greater than zero>>>>>>>>>>>>>>>");
			getClinicmasterList();
			System.out.println("!!!! optimization 28 in userManageBean starts:::"+new Date());
			averageMedicine=String.valueOf(userService.findaverageMedicinePerPatient(numberOfPatientParticularProvider));
			System.out.println("!!!! optimization 28 in userManageBean starts:::"+new Date());
			
			System.out.println("!!!! optimization 29 in userManageBean starts:::"+new Date());
			averagePhysicians=String.valueOf(userService.findaveragPhysicians(numberOfPatientParticularProvider.size(),ClinicAndProviderIdList.get(2)));
			System.out.println("!!!! optimization 29 in userManageBean starts:::"+new Date());
			
			List<Integer>pharmaDashboardAnalytics=userService.calTotalNumberOfDrugHistory(ClinicAndProviderIdList.get(2));
			if(pharmaDashboardAnalytics!=null){
			totalNumberOfDrugHistoryRecordsPulled=String.valueOf(pharmaDashboardAnalytics.get(0));
		
		//	if(pharmaDashboardAnalytics.get(1)>0){//pharmaDashboardAnalytics.get(1) 
				numberOfRowsPerPatient= String.valueOf(pharmaDashboardAnalytics.get(0)/numberOfPatientParticularProvider.size());
		//	}
			}
		
		}else{
			logger.info("<<<<<<<<<<inside when numberOfPatientParticularProvider is zero>>>>>>>>>>>>>>>");
			averageMedicine=String.valueOf("0");
			averagePhysicians=String.valueOf("0");
			totalNumberOfDrugHistoryRecordsPulled=String.valueOf("0");
			numberOfRowsPerPatient=String.valueOf("0");
		}
		
	
		Integer noOfPatientsCurrentlyOnEnbrel=userService.getPatientsCurrentlyOnEnbrel(ClinicAndProviderIdList.get(2));
		currentEnbrelPatientCount=String.valueOf(noOfPatientsCurrentlyOnEnbrel);
		logger.info("currentEnbrelPatientCount by using property "+currentEnbrelPatientCount+"by getter "+getCurrentEnbrelPatientCount());
		}catch(Exception e){
			e.printStackTrace();
			averageMedicine=String.valueOf("0");
			averagePhysicians=String.valueOf("0");
			totalNumberOfDrugHistoryRecordsPulled=String.valueOf("0");
			numberOfRowsPerPatient=String.valueOf("0");
			currentEnbrelPatientCount=String.valueOf("0");
		}
		context.addCallbackParam("currentEnbrelPatientCount", currentEnbrelPatientCount);
	}
	
	public List<Object> getComplianceMeterDetailList() {
		return complianceMeterDetailList;
	}


	public void setComplianceMeterDetailList(List<Object> complianceMeterDetailList) {
		this.complianceMeterDetailList = complianceMeterDetailList;
	}


	/*
	 * find compliance meter in doctor profile page...
	 * @author:Gopal Krishna JHA..
	 * 
	 * 
	 */
	public MeterGaugeChartModel findComplianceMeterDetail() {
		logger.info("!!!!!Optimization findComplianceMeterDetail called in usermanage bean:::::"+ new Date());
		complianceMeterDetailList=userService.findAllComplianceDetails();
		getClinicAndProviderIdList();
		List<BigInteger>noOfPatientAccoringTocompliance=userService.findPatientMedicationDataListAccordingToProvider(ClinicAndProviderIdList.get(2));
		 
		logger.info("noOfPatientAccoringTocompliance:::::::size::"+noOfPatientAccoringTocompliance.size());
			logger.info("red compliance :::"+noOfPatientAccoringTocompliance.get(0));
			noOfRedSymbolPatient =  noOfPatientAccoringTocompliance.get(0); // Get Complince Data for Red Symbol Patient 
			
			logger.info("amber compliance :::"+noOfPatientAccoringTocompliance.get(1));
			noOfOrangeSymbolPatient = noOfPatientAccoringTocompliance.get(1); // get Compilance Data For Orange Symbol Patient 
			
			logger.info("green compliance :::"+noOfPatientAccoringTocompliance.get(2));
			noOfGreenSymbolPatient = noOfPatientAccoringTocompliance.get(2); //Get Complince data for  Green Symbol Patient 
		
		MeterGaugeChartModel meterModel = new MeterGaugeChartModel();		
		if(!complianceMeterDetailList.isEmpty())
		{
			int noOfRow = 0;
			double totalAverage = 0;
			for(Object obj: complianceMeterDetailList){
			  Object[] row = (Object[]) obj;
			  noOfRow++;
			  if((Double)row[0]!=null){
				  if((Double)row[0]>100){
					  totalAverage=totalAverage+100;
				  }else{
				  totalAverage=totalAverage+(Double)row[0];
				  }
			  }
			 
			//System.out.println(":::::::::::::::::::::::::::::::totalAverage"+totalAverage+"::::::::noOfRow:::+noOfRow"+noOfRow);
			  //noOfpharmcay=noOfpharmcay+(Integer)row[1];
			//System.out.println("complianceMeterDetailList:::::::::"+complianceMeterDetailList.size());
			  int value=0;
			  value=(int) (totalAverage/complianceMeterDetailList.size());
			  //System.out.println(":::::::::::::::::::::::::::::::totalAverage"+totalAverage+"::::::::noOfRow:::+noOfRow"+noOfRow+":::value::"+value);
			  List<Number> intervals = new ArrayList<Number>(){
				  {  
		            add(60);  
		            add(80);  
		            add(100);   
		        }};  
			  
			  meterModel.setValue(value);
			  meterModel.setIntervals(intervals);
			 }
		}
		logger.info("!!!!!Optimization findComplianceMeterDetail ENDSSSSSSSSSSSSSS in usermanage bean:::::"+ new Date());
		return meterModel;
	}


	/*
	 * find clinic Pie Chart Detail According to particular provider..
	 * @author:Gopal Krishna jha
	 */
	public PieChartModel findClinicPieCharDetail(Integer providerId) {
		List<Object> clinicPieChartList=userService.findClinicPieChart(providerId);
		//List<Object> clinicPieChartList=userService.findClinicPieChart(providerId);
		Gson gson = new Gson();
		
		PieChartModel pieModel = new PieChartModel(); 
		for(Object obj: clinicPieChartList){
			  Object[] row = (Object[]) obj;
			 
			  String clinicName=findClinicNameAccordingToId(clinicmasterList,(Integer)row[0]);
			  int value=0;
			  value=((Integer)row[0]*100)/clinicPieChartList.size();
			  pieModel.set(clinicName, value);
			  String json1 = gson.toJson(pieModel);
			  PieChartModel gotFromJson = gson.fromJson(json1, PieChartModel.class);
		
			
			  System.out.println("value in json"+json1+"gotFromJson value::::::::::"+gotFromJson.getData());
		}
		//return new Gson().toJson(pieModel);
				return pieModel;
			}


	/*
	 * find user insurance detail Graph..
	 * @author: Gopal Krishna jha...
	 */
	public CartesianChartModel findUserInsuranceDetail( int clinicProviderId, List<InsuranceCompanies> insuranceCompaniesList, List<Integer> numberOfPatientParticularProvider) {
		logger.info("!!!!!!!!!!!optimization findUserInsuranceDetail method in usermanagebean start:::::"+ new Date());
		List<Object> userInsuranceDetailsList=userService.findInsuranceGraphDetail(clinicProviderId,numberOfPatientParticularProvider,insuranceCompaniesList);
		ChartSeries insuranceChartdetail = new ChartSeries(); 
		logger.info("insurance size:::"+userInsuranceDetailsList.size());
		CartesianChartModel chart=new CartesianChartModel();  
		int count=0;
		for(Object obj: userInsuranceDetailsList){
			  Object[] row = (Object[]) obj;
			
			  String insuranceName=findInsurancenameAccordingToId(insuranceCompaniesList,(Integer)row[0]);
			  
			  
			  System.out.println("::::insuranceCompaniesList,(Integer)row[1]::"+(Long)row[1]);
			  Long value=(long) 0;
			  value=(Long)row[1];
			  System.out.println(":::::::::::::>>>>>insuranceName::"+insuranceName+"::value:::::"+value);
			  if(insuranceName.isEmpty())
			  {
				  break;
			  }
			  insuranceChartdetail.set(insuranceName,value);
			  //System.out.println("insurancegraph:::::::::::::"+userInsuranceDetailsList.size()+value);
			  
			  if(count>=4)
				{
					break;
				}
				count++;
			  
			}
		
		
			chart.addSeries(insuranceChartdetail);
			logger.info("!!!!!!!!!!!optimization findUserInsuranceDetail method in usermanagebean ENDSSSSSSSSSSSSSS:::::"+ new Date());
		return chart;
		
	}
	/*
	 * find Insurance  name according to clinic id ..
	 * @author : gopal krishna jha
	 */

	public String findInsurancenameAccordingToId(List<InsuranceCompanies> insuranceCompanyList, int row)
	{
		String insurancename="";
	
		for(InsuranceCompanies company:insuranceCompanyList)
		{
			if(row==company.getId())
			{
				insurancename=company.getCompanyName();
			
				//System.out.println("insuranceChartdetail:::"+company.getCompanyName());
			}
		}
		return insurancename;
	}
	
	
	/*
	 * find clinic name according to clinic id ..
	 * @author : gopal krishna jha
	 */

	private String findClinicNameAccordingToId(
			List<ClinicMaster> clinicmasterList, int clinicId) {
		String clinicName="";
		for(ClinicMaster clinicmaster:clinicmasterList)
		{
			if(clinicmaster.getId()==clinicId)
				clinicName=clinicmaster.getClinicName();
			//System.out.println("value::::::::::::::::::"+clinicName);
		}
		return clinicName;
	}
	
	/*private void createMeterGaugeModel() {  
		  
       List<Number> addSpeed = new ArrayList<Number>(){{
			add(20);  
            add(50);  
            add(120);  
            add(220);  
        }};  
  
        meterGaugeModel = new MeterGaugeChartModel(140, addSpeed);  
    } */


	public int getPharmacogenomicsPercentage() {
		return pharmacogenomicsPercentage;
	}

	public void setPharmacogenomicsPercentage(int pharmacogenomicsPercentage) {
		this.pharmacogenomicsPercentage = pharmacogenomicsPercentage;
	}

	public int getAveragePharmacyPerPatient() {
		return averagePharmacyPerPatient;
	}

	public void setAveragePharmacyPerPatient(int averagePharmacyPerPatient) {
		this.averagePharmacyPerPatient = averagePharmacyPerPatient;
	}

	public String getMedicineNameForFormularySearch() {
		return medicineNameForFormularySearch;
	}

	public void setMedicineNameForFormularySearch(
			String medicineNameForFormularySearch) {
		this.medicineNameForFormularySearch = medicineNameForFormularySearch;
	}


	public MeterGaugeChartModel getMeterGaugeModel() {
		  if(meterGaugeModel ==null){
		       meterGaugeModel = findComplianceMeterDetail();
		       System.out.println("Value of meter:"+meterGaugeModel.getValue());
		       if(meterGaugeModel.getValue()==null){
		    	   meterGaugeModel.setValue(0);
		       }
		  }
		return meterGaugeModel;
	}


	public void setMeterGaugeModel(MeterGaugeChartModel meterGaugeModel) {
		this.meterGaugeModel = meterGaugeModel;
	}


	public String getLogedFirstName() {
		/*if(logedFirstName.equals(""))
		{*/
			logedFirstName=new ContextUtil().getLoggerFirstName();
		/*}*/
		return logedFirstName;
	}


	public void setLogedFirstName(String logedFirstName) {
		this.logedFirstName = logedFirstName;
	}
	
	

	public String getLogedLastName() {
		/*if(logedLastName.equals(""))
		{*/
			logedLastName=new ContextUtil().getLoggerLastName();
		/*}*/
		return logedLastName;
	}


	public void setLogedLastName(String logedLastName) {
		this.logedLastName = logedLastName;
	}


	public String getLogeMiddleName() {
		if(logeMiddleName.equals(""))
		{
			logeMiddleName=new ContextUtil().getLoggedUserMiddleName();
		}
		return logeMiddleName;
	}


	public void setLogeMiddleName(String logeMiddleName) {
		this.logeMiddleName = logeMiddleName;
	}


	/**
	 * @return the formulary1wsBean
	 */
	public NcFormulary1WSBean getFormulary1WsBean() {
		return formulary1WsBean;
	}


	/**
	 * @param formulary1wsBean the formulary1wsBean to set
	 */
	public void setFormulary1wsBean(NcFormulary1WSBean formulary1WsBean) {
		this.formulary1WsBean = formulary1WsBean;
	}


	/**
	 * @return the patientAllergyExtendedList
	 * @throws Exception 
	 */
	public List<PatientAllergyExtendedDetailV4> getPatientAllergyExtendedList() throws Exception {
		if(patientAllergyExtendedList==null){
			patientAllergyExtendedList=new ArrayList<PatientAllergyExtendedDetailV4>();
		
		//boolean checkUrl=findUrlForComingBackClinakosFromNewCrop();
		patientAllergyExtendedList=update1wsBean.getPatientAllergyHistory(providerLocation);
		
		System.out.println(":::::::::::::::::::><><><><>QQQQQsize::"+patientAllergyExtendedList.size());
		}
		return patientAllergyExtendedList;
	}


	

	/**
	 * @param patientAllergyExtendedList the patientAllergyExtendedList to set
	 */
	public void setPatientAllergyExtendedList(
			List<PatientAllergyExtendedDetailV4> patientAllergyExtendedList) {
		this.patientAllergyExtendedList = patientAllergyExtendedList;
	}


	/**
	 * @return the update1wsBean
	 */
	public NcUpdate1WSBean getUpdate1wsBean() {
		return update1wsBean;
	}


	/**
	 * @param update1wsBean the update1wsBean to set
	 */
	public void setUpdate1wsBean(NcUpdate1WSBean update1wsBean) {
		this.update1wsBean = update1wsBean;
	}
	
	/**
	 * Get drug dose combinition on the basis of medicine name 
	 * 
	 */
	public String getDrugDoseCombination(){
		System.out.println("Methdo started for drug dose combo ");
		wsDrugList=new ArrayList<WSDrug>();
		if(!(medicineNameForFormularySearch.equals(""))){
			// System.out.println("Drug dose combo medicition ");
			wsDrugList=userService.findAllDrugDoseComboForFormularyMedicine(medicineNameForFormularySearch);
		}
		setMedicineNameForFormularySearch(medicineNameForFormularySearch);
		RequestContext.getCurrentInstance().execute("drugDoseCombo.show();");
		return null;
		
	}


	/**
	 * @return the wsDrugList
	 */
	public List<WSDrug> getWsDrugList() {
		if(wsDrugList==null){
			wsDrugList=new ArrayList<WSDrug>();
		}
		return wsDrugList;
	}


	/**
	 * @param wsDrugList the wsDrugList to set
	 */
	public void setWsDrugList(List<WSDrug> wsDrugList) {
		this.wsDrugList = wsDrugList;
	}


	/**
	 * @return the drugDoseComboDatatable
	 */
	public DataTable getDrugDoseComboDatatable() {
		if(drugDoseComboDatatable==null){
			drugDoseComboDatatable=new DataTable();
		}
		return drugDoseComboDatatable;
	}


	/**
	 * @param drugDoseComboDatatable the drugDoseComboDatatable to set
	 */
	public void setDrugDoseComboDatatable(DataTable drugDoseComboDatatable) {
		this.drugDoseComboDatatable = drugDoseComboDatatable;
	}
	
	/**
	 * Get Insurance Company Detail 
	 */
	public String getInsuranceDetail(){
		
		insuranceDetailList=new ArrayList<InsuranceCompanies>();
		if(!(insuranceCompanyForFormularySearch.equals(""))){
			
		insuranceDetailList= userService.getInsuranceDetail(insuranceCompanyForFormularySearch);
		}
		RequestContext.getCurrentInstance().execute("insuranceDetail.show();");
		return null;
		
	}


	/**
	 * @return the insuranceCompanyForFormularySearch
	 */
	public String getInsuranceCompanyForFormularySearch() {
		return insuranceCompanyForFormularySearch;
	}


	/**
	 * @param insuranceCompanyForFormularySearch the insuranceCompanyForFormularySearch to set
	 */
	public void setInsuranceCompanyForFormularySearch(
			String insuranceCompanyForFormularySearch) {
		this.insuranceCompanyForFormularySearch = insuranceCompanyForFormularySearch;
	}


	/**
	 * @return the insuranceDetailList
	 */
	public List<InsuranceCompanies> getInsuranceDetailList() {
		if(insuranceDetailList==null){
			insuranceDetailList=new ArrayList<InsuranceCompanies>();
		}
		return insuranceDetailList;
	}


	/**
	 * @param insuranceDetailList the insuranceDetailList to set
	 */
	public void setInsuranceDetailList(List<InsuranceCompanies> insuranceDetailList) {
		this.insuranceDetailList = insuranceDetailList;
	}

	/**
	 * @return the insuranceDetailDatatable
	 */
	public DataTable getInsuranceDetailDatatable() {
		if(insuranceDetailDatatable==null){
			insuranceDetailDatatable=new DataTable();
		}
		return insuranceDetailDatatable;
	}

	/**
	 * @param insuranceDetailDatatable the insuranceDetailDatatable to set
	 */
	public void setInsuranceDetailDatatable(DataTable insuranceDetailDatatable) {
		this.insuranceDetailDatatable = insuranceDetailDatatable;
	}

	/**
	 * Select Insurance Company for Formulary Search 
	 */
	public void selectedInsuranceCompany(){
		InsuranceCompanies selectedInsuranceCompany=(InsuranceCompanies) getInsuranceDetailDatatable().getRowData();
		insuranceCompanies.setId(selectedInsuranceCompany.getId());
		setInsuranceCompanyForFormularySearch(selectedInsuranceCompany.getCompanyName());
		
	}


	public ProviderLocation getProviderLocation() {
		logger.info("getProviderLocatoLocation method start:::"+new ContextUtil().getProviderId());
		if(providerLocation.getId()==0)
		{
			providerLocation=userService.findProviderLocationDetail(new ContextUtil().getProviderId());
		}
		return providerLocation;
	}


	public void setProviderLocation(ProviderLocation providerLocation) {
		this.providerLocation = providerLocation;
	}


	public RoleSecurity getRoleSecurity() {
		
		logger.info("getRoleSecurity:::::::"+new ContextUtil().getLoginId());
		if(roleSecurity.getId()==0)
			roleSecurity=userService.findRoleDetails(new ContextUtil().getLoginId());
		logger.info("getRoleSecurity:::::::"+roleSecurity.getId()+":::"+roleSecurity.getRoleName()+":::"+roleSecurity.getRoleType()+":::"+roleSecurity.getNewCropRole());
		return roleSecurity;
	}


	public void setRoleSecurity(RoleSecurity roleSecurity) {
		this.roleSecurity = roleSecurity;
	}


	/**
	 * @return the formularyAlternativeDrugInfoMap
	 */
	public Map<String, List<FormularyDetail>> getFormularyAlternativeDrugInfoMap() {
		return formularyAlternativeDrugInfoMap;
	}


	/**
	 * @param formularyAlternativeDrugInfoMap the formularyAlternativeDrugInfoMap to set
	 */
	public void setFormularyAlternativeDrugInfoMap(
			Map<String, List<FormularyDetail>> formularyAlternativeDrugInfoMap) {
		this.formularyAlternativeDrugInfoMap = formularyAlternativeDrugInfoMap;
	}


	/**
	 * @return the alternativeFormularyDrugList
	 */
	public List<FormularyDetail> getAlternativeFormularyDrugList() {
		if(alternativeFormularyDrugList==null){
			alternativeFormularyDrugList=new ArrayList<FormularyDetail>();
		}
		return alternativeFormularyDrugList;
	}


	/**
	 * @param alternativeFormularyDrugList the alternativeFormularyDrugList to set
	 */
	public void setAlternativeFormularyDrugList(
			List<FormularyDetail> alternativeFormularyDrugList) {
		this.alternativeFormularyDrugList = alternativeFormularyDrugList;
	}


	/**
	 * @return the showAlternativeFormularyMedicine
	 */
	public boolean isShowAlternativeFormularyMedicine() {
		return showAlternativeFormularyMedicine;
	}


	/**
	 * @param showAlternativeFormularyMedicine the showAlternativeFormularyMedicine to set
	 */
	public void setShowAlternativeFormularyMedicine(
			boolean showAlternativeFormularyMedicine) {
		this.showAlternativeFormularyMedicine = showAlternativeFormularyMedicine;
	}

	/**
	 * Patient Xml Genration 
	 *//*
	public void getPatientAllergyXml(){
		System.out.println("patient Allergy Genration ");
		update1wsBean.createPatientCodifiedAllergy();
	}*/
	
	/**
	 * Save New Crop Allergy In Our Local System 
	 * @throws Exception 
	 * 
	 */
	/*public void saveNewCropAllergy() throws Exception{
		userService.saveNewCropAllergy(getPatientAllergyExtendedList(),new ContextUtil().getPatientId());
	}

*/
	/**
	 * @return the numberOfPatientforanticoagclinic
	 */
	public List<AnticoagProviderLocation> getNumberOfPatientforanticoagclinic() {
		/*logger.info("getNumberOfPatientforanticoagclinic:::::");
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		
		int procedureType=2;
		String start_result="0";
		String end_result="0";
		String medicinestage=MAINTAINENCE_STAGE;
		String statusID="mm";
		
		if(numberofpatientformedactionplanforgraterrange==null)
			//numberOfPatientforanticoagclinic=userService.findnumberofpatientforanticoagclinic(ClinicAndProviderIdList.get(2));
			numberOfPatientforanticoagclinic=userService.findnumberofpatientforanticoagclinic(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);*/
		return numberOfPatientforanticoagclinic;
	}


	/**
	 * @param numberOfPatientforanticoagclinic the numberOfPatientforanticoagclinic to set
	 */
	public void setNumberOfPatientforanticoagclinic(
			List<AnticoagProviderLocation> numberOfPatientforanticoagclinic) {
		this.numberOfPatientforanticoagclinic = numberOfPatientforanticoagclinic;
	}


	/**
	 * @return the numberofpatientformedactionplan
	 */
	
	public List<AnticoagProviderLocation> getNumberofpatientformedactionplan() {
		logger.info("getNumberofpatientformedactionplan:::::::::");
	/*	if(numberofpatientformedactionplan==null){
			numberofpatientformedactionplan = new ArrayList<AnticoagProviderLocation>();
	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		int procedureType=2;
		String start_result="0";
		String end_result="0";
		String medicinestage=MAINTAINENCE_STAGE;
		String statusID="hm";
		
			//numberofpatientformedactionplan=userService.findnumberofpatientformedactionplan(ClinicAndProviderIdList.get(2));
			numberofpatientformedactionplan=userService.findnumberofpatientformedactionplan(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);
		System.out.println("numberofpatientformedactionplan size@@@@@@@@@@@@ before "+numberofpatientformedactionplan.size());
		//getNumberofpatientformedactionplanforgraterrange();
		//numberofpatientformedactionplan.addAll(numberofpatientformedactionplanforgraterrange);
		Set uniqueSetValues = new HashSet(numberofpatientformedactionplan);
		numberofpatientformedactionplan.clear();
		numberofpatientformedactionplan.addAll(uniqueSetValues);
		System.out.println("numberofpatientformedactionplan size@@@@@@@@@@@@ "+numberofpatientformedactionplan.size());
		}*/
		return numberofpatientformedactionplan;
	}


	/**
	 * @param numberofpatientformedactionplan the numberofpatientformedactionplan to set
	 */
	public void setNumberofpatientformedactionplan(
			List<AnticoagProviderLocation> numberofpatientformedactionplan) {
		this.numberofpatientformedactionplan = numberofpatientformedactionplan;
	}


	/**
	 * @return the numberofpatientformedactionplanforgraterrange
	 */
	public List<AnticoagProviderLocation> getNumberofpatientformedactionplanforgraterrange() {
		logger.info("getNumberofpatientformedactionplanforgraterrange");
		//int logedUserId=new ContextUtil().getLoginId();
		//System.out.println("::::::::doctor id:::"+logedUserId);
		//List<Integer>ClinicAndProviderIdList= userService.findClinicAndProviderId(logedUserId);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		
		int procedureType=2;
		String start_result="4.5";
		String end_result="0";
		String medicinestage=MAINTAINENCE_STAGE;
		String statusID="";
		
		if(numberofpatientformedactionplanforgraterrange==null){
			numberofpatientformedactionplanforgraterrange = new ArrayList<AnticoagProviderLocation>();
			//numberofpatientformedactionplanforgraterrange=userService.findnumberofpatientformedactionplanforgraterrange(ClinicAndProviderIdList.get(2));
			numberofpatientformedactionplanforgraterrange=userService.findnumberofpatientformedactionplanforgraterrange(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);
		}
		return numberofpatientformedactionplanforgraterrange;
	}


	/**
	 * @param numberofpatientformedactionplanforgraterrange the numberofpatientformedactionplanforgraterrange to set
	 */
	public void setNumberofpatientformedactionplanforgraterrange(
			List<AnticoagProviderLocation> numberofpatientformedactionplanforgraterrange) {
		this.numberofpatientformedactionplanforgraterrange = numberofpatientformedactionplanforgraterrange;
	}


	/**
	 * @return the totalnumberofpatientinrlab
	 */
	public List<Integer> getTotalnumberofpatientinrlab() {
		logger.info("getTotalnumberofpatientinrlab");
		//int logedUserId=new ContextUtil().getLoginId();
		//System.out.println("::::::::doctor id:::"+logedUserId);
		//List<Integer>ClinicAndProviderIdList= userService.findClinicAndProviderId(logedUserId);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		if(totalnumberofpatientinrlab!=null)
			totalnumberofpatientinrlab=userService.findnumberofpatientinrlab(ClinicAndProviderIdList.get(2));
		return totalnumberofpatientinrlab;
	}


	/**
	 * @param totaknumberofpatientinrlab the totaknumberofpatientinrlab to set
	 */
	public void setTotalnumberofpatientinrlab(
			List<Integer> totaknumberofpatientinrlab) {
		this.totalnumberofpatientinrlab = totaknumberofpatientinrlab;
	}
	private DoctorDetail doctorDetailData=new DoctorDetail();
	

	public DoctorDetail getDoctorDetailData() {
		if(doctorDetailData.getId()==0)
		{
			doctorDetailData = userService.findDoctorDetailData();
		}
		return doctorDetailData;
	}


	public void setDoctorDetailData(DoctorDetail doctorDetailData) {
		this.doctorDetailData = doctorDetailData;
	}


	public void setClinicMasterList(List<ClinicMaster> clinicMasterList) {
		this.clinicMasterList = clinicMasterList;
	}

	/* * find clinic D3 Pie Chart Detail According to particular provider..
	 * 
	 * Author @Sanket Singh
	 */
	public String getClinicMasterList() {
		if(clinicMasterList == null){
		List<ClinicMaster> clinicMasterList=new ArrayList<ClinicMaster>();
		}
      return new Gson().toJson(clinicMasterList);
	}

	

	public void setClinicMasterpiechart(List<ClinicMaster> clinicMasterpiechart) {
		this.clinicMasterpiechart = clinicMasterpiechart;
	}


	public List<ClinicMaster> getClinicMasterpiechart() {
		return clinicMasterpiechart;
	}


	public void setClinicBarChart(String clinicBarChart) {
		this.clinicBarChart = clinicBarChart;
	}


	public String getClinicBarChart() {
		return clinicBarChart;
	}

	public void setInsuranceDetailListBar(List<InsuranceCompanies> insuranceDetailListBar) {
		this.insuranceDetailListBar = insuranceDetailListBar;
	}
	/*	
	
	 * find clinic D3 Js Bar Chart Detail According to particular provider..
	 * Author Sanket Singh
	 * 
	 * 
*/

	public String getInsuranceDetailListBar() {
		logger.info("getInsuranceDetailListBar method");
		insuranceDetailListBar=new ArrayList<InsuranceCompanies>();
		List<Object> userInsuranceDetailsList=userService.findInsuranceGraphDetail(new ContextUtil().getClinicProviderId(),numberOfPatientParticularProvider,insuranceCompaniesList);
	
		System.out.println("userInsuranceDetailsList value:::::::::"+userInsuranceDetailsList.size());
		System.out.println("clinicproviderId::::::::::::"+new ContextUtil().getClinicProviderId());
		
		
		int count=0;
	for(Object obj: userInsuranceDetailsList){
		  Object[] row = (Object[]) obj;
			
			  String insuranceName=findInsurancenameAccordingToId(insuranceCompaniesList,(Integer)row[0]);
			  
			  
			 // System.out.println("::::insuranceCompaniesList,(Integer)row[0]::"+(Integer)row[0]);
			  Long value=(long) 0;
			   value=(Long)row[1];
			  System.out.println(":::::::::::::>>>>>insuranceName::"+insuranceName+"::value:::::"+value);
			 if(insuranceName.isEmpty())
			  {
				  break;
			  }
			 //System.out.println("value of list::::::::::::::"+value +"no of provider::::::::"+numberOfPatientParticularProvider);
			//  insuranceChartdetail.set(insuranceName,value);
			  //System.out.println("insurancegraph:::::::::::::"+userInsuranceDetailsList.size()+value);
			 InsuranceCompanies insuranceCompanies=new InsuranceCompanies ();
			 insuranceCompanies.setCompanyName(insuranceName);
			  insuranceCompanies.setCompanyValue(Integer.valueOf(String.valueOf(value)));
			  
			System.out.println("companyName::::::::::"+insuranceCompanies.getCompanyName()+"companyValue:::::::::"+insuranceCompanies.getCompanyValue());
			  insuranceDetailListBar.add(insuranceCompanies);
			 
	
	
	

			 if(count>=4)
				{
					break;
				}
				count++;
			  
		
	}
	return new Gson().toJson(insuranceDetailListBar);

	}


	/**
	 * @return the totalnumberofpatientinrlabforinitiationphase
	 */
	public List<AnticoagProviderLocation> getTotalnumberofpatientinrlabforinitiationphase() {
		
		logger.info("getTotalnumberofpatientinrlabforinitiationphase");
		//int logedUserId=new ContextUtil().getLoginId();
		//System.out.println("::::::::doctor id:::"+logedUserId);
		//List<Integer>ClinicAndProviderIdList= userService.findClinicAndProviderId(logedUserId);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		if(totalnumberofpatientinrlabforinitiationphase!=null)
			totalnumberofpatientinrlabforinitiationphase=userService.findnumberofpatientinrlabforinitiationphase(ClinicAndProviderIdList.get(2));
		
		return totalnumberofpatientinrlabforinitiationphase;
	}


	/**
	 * @param totalnumberofpatientinrlabforinitiationphase the totalnumberofpatientinrlabforinitiationphase to set
	 */
	public void setTotalnumberofpatientinrlabforinitiationphase(
			List<AnticoagProviderLocation> totalnumberofpatientinrlabforinitiationphase) {
		this.totalnumberofpatientinrlabforinitiationphase = totalnumberofpatientinrlabforinitiationphase;
	}


	/**
	 * @return the numberofpatientformedactionplanforgraterrangeforintiationphase
	 */
	public List<AnticoagProviderLocation> getNumberofpatientformedactionplanforgraterrangeforintiationphase() {
		logger.info("getNumberofpatientformedactionplanforgraterrangeforintiationphase:::::::");
		//int logedUserId=new ContextUtil().getLoginId();
		//System.out.println("::::::::doctor id:::"+logedUserId);
		//List<Integer>ClinicAndProviderIdList= userService.findClinicAndProviderId(logedUserId);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		
		String medicinestage="Initiation";
		int procedureType=2;
		String start_result="4.5";
		String end_result="0";
		
		if(numberofpatientformedactionplanforgraterrangeforintiationphase != null)
			//numberofpatientformedactionplanforgraterrangeforintiationphase=userService.findnumberofpatientformedactionplanforgraterrangeforintiationphase(ClinicAndProviderIdList.get(2));
			numberofpatientformedactionplanforgraterrangeforintiationphase=userService.findnumberofpatientformedactionplanforgraterrangeforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
		System.out.println("numberofpatientformedactionplanforgraterrangeforintiationphase size************ "+numberofpatientformedactionplanforgraterrangeforintiationphase.size());
		return numberofpatientformedactionplanforgraterrangeforintiationphase;
	}


	/**
	 * @param numberofpatientformedactionplanforgraterrangeforintiationphase the numberofpatientformedactionplanforgraterrangeforintiationphase to set
	 */
	public void setNumberofpatientformedactionplanforgraterrangeforintiationphase(
			List<AnticoagProviderLocation> numberofpatientformedactionplanforgraterrangeforintiationphase) {
		this.numberofpatientformedactionplanforgraterrangeforintiationphase = numberofpatientformedactionplanforgraterrangeforintiationphase;
	}


	/**
	 * @return the numberofpatientformedactionplanforinitiationphase
	 */
	
	public List<AnticoagProviderLocation> getNumberofpatientformedactionplanforinitiationphase() {
		logger.info("getNumberofpatientformedactionplanforinitiationphase::::::::");
	/*	if(numberofpatientformedactionplanforinitiationphase==null){
			numberofpatientformedactionplanforinitiationphase = new ArrayList<AnticoagProviderLocation>();
	
		getClinicAndProviderIdList();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		
		String medicinestage="Initiation";
		int procedureType=2;
		String start_result="0";
		String end_result="2";
		String statusID="hi";
		
			//numberofpatientformedactionplanforinitiationphase=userService.findnumberofpatientformedactionplanforintiationphase(ClinicAndProviderIdList.get(2));
			numberofpatientformedactionplanforinitiationphase=userService.findnumberofpatientformedactionplanforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);
		System.out.println("numberofpatientformedactionplanforinitiationphase size######### "+numberofpatientformedactionplanforinitiationphase.size());
		//getNumberofpatientformedactionplanforgraterrangeforintiationphase();
		//numberofpatientformedactionplanforinitiationphase.addAll(numberofpatientformedactionplanforgraterrangeforintiationphase);
		Set removingDuplicateFormHighAndHigherAnticogList = new HashSet(numberofpatientformedactionplanforinitiationphase);
		//removingDuplicateFormHighAndHigherAnticogList.add(numberofpatientformedactionplanforinitiationphase);
		numberofpatientformedactionplanforinitiationphase.clear();
		numberofpatientformedactionplanforinitiationphase.addAll(removingDuplicateFormHighAndHigherAnticogList);
				
		System.out.println("medactionpatientlist=== "+numberofpatientformedactionplanforinitiationphase.size());
		}*/
		return numberofpatientformedactionplanforinitiationphase;
	}


	/**
	 * @param numberofpatientformedactionplanforinitiationphase the numberofpatientformedactionplanforinitiationphase to set
	 */
	public void setNumberofpatientformedactionplanforinitiationphase(
			List<AnticoagProviderLocation> numberofpatientformedactionplanforinitiationphase) {
		this.numberofpatientformedactionplanforinitiationphase = numberofpatientformedactionplanforinitiationphase;
	}


	/**
	 * @return the numberOfPatientforanticoagclinicforintiationphase
	 */
	public List<AnticoagProviderLocation> getNumberOfPatientforanticoagclinicforintiationphase() {
		//Following Lines commented By Nagaraj on 18/dec/2014 from 1634 to 1640
	/*	logger.info("getNumberOfPatientforanticoagclinicforintiationphase:::::::::"+levelVal);
	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));*/
		
		//String levelVal1=(String) session.getAttribute("levelVal");
		//System.out.println(session.getAttribute("levelVal"));
		//if(numberOfPatientforanticoagclinicforintiationphase!=null)
			//if(levelVal1 == "li")
			//{
				//System.out.println("inside low");
		//Following Lines commented By Nagaraj on 18/dec/2014 from 1649 to 1654
			/*	String medicinestage="Initiation";
				int procedureType=2;
				String start_result="2";
				String end_result="3";
				String statusID="li";
				numberOfPatientforanticoagclinicforintiationphase=userService.findnumberofpatientforanticoagclinicforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);*/
			/*}
			else if(levelVal1 == "mi")
			{
				System.out.println("inside medium");
				String medicinestage="Initiation";
				int procedureType=2;
				double start_result=3;
				double end_result=4.5;
				numberOfPatientforanticoagclinicforintiationphase=userService.findnumberofpatientforanticoagclinicforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
			}
			else if(levelVal1 == "hi")
			{
				System.out.println("inside high");
				String medicinestage="Initiation";
				int procedureType=2;
				double start_result=0;
				double end_result=2;
				numberOfPatientforanticoagclinicforintiationphase=userService.findnumberofpatientforanticoagclinicforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
			}*/
			
		
		/*for(AnticoagProviderLocation apl : numberOfPatientforanticoagclinicforintiationphase)
		{
			System.out.println("firstname   "+apl.getFirst_name());
		}*/
		return numberOfPatientforanticoagclinicforintiationphase;
	}


	/**
	 * @param numberOfPatientforanticoagclinicforintiationphase the numberOfPatientforanticoagclinicforintiationphase to set
	 */
	public void setNumberOfPatientforanticoagclinicforintiationphase(
			List<AnticoagProviderLocation> numberOfPatientforanticoagclinicforintiationphase) {
		this.numberOfPatientforanticoagclinicforintiationphase = numberOfPatientforanticoagclinicforintiationphase;
	}


	/**
	 * @return the numberofpatientformedactionplanforhigherendofpatient
	 */
	public List<AnticoagProviderLocation> getNumberofpatientformedactionplanforhigherendofpatient() {
		/*logger.info("getNumberofpatientformedactionplanforhigherendofpatient:::::");
		if(numberofpatientformedactionplanforhigherendofpatient==null){
			numberofpatientformedactionplanforhigherendofpatient = new ArrayList<AnticoagProviderLocation>();
	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		
		String medicinestage=MAINTAINENCE_STAGE;
		int procedureType=2;
		String start_result="0";
		String end_result="0";
		String statusID="lm";
		
			//numberofpatientformedactionplanforhigherendofpatient=userService.findnumberofpatientforanticoagclinicforhigerendofpatient(ClinicAndProviderIdList.get(2));
			numberofpatientformedactionplanforhigherendofpatient=userService.findnumberofpatientforanticoagclinicforhigerendofpatient(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);
		System.out.println("numberofpatientformedactionplanforhigherendofpatient  ^^^^^^^^^^^^ "+numberofpatientformedactionplanforhigherendofpatient.size());
		}*/
		return numberofpatientformedactionplanforhigherendofpatient;
	}


	/**
	 * @param numberofpatientformedactionplanforhigherendofpatient the numberofpatientformedactionplanforhigherendofpatient to set
	 */
	public void setNumberofpatientformedactionplanforhigherendofpatient(
			List<AnticoagProviderLocation> numberofpatientformedactionplanforhigherendofpatient) {
		this.numberofpatientformedactionplanforhigherendofpatient = numberofpatientformedactionplanforhigherendofpatient;
	}


	/**
	 * @return the numberofpatientformedactionplanforhigherendofpatientforintiationphase
	 */
	public List<AnticoagProviderLocation> getNumberofpatientformedactionplanforhigherendofpatientforintiationphase() {
		/*
		logger.info("getNumberofpatientformedactionplanforhigherendofpatientforintiationphase::::::");
	
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2)); //levelVal
		
		String medicinestage="Initiation";
		int procedureType=2;
		String start_result="3";
		String end_result="4.5";
		String statusID="mi";
		
		if(numberofpatientformedactionplanforhigherendofpatientforintiationphase!=null)
			//numberofpatientformedactionplanforhigherendofpatientforintiationphase=userService.findnumberofpatientforanticoagclinicforhigerendofpatientforintiation(ClinicAndProviderIdList.get(2));
			numberofpatientformedactionplanforhigherendofpatientforintiationphase=userService.findnumberofpatientforanticoagclinicforhigerendofpatientforintiation(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);*/
		return numberofpatientformedactionplanforhigherendofpatientforintiationphase;
	}


	/**
	 * @param numberofpatientformedactionplanforhigherendofpatientforintiationphase the numberofpatientformedactionplanforhigherendofpatientforintiationphase to set
	 */
	public void setNumberofpatientformedactionplanforhigherendofpatientforintiationphase(
			List<AnticoagProviderLocation> numberofpatientformedactionplanforhigherendofpatientforintiationphase) {
		this.numberofpatientformedactionplanforhigherendofpatientforintiationphase = numberofpatientformedactionplanforhigherendofpatientforintiationphase;
	}
	
	
/*	public String getNewCropPatientDetails() {
		newCropPatientDetails="";
		for(PatientAllergy patAllergy:getPatientAllergyList()){
			if(!(patAllergy.getAllergySeverity().trim().equals("") ))
			{
				System.out.println(":::::::::::::::::!!!!!!!!!!!!!!!!!"+patAllergy.getAllergyName()+":::"+patAllergy.getAllergySeverity()+":::"+patAllergy.getCompositeAllergyId());
			}
			else
				System.out.println("2222:::::::::::::::::!!!!!!!!!!!!!!!!!"+patAllergy.getAllergyName()+":::"+patAllergy.getAllergySeverity());
			
			String onsetdate="";
			if(!(patAllergy.getOnSetDate()==null))
			{
				onsetdate=new DateUtil().convertDateFormatUsingFormat(patAllergy.getOnSetDate(),DATE_PATTERN);
			}
			
			if(!(patAllergy.getAllergySeverity().trim().equals("") ))
			{
			if(patAllergy.getCompositeAllergyId()==0)
			{
				
				System.out.println(":::::::::::::::::!!!!!!1234!!!!!!!!!!!"+patAllergy.getAllergyName()+":::"+patAllergy.getAllergySeverity()+":::"+patAllergy.getCompositeAllergyId()
						+"pff"+patAllergy.getAllergySource()+"hero"+patAllergy.getAllergytype()+"htr"+patAllergy.getAlergyConceptId()+"hhh"+patAllergy.getAllergySeverity()
						+"fff"+patAllergy.getAllergyNotes()+"dd"+onsetdate);
				
				
		newCropPatientDetails=newCropPatientDetails+
				
				"<Credentials>"+
	   " <partnerName>#{webservices.partnerName}</partnerName>"+
	    "<name>#{webservices.name}</name>"+
	    "<password>#{webservices.password}</password>"+
	    "<productName>#{webservices.productName}</productName>"+
	    "<productVersion>#{webservices.productVersion}</productVersion>"+
	  "</Credentials>";
				
				
				
				
				
				
						"<PatientAllergies>"+
						 "<allergyID>"+patAllergy.getAllergySource()+":"+patAllergy.getAllergytype()+":"+patAllergy.getAlergyConceptId()+"</allergyID>"+	
					      "<allergyTypeID>"+"Y"+"</allergyTypeID>"+
					      "<allergySeverityTypeID>"+patAllergy.getAllergySeverity()+"</allergySeverityTypeID>"+
					      "<allergyComment>"+patAllergy.getAllergyNotes()+"</allergyComment>"+
					    "<onsetDate>" +onsetdate+"</onsetDate>"+
					    "</PatientAllergies>";
			}
			else
			{
			newCropPatientAllergy=newCropPatientAllergy+
			"<PatientAllergies>"+
			 "<allergyID>"+patAllergy.getCompositeAllergyId()+"</allergyID>"+	
		      "<allergyTypeID>"+"FDB"+"</allergyTypeID>"+
		      "<allergySeverityTypeID>"+patAllergy.getAllergySeverity()+"</allergySeverityTypeID>"+
		      "<allergyComment>"+patAllergy.getAllergyNotes()+"</allergyComment>"+
		      "<onsetDate>" +onsetdate+"</onsetDate>"+
		    "</PatientAllergies>";
			}
		}
		
		//System.out.println(":::::::::::::::::::newCropPatientAllergy::::"+newCropPatientAllergy);
		return newCropPatientDetails.trim();
	}*/


	/*public void setNewCropPatientDetails(String newCropPatientDetails) {
		this.newCropPatientDetails = newCropPatientDetails;
	}
*/
	
	/**
	 * Get Patient PBM Eligblity Detail and PBMDrugHistory Detail
	 * Save PAtient PBM Eligblity  Result and PBM Drug history Detail
	 * @throws Exception 
	 * 
	 */
	public void getPatientPBMDrugHistoryNPbmEligblity() throws Exception{
		System.out.println("Account Id"+getProviderLocation().getAccountId()+"Role Type  "+getRoleSecurity().getRoleType()+"New Crop Role "+getRoleSecurity().getNewCropRole());
		List<UserLoginDetail> patientDetailList=userService.getAllPatientDetailList();
		System.out.println("timePeriod: ::::::::"+timePeriod);
		for(UserLoginDetail patientDetail:patientDetailList){
			List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList=update1wsBean.callGetPBMDrugHistoryV2(timePeriod, providerLocation, getRoleSecurity(), patientDetail);
			System.out.println("drugHistoryDetailResultList.size()"+patientPbmDrugHistoryDetailList.size());
			
			for(PatientPbmDrugHistoryDetail patientPbmDrugHistoryDetail:patientPbmDrugHistoryDetailList){
				
				userService.savePatientPBMDrugHistoryDetailResult(patientPbmDrugHistoryDetail);
			
			}
		}
		
		
		
		
		
	}


	/**
	 * Get the Patient PBM Drug History Detail 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public List<PatientPbmDrugHistoryDetail> getPatientPbmDrugHistoryDetailList() throws ParserConfigurationException, SAXException, IOException {
		if(patientPbmDrugHistoryDetailList==null){
			patientPbmDrugHistoryDetailList=new ArrayList<PatientPbmDrugHistoryDetail>();
		}
		 patientPbmDrugHistoryDetailList=userService.getAllPatientPbmDrugHistoryDetail(1);
		 System.out.println("patientPbmDrugHistoryDetailList.size()"+patientPbmDrugHistoryDetailList.size());
		 
		 System.out.println("timePeriod  "+timePeriod );
		
		List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultList=update1wsBean.convertPBMDrugHistoryXMLToSaxParser(timePeriod, providerLocation, roleSecurity,patientPbmDrugHistoryDetailList);
			
			for(PatientPBMDrugHistoryResult drugHistoryResult:patientPBMDrugHistoryResultList){
				System.out.println(" drugHistoryResult.getDrugInfo():::::::::   "+drugHistoryResult.getDrugInfo()+"   drugHistoryResult.getPatientId():::::::  "+drugHistoryResult.getPatientId()+"  drugHistoryResult.getFillDate() :::::::: "+drugHistoryResult.getFillDate());
				userService.savePatientPBMDrugHistoryResult(drugHistoryResult);
			}
			
			
		
		return patientPbmDrugHistoryDetailList;
	}


	
	/**
	 * Get PatientPBMDrugHistory Result For Formulary Composite 
	 * Save FormularyComposite Drug Detail Info
	 * Save FormularyComposite Copay Info 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * 
	 */
	public void getPatientPBMDrugHistoryResultValue() throws ParserConfigurationException, SAXException, IOException{
		
		List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultList=userService.getAllPatientPBMDrugHistoryResultValue(1);
		System.out.println("patientPBMDrugHistoryResultList.size()"+patientPBMDrugHistoryResultList.size());
		Map<String,ArrayList<? extends Object>> formularyCompositeDetailMap=formulary2WsBean.convertFormularyCompositeUsingDomParser(getProviderLocation(),getRoleSecurity(),patientPBMDrugHistoryResultList);
		
		ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeDrugDetailInfoList=new ArrayList<PatientFormularyCompositeDrugDetailInfo>();
		ArrayList<PatientFormularyCompositeCopayInfo> formularyCompositeCopayInfoList=new ArrayList<PatientFormularyCompositeCopayInfo>();
		
		for(Map.Entry<String, ArrayList<? extends Object>> entry:formularyCompositeDetailMap.entrySet()){
			if(entry.getKey().equals("Table3")){
				formularyCompositeDrugDetailInfoList.addAll((Collection<? extends PatientFormularyCompositeDrugDetailInfo>) entry.getValue());
				
			}
			
			if(entry.getKey().equals("Table4")){
				formularyCompositeCopayInfoList.addAll((Collection<? extends PatientFormularyCompositeCopayInfo>) entry.getValue());
			}
			
			userService.savePatientFormularyCompositeDrugDetailInfoData(formularyCompositeDrugDetailInfoList);
			userService.savePatientFormularyCompositeCopayInfoData(formularyCompositeCopayInfoList);
			
		}
		
		
	}


	/**
	 * @return the formulary2WsBean
	 */
	public NcFormulary2WSBean getFormulary2WsBean() {
		return formulary2WsBean;
	}


	/**
	 * @param formulary2WsBean the formulary2WsBean to set
	 */
	public void setFormulary2WsBean(NcFormulary2WSBean formulary2WsBean) {
		this.formulary2WsBean = formulary2WsBean;
	}
	
	/**
	 * Decode Patient PBM Eligblity Detail XML Response 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public void getPatientPbmEligblityDetailData() throws ParserConfigurationException, SAXException, IOException{
		int patientId=1;
		patientPbmDrugHistoryDetailList=userService.getAllPatientPbmDrugHistoryDetail(patientId);
		List<PatientPBMEligibilityDetailData> pbmEligibilityDetailDataList=update1wsBean.parsePatientPbmEligblityXmlResponse(patientPbmDrugHistoryDetailList);
		
		System.out.println("pbmEligibilityDetailDataList.size()"+pbmEligibilityDetailDataList.size());
		
		userService.savePatientPBMEligibilityDetailData(pbmEligibilityDetailDataList);
		
	}
	

	public String getLoggedUsertimeZone() {
		  /*if(loggedUsertimeZone.isEmpty())
		  {*/
		   loggedUsertimeZone=new ContextUtil().getLoggedUsertimeZone();
		   System.out.println("loggedUsertimeZone:::::::in usermanage bean="+loggedUsertimeZone+"::::"+new ContextUtil().getLoggedUsertimeZone());
		 // }
		  return loggedUsertimeZone;
		 }


	/**
	 * @return the timePeriod
	 */
	public int getTimePeriod() {
		return timePeriod;
	}

	/**
	 * @param timePeriod the timePeriod to set
	 */
	public void setTimePeriod(int timePeriod) {
		this.timePeriod = timePeriod;

	}
	
/**
 * Used in MasterDataManagment.xhtml
 * used to download Latest PBM drug data/ Call All Master Data Pulling Method 
 * <p>
 * 
 */
	public void callPatientPbmDrugHistoryDataPullingMethod(){
		 try {
			 System.out.println("Data Pulling method started now .............");
			 List<UserLoginDetail> patientDetailList=userService.getAllPatientDetailList();
				System.out.println(patientDetailList.size()+"timePeriod: ::::::::"+timePeriod);
				if(timePeriod==0){
					timePeriod=4;
				}
				else {
					timePeriod=timePeriod;
				}
				if(selectedUserLoginDetail.length==0){
					for(UserLoginDetail patientDetail:patientDetailList){
						userService.deletePatientPBMDrugHistoryDetailResult(patientDetail.getUserId());
						userService.deletePatientPBMDrugHistoryResult(patientDetail.getUserId());
						
						List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList=update1wsBean.callGetPBMDrugHistoryV2(timePeriod, getProviderLocation(), getRoleSecurity(), patientDetail);
						System.out.println("drugHistoryDetailResultList.size()"+patientPbmDrugHistoryDetailList.size());
						
						for(PatientPbmDrugHistoryDetail patientPbmDrugHistoryDetail:patientPbmDrugHistoryDetailList){
							
							userService.savePatientPBMDrugHistoryDetailResult(patientPbmDrugHistoryDetail);
						
						}
						
						 patientPbmDrugHistoryDetailList=userService.getAllPatientPbmDrugHistoryDetail(patientDetail.getUserId());
						 List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultList=update1wsBean.convertPBMDrugHistoryXMLToSaxParser(timePeriod, getProviderLocation(), getRoleSecurity(),patientPbmDrugHistoryDetailList);
							
							for(PatientPBMDrugHistoryResult drugHistoryResult:patientPBMDrugHistoryResultList){
								System.out.println(" drugHistoryResult.getDrugInfo():::::::::   "+drugHistoryResult.getDrugInfo()+"   drugHistoryResult.getPatientId():::::::  "+drugHistoryResult.getPatientId()+"  drugHistoryResult.getFillDate() :::::::: "+drugHistoryResult.getFillDate());
							userService.savePatientPBMDrugHistoryResult(drugHistoryResult);
							}
							
							List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultValueList=userService.getAllPatientPBMDrugHistoryResultValue(patientDetail.getUserId());
							ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeDrugDetailInfoList=new ArrayList<PatientFormularyCompositeDrugDetailInfo>();
							ArrayList<PatientFormularyCompositeCopayInfo> formularyCompositeCopayInfoList=new ArrayList<PatientFormularyCompositeCopayInfo>();
							userService.updateHealthPlanAndPharmacyDetail(patientDetail.getUserId());
							userService.careTeamIntegration(patientDetail.getUserId());
							logger.info(patientPBMDrugHistoryResultValueList.size()+"!:::::::::");
							
				}
				
						//write compliance caluclation..
						
						
						
				}
				
			
					
				
				userService.updateComplianceValue();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}


	public BigInteger getNoOfGreenSymbolPatient() {
		return noOfGreenSymbolPatient;
	}


	public void setNoOfGreenSymbolPatient(BigInteger noOfGreenSymbolPatient) {
		this.noOfGreenSymbolPatient = noOfGreenSymbolPatient;
	}

	/**
	 * Pull Data On Reconsile Button 
	 * OverLoaded Method 
	 * @param timePeriod2 
	 * 
	 */
	
	public void callPatientPbmDrugHistoryDataPullingOnReconsile(int timePeriod2){
		 try {
			 logger.info("callPatientPbmDrugHistoryDataPullingOnReconsile::::::::" );
			 List<UserLoginDetail> patientDetailList=userService.getPatientDetailList(new ContextUtil().getPatientId());
				System.out.println("timePeriod: ::::::::"+timePeriod);
				int timePeriod=timePeriod2;
				//timePeriod=4;
				userService.deletePatientPBMDrugHistoryDetailResult(new ContextUtil().getPatientId());
				userService.deletePatientPBMDrugHistoryResult(new ContextUtil().getPatientId());
				List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList=new ArrayList<PatientPbmDrugHistoryDetail>();
				logger.info("before:::usercrendintal time:::"+new Timestamp(new java.util.Date().getTime()));
				for(UserLoginDetail patientDetail:patientDetailList){
					patientPbmDrugHistoryDetailList=update1wsBean.callGetPBMDrugHistoryV2(timePeriod, getProviderLocation(),
							getRoleSecurity(), patientDetail);
					System.out.println("drugHistoryDetailResultList.size()"+patientPbmDrugHistoryDetailList.size());
					logger.info("drugHistoryDetailResultList.size()::::::;"+patientPbmDrugHistoryDetailList.size());
					
					for(PatientPbmDrugHistoryDetail patientPbmDrugHistoryDetail:patientPbmDrugHistoryDetailList){
						logger.info("Pbm drug history xml response ::::::::::::"+patientPbmDrugHistoryDetail.getPbmDrugHistoryXmlResponse());
						userService.savePatientPBMDrugHistoryDetailResult(patientPbmDrugHistoryDetail);
					
					}
					
					//logger.info("after user crendential:::update time:::"+new Timestamp(new java.util.Date().getTime()));
					//patientPbmDrugHistoryDetailList=userService.getAllPatientPbmDrugHistoryDetail(patientDetail.getUserId());
					logger.info("before:::sax parser start time:::"+new Timestamp(new java.util.Date().getTime()));
					 List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultList=update1wsBean.convertPBMDrugHistoryXMLToSaxParser(timePeriod, getProviderLocation(), getRoleSecurity(),patientPbmDrugHistoryDetailList);
					logger.info("before:::pbmdrug result time:::"+new Timestamp(new java.util.Date().getTime()));
						for(PatientPBMDrugHistoryResult drugHistoryResult:patientPBMDrugHistoryResultList){
							System.out.println(" drugHistoryResult.getDrugInfo():::::::::   "+drugHistoryResult.getDrugInfo()+"   drugHistoryResult.getPatientId():::::::  "+drugHistoryResult.getPatientId()+"  drugHistoryResult.getFillDate() :::::::: "+drugHistoryResult.getFillDate());
							userService.savePatientPBMDrugHistoryResult(drugHistoryResult);
						}
						
						//List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultValueList=userService.getAllPatientPBMDrugHistoryResultValue(patientDetail.getUserId());
						ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeDrugDetailInfoList=new ArrayList<PatientFormularyCompositeDrugDetailInfo>();
						ArrayList<PatientFormularyCompositeCopayInfo> formularyCompositeCopayInfoList=new ArrayList<PatientFormularyCompositeCopayInfo>();
						
						logger.info("before:::update time:::"+new Timestamp(new java.util.Date().getTime()));

						userService.updateHealthPlanAndPharmacyDetail(patientDetail.getUserId());
						logger.info("end:::update time:to pharmacy history::"+new Timestamp(new java.util.Date().getTime()));
						userService.careTeamIntegration(patientDetail.getUserId());
						logger.info("end careteamIntegra:::update time:::"+new Timestamp(new java.util.Date().getTime()));
						
						
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}


	/**
	 * @return the passwordEncoderService
	 */
	public IPasswordEncoderGeneratorService getPasswordEncoderService() {
		return PasswordEncoderService;
	}


	/**
	 * @param passwordEncoderService the passwordEncoderService to set
	 */
	public void setPasswordEncoderService(
			IPasswordEncoderGeneratorService passwordEncoderService) {
		PasswordEncoderService = passwordEncoderService;
	}
	
/*
 * *******************Method for encrypting existing password
 * ******@author:saurabh
 */
	public void encryptExistingPassword(){
		logger.info("=========calling method encryptExistingPassword in userManageBean=========");
		PasswordEncoderService.encryptExistingPassword();
	}

	
	
	/*public List<AnticoagProviderLocation> getRetrivePatientByAnticoagCategoryList() {
		
		if(levelVal==null){
			levelVal="";
		}
		System.out.println("Level Value coming from Javascript is in retrivePatientByAnticoagCategoryList:::"+levelVal);
		
		int logedUserId=new ContextUtil().getLoginId();
		//System.out.println("::::::::doctor id:::"+logedUserId);
		List<Integer>ClinicAndProviderIdList= userService.findClinicAndProviderId(logedUserId);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		
			if(levelVal.equals("li"))
			{
				System.out.println("inside low in initiation");
				String medicinestage="Initiation";
				int procedureType=2;
				String start_result="2";
				String end_result="3";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
			}
			if(levelVal.equals("mi"))
			{
				System.out.println("inside medium in initiation");
				String medicinestage="Initiation";
				int procedureType=2;
				String start_result="3";
				String end_result="4.5";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
			}
			if(levelVal.equals("hi"))
			{
				System.out.println("inside high in initiation");
				String medicinestage="Initiation";
				int procedureType=2;
				int flagCompare=0;
				String start_result="0";
				String end_result="2";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
							
			   List<AnticoagProviderLocation> newList = new ArrayList<AnticoagProviderLocation>();
			  
				for(AnticoagProviderLocation result: numberofpatientformedactionplanforgraterrangeforintiationphase){
					AnticoagProviderLocation anticoagHigherpatient = new AnticoagProviderLocation();
					anticoagHigherpatient.setPatientid(result.getPatientid());
					anticoagHigherpatient.setFirst_name(result.getFirst_name());
					anticoagHigherpatient.setLast_name(result.getLast_name());
					anticoagHigherpatient.setMiddle_name(result.getMiddle_name());
					anticoagHigherpatient.setDate_of_birth(result.getDate_of_birth());
					newList.add(anticoagHigherpatient);
					//System.out.println("patientId of higher:::"+result.getPatientid());
				}
					
					for(AnticoagProviderLocation result_check: retrivePatientByAnticoagCategoryList){
						AnticoagProviderLocation antocogHighPatient =new AnticoagProviderLocation();
						antocogHighPatient.setPatientid(result_check.getPatientid());
						antocogHighPatient.setFirst_name(result_check.getFirst_name());
						antocogHighPatient.setLast_name(result_check.getLast_name());
						antocogHighPatient.setMiddle_name(result_check.getMiddle_name());
						antocogHighPatient.setDate_of_birth(result_check.getDate_of_birth());
						newList.add(antocogHighPatient);
						//System.out.println("patientId of high:::"+result_check.getPatientid());
					}
					newList.addAll(numberofpatientformedactionplanforgraterrangeforintiationphase);
					newList.addAll(retrivePatientByAnticoagCategoryList);
						logger.info("size of newList::::withDuplicate:::"+newList.size());
					Set removeDuplicatePatientDataInitiation = new HashSet(newList);
					newList.clear();
					newList.addAll(removeDuplicatePatientDataInitiation);
					logger.info("size of newList::::withOut-Duplicate:::"+newList.size());
					if(antocogHighPatient.getPatientid() == anticoagHigherpatient.getPatientid())
					 {
						if(!newList.contains(antocogHighPatient) && (!newList.contains(anticoagHigherpatient))){
							newList.add(antocogHighPatient);
						}
					 }
					else if(antocogHighPatient.getPatientid() != anticoagHigherpatient.getPatientid())
					{
						if(!newList.contains(anticoagHigherpatient) && (newList.contains(antocogHighPatient))){
							newList.add(anticoagHigherpatient);
							//newList.add(antocogHighPatient);
						}
					}
				  
				
				
				System.out.println("new list size############## "+newList.size()+" "+numberofpatientformedactionplanforgraterrangeforintiationphase.size());
				
				retrivePatientByAnticoagCategoryList.clear();
				System.out.println("size of high and higher:::::::"+retrivePatientByAnticoagCategoryList.size());
				retrivePatientByAnticoagCategoryList.addAll(newList);
				
				//retrivePatientByAnticoagCategoryList.addAll(numberofpatientformedactionplanforgraterrangeforintiationphase);
				
			}
			if(levelVal.equals("lm"))
			{
				System.out.println("inside low");
				String medicinestage=MAINTAINENCE_STAGE;
				int procedureType=2;
				String start_result="2";
				String end_result="3";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
			}
			if(levelVal.equals("mm"))
			{
				System.out.println("inside medium");
				String medicinestage=MAINTAINENCE_STAGE;
				int procedureType=2;
				String start_result="3";
				String end_result="4.5";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
			}
			if(levelVal.equals("hm"))
			{
				System.out.println("inside high");
				String medicinestage=MAINTAINENCE_STAGE;
				int procedureType=2;
				String start_result="0";
				String end_result="2";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
				System.out.println("high list=========="+retrivePatientByAnticoagCategoryList.size()+"higher list========== "+numberofpatientformedactionplanforgraterrange.size());
			    List<AnticoagProviderLocation> newListMaintenance = new ArrayList<AnticoagProviderLocation>();
			      
			    newListMaintenance.addAll(numberofpatientformedactionplanforgraterrange);
			    newListMaintenance.addAll(retrivePatientByAnticoagCategoryList);
			     logger.info("size of newList::::withDuplicate:::"+newListMaintenance.size());
			    Set removeDuplicatePatientDataMaintenance = new HashSet(newListMaintenance);
			    newListMaintenance.clear();
			    newListMaintenance.addAll(removeDuplicatePatientDataMaintenance);
			    logger.info("size of newList::::withOut-Duplicate:::"+newListMaintenance.size());
			    
			    System.out.println("new list size############## "+newListMaintenance.size()+" "+numberofpatientformedactionplanforgraterrange.size());
			    
			    retrivePatientByAnticoagCategoryList.clear();
			    System.out.println("size of high and higher:::::::"+retrivePatientByAnticoagCategoryList.size());
			    retrivePatientByAnticoagCategoryList.addAll(newListMaintenance);
			}
			//userLoginDetailList.addAll(retrivePatientByAnticoagCategoryList);
			//System.out.println("This is size of ther list-----"+retrivePatientByAnticoagCategoryList.size());
			//numberofpatientformedactionplanforinitiationphase.addAll(numberofpatientformedactionplanforgraterrangeforintiationphase);
			
		return retrivePatientByAnticoagCategoryList ;
				
	}
	

	public void setRetrivePatientByAnticoagCategoryList(
			List<AnticoagProviderLocation> retrivePatientByAnticoagCategoryList) {
		this.retrivePatientByAnticoagCategoryList = retrivePatientByAnticoagCategoryList;
	}*/
	
/*public List<UserLoginDetail> getRetrivePatientByAnticoagCategoryList() {
		
		if(levelVal==null){
			levelVal="";
		}
		System.out.println("Level Value coming from Javascript is in retrivePatientByAnticoagCategoryList:::"+levelVal);
		
		int logedUserId=new ContextUtil().getLoginId();
		//System.out.println("::::::::doctor id:::"+logedUserId);
		List<Integer>ClinicAndProviderIdList= userService.findClinicAndProviderId(logedUserId);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		
			if(levelVal.equals("li"))
			{
				System.out.println("inside low in initiation");
				String medicinestage="Initiation";
				int procedureType=2;
				String start_result="2";
				String end_result="3";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
				userLoginDetailList=retrivePatientByAnticoagCategoryList;
			}
			if(levelVal.equals("mi"))
			{
				System.out.println("inside medium in initiation");
				String medicinestage="Initiation";
				int procedureType=2;
				String start_result="3";
				String end_result="4.5";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
				userLoginDetailList=retrivePatientByAnticoagCategoryList;
			}
			if(levelVal.equals("hi"))
			{
				System.out.println("inside high in initiation");
				String medicinestage="Initiation";
				int procedureType=2;
				int flagCompare=0;
				String start_result="0";
				String end_result="2";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
				start_result="4.5";
				end_result="0";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
				
			   List<UserLoginDetail> newList = new ArrayList<UserLoginDetail>();
			  
				for(AnticoagProviderLocation result: numberofpatientformedactionplanforgraterrangeforintiationphase){
					AnticoagProviderLocation anticoagHigherpatient = new AnticoagProviderLocation();
					anticoagHigherpatient.setPatientid(result.getPatientid());
					anticoagHigherpatient.setFirst_name(result.getFirst_name());
					anticoagHigherpatient.setLast_name(result.getLast_name());
					anticoagHigherpatient.setMiddle_name(result.getMiddle_name());
					anticoagHigherpatient.setDate_of_birth(result.getDate_of_birth());
					newList.add(anticoagHigherpatient);
					//System.out.println("patientId of higher:::"+result.getPatientid());
				}
					
					for(AnticoagProviderLocation result_check: retrivePatientByAnticoagCategoryList){
						AnticoagProviderLocation antocogHighPatient =new AnticoagProviderLocation();
						antocogHighPatient.setPatientid(result_check.getPatientid());
						antocogHighPatient.setFirst_name(result_check.getFirst_name());
						antocogHighPatient.setLast_name(result_check.getLast_name());
						antocogHighPatient.setMiddle_name(result_check.getMiddle_name());
						antocogHighPatient.setDate_of_birth(result_check.getDate_of_birth());
						newList.add(antocogHighPatient);
						//System.out.println("patientId of high:::"+result_check.getPatientid());
					}
					//newList.addAll(numberofpatientformedactionplanforgraterrangeforintiationphase);
					newList.addAll(retrivePatientByAnticoagCategoryList);
						logger.info("size of newList::::withDuplicate:::"+newList.size());
					Set removeDuplicatePatientDataInitiation = new HashSet(newList);
					newList.clear();
					newList.addAll(removeDuplicatePatientDataInitiation);
					logger.info("size of newList::::withOut-Duplicate:::"+newList.size());
					if(antocogHighPatient.getPatientid() == anticoagHigherpatient.getPatientid())
					 {
						if(!newList.contains(antocogHighPatient) && (!newList.contains(anticoagHigherpatient))){
							newList.add(antocogHighPatient);
						}
					 }
					else if(antocogHighPatient.getPatientid() != anticoagHigherpatient.getPatientid())
					{
						if(!newList.contains(anticoagHigherpatient) && (newList.contains(antocogHighPatient))){
							newList.add(anticoagHigherpatient);
							//newList.add(antocogHighPatient);
						}
					}
				  
				
				
				System.out.println("new list size############## "+newList.size()+" "+numberofpatientformedactionplanforgraterrangeforintiationphase.size());
				
				retrivePatientByAnticoagCategoryList.clear();
				System.out.println("size of high and higher:::::::"+retrivePatientByAnticoagCategoryList.size());
				retrivePatientByAnticoagCategoryList.addAll(newList);
				
				//retrivePatientByAnticoagCategoryList.addAll(numberofpatientformedactionplanforgraterrangeforintiationphase);
				userLoginDetailList=retrivePatientByAnticoagCategoryList;
				
			}
			if(levelVal.equals("lm"))
			{
				System.out.println("inside low");
				String medicinestage=MAINTAINENCE_STAGE;
				int procedureType=2;
				String start_result="2";
				String end_result="3";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
				userLoginDetailList=retrivePatientByAnticoagCategoryList;
			}
			if(levelVal.equals("mm"))
			{
				System.out.println("inside medium");
				String medicinestage=MAINTAINENCE_STAGE;
				int procedureType=2;
				String start_result="3";
				String end_result="4.5";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
				userLoginDetailList=retrivePatientByAnticoagCategoryList;
			}
			if(levelVal.equals("hm"))
			{
				System.out.println("inside high");
				String medicinestage=MAINTAINENCE_STAGE;
				int procedureType=2;
				String start_result="0";
				String end_result="2";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
				start_result="4.5";
				end_result="0";
				retrivePatientByAnticoagCategoryList=userService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
				System.out.println("high list=========="+retrivePatientByAnticoagCategoryList.size()+"higher list========== "+numberofpatientformedactionplanforgraterrange.size());
			    List<UserLoginDetail> newListMaintenance = new ArrayList<UserLoginDetail>();
			      
			    //newListMaintenance.addAll(numberofpatientformedactionplanforgraterrange);
			    newListMaintenance.addAll(retrivePatientByAnticoagCategoryList);
			     logger.info("size of newList::::withDuplicate:::"+newListMaintenance.size());
			    Set removeDuplicatePatientDataMaintenance = new HashSet(newListMaintenance);
			    newListMaintenance.clear();
			    newListMaintenance.addAll(removeDuplicatePatientDataMaintenance);
			    logger.info("size of newList::::withOut-Duplicate:::"+newListMaintenance.size());
			    
			    System.out.println("new list size############## "+newListMaintenance.size()+" "+numberofpatientformedactionplanforgraterrange.size());
			    
			    retrivePatientByAnticoagCategoryList.clear();
			    System.out.println("size of high and higher:::::::"+retrivePatientByAnticoagCategoryList.size());
			    retrivePatientByAnticoagCategoryList.addAll(newListMaintenance);
			    userLoginDetailList=retrivePatientByAnticoagCategoryList;
			}
			userLoginDetailList=retrivePatientByAnticoagCategoryList;
			Set removeDuplicatePatientDataMaintenance = new HashSet(userLoginDetailList);
			userLoginDetailList.clear();
			userLoginDetailList.addAll(removeDuplicatePatientDataMaintenance);
			if(retrivePatientByAnticoagCategoryList != null)
			{
				userLoginDetailList.addAll(retrivePatientByAnticoagCategoryList);
			}
			
			//System.out.println("This is size of ther list-----"+retrivePatientByAnticoagCategoryList.size());
			//numberofpatientformedactionplanforinitiationphase.addAll(numberofpatientformedactionplanforgraterrangeforintiationphase);
			
		return retrivePatientByAnticoagCategoryList ;
				
	}

public void setRetrivePatientByAnticoagCategoryList(
		List<UserLoginDetail> retrivePatientByAnticoagCategoryList) {
	this.retrivePatientByAnticoagCategoryList = retrivePatientByAnticoagCategoryList;
}

   
	//Method to find out the Anticoag Patient List based on category by Anand S Jha
	public String fetchPatientByAnticoag()
	{
		getNumberofpatientformedactionplanforhigherendofpatientforintiationphase();
		System.out.println("Level Value coming from Javascript is:::"+levelVal);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.setAttribute("levelVal1", levelVal);
		getRetrivePatientByAnticoagCategoryList();
		 System.out.println("After getting values");
		//getUniqueResultList();
		System.out.println("After making unique");
		RequestContext.getCurrentInstance().execute("PF('dlgForAnticoagClinicStatusLow').show()");
		//System.out.println("   "+session.getAttribute("levelVal1"));
		//getNumberOfPatientforanticoagforintiationphase();
		levelVal=null;
		
		System.out.println("This is size of ther list-----"+getRetrivePatientByAnticoagCategoryList().size());
		
		return null;
		
	}*/
	
	//Method to generate full insurance list
	public void setInsuranceDetailListBarFull(List<InsuranceCompanies> insuranceDetailListBarFull) {
		this.insuranceDetailListBarFull = insuranceDetailListBarFull;
	}
	/*	
	
	 * find clinic D3 Js Bar Chart Detail According to particular provider..
	 * Author Sanket Singh
	 * 
	 * 
*/

	public String getInsuranceDetailListBarFull() {
		/*logger.info("getInsuranceDetailListBar Full method");
		int providerId=new ContextUtil().getProviderId();
		List<ChartModel>insuranceDetailListBarFull=userService.getInsuranceChartForProvider(providerId,true);
	return new Gson().toJson(insuranceDetailListBarFull);*/
		return null; 

	}

 /*For Background charts loading 
  * added by vinod*/
	 public void backgroungLoadingForCharts(){
		 try{
		 logger.info("backgroundloading for Chart in usermanageBean::::::::");
		 findDoctorProfileDetail();
		 
		 RequestContext context = RequestContext.getCurrentInstance();
		 clinicMasterList = new ArrayList<ClinicMaster>();
		 getClinicmasterList();
		 if(clinicMasterList.isEmpty()){
			//List<ClinicMaster>clinicMasterList=new ArrayList<ClinicMaster>();
			List<Object> clinicPieChartList=userService.findClinicPieChart(new ContextUtil().getProviderId());
			for(Object obj: clinicPieChartList){
				  Object[] row = (Object[]) obj;
				  String clinicName=findClinicNameAccordingToId(clinicmasterList,Integer.valueOf(String.valueOf(row[0])));
				  int value=0;
				  value=(Integer.valueOf(String.valueOf(row[0]))*100)/(clinicPieChartList.size());
				ClinicMaster clinicMaster=new ClinicMaster();
				clinicMaster.setClinicName(clinicName);
				clinicMaster.setPercentage(value);
				clinicMasterList.add(clinicMaster);
			}
			System.out.println("clinicMasterList:::::::::::size "+clinicMasterList.size());
		 }
	      String clinicsData = new Gson().toJson(clinicMasterList);
			context.addCallbackParam("clinicChartData", clinicsData);
			
			
			/*insuranceDetailListBar=new ArrayList<InsuranceCompanies>();
			List<Object> userInsuranceDetailsList=userService.findInsuranceGraphDetail(new ContextUtil().getClinicProviderId(),numberOfPatientParticularProvider,insuranceCompaniesList);
			System.out.println("userInsuranceDetailsList value:::::::::"+userInsuranceDetailsList.size());
			System.out.println("clinicproviderId::::::::::::"+new ContextUtil().getClinicProviderId());
			int count=0;
		for(Object obj: userInsuranceDetailsList){
			  Object[] row = (Object[]) obj;
				  String insuranceName=findInsurancenameAccordingToId(insuranceCompaniesList,(Integer)row[0]);
				  int value=0;
				   value=Integer.valueOf(String.valueOf(row[1]));
				  System.out.println(":::::::::::::>>>>>insuranceName::"+insuranceName+"::value:::::"+value);
				 if(insuranceName.isEmpty())
				  {
					  break;
				  }
				 InsuranceCompanies insuranceCompanies=new InsuranceCompanies ();
				 insuranceCompanies.setCompanyName(insuranceName);
				  insuranceCompanies.setCompanyValue(value);
				  
				System.out.println("companyName::::::::::"+insuranceCompanies.getCompanyName()+"companyValue:::::::::"+insuranceCompanies.getCompanyValue());
				  insuranceDetailListBar.add(insuranceCompanies);
				 if(count>=4)
					{
						break;
					}
					count++;
		}*/
			int providerId=new ContextUtil().getProviderId();
			List<ChartModel>insuranceDetailListBar=userService.getInsuranceChartForProvider(providerId,false);
		String insuranceChart =  new Gson().toJson(insuranceDetailListBar);
          context.addCallbackParam("insuranceChartData", insuranceChart);
          
          
          //Added By Nagaraj on 18/dec/2014 for background loading of Anticoag Analytyic chart
          List<AnticoagProviderLocation>numberofpatientformedactionplanforinitiationphaseList=loadNumberofpatientformedactionplanforinitiationphase();
          List<AnticoagProviderLocation>numberofpatientformedactionplanList=loadNumberofpatientformedactionplan();
          List<AnticoagProviderLocation>numberofpatientformedactionplanforhigherendofpatientforintiationphaseList=loadNumberofpatientformedactionplanforhigherendofpatientforintiationphase();
          List<AnticoagProviderLocation>numberofpatientformedactionplanforhigherendofpatientList=loadNumberofpatientformedactionplanforhigherendofpatient();
          List<AnticoagProviderLocation>numberOfPatientforanticoagclinicforintiationphaseList=loadNumberOfPatientforanticoagclinicforintiationphase();
          List<AnticoagProviderLocation>numberOfPatientforanticoagclinicList=loadNumberOfPatientforanticoagclinic();
          
          context.addCallbackParam("numberofpatientformedactionplanforinitiationphase", numberofpatientformedactionplanforinitiationphaseList.size());
          context.addCallbackParam("numberofpatientformedactionplan", numberofpatientformedactionplanList.size());
          context.addCallbackParam("numberofpatientformedactionplanforhigherendofpatientforintiationphase", numberofpatientformedactionplanforhigherendofpatientforintiationphaseList.size());
          context.addCallbackParam("numberofpatientformedactionplanforhigherendofpatient", numberofpatientformedactionplanforhigherendofpatientList.size());
          context.addCallbackParam("numberOfPatientforanticoagclinicforintiationphase", numberOfPatientforanticoagclinicforintiationphaseList.size());
          context.addCallbackParam("numberOfPatientforanticoagclinic", numberOfPatientforanticoagclinicList.size());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }

	




	




	/**
	  * 
	  * Added By Nagaraj 18/dec/2014 for loading in background after user has logged in successfully
	  * loadNumberofpatientformedactionplanforinitiationphase
	  * @return
	  */
	private List<AnticoagProviderLocation> loadNumberofpatientformedactionplanforinitiationphase() {
		numberofpatientformedactionplanforinitiationphase = new ArrayList<AnticoagProviderLocation>();
		
		getClinicAndProviderIdList();
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		
		String medicinestage="Initiation";
		int procedureType=2;
		String start_result="0";
		String end_result="2";
		String statusID="hi";
		
			//numberofpatientformedactionplanforinitiationphase=userService.findnumberofpatientformedactionplanforintiationphase(ClinicAndProviderIdList.get(2));
			numberofpatientformedactionplanforinitiationphase=userService.findnumberofpatientformedactionplanforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);
		System.out.println("numberofpatientformedactionplanforinitiationphase size######### "+numberofpatientformedactionplanforinitiationphase.size());
		//getNumberofpatientformedactionplanforgraterrangeforintiationphase();
		//numberofpatientformedactionplanforinitiationphase.addAll(numberofpatientformedactionplanforgraterrangeforintiationphase);
		Set removingDuplicateFormHighAndHigherAnticogList = new HashSet(numberofpatientformedactionplanforinitiationphase);
		//removingDuplicateFormHighAndHigherAnticogList.add(numberofpatientformedactionplanforinitiationphase);
		numberofpatientformedactionplanforinitiationphase.clear();
		numberofpatientformedactionplanforinitiationphase.addAll(removingDuplicateFormHighAndHigherAnticogList);
				
		System.out.println("medactionpatientlist=== "+numberofpatientformedactionplanforinitiationphase.size());
		return numberofpatientformedactionplanforinitiationphase;
	}
	
	/**
	  * 
	  * Added By Nagaraj 18/dec/2014 for loading in background after user has logged in successfully
	  * loadNumberofpatientformedactionplan
	  * @return
	  */
	 private List<AnticoagProviderLocation> loadNumberofpatientformedactionplan() {
			
		 numberofpatientformedactionplan = new ArrayList<AnticoagProviderLocation>();
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
			session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
			session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
			int procedureType=2;
			String start_result="0";
			String end_result="0";
			String medicinestage=MAINTAINENCE_STAGE;
			String statusID="hm";
			
				//numberofpatientformedactionplan=userService.findnumberofpatientformedactionplan(ClinicAndProviderIdList.get(2));
				numberofpatientformedactionplan=userService.findnumberofpatientformedactionplan(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);
			System.out.println("numberofpatientformedactionplan size@@@@@@@@@@@@ before "+numberofpatientformedactionplan.size());
			//getNumberofpatientformedactionplanforgraterrange();
			//numberofpatientformedactionplan.addAll(numberofpatientformedactionplanforgraterrange);
			Set uniqueSetValues = new HashSet(numberofpatientformedactionplan);
			numberofpatientformedactionplan.clear();
			numberofpatientformedactionplan.addAll(uniqueSetValues);
			System.out.println("numberofpatientformedactionplan size@@@@@@@@@@@@ "+numberofpatientformedactionplan.size());
			return numberofpatientformedactionplan;
		}
	

	 /**
	  * 
	  * Added By Nagaraj 18/dec/2014 for loading in background after user has logged in successfully
	  * loadNumberofpatientformedactionplanforhigherendofpatientforintiationphase
	  * @return
	  */
		private List<AnticoagProviderLocation> loadNumberofpatientformedactionplanforhigherendofpatientforintiationphase() {
			logger.info("getNumberofpatientformedactionplanforhigherendofpatientforintiationphase::::::");
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
			session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
			session.setAttribute("providerId", ClinicAndProviderIdList.get(2)); //levelVal
			
			String medicinestage="Initiation";
			int procedureType=2;
			String start_result="3";
			String end_result="4.5";
			String statusID="mi";
			numberofpatientformedactionplanforhigherendofpatientforintiationphase=new ArrayList<AnticoagProviderLocation>();
			
			numberofpatientformedactionplanforhigherendofpatientforintiationphase=userService.findnumberofpatientforanticoagclinicforhigerendofpatientforintiation(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);
			return numberofpatientformedactionplanforhigherendofpatientforintiationphase;
		}
		
		 /**
		  * 
		  * Added By Nagaraj 18/dec/2014 for loading in background after user has logged in successfully
		  * loadNumberofpatientformedactionplanforhigherendofpatient
		  * @return
		  */
		private List<AnticoagProviderLocation> loadNumberofpatientformedactionplanforhigherendofpatient() {
			logger.info("getNumberofpatientformedactionplanforhigherendofpatient:::::");
			
				numberofpatientformedactionplanforhigherendofpatient = new ArrayList<AnticoagProviderLocation>();
		
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
			session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
			session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
			
			String medicinestage=MAINTAINENCE_STAGE;
			int procedureType=2;
			String start_result="0";
			String end_result="0";
			String statusID="lm";
			
			numberofpatientformedactionplanforhigherendofpatient=userService.findnumberofpatientforanticoagclinicforhigerendofpatient(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);
			System.out.println("numberofpatientformedactionplanforhigherendofpatient  ^^^^^^^^^^^^ "+numberofpatientformedactionplanforhigherendofpatient.size());
			return numberofpatientformedactionplanforhigherendofpatient;
		}
		
		 /**
		  * 
		  * Added By Nagaraj 18/dec/2014 for loading in background after user has logged in successfully
		  * loadNumberofpatientformedactionplanforhigherendofpatient
		  * @return
		  */
		private List<AnticoagProviderLocation> loadNumberOfPatientforanticoagclinicforintiationphase() {
			logger.info("getNumberOfPatientforanticoagclinicforintiationphase:::::::::"+levelVal);
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
			session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
			session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
			
			String medicinestage="Initiation";
			int procedureType=2;
			String start_result="2";
			String end_result="3";
			String statusID="li";
			numberOfPatientforanticoagclinicforintiationphase=new ArrayList<AnticoagProviderLocation>();
			numberOfPatientforanticoagclinicforintiationphase=userService.findnumberofpatientforanticoagclinicforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);
			return numberOfPatientforanticoagclinicforintiationphase;
		}

		 /**
		  * 
		  * Added By Nagaraj 18/dec/2014 for loading in background after user has logged in successfully
		  * loadNumberOfPatientforanticoagclinic
		  * @return
		  */
		private List<AnticoagProviderLocation> loadNumberOfPatientforanticoagclinic() {
			logger.info("getNumberOfPatientforanticoagclinic:::::");
			
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
			session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
			session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
			
			int procedureType=2;
			String start_result="0";
			String end_result="0";
			String medicinestage=MAINTAINENCE_STAGE;
			String statusID="mm";
			numberOfPatientforanticoagclinic=new ArrayList<AnticoagProviderLocation>();
			numberOfPatientforanticoagclinic=userService.findnumberofpatientforanticoagclinic(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,statusID);
			return numberOfPatientforanticoagclinic;
		}
		
	



	
	

	public RolePrivillegeUtil getRolePrivillegeUtil() {
		return rolePrivillegeUtil;
	}


	public void setRolePrivillegeUtil(RolePrivillegeUtil rolePrivillegeUtil) {
		this.rolePrivillegeUtil = rolePrivillegeUtil;
	}


	public String getAverageMedicine() {
		return averageMedicine;
	}


	public void setAverageMedicine(String averageMedicine) {
		this.averageMedicine = averageMedicine;
	}


	public String getAverageDiagnoses() {
		return averageDiagnoses;
	}


	public void setAverageDiagnoses(String averageDiagnoses) {
		this.averageDiagnoses = averageDiagnoses;
	}


	public String getAveragePhysicians() {
		return averagePhysicians;
	}


	public void setAveragePhysicians(String averagePhysicians) {
		this.averagePhysicians = averagePhysicians;
	}

/*
 * ***********************USED ONLY FOR CORRECT OLD PHARMACY DATA
 * *******************added by @saurabh to update
 * *****************pbm_drug_result and care_team
 * at the time of removing this method, remove this method from service and dao layer
 */
	public void convertPBMDrugHistoryXMLToSaxParserForUpdateDoctorName(){
		logger.info("<<<<<<<<<<<<<<<<<<<<<<<METHOD convertPBMDrugHistoryXMLToSaxParserForUpdateDoctorName CALLED IN UserManageBean>>>>>>>>>>>>>>>>>>>>>");
		 try {
			 List<UserLoginDetail> patientDetailList=userService.getPatientList();
				System.out.println(patientDetailList.size()+"timePeriod: ::::::::"+timePeriod);
					timePeriod=4;
				for(UserLoginDetail patientDetail:patientDetailList){
					logger.info(">>>>>>>>>>>>>>>>INSIDE FOR LOOP METHOD CALL ACCORDING TO PATIENT-ID>>>>>>>>>>>>>>>>>>"+patientDetail.getUserId());
		//-------------at the time of removing this method, remove this method from service and dao layer
					userService.deletePatientDrugData(patientDetail.getUserId());
					 patientPbmDrugHistoryDetailList=userService.getAllPatientPbmDrugHistoryDetail(patientDetail.getUserId());
					 List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultList=update1wsBean.convertPBMDrugHistoryXMLToSaxParser(timePeriod, getProviderLocation(), getRoleSecurity(),patientPbmDrugHistoryDetailList);
						for(PatientPBMDrugHistoryResult drugHistoryResult:patientPBMDrugHistoryResultList){
							logger.info(" drugHistoryResult.getDrugInfo():::::::::   "+drugHistoryResult.getDrugInfo()+"   drugHistoryResult.getPatientId():::::::  "+drugHistoryResult.getPatientId()+"  drugHistoryResult.getFillDate() :::::::: "+drugHistoryResult.getFillDate());
						userService.savePatientPBMDrugHistoryResult(drugHistoryResult);
						}
						List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultValueList=userService.getAllPatientPBMDrugHistoryResultValue(patientDetail.getUserId());
						userService.updateHealthPlanAndPharmacyDetail(patientDetail.getUserId());
						userService.careTeamIntegration(patientDetail.getUserId());
						logger.info(patientPBMDrugHistoryResultValueList.size()+"!:::::::::");
						//write compliance caluclation..
						logger.info(">>>>>>>>>>>>>>>>FOR LOOP METHOD COMPLETEDDDDDDDDDDDD>>>>>>>>>>>>>>>>>>"+patientDetail.getUserId());
				}
				userService.updateComplianceValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 logger.info("<<<<<<<<<<<<<<<<<<<<<<<METHOD convertPBMDrugHistoryXMLToSaxParserForUpdateDoctorName COMPLETEDDDDDDDD IN UserManageBean>>>>>>>>>>>>>>>>>>>>>"); 
	}


	/**
	 * @return the patientDataWithPatientConsentForBatchDataPullProcessList
	 */
	public List<UserLoginDetail> getPatientDataWithPatientConsentForBatchDataPullProcessList() {
		if(patientDataWithPatientConsentForBatchDataPullProcessList==null){
			patientDataWithPatientConsentForBatchDataPullProcessList=new ArrayList<UserLoginDetail>();
			/*patientDataWithPatientConsentForBatchDataPullProcessList=userService.getpatientDataWithPatientConsentForDataPullProcess(new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());*/
			patientDataWithPatientConsentForBatchDataPullProcessList=userService.getAllpatientDataWithPatientConsentForDataPullProcess(new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());
		}
		
		
		
		return patientDataWithPatientConsentForBatchDataPullProcessList;
	}


	/**
	 * @param patientDataWithPatientConsentForBatchDataPullProcessList the patientDataWithPatientConsentForBatchDataPullProcessList to set
	 */
	public void setPatientDataWithPatientConsentForBatchDataPullProcessList(
			List<UserLoginDetail> patientDataWithPatientConsentForBatchDataPullProcessList) {
		this.patientDataWithPatientConsentForBatchDataPullProcessList = patientDataWithPatientConsentForBatchDataPullProcessList;
	}


	/**
	 * @return the selectedUserLoginDetail
	 */
	public UserLoginDetail[] getSelectedUserLoginDetail() {
		return selectedUserLoginDetail;
	}


	/**
	 * @param selectedUserLoginDetail the selectedUserLoginDetail to set
	 */
	public void setSelectedUserLoginDetail(UserLoginDetail[] selectedUserLoginDetail) {
		System.out.println("Selected " + selectedUserLoginDetail.length );
		this.selectedUserLoginDetail = selectedUserLoginDetail;
	}
	
	/**
	 * Batch Data Pulling For
	 * Patient Consent Grant Patient 
	 * @author Anjani
	 * @throws SQLException 
	 * @throws HibernateException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public void batchDataPullingForPatientConsentGrantPatient() throws HibernateException, SQLException, ParserConfigurationException, SAXException, IOException{/*
		logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*****************batchDataPulling process optimization="+new Date());
		List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList=new ArrayList<PatientPbmDrugHistoryDetail>();
		 List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultList=new ArrayList<PatientPBMDrugHistoryResult>();
		 totalNoOfPatientPulled=0;
		 totalNoOfPatientMedicalRecordPulled=0;
		 noOfPatientNotGetRecord=0;
		 totalNoOfSelectedPatient=0;
		 progressBatchProcess=0;
		 completedPerc=0;
		 int count=0;
		 patientHaveMedications=0;
		 
		if(timePeriod==0){
			timePeriod=48;
		}
		else {
			timePeriod=timePeriod;
		}
		
		int patNoSequance=0;
		double compltedSize=0.0;
		
		List<List<UserLoginDetail>> splitedUserDetailList=null;
		if (!(selectedUserLoginDetail.length==0)) {
			logger.info("*********batchDataPullingForPatientConsentGrantPatient starts********if block selectedUserLoginDetail*********batchDataPulling process optimization="+new Date());
			
		
			 splitedUserDetailList=com.google.common.collect.Lists.partition(Arrays.asList(selectedUserLoginDetail), SELECTED_PATIENT_SPLIT_SIZE);
			
			for(List<UserLoginDetail> subListOfSelectedUserDetail:splitedUserDetailList){
				
				 compltedSize+=subListOfSelectedUserDetail.size();
				
				logger.info("splitedUserDetailList:::::::::::Size "+splitedUserDetailList.size()+"Main List ::::::::;;"+selectedUserLoginDetail.length+"completed Size "+compltedSize);
				for(UserLoginDetail patientDetailBatchPull: subListOfSelectedUserDetail){
					
			       totalNoOfSelectedPatient=selectedUserLoginDetail.length;
			       logger.info("total no of selected patient :::::::::::"+totalNoOfSelectedPatient);
					for(UserLoginDetail patientDetailBatchPull:selectedUserLoginDetail){
						patNoSequance++;
						logger.info("*********batchDataPullingForPatientConsentGrantPatient starts**********delete query*******batchDataPulling process optimization="+new Date());	
						userService.deletePatientPBMDrugHistoryDetailResult(patientDetailBatchPull.getUserId());
						userService.deletePatientPBMDrugHistoryResult(patientDetailBatchPull.getUserId());
						logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*********delete query ends********batchDataPulling process optimization="+new Date());
						
						logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******calling callGetPBMDrugHistoryV2**********batchDataPulling process optimization="+new Date());
						patientPbmDrugHistoryDetailList=update1wsBean.callGetPBMDrugHistoryV2(timePeriod, getProviderLocation(), getRoleSecurity(), patientDetailBatchPull);
						logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******ends callGetPBMDrugHistoryV2**********batchDataPulling process optimization="+new Date());
						
						System.out.println("drugHistoryDetailResultList.size() "+patientPbmDrugHistoryDetailList.size());
						
						for(PatientPbmDrugHistoryDetail patientPbmDrugHistoryDetail:patientPbmDrugHistoryDetailList){
							logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******calling savePatientPBMDrugHistoryDetailResult**********batchDataPulling process optimization="+new Date());	
							userService.savePatientPBMDrugHistoryDetailResult(patientPbmDrugHistoryDetail);
							logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******ends savePatientPBMDrugHistoryDetailResult**********batchDataPulling process optimization="+new Date());
						count++;
						if(org.apache.commons.lang.StringUtils.isNotEmpty(patientPbmDrugHistoryDetail.getPbmDrugHistoryXmlResponse())){
							patientHaveMedications++;
						}
						logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******calculating completedPerc**********batchDataPulling process optimization="+new Date());
						completedPerc=  (int) ((count/(double)selectedUserLoginDetail.length)*100);
						logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******ends calculating completedPerc**********batchDataPulling process optimization="+new Date());
						logger.info("completedPerc::::::::::::::;"+completedPerc);
						
						}
						
						logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******calling getAllPatientPbmDrugHistoryDetail**********batchDataPulling process optimization="+new Date());
						 patientPbmDrugHistoryDetailList=userService.getAllPatientPbmDrugHistoryDetail(patientDetailBatchPull.getUserId());
						 logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******ends getAllPatientPbmDrugHistoryDetail**********batchDataPulling process optimization="+new Date());
						 
						 logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******calling convertPBMDrugHistoryXMLToSaxParser**********batchDataPulling process optimization="+new Date());
						 patientPBMDrugHistoryResultList=update1wsBean.convertPBMDrugHistoryXMLToSaxParser(timePeriod, getProviderLocation(), getRoleSecurity(),patientPbmDrugHistoryDetailList);
						
						 logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******ends convertPBMDrugHistoryXMLToSaxParser**********batchDataPulling process optimization="+new Date());
							for(PatientPBMDrugHistoryResult drugHistoryResult:patientPBMDrugHistoryResultList){
								System.out.println(" drugHistoryResult.getDrugInfo():::::::::   "+drugHistoryResult.getDrugInfo()+"   drugHistoryResult.getPatientId():::::::  "+drugHistoryResult.getPatientId()+"  drugHistoryResult.getFillDate() :::::::: "+drugHistoryResult.getFillDate());
								 logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******calling savePatientPBMDrugHistoryResult**********batchDataPulling process optimization="+new Date());
								   userService.savePatientPBMDrugHistoryResult(drugHistoryResult);
								 logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******ends savePatientPBMDrugHistoryResult**********batchDataPulling process optimization="+new Date());
							}
							
							logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******calling getAllPatientPBMDrugHistoryResultValue**********batchDataPulling process optimization="+new Date());
							List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultValueList=userService.getAllPatientPBMDrugHistoryResultValue(patientDetailBatchPull.getUserId());
							logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******ends patientPBMDrugHistoryResultValueList**********batchDataPulling process optimization="+new Date());
							
							ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeDrugDetailInfoList=new ArrayList<PatientFormularyCompositeDrugDetailInfo>();
							ArrayList<PatientFormularyCompositeCopayInfo> formularyCompositeCopayInfoList=new ArrayList<PatientFormularyCompositeCopayInfo>();
							
							logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******calling updateHealthPlanAndPharmacyDetail**********batchDataPulling process optimization="+new Date());
							userService.updateHealthPlanAndPharmacyDetail(patientDetailBatchPull.getUserId());
							logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******ends updateHealthPlanAndPharmacyDetail**********batchDataPulling process optimization="+new Date());
							
							logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******calling careTeamIntegration**********batchDataPulling process optimization="+new Date());
							userService.careTeamIntegration(patientDetailBatchPull.getUserId());
							logger.info("*********batchDataPullingForPatientConsentGrantPatient starts*******ends careTeamIntegration**********batchDataPulling process optimization="+new Date());
							
							logger.info(patientPBMDrugHistoryResultValueList.size()+"!:::::::::");
							
							progressBatchProcess=patNoSequance;
							logger.info("Part Completed :::::::::::: percent"+((patNoSequance/selectedUserLoginDetail.length)));
							
							
							logger.info("progressBatchProcess::::::"+progressBatchProcess);
							totalNoOfSelectedPatient=selectedUserLoginDetail.length;
							
							
					     }
				}
				
				
			}
			logger.info("Splited List Size "+splitedUserDetailList.size());
			//For capturing Interactions
			
			
			
			try{
				FacesContext context = FacesContext	.getCurrentInstance();
				BatchReconcileProcess batchReconcileProcessObj=(BatchReconcileProcess)context.getApplication().getELResolver().getValue(context.getELContext(), null,"batchReconcileProcess");
				//batchReconcileProcessObj.autoFillCurrentMedsFromPharmacyMeds(selectedUserLoginDetail, timePeriod);
			}catch(Exception e){
				e.printStackTrace();
			}
			try{
			FacesContext context1 = FacesContext	.getCurrentInstance();
			BatchInteractionBean batchInteractionBean=(BatchInteractionBean)context1.getApplication().getELResolver().getValue(context1.getELContext(), null,"batchInteractionBean");
			//batchInteractionBean.fetchInteractionsData(selectedUserLoginDetail);
			}catch(Exception e){
				e.printStackTrace();
			}
		       }
		     String msg="Total number of records pulled from NewCrop = "+patientPbmDrugHistoryDetailList.size();
		     FacesContext.getCurrentInstance().addMessage("messageUpdateForDataPull", new FacesMessage(FacesMessage.SEVERITY_INFO,msg,"")); 
		     totalNoOfPatientPulled=count;
	         totalNoOfPatientMedicalRecordPulled=patientPBMDrugHistoryResultList.size();
	     
	         noOfPatientNotGetRecord=selectedUserLoginDetail.length - totalNoOfPatientPulled;
	         
		     
		    
		     logger.info("Total no of selected patient :::::::;"+selectedUserLoginDetail.length+"total recored pulled "+totalNoOfPatientMedicalRecordPulled+"noOfPatientNotGetRecord"+noOfPatientNotGetRecord);
		     totalNoOfSelectedPatient=selectedUserLoginDetail.length;
		     getPatientConsentDataWithFilter();
		     completedPerc=0;
		     RequestContext requestContext = RequestContext.getCurrentInstance();    
		     requestContext.execute("batchDataPullResult.show()");
		     logger.info("*********batchDataPullingForPatientConsentGrantPatient ENDS*****************batchDataPulling process optimization="+new Date());
	         */}


	/**
	 * @return the patientBatchDateList
	 */
	public List<Date> getPatientBatchDateList() {
		if(patientBatchDateList==null){
			patientBatchDateList=new ArrayList<Date>();
			
		}
		patientBatchDateList=userService.getAllPatientBatchDataData(new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());
		return patientBatchDateList;
	}


	/**
	 * @param patientBatchDateList the patientBatchDateList to set
	 */
	public void setPatientBatchDateList(List<Date> patientBatchDateList) {
		this.patientBatchDateList = patientBatchDateList;
	}


	/**
	 * @return the patientBatchNumberDataList
	 */
	public List<Integer> getPatientBatchNumberDataList() {
		if(patientBatchNumberDataList==null){
			
			patientBatchNumberDataList= new ArrayList<Integer>();
		}
		
		
			patientBatchNumberDataList=userService.getPatientBatchNumberData(new Date(),new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());
		
		return patientBatchNumberDataList;
	}


	/**
	 * @param patientBatchNumberDataList the patientBatchNumberDataList to set
	 */
	public void setPatientBatchNumberDataList(
			List<Integer> patientBatchNumberDataList) {
		this.patientBatchNumberDataList = patientBatchNumberDataList;
	}


	/**
	 * @return the patientBatchDate
	 */
	public String getPatientBatchDate() {
		return patientBatchDate;
	}


	/**
	 * @param patientBatchDate the patientBatchDate to set
	 */
	public void setPatientBatchDate(String patientBatchDate) {
		this.patientBatchDate = patientBatchDate;
	}


	/**
	 * Get Patient Data With Patient Consent With Batch No Filter
	 * 
	 */
	public void getPatientConsentDataWithFilter(){
		/*logger.info("::::inside getPatientConsentDataWithFilter in usermanageBean, providerId="+new ContextUtil().getProviderId()+
				" ::::providerLocationId= "+new ContextUtil().getProviderLocationId()+" :::getPatientBatchDate()= "+getPatientBatchDate()+
				" :::userLoginDetail.getPatientBatchNo= "+userLoginDetail.getPatientBatchNo()+" :::patientConsent= "+patientConsent);*/
		if(StringUtils.isEmpty(getPatientBatchDate()) && userLoginDetail.getPatientBatchNo()==0){
			patientDataWithPatientConsentForBatchDataPullProcessList=userService.getAllpatientDataWithPatientConsentForDataPullProcess(new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());
		}
		else if ((StringUtils.isEmpty(getPatientBatchDate())) && userLoginDetail.getPatientBatchNo()!=0) {
			patientDataWithPatientConsentForBatchDataPullProcessList=userService.getPatientDataWithPatientConsentByBatchNoFilter(new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId(),getPatientBatchDate(),userLoginDetail.getPatientBatchNo(),patientConsent);
		}
		else if (!(StringUtils.isEmpty(getPatientBatchDate()))  &&  userLoginDetail.getPatientBatchNo()==0) {
			patientDataWithPatientConsentForBatchDataPullProcessList=userService.getAllPatientDataForBatchPullBasedOnBatchDate(new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId(),getPatientBatchDate(),userLoginDetail.getPatientBatchNo(),patientConsent);
		}
		else if ( !(StringUtils.isEmpty(getPatientBatchDate())) &&  userLoginDetail.getPatientBatchNo()!=0) {
			patientDataWithPatientConsentForBatchDataPullProcessList=userService.getAllPatientDataForBatchPullBasedOnBatchDateAndBatchNo(new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId(),getPatientBatchDate(),userLoginDetail.getPatientBatchNo(),patientConsent);
		}
		
		
		
		
		
	}



	/**
	 * @return the patientConsent
	 */
	public char getPatientConsent() {
		return patientConsent;
	}


	/**
	 * @param patientConsent the patientConsent to set
	 */
	public void setPatientConsent(char patientConsent) {
		this.patientConsent = patientConsent;
	}



	public String getTotalNumberOfDrugHistoryRecordsPulled() {
		return totalNumberOfDrugHistoryRecordsPulled;
	}


	public void setTotalNumberOfDrugHistoryRecordsPulled(
			String totalNumberOfDrugHistoryRecordsPulled) {
		this.totalNumberOfDrugHistoryRecordsPulled = totalNumberOfDrugHistoryRecordsPulled;
	}


	public String getNumberOfRowsPerPatient() {
		return numberOfRowsPerPatient;
	}


	public void setNumberOfRowsPerPatient(String numberOfRowsPerPatient) {
		this.numberOfRowsPerPatient = numberOfRowsPerPatient;
	}


	public void loadInsuranceChart() {
		RequestContext context=RequestContext.getCurrentInstance();
		int providerId=new ContextUtil().getProviderId();
		List<ChartModel>insuranceDetailListBar=userService.getInsuranceChartForProvider(providerId,false);
		/*getInsuranceCompaniesList();
		List<Object> userInsuranceDetailsList=userService.findInsuranceGraphDetail(new ContextUtil().getClinicProviderId(),numberOfPatientParticularProvider,insuranceCompaniesList);
		System.out.println("userInsuranceDetailsList value:::::::::"+userInsuranceDetailsList.size());
		System.out.println("clinicproviderId::::::::::::"+new ContextUtil().getClinicProviderId());
		int count=0;
	for(Object obj: userInsuranceDetailsList){
		  Object[] row = (Object[]) obj;
			  String insuranceName=findInsurancenameAccordingToId(insuranceCompaniesList,(Integer)row[0]);
			  int value=0;
			   value=Integer.valueOf(String.valueOf(row[1]));
			  System.out.println(":::::::::::::>>>>>insuranceName::"+insuranceName+"::value:::::"+value);
			 if(insuranceName.isEmpty())
			  {
				  break;
			  }
			 InsuranceCompanies insuranceCompanies=new InsuranceCompanies ();
			 insuranceCompanies.setCompanyName(insuranceName);
			  insuranceCompanies.setCompanyValue(value);
			  
			System.out.println("companyName::::::::::"+insuranceCompanies.getCompanyName()+"companyValue:::::::::"+insuranceCompanies.getCompanyValue());
			  insuranceDetailListBar.add(insuranceCompanies);
			 if(count>=4)
				{
					break;
				}
				count++;
	}*/
		
		
	String insuranceChart =  new Gson().toJson(insuranceDetailListBar);
      context.addCallbackParam("insuranceChartData", insuranceChart);
	}


	public MeterGaugeChartModel getEnbrelAdherenceChartModel() {
		enbrelAdherenceChartModel = findEnbrelComplianceMeterDetail();
	       System.out.println("Value of meter:"+enbrelAdherenceChartModel.getValue());
	       if(enbrelAdherenceChartModel.getValue()==null){
	    	   enbrelAdherenceChartModel.setValue(0);
	       }
		return enbrelAdherenceChartModel;
	}


	private MeterGaugeChartModel findEnbrelComplianceMeterDetail() {
		logger.info("!!!!optimization findEnbrelComplianceMeterDetail called in usermanagebean:::"+ new Date());
		 List<Object>enbrelCompliances=userService.findAdherenceComplianceDetails();
		
		getClinicAndProviderIdList();
		List<ChartModel>noOfPatientAccoringTocompliance=userService.findEnbrelAdherencePatient(ClinicAndProviderIdList.get(2));
		
		if(noOfPatientAccoringTocompliance!=null && noOfPatientAccoringTocompliance.size()>0){
			enbrelPatientsLowCompliance=noOfPatientAccoringTocompliance.get(0).getCountValue();
			enbrelPatientsMedCompliance=noOfPatientAccoringTocompliance.get(1).getCountValue();
			enbrelPatientsHighCompliance=noOfPatientAccoringTocompliance.get(2).getCountValue();
			logger.info("enbrelPatientsLowCompliance==> "+enbrelPatientsLowCompliance+"\n"+"enbrelPatientsMedCompliance==> "+enbrelPatientsMedCompliance+"\n"
			+"enbrelPatientsHighCompliance==> "+enbrelPatientsHighCompliance);
		}
		
		
		MeterGaugeChartModel meterModel = new MeterGaugeChartModel();		
		if(!enbrelCompliances.isEmpty())
		{
			int noOfRow = 0;
			double totalAverage = 0;
			for(Object obj: enbrelCompliances){
			
			  noOfRow++;
			  Double objVal=Double.valueOf((Integer)obj);
			  if(objVal!=null){
				  if(objVal>100){
					  totalAverage=totalAverage+100;
				  }else{
				  totalAverage=totalAverage+objVal;
				  }
			  }
			 
	
			  int value=0;
			  value=(int) (totalAverage/enbrelCompliances.size());
			
			  List<Number> intervals = new ArrayList<Number>(){
				  {  
		            add(60);  
		            add(80);  
		            add(100);   
		        }};  
			  
			  meterModel.setValue(value);
			  meterModel.setIntervals(intervals);
			 }
		}
		logger.info("!!!!optimization findEnbrelComplianceMeterDetail ENDSSSSSSSSSSSSSSS in usermanagebean:::"+ new Date());
		return meterModel;
		
	}


	public void setEnbrelAdherenceChartModel(
			MeterGaugeChartModel enbrelAdherenceChartModel) {
		this.enbrelAdherenceChartModel = enbrelAdherenceChartModel;
	}


	private int enbrelPatientsLowCompliance;
	private int enbrelPatientsMedCompliance;
	private int enbrelPatientsHighCompliance;

	public int getEnbrelPatientsLowCompliance() {
		return enbrelPatientsLowCompliance;
	}


	public void setEnbrelPatientsLowCompliance(int enbrelPatientsLowCompliance) {
		this.enbrelPatientsLowCompliance = enbrelPatientsLowCompliance;
	}


	public int getEnbrelPatientsMedCompliance() {
		return enbrelPatientsMedCompliance;
	}


	public void setEnbrelPatientsMedCompliance(int enbrelPatientsMedCompliance) {
		this.enbrelPatientsMedCompliance = enbrelPatientsMedCompliance;
	}


	public int getEnbrelPatientsHighCompliance() {
		return enbrelPatientsHighCompliance;
	}


	public void setEnbrelPatientsHighCompliance(int enbrelPatientsHighCompliance) {
		this.enbrelPatientsHighCompliance = enbrelPatientsHighCompliance;
	}


	public String getCurrentEnbrelPatientCount() {
		logger.info("Enbrel Patient count"+currentEnbrelPatientCount);
		return currentEnbrelPatientCount;
	}


	public void setCurrentEnbrelPatientCount(String currentEnbrelPatientCount) {
		this.currentEnbrelPatientCount = currentEnbrelPatientCount;
	}
	
	
	
	
	
	/**
	 * De-Identification Process of Patient Data
	 * For Third Party 
	 * Analytics Purpose 
	 * @author IDC-008
	 * 
	 */
	public void deIdentificationDataProcess(){
		userService.doDeIdentificationDataProcess(new ContextUtil().getProviderId());
		String msg="De-Identification process has been  completed ";
	     FacesContext.getCurrentInstance().addMessage("messageUpdateForDataPull", new FacesMessage(FacesMessage.SEVERITY_INFO,msg,"")); 
	}


	/**
	 * @return the totalNoOfPatientRecordPulled
	 */
	public Long getTotalNoOfPatientPulled() {
		return totalNoOfPatientPulled;
	}


	/**
	 * @param totalNoOfPatientRecordPulled the totalNoOfPatientRecordPulled to set
	 */
	public void setTotalNoOfPatientPulled(Long totalNoOfPatientPulled) {
		this.totalNoOfPatientPulled = totalNoOfPatientPulled;
	}


	/**
	 * @return the totalNoOfPatientMedicalRecordPulled
	 */
	public Long getTotalNoOfPatientMedicalRecordPulled() {
		return totalNoOfPatientMedicalRecordPulled;
	}


	/**
	 * @param totalNoOfPatientMedicalRecordPulled the totalNoOfPatientMedicalRecordPulled to set
	 */
	public void setTotalNoOfPatientMedicalRecordPulled(
			Long totalNoOfPatientMedicalRecordPulled) {
		this.totalNoOfPatientMedicalRecordPulled = totalNoOfPatientMedicalRecordPulled;
	}


	/**
	 * @return the noOfPatientNotGetRecord
	 */
	public int getNoOfPatientNotGetRecord() {
		return noOfPatientNotGetRecord;
	}


	/**
	 * @param noOfPatientNotGetRecord the noOfPatientNotGetRecord to set
	 */
	public void setNoOfPatientNotGetRecord(int noOfPatientNotGetRecord) {
		this.noOfPatientNotGetRecord = noOfPatientNotGetRecord;
	}


	

	/**
	 * 
	 * 
	 */
	public void onComplete(){
		/* FacesContext.getCurrentInstance().addMessage(null, new  FacesMessage(FacesMessage.SEVERITY_INFO, "Progress Completed", "Progress Completed"));*/
		 /*RequestContext requestContext = RequestContext.getCurrentInstance();    
	     requestContext.execute("batchDataPullResult.show()");
	     requestContext.execute("pbAjax.hide()");*/
	}


	


	/**
	 * @return the totalNoOfSelectedPatient
	 */
	public int getTotalNoOfSelectedPatient() {
		return totalNoOfSelectedPatient;
	}


	/**
	 * @param totalNoOfSelectedPatient the totalNoOfSelectedPatient to set
	 */
	public void setTotalNoOfSelectedPatient(int totalNoOfSelectedPatient) {
		this.totalNoOfSelectedPatient = totalNoOfSelectedPatient;
	}


	/**
	 * @return the completedPerc
	 */
	public int getCompletedPerc() {
		return completedPerc;
	}


	/**
	 * @param completedPerc the completedPerc to set
	 */
	public void setCompletedPerc(int completedPerc) {
		this.completedPerc = completedPerc;
	}

/**
 * @author: SAURABH
 * used on batchPatientMedicationLists.xhtml.
 * & called in userManageBean's method filterPatientForReport
 * To fetch/filter the patient's detail list according to user input 
 * <p>
 * @return list<UserLoginDetail>
 * 
 */
	public List<UserLoginDetail> getBatchPatientForReportList() {
		if (fromPatientIdForReport==0 || toPatientIdForReport==0) {
			if (batchPatientForReportList==null) {
				batchPatientForReportList=new ArrayList<UserLoginDetail>();
			     batchPatientForReportList=userService.fetchbatchPatientForReportList(new ContextUtil().getProviderId(),
			    		 new ContextUtil().getProviderLocationId(),fromPatientIdForReport,toPatientIdForReport);
			}
		}
		else {
			System.out.println("inside else block getBatchPatientForReportList");
		}
		//setToPatientIdForReport(0);
		//setFromPatientIdForReport(0);
		return batchPatientForReportList;
	}


	/**
	 * @return the progressBatchProcess
	 */
	public int getProgressBatchProcess() {
		return progressBatchProcess;
	}


	/**
	 * @param progressBatchProcess the progressBatchProcess to set
	 */
	public void setProgressBatchProcess(int progressBatchProcess) {
		this.progressBatchProcess = progressBatchProcess;
	}


	


	public void setBatchPatientForReportList(List<UserLoginDetail> batchPatientForReportList) {
		this.batchPatientForReportList = batchPatientForReportList;
	}
	
/**
 * used on batchPatientMedicationLists.xhtml
 * Used for fetch list of all patient's ID available in logged in user's provider
 * It will return list of integer type
 * <p> 
 * 
 */
	public List<Integer> getPatientIdList() {
		patientIdList=userService.fetchPatientIdList();
		return patientIdList;
	}

	public void setPatientIdList(List<Integer> patientIdList) {
		this.patientIdList = patientIdList;
	}

/**
 *@author: SAURABH
 * used on batchPatientMedicationLists.xhtml
 * It will filter the range of patients, according to user input
 * Internally calling userManageBean's method getBatchPatientForReportList
 * <p>
 * @return void
 */
public void filterPatientForReport(){
	System.out.println("from="+getFromPatientIdForReport()+"::to="+getToPatientIdForReport());
	//getBatchPatientForReportList();
}


public int getFromPatientIdForReport() {
	return fromPatientIdForReport;
}


public void setFromPatientIdForReport(int fromPatientIdForReport) {
	this.fromPatientIdForReport = fromPatientIdForReport;
}


public int getToPatientIdForReport() {
	return toPatientIdForReport;
}


public void setToPatientIdForReport(int toPatientIdForReport) {
	this.toPatientIdForReport = toPatientIdForReport;
}


/**
 * @return the patientHaveMedications
 */
public Long getPatientHaveMedications() {
	return patientHaveMedications;
}


/**
 * @param patientHaveMedications the patientHaveMedications to set
 */
public void setPatientHaveMedications(Long patientHaveMedications) {
	this.patientHaveMedications = patientHaveMedications;
}
	
public String refreshApplicationForBatchDataPull(){
	System.out.println("::::inside refreshApplicationForBatchDataPull::::");
	batchDataPullTimeDurationCounter++;
	System.out.println("::::inside refreshApplicationForBatchDataPull::::batchDataPullTimeDurationCounter="+batchDataPullTimeDurationCounter);
	setBatchDataPullTimeDuration(String.valueOf((batchDataPullTimeDurationCounter)*56));
	System.out.println("::::inside refreshApplicationForBatchDataPull::::setBatchDataPullTimeDuration="+batchDataPullTimeDuration);
	return "reload_batchDataPull_screen";
}
public int getBatchDataPullTimeDurationCounter() {
	return batchDataPullTimeDurationCounter;
}


public void setBatchDataPullTimeDurationCounter(int batchDataPullTimeDurationCounter) {
	this.batchDataPullTimeDurationCounter = batchDataPullTimeDurationCounter;
}


public String getBatchDataPullTimeDuration() {
	return batchDataPullTimeDuration;
}


public void setBatchDataPullTimeDuration(String batchDataPullTimeDuration) {
	this.batchDataPullTimeDuration = batchDataPullTimeDuration;
}

  /**
 * @throws Exception 
 * @throws ParseErrorException 
 * @throws ResourceNotFoundException 
   * 
   * 
   */
 public void batchMedicationReportGenration() throws ResourceNotFoundException, ParseErrorException, Exception{
	
	 for(UserLoginDetail userDetailForReport:selectedUserDetailForReport){
		System.out.println("user id::::::::::::::::::::::"+userDetailForReport.getUserId()+""); 
	 }
	
	 currentMedicationListForBatchReport=userService.getCurrentMedicationDataForBatchReport(selectedUserDetailForReport,new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());
	 for(PatientMedicationData patMedData:currentMedicationListForBatchReport){
		 logger.info("patMedData:::::::::::::::"+patMedData.getPatientId()+"Pattttt"+patMedData.getDrugs());
	 }
	
	 JRBeanCollectionDataSource beanCollectionDataSource=new JRBeanCollectionDataSource(currentMedicationListForBatchReport);
		
	 String reportPath=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/patientMedicineDetailReport.jasper");
	
	 try {
		jasperPrint=JasperFillManager.fillReport(reportPath, new HashMap(), beanCollectionDataSource);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	/* JasperExportManager.exportReportToPdfFile(jasperPrint, "batchDataMedicationRecord.pdf");*/
	 
			 
	 
	 
	 
 }


public UserManageBean() {
	super();
}


/**
 * @return the selectedUserDetailForReport
 */
public UserLoginDetail[] getSelectedUserDetailForReport() {
	return selectedUserDetailForReport;
}


/**
 * @param selectedUserDetailForReport the selectedUserDetailForReport to set
 */
public void setSelectedUserDetailForReport(
		UserLoginDetail[] selectedUserDetailForReport) {
	this.selectedUserDetailForReport = selectedUserDetailForReport;
}


/**
 * @return the currentMedicationListForBatchReport
 */
public List<PatientMedicationData> getCurrentMedicationListForBatchReport() {
	if(currentMedicationListForBatchReport==null){
		currentMedicationListForBatchReport=new ArrayList<PatientMedicationData>();
	}
	return currentMedicationListForBatchReport;
}


/**
 * @param currentMedicationListForBatchReport the currentMedicationListForBatchReport to set
 */
public void setCurrentMedicationListForBatchReport(
		List<PatientMedicationData> currentMedicationListForBatchReport) {
	this.currentMedicationListForBatchReport = currentMedicationListForBatchReport;
}
 

/**
 * @author Anjani
 * Generates patient's medicine Jasper reports in PDF format 
 * Internally calling UserManageBean's method batchPatientDetailReportGeneration
 * <p>
 * @return void
 * @throws Exception, ParseErrorException,  ResourceNotFoundException 
 * 
 */
 public void batchPatientMedicineReportInPDF() throws ResourceNotFoundException, ParseErrorException, Exception{
	 /*batchMedicationReportGenration();*/
	 batchPatientDetailReportGeneration();
	
	 HttpServletResponse httpServletResponse=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	 httpServletResponse.addHeader("Content-disposition", "attachment; filename=Patient_Report_"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".pdf");
	 ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream(); 
	 JasperExportManager.exportReportToPdfStream(patientDetailJasperPrint, servletOutputStream);
	 FacesContext.getCurrentInstance().responseComplete();
 }


/**
 * @return the jasperPrint
 */
public JasperPrint getJasperPrint() {
	if(jasperPrint==null){
		jasperPrint=new JasperPrint();
	}
	return jasperPrint;
}


/**
 * @param jasperPrint the jasperPrint to set
 */
public void setJasperPrint(JasperPrint jasperPrint) {
	this.jasperPrint = jasperPrint;
}

/**
 * @author Anjani
 * Fetch patient's details & medicine detail and pass these details to JasperPrint object
 * used in UserManageBean's method batchPatientMedicineReportInPDF
 * <p>
 * @return void
 * @throws Exception 
 * @throws ParseErrorException 
 * @throws ResourceNotFoundException 
 * 
 */

public void batchPatientDetailReportGeneration() throws ResourceNotFoundException, ParseErrorException, Exception{
	
	patientDetailListForBatchReport=userService.getPatientDetailDataForBatchReport(selectedUserDetailForReport,new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());
	currentMedicationListForBatchReport=userService.getCurrentMedicationDataForBatchReport(selectedUserDetailForReport,new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());
    JRDataSource batchPatientDetailDataSource=new JRBeanCollectionDataSource(patientDetailListForBatchReport);
    
    for(UserLoginDetail pd:patientDetailListForBatchReport){
    	logger.info(pd.getPatientMedicationDataList().size());
    	logger.info("getPatSendMessageEditRxList().size()"+pd.getPatSendMessageEditRxList().size());
    	for(PatientMedicationData medData:pd.getPatientMedicationDataList()){
    		logger.info("Med Name :::::::::"+medData.getDrugs());
    	}
    }
    
     URL masterReportUrl=UserManageBean.class.getClassLoader().getResource("/com/clinakos/report/batchDataPatientDetail.jasper");
    URL medicineReportUrl=UserManageBean.class.getClassLoader().getResource("/com/clinakos/report/");

    URL clinakosLogoUrl=UserManageBean.class.getClassLoader().getResource("/LogoImage/Clinakos_Logo.png");
    
    
    
    logger.info("clinakosLogoUrl::::::::::::"+clinakosLogoUrl);
    
    String masterReportPath=masterReportUrl.getPath();
    String reportPath=medicineReportUrl.getPath();
    String clinakosLogo=clinakosLogoUrl.getPath();
    
   // For testing purpose 
    
   /*String masterReportPath=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/batchDataPatientDetail.jasper");
    String reportPath=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/");
    URL clinakosLogoUrl=UserManageBean.class.getClassLoader().getResource("/LogoImage/Clinakos_Logo.png");
    String clinakosLogo=clinakosLogoUrl.getPath();*/
    
    
    logger.info(masterReportPath+"  medicineReportPath "+reportPath);
    logger.info("clinakosLogoUrl::::::::::::"+clinakosLogoUrl+"Clinakos Logo  "+clinakosLogo);
   
    Map<String, Object> parameters=new HashMap<String, Object>();
    parameters.put("SUBREPORT_DIR", reportPath);
    parameters.put("REPORT_LOGO", clinakosLogo);
   
   
    patientDetailJasperPrint=JasperFillManager.fillReport(masterReportPath, parameters, batchPatientDetailDataSource);
}


/**
 * @return the patientDetailListForBatchReport
 */
public List<UserLoginDetail> getPatientDetailListForBatchReport() {
	if(patientDetailListForBatchReport==null){
		patientDetailListForBatchReport=new ArrayList<UserLoginDetail>();
	}
	return patientDetailListForBatchReport;
}


/**
 * @param patientDetailListForBatchReport the patientDetailListForBatchReport to set
 */
public void setPatientDetailListForBatchReport(
		List<UserLoginDetail> patientDetailListForBatchReport) {
	this.patientDetailListForBatchReport = patientDetailListForBatchReport;
}


/**
 * @return the patientDetailJasperPrint
 */
public JasperPrint getPatientDetailJasperPrint() {
	if(patientDetailJasperPrint==null){
		patientDetailJasperPrint=new JasperPrint();
	}
	return patientDetailJasperPrint;
}


/**
 * @param patientDetailJasperPrint the patientDetailJasperPrint to set
 */
public void setPatientDetailJasperPrint(JasperPrint patientDetailJasperPrint) {
	this.patientDetailJasperPrint = patientDetailJasperPrint;
}

/***********@author:SAURABH
 **********For asynchronous data pull 
 * 
 */
public void multiThreadingBatchDataPullingForPatientConsentGrantPatient(){
	int count=0;
	//@author: saurabh , variables for batch data pull in MultiThreading
	final int providerIdForMultiThreading=new ContextUtil().getProviderId();
	final int providerLocationIdForMultiThreading=new ContextUtil().getProviderLocationId();
	final String loggedInRoleForMultiThreading=new ContextUtil().getRole();
	final int loginIdForMultiThreading=new ContextUtil().getLoginId();
	
	final ProviderLocation providerLocationObj=userService.findProviderLocationForMultiThreading(providerIdForMultiThreading,loggedInRoleForMultiThreading);
	final DoctorDetail doctorDetailForMultithreading = userService.findDoctorDetailDataForMultiThreading(providerIdForMultiThreading,
			providerLocationIdForMultiThreading,loggedInRoleForMultiThreading,loginIdForMultiThreading);
	final RoleSecurity roleSecurityObj=userService.findRoleDetails(loginIdForMultiThreading);
	
	final UserLoginDetail[] userLoginDetailForThreading=selectedUserLoginDetail;
	
	System.out.println("::***batchDataPullingForPatientConsentGrantPatient starts***::length of selectedUserLoginDetail= "+
			selectedUserLoginDetail.length+"::provider_id="+providerIdForMultiThreading+"::providerLocationIdForMultiThreading="+providerLocationIdForMultiThreading
			+"::loggedInRoleForMultiThreading= "+loggedInRoleForMultiThreading+"::loginIdForMultiThreading= "+loginIdForMultiThreading);
	System.out.println(":::"+System.currentTimeMillis()+":::::"+Thread.currentThread().getName()+":::starting"+new Date());
	userService.resetOldRecords(loginIdForMultiThreading);
	
	//final ProviderLocation providerLocationObj=getProviderLocationForMultiThreading();
	//final RoleSecurity roleSecurityObj=getRoleSecurityForMultiThreading();
	//final DoctorDetail doctorDetailForMultithreading=getDoctorDetailForMultiThreading();
	FacesContext context = FacesContext	.getCurrentInstance();
	final BatchReconcileProcess batchReconcileProcessObj=(BatchReconcileProcess)context.getApplication().getELResolver().getValue(context.getELContext(), null,"batchReconcileProcess");
	final BatchInteractionBean batchInteractionBean=(BatchInteractionBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"batchInteractionBean");
	ExecutorService executor=Executors.newFixedThreadPool(10);
	for(final UserLoginDetail patients: userLoginDetailForThreading){
	  Runnable runable=new Runnable(){
		  public void run(){
			  logger.info(":Inside RUN::"+System.currentTimeMillis()+":::::"+Thread.currentThread().getName()+":::starting"+new Date());
				List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList=new ArrayList<PatientPbmDrugHistoryDetail>();
				 List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultList=new ArrayList<PatientPBMDrugHistoryResult>();
				 PatientPBMDrugHistoryResult savePatientPBMDrugHistoryResultObj= new PatientPBMDrugHistoryResult();
				if(timePeriod==0){
					timePeriod=48;
				}
				else {
					timePeriod=timePeriod;
				}
				//int patNoSequance=0;
				//double compltedSize=0.0;
								userService.deletePatientPBMDrugHistoryDetailResult(patients.getUserId());
								userService.deletePatientPBMDrugHistoryResult(patients.getUserId());
								logger.info("*********Inside Run calling update1wsBean.callGetPBMDrugHistoryV2ForMultiThreading for patient= "+patients.getUserId()
								+ " ::::starting time= "+new Date());
								patientPbmDrugHistoryDetailList=update1wsBean.callGetPBMDrugHistoryV2ForMultiThreading(timePeriod, providerLocationObj, 
										roleSecurityObj, patients,doctorDetailForMultithreading,loginIdForMultiThreading);
								logger.info("*********Inside Run calling update1wsBean.callGetPBMDrugHistoryV2ForMultiThreading for patient= "+patients.getUserId()
								+ " ::::end time= "+new Date());
								for(PatientPbmDrugHistoryDetail patientPbmDrugHistoryDetail:patientPbmDrugHistoryDetailList){
									userService.savePatientPBMDrugHistoryDetailResult(patientPbmDrugHistoryDetail);
								}
								 patientPbmDrugHistoryDetailList=userService.getAllPatientPbmDrugHistoryDetail(patients.getUserId());
								 try {
									 
									 logger.info("*********Inside Run calling update1wsBean.convertPBMDrugHistoryXMLToSaxParser for patient= "+patients.getUserId()
										+ " ::::start time= "+new Date());
									 
									patientPBMDrugHistoryResultList=update1wsBean.convertPBMDrugHistoryXMLToSaxParser(timePeriod, providerLocationObj,
											roleSecurityObj,patientPbmDrugHistoryDetailList);
										} catch (ParserConfigurationException e) {
											logger.error("::::ParserConfigurationException for patient id= "+patients.getUserId()+
													", in update1wsBean.convertPBMDrugHistoryXMLToSaxParser::::"+e.toString());
											e.printStackTrace();
										} catch (SAXException e) {
											logger.error("::::SAXException for patient id= "+patients.getUserId()+
													", in update1wsBean.convertPBMDrugHistoryXMLToSaxParser::::"+e.toString());
											e.printStackTrace();
										} catch (IOException e) {
											logger.error("::::IOException for patient id= "+patients.getUserId()+
													", in update1wsBean.convertPBMDrugHistoryXMLToSaxParser::::"+e.toString());
											e.printStackTrace();
										}
								 
								 logger.info("*********Inside Run calling update1wsBean.convertPBMDrugHistoryXMLToSaxParser for patient= "+patients.getUserId()
									+ " ::::end time= "+new Date());
								 for(PatientPBMDrugHistoryResult drugHistoryResult:patientPBMDrugHistoryResultList){
									   drugHistoryResult.setPulledBy(loginIdForMultiThreading);
									   drugHistoryResult.setDrugHistoryExist(true);
									   userService.savePatientPBMDrugHistoryResult(drugHistoryResult);
								}
								  /*List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultValueList=userService.getAllPatientPBMDrugHistoryResultValue
													(patients.getUserId());
									ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeDrugDetailInfoList=new ArrayList<PatientFormularyCompositeDrugDetailInfo>();
									ArrayList<PatientFormularyCompositeCopayInfo> formularyCompositeCopayInfoList=new ArrayList<PatientFormularyCompositeCopayInfo>(); 
								  */
									logger.info("*********Inside Run calling updateHealthPlanDetail for patient= "+patients.getUserId()
											+ " ::::starting time= "+new Date());
									updateHealthPlanDetail(patients.getUserId());
									logger.info("*********Inside Run calling updateHealthPlanDetail for patient= "+patients.getUserId()
									+ " ::::ending time= "+new Date());
									try {
										userService.careTeamIntegration(patients.getUserId());
									} catch (HibernateException e) {
										logger.error("::::Hibernate exception for patient id= "+patients.getUserId()+
												", in userService.careTeamIntegration::::"+e.toString());
										e.printStackTrace();
									} catch (Exception e) {
										logger.error("::::Exception for patient id= "+patients.getUserId()+
												", in userService.careTeamIntegration::::"+e.toString());
										e.printStackTrace();
									}
									//progressBatchProcess=patNoSequance;
									totalNoOfSelectedPatient=selectedUserLoginDetail.length;

									try{
										
										batchReconcileProcessObj.autoFillCurrentMedsFromPharmacyMeds(patients.getUserId(), timePeriod,
													providerIdForMultiThreading,loginIdForMultiThreading,providerLocationObj,roleSecurityObj,doctorDetailForMultithreading);
									}catch(NullPointerException ne){
										//BatchReconcileProcess batchReconcileProcessObj=new BatchReconcileProcess();
										ne.printStackTrace();
									}
									catch(Exception e){
										e.printStackTrace();
									}
									try{
									//	FacesContext context1 = FacesContext	.getCurrentInstance();
									
										batchInteractionBean.fetchInteractionsData(patients.getUserId(), providerIdForMultiThreading,
												loginIdForMultiThreading,providerLocationObj);
									}catch(NullPointerException ne){
										//BatchInteractionBean batchInteractionBean=new BatchInteractionBean();
										ne.printStackTrace();
									}catch(Exception e){
										e.printStackTrace();
									}
									//getPatientConsentDataWithFilter();
								
			logger.info("<<<<<<<process::"+Thread.currentThread().getName()+":::complete for::::"+patients.getUserId());						
		  }};
		executor.execute(runable);
	 }
	executor.shutdown();
//---to force other parts to wait until the executer thread not terminated 	
	while(!executor.isTerminated()){

	}
	boolean executorService=executor.isTerminated();
	logger.info("All Tasks for Pharmacy History Completed ."+executor.isTerminated());
	if (executorService) {
		/*try {
			getPatientConsentDataWithFilter();
		} catch (Exception e) {
			logger.error(":::::EXECEPTION IN getPatientConsentDataWithFilter USERMENAGE BEAN Line 4005"+e.getMessage());
			e.printStackTrace();
		}*/
		
	} 
	
	totalNoOfPatientPulled=userService.findNumberOfPatientsHavingPbmElegiblity(loginIdForMultiThreading);
	//completedPerc=  (int) ((totalNoOfPatientPulled/(double)selectedUserLoginDetail.length)*100);
    totalNoOfPatientMedicalRecordPulled=userService.findPatientHaveMedications(loginIdForMultiThreading);
    patientHaveMedications=userService.findNumberOfpulledDrugs(loginIdForMultiThreading);//patientPBMDrugHistoryResultList.size();
    noOfPatientNotGetRecord=(int) (selectedUserLoginDetail.length - totalNoOfPatientPulled);
    totalNoOfSelectedPatient=selectedUserLoginDetail.length;
    getPatientConsentDataWithFilterForMultithreading(providerIdForMultiThreading,providerLocationIdForMultiThreading);
   // completedPerc=170;
    RequestContext requestContext = RequestContext.getCurrentInstance();    
    requestContext.execute("batchDataPullResult.show()");
	logger.info("::batchDataPullingForPatientConsentGrantPatient ENDS:"+System.currentTimeMillis()+":::::"+
									Thread.currentThread().getName()+":::done"+new Date());
	logger.info("State:" + executor.isTerminated()+"::shutdown::"+executor.isShutdown());
		
}


/****@author Saurabh
 * 
 */
public synchronized void updateHealthPlanDetail(int patientId){
	try {
		 userService.updateHealthPlanAndPharmacyDetail(patientId);
	} catch (HibernateException e) {
		logger.error("::::Hibernate exception for patient id= "+patientId+
				", in userService.updateHealthPlanAndPharmacyDetail::::"+e.toString());
		e.printStackTrace();
	} catch (SQLException e) {
		logger.error("::::SQLException for patient id= "+patientId+
				", in userService.updateHealthPlanAndPharmacyDetail::::"+e.toString());
		e.printStackTrace();
	}
 }

/****@author Saurabh
 * ***to pull pharmacy record's of all patient
 */
public void batchDataPullingForAllPatient(){
	logger.info("::::inside batchDataPullingForAllPatient in usermanage bean:::::SelectedUserLoginDetail= "+selectedUserLoginDetail.length);
	setSelectedUserLoginDetail(patientDataWithPatientConsentForBatchDataPullProcessList.toArray
			(new UserLoginDetail[patientDataWithPatientConsentForBatchDataPullProcessList.size()]));
	logger.info("::::after assignign SelectedUserLoginDetail= "+selectedUserLoginDetail.length);
	multiThreadingBatchDataPullingForPatientConsentGrantPatient();
 }

/****@author Saurabh
 * ***used in method multiThreadingBatchDataPullingForPatientConsentGrantPatient
 * line 3875
 */
public void getPatientConsentDataWithFilterForMultithreading(int providerLocationIdForMultiThreading,int providerIdForMultiThreading){
	logger.info("::::inside getPatientConsentDataWithFilter in usermanageBean, providerId="+providerIdForMultiThreading+
			" ::::providerLocationId= "+providerLocationIdForMultiThreading+" :::getPatientBatchDate()= "+getPatientBatchDate()+
			" :::userLoginDetail.getPatientBatchNo= "+userLoginDetail.getPatientBatchNo()+" :::patientConsent= "+patientConsent);
	if(StringUtils.isEmpty(getPatientBatchDate()) && userLoginDetail.getPatientBatchNo()==0){
		patientDataWithPatientConsentForBatchDataPullProcessList=userService.getAllpatientDataWithPatientConsentForDataPullProcess(providerIdForMultiThreading,
																				providerLocationIdForMultiThreading);
	}
	else if ((StringUtils.isEmpty(getPatientBatchDate())) && userLoginDetail.getPatientBatchNo()!=0) {
		patientDataWithPatientConsentForBatchDataPullProcessList=userService.getPatientDataWithPatientConsentByBatchNoFilter(providerIdForMultiThreading,
				providerLocationIdForMultiThreading,getPatientBatchDate(),userLoginDetail.getPatientBatchNo(),patientConsent);
	}
	else if (!(StringUtils.isEmpty(getPatientBatchDate()))  &&  userLoginDetail.getPatientBatchNo()==0) {
		patientDataWithPatientConsentForBatchDataPullProcessList=userService.getAllPatientDataForBatchPullBasedOnBatchDate(providerIdForMultiThreading,
				providerLocationIdForMultiThreading,getPatientBatchDate(),userLoginDetail.getPatientBatchNo(),patientConsent);
	}
	else if ( !(StringUtils.isEmpty(getPatientBatchDate())) &&  userLoginDetail.getPatientBatchNo()!=0) {
		patientDataWithPatientConsentForBatchDataPullProcessList=userService.getAllPatientDataForBatchPullBasedOnBatchDateAndBatchNo(providerIdForMultiThreading,
				providerLocationIdForMultiThreading,getPatientBatchDate(),userLoginDetail.getPatientBatchNo(),patientConsent);
	}
	
   }





 /**
  * @author Anjani
  * used on batchPatientMedicationLists.xhtml
  * To generates the batch Patient Report With Lab Detail in jasper- pdf format 
  * Internally calling userManageBean's method patientBatchReportWithLabDetailGenration
  * <p>
  * @return void
  * @throws JRException 
  * @throws IOException 
  */
 public void batchPatientReportWithLabDetailInPdf() throws JRException, IOException {
	 patientBatchReportWithLabDetailGenration();
	 HttpServletResponse httpServletResponse=(HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	 httpServletResponse.addHeader("Content-disposition", "attachment; filename=Patient_Report_With_Lab"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+".pdf");
	 ServletOutputStream servletOutputStream=httpServletResponse.getOutputStream(); 
	 JasperExportManager.exportReportToPdfStream(patientDetailWithLabJasperPrint, servletOutputStream);
	 FacesContext.getCurrentInstance().responseComplete();
 }


/**
 * @return the patientDetailWithLabJasperPrint
 */
public JasperPrint getPatientDetailWithLabJasperPrint() {
	if(patientDetailWithLabJasperPrint==null){
		patientDetailWithLabJasperPrint=new JasperPrint();
	}
	return patientDetailWithLabJasperPrint;
}



/**
 * @param patientDetailWithLabJasperPrint the patientDetailWithLabJasperPrint to set
 */
public void setPatientDetailWithLabJasperPrint(
		JasperPrint patientDetailWithLabJasperPrint) {
	this.patientDetailWithLabJasperPrint = patientDetailWithLabJasperPrint;
}

/**
 * Fetch patient's details & lab detail and pass these details to patientDetailWithLabJasperPrint object
 * Used in userManageBean's method batchPatientReportWithLabDetailInPdf
 * <p>
 * @return void
 * @throws JRException
 * 
 */
public void patientBatchReportWithLabDetailGenration() throws JRException{
	 patientDetailListForBatchReport=userService.getPatientDetailDataForBatchReport(selectedUserDetailForReport,new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());
	 logger.info("patientDetailListForBatchReport.size()::::::::::"+patientDetailListForBatchReport.size());
	 
		currentMedicationListForBatchReport=userService.getCurrentMedicationDataForBatchReport(selectedUserDetailForReport,new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());
	    JRDataSource batchPatientDetailWithLabDataSource=new JRBeanCollectionDataSource(patientDetailListForBatchReport);
	    
	    for(UserLoginDetail pd:patientDetailListForBatchReport){
	    	logger.info("MEDICATION lIST sIZE"+pd.getPatientMedicationDataList().size());
	    	logger.info("getPatSendMessageEditRxList().size()"+pd.getPatSendMessageEditRxList().size());
	    	logger.info("pd.getPatLabReslutList() :::::::"+pd.getPatLabReslutList());
	    	for(PatientMedicationData medData:pd.getPatientMedicationDataList()){
	    		logger.info("Med Name :::::::::"+medData.getDrugs());
	    	}
	    }
	    
	    URL masterReportUrl=UserManageBean.class.getClassLoader().getResource("/com/clinakos/report/batchPatientDetailWithLabReport.jasper");
	    URL medicineReportUrl=UserManageBean.class.getClassLoader().getResource("/com/clinakos/report/");

	    URL clinakosLogoUrl=UserManageBean.class.getClassLoader().getResource("/LogoImage/Clinakos_Logo.png");
	    
	    
	    
	    logger.info("clinakosLogoUrl::::::::::::"+clinakosLogoUrl);
	    
	     String masterReportPath=masterReportUrl.getPath();
	    String reportPath=medicineReportUrl.getPath();
	    String clinakosLogo=clinakosLogoUrl.getPath();
	    
	   // For testing purpose 
	    
	  /*String masterReportPath=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/batchPatientDetailWithLabReport.jasper");
	    String reportPath=FacesContext.getCurrentInstance().getExternalContext().getRealPath("/report/");
	    String clinakosLogo=clinakosLogoUrl.getPath();*/
	    
	    
	    logger.info(masterReportPath+"  medicineReportPath "+reportPath);
	    logger.info("clinakosLogoUrl::::::::::::"+clinakosLogoUrl+"Clinakos Logo  "+clinakosLogo);
	   
	    Map<String, Object> parameters=new HashMap<String, Object>();
	    parameters.put("SUBREPORT_DIR", reportPath);
	    parameters.put("REPORT_LOGO", clinakosLogo);
	   
	   
	   patientDetailWithLabJasperPrint=JasperFillManager.fillReport(masterReportPath, parameters, batchPatientDetailWithLabDataSource);
}


}
	
	

