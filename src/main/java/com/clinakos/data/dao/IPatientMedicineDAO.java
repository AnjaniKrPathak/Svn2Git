package com.clinakos.data.dao;

import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugDiseaseDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugInteraction;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.joda.time.LocalDate;

import com.clinakos.data.model.core.BatchInteractionAnalytic;
import com.clinakos.data.model.core.FormularyDetail;
import com.clinakos.data.model.patient.ACOPatientMeasure;
import com.clinakos.data.model.patient.AlertGenericMedActionLab;
import com.clinakos.data.model.patient.AllergyMaster;
import com.clinakos.data.model.patient.ChildMedActionPlanParameter;
import com.clinakos.data.model.patient.ClinicDiagonsis;
import com.clinakos.data.model.patient.ClinicLabDetails;
import com.clinakos.data.model.patient.ClinicQuestionnaire;
import com.clinakos.data.model.patient.ClinicSubdiagnosis;
import com.clinakos.data.model.patient.ContraindicatedMeds;
import com.clinakos.data.model.patient.DosageFrom;
import com.clinakos.data.model.patient.DrugDrugInteractionData;
import com.clinakos.data.model.patient.DrugInteractionForWarfarin;
import com.clinakos.data.model.patient.EncounterSummary;
import com.clinakos.data.model.patient.GenericMedActionPlan;
import com.clinakos.data.model.patient.GeriatricPrecaution;
import com.clinakos.data.model.patient.LabDetail;
import com.clinakos.data.model.patient.MasterDrugAllergyInteraction;
import com.clinakos.data.model.patient.MasterDrugDiseaseInteraction;
import com.clinakos.data.model.patient.MasterLOINCData;
import com.clinakos.data.model.patient.MasterMonitorParameters;
import com.clinakos.data.model.patient.MedActionPlan;
import com.clinakos.data.model.patient.MedUnitSummary;
import com.clinakos.data.model.patient.MedandGenricmed;
import com.clinakos.data.model.patient.Medicine;
import com.clinakos.data.model.patient.PaediatricPrecaution;
import com.clinakos.data.model.patient.ParentMedActionPlan;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientDrugAllergyInteraction;
import com.clinakos.data.model.patient.PatientDrugDiseaseInteraction;
import com.clinakos.data.model.patient.PatientDrugMapping;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.PatientMedicationHistory;
import com.clinakos.data.model.patient.PatientMedicineNotes;
import com.clinakos.data.model.patient.PatientPharmacogenomicsCurrentMedicineData;
import com.clinakos.data.model.patient.PatientProvider;
import com.clinakos.data.model.patient.PatientVital;
import com.clinakos.data.model.patient.PharmacogenomicsRecomendations;
import com.clinakos.data.model.patient.PharmacogenomicsUserSummary;
import com.clinakos.data.model.patient.PharmacyDetail;
import com.clinakos.data.model.patient.ProcedureType;
import com.clinakos.data.model.patient.ReconcileInfo;
import com.clinakos.data.model.patient.RouteDetails;
import com.clinakos.data.model.patient.SendMessageEditRx;
import com.clinakos.data.model.patient.SigCode;
import com.clinakos.data.model.patient.UnitDetails;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.VisitHistory;
import com.clinakos.data.model.patient.WSDrug;
import com.clinakos.data.model.patient.WeeklyDose;






public interface  IPatientMedicineDAO {

	List<PatientMedicationData> findPatientMedicationDataList(int patientId); //Method to fetch Medication Data List for particular Patient by LI-0011
	
	List<PatientMedicationHistory> fetchMedicationHistory(int patientId,    //Method to fetch Medication Data History for Particular Patient for Particular Medicine by LI-0011
			String medicineName);
	List<PharmacyDetail> findPharmacyDetailListAccordingToPatirnt(int patientId);

	List<PatientMedicationData> showPatientMedicationeData(int patientId);
	List<FormularyDetail> findFormularyDetailList();
	List<PatientMedicationHistory> showPatientMedicationeHistoryData(String drugs);
	
	//Method to fetch Medicine list for Drug Price optimizer Modified by Gopal Krishna jha at Lumbini Innovations
	List<FormularyDetail> fetchForumlarMedList(List<String> medicineList, int insuranceId); 	

	void savePatientMedicationNotes(PatientMedicineNotes patientMedicineNotes);

	List<PatientMedicineNotes> getMedicineNotes();
	String findDoctorNameAccordingToId(int doctorId);
	void updatpatientMedicationForComplianceAndFormulary(
			List<PatientMedicationData> patientMedicationDataList);
	public List<ACOPatientMeasure> getACOPatientMeasure(int pat_id) ;
	public List<ACOPatientMeasure> getACOPatientMeasureHistory(int pat_id) ;
	public void CalPatientmesureCount(int pat_id) ;

	List<FormularyDetail> fetchMyFormularyMedList(int patientId);  //Method to fetch Medicine list for My Formulary Look up by LI-0011 at Lumbini Innovations

	List<FormularyDetail> optimizeDrugPrice(int patientId, int insuranceId, FormularyDetail[] selectedFDetel, String otherMed);  //Method to Optimize Drug Price for Doctor Profile based on selected member and medicine by LI-0011 at Lumbini Innovations

	//List<FormularyDetail> lookUpMyFormulary(int loginId,FormularyDetail[] selectedFormulary,String otherMed); //Method to look up My formulary for Logged in Patient by LI-0011 at Lumbini Innovations

	List<PatientMedicationData> findpatientMedicationSummaryList(int patientId);
	List<String> findAlternativeMedicine(String drugs, int insuranceId);
	List<PharmacogenomicsRecomendations> showRecommendedFutureMedicine(int patientId);

	List<PharmacogenomicsRecomendations> findImpactedMedication(int patientId); //----method to find impacted medicine for Pharmacogenomics

	List<ClinicQuestionnaire> findClinicQuestionList(int clinicProviderId);

	List<ClinicDiagonsis> findClinicDiagnosisList(String medicineNAme);

	List<String> findLabFrequencyRange(String labName);

	//PatientVital findPatientWeight(int patientId);

	//PharmacogenomicsUserSummary findPatientGenotype(int patientId);

	///PharmacogenomicsUserSummary findPatientGenotype2(int patientId);

	//UserLoginDetail findPatientRaceSummary(int patientId);

	//List<String> findPatMedList(int patientId);
	//List<PatientMedicationData> getMorningPillList(int loginId); //Method to Fetch virtual pill box data for morning list by LI-0011 at Lumbini Innovations, Bangalore

	//List<PatientMedicationData> getNoonPillList(int loginId); //Method to Fetch virtual pill box data for Noon list by LI-0011 at Lumbini Innovations, Bangalore

	//List<PatientMedicationData> getSpecialCasePillList(int loginId); //Method to Fetch virtual pill box data for Special Case list by LI-0011 at Lumbini Innovations, Bangalore

	//List<PatientMedicationData> getBedTimePillList(int loginId); //Method to Fetch virtual pill box data for Bed Time list by LI-0011 at Lumbini Innovations, Bangalore

	//List<PatientMedicationData> getEveningPillList(int loginId); //Method to Fetch virtual pill box data for evening list by LI-0011 at Lumbini Innovations, Bangalore

	List<String> findRegimenList();

	List<PatientMedicationHistory> findMedicineHistoryForAnticoag(int patientId);

	void saveMedDActionPlan(List<MedActionPlan> medicalActionPlanList, VisitHistory visitHistory, List<PatientMedicationData> addNewmedicationListForPatient, List<PatientMedicationData> removeDuplicateModifyMedicationListForPatient, List<Integer> careTeamMemberListForReconcile);

	//List<String> findAmiodaroneMedList(int patientId);

	List<Medicine> findAnticoagMedList(String clinicName);

	VisitHistory findVisitHistory(int patientId, int clinicProviderId,
			int loginId);

	List<Medicine> findMedicineList();

	List<MedActionPlan> findMyMedicationGoal(int loginId);

	List<SigCode> findSigCodeList();

	PatientVital findPatientDetail(int patientId);

	List<PharmacogenomicsUserSummary> getPharmacogenomicsUserSummaryList(
			int patientId);

	List<FormularyDetail> findDrugsPriceOPtimizer(
			FormularyDetail[] selectedFDetel, int insuranceId);

	List<FormularyDetail> findDrugsPriceOPtimizerForOtherMeds(String otherMed,
			int insuranceId);

	List<MedActionPlan> findMedActionPlanForPatient(int patientId,
			int providerId);

	void updateSelectedImage(
			PatientMedicationData selectedRowIdOfPatientMedicationData);

	List<PatientMedicationHistory> findIndiviualDose(int patientId,int providerId);

	Date findAnticoagClinicStartDate(List<Medicine> anicoagMedList,
			int patientId, int providerId);

	Date findAnticoagDate(List<Medicine> anicoagMedList, int patientId);
	List<WSDrug> findSearchMedicineDetailIst(String drugs);
	List<String> findLastReconcileDate(int patientId, int providerId);

	UserLoginDetail findDoctorDetail(int loginId);
	List<ClinicSubdiagnosis> findClinicSubDiagnosisList(int clinicDiagnosisId);

	List<WSDrug> findDoseListOfParticularMeds(Double drugNameId, String dosageForm);

	List<AllergyMaster> findMasterAllergyData(String allergyNameForAddingNew );

	List<PatientAllergy> getAllPatientAllergy(int patientId);

	List<RouteDetails> findRouteNameDetailList();

	List<UnitDetails> findUnitNameDetailsList();

	List<DosageFrom> findDosageNameDetailsList();

	void savePatientAllergy(PatientAllergy patientAllergy);

	void deleteAllergyDetails(PatientAllergy patientAllergy);

	void allergyIntegrateFromNewCropToClinakos(
			List<PatientAllergy> patientAllergyListForIntegrate,int patientId);

	void saveValueForIntegration(int patientId, boolean checkIntegration);

	boolean findStatusOfIntegration(int patientId);

	/*void rateFromNewCropToClinakos(
			List<PatientMedicationData> patientMedicationDataForIntegrate,
			int patientId, List<PatientMedicationData> patientMedicationDataList);
*/
	Double getDrugIdByDrugName(String medicineName);

	List<MedUnitSummary> findUnitSummaryList();

	List<LabDetail> findLabDetailListAccordingToGenericname(String genericDrugs);

	void editAllergyDetails(PatientAllergy patientAllergy, String allergyDetailForEdit);

	void updatePharmacyDetailOfParticularUser(
			List<PharmacyDetail> pharmacyDetailListForUpdate, int patientId);

	void integrateDrugInteractionToDatabase(
			List<DrugInteraction> drugInteractionListData);

	List<PatientDrugMapping> getAllUniqueDrugRecords();

	void ignoreSelectedDrugInDatabase(
			DrugDrugInteractionData drugDrugInteractionData);

	void integrateDrugAllergyInteractionToDatabase(
			List<DrugAllergyDetail> drugAllergyDetailList);

	List<PatientDrugAllergyInteraction> getAllUniquePatientDrugAllergyRecords();

	void ignoreSelectedDrugAllergy(
			MasterDrugAllergyInteraction masterDrugAllergyInteraction);

	void integrateDrugDiseaseInteractionToDatabase(
			List<DrugDiseaseDetail> drugDiseaseDetailList);

	List<PatientDrugDiseaseInteraction> getAllUniquePatientDrugDiseaseRecords();

	void ignoreSelectedDrugDisease(
			MasterDrugDiseaseInteraction masterDrugDiseaseInteraction);

	void integratePharmcogenomicsCurrentMedicineToDb(
			List<PharmacogenomicsRecomendations> pharmacogenomicsRecomendationsListForImpactedMedicine);


	//List<PatientPharmacogenomicsCurrentMedicineData> getAllRecordsFromPharmcogenics();

	/*void ignoreSelectedPharmcogenomics(
			PatientPharmacogenomicsCurrentMedicineData patientPharmacogenomicsCurrentMedicineData);

*/


	List<PatientPharmacogenomicsCurrentMedicineData> getAllRecordsFromPharmcogenics(boolean status);

	void ignoreSelectedPharmcogenomics(PatientPharmacogenomicsCurrentMedicineData patientPharmacogenomicsCurrentMedicineData);

	void deleteMedicineChanges(
			PatientMedicationData changePatientMedicineBackUpdata);

	void saveMedicineDetail(PatientMedicationData patientMedicationData);

	void modifyMedicineChange(
			PatientMedicationData currentpatientMedicationData,
			PatientMedicationData changePatientMedicineBackUpdata, String deleteMedActionPlanOrUpdate);

	void saveMedActionPlan(
			List<MedActionPlan> medactionplanListForParticularMedicine);

	void deleteParticularMedactionPlan(MedActionPlan medActionPlan);

	void medicineIntegrateFromNewCropToClinakos(
			List<PatientMedicationData> patientMedicationDataForIntegrate,
			int patientId, List<PatientMedicationData> patientMedicationDataList, List<Medicine> medicineListForCheckingMedActionPlan, List<MedActionPlan> medActionPlanList, List<ParentMedActionPlan> allGeneralMedPlans);

	List<PatientMedicationData> findpatientDiscontinueMedicineListOfParticularVisit(
			int patientId, int providerId, Date selectedEncounterDateForPrint);

	void endingVisitOfParticularPatient(int patientId, int providerId);

	List<WSDrug> findDoseListOfParticularMedsNotStrength(double drugNameId,
			String dosageForm);
	
	List<DrugDrugInteractionData> filterDrugInteractions(String s);

	WSDrug findFullDrugDetailAccordingToThereName(String drugDetail);

	WSDrug findDrugDetailAccordingToDrugId(double drugId);

	List<PatientMedicationHistory> findFullPatientMedicationHistoryData(
			int patientId, int providerId);

	void updatMedActionPlanOfParticularPatient(MedActionPlan medActionPlan);

	void updateMedActionPlan(PatientMedicationData patientMedicationData);

	List<MasterLOINCData> searchLabInLoincMaster(String loincNumber);

	boolean saveGenericMedActionPlan(int patientId,
			List<GenericMedActionPlan> genericMedActionPlanList, List<GenericMedActionPlan> temporaryGenericMedPlanList, Date dosingStartDate);

	PharmacogenomicsRecomendations findAttentionRatingImpactedMedication(
			int patientId, double drugNameId, String drug);

	List<ParentMedActionPlan> getAllGenericMedActionPlan(int patientId);


	void updateTakeActionPlan(MedActionPlan medActionPlan, WeeklyDose weeklyDose);

	WeeklyDose findWeeklyDoseOfParticularPatient(int patientId, int doctorId, double drugId);

	void updateGenericMedActionPlan(GenericMedActionPlan genericMedActionPlan);

	void deleteGenericMedActionPlan(GenericMedActionPlan genericMedActionPlan);

	

	List<PharmacogenomicsRecomendations> findPharmacogenomicsInteractionDuringAddMedicine(double drugNameId,
			String drugs, int patientId);

	void deletePatientMedicationData(double drugId, int patientId,
			int providerId, double drugNameId);

	List<GenericMedActionPlan> allMedPlansForPatient(
			PatientMedicationData patientMedicationData, int patientID);

	List<AlertGenericMedActionLab> alertMedActionPlans(int patientID);

	void saveMessageDetials(SendMessageEditRx sendMessageEditRx);

	List<SendMessageEditRx> getsendMessageEditRxList(int patientId);

	List<MasterMonitorParameters> getAllSubParameters(String monitoringParameter);

	List<MedandGenricmed> getAllClinicMedPlans(int patientId);

	List<MedandGenricmed> findgenricandnongenricMedActionPlanForPatient(
			int patientId, int providerId);

	List<MedandGenricmed> fetchGenericList(int patientId);

	List<String> findLabTypeList();

	ProcedureType getLabUnitBasedOnParameter(String childParameter);

	WSDrug getWSdrugObj(String dataProvider, double drugId, double drugNameIdVal);

	

	List<SendMessageEditRx> getSendMessageCurrentEditRxList(int patientId,
			Date todayDate);

	List<PatientMedicationHistory> findDiscontinueMedicineListOfParticularVisit(
			int patientId, int providerId);

	List<PatientMedicationData> findPatientMedicationDataListForPatient(
			int patientId);

	List<MedandGenricmed> findgenricandnongenricMedActionPlanPatient(
			int patientId, int providerId);

	List<PatientMedicationData> findMedicineForPatient(int patientId);

	void saveMedicineDetailforEducation(
			PatientMedicineNotes patientMedicineNotes, EncounterSummary encounterSummary);
	List<DrugInteractionForWarfarin> findDrugInteractionForWarfarinList(
			List<PatientMedicationData> patientMedicationDataList);


	List<PharmacyDetail> findPharmacyDetailHistoryListAccordingToParticularDrugId(
			double drugId, int patientId);

	List<PharmacyDetail> getPharmacyDetailData(int timePeriod, int patientId);

	boolean checkSpecialtyDrug(String drugs);

	String getPatientGender(int patientId);

	List<WeeklyDose> findWeeklyDoseOfParticularPatientforanticoagmedicine(
			int loginid, int providerid);	

	List<PatientMedicationData> fetchAllPatientMedicationDataRecords();


	void updateCompliancePercentageForDemo(int compliancePercentage, int id);
	
	List<MedActionPlan> findMedActionPlanForPatientforanticaog(int patientId,
			int providerId);

	List<GenericMedActionPlan> getPsychoPharmClinicMedActionPlanData(
			int patientId);

	List<GenericMedActionPlan> getPsychoPharmMedActionChartDrugNameData(
			int patientId) ;

	List<GenericMedActionPlan> getPsychoPharmMonitoringParameterChartValue(
			int patientId);


	List<ParentMedActionPlan> getHepatitisCMedActionPlanData(int patientId);

	List<PatientMedicationData> getCurrentPsychopharmMeds(int patientId,
			String psychopharmClinicName);


	List<PatientMedicationData> getCurrentHepatitsCMedData(int patientId,
			String hepatitisCClinicName);

	//void getCurrentPsychopharmMeds(PatientMedicationHistory patHis);


	void saveNewCropDiscontinueMedsData(PatientMedicationHistory patHis);

	List<GenericMedActionPlan> getHepCChartLabMonitoringParameterData(
			int patientId, String hepatitisCClinicName);

	List<String> getAllPsychoPharmClinicMedicineList(
			String psychopharmClinicName);

	List<String> getHepatitisCClinicMedicineList(String hepatitisCClinicName);

	List<ContraindicatedMeds> getMasterContraindicatedMeds();

	List<MasterMonitorParameters> getGeneralMedPlanLabs(String generalMapView);

	ParentMedActionPlan getAllMedPlansForPatient(
			PatientMedicationData patientMedicationData, int patientID);

	boolean saveNewGeneralMedActionPlan(int patientId,
			ParentMedActionPlan parentMedActionPlan,
			List<ChildMedActionPlanParameter> temporaryChildMedActionPlansParams, Date dosingStartDate, List<PatientDiagnosesDetails> patientDiagnosisList);

	List<ParentMedActionPlan> generalMedPlansForParticularPatient(int patientId);

	void deleteGeneralMAPonSelectedDrug(ParentMedActionPlan parentMedActionPlan);

	void updateGeneralMAP(ParentMedActionPlan parentMedActionPlan);

	public String getUserNameByUserId(int createdBy);

	List<ParentMedActionPlan> getPsychopharmClinicMedPlans(int patientId,
			String psychopharmClinicName);

	List<MasterMonitorParameters> getPsychopharmDefaultLabParameters(
			String psychopharmMapView, String genderOfPatient);

	ReconcileInfo getLatestReconcileInfo(int patientId);

	void updateReconcileInfoonDeleteMed(
			PatientMedicationData changePatientMedicineBackUpdata,
			boolean status);

	List<GeriatricPrecaution> getGeriatricPrecautions(double selectedDrugId);

	List<PaediatricPrecaution> getPaediatricPrecautions(double selectedDrugId,
			Integer patientAgeInDaysVal);

	void saveReconcileInfoObj(
			PatientMedicationData patientMedicationDataForReconcile)throws HibernateException, Exception;

	void deleteSelectedRxDrug(
			PatientMedicationData changePatientMedicineBackUpdata);

	void updateMAPmessage(PatientMedicationData changePatientMedicineBackUpdata);

	List<EncounterSummary> getPatientEncounterSummaryHistoryData(int patientId);

	List<EncounterSummary> getEncounterSummaryHistoryData(int patientId,
			Date startDate, Date endDate);


	EncounterSummary getSelectedEncounterSummaryData(int patientId,
			int providerId, Date selectedEncounterDateForPrint);

	List<MedandGenricmed> getMedAndGenrticMedPlanDataForSelectedDate(
			int patientId, int providerId, Date selectedEncounterDateForPrint);

	void updateMedAactionPlanStatus(
			PatientMedicationData changePatientMedicineBackUpdata);

	List<ParentMedActionPlan> getInactiveMedsWithActiveMAP(int patientId);

	List<SendMessageEditRx> getSendMessageEditRDataForSelectedEncounterDate(
			int patientId, int providerId, Date selectedEncounterDateForPrint);

	List<PatientMedicationData> getAddedOrChangeMedDataOnSelectedEncounterDate(
			int patientId, int providerId, Date selectedEncounterDateForPrint);

	List<PatientMedicationData> getPatientMedicationDataForSelectedEncounterDate(
			int patientId, int providerId, Date selectedEncounterDateForPrint);

	Map<Integer, List<PatientMedicationData>> getPatientMedicationDataMap(
			int providerId, UserLoginDetail[] selectedUserLoginDetail);

	List<HashMap<Integer, List<PharmacyDetail>>> getPharmacyHistoryData(
			UserLoginDetail[] selectedUserLoginDetail, LocalDate todayDate,
			LocalDate endDate);

	void saveAutoFillReconcileMeds(
			Map<Integer, List<PatientMedicationData>> pharmacyToPatientMedicationObjMaps);

	Map<Integer, List<PharmacyDetail>> getPharmacyOldHistory(
			List<Integer> selectedUserIds);

	void saveInteractionAnalytics(
			List<BatchInteractionAnalytic> interactionAnalytics);

	List<BatchInteractionAnalytic> getInteractionHistoryData(int patientId,
			int providerId);

	List<PharmacyDetail> getOldMedicationDataList(int patientId, List<PatientMedicationData> patientMedicationDataList);

	List<PatientProvider> getPatientList(int providerId);

/*	List<ChildMedActionPlanParameter> getPsychopharmDefaultLabParameters(
			String psychopharmMapView, String genderOfPatient);*/



	
}
