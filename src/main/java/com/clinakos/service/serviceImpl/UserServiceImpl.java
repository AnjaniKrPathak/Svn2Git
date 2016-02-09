package com.clinakos.service.serviceImpl;


import static com.clinakos.common.util.ClinakosConstant.DecryptionKey;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.clinakos.data.dao.IUserDao;
import com.clinakos.data.model.core.AnticoagProviderLocation;
import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.AuditPatientDeactivation;
import com.clinakos.data.model.core.FormularyDetail;
import com.clinakos.data.model.core.InsuranceCompanies;
import com.clinakos.data.model.core.PatientFormularyCompositeCopayInfo;
import com.clinakos.data.model.core.PatientFormularyCompositeDrugDetailInfo;
import com.clinakos.data.model.core.PatientPBMDrugHistoryResult;
import com.clinakos.data.model.core.PatientPBMEligibilityDetailData;
import com.clinakos.data.model.core.PatientPbmDrugHistoryDetail;
import com.clinakos.data.model.core.PracticeSearchResultSet;
import com.clinakos.data.model.core.ProviderDetail;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.core.RoleSecurity;
import com.clinakos.data.model.patient.ChartModel;
import com.clinakos.data.model.patient.ClinicMaster;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.WSDrug;
import com.clinakos.service.IUserService;
public class UserServiceImpl implements IUserService,Serializable{
	private static final long serialVersionUID = 1L;
	//UserDaoImpl userDao;
	private IUserDao userDAO;

	
	public IUserDao getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDao userDAO) {
		this.userDAO = userDAO;
	}

	
	public List<InsuranceCompanies> insuranceCompaniesList() {
		return userDAO.insuranceCompanies();
	}
	
	public List<FormularyDetail> findFormularySearchDetail(int insuranceId,String MedicineName) {
		return userDAO.findFormularySearchDetail(insuranceId,MedicineName);
	}
	
	public List<Integer> findClinicAndProviderId(int loginId) {
	
		return userDAO.findClinicAndProviderId(loginId);
	}

	public List<Integer> findnumberOfPatientParticularProvider(Integer clinicProviderId) {
	
		return userDAO.findnumberOfPatientParticularProvider(clinicProviderId);
	}
	public List<AnticoagProviderLocation> findnumberofpatientforanticoagclinic(Integer clinicProviderId, String medicinestage, int procedureType, String start_result, String end_result, String statusID) {
		
		return userDAO.findnumberofpatientforanticoagclinic(clinicProviderId,medicinestage,procedureType,start_result,end_result,statusID);
	}
	
public List<AnticoagProviderLocation> findnumberofpatientforanticoagclinicforhigerendofpatient(Integer clinicProviderId, String medicinestage, int procedureType, String start_result, String end_result, String statusID) {
		
		return userDAO.findnumberofpatientforanticoagclinicforhigerendofpatient(clinicProviderId,medicinestage,procedureType,start_result,end_result,statusID);
	}
	
public List<AnticoagProviderLocation> findnumberofpatientforanticoagclinicforhigerendofpatientforintiation(Integer clinicProviderId, String medicinestage, int procedureType, String start_result, String end_result, String statusID) {
	
	return userDAO.findnumberofpatientforanticoagclinicforhigerendofpatientforintiation(clinicProviderId,medicinestage,procedureType,start_result,end_result,statusID);
}
public List<AnticoagProviderLocation> findnumberofpatientforanticoagclinicforintiationphase(Integer clinicProviderId, String medicinestage, int procedureType, String start_result, String end_result, String statusID) {
		
		return userDAO.findnumberofpatientforanticoagclinicforintiationphase(clinicProviderId,medicinestage,procedureType,start_result,end_result,statusID);
	}
	public List<AnticoagProviderLocation> findnumberofpatientformedactionplan(Integer clinicProviderId, String medicinestage, int procedureType, String start_result, String end_result, String statusID)
	{
		return userDAO.findnumberofpatientformedactionplan(clinicProviderId,medicinestage,procedureType,start_result,end_result,statusID);
	}
	public List<AnticoagProviderLocation> findnumberofpatientformedactionplanforgraterrange(Integer clinicProviderId, String medicinestage, int procedureType, String start_result, String end_result, String statusID)
	{
		return userDAO.findnumberofpatientformedactionplanforgraterrange(clinicProviderId,medicinestage,procedureType,start_result,end_result,statusID);
	}
	public List<AnticoagProviderLocation> findnumberofpatientformedactionplanforgraterrangeforintiationphase(Integer clinicProviderId, String medicinestage, int procedureType, String start_result, String end_result)
	{
		return userDAO.findnumberofpatientformedactionplanforgraterrangeintiationphase(clinicProviderId,medicinestage,procedureType,start_result,end_result);
	}
	
	public List<AnticoagProviderLocation> findnumberofpatientformedactionplanforintiationphase(Integer clinicProviderId, String medicinestage, int procedureType, String start_result, String end_result, String statusID)
	{
		return userDAO.findnumberofpatientformedactionplanforintiationphase(clinicProviderId,medicinestage,procedureType,start_result,end_result,statusID);
	}
	
	public List<Integer> findnumberofpatientinrlab(Integer clinicProviderId)
	{
		return userDAO.findnumberofpatientinrlab(clinicProviderId);
	}
	
	public List<AnticoagProviderLocation> findnumberofpatientinrlabforinitiationphase(Integer clinicProviderId)
	{
		return userDAO.findnumberofpatientinrlabforintiationphase(clinicProviderId);
	}
	
	public List<Object> findInsuranceGraphDetail(int clinicProviderId,List<Integer> numberOfPatientParticularProvider,List<InsuranceCompanies> insuranceCompaniesList) {

		return userDAO.findInsuranceGraphDetail(clinicProviderId,numberOfPatientParticularProvider,insuranceCompaniesList);
	}

	public List<Object>  findClinicPieChart(int clinicProviderId) {

		return userDAO.findClinicPieChart(clinicProviderId);
	}
   /**
    * used in showing Average Med on doctor profile Page 
    * @return getAveragemedicine Per Patient
    *  
    */
	public int findaverageMedicinePerPatient(List<Integer>  numberOfPatientParticularProviser) {

		return userDAO.findaverageMedicinePerPatient(numberOfPatientParticularProviser);
	}

	public int findaverageDiagnoses(List<Integer> numberOfPatientParticularProvider) {
		
		return userDAO.findaverageDiagnoses(numberOfPatientParticularProvider);
	}
    /**
     * Find total no Physician in Provider Per Patient  
     * @return  Avg Phsician Per Patient 
     * 
     */
	public int findaveragPhysicians(int toalNoOfPatient, int providerId) {
	
		return userDAO.findaveragPhysicians(toalNoOfPatient,  providerId);
	}

	public int findPharmacogenomicsPercentage(List<Integer> numberOfPatientParticularProvider) {
		
		return userDAO.findPharmacogenomicsPercentage(numberOfPatientParticularProvider);
	}

	public int findAveragePharmacyPerPatient(List<Integer> numberOfPatientParticularProvider) {
	
		return userDAO.findAveragePharmacyPerPatient(numberOfPatientParticularProvider);
	}

	
	public List<Object> findAllComplianceDetails() {
		// TODO Auto-generated method stub
		return userDAO.findAllComplianceDetails();
	}

	/**
	 * @return List of Compliance Meter Data 
	 */
	public List<BigInteger> findPatientMedicationDataListAccordingToProvider(
			int providerId) {
		// TODO Auto-generated method stub
		return userDAO.findPatientMedicationDataListAccordingToProvider(providerId);
	}

	public List<ClinicMaster> findClinicMasterList() {
		return userDAO.findClinicMasterList();
	}

	
	public Double getDrugIdByDrugName(String medicineForFormularySearch) {
		// TODO Auto-generated method stub
		return userDAO.getDrugIdByDrugName(medicineForFormularySearch);
	}


	public DoctorDetail findDoctorDetailData() {
		// TODO Auto-generated method stub
		return userDAO.findDoctorDetailData();
	}

	
	public UserLoginDetail findPatientDetailData() {
		// TODO Auto-generated method stub
		return userDAO.findPatientDetailData();
	}


	public ArrayList<String> getAllDrugIdBySelectedFormularyDrug(
			FormularyDetail[] selectedFormularyDrug) {
		// TODO Auto-generated method stub
		return userDAO.getAllDrugIdBySelectedFormularyDrug(selectedFormularyDrug);
	}


	public List<WSDrug> findAllDrugDoseComboForFormularyMedicine(
			String medicineForFormularySearch) {
		// TODO Auto-generated method stub
		return userDAO.findAllDrugDoseComboForFormularyMedicine(medicineForFormularySearch);
		
		
	}


	public List<InsuranceCompanies> getInsuranceDetail(
			String insuranceCompanyForFormularySearch) {
		// TODO Auto-generated method stub
		return userDAO.getInsuranceDetail(insuranceCompanyForFormularySearch);
	}

	
	public ProviderLocation findProviderLocationDetail(int providerId) {
		// TODO Auto-generated method stub
		return userDAO.findProviderLocationDetail(providerId);
	}

 /**
  * Send Email Notification when web services will be failed 
  */
	public void saveWSFailStatus(AuditInfo auditInfo) {
		// TODO Auto-generated method stub
		System.out.println("Fail ws statuss  In user service impl " );
		try {
			DoctorDetail doctorDetail=userDAO.findDoctorDetailData();
			String emailId=doctorDetail.getEmailId();
			String subject="New Crop Services has been failed";
			String message=auditInfo.getComment();
			//new NotificationUtil().sendMail(emailId , subject,message);
			userDAO.saveAuditValue(auditInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	
	public RoleSecurity findRoleDetails(int loginId) {
		// TODO Auto-generated method stub
		return userDAO.findRoleDetails(loginId);
	}

	
	public Double getDrugIdByDrug(String medicineForFormulary) {
		// TODO Auto-generated method stub
		return userDAO.getDrugByDrug(medicineForFormulary);
	}

	
	public void savePatientPBMDrugHistoryDetailResult(
			PatientPbmDrugHistoryDetail patientPbmDrugHistoryDetail) {
		 userDAO.savePatientPBMDrugHistoryDetailResult(patientPbmDrugHistoryDetail);
		
	}

	
	public List<UserLoginDetail> getAllPatientDetailList() {
		// TODO Auto-generated method stub
		return userDAO.getAllPatientDetailList();
	}

	
	public List<PatientPbmDrugHistoryDetail> getAllPatientPbmDrugHistoryDetail(int patientId) {
		// TODO Auto-generated method stub
		return userDAO.getAllPatientPbmDrugHistoryDetail(patientId);
	}

	
	public void savePatientPBMDrugHistoryResult(
			PatientPBMDrugHistoryResult drugHistoryResult) {
		userDAO.savePatientPBMDrugHistoryResult(drugHistoryResult);
		
	}


	public List<PatientPBMDrugHistoryResult> getAllPatientPBMDrugHistoryResultValue(int patientId) {
		return userDAO.getAllPatientPBMDrugHistoryResultValue(patientId);
		
	}

	
	public void savePatientFormularyCompositeDrugDetailInfoData(
			ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeDrugDetailInfoList) {
		userDAO.savePatientFormularyCompositeDrugDetailInfoData(formularyCompositeDrugDetailInfoList);
		
	}

	
	public void savePatientFormularyCompositeCopayInfoData(
			ArrayList<PatientFormularyCompositeCopayInfo> formularyCompositeCopayInfoList) {
		
		userDAO.savePatientFormularyCompositeCopayInfoData(formularyCompositeCopayInfoList);
	}

	
	public ProviderDetail getProviderDetailData(int providerId) {
		// TODO Auto-generated method stub
		return userDAO.getProviderDetailData(providerId);
	}

	public void savePatientPBMEligibilityDetailData(
			List<PatientPBMEligibilityDetailData> pbmEligibilityDetailDataList) {
		 userDAO.savePatientPBMEligibilityDetailData(pbmEligibilityDetailDataList);
		
	}

	
	public void updateHealthPlanAndPharmacyDetail(int userId) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		userDAO.updateHealthPlanAndPharmacyDetailData(userId);
		
	}


	public void careTeamIntegration(int userId) {
		// TODO Auto-generated method stub
		userDAO.careTeamintegration(userId);
		
	}


	public List<UserLoginDetail> getPatientDetailList(int patientId) {
		// TODO Auto-generated method stub
		return userDAO.getPatientDetailList(patientId);
	}

	
	public void deletePatientPBMDrugHistoryDetailResult(int patientId) {
		// TODO Auto-generated method stub
		userDAO.deletePatientPBMDrugHistoryDetailResult(patientId);
		
	}

	
	public void deletePatientPBMDrugHistoryResult(int patientId) {
		userDAO.deletePatientPBMDrugHistoryResult(patientId);
		
	}

	/*@Override
	public List<AnticoagProviderLocation> numberofpatientforanticoagforintiationphase(
			Integer clinicProviderId, String medicinestage, int procedureType,
			String start_result, String end_result) {
		// TODO Auto-generated method stub
		return userDAO.numberofpatientforanticoagforintiationphase(clinicProviderId,medicinestage,procedureType,start_result,end_result);
	}*/
	
	public List<UserLoginDetail> numberofpatientforanticoagforintiationphase(
			Integer clinicProviderId, String medicinestage, int procedureType,
			String start_result, String end_result) {
		// TODO Auto-generated method stub
		return userDAO.numberofpatientforanticoagforintiationphase(clinicProviderId,medicinestage,procedureType,start_result,end_result);
	}


	public List<UserLoginDetail> fetchPateintListForCompliance(
			String selectedCompliancePart, int providerId) {
		return userDAO.fetchPateintListForCompliance(selectedCompliancePart,providerId);
	}

	@Override
	public void updateComplianceValue() {
		userDAO.updateComplianceValue();
		
	}

	public void deletePatientDrugData(int userId) {
		userDAO.deletePatientDrugData(userId);
	}

	public List<UserLoginDetail> getPatientList() {
		return userDAO.getPatientList();
	}

	
	public List<UserLoginDetail> getpatientDataWithPatientConsentForDataPullProcess(
			int providerId, int providerLocationId) {
		
		return userDAO.getpatientDataWithPatientConsentForDataPullProcess(providerId,providerLocationId);
	}

	
	public List<Date> getAllPatientBatchDataData(int providerId,
			int providerLocationId) {
		
		return userDAO.getAllPatientBatchDataData(providerId,providerLocationId);
	}

	
	public List<Integer> getPatientBatchNumberData(
			Date patientBatchCreatedDate, int providerId, int providerLocationId) {
		
		return userDAO.getPatientBatchNumberData(patientBatchCreatedDate,providerId,providerLocationId);
	}

	
	public List<UserLoginDetail> getPatientDataWithPatientConsentByBatchNoFilter(
			int providerId, int providerLocationId, String patientBatchDate,
			int patientBatchNo,char patientConsent) {
		
		return userDAO.getPatientDataWithPatientConsentByBatchNoFilter(providerId,providerLocationId,patientBatchDate,patientBatchNo,patientConsent);
	}

	@Override
	public List<Integer> calTotalNumberOfDrugHistory(Integer providerId) {
		
		return userDAO.calTotalNumberOfDrugHistory(providerId);
	}

	@Override
	public List<Object> findAdherenceComplianceDetails() {
		// TODO Auto-generated method stub
		return userDAO.findEnbrelAdherenceComplianceDetails();
	}

	@Override
	public List<ChartModel> findEnbrelAdherencePatient(Integer providerId) {
		// TODO Auto-generated method stub
		return userDAO.findEnbrelAdherencePatients(providerId);
	}

	@Override
	public Integer getPatientsCurrentlyOnEnbrel(Integer providerId) {
		// TODO Auto-generated method stub
		return userDAO.getPatientsCurrentlyOnEnbrel(providerId);
	}

	@Override
	public List<UserLoginDetail> fetchPateintsForEnbrelCompliance(
			String selectedCompliancePart, int providerId) {
		// TODO Auto-generated method stub
		return userDAO.fetchPateintsForEnbrelCompliance(selectedCompliancePart,providerId);
	}

	
	public List<UserLoginDetail> getAllPatientDataForBatchPullBasedOnBatchDate(
			int providerId, int providerLocationId, String patientBatchDate,int patientBatchNo,
			char patientConsent) {
		
		return userDAO.getAllPatientDataForBatchPullBasedOnBatchDate(providerId,providerLocationId,patientBatchDate,patientBatchNo,patientConsent);
	}

	
	public List<UserLoginDetail> getAllPatientDataForBatchPullBasedOnBatchDateAndBatchNo(
			int providerId, int providerLocationId, String patientBatchDate,
			int patientBatchNo, char patientConsent) {
	      
		return userDAO.getAllPatientDataForBatchPullBasedOnBatchDateAndBatchNo(providerId,providerLocationId,patientBatchDate,patientBatchNo,patientConsent);
	}

	
	public List<UserLoginDetail> getAllpatientDataWithPatientConsentForDataPullProcess(
			int providerId, int providerLocationId) {
		// TODO Auto-generated method stub
		return userDAO.getAllpatientDataWithPatientConsentForDataPullProcess(providerId,providerLocationId);
	}

	
	public void doDeIdentificationDataProcess(int providerId) {
		userDAO.doDeIdentificationDataProcess(providerId,DecryptionKey);
		
	}

	@Override
	public List<ChartModel> getInsuranceChartForProvider(
			int providerId,boolean isAllRecordsNeeded) {
		
		return userDAO.getInsuranceChartForProvider(providerId,isAllRecordsNeeded);
	}

	/***@uthor: SAURABH
	 * ******for fethcing all patients record for 
	 * ************batchPatientForReportList screen
	 */
	
	public List<UserLoginDetail> fetchbatchPatientForReportList(int providerId, int providerLocationId,
			 int fromPatientIdForReport, int toPatientIdForReport) {

		return userDAO.fetchbatchPatientForReportList( providerId,  providerLocationId,
				  fromPatientIdForReport,  toPatientIdForReport);
	}

	public List<Integer> fetchPatientIdList() {
		return userDAO.fetchPatientIdList();
	}

	
	public List<PatientMedicationData> getCurrentMedicationDataForBatchReport(
			UserLoginDetail[] selectedUserDetailForReport, int providerId,
			int providerLocationId) {
		
		return userDAO.getCurrentMedicationDataForBatchReport(selectedUserDetailForReport,providerId,providerLocationId);
	}

	
	public List<UserLoginDetail> getPatientDetailDataForBatchReport(
			UserLoginDetail[] selectedUserDetailForReport, int providerId,
			int providerLocationId) {
		
		return userDAO.getPatientDetailDataForBatchReport(selectedUserDetailForReport,providerId,providerLocationId);
	}

	@Override
	public ProviderLocation findProviderLocationForMultiThreading(int providerIdForMultiThreading,
			String loggedInRoleForMultiThreading) {
		return userDAO.findProviderLocationForMultiThreading(providerIdForMultiThreading,loggedInRoleForMultiThreading);
	}

	@Override
	public DoctorDetail findDoctorDetailDataForMultiThreading(int providerIdForMultiThreading,
			int providerLocationIdForMultiThreading, String loggedInRoleForMultiThreading,
			int loginIdForMultiThreading) {
		return userDAO.findDoctorDetailDataForMultiThreading(providerIdForMultiThreading, providerLocationIdForMultiThreading,loggedInRoleForMultiThreading,
				 loginIdForMultiThreading);
	}

	@Override
	public UserLoginDetail findPatientDetailData(Integer patient_Id,
			int providerId) {
		
		return userDAO.findPatientDetailData(patient_Id,providerId);
	}
/*****@author Saurabh
 * ***for calculating the no. of patients having pbm history pulled
 */
	@Override
	public Long findNumberOfPatientsHavingPbmElegiblity(int loginIdForMultiThreading) {
		return userDAO.findNumberOfPatientsHavingPbmElegiblity(loginIdForMultiThreading);
		/*
		int startId=0;
		int endId=0;
		int patientCount=0;
		List<UserLoginDetail> uld = Arrays.asList(userLoginDetailForThreading);
		List<Integer> PatientsIdHavingPbmElegiblity= new ArrayList<Integer>();
		for(int index=0; index < uld.size(); index++) {
			UserLoginDetail currElement = uld.get(index);
			if(index == 0) {
		        //currElement is the first element
				endId=currElement.getUserId();
		    }
		    if(index == uld.size() - 1) {
		         //currElement is the last element
		    	startId=currElement.getUserId();
		    }
		  }
		PatientsIdHavingPbmElegiblity=userDAO.findNumberOfPatientsHavingPbmElegiblity(startId,endId);
		for (UserLoginDetail u : uld) {
				 if (PatientsIdHavingPbmElegiblity.contains(u.getUserId())) {
					 patientCount++;
				}
		}
		System.out.println("under findNumberOfPatientsHavingPbmElegiblity::::startId= "+startId+"  :::::endId= "+
				endId+" :::patientCount= "+patientCount);
		return patientCount;
	*/
		}

/*****@author Saurabh
 * ***for calculating the no. of patients having drug history pulled
 */
	@Override
	public Long findNumberOfpulledDrugs(int loginIdForMultiThreading) {
		
		return userDAO.findNumberOfpulledDrugs(loginIdForMultiThreading);
		/*int startPatientId=0;
		int endPatientId=0;
		int pulledDrugCount=0;
		List<UserLoginDetail> uld = Arrays.asList(userLoginDetailForThreading);
		List<Integer> PatientsIdHavingDrug= new ArrayList<Integer>();
		for(int index=0; index < uld.size(); index++) {
			UserLoginDetail currElement = uld.get(index);
			if(index == 0) {
		        //currElement is the first element
				endPatientId=currElement.getUserId();
		    }
		    if(index == uld.size() - 1) {
		         //currElement is the last element
		    	startPatientId=currElement.getUserId();
		    }
		  }
		PatientsIdHavingDrug=userDAO.findNumberOfpulledDrugs(startPatientId,endPatientId);
		for (UserLoginDetail u : uld) {
				 if (PatientsIdHavingDrug.contains(u.getUserId())) {
					 pulledDrugCount++;
				}
		}
		System.out.println("under findNumberOfpulledDrugs::::startId= "+startPatientId+"  :::::endId= "+
				endPatientId+" :::pulledDrugCount= "+pulledDrugCount);
		return pulledDrugCount;*/
	}

@Override
public List<UserLoginDetail> getPatientsHavingDevices(int providerId,boolean isConnectedDevices) {
	
	return userDAO.getPatientsHavingDevices(providerId,isConnectedDevices);
}

@Override
public UserLoginDetail getPatientDetails(int providerId, int patientId,boolean isPatientActive) {
	
	return userDAO.getPatientDetails(providerId,patientId,isPatientActive);
}

@Override
public List<PracticeSearchResultSet> findAllPracticesDiagnosis(String diagnosis) {
	
	return userDAO.findAllPracticesDiagnosis(diagnosis,DecryptionKey);
}

@Override
public List<PracticeSearchResultSet> findAllPracticesDrugByName(String drugs) {
	
	return userDAO.findAllPracticesDrugByName(drugs,DecryptionKey);
}

@Override
public List<PracticeSearchResultSet> findAllPracticesDrugById(Double drugId) {
	
	return userDAO.findAllPracticesDrugById(drugId,DecryptionKey);
}

@Override
public List<UserLoginDetail> getAllPatientDataforProvider(int providerId) {
	
	return userDAO.getAllPatientDataforProvider(providerId,DecryptionKey);
}

@Override
public AuditPatientDeactivation prepareDeactivationAuditObj(int patientId,
		int providerId, Date deactivatedDate, int loginId) {
	AuditPatientDeactivation auditObj = new AuditPatientDeactivation();
	auditObj.setPatientId(patientId);
	auditObj.setProviderId(providerId);
	auditObj.setUserId(loginId);
	auditObj.setDeactivatedDate(deactivatedDate);
	
	return auditObj;
}

@Override
public boolean deletePatientAllData(int providerId, int userId) {
	
	return userDAO.deletePatientAllData(providerId,userId);
}

@Override
public boolean insertPatientAllDataToDeactivation(int providerId, int userId) {
	
	return userDAO.insertPatientAllDataToDeactivation(providerId,userId);
}

@Override
public boolean saveAuditForPatientDeactivation(
		List<AuditPatientDeactivation> auditPatientDeactiveRecords) {
	return userDAO.saveAuditForPatientDeactivation(auditPatientDeactiveRecords);
}

@Override
public List<UserLoginDetail> getDeactivatedPatients(int providerId) {
	
	return userDAO.getDeactivatedPatients(providerId,DecryptionKey);
}

@Override
public void resetOldRecords(int loginIdForMultiThreading) {
	userDAO.resetOldRecords(loginIdForMultiThreading);
}

@Override
public Long findPatientHaveMedications(int loginIdForMultiThreading) {
	return  userDAO.findPatientHaveMedications(loginIdForMultiThreading);
}
	

	/*public void saveNewCropAllergy(
			List<PatientAllergyExtendedDetailV4> patientAllergyExtendedList,
			int patientId) {
		// TODO Auto-generated method stub
		
			userDAO.saveAllergy(patientAllergyExtendedList,patientId);
			
		}*/
		

@Override
public List<PracticeSearchResultSet> findAllPracticesDiagnosisById(
		String diagnosis) {
	
	return userDAO.findAllPracticesDiagnosisById(diagnosis);
}

@Override
public List<PracticeSearchResultSet> findAllPracticesLabByName(String lab) {
	
	return userDAO.findAllPracticesLabByName(lab, DecryptionKey);
}

@Override
public List<PracticeSearchResultSet> findAllPracticesLabById(String lab) {
	// TODO Auto-generated method stub
	return userDAO.findAllPracticesLabByLoincCode(lab, DecryptionKey);
}

@Override
public List<PracticeSearchResultSet> findPracticeDataBasedOnDate(Date fromDate,
		Date toDate) {
	
	return userDAO.findPracticeDataBasedOnDate(fromDate,toDate);
}
	

}
