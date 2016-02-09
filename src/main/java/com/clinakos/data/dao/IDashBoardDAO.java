package com.clinakos.data.dao;

import https.secure_newcropaccounts_com.v7.webservices.DownloadDetail;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;

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
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientGuarantor;
import com.clinakos.data.model.patient.PatientProvider;
import com.clinakos.data.model.patient.PatientProviderClinic;
import com.clinakos.data.model.patient.PatientVital;
import com.clinakos.data.model.patient.SpecialityDrugCategory;
import com.clinakos.data.model.patient.UserInsuranceDetails;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.ViewFunctionalDetails;


public interface IDashBoardDAO {

	//List<UserLoginDetail> finduserlistforadmin();

	List<UserLoginDetail> searchPatient(UserLoginDetail userDetailBeforeSearch,String role);
	
	List<UserLoginDetail> searchPatientNew(UserLoginDetail userDetailBeforeSearch, String role);

	List<String> findPatientProviderClinicList(int id);

	List<PatientDiagnosesDetails> findDiagnosisDetailList(int patientId);

	PatientVital findParticularPatientVitalDetail(int patientId);

	List<ViewFunctionalDetails> findViewFunctionDetailList(int patientId);

	List<LOVType> findLovTypeListInDetail();

	List<String> findUserInsuranceDetails(int id);

	List<PatientDiagnosesDetails> showPatientDiagnosesDetails();

	PatientVital showLoggedPatientVitalData();

	//List<LoginSecurity> getUserForgetPassword(String email);

	String findUserPassword(String email);

	//List<UserLoginDetail> findUserDetailsForAdmin();

	List<LoginSecurity> findDetailsForAdmin();

	void saveNewCropDownloadDetailData(DownloadDetail downloadDetail);

	List<DownloadDetail> getRecentDownloadDetailData() throws HibernateException, SQLException;

	List<EmpProviderLocation> getEmpProviderLocationList();

	List<DiagnosesChart> diagnosesDetail();

	//List<String> findUserDetailsForAdmin();
	
	List<FormularyChart> findFormularyChart(Map<String, String> formularyMAP);
	
	List<PatientCountForDrugGene> fetchDrugGeneData();
	
	List<NetworkChart> findNetworkChart(int limitFlag,int logedUserId, String logedUserFirstName, String logedUserLastName);

	List<SpecialityDrugCategory> findSpecialityDrugDetails();

	List<DrugCategoryDetail> findDrugCategoryDetailList();

	List<UserLoginDetail> fetchPatientByFormulary(String levelVal4Formulary,	int providerId);
	
	List<UserLoginDetail> numberofpatientforanticoagforintiationphase(
			Integer integer, String medicinestage, int procedureType,
			String start_result, String end_result, String levelVal);
	
	List<Integer> findClinicAndProviderId(int loginId);

	List<UserLoginDetail> fetchPatientBySelectedCategory(int i,String medicinestage,int procedureType,double start_result,double end_result,
			String levelVal4Formulary, int providerId);

	boolean savepateintData(LoginSecurity loginSecurity, UserLoginDetail userLoginDetailsNewPatient,EmployerDetails employerDetails,PatientVital patientVitalNewPatient, List<PatientAllergy> newPatientAllergyList,
			    PatientProvider patientProviderDetail, List<PatientDiagnosesDetails> newPateintDiagosesList, List<PatientProviderClinic> patientProviderClinicListNewPatient, List<UserInsuranceDetails> patientInsuranceDetailList, PatientGuarantor patientGuarantorNewPatient);

	
	List<Integer> getMaxUserId();

	boolean checkForEmailExist(String email);

	List<ClinicMaster> findClinicMasterDetail(String userInputForAddClinic);

	List<ClinicProvider> findclinicProviderDetail(int providerId);

	List<InsuranceCompanies> fetchInsuranceDetail(String userInputForAddInsurance);

	List<PatientProviderClinic> fetchPatientClinicData(int patientId);

	EmployerDetails findPatientEmployerDetails(int userId);

	List<UserInsuranceDetails> fetchPatientInsuranceDetail(int patientId);




	List<RptDrugCategory> fetchDrugByCategoryChart(String levelVal4DrugChart, int providerId);

	void savePatientEditProfile(UserLoginDetail userLoginDetail,EmployerDetails patientEmployerDetails,PatientVital patientVital,
			List<UserInsuranceDetails> copyOfOriginalPateintInsuranceEditProfileList,List<UserInsuranceDetails> pateintInsuranceEditProfileList,
			List<UserInsuranceDetails> temporaryDeleteInsuranceListProfile, List<PatientProviderClinic> copyOfOriginalPatientClinicDataListprofile,List<PatientProviderClinic> patientClinicDataListprofile,
			List<PatientProviderClinic> temporaryDeleteClinicListProfile, List<PatientDiagnosesDetails> editPateintProfileDiagosesList,List<PatientDiagnosesDetails> temporaryAddDiagnosesEditProfileList,
			List<PatientDiagnosesDetails> deleteDiagnosesEditProfileList, List<PatientAllergy> allergyListForEditPatient,List<PatientAllergy> temporaryAddAllergyForProfile, List<PatientAllergy> deleteAllergyForProfilePageList, PatientGuarantor patientGuarantor, List<UserInsuranceDetails> insuranceListForUpdate);
	
	int getTotalDoctor(int limitFlag, int logedUserId,
			String logedUserFirstName, String logedUserLastName);

	void saveClinicProviderData(ClinicProvider clinicData);
	
	//For Psychopharm chart low initiation
	List<UserLoginDetail> findnumberofpatientforlithiumlowforintiationphase(String statusID);
	
	//For Psychopharm chart medium initiation
		List<UserLoginDetail> findnumberofpatientforlithiumMediumforintiationphase(String statusID);
			
			//For Psychopharm chart high initiation
		List<UserLoginDetail> findnumberofpatientforlithiumHighforintiationphase(String statusID);
		
		// For psychopharm clinic chart while clicking the chart
		List<UserLoginDetail> findnumberofpatientforlithium(String statusID);
		
		//For getting gender for particular patient for patient portal
		String fetchGenderFromDB(int logedUserId);

		List<UserLoginDetail> fetchListForOncology(String levelVal,
				int logedUserId);

		List<UserLoginDetail> fetchListForDrugGeneInteraction(
				int limitFlag, int logedUserId, String drugNameFromHeatMap, String geneNameFromHeatMap, int provider_id);

		List<SpecialityDrugCategory> fetchPatientBySpeciality(int providerId,
				String levelVal4SPVSNONSPDrugChart);

		PatientGuarantor getPatientGuarantor(int loggedPatient);
		boolean saveSuperAdminData(UserLoginDetail userLoginDetailsNewPatient,DoctorDetail doctorDetail,LoginSecurity loginSecurity,ClinicProvider clinicProvider,
				ClinicDoctor clinicDoctor, ProviderUser providerUser) ;
		boolean saveOrganizationData(ProviderDetail providerDetail,ProviderLocation providerLocation) ;
		List<OrganisationType> organisationTypeList();
		List<RoleSecurity> getRoleSecurityList();
		List<ProviderDetail> getOrganizationList();
		boolean updateOrganizationData(ProviderDetail providerDetailEditObj);
	    boolean  deleteSelectedOrg(int orgId);

		UserInsuranceDetails getInsurance(int loggedID, int insuranceID);
		public List<UserLoginDetail> getUserLoginDetailListToDisplay();
		public DoctorDetail getdisplayDoctorDetailListToDisplay(int userId);
		public LoginSecurity getLoginsecurityDetails(String userId);
		public boolean updateUserData(UserLoginDetail userLoginDetailEditObj,DoctorDetail doctorDetailEditObj,LoginSecurity loginSecurityEditObj);
		public boolean deleteSelectedUser(int userId,String emailId);
		public List<MasterTimeZone> fetchTimezoneList();
		public List<ProviderDetail> fetchCompanyList(int providerId);
		public List<ClinicMaster> fetchClinicList();
		
		//List<ProviderUser> findProviderIdForAdmin();
		public List<ProviderLocation> fetchProviderLocationList(int providerId, String roleName);
		public boolean saveBrachAddress(ProviderLocation providerLocation);
		public void deleteBranchLocation(int locId);


		void addPatientVital(PatientVital patientVital, int patientId);


		boolean checkForsocialSecurityNumber(String socialSecurityNumber);

		PatientVital getLatestPatientVitalDataForDispay(int patientId);

		void saveforgotPasswordSendingLink(
				ForgotPasswordSendingLink forgotPasswordSendingLink);

		ForgotPasswordSendingLink checkValidForgotPasswordUrl(String queryString);

		void resetPasswordForForgotPassword(int userId, String confirmPassword);

		void updatePasswordResetDate(
				int forgotPasswordSendingLinkId);

		void savePatientUploadData(List<PatientUploadData> patientUploadDataList, int providerId, int loginId);

		String saveUploadedCsvData(int providerId, int providerLocationId);

		List<PatientUploadData> getUploadedPatientData(int providerId);

		void saveUploadedDataErrorValue(
				List<PatientUploadDataErrorMessages> uploadedDataErrorList, int providerId, int adminId);

		List<PatientUploadDataErrorMessages> getPatientUploadedDataErrorMessage(
				int providerId, int loginId);

		List<ChartModel> getGenderPieChartData(int providerId,
				String decryptionkey);

		List<ChartModel> getAgeSplitChartData(int providerId);

		List<ChartModel> getNoOfPatientsOnRAdrugs(int providerId);

		List<ChartModel> getEnbrelConcurrentMeds(int providerId);

		List<UserLoginDetail> fetchPateintsForAgeSplit(int providerId,
				int lowRangeValue, int highRangeValue);

		List<UserLoginDetail> fetchPateintsForGenderSplit(int providerId,
				String rangeName, String decryptionkey);

		List<UserLoginDetail> fetchPatientsForEnbrelConcurrentMeds(
				int providerId, String rangeName, double drugId, double drugNameId, String decryptionkey);

		List<UserLoginDetail> fetchPatientsForRAdrug(int providerId,
				String rangeName, double drugId, String decryptionkey);

		List<ChartModel> getTimeOnEnbrelData(int providerId);

		List<UserLoginDetail> getPatientInfoForTimeOnEnbrel(int providerId,
				String patientIds, String decryptionkey);

		List<ProviderLocation> getEditProviderLocationList(int providerId);

		List<String> getPbmNameData(int userId);

		void saveEditProviderLocationObj(ProviderLocation editProviderLocationObj);

		void saveDiagnosisUploadData(List<DiagnosisUploadData> diagnosisUploadDataList, int providerId, int loginId,
				int providerLocationId);

		List<DiagnosisUploadData> getDiagnosisUploadData(int loginId);

		String saveDiagnosisData();

		HashMap<Integer, String> findMasterICD10DiagnosisMap();

		HashMap<Integer, String> findMasterICD9DiagnosisMap();


}
