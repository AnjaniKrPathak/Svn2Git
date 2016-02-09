package com.clinakos.service.serviceImpl;


import static com.clinakos.common.util.ClinakosConstant.CLINAKOS_MESSAGES_LOCATION;
import static com.clinakos.common.util.ClinakosConstant.PROP_VAL_DELIM;
import https.secure_newcropaccounts_com.v7.webservices.DownloadDetail;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.web.util.HtmlUtils;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.dao.IDashBoardDAO;
import com.clinakos.data.model.core.DiagnosisUploadData;
import com.clinakos.data.model.core.EmpProviderLocation;
import com.clinakos.data.model.patient.FormularyChart;
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
import com.clinakos.data.model.patient.CareTeam;
import com.clinakos.data.model.patient.ChartModel;
import com.clinakos.data.model.patient.ClinicDoctor;
import com.clinakos.data.model.patient.ClinicMaster;
import com.clinakos.data.model.patient.ClinicProvider;
import com.clinakos.data.model.patient.DiagnosesChart;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.DrugCategoryDetail;
import com.clinakos.data.model.patient.EmployerDetails;
import com.clinakos.data.model.patient.LOVType;
import com.clinakos.data.model.patient.MasterTimeZone;
import com.clinakos.data.model.patient.NetworkChart;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientCountForDrugGene;
import com.clinakos.data.model.patient.PatientGuarantor;
import com.clinakos.data.model.patient.PatientProvider;
import com.clinakos.data.model.patient.PatientProviderClinic;
import com.clinakos.data.model.patient.SpecialityDrugCategory;
import com.clinakos.data.model.patient.UserInsuranceDetails;
/*import com.clinakos.data.model.patient.NetworkChart;*/
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientVital;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.ViewFunctionalDetails;
import com.clinakos.service.IDashBoardService;

import static com.clinakos.common.util.ClinakosConstant.*;

public class DashBoardServiceImpl implements IDashBoardService,Serializable{
	private static final long serialVersionUID = 1000101023;
	
	private IDashBoardDAO dashBoardDAO;
	public static final Logger logger = Logger.getLogger("DashBoardServiceImpl.class");
	public IDashBoardDAO getDashBoardDAO() {
		return dashBoardDAO;
	}

	public void setDashBoardDAO(IDashBoardDAO dashBoardDAO) {
		this.dashBoardDAO = dashBoardDAO;
	}
	
	public List<UserLoginDetail> searchPatient(UserLoginDetail userDetailBeforeSearch,String role) {
		
		return dashBoardDAO.searchPatient(userDetailBeforeSearch,role);
	}
	
	public List<UserLoginDetail> searchPatientNew(UserLoginDetail userDetailBeforeSearch,String role) {
		
		return dashBoardDAO.searchPatientNew(userDetailBeforeSearch,role);
	}
	/**
	 *  @see com.clinakos.service.IDashBoardService#userInsuranceDetails(int)
	 * @param userId 
	 * @return userInsurance Name  List 
	 */
	public List<String> userInsuranceDetails( int id) {
		
		return dashBoardDAO.findUserInsuranceDetails(id);
	}
	
	public List<String> findPatientProviderClinicList( int id) {
		
		return dashBoardDAO.findPatientProviderClinicList(id);
	}
	
	public List<PatientDiagnosesDetails> findDiagnosisDetailList(int patientId) {
	
		return dashBoardDAO.findDiagnosisDetailList(patientId);
	}
	
	public PatientVital findParticularPatientVitalDetail(int patientId) {
		
		return dashBoardDAO.findParticularPatientVitalDetail(patientId);
	}
	
	public List<ViewFunctionalDetails> findViewFunctionDetailList(int patientId) {
		
		return dashBoardDAO.findViewFunctionDetailList(patientId);
	}
	
	public List<LOVType> findLovTypeListInDetail() {
		
		return dashBoardDAO.findLovTypeListInDetail();
	}


	public List<PatientDiagnosesDetails> getPatientDiagnosesDetails() {
		// TODO Auto-generated method stub
		return dashBoardDAO.showPatientDiagnosesDetails();
	}


	public PatientVital getLoggedPatientVitalData() {
		// TODO Auto-generated method stub
		return dashBoardDAO.showLoggedPatientVitalData();
	}
	/*public List<LoginSecurity>getUserPassword( String email) {
		return dashBoardDAO.getUserForgetPassword(email);
	}
*/

	public String findUserPassword(String email) {
		// TODO Auto-generated method stub
		return dashBoardDAO.findUserPassword(email);
	}

	
	/*public List<UserLoginDetail> findUserDetailsForAdmin() {
		// TODO Auto-generated method stub
		return dashBoardDAO.findUserDetailsForAdmin();
	}*/

	
	public List<LoginSecurity> findDetailsForAdmin() {
		// TODO Auto-generated method stub
		return dashBoardDAO.findDetailsForAdmin();
	}

	
	public void saveNewCropDownloadDetailData(DownloadDetail downloadDetail) {
		// TODO Auto-generated method stub
		dashBoardDAO.saveNewCropDownloadDetailData(downloadDetail);
		
	}

	
	public List<DownloadDetail> getRecentDownloadDetailData() throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		return dashBoardDAO.getRecentDownloadDetailData();
	}
/*
 * MODIFIED BY SAURABH FOR SETTING PROVIDERiD & PROVIDERlOCATIONiD IN SESSION FOR ADMIN/SUPERaDMIN
 * @see com.clinakos.service.IDashBoardService#getEmpProviderLocationList()
 */
	public List<EmpProviderLocation> getEmpProviderLocationList() {
		return dashBoardDAO.getEmpProviderLocationList();
	}
	
	/**
	 * It will return Data Of Diagnosis Chart  
	 * @return Diagnosis Chart 
	 * @see com.clinakos.service.IDashBoardService#getDiagnosesChartList
	 */
	public List<DiagnosesChart> getDiagnosesChartList() {
		
		return dashBoardDAO.diagnosesDetail();
	}

	

	public List<FormularyChart>  findFormularyChart() {
		Map<String,String>formularyMAP=new HashMap<String, String>();
		try{
			formularyMAP=parsePropertyEntryDropdown(FORMULARY_ANALYTIC_MAP_TO_OLD_TEXT_REVERSE);
		}catch(Exception e){
			e.printStackTrace();
		}
		return dashBoardDAO.findFormularyChart(formularyMAP);
	}
	

	public List<PatientCountForDrugGene> getPatientDrugGeneList() {
		
		return dashBoardDAO.fetchDrugGeneData();
	}

	
	public List<NetworkChart>  findNetworkChart(int limitFlag,int logedUserId, String logedUserFirstName, String logedUserLastName) {

		return dashBoardDAO.findNetworkChart(limitFlag,logedUserId, logedUserFirstName, logedUserLastName);
	}

	
	public List<SpecialityDrugCategory> findSpecialityDrugDetails() {
		
		return dashBoardDAO.findSpecialityDrugDetails();
	}

	
	public List<DrugCategoryDetail> findDrugCategoryDetailList() {
		
		return dashBoardDAO.findDrugCategoryDetailList();
	}


	public List<UserLoginDetail> fetchPatientByFormulary(String levelVal4Formulary, int providerId)
	{
		// TODO Auto-generated method stub
		return dashBoardDAO.fetchPatientByFormulary(levelVal4Formulary,providerId);
	}

	public List<UserLoginDetail> numberofpatientforanticoagforintiationphase(
			Integer clinicProviderId, String medicinestage, int procedureType,
			String start_result, String end_result, String levelVal) {
		// TODO Auto-generated method stub
		return dashBoardDAO.numberofpatientforanticoagforintiationphase(clinicProviderId,medicinestage,procedureType,start_result,end_result,levelVal);
	}

	
	public List<Integer> findClinicAndProviderId(int loginId) {
		
		return dashBoardDAO.findClinicAndProviderId(loginId);
	}

	public boolean savepateintData(LoginSecurity loginSecurity,UserLoginDetail userLoginDetailsNewPatient, EmployerDetails employerDetails,PatientVital patientVitalNewPatient,List<PatientAllergy> newPatientAllergyList,
			PatientProvider patientProviderDetail,List<PatientDiagnosesDetails> newPateintDiagosesList,List<PatientProviderClinic> patientProviderClinicListNewPatient,
			List<UserInsuranceDetails> patientInsuranceDetailList, PatientGuarantor patientGuarantorNewPatient) {
		
	boolean check=dashBoardDAO.savepateintData(loginSecurity,userLoginDetailsNewPatient,employerDetails,patientVitalNewPatient,newPatientAllergyList,patientProviderDetail,newPateintDiagosesList,
				patientProviderClinicListNewPatient,patientInsuranceDetailList,patientGuarantorNewPatient);
	return check;
	}
	

	
	 public List<UserLoginDetail> fetchPatientBySelectedCategory(int i,String medicinestage,int procedureType,double start_result,double end_result,
	   String levelVal4Formulary, int providerId) {
	  // TODO Auto-generated method stub
	  return dashBoardDAO.fetchPatientBySelectedCategory(i,medicinestage,procedureType,start_result,end_result,levelVal4Formulary,providerId);
	 }

	
	public List<Integer> getMaxUserId() {
		
		return dashBoardDAO.getMaxUserId();
	}

	
	public boolean checkForEmailExist(String email) {
		boolean check =	dashBoardDAO.checkForEmailExist(email);
		return check;
	}

	
	public List<ClinicMaster> findClinicMasterDetail(String userInputForAddClinic) {
		
		return dashBoardDAO.findClinicMasterDetail(userInputForAddClinic);
	}

	
	public List<ClinicProvider> findclinicProviderDetail(int providerId) {
		
		return dashBoardDAO.findclinicProviderDetail(providerId);
	}


	public List<InsuranceCompanies> fetchInsuranceDetail(String userInputForAddInsurance) {
		 
		return dashBoardDAO.fetchInsuranceDetail(userInputForAddInsurance);
	}


	
	public List<PatientProviderClinic> fetchPatientClinicData(int patientId) {
		
		return dashBoardDAO.fetchPatientClinicData(patientId);
	}

	
	public EmployerDetails findPatientEmployerDetails(int userId) {
		
		return dashBoardDAO.findPatientEmployerDetails(userId);
	}

	
	
	public List<UserInsuranceDetails> fetchPatientInsuranceDetail(int patientId) {
		
		return dashBoardDAO.fetchPatientInsuranceDetail(patientId);
	}


	public List<RptDrugCategory> fetchDrugByCategoryChart(String levelVal4DrugChart,	int providerId) {
		// TODO Auto-generated method stub
		return dashBoardDAO.fetchDrugByCategoryChart(levelVal4DrugChart,providerId);
	}

	
	public void savePatientEditProfile(UserLoginDetail userLoginDetail,EmployerDetails patientEmployerDetails,PatientVital patientVital,
			List<UserInsuranceDetails> copyOfOriginalPateintInsuranceEditProfileList,List<UserInsuranceDetails> pateintInsuranceEditProfileList,List<UserInsuranceDetails> temporaryDeleteInsuranceListProfile, 
			List<PatientProviderClinic> copyOfOriginalPatientClinicDataListprofile,List<PatientProviderClinic> patientClinicDataListprofile,List<PatientProviderClinic> temporaryDeleteClinicListProfile,
			List<PatientDiagnosesDetails> editPateintProfileDiagosesList,List<PatientDiagnosesDetails> temporaryAddDiagnosesEditProfileList,List<PatientDiagnosesDetails> deleteDiagnosesEditProfileList, 
			List<PatientAllergy> allergyListForEditPatient,List<PatientAllergy> temporaryAddAllergyForProfile, List<PatientAllergy> deleteAllergyForProfilePageList, PatientGuarantor patientGuarantor,List<UserInsuranceDetails> insuranceListForUpdate) {
		
		dashBoardDAO.savePatientEditProfile(userLoginDetail,patientEmployerDetails,patientVital,
				copyOfOriginalPateintInsuranceEditProfileList,pateintInsuranceEditProfileList,temporaryDeleteInsuranceListProfile,
				copyOfOriginalPatientClinicDataListprofile,patientClinicDataListprofile,temporaryDeleteClinicListProfile,
				editPateintProfileDiagosesList,temporaryAddDiagnosesEditProfileList,deleteDiagnosesEditProfileList,
				allergyListForEditPatient,temporaryAddAllergyForProfile,deleteAllergyForProfilePageList,patientGuarantor,insuranceListForUpdate);
	}

	@Override
	public int getTotalDoctor(int limitFlag, int logedUserId,
			String logedUserFirstName, String logedUserLastName) {
		// TODO Auto-generated method stub
		return dashBoardDAO.getTotalDoctor(limitFlag,logedUserId,logedUserFirstName,logedUserLastName);
		
	}

	
	public void saveClinicProviderData(ClinicProvider clinicData) {
		dashBoardDAO.saveClinicProviderData(clinicData);
		
	}
	
	
	//For Psychopharm chart low initiation
			public List<UserLoginDetail> findnumberofpatientforlithiumlowforintiationphase(String statusID) {
				// TODO Auto-generated method stub
				return dashBoardDAO.findnumberofpatientforlithiumlowforintiationphase(statusID);
			}

			//For Psychopharm chart medium initiation
			public List<UserLoginDetail> findnumberofpatientforlithiumMediumforintiationphase(String statusID) {
				// TODO Auto-generated method stub
				return dashBoardDAO.findnumberofpatientforlithiumMediumforintiationphase(statusID);
			}

			//For Psychopharm chart high initiation
			public List<UserLoginDetail> findnumberofpatientforlithiumHighforintiationphase(String statusID) {
				// TODO Auto-generated method stub
				return dashBoardDAO.findnumberofpatientforlithiumHighforintiationphase(statusID);
			}

			// For psychopharm clinic chart while clicking the chart
			public List<UserLoginDetail> findnumberofpatientforlithium(String statusID) {
				// TODO Auto-generated method stub
				return dashBoardDAO.findnumberofpatientforlithium(statusID);
			}
			
			//For getting gender for particular patient for patient portal
			public String fetchGenderFromDB(int logedUserId) {
				
				return dashBoardDAO.fetchGenderFromDB(logedUserId);
			}

			@Override
			public List<UserLoginDetail> fetchListForOncology(String levelVal,
					int logedUserId) {
				// TODO Auto-generated method stub
				return dashBoardDAO.fetchListForOncology(levelVal,logedUserId);
			}
			@Override
			public List<UserLoginDetail> fetchListForDrugGeneInteraction(int limitFlag, int logedUserId,String drugNameFromHeatMap, String geneNameFromHeatMap,int provider_id) {
				// TODO Auto-generated method stub
				return dashBoardDAO.fetchListForDrugGeneInteraction(limitFlag, logedUserId,drugNameFromHeatMap,geneNameFromHeatMap,provider_id);
			}

			@Override
			public List<SpecialityDrugCategory> fetchPatientBySpeciality(
					int providerId, String levelVal4SPVSNONSPDrugChart) {
				// TODO Auto-generated method stub
				return dashBoardDAO.fetchPatientBySpeciality(providerId,levelVal4SPVSNONSPDrugChart);
			}
			
			@Override
			public PatientGuarantor getPatientGuarantor(int loggedPatient) {
				// TODO Auto-generated method stub
				return dashBoardDAO.getPatientGuarantor(loggedPatient);		
			}

			@Override
			public UserInsuranceDetails getInsurance(int loggedID,
					int insuranceID) {
				// TODO Auto-generated method stub
				return dashBoardDAO.getInsurance(loggedID,insuranceID);
			}
			
			@Override
			public boolean saveSuperAdminData(UserLoginDetail userLoginDetailsNewPatient,DoctorDetail doctorDetail,LoginSecurity loginSecurity,ClinicProvider clinicProvider,
					ClinicDoctor clinicDoctor, ProviderUser providerUser) {
				
			boolean check=dashBoardDAO.saveSuperAdminData(userLoginDetailsNewPatient,doctorDetail,loginSecurity,clinicProvider,clinicDoctor, providerUser);
			return check;
			}
			
			@Override
			public boolean saveOrganizationData(ProviderDetail providerDetail,ProviderLocation providerLocation) {
				boolean check=dashBoardDAO.saveOrganizationData(providerDetail,providerLocation);
				return check;
			}
			
			@Override
			public List<OrganisationType> organisationTypeList(){
				return dashBoardDAO.organisationTypeList();
			}
			
			@Override
			public List<RoleSecurity> getRoleSecurityList(){
				return dashBoardDAO.getRoleSecurityList();
			}
			
			@Override
			public List<ProviderDetail> getOrganizationList() {
				return dashBoardDAO.getOrganizationList();
			}
			
			@Override
			public boolean updateOrganizationData(ProviderDetail providerDetailEditObj){
				return dashBoardDAO.updateOrganizationData(providerDetailEditObj);
			}
			
			public boolean  deleteSelectedOrg(int orgId){
				return dashBoardDAO.deleteSelectedOrg(orgId);
			}
			
			public List<UserLoginDetail> getUserLoginDetailListToDisplay(){
				 
				return dashBoardDAO.getUserLoginDetailListToDisplay();
			 }
			public DoctorDetail getdisplayDoctorDetailListToDisplay(int userId){
				return dashBoardDAO.getdisplayDoctorDetailListToDisplay(userId);
			}
			public LoginSecurity getLoginsecurityDetails(String userId){
				return dashBoardDAO.getLoginsecurityDetails(userId);
			}
			public boolean updateUserData(UserLoginDetail userLoginDetailEditObj,DoctorDetail doctorDetailEditObj,LoginSecurity loginSecurityEditObj){
				return dashBoardDAO.updateUserData(userLoginDetailEditObj,doctorDetailEditObj,loginSecurityEditObj);
			}
			public boolean deleteSelectedUser(int userId,String emailId){
				return dashBoardDAO.deleteSelectedUser(userId,emailId);
			}
			
			public List<MasterTimeZone> fetchTimezoneList(){
				return dashBoardDAO.fetchTimezoneList();
			}
			
			public List<ProviderDetail> fetchCompanyList(int providerId){
				 return dashBoardDAO.fetchCompanyList(providerId);
			 }
			public List<ClinicMaster> fetchClinicList() {
			    return dashBoardDAO.fetchClinicList();
			 }
			 public List<ProviderLocation> fetchProviderLocationList(int providerId, String roleName){
				 return dashBoardDAO.fetchProviderLocationList(providerId, roleName); 
			 }
			 public boolean saveBrachAddress(ProviderLocation providerLocation){
				 return dashBoardDAO.saveBrachAddress(providerLocation);
			 }
			 public void deleteBranchLocation(int locId){
				  dashBoardDAO.deleteBranchLocation(locId);
			 }

			@Override
			public Map<String, String> formularyMAPtoOldText() {
				Map<String,String>formularyAnalyticMAP=new HashMap<String, String>();
				try{
					formularyAnalyticMAP=parsePropertyEntryDropdown(FORMULARY_ANALYTIC_MAP_TO_OLD_TEXT);
					
				}catch(Exception e){
					e.printStackTrace();
				}
				return formularyAnalyticMAP;
			}
			
			public Map<String, String> parsePropertyEntryDropdown(String propKey)  {
				Map<String, String> checkBoxMonitoringItems = new LinkedHashMap<String, String>();
				try{
					Properties properties = new Properties();
					properties.load(this.getClass().getClassLoader().getResourceAsStream(CLINAKOS_MESSAGES_LOCATION));	
					String propVal = properties.getProperty(propKey);
					logger.info("property value "+propVal);
					StringTokenizer propElements = new StringTokenizer(propVal, PROP_VAL_DELIM);
					String eachElement = "";	
					if ( propElements !=null) {
						for (; propElements.hasMoreElements();) {
							eachElement = HtmlUtils.htmlUnescape(propElements.nextElement().toString());
							checkBoxMonitoringItems.put(eachElement.substring(0, eachElement.indexOf(":")), eachElement.substring(eachElement.indexOf(":")+1, eachElement.length()));
						}
					}
				}catch(IOException e){
					e.printStackTrace();
				}
				return checkBoxMonitoringItems;
			}


			/**
			 * Add Patient Vital Data 
			 * @param patientVital
			 * @param patientId
			 * @see com.clinakos.service.IDashBoardService#addPatientVital(PatientVital, int)
			 */
			public void addPatientVital(PatientVital patientVital, int patientId) {
				dashBoardDAO.addPatientVital(patientVital,patientId);
				
			}


			public boolean checkForsocialSecurityNumber(
					String socialSecurityNumber) {
				return dashBoardDAO.checkForsocialSecurityNumber(socialSecurityNumber);
			}

			/**
			 * Get Patient Vital Data 
			 * @param patientId
			 * @see com.clinakos.service.IDashBoardService#getLatestPatientVitalDataForDispay(int)
			 * @return Patient Vital Object 
			 */
			public PatientVital getLatestPatientVitalDataForDispay(int patientId) {
				
				return dashBoardDAO.getLatestPatientVitalDataForDispay(patientId);
			}
			
/*************@uthor: saurabh
 * *********for saving forgot password link in forgotPasswordSendingLink
 * (non-Javadoc)
 * @see com.clinakos.service.IDashBoardService#saveforgotPasswordSendingLink(java.lang.String, int)
 */
			public void saveforgotPasswordSendingLink(ForgotPasswordSendingLink forgotPasswordSendingLink) {
				dashBoardDAO.saveforgotPasswordSendingLink(forgotPasswordSendingLink);
			}

/*****@author SAURABH	
 * **********for checking valid forget password url & authorize user
 */
			public ForgotPasswordSendingLink checkValidForgotPasswordUrl(String queryString) {
				return dashBoardDAO.checkValidForgotPasswordUrl(queryString);
			}

/*******@author Saurabh
 * 
 * ****for updating password using forgot password
*/
public void resetPasswordForForgotPassword(int userId, String confirmPassword) {
	dashBoardDAO.resetPasswordForForgotPassword(userId,confirmPassword);
}

/*******@author Saurabh
 * 
 * ****for updating password Reset Date, for forgot password
*/
public void updatePasswordResetDate(
		int forgotPasswordSendingLinkId) {
	dashBoardDAO.updatePasswordResetDate(forgotPasswordSendingLinkId);
}


public void savePatientUploadData(List<PatientUploadData> patientUploadDataList,int providerId,int loginId) {
	dashBoardDAO.savePatientUploadData(patientUploadDataList,providerId,loginId);
	
}


public String saveUploadedCsvData(int providerId,int providerLocationId) {
	 return dashBoardDAO.saveUploadedCsvData(providerId,providerLocationId);
	
}


public List<PatientUploadData> getUploadedPatientData(int providerId) {
	
	return dashBoardDAO.getUploadedPatientData(providerId);
}


public void saveUploadedDataErrorValue(
		List<PatientUploadDataErrorMessages> uploadedDataErrorList,int providerId,int adminId) {
   dashBoardDAO.saveUploadedDataErrorValue(uploadedDataErrorList,providerId,adminId);
	
}


public List<PatientUploadDataErrorMessages> getPatientUploadedDataErrorMessage(
		int providerId, int loginId) {
	
	return dashBoardDAO.getPatientUploadedDataErrorMessage(providerId,loginId);
}

@Override
public List<ChartModel> getGenderPieChartData(int providerId) {
	
	return dashBoardDAO.getGenderPieChartData(providerId,DecryptionKey);
}

@Override
public List<ChartModel> getAgeSplitChartData(int providerId) {
	
	return dashBoardDAO.getAgeSplitChartData(providerId);
}

@Override
public List<ChartModel> getNoOfPatientsOnRAdrugs(int providerId) {
	
	return dashBoardDAO.getNoOfPatientsOnRAdrugs(providerId);
}

@Override
public List<ChartModel> getEnbrelConcurrentMeds(int providerId) {
	// TODO Auto-generated method stub
	return dashBoardDAO.getEnbrelConcurrentMeds(providerId);
}

@Override
public List<UserLoginDetail> fetchPateintsForAgeSplit(int providerId,
		int lowRangeValue, int highRangeValue) {
	
	return dashBoardDAO.fetchPateintsForAgeSplit(providerId,lowRangeValue,highRangeValue);
}

@Override
public List<UserLoginDetail> fetchPateintsForGenderSplit(int providerId,
		String rangeName) {
	
	return dashBoardDAO.fetchPateintsForGenderSplit(providerId,rangeName,DecryptionKey);
}

@Override
public List<UserLoginDetail> fetchPatientsForEnbrelConcurrentMeds(
		int providerId, String rangeName, double drugId,double drugNameId) {
	
	return dashBoardDAO.fetchPatientsForEnbrelConcurrentMeds(providerId,rangeName,drugId,drugNameId,DecryptionKey);
}

@Override
public List<UserLoginDetail> fetchPatientsForRAdrug(int providerId,
		String rangeName, double drugId) {
	
	return dashBoardDAO.fetchPatientsForRAdrug(providerId,rangeName,drugId,DecryptionKey);
}

@Override
public List<ChartModel> getTimeOnEnbrelData(int providerId) {
	
	return dashBoardDAO.getTimeOnEnbrelData(providerId);
}

@Override
public List<UserLoginDetail> getPatientInfoForTimeOnEnbrel(int providerId,
		String patientIds) {
	// TODO Auto-generated method stub
	return dashBoardDAO.getPatientInfoForTimeOnEnbrel(providerId,patientIds,DecryptionKey);
}

public List<ProviderLocation> getEditProviderLocationList(int providerId) {

	return dashBoardDAO.getEditProviderLocationList(providerId);
}

/**
 * @see com.clinakos.service.IDashBoardService#getPbmNameData(int)	
 * @param userId
 * @return Pbm Name List data 
 */
public List<String> getPbmNameData(int userId) {
	
	return dashBoardDAO.getPbmNameData(userId);
}

/******@author SAURABH 	
 * ********to save/update edited provider data
 * 
 */
	public void saveEditProviderLocationObj(ProviderLocation editProviderLocationObj) {
		dashBoardDAO.saveEditProviderLocationObj(editProviderLocationObj);
		
	}
/**@author Saurabh
 * 
 * (non-Javadoc)
 * @see com.clinakos.service.IDashBoardService#saveDiagnosisUploadData(java.util.List, int, int, int)
 */
public void saveDiagnosisUploadData(List<DiagnosisUploadData> diagnosisUploadDataList, int providerId, int loginId,
		int providerLocationId) {
	dashBoardDAO.saveDiagnosisUploadData(diagnosisUploadDataList,providerId,loginId,providerLocationId);
}

/***@author Saurabh
 * For getting all the uploaded diagnosis data
 * @see com.clinakos.service.IDashBoardService#getDiagnosisUploadData(int)
 */
public List<DiagnosisUploadData> getDiagnosisUploadData(int loginId) {
	return dashBoardDAO.getDiagnosisUploadData(loginId);
}

/***@author Saurabh
 * (non-Javadoc)
 * @see com.clinakos.service.IDashBoardService#saveDiagnosisData()
 */
public String saveDiagnosisData() {
	return dashBoardDAO.saveDiagnosisData();
}

@Override
public HashMap<Integer, String> findMasterICD10DiagnosisMap() {
	return dashBoardDAO.findMasterICD10DiagnosisMap();
}

@Override
public HashMap<Integer, String> findMasterICD9DiagnosisMap() {
	return dashBoardDAO.findMasterICD9DiagnosisMap();
}

}

