package com.clinakos.viewController.bean;

import static com.clinakos.common.util.ClinakosConstant.KIDNEYFUNCTION;
import static com.clinakos.common.util.ClinakosConstant.LIVERFUNCTION;
import static com.clinakos.common.util.ClinakosConstant.MAINTAINENCE_STAGE;
import static com.clinakos.common.util.ClinakosConstant.NORMAL_WEIGHT;
import static com.clinakos.common.util.ClinakosConstant.OBESE;
import static com.clinakos.common.util.ClinakosConstant.OVER_WEIGHT;
import static com.clinakos.common.util.ClinakosConstant.UNDERWEIGHT;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.CartesianChartModel;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriUtils;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.DateUtil;
import com.clinakos.common.util.NotificationUtil;
import com.clinakos.data.model.core.AnticoagProviderLocation;
import com.clinakos.data.model.core.DiagnosisUploadData;
import com.clinakos.data.model.core.DiagnosisUploadDataErrorMessage;
import com.clinakos.data.model.core.EmpProviderLocation;
import com.clinakos.data.model.core.ForgotPasswordSendingLink;
import com.clinakos.data.model.core.FormularyDetail;
import com.clinakos.data.model.core.InsuranceCompanies;
import com.clinakos.data.model.core.LoginSecurity;
import com.clinakos.data.model.core.OrganisationType;
import com.clinakos.data.model.core.PatientUploadData;
import com.clinakos.data.model.core.PatientUploadDataErrorMessages;
import com.clinakos.data.model.core.ProviderDetail;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.core.ProviderUser;
import com.clinakos.data.model.core.RoleSecurity;
import com.clinakos.data.model.core.RptDrugCategory;
import com.clinakos.data.model.patient.ChartModel;
import com.clinakos.data.model.patient.ClinicDoctor;
import com.clinakos.data.model.patient.ClinicMaster;
import com.clinakos.data.model.patient.ClinicProvider;
import com.clinakos.data.model.patient.DiagnosesChart;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.DrugCategoryDetail;
import com.clinakos.data.model.patient.EmployerDetails;
import com.clinakos.data.model.patient.FormularyChart;
import com.clinakos.data.model.patient.LOVCode;
import com.clinakos.data.model.patient.LOVType;
import com.clinakos.data.model.patient.MasterTimeZone;
import com.clinakos.data.model.patient.NetworkChart;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientCountForDrugGene;
/*import com.clinakos.data.model.patient.NetworkChart;*/
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientGuarantor;
import com.clinakos.data.model.patient.PatientProvider;
import com.clinakos.data.model.patient.PatientProviderClinic;
import com.clinakos.data.model.patient.PatientVital;
import com.clinakos.data.model.patient.SpecialityDrugCategory;
import com.clinakos.data.model.patient.UserInsuranceDetails;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.ViewFunctionalDetails;
import com.clinakos.data.model.patient.WSDrug;
import com.clinakos.service.IDashBoardService;
import com.clinakos.service.IRoleBasedAuthenticationService;
import com.clinakos.service.IUserService;
import com.clinakos.service.serviceImpl.PasswordEncoderGeneratorServiceImpl;
import com.clinakos.viewController.webservicMangedBean.NcFormulary1WSBean;
import com.clinakos.viewController.webservicMangedBean.NcUpdate1WSBean;
import com.csvreader.CsvReader;
import com.google.gson.Gson;

import https.secure_newcropaccounts_com.v7.webservices.DownloadDetail;

public class DashBoardManageBean implements Serializable {
	//private static final long serialVersionUID = -78303832320382L;

	public static final Logger logger = Logger
			.getLogger("DashBoardManageBean.class");
	private List<UserLoginDetail> userLoginDetailList;
	private UserLoginDetail userDetailBeforeSearch = new UserLoginDetail();
	IDashBoardService dashBoardService;
	IRoleBasedAuthenticationService roleAuthService;
	private DataTable patientSearchTableObj;
	private DataTable patientSearchTableObjForVirtualClinic;
	public UserLoginDetail userLoginDetail = new UserLoginDetail(); 
	public NetworkChart networkChart = new NetworkChart();
	private boolean showPatientMenu;
	List<String> insuranceNameList = new ArrayList<String>(); // Insurance Detail data
	//List<UserLoginDetail>nameDetailsListForAdmin =new ArrayList<UserLoginDetail>();
	List<LoginSecurity>loginDetailsForAdminList = new ArrayList<LoginSecurity>();
	private UserInsuranceDetails userInsuranceDetails = new UserInsuranceDetails();
	private PatientVital patientVital = null;
	private List<ViewFunctionalDetails> viewFunctionDetailList = new ArrayList<ViewFunctionalDetails>();
	private List<LOVType> lovTypeList = new ArrayList<LOVType>();
	private List<String> kidneyFunctionDetailList = new ArrayList<String>();
	private List<String> liverFunctionList = new ArrayList<String>();
	

	//private List<UserLoginDetail> userListForadmin = new ArrayList<UserLoginDetail>();
	// private static final String KIDNEYFUNCTION="Kidney";
	// private static final String LIVERFUNCTION="Liver";
	private PasswordEncoderGeneratorServiceImpl passwordEncoderGeneratorServiceImpl= new PasswordEncoderGeneratorServiceImpl();
	private PatientProviderClinic patientProviderClinic = new PatientProviderClinic();

	List<PatientDiagnosesDetails> diagnosisList = new ArrayList<PatientDiagnosesDetails>();
	List<String> patientProviderClinicList = new ArrayList<String>();
	List<PatientDiagnosesDetails> patientDiagnosesList;
	
	//private ProviderLocation providerLocation=new ProviderLocation();

	int tabIndex; // Variable for TabIndex for Patient Tab selection
	int accPanelIndex; // Variable for accordion Panel Active Index
	private boolean showPatientSubmenu = true;
	private DataTable patientSearchTableObjForPatientMenu;

	private int indexVar = 1;
	public int indexValue = 1;
	private int subIndexVar=1;
	private int subPIndexVar=1;
	
	private boolean emailCheck = false;
	private String insuranceCompanyForFormulary="";
	private List<InsuranceCompanies> insuranceCompanieList;
	private IUserService userService;
	private DataTable insuranceCompanyDataTable;
	private InsuranceCompanies insuranceCompanies;
	private String medNameForFormularySearch="";
	private List<WSDrug> drugListForFormularySearch;
	private DataTable drugDoseCombinationDataTable;
	private Map<String, List<FormularyDetail>> formularyAlternativeDrugMap;
	
	@Autowired
	NcFormulary1WSBean formulary1WsBean;
	
	private List<FormularyDetail> formularyDetails;
	private boolean showFormularyDetailDataTable;
	private List<FormularyDetail> alternativeDrugInfoList;
	private boolean showAlternativeDrugDataTable; 
	private Double calculateCrCl;
	private boolean kidneyResultValue = false;
	
	@Autowired 
	NcUpdate1WSBean update1WsBean;
	
	List<DownloadDetail> downloadDetailList;
	private List<EmpProviderLocation> empProviderLocationList;
	
//--------------added by vinod for allignment of kidney/liver function
		private List<String> kidneyLabNameList = new ArrayList<String>(); // Lab Name of Kidney Function Lab 
		private List<Double> kidneyLabResultList =  new ArrayList<Double>(); // Lab Result of kideney Function Lab 
		private List<String> kidneyLabUnitList = new ArrayList<String>(); // Lab Unit of Kidney Function lab 
		
		private List<String> liverLabNameList = new ArrayList<String>(); // Lab Name of Liver Function 
		private List<Double> liverLabResultList = new ArrayList<Double>(); // Lab result Value of liver Function 
		private List<String> liverLabUnitList = new  ArrayList<String>(); // Lab Result unit of liver Function 
		
		private DataTable bindUserDataTable;
		public UserLoginDetail selectedUserDetail;
		public AnticoagProviderLocation selectedUserDetailFromAnticoagChart;
		
		private List<DiagnosesChart>  diagnosesChartList= null;
	    private List<DiagnosesChart> diagnosesChartBarList =null;
	    private CartesianChartModel diagnosisChartObj;
	    private List<FormularyChart> formularyList=null;
	    private List<UserLoginDetail> patientListforPopup=null;
	    private List<NetworkChart> networkChartList=null;
	    private List<NetworkChart> networkChartListFull=null;
	    
	    
	    
		  // added by vinod  for heatmap  
		    private List<PatientCountForDrugGene> patientDrugGeneList =null;
		    List<PatientCountForDrugGene> patientDrugGeneChartList = null;
		    List<PatientCountForDrugGene> patientDrugGeneFullChartList = null;
		    private List<PatientCountForDrugGene>uniqueMedNamesForGene;
		    private List<PatientCountForDrugGene>uniqueGeneNamesForGene;	
		    private List<PatientCountForDrugGene>uniqueMedNamesForGeneFull;
		    private List<PatientCountForDrugGene>uniqueGeneNamesForGeneFull;
		    private  List<SpecialityDrugCategory>  specialityDrugList = null;
		    private List<DrugCategoryDetail> drugCategoryDetailList = null;
		    
		    private List<UserLoginDetail> retrivePatientByAnticoagCategoryList=new ArrayList<UserLoginDetail>();
		    private List<UserLoginDetail> listforOncologyPatient=new ArrayList<UserLoginDetail>();
		    private List<UserLoginDetail> listforDrugGeneInteractionPatient=new ArrayList<UserLoginDetail>(); // 
		    
		    public String levelVal;
		    
		  //added by vinod for addpateintData 
		    public EmployerDetails employerDetails = new EmployerDetails();
		    List<PatientAllergy>  newPatientAllergyList = new  ArrayList<PatientAllergy>();
		    List<Integer> maxUserIdFromUserDetails;
		   public PatientAllergy pateintAllergyData = new PatientAllergy();
		   public boolean checkForEmailExist;
		   List<PatientDiagnosesDetails> newPateintDiagosesList = new ArrayList<PatientDiagnosesDetails>();
		   public ClinicMaster clinicMasterDetail = new ClinicMaster();
		   List<ClinicMaster>  clinicMasterDetailList;
		   String userInputForAddClinic;
		   private DataTable addClinicMasterDataNewPatient; 
		   List<ClinicProvider> clinicProviderDetailList;
		   public PatientProviderClinic patientProviderClinicDetail = new PatientProviderClinic();;
		   List<PatientProviderClinic> patientProviderClinicListNewPatient = new ArrayList<PatientProviderClinic>();
		   public UserLoginDetail userLoginDetailsNewPatient = new UserLoginDetail();
		   public PatientVital patientVitalNewPatient = new PatientVital();
		   String userInputForAddInsurance;
		   List<InsuranceCompanies> masterInsuranceDetailList;
		   public DataTable masterInsuranceTableBinding;
		   List<UserInsuranceDetails> patientInsuranceDetailList = new ArrayList<UserInsuranceDetails>();
		   public UserInsuranceDetails pateintInsurance = new UserInsuranceDetails();
		   public PatientProvider patientProviderDetail = new PatientProvider();
		   public int selectInsuranceLevel;
		   public DataTable newPatientInsuranceBinding = new DataTable();
		   public DataTable clinicRowDataBinding = new DataTable();
		   
		   //for profile Page to add Clinic and Insurance
		   
		 public DataTable  addClinicForPatientBinding = new DataTable();
		 List<PatientProviderClinic> patientClinicList = new ArrayList<PatientProviderClinic>();
		// public DataTable editDeleteClinicRowDataBinding = new DataTable();
		 List<PatientProviderClinic> patientClinicDataListprofile;
		 
		 //for edit patient data by vinod
		 public EmployerDetails patientEmployerDetails = new EmployerDetails();
		 public DataTable addInsuranceEditProfile = new DataTable();
		 List<UserInsuranceDetails> patientInsuranceEditProfileList = new ArrayList<UserInsuranceDetails>();
		 public List<UserInsuranceDetails> pateintInsuranceEditProfileList;
		 public DataTable editProfileClinicBinding = new DataTable();
		 public DataTable bindingRowDataForEditPatient = new DataTable();
		 List<PatientProviderClinic> copyOfOriginalPatientClinicDataListprofile;
		 List<UserInsuranceDetails>  copyOfOriginalPateintInsuranceEditProfileList;
		 List<UserInsuranceDetails> temporaryDeleteInsuranceListProfile = new ArrayList<UserInsuranceDetails>();
		 List<PatientProviderClinic> temporaryDeleteClinicListProfile = new ArrayList<PatientProviderClinic>();
		 List<PatientDiagnosesDetails> editPateintProfileDiagosesList = new ArrayList<PatientDiagnosesDetails>();
		 private List<PatientDiagnosesDetails> editPatientProfileICDDiagnosisList;
		 List<PatientDiagnosesDetails>  temporaryAddDiagnosesEditProfileList = new ArrayList<PatientDiagnosesDetails>();
		 List<PatientDiagnosesDetails>  deleteDiagnosesEditProfileList = new ArrayList<PatientDiagnosesDetails>();
		 PatientDiagnosesDetails patientDiagnosesDetails=new PatientDiagnosesDetails();
		 public DataTable deleteDiagnosesEditProfile = new DataTable();
		 List<PatientAllergy> allergyListForEditPatient = new  ArrayList<PatientAllergy>();
		 List<PatientAllergy> databaseAllergyListForEditPage;
		 private PatientAllergy patientAllergy =new PatientAllergy();
		 public DataTable bindingAllergyDataForEditProfile= new DataTable();
		 List<PatientAllergy> temporaryAddAllergyForProfile = new ArrayList<PatientAllergy>();
		 List<PatientAllergy> deleteAllergyForProfilePageList = new ArrayList<PatientAllergy>();
		 
		 private Date commonTodayDateForVallidationEditProfile=new DateUtil().getTodayDate();
		// List<PatientAllergy> editAllergyTemporaryList = new ArrayList<PatientAllergy>();
		
		 private List<UserLoginDetail> numberofpatientforlithiumLow=new ArrayList<UserLoginDetail>();
		 private List<UserLoginDetail> numberofpatientforlithiumMedium=new ArrayList<UserLoginDetail>();
		 private List<UserLoginDetail> numberofpatientforlithiumHigh=new ArrayList<UserLoginDetail>();
		 List<Integer> ClinicAndProviderIdList;
		 public PatientGuarantor patientGuarantor = new PatientGuarantor();
		 public PatientGuarantor patientGuarantorNewPatient = new PatientGuarantor();
		 public UserInsuranceDetails userInsuranceDetail = new UserInsuranceDetails();
		 public UserInsuranceDetails userInsuranceDetailAdd = new UserInsuranceDetails();
		 public UserInsuranceDetails userInsuranceDetailEdit = new UserInsuranceDetails();
		 public List<UserInsuranceDetails> insuranceListForUpdate = new ArrayList<UserInsuranceDetails>();
		 public DoctorDetail doctorDetail = new DoctorDetail();
		 public ProviderDetail providerDetail = new ProviderDetail();
		 private List<OrganisationType> organisationTypeList=new ArrayList<OrganisationType>();
		 private List<RoleSecurity> roleSecurityList=new ArrayList<RoleSecurity>();

		 private List<ProviderDetail> organizationList=new ArrayList<ProviderDetail>();
		 private ProviderDetail providerDetailEditObj = new ProviderDetail();
		 private LoginSecurity loginSecurity = new LoginSecurity();
		 private List<UserLoginDetail> displayuserLoginDetailList=new ArrayList<UserLoginDetail>();
		 private UserLoginDetail userLoginDetailEditObj = new UserLoginDetail();
		 private DoctorDetail doctorDetailEditObj = new DoctorDetail();
		 private LoginSecurity loginSecurityEditObj =  new LoginSecurity();
		 private List<MasterTimeZone> timeZoneList=new ArrayList<MasterTimeZone>();
		 private ProviderLocation providerLocation =  new ProviderLocation();
		 private List<ProviderDetail> companyList=new ArrayList<ProviderDetail>();
		 private List<ClinicMaster> clinicList=new ArrayList<ClinicMaster>();
		 private ClinicProvider clinicProvider =  new ClinicProvider();
		 private ClinicDoctor clinicDoctor = new ClinicDoctor();
		 private List<ProviderLocation>providerLocationList=new ArrayList<ProviderLocation>();
		 private ProviderUser providerUser= new ProviderUser();
		 //Added By Anjani
		 private PatientVital patientVitalForPatientIntake;
		 private PatientVital patientVitalDisplayOnPatientIntake=null; // Patient Vital Object  For Display  
		 private String messageForPateintAddedDialog= new String();
		 private boolean disableValueForAddUser= false;
		 private int selectedRole;
		//added by Nagaraj fro drilldownCompliance on 06/Feb/2015 as per assembla ticket #1079 as age is getting 0 when doctor selects any patient from analytics page 
			private String selectedCompliancePart;
			List<UserLoginDetail> pateintListForComplianceMeterList = new ArrayList<UserLoginDetail>();

			private UploadedFile file;
			private InputStream inputStream;
		
				 

		ForgotPasswordSendingLink forgotPasswordSendingLink=new ForgotPasswordSendingLink();
		private String forgetPassword;		 
		private boolean showUpdatePwdForForgotPassword=false;
		private int resetForgotPasswordRequestedUserId;
		private List<PatientUploadDataErrorMessages> patientUploadedDataErrorMessageList;
		private List<ProviderLocation> providerLocationListForNewUser;
		private boolean disableClinicForAddingAdmin=false;
		private Integer progress=null;
		private String message;
		private List<String> usStateList=null;
		private List<ProviderLocation>editProviderLocationList=new ArrayList<ProviderLocation>();
		private List<String> pbmNameList=null; // Pbm Name List 
		private DataTable editProviderLocationDataTableObj=new DataTable();
		private ProviderLocation editProviderLocationObj;
		private List<DiagnosisUploadDataErrorMessage> diagnosisUploadedDataErrorList;
		private boolean enableDiagnosisUploadButton=false;
		private boolean enableDiagnosisUploadValidationMessage=false;
	public void init() {
		logger.debug("::::::init startin DashBoard ManageBean");
		//findDashBoardDetail();
	 //getLoggedPatientData();

	}
     
	

	public void reset(){
		
		/*to reset values  EditpatientPage
		 * by vinod*/
		//patientVital.setWeightUnits(null);
		pateintInsuranceEditProfileList = null;
		patientClinicDataListprofile = null;
		copyOfOriginalPatientClinicDataListprofile=null;
		copyOfOriginalPateintInsuranceEditProfileList= null;
		temporaryDeleteInsuranceListProfile = new ArrayList<UserInsuranceDetails>();
		temporaryDeleteClinicListProfile= new ArrayList<PatientProviderClinic>();
		editPatientProfileICDDiagnosisList = null;
		editPateintProfileDiagosesList=null;
		temporaryAddDiagnosesEditProfileList= new ArrayList<PatientDiagnosesDetails>();
		allergyListForEditPatient =null;
		setAllergyListForEditPatient(null);
		databaseAllergyListForEditPage =null;
		temporaryAddAllergyForProfile = new ArrayList<PatientAllergy>();
		deleteDiagnosesEditProfileList = new ArrayList<PatientDiagnosesDetails>();
		deleteAllergyForProfilePageList = new ArrayList<PatientAllergy>();
		
	/*to reset values  addpatientPage
	 * by vinod*/
		userLoginDetailsNewPatient = new UserLoginDetail();
		 employerDetails = new EmployerDetails();
		 patientVitalNewPatient = new PatientVital();
		 newPatientAllergyList = new ArrayList<PatientAllergy>();
		 newPateintDiagosesList = new ArrayList<PatientDiagnosesDetails>();
		 patientProviderClinicListNewPatient=new ArrayList<PatientProviderClinic>();
		 patientProviderDetail = new PatientProvider();
		 patientInsuranceDetailList =new ArrayList<UserInsuranceDetails>();
		 setUserInputForAddClinic(null);
			setUserInputForAddInsurance(null);
			masterInsuranceDetailList= new ArrayList<InsuranceCompanies>();
			clinicMasterDetailList= new ArrayList<ClinicMaster>();
			FacesContext context = FacesContext.getCurrentInstance();
			PatientManageBean patientManageBean=(PatientManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientManageBean");
			patientManageBean.cleanDiagnosisForm();
			
			//FacesContext context = FacesContext	.getCurrentInstance();
			PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
			patientMedicineManageBean.resetAllergyList();
	}
	
	public List<UserLoginDetail> getUserLoginDetailList() {
		
		return userLoginDetailList;
	}

	public void setUserLoginDetailList(List<UserLoginDetail> userLoginDetailList) {
		this.userLoginDetailList = userLoginDetailList;
	}

	public UserLoginDetail getUserDetailBeforeSearch() {
		return userDetailBeforeSearch;
	}

	public void setUserDetailBeforeSearch(UserLoginDetail userDetailBeforeSearch) {
		this.userDetailBeforeSearch = userDetailBeforeSearch;
	}

	public DataTable getPatientSearchTableObj() {
		return patientSearchTableObj;
	}

	public void setPatientSearchTableObj(DataTable patientSearchTableObj) {
		this.patientSearchTableObj = patientSearchTableObj;
	}

	public PatientProviderClinic getPatientProviderClinic() {
		return patientProviderClinic;
	}

	public void setPatientProviderClinic(
			PatientProviderClinic patientProviderClinic) {
		this.patientProviderClinic = patientProviderClinic;
	}

	public UserLoginDetail getUserLoginDetail() {
		return userLoginDetail;
	}

	public void setUserLoginDetail(UserLoginDetail userLoginDetail) {
		this.userLoginDetail = userLoginDetail;
	}

	public boolean isShowPatientMenu() {
		return showPatientMenu;
	}

	public void setShowPatientMenu(boolean showPatientMenu) {
		this.showPatientMenu = showPatientMenu;
	}

	public List<String> getInsuranceNameList() {
		return insuranceNameList;
	}

	public void setInsuranceNameList(List<String> insuranceNameList) {
		this.insuranceNameList = insuranceNameList;
	}

	public UserInsuranceDetails getUserInsuranceDetails() {
		return userInsuranceDetails;
	}

	public void setUserInsuranceDetails(
			UserInsuranceDetails userInsuranceDetails) {
		this.userInsuranceDetails = userInsuranceDetails;
	}

	public PatientVital getPatientVital() {
		if(patientVital==null){
			patientVital=new PatientVital();
			patientVital = dashBoardService
					.findParticularPatientVitalDetail(userLoginDetail.getUserId());
		}
		
		return patientVital;
	}

	public void setPatientVital(PatientVital patientVital) {
		this.patientVital = patientVital;
	}

	public List<ViewFunctionalDetails> getViewFunctionDetailList() {
		return viewFunctionDetailList;
	}

	public void setViewFunctionDetailList(
			List<ViewFunctionalDetails> viewFunctionDetailList) {
		this.viewFunctionDetailList = viewFunctionDetailList;
	}

	public List<LOVType> getLovTypeList() {
		return lovTypeList;
	}

	public void setLovTypeList(List<LOVType> lovTypeList) {
		this.lovTypeList = lovTypeList;
	}

public List<String> getKidneyFunctionDetailList() {
		return kidneyFunctionDetailList;
	}

	public void setKidneyFunctionDetailList(
			List<String> kidneyFunctionDetailList) {
		this.kidneyFunctionDetailList = kidneyFunctionDetailList;
	}

public List<String> getLiverFunctionList() {
		return liverFunctionList;
	}

	public void setLiverFunctionList(List<String> liverFunctionList) {
		this.liverFunctionList = liverFunctionList;
	}

	public List<String> getPatientProviderClinicList() {
		return patientProviderClinicList;
	}

	public void setPatientProviderClinicList(
			List<String> patientProviderClinicList) {
		this.patientProviderClinicList = patientProviderClinicList;
	}

	public List<PatientDiagnosesDetails> getDiagnosisList() {
		return diagnosisList;
	}

	public void setDiagnosisList(List<PatientDiagnosesDetails> diagnosisList) {
		this.diagnosisList = diagnosisList;
	}

	public void setDashBoardService(IDashBoardService dashBoardService) {
		this.dashBoardService = dashBoardService;
	}

	public IRoleBasedAuthenticationService getRoleAuthService() {
		return roleAuthService;
	}
	public void setRoleAuthService(IRoleBasedAuthenticationService roleAuthService) {
		this.roleAuthService = roleAuthService;
	}
	
	// Getter for Tab Index Variable
	public int getTabIndex() {
		return tabIndex;
	}

	// Setter for Tab Index Variable
	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	// Getter for Accordion Panel Index Variable
	public int getAccPanelIndex() {
		return accPanelIndex;
	}

	// Setter for Accordion Panel Index Variable
	public void setAccPanelIndex(int accPanelIndex) {
		this.accPanelIndex = accPanelIndex;
	}

	/* for printing current system time and date */
	public Date getDate() {
		return new Date();
	}

	/**
	 * searchPatient according to there user name ,
	 * firstname,lastname,.................
	 * 
	 * @return
	 */
	public String searchPatient() {
		logger.info("searchPatient method start in dashBoard manage bean:::::::"
				+ userDetailBeforeSearch.getLastName()
				+ ":::id::"
				+ userDetailBeforeSearch.getId());
		System.out.println("Hi i mi n search ::::::::");
		
		userLoginDetailList = new ArrayList<UserLoginDetail>();

		if (!(userDetailBeforeSearch == null)||(userDetailBeforeSearch.getFirstName().length() >= 1)
				|| (userDetailBeforeSearch.getLastName().length() >= 1)
				|| (userDetailBeforeSearch.getSocialSecurityNumber().length() >= 1)
				|| !(userDetailBeforeSearch.getDateOfBirth() == null)
				|| (userDetailBeforeSearch.getId() >= 1)
				|| (userDetailBeforeSearch.getPhoneNumber().length() >= 1)) {
			userLoginDetailList = dashBoardService
					.searchPatient(userDetailBeforeSearch,new ContextUtil().getRole());
			// logger.info("::::size of search detail in dasgboard manage bean::::"+userLoginDetailList.size());
			for (UserLoginDetail userDetail : userLoginDetailList) {
				// System.out.println("userDetail security no:::"+userDetail.getSocialSecurityNumber());
				if(!(userDetail.getSocialSecurityNumber().equals("") || userDetail.getSocialSecurityNumber() == null)){
					String ssno = userDetail.getSocialSecurityNumber().substring(
							userDetail.getSocialSecurityNumber().length() - 4);
					userDetail.setMaskSociaSecurityNumber(ssno);
				}
				
			}

		}
		
		if((StringUtils.isNotBlank(userDetailBeforeSearch.getSearchKey()) && userDetailBeforeSearch!=null) )
		{
			userLoginDetailList = dashBoardService.searchPatientNew(userDetailBeforeSearch,new ContextUtil().getRole()); // Search Patient Based On Global Search 
		}
		userDetailBeforeSearch = new UserLoginDetail();
		RequestContext.getCurrentInstance().execute("searchPatient_on_virtual_clinic.show();");
		logger.info("after::::searchPatient method end in dashBoard manage bean:::::::"
				+ userDetailBeforeSearch.getFirstName()
				+ "::id::"
				+ userDetailBeforeSearch.getId()
				+ "size::"
				+ userLoginDetailList.size());
		return null;
	}
	
	/**
	 * doctorProfile.jsf/doctorheader2.xhtml
	 * Search Patient On Home Page 
	 * based on FirstName , LastName , PatientId and PhoneNo
	 *  
	 * @return null
	 */
	public String searchPatientforhomepage() {
		logger.info("searchPatient method start in dashBoard manage bean:::::::"
				+ userDetailBeforeSearch.getLastName()
				+ ":::id::"
				+ userDetailBeforeSearch.getId());
		System.out.println("Hi i mi n search ::::::::");
		
		userLoginDetailList = new ArrayList<UserLoginDetail>();

		if (!(userDetailBeforeSearch == null)||(userDetailBeforeSearch.getFirstName().length() >= 1)
				|| (userDetailBeforeSearch.getLastName().length() >= 1)
				|| (userDetailBeforeSearch.getSocialSecurityNumber().length() >= 1)
				|| !(userDetailBeforeSearch.getDateOfBirth() == null)
				|| (userDetailBeforeSearch.getId() >= 1)
				|| (userDetailBeforeSearch.getPhoneNumber().length() >= 1)) {
			userLoginDetailList = dashBoardService
					.searchPatient(userDetailBeforeSearch,new ContextUtil().getRole()); //Modified By Anjani For Pharma Role Patient Search
			// logger.info("::::size of search detail in dasgboard manage bean::::"+userLoginDetailList.size());
			for (UserLoginDetail userDetail : userLoginDetailList) {
				// System.out.println("userDetail security no:::"+userDetail.getSocialSecurityNumber());
				if(!(userDetail.getSocialSecurityNumber().equals("") || userDetail.getSocialSecurityNumber() == null)){
					String ssno = userDetail.getSocialSecurityNumber().substring(
							userDetail.getSocialSecurityNumber().length() - 4);
					userDetail.setMaskSociaSecurityNumber(ssno);
				}
				
			}

		}
		
		if((StringUtils.isNotBlank(userDetailBeforeSearch.getSearchKey()) && userDetailBeforeSearch!=null) )
		{
			userLoginDetailList = dashBoardService.searchPatientNew(userDetailBeforeSearch,new ContextUtil().getRole()); // Search Patient on Global Search 
		}
		userDetailBeforeSearch = new UserLoginDetail();
		RequestContext.getCurrentInstance().execute("searchPatient.show();");
		logger.info("after::::searchPatient method end in dashBoard manage bean:::::::"
				+ userDetailBeforeSearch.getFirstName()
				+ "::id::"
				+ userDetailBeforeSearch.getId()
				+ "size::"
				+ userLoginDetailList.size());
		return null;
	}
	/**
	 * doctorProfile.jsf/doctorheader2.xhtml/doctor_profile_tab.xhtml
	 * Search Patient based Global Search based 
	 * @return null 
	 */
	public String searchPatientNew() 
	{

		logger.info("searchPatientNew method start in dashBoard manage bean:::::::"	+ userDetailBeforeSearch.getSearchKey());
		userLoginDetailList = new ArrayList<UserLoginDetail>();		

		if((StringUtils.isNotBlank(userDetailBeforeSearch.getSearchKey()) && userDetailBeforeSearch!=null) )
		{
			userLoginDetailList = dashBoardService.searchPatientNew(userDetailBeforeSearch,new ContextUtil().getRole());
		}
		
		userDetailBeforeSearch = new UserLoginDetail();
		return null;
	}

	/*
	 * private void findDashBoardDetail() {
	 * 
	 * }
	 */

	/*
	 * ********************** For binding searchPatient datatable
	 */

	/*public void patientSearchData(ActionEvent ae) {
		logger.info("patientSearchData method in usermanagebean startted::::");

		UserLoginDetail selectedRowId = (UserLoginDetail) getPatientSearchTableObj()
				.getRowData();
		System.out
				.println("::::::patientSearchData:::" + selectedRowId.getId());
		this.userLoginDetail.setId(selectedRowId.getId());

		// ---------------------To enable menu under patient tab
		setShowPatientSubmenu(false);
		// this.userLoginDetail.setLoginDetailId(selectedRowId.getLoginDetailId());

		
		  set patient id and patient name in session for further use by gopal
		 

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);

		session.setAttribute("patientId", userLoginDetail.getId());
		// session.setAttribute("PatientLoginId",
		// userLoginDetail.getLoginDetailId());

		FacesContext context = FacesContext.getCurrentInstance();

		LabManageBean labManageBean = (LabManageBean) context.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null, "labManageBean");
		// dctrBean=new DoctorBean();
		labManageBean.setProcedureResultList(null);
		labManageBean.setProcedureResultHistoryList(null);
		labManageBean.setLoincKeyMasterList(null);
		labManageBean.setProcedureResultHistoryListForChart(null);
		labManageBean.resetValue();
		PatientMedicineManageBean patientMedicineManageBean = (PatientMedicineManageBean) context
				.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null,
						"patientMedicineManageBean");
		// patientMedicineManageBean.setPatientMedicationDataList(null);
		patientMedicineManageBean.reset();
		
		PatientManageBean patientManageBean = (PatientManageBean) context
				.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null,
						"patientManageBean");
		// patientMedicineManageBean.setPatientMedicationDataList(null);
		patientManageBean.resetVAlue();
		setShowPatientMenu(true);
		NcUpdate1WSBean update1ServiceClient = (NcUpdate1WSBean) context.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null, "update1ServiceClient");
		update1ServiceClient.resetValue();
		

		for (UserLoginDetail user : userLoginDetailList) {
			if (user.getId() == selectedRowId.getId()) {
				this.userLoginDetail = user;

				userLoginDetail.setAge(new Date().getYear()
						- userLoginDetail.getDateOfBirth().getYear());

			}
		}

		insuranceNameList = dashBoardService
				.userInsuranceDetails(userLoginDetail.getId());
		patientProviderClinicList = dashBoardService
				.findPatientProviderClinicList(userLoginDetail.getId());

		diagnosisList = dashBoardService
				.findDiagnosisDetailList(userLoginDetail.getId());

		patientVital = dashBoardService
				.findParticularPatientVitalDetail(userLoginDetail.getId());
		patientVital.setWeightInKG(patientVital.getWeight());
		
		

		// find liver function, kidney function value...
		viewFunctionDetailList = dashBoardService
				.findViewFunctionDetailList(userLoginDetail.getId());

		lovTypeList = dashBoardService.findLovTypeListInDetail();

		kidneyFunctionDetailList =  findFunctionDetailList(KIDNEYFUNCTION,
				lovTypeList, viewFunctionDetailList);
		liverFunctionList = findFunctionDetailList(LIVERFUNCTION, lovTypeList,
				viewFunctionDetailList);
       
	
		findBodyMassIndex(patientVital.getHeight(), patientVital.getWeight());
		tabIndex = 0;
		accPanelIndex = 1;

	}*/

	/*public void patientSearchDataforVirtualClinic(ActionEvent ae) {
		logger.info("patientSearchDataforVirtualClinic method in usermanagebean startted::::");
		// patientSearchTableObjForVirtualClinic
		UserLoginDetail selectedRowId = (UserLoginDetail) getPatientSearchTableObjForVirtualClinic()
				.getRowData();
		System.out.println("::::::patientSearchData:::"+selectedRowId.getId());
		this.userLoginDetail.setId(selectedRowId.getId());

		// ---------------------To enable menu under patient tab
		setShowPatientSubmenu(false);

		// this.userLoginDetail.setLoginDetailId(selectedRowId.getLoginDetailId());

		
		 * set patient id and patient name in session for further use by gopal
		 

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);

		session.setAttribute("patientId", userLoginDetail.getId());
		// session.setAttribute("PatientLoginId",
		// userLoginDetail.getLoginDetailId());

		FacesContext context = FacesContext.getCurrentInstance();

		LabManageBean labManageBean = (LabManageBean) context.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null, "labManageBean");
		// dctrBean=new DoctorBean();
		labManageBean.setProcedureResultList(null);
		labManageBean.setProcedureResultHistoryList(null);
		labManageBean.setLoincKeyMasterList(null);
		labManageBean.setProcedureResultHistoryListForChart(null);
		labManageBean.resetValue();
		PatientMedicineManageBean patientMedicineManageBean = (PatientMedicineManageBean) context
				.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null,
						"patientMedicineManageBean");
		// patientMedicineManageBean.setPatientMedicationDataList(null);
		patientMedicineManageBean.reset();
		setShowPatientMenu(true);

		for (UserLoginDetail user : userLoginDetailList) {
			if (user.getId() == selectedRowId.getId()) {
				this.userLoginDetail = user;

				userLoginDetail.setAge(new Date().getYear()
						- userLoginDetail.getDateOfBirth().getYear());

			}
		}

		insuranceNameList = dashBoardService
				.userInsuranceDetails(userLoginDetail.getId());
		patientProviderClinicList = dashBoardService
				.findPatientProviderClinicList(userLoginDetail.getId());

		diagnosisList = dashBoardService
				.findDiagnosisDetailList(userLoginDetail.getId());

		patientVital = dashBoardService
				.findParticularPatientVitalDetail(userLoginDetail.getId());
		
		//patient login 
		patientVital = dashBoardService
		.findParticularPatientVitalDetail(userLoginDetail.getUserId());
		
		System.out.println("patientVital::::in patient_login:::"+patientVital.getHeight());

		// find liver function, kidney function value...
		viewFunctionDetailList = dashBoardService
				.findViewFunctionDetailList(userLoginDetail.getId());

		lovTypeList = dashBoardService.findLovTypeListInDetail();

		kidneyFunctionDetailList =  findFunctionDetailList(KIDNEYFUNCTION,
				lovTypeList, viewFunctionDetailList);
		liverFunctionList = findFunctionDetailList(LIVERFUNCTION, lovTypeList,
				viewFunctionDetailList);
		
		findBodyMassIndex(patientVital.getHeight(), patientVital.getWeight());

		tabIndex = 0;
		accPanelIndex = 1;
	}*/

	/*
	 * **************************Patient search from patient menu
	 */
	public void patientSearchFromPatientMenu(ActionEvent ae) {
		
		logger.info("patientSearchDataforVirtualClinic method in usermanagebean startted::::");
		// patientSearchTableObjForVirtualClinic
		UserLoginDetail selectedRowId = (UserLoginDetail) getPatientSearchTableObjForPatientMenu()
				.getRowData();
		// System.out.println("::::::patientSearchData:::"+selectedRowId.getId());
		this.userLoginDetail.setId(selectedRowId.getId());

		// ---------------------To enable menu under patient tab
		setShowPatientSubmenu(false);

//----added by vinod for allignment of kidney/liver function		
		//setKidneyLabNameList(null);
		//setKidneyLabResultList(null);
		//setKidneyLabUnitList(null);
		
		//setLiverLabNameList(null);
		//(null);
		//setLiverLabUnitList(null);
		
		 kidneyLabNameList = new ArrayList<String>();
		 kidneyLabResultList =  new ArrayList<Double>();
		 kidneyLabUnitList = new ArrayList<String>();
		
		 liverLabNameList = new ArrayList<String>();
		 liverLabResultList = new ArrayList<Double>();
		 liverLabUnitList = new ArrayList<String>();
		// this.userLoginDetail.setLoginDetailId(selectedRowId.getLoginDetailId());

		/*
		 * set patient id and patient name in session for further use by gopal
		 */

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);

		session.setAttribute("patientId", userLoginDetail.getUserId());
		// session.setAttribute("PatientLoginId",
		// userLoginDetail.getLoginDetailId());

		FacesContext context = FacesContext.getCurrentInstance();

		LabManageBean labManageBean = (LabManageBean) context.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null, "labManageBean");
		// dctrBean=new DoctorBean();
		labManageBean.setProcedureResultListForCurrentLab(null);
		labManageBean.setProcedureResultHistoryList(null);
		labManageBean.setLoincKeyMasterList(null);
		labManageBean.setProcedureResultHistoryListForChart(null);
		labManageBean.resetValue();
		PatientMedicineManageBean patientMedicineManageBean = (PatientMedicineManageBean) context
				.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null,
						"patientMedicineManageBean");
		// patientMedicineManageBean.setPatientMedicationDataList(null);
		patientMedicineManageBean.reset();
		setShowPatientMenu(true);

		for (UserLoginDetail user : userLoginDetailList) {
			System.out.println("inside for each UserLoginDetail id="+user.getId()+":selectedRowId="+selectedRowId.getId()
					+":userId="+user.getUserId()+":selectedRowUserId="+selectedRowId.getUserId());
			if (user.getUserId() == selectedRowId.getUserId()) {
				this.userLoginDetail = user;

				userLoginDetail.setAge(new Date().getYear()
						- userLoginDetail.getDateOfBirth().getYear());

			}
		}

		insuranceNameList = dashBoardService
				.userInsuranceDetails(userLoginDetail.getUserId()); //get Insurance Data For Patient Detail Header Page 
		patientProviderClinicList = dashBoardService
				.findPatientProviderClinicList(userLoginDetail.getUserId());

		diagnosisList = dashBoardService
				.findDiagnosisDetailList(userLoginDetail.getUserId());

		patientVital = dashBoardService
				.findParticularPatientVitalDetail(userLoginDetail.getUserId());
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>" + patientVital.getHeight());

		// find liver function, kidney function value...
		viewFunctionDetailList = dashBoardService
				.findViewFunctionDetailList(userLoginDetail.getUserId());

		lovTypeList = dashBoardService.findLovTypeListInDetail();

		kidneyFunctionDetailList =  findFunctionDetailList(KIDNEYFUNCTION,
				lovTypeList, viewFunctionDetailList);
		liverFunctionList = findFunctionDetailList(LIVERFUNCTION, lovTypeList,
				viewFunctionDetailList);
//----added by vinod for allignment of kidney/liver function
		
		kidneyLabNameList = findFunctionLabName(KIDNEYFUNCTION, lovTypeList, viewFunctionDetailList); //Get All Lab  Name of Kidney function Lab  for PatientDetailHeader.jsf 
		kidneyLabResultList = findFunctionLabResult(kidneyLabNameList, viewFunctionDetailList);//Get All Lab  Result of Kidney function Lab  for PatientDetailHeader.jsf 
		kidneyLabUnitList = findFunctionLabUnit(kidneyLabNameList,  viewFunctionDetailList);//Get All Lab  unit  of Kidney function Lab  for PatientDetailHeader.jsf 
		
		liverLabNameList = findFunctionLabName(LIVERFUNCTION, lovTypeList, viewFunctionDetailList); //Get All Lab  Name of Liver for PatientDetailHeader.jsf 
		liverLabResultList = findFunctionLabResult(liverLabNameList,  viewFunctionDetailList); //Get All Lab  Result Value  of Liver Function  for PatientDetailHeader.jsf 
		liverLabUnitList = findFunctionLabUnit(liverLabNameList,  viewFunctionDetailList);//Get All Lab  Result unit   of Liver Function  for PatientDetailHeader.jsf 
		findBodyMassIndex(patientVital.getHeight(), patientVital.getWeight(),this.getUserLoginDetail().getGender());
		//Added By Anjani for PBM Name 
		pbmNameList =dashBoardService.getPbmNameData(userLoginDetail.getUserId()); // Pbm Name List For Patient Detail Header page 
		Collections.replaceAll(pbmNameList,"SURESCRIPTS LLC","XYZ");	
				

		tabIndex = 0;
		accPanelIndex = 1;
	}

	public List<String> findFunctionDetailList(String funValue,
			List<LOVType> lovTypeListValue,
			List<ViewFunctionalDetails> viewFunctionDetailList) {
		logger.info("functionDetailList start in usermanage bean:::::");
		List<String> functionNameList = new ArrayList<String>();
		for (Iterator iterator = lovTypeListValue.iterator(); iterator
				.hasNext();) {
			LOVType ltype = (LOVType) iterator.next();
			/* ltype.getText().startsWith(A);*/

			if (ltype.getText().equals(funValue)) {

				Set lcode = ltype.getLovCodeDetail();

				for (Iterator iterator2 = lcode.iterator(); iterator2.hasNext();) {
					LOVCode lcode1 = (LOVCode) iterator2.next();

					String functionDetail = lcode1.getLabel();
					   
					for (ViewFunctionalDetails vfdetail : viewFunctionDetailList) {

						if (vfdetail.getName().equals(lcode1.getLabel())) {
                           
							functionDetail = functionDetail + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "
									+ vfdetail.getResult() + "&nbsp;"
									+ vfdetail.getUnit();
							
						}
					}
					functionNameList.add(functionDetail);
					Collections.sort(functionNameList);
				}
			}
		}
        
		return functionNameList;
	}
	

	/*
	 * code for find the BMI of patient use of height and weight developed by
	 * gopal krishna jha from LI
	 */
	public void findBodyMassIndex(String heightInString, double weight, String gender) {
		

		logger.info("findBodyMassIndex method in dashboard manage bean start::"
				+ heightInString +"gender::::"+gender);
	
		double massLB = weight * 2.2046;
		System.out.println("=====weight==========="+weight);
		double massLbRound = Math.round(massLB * 100.0) / 100.0;
		patientVital.setWeight(massLbRound);
		int heightInInch = 0;
		double bmi,bmi1 = 0;
		// String heightInString=Double.toString(height);

		String feet, inch;
		// int x=heightInString.lastIndexOf(".");
		if(heightInString!=null)
			{
			if(heightInString.contains("."))
			{
				if(!heightInString.substring(0, heightInString.indexOf(".")).isEmpty())
				{
			feet = heightInString.substring(0, heightInString.indexOf("."));
				}
				else
				{
					feet="0";
				}
				if(!heightInString.substring(heightInString.indexOf(".") + 1,
				heightInString.length()).isEmpty())
				{
			inch = heightInString.substring(heightInString.indexOf(".") + 1,
				heightInString.length());
				}
				else
				{
					inch="0";
				}
			heightInInch = ((Integer.parseInt(feet) * 12) + (Integer.parseInt(inch)));
			patientVital.setHeight(feet+"."+inch);
			}
			else
			{
				feet = heightInString;
				inch = "0";
				heightInInch = ((Integer.parseInt(feet) * 12) + (Integer.parseInt(inch)));
				patientVital.setHeight(feet+"."+inch);
				
			}
			System.out.println(massLB+">>>>>>>>>>>>>>>>>>>>heightInInch:::"+heightInInch);
			bmi = (massLB * 703) / (heightInInch * heightInInch);
			bmi1 = Math.round(bmi * 100.0) / 100.0;
			}
		System.out.println(":::::::::bmi:::"+bmi1);
		patientVital.setBodyMassIndex(bmi1);
		int bmiForCheck = (int) (bmi1 * 100);
		String message = "";
		double IBW = 0;
		
		double normalBMIMinweightInLB, normalBMIMaxweightInLB, roundnormalBMIMinweightInLB, roundnormalBMIMaxweightInLB;
		
		//2.20462
		
		
		//normalBMIMinweightInLB = (18.50 * heightInInch * heightInInch) / 703;
		//normalBMIMaxweightInLB = (24.90 * heightInInch * heightInInch) / 703;
		
		//if(userLoginDetail.getGender().equalsIgnoreCase(""))
		
		if(heightInString!=null)
		{
					if(gender.equalsIgnoreCase("Female"))
					{
						IBW = 45.5+(heightInInch-60)*2.3;
						System.out.println("IBW::::Female:details:::"+IBW);
						IBW=IBW*2.20462;
						IBW=(double)Math.round(IBW * 100) / 100;
						patientVital.setBmiRange(""+IBW);
					}
					if(gender.equalsIgnoreCase("Male"))
					{
						IBW = 50+(heightInInch-60)*2.3;
						System.out.println("IBW:::Male::details:::"+IBW);
						IBW=IBW*2.20462;
						IBW=(double)Math.round(IBW * 100) / 100;
						patientVital.setBmiRange(""+IBW);
					}
					/*roundnormalBMIMinweightInLB = Math
							.round(normalBMIMinweightInLB * 100.0) / 100.0;
					roundnormalBMIMaxweightInLB = Math
							.round(normalBMIMaxweightInLB * 100.0) / 100.0;
					patientVital.setBmiRange("" + roundnormalBMIMinweightInLB + "-"
							+ roundnormalBMIMaxweightInLB);*/
					System.out.println("bmiForCheck:::::::"+bmiForCheck);
					if (bmiForCheck < 1850) {
						message = UNDERWEIGHT;
					}
					if (bmiForCheck >= 1850 && bmiForCheck < 2490) {
						message = NORMAL_WEIGHT;
					}
			
					if (bmiForCheck >= 2500 && bmiForCheck < 2990) {
						message = OVER_WEIGHT;
					}
			
					/*if (bmiForCheck >= 3000 && bmiForCheck < 3499) {
						message = OBESE;
					}
					if (bmiForCheck >= 3500 && bmiForCheck < 3999) {
						message = VERY_OBESE;
			
					}
					if (bmiForCheck >= 4000) {
						message = MORBIDLY_OBESE;
					}*/
					
					if (bmiForCheck >= 3000) {
						message = OBESE;
					}
		}
					

		patientVital.setBmiMessage(message);
		

	}

	/**
	 * Get Diagnosis detail for Patient detail page when Patient will be Login
	 * 
	 * height weight and bmi values  at patient detail page 
	 * 
	 * @return the patientDiagnosesList
	 */
	public List<PatientDiagnosesDetails> getPatientDiagnosesList() {
		if (patientDiagnosesList == null) {
			patientDiagnosesList = new ArrayList<PatientDiagnosesDetails>();
		}
		
		
		try {
			
			patientVital = dashBoardService.getLoggedPatientVitalData();
			getGenderFromDB();
			findBodyMassIndex(getPatientVital().getHeight(), getPatientVital()
					.getWeight(), genderFromDB);
			patientDiagnosesList = dashBoardService.getPatientDiagnosesDetails();
		
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception in getPatientDiagnosesList "+e);
		}
		
		return patientDiagnosesList;
	
	
	}

	/**
	 * @param patientDiagnosesList
	 *            the patientDiagnosesList to set
	 */
	public void setPatientDiagnosesList(
			List<PatientDiagnosesDetails> patientDiagnosesList) {
		this.patientDiagnosesList = patientDiagnosesList;
	}

	/**
	 * Get Patient Related Data when unique patient will be login
	 *  Author sanket singh
	 */
	/*public void getLoggedPatientData() {
		
     
		
			System.out.println("weight:::::::"+patientVital.getWeight());
			patientVital = dashBoardService.getLoggedPatientVitalData();
			findBodyMassIndex(getPatientVital().getHeight(), getPatientVital()
					.getWeight());
			
		} 
*/
	// Method to open Doctor Home by LI-0011
	public void goHome() throws IOException {
		accPanelIndex = 0;
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.redirect("doctorProfile.jsf");
	}

	// Method to open Overview Index by LI-0011
	public void setOverviewIndex() {
		
		setIndexVar(1);
		setSubIndexVar(1);
	}

	// Method to open Medication Index by LI-0011
	public String setMedicationIndex() {
		tabIndex = 1;
		accPanelIndex = 1;
		setSubIndexVar(3);
		return "go_to_Medication";
	}

	// Method to open Pharmacotherapy Clinic by LI-0011
	public String setPharmacotherapyClinicIndex() {
		tabIndex = 2;
		accPanelIndex = 1;
		return "go_to_PharmacotherapyClinic";
	}

	// Method to open Pharmacogenomic Profile by LI-0011
	public String setPharmacogenomicProfileIndex() {
		tabIndex = 3;
		accPanelIndex = 1;
		setIndexVar(4);
		setIndexValue(4);
		//setSubIndexVar(3);
		System.out.println("from now: inde:"+indexVar);
		return "go_to_PharmacogenomicProfile";
	}

	// Method to open Lab Result by LI-0011
	public void setLabResultIndex() {
		tabIndex = 4;
		accPanelIndex = 1;
		setIndexVar(7);
	}

	// Method to open Diagnoses by LI-0011
	public String setDiagnosesIndex() {
		tabIndex = 5;
		accPanelIndex = 1;
		setIndexVar(1);
		setSubIndexVar(2);
		return "go_to_Diagnoses";
	}
	
	//Method setting sub menu index
	public String setEditPatientIndex(){
		tabIndex = 5;
		accPanelIndex = 1;
		setIndexVar(1);
		setSubIndexVar(3);
		reset();
		if(patientVital.getHeight().contains("."))
		{
			patientVital.setHeightinfeet(patientVital.getHeight().substring(0, patientVital.getHeight().indexOf(".")));
			patientVital.setHeightininches(patientVital.getHeight().substring( patientVital.getHeight().indexOf(".")+1, patientVital.getHeight().length()));
		}
		int patientId = new ContextUtil().getPatientId();
		int providerId = new ContextUtil().getProviderId();
		final boolean isPatientActive = true;
		UserLoginDetail userObj = userLoginDetail;
		userLoginDetail = new UserLoginDetail();
		try{
		userLoginDetail = userService.getPatientDetails(providerId,patientId,isPatientActive);
		
		if(userLoginDetail.getDateOfBirth()!=null){
			int age = calculateAgeInYears(userLoginDetail.getDateOfBirth());
				userLoginDetail.setAge(age);
			}
		}catch(Exception e){
			userLoginDetail = userObj;
			e.printStackTrace();
		}
		
		
		return "go_to_editPatient";
	}
	
	/**
	 * Calculate age in Years based on Date of Birth
	 * @param dateOfBirth
	 * @return
	 */
	private int calculateAgeInYears(Date dateOfBirth) {
			Years years = Years.yearsBetween(new LocalDate(userLoginDetail.getDateOfBirth()), new LocalDate());
			Integer patientAge=years.getYears();
			logger.info("Patient Age: "+patientAge+" Birth date: "+userLoginDetail.getDateOfBirth());
			return patientAge;
	}



	//Method setting sub menu index
	public void setDiagnoseIndex() {
		tabIndex = 5;
		accPanelIndex = 1;
		setIndexVar(1);
		setSubIndexVar(2);
		getPatientVital();
		
	}

	// Method to open Drug Price Optimizer by LI-0011
	public String setDPOptimizerIndex() {
		setSubIndexVar(12);
		setIndexVar(8);
		return "go_to_DPOptimizer";
	}

	// Method to open Care Team by LI-0011
	public String setCareTeamIndex() {
		tabIndex = 7;
		accPanelIndex = 1;
		setSubIndexVar(6);
		setIndexVar(9);
		return "go_to_careteam";
	}
	
	// Method to open Care Team by LI-0011
/*	public String setclinakosInternalIndex() {
		tabIndex = 7;
		accPanelIndex = 1;
		setSubIndexVar(16);
		return "go_to_careteam";
	}*/
	// Method go_to_virtualpillbox by LI-0011
		public String setVPBIndex() {			
			setSubPIndexVar(1);
			return "go_to_virtualpillbox";
		}
		
		// Method go_to_My_Medication by LI-0011
				public String setMyMedicationIndex() {			
					setSubPIndexVar(0);
					return "go_to_My_Medication";
				}
		
	// Method to listen Tab Change and Open Selected Tab Page for Patient
	// section by LI-0011
	public void onTabChange(TabChangeEvent event) throws IOException {
		String url = "";

		if (event.getTab().getId().equalsIgnoreCase("overview")) {
			tabIndex = 0;
			accPanelIndex = 1;
			url = "overview.jsf";
		} else if (event.getTab().getId().equalsIgnoreCase("medication")) {
			tabIndex = 1;
			accPanelIndex = 1;
			url = "medication.jsf";
		} else if (event.getTab().getId()
				.equalsIgnoreCase("pharmacotherapyClinic")) {
			tabIndex = 2;
			accPanelIndex = 1;
			url = "pharmacotherepyClinic.jsf";
		} else if (event.getTab().getId()
				.equalsIgnoreCase("pharmacogen0micProfile")) {
			tabIndex = 3;
			accPanelIndex = 1;
			url = "pharmacogenomicProfile.jsf";
		} else if (event.getTab().getId().equalsIgnoreCase("labResults")) {
			tabIndex = 4;
			accPanelIndex = 1;
			url = "labResults.jsf";
		} else if (event.getTab().getId().equalsIgnoreCase("diagonoses")) {
			tabIndex = 5;
			accPanelIndex = 1;
			url = "diagonoses.jsf";
		} else if (event.getTab().getId().equalsIgnoreCase("dpOptimizer")) {
			tabIndex = 6;
			accPanelIndex = 1;
			url = "dPOptimizer.jsf";
		} else if (event.getTab().getId().equalsIgnoreCase("careTeam")) {
			tabIndex = 7;
			accPanelIndex = 1;
			url = "careTeam.jsf";
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.redirect(url);
	}

	// Method to open Virtual Clinic Home by LI-0011
	public void goVCHome() throws IOException {
		accPanelIndex = 2;
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.redirect("virtualClinicHome.jsf");
	}

	// Method to open Population Health Overview by LI-0011
	public void goPHOVerview() throws IOException {
		accPanelIndex = 3;
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.redirect("populationHealthOverview.jsf");
	}

	public void goPatientHome() throws IOException {
		accPanelIndex = 1;
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.redirect("patientSearch.jsf");
	}

	// Method to listen Tab Change and Open Selected Tab Page in DocMenu Section
	// by LI-0011
	public void menuGroupChange(TabChangeEvent event) throws IOException {
		// System.out.println("Tab is changed for ::"+showPatientMenu);
		/*
		 * if(showPatientMenu==false) {
		 */
		/*
		 * if(event.getTab().getId().equalsIgnoreCase("docHome")) { goHome();
		 * accPanelIndex=0; } else
		 * if(event.getTab().getId().equalsIgnoreCase("docVC")) {
		 * accPanelIndex=1; FacesContext fc = FacesContext.getCurrentInstance();
		 * ExternalContext ec = fc.getExternalContext();
		 * ec.redirect("virtualClinicHome.jsf"); } else
		 * if(event.getTab().getId().equalsIgnoreCase("docPH")) {
		 * accPanelIndex=2; FacesContext fc = FacesContext.getCurrentInstance();
		 * ExternalContext ec = fc.getExternalContext();
		 * ec.redirect("populationHealthOverview.jsf"); }
		 */
		// }
		/*
		 * else {
		 */
		if (event.getTab().getId().equalsIgnoreCase("docHome")) {
			goHome();
			accPanelIndex = 0;
		} else if (event.getTab().getId().equalsIgnoreCase("docPatient")) {
			goPatientHome();
		} else if (event.getTab().getId().equalsIgnoreCase("docVC")) {
			goVCHome();
		} else if (event.getTab().getId().equalsIgnoreCase("docPH")) {
			goPHOVerview();
		}
		// }

	}

	/*
	 * ******for redirecting from Pharmacogenomics profile to Pharmacogenomics
	 * clinic
	 */
	public String redirectPharmacogenomics() {
		tabIndex = 2;
		accPanelIndex = 1;
		return "redirect_to_Pharmacogenomics_clinic";
	}

	public DataTable getPatientSearchTableObjForVirtualClinic() {
		return patientSearchTableObjForVirtualClinic;
	}

	public void setPatientSearchTableObjForVirtualClinic(
			DataTable patientSearchTableObjForVirtualClinic) {
		this.patientSearchTableObjForVirtualClinic = patientSearchTableObjForVirtualClinic;
	}

	public boolean isShowPatientSubmenu() {
		return showPatientSubmenu;
	}

	public void setShowPatientSubmenu(boolean showPatientSubmenu) {
		this.showPatientSubmenu = showPatientSubmenu;
	}

	public DataTable getPatientSearchTableObjForPatientMenu() {
		return patientSearchTableObjForPatientMenu;
	}

	public void setPatientSearchTableObjForPatientMenu(
			DataTable patientSearchTableObjForPatientMenu) {
		this.patientSearchTableObjForPatientMenu = patientSearchTableObjForPatientMenu;
	}

	public int getIndexVar() {
		// System.out.println("value of indexVar:::"+indexVar);
		return indexVar;
	}

	public void setIndexVar(int indexVar) {
		this.indexVar = indexVar;
	}
	
	//Sub index value used to highlight the sub menu by Anand S Jha
	public int getSubIndexVar() {
		return subIndexVar;
	}

	public void setSubIndexVar(int subIndexVar) {
		this.subIndexVar = subIndexVar;
	}

	/*
	 * public void setMenuIndex(int id) { //indexVar=id; setIndexVar(id);
	 * //indexVar=2; System.out.println("new Index is "+indexVar); }
	 */



	public int getSubPIndexVar() {
		return subPIndexVar;
	}

	public void setSubPIndexVar(int subPIndexVar) {
		this.subPIndexVar = subPIndexVar;
	}

	public int getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(int indexValue) {
		this.indexValue = indexValue;
	}

	// Method to set Overview Index for menu
	
	public void setoverviewindex() {
		setIndexVar(1);
		setIndexValue(1);
		setSubIndexVar(1);
		count=0;
	}

	// Method to set Medication Index for menu
	public void setMedIndex() {
		// indexVar=id;
		setIndexVar(2);
		setIndexValue(1);
		//System.out.println("new Index is medication " + indexVar);
	}
	public void setpharmacogenoIndex() {
		// indexVar=id;
		setIndexVar(4);
		setIndexValue(1);
		//System.out.println("new Index is medication " + indexVar);
	}

	public void setpMedIndex() {
		// indexVar=id;
		setIndexVar(2);
		setIndexValue(2);
		//System.out.println("new Index is medication " + indexVar);
	}

	public void setlMedIndex() {
		// indexVar=id;
		setIndexVar(2);
		setIndexValue(3);
		
		//System.out.println("new Index is medication " + indexVar);
	}

	public void setdMedIndex() {
		// indexVar=id;
		setIndexVar(2);
		setIndexValue(4);
		//System.out.println("new Index is medication " + indexVar);
	}

	public void setcMedIndex() {
		// indexVar=id;
		setIndexVar(2);
		setIndexValue(5);
		//System.out.println("new Index is medication " + indexVar);
	}
	
	public void myanlyticsIndex() {
		// indexVar=id;
		setIndexVar(3);
		setSubIndexVar(1);
		count++;
		setLoadvar(count);
		//System.out.println("new Index is medication " + indexVar);
	}
	public void myvirtualclinicIndex() {
		// indexVar=id;
		setIndexVar(3);
		setIndexValue(2);
		setSubIndexVar(2);
		count=0;
		//System.out.println("new Index is medication " + indexVar);
	}
	
	public void pharmacotherpyissueIndex() {
		// indexVar=id;
		setIndexVar(3);
		setIndexValue(3);
		setSubIndexVar(3);
		count=0;
		//System.out.println("new Index is medication " + indexVar);
	}
	public void costoptimization() {
		// indexVar=id;
		setIndexVar(3);
		setIndexValue(4);
		setSubIndexVar(4);
		count=0;
		//System.out.println("new Index is medication " + indexVar);
	}
	public void setgapincareIndex() {
		// indexVar=id;
		setIndexVar(3);
		setIndexValue(5);
		setSubIndexVar(5);
		count=0;
		//System.out.println("new Index is medication " + indexVar);
	}
	public void setallergyIndex() {
		// indexVar=id;
		setIndexVar(3);
		setIndexValue(6);
		setSubIndexVar(6);
		count=0;
		//System.out.println("new Index is medication " + indexVar);
	}
	
	public void labmonitorIndex() {
		// indexVar=id;
		setIndexVar(3);
		setIndexValue(7);
		setSubIndexVar(7);
		count=0;
		//System.out.println("new Index is medication " + indexVar);
	}
	
	public void medicationmanagementIndex() {
		// indexVar=id;
		setIndexVar(3);
		setIndexValue(8);
		setSubIndexVar(8);
		count=0;
		//System.out.println("new Index is medication " + indexVar);
	}
	public void acoo33Index() {
		// indexVar=id;
		setIndexVar(3);
		setIndexValue(9);
		setSubIndexVar(9);
		count=0;
		//System.out.println("new Index is medication " + indexVar);
	}
	
	public void hdsi2013() {
		// indexVar=id;
		setIndexVar(3);
		setIndexValue(10);
		setSubIndexVar(10);
		count=0;
		//System.out.println("new Index is medication " + indexVar);
	}
	// Method to set Personalized Med Review Index for clinic menu
	public void setPMIndex() {
		setIndexVar(3);
		setIndexValue(1);
		setSubIndexVar(3);
		count=0;
		//System.out.println("new Index is DPO " + indexVar);
	}

	public void setAPMIndex() {
		setIndexVar(3);
		setIndexValue(3);
		setSubIndexVar(4);
		count=0;
		//System.out.println("new Index is DPO " + indexVar);
	}
	
	public void setgenmedplanIndex() {
		setIndexVar(13);
		//setIndexValue(4);
		//setSubIndexVar(5);
		//count=0;
		//System.out.println("new Index is DPO " + indexVar);
	}
	
	public void setpsyphoramIndex() {
		setIndexVar(3);
		setIndexValue(5);
		setSubIndexVar(6);
		count=0;
		//System.out.println("new Index is DPO " + indexVar);
	}
	
	public void setspecialtydrugIndex() {
		setIndexVar(3);
		setIndexValue(5);
		setSubIndexVar(7);
		count=0;
		//System.out.println("new Index is DPO " + indexVar);
	}

	public void setnVSIndex() {
		setIndexVar(4);
		setIndexValue(1);
		setSubIndexVar(11);
		//System.out.println("new Index is Visit Summary " + indexVar);
	}
	
	public void masterdataIndex() {
		setIndexVar(4);
		setIndexValue(2);
		setSubIndexVar(12);
		//System.out.println("new Index is Visit Summary " + indexVar);
	}

	public void maintanceIndex() {
		setIndexVar(4);
		setIndexValue(3);
		setSubIndexVar(13);
		//System.out.println("new Index is Visit Summary " + indexVar);
	}
	
	public void addPateintIndex() {
		setIndexVar(4);
		setIndexValue(4);
		setSubIndexVar(14);
	}
	
	public void addBatchDataPullProcessIndex() {
		setIndexVar(4);
		setIndexValue(5);
		setSubIndexVar(19);
	}
	
	public void addbatchPatientMedicationListsReport() {
		setIndexVar(4);
		setIndexValue(6);
		setSubIndexVar(20);
	}
	public void uploadDiagnosis() {
		setIndexVar(4);
		setIndexValue(7);
		setSubIndexVar(20);
	}
	public String visitsummryindex()
	{
		
		setIndexVar(10);
		return "go_to_visitSummary_page";
	}
	public void setVSIndex() {
		setIndexVar(4);
		setIndexValue(3);
		setSubIndexVar(10);
		//System.out.println("new Index is Visit Summary " + indexVar);
	}

	// Method to set DPO Index for menu
	public void setDPOIndex() {
		setIndexVar(5);
		setIndexValue(1);
		//System.out.println("new Index is DPO " + indexVar);
	}

	// Set E-prescribed menu
	public String setePIndex() {
		setIndexVar(6);
		setIndexValue(6);
		setSubIndexVar(9);
		return "go_to_anticoaginr";
		//System.out.println("new Index is DPO " + indexVar);
	}
	
	
	public void setbPIndex() {
		setIndexVar(6);
		setIndexValue(2);
		setSubIndexVar(10);
		//System.out.println("new Index is DPO " + indexVar);
	}
	
	public void setglucoseIndex() {
		setIndexVar(6);
		setIndexValue(3);
		setSubIndexVar(11);
		//System.out.println("new Index is DPO " + indexVar);
	}
	
	public void setinrIndex() {
		setIndexVar(6);
		setIndexValue(4);
		setSubIndexVar(12);
		//System.out.println("new Index is DPO " + indexVar);
	}
	
	public void pulseIndex() {
		setIndexVar(6);
		setIndexValue(5);
		setSubIndexVar(13);
		//System.out.println("new Index is DPO " + indexVar);
	}
	
	public void temperatureIndex() {
		setIndexVar(6);
		setIndexValue(6);
		setSubIndexVar(14);
		//System.out.println("new Index is DPO " + indexVar);
	}
	
	public void weightIndex() {
		setIndexVar(6);
		setIndexValue(7);
		setSubIndexVar(15);
		//System.out.println("new Index is DPO " + indexVar);
	}
     //Added By Anjani For Sleep Page index 
	public void sleepIndex() {
		setIndexVar(6);
		setIndexValue(8);
		setSubIndexVar(16);
		//System.out.println("new Index is DPO " + indexVar);
	}
	//Added By Anjani For Tobacco Page index 
	public void tobaccoIndex() {
		setIndexVar(6);
		setIndexValue(9);
		setSubIndexVar(17);
		//System.out.println("new Index is DPO " + indexVar);
	}

	// Set My careteam menu
	public void setCTIndex() {
		setIndexVar(7);
		setIndexValue(1);
		System.out.println("new Index is Care Team " + indexVar);
	}

	// Home menu for Doctor login
	public void setHIndex() {
		setIndexVar(1);
		setIndexValue(1);
		count=0;
		//System.out.println("new Index is HOME " + indexVar);
	}

	public void setpVCHIndex() {
		setIndexVar(2);
		setIndexValue(2);
		//System.out.println("new Index is setpVCHIndex " + indexVar);
	}
int loadvar=0;
int count=0;
	public int getLoadvar() {
	return loadvar;
}

public void setLoadvar(int loadvar) {
	this.loadvar = loadvar;
}

	public void setPHOMedIndex() {
		setIndexVar(3);
		setSubIndexVar(1);
		count++;
		setLoadvar(count);
		//System.out.println("new Index is setpVCHIndex " + indexVar);
	}
	public String setadminuiIndex() {
		setIndexVar(4);
		setIndexValue(4);
		setSubIndexVar(11);
		count=0;
		return "go_to_admin_ui";
		//System.out.println("new Index is setpVCHIndex " + indexVar);
	}
	public void setadminIndex() {
		setIndexVar(4);
		setIndexValue(4);
		setSubIndexVar(11);
		System.out.println("new Index is admin panel " + indexVar+"::"+subIndexVar);
	}
	public void setmmIndex() {
		setIndexVar(2);
		setIndexValue(1);
		//System.out.println("new Index is setpVCHIndex " + indexVar);
	}
  
	public void setmvpIndex() {
		setIndexVar(2);
		setIndexValue(2);
		//System.out.println("new Index is setpVCHIndex " + indexVar);
	}
	
public void setVisitSummaryIndex()
{
	setIndexVar(10);
	//setIndexValue(1);
	setSubIndexVar(20);
}

public void setVHistory()
{
	setIndexVar(10);
	//setIndexValue(2);
	setSubIndexVar(21);
}

public void setClinakosinternalIndex()
{
	setIndexVar(11);
	setIndexValue(1);
	setSubIndexVar(16);
}

public void setnewchartIndex()
{
	setIndexVar(11);
	setIndexValue(2);
	setSubIndexVar(17);
}



public void setvisithistoryIndex()
{
	setIndexVar(11);
	setIndexValue(3);
	setSubIndexVar(18);
}

public void setwarfarinmaintanceIndex()
{
	setIndexVar(11);
	setIndexValue(4);
	setSubIndexVar(19);
}

	List<LoginSecurity> loginSecuritylist;

	/* >>>>>>>>>>>>>>>>.....recover password>>>>>>>>>>>>>>>>>>> */

	public List<LoginSecurity> getLoginSecuritylist() {

		return loginSecuritylist;
	}

	/*
	 * sending password for clicking forgot password
	 * @author:Gopal Krishna jha
	 * **********modified by saurabh
	 *******for sending link/url to user
	 */
	
	public String passwordRecovery() throws MessagingException, UnsupportedEncodingException
	{
		//System.out.println("::::::::::::"+userLoginDetail.getEmail());
		String mailId="";
		String msg="";
		if(userLoginDetail.getEmail().isEmpty())
		{
			msg="Please insert Email Id";
			FacesContext.getCurrentInstance().addMessage("messageUpdateForNewAddMeds", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,"")); 
			
		}
		else
		{
			mailId=dashBoardService.findUserPassword(userLoginDetail.getEmail());
			
			if(!(mailId.equals("")))
			{
				logger.info("#########value of mailId in dashboardManageBEAN#######="+mailId);
				String[] parts = mailId.split("-");
			    String userId = parts[0]; // 004
			    String email= parts[1]; // 034556
			    logger.info("#########value of mailId parts in dashboardManageBEAN#######1="+userId+"###2="+email);
				forgotPasswordSendingLink=new ForgotPasswordSendingLink();
				HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				String url = req.getRequestURL().toString();
				url=url.replace("recoverPassword", "reset-forgot-password");
				String sendinGenerateLink=new NotificationUtil().generateRandomAlphaNumeric(12);
				logger.info("#########value of sendinGenerateLink="+sendinGenerateLink+"  :::"+UriUtils.encodeUri(sendinGenerateLink, "UTF-8")+"  ::::"+
						URLEncoder.encode(sendinGenerateLink,"UTF-8"));
				forgotPasswordSendingLink.setActive(true);
				forgotPasswordSendingLink.setCreatedDate(new Date());
				forgotPasswordSendingLink.setUserId(Integer.parseInt(userId));
				forgotPasswordSendingLink.setSendingLink(sendinGenerateLink);
				dashBoardService.saveforgotPasswordSendingLink(forgotPasswordSendingLink);
				String mailSubject="Clinakos account recovery";
				String mailMsg="<table bgcolor=WhiteSmoke border=0 width=100%><tr><td><br>Dear User &nbsp;</br>";
					   mailMsg="Someone requested an account recovery on <b>Clinakos</b> for <b>"+
							    userLoginDetail.getEmail() 
							   +"</b>. If you did not request this, just ignore this email. We'll keep your account safe.<br></br>";
					   mailMsg+="<br></br>If you would like to reset your password, <a href=\" "+url+"?"+sendinGenerateLink+"\">just follow this link</a>, and we will automatically update your account.<br></br>";
					   mailMsg+="<br></br>*******It is a system generated mail.Please do not reply.********</b></td></tr></table>";
				boolean result= new NotificationUtil().sendMail(email.trim(),mailSubject,mailMsg);
				
				if(result)
					msg="We've sent you a link on your mail to change your password";
				else
					msg="Your Email Id is not available";
				FacesContext.getCurrentInstance().addMessage("messageUpdateForNewAddMeds", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,"")); 
			}
			else
			{
				msg="Your Email Id is not available ";
				FacesContext.getCurrentInstance().addMessage("messageUpdateForNewAddMeds", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,"")); 
			}
		}
		
		
		userLoginDetail=new UserLoginDetail();
		forgotPasswordSendingLink=new ForgotPasswordSendingLink();
		mailId="";
		return null;
	}

	public void setLoginSecuritylist(List<LoginSecurity> loginSecuritylist) {

		this.loginSecuritylist = loginSecuritylist;
	}

	public boolean isEmailCheck() {
		return emailCheck;
	}

	public void setEmailCheck(boolean emailCheck) {
		this.emailCheck = emailCheck;
	}

	public String backToLoginButton() {
		logger.info(":::::backtologin page:::::::::::");

		return "go_to_Login";
	}
	
	public String goToForgotPwd()
	{
		userLoginDetail=new UserLoginDetail();
		return "go_to_forgetPassword";
	}

	/**
	 * @return the insuranceCompanyForFormulary
	 */
	public String getInsuranceCompanyForFormulary() {
		return insuranceCompanyForFormulary;
	}

	/**
	 * @param insuranceCompanyForFormulary the insuranceCompanyForFormulary to set
	 */
	public void setInsuranceCompanyForFormulary(String insuranceCompanyForFormulary) {
		this.insuranceCompanyForFormulary = insuranceCompanyForFormulary;
	}

	/**
	 * @return the insuranceCompanieList
	 */
	public List<InsuranceCompanies> getInsuranceCompanieList() {
		if(insuranceCompanieList==null){
			insuranceCompanieList=new ArrayList<InsuranceCompanies>();
		}
		return insuranceCompanieList;
	}

	/**
	 * @param insuranceCompanieList the insuranceCompanieList to set
	 */
	public void setInsuranceCompanieList(
			List<InsuranceCompanies> insuranceCompanieList) {
		this.insuranceCompanieList = insuranceCompanieList;
	}
	

	/**
	 * Get Insurance Company Detail On Virtual Clinic 
	 * for formulary Search 
	 * 
	 */
	public String getInsuranceCompanyDetail(){
		insuranceCompanieList=new ArrayList<InsuranceCompanies>();
		if(!(insuranceCompanyForFormulary.equals(""))){
			insuranceCompanieList=userService.getInsuranceDetail(insuranceCompanyForFormulary);
		}
		RequestContext.getCurrentInstance().execute("insuranceDetail.show();");
		
		return null;
		
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	

	public String patientMore()
	{
		logger.info(":::::back to patient diagnosis page:::::::::::");
		
		return "go_to_patientDiagonoses";
	}	  
	

	/**
	 * Get Selected Insurance Company For Formulary Coverage Detail 
	 */
	public void getSelectedInsuranceCompany(){
		InsuranceCompanies selectedInsuranceCompany=(InsuranceCompanies) getInsuranceCompanyDataTable().getRowData();
		getInsuranceCompanies().setId(selectedInsuranceCompany.getId());
		setInsuranceCompanyForFormulary(selectedInsuranceCompany.getCompanyName());
		
	}


	/**
	 * @return the insuranceCompanyDataTable
	 */
	public DataTable getInsuranceCompanyDataTable() {
		if(insuranceCompanyDataTable==null){
			insuranceCompanyDataTable=new DataTable();
		}
		return insuranceCompanyDataTable;
	}

	/**
	 * @param insuranceCompanyDataTable the insuranceCompanyDataTable to set
	 */
	public void setInsuranceCompanyDataTable(DataTable insuranceCompanyDataTable) {
		this.insuranceCompanyDataTable = insuranceCompanyDataTable;
	}

	/**
	 * @return the insuranceCompanies
	 */
	public InsuranceCompanies getInsuranceCompanies() {
		if(insuranceCompanies==null){
			insuranceCompanies=new InsuranceCompanies();
		}
		return insuranceCompanies;
	}

	/**
	 * @param insuranceCompanies the insuranceCompanies to set
	 */
	public void setInsuranceCompanies(InsuranceCompanies insuranceCompanies) {
		this.insuranceCompanies = insuranceCompanies;
	}

	/**
	 * @return the medNameForFormularySearch
	 */
	public String getMedNameForFormularySearch() {
		return medNameForFormularySearch;
	}

	/**
	 * @param medNameForFormularySearch the medNameForFormularySearch to set
	 */
	public void setMedNameForFormularySearch(String medNameForFormularySearch) {
		this.medNameForFormularySearch = medNameForFormularySearch;
	}
	
	/**
	 * Get Drug Dose Combination for formulary search 
	 * 
	 */
	public String getDrugDoseCombinationForFormulary(){
		drugListForFormularySearch=new ArrayList<WSDrug>();
		if(!(medNameForFormularySearch.equals(""))){
			drugListForFormularySearch=userService.findAllDrugDoseComboForFormularyMedicine(medNameForFormularySearch);
		}
		RequestContext.getCurrentInstance().execute("drugDoseCombo.show();");
		return null;
		
	}

	/**
	 * @return the drugListForFormularySearch
	 */
	public List<WSDrug> getDrugListForFormularySearch() {
		if(drugListForFormularySearch==null){
			drugListForFormularySearch=new ArrayList<WSDrug>();
		}
		return drugListForFormularySearch;
	}

	/**
	 * @param drugListForFormularySearch the drugListForFormularySearch to set
	 */
	public void setDrugListForFormularySearch(
			List<WSDrug> drugListForFormularySearch) {
		this.drugListForFormularySearch = drugListForFormularySearch;
	}

	/**
	 * @return the drugDoseCombinationDataTable
	 */
	public DataTable getDrugDoseCombinationDataTable() {
		if(drugDoseCombinationDataTable==null){
			drugDoseCombinationDataTable=new DataTable();
		}
		
		return drugDoseCombinationDataTable;
	}

	/**
	 * @param drugDoseCombinationDataTable the drugDoseCombinationDataTable to set
	 */
	public void setDrugDoseCombinationDataTable(
			DataTable drugDoseCombinationDataTable) {
		this.drugDoseCombinationDataTable = drugDoseCombinationDataTable;
	}
	
	/**
	 * Get Formulary Coverage One the basis of Medicine 
	 * And Insurance Company 
	 * @throws Exception 
	 */
	public void getMedicineFormularySearch() throws Exception{
		formularyAlternativeDrugMap=new HashMap<String, List<FormularyDetail>>();
		setShowAlternativeDrugDataTable(false);
		formularyDetails=new ArrayList<FormularyDetail>();
		alternativeDrugInfoList=new ArrayList<FormularyDetail>();
		
		
		String drugMedicineForFormulary="";
		try{
		if(!(getDrugDoseCombinationDataTable().getRowCount()==0)){
			WSDrug selectedDrug=(WSDrug) getDrugDoseCombinationDataTable().getRowData();
			System.out.println("selected drug "+selectedDrug.getDrugDetail());
			drugMedicineForFormulary=selectedDrug.getDrugDetail();
			setMedNameForFormularySearch(drugMedicineForFormulary);
		}
		if(!(insuranceCompanies.getId()==0 && !(drugMedicineForFormulary.equals("")))){
			
			FacesContext context = FacesContext	.getCurrentInstance();
			UserManageBean userManageBean=(UserManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"userManageBean");
			formularyAlternativeDrugMap=formulary1WsBean.getFormularyCoverage(insuranceCompanies.getId(), drugMedicineForFormulary,userManageBean.getProviderLocation());
			for(Map.Entry entry:formularyAlternativeDrugMap.entrySet()){
				System.out.println("Map Key "+entry.getKey()+"Map Value "+entry.getValue());
				if(entry.getKey().equals("formularyDetails")){
					
					formularyDetails.addAll((Collection<? extends FormularyDetail>)entry.getValue());
					System.out.println("Formulary List Size"+formularyDetails.size());
				}
				if(entry.getKey().equals("alternativeFormularyDrugList") && !(entry.getKey()==null) && !(entry.getValue().equals(null))){
					alternativeDrugInfoList.addAll((Collection<? extends FormularyDetail>)entry.getValue());
					setShowAlternativeDrugDataTable(true);
					System.out.println(" Alternative Drug Info List size "+alternativeDrugInfoList.size());
				}
			}
		}
		
		setShowFormularyDetailDataTable(true);
		}
		catch(NullPointerException ne)
		{
			ne.getMessage();
		}
		catch(Exception ne)
		{
			ne.getMessage();
		}
		
	}

	/**
	 * @return the formularyAlternativeDrugMap
	 */
	public Map<String, List<FormularyDetail>> getFormularyAlternativeDrugMap() {
		if(formularyAlternativeDrugMap==null){
			formularyAlternativeDrugMap=new HashMap<String, List<FormularyDetail>>();
		}
		return formularyAlternativeDrugMap;
	}

	/**
	 * @param formularyAlternativeDrugMap the formularyAlternativeDrugMap to set
	 */
	public void setFormularyAlternativeDrugMap(
			Map<String, List<FormularyDetail>> formularyAlternativeDrugMap) {
		this.formularyAlternativeDrugMap = formularyAlternativeDrugMap;
	}

	/**
	 * @return the formulary1WsBean
	 */
	public NcFormulary1WSBean getFormulary1WsBean() {
		return formulary1WsBean;
	}

	/**
	 * @param formulary1WsBean the formulary1WsBean to set
	 */
	public void setFormulary1WsBean(NcFormulary1WSBean formulary1WsBean) {
		this.formulary1WsBean = formulary1WsBean;
	}

	/**
	 * @return the formularyDetails
	 */
	public List<FormularyDetail> getFormularyDetails() {
		if(formularyDetails==null){
			formularyDetails=new ArrayList<FormularyDetail>();
		}
		return formularyDetails;
	}

	/**
	 * @param formularyDetails the formularyDetails to set
	 */
	public void setFormularyDetails(List<FormularyDetail> formularyDetails) {
		this.formularyDetails = formularyDetails;
	}

	/**
	 * @return the showFormularyDetailDataTable
	 */
	public boolean isShowFormularyDetailDataTable() {
		return showFormularyDetailDataTable;
	}

	/**
	 * @param showFormularyDetailDataTable the showFormularyDetailDataTable to set
	 */
	public void setShowFormularyDetailDataTable(boolean showFormularyDetailDataTable) {
		this.showFormularyDetailDataTable = showFormularyDetailDataTable;
	}

	/**
	 * @return the alternativeDrugInfoList
	 */
	public List<FormularyDetail> getAlternativeDrugInfoList() {
		if(alternativeDrugInfoList==null){
			alternativeDrugInfoList=new ArrayList<FormularyDetail>();
		}
		return alternativeDrugInfoList;
	}

	/**
	 * @param alternativeDrugInfoList the alternativeDrugInfoList to set
	 */
	public void setAlternativeDrugInfoList(
			List<FormularyDetail> alternativeDrugInfoList) {
		this.alternativeDrugInfoList = alternativeDrugInfoList;
	}

	/**
	 * @return the showAlternativeDrugDataTable
	 */
	public boolean isShowAlternativeDrugDataTable() {
		return showAlternativeDrugDataTable;
	}

	/**
	 * @param showAlternativeDrugDataTable the showAlternativeDrugDataTable to set
	 */
	public void setShowAlternativeDrugDataTable(boolean showAlternativeDrugDataTable) {
		this.showAlternativeDrugDataTable = showAlternativeDrugDataTable;
	}

	//Method to open allergy page by Anand S Jha
	public void setAlergyIndex() {
		setIndexVar(6);
		setIndexValue(1);
		//System.out.println("new Index is DPO " + indexVar);
	}
	//Method to go to allergy page by Anand S Jha
	public String gotoAlergyPage()
	{
		setIndexVar(2);
		setIndexValue(6);
		setSubIndexVar(7);
		return "go_to_alergy_page";
	}
	
	//Method to go to Personalized Med Review page by Anand S Jha
		public String gotoPMRPage()
		{
			setSubIndexVar(3);
			return "go_to_persionalizeMedReview";
		}
    //Method to go to Personalized Med Review page by Anand S Jha
				public String gotoanticoagPage()
				{
					setSubIndexVar(4);
					return "go_to_anticoag";
				}
				//Method to go to Care Analysis page by Anand S Jha
				public String setCAIndex()
				{
					setIndexVar(5);
					setSubIndexVar(8);
					return "go_to_Care_Analysis";
				}
				
				public String setdeviceIndex()
				{
					setIndexVar(6);
					setSubIndexVar(8);
					return "go_to_device";
				}
				
	public List<LoginSecurity> getLoginDetailsForAdminList() {
		
		if(loginDetailsForAdminList.isEmpty())
			loginDetailsForAdminList=dashBoardService.findDetailsForAdmin();
		return loginDetailsForAdminList;
	}

	public void setLoginDetailsForAdminList(
			List<LoginSecurity> loginDetailsForAdminList) {
		this.loginDetailsForAdminList = loginDetailsForAdminList;
	}

	/**
	 * @return the update1WsBean
	 */
	public NcUpdate1WSBean getUpdate1WsBean() {
		return update1WsBean;
	}

	/**
	 * @param update1WsBean the update1WsBean to set
	 */
	public void setUpdate1WsBean(NcUpdate1WSBean update1WsBean) {
		this.update1WsBean = update1WsBean;
	}
	
	
 /**
  * Used in MasterDataManagment.xhtml
  * used to Save Latest new crop download detail data in database 
  * <p>
  * @throws Exception 
  */
	public void saveRecentDownloadDetailData() throws Exception{
		FacesContext context = FacesContext	.getCurrentInstance();
		UserManageBean userManageBean=(UserManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"userManageBean");
		DownloadDetail downloadDetail=update1WsBean.getMostRecentDownloadUrl(userManageBean.getProviderLocation());
		dashBoardService.saveNewCropDownloadDetailData(downloadDetail);
		
	}

/**
 * Used in MasterDataManagment.xhtml
 * use to fetch/display the new crop master data download detail on UI.
 * <p>
 * @return the downloadDetailList
 * @throws ParseException 
 * @throws SQLException 
 * @throws HibernateException 
 */
public List<DownloadDetail> getDownloadDetailList() throws ParseException, HibernateException, SQLException {
	if(downloadDetailList==null){
		downloadDetailList=new ArrayList<DownloadDetail>();
	}
	List<DownloadDetail> downloadDetailsOrignal=new ArrayList<DownloadDetail>();
	downloadDetailsOrignal=dashBoardService.getRecentDownloadDetailData();
	downloadDetailList=new ArrayList<DownloadDetail>();
	for(DownloadDetail dwnDetail:downloadDetailsOrignal){
		DownloadDetail downloadDetail=new DownloadDetail();
		String cchitDbDate=dwnDetail.getCchitDrugDatabaseDate();
		DateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		Date date=dateFormat.parse(cchitDbDate);
		SimpleDateFormat newFormat=new SimpleDateFormat("yyyy-MM-dd");
		String formatedChitDbDate=newFormat.format(date);
		downloadDetail.setCchitDrugDatabaseDate(formatedChitDbDate);
		downloadDetail.setComments(dwnDetail.getComments());
		downloadDetail.setLatestDownloadDay(dwnDetail.getLatestDownloadDay());
		downloadDetail.setLatestDownloadMonth(dwnDetail.getLatestDownloadMonth());
		downloadDetail.setLatestDownloadSize(dwnDetail.getLatestDownloadSize());
		downloadDetail.setLatestDownloadUrl(dwnDetail.getLatestDownloadUrl());
		downloadDetail.setLatestDownloadYear(dwnDetail.getLatestDownloadYear());
		downloadDetailList.add(downloadDetail);
	}
	
	return downloadDetailList;
}

/**
 * @param downloadDetailList the downloadDetailList to set
 */
public void setDownloadDetailList(List<DownloadDetail> downloadDetailList) {
	this.downloadDetailList = downloadDetailList;
}
/**
 * 	On Selection of Row Land on OverviewFilter.jsf page 
 * Based on Selected Patient 
 * @throws IOException
 */
public void onRowSelect() throws IOException {  
	System.out.print("hello");
//	userLoginDetail=(UserLoginDetail) getBindUserDataTable().getRowData();
	userLoginDetail=new UserLoginDetail();
	userLoginDetail=selectedUserDetail;
	logger.info("patientSearchData method in usermanagebean startted::::"+userLoginDetail.getId());
	
	/*UserLoginDetail selectedRowId = (UserLoginDetail) getPatientSearchTableObj()
			.getRowData();*/
	/*System.out.println("::::::umesh row patientSearchData:::" + userLoginDetail.getFirstName()+userLoginDetail.getId());*/
	this.userLoginDetail.setId(userLoginDetail.getId());

	// ---------------------To enable menu under patient tab
	setShowPatientSubmenu(false);
	// this.userLoginDetail.setLoginDetailId(selectedRowId.getLoginDetailId());

	/*
	 * set patient id and patient name in session for further use by gopal
	 */

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
			.getExternalContext().getSession(true);

	session.setAttribute("patientId", userLoginDetail.getUserId());
	// session.setAttribute("PatientLoginId",
	// userLoginDetail.getLoginDetailId());
	System.out.println("::::::check row patientSearchData:::" + userLoginDetail.getUserId());
	FacesContext context = FacesContext.getCurrentInstance();

	LabManageBean labManageBean = (LabManageBean) context.getApplication()
			.getELResolver()
			.getValue(context.getELContext(), null, "labManageBean");
	// dctrBean=new DoctorBean();
	labManageBean.setProcedureResultListForCurrentLab(null);
	labManageBean.setProcedureResultHistoryList(null);
	labManageBean.setLoincKeyMasterList(null);
	labManageBean.setProcedureResultHistoryListForChart(null);
	labManageBean.resetValue();
	PatientMedicineManageBean patientMedicineManageBean = (PatientMedicineManageBean) context
			.getApplication()
			.getELResolver()
			.getValue(context.getELContext(), null,
					"patientMedicineManageBean");
	// patientMedicineManageBean.setPatientMedicationDataList(null);
	patientMedicineManageBean.reset();
	
	
	PatientManageBean patientManageBean = (PatientManageBean) context
			.getApplication()
			.getELResolver()
			.getValue(context.getELContext(), null,
					"patientManageBean");
	// patientMedicineManageBean.setPatientMedicationDataList(null);
	patientManageBean.resetVAlue();
	setShowPatientMenu(true);
//----added by vinod for allignment of kidney/liver function
		setKidneyLabNameList(null);
		setKidneyLabResultList(null);
		setKidneyLabUnitList(null);
		
		setLiverLabNameList(null);
		setLiverLabResultList(null);
		setLiverLabUnitList(null);
		
		 kidneyLabNameList = new ArrayList<String>();
		 kidneyLabResultList =  new ArrayList<Double>();
		 kidneyLabUnitList = new ArrayList<String>();
		
		 liverLabNameList = new ArrayList<String>();
		 liverLabResultList = new ArrayList<Double>();
		 liverLabUnitList = new ArrayList<String>();
		/* System.out.println("::::::check row patientSearchData:::12--" + userLoginDetail.getUserId());*/
	for (UserLoginDetail user : userLoginDetailList) {
		System.out.println("11111111 inside for each UserLoginDetail id="+user.getId()+":userLoginDetail.getId="+userLoginDetail.getId()
				+":userId="+user.getUserId()+":userLoginDetail.getUId="+userLoginDetail.getUserId());
		if (user.getUserId() == userLoginDetail.getUserId()) {
			
			this.userLoginDetail = user;

			
			/**
			 *  Assembla Ticket:#1079
			 * following lines commented by Nagaraj on 20/Jan/2015 as it was wrongly calculated as reported on production instance
			 * ex:Date of birth is 09/25/1926, today’s date is 1/19/2015, so patient age should be 88 but it displays 89
			 * To fix this issue:Used Joda Time library
			 */
			
		/*	userLoginDetail.setAge(new Date().getYear()
					- userLoginDetail.getDateOfBirth().getYear());*/
			
			if(userLoginDetail.getDateOfBirth()!=null){
				 Years years = Years.yearsBetween(new LocalDate(userLoginDetail.getDateOfBirth()), new LocalDate());
				Integer patientAge=years.getYears();
				logger.info("Patient Age: "+patientAge+" Birth date: "+userLoginDetail.getDateOfBirth());
				userLoginDetail.setAge(patientAge);
			}

		}
	}

	insuranceNameList = dashBoardService
			.userInsuranceDetails(userLoginDetail.getUserId()); // get Insurance Data For Patient Detail Header Page 
	patientProviderClinicList = dashBoardService
			.findPatientProviderClinicList(userLoginDetail.getUserId());

	diagnosisList = dashBoardService
			.findDiagnosisDetailList(userLoginDetail.getUserId());

	patientVital = dashBoardService
			.findParticularPatientVitalDetail(userLoginDetail.getUserId());
	patientVital.setWeightInKG(patientVital.getWeight());
	patientEmployerDetails = dashBoardService
			    .findPatientEmployerDetails(userLoginDetail.getUserId());
	patientGuarantor=dashBoardService.getPatientGuarantor(userLoginDetail.getUserId());
	

	// find liver function, kidney function value...
	viewFunctionDetailList = dashBoardService
			.findViewFunctionDetailList(userLoginDetail.getUserId());

	lovTypeList = dashBoardService.findLovTypeListInDetail();

	kidneyFunctionDetailList =  findFunctionDetailList(KIDNEYFUNCTION,
			lovTypeList, viewFunctionDetailList);
	liverFunctionList = findFunctionDetailList(LIVERFUNCTION, lovTypeList,
			viewFunctionDetailList);
	
//----added by vinod for allignment of kidney/liver function
	
	kidneyLabNameList = findFunctionLabName(KIDNEYFUNCTION, lovTypeList, viewFunctionDetailList);//Get All Lab  Name of Kidney Function  for PatientDetailHeader.jsf 
	kidneyLabResultList = findFunctionLabResult(kidneyLabNameList, viewFunctionDetailList);//Get All Lab  Name of Kidney Function  for PatientDetailHeader.jsf
	kidneyLabUnitList = findFunctionLabUnit(kidneyLabNameList,  viewFunctionDetailList);//Get All Lab  unit  of Kidney function Lab  for PatientDetailHeader.jsf 
	
	liverLabNameList = findFunctionLabName(LIVERFUNCTION, lovTypeList, viewFunctionDetailList); //Get All Lab  Name of Liver for PatientDetailHeader.jsf 
	liverLabResultList = findFunctionLabResult(liverLabNameList,  viewFunctionDetailList); //Get All Lab  Result Value  of Liver Function  for PatientDetailHeader.jsf 
	liverLabUnitList = findFunctionLabUnit(liverLabNameList,  viewFunctionDetailList);//Get All Lab  Result unit   of Liver Function  for PatientDetailHeader.jsf 
	findBodyMassIndex(patientVital.getHeight(), patientVital.getWeight(),this.getUserLoginDetail().getGender());
	//Added By Anjani for PBM name 
	pbmNameList =dashBoardService.getPbmNameData(userLoginDetail.getUserId()); // Get Pbm Detail For PatientDetailHeader page 
	Collections.replaceAll(pbmNameList,"SURESCRIPTS LLC","XYZ");
	tabIndex = 0;
	accPanelIndex = 1;
	calculateCrCl=null;
	redirectToParticularURL();
	


} 
//Need to refactor
public void redirectToParticularURL() throws IOException {
	
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	String role=new ContextUtil().getRole();
	if(role.equalsIgnoreCase("ROLE_PHARMA")){
		logger.info("Request contextpath "+ec.getRequestContextPath());
		ec.redirect(ec.getRequestContextPath() +"/page/Doctor/overViewFilter.jsf");
	}else{
		ec.redirect("overViewFilter.jsf");
	}
	
}
//Need to refactor
public void redirectToParticularAnalyticsURL() throws IOException{
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	String role=new ContextUtil().getRole();
	if(role.equalsIgnoreCase("ROLE_PHARMA")){
		logger.info("Request contextpath "+ec.getRequestContextPath());
		ec.redirect(ec.getRequestContextPath() +"/page/Pharma/pharmaDashboard.jsf");
	}else{
		ec.redirect("doctorProfile.jsf");
	}
}

/*public void onRowSelectforanticoagintition(SelectEvent event) throws IOException
{
	FacesContext context = FacesContext.getCurrentInstance();

	LabManageBean labManageBean = (LabManageBean) context.getApplication()
			.getELResolver()
			.getValue(context.getELContext(), null, "labManageBean");
	// dctrBean=new DoctorBean();
	labManageBean.setProcedureResultListForCurrentLab(null);
	labManageBean.setProcedureResultHistoryList(null);
	labManageBean.setLoincKeyMasterList(null);
	labManageBean.setProcedureResultHistoryListForChart(null);
	labManageBean.resetValue();
	PatientMedicineManageBean patientMedicineManageBean = (PatientMedicineManageBean) context
			.getApplication()
			.getELResolver()
			.getValue(context.getELContext(), null,
					"patientMedicineManageBean");
	// patientMedicineManageBean.setPatientMedicationDataList(null);
	patientMedicineManageBean.reset();
	
	PatientManageBean patientManageBean = (PatientManageBean) context
			.getApplication()
			.getELResolver()
			.getValue(context.getELContext(), null,
					"patientManageBean");
	// patientMedicineManageBean.setPatientMedicationDataList(null);
	patientManageBean.resetVAlue();
	setShowPatientMenu(true);
//----added by vinod for allignment of kidney/liver function
		setKidneyLabNameList(null);
		setKidneyLabResultList(null);
		setKidneyLabUnitList(null);
		
		setLiverLabNameList(null);
		setLiverLabResultList(null);
		setLiverLabUnitList(null);
		
		 kidneyLabNameList = new ArrayList<String>();
		 kidneyLabResultList =  new ArrayList<Double>();
		 kidneyLabUnitList = new ArrayList<String>();
		
		 liverLabNameList = new ArrayList<String>();
		 liverLabResultList = new ArrayList<Double>();
		 liverLabUnitList = new ArrayList<String>();
	for (UserLoginDetail user : userLoginDetailList) {
		if (user.getId() == userLoginDetail.getId()) {
			this.userLoginDetail = user;

			userLoginDetail.setAge(new Date().getYear()
					- userLoginDetail.getDateOfBirth().getYear());

		}
	}

	insuranceNameList = dashBoardService
			.userInsuranceDetails(userLoginDetail.getId());
	patientProviderClinicList = dashBoardService
			.findPatientProviderClinicList(userLoginDetail.getId());

	diagnosisList = dashBoardService
			.findDiagnosisDetailList(userLoginDetail.getId());

	patientVital = dashBoardService
			.findParticularPatientVitalDetail(userLoginDetail.getId());
	patientVital.setWeightInKG(patientVital.getWeight());
	
	

	// find liver function, kidney function value...
	viewFunctionDetailList = dashBoardService
			.findViewFunctionDetailList(userLoginDetail.getId());

	lovTypeList = dashBoardService.findLovTypeListInDetail();

	kidneyFunctionDetailList =  findFunctionDetailList(KIDNEYFUNCTION,
			lovTypeList, viewFunctionDetailList);
	liverFunctionList = findFunctionDetailList(LIVERFUNCTION, lovTypeList,
			viewFunctionDetailList);
	
//----added by vinod for allignment of kidney/liver function
	
	kidneyLabNameList = findFunctionLabName(KIDNEYFUNCTION, lovTypeList, viewFunctionDetailList);
	kidneyLabResultList = findFunctionLabResult(kidneyLabNameList, viewFunctionDetailList);
	kidneyLabUnitList = findFunctionLabUnit(kidneyLabNameList,  viewFunctionDetailList);
	
	liverLabNameList = findFunctionLabName(LIVERFUNCTION, lovTypeList, viewFunctionDetailList);
	liverLabResultList = findFunctionLabResult(liverLabNameList,  viewFunctionDetailList);
	liverLabUnitList = findFunctionLabUnit(liverLabNameList,  viewFunctionDetailList);
	findBodyMassIndex(patientVital.getHeight(), patientVital.getWeight());
	tabIndex = 0;
	accPanelIndex = 1;
	calculateCrCl=0;
	
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	ec.redirect("overview.jsf");
}*/

public void onRowSelectforvirtualclinic(SelectEvent event) throws IOException
{
	logger.info("patientSearchDataforVirtualClinic method in usermanagebean startted::::"+userLoginDetail.getId());
	// patientSearchTableObjForVirtualClinic
	/*UserLoginDetail selectedRowId = (UserLoginDetail) getPatientSearchTableObjForVirtualClinic()
			.getRowData();*/
	System.out.println("::::::patientSearchData:::"+userLoginDetail.getId());
	this.userLoginDetail.setId(userLoginDetail.getId());

	// ---------------------To enable menu under patient tab
	setShowPatientSubmenu(false);

	// this.userLoginDetail.setLoginDetailId(selectedRowId.getLoginDetailId());

	/*
	 * set patient id and patient name in session for further use by gopal
	 */

	HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
			.getExternalContext().getSession(true);

	session.setAttribute("patientId", userLoginDetail.getUserId());
	// session.setAttribute("PatientLoginId",
	// userLoginDetail.getLoginDetailId());

	FacesContext context = FacesContext.getCurrentInstance();

	LabManageBean labManageBean = (LabManageBean) context.getApplication()
			.getELResolver()
			.getValue(context.getELContext(), null, "labManageBean");
	// dctrBean=new DoctorBean();
	labManageBean.setProcedureResultListForCurrentLab(null);
	labManageBean.setProcedureResultHistoryList(null);
	labManageBean.setLoincKeyMasterList(null);
	labManageBean.setProcedureResultHistoryListForChart(null);
	labManageBean.resetValue();
	PatientMedicineManageBean patientMedicineManageBean = (PatientMedicineManageBean) context
			.getApplication()
			.getELResolver()
			.getValue(context.getELContext(), null,
					"patientMedicineManageBean");
	// patientMedicineManageBean.setPatientMedicationDataList(null);
	patientMedicineManageBean.reset();
	setShowPatientMenu(true);

//----added by vinod for allignment of kidney/liver function
	setKidneyLabNameList(null);
	setKidneyLabResultList(null);
	setKidneyLabUnitList(null);
	
	setLiverLabNameList(null);
	setLiverLabResultList(null);
	setLiverLabUnitList(null);
	calculateCrCl=null;
	
	 kidneyLabNameList = new ArrayList<String>();
	 kidneyLabResultList =  new ArrayList<Double>();
	 kidneyLabUnitList = new ArrayList<String>();
	
	 liverLabNameList = new ArrayList<String>();
	 liverLabResultList = new ArrayList<Double>();
	 liverLabUnitList = new ArrayList<String>();
	 
	for (UserLoginDetail user : userLoginDetailList) {
		System.out.println("22222inside for each UserLoginDetail id="+user.getId()+":userLoginDetail.getId="+userLoginDetail.getId()
				+":userId="+user.getUserId()+":userLoginDetail.getUId="+userLoginDetail.getUserId());
		if (user.getUserId() ==userLoginDetail.getUserId()) {
			this.userLoginDetail = user;

			if(userLoginDetail.getDateOfBirth()!=null){
				 Years years = Years.yearsBetween(new LocalDate(userLoginDetail.getDateOfBirth()), new LocalDate());
				Integer patientAge=years.getYears();
				logger.info("Patient Age : onRowSelectforvirtualclinic"+patientAge+" Birth date:onRowSelectforvirtualclinic "+userLoginDetail.getDateOfBirth());
				userLoginDetail.setAge(patientAge);
			}

		}
	}

	insuranceNameList = dashBoardService
			.userInsuranceDetails(userLoginDetail.getUserId()); //get Insurance Data For Patient Detail Header Page 
	patientProviderClinicList = dashBoardService
			.findPatientProviderClinicList(userLoginDetail.getUserId());

	diagnosisList = dashBoardService
			.findDiagnosisDetailList(userLoginDetail.getUserId());

	patientVital = dashBoardService
			.findParticularPatientVitalDetail(userLoginDetail.getUserId());
	//Added By Anjani for PBM
	pbmNameList =dashBoardService.getPbmNameData(userLoginDetail.getUserId()); //Pbm Name List For Patient Detail Header page 
	Collections.replaceAll(pbmNameList,"SURESCRIPTS LLC","XYZ");
	
	//patient login 
	patientVital = dashBoardService
	.findParticularPatientVitalDetail(userLoginDetail.getUserId());
	
	System.out.println("patientVital::::in patient_login:::"+patientVital.getHeight());

	// find liver function, kidney function value...
	viewFunctionDetailList = dashBoardService
			.findViewFunctionDetailList(userLoginDetail.getUserId());

	lovTypeList = dashBoardService.findLovTypeListInDetail();

	kidneyFunctionDetailList =  findFunctionDetailList(KIDNEYFUNCTION,
			lovTypeList, viewFunctionDetailList);
	liverFunctionList = findFunctionDetailList(LIVERFUNCTION, lovTypeList,
			viewFunctionDetailList);
//-------added by vinod for allignment of kidney/liver function
	kidneyLabNameList = findFunctionLabName(KIDNEYFUNCTION, lovTypeList, viewFunctionDetailList);//Get All Lab  Name of Kidney Function  for PatientDetailHeader.jsf
	kidneyLabResultList = findFunctionLabResult(kidneyLabNameList, viewFunctionDetailList);//Get All Lab  Name of Kidney Function  for PatientDetailHeader.jsf
	kidneyLabUnitList = findFunctionLabUnit(kidneyLabNameList,  viewFunctionDetailList);//Get All Lab  unit  of Kidney function Lab  for PatientDetailHeader.jsf 
	
	liverLabNameList = findFunctionLabName(LIVERFUNCTION, lovTypeList, viewFunctionDetailList); //Get All Lab  Name of Liver for PatientDetailHeader.jsf 
	liverLabResultList = findFunctionLabResult(liverLabNameList,  viewFunctionDetailList);//Get All Lab  Result Value  of Liver Function  for PatientDetailHeader.jsf 
	liverLabUnitList = findFunctionLabUnit(liverLabNameList,  viewFunctionDetailList); //Get All Lab  Result unit   of Liver Function  for PatientDetailHeader.jsf 
	
	findBodyMassIndex(patientVital.getHeight(), patientVital.getWeight(),this.getUserLoginDetail().getGender());

	tabIndex = 0;
	accPanelIndex = 1;
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	ec.redirect("overview.jsf");
	
	
	}

	/**
	 * @return the empProviderLocationList
	 */
	public List<EmpProviderLocation> getEmpProviderLocationList() {
		
		if (empProviderLocationList==null) {
			empProviderLocationList=new ArrayList<EmpProviderLocation>();
			empProviderLocationList=dashBoardService.getEmpProviderLocationList();
		}
		return empProviderLocationList;
	}
	
	/**
	 * @param empProviderLocationList the empProviderLocationList to set
	 */
	public void setEmpProviderLocationList(
			List<EmpProviderLocation> empProviderLocationList) {
		this.empProviderLocationList = empProviderLocationList;
	}

	/**
	 * @return the kidneyLabNameList
	 */
	public List<String> getKidneyLabNameList() {
		return kidneyLabNameList;
	}

	/**
	 * @param kidneyLabNameList the kidneyLabNameList to set
	 */
	public void setKidneyLabNameList(List<String> kidneyLabNameList) {
		this.kidneyLabNameList = kidneyLabNameList;
	}

	/**
	 * @return the kidneyLabResultList
	 */
	public List<Double> getKidneyLabResultList() {
		return kidneyLabResultList;
	}

	/**
	 * @param kidneyLabResultList the kidneyLabResultList to set
	 */
	public void setKidneyLabResultList(List<Double> kidneyLabResultList) {
		this.kidneyLabResultList = kidneyLabResultList;
	}

	/**
	 * @return the kidneyLabUnitList
	 */
	public List<String> getKidneyLabUnitList() {
		return kidneyLabUnitList;
	}

	/**
	 * @param kidneyLabUnitList the kidneyLabUnitList to set
	 */
	public void setKidneyLabUnitList(List<String> kidneyLabUnitList) {
		this.kidneyLabUnitList = kidneyLabUnitList;
	}

	/**
	 * @return the liverLabNameList
	 */
	public List<String> getLiverLabNameList() {
		return liverLabNameList;
	}

	/**
	 * @param liverLabNameList the liverLabNameList to set
	 */
	public void setLiverLabNameList(List<String> liverLabNameList) {
		this.liverLabNameList = liverLabNameList;
	}

	/**
	 * @return the liverLabResultList
	 */
	public List<Double> getLiverLabResultList() {
		return liverLabResultList;
	}

	/**
	 * @param liverLabResultList the liverLabResultList to set
	 */
	public void setLiverLabResultList(List<Double> liverLabResultList) {
		this.liverLabResultList = liverLabResultList;
	}

	/**
	 * @return the liverLabUnitList
	 */
	public List<String> getLiverLabUnitList() {
		return liverLabUnitList;
	}

	/**
	 * @param liverLabUnitList the liverLabUnitList to set
	 */
	public void setLiverLabUnitList(List<String> liverLabUnitList) {
		this.liverLabUnitList = liverLabUnitList;
	}
	
/*
 * *******for fetching function kidney/liver lab name on overview page
 * @return Lab Name of Liver Function 
 */
	public List<String> findFunctionLabName(String funValue,List<LOVType> lovTypeListValue, List<ViewFunctionalDetails> viewFunctionDetailList){
		

		logger.info("functionDetailList start in dashboardmanage bean:::::");
		List<String> functionNameList = new ArrayList<String>();
		for (Iterator iterator = lovTypeListValue.iterator(); iterator
				.hasNext();) {
			LOVType ltype = (LOVType) iterator.next();
			/* ltype.getText().startsWith(A);*/
			if (ltype.getText().equals(funValue)) {
				Set lcode = ltype.getLovCodeDetail();
				for (Iterator iterator2 = lcode.iterator(); iterator2.hasNext();) {
					LOVCode lcode1 = (LOVCode) iterator2.next();
					String functionDetail = lcode1.getLabel();
					for (ViewFunctionalDetails vfdetail : viewFunctionDetailList) {
						if (vfdetail.getName().equals(lcode1.getLabel())) {
							functionDetail = functionDetail;
						}
					}
						functionNameList.add(functionDetail);
					    Collections.sort(functionNameList);
				}
			}
		}
		return functionNameList;
	
	}

/*
 * *******for fetching function kidney/liver lab result on overview page
 */
	  public List<Double> findFunctionLabResult(List<String> functionLabNameList,List<ViewFunctionalDetails> viewFunctionDetailList) {
		logger.info("findFunctionLabResult start :::::::::::;;");
		List<Double> functionlabResultList = new ArrayList<Double>();
		try {
			//double labResult = 0.00;
			
					for (String str : functionLabNameList) {
						logger.info("labResult value before vflist :::"+str);
						double labResult = 0.00;
						for (ViewFunctionalDetails vfdetail : viewFunctionDetailList) {
							logger.info("labResult value after vflist :::"+str+vfdetail.getName());
							
							if (vfdetail.getName().equals(str)) {
								labResult = vfdetail.getResult();
								
								logger.info("value of labResult:::"+labResult+":::"+str);
								break;
							}
							
							}
						functionlabResultList.add(labResult);
						//Collections.sort(functionlabResultList);
						}

		} catch (Exception e) {
			logger.equals("exception is"+e);
		}

		return functionlabResultList;
	}

/*
 * *******for fetching function kidney/liver lab unit on overview page
 */	
	public List<String> findFunctionLabUnit(List<String> functionLabNameList,List<ViewFunctionalDetails> viewFunctionDetailList) {
		logger.info("findFunctionLabUnit start :::::::::::;;");
		List<String> functionlabUnitList = new ArrayList<String>();
		try {
			
			for (String str : functionLabNameList) {
				String labUnit = "";
				logger.info("value before vflist unit:::"+str);
				for (ViewFunctionalDetails vfdetail : viewFunctionDetailList) {
					logger.info("value after vflist unit:::"+str+vfdetail.getName());
					//labUnit = "N/A";
					if (vfdetail.getName().equals(str)) {
						labUnit = vfdetail.getUnit();
						logger.info("value of labunit:::"+labUnit+":::"+str);
						break;
					}
					}
				functionlabUnitList.add(labUnit);
				//Collections.sort(functionlabResultList);
				}
		} catch (Exception e) {
			logger.equals("exception is"+e);
		}

		return functionlabUnitList;
	}

/*
 * *******method to update liver and kidney function
 * value after adding new one 
 * @uthor:saurabh
 * 
 */
	public void updateLiverAndKidneyFunctionValue()
	{
		logger.info("inside method getLiverAndKidneyFunction:::");
		viewFunctionDetailList = dashBoardService.findViewFunctionDetailList(userLoginDetail.getId());
		setKidneyLabNameList(null);
		setKidneyLabResultList(null);
		setKidneyLabUnitList(null);
		setLiverLabNameList(null);
		setLiverLabResultList(null);
		setLiverLabUnitList(null);
		kidneyLabNameList = new ArrayList<String>();
		kidneyLabResultList =  new ArrayList<Double>();
		kidneyLabUnitList = new ArrayList<String>();
		liverLabNameList = new ArrayList<String>();
		liverLabResultList = new ArrayList<Double>();
		liverLabUnitList = new ArrayList<String>();
		
		kidneyLabNameList = findFunctionLabName(KIDNEYFUNCTION, lovTypeList, viewFunctionDetailList);//Get All Lab  Name of Kidney Function  for PatientDetailHeader.jsf
		kidneyLabResultList = findFunctionLabResult(kidneyLabNameList, viewFunctionDetailList);//Get All Lab  Result  of Kidney Function  for PatientDetailHeader.jsf
		kidneyLabUnitList = findFunctionLabUnit(kidneyLabNameList,  viewFunctionDetailList);//Get All Lab  unit  of Kidney function Lab  for PatientDetailHeader.jsf 
		
		liverLabNameList = findFunctionLabName(LIVERFUNCTION, lovTypeList, viewFunctionDetailList); //Get All Lab  Name of Liver for PatientDetailHeader.jsf 
		liverLabResultList = findFunctionLabResult(liverLabNameList,  viewFunctionDetailList); //Get All Lab  Result  of Liver Function  for PatientDetailHeader.jsf 
		liverLabUnitList = findFunctionLabUnit(liverLabNameList,  viewFunctionDetailList);//Get All Lab  Result unit   of Liver Function  for PatientDetailHeader.jsf 
		//setCalculateCrCl(null);
		calculateCrCl=null;
		getCalculateCrCl();
	}
	
	
/*
 * ***Method to update daignosis list after adding 
 * ***new diagnosis
 * @uthor : saurabh
 */
	
	public void updateDiagnosisListAfterAddingNewDiagnosis()
	{
		logger.info("inside updateDiagnosisListAfterAddingNewOne in dashBoardManageBean:::");
		diagnosisList = dashBoardService.findDiagnosisDetailList(userLoginDetail.getId());
		
	}

	public Double getCalculateCrCl() {
		logger.info("getCalculateCrCl method start");
	    if(calculateCrCl==null)
		{

			FacesContext context = FacesContext.getCurrentInstance();

			PatientMedicineManageBean patientMedicineManageBean = (PatientMedicineManageBean) context.getApplication()
					.getELResolver()
					.getValue(context.getELContext(), null, "patientMedicineManageBean");
			if(patientVital.getWeight()==0)
				calculateCrCl=null;
			else
			calculateCrCl=patientMedicineManageBean.calculateCrediantialOFCreatimeClearence();
			
		}
		return calculateCrCl;
	}

	public void setCalculateCrCl(Double calculateCrCl) {
		this.calculateCrCl = calculateCrCl;
	}

	
	/*added by vinod for  show the crcl value according to kidneyfunctionresult on patient detail*/
	/**
	 * @return the kidneyResultValue
	 */
	public boolean isKidneyResultValue() {
		
		/*List<Double> labResultList = findFunctionLabResult(kidneyLabNameList, viewFunctionDetailList);
		
		for(String lab:kidneyLabNameList){
			if(lab.getName().equalsIgnoreCase("Cr") && lab.getResult() != 0.00){
				kidneyResultValue = true;
			}
			else if (lab.getName().equalsIgnoreCase("bun") && lab.getResult() !=0.00) 
			{
				kidneyResultValue = true;
			}else
				kidneyResultValue = false;
		}*/
 		
		 double crcl = kidneyLabResultList.get(1);
		if(crcl==0.00 || kidneyLabResultList.isEmpty()){
			logger.info("kidneyLabResultList::"+kidneyLabResultList);
			kidneyResultValue = false;
		}
		else{
			kidneyResultValue = true;
		}
		System.out.println("kidneyResultValue::::::::::"+kidneyResultValue);
		return kidneyResultValue;
	}

	/**
	 * @param kidneyResultValue the kidneyResultValue to set
	 */
	public void setKidneyResultValue(boolean kidneyResultValue) {
		this.kidneyResultValue = kidneyResultValue;
	}

	public DataTable getBindUserDataTable() {
		return bindUserDataTable;
	}

	public void setBindUserDataTable(DataTable bindUserDataTable) {
		this.bindUserDataTable = bindUserDataTable;
	}

	public UserLoginDetail getSelectedUserDetail() {
		if(selectedUserDetail==null){
			selectedUserDetail=new UserLoginDetail();
		}
		return selectedUserDetail;
	}

	public void setSelectedUserDetail(UserLoginDetail selectedUserDetail) {
		this.selectedUserDetail = selectedUserDetail;
	}

	public List<DiagnosesChart> getDiagnosesChartList() {
		if(diagnosesChartList == null){
			diagnosesChartList = new ArrayList<DiagnosesChart>();
			diagnosesChartList = dashBoardService.getDiagnosesChartList();
	    }
		return diagnosesChartList;
	}

	public void setDiagnosesChartList(List<DiagnosesChart> diagnosesChartList) {
		this.diagnosesChartList = diagnosesChartList;
	}
	
/*	  public void diagnosisCategoryGraph(){
		logger.info("diagnosisCategoryGraph method started dashboasrdManagewbean::::::::::::::::::::::::::");
		diagnosisChartObj = new CartesianChartModel();
		ChartSeries diagnosisClass = new ChartSeries();
		for (DiagnosesChart diachart : getDiagnosesChartList()) {
			logger.info("diagnosesName:::::::"+diachart.getDiagnosesName()+" noofPatients::::::::::"+diachart.getNoofpatients());
			diagnosisClass.set(diachart.getDiagnosesName(),diachart.getNoofpatients());
		 }
		 diagnosisClass.setLabel("Diagnosis");
		diagnosisChartObj.addSeries(diagnosisClass);
		}*/

	public String getDiagnosesChartBarList() {
		logger.info("!!!!!optimization 1 start ="+new Date());
		diagnosesChartBarList = new ArrayList<DiagnosesChart>();
		for(DiagnosesChart diagnosis : getDiagnosesChartList()){
			
			DiagnosesChart diagnosiscount = new DiagnosesChart();
			diagnosiscount.setDiagnosesName(diagnosis.getDiagnosesName());
			diagnosiscount.setNoofpatients(diagnosis.getNoofpatients());
			diagnosesChartBarList.add(diagnosiscount);
			//System.out.println("DiagnosesName:::::::::::::"+diagnosis.getDiagnosesName()+"Noofpatients::::::::::::"+diagnosis.getNoofpatients());
		 }
		logger.info("!!!!!optimization 1 ends ="+new Date());
		return new Gson().toJson(diagnosesChartBarList);
	}

	public void setDiagnosesChartBarList(List<DiagnosesChart> diagnosesChartBarList) {
		this.diagnosesChartBarList = diagnosesChartBarList;
	}

	public CartesianChartModel getDiagnosisChartObj() {
		return diagnosisChartObj;
	}

	public void setDiagnosisChartObj(CartesianChartModel diagnosisChartObj) {
		this.diagnosisChartObj = diagnosisChartObj;
	}

	public String getFormularyList() {
		logger.info("!!!!!optimization 2 start ="+new Date());
		formularyList=dashBoardService.findFormularyChart();
		
		for(FormularyChart obj: formularyList){
			System.out.println("Retrieved values====================== "+obj.getMedicineId()+" "+obj.getFormulayTier());
		}
		logger.info("!!!!!optimization 2 ends ="+new Date());
		return new Gson().toJson(formularyList);
	}

	public void setFormularyList(List<FormularyChart> formularyList) {
		this.formularyList = formularyList;
	}
	

	public List<PatientCountForDrugGene> getPatientDrugGeneList() {
		if(patientDrugGeneList  == null){
			patientDrugGeneList = new ArrayList<PatientCountForDrugGene>();
			patientDrugGeneList = dashBoardService.getPatientDrugGeneList();
		}		
		
		return patientDrugGeneList;
	}

	public void setPatientDrugGeneList(
			List<PatientCountForDrugGene> patientDrugGeneList) {
		this.patientDrugGeneList = patientDrugGeneList;
	}

	/*public String getPatientDrugGeneChartList() {
		patientDrugGeneChartList = new ArrayList<PatientCountForDrugGene>();
		List<PatientCountForDrugGene> duplicateRecordList = new ArrayList<PatientCountForDrugGene>();
		try {		
		int count =0;
		for (PatientCountForDrugGene drugGeneList : getPatientDrugGeneList()) {
				if(count <= 9){				
				PatientCountForDrugGene drugGeneData = new PatientCountForDrugGene();
				drugGeneData.setGeneSymbol(drugGeneList.getGeneSymbol());
				drugGeneData.setMedicineName(drugGeneList.getMedicineName());
				drugGeneData.setPatientCount(drugGeneList.getPatientCount()); 
				drugGeneData.setAttentionRating(drugGeneList.getAttentionRating());
				if(count<=9){
					if(!(drugGeneData.getMedicineName().equalsIgnoreCase(drugGeneList.getMedicineName())) ){
						if(!(patientDrugGeneChartList.contains(drugGeneData))){
							patientDrugGeneChartList.add(drugGeneData);
							count++;
				          }
						else{
						   if(patientDrugGeneChartList.contains(drugGeneData)){
							   patientDrugGeneChartList.add(drugGeneData);
						   }
							
						}
					}
					
				}
				
					for(PatientCountForDrugGene retrivingTenRecods :duplicateRecordList)
					{
						System.out.println("medicineName::::: "+retrivingTenRecods.getMedicineName());
						if(drugGeneData.getMedicineName().equalsIgnoreCase(retrivingTenRecods.getMedicineName()))
						{
							if(patientDrugGeneChartList.contains(retrivingTenRecods))
							{
							patientDrugGeneChartList.add(retrivingTenRecods);
					       count =count+0;
				         }
						}
				else  {
					if(!patientDrugGeneChartList.contains(retrivingTenRecods))
					{
					patientDrugGeneChartList.add(retrivingTenRecods);
					count =count+1;
					}
			       }
			}
					duplicateRecordList.add(drugGeneData);
			logger.info("patientDrugGeneChartList ::::: size:: "+patientDrugGeneChartList.size());
	logger.info("GeneSymbol:::::::::"+drugGeneData.getGeneSymbol()+"MedicineName:::::::::"+drugGeneData.getMedicineName()+
			"noOfPatients::::::"+drugGeneData.getPatientCount()+"impact::::: "+drugGeneData.getAttentionRating());
				
				
				}
		System.out.println("patientDrugGeneChartList:::"+patientDrugGeneChartList.size());
		}
		} catch (Exception e) {
			logger.error("error in getPatientDrugGeneChartList"+e);
		}
		return new Gson().toJson(patientDrugGeneChartList);
	}*/

	public void setPatientDrugGeneChartList(
			List<PatientCountForDrugGene> patientDrugGeneChartList) {
		this.patientDrugGeneChartList = patientDrugGeneChartList;
	}


	
	/*public String getUniqueMedNamesForGene() {
		uniqueMedNamesForGene=new ArrayList<PatientCountForDrugGene>();
		int medCount=1;
		for(PatientCountForDrugGene drugGeneList : getPatientDrugGeneList()){
			PatientCountForDrugGene drugGeneData = new PatientCountForDrugGene();
			drugGeneData.setGeneSymbol("");
			drugGeneData.setMedicineName(drugGeneList.getMedicineName());
			drugGeneData.setPatientCount(drugGeneList.getPatientCount());
			drugGeneData.setAttentionRating(drugGeneList.getAttentionRating());
			
			if(medCount<=10){
				if(!(uniqueMedNamesForGene.contains(drugGeneData))){
					uniqueMedNamesForGene.add(drugGeneData);
					System.out.println("medCount :::::::::"+medCount);
					medCount++;
					
				}
			}
			
		logger.info(" Medicine GeneSymbol::::"+ uniqueMedNamesForGene.size() +":::::"+drugGeneData.getGeneSymbol()+"MedicineName:::::::::"+drugGeneData.getMedicineName()+
				"noOfPatients::::::"+drugGeneData.getPatientCount()+"impact:::::::: "+drugGeneData.getAttentionRating());
		}
		logger.info("Unique Med Name List"+ uniqueMedNamesForGene.size() );
		return new Gson().toJson(uniqueMedNamesForGene);
	}*/

	public void setUniqueMedNamesForGene(
			List<PatientCountForDrugGene> uniqueMedNamesForGene) {
		this.uniqueMedNamesForGene = uniqueMedNamesForGene;
	}

	/*public String getUniqueGeneNamesForGene() {
		uniqueGeneNamesForGene=new ArrayList<PatientCountForDrugGene>();
		for(PatientCountForDrugGene drugGeneList : patientDrugGeneChartList){
			PatientCountForDrugGene drugGeneData = new PatientCountForDrugGene();
			drugGeneData.setGeneSymbol(drugGeneList.getGeneSymbol());
			drugGeneData.setMedicineName("");
			drugGeneData.setPatientCount(drugGeneList.getPatientCount());
			drugGeneData.setAttentionRating(drugGeneList.getAttentionRating());
			if(!uniqueGeneNamesForGene.contains(drugGeneData)){
				uniqueGeneNamesForGene.add(drugGeneData);
			}			
			
			logger.info(" Gene GeneSymbol:::::::::"+drugGeneData.getGeneSymbol()+"MedicineName:::::::::"+drugGeneData.getMedicineName()+
					"noOfPatients::::::"+drugGeneData.getPatientCount()+"impact:::::::: "+drugGeneData.getAttentionRating());
			
		}
		return new Gson().toJson(uniqueGeneNamesForGene);
		
	}*/

	public void setUniqueGeneNamesForGene(
			List<PatientCountForDrugGene> uniqueGeneNamesForGene) {
		this.uniqueGeneNamesForGene = uniqueGeneNamesForGene;
	}

public int total_connectedDoctor=0;




	public int getTotal_connectedDoctor() {
		if(total_connectedDoctor==0)
		{
			int logedUserId=new ContextUtil().getLoginId();
			String logedUserFirstName=new ContextUtil().getLoggerFirstName();
			String logedUserLastName=new ContextUtil().getLoggerLastName();
			total_connectedDoctor=dashBoardService.getTotalDoctor(0,logedUserId,logedUserFirstName,logedUserLastName);
			
		}
	return total_connectedDoctor;
}



public void setTotal_connectedDoctor(int total_connectedDoctor) {
	this.total_connectedDoctor = total_connectedDoctor;
}



	/*public String getNetworkChartList() {
		
		int logedUserId=new ContextUtil().getLoginId();
		String logedUserFirstName=new ContextUtil().getLoggerFirstName();
		String logedUserLastName=new ContextUtil().getLoggerLastName();
		int totalSize=dashBoardService.getTotalDoctor(0,logedUserId,logedUserFirstName,logedUserLastName);
		networkChart.setTotal_connection(totalSize);
		total_connectedDoctor=totalSize;
		setTotal_connectedDoctor(totalSize);
		System.out.println("logedUserFirstName---------------- "+logedUserFirstName+"logedUserLastName-------------------- "+logedUserLastName);
		networkChartList=dashBoardService.findNetworkChart(1,logedUserId,logedUserFirstName,logedUserLastName);
		System.out.println("Total Connected Doctor in network are "+total_connectedDoctor);
		
		return new Gson().toJson(networkChartList);
	}*/



	public void setNetworkChartList(List<NetworkChart> networkChartList) {
		this.networkChartList = networkChartList;
	}
/*
 * *********Method to update BMI & patient weight
 * *******after add/update weight in lab
 * 	@uthor: saurabh
 */
	
	public void updateBmiAndWeightAfterAddingUpdatingWeightInLab(){
		logger.info("=========inside updateBmiAndWeightAfterAddingUpdatingWeightInLab in Dashboard ManageBean===================");
		patientVital = dashBoardService.findParticularPatientVitalDetail(userLoginDetail.getUserId());
		findBodyMassIndex(patientVital.getHeight(), patientVital.getWeight(),this.getUserLoginDetail().getGender());
		
	}

	public String getSpecialityDrugList() {
		logger.info("!!!!!optimization 3 start ="+new Date());
		if(specialityDrugList == null){
			specialityDrugList = new ArrayList<SpecialityDrugCategory>();
			specialityDrugList = dashBoardService.findSpecialityDrugDetails();
		}
		logger.info("!!!!!optimization 3 ends ="+new Date());
		return new Gson().toJson(specialityDrugList);
	}

	public void setSpecialityDrugList(
			List<SpecialityDrugCategory> specialityDrugList) {
		this.specialityDrugList = specialityDrugList;
	}

	public String getDrugCategoryDetailList() {
		logger.info("!!!!!optimization 4 start ="+new Date());
		if(drugCategoryDetailList == null){
			drugCategoryDetailList = new ArrayList<DrugCategoryDetail>();
			drugCategoryDetailList = dashBoardService.findDrugCategoryDetailList();
		}
		for(DrugCategoryDetail data :drugCategoryDetailList){
			//System.out.println("drugtype:::::::::"+data.getMedicineCategory()+"medcount:::::::::::"+data.getDrugCountPerCategory());
		}
		logger.info("!!!!!optimization 4 ends ="+new Date());
		return new Gson().toJson(drugCategoryDetailList);
	}

	public void setDrugCategoryDetailList(
			List<DrugCategoryDetail> drugCategoryDetailList) {
		this.drugCategoryDetailList = drugCategoryDetailList;
	}

	public AnticoagProviderLocation getSelectedUserDetailFromAnticoagChart() {
		if(selectedUserDetailFromAnticoagChart==null){
			selectedUserDetailFromAnticoagChart=new AnticoagProviderLocation();
		}
		return selectedUserDetailFromAnticoagChart;
	}

	public void setSelectedUserDetailFromAnticoagChart(
			AnticoagProviderLocation selectedUserDetailFromAnticoagChart) {
		this.selectedUserDetailFromAnticoagChart = selectedUserDetailFromAnticoagChart;
	}
	
	/*Method to display list of patient based on formulary by Anand S Jha  formularyList patientListforPopup fetchPatientByFormulary levelVal4Formulary */
	public String levelVal4Formulary;
	

	public String getLevelVal4Formulary() {
		return levelVal4Formulary;
	}

	public void setLevelVal4Formulary(String levelVal4Formulary) {
		this.levelVal4Formulary = levelVal4Formulary;
	}

	public List<UserLoginDetail> getPatientListforPopup() {
		
		return patientListforPopup;
	}

	public void setPatientListforPopup(List<UserLoginDetail> patientListforPopup) {
		this.patientListforPopup = patientListforPopup;
	}
	
	/*Method to populate patient list based on formulary selected*/
	public void fetchPatientByFormulary()
	{
		System.out.println("inside fetchPatientByFormulary************");
		String medicinestage="Initiation";
		int procedureType=2;
		double start_result=2.0;
		double end_result=4.0;
		patientListforPopup=new ArrayList<UserLoginDetail>();
		/*System.out.println("Value is coming after click on chart : "+levelVal4Formulary);*/
		
		if(levelVal4Formulary.equalsIgnoreCase("phi") || levelVal4Formulary.equalsIgnoreCase("phm") 
				|| levelVal4Formulary.equalsIgnoreCase("pmi") || levelVal4Formulary.equalsIgnoreCase("pmm")
				|| levelVal4Formulary.equalsIgnoreCase("pli") || levelVal4Formulary.equalsIgnoreCase("plm")
				|| levelVal4Formulary.equalsIgnoreCase("hhi") || levelVal4Formulary.equalsIgnoreCase("hhm") 
				|| levelVal4Formulary.equalsIgnoreCase("hmi") || levelVal4Formulary.equalsIgnoreCase("hmm")
				|| levelVal4Formulary.equalsIgnoreCase("hli") || levelVal4Formulary.equalsIgnoreCase("hlm")
				)
		{
			patientListforPopup= dashBoardService.fetchPatientBySelectedCategory(1,medicinestage,procedureType,start_result,end_result,levelVal4Formulary, new ContextUtil().getProviderId());
			/*System.out.println("Value is coming after click on chart New : "+levelVal4Formulary);*/
		}
		
		else
		{
			Map<String,String>formularyMAPanalyticChart=dashBoardService.formularyMAPtoOldText();
			String formularyType;
			try{
			formularyType=formularyMAPanalyticChart.get(levelVal4Formulary);
			}catch(Exception e){
				formularyType=new String();
				e.printStackTrace();
			}
			patientListforPopup= dashBoardService.fetchPatientByFormulary(formularyType, new ContextUtil().getProviderId());	
		}
		RequestContext context = RequestContext.getCurrentInstance(); 
		context.addCallbackParam("responseCompleted", true);
		/*userLoginDetailList=patientListforPopup;*/
		userLoginDetailList.addAll(patientListforPopup);
		
		
		/*setPatientListforPopup(dashBoardService.fetchPatientByFormulary(levelVal4Formulary,new ContextUtil().getProviderId()));*/
	
		/*System.out.println("result after click on formulary Pie :: " + levelVal4Formulary + " from provider id: " +new ContextUtil().getProviderId()+ "with size "+ patientListforPopup.size());*/
	}
	
	
	
public List<Integer> getClinicAndProviderIdList() {
	int logedUserId=new ContextUtil().getLoginId();
			if(ClinicAndProviderIdList == null){
				ClinicAndProviderIdList= new ArrayList<Integer>();
				ClinicAndProviderIdList=userService.findClinicAndProviderId(logedUserId);
			}
		return ClinicAndProviderIdList;
	}



	public void setClinicAndProviderIdList(List<Integer> clinicAndProviderIdList) {
		ClinicAndProviderIdList = clinicAndProviderIdList;
	}



public List<UserLoginDetail> getRetrivePatientByAnticoagCategoryList() {
	logger.info("!!!!optimization getRetrivePatientByAnticoagCategoryList called in usermanagebean:::"+ new Date());
		if(levelVal==null){
			levelVal="";
		}
		//retrivePatientByAnticoagCategoryList = new ArrayList<UserLoginDetail>();
		System.out.println("Level Value coming from Javascript is in retrivePatientByAnticoagCategoryList in dashboardmanagebean:::"+levelVal);
		List<UserLoginDetail> allDataList = new ArrayList<UserLoginDetail>();
		List<UserLoginDetail> newList = new ArrayList<UserLoginDetail>();
		getClinicAndProviderIdList();
		//int logedUserId=new ContextUtil().getLoginId();
		//System.out.println("::::::::doctor id:::"+logedUserId);
		//ClinicAndProviderIdList= dashBoardService.findClinicAndProviderId(logedUserId);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

		session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
		session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
		session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
		
			if(levelVal.equals("li"))
			{
				System.out.println("inside low in initiation");
				String medicinestage="Initiation";
				int procedureType=2;
				String start_result="0";
				String end_result="0";
				retrivePatientByAnticoagCategoryList=dashBoardService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,levelVal);
				userLoginDetailList=retrivePatientByAnticoagCategoryList;
				System.out.println("retrivePatientByAnticoagCategoryList size$$$$$$$$$$$$ "+retrivePatientByAnticoagCategoryList.size());
			}
			if(levelVal.equals("mi"))
			{
				System.out.println("inside medium in initiation");
				String medicinestage="Initiation";
				int procedureType=2;
				String start_result="0";
				String end_result="0";
				retrivePatientByAnticoagCategoryList=dashBoardService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,levelVal);
				userLoginDetailList=retrivePatientByAnticoagCategoryList;
				System.out.println("retrivePatientByAnticoagCategoryList size$$$$$$$$$$$$ "+retrivePatientByAnticoagCategoryList.size());
			}
			if(levelVal.equals("hi"))
			{
				System.out.println("inside high in initiation");
				String medicinestage="Initiation";
				int procedureType=2;
				
				String start_result="0";
				String end_result="0";
				retrivePatientByAnticoagCategoryList=dashBoardService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,levelVal);
				allDataList.addAll(retrivePatientByAnticoagCategoryList);
				System.out.println("allDataList with high value%%%%%%%%% "+allDataList.size());
				start_result="0";
				end_result="0";
				//retrivePatientByAnticoagCategoryList=dashBoardService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result);
				//allDataList.addAll(retrivePatientByAnticoagCategoryList);
				System.out.println("allDataList with higher value%%%%%%%%% "+allDataList.size());
				System.out.println("retrivePatientByAnticoagCategoryList size$$$$$$$$$$$$ "+retrivePatientByAnticoagCategoryList.size());
			   //List<UserLoginDetail> newList = new ArrayList<UserLoginDetail>();
			 /* 
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
					}*/
					//newList.addAll(numberofpatientformedactionplanforgraterrangeforintiationphase);
					//newList.addAll(retrivePatientByAnticoagCategoryList);
				
				//for(UserLoginDetail uld : allDataList)
				/*List<UserLoginDetail> finalList = new ArrayList<UserLoginDetail>();
				finalList.addAll(allDataList);
				for(int i=0;i<allDataList.size();i++)
				{
					UserLoginDetail uld =allDataList.get(i);
					int id=uld.getUserId();
					
					//for(UserLoginDetail uld1 : allDataList)
					for(int j=i+1;j<allDataList.size();j++)
					{
						UserLoginDetail uld1 =allDataList.get(j);
						int id1=uld1.getUserId();
						System.out.println("id high "+id+" "+id1);
						if(id == id1)
						{
							System.out.println("id ================== "+id+" "+id1);
							if(uld.getResult() < uld1.getResult())
							{
								finalList.remove(uld);
							}
							else
							{
								finalList.remove(uld1);
							}
						}
						
					}
				}
				System.out.println("finalList size&&&&&&&&&&&&&& "+finalList.size());*/
						/*logger.info("size of newList::::withDuplicate:::"+newList.size());
					List<UserLoginDetail> highHigerPatientList = new ArrayList<UserLoginDetail>();
					for(UserLoginDetail uld : newList)
					//for(int j=0;j<newList.size();j++)
					{
						
						//UserLoginDetail uld =newList.get(j);
						//System.out.println("newList.get(j) "+newList.get(j));
						for(UserLoginDetail uld1 : allDataList)
						//for(int i=0;i<allDataList.size();i++)
						{
							System.out.println(uld.getUserId()+" "+uld1.getUserId());
							//UserLoginDetail uld1 =allDataList.get(i);
							//System.out.println("allDataList.get(j) "+allDataList.get(i));
							if(uld.getUserId() != uld1.getUserId())
							{
								System.out.println("uld1.getUserId()%%%%% "+uld1.getUserId());
								UserLoginDetail addDetails = new UserLoginDetail();
								addDetails.setUserId(uld1.getUserId());
								addDetails.setFirstName(uld1.getFirstName());
								addDetails.setLastName(uld1.getLastName());
								addDetails.setMiddleName(uld1.getMiddleName());
								addDetails.setDateOfBirth(uld1.getDateOfBirth());
								addDetails.setResult(uld1.getResult());
								highHigerPatientList.add(addDetails);
								
							}
						}
						//removeDuplicatePatientDataInitiation.add(uld);
					}
					//highHigerPatientList.addAll(newList);
					System.out.println("removeDuplicatePatientDataInitiation size********8 "+highHigerPatientList.size());
					//newList.clear();
					Set removeDuplicatePatientDataInitiation = new HashSet(highHigerPatientList);
					highHigerPatientList.clear();
					highHigerPatientList.addAll(removeDuplicatePatientDataInitiation);
					logger.info("size of newList::::withOut-Duplicate:::"+highHigerPatientList.size());
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
				  
				
				
				System.out.println("new list size##############  in dashboardmanagebean"+retrivePatientByAnticoagCategoryList.size());*/
				
				/*retrivePatientByAnticoagCategoryList.clear();
				System.out.println("size of high and higher in dashboardmanagebean:::::::"+retrivePatientByAnticoagCategoryList.size());
				retrivePatientByAnticoagCategoryList.addAll(finalList);*/
				//System.out.println("final retrivePatientByAnticoagCategoryList size &&&&& "+retrivePatientByAnticoagCategoryList.size());
				//retrivePatientByAnticoagCategoryList.addAll(numberofpatientformedactionplanforgraterrangeforintiationphase);
				userLoginDetailList=retrivePatientByAnticoagCategoryList;
				
			}
			if(levelVal.equals("lm"))
			{				
				System.out.println("inside low");
				String medicinestage=MAINTAINENCE_STAGE;
				int procedureType=2;
				String start_result="0";
				String end_result="0";
				retrivePatientByAnticoagCategoryList=dashBoardService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,levelVal);
				userLoginDetailList=retrivePatientByAnticoagCategoryList;
				System.out.println("retrivePatientByAnticoagCategoryList size$$$$$$$$$$$$ "+retrivePatientByAnticoagCategoryList.size());
			}
			if(levelVal.equals("mm"))
			{
				System.out.println("inside medium");
				String medicinestage=MAINTAINENCE_STAGE;
				int procedureType=2;
				String start_result="0";
				String end_result="0";
				retrivePatientByAnticoagCategoryList=dashBoardService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,levelVal);
				userLoginDetailList=retrivePatientByAnticoagCategoryList;
				System.out.println("retrivePatientByAnticoagCategoryList size$$$$$$$$$$$$ "+retrivePatientByAnticoagCategoryList.size());
			}
			if(levelVal.equals("hm"))
			{
				System.out.println("inside high");
				String medicinestage=MAINTAINENCE_STAGE;
				int procedureType=2;
				String start_result="0";
				String end_result="0";
				retrivePatientByAnticoagCategoryList=dashBoardService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,levelVal);
				allDataList.addAll(retrivePatientByAnticoagCategoryList);
				System.out.println("Maintainence with high list=========="+allDataList.size());
				start_result="0";
				end_result="0";
				//retrivePatientByAnticoagCategoryList=dashBoardService.numberofpatientforanticoagforintiationphase(ClinicAndProviderIdList.get(2),medicinestage,procedureType,start_result,end_result,levelVal);
				//allDataList.addAll(retrivePatientByAnticoagCategoryList);
				System.out.println("Maintainence with higher list=========="+allDataList.size());
				/*System.out.println("high list=========="+retrivePatientByAnticoagCategoryList.size());
			    List<UserLoginDetail> newListMaintenance = new ArrayList<UserLoginDetail>();
			      
			    //newListMaintenance.addAll(numberofpatientformedactionplanforgraterrange);
			    newListMaintenance.addAll(allDataList);
			     logger.info("size of newList::::withDuplicate:::"+newListMaintenance.size());
			    Set removeDuplicatePatientDataMaintenance = new HashSet(newListMaintenance);
			    newListMaintenance.clear();
			    newListMaintenance.addAll(removeDuplicatePatientDataMaintenance);
			    logger.info("size of newList::::withOut-Duplicate:::"+newListMaintenance.size());
			    
			    System.out.println("new list size############## "+newListMaintenance.size());
			    
			    retrivePatientByAnticoagCategoryList.clear();
			    System.out.println("size of high and higher:::::::"+retrivePatientByAnticoagCategoryList.size());*/
				/*List<UserLoginDetail> finalListMaintainence = new ArrayList<UserLoginDetail>();
				finalListMaintainence.addAll(allDataList);
				for(int i=0;i<allDataList.size();i++)
				{
					UserLoginDetail uld =allDataList.get(i);
					int id=uld.getUserId();
					
					//for(UserLoginDetail uld1 : allDataList)
					for(int j=i+1;j<allDataList.size();j++)
					{
						UserLoginDetail uld1 =allDataList.get(j);
						int id1=uld1.getUserId();
						System.out.println("id high "+id+" "+id1);
						if(id == id1)
						{
							System.out.println("id ================== "+id+" "+id1);
							if(uld.getResult() < uld1.getResult())
							{
								finalListMaintainence.remove(uld);
							}
							else
							{
								finalListMaintainence.remove(uld1);
							}
						}
						
					}
				}
				retrivePatientByAnticoagCategoryList.clear();
			    retrivePatientByAnticoagCategoryList.addAll(finalListMaintainence);*/
			    userLoginDetailList=retrivePatientByAnticoagCategoryList;
			    System.out.println("retrivePatientByAnticoagCategoryList size$$$$$$$$$$$$ "+retrivePatientByAnticoagCategoryList.size());
			}
			RequestContext context = RequestContext.getCurrentInstance(); 
			context.addCallbackParam("responseCompleted", true);
			userLoginDetailList=retrivePatientByAnticoagCategoryList;
			Set removeDuplicatePatientDataMaintenance = new HashSet(userLoginDetailList);
			userLoginDetailList.clear();
			userLoginDetailList.addAll(removeDuplicatePatientDataMaintenance);
			System.out.println("retrivePatientByAnticoagCategoryList size+++++++++ "+retrivePatientByAnticoagCategoryList.size());			
			Collections.sort(retrivePatientByAnticoagCategoryList,new Comparator<UserLoginDetail>() {
			   

			
				public int compare(UserLoginDetail o1, UserLoginDetail o2) {
					int retVal=0;
					try{
					if(o1!=null && o2!=null){
						retVal= o2.getResult().compareTo(o1.getResult());
					}
					}catch(NullPointerException nfe){
						logger.error("ERROR: exception while sorting list"+nfe.getMessage());
						nfe.printStackTrace();
					}
					return retVal;
					
				}
			 });
			System.out.println("retrivePatientByAnticoagCategoryList size########### "+retrivePatientByAnticoagCategoryList.size());
			logger.info("!!!!optimization getRetrivePatientByAnticoagCategoryList ENDSSSSSSSSSSSS in usermanagebean:::"+ new Date());
			return retrivePatientByAnticoagCategoryList ;
				
	}

public void setRetrivePatientByAnticoagCategoryList(
		List<UserLoginDetail> retrivePatientByAnticoagCategoryList) {
	this.retrivePatientByAnticoagCategoryList = retrivePatientByAnticoagCategoryList;
}





	//Method to find out the Anticoag Patient List based on category by Anand S Jha
	public String fetchPatientByAnticoag()
	{
		//getNumberofpatientformedactionplanforhigherendofpatientforintiationphase();
		System.out.println("Level Value coming from Javascript in dashboardmanagebean:::"+levelVal);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.setAttribute("levelVal1", levelVal);
		getRetrivePatientByAnticoagCategoryList();
		 
		//getUniqueResultList();
		
		RequestContext.getCurrentInstance().execute("PF('dlgForAnticoagClinicStatusLow').show()");
		//System.out.println("   "+session.getAttribute("levelVal1"));
		//getNumberOfPatientforanticoagforintiationphase();
		levelVal=null;
		
		System.out.println("This is size of the list in dashboardmanagebean-----"+getRetrivePatientByAnticoagCategoryList().size());
		
		return null;
		
	}
 
	public String getLevelVal() {
		return levelVal;
	}


	public void setLevelVal(String levelVal) {
		this.levelVal = levelVal;
	}
	
	public List<Integer> getMaxUserIdFromUserDetails(){
	/*	if(maxUserIdFromUserDetails ==null){*/
          maxUserIdFromUserDetails = new ArrayList<Integer>();
          maxUserIdFromUserDetails=dashBoardService.getMaxUserId();
		/*}*/
		return maxUserIdFromUserDetails;
	}

	public void setMaxUserIdFromUserDetails(List<Integer> maxUserIdFromUserDetails) {
		this.maxUserIdFromUserDetails = maxUserIdFromUserDetails;
	}

	public UserLoginDetail getUserLoginDetailsNewPatient() {
		return userLoginDetailsNewPatient;
	}

	public void setUserLoginDetailsNewPatient(
			UserLoginDetail userLoginDetailsNewPatient) {
		this.userLoginDetailsNewPatient = userLoginDetailsNewPatient;
	}

	public PatientVital getPatientVitalNewPatient() {
		return patientVitalNewPatient;
	}

	public void setPatientVitalNewPatient(PatientVital patientVitalNewPatient) {
		this.patientVitalNewPatient = patientVitalNewPatient;
	}
	
	int userIdForNewPatient=0;
	
/*@author: vinod
 * for add patient
 * modify by saurabh for ticket 1057
 * 	
 */
	public void addPateint() throws MessagingException{
		logger.info("addpateint method starts in dashboardmanage:::::::");
		  //userLoginDetail.setUserId(userLoginDetail.getId());
		boolean checksocialSecurityNumber=false;		
		for(Integer userId:getMaxUserIdFromUserDetails()){
			userIdForNewPatient = userId+1;
		}
		if (StringUtils.isBlank((userLoginDetailsNewPatient.getSocialSecurityNumber()))) {
			  userLoginDetailsNewPatient.setSocialSecurityNumber(null);
		  }
		logger.info("user ented email from Ui:::::::"+userLoginDetailsNewPatient.getEmail());
		boolean checkEmail=	dashBoardService.checkForEmailExist(userLoginDetailsNewPatient.getEmail());
		if (StringUtils.isNotBlank(userLoginDetailsNewPatient.getSocialSecurityNumber())) {  
			checksocialSecurityNumber=	dashBoardService.checkForsocialSecurityNumber(userLoginDetailsNewPatient.getSocialSecurityNumber());
		}
		//isCheckForEmailExist();
		logger.info("status of socialSecurityNumber exists:::::::::"+checksocialSecurityNumber);
		if(!checksocialSecurityNumber){
			//userLoginDetailsNewPatient.setId(id)
		 loginSecurity.setId(userIdForNewPatient);
		 loginSecurity.setRole(3);
		 loginSecurity.setUserId(userLoginDetailsNewPatient.getFirstName()+userIdForNewPatient);
		 loginSecurity.setStatus(true);
		 loginSecurity.setCreatedBy(new ContextUtil().getLoginId());
		 loginSecurity.setCreatedDate(new Date());
		 loginSecurity.setPassword(passwordEncoderGeneratorServiceImpl.encryptPassword(userLoginDetailsNewPatient.getFirstName()+userIdForNewPatient));
		 userLoginDetailsNewPatient.setUserId(userIdForNewPatient);
		 userLoginDetailsNewPatient.setStatus(true);
		 userLoginDetailsNewPatient.setCountry(userLoginDetailsNewPatient.getCountry().toUpperCase());
		 userLoginDetailsNewPatient.setState(userLoginDetailsNewPatient.getState().toUpperCase());
		  employerDetails.setPatientId(userLoginDetailsNewPatient.getUserId());
		  patientVitalNewPatient.setPatientIdForVital(userLoginDetailsNewPatient.getUserId());
		  patientVitalNewPatient.setRace(userLoginDetailsNewPatient.getRace());
		  patientGuarantorNewPatient.setUserID(userIdForNewPatient);
		  patientGuarantorNewPatient.setGuarantorID(1);
		  if(StringUtils.isBlank(patientVitalNewPatient.getHeightinfeet())){
			  patientVitalNewPatient.setHeightinfeet("0");
		  }
		  if(StringUtils.isBlank(patientVitalNewPatient.getHeightininches())){
			  patientVitalNewPatient.setHeightininches("00");
		  }
		  patientVitalNewPatient.setHeight(patientVitalNewPatient.getHeightinfeet()+"."+patientVitalNewPatient.getHeightininches());
		  if(patientVitalNewPatient.getWeightUnits().equalsIgnoreCase("lbs")){
			  double massLB = patientVitalNewPatient.getWeight() / 2.2046;
			  String massLbRound = new DecimalFormat("#.#####").format(massLB);
			  patientVitalNewPatient.setWeight(Double.parseDouble(massLbRound));
		  }
		  
		  if (patientVitalNewPatient.getWeight()==null) {
			  patientVitalNewPatient.setWeight(0.0);
			}
		  patientVitalNewPatient.setMeasurementDate(new Date());
		  patientProviderDetail.setPatientId(userLoginDetailsNewPatient.getUserId());
		  patientProviderDetail.setProviderId(new ContextUtil().getProviderId());
		  patientProviderDetail.setProviderLocationId(new ContextUtil().getProviderLocationId());
		  //pateintAllergyData.setPatientId(userLoginDetail.getUserId()); 
		  //newPatientAllergyList.add(pateintAllergyData);
		 boolean checkSaveStatus=dashBoardService.savepateintData(loginSecurity, userLoginDetailsNewPatient,employerDetails,patientVitalNewPatient,newPatientAllergyList,patientProviderDetail,
				 newPateintDiagosesList,patientProviderClinicListNewPatient,patientInsuranceDetailList,patientGuarantorNewPatient);
		 //RequestContext.getCurrentInstance().addCallbackParam("validationFailed", false);
		 logger.info("saving status of pateint data:::::::"+checkSaveStatus);
		 patientGuarantorNewPatient = new PatientGuarantor();
//-------by saurabh		

		 /*loginSecurity=new LoginSecurity();
		 userLoginDetailsNewPatient= new UserLoginDetail();
		 employerDetails=new EmployerDetails();
		 patientVitalNewPatient=new PatientVital();
		 newPatientAllergyList=new ArrayList<PatientAllergy>();
		 patientProviderDetail=new PatientProvider();
		 newPateintDiagosesList= new ArrayList<PatientDiagnosesDetails>();
		 patientProviderClinicListNewPatient=new ArrayList<PatientProviderClinic>();
		 patientInsuranceDetailList=new ArrayList<UserInsuranceDetails>();*/
		 if (!checkSaveStatus) {
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sorry,Your Process Has Been Failed! Please Try Again", ""));
			 RequestContext.getCurrentInstance().addCallbackParam("processFailed", true);
		 }
		 else{
			 System.out.println("saving status of pateint data:::::::"+checkSaveStatus);
	//-------for sending notificatication mail
			/* if (StringUtils.isNotBlank(userLoginDetailsNewPatient.getEmail())) {
					String mailSubject="Welcome to Clinakos";
					String mailMsg="<table bgcolor=WhiteSmoke border=0 width=100%><tr><td><br>Welcome! &nbsp;</br>";
						   mailMsg="Hello  <b>"+userLoginDetailsNewPatient.getFirstName()+"</b>,  <br></br>";
						   mailMsg+="<br></br>Thank you for registering with Clinakos.Please use following credentials for login.<br></br>";
						   mailMsg+="<br></br>UserName:"+loginSecurity.getUserId()+"<br></br>";
						   mailMsg+="Password:"+loginSecurity.getUserId()+"<br></br>";
						   mailMsg+="<br></br><a href=\"https://care.clinakos.com/clinakos/\">Click </a>here to log in to your new account.<br></br>";
						   mailMsg+="<br></br>*******It is a system generated mail.Please do not reply.********</b></td></tr></table>";
						   new NotificationUtil().sendMail(userLoginDetailsNewPatient.getEmail().trim(),mailSubject,mailMsg);
			    }*/
		   RequestContext.getCurrentInstance().execute("pateintAddedDialog.show()");
		 }   
		}
		else{
			 System.out.println("inside outer elase for saving status of pateint data:::::::"+checksocialSecurityNumber);
		   RequestContext.getCurrentInstance().execute("SocialSecurityNumberDialog.show()");
		 } 
	}
	
	public void changePateintWeight(){
		logger.info(" seleted weight unit"+patientVitalNewPatient.getWeightUnits());
		 
		if(patientVitalNewPatient.getWeightUnits().equalsIgnoreCase("Kg")){
			//calculateCreatimineClearance.setWeight(calculateCreatimineClearance.getSimpleWeight());
			double massLB = patientVitalNewPatient.getWeight() * 2.2046;
			//double massLbRound = Math.round(massLB * 100.0) / 100.0;
			String massLbRound = new DecimalFormat("#.#####").format(massLB);
			patientVitalNewPatient.setConvertWeight(Double.parseDouble(massLbRound));
			patientVitalNewPatient.setUpdateUnites("(Lbs)");
		}
		else
		{
			double massLB = patientVitalNewPatient.getWeight() / 2.2046;
			//double massLbRound = Math.round(massLB * 100.0) / 100.0;
			String massLbRound = new DecimalFormat("#.#####").format(massLB);
			patientVitalNewPatient.setConvertWeight(Double.parseDouble(massLbRound));
			patientVitalNewPatient.setUpdateUnites("(Kg)");
		}
		
	}
	

	public List<PatientAllergy> getNewPatientAllergyList() {
		return newPatientAllergyList;
	}

	public void setNewPatientAllergyList(List<PatientAllergy> newPatientAllergyList) {
		this.newPatientAllergyList = newPatientAllergyList;
	}

	public EmployerDetails getEmployerDetails() {
		return employerDetails;
	}

	public void setEmployerDetails(EmployerDetails employerDetails) {
		this.employerDetails = employerDetails;
	}
	
	
	public void addNewPatientAllergy(){
		FacesContext context = FacesContext	.getCurrentInstance();
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		patientMedicineManageBean.addAllergyForNewPatient();
		newPatientAllergyList=patientMedicineManageBean.getAllergyListForNewPatient();
		patientMedicineManageBean.setAllergyMasterList(null);
	}

	public PatientAllergy getPateintAllergyData() {
		return pateintAllergyData;
	}

	public void setPateintAllergyData(PatientAllergy pateintAllergyData) {
		this.pateintAllergyData = pateintAllergyData;
	}

	public boolean isCheckForEmailExist() {
		return checkForEmailExist;
	}

	public void setCheckForEmailExist(boolean checkForEmailExist) {
		this.checkForEmailExist = checkForEmailExist;
	}
    
	public void addNewPatientDiagnosesIcd10(){
		FacesContext context = FacesContext	.getCurrentInstance();
		PatientManageBean patientManageBean=(PatientManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientManageBean");
		patientManageBean.findDiagnosisNameNewPatient();
		//patientManageBean.setIcd9DiagnosisNameForNewPatient();
		newPateintDiagosesList=patientManageBean.getPateintDiagnosesForNewPatientList();
		for (PatientDiagnosesDetails iterable: newPateintDiagosesList) {
			logger.info("diaName::::"+iterable.getCode()+"diaId:::"+iterable.getIcdId());
		}
		patientManageBean.setDignosisdetailsList(null);
	}
    
	public void addNewPatientDiagnosesIcd9() {
		FacesContext context = FacesContext	.getCurrentInstance();
		PatientManageBean patientManageBean=(PatientManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientManageBean");
		patientManageBean.setIcd9DiagnosisNameForNewPatient();
		newPateintDiagosesList=patientManageBean.getPateintDiagnosesForNewPatientList();
		patientManageBean.setDignosisdetailsList(null);
	}
	
	public List<PatientDiagnosesDetails> getNewPateintDiagosesList() {
		return newPateintDiagosesList;
	}

	public void setNewPateintDiagosesList(
			List<PatientDiagnosesDetails> newPateintDiagosesList) {
		this.newPateintDiagosesList = newPateintDiagosesList;
	}

	public List<ClinicMaster> getClinicMasterDetailList() {
		return clinicMasterDetailList;
	}

	public void setClinicMasterDetailList(List<ClinicMaster> clinicMasterDetailList) {
		this.clinicMasterDetailList = clinicMasterDetailList;
	}

	public ClinicMaster getClinicMasterDetail() {
		return clinicMasterDetail;
	}

	public void setClinicMasterDetail(ClinicMaster clinicMasterDetail) {
		this.clinicMasterDetail = clinicMasterDetail;
	}

	public String getUserInputForAddClinic() {
		return userInputForAddClinic;
	}

	public void setUserInputForAddClinic(String userInputForAddClinic) {
		this.userInputForAddClinic = userInputForAddClinic;
	}
   
	public void searchMasterClinic(){
		logger.info("userInput::data::::::"+getUserInputForAddClinic());
		if(userInputForAddClinic == null){
			userInputForAddClinic="";
		}
		clinicMasterDetailList = new ArrayList<ClinicMaster>();
		clinicMasterDetailList = dashBoardService.findClinicMasterDetail(getUserInputForAddClinic());
		for(ClinicMaster clinic:clinicMasterDetailList){
			logger.info("clinicName:::::"+clinic.getClinicName());
		}
	}

	public DataTable getAddClinicMasterDataNewPatient() {
		return addClinicMasterDataNewPatient;
	}

	public void setAddClinicMasterDataNewPatient(
			DataTable addClinicMasterDataNewPatient) {
		this.addClinicMasterDataNewPatient = addClinicMasterDataNewPatient;
	}
	public void addClinicsForNewPatient() {
		logger.info("addClinicsForNewPatient:::::method starts::::::::");
		ClinicMaster clinicRowData = (ClinicMaster) getAddClinicMasterDataNewPatient().getRowData();
		logger.info("clinicName::::::"+clinicRowData.getClinicName());
		ClinicProvider clinicData = new ClinicProvider();
		clinicData.setProviderId(new ContextUtil().getProviderId());
		clinicData.setClinicId(clinicRowData.getId());
		clinicData.setProviderLocationId(new ContextUtil().getProviderLocationId());
 	      if(!getClinicProviderDetailList().contains(clinicData)){
			//clinicProviderDetailList.add(clinicData);
			dashBoardService.saveClinicProviderData(clinicData);
		}
		boolean check = false;
		for(PatientProviderClinic clinicdata:getPatientProviderClinicListNewPatient()){
			if(clinicdata.getClinicName().equalsIgnoreCase(clinicRowData.getClinicName()))
				check=true;
		}
			for(ClinicProvider clinicdata:getClinicProviderDetailList()){
				logger.info("clinicdata:::::"+clinicdata.getClinicId()+"clinicRowData::::::"+clinicRowData.getId());
				if(clinicdata.getClinicId() == (clinicRowData.getId()) && clinicdata.getProviderId()== new ContextUtil().getProviderId() 
						&& clinicdata.getProviderLocationId()== new ContextUtil().getProviderLocationId())
				{
					patientProviderClinicDetail.setClinicId(clinicRowData.getId());
					patientProviderClinicDetail.setClinicName(clinicRowData.getClinicName());
					patientProviderClinicDetail.setClinicProviderId(clinicdata.getId());
					//patientProviderClinicDetail.setPatientId(userLoginDetail.getUserId());
					logger.info(":::::clinicName:::::"+patientProviderClinicDetail.getClinicName());
				}
			}
			if(!check){
				if(patientProviderClinicDetail.getClinicName()!= null){
			patientProviderClinicListNewPatient.add(patientProviderClinicDetail);
				}
			patientProviderClinicDetail = new PatientProviderClinic();
			setUserInputForAddClinic(null);
			clinicMasterDetailList=null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Clinic Added Successfully", ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Already There", ""));
		    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		patientProviderClinicDetail = new PatientProviderClinic();
		clinicMasterDetailList=null;
 	}

	public List<ClinicProvider> getClinicProviderDetailList() {
		clinicProviderDetailList = new ArrayList<ClinicProvider>();
		clinicProviderDetailList =dashBoardService.findclinicProviderDetail(new ContextUtil().getProviderId());
		return clinicProviderDetailList;
	}

	public void setClinicProviderDetailList(
			List<ClinicProvider> clinicProviderDetailList) {
		this.clinicProviderDetailList = clinicProviderDetailList;
	}

	public PatientProviderClinic getPatientProviderClinicDetail() {
		return patientProviderClinicDetail;
	}

	public void setPatientProviderClinicDetail(
			PatientProviderClinic patientProviderClinicDetail) {
		this.patientProviderClinicDetail = patientProviderClinicDetail;
	}

	public List<PatientProviderClinic> getPatientProviderClinicListNewPatient() {
		return patientProviderClinicListNewPatient;
	}

	public void setPatientProviderClinicListNewPatient(
			List<PatientProviderClinic> patientProviderClinicListNewPatient) {
		this.patientProviderClinicListNewPatient = patientProviderClinicListNewPatient;
	}

	public String getUserInputForAddInsurance() {
		return userInputForAddInsurance;
	}

	public void setUserInputForAddInsurance(String userInputForAddInsurance) {
		this.userInputForAddInsurance = userInputForAddInsurance;
	}

	public List<InsuranceCompanies> getMasterInsuranceDetailList() {
		return masterInsuranceDetailList;
	}

	public void setMasterInsuranceDetailList(
			List<InsuranceCompanies> masterInsuranceDetailList) {
		this.masterInsuranceDetailList = masterInsuranceDetailList;
	}
    
	public void searchForpatientInsurance(){
		if(userInputForAddInsurance == null){
			userInputForAddInsurance="";
		}
		masterInsuranceDetailList = new ArrayList<InsuranceCompanies>();
		logger.info("user Input data For Search Insurance:::::::::"+userInputForAddInsurance);
		masterInsuranceDetailList=dashBoardService.fetchInsuranceDetail(userInputForAddInsurance);
	}
	
	public void addInsuranceForNewpatient(){
		logger.info("addInsuranceForNewpatient method starts in dashboardmanagebean:::::::::");
		InsuranceCompanies selectedInsurance = (InsuranceCompanies) getMasterInsuranceTableBinding().getRowData();
		logger.info("selected insurance id::::::"+selectedInsurance.getId()+" "+selectedInsurance.getAddress1()+" "+selectedInsurance.getCompanyName());
		boolean checkStatus=false;
		for(UserInsuranceDetails userInsurance:getPatientInsuranceDetailList()){
			if(userInsurance.getInsuranceId()==selectedInsurance.getHealPlanDetailID())
				checkStatus=true;
		}
		if(!checkStatus){
			userInsuranceDetailAdd.setInsuranceId(selectedInsurance.getId());
			logger.info("selectInsuranceLevel::::::value"+selectInsuranceLevel);
			if(selectInsuranceLevel == 1){
				userInsuranceDetailAdd.setPrimaryInsurance("yes");
			}
			else{
				userInsuranceDetailAdd.setPrimaryInsurance("no");
			}
			if(selectInsuranceLevel == 2){
				userInsuranceDetailAdd.setSecondaryInsurance("yes");
			}
			else{
				userInsuranceDetailAdd.setSecondaryInsurance("no");
			}
			if(selectInsuranceLevel == 3){
				userInsuranceDetailAdd.setTertiaryInsurance("yes");
			}
			else{
				userInsuranceDetailAdd.setTertiaryInsurance("no");
			}
			userInsuranceDetailAdd.setPayorName(selectedInsurance.getCompanyName());
			userInsuranceDetailAdd.setPayorAddress1(selectedInsurance.getAddress1());
			userInsuranceDetailAdd.setPayorAddress2(selectedInsurance.getAddress2());
			userInsuranceDetailAdd.setPayorCity(selectedInsurance.getCity());
			userInsuranceDetailAdd.setPayorState(selectedInsurance.getState());
			userInsuranceDetailAdd.setPayorCountry(selectedInsurance.getCountry());
			userInsuranceDetailAdd.setPayorZip(selectedInsurance.getZip());
			userInsuranceDetailAdd.setPayorZip4(selectedInsurance.getZip4());
			//patientInsuranceDetailList.add(userInsuranceDetail);
			//patientInsuranceDetailList.add(userInsuranceDetail);
			userInsuranceDetailAdd.setCompanyName(selectedInsurance.getCompanyName());
			Random r = new Random();
			int payorAndInsureIDRandomNumber=0;
			int payorAndGroupNumberRandomNumber=0;
			for(int i=0;i<1;i++)
			{
				payorAndInsureIDRandomNumber=r.nextInt(1000);
				payorAndGroupNumberRandomNumber=r.nextInt(1000);
			}
			userInsuranceDetailAdd.setPayorAndInsuredID("hsi-"+payorAndInsureIDRandomNumber);
			userInsuranceDetailAdd.setPayorGroupNumber(""+payorAndGroupNumberRandomNumber);
			pateintInsurance = new UserInsuranceDetails();
			masterInsuranceDetailList = null;
			setUserInputForAddInsurance(null);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Insurance Added Successfully", ""));
			/*for(UserInsuranceDetails uid : patientInsuranceDetailList){
			System.out.println("patientInsuranceDetailList values****** "+uid.getInsuredFirstName());
			}*/
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Already There", ""));
		}
	}
	
	public void addInsurance()
	{
		//patientInsuranceDetailList = new ArrayList<UserInsuranceDetails>();
		
		logger.info("get insured DOB "+userInsuranceDetailAdd.getInsuredDOB());
		if(userInsuranceDetailAdd.getInsuredDOB()==null){
		//	RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Fill All Mandatory Fields", ""));
			RequestContext.getCurrentInstance().addCallbackParam("isInsuranceValidationSuccess", false);
		}else{
			patientInsuranceDetailList.add(userInsuranceDetailAdd);
			RequestContext.getCurrentInstance().addCallbackParam("isInsuranceValidationSuccess", true);
		}
		userInsuranceDetailAdd = new UserInsuranceDetails();
		for(UserInsuranceDetails uid : patientInsuranceDetailList){
			System.out.println("patientInsuranceDetailList values****** "+uid.getInsuredFirstName());
			System.out.println("patientInsuranceDetailList values****** "+uid.getInsuredLastName());
			System.out.println("patientInsuranceDetailList values****** "+uid.getInsuredZip());
		}
		
	}

	public DataTable getMasterInsuranceTableBinding() {
		return masterInsuranceTableBinding;
	}

	public void setMasterInsuranceTableBinding(DataTable masterInsuranceTableBinding) {
		this.masterInsuranceTableBinding = masterInsuranceTableBinding;
	}

	public List<UserInsuranceDetails> getPatientInsuranceDetailList() {
		return patientInsuranceDetailList;
	}

	public void setPatientInsuranceDetailList(
			List<UserInsuranceDetails> patientInsuranceDetailList) {
		this.patientInsuranceDetailList = patientInsuranceDetailList;
	}

	public UserInsuranceDetails getPateintInsurance() {
		return pateintInsurance;
	}

	public void setPateintInsurance(UserInsuranceDetails pateintInsurance) {
		this.pateintInsurance = pateintInsurance;
	}

	public PatientProvider getPatientProviderDetail() {
		return patientProviderDetail;
	}

	public void setPatientProviderDetail(PatientProvider patientProviderDetail) {
		this.patientProviderDetail = patientProviderDetail;
	}
	public void onRowSelectForNewlyAddedPatient() throws IOException {  
		System.out.print("hello");
//		userLoginDetail=(UserLoginDetail) getBindUserDataTable().getRowData();
		userLoginDetail=new UserLoginDetail();
		userLoginDetail=userLoginDetailsNewPatient;
		userLoginDetailList.add(userLoginDetailsNewPatient);
		//getUserLoginDetailList();
		for(UserLoginDetail userLogin:userLoginDetailList){
			logger.info("userLoginid::::::"+userLogin.getUserId());
			logger.info("userIdForNewPatient::::::"+userIdForNewPatient);
			if(userLogin.getUserId() == userIdForNewPatient){
				userLoginDetail.setId(userLogin.getId());
			}
		}
		logger.info("patientSearchData method in usermanagebean startted::::"+userLoginDetail.getId());
		
		/*UserLoginDetail selectedRowId = (UserLoginDetail) getPatientSearchTableObj()
				.getRowData();*/
		/*System.out.println("::::::umesh row patientSearchData:::" + userLoginDetail.getFirstName()+userLoginDetail.getId());*/
		this.userLoginDetail.setId(userLoginDetail.getId());

		// ---------------------To enable menu under patient tab
		setShowPatientSubmenu(false);
		// this.userLoginDetail.setLoginDetailId(selectedRowId.getLoginDetailId());

		/*
		 * set patient id and patient name in session for further use by gopal
		 */
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);

		session.setAttribute("patientId", userLoginDetail.getUserId());
		// session.setAttribute("PatientLoginId",
		// userLoginDetail.getLoginDetailId());
		System.out.println("::::::check row patientSearchData:::" + userLoginDetail.getUserId());
		FacesContext context = FacesContext.getCurrentInstance();

		LabManageBean labManageBean = (LabManageBean) context.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null, "labManageBean");
		// dctrBean=new DoctorBean();
		labManageBean.setProcedureResultListForCurrentLab(null);
		labManageBean.setProcedureResultHistoryList(null);
		labManageBean.setLoincKeyMasterList(null);
		labManageBean.setProcedureResultHistoryListForChart(null);
		labManageBean.resetValue();
		PatientMedicineManageBean patientMedicineManageBean = (PatientMedicineManageBean) context
				.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null,
						"patientMedicineManageBean");
		// patientMedicineManageBean.setPatientMedicationDataList(null);
		patientMedicineManageBean.reset();
		
		
		PatientManageBean patientManageBean = (PatientManageBean) context
				.getApplication()
				.getELResolver()
				.getValue(context.getELContext(), null,
						"patientManageBean");
		// patientMedicineManageBean.setPatientMedicationDataList(null);
		patientManageBean.resetVAlue();
		setShowPatientMenu(true);
	//----added by vinod for allignment of kidney/liver function
			setKidneyLabNameList(null);
			setKidneyLabResultList(null);
			setKidneyLabUnitList(null);
			
			setLiverLabNameList(null);
			setLiverLabResultList(null);
			setLiverLabUnitList(null);
			
			 kidneyLabNameList = new ArrayList<String>();
			 kidneyLabResultList =  new ArrayList<Double>();
			 kidneyLabUnitList = new ArrayList<String>();
			
			 liverLabNameList = new ArrayList<String>();
			 liverLabResultList = new ArrayList<Double>();
			 liverLabUnitList = new ArrayList<String>();
			/* System.out.println("::::::check row patientSearchData:::12--" + userLoginDetail.getUserId());*/
		for (UserLoginDetail user : userLoginDetailList) {
			System.out.println("11111111 inside for each UserLoginDetail id="+user.getId()+":userLoginDetail.getId="+userLoginDetail.getId()
					+":userId="+user.getUserId()+":userLoginDetail.getUId="+userLoginDetail.getUserId());
			if (user.getUserId() == userLoginDetail.getUserId()) {
				
				this.userLoginDetail = user;

				if(userLoginDetail.getDateOfBirth()!=null){
					 Years years = Years.yearsBetween(new LocalDate(userLoginDetail.getDateOfBirth()), new LocalDate());
					Integer patientAge=years.getYears();
					logger.info("Patient Age : onRowSelectForNewlyAddedPatient"+patientAge+" Birth onRowSelectForNewlyAddedPatient "+userLoginDetail.getDateOfBirth());
					userLoginDetail.setAge(patientAge);
				}

			}
		}

		insuranceNameList = dashBoardService
				.userInsuranceDetails(userLoginDetail.getUserId()); //get Insurance Data For Patient Detail Header Page 
		patientProviderClinicList = dashBoardService
				.findPatientProviderClinicList(userLoginDetail.getUserId());

		diagnosisList = dashBoardService
				.findDiagnosisDetailList(userLoginDetail.getUserId());

		patientVital = dashBoardService
				.findParticularPatientVitalDetail(userLoginDetail.getUserId());
		patientVital.setWeightInKG(patientVital.getWeight());
		
		patientEmployerDetails = dashBoardService
			    .findPatientEmployerDetails(userLoginDetail.getUserId());
		patientGuarantor=dashBoardService.getPatientGuarantor(userLoginDetail.getUserId());
		//Added By Anjani For PBM
		pbmNameList =dashBoardService.getPbmNameData(userLoginDetail.getUserId()); //Pbm Name List For Patient Detail Header page 
		Collections.replaceAll(pbmNameList,"SURESCRIPTS LLC","XYZ");
		// find liver function, kidney function value...
		viewFunctionDetailList = dashBoardService
				.findViewFunctionDetailList(userLoginDetail.getUserId());

		lovTypeList = dashBoardService.findLovTypeListInDetail();

		kidneyFunctionDetailList =  findFunctionDetailList(KIDNEYFUNCTION,
				lovTypeList, viewFunctionDetailList);
		liverFunctionList = findFunctionDetailList(LIVERFUNCTION, lovTypeList,
				viewFunctionDetailList);
		
	//----added by vinod for allignment of kidney/liver function
		
		kidneyLabNameList = findFunctionLabName(KIDNEYFUNCTION, lovTypeList, viewFunctionDetailList);//Get All Lab  Name of Kidney Function  for PatientDetailHeader.jsf
		kidneyLabResultList = findFunctionLabResult(kidneyLabNameList, viewFunctionDetailList); //Get All Lab  Name of Kidney Function  for PatientDetailHeader.jsf
		kidneyLabUnitList = findFunctionLabUnit(kidneyLabNameList,  viewFunctionDetailList);//Get All Lab  unit  of Kidney function Lab  for PatientDetailHeader.jsf 
		
		liverLabNameList = findFunctionLabName(LIVERFUNCTION, lovTypeList, viewFunctionDetailList); //Get All Lab  Name of Liver for PatientDetailHeader.jsf  
		liverLabResultList = findFunctionLabResult(liverLabNameList,  viewFunctionDetailList); //Get All Lab  Result  of Liver Function  for PatientDetailHeader.jsf 
		liverLabUnitList = findFunctionLabUnit(liverLabNameList,  viewFunctionDetailList); //Get All Lab  Result unit   of Liver Function  for PatientDetailHeader.jsf 
		findBodyMassIndex(patientVital.getHeight(), patientVital.getWeight(),this.getUserLoginDetail().getGender());
		tabIndex = 0;
		accPanelIndex = 1;
		calculateCrCl=null;
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		ec.redirect("diagonoses.jsf");
		userLoginDetailsNewPatient = new UserLoginDetail();
		 employerDetails = new EmployerDetails();
		 patientVitalNewPatient = new PatientVital();
		 newPatientAllergyList = null;
		 newPateintDiagosesList = null;
		 patientProviderClinicListNewPatient=null;
		 patientProviderDetail = new PatientProvider();
		 patientInsuranceDetailList =null;
	}

	public int getSelectInsuranceLevel() {
		return selectInsuranceLevel;
	}

	public void setSelectInsuranceLevel(int selectInsuranceLevel) {
		this.selectInsuranceLevel = selectInsuranceLevel;
	} 
	public void addInsuranceLevel(){
		logger.info("selectInsuranceLevel::::::"+selectInsuranceLevel);
		/*HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("level", selectInsuranceLevel);*/
		RequestContext.getCurrentInstance().execute("selectInsurancePopup.show()");
	}
	
	/**
	 * doctorProfile.jsf/doctorheader2.xhtml
	 * Add new Patient On Patient  search Time 
	 * If Patient was not available on Global 
	 * Search 
	 */
	public void globalAddNewPatient(){
		logger.info("globalAddNewPatient method starts in dashboardManageBean::::::");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.removeAttribute("patientId");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("addPatient.jsf");
		} catch (Exception e) {
			logger.error("error at globalAddNewPatient in dashboardManageBean::::");
			e.printStackTrace();
		}
	}
	
	public void resetPatientMasterDataOnCancel(){
		logger.info("resetPatientMasterDataOnCancel in dashBoardManage Bean:::::::");
		setUserInputForAddClinic(null);
		setUserInputForAddInsurance(null);
		masterInsuranceDetailList= null;
		clinicMasterDetailList=null;
	}
	
	public void resetAddPatientDataOnAddAnotherButton() {
		logger.info("resetAddPatientDataOnAddAnotherButton in dashBoardManage Bean:::::::");
		loginSecurity=new LoginSecurity();
		userLoginDetailsNewPatient = new UserLoginDetail();
		 employerDetails = new EmployerDetails();
		 patientVitalNewPatient = new PatientVital();
		 newPatientAllergyList = null;
		 newPateintDiagosesList = null;
		 patientProviderClinicListNewPatient=null;
		 patientProviderDetail = new PatientProvider();
		 patientInsuranceDetailList =null;
		 try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("addPatient.jsf");
			} catch (Exception e) {
				logger.error("error at resetAddPatientDataOnAddAnotherButton in dashboardManageBean::::");
				e.printStackTrace();
			}
	}
	
	int insuranceRowId;
	public void editDeleteRowForInsurance(){
		logger.info("editDeleteRowForInsurance in dashBoardManageBean:::::");
		pateintInsurance = new UserInsuranceDetails();
		pateintInsurance = (UserInsuranceDetails) getNewPatientInsuranceBinding().getRowData();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params=fc.getExternalContext().getRequestParameterMap();
		insuranceRowId = Integer.parseInt(params.get("rowForInsurance"));
		
	}
  
	public void deleteInsurance(){
		logger.info("deleteInsurance method starts::::dashBoardManageBean:::");
		logger.info("row number for delete insurance:::::::::"+insuranceRowId);
		logger.info("patientInsuranceDetailList size:::::::::"+patientInsuranceDetailList.size());
		patientInsuranceDetailList.remove(insuranceRowId);
		logger.info("patientInsuranceDetailList size:::::::::"+patientInsuranceDetailList.size());
		pateintInsurance = new UserInsuranceDetails();
	}
	
	public DataTable getNewPatientInsuranceBinding() {
		return newPatientInsuranceBinding;
	}

	public void setNewPatientInsuranceBinding(DataTable newPatientInsuranceBinding) {
		this.newPatientInsuranceBinding = newPatientInsuranceBinding;
	}

	public DataTable getClinicRowDataBinding() {
		return clinicRowDataBinding;
	}

	public void setClinicRowDataBinding(DataTable clinicRowDataBinding) {
		this.clinicRowDataBinding = clinicRowDataBinding;
	}
	
	
	int clinicRowId;
	public void editDeleteClinicData(){
		logger.info("editDeleteClinicData method starts in dashBoardManageBean::::::::::::::::");
		patientProviderClinicDetail = new PatientProviderClinic();
		patientProviderClinicDetail=(PatientProviderClinic) getClinicRowDataBinding().getRowData();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		clinicRowId = Integer.parseInt(params.get("rowIndexForclinic"));
	}
	
	public void deleteClinicData(){
		logger.info("deleteClinicData method starts in dashBoardManageBean::::::::::::::::");
		logger.info("delete clinic row id::::::"+clinicRowId+"clinic list size:::::"+patientProviderClinicListNewPatient.size());
		patientProviderClinicListNewPatient.remove(clinicRowId);
		logger.info("clinic list size after delete::::::::"+patientProviderClinicListNewPatient.size());
	}
	
	/* add clinic in profile page starts
	 * 
	 * by vinod */
	
	public DataTable getAddClinicForPatientBinding() {
		return addClinicForPatientBinding;
	}

	public void setAddClinicForPatientBinding(DataTable addClinicForPatientBinding) {
		this.addClinicForPatientBinding = addClinicForPatientBinding;
	}
	
	
	public void addClinicInProfilePage(){
		logger.info("addClinicInProfilePage :::::::starts In dashboard Manage::::::::");
		ClinicMaster clinicGetData = (ClinicMaster) getAddClinicForPatientBinding().getRowData();
		logger.info("clinicName::::::"+clinicGetData.getClinicName());
		ClinicProvider clinicData = new ClinicProvider();
		clinicData.setProviderId(new ContextUtil().getProviderId());
		clinicData.setClinicId(clinicGetData.getId());
		//clinicData.setProviderLocationId(new ContextUtil().getProviderLocationId());
 	      if(!getClinicProviderDetailList().contains(clinicData)){
			//clinicProviderDetailList.add(clinicData);
			dashBoardService.saveClinicProviderData(clinicData);
		}
		boolean check = false;
		for(PatientProviderClinic clinicdata:getCopyOfOriginalPatientClinicDataListprofile()){
			if(clinicdata.getClinicName().equalsIgnoreCase(clinicGetData.getClinicName()))
				check=true;
		}
			for(ClinicProvider clinicdata:getClinicProviderDetailList()){
				logger.info("clinicdata:::::"+clinicdata.getClinicId()+"clinicRowData::::::"+clinicGetData.getId());
		//&& clinicData.getProviderLocationId()== new ContextUtil().getProviderLocationId() && clinicData.getProviderId() == new ContextUtil().getProviderId()
				if(clinicdata.getClinicId() == (clinicGetData.getId()))
				{
					patientProviderClinicDetail.setClinicId(clinicGetData.getId());
					patientProviderClinicDetail.setClinicName(clinicGetData.getClinicName());
					patientProviderClinicDetail.setClinicProviderId(clinicdata.getId());
					patientProviderClinicDetail.setPatientId(new ContextUtil().getPatientId());
					//patientProviderClinicDetail.setPatientId(userLoginDetail.getUserId());
					logger.info(":::::clinicName:::::"+patientProviderClinicDetail.getClinicName());
				}
			}
			if(!check){
				if(patientProviderClinicDetail.getClinicName() != null){
				copyOfOriginalPatientClinicDataListprofile.add(patientProviderClinicDetail);
				}
			patientProviderClinicDetail = new PatientProviderClinic();
			setUserInputForAddClinic(null);
			clinicMasterDetailList=null;
			patientClinicList= null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Clinic Saved Successfully", ""));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Already There", ""));
		    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
		patientProviderClinicDetail = new PatientProviderClinic();
		clinicMasterDetailList=null;
	}

	public List<PatientProviderClinic> getPatientClinicList() {
		return patientClinicList;
	}

	public void setPatientClinicList(List<PatientProviderClinic> patientClinicList) {
		this.patientClinicList = patientClinicList;
	}

	
	public DataTable getEditProfileClinicBinding() {
		return editProfileClinicBinding;
	}

	public void setEditProfileClinicBinding(DataTable editProfileClinicBinding) {
		this.editProfileClinicBinding = editProfileClinicBinding;
	}
	
	public List<PatientProviderClinic> getTemporaryDeleteClinicListProfile() {
		return temporaryDeleteClinicListProfile;
	}



	public void setTemporaryDeleteClinicListProfile(
			List<PatientProviderClinic> temporaryDeleteClinicListProfile) {
		this.temporaryDeleteClinicListProfile = temporaryDeleteClinicListProfile;
	}
	
	int clinicRowDataIdForProfilePage;
	public void editOrDeleteClinics(){
		logger.info("editOrDeleteClinics:::::method in dashBaordBean:::::: ");
		PatientProviderClinic patientProClinic = (PatientProviderClinic) getEditProfileClinicBinding().getRowData();
		patientProClinic.setPatientId(new ContextUtil().getPatientId());
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params=fc.getExternalContext().getRequestParameterMap();
		clinicRowDataIdForProfilePage = Integer.parseInt(params.get("rowIndex"));
		logger.info("deleted clinic row id::::"+clinicRowDataIdForProfilePage);
		for(ClinicProvider clinicdata:getClinicProviderDetailList()){
			if(clinicdata.getClinicId() == (patientProClinic.getClinicId())){
				logger.info("clinic id"+clinicdata.getId());
				patientProClinic.setClinicProviderId(clinicdata.getId());
				patientProClinic.setProviderLocationId(clinicdata.getProviderLocationId());
				if(patientProClinic.getClinicId()>0){
					patientProClinic.setCheckDeleteClinic(true);
					if(!temporaryDeleteClinicListProfile.contains(patientProClinic))
					temporaryDeleteClinicListProfile.add(patientProClinic);
					for(PatientProviderClinic pa:temporaryDeleteClinicListProfile){
					logger.info(" deleted clinaicId :::::::"+pa.getClinicId()+" clinicProviderId::::::"+pa.getClinicProviderId());
					}
				}
				else {
					patientProClinic.setCheckDeleteClinic(false);
				}
			}
		}
		//patientProviderClinicDetail.setClinicId(clinicRowDataIdForProfilePage)
		logger.info("list size after delete clinic :::::::::"+temporaryDeleteClinicListProfile.size());
	}
	
	public List<PatientProviderClinic> getCopyOfOriginalPatientClinicDataListprofile() {
		 if(copyOfOriginalPatientClinicDataListprofile == null){
			 copyOfOriginalPatientClinicDataListprofile = new ArrayList<PatientProviderClinic>();
			 List<PatientProviderClinic> patientClinicCacheList = new ArrayList<PatientProviderClinic>();
			 patientClinicCacheList.addAll(getPatientClinicDataListprofile());
			 copyOfOriginalPatientClinicDataListprofile.addAll(patientClinicCacheList);
		 }
		return copyOfOriginalPatientClinicDataListprofile;
	}

	public void setCopyOfOriginalPatientClinicDataListprofile(
			List<PatientProviderClinic> copyOfOriginalPatientClinicDataListprofile) {
		this.copyOfOriginalPatientClinicDataListprofile = copyOfOriginalPatientClinicDataListprofile;
	}
	
	public void deleteClinicFromProfilePage(){
		logger.info("deleteClinicFromProfilePage::::::::::in dashboardmanageBean::::::");
		copyOfOriginalPatientClinicDataListprofile.remove(clinicRowDataIdForProfilePage);
		logger.info("clinicList after deleteing clinic row::::::::::"+patientClinicDataListprofile.size());
		/*for(ClinicProvider clinicdata:getClinicProviderDetailList()){
				if(patientProviderClinicDetail.getClinicId() == clinicdata.getClinicId())
					patientProviderClinicDetail.setClinicProviderId(clinicdata.getId());
		}*/
		patientProviderClinicDetail = new PatientProviderClinic();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Clinic Deleted Successfully", ""));
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}

	public List<PatientProviderClinic> getPatientClinicDataListprofile() {
		if(patientClinicDataListprofile==null){
			patientClinicDataListprofile = new ArrayList<PatientProviderClinic>();
			patientClinicDataListprofile = dashBoardService.fetchPatientClinicData(new ContextUtil().getPatientId());
		}
		return patientClinicDataListprofile;
	}

	public void setPatientClinicDataListprofile(
			List<PatientProviderClinic> patientClinicDataListprofile) {
		this.patientClinicDataListprofile = patientClinicDataListprofile;
	}

	public EmployerDetails getPatientEmployerDetails() {
		return patientEmployerDetails;
	}

	public void setPatientEmployerDetails(EmployerDetails patientEmployerDetails) {
		this.patientEmployerDetails = patientEmployerDetails;
	}

	public DataTable getAddInsuranceEditProfile() {
		return addInsuranceEditProfile;
	}

	public void setAddInsuranceEditProfile(DataTable addInsuranceEditProfile) {
		this.addInsuranceEditProfile = addInsuranceEditProfile;
	}
	
	public void addInsuranceForProfilePage(){
		int loggedID= new ContextUtil().getPatientId();
		logger.info("addInsuranceForProfilePage method starts::::::::in dashBoardManageBean");
		InsuranceCompanies companyName = (InsuranceCompanies) getAddInsuranceEditProfile().getRowData();
		logger.info("selected Insurance Name::::::::::"+companyName.getCompanyName()+" "+companyName.getId()+" "+loggedID);
		
		
		boolean checkStatus=false;
		for(UserInsuranceDetails userInsurance:getCopyOfOriginalPateintInsuranceEditProfileList()){
			if(userInsurance.getInsuranceId()==companyName.getHealPlanDetailID())
				checkStatus=true;
		}
		if(!checkStatus){
			userInsuranceDetail.setInsuranceId(companyName.getId());
			userInsuranceDetail.setUserId(new ContextUtil().getPatientId());
			logger.info("selectInsuranceLevel::::::value"+selectInsuranceLevel);
			if(selectInsuranceLevel == 1){
				userInsuranceDetail.setPrimaryInsurance("yes");
			}
			else{
				userInsuranceDetail.setPrimaryInsurance("no");
			}
			if(selectInsuranceLevel == 2){
				userInsuranceDetail.setSecondaryInsurance("yes");
			}
			else{
				userInsuranceDetail.setSecondaryInsurance("no");
			}
			if(selectInsuranceLevel == 3){
				userInsuranceDetail.setTertiaryInsurance("yes");
			}
			else{
				userInsuranceDetail.setTertiaryInsurance("no");
			}
			userInsuranceDetail.setPayorName(companyName.getCompanyName());
			userInsuranceDetail.setPayorAddress1(companyName.getAddress1());
			userInsuranceDetail.setPayorAddress2(companyName.getAddress2());
			userInsuranceDetail.setPayorCity(companyName.getCity());
			userInsuranceDetail.setPayorState(companyName.getState());
			userInsuranceDetail.setPayorCountry(companyName.getCountry());
			userInsuranceDetail.setPayorZip(companyName.getZip());
			userInsuranceDetail.setPayorZip4(companyName.getZip4());
			
			//copyOfOriginalPateintInsuranceEditProfileList = new ArrayList<UserInsuranceDetails>();
			//copyOfOriginalPateintInsuranceEditProfileList.add(userInsuranceDetail);
			userInsuranceDetail.setCompanyName(companyName.getCompanyName());
			Random randomNumber = new Random();
			int payorAndInsuredRandomNumber=0;
			int payorGroupNumberRandomNumber=0;
			for(int i=0;i<1;i++)
			{
				payorAndInsuredRandomNumber=randomNumber.nextInt(1000);
				payorGroupNumberRandomNumber=randomNumber.nextInt(1000);
			}
			userInsuranceDetail.setPayorAndInsuredID("hsi-"+payorAndInsuredRandomNumber);
			userInsuranceDetail.setPayorGroupNumber(""+payorGroupNumberRandomNumber);
			masterInsuranceDetailList = null;
			setUserInputForAddInsurance(null);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Insurance Added Successfully", ""));
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Already There", ""));
		}
	}

	public List<UserInsuranceDetails> getPatientInsuranceEditProfileList() {
		return patientInsuranceEditProfileList;
	}

	public void setPatientInsuranceEditProfileList(
			List<UserInsuranceDetails> patientInsuranceEditProfileList) {
		this.patientInsuranceEditProfileList = patientInsuranceEditProfileList;
	}
	
	public void addInsuranceEditProfile(){
		logger.info("selectInsuranceLevel::::::"+selectInsuranceLevel);
		RequestContext.getCurrentInstance().execute("addInsurancePopUpEdit.show()");
	}

	public List<UserInsuranceDetails> getPateintInsuranceEditProfileList() {
		if(pateintInsuranceEditProfileList == null){
			pateintInsuranceEditProfileList = new ArrayList<UserInsuranceDetails>();
			pateintInsuranceEditProfileList=dashBoardService.fetchPatientInsuranceDetail(new ContextUtil().getPatientId());
			for(UserInsuranceDetails uIn:pateintInsuranceEditProfileList){
			logger.info("insurance type:::"+" "+uIn.getInsuranceId()+" "+uIn.getPrimaryInsurance()+" "+uIn.getSecondaryInsurance()+" "+uIn.getTertiaryInsurance());
		  }
		}
		
		return pateintInsuranceEditProfileList;
	}

	public void setPateintInsuranceEditProfileList(
			List<UserInsuranceDetails> pateintInsuranceEditProfileList) {
		this.pateintInsuranceEditProfileList = pateintInsuranceEditProfileList;
	}
	
	
	public DataTable getBindingRowDataForEditPatient() {
		return bindingRowDataForEditPatient;
	}

	public void setBindingRowDataForEditPatient(
			DataTable bindingRowDataForEditPatient) {
		this.bindingRowDataForEditPatient = bindingRowDataForEditPatient;
	}
	
	public List<UserInsuranceDetails> getTemporaryDeleteInsuranceListProfile() {
		return temporaryDeleteInsuranceListProfile;
	}

	public void setTemporaryDeleteInsuranceListProfile(
			List<UserInsuranceDetails> temporaryDeleteInsuranceListProfile) {
		this.temporaryDeleteInsuranceListProfile = temporaryDeleteInsuranceListProfile;
	}

	
	int patientInsuranceIdEditProfile;
	public void editorDeleteInsuranceProfile(){
		logger.info("editorDeleteInsuranceProfile:::::starts:::::in dashBoardManageBean:::::::");
		//UserInsuranceDetails pateintInsuranceDelete = new UserInsuranceDetails();
		UserInsuranceDetails pateintInsuranceDelete = (UserInsuranceDetails) getBindingRowDataForEditPatient().getRowData();
		pateintInsuranceDelete.setUserId(new ContextUtil().getPatientId());
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		patientInsuranceIdEditProfile = Integer.parseInt(params.get("insuranceRow"));
		//int deleteInsuranceId = Integer.parseInt(params.get("insuranceID"));
			if(pateintInsuranceDelete.getInsuranceId()>0){
				pateintInsuranceDelete.setCheckInsuranceDelete(true);
				temporaryDeleteInsuranceListProfile.add(pateintInsuranceDelete);
			}
			else {
				pateintInsuranceDelete.setCheckInsuranceDelete(false);
			}
			/*for(UserInsuranceDetails uid : copyOfOriginalPateintInsuranceEditProfileList)
			{
				if(pateintInsuranceDelete.getId() == uid.getId())
				{
					userInsuranceDetail.setId(pateintInsuranceDelete.getId());
					userInsuranceDetail.setPayorName(pateintInsuranceDelete.getPayorName());
					System.out.println("payor name is********** "+pateintInsuranceDelete.getPayorName()+" "+userInsuranceDetail.getPayorName());
				}
			}*/
			
		logger.info("temporaryDeleteInsuranceListProfile list size:::::::after delete insurance::::::"+temporaryDeleteInsuranceListProfile.size());
		/*pateintInsurance.setInsuranceId(deleteInsuranceId);
		pateintInsurance.setCompanyName(params.get("insuranceName"));*/
		System.out.println("deleteing patient insurance rowNumber  id:::::"+patientInsuranceIdEditProfile);
	}
	
	public void deleteInsuranceEditProfile(){
		logger.info("deleteInsuranceEditProfile:::::starts:::::in dashBoardManageBean:::::::");
		copyOfOriginalPateintInsuranceEditProfileList.remove(patientInsuranceIdEditProfile);
	}

	public List<UserInsuranceDetails> getCopyOfOriginalPateintInsuranceEditProfileList() {
		if(copyOfOriginalPateintInsuranceEditProfileList == null){
			copyOfOriginalPateintInsuranceEditProfileList = new ArrayList<UserInsuranceDetails>();
			List<UserInsuranceDetails> cacheOfInsuranceList = new ArrayList<UserInsuranceDetails>();
			cacheOfInsuranceList.addAll(getPateintInsuranceEditProfileList());
			copyOfOriginalPateintInsuranceEditProfileList.addAll(cacheOfInsuranceList);
			
		}
		return copyOfOriginalPateintInsuranceEditProfileList;
	}

	public void setCopyOfOriginalPateintInsuranceEditProfileList(
			List<UserInsuranceDetails> copyOfOriginalPateintInsuranceEditProfileList) {
		this.copyOfOriginalPateintInsuranceEditProfileList = copyOfOriginalPateintInsuranceEditProfileList;
	}
	
	
	//Method to plot Drill down chart for Drug Type
	

	public String levelVal4DrugChart;
	public String getLevelVal4DrugChart() {
		return levelVal4DrugChart;
	}

	public void setLevelVal4DrugChart(String levelVal4DrugChart) {
		this.levelVal4DrugChart = levelVal4DrugChart;
	}
	
	public List<RptDrugCategory> drugListByCategoryChart =null;
	public String getDrugListByCategoryChart() {
		logger.info("!!!!!optimization 5 start ="+new Date());
		  /* -- */
		  List<RptDrugCategory> drugListByCategoryChartInside=new ArrayList<RptDrugCategory>();

			drugListByCategoryChartInside=dashBoardService.fetchDrugByCategoryChart(levelVal4DrugChart, new ContextUtil().getProviderId());
			//System.out.println("value coming at bean for drugchart - anand is " + levelVal4DrugChart);
			//System.out.println("list having detail for drug -- anand::"+drugListByCategoryChartInside.size());
			
			for (RptDrugCategory drug : drugListByCategoryChartInside) {
			
			RptDrugCategory rptDrugCategory=new RptDrugCategory();
			rptDrugCategory.setDrugName(drug.getDrugName());
			rptDrugCategory.setTotalPatient(drug.getTotalPatient());
			//System.out.println("DrugCategory::::::: "+drug.getDrugName()+"totalDrug::::::: "+drug.getTotalPatient());
			drugListByCategoryChart.add(rptDrugCategory);
			
		  }
			//System.out.println("joson list data:"+drugListByCategoryChart.size());
			logger.info("!!!!!optimization 5 ends ="+new Date());
		  return  new Gson().toJson(drugListByCategoryChart);		
	}

	public void setDrugListByCategoryChart(
			List<RptDrugCategory> drugListByCategoryChart) {
		this.drugListByCategoryChart = drugListByCategoryChart;
	}

	public void fetchDrugByCategoryChart()
	{
		
		getDrugListByCategoryChart();
		System.out.println("joson list data:"+drugListByCategoryChart.size());
	  
		
	}

	public NetworkChart getNetworkChart() {
		return networkChart;
	}

	public void setNetworkChart(NetworkChart networkChart) {
		this.networkChart = networkChart;
	}


	public String getNetworkChartListFull() {
		logger.info("!!!!!optimization 6 start ="+new Date());
		int logedUserId=new ContextUtil().getLoginId();
		String logedUserFirstName=new ContextUtil().getLoggerFirstName();
		String logedUserLastName=new ContextUtil().getLoggerLastName();
		//System.out.println("logedUserFirstNameFull---------------- "+logedUserFirstName+"logedUserLastName-------------------- "+logedUserLastName);
		networkChartListFull=dashBoardService.findNetworkChart(0,logedUserId,logedUserFirstName,logedUserLastName);
		System.out.println("networkChartListFull size:::"+networkChartListFull.size());
		
		Set<NetworkChart> networkChartset = new LinkedHashSet<NetworkChart>(networkChartListFull);
		networkChartListFull=new ArrayList<NetworkChart>(networkChartset);
		System.out.println("22222222networkChartListFull size:::"+networkChartListFull.size());
		//System.out.println("Total Connected Doctor in network are "+networkChartListFull.size());
		
		/*int totalSize=networkChartListFull.size();
		networkChart.setTotal_connection(totalSize);*/
		/*
		setTotal_connectedDoctor(networkChartListFull.size());*/
		//System.out.println("total connected doctor in netowrk full is "+total_connectedDoctor);
		logger.info("!!!!!optimization 6 ends ="+new Date());
		return new Gson().toJson(networkChartListFull);
	}


	public void setNetworkChartListFull(List<NetworkChart> networkChartListFull) {
		this.networkChartListFull = networkChartListFull;
	}

	public void addEditPatientProfileDiagnosesIcd10(){
		FacesContext context = FacesContext	.getCurrentInstance();
		PatientManageBean patientManageBean=(PatientManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientManageBean");
		patientManageBean.findDiagnosisNameEditPatient();
		//patientManageBean.setIcd9DiagnosisNameForNewPatient();
		editPateintProfileDiagosesList.addAll(patientManageBean.getPateintDiagnosesForEditProfileList());
		temporaryAddDiagnosesEditProfileList.addAll(patientManageBean.getPateintDiagnosesForEditProfileList());
		logger.info("pateintDiagnoses list size:::::::::after add"+editPateintProfileDiagosesList.size());
	    logger.info("pateintDiagnoses list size:::::::::after add"+temporaryAddDiagnosesEditProfileList.size());
		patientManageBean.setPateintDiagnosesForEditProfileList(null);
	}
    
	public void addEditPateintProfileDiagnosesIcd9() {
		FacesContext context = FacesContext	.getCurrentInstance();
		PatientManageBean patientManageBean=(PatientManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientManageBean");
		patientManageBean.setIcd9DiagnosisNameForEditPatient();
		editPateintProfileDiagosesList.addAll(patientManageBean.getPateintDiagnosesForEditProfileList());
		temporaryAddDiagnosesEditProfileList.addAll(patientManageBean.getPateintDiagnosesForEditProfileList());
		logger.info("pateintDiagnoses list size:::::::::after add"+editPateintProfileDiagosesList.size());
	    logger.info("pateintDiagnoses list size:::::::::after add"+temporaryAddDiagnosesEditProfileList.size());
		patientManageBean.setPateintDiagnosesForEditProfileList(null);
	}



	public List<PatientDiagnosesDetails> getEditPateintProfileDiagosesList() {
		if(editPateintProfileDiagosesList == null){
			editPateintProfileDiagosesList= new ArrayList<PatientDiagnosesDetails>();
		List<PatientDiagnosesDetails> cacheListForDiagnoses = new ArrayList<PatientDiagnosesDetails>();
		System.out.println("calling line no::::4570::::dashBoardManage::");
		cacheListForDiagnoses.addAll(getEditPatientProfileICDDiagnosisList());
		editPateintProfileDiagosesList.addAll(cacheListForDiagnoses);
		}
		return editPateintProfileDiagosesList;
	}



	public void setEditPateintProfileDiagosesList(
			List<PatientDiagnosesDetails> editPateintProfileDiagosesList) {
		this.editPateintProfileDiagosesList = editPateintProfileDiagosesList;
	}



	public List<PatientDiagnosesDetails> getEditPatientProfileICDDiagnosisList() {
		if(editPatientProfileICDDiagnosisList == null){
			editPatientProfileICDDiagnosisList = new ArrayList<PatientDiagnosesDetails>();
			FacesContext context = FacesContext	.getCurrentInstance();
			PatientManageBean patientManageBean=(PatientManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientManageBean");
			editPatientProfileICDDiagnosisList.addAll(patientManageBean.getPatientICDDiagnosisList());
			patientManageBean.setPatientICDDiagnosisList(null);
		}
		return editPatientProfileICDDiagnosisList;
	}



	public void setEditPatientProfileICDDiagnosisList(
			List<PatientDiagnosesDetails> editPatientProfileICDDiagnosisList) {
		this.editPatientProfileICDDiagnosisList = editPatientProfileICDDiagnosisList;
	}
	


	public DataTable getDeleteDiagnosesEditProfile() {
		return deleteDiagnosesEditProfile;
	}



	public void setDeleteDiagnosesEditProfile(DataTable deleteDiagnosesEditProfile) {
		this.deleteDiagnosesEditProfile = deleteDiagnosesEditProfile;
	}


	
	public void deletePatientDiagnosisEditProfile()
	{
	
	logger.info("deletePatientDiagnosisNewPatient:::::::::setidtodeletePatientdiagnosis method start:::for new Pateint");
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		 patientDiagnosesDetails=(PatientDiagnosesDetails) getDeleteDiagnosesEditProfile().getRowData();
		 patientDiagnosesDetails.setPatientId(new ContextUtil().getPatientId());
		patientDiagnosesDetails.setDoctorId(new ContextUtil().getLoginId());
		//logger.info("value of code:::::::::get code:::"+patientDiagnosesDetails.getCode());
}
	
	
	
	public void deleteDiagnosisRowEditProfile()
	{
		logger.info("Delete method start for new patient:::::::::::::");
		logger.info("deleteing data"+patientDiagnosesDetails.getId()+"icdId:: "+patientDiagnosesDetails.getIcdId());
          if(patientDiagnosesDetails.getIcdId() != null){
        	  deleteDiagnosesEditProfileList.add(patientDiagnosesDetails);
        	  patientDiagnosesDetails.setCheckstatusDatabase(true);
          }
		editPateintProfileDiagosesList.remove(patientDiagnosesDetails);
		temporaryAddDiagnosesEditProfileList.remove(patientDiagnosesDetails);
	    logger.info("pateintDiagnoses list size:::::::::after delete"+editPateintProfileDiagosesList.size());
	    logger.info("pateintDiagnoses list size:::::::::after delete"+temporaryAddDiagnosesEditProfileList.size());
	   // msg="Diagnosis Deleted Successfully";
		/*FacesContext.getCurrentInstance().addMessage("messageUpdateForDiscountinueMeds", new FacesMessage(FacesMessage.SEVERITY_INFO,"Diagnosis Deleted Successfully",""));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);*/
	}
	
	public void addAllergyForEditProfilePage(){
		FacesContext context = FacesContext	.getCurrentInstance();
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
		patientMedicineManageBean.addAllergyForEditPatient();
		allergyListForEditPatient.addAll(patientMedicineManageBean.getAllergyDetailsForEditPatientList());
		temporaryAddAllergyForProfile.addAll(patientMedicineManageBean.getAllergyDetailsForEditPatientList());
		logger.info("list size  after  adding allergy::::or "+temporaryAddAllergyForProfile.size());
		logger.info("list size  after  adding allergy::::duop "+allergyListForEditPatient.size());
		patientMedicineManageBean.setAllergyDetailsForEditPatientList(null);
	}
	
	

	public List<PatientAllergy> getAllergyListForEditPatient() {
               if(allergyListForEditPatient == null){
            	   allergyListForEditPatient = new ArrayList<PatientAllergy>();
            	   List<PatientAllergy> cacheListForAllergyEditList = new ArrayList<PatientAllergy>();
            	   cacheListForAllergyEditList.addAll(getDatabaseAllergyListForEditPage());
            	   allergyListForEditPatient.addAll(cacheListForAllergyEditList);
               }
		return allergyListForEditPatient;
	}


	public void setAllergyListForEditPatient(
			List<PatientAllergy> allergyListForEditPatient) {
		this.allergyListForEditPatient = allergyListForEditPatient;
	}



	public List<PatientAllergy> getDatabaseAllergyListForEditPage() {
		if(databaseAllergyListForEditPage == null){
			databaseAllergyListForEditPage = new ArrayList<PatientAllergy>();
			FacesContext context = FacesContext	.getCurrentInstance();
			PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
			databaseAllergyListForEditPage.addAll(patientMedicineManageBean.getPatientAllergyList());
			logger.info("size of allergy DB list:::::"+patientMedicineManageBean.getPatientAllergyList().size());
			patientMedicineManageBean.setPatientAllergyList(null);
		}
		return databaseAllergyListForEditPage;
	}



	public void setDatabaseAllergyListForEditPage(
			List<PatientAllergy> databaseAllergyListForEditPage) {
		this.databaseAllergyListForEditPage = databaseAllergyListForEditPage;
	}

	
	int allergyRowIdnumber;
	public void editDeleteRowForEditProfile()
	{
		logger.info("setidtodeletePatientAllergyForNewPatient method start:::");
		patientAllergy = new PatientAllergy();
		patientAllergy = (PatientAllergy) getBindingAllergyDataForEditProfile().getRowData();
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		allergyRowIdnumber=Integer.parseInt(params.get("rowIdForAllergyEditProfile"));
		System.out.println("::::::::::::::::::::::::>>>aller"+patientAllergy.getAllergyName());
	}
	
	public void deleteAllergyEditProfile()
	{
		logger.info("deleteAllergyNewPatient method start for new Patient:::::::::::::");
		//allergySize=0;
		System.out.println(":::::::::::row id:asdel:"+allergyRowIdnumber+":::"+allergyListForEditPatient.size());
		if(patientAllergy.getCompositeAllergyId()>0){
			deleteAllergyForProfilePageList.add(patientAllergy);
			patientAllergy.setCheckDbStatus(true);
		}
		allergyListForEditPatient.remove(patientAllergy);
		temporaryAddAllergyForProfile.remove(patientAllergy);
		logger.info("list size  after  delete allergy::::or "+temporaryAddAllergyForProfile.size());
		logger.info("list size  after  delete allergy::::duop "+allergyListForEditPatient.size());
		System.out.println("::::::::::::::::::::patientAllergyList"+allergyListForEditPatient.size());
		//patientMedicineService.deleteAllergyDetails(patientAllergy);
		patientAllergy=new PatientAllergy();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Allergy Deleted Successfully",  "");  
		FacesContext.getCurrentInstance().addMessage(null, message);  

	}



	public DataTable getBindingAllergyDataForEditProfile() {
		return bindingAllergyDataForEditProfile;
	}

	public void setBindingAllergyDataForEditProfile(
			DataTable bindingAllergyDataForEditProfile) {
		this.bindingAllergyDataForEditProfile = bindingAllergyDataForEditProfile;
	}

	
	public void changeEditPateintWeight(){
		logger.info(" seleted weight unit"+patientVital.getWeightUnits());
		
		if(patientVital.getWeightUnits().equalsIgnoreCase("Kg")){
			//calculateCreatimineClearance.setWeight(calculateCreatimineClearance.getSimpleWeight());
			double massLB = patientVital.getWeight() * 2.2046;
			String massLbRound = new DecimalFormat("#.#####").format(massLB);
			patientVital.setConvertWeight(Double.parseDouble(massLbRound));
			patientVital.setUpdateUnites("(Lbs)");
		}
		else
		{
			double massLB = patientVital.getWeight() / 2.2046;
			String massLbRound = new DecimalFormat("#.#####").format(massLB);
			patientVital.setConvertWeight(Double.parseDouble(massLbRound));
			patientVital.setUpdateUnites("(Kg)");

		}
	}
	
/*author: vinod 
 * For edit Pateint details modified by umesh*/	
	public void savePatientEditProfile()
	{
		logger.info("editPatientData::::::starts in dashBoardManageBean:::::::");
		logger.info("selected parameter for weight to save edit profile::::::"+patientVital.getWeightUnits()+patientVital.getHeightinfeet()+patientVital.getHeightininches());
		if(patientVital.getHeightinfeet().isEmpty() || patientVital.getHeightinfeet()==null)
		{
			logger.info("height in feet"+patientVital.getHeightinfeet());
			patientVital.setHeightinfeet("0");
			logger.info("height in feet1"+patientVital.getHeightinfeet());
		}
		if(patientVital.getHeightininches().isEmpty() || patientVital.getHeightininches()==null)
		{
			logger.info("height in inches"+patientVital.getHeightininches());
			patientVital.setHeightininches("00");
			logger.info("height in inches1"+patientVital.getHeightininches());
		}
		patientVital.setHeight(patientVital.getHeightinfeet()+"."+patientVital.getHeightininches());
		if(patientVital.getHeight().isEmpty()||patientVital.getHeight()==null){
			patientVital.setHeight("0.00");
		  }
		  if(patientVital.getWeightUnits().equalsIgnoreCase("lbs")){
			  double massLB = patientVital.getWeight() / 2.2046;
			 String massLbRound = new DecimalFormat("#.#####").format(massLB);
			  patientVital.setWeight(Double.parseDouble(massLbRound));
		  }
		  if (StringUtils.isNotBlank(userLoginDetail.getState()) ) {
			  userLoginDetail.setState(userLoginDetail.getState().toUpperCase());
		  }
		  if (StringUtils.isNotBlank(userLoginDetail.getCountry()) ) {
			  userLoginDetail.setCountry(userLoginDetail.getCountry().toUpperCase());
		  }
		  System.out.println("insuranceListForUpdate size in final update "+insuranceListForUpdate.size());
		      patientVital.setRace(userLoginDetail.getRace());
		      patientGuarantor.setUserID(new ContextUtil().getPatientId());
			dashBoardService.savePatientEditProfile(userLoginDetail,patientEmployerDetails,patientVital,
					copyOfOriginalPateintInsuranceEditProfileList,pateintInsuranceEditProfileList,temporaryDeleteInsuranceListProfile,
					copyOfOriginalPatientClinicDataListprofile,patientClinicDataListprofile,temporaryDeleteClinicListProfile,
					editPateintProfileDiagosesList,temporaryAddDiagnosesEditProfileList,deleteDiagnosesEditProfileList,
					allergyListForEditPatient,temporaryAddAllergyForProfile,deleteAllergyForProfilePageList,patientGuarantor,insuranceListForUpdate);
		
			/*temporaryDeleteInsuranceListProfile = new ArrayList<UserInsuranceDetails>();
			dashBoardService.savePatientInsuranceProfile(copyOfOriginalPateintInsuranceEditProfileList,temporaryDeleteInsuranceListProfile,pateintInsuranceEditProfileList);
		*/ 
			
			/*for convert patient weight from KG to LBS on Edit pateint weight in profile page
			 * this method will call only one time on save of edit profile details*/
			patientVital = new PatientVital();
			patientVital = dashBoardService.findParticularPatientVitalDetail(userLoginDetail.getUserId());
			patientVital.setWeightInKG(patientVital.getWeight());
			findBodyMassIndex(patientVital.getHeight(), patientVital.getWeight(),this.getUserLoginDetail().getGender());
			
			
			FacesContext context=FacesContext.getCurrentInstance();
			PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");
			patientMedicineManageBean.setPatientAllergyList(null);
			PatientManageBean patientManageBean=(PatientManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientManageBean");
			patientManageBean.setPatientICDDiagnosisList(null);
			
			/*For redirect to overview Filter pages*/
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			try {
				ec.redirect("overViewFilter.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}



	public Date getCommonTodayDateForVallidationEditProfile() {
		return commonTodayDateForVallidationEditProfile;
	}



	public void setCommonTodayDateForVallidationEditProfile(
			Date commonTodayDateForVallidationEditProfile) {
		this.commonTodayDateForVallidationEditProfile = commonTodayDateForVallidationEditProfile;
	}
	
	public void editAllergyRowEditProfile(){
		logger.info("editAllergyRowEditProfile :::method starts in dashboardManageBean::::::");
		//allergyListForEditPatient.add(patientAllergy);
		patientAllergy = new PatientAllergy(); 
		//allergyListForEditPatient= null;
	}


	public PatientAllergy getPatientAllergy() {
		return patientAllergy;
	}


	public void setPatientAllergy(PatientAllergy patientAllergy) {
		this.patientAllergy = patientAllergy;
	}


	public List<PatientAllergy> getTemporaryAddAllergyForProfile() {
		return temporaryAddAllergyForProfile;
	}


	public void setTemporaryAddAllergyForProfile(
			List<PatientAllergy> temporaryAddAllergyForProfile) {
		this.temporaryAddAllergyForProfile = temporaryAddAllergyForProfile;
	}


	public List<PatientDiagnosesDetails> getTemporaryAddDiagnosesEditProfileList() {
		return temporaryAddDiagnosesEditProfileList;
	}


	public void setTemporaryAddDiagnosesEditProfileList(
			List<PatientDiagnosesDetails> temporaryAddDiagnosesEditProfileList) {
		this.temporaryAddDiagnosesEditProfileList = temporaryAddDiagnosesEditProfileList;
	}

	public List<PatientDiagnosesDetails> getDeleteDiagnosesEditProfileList() {
		return deleteDiagnosesEditProfileList;
	}

	public void setDeleteDiagnosesEditProfileList(
			List<PatientDiagnosesDetails> deleteDiagnosesEditProfileList) {
		this.deleteDiagnosesEditProfileList = deleteDiagnosesEditProfileList;
	}

	public List<PatientAllergy> getDeleteAllergyForProfilePageList() {
		return deleteAllergyForProfilePageList;
	}

	public void setDeleteAllergyForProfilePageList(
			List<PatientAllergy> deleteAllergyForProfilePageList) {
		this.deleteAllergyForProfilePageList = deleteAllergyForProfilePageList;
	}


	/* full chart list */
	
	public String getPatientDrugGeneFullChartList() {
		logger.info("!!!!!optimization 7 start ="+new Date());
		patientDrugGeneFullChartList = new ArrayList<PatientCountForDrugGene>();
		List<PatientCountForDrugGene> duplicateRecordList = new ArrayList<PatientCountForDrugGene>();
		try {		
		for (PatientCountForDrugGene drugGeneList : getPatientDrugGeneList()) {
					
				PatientCountForDrugGene drugGeneData = new PatientCountForDrugGene();
				drugGeneData.setGeneSymbol(drugGeneList.getGeneSymbol());
				drugGeneData.setMedicineName(drugGeneList.getMedicineName());
				drugGeneData.setPatientCount(drugGeneList.getPatientCount()); 
				drugGeneData.setAttentionRating(drugGeneList.getAttentionRating());
				
						if(!(patientDrugGeneFullChartList.contains(drugGeneData))){
							patientDrugGeneFullChartList.add(drugGeneData);
							count++;
				          }
						else{
						   if(patientDrugGeneFullChartList.contains(drugGeneData)){
							   patientDrugGeneFullChartList.add(drugGeneData);
						   }						
				}
				
	
		//System.out.println("patientDrugGeneChartList:::"+patientDrugGeneFullChartList.size());
		}
		} catch (Exception e) {
			logger.error("error in getPatientDrugGeneChartList"+e);
		}
		logger.info("!!!!!optimization 7 ends ="+new Date());
		return new Gson().toJson(patientDrugGeneFullChartList);
	}


	public void setPatientDrugGeneFullChartList(
			List<PatientCountForDrugGene> patientDrugGeneFullChartList) {
		this.patientDrugGeneFullChartList = patientDrugGeneFullChartList;
	}
	
	
	
	public String getUniqueMedNamesForGeneFull() {
		logger.info("!!!!!optimization 8 start ="+new Date());
		uniqueMedNamesForGeneFull=new ArrayList<PatientCountForDrugGene>();
		int medCount=1;
		for(PatientCountForDrugGene drugGeneList : getPatientDrugGeneList()){
			PatientCountForDrugGene drugGeneData = new PatientCountForDrugGene();
			drugGeneData.setGeneSymbol("");
			drugGeneData.setMedicineName(drugGeneList.getMedicineName());
			drugGeneData.setPatientCount(drugGeneList.getPatientCount());
			drugGeneData.setAttentionRating(drugGeneList.getAttentionRating());
			
			
				if(!(uniqueMedNamesForGeneFull.contains(drugGeneData))){
					uniqueMedNamesForGeneFull.add(drugGeneData);
					System.out.println("medCount :::::::::"+medCount);
					medCount++;
					
				}
		
			
		/*logger.info(" Medicine GeneSymbol::::"+ uniqueMedNamesForGene.size() +":::::"+drugGeneData.getGeneSymbol()+"MedicineName:::::::::"+drugGeneData.getMedicineName()+
				"noOfPatients::::::"+drugGeneData.getPatientCount()+"impact:::::::: "+drugGeneData.getAttentionRating());*/
		}
		//logger.info("Unique Med Name List"+ uniqueMedNamesForGeneFull.size() );
		logger.info("!!!!!optimization 8 ends ="+new Date());
		return new Gson().toJson(uniqueMedNamesForGeneFull);
	}

	public void setUniqueMedNamesForGeneFull(
			List<PatientCountForDrugGene> uniqueMedNamesForGeneFull) {
		this.uniqueMedNamesForGeneFull = uniqueMedNamesForGeneFull;
	}

	public String getUniqueGeneNamesForGeneFull() {
		logger.info("!!!!!optimization 9 start ="+new Date());
		uniqueGeneNamesForGeneFull=new ArrayList<PatientCountForDrugGene>();
		for(PatientCountForDrugGene drugGeneList : patientDrugGeneFullChartList){
			PatientCountForDrugGene drugGeneData = new PatientCountForDrugGene();
			drugGeneData.setGeneSymbol(drugGeneList.getGeneSymbol());
			drugGeneData.setMedicineName("");
			drugGeneData.setPatientCount(drugGeneList.getPatientCount());
			drugGeneData.setAttentionRating(drugGeneList.getAttentionRating());
			if(!uniqueGeneNamesForGeneFull.contains(drugGeneData)){
				uniqueGeneNamesForGeneFull.add(drugGeneData);
			}			
			
			//logger.info(" Gene GeneSymbol:::::::::"+drugGeneData.getGeneSymbol()+"MedicineName:::::::::"+drugGeneData.getMedicineName()+
					//"noOfPatients::::::"+drugGeneData.getPatientCount()+"impact:::::::: "+drugGeneData.getAttentionRating());
			
		}
		logger.info("!!!!!optimization 9 ends ="+new Date());
		return new Gson().toJson(uniqueGeneNamesForGeneFull);
		
	}

	public void setUniqueGeneNamesForGeneFull(
			List<PatientCountForDrugGene> uniqueGeneNamesForGeneFull) {
		this.uniqueGeneNamesForGeneFull = uniqueGeneNamesForGeneFull;
	}

	/* full chart list end */
	
	//For lithium chart dashboardmanagebean
	
	public String levelValForLithium;
	
	
	public String getLevelValForLithium() {
		return levelValForLithium;
	}

public void setLevelValForLithium(String levelValForLithium) {
	this.levelValForLithium = levelValForLithium;
}

//Method to find out the Lithium Patient List based on category by venu
	public String fetchPatientByLithium()
	{
		//getNumberofpatientformedactionplanforhigherendofpatientforintiationphase();
		System.out.println("Level Value coming from Javascript in dashboardmanagebean:::"+levelValForLithium);		
		getRetrivePatientForLithiumList();
		RequestContext.getCurrentInstance().execute("PF('dlgForLithiumClinicStatusLow').show()");
		//System.out.println("   "+session.getAttribute("levelVal1"));
		//getNumberOfPatientforanticoagforintiationphase();
		levelValForLithium=null;
		
		System.out.println("size of the Lithium list in dashboardmanagebean-----"+getRetrivePatientForLithiumList().size());
		
		return null;
		
	}


private List<UserLoginDetail> retrivePatientForLithiumList=new ArrayList<UserLoginDetail>();

//For psychopharm clinic chart while clicking the chart
public List<UserLoginDetail> getRetrivePatientForLithiumList() {
	if(levelValForLithium==null){
		levelValForLithium="";
	}
	System.out.println("Level Value coming from Javascript is in retrivePatientForLithiumList in dashboardmanagebean:::"+levelValForLithium);
	
	//int logedUserId=new ContextUtil().getLoginId();
	//System.out.println("::::::::doctor id:::"+logedUserId);
	//List<Integer>ClinicAndProviderIdList= dashBoardService.findClinicAndProviderId(logedUserId);
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

	session.setAttribute("clinicproviderId", ClinicAndProviderIdList.get(0));
	session.setAttribute("clinicId", ClinicAndProviderIdList.get(1));
	session.setAttribute("providerId", ClinicAndProviderIdList.get(2));
	
		if(levelValForLithium.equals("pli"))
		{
			System.out.println("inside low in initiation");
			//String medicinestage="Initiation";
			//int procedureType=2;
			//String start_result="0";
			//String end_result="0";
			retrivePatientForLithiumList=dashBoardService.findnumberofpatientforlithium(levelValForLithium);
			userLoginDetailList=retrivePatientForLithiumList;
		}
		if(levelValForLithium.equals("pmi"))
		{
			System.out.println("inside medium in initiation");
			//String medicinestage="Initiation";
			//int procedureType=2;
			//String start_result="0";
			//String end_result="0";
			retrivePatientForLithiumList=dashBoardService.findnumberofpatientforlithium(levelValForLithium);
			userLoginDetailList=retrivePatientForLithiumList;
		}
		if(levelValForLithium.equals("phi"))
		{
			System.out.println("inside high in initiation");
			//String medicinestage="Initiation";
			//int procedureType=2;					
			//String start_result="0";
			//String end_result="0";
			retrivePatientForLithiumList=dashBoardService.findnumberofpatientforlithium(levelValForLithium);
			userLoginDetailList=retrivePatientForLithiumList;
			
		}
		
		
		RequestContext context = RequestContext.getCurrentInstance(); 
		context.addCallbackParam("responseCompleted", true);
		userLoginDetailList=retrivePatientForLithiumList;
		Set removeDuplicatePatientDataMaintenance = new HashSet(userLoginDetailList);
		userLoginDetailList.clear();
		userLoginDetailList.addAll(removeDuplicatePatientDataMaintenance);
					
		/*Collections.sort(retrivePatientForLithiumList,new Comparator<UserLoginDetail>() {
		   

		
			public int compare(UserLoginDetail o1, UserLoginDetail o2) {
				return o2.getResult().compareTo(o1.getResult());
				
			}
		 });*/
	return retrivePatientForLithiumList;
}



public void setRetrivePatientForLithiumList(
		List<UserLoginDetail> retrivePatientForLithiumList) {
	this.retrivePatientForLithiumList = retrivePatientForLithiumList;
}


		
public List<UserLoginDetail> getNumberofpatientforlithiumLow() {
	logger.info("getNumberofpatientforlithiumLowforinitiationphase::::::::");
	
	/*String statusID="pli";
	
	if(numberofpatientforlithiumLow!=null){
		numberofpatientforlithiumLow=dashBoardService.findnumberofpatientforlithiumlowforintiationphase(statusID);
	}*/
	System.out.println("numberofpatientformedactionplanforinitiationphase size######### "+numberofpatientforlithiumLow.size());
	return numberofpatientforlithiumLow;
}

public void setNumberofpatientforlithiumLow(
		List<UserLoginDetail> numberofpatientforlithiumLow) {
	this.numberofpatientforlithiumLow = numberofpatientforlithiumLow;
}



public List<UserLoginDetail> getNumberofpatientforlithiumMedium() {
	logger.info("getNumberofpatientforlithiumMediumforinitiationphase::::::::");

	/*String statusID="pmi";
	
	if(numberofpatientforlithiumMedium!=null){
		numberofpatientforlithiumMedium=dashBoardService.findnumberofpatientforlithiumMediumforintiationphase(statusID);
	}*/
	System.out.println("numberofpatientforlithiumMediumforinitiationphase size######### "+numberofpatientforlithiumMedium.size());
	return numberofpatientforlithiumMedium;
}



public void setNumberofpatientforlithiumMedium(
		List<UserLoginDetail> numberofpatientforlithiumMediumforinitiationphase) {
	this.numberofpatientforlithiumMedium = numberofpatientforlithiumMediumforinitiationphase;
}



public List<UserLoginDetail> getNumberofpatientforlithiumHigh() {
	logger.info("getNumberofpatientforlithiumHighforinitiationphase::::::::");
	
	/*String statusID="phi";
	
	if(numberofpatientforlithiumHigh!=null){
		
		numberofpatientforlithiumHigh=dashBoardService.findnumberofpatientforlithiumHighforintiationphase(statusID);
	}*/
	System.out.println("numberofpatientforlithiumHighforinitiationphase size######### "+numberofpatientforlithiumHigh.size());
	return numberofpatientforlithiumHigh;
}



public void setNumberofpatientforlithiumHigh(
		List<UserLoginDetail> numberofpatientforlithiumHigh) {
	this.numberofpatientforlithiumHigh = numberofpatientforlithiumHigh;
}

//For getting gender for particular patient for patient portal
String genderFromDB = new String();	

public String getGenderFromDB() {
	int logedUserId=new ContextUtil().getLoginId();
	System.out.println("logged user id++++++++++++ "+logedUserId);
	genderFromDB=dashBoardService.fetchGenderFromDB(logedUserId);
	return genderFromDB;
}

public void setGenderFromDB(String genderFromDB) {
	this.genderFromDB = genderFromDB;
}

//Method to find out the Oncology Patient List based on category by Anand S Jha
public String levelValForOncology;



public String getLevelValForOncology() {
	return levelValForOncology;
}



public void setLevelValForOncology(String levelValForOncology) {
	this.levelValForOncology = levelValForOncology;
}




public List<UserLoginDetail> getListforOncologyPatient() {
	int logedUserId=new ContextUtil().getLoginId();
	List<UserLoginDetail> listforOncologyPatient1 = new ArrayList<UserLoginDetail>();
	listforOncologyPatient1=dashBoardService.fetchListForOncology(levelValForOncology,logedUserId);
	listforOncologyPatient=listforOncologyPatient1;
	userLoginDetailList=listforOncologyPatient;
	System.out.println("size of list for oncology in bean is "+listforOncologyPatient.size()+"::"+listforOncologyPatient1.size()+":::::::"+levelValForOncology);
	return listforOncologyPatient;
}



public void setListforOncologyPatient(
		List<UserLoginDetail> listforOncologyPatient) {
	
	this.listforOncologyPatient = listforOncologyPatient;
}



	public String fetchPatientByOncology()
	{
		//getNumberofpatientformedactionplanforhigherendofpatientforintiationphase();
		System.out.println("Level Value coming from Javascript in dashboardmanagebean::Oncology:"+levelValForOncology);
		getListforOncologyPatient();
		
		 
		//getUniqueResultList();
		
		RequestContext.getCurrentInstance().execute("PF('dlgForOncologyClinicStatusLow').show()");
		//System.out.println("   "+session.getAttribute("levelVal1"));
		//getNumberOfPatientforanticoagforintiationphase();
		
		
		System.out.println("This is size of the list in dashboardmanagebean-----"+getListforOncologyPatient().size());
		
		return null;
		
	}
	

	/**
	 * @see doctorProfile.jsf
	 * Method Used in Doctor Profile Page 
	 * 
	 */
		
		 public void highchartsBackGroundLoading(){
			 logger.info("chart back ground load method started::::::");
			 RequestContext context=RequestContext.getCurrentInstance();
			 
			 //fetching data for formulary 
			 logger.info("!!!!!optimization 10 start ="+new Date());
			    formularyList = new ArrayList<FormularyChart>();
				formularyList=dashBoardService.findFormularyChart();
				for(FormularyChart fc:formularyList){
					System.out.println("tier ::"+fc.getFormulayTier()+" med::"+fc.getMedicineId());
				}
				String formularyChartData = new Gson().toJson(formularyList);
				context.addCallbackParam("formularyChart", formularyChartData);
				logger.info("!!!!!optimization 10 ends ="+new Date());
			//fetching data for drugCategory	
				logger.info("!!!!!optimization 11 start ="+new Date());
			    drugCategoryDetailList = new ArrayList<DrugCategoryDetail>();
				drugCategoryDetailList = dashBoardService.findDrugCategoryDetailList();
				String drugCategoryChartData= new Gson().toJson(drugCategoryDetailList);
				context.addCallbackParam("drugCategoryData", drugCategoryChartData);
				logger.info("!!!!!optimization 11 ends ="+new Date());
				//fetching data specialityDrugData
				logger.info("!!!!!optimization 12 start ="+new Date());
				specialityDrugList = new ArrayList<SpecialityDrugCategory>();
				specialityDrugList = dashBoardService.findSpecialityDrugDetails();
				String specialityDrigChartData = new Gson().toJson(specialityDrugList);
				context.addCallbackParam("specialityDrugData", specialityDrigChartData);
				logger.info("!!!!!optimization 12 ends ="+new Date());
				//fetching data for diagnosesChartList
				logger.info("!!!!!optimization 13 start ="+new Date());
			
				diagnosesChartList = new ArrayList<DiagnosesChart>();
				diagnosesChartList = dashBoardService.getDiagnosesChartList(); // Method for get Diagnosis Data 
				String diagnosesChatData = new Gson().toJson(diagnosesChartList);
				 context.addCallbackParam("diagnosesData", diagnosesChatData);
				 logger.info("!!!!!optimization 13 ends ="+new Date()); 
			 //fectchingDataForDrugGeneInteraction(5425-5494)
				 logger.info("!!!!!optimization 14 start ="+new Date());
				 patientDrugGeneList = new ArrayList<PatientCountForDrugGene>();
					patientDrugGeneList = dashBoardService.getPatientDrugGeneList();
				 
			    patientDrugGeneChartList = new ArrayList<PatientCountForDrugGene>();
				List<PatientCountForDrugGene> duplicateRecordList = new ArrayList<PatientCountForDrugGene>();
				try {		
				int count =0;
				for (PatientCountForDrugGene drugGeneList : patientDrugGeneList) {
						PatientCountForDrugGene drugGeneData = new PatientCountForDrugGene();
						drugGeneData.setGeneSymbol(drugGeneList.getGeneSymbol());
						drugGeneData.setMedicineName(drugGeneList.getMedicineName());
						drugGeneData.setPatientCount(drugGeneList.getPatientCount()); 
						drugGeneData.setAttentionRating(drugGeneList.getAttentionRating());
						if(count<=9){
						for(PatientCountForDrugGene retrivRecords:duplicateRecordList){
							if(retrivRecords.getMedicineName().equalsIgnoreCase(drugGeneData.getMedicineName())){
								if(!(patientDrugGeneChartList.contains(drugGeneData))){
									patientDrugGeneChartList.add(drugGeneData);
						          }
							}
								else{
								   if(!(patientDrugGeneChartList.contains(retrivRecords))){
									   patientDrugGeneChartList.add(retrivRecords);
									   count++;
								   }
								}
							}
						}
						duplicateRecordList.add(drugGeneData);
				//System.out.println("patientDrugGeneChartList:::"+patientDrugGeneChartList.size());
				}
				} catch (Exception e) {
					logger.error("error in drugGeneData"+e);
				}
				String drugGeneChartData = new Gson().toJson(patientDrugGeneChartList);
				context.addCallbackParam("drugGeneChartDetails", drugGeneChartData);
				logger.info("!!!!!optimization 14 ends ="+new Date());
				logger.info("!!!!!optimization 15 start ="+new Date());
				uniqueMedNamesForGene=new ArrayList<PatientCountForDrugGene>();
				int medCount=1;
				for(PatientCountForDrugGene drugGeneList : patientDrugGeneList){
					PatientCountForDrugGene drugGeneData = new PatientCountForDrugGene();
					drugGeneData.setGeneSymbol("");
					drugGeneData.setMedicineName(drugGeneList.getMedicineName());
					drugGeneData.setPatientCount(drugGeneList.getPatientCount());
					drugGeneData.setAttentionRating(drugGeneList.getAttentionRating());
					if(medCount<=10){
						if(!(uniqueMedNamesForGene.contains(drugGeneData))){
							uniqueMedNamesForGene.add(drugGeneData);
							System.out.println("medCount :::::::::"+medCount);
							medCount++;
						}
					}
				//logger.info(" Medicine GeneSymbol::::"+ uniqueMedNamesForGene.size() +":::::"+drugGeneData.getGeneSymbol()+"MedicineName:::::::::"+drugGeneData.getMedicineName()+
						//"noOfPatients::::::"+drugGeneData.getPatientCount()+"impact:::::::: "+drugGeneData.getAttentionRating());
				}
				//logger.info("Unique Med Name List"+ uniqueMedNamesForGene.size() );
				String uniqueDrugName =  new Gson().toJson(uniqueMedNamesForGene);
				context.addCallbackParam("uniqueMedName", uniqueDrugName);
				logger.info("!!!!!optimization 15 ends ="+new Date());
				
				logger.info("!!!!!optimization 16 start ="+new Date());
				uniqueGeneNamesForGene=new ArrayList<PatientCountForDrugGene>();
				for(PatientCountForDrugGene drugGeneList : patientDrugGeneChartList){
					PatientCountForDrugGene drugGeneData = new PatientCountForDrugGene();
					drugGeneData.setGeneSymbol(drugGeneList.getGeneSymbol());
					drugGeneData.setMedicineName("");
					drugGeneData.setPatientCount(drugGeneList.getPatientCount());
					drugGeneData.setAttentionRating(drugGeneList.getAttentionRating());
					if(!uniqueGeneNamesForGene.contains(drugGeneData)){
						uniqueGeneNamesForGene.add(drugGeneData);
					}			
					//logger.info(" Gene GeneSymbol:::::::::"+drugGeneData.getGeneSymbol()+"MedicineName:::::::::"+drugGeneData.getMedicineName()+
							//"noOfPatients::::::"+drugGeneData.getPatientCount()+"impact:::::::: "+drugGeneData.getAttentionRating());
				}
				String uniqueGeneNameData= new Gson().toJson(uniqueGeneNamesForGene);
				context.addCallbackParam("uniqueGeneName", uniqueGeneNameData);
				logger.info("!!!!!optimization 16 ends ="+new Date());
				
				logger.info("!!!!!optimization 17 start ="+new Date());
				FacesContext fcontext = FacesContext.getCurrentInstance();
				UserManageBean userManageBean = (UserManageBean) fcontext.getApplication().getELResolver().getValue(fcontext.getELContext(), null, "userManageBean");
				userManageBean.backgroungLoadingForCharts();
				logger.info("!!!!!optimization 17 ends ="+new Date());
				
				logger.info("!!!!!optimization 18 start ="+new Date());
				ReportManageBean reportManageBean =(ReportManageBean) fcontext.getApplication().getELResolver().getValue(fcontext.getELContext(), null, "reportManageBean");
				reportManageBean.aco28MeasureValue();
				logger.info("!!!!!optimization 18 ends ="+new Date());
				
				logger.info("!!!!!optimization 19 start ="+new Date());
				reportManageBean.patientAnalyticsDetails();
				logger.info("!!!!!optimization 19 ends ="+new Date());
         /* fetching data for network chart
          * MODIFY BY SAURABH FOR OPTIMISING NETWORK CHART CALL TWO TIMES ON 25-11-2015*/
				logger.info("!!!!!optimization 20 start ="+new Date());
				int logedUserId=new ContextUtil().getLoginId();
				String logedUserFirstName=new ContextUtil().getLoggerFirstName();
				String logedUserLastName=new ContextUtil().getLoggerLastName();
				//String testNetworkChartList=getNetworkChartListFull();fd
				//System.out.println("logedUserFirstName---------------- "+logedUserFirstName+"logedUserLastName-------------------- "+logedUserLastName);
				networkChartList=dashBoardService.findNetworkChart(1,logedUserId,logedUserFirstName,logedUserLastName);
				//System.out.println("Total Connected Doctor in network are "+total_connectedDoctor);
				logger.info("!!!!!!! NetworkChartList size::::"+networkChartList.size());
				String networkChart = new Gson().toJson(networkChartList);
				context.addCallbackParam("networkworkChartData", networkChart);
				logger.info("!!!!!optimization 20 ends ="+new Date());
		//added on 17/dec/2014 for psychopharmclinic chart
				logger.info("!!!!!optimization 21 start ="+new Date());
				String highLithium="phi";
				String mediumLithium="pmi";
				String lowLithium="pli";
				numberofpatientforlithiumHigh=dashBoardService.findnumberofpatientforlithiumHighforintiationphase(highLithium);
				numberofpatientforlithiumMedium=dashBoardService.findnumberofpatientforlithiumMediumforintiationphase(mediumLithium);
				numberofpatientforlithiumLow=dashBoardService.findnumberofpatientforlithiumlowforintiationphase(lowLithium);
				//logger.info("numberofpatientforlithiumHigh size "+numberofpatientforlithiumHigh.size());
				//logger.info("numberofpatientforlithiumMedium size "+numberofpatientforlithiumMedium.size());
				//logger.info("numberofpatientforlithiumLow size "+numberofpatientforlithiumLow.size());
				context.addCallbackParam("numberofpatientforlithiumHigh", numberofpatientforlithiumHigh.size());
				context.addCallbackParam("numberofpatientforlithiumMedium", numberofpatientforlithiumMedium.size());
				context.addCallbackParam("numberofpatientforlithiumLow", numberofpatientforlithiumLow.size());
				logger.info("!!!!!optimization 21 ends ="+new Date());
		 }


		 /***
		  * 
		  * Added By:Nagaraj
		  * Date : 01/Jun/2015
		  * Method used to load charts on deferred loading(Remote command) for PHARMA role
		  * @return
		  */
		 
		 public void pharmaDashboardChartLoading(){
			 logger.info("!!!!!!!!!optimizing pharma screen starts::::::"+ new Date());
			 int providerId=new ContextUtil().getProviderId();
			 RequestContext context=RequestContext.getCurrentInstance();
			 logger.info("{}{}{}{} --- Charts loading from background thru async remote command--- {}{}{}{}"+providerId);
			 logger.info("!!!!!!!!!optimizing 51 starts::::::"+ new Date());
			List<ChartModel>genderPieChartData=dashBoardService.getGenderPieChartData(providerId);
			logger.info("!!!!!!!!!optimizing 51 endsssssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 52 starts::::::"+ new Date());
			List<ChartModel>ageSplitChartData=dashBoardService.getAgeSplitChartData(providerId);
			logger.info("!!!!!!!!!optimizing 51 endssssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 53 starts::::::"+ new Date());
			List<ChartModel>patientsOnRA_Drugs=dashBoardService.getNoOfPatientsOnRAdrugs(providerId);
			logger.info("!!!!!!!!!optimizing 53 endssssssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 54 starts::::::"+ new Date());
			List<ChartModel>enbrelConcurrentMeds=dashBoardService.getEnbrelConcurrentMeds(providerId);
			logger.info("!!!!!!!!!optimizing 54 endsssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 55 starts::::::"+ new Date());
			List<ChartModel>timeOnEnbrelData=dashBoardService.getTimeOnEnbrelData(providerId);
			logger.info("!!!!!!!!!optimizing 55 endssssssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 56 starts::::::"+ new Date());
			  /* fetching data for network chart*/
			int logedUserId=new ContextUtil().getLoginId();
			String logedUserFirstName=new ContextUtil().getLoggerFirstName();
			String logedUserLastName=new ContextUtil().getLoggerLastName();
			logger.info("logedUserFirstName---------------- "+logedUserFirstName+"logedUserLastName-------------------- "+logedUserLastName);
			networkChartList=dashBoardService.findNetworkChart(1,logedUserId,logedUserFirstName,logedUserLastName);
			System.out.println("Total Connected Doctor in network are "+total_connectedDoctor);
			
			String networkChart = new Gson().toJson(networkChartList);
			context.addCallbackParam("networkworkChartData", networkChart);
			logger.info("!!!!!!!!!optimizing 56 endssssssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 57 starts::::::"+ new Date());
			context.addCallbackParam("genderPieChartData", new Gson().toJson(genderPieChartData));
			 FacesContext fcontext = FacesContext.getCurrentInstance();
			 logger.info("!!!!!!!!!optimizing 57 endsssssss::::::"+ new Date());
			 
			 logger.info("!!!!!!!!!optimizing 58 starts::::::"+ new Date());
				UserManageBean userManageBean = (UserManageBean) fcontext.getApplication().getELResolver().getValue(fcontext.getELContext(), null, "userManageBean");
				userManageBean.loadDoctorProfileDetail();
			logger.info("!!!!!!!!!optimizing 58  endssssssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 59 starts::::::"+ new Date());
				userManageBean.loadInsuranceChart();
			logger.info("!!!!!!!!!optimizing 59 endssssssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 60 starts::::::"+ new Date());
			context.addCallbackParam("ageSplitChartData", new Gson().toJson(ageSplitChartData));
			logger.info("!!!!!!!!!optimizing 60 endssssssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 61 starts::::::"+ new Date());
			context.addCallbackParam("patientsOnRAdrugs", new Gson().toJson(patientsOnRA_Drugs));
			logger.info("!!!!!!!!!optimizing 61 endssssssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 62 starts::::::"+ new Date());
			context.addCallbackParam("pharmaEnbrelConcurrentMedsData", new Gson().toJson(enbrelConcurrentMeds));
			logger.info("!!!!!!!!!optimizing 62 endssssssss::::::"+ new Date());
			
			logger.info("!!!!!!!!!optimizing 63 starts::::::"+ new Date());
			context.addCallbackParam("timeOnEnbrelData", new Gson().toJson(timeOnEnbrelData));
			logger.info("!!!!!!!!!optimizing 63 endssssssss::::::"+ new Date());
			 logger.info("{}{}{}{} ------ Finished loading pharma dashboard from background --------- {}{}{}{}");
			 logger.info("!!!!!!!!!optimizing pharma screen endsssssssss::::::"+ new Date());
		 }
		 

		//Method to fetch list for Heatmap by clicked drug-gene detail
			public String fetchPatientListByDrugGene()
			{
			/*System.out.println("Going to fetch list of patient for provided detail in bean after click ");*/
			getListforDrugGeneInteractionPatient();
			RequestContext.getCurrentInstance().execute("PF('dlgForHeatMapStatusLow').show()");
				return null;
			}
			
			/**
			 * This Method will be trigrred from UI for age split chart when user interacts with graph
			 * 
			 */
			public void fetchPateintsForAgeSplit(){
				int providerId=new ContextUtil().getProviderId();
				int lowRangeValue=0;
				int highRangeValue=0;
				try{
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					lowRangeValue=Integer.parseInt(chartModel.getLowRangeVal());
					highRangeValue=Integer.parseInt(chartModel.getHighRangeVal());
					pateintListForComplianceMeterList=dashBoardService.fetchPateintsForAgeSplit(providerId,lowRangeValue,highRangeValue);
					logger.info("no.of patients "+pateintListForComplianceMeterList.size()+"Fetch Patients According to Low range "+chartModel.getLowRangeVal()+"and High Range "+chartModel.getHighRangeVal()+" for provider "+providerId);
					userLoginDetailList=pateintListForComplianceMeterList;
					openDialogBox();		
							
				}catch(NumberFormatException nfe){
					logger.error("ERROR :converting String to Int {Low Range Age Value } "+lowRangeValue +" {High Range Age value} "+highRangeValue);
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					nfe.printStackTrace();
				}catch(Exception e){
					logger.error("ERROR :"+e.getMessage().toString());
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					e.printStackTrace();
				}
			}
			
			/**
			 * This Method will be trigrred from UI for gender split chart when user interacts with graph
			 * 
			 */
			public void fetchPateintsForGenderSplit(){
				int providerId=new ContextUtil().getProviderId();
				int lowRangeValue=0;
				int highRangeValue=0;
				try{
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					
					pateintListForComplianceMeterList=dashBoardService.fetchPateintsForGenderSplit(providerId,chartModel.getRangeName());
					logger.info("no.of patients "+pateintListForComplianceMeterList.size()+"Fetch Patients According to Low range "+chartModel.getRangeName()+" for provider "+providerId);
					userLoginDetailList=pateintListForComplianceMeterList;
					openDialogBox();		
							
				}catch(NumberFormatException nfe){
					logger.error("ERROR :converting String to Int {Low Range Age Value } "+lowRangeValue +" {High Range Age value} "+highRangeValue);
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					nfe.printStackTrace();
				}catch(Exception e){
					logger.error("ERROR :"+e.getMessage().toString());
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					e.printStackTrace();
				}
			}
			
			public void fetchPatientsForTimeOnEnbrel(){
				int providerId=new ContextUtil().getProviderId();
				logger.info("Selected time period for Time on enbrel "+chartModel.getRangeName()+" related drug id "+chartModel.getPatients());
				try{
					String patientIds=StringUtils.trim(chartModel.getPatients());
					logger.info("Patient Ids for Time on enbrel users "+patientIds);
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					pateintListForComplianceMeterList=dashBoardService.getPatientInfoForTimeOnEnbrel(providerId,patientIds);
					logger.info("no.of patients "+pateintListForComplianceMeterList.size()+"Fetch Patients According to  "+chartModel.getRangeName()+" for provider "+providerId);
					userLoginDetailList=pateintListForComplianceMeterList;
					openDialogBox();
				}catch(Exception e){
					logger.error("ERROR :"+e.getMessage().toString());
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					e.printStackTrace();
				}
					
			}

			public void fetchPatientsForEnbrelConcurrentMeds(){
				logger.info("Selected drug for Enbrel concurrent meds "+chartModel.getRangeName());
				int providerId=new ContextUtil().getProviderId();
				logger.info("Selected time period for Time on enbrel "+chartModel.getRangeName()+" related drug id "+chartModel.getDrugId());
				try{
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					pateintListForComplianceMeterList=dashBoardService.fetchPatientsForEnbrelConcurrentMeds(providerId,chartModel.getRangeName(),chartModel.getDrugId(),chartModel.getDrugNameId());
					logger.info("no.of patients "+pateintListForComplianceMeterList.size()+"Fetch Patients According to  "+chartModel.getRangeName()+" for provider "+providerId);
					userLoginDetailList=pateintListForComplianceMeterList;
					openDialogBox();
				}catch(Exception e){
					logger.error("ERROR :"+e.getMessage().toString());
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					e.printStackTrace();
				}
			}
			
			public void fetchPatientsForOtherEnbrelRAdrugs(){
				logger.info("Selected drug for fetchPatientsForOtherEnbrelRAdrugs "+chartModel.getRangeName());
				int providerId=new ContextUtil().getProviderId();
				logger.info("Selected time period for Time on enbrel "+chartModel.getRangeName()+" related drug id "+chartModel.getDrugId());
				try{
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					pateintListForComplianceMeterList=dashBoardService.fetchPatientsForRAdrug(providerId,chartModel.getRangeName(),chartModel.getDrugId());
					logger.info("no.of patients "+pateintListForComplianceMeterList.size()+"Fetch Patients According to  "+chartModel.getRangeName()+" for provider "+providerId);
					userLoginDetailList=pateintListForComplianceMeterList;
					openDialogBox();
				}catch(Exception e){
					logger.error("ERROR :"+e.getMessage().toString());
					userLoginDetailList=new ArrayList<UserLoginDetail>();
					pateintListForComplianceMeterList=new ArrayList<UserLoginDetail>();
					e.printStackTrace();
				}
				
			}
			
private void openDialogBox() {
	RequestContext.getCurrentInstance().execute("complianceDrillDown.show()");
				
			}
private String drugNameFromHeatMap;
private String geneNameFromHeatMap;

	public String getDrugNameFromHeatMap() {
	return drugNameFromHeatMap;
}



public void setDrugNameFromHeatMap(String drugNameFromHeatMap) {
	this.drugNameFromHeatMap = drugNameFromHeatMap;
}



public String getGeneNameFromHeatMap() {
	return geneNameFromHeatMap;
}



public void setGeneNameFromHeatMap(String geneNameFromHeatMap) {
	this.geneNameFromHeatMap = geneNameFromHeatMap;
}



	public List<UserLoginDetail> getListforDrugGeneInteractionPatient() {
		int logedUserId=new ContextUtil().getLoginId();
		List<UserLoginDetail> listforHeatMapPatient = new ArrayList<UserLoginDetail>();
		/*drugNameFromHeatMap="warfarin";
		geneNameFromHeatMap="cyp2c9";*/
		int limitFlag=10;
		listforHeatMapPatient=dashBoardService.fetchListForDrugGeneInteraction(limitFlag,logedUserId,drugNameFromHeatMap,geneNameFromHeatMap,new ContextUtil().getProviderId());
		
		listforDrugGeneInteractionPatient=listforHeatMapPatient;
		userLoginDetailList=listforDrugGeneInteractionPatient;
//		System.out.println("size of list for oncology in bean is "+listforOncologyPatient.size()+"::"+listforDrugGeneInteractionPatient.size()+":::::::"+levelValForOncology);
		
		return listforDrugGeneInteractionPatient;
	}



	public void setListforDrugGeneInteractionPatient(
			List<UserLoginDetail> listforDrugGeneInteractionPatient) {
		this.listforDrugGeneInteractionPatient = listforDrugGeneInteractionPatient;
	}
	
//CODE Starts for Speciality Vs Non Speciality Drug Drill Down
	private String levelVal4SPVSNONSPDrugChart;
	
	private List<SpecialityDrugCategory> specialityvsnonDrugPatientList = new ArrayList<SpecialityDrugCategory>(); // Speciality Drug Category Medication Initilization 
	
	


	public String getLevelVal4SPVSNONSPDrugChart() {
		return levelVal4SPVSNONSPDrugChart;
	}



	public void setLevelVal4SPVSNONSPDrugChart(String levelVal4SPVSNONSPDrugChart) {
		this.levelVal4SPVSNONSPDrugChart = levelVal4SPVSNONSPDrugChart;
	}
	

	public List<SpecialityDrugCategory> getSpecialityvsnonDrugPatientList() {
		logger.info("Inside Getter for Patient list speciality:"+levelVal4SPVSNONSPDrugChart);
		List<SpecialityDrugCategory> specialityvsnonDrugPatientListInternal;
		specialityvsnonDrugPatientListInternal=dashBoardService.fetchPatientBySpeciality(new ContextUtil().getProviderId(),levelVal4SPVSNONSPDrugChart);
		logger.info("Came with "+specialityvsnonDrugPatientListInternal.size()+" Patients List for "+ levelVal4SPVSNONSPDrugChart +" Medicine");
		return specialityvsnonDrugPatientListInternal;
	}



	public void setSpecialityvsnonDrugPatientList(
			List<SpecialityDrugCategory> specialityvsnonDrugPatientList) {
		this.specialityvsnonDrugPatientList = specialityvsnonDrugPatientList;
	}

	/**
	 * used in doctorProfile.jsf 
	 * open speciality vs non speciality drug dialog box 
	 * @return null 
	 */
	public String fetchPatientBySpeciality()
	{
		logger.info("Going to fetch list of patient for "+levelVal4SPVSNONSPDrugChart+" Medicine");
		getSpecialityvsnonDrugPatientList();
		RequestContext.getCurrentInstance().execute("PF('dlgForspvsnonsp').show()"); // Open the dialog box 
		logger.info("Total medincine count for "+ levelVal4SPVSNONSPDrugChart+" is "+specialityvsnonDrugPatientList.size());
		return null;
		
	}

	public PatientGuarantor getPatientGuarantor() {		
		return patientGuarantor;
	}

	public void setPatientGuarantor(PatientGuarantor patientGuarantor) {
		this.patientGuarantor = patientGuarantor;
	}	
	
	public PatientGuarantor getPatientGuarantorNewPatient() {
		return patientGuarantorNewPatient;
	}

	public void setPatientGuarantorNewPatient(
			PatientGuarantor patientGuarantorNewPatient) {
		this.patientGuarantorNewPatient = patientGuarantorNewPatient;
	}



	public UserInsuranceDetails getUserInsuranceDetail() {
		return userInsuranceDetail;
	}



	public void setUserInsuranceDetail(UserInsuranceDetails userInsuranceDetail) {
		this.userInsuranceDetail = userInsuranceDetail;
	}

	// commented for not using multiple guarantor's by venu
	/*public void addGuarantorDetail()
	{
		System.out.println("Same Guarantor detail");
		getPatientGuarantor();
	}
	
	public void addUserDetail()
	{
		System.out.println("inside add user detail");
		getUserLoginDetail();
		for(UserLoginDetail uld : userLoginDetailList)
		{
			patientGuarantor = new PatientGuarantor();
			patientGuarantor.setGuarantorFirstName(uld.getFirstName());
			patientGuarantor.setGuarantorMiddleName(uld.getMiddleName());
			patientGuarantor.setGuarantorLastName(uld.getLastName());
			patientGuarantor.setGuarantorDOB(uld.getDateOfBirth());
			patientGuarantor.setGuarantorGender(uld.getGender());
			patientGuarantor.setGuarantorAddress1(uld.getDoorNo());
			patientGuarantor.setGuarantorAddress2(uld.getStreet());
			patientGuarantor.setGuarantorCity(uld.getCity());
			patientGuarantor.setGuarantorState(uld.getState());
			patientGuarantor.setGuarantorCountry(uld.getCountry());
			patientGuarantor.setGuarantorZip(uld.getPincode());
			patientGuarantor.setGuarantorHomeTelephone(uld.getMobileNumber());
			patientGuarantor.setGuarantorRelationship("Self");
		}
	}*/	
	 
public DoctorDetail getDoctorDetail() {
	return doctorDetail;
}



public void setDoctorDetail(DoctorDetail doctorDetail) {
	this.doctorDetail = doctorDetail;
}





public ProviderDetail getProviderDetail() {
	return providerDetail;
}



public void setProviderDetail(ProviderDetail providerDetail) {
	this.providerDetail = providerDetail;
}

public void addOrganization(){
	System.out.println("inside add org method");
	//Map<String, String> findAccountSiteIdMap = new LinkedHashMap<String, String>();
	providerLocation.setPrimaryContactNumber(providerLocation.getFaxNumber());
	dashBoardService.saveOrganizationData(providerDetail,providerLocation);
	
	providerDetail= new ProviderDetail();
	providerLocation = new ProviderLocation();
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage(null, new FacesMessage("Successful",  "Organization saved Successfully " ) );
	
}

public List<OrganisationType> getOrganisationTypeList() {
	organisationTypeList = dashBoardService.organisationTypeList();
	return organisationTypeList;
}



public void setOrganisationTypeList(List<OrganisationType> organisationTypeList) {
	this.organisationTypeList = organisationTypeList;
}



public List<RoleSecurity> getRoleSecurityList() {
	roleSecurityList = dashBoardService.getRoleSecurityList();	
	return roleSecurityList;
}



public void setRoleSecurityList(List<RoleSecurity> roleSecurityList) {
	this.roleSecurityList = roleSecurityList;
}

public void editInsuranceProfile(){
	logger.info("editorInsuranceProfile:::::starts:::::in dashBoardManageBean:::::::");
	int loggedID=new ContextUtil().getPatientId();
	List<UserInsuranceDetails> list = new ArrayList<UserInsuranceDetails>();
	//UserInsuranceDetails pateintInsuranceDelete = new UserInsuranceDetails();
	UserInsuranceDetails pateintInsuranceEdit = (UserInsuranceDetails) getBindingRowDataForEditPatient().getRowData();
	pateintInsuranceEdit.setUserId(new ContextUtil().getPatientId());
	FacesContext fc = FacesContext.getCurrentInstance();
	Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	patientInsuranceIdEditProfile = Integer.parseInt(params.get("insuranceRow"));
	System.out.println("row id +++++++++ "+patientInsuranceIdEditProfile);
	
	int insuranceID=pateintInsuranceEdit.getInsuranceId();
	System.out.println("logged id**** "+loggedID+" "+insuranceID);
	userInsuranceDetailEdit=dashBoardService.getInsurance(loggedID,insuranceID);
	System.out.println("list size***** "+list.size());		
	
		
}



public void editInsuranceEditProfile(){
	logger.info("editInsuranceEditProfile:::::starts:::::in dashBoardManageBean:::::::");
	insuranceListForUpdate = new ArrayList<UserInsuranceDetails>();
	insuranceListForUpdate.add(userInsuranceDetailEdit);
	for(UserInsuranceDetails uid : insuranceListForUpdate)
	{
		System.out.println("list size of insuranceListForUpdate&&&&& "+insuranceListForUpdate.size()+" "+uid.getInsuredFirstName());
		//userInsuranceDetail.setInsuredAddress1(uid.getInsuredAddress1());
	}
}

public void addInsuranceForEditPatient()
{
	//copyOfOriginalPateintInsuranceEditProfileList = new ArrayList<UserInsuranceDetails>();
	copyOfOriginalPateintInsuranceEditProfileList.add(userInsuranceDetail);
	userInsuranceDetail = new UserInsuranceDetails();
	for(UserInsuranceDetails uid : copyOfOriginalPateintInsuranceEditProfileList)
	{
		System.out.println("copyOfOriginalPateintInsuranceEditProfileList values are***** "+uid.getInsuredFirstName());
	}
	
	
}

public UserInsuranceDetails getUserInsuranceDetailEdit() {
	return userInsuranceDetailEdit;
}



public void setUserInsuranceDetailEdit(
		UserInsuranceDetails userInsuranceDetailEdit) {
	this.userInsuranceDetailEdit = userInsuranceDetailEdit;
}

public UserInsuranceDetails getUserInsuranceDetailAdd() {
	return userInsuranceDetailAdd;
}

public void setUserInsuranceDetailAdd(
		UserInsuranceDetails userInsuranceDetailAdd) {
	this.userInsuranceDetailAdd = userInsuranceDetailAdd;
}



public List<ProviderDetail> getOrganizationList() {
	organizationList = dashBoardService.getOrganizationList();
	return organizationList;
}


public void setOrganizationList(List<ProviderDetail> organizationList) {
	this.organizationList = organizationList;
}



public ProviderDetail getProviderDetailEditObj() {
	return providerDetailEditObj;
}



public void setProviderDetailEditObj(ProviderDetail providerDetailEditObj) {
	this.providerDetailEditObj = providerDetailEditObj;
}

public void updateOrganization(){
	 dashBoardService.updateOrganizationData(providerDetailEditObj);
}

public void deleteOrganization(ProviderDetail deleteOrganization){
	dashBoardService.deleteSelectedOrg(deleteOrganization.getId());
	
}

public LoginSecurity getLoginSecurity() {
	return loginSecurity;
}



public void setLoginSecurity(LoginSecurity loginSecurity) {
	this.loginSecurity = loginSecurity;
}


public List<UserLoginDetail> getDisplayuserLoginDetailList() {
	displayuserLoginDetailList = dashBoardService.getUserLoginDetailListToDisplay();
	return displayuserLoginDetailList;
}



public void setDisplayuserLoginDetailList(
		List<UserLoginDetail> displayuserLoginDetailList) {
	this.displayuserLoginDetailList = displayuserLoginDetailList;
}



public UserLoginDetail getUserLoginDetailEditObj() {
	doctorDetailEditObj = dashBoardService.getdisplayDoctorDetailListToDisplay(userLoginDetailEditObj.getUserId());
	loginSecurityEditObj = dashBoardService.getLoginsecurityDetails(userLoginDetailEditObj.getEmail());
	return userLoginDetailEditObj;
}



public void setUserLoginDetailEditObj(UserLoginDetail userLoginDetailEditObj) {
	this.userLoginDetailEditObj = userLoginDetailEditObj;
}


public LoginSecurity getLoginSecurityEditObj() {
	return loginSecurityEditObj;
}



public void setLoginSecurityEditObj(LoginSecurity loginSecurityEditObj) {
	this.loginSecurityEditObj = loginSecurityEditObj;
}



public DoctorDetail getDoctorDetailEditObj() {
	return doctorDetailEditObj;
}



public void setDoctorDetailEditObj(DoctorDetail doctorDetailEditObj) {
	this.doctorDetailEditObj = doctorDetailEditObj;
}

public void updateUserEditedData(){
	logger.info(loginSecurityEditObj.isStatus()+"::::::updateUserEditedData called in dashboard beanS::::::"+userLoginDetailEditObj.getEmail()+":::id="+userLoginDetailEditObj.getUserId());
	loginSecurityEditObj.setRole(getSelectedRole());
	doctorDetailEditObj.setIsActive(loginSecurityEditObj.isStatus()); 
	 boolean checkSaveStatus=dashBoardService.updateUserData(userLoginDetailEditObj,doctorDetailEditObj,loginSecurityEditObj);
	 setSelectedRole(0);
	 
}

/*@author: Prasad
 * **********for adding new user from organisation tab
 * modify by: saurabh
 */
public void addSuperAdmin() throws MessagingException{
	logger.info("add admin method starts in dashboardmanage:::::::"+clinicProvider.getProviderId()+":::"+clinicProvider.getProviderLocationId());
	messageForPateintAddedDialog=new String();
	patientProviderDetail = new PatientProvider();
	boolean validatePassword=false;
	boolean checksocialSecurityNumber=false;
		if (loginSecurity.getPassword().equals(loginSecurity.getConfirmPassword())) {
			validatePassword=true;
		}
		if (StringUtils.isBlank((userLoginDetailsNewPatient.getSocialSecurityNumber()))) {
			  userLoginDetailsNewPatient.setSocialSecurityNumber(null);
		  }
	
		if (StringUtils.isNotBlank(userLoginDetailsNewPatient.getSocialSecurityNumber())) {  
			checksocialSecurityNumber=	dashBoardService.checkForsocialSecurityNumber(userLoginDetailsNewPatient.getSocialSecurityNumber());
		}
	logger.info("user ented password from Ui:::::::"+loginSecurity.getConfirmPassword()+":::"+loginSecurity.getPassword());
	logger.info("boolean vale:::::validatePassword:"+validatePassword+"::checksocialSecurityNumber:"+checksocialSecurityNumber);
	if (getSelectedRole()==2 && StringUtils.isBlank(doctorDetail.getDea()) && 
				StringUtils.isBlank(doctorDetail.getNpi()) && StringUtils.isBlank(doctorDetail.getDocLicenseNumber())) {
		RequestContext.getCurrentInstance().execute("validationMsgForAddEditUser.show()");
		}
	else {
		if (validatePassword) {
			logger.info(">>>>>>>>>>>inside validatePasswordis true<<<<<<<<<<<<<<<");
			if ((!(checksocialSecurityNumber)) ) {
				logger.info(">>>>>>>>>>>inside validatePasswordis false<<<<<<<<<<<<<<<");
				for(Integer userId:getMaxUserIdFromUserDetails()){
					userIdForNewPatient = userId+1;
				}
				logger.info(">>>>>>>>>>>value of userIdForNewPatient="+userIdForNewPatient+":::user role:"+loginSecurity.getRole()+ selectedRole);
				  loginSecurity.setRole(getSelectedRole());
				 // loginSecurity.setId(userIdForNewPatient);
				  //loginSecurity.setUserId(userLoginDetailsNewPatient.getFirstName()+userIdForNewPatient);
				  loginSecurity.setStatus(true);
				  loginSecurity.setCreatedBy(new ContextUtil().getLoginId());
				  loginSecurity.setCreatedDate(new Date());
				  loginSecurity.setPassword(passwordEncoderGeneratorServiceImpl.encryptPassword(loginSecurity.getConfirmPassword()));
				  userLoginDetailsNewPatient.setState(userLoginDetailsNewPatient.getState().toUpperCase());
				  userLoginDetailsNewPatient.setCountry(userLoginDetailsNewPatient.getCountry().toUpperCase());
				  userLoginDetailsNewPatient.setUserId(userIdForNewPatient);
				  userLoginDetailsNewPatient.setStatus(true);
				  //doctorDetail.setUserId(userIdForNewPatient);
				  doctorDetail.setIsActive(loginSecurity.isStatus());
				  //patientProviderDetail.setPatientId(userLoginDetailsNewPatient.getUserId());
				  //patientProviderDetail.setProviderId(new ContextUtil().getProviderId());
				  //clinicDoctor.setClinicProviderID(clinicProvider.getProviderId());
				  //clinicDoctor.setDoctorId(userLoginDetailsNewPatient.getUserId()) ;
				  providerUser.setProviderId(clinicProvider.getProviderId());
				  providerUser.setProviderLocationId(clinicProvider.getProviderLocationId());
				  //providerUser.setUserId(userIdForNewPatient);
				 boolean checkSaveStatus=dashBoardService.saveSuperAdminData(userLoginDetailsNewPatient,doctorDetail,loginSecurity,clinicProvider,clinicDoctor,providerUser);
				 logger.info("saving status of pateint data:::::::"+checkSaveStatus);
				 if (checkSaveStatus) {
					  RequestContext.getCurrentInstance().execute("msgForAddEditUser.show()");
					 }
					 else{
						 System.out.println("saving status of pateint data:::::::"+checkSaveStatus);
						 setMessageForPateintAddedDialog("Sorry,Your Process Has Been Failed! Please Try Again");
					 }   
					 userLoginDetailsNewPatient= new UserLoginDetail();
					 doctorDetail=new DoctorDetail();
					 patientProviderDetail= new PatientProvider();
					 clinicDoctor=new ClinicDoctor();
					 providerUser=new ProviderUser();
					 messageForPateintAddedDialog=new String();
					 loginSecurity=new LoginSecurity();
					 companyList=new ArrayList<ProviderDetail>();
					 providerLocationListForNewUser=new ArrayList<ProviderLocation>();
					 setSelectedRole(0);
			}
			else {
				setMessageForPateintAddedDialog("Social Security Number Already Exist");
			}
		}
		else {
			setMessageForPateintAddedDialog("Please Provide Same Password");
		}
	  }
}

public void deleteUser(UserLoginDetail deleteUser){
	dashBoardService.deleteSelectedUser(deleteUser.getUserId(),deleteUser.getEmail());
	
}





public List<MasterTimeZone> getTimeZoneList() {
	timeZoneList = dashBoardService.fetchTimezoneList();
	return timeZoneList;
}



public void setTimeZoneList(List<MasterTimeZone> timeZoneList) {
	this.timeZoneList = timeZoneList;
}



public ProviderLocation getProviderLocation() {
	return providerLocation;
}



public void setProviderLocation(ProviderLocation providerLocation) {
	this.providerLocation = providerLocation;
}



public List<ProviderDetail> getCompanyList() {
	companyList = dashBoardService.fetchCompanyList(new ContextUtil().getProviderId());
	return companyList;
}



public void setCompanyList(List<ProviderDetail> companyList) {
	this.companyList = companyList;
}



public List<ClinicMaster> getClinicList() {
	clinicList = dashBoardService.fetchClinicList();
	return clinicList;
}



public void setClinicList(List<ClinicMaster> clinicList) {
	this.clinicList = clinicList;
}



public ClinicProvider getClinicProvider() {
	return clinicProvider;
}



public void setClinicProvider(ClinicProvider clinicProvider) {
	this.clinicProvider = clinicProvider;
}



public ClinicDoctor getClinicDoctor() {
	return clinicDoctor;
}



public void setClinicDoctor(ClinicDoctor clinicDoctor) {
	this.clinicDoctor = clinicDoctor;
}



public List<ProviderLocation> getProviderLocationList() {
	System.out.println(">>>>>>>>>>>getProviderLocationList>>>>>>"+providerDetailEditObj.getId());
	if (providerDetailEditObj.getId()>0) {
		providerLocationList = dashBoardService.fetchProviderLocationList(providerDetailEditObj.getId(),new ContextUtil().getRole());
	}
	else if (providerDetailEditObj.getId()==0) {
		int providerId= new ContextUtil().getProviderId();
		providerLocationList = dashBoardService.fetchProviderLocationList(providerId,new ContextUtil().getRole());
	}
	return providerLocationList;
}



public void setProviderLocationList(List<ProviderLocation> providerLocationList) {
	this.providerLocationList = providerLocationList;
}

public void addBranchAddress(){
	providerLocation.setProviderId(providerDetailEditObj.getId());
	providerLocation.setAccountId(providerDetailEditObj.getAccountId());
	providerLocation.setState(providerLocation.getState().toUpperCase());
	providerLocation.setCountry(providerLocation.getCountry().toUpperCase());
	System.out.println("addBranchAddress::::country="+providerLocation.getCountry()+":::state="+providerLocation.getState());
	dashBoardService.saveBrachAddress(providerLocation);
	RequestContext.getCurrentInstance().execute("addAddressDialog.hide();");
	providerLocation = new ProviderLocation();
}

public void deleteBranchLocation(){
	logger.info("::::::::::inside deleteBranchLocation:::::::::");
	 int locationIdForDelete=0;
	 FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		System.out.println(Integer.parseInt(params.get("locId")));
		locationIdForDelete=(Integer.parseInt(params.get("locId")));
		logger.info("::::::::::inside deleteBranchLocation:::::::::"+locationIdForDelete);
		dashBoardService.deleteBranchLocation(locationIdForDelete);
	/* logger.info("::::::::::inside deleteBranchLocation:::::::::");
	 System.out.println("inside deleteBranchLocation "+location.getId());
	 dashBoardService.deleteBranchLocation(location.getId();*/
	 //FacesContext fc = FacesContext.getCurrentInstance();
		//Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		//logger.info("::::::::::inside deleteBranchLocation dashboardManageBean:::::"+Integer.parseInt(params.get("locId")));
	 //dashBoardService.deleteBranchLocation(Integer.parseInt(params.get("locId")));
}



public PasswordEncoderGeneratorServiceImpl getPasswordEncoderGeneratorServiceImpl() {
	return passwordEncoderGeneratorServiceImpl;
}



public void setPasswordEncoderGeneratorServiceImpl(
		PasswordEncoderGeneratorServiceImpl passwordEncoderGeneratorServiceImpl) {
	this.passwordEncoderGeneratorServiceImpl = passwordEncoderGeneratorServiceImpl;
}



public ProviderUser getProviderUser() {
	return providerUser;
}



public void setProviderUser(ProviderUser providerUser) {
	this.providerUser = providerUser;
}


public String getSelectedCompliancePart() {
	return selectedCompliancePart;
}


public void setSelectedCompliancePart(String selectedCompliancePart) {
	this.selectedCompliancePart = selectedCompliancePart;
}

public void fetchPateintListForCompliance(){
	logger.info("fetchPateintListForCompliance method starts in userManageBean::::::::");
	pateintListForComplianceMeterList = new ArrayList<UserLoginDetail>();
	userLoginDetailList=new ArrayList<UserLoginDetail>();
	pateintListForComplianceMeterList = userService.fetchPateintListForCompliance(selectedCompliancePart,new ContextUtil().getProviderId());
	
	for(UserLoginDetail patLi:pateintListForComplianceMeterList){
		logger.info("pateintID::: "+patLi.getUserId()+" firstname:::: "+patLi.getFirstName());
	}
	userLoginDetailList=pateintListForComplianceMeterList;
	logger.info("User Login Details List size "+userLoginDetailList.size());
	logger.info("complaincePateintList size:::::::::"+pateintListForComplianceMeterList.size());
	RequestContext.getCurrentInstance().execute("complianceDrillDown.show()");
}

/**
 * Fetch Patients for Enbrel compliance based on selected Type from The UI
 * 
 */
public void fetchPateintsForEnbrelCompliance(){
	logger.info("fetchPateintListForCompliance method starts in userManageBean::::::::");
	pateintListForComplianceMeterList = new ArrayList<UserLoginDetail>();
	userLoginDetailList=new ArrayList<UserLoginDetail>();
	pateintListForComplianceMeterList = userService.fetchPateintsForEnbrelCompliance(selectedCompliancePart,new ContextUtil().getProviderId());
	
	userLoginDetailList=pateintListForComplianceMeterList;
	logger.info("User Login Details List size "+userLoginDetailList.size());
	logger.info("complaincePateintList size:::::::::"+pateintListForComplianceMeterList.size());
	openDialogBox();
}


public List<UserLoginDetail> getPateintListForComplianceMeterList() {
	return pateintListForComplianceMeterList;
}


public void setPateintListForComplianceMeterList(
		List<UserLoginDetail> pateintListForComplianceMeterList) {
	this.pateintListForComplianceMeterList = pateintListForComplianceMeterList;
}
 /**
  * diagonoses.jsf
  * Add Patient Vital Data On Patient Intake Page 
  * 
  */
 public void addPatientVitalData(){
	 logger.info("add Patient Vital data method ");
	 String height=patientVitalForPatientIntake.getFeet()+"."+patientVitalForPatientIntake.getInch();
	 patientVitalForPatientIntake.setHeight(height);
	 patientVitalForPatientIntake.setPatientIdForVital(new ContextUtil().getPatientId());
	 patientVitalForPatientIntake.setMeasurementDate(new DateUtil().getTodayDate());
	 dashBoardService.addPatientVital(patientVitalForPatientIntake,new ContextUtil().getPatientId()); // Save Patient Vital Data In DataBase based on Patient Id  
	 FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient Vital data has been added Succesfully",  ""); // Message Display after Successfully Saved  data 
     FacesContext.getCurrentInstance().addMessage(null, message); 
     patientVitalForPatientIntake=new PatientVital();
     patientVital = new PatientVital();
     patientVital = dashBoardService
				.findParticularPatientVitalDetail(userLoginDetail.getUserId());
 	patientVital.setWeightInKG(patientVital.getWeight());
 	
 	
 }



/**
 * @return the patientVitalForPatientIntake
 */
public PatientVital getPatientVitalForPatientIntake() {
	if(patientVitalForPatientIntake==null){
		patientVitalForPatientIntake=new PatientVital();
	}
	
	return patientVitalForPatientIntake;
}



/**
 * @param patientVitalForPatientIntake the patientVitalForPatientIntake to set
 */
public void setPatientVitalForPatientIntake(
		PatientVital patientVitalForPatientIntake) {
	this.patientVitalForPatientIntake = patientVitalForPatientIntake;
}



/**
 * used  diagonoses.jsf 
 * @return the patientVitalDisplayOnPatientIntake
 */
public PatientVital getPatientVitalDisplayOnPatientIntake() {
	if(patientVitalDisplayOnPatientIntake==null){
		patientVitalDisplayOnPatientIntake=new PatientVital();
	}
	patientVitalDisplayOnPatientIntake=dashBoardService.getLatestPatientVitalDataForDispay(new ContextUtil().getPatientId()); // Patient Vital Data Based on Patient Id 
	return patientVitalDisplayOnPatientIntake;
}



/**
 * @param patientVitalDisplayOnPatientIntake the patientVitalDisplayOnPatientIntake to set
 */
public void setPatientVitalDisplayOnPatientIntake(
		PatientVital patientVitalDisplayOnPatientIntake) {
	this.patientVitalDisplayOnPatientIntake = patientVitalDisplayOnPatientIntake;
}

/*@author: saurabh
 * **********cancelAddUser
 * 
 */
public void cancelAddUser(){
	 userLoginDetailsNewPatient= new UserLoginDetail();
	 doctorDetail=new DoctorDetail();
	 patientProviderDetail= new PatientProvider();
	 clinicDoctor=new ClinicDoctor();
	 providerUser=new ProviderUser();
	patientProviderDetail = new PatientProvider();
	messageForPateintAddedDialog=new String();
	 loginSecurity=new LoginSecurity();
	 setSelectedRole(0);
}

public String getMessageForPateintAddedDialog() {
	return messageForPateintAddedDialog;
}

public void setMessageForPateintAddedDialog(String messageForPateintAddedDialog) {
	this.messageForPateintAddedDialog = messageForPateintAddedDialog;
}
/****@author saurabh
 * ****for enable/disable fields at adding new user
 * 
 */
public void selectedRoleForAddPeople(){
	if (!(getSelectedRole()==2)) {
		setDisableValueForAddUser(true);
	}	
	else if (getSelectedRole()==2) {
		setDisableValueForAddUser(false);
	}
	if (!(getSelectedRole()==1)) {
		setDisableClinicForAddingAdmin(false);
	}
	else if (getSelectedRole()==1) {
		setDisableClinicForAddingAdmin(true);
	}
}

public boolean isDisableValueForAddUser() {
	return disableValueForAddUser;
}

public void setDisableValueForAddUser(boolean disableValueForAddUser) {
	this.disableValueForAddUser = disableValueForAddUser;
}

public int getSelectedRole() {
	return selectedRole;
}

public void setSelectedRole(int selectedRole) {
	this.selectedRole = selectedRole;
}




/**
 * @return the file
 */
public UploadedFile getFile() {
   
	return file;
}

public ForgotPasswordSendingLink getForgotPasswordSendingLink() {
	return forgotPasswordSendingLink;

}




/**
 * @param file the file to set
 */
public void setFile(UploadedFile file) {
	this.file = file;
}


/**
 * used on addGroup.xhtml..
 * For uploading patient details CSv file
 * and Validating CSV file name, size & type
 * <p> 
 * @throws IOException 
 * @throws ParseException 
 * 
 */
  public void uploadFile(FileUploadEvent event) throws IOException, ParseException{
	
		String fileName=event.getFile().getFileName();
		String contentType=event.getFile().getContentType();
		String source=event.getSource().toString();
		inputStream=event.getFile().getInputstream();
		
	 /* CsvReader reader=new CsvReader(new InputStreamReader(inputStream));
	    
		
		 reader.readHeaders();*/
		
		 
		 if(event.getFile().getFileName().length()==0){
			 FacesMessage message = new FacesMessage("Error: File name is invalid !!");
			 FacesContext.getCurrentInstance().addMessage(null, message);
			 
		 }
		 else if (event.getFile().getFileName().length()>50) {
			 FacesMessage message = new FacesMessage("Error: File name is too long !!");
			 FacesContext.getCurrentInstance().addMessage(null, message);
			
			
		}
		 /*else if(!"text/csv".equals(event.getFile().getContentType())){
				FacesMessage message = new FacesMessage("Error: File type is invalid !!");
				FacesContext.getCurrentInstance().addMessage(null, message);
				
		 }
		 */
		 else if(event.getFile().getSize()>1024*1024){
				FacesMessage message = new FacesMessage("Error: File size is too big !!");
				FacesContext.getCurrentInstance().addMessage(null, message);
				
		 }
		 
		 
		 
		/* validateUploadedPatientCsvFileData(inputStream);*/
		 List<PatientUploadData>patientUploadDataList=readPatientUploadCsvFileData(inputStream);
		 dashBoardService.savePatientUploadData(patientUploadDataList,new ContextUtil().getProviderId(),new ContextUtil().getLoginId());
		 
		 if(event.getFile().getFileName().length()!=0  && !(event.getFile().getSize()>1024*1024) ){
			 FacesContext.getCurrentInstance().addMessage(null, 
			  new FacesMessage("",String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));
		
		 }
			 
		 
		    
	
		
		
		
	
 }



  /**
   * @return the inputStream
   */
  public InputStream getInputStream() {
	 
  	return inputStream;
  }



  /**
   * @param inputStream the inputStream to set
   */
  public void setInputStream(InputStream inputStream) {
  	this.inputStream = inputStream;
  }
  
/**
 * used on addGroup.xhtml
 * Save patient's detail uploaded data into DB
 * and generates respective message 
 * <p> 
 * @throws IOException 
 * @throws ParseException 
 *
*/
  public void saveUploadedPatientCsvFileData() throws IOException, ParseException{
	 
	  
	
	 
	  try {
		  String message=dashBoardService.saveUploadedCsvData(new ContextUtil().getProviderId(),new ContextUtil().getProviderLocationId());
		  if(message!=""){
			  FacesContext.getCurrentInstance().addMessage(null, 
				        new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
		  }
		  else  {
			  FacesContext.getCurrentInstance().addMessage(null, 
				        new FacesMessage(FacesMessage.SEVERITY_INFO, "Patient Data has been Loaded Successfully .", ""));
		}
		  
			
		
	} catch (NullPointerException ne) {
		String message="Getting null Value ";
		 FacesContext.getCurrentInstance().addMessage(null, 
			        new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
		ne.printStackTrace();
	}
	  
	  
	  
	  
  }
  
 /**
  * called in dashboard manageBean's method uploadFile
  * Save patient's CSV uploaded data into intermediate table
  * in a particular pre-defined format 
  * <p>
  *  @throws IOException, ParseException
  *  
  */
  public List<PatientUploadData> readPatientUploadCsvFileData(InputStream inputStream) throws IOException, ParseException{
	  
	  List<PatientUploadData> patientUploadDataList=new ArrayList<PatientUploadData>();
	  CsvReader csvReader=new CsvReader(new InputStreamReader(inputStream));
	  
	  csvReader.readHeaders();
	  while (csvReader.readRecord()) {
		  PatientUploadData patientUploadData=new PatientUploadData();
		  patientUploadData.setPatient_External_Id(csvReader.get("Source_Patient_ID"));
		  System.out.println("Source Emr Patient Id"+csvReader.get("Source_Patient_ID"));
		  patientUploadData.setPatient_First_Name(csvReader.get("Patient_First_Name").toUpperCase().trim());
		  patientUploadData.setPatient_Last_Name(csvReader.get("Patient_Last_Name").toUpperCase().trim());
		  patientUploadData.setPatient_Middle_Initial(csvReader.get("Patient_Middle_Name"));
		  patientUploadData.setPatient_State(csvReader.get("Patient_State").trim().toUpperCase());
		  String gender=csvReader.get("Patient_Gender");
		  if(gender.equalsIgnoreCase("Male")){
			  patientUploadData.setPatient_Gender("M");
		  }
		  else if (gender.equalsIgnoreCase("Female")) {
			  patientUploadData.setPatient_Gender("F");
		 }
		  else if (gender.equalsIgnoreCase("Other")) {
			  patientUploadData.setPatient_Gender("Other");
		}
		  else {
			  patientUploadData.setPatient_Gender("");
		}
		  
		patientUploadData.setPatient_Address_Line1(csvReader.get("Patient_Address_Line1").trim());
		patientUploadData.setPatient_Address_Line2(csvReader.get("Patient_Address_Line2").trim());
		patientUploadData.setPatient_Country(csvReader.get("Patient_Country").trim());
		patientUploadData.setPatient_City((csvReader.get("Patient_City")).replaceAll("\\s+", " ").trim().toUpperCase());
		patientUploadData.setPatient_Zip_Code(csvReader.get("Patient_Zip_Code"));
		patientUploadData.setPatient_Home_Telephone(csvReader.get("Patient_Home_Telephone"));
		patientUploadData.setPatient_Email(csvReader.get("Patient_Email"));
		
		/*DateFormat format=new SimpleDateFormat("dd-MM-yyyy");
		String dob=csvReader.get("Patient_Date_of_Birth");
		Date dobDate=format.parse(dob);*/
		patientUploadData.setPatient_Date_of_Birth(csvReader.get("Patient_Date_of_Birth"));
		patientUploadData.setAdminId(new ContextUtil().getLoginId());
		patientUploadData.setProviderId(new ContextUtil().getProviderId());
		patientUploadDataList.add(patientUploadData);
	}
	  
	return patientUploadDataList;
	  
  }
    
  








public void setForgotPasswordSendingLink(
		ForgotPasswordSendingLink forgotPasswordSendingLink) {
	this.forgotPasswordSendingLink = forgotPasswordSendingLink;
}

public String getForgetPassword() {
	return forgetPassword;
}

public void setForgetPassword(String forgetPassword) {
	this.forgetPassword = forgetPassword;
}

/*******@author Saurabh
 * 
 * ****for updating password using forgot password
 */
public String resetPasswordForForgotPassword() throws MessagingException{
	logger.info("##########resetPasswordRecovery method called in dashboardManageBean#############"+forgotPasswordSendingLink.getUserId());
	String msg;
	if(loginSecurity.getPassword().equals(loginSecurity.getConfirmPassword()))
	{
		String confirmPassword=passwordEncoderGeneratorServiceImpl.encryptPassword(loginSecurity.getConfirmPassword());
		dashBoardService.resetPasswordForForgotPassword(forgotPasswordSendingLink.getUserId(),confirmPassword);
		//dashBoardService.updatePasswordResetDate(forgotPasswordSendingLink.getId());
		RequestContext.getCurrentInstance().execute("passwordRecoverySuccessDlg.show();");
		loginSecurity=new LoginSecurity();
		//return "back_to_login_screen_from_reset_forgot_password";
	}
	else {
		msg="Both password should match";
		FacesContext.getCurrentInstance().addMessage("messageUpdateForNewAddMeds", new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,"")); 
		
	}
	//forgotPasswordSendingLink=new ForgotPasswordSendingLink();
	return null;
}

/*****@author SAURABH	
 * **********for checking valid forget password url & authorize user
 */
public boolean isValidForgotPasswordUrl() {
	logger.info("##############isValidForgotPasswordUrl called in dashboard manageBean##############"+forgotPasswordSendingLink.getId());
	if(forgotPasswordSendingLink.getId()==0)
	{
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		logger.info(":::1::"+req.getQueryString());
		forgotPasswordSendingLink=new ForgotPasswordSendingLink();
		forgotPasswordSendingLink=dashBoardService.checkValidForgotPasswordUrl(req.getQueryString());
		logger.info("created date::::::::"+forgotPasswordSendingLink.getCreatedDate());
		if(forgotPasswordSendingLink.getCreatedDate()==null)
		{
			showUpdatePwdForForgotPassword=true;
			return showUpdatePwdForForgotPassword;
		}
		logger.info("::::::::"+forgotPasswordSendingLink.getUserId()+"::::"+forgotPasswordSendingLink.getCreatedDate());
		
		//check 24 hr from created date..
		int noOfDay=new DateUtil().findNumberOfDays(forgotPasswordSendingLink.getCreatedDate(), new Date());
		logger.info(":11:::::noOfDay:::"+noOfDay);
		if(noOfDay>=1)
			showUpdatePwdForForgotPassword=true;
	}
	logger.info("checkShowUpdatePwd::::::::::::::::"+showUpdatePwdForForgotPassword);
	return showUpdatePwdForForgotPassword;
}

public boolean isShowUpdatePwdForForgotPassword() {
	return showUpdatePwdForForgotPassword;
}

public void setShowUpdatePwdForForgotPassword(
		boolean showUpdatePwdForForgotPassword) {
	this.showUpdatePwdForForgotPassword = showUpdatePwdForForgotPassword;
}

public int getResetForgotPasswordRequestedUserId() {
	return resetForgotPasswordRequestedUserId;
}

public void setResetForgotPasswordRequestedUserId(
		int resetForgotPasswordRequestedUserId) {
	this.resetForgotPasswordRequestedUserId = resetForgotPasswordRequestedUserId;
}
/**
 * used on addGroup.xhtml.
 * Validating the uploaded CSV file's column name & data/patient detail format
 * And generating the respective UI messages
 * <p> 
 * @throws IOException 
 * @throws ParseException 
 * 
 */
public void checkFormatOfPatientUploadedCsvFileData() throws IOException, ParseException{
	List<PatientUploadData> uploadedPatientDataList=new ArrayList<PatientUploadData>();
	 uploadedPatientDataList=dashBoardService.getUploadedPatientData(new ContextUtil().getProviderId());
	 List<PatientUploadDataErrorMessages> uploadedDataErrorList=new ArrayList<PatientUploadDataErrorMessages>();
	 patientUploadedDataErrorMessageList=new ArrayList<PatientUploadDataErrorMessages>();
	try {
		
		for(PatientUploadData patUploadData:uploadedPatientDataList) {
			PatientUploadDataErrorMessages uploadDataErrorMessages=new PatientUploadDataErrorMessages();
			String errorMessage=new String();
			  if(patUploadData.getPatient_External_Id().equals("")){
				  errorMessage+="Source Patient External Id is Empty .";
			  }
			
			  if(patUploadData.getPatient_First_Name().equals("") ){
				  
					
					errorMessage+= " First Name is Empty . ";
			  }
			  else if (patUploadData.getPatient_First_Name()!="") {
				 
				
			 }
			  if(patUploadData.getPatient_Middle_Initial()!=""){
				 
			  }
			  if(patUploadData.getPatient_Last_Name().equals("")){
				 
				 
				  errorMessage+=" Last Name is Empty . ";
			  }
			  else if (patUploadData.getPatient_Last_Name()!="") {
				  
			  }
			  if(patUploadData.getPatient_Gender().equals("") ){
				  
				  errorMessage+= " Gender is Empty .";
			  }
			  else if (patUploadData.getPatient_Gender()!="") {
				  String gender=patUploadData.getPatient_Gender();
				
			}
			  else{
				  errorMessage+="Gender is Missing";
			  }
			  
			  if(patUploadData.getPatient_Date_of_Birth().equals("")){
				  
				  
				  errorMessage+=" Patient DOB is Empty  . ";
			  }
			  else if (patUploadData.getPatient_Date_of_Birth()!="") {
				  
			   if(patUploadData.getPatient_Date_of_Birth().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")){
				   DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
					Date dob=format.parse(patUploadData.getPatient_Date_of_Birth());
					String patDob=format.format(dob);
			   }
				else  {
					  
					  
					  errorMessage+=" Patient DOB is not in Correct format (yyyy-MM-dd)  .  "; 
				}
				
				
			}
			  if(patUploadData.getPatient_Address_Line1()!=""){
				 
				  
			  }
			  if(patUploadData.getPatient_Address_Line2()!=""){
				
				 
			  }
			 if(patUploadData.getPatient_City()!=""){
				 
			  }
			  if(patUploadData.getPatient_State()==""){
				
				  errorMessage+= " State is Empty . ";
			  }else if (patUploadData.getPatient_State().length()!=2)   {
				
				 
				 
				  errorMessage+=" State  is not in postcode format(CA, MO, OR, etc) . ";
			}
			  else if (patUploadData.getPatient_State().length()==2) {
				 
			}
			 if(patUploadData.getPatient_Country().equals("")){
				
				  errorMessage+=" Country is empty . ";
			 }
			 else if (!(patUploadData.getPatient_Country().equalsIgnoreCase("US"))) {
				
				
				 errorMessage+=" Country  should default to US . ";
			}
			 else if (patUploadData.getPatient_Country().equalsIgnoreCase("US")) {
				 
				 
			}
			 if(patUploadData.getPatient_Zip_Code()==""){
				
				  
				  errorMessage+=" Zip Code is empty . ";
			 }
			 else if (patUploadData.getPatient_Zip_Code().length()!=5) {
				
				
				 
				 errorMessage+=" Zip code  should be the 5 base numbers . ";
				
			}
			 else if (patUploadData.getPatient_Zip_Code().length()==5) {
				
			}
			 uploadDataErrorMessages.setAdminId(new ContextUtil().getLoginId());
			 uploadDataErrorMessages.setErrorMessage(errorMessage);
			 uploadDataErrorMessages.setProviderId(new ContextUtil().getProviderId());
			 uploadDataErrorMessages.setSourceEmrPatientId(patUploadData.getPatient_External_Id());
			 
		/*	 uploadedDataErrorList.add(uploadDataErrorMessages);*/
			 patientUploadedDataErrorMessageList.add(uploadDataErrorMessages);
			 
		}
			
	/*	dashBoardService.saveUploadedDataErrorValue(uploadedDataErrorList,new ContextUtil().getProviderId(),new ContextUtil().getLoginId());		  */
		
			
	} catch (NullPointerException ne) {
		
		 ne.printStackTrace();
	}
 
 }



/**
 * @return the patientUploadedDataErrorMessageList
 */
public List<PatientUploadDataErrorMessages> getPatientUploadedDataErrorMessageList() {
	if(patientUploadedDataErrorMessageList==null){
		patientUploadedDataErrorMessageList=new ArrayList<PatientUploadDataErrorMessages>();
	}
	/*patientUploadedDataErrorMessageList=dashBoardService.getPatientUploadedDataErrorMessage(new ContextUtil().getProviderId(),new ContextUtil().getLoginId());*/
	return patientUploadedDataErrorMessageList;
}



/**
 * @param patientUploadedDataErrorMessageList the patientUploadedDataErrorMessageList to set
 */
public void setPatientUploadedDataErrorMessageList(
		List<PatientUploadDataErrorMessages> patientUploadedDataErrorMessageList) {
	this.patientUploadedDataErrorMessageList = patientUploadedDataErrorMessageList;
}
/****
 * *****
 * 	
 */
public void onProviderChange(){
	logger.info("############inside onProviderChange method in dashboard manage bean#############"+clinicProvider.getProviderId());
	providerLocationListForNewUser=new ArrayList<ProviderLocation>();
	List<ProviderLocation> providerLocationListForSuperAdmin= new ArrayList<ProviderLocation>();
	providerLocationListForSuperAdmin=getProviderLocationList();
	for (ProviderLocation pl : providerLocationListForSuperAdmin) {
		System.out.println("####loop onProviderChange######"+pl.getLocation()+":::"+pl.getId()+":::"+pl.getProviderId());
		if (pl.getProviderId()==clinicProvider.getProviderId()) {
			providerLocationListForNewUser.add(pl);
		}
	}
	//providerLocationListForNewUser
}



public List<ProviderLocation> getProviderLocationListForNewUser() {
	return providerLocationListForNewUser;
}



public void setProviderLocationListForNewUser(
		List<ProviderLocation> providerLocationListForNewUser) {
	this.providerLocationListForNewUser = providerLocationListForNewUser;
}



public boolean isDisableClinicForAddingAdmin() {
	return disableClinicForAddingAdmin;
}



public void setDisableClinicForAddingAdmin(boolean disableClinicForAddingAdmin) {
	this.disableClinicForAddingAdmin = disableClinicForAddingAdmin;
}










/**
 * @param progress the progress to set
 */
public void setProgress(Integer progress) {
	this.progress = progress;
}



/**
 * @return the progress
 * @throws InterruptedException 
 */
public Integer getProgress() throws InterruptedException {
	if(progress == null){
		progress =0;
	}
	 else {
		 for(int i=0;i<100;i++){
			  progress++;  
				 message="processing["+i+ "]";
				
		 }
	    
	    }  

	return progress;
  }



/**
 * @return the message
 */
public String getMessage() {
	return message;
}



/**
 * @param message the message to set
 */
public void setMessage(String message) {
	this.message = message;
}



/**
 * @return the usStateList
 */
public List<String> getUsStateList() {
	if(usStateList==null){
		usStateList=new ArrayList<String>();
		usStateList.add("CA");
		usStateList.add("AL");
		usStateList.add("AK");
		usStateList.add("AS");
		usStateList.add("AZ");
		usStateList.add("AR");
		usStateList.add("CO");
		usStateList.add("CT");
		usStateList.add("DE");
		usStateList.add("DC");
		usStateList.add("FL");
		usStateList.add("GA");
		usStateList.add("GU");
		usStateList.add("HI");
		usStateList.add("ID");
		usStateList.add("IL");
		usStateList.add("IN");
		usStateList.add("IA");
		usStateList.add("KS");
		usStateList.add("KY");
		usStateList.add("LA");
		usStateList.add("ME");
		usStateList.add("MD");
		usStateList.add("MH");
		usStateList.add("MA");
		usStateList.add("MI");
		usStateList.add("FM");
		usStateList.add("MN");
		usStateList.add("MS");
		usStateList.add("MO");
		usStateList.add("MT");
		usStateList.add("NE");
		usStateList.add("NV");
		usStateList.add("NH");
		usStateList.add("NJ");
		usStateList.add("NM");
		usStateList.add("NY");
		usStateList.add("NC");
		usStateList.add("ND");
		usStateList.add("MP");
		usStateList.add("OH");
		usStateList.add("OK");
		usStateList.add("OR");
		usStateList.add("PW");
		usStateList.add("PA");
		usStateList.add("PR");
		usStateList.add("RI");
		usStateList.add("SC");
		usStateList.add("SD");
		usStateList.add("TN");
		usStateList.add("TX");
		usStateList.add("UT");
		usStateList.add("VT");
		usStateList.add("VA");
		usStateList.add("VI");
		usStateList.add("WA");
		usStateList.add("WV");
		usStateList.add("WI");
		usStateList.add("WY");
	}
	Collections.sort(usStateList);
	return usStateList;
}



/**
 * @param usStateList the usStateList to set
 */
public void setUsStateList(List<String> usStateList) {
	this.usStateList = usStateList;
}




public ChartModel chartModel=new ChartModel();

public ChartModel getChartModel() {
	return chartModel;
}

public void setChartModel(ChartModel chartModel) {
	this.chartModel = chartModel;
}


/***********@author: saurabh
 * ************to display list of locations on click of edit provider
 * *****************by role super-admin
 */

public List<ProviderLocation> getEditProviderLocationList() {
	System.out.println(">>>>>>>>>>>getEditProviderLocationList inside dashboardManageBean>>>>>>"+providerDetailEditObj.getId());
	editProviderLocationList=dashBoardService.getEditProviderLocationList(providerDetailEditObj.getId());
	return editProviderLocationList;
}

public void setEditProviderLocationList(
		List<ProviderLocation> editProviderLocationList) {
	this.editProviderLocationList = editProviderLocationList;
}



/**
 * @return the pbmNameList
 */
public List<String> getPbmNameList() {
	if(pbmNameList==null){
		pbmNameList=new ArrayList<String>();
	}
	return pbmNameList;
}



/**
 * @param pbmNameList the pbmNameList to set
 */
public void setPbmNameList(List<String> pbmNameList) {
	this.pbmNameList = pbmNameList;
}

/**@author: SAURABH
 * ****For edit provider location data
 * 
 */
	public void editBranchLocation( ){
		 System.out.println("inside editBranchLocation::::");
		 editProviderLocationObj=new ProviderLocation();
		 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
			System.out.println(Integer.parseInt(params.get("editBranchLocationId")));
			editProviderLocationObj.setId(Integer.parseInt(params.get("editBranchLocationId")));
			editProviderLocationObj.setAccountId(params.get("editBranchLocationAcc"));
			editProviderLocationObj.setAddressLine1(params.get("editBranchLocationAddressLine1"));
			editProviderLocationObj.setAddressLine2(params.get("editBranchLocationAddressLine2"));
			editProviderLocationObj.setCity(params.get("editBranchLocationCity"));
			editProviderLocationObj.setFaxNumber(params.get("editBranchLocationfaxNumber"));
			editProviderLocationObj.setLocation(params.get("editBranchLocation"));
			editProviderLocationObj.setPrimaryPhoneNumber(params.get("editBranchLocationPrimaryPhoneNumber"));
			editProviderLocationObj.setSiteId(params.get("editBranchLocationSite"));
			editProviderLocationObj.setState(params.get("editBranchLocationState"));
			editProviderLocationObj.setZipCode(params.get("editBranchLocationZipCode"));
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('editBranchAddressDialog').show();");
			System.out.println("inside editBranchLocation::::"+editProviderLocationObj.getId()+editProviderLocationObj.getAccountId());
	}



public DataTable getEditProviderLocationDataTableObj() {
	return editProviderLocationDataTableObj;
}

public void setEditProviderLocationDataTableObj(DataTable editProviderLocationDataTableObj) {
	this.editProviderLocationDataTableObj = editProviderLocationDataTableObj;
}



public ProviderLocation getEditProviderLocationObj() {
	return editProviderLocationObj;
}



public void setEditProviderLocationObj(ProviderLocation editProviderLocationObj) {
	this.editProviderLocationObj = editProviderLocationObj;
}

/******@author SAURABH KUMAR	
 * ********to save/update edited provider data
 * 
 */
public void saveEditProviderLocationObj(){
	dashBoardService.saveEditProviderLocationObj(editProviderLocationObj);
	RequestContext.getCurrentInstance().execute("PF('editBranchAddressDialog').hide()");
}

/**
 * @author SAURABH
 * used on uploadDiagnosis.xhtml..
 * for selecting and Validating DIAGNOSIS CSV File name, extension & size.
 * And save it to intermediate table in DB
 * <p>
 * @throws IOException 
 * @throws ParseException 
 * 
 */
  public void uploadDiagnosisFile(FileUploadEvent event) throws IOException, ParseException{
	
		String fileName=event.getFile().getFileName();
		String contentType=event.getFile().getContentType();
		String source=event.getSource().toString();
		inputStream=event.getFile().getInputstream();
		 
		 if(event.getFile().getFileName().length()==0){
			 FacesMessage message = new FacesMessage("Error: File name is invalid !!");
			 FacesContext.getCurrentInstance().addMessage(null, message);
		 }
		 else if (event.getFile().getFileName().length()>50) {
			 FacesMessage message = new FacesMessage("Error: File name is too long !!");
			 FacesContext.getCurrentInstance().addMessage(null, message);
		}
		else if(event.getFile().getSize()>1024*1024){
				FacesMessage message = new FacesMessage("Error: File size is too big !!");
				FacesContext.getCurrentInstance().addMessage(null, message);
		 }
		 
		/* validateUploadedPatientCsvFileData(inputStream);*/
		 List<DiagnosisUploadData>diagnosisUploadDataList=readDiagnosisUploadCsvData(inputStream);
		 dashBoardService.saveDiagnosisUploadData(diagnosisUploadDataList,new ContextUtil().getProviderId(),new ContextUtil().getLoginId(),new ContextUtil().getProviderLocationId());
		 
		 if(event.getFile().getFileName().length()!=0  && !(event.getFile().getSize()>1024*1024) ){
			 FacesContext.getCurrentInstance().addMessage(null, 
			  new FacesMessage("",String.format("File '%s' of type '%s' successfully uploaded!", fileName, contentType)));
		
		 }
 }
  
/**
 * @author SAURABH
 * called from method uploadDiagnosisFile of dashBoardManageBean
 * It will read the column wise data from CSV file and add it to pojo class
 * <p>
 * @throws IOException 
 * @throws ParseException
 */
 public List<DiagnosisUploadData> readDiagnosisUploadCsvData(InputStream inputStream) throws IOException, ParseException{
	  List<DiagnosisUploadData> diagnosisUploadDataList=new ArrayList<DiagnosisUploadData>();
	  CsvReader csvReader=new CsvReader(new InputStreamReader(inputStream));
	  csvReader.readHeaders();
	  while (csvReader.readRecord()) {
		  DiagnosisUploadData diagnosisUploadData=new DiagnosisUploadData();
		  diagnosisUploadData.setClinakosId(csvReader.get("user_id"));
		  diagnosisUploadData.setExternalId(csvReader.get("external_id"));
		  diagnosisUploadData.setIcdCode(csvReader.get("icd_code"));
		  diagnosisUploadData.setIcdCodeType(csvReader.get("icd_code_type"));
		  diagnosisUploadData.setDateOfDiagnosis(csvReader.get("date"));
		  diagnosisUploadData.setExternalIdAvailable(csvReader.get("is_external_id"));
		  diagnosisUploadData.setDoctorNPI(csvReader.get("doctor_npi"));
		  diagnosisUploadData.setAdminId(new ContextUtil().getLoginId());
		  System.out.println("Source Emr Patient Id"+csvReader.get("external_id"));
		diagnosisUploadDataList.add(diagnosisUploadData);
	}
	return diagnosisUploadDataList;
  }
 
/**
  * @author Saurabh
  * used on uploadDiagnosis.xhtml.
  * For validating content and format of uploaded DIAGNOSIS CSV File data
  * It will also display the respective error message.
  * <p> 
  * @throws IOException 
  * @throws ParseException 
  * 
**/
 public void checkFormatOfDiagnosisUploadedCSVData() throws IOException, ParseException{
 	List<DiagnosisUploadData> uploadedDiagnosisDataList=new ArrayList<DiagnosisUploadData>();
 	uploadedDiagnosisDataList=dashBoardService.getDiagnosisUploadData(new ContextUtil().getLoginId());
 	HashMap<Integer, String> masterICD10DiagnosisMap = new HashMap<Integer, String>();
 	HashMap<Integer, String> masterICD9DiagnosisMap = new HashMap<Integer, String>();
 	masterICD10DiagnosisMap=dashBoardService.findMasterICD10DiagnosisMap();
 	masterICD9DiagnosisMap=dashBoardService.findMasterICD9DiagnosisMap();
 	diagnosisUploadedDataErrorList=new ArrayList<DiagnosisUploadDataErrorMessage>();
 	try {
 		
 		for(DiagnosisUploadData diagnosisData:uploadedDiagnosisDataList) {
 			DiagnosisUploadDataErrorMessage uploadDataErrorMessages=new DiagnosisUploadDataErrorMessage();
 			boolean icd9Check= false;
 			boolean icd10Check= false;
 			String errorMessage= new String();
	 			if (StringUtils.isBlank(diagnosisData.getExternalIdAvailable())) {
	 				errorMessage+="is_external_id is Empty .";
				}
	 			else if (diagnosisData.getExternalIdAvailable().equals("0")) {
						if (StringUtils.isBlank(diagnosisData.getClinakosId())) {
							errorMessage+="Patient's Clinakos Id is Empty .";
						}
				}
				else if(diagnosisData.getExternalIdAvailable().equals("1")){
					if (StringUtils.isBlank(diagnosisData.getExternalId())) {
						errorMessage+="Patient's External Id is Empty .";
					}
				}
				else{
					errorMessage+="is_external_id has not a valid Data. It should be 1 or 0";
				}
	 			
	 			if (StringUtils.isBlank(diagnosisData.getIcdCode())) {
	 				errorMessage+=" ICD Code is Empty  . ";
				}
	 			
	 			if (StringUtils.isNotBlank(diagnosisData.getIcdCode())) {
	 				if (diagnosisData.getIcdCodeType().equalsIgnoreCase("ICD10")) {
	 					if (!(masterICD10DiagnosisMap.containsValue(diagnosisData.getIcdCode()))) {
		 					errorMessage+=" Doesn't has valid ICD10 Code : "+diagnosisData.getIcdCode();
		 					icd10Check=true;
						}
	 					if (icd10Check) {
	 						if (masterICD9DiagnosisMap.containsValue(diagnosisData.getIcdCode())) {
			 					errorMessage+=" , ICD Code doesn't belongs to ICD10, it is listed in ICD9  ";
							}
						}
					}
	 				else if (diagnosisData.getIcdCodeType().equalsIgnoreCase("ICD9")) {
	 					if (!(masterICD9DiagnosisMap.containsValue(diagnosisData.getIcdCode()))) {
		 					errorMessage+=" Doesn't has valid ICD9 Code : "+diagnosisData.getIcdCode();
		 					icd9Check=true;
						}
	 					if (icd9Check) {
	 						 if (masterICD10DiagnosisMap.containsValue(diagnosisData.getIcdCode())) {
			 					errorMessage+=", ICD Code doesn't belongs to ICD9, it is listed in ICD10  ";
							}
						}
					}
	 				
				}
	 			
	 			if (StringUtils.isBlank(diagnosisData.getDoctorNPI()) || diagnosisData.getDoctorNPI().length()!= 10) {
	 				errorMessage+=" DoctorNPI is Empty or doesn't contains 10 character  . ";
				}
	 			if (StringUtils.isBlank(diagnosisData.getIcdCodeType())) {
	 				errorMessage+=" ICD Code Type is Empty  . ";
				}
	 			
	 			if (StringUtils.isBlank(diagnosisData.getDateOfDiagnosis())) {
	 				errorMessage+=" Date Of Diagnosis is Empty  . ";
				}
	 			
	 			else if(StringUtils.isNotBlank(diagnosisData.getDateOfDiagnosis())){
	 				if(diagnosisData.getDateOfDiagnosis().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")){
	  				   DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	  					Date dob=format.parse(diagnosisData.getDateOfDiagnosis());
	  					String patDob=format.format(dob);
	  			   }
	  				else  {
	  					  errorMessage+=" Date Of Diagnosis is not in Correct format (yyyy-MM-dd)  .  "; 
	  				}
	 			}
	 			if (StringUtils.isBlank(errorMessage)) {
	 				errorMessage="Success";					
				}
	 			if (!(errorMessage.equalsIgnoreCase("Success"))) {
		 			 uploadDataErrorMessages.setUploadedBy(new ContextUtil().getLoginId());
		 			 uploadDataErrorMessages.setErrorMessage(errorMessage);
		 			 uploadDataErrorMessages.setUploadedDate(new Date());
		 			 uploadDataErrorMessages.setExternalId(diagnosisData.getExternalId());
		 			uploadDataErrorMessages.setClinakosId(diagnosisData.getClinakosId());
		 			System.out.println("is_external_id="+diagnosisData.getExternalIdAvailable()+":::date_of_diagnosis="+diagnosisData.getDateOfDiagnosis());
		 			diagnosisUploadedDataErrorList.add(uploadDataErrorMessages);
	 			}
 		}
 		if (diagnosisUploadedDataErrorList.isEmpty() || diagnosisUploadedDataErrorList==null ) {
				logger.info(diagnosisUploadedDataErrorList.size()+"inside if block, enableDiagnosisUploadButton= "+isEnableDiagnosisUploadButton());
			setEnableDiagnosisUploadButton(true);
			setEnableDiagnosisUploadValidationMessage(true);
			logger.info("inside if block after setting true value, enableDiagnosisUploadButton= "+isEnableDiagnosisUploadButton());
		}
 	} catch (NullPointerException ne) {
 		 ne.printStackTrace();
 	}
  }

public List<DiagnosisUploadDataErrorMessage> getDiagnosisUploadedDataErrorList() {
	return diagnosisUploadedDataErrorList;
}

public void setDiagnosisUploadedDataErrorList
(List<DiagnosisUploadDataErrorMessage> diagnosisUploadedDataErrorList) {
	this.diagnosisUploadedDataErrorList = diagnosisUploadedDataErrorList;
}
 
/*****@author Saurabh
 * For pushing data from IntermediateDiagnosisTable
 * To PatientDiagnosisTable
 */
public void saveDiagnosisData(){
	  try {
		  String message=dashBoardService.saveDiagnosisData();
		  if(message!=""){
			  FacesContext.getCurrentInstance().addMessage(null, 
				        new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
		  }
		  else  {
			  FacesContext.getCurrentInstance().addMessage(null, 
				        new FacesMessage(FacesMessage.SEVERITY_INFO, "Data has been Loaded Successfully .", ""));
		}
	} catch (NullPointerException ne) {
		String message="Getting null Value ";
		 FacesContext.getCurrentInstance().addMessage(null, 
			        new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
		ne.printStackTrace();
	}
  }



public boolean isEnableDiagnosisUploadButton() {
	return enableDiagnosisUploadButton;
}



public void setEnableDiagnosisUploadButton(boolean enableDiagnosisUploadButton) {
	this.enableDiagnosisUploadButton = enableDiagnosisUploadButton;
}



public boolean isEnableDiagnosisUploadValidationMessage() {
	return enableDiagnosisUploadValidationMessage;
}



public void setEnableDiagnosisUploadValidationMessage(boolean enableDiagnosisUploadValidationMessage) {
	this.enableDiagnosisUploadValidationMessage = enableDiagnosisUploadValidationMessage;
}

}

