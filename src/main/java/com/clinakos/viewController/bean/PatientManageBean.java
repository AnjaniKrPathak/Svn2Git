package com.clinakos.viewController.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.SelectableDataModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.model.core.EmpProviderLocation;
import com.clinakos.data.model.patient.AllergyMaster;
import com.clinakos.data.model.patient.CareTeam;
import com.clinakos.data.model.patient.ClinicMaster;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.Icd9Diagnosis;
import com.clinakos.data.model.patient.MasterDiagnosis;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.service.IPatientService;

public class PatientManageBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3989645527006275450L;
	public static final Logger logger = Logger
			.getLogger("PatientManageBean.class");
	private IPatientService patientService;
	private List<PatientDiagnosesDetails> patientICD_10DiagnosisList;
	private List<PatientDiagnosesDetails> patientICD_9DiagnosiosList;
	private List<PatientDiagnosesDetails> patientICDDiagnosisList; 
	
	private List<ClinicMaster>clinicMasterList=new ArrayList<ClinicMaster>();
	//private List<CareTeam> fetchCareTeamList;// List to fetch CareTeam of a particular Patient
	private List<PatientAllergy> patientAllergyList ;
	//private List<DoctorDetail> fetchMyCareTeamList;  	 //List to fetch Care Team List for logged patient by LI-0011 at Lumbini Innovations
	int paccPanelIndex;//variable for patient accordion Panel Active Index
	private List<DoctorDetail> careTeamListForPatient;
	public String loggedUserName="";
	public String loggedFName="";
	public String loggedLName="";
	public String getLoggedFName() {
		if(loggedFName.equals(""))
		{
			loggedFName=new ContextUtil().getLoggerFirstName();
		}
		return loggedFName;
	}
	public void setLoggedFName(String loggedFName) {
		
		this.loggedFName = loggedFName;
	}
	public String getLoggedLName() {
		if(loggedLName.equals(""))
		{
			loggedLName=new ContextUtil().getLoggerLastName();
		}
		return loggedLName;
	}
	public void setLoggedLName(String loggedLName) {
		this.loggedLName = loggedLName;
	}

	private DataTable searchDiagnosisDataTable;
	private List<MasterDiagnosis >dignosisdetailsList=new ArrayList<MasterDiagnosis>(); // Diagnosis Detail Data List  
	private  MasterDiagnosis diagnosesDetails=new MasterDiagnosis();
	private PatientDiagnosesDetails  patientDiagnosesDetails=new PatientDiagnosesDetails();
	
	private Icd9Diagnosis icd9Diagnosis= new Icd9Diagnosis();
	private List<Icd9Diagnosis> icd9DiagnosisList = new ArrayList<Icd9Diagnosis>(); // Patient ICD 9  Diagnosis Detail list  
	private String selectedIcdFormat="ICD9";
	private DataTable icd9DiagnosisTable;
	private String userInputForIcdDiagnosisSearchTable;
	private String userInputForIcdCode;
	private MasterDiagnosis selectedDiagonisObj;
	private Icd9Diagnosis selectedDiagonsisICD9Obj;
	private int selectIntervention=1;
	private boolean showRxTab;
	
    private List<PatientDiagnosesDetails> pateintDiagnosesForNewPatientList = new ArrayList<PatientDiagnosesDetails>();
    public DataTable masterDiagnosesDataBindingNewPatient = new DataTable();
    public DataTable masterIcd9Binding = new DataTable();
    public DataTable editPateintMasterICD10DiagnosesBinding = new DataTable();
    public DataTable editPateintMasterICD9DiagnosesBinding = new DataTable();
    private DataTable diagnosesdatatable=new DataTable();
    private List<PatientDiagnosesDetails> pateintDiagnosesForEditProfileList;
    private CareTeam careTeam= new CareTeam();
    private int rowIdForEditCareTeam;
    private int rowIdForDeleteCareTeam;
    private int addClinakosPhysician=1;
    private String valueForSearchingClinakosDoc;
    private List<EmpProviderLocation> empProviderLocationList;
    private int chooseSelectedDiagnosisOption=1;
    private PatientDiagnosesDetails patientDiagnosesDetailsObj;
    
	//private List<DoctorDetail> careTeamListForPatientforSort;
	/**
	 * @return the patientService
	 */
	
	/*
	 * for reseting the value for patient change..
	 * @author:Gopal Krishna jha
	 */
	public void resetVAlue()
	{
		clinicMasterList=new ArrayList<ClinicMaster>();
		careTeamListForPatient=null;
		patientICDDiagnosisList=null;
		//setCareTeamListForPatient(null);
	}//
	public IPatientService getPatientService() {
		return patientService;
	}

	/**
	 * @param patientService
	 *            the patientService to set
	 */
	public void setPatientService(IPatientService patientService) {
		this.patientService = patientService;
	}

	/*// Getter to fetch Care Team list for Particular Patient, Created on 28th
	// June 2013 by Anand S Jha
	public List<DoctorDetail> getFetchCareTeamList() {
		logger.info("getFetchCareTeamList strated:::::::::");
		int patientId = new ContextUtil().getPatientId();	
		logger.info("getFetchCareTeamList method end:::::::::");
		return patientService.fetchCareTeamList(patientId);
	}

	// Setter for CareTeam
	public void setFetchCareTeamList(List<CareTeam> careTeamList) {
		this.fetchCareTeamList = careTeamList;
	}*/

	/**
	 * Method for taking value of Patient Diagnoses ICD Code List
	 * 
	 * @return the patientICD_10DiagnosisList
	 */
	public List<PatientDiagnosesDetails> getPatientICD_10DiagnosisList() {
		if ( patientICD_10DiagnosisList==null) {
			patientICD_10DiagnosisList = new ArrayList<PatientDiagnosesDetails>();
			patientICD_10DiagnosisList = patientService
			.getPatientICD_10Diagonsis();
		}
		/*try {

			patientICDss_10DiagnosisList = patientService
					.getPatientICD_10Diagonsis();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
		return patientICD_10DiagnosisList;
	}

	/**
	 * @param patientICDD_10DiagnosisList
	 *            the patientICD_10DiagnosisList to set
	 */
	public void setPatientICD_10DiagnosisList(
			List<PatientDiagnosesDetails> patientICDD_10DiagnosisList) {
		this.patientICD_10DiagnosisList = patientICDD_10DiagnosisList;
	}
	
	/*for idle screen added by umesh yadav*/
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
     /*     Thread.sleep(2000);
          FacesContext fc = FacesContext.getCurrentInstance();
          ExternalContext ec = fc.getExternalContext();                                                                  
          ec.redirect("/clinakos/page/core/login.jsf");
          HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
          session.invalidate();
       
         
}*/

	/**
	 * Get Patient ICD 9 code Diagnoses
	 * 
	 * @return the patientICD_9DiagnosiosList
	 */
	public List<PatientDiagnosesDetails> getPatientICD_9DiagnosiosList() {
		if (patientICD_9DiagnosiosList == null) {
			patientICD_9DiagnosiosList = new ArrayList<PatientDiagnosesDetails>();
			patientICD_9DiagnosiosList = patientService
			.getPatientICD_9CodeDiagonsis();
		}
		/*try {
			patientICD_9DiagnosiosList = patientService
					.getPatientICD_9CodeDiagonsis();
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}*/

		return patientICD_9DiagnosiosList;
	}

	/**
	 * @param patientICD_9DiagnosiosList
	 *            the patientICD_9DiagnosiosList to set
	 */
	public void setPatientICD_9DiagnosiosList(
			List<PatientDiagnosesDetails> patientICD_9DiagnosiosList) {
		this.patientICD_9DiagnosiosList = patientICD_9DiagnosiosList;
	}

	
	//Getter for Patient Accordion Panel Index Variable
			public int getPaccPanelIndex() {
			return paccPanelIndex;
		}

		//Setter for Patient Accordion Panel Index Variable
		public void setPaccPanelIndex(int paccPanelIndex) {
			this.paccPanelIndex = paccPanelIndex;
		}

	/*	//Method to fetch Care Team List for Logged in Patient author LI-011 at Lumbini Innovations
		public List<DoctorDetail> getFetchMyCareTeamList() {
			int patientId = new ContextUtil().getLoginId();
			return patientService.fetchCareTeamList(patientId);
			
		}*/

		/*public void setFetchMyCareTeamList(List<DoctorDetail> fetchMyCareTeamList) {
			this.fetchMyCareTeamList = fetchMyCareTeamList;
		}*/
		//Method to open My Overview Page for Patient Login
		public String setMyOverviewIndex()
		{
			
			paccPanelIndex=0;
			return "go_to_My_overview";
		}
		
		public String setMyMedicationIndex()
		{
			System.out.println("my medication redirection...");
			paccPanelIndex=1;
			return "go_to_My_Medication";
		}
		
		public String setMyMedicationGoalIndex()
		{
			
			paccPanelIndex=2;
			return "go_to_My_Medication_Goal";
		}
		
		public String setMyAnalyticsIndex()
		{
			
			paccPanelIndex=3;
			return "go_to_My_Analytics";
		}
		
		public String setMyFormularyIndex()
		{
			
			paccPanelIndex=4;
			return "go_to_My_Formulary";
		}
		
		public String setMyCareIndex()
		{
			
			paccPanelIndex=5;
			return "go_to_My_Care_Team";
		}
		
		public String setMyAllergyIndex()
		{
			
			paccPanelIndex=6;
			return "go_to_My_Allergy";
		}

	/**
	 * @return the patientAllergyList
	 */
		public List<PatientAllergy> getPatientAllergyList() {
		if (patientAllergyList==null) {
			patientAllergyList= new ArrayList<PatientAllergy>();
			patientAllergyList=patientService.getAllergyList();
		}
		return patientAllergyList;
	}

	/**
	 * @param patientAllergyList the patientAllergyList to set
	 */

		public void setPatientAllergyList(List<PatientAllergy> patientAllergyList) {
		this.patientAllergyList = patientAllergyList;
	}

		
		//Method to listen Tab Change and Open Selected Tab Page in patientMenu Section by Anand
		public void patientMenuGroupChange(TabChangeEvent event) throws IOException { 			
			if(event.getTab().getId().equalsIgnoreCase("myOverview"))
			{
				paccPanelIndex=0;
				FacesContext fc = FacesContext.getCurrentInstance();
				ExternalContext ec = fc.getExternalContext();					
				ec.redirect("patientProfile.jsf");			
			}
			else if(event.getTab().getId().equalsIgnoreCase("myMedication"))
			{
				paccPanelIndex=1;
				FacesContext fc = FacesContext.getCurrentInstance();
				ExternalContext ec = fc.getExternalContext();					
				ec.redirect("myMedication.jsf");
			}
			else if(event.getTab().getId().equalsIgnoreCase("myMedicationGoal"))
			{
				paccPanelIndex=2;
				FacesContext fc = FacesContext.getCurrentInstance();
				ExternalContext ec = fc.getExternalContext();					
				ec.redirect("myMedicationGoal.jsf");
				setMyMedicationGoalIndex();
			}
			else if(event.getTab().getId().equalsIgnoreCase("myAnalytics"))
			{
				setMyAnalyticsIndex();
			}	
			else if(event.getTab().getId().equalsIgnoreCase("myFormulary"))
			{
				paccPanelIndex=4;
				FacesContext fc = FacesContext.getCurrentInstance();
				ExternalContext ec = fc.getExternalContext();					
				ec.redirect("myFormulary.jsf");
			}	
			else if(event.getTab().getId().equalsIgnoreCase("myCareTeam"))
			{
				paccPanelIndex=5;
				FacesContext fc = FacesContext.getCurrentInstance();
				ExternalContext ec = fc.getExternalContext();					
				ec.redirect("myCareTeam.jsf");
			}
			else if(event.getTab().getId().equalsIgnoreCase("myAllergy"))
			{
				paccPanelIndex=6;
				FacesContext fc = FacesContext.getCurrentInstance();
				ExternalContext ec = fc.getExternalContext();					
				ec.redirect("myAllergy.jsf");
			}
	    }
		// FOR HEADER USER NAME..............
		public String getLoggedUserName() {
			if(loggedUserName.length()==0)
			{
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				loggedUserName = user.getUsername();
				loggedFName=new ContextUtil().getLoggerFirstName();
				loggedLName =new ContextUtil().getLoggerLastName();
			}
			return loggedUserName;
			
		}

		public void setUserId(String userId) {
			this.loggedUserName = userId;
		}

		/*
		 * find clinic master list 
		 * @author; Gopal Krihsnja JHa
		 */
		public List<ClinicMaster> getClinicMasterList() {
			if(clinicMasterList.isEmpty())
				clinicMasterList=patientService.findClinicList();
			return clinicMasterList;
		}

		public void setClinicMasterList(List<ClinicMaster> clinicMasterList) {
			this.clinicMasterList = clinicMasterList;
		}
		public List<DoctorDetail> getCareTeamListForPatient() throws HibernateException, SQLException {
			 if(careTeamListForPatient == null){
				  careTeamListForPatient = new ArrayList<DoctorDetail>();
				  careTeamListForPatient=patientService.findCareTeamDetailList(new ContextUtil().getPatientId());
			  }
			
			return careTeamListForPatient;
		}
		public void setCareTeamListForPatient(List<DoctorDetail> careTeamListForPatient) {
			this.careTeamListForPatient = careTeamListForPatient;
		}
		
		/**
		 * diagonoses.jsf
		 * Get Patient Diagnosis Detail 
		 * @return Patient Diagnosis Detail List 
		 */
		public List<PatientDiagnosesDetails> getPatientICDDiagnosisList() {
			
			if(patientICDDiagnosisList==null)
			{
				System.out.println("patientICDDiagnosisList::::::::::::");
				patientICDDiagnosisList= new ArrayList<PatientDiagnosesDetails>();
				patientICDDiagnosisList=patientService.findPatientDiognosisDetails(new ContextUtil().getPatientId()); // Patient Diagnosis Detail Based on Patient Id 
			}
			
			
			return patientICDDiagnosisList;
		}
		
		
		
		/*public void checkSelectIntervention()
		{
			logger.info("checkSelectIntervention::::"+selectIntervention);
			if(selectIntervention==1 )
				setShowRxTab(true);
			else
				setShowRxTab(false);
			System.out.println("::::::::::::::::::>>>>>>"+isShowRxTab());

		}*/
		
		/*
		 * find  Master Diagnosis List 
		 * 
		 * @author; Sanket Singh
		 */
		public void searchMasterDiagnosisData(){
	
			logger.info("inside searchMasterDiagnosisData:::"+selectedIcdFormat+"::"+userInputForIcdDiagnosisSearchTable+":::::"+selectIntervention);
			if (getSelectedIcdFormat().equals("ICD10")) {
				//System.out.println(":::selectedIcdFormat:icd10::"+selectedIcdFormat+":::"+getUserInputForIcdDiagnosisSearchTable());
				dignosisdetailsList=new ArrayList<MasterDiagnosis>();
				dignosisdetailsList=patientService.findPatientDiognosisDetails1(getUserInputForIcdDiagnosisSearchTable(),selectIntervention); //Get  Master Diagnosis Data Based on Selected Diagnosis and Selected Intervation 
				//System.out.println("findPatientDiognosisDetails1::::::::::"+dignosisdetailsList.size());
				setIcd9DiagnosisList(null);
			}
			
			if (getSelectedIcdFormat().equals("ICD9")) {
				//System.out.println(":::selectedIcdFormat:icd9::"+selectedIcdFormat+":::"+userInputForIcdDiagnosisSearchTable);
				icd9DiagnosisList=new ArrayList<Icd9Diagnosis>();
				icd9DiagnosisList=patientService.getIcd9MasterList(getUserInputForIcdDiagnosisSearchTable(),selectIntervention); // Get ICd 9 Patient Diagnosis Detail Data 
				setDiagnosesDetails(null);

			}
			
			else {
				logger.info("searchMasterDiagnosisData in else block:getSelectedIcdFormat:"+getSelectedIcdFormat()+"getUserInputForIcdDiagnosisSearchTable:"+getUserInputForIcdDiagnosisSearchTable());
			}
			/*for(MasterDiagnosis  malist:dignosisdetailsList){
				System.out.println(" NameMasterList:::::: "+malist.getDxCode());
			}*/
		}
		
		
		/*
		 * 
		 * Find Diagnosis Name 
		*/
		public void findDiagnosisName()
		{

			MasterDiagnosis diagnosisdetail=(MasterDiagnosis) getSearchDiagnosisDataTable().getRowData();
			logger.info("diganosis:"+ diagnosisdetail.getDxCode()+"id:::::"+diagnosisdetail.getDxCode()+"::"+getSelectedIcdFormat());
			patientDiagnosesDetails.setIcdId(diagnosisdetail.getDxCode());
			patientDiagnosesDetails.setCodeDescription(diagnosisdetail.getLongDesc());
			patientDiagnosesDetails.setCode(diagnosisdetail.getShortDesc());
			//patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());
			patientDiagnosesDetails.setIcdCodeType(getSelectedIcdFormat());
			//patientService.savePatientDiagnosis(patientDiagnosesDetails);
			boolean check = false;
			for(PatientDiagnosesDetails patdiagosisDetail :getPatientICDDiagnosisList()){
				
				if(patdiagosisDetail.getIcdId().equals(patientDiagnosesDetails.getIcdId()))
					check =true;
			}
			if(!check){
				patientService.savePatientDiagnosis(patientDiagnosesDetails); // Save Patient Diagnosis Detail on Selection   
				callDashboardBeanMethodForUpdatingPatientDetail();
				patientDiagnosesDetails = new PatientDiagnosesDetails();
				patientICDDiagnosisList = null;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Diagnoses Saved Successfully", ""));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			}
			else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Already There", ""));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			}
			setUserInputForIcdDiagnosisSearchTable(null);
			patientICDDiagnosisList=null;
			dignosisdetailsList=null;
			icd9DiagnosisList=null;	
		    FacesContext context = FacesContext.getCurrentInstance();
			PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
			patientMedicineManageBean.setDrugDiseaseDetailList(null);
		}

	
		public void setPatientICDDiagnosisList(
				List<PatientDiagnosesDetails> patientICDDiagnosisList) {
			this.patientICDDiagnosisList = patientICDDiagnosisList;
		}
		public DataTable getSearchDiagnosisDataTable() {
			return searchDiagnosisDataTable;
		}
		public void setSearchDiagnosisDataTable(DataTable searchDiagnosisDataTable) {
			this.searchDiagnosisDataTable = searchDiagnosisDataTable;
		}
		public MasterDiagnosis getDiagnosesDetails() {
			return diagnosesDetails;
		}
		public void setDiagnosesDetails(MasterDiagnosis diagnosesDetails) {
			this.diagnosesDetails = diagnosesDetails;
		}
		public List<MasterDiagnosis > getDignosisdetailsList() {
		  
			return dignosisdetailsList;
		}
		public void setDignosisdetailsList(List<MasterDiagnosis > dignosisdetailsList) {
			this.dignosisdetailsList = dignosisdetailsList;
		}
		public PatientDiagnosesDetails getPatientDiagnosesDetails() {
			return patientDiagnosesDetails;
		}
		public void setPatientDiagnosesDetails(
				PatientDiagnosesDetails patientDiagnosesDetails) {
			this.patientDiagnosesDetails = patientDiagnosesDetails;
		}
		
		String  rowIdnumber;
	
		public void deletePatientDiagnosis()
		{
		
		logger.info("setidtodeletePatientdiagnosis method start:::");

		patientDiagnosesDetails=new PatientDiagnosesDetails();
		patientDiagnosesDetails= (PatientDiagnosesDetails) getDiagnosesdatatable().getRowData();
			FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	
			rowIdnumber=params.get("rowIdFordiagnosisList");
			int databaseDiognosisId=Integer.parseInt(params.get("dbId"));
			logger.info("database primary key id for diaognosis "+databaseDiognosisId);
			logger.info("value of rowIdFordiagnosisList:::::::::dig:::"+rowIdnumber);
			patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());	
			logger.info("value of patientId:::::::::getPatientId:::"+patientDiagnosesDetails.getPatientId());
			patientDiagnosesDetails.setId(databaseDiognosisId);
		    patientDiagnosesDetails.setIcdId(params.get("idValue"));
			patientDiagnosesDetails.setCode(params.get("codeValue"));
			patientDiagnosesDetails.setCodeDescription(params.get("codedescription"));
			
			logger.info("value of code:::::::::get code:::"+patientDiagnosesDetails.getCode()+"value of codedescription"+patientDiagnosesDetails.getCodeDescription());
	}
		/**
		 * diagonoses.jsf 
		 * Delete Diagnosis Data Row based on Selection  
		 */
		 public void deleteDiagnosisRow()
		{
			 String msg="";
			logger.info("Delete method start:::::::::::::");
			patientService.deleteDiagnosisDetails(patientDiagnosesDetails); // Delete Diagnosis Detail Data 
		    patientICDDiagnosisList=null;
		    callDashboardBeanMethodForUpdatingPatientDetail();
		    //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Diagnosis Deleted Successfully",  "");  
		    
		    msg="Diagnosis Deleted Successfully";
			FacesContext.getCurrentInstance().addMessage("messageUpdateForDiscountinueMeds", new FacesMessage(FacesMessage.SEVERITY_INFO,msg,""));
	        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	       System.out.println("updated message ::::::::");
		}
		 
       public void cleanDiagnosis()
       {
    	   logger.info("value is::::::::::::::::::::");
    	
    	   /*dignosisdetailsList=new ArrayList<MasterDiagnosis>();
    	   diagnosesDetails=new MasterDiagnosis();
    	   setPatientICD_10DiagnosisList(null);
			patientICDDiagnosisList=null;
			patientDiagnosesDetails=new PatientDiagnosesDetails();
			 RequestContext.getCurrentInstance().execute("searchDiagnosisPopUp.show();");*/
   	}
       
      /* 
       * diagonoses.jsf
       * Edit Or Modification of Existing Diagnosis detail data 
       * Author @sanket singh
       * */
       public void editDiagnosis ()
       {
    	   logger.info("value in bean::::::::::::::::::"+patientDiagnosesDetails.getIcdId());
    	   patientService.editDiagnosisDetails(patientDiagnosesDetails); // Edit/Modification of Diagnosis data   
    	   patientICDDiagnosisList=null;
    	   FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Diagnosis Edited Successfully",  "");  
	       FacesContext.getCurrentInstance().addMessage(null, message);  
       }
	/**
	 * @return the icd9Diagnosis
	 */
	public Icd9Diagnosis getIcd9Diagnosis() {
		return icd9Diagnosis;
	}
	/**
	 * @param icd9Diagnosis the icd9Diagnosis to set
	 */
	public void setIcd9Diagnosis(Icd9Diagnosis icd9Diagnosis) {
		this.icd9Diagnosis = icd9Diagnosis;
	}
	/**
	 * @return the icd9DiagnosisList
	 */
	public List<Icd9Diagnosis> getIcd9DiagnosisList() {
		
		return icd9DiagnosisList;
	}
	/**
	 * @param icd9DiagnosisList the icd9DiagnosisList to set
	 */
	public void setIcd9DiagnosisList(List<Icd9Diagnosis> icd9DiagnosisList) {
		this.icd9DiagnosisList = icd9DiagnosisList;
	}
	/**
	 * @return the selectedIcdFormat
	 */
	public String getSelectedIcdFormat() {
		return selectedIcdFormat;
	}
	/**
	 * @param selectedIcdFormat the selectedIcdFormat to set
	 */
	public void setSelectedIcdFormat(String selectedIcdFormat) {
		this.selectedIcdFormat = selectedIcdFormat;
	}
	/**
	 * @return the icd9DiagnosisTable
	 */
	public DataTable getIcd9DiagnosisTable() {
		return icd9DiagnosisTable;
	}
	/**
	 * @param icd9DiagnosisTable the icd9DiagnosisTable to set
	 */
	public void setIcd9DiagnosisTable(DataTable icd9DiagnosisTable) {
		this.icd9DiagnosisTable = icd9DiagnosisTable;
	}

  /**
   * diagonoses.jsf 
   * Set Diagnosis Value of ICD 9 diagnosis for Saving ICD 9 Diagnosis 
   */
	public void setIcd9DiagnosisName()
	{
		Icd9Diagnosis icd9DiagnosisTable=(Icd9Diagnosis) getIcd9DiagnosisTable().getRowData();
		logger.info("diganosis:"+ icd9DiagnosisTable.getShortDescription()+"id:::::"+icd9DiagnosisTable.getId()+"::"+getSelectedIcdFormat());
		patientDiagnosesDetails.setIcdId(icd9DiagnosisTable.getFormatedCode());
		patientDiagnosesDetails.setCodeDescription(icd9DiagnosisTable.getLongDiscription());
		patientDiagnosesDetails.setCode(icd9DiagnosisTable.getShortDescription());
		patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());
		patientDiagnosesDetails.setIcdCodeType(getSelectedIcdFormat());
		//patientService.savePatientDiagnosis(patientDiagnosesDetails);
		boolean check = false;
		for(PatientDiagnosesDetails patdiagosisDetail :getPatientICDDiagnosisList()){
			
			if(patdiagosisDetail.getIcdId().equals(patientDiagnosesDetails.getIcdId()))
				check =true;
		}
		if(!check){
			patientService.savePatientDiagnosis(patientDiagnosesDetails); // Save ICD9 Diagnosis Data 
			callDashboardBeanMethodForUpdatingPatientDetail();
			patientDiagnosesDetails = new PatientDiagnosesDetails();
			patientICDDiagnosisList = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Diagnoses Saved Successfully", "")); // Show message after saved Patient Diagnosis detail  
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		else{
	         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Already There", ""));
	         FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		patientICDDiagnosisList=null;
		icd9DiagnosisList=null;
		setUserInputForIcdDiagnosisSearchTable(null);
		dignosisdetailsList=null;
		FacesContext context = FacesContext	.getCurrentInstance();
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		patientMedicineManageBean.setDrugDiseaseDetailList(null);
	}
	
	public void cleanDiagnosisForm()
	{
		icd9DiagnosisList=null; // Set ICd9 diagnosis Detail Data null 
		setUserInputForIcdDiagnosisSearchTable(null);
		dignosisdetailsList=null;
		setSelectIntervention(1);
		setSelectedIcdFormat(null);
	}
	
	

	/**
	 * @return the userInputForIcdDiagnosisSearchTable
	 */
	public String getUserInputForIcdDiagnosisSearchTable() {
		return userInputForIcdDiagnosisSearchTable;
	}
	/**
	 * @param userInputForIcdDiagnosisSearchTable the userInputForIcdDiagnosisSearchTable to set
	 */
	public void setUserInputForIcdDiagnosisSearchTable(
			String userInputForIcdDiagnosisSearchTable) {
		this.userInputForIcdDiagnosisSearchTable = userInputForIcdDiagnosisSearchTable;
	}
	
/*
 * **method to update diagnosis on patient
 * Detail header after adding new Diagnosis
 * @uthor:saurabh
 */
	public void callDashboardBeanMethodForUpdatingPatientDetail()
	{
		logger.info("callDashboardBeanMethodForUpdatingPatientDetail called in PatientManageBean:::::::");
		FacesContext context = FacesContext	.getCurrentInstance();
		DashBoardManageBean dashBoardManageBean=(DashBoardManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"dashBoardManageBean");
		dashBoardManageBean.updateDiagnosisListAfterAddingNewDiagnosis();
	}
	public MasterDiagnosis getSelectedDiagonisObj() {
		if(selectedDiagonisObj==null){
			selectedDiagonisObj=new MasterDiagnosis();
		}
		return selectedDiagonisObj;
	}
	public void setSelectedDiagonisObj(MasterDiagnosis selectedDiagonisObj) {
		this.selectedDiagonisObj = selectedDiagonisObj;
	}
	public Icd9Diagnosis getSelectedDiagonsisICD9Obj() {
		if(selectedDiagonsisICD9Obj==null){
			selectedDiagonsisICD9Obj=new Icd9Diagnosis();
		}
		return selectedDiagonsisICD9Obj;
	}
	public void setSelectedDiagonsisICD9Obj(Icd9Diagnosis selectedDiagonsisICD9Obj) {
		this.selectedDiagonsisICD9Obj = selectedDiagonsisICD9Obj;
	}

	/*
	 * for overview Diagnosis for ICD10 in Generic Med action plan
	 */
	public void findDiagnosisICD10NameOverview(){
		MasterDiagnosis diagnosisdetail=selectedDiagonisObj;
		logger.info("diganosis overview :"+ diagnosisdetail.getDxCode()+"id overview page:::::"+diagnosisdetail.getDxCode()+"::"+getSelectedIcdFormat());
		patientDiagnosesDetails.setIcdId(diagnosisdetail.getDxCode());
		patientDiagnosesDetails.setCodeDescription(diagnosisdetail.getLongDesc());
		patientDiagnosesDetails.setCode(diagnosisdetail.getShortDesc());
		patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());
		patientDiagnosesDetails.setIcdCodeType(getSelectedIcdFormat());
	
		setUserInputForIcdDiagnosisSearchTable(null);
		patientICDDiagnosisList=null;
		dignosisdetailsList=null;
		icd9DiagnosisList=null;	
	    FacesContext context = FacesContext.getCurrentInstance();
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		patientMedicineManageBean.setPatientDiagnosesDetailsObj(patientDiagnosesDetails);
		patientMedicineManageBean.getParentMedActionPlan().setPatientDiagnosisDetailsObj(patientDiagnosesDetails);
		//	patientMedicineManageBean.getParentMedActionPlan().setPatientDiagnosisDetailsId(patientDiagnosesDetails.getIcdId());
		
		/*if(patientMedicineManageBean.getGenericMedActionPlanList().isEmpty()){
			patientMedicineManageBean.setPatientDiagnosesDetailsObj(patientDiagnosesDetails);
	
		}else{
			patientMedicineManageBean.getGenericMedActionPlanList().get(0).setPatientDiagnosesDetails(patientDiagnosesDetails);
			patientMedicineManageBean.setPatientDiagnosesDetailsObj(patientDiagnosesDetails);
		}*/
	}
	
	
	public void prepareDiagnosisObj(){
		logger.info("prepare diagnosis object method fired");
		patientDiagnosesDetails=new PatientDiagnosesDetails();
		patientDiagnosesDetails=patientDiagnosesDetailsObj;
		setUserInputForIcdDiagnosisSearchTable(null);
		patientICDDiagnosisList=null;
		dignosisdetailsList=null;
		icd9DiagnosisList=null;	
	    FacesContext context = FacesContext.getCurrentInstance();
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		patientMedicineManageBean.setPatientDiagnosesDetailsObj(patientDiagnosesDetails);
		patientMedicineManageBean.getParentMedActionPlan().setPatientDiagnosisDetailsObj(patientDiagnosesDetails);
	}
	
	/*
	 * for overview Diagnosis for ICD9 in Generic Med action plan
	 */
	public void  setIcd9DiagnosisNameOverview(){
		Icd9Diagnosis icd9DiagnosisTable=selectedDiagonsisICD9Obj;
		logger.info("diganosis overview:"+ icd9DiagnosisTable.getShortDescription()+"id::overview:::"+icd9DiagnosisTable.getId()+"::"+getSelectedIcdFormat());
		patientDiagnosesDetails.setIcdId(icd9DiagnosisTable.getDxCode());
		patientDiagnosesDetails.setCodeDescription(icd9DiagnosisTable.getLongDiscription());
		patientDiagnosesDetails.setCode(icd9DiagnosisTable.getShortDescription());
		patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());
		patientDiagnosesDetails.setIcdCodeType(getSelectedIcdFormat());
		
		patientICDDiagnosisList=null;
		icd9DiagnosisList=null;
		setUserInputForIcdDiagnosisSearchTable(null);
		dignosisdetailsList=null;
		FacesContext context = FacesContext	.getCurrentInstance();
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		/*if(patientMedicineManageBean.getGenericMedActionPlanList().isEmpty()){*/
			patientMedicineManageBean.setPatientDiagnosesDetailsObj(patientDiagnosesDetails);
			patientMedicineManageBean.getParentMedActionPlan().setPatientDiagnosisDetailsObj(patientDiagnosesDetails);
	
		/*}else{
			patientMedicineManageBean.getGenericMedActionPlanList().get(0).setPatientDiagnosesDetails(patientDiagnosesDetails);
			patientMedicineManageBean.setPatientDiagnosesDetailsObj(patientDiagnosesDetails);
		}*/
}
	/*public void setCareTeamListForPatientforSort(
			List<DoctorDetail> careTeamListForPatientforSort) {
		this.careTeamListForPatientforSort = careTeamListForPatientforSort;
	}
	public List<DoctorDetail> getCareTeamListForPatientforSort() {
		
		if(careTeamListForPatientforSort == null)
		{
			
		careTeamListForPatientforSort=patientService.findCareTeamDetailList(new ContextUtil().getPatientId());
		}
		

		return careTeamListForPatientforSort;
	}*/
	public String getUserInputForIcdCode() {
		return userInputForIcdCode;
	}
	public void setUserInputForIcdCode(String userInputForIcdCode) {
		this.userInputForIcdCode = userInputForIcdCode;
	}
	public int getSelectIntervention() {
		return selectIntervention;
	}
	public void setSelectIntervention(int selectIntervention) {
		this.selectIntervention = selectIntervention;
	}
	public boolean isShowRxTab() {
		return showRxTab;
	}
	public void setShowRxTab(boolean showRxTab) {
		this.showRxTab = showRxTab;
	}
	
	public void searchMasterDiagnosisDataNewPatient(){
		
		logger.info("inside searchMasterDiagnosisData:::"+selectedIcdFormat+"::"+userInputForIcdDiagnosisSearchTable+":::::"+selectIntervention);
		if (getSelectedIcdFormat().equals("ICD10")) {
			//System.out.println(":::selectedIcdFormat:icd10::"+selectedIcdFormat+":::"+getUserInputForIcdDiagnosisSearchTable());
			dignosisdetailsList=new ArrayList<MasterDiagnosis>();
			dignosisdetailsList=patientService.findPatientDiognosisDetails1(getUserInputForIcdDiagnosisSearchTable(),selectIntervention); 
			//System.out.println("findPatientDiognosisDetails1::::::::::"+dignosisdetailsList.size());
			setIcd9DiagnosisList(null);
		}
		
		if (getSelectedIcdFormat().equals("ICD9")) {
			//System.out.println(":::selectedIcdFormat:icd9::"+selectedIcdFormat+":::"+userInputForIcdDiagnosisSearchTable);
			icd9DiagnosisList=new ArrayList<Icd9Diagnosis>();
			icd9DiagnosisList=patientService.getIcd9MasterList(getUserInputForIcdDiagnosisSearchTable(),selectIntervention); // Get 
			setDiagnosesDetails(null);

		}
		
		else {
			logger.info("searchMasterDiagnosisData in else block:getSelectedIcdFormat:"+getSelectedIcdFormat()+"getUserInputForIcdDiagnosisSearchTable:"+getUserInputForIcdDiagnosisSearchTable());
		}
	}
	
	public void findDiagnosisNameNewPatient()
	{
       logger.info("findDiagnosisNameNewPatient method starts::::::::");
		MasterDiagnosis diagnosisdetail=(MasterDiagnosis) getMasterDiagnosesDataBindingNewPatient().getRowData();
		logger.info("diganosis:"+ diagnosisdetail.getDxCode()+"id:::::"+diagnosisdetail.getDxCode()+"::"+getSelectedIcdFormat());
		patientDiagnosesDetails.setIcdId(diagnosisdetail.getDxCode());
		patientDiagnosesDetails.setCodeDescription(diagnosisdetail.getLongDesc());
		patientDiagnosesDetails.setCode(diagnosisdetail.getShortDesc());
		//patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());
		patientDiagnosesDetails.setIcdCodeType(getSelectedIcdFormat());
		patientDiagnosesDetails.setDocFirstName(new ContextUtil().getLoggerFirstName());
		patientDiagnosesDetails.setDocLastName(new ContextUtil().getLoggerLastName());
		patientDiagnosesDetails.setDocmiddleName(new ContextUtil().getLoggedUserMiddleName());
		//patientService.savePatientDiagnosis(patientDiagnosesDetails);
		boolean check = false;
		for(PatientDiagnosesDetails patdiagosisDetail :pateintDiagnosesForNewPatientList){
			
			if(patdiagosisDetail.getIcdId().equals(patientDiagnosesDetails.getIcdId()))
				check =true;
		}
		if(!check){
			pateintDiagnosesForNewPatientList.add(patientDiagnosesDetails);
			//callDashboardBeanMethodForUpdatingPatientDetail();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Diagnoses Added Successfully", ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Already There", ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		patientDiagnosesDetails = new PatientDiagnosesDetails();
		patientICDDiagnosisList = null;
		setUserInputForIcdDiagnosisSearchTable(null);
		//patientICDDiagnosisList=null;
		//dignosisdetailsList=null;
		icd9DiagnosisList=null;	
		setDignosisdetailsList(null);
	    FacesContext context = FacesContext.getCurrentInstance();
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		patientMedicineManageBean.setDrugDiseaseDetailList(null);
	}
	
	public void setIcd9DiagnosisNameForNewPatient()
	{
		logger.info("setIcd9DiagnosisNameForNewPatient method starts::::::::");
		Icd9Diagnosis icd9DiagnosisTable=(Icd9Diagnosis)getMasterIcd9Binding().getRowData();
		logger.info("diganosis:"+ icd9DiagnosisTable.getShortDescription()+"id:::::"+icd9DiagnosisTable.getId()+"::"+getSelectedIcdFormat());
		patientDiagnosesDetails.setIcdId(icd9DiagnosisTable.getFormatedCode());
		patientDiagnosesDetails.setCodeDescription(icd9DiagnosisTable.getLongDiscription());
		patientDiagnosesDetails.setCode(icd9DiagnosisTable.getShortDescription());
		//patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());
		patientDiagnosesDetails.setIcdCodeType(getSelectedIcdFormat());
		patientDiagnosesDetails.setDocFirstName(new ContextUtil().getLoggerFirstName());
		patientDiagnosesDetails.setDocLastName(new ContextUtil().getLoggerLastName());
		patientDiagnosesDetails.setDocmiddleName(new ContextUtil().getLoggedUserMiddleName());
		//patientService.savePatientDiagnosis(patientDiagnosesDetails);
		boolean check = false;
		for(PatientDiagnosesDetails patdiagosisDetail :pateintDiagnosesForNewPatientList){
			
			if(patdiagosisDetail.getIcdId().equals(patientDiagnosesDetails.getIcdId()))
				check =true;
		}
		if(!check){
			pateintDiagnosesForNewPatientList.add(patientDiagnosesDetails);
			//patientService.savePatientDiagnosis(patientDiagnosesDetails);
			//callDashboardBeanMethodForUpdatingPatientDetail();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Diagnoses Added Successfully", ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		else{
	         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Already There", ""));
	         FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		patientDiagnosesDetails = new PatientDiagnosesDetails();
		patientICDDiagnosisList = null;
		//patientICDDiagnosisList=null;
		icd9DiagnosisList=null;
		setUserInputForIcdDiagnosisSearchTable(null);
		//dignosisdetailsList=null;
		setDignosisdetailsList(null);
		FacesContext context = FacesContext	.getCurrentInstance();
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		patientMedicineManageBean.setDrugDiseaseDetailList(null);
	}
	public List<PatientDiagnosesDetails> getPateintDiagnosesForNewPatientList() {
		return pateintDiagnosesForNewPatientList;
	}
	public void setPateintDiagnosesForNewPatientList(
			List<PatientDiagnosesDetails> pateintDiagnosesForNewPatientList) {
		this.pateintDiagnosesForNewPatientList = pateintDiagnosesForNewPatientList;
	}
	public void deletePatientDiagnosisNewPatient()
	{
	
	logger.info("deletePatientDiagnosisNewPatient:::::::::setidtodeletePatientdiagnosis method start:::for new Pateint");
		patientDiagnosesDetails=new PatientDiagnosesDetails();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();

		rowIdnumber=params.get("rowIdFordiagnosisList");
		int databaseDiognosisId=Integer.parseInt(params.get("dbId"));
		logger.info("database primary key id for diaognosis "+databaseDiognosisId);
		logger.info("value of rowIdFordiagnosisList:::::::::dig:::"+rowIdnumber);
		/*patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());	
		logger.info("value of patientId:::::::::getPatientId:::"+patientDiagnosesDetails.getPatientId());*/
		patientDiagnosesDetails.setId(databaseDiognosisId);
	    patientDiagnosesDetails.setIcdId(params.get("idValue"));
		patientDiagnosesDetails.setCode(params.get("codeValue"));
		
		logger.info("value of code:::::::::get code:::"+patientDiagnosesDetails.getCode());
}
	
	public void deleteDiagnosisRowNewPatient()
	{
		// String msg="";
		logger.info("Delete method start for new patient:::::::::::::");
	    //patientICDDiagnosisList=null;
	//    pateintDiagnosesForNewPatientList.remove(rowIdnumber);
	    pateintDiagnosesForNewPatientList.remove(patientDiagnosesDetails.getId());
	    pateintDiagnosesForNewPatientList.remove(patientDiagnosesDetails.getIcdId());
	    pateintDiagnosesForNewPatientList.remove(patientDiagnosesDetails.getCode());
	    logger.info("pateintDiagnosesForNewPatientList size:::::::::"+pateintDiagnosesForNewPatientList.size());
	   // msg="Diagnosis Deleted Successfully";
		FacesContext.getCurrentInstance().addMessage("messageUpdateForDiscountinueMeds", new FacesMessage(FacesMessage.SEVERITY_INFO,"Diagnosis Deleted Successfully",""));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
       System.out.println("updated message ::::::::");
	}
	
	
	public DataTable getMasterDiagnosesDataBindingNewPatient() {
		return masterDiagnosesDataBindingNewPatient;
	}
	public void setMasterDiagnosesDataBindingNewPatient(
			DataTable masterDiagnosesDataBindingNewPatient) {
		this.masterDiagnosesDataBindingNewPatient = masterDiagnosesDataBindingNewPatient;
	}
	public DataTable getMasterIcd9Binding() {
		return masterIcd9Binding;
	}
	public void setMasterIcd9Binding(DataTable masterIcd9Binding) {
		this.masterIcd9Binding = masterIcd9Binding;
	}
	public DataTable getEditPateintMasterICD10DiagnosesBinding() {
		return editPateintMasterICD10DiagnosesBinding;
	}
	public void setEditPateintMasterICD10DiagnosesBinding(
			DataTable editPateintMasterICD10DiagnosesBinding) {
		this.editPateintMasterICD10DiagnosesBinding = editPateintMasterICD10DiagnosesBinding;
	}
	public DataTable getEditPateintMasterICD9DiagnosesBinding() {
		return editPateintMasterICD9DiagnosesBinding;
	}
	public void setEditPateintMasterICD9DiagnosesBinding(
			DataTable editPateintMasterICD9DiagnosesBinding) {
		this.editPateintMasterICD9DiagnosesBinding = editPateintMasterICD9DiagnosesBinding;
	}
	
	
	
	public void findDiagnosisNameEditPatient()
	{
       logger.info("findDiagnosisNameNewPatient method starts::::::::");
		MasterDiagnosis diagnosisdetail=(MasterDiagnosis) getEditPateintMasterICD10DiagnosesBinding().getRowData();
		logger.info("diganosis:"+ diagnosisdetail.getDxCode()+"id:::::"+diagnosisdetail.getDxCode()+"::"+getSelectedIcdFormat());
		patientDiagnosesDetails.setIcdId(diagnosisdetail.getDxCode());
		patientDiagnosesDetails.setCodeDescription(diagnosisdetail.getLongDesc());
		patientDiagnosesDetails.setCode(diagnosisdetail.getShortDesc());
		patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());
		patientDiagnosesDetails.setDate(new Date());
		patientDiagnosesDetails.setDoctorId(new ContextUtil().getLoginId());
		patientDiagnosesDetails.setIcdCodeType(getSelectedIcdFormat());
		patientDiagnosesDetails.setDocFirstName(new ContextUtil().getLoggerFirstName());
		patientDiagnosesDetails.setDocLastName(new ContextUtil().getLoggerLastName());
		patientDiagnosesDetails.setDocmiddleName(new ContextUtil().getLoggedUserMiddleName());
		//patientService.savePatientDiagnosis(patientDiagnosesDetails);
		boolean check = false;
		for(PatientDiagnosesDetails patdiagosisDetail :getPatientICDDiagnosisList()){
			
			if(patdiagosisDetail.getIcdId().equals(patientDiagnosesDetails.getIcdId()))
				check =true;
		}
		if(!check){
			getPateintDiagnosesForEditProfileList();
			pateintDiagnosesForEditProfileList.add(patientDiagnosesDetails);
			//patientICDDiagnosisList.addAll(pateintDiagnosesForEditProfileList);
			//callDashboardBeanMethodForUpdatingPatientDetail();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Diagnoses Added Successfully", ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Already There", ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		patientDiagnosesDetails = new PatientDiagnosesDetails();
		//patientICDDiagnosisList = null;
		setUserInputForIcdDiagnosisSearchTable(null);
		//patientICDDiagnosisList=null;
		//dignosisdetailsList=null;
		icd9DiagnosisList=null;	
		setDignosisdetailsList(null);
	    FacesContext context = FacesContext.getCurrentInstance();
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		patientMedicineManageBean.setDrugDiseaseDetailList(null);
	}
	
	public void setIcd9DiagnosisNameForEditPatient()
	{
		logger.info("setIcd9DiagnosisNameForNewPatient method starts::::::::");
		Icd9Diagnosis icd9DiagnosisTable=(Icd9Diagnosis)getEditPateintMasterICD9DiagnosesBinding().getRowData();
		logger.info("diganosis:"+ icd9DiagnosisTable.getShortDescription()+"id:::::"+icd9DiagnosisTable.getId()+"::"+getSelectedIcdFormat());
		patientDiagnosesDetails.setIcdId(icd9DiagnosisTable.getFormatedCode());
		patientDiagnosesDetails.setCodeDescription(icd9DiagnosisTable.getLongDiscription());
		patientDiagnosesDetails.setCode(icd9DiagnosisTable.getShortDescription());
		patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());
		patientDiagnosesDetails.setDate(new Date());
		patientDiagnosesDetails.setDoctorId(new ContextUtil().getLoginId());
		patientDiagnosesDetails.setIcdCodeType(getSelectedIcdFormat());
		patientDiagnosesDetails.setDocFirstName(new ContextUtil().getLoggerFirstName());
		patientDiagnosesDetails.setDocLastName(new ContextUtil().getLoggerLastName());
		patientDiagnosesDetails.setDocmiddleName(new ContextUtil().getLoggedUserMiddleName());
		//patientService.savePatientDiagnosis(patientDiagnosesDetails);
		boolean check = false;
		for(PatientDiagnosesDetails patdiagosisDetail :getPatientICDDiagnosisList()){
			
			if(patdiagosisDetail.getIcdId().equals(patientDiagnosesDetails.getIcdId()))
				check =true;
		}
		if(!check){
			getPateintDiagnosesForEditProfileList();
			pateintDiagnosesForEditProfileList.add(patientDiagnosesDetails);
			//patientICDDiagnosisList.addAll(pateintDiagnosesForEditProfileList);
			//patientService.savePatientDiagnosis(patientDiagnosesDetails);
			//callDashboardBeanMethodForUpdatingPatientDetail();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Diagnoses Added Successfully", ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		else{
	         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Already There", ""));
	         FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		patientDiagnosesDetails = new PatientDiagnosesDetails();
		//patientICDDiagnosisList = null;
		//patientICDDiagnosisList=null;
		icd9DiagnosisList=null;
		setUserInputForIcdDiagnosisSearchTable(null);
		//dignosisdetailsList=null;
		setDignosisdetailsList(null);
		FacesContext context = FacesContext	.getCurrentInstance();
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		patientMedicineManageBean.setDrugDiseaseDetailList(null);
	}
	
	
	
	public List<PatientDiagnosesDetails> getPateintDiagnosesForEditProfileList() {
		if(pateintDiagnosesForEditProfileList == null){
			pateintDiagnosesForEditProfileList = new ArrayList<PatientDiagnosesDetails>();
		}
		return pateintDiagnosesForEditProfileList;
	}
	public void setPateintDiagnosesForEditProfileList(
			List<PatientDiagnosesDetails> pateintDiagnosesForEditProfileList) {
		this.pateintDiagnosesForEditProfileList = pateintDiagnosesForEditProfileList;
	}
	public DataTable getDiagnosesdatatable() {
		return diagnosesdatatable;
	}
	public void setDiagnosesdatatable(DataTable diagnosesdatatable) {
		this.diagnosesdatatable = diagnosesdatatable;
	}
	
	//By clicking more button in patient profile page redirect to allergy page by venu
	public String AllergyMore()
	{
		return "go_to_My_Allergy";
	}
	
	public CareTeam getCareTeam() {
		return careTeam;
	}
	public void setCareTeam(CareTeam careTeam) {
		this.careTeam = careTeam;
	}
	
	public int getRowIdForEditCareTeam() {
		return rowIdForEditCareTeam;
	}
	public void setRowIdForEditCareTeam(int rowIdForEditCareTeam) {
		this.rowIdForEditCareTeam = rowIdForEditCareTeam;
	}
	

	public int getRowIdForDeleteCareTeam() {
		return rowIdForDeleteCareTeam;
	}
	public void setRowIdForDeleteCareTeam(int rowIdForDeleteCareTeam) {
		this.rowIdForDeleteCareTeam = rowIdForDeleteCareTeam;
	}
	
	public int getAddClinakosPhysician() {
		return addClinakosPhysician;
	}
	public void setAddClinakosPhysician(int addClinakosPhysician) {
		this.addClinakosPhysician = addClinakosPhysician;
	}
	

/*@saurabh
 * *****added by saurabh for 	
 * save new doctor detail in care Team
 */	
	public void saveDocForCareTeam()
	{
		logger.info(":::::SaveDocForCareTeam method called in patientManageBean::::::::::");
		System.out.println(careTeam.getDoctorFirstName()+careTeam.getDoctorLastName()+getAddClinakosPhysician());
			 careTeam.setPatientId(new ContextUtil().getPatientId());
			 patientService.saveDocForCareTeam(careTeam);
		careTeam=new CareTeam();
		careTeamListForPatient=null;
	}

/*@saurabh
 * *****added by saurabh for 	
 * cancel add doctor detail pop-up for care Team
 * Also open dialog box for add doc, after cleaning careTeam obj
*/
	public void cancelDocForCareTeam(){
		logger.info("::::::::CancelDocForCareTeam method called in patientManageBean:::::::::::");
		careTeam= new CareTeam();
		setRowIdForDeleteCareTeam(0);
		setRowIdForEditCareTeam(0);
	}
	
/*@saurabh
 * *****select Row Id from care team for delete doctor 
 * 
 */
	public void selectRowIdFromCareTeamForDelete(){
		logger.info(":::::selectRowIdFromCareTeamForDelete method called in patientManageBean::::::::::");
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		System.out.println(Integer.parseInt(params.get("careTeamRowId")));
		setRowIdForDeleteCareTeam(Integer.parseInt(params.get("careTeamRowId")));
	}

/*@saurabh
 * *****added by saurabh for 	
 * delete doctor detail from care Team
 */
	public void deleteDoctorFromCareTeam(){
		logger.info(":::::deleteDoctorFromCareTeam method called in patientManageBean::::::::::"+getRowIdForDeleteCareTeam());
		patientService.deleteDoctorFromCareTeam(getRowIdForDeleteCareTeam());
		careTeamListForPatient=null;
		setRowIdForDeleteCareTeam(0); 
	}

/*@saurabh
 * 	*******select row Id for edit doctor from care team
 */
	
	public void selectRowIdForEditDoctorFromCareTeam(){
		logger.info(":::::selectRowIdForEditDoctorFromCareTeam method called in patientManageBean::::::::::");
		careTeam=new CareTeam();
    	FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		System.out.println(Integer.parseInt(params.get("careTeamRowId")));
		careTeam.setId(Integer.parseInt(params.get("careTeamRowId")));
		careTeam.setDoctorFirstName(params.get("careTeamDocFirstName"));
		careTeam.setDoctorLastName(params.get("careTeamLastName"));
		careTeam.setAddress(params.get("careTeamAddress"));
		careTeam.setDegree(params.get("careTeamDegree"));
		careTeam.setSpecialty(params.get("careTeamSpeciality"));
		careTeam.setEmail(params.get("careTeamEmail"));
		careTeam.setNpi(params.get("careTeamNpi"));
		careTeam.setPhoneNumber(params.get("careTeamPhoneNumber"));  
		
		System.out.println(careTeam.getDoctorFirstName()+careTeam.getDoctorLastName()+careTeam.getAddress()+careTeam.getDegree()+careTeam.getDegree()+careTeam.getSpecialty()+
    			careTeam.getEmail()+careTeam.getNpi()+careTeam.getPhoneNumber());
	}
	
	
/*@saurabh
 * *****added by saurabh for 	
 * edit doctor detail in care Team
 */
    public void editDoctorFromCareTeam(){
    	logger.info(":::::editDoctorFromCareTeam method called in patientManageBean::::::::::");
    	System.out.println(careTeam.getDoctorFirstName()+careTeam.getDoctorLastName()+careTeam.getAddress()+careTeam.getDegree()+careTeam.getDegree()+careTeam.getSpecialty()+
    			careTeam.getEmail()+careTeam.getNpi()+careTeam.getPhoneNumber());
		patientService.editDoctorFromCareTeam(careTeam);
		careTeamListForPatient=null;
		setRowIdForEditCareTeam(0);
	}
    
/*@author: saurabh
 * ********added by saurabh for
 * searching clinakos existing doctor for add doc in care team
 */
    
    public List<EmpProviderLocation> searchClinakosDocForCareTeam(){
    	logger.info(":::::searchClinakosDocForCareTeam method called in patientManageBean::::::::::"+getValueForSearchingClinakosDoc());
		empProviderLocationList=new ArrayList<EmpProviderLocation>();
		empProviderLocationList=patientService.searchDocFromExistingClinakos(getValueForSearchingClinakosDoc(),new ContextUtil().getProviderId());
		setValueForSearchingClinakosDoc("");
    	return empProviderLocationList;
    }
	public String getValueForSearchingClinakosDoc() {
		return valueForSearchingClinakosDoc;
	}
	public void setValueForSearchingClinakosDoc(String valueForSearchingClinakosDoc) {
		this.valueForSearchingClinakosDoc = valueForSearchingClinakosDoc;
	}
	
/*@author: saurabh
 * ********added by saurabh for
 * searching clinakos existing doctor for add doc in care team
 */
	public List<EmpProviderLocation> getEmpProviderLocationList() {
		return empProviderLocationList;
	}
	public void setEmpProviderLocationList(
			List<EmpProviderLocation> empProviderLocationList) {
		this.empProviderLocationList = empProviderLocationList;
	}

/*@uthor: saurabh
 * **********to add clinakos doctor in care team
 * 
 */
	public void addClinakosDoctorIncareTeam(){
		logger.info(":::::addClinakosDoctorIncareTeam method called in patientManageBean::::::::::");
		careTeam= new CareTeam();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		System.out.println(Integer.parseInt(params.get("clinakosDoctorId")));
		careTeam.setDoctorId(Integer.parseInt(params.get("clinakosDoctorId")));
		careTeam.setPatientId(new ContextUtil().getPatientId());
		//careTeam.setDoctorId(new ContextUtil().getLoginId());
		careTeam.setProviderId(new ContextUtil().getProviderId());
		careTeam.setClinakosUser(true);
		careTeam.setActive(true);
		patientService.addClinakosDoctorIncareTeam(careTeam);
		careTeamListForPatient=null;
	}
	public int getChooseSelectedDiagnosisOption() {
		return chooseSelectedDiagnosisOption;
	}
	public void setChooseSelectedDiagnosisOption(int chooseSelectedDiagnosisOption) {
		this.chooseSelectedDiagnosisOption = chooseSelectedDiagnosisOption;
	}
	
	public void showDiagnosisPanel(){
		logger.info("Diagnosis event change panel fired");
		logger.info("Selected Radio value "+chooseSelectedDiagnosisOption);
		if(chooseSelectedDiagnosisOption==1){
			setNewDiagnosis(false);
		//	RequestContext.getCurrentInstance().addCallbackParam("isNewDiagnosisSelected", false);
		}else if(chooseSelectedDiagnosisOption==2){
			setNewDiagnosis(true);
		//	RequestContext.getCurrentInstance().addCallbackParam("isNewDiagnosisSelected", true);
		}
	}
	
	private boolean isNewDiagnosis=false;
	public boolean isNewDiagnosis() {
		return isNewDiagnosis;
	}
	public void setNewDiagnosis(boolean isNewDiagnosis) {
		this.isNewDiagnosis = isNewDiagnosis;
	}
	public PatientDiagnosesDetails getPatientDiagnosesDetailsObj() {
		if(patientDiagnosesDetailsObj==null){
			patientDiagnosesDetailsObj=new PatientDiagnosesDetails();
		}
		return patientDiagnosesDetailsObj;
	}
	public void setPatientDiagnosesDetailsObj(
			PatientDiagnosesDetails patientDiagnosesDetailsObj) {
		this.patientDiagnosesDetailsObj = patientDiagnosesDetailsObj;
	}
	
	
	
}   

