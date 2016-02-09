package com.clinakos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.EmdeonLabOrders;
import com.clinakos.data.model.patient.GenericMedActionPlan;
import com.clinakos.data.model.patient.LabResultDetailClinakos;
import com.clinakos.data.model.patient.LoincKeyMaster;
import com.clinakos.data.model.patient.MedActionPlan;
import com.clinakos.data.model.patient.MedandGenricmed;
import com.clinakos.data.model.patient.ORUPatientLabResult;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.PatientMedicationHistory;
import com.clinakos.data.model.patient.PharmacogenomicsUserSummary;
import com.clinakos.data.model.patient.PharmacyDetail;
import com.clinakos.data.model.patient.ProcedureResultHistory;
import com.clinakos.data.model.patient.ProcedureType;
import com.clinakos.data.model.patient.SigCode;

public interface ILabService {
	/**
	 * @return
	 */
	List<ProcedureResultHistory> getAllProcedureResult();

	List<ProcedureResultHistory> labResultValueForChart(Date periodDate, String labName, int labId);

	/**
	 * @param string
	 * @param currentLabDate 
	 * @return
	 */
	List<ProcedureResultHistory> getProcedureResultHistory(String string);

	List<LoincKeyMaster> getloincMasterList();

	// String findChartLabel(int chartTypeID);

	String findlabTestMaxValue(int chartTypeID);

	List<PharmacogenomicsUserSummary> findPharmacogenomicsUserSummaryList(
			int patientId);

	List<ProcedureResultHistory> getProcedureResultForPatient();

	List<PharmacogenomicsUserSummary> getPharmacogenomicsSummaryBasedOnMedicine();

	List<MedandGenricmed> findRemindersForLab(int loginId, Date nextDate);

	void saveLabDetailValue(Date resultDate,String result, int labTypeId, String labType, String loincCode);

	List<ProcedureType> getLabTypeMaster();

	void deleteLabDetails(int rowIdnumber);

	void editLabResult(int selectedCurrentLabIdForEdit,double selectedCurrentLabResultForEdit, String selectedCurrentLabNameForEdit);


	void editHistory(int rowIdOfLabHistory,double labHistoryResult);

	List<ProcedureResultHistory> getprocedureResultHistoryListOnRowExpension(String selectedLabName, int rowId);


	void saveLabResltDetailDataFromNewCrop(
			List<LabResultDetailClinakos> labDetailClinakosList);


	List<ProcedureResultHistory> labResultValueForLineChart();

	/*List<PatientMedicationHistory> fetchMedicationHistoryList(int patientId,
			String string);*/

	void saveOruPatientLabResultFromNewCropToClinakos(
			List<ORUPatientLabResult> wsPatientLabResultList, Date strReportDate);

	List<ORUPatientLabResult> getAllWebServicesPatientLabResult();

	ProcedureResultHistory findCurrentLabValue(int patientId, int providerId,
			int loginId, String labName);

	List<GenericMedActionPlan> getAllGenericMedActionPlan(int id);

	List<GenericMedActionPlan> getAllGenericMedActionPlan1(int patientId);

	List<MedandGenricmed> fetchGenericList(int patientId);

	List<ProcedureResultHistory> getProcedureHistoryListForChart(String lab, int patientId,Date periodDate);

	List<MedandGenricmed> fetchGenericListforLab(int patientId, String lab);

	List<PatientMedicationHistory> fetchGenericListforDisplay(int patientId,
			String medicinename, Date periodDate);

	List<ProcedureType> getProcedureTypeAferSearch(int searchLabOption,
			String inputForLabSearch);

	List<SigCode> fetchsigcodelist();

	List<PharmacyDetail> findPharmacyDetailHistoryListAccordingToParticularDrugId(
			double drugid, int patientId, Date periodDate);


	List<PatientMedicationData> fetchpatientmedicationlist(int patientId,
			String medicinename, Date periodDate);

	void integrateEmedonLabDataInClinakos(Date reportDate);

	List<ProcedureResultHistory> getPsychoPharmLabResultData(int patientId, String psychoPharmClinicName );

	List<ProcedureResultHistory> getHepCLabResultData(int patientId,
			String hepatitisCClinicName);

	ORUPatientLabResult getEmedonLabResult(String medRecordId);

	List<ProcedureType> getPsychopharmProcedureTypes(List<String> labParameters);

	void saveEmdeonLabOrderData(ArrayList<EmdeonLabOrders> emdeonLabOrdersList);




	
}
