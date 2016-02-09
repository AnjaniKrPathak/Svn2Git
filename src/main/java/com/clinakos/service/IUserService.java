package com.clinakos.service;

import https.secure_newcropaccounts_com.v7.webservices.PatientAllergyExtendedDetailV4;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

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




public interface IUserService {
	

	List<InsuranceCompanies> insuranceCompaniesList();

	List<Integer> findClinicAndProviderId(int loginId);

	List<Integer> findnumberOfPatientParticularProvider(Integer clinicProviderIdus);

	List<Object> findInsuranceGraphDetail(int clinicProviderId, List<Integer> numberOfPatientParticularProvider, List<InsuranceCompanies> insuranceCompaniesList);

	List<Object> findClinicPieChart(int clinicProviderId);

	int findaverageMedicinePerPatient(List<Integer> numberOfPatientParticularProvider);

	int findaverageDiagnoses(List<Integer> numberOfPatientParticularProvider);

	int findaveragPhysicians(int size, int providerId);

	int findPharmacogenomicsPercentage(List<Integer> numberOfPatientParticularProvider);

	int findAveragePharmacyPerPatient(List<Integer> numberOfPatientParticularProvider);

	List<FormularyDetail> findFormularySearchDetail(int insuranceId, String medicineName);

	List<Object> findAllComplianceDetails();

	List<BigInteger> findPatientMedicationDataListAccordingToProvider(
			int providerId);

	List<ClinicMaster> findClinicMasterList();

	Double getDrugIdByDrugName(String medicineForFormularySearch);

	DoctorDetail findDoctorDetailData();

	UserLoginDetail findPatientDetailData();

	ArrayList<String> getAllDrugIdBySelectedFormularyDrug(
			FormularyDetail[] selectedFormularyDrug);

	List<WSDrug> findAllDrugDoseComboForFormularyMedicine(
			String medicineForFormularySearch);

	List<InsuranceCompanies> getInsuranceDetail(String insuranceCompanyForFormularySearch);

	ProviderLocation findProviderLocationDetail(int providerId);

	RoleSecurity findRoleDetails(int loginId);
	
	void saveWSFailStatus(AuditInfo auditInfo);

/*	void saveNewCropAllergy(
			List<PatientAllergyExtendedDetailV4> patientAllergyExtendedList,
			int patientId);*/

	List<AnticoagProviderLocation> findnumberofpatientforanticoagclinic(Integer clinicProviderIdus, String medicinestage, int procedureType, String start_result, String end_result, String statusID);

	List<AnticoagProviderLocation> findnumberofpatientformedactionplan(Integer clinicProviderIdus, String medicinestage, int procedureType, String start_result, String end_result, String statusID);

	List<AnticoagProviderLocation> findnumberofpatientformedactionplanforgraterrange(Integer clinicProviderIdus, String medicinestage, int procedureType, String start_result, String end_result, String statusID);

	List<Integer> findnumberofpatientinrlab(Integer clinicProviderIdus);

	Double getDrugIdByDrug(String medicineForFormulary);

	List<AnticoagProviderLocation> findnumberofpatientinrlabforinitiationphase(Integer integer);

	List<AnticoagProviderLocation> findnumberofpatientformedactionplanforgraterrangeforintiationphase(
			Integer integer, String medicinestage, int procedureType, String start_result, String end_result);

	List<AnticoagProviderLocation> findnumberofpatientformedactionplanforintiationphase(
			Integer integer, String medicinestage, int procedureType, String start_result, String end_result, String statusID);

	List<AnticoagProviderLocation> findnumberofpatientforanticoagclinicforintiationphase(
			Integer integer, String medicinestage, int procedureType, String start_result, String end_result, String statusID);

	List<AnticoagProviderLocation> findnumberofpatientforanticoagclinicforhigerendofpatient(
			Integer integer, String medicinestage, int procedureType, String start_result, String end_result, String statusID);

	List<AnticoagProviderLocation> findnumberofpatientforanticoagclinicforhigerendofpatientforintiation(
			Integer integer, String medicinestage, int procedureType, String start_result, String end_result, String statusID);

	void savePatientPBMDrugHistoryDetailResult(
			PatientPbmDrugHistoryDetail patientPbmDrugHistoryDetail);

	List<UserLoginDetail> getAllPatientDetailList();

	List<PatientPbmDrugHistoryDetail> getAllPatientPbmDrugHistoryDetail(int patientId);

	

	List<PatientPBMDrugHistoryResult> getAllPatientPBMDrugHistoryResultValue(int pastientId);

	void savePatientFormularyCompositeDrugDetailInfoData(
			ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeDrugDetailInfoList);

	void savePatientFormularyCompositeCopayInfoData(
			ArrayList<PatientFormularyCompositeCopayInfo> formularyCompositeCopayInfoList);

	ProviderDetail getProviderDetailData(int providerId);
	
	void savePatientPBMEligibilityDetailData(
			List<PatientPBMEligibilityDetailData> pbmEligibilityDetailDataList);
	
	void savePatientPBMDrugHistoryResult(
			PatientPBMDrugHistoryResult drugHistoryResult);

	void updateHealthPlanAndPharmacyDetail(int userId) throws HibernateException, SQLException;

	void careTeamIntegration(int userId);

	List<UserLoginDetail> getPatientDetailList(int patientId);

	void deletePatientPBMDrugHistoryDetailResult(int patientId);

	void deletePatientPBMDrugHistoryResult(int patientId);

	/*List<AnticoagProviderLocation> numberofpatientforanticoagforintiationphase(
			Integer integer, String medicinestage, int procedureType,
			String start_result, String end_result);*/
	
	List<UserLoginDetail> numberofpatientforanticoagforintiationphase(
			Integer integer, String medicinestage, int procedureType,
			String start_result, String end_result);

	List<UserLoginDetail> fetchPateintListForCompliance(
			String selectedCompliancePart, int providerId);

	void updateComplianceValue();

	void deletePatientDrugData(int userId);

	List<UserLoginDetail> getPatientList();

	List<UserLoginDetail> getpatientDataWithPatientConsentForDataPullProcess(
			int providerId, int providerLocationId);

	List<Date> getAllPatientBatchDataData(int providerId,
			int providerLocationId);

	List<Integer> getPatientBatchNumberData(Date patientBatchCreatedDate,
			int providerId, int providerLocationId);

	List<UserLoginDetail> getPatientDataWithPatientConsentByBatchNoFilter(
			int providerId, int providerLocationId, String patientBatchDate,
			int patientBatchNo, char patientConsent);

	List<Integer> calTotalNumberOfDrugHistory(Integer integer);

	List<Object> findAdherenceComplianceDetails();

	List<ChartModel> findEnbrelAdherencePatient(Integer providerId);


	List<UserLoginDetail> getAllPatientDataForBatchPullBasedOnBatchDate(
			int providerId, int providerLocationId, String patientBatchDate,
			int patientBatchNo, char patientConsent);

	List<UserLoginDetail> getAllPatientDataForBatchPullBasedOnBatchDateAndBatchNo(
			int providerId, int providerLocationId, String patientBatchDate,
			int patientBatchNo, char patientConsent);


	Integer getPatientsCurrentlyOnEnbrel(Integer integer);


	List<UserLoginDetail> fetchPateintsForEnbrelCompliance(
			String selectedCompliancePart, int providerId);

	List<UserLoginDetail> getAllpatientDataWithPatientConsentForDataPullProcess(
			int providerId, int providerLocationId);

	void doDeIdentificationDataProcess(int providerId);

	List<ChartModel> getInsuranceChartForProvider(
			int providerId, boolean b);

	List<UserLoginDetail> fetchbatchPatientForReportList(int providerId, int providerLocationId, int fromPatientIdForReport, int toPatientIdForReport);

	List<Integer> fetchPatientIdList();

	List<PatientMedicationData> getCurrentMedicationDataForBatchReport(
			UserLoginDetail[] selectedUserDetailForReport, int providerId,
			int providerLocationId);

	List<UserLoginDetail> getPatientDetailDataForBatchReport(
			UserLoginDetail[] selectedUserDetailForReport, int providerId,
			int providerLocationId);

	ProviderLocation findProviderLocationForMultiThreading(int providerIdForMultiThreading,
			String loggedInRoleForMultiThreading);

	DoctorDetail findDoctorDetailDataForMultiThreading(int providerIdForMultiThreading,
			int providerLocationIdForMultiThreading, String loggedInRoleForMultiThreading,
			int loginIdForMultiThreading);

	UserLoginDetail findPatientDetailData(Integer patient_Id, int providerId);

	Long findNumberOfPatientsHavingPbmElegiblity(int loginIdForMultiThreading);

	Long findNumberOfpulledDrugs(int loginIdForMultiThreading);

	List<UserLoginDetail> getPatientsHavingDevices(int providerId, boolean isConnectedDevices);

	UserLoginDetail getPatientDetails(int providerId, int patientId, boolean isPatientActive);

	List<PracticeSearchResultSet> findAllPracticesDiagnosis(String diagnosis);

	List<PracticeSearchResultSet> findAllPracticesDrugByName(String drugs);

	List<PracticeSearchResultSet> findAllPracticesDrugById(Double drugId);

	List<UserLoginDetail> getAllPatientDataforProvider(int providerId);

	AuditPatientDeactivation prepareDeactivationAuditObj(int userId,
			int providerId, Date deactivatedDate, int loginId);

	boolean deletePatientAllData(int providerId, int userId);

	boolean insertPatientAllDataToDeactivation(int providerId, int userId);

	boolean saveAuditForPatientDeactivation(
			List<AuditPatientDeactivation> auditPatientDeactiveRecords);

	List<UserLoginDetail> getDeactivatedPatients(int providerId);

	void resetOldRecords(int loginIdForMultiThreading);

	Long findPatientHaveMedications(int loginIdForMultiThreading);

	List<PracticeSearchResultSet> findAllPracticesDiagnosisById(String diagnosis);

	List<PracticeSearchResultSet> findAllPracticesLabByName(String lab);

	List<PracticeSearchResultSet> findAllPracticesLabById(String lab);

	List<PracticeSearchResultSet> findPracticeDataBasedOnDate(Date fromDate,
			Date toDate);
			

}
