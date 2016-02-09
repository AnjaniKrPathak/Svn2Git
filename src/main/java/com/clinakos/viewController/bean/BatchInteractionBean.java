/**
 * 
 */
package com.clinakos.viewController.bean;

import static com.clinakos.common.util.ClinakosConstant.DRUG_ALLERGY_INTERACTION;
import static com.clinakos.common.util.ClinakosConstant.DRUG_DISEASE_INTERACTION;
import static com.clinakos.common.util.ClinakosConstant.DRUG_DRUG_INTERECTION;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_LEVEL_SEARCH_STR;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_LEVEL_SEARCH_STR_CONTRADICATION;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_LEVEL_SEARCH_STR_CONTRAINDICATED;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_LEVEL_SEARCH_STR_DISEASE;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_LEVEL_SEARCH_STR_MODERATE;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_LEVEL_SEARCH_STR_RELATIVE;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_TOOLTIP;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_TOOLTIP_CONTRAINDICATED;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_TOOLTIP_MODERATE;
import static com.clinakos.common.util.ClinakosConstant.HIGH_SEVERITY_LEVEL;
import static com.clinakos.common.util.ClinakosConstant.LOW_SEVERITY_LEVEL;
import static com.clinakos.common.util.ClinakosConstant.MEDIUM_SEVERITY_LEVEL;
import static com.clinakos.common.util.ClinakosConstant.MILD_LEVEL;
import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugDiseaseDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugDrugInteraction;
import https.secure_newcropaccounts_com.v7.webservices.DrugInteraction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.event.ToggleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.DrugInteractions;
import com.clinakos.data.model.core.BatchAllergyInteraction;
import com.clinakos.data.model.core.BatchDiseaseInteraction;
import com.clinakos.data.model.core.BatchDrugInteraction;
import com.clinakos.data.model.core.BatchInteractionAnalytic;
import com.clinakos.data.model.core.BatchInteractionData;
import com.clinakos.data.model.core.BatchPatientMedsHistory;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.patient.DrugInteractionOverview;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.service.IPatientMedicineService;
import com.clinakos.service.IPatientService;
import com.clinakos.service.serviceImpl.PatientMedicineServiceImpl;
import com.clinakos.viewController.webservicMangedBean.NcDrugDetailWSBean;
import com.clinakos.viewController.webservicMangedBean.NcUpdate1WSBean;
import com.google.common.primitives.Ints;

/**
 * BatchInteractionBean used to call interaction newcrop webservices thru async call from batch data pull process(i.e usermanagebean method 
 * batchDataPullingForPatientConsentGrantPatient())
 * 
 * As batch pull data process will take time so it has been done in async call.
 * 
 * @author Nagaraj on 07/July/2015
 *
 */

public class BatchInteractionBean implements Serializable{

	public static final Logger logger = Logger.getLogger("BatchInteractionBean.class");
	
	@Autowired
	IPatientMedicineService patientMedicineService;
	private static final long serialVersionUID = 1L;
	
	public static final double NO_DRUG =0.0;
	
	@Autowired
	private  NcDrugDetailWSBean drugDetailWSBean;
	
	@Autowired
	private  NcUpdate1WSBean ncUpdate1WSBean;
	
	private List<BatchInteractionAnalytic>interactionHistoryData; // List of Batch Interaction History  Data 
	
	@Autowired
	IPatientService patientService;
	
	public List<DrugInteractionOverview>interactionHistory;

	/**
	 * It will fetch all interactions like drug-drug,drug-disease,drug-allergy,formulary status and pharmcogenomics interactions 
	 * based on selected patients from admin UI 
	 * It will handle exceptions if webservice is throwing error message and it will be saved to database
	 * 
	 * @param selectedUserLoginDetail
	 */
	
	public  void fetchInteractionsData(int selectedPatientId,int providerIdForMultiThreading,
			int loginIdForMultiThreading,ProviderLocation providerLocationObj) {
		//First fetch all medication data for selected patient 
		//send these medication list to web service to get the interactions
		//save it to database
		//if any exceptions thrown then handle these exceptions and save it to database
		UserLoginDetail  selectedUserLoginDetail[] = new UserLoginDetail[1];
		UserLoginDetail userObj = new UserLoginDetail();
		userObj.setUserId(selectedPatientId);
		
		selectedUserLoginDetail[0] = userObj;
		int providerId=providerIdForMultiThreading;
		Date batchPullDate=new Date();
		int loggedUserId=loginIdForMultiThreading;
		/*FacesContext context = FacesContext	.getCurrentInstance();
		UserManageBean userManageBean=(UserManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"userManageBean");	
		PatientMedicineManageBean patientMedicineManageBean=(PatientMedicineManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"patientMedicineManageBean");*/	
		ProviderLocation providerLocation=providerLocationObj;
		Map<Integer,List<PatientMedicationData>>medicationMap=patientMedicineService.getPatientMedicationData(providerId,selectedUserLoginDetail);
		List<BatchInteractionAnalytic>interactionAnalytics=new ArrayList<BatchInteractionAnalytic>();
		for(Map.Entry<Integer, List<PatientMedicationData>>entry:medicationMap.entrySet()){
			// prepare data in master data relationship
			int patientId = entry.getKey();
			System.out.println("Patient id "+patientId);
			BatchInteractionAnalytic analytic = prepareAnalyticMaster(
					patientId, providerId, loggedUserId, batchPullDate);
			List<PatientMedicationData> patientMedications = new ArrayList<PatientMedicationData>();
			List<BatchPatientMedsHistory>batchPatientMedsHistories=new ArrayList<BatchPatientMedsHistory>();
			patientMedications = entry.getValue();
			List<PatientDiagnosesDetails> patientDiagnosesDetailList=new ArrayList<PatientDiagnosesDetails>();
			List<PatientAllergy>patientAllergyList=new ArrayList<PatientAllergy>();
			try{
			 patientDiagnosesDetailList=patientService.findPatientDiognosisDetails(patientId);
			}catch(Exception e){
				patientDiagnosesDetailList=new ArrayList<PatientDiagnosesDetails>();
				e.printStackTrace();
			}
			try{
			patientAllergyList=patientMedicineService.getAllPatientAllergy(patientId);
			}catch(Exception e){
				patientAllergyList=new ArrayList<PatientAllergy>();
				e.printStackTrace();
			}
			for(PatientMedicationData medicationData:patientMedications){
				logger.info("For loop :Orginal Obj ::: Drug Id "+medicationData.getDrugId()+" Drug Name Id "+medicationData.getDrugNameId()+" Formulary Symbol "+medicationData.getFormularySymbol()+" compliance Percentage "+medicationData.getCompliancePercentage()+" Drugs "+medicationData.getDrugs());
				BatchPatientMedsHistory batchPatientMedsHistory=prepareMedData(medicationData);
				List<BatchDrugInteraction>drugAnalyticData=new ArrayList<BatchDrugInteraction>();
				List<BatchDiseaseInteraction>diseaseAnalyticData=new ArrayList<BatchDiseaseInteraction>();
				List<BatchAllergyInteraction>allergyAnalyticData=new ArrayList<BatchAllergyInteraction>();
			try {
				List<DrugInteraction> drugInteractionsList = drugDetailWSBean
						.callDrugDrugInteractionAdmin(patientMedications,
								providerLocation, medicationData.getDrugId(),patientId);
				drugAnalyticData=getDrugInteractionAnalyticData(drugInteractionsList,medicationData.getDrugId(),DrugInteractions.drug_drug_interactions.getValue());

			} catch (Exception e) {
				// Save to AuditInteractionLogs
				drugAnalyticData=new ArrayList<BatchDrugInteraction>();
				e.printStackTrace();
			}
			
			try{
				
				 List<DrugDiseaseDetail>drugDiseaseInteractionsList=ncUpdate1WSBean.callDrugDiseaseInteractionForMultithreading(String.valueOf(patientId),
							patientMedications,patientDiagnosesDetailList,providerLocation,medicationData.getDrugId(),providerId);
				diseaseAnalyticData=getDrugDiseaseInteractionAnalyticData(drugDiseaseInteractionsList,DrugInteractions.drug_disease_interactions.getValue());
			}catch(Exception e){
				// Save to AuditInteractionLogs
				diseaseAnalyticData=new ArrayList<BatchDiseaseInteraction>();
				e.printStackTrace();
			}
			try{
				List<DrugAllergyDetail>drugAllergyDetailsList=drugDetailWSBean.getDrugAllergyInteraction(String.valueOf(patientId),patientAllergyList,patientMedications,providerLocation,medicationData.getDrugId());
				allergyAnalyticData=getDrugAllergyInteractionAnalyticData(drugAllergyDetailsList,patientAllergyList,DrugInteractions.drug_allergy_interactions.getValue());
			}catch(Exception e){
				// Save to AuditInteractionLogs
				allergyAnalyticData=new ArrayList<BatchAllergyInteraction>();
				e.printStackTrace();
			}
			batchPatientMedsHistory.setDrugInteractions(drugAnalyticData);
			batchPatientMedsHistory.setDiseaseInteractions(diseaseAnalyticData);
			batchPatientMedsHistory.setAllergyInteractions(allergyAnalyticData);
			batchPatientMedsHistories.add(batchPatientMedsHistory);
			}
			analytic.setBatchPatientMedsHistories(batchPatientMedsHistories);
			interactionAnalytics.add(analytic);
		}
		
		patientMedicineService.saveInteractionAnalytics(interactionAnalytics);
	}

	

	private BatchPatientMedsHistory prepareMedData(
			PatientMedicationData patientMedicationDataObject) {
		BatchPatientMedsHistory batchPatientMedsHistory=new BatchPatientMedsHistory();
		try{
			logger.info("prepareMedData :Orginal Obj ::: Drug Id "+patientMedicationDataObject.getDrugId()+" Drug Name Id "+patientMedicationDataObject.getDrugNameId()+" Formulary Symbol "+patientMedicationDataObject.getFormularySymbol()+" compliance Percentage "+patientMedicationDataObject.getCompliancePercentage()+" Drugs "+patientMedicationDataObject.getDrugs());
			batchPatientMedsHistory.setDrugId(patientMedicationDataObject.getDrugId());
			batchPatientMedsHistory.setDrugNameId(patientMedicationDataObject.getDrugNameId());
			if(StringUtils.isNotBlank(patientMedicationDataObject.getFormularySymbol())){
			batchPatientMedsHistory.setFormularyText(patientMedicationDataObject.getFormularySymbol());
			}
			batchPatientMedsHistory.setAdherenceScore(patientMedicationDataObject.getCompliancePercentage());
			batchPatientMedsHistory.setDrugName(patientMedicationDataObject.getDrugs());
			logger.info("prepareMedData :BatchPatientMedsHistory obj "+batchPatientMedsHistory.toString());
		}catch(NullPointerException nfe){
			nfe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return batchPatientMedsHistory;
	}

	private static List<BatchAllergyInteraction> getDrugAllergyInteractionAnalyticData(
			List<DrugAllergyDetail> drugAllergyDetailsList, List<PatientAllergy> patientAllergyList, int value) {
		List<BatchAllergyInteraction>analyticData=new ArrayList<BatchAllergyInteraction>();
		for(DrugAllergyDetail drugAllergyDetail:drugAllergyDetailsList){
			String allergyNameWS=new String();
			String allergySeverityLevel="";
			//BatchInteractionData interactionData=new BatchInteractionData();
			BatchAllergyInteraction interactionData=new BatchAllergyInteraction();
			interactionData.setInteractionType(value);
			interactionData.setInteractionText(drugAllergyDetail.getInteractionText());
			
			if(StringUtils.contains(drugAllergyDetail.getInteractionText(), "with")){
				int i = drugAllergyDetail.getInteractionText().lastIndexOf(' '); 
				allergyNameWS = drugAllergyDetail.getInteractionText().substring(i+1); 
				
			}
			for(PatientAllergy patAllergy:patientAllergyList){
				if(patAllergy.getAllergyName().equals(allergyNameWS)){
					allergySeverityLevel=patAllergy.getAllergySeverity();
					break;
				}
			}
			if(StringUtils.isNotEmpty(allergySeverityLevel)){
				if(StringUtils.equals(allergySeverityLevel, DRUG_SEVERITY_TOOLTIP)){
					interactionData.setSeverityLevel("1");
				}else if(StringUtils.equals(allergySeverityLevel, DRUG_SEVERITY_TOOLTIP_MODERATE)){
					interactionData.setSeverityLevel("2");
				}else if(StringUtils.equals(allergySeverityLevel, MILD_LEVEL)){
					interactionData.setSeverityLevel("3");
				}
			}else {
				interactionData.setSeverityLevel("3");
			}
	
			analyticData.add(interactionData);	
		}
		return analyticData;
	}

	private static List<BatchDiseaseInteraction> getDrugDiseaseInteractionAnalyticData(
			List<DrugDiseaseDetail> drugDiseaseInteractionsList, int value) {
		List<BatchDiseaseInteraction>analyticData=new ArrayList<BatchDiseaseInteraction>();
		for(DrugDiseaseDetail diseaseDetail:drugDiseaseInteractionsList){
			/*BatchInteractionData interactionData=new BatchInteractionData();
			interactionData.setInteractionType(value);
			interactionData.setSeverityLevel(diseaseDetail.getSeverityLevel());
			interactionData.setInteractionText(diseaseDetail.getDirectCondition());*/
			BatchDiseaseInteraction diseaseInteraction=new BatchDiseaseInteraction();
			diseaseInteraction.setDrugName(diseaseDetail.getDrugName());
			diseaseInteraction.setIcd9(diseaseDetail.getICD9());
			/*diseaseInteraction.setInteractionText(diseaseDetail.getDirectCondition());*/
			diseaseInteraction.setInteractionType(value);
			diseaseInteraction.setSeverityLevel(diseaseDetail.getSeverityLevel());
			diseaseInteraction.setDirectCondition(diseaseDetail.getDirectCondition());
			diseaseInteraction.setDiseaseRelation(diseaseDetail.getDiseaseRelation());
			diseaseInteraction.setDiseaseRelationText(diseaseDetail.getDiseaseRelationText());
			diseaseInteraction.setRelatedCondition(diseaseDetail.getRelatedCondition());
			diseaseInteraction.setSeverityLevelShortText(diseaseDetail.getSeverityLevelShortText());
			diseaseInteraction.setSeverityLevelText(diseaseDetail.getSeverityLevelText());
			analyticData.add(diseaseInteraction);
		}
		return analyticData;
	}

	private static List<BatchDrugInteraction> getDrugInteractionAnalyticData(
			List<DrugInteraction> drugInteractionsList,
			double selectedDrugId, int interactionValue) {
		List<DrugInteractionOverview>drugInterectionListDetail=new ArrayList<DrugInteractionOverview>();
		List<BatchDrugInteraction>analyticData=new ArrayList<BatchDrugInteraction>();
		for(DrugInteraction drugInteractionData:drugInteractionsList){
			if(selectedDrugId==Double.valueOf(drugInteractionData.getDrug1ID())){
			DrugInteractionOverview drugInteractionOverview =new DrugInteractionOverview();
			drugInteractionOverview.setClinicalEffects(drugInteractionData.getClinicalEffects());
			drugInteractionOverview.setPatientManagement(drugInteractionData.getPatientManagement());
			drugInteractionOverview.setIssueType(DRUG_DRUG_INTERECTION);
			drugInteractionOverview.setDrugId(drugInteractionData.getDrug1());
			drugInteractionOverview.setConflict(drugInteractionData.getDrug2());
			if(StringUtils.contains(StringUtils.lowerCase(drugInteractionData.getSeverityLevel()), DRUG_SEVERITY_LEVEL_SEARCH_STR)){
				drugInteractionOverview.setSeverityLevel("1");
				drugInteractionOverview.setSeverityLevelToolTip(DRUG_SEVERITY_TOOLTIP);
			}else if(StringUtils.contains(StringUtils.lowerCase(drugInteractionData.getSeverityLevel()), DRUG_SEVERITY_LEVEL_SEARCH_STR_CONTRAINDICATED)){
				drugInteractionOverview.setSeverityLevel("1");
				drugInteractionOverview.setSeverityLevelToolTip(DRUG_SEVERITY_TOOLTIP_CONTRAINDICATED);
			}else if(StringUtils.contains(StringUtils.lowerCase(drugInteractionData.getSeverityLevel()), DRUG_SEVERITY_LEVEL_SEARCH_STR_MODERATE)){
				drugInteractionOverview.setSeverityLevel("2");
				drugInteractionOverview.setSeverityLevelToolTip(DRUG_SEVERITY_TOOLTIP_MODERATE);
			}
			
			if(!drugInterectionListDetail.contains(drugInteractionOverview)){
				drugInterectionListDetail.add(drugInteractionOverview);
			/*	BatchInteractionData interactionData=new BatchInteractionData();
				interactionData.setDrugId(Double.valueOf(drugInteractionData.getDrug1ID()));
				interactionData.setInteractionText(drugInteractionData.getClinicalEffects());
				interactionData.setInteractionType(interactionValue);
				interactionData.setSeverityLevel(drugInteractionData.getSeverityLevel());*/
				BatchDrugInteraction drugInteraction=new BatchDrugInteraction();
				drugInteraction.setDrug1Id(Double.valueOf(drugInteractionData.getDrug1ID()));
				drugInteraction.setDrug2Id(Double.valueOf(drugInteractionData.getDrug2ID()));
				//drugInteraction.setInteractionText(drugInteractionData.getClinicalEffects());
				drugInteraction.setSeverityLevel(drugInteractionOverview.getSeverityLevel());
				drugInteraction.setClinicalEffects(drugInteractionData.getClinicalEffects());
				drugInteraction.setPatientManagement(drugInteractionData.getPatientManagement());
				drugInteraction.setDrug1Name(drugInteractionData.getDrug1());
				drugInteraction.setDrug2Name(drugInteractionData.getDrug2());
				drugInteraction.setInteractionType(interactionValue);
				analyticData.add(drugInteraction);
				
			}
			}
		}
		return analyticData;
	}

	private static BatchInteractionAnalytic prepareAnalyticMaster(
			int patientId, int providerId, int loggedUserId, Date batchPullDate) {
		BatchInteractionAnalytic interactionAnalytic=new BatchInteractionAnalytic();
		interactionAnalytic.setPatientId(patientId);
		interactionAnalytic.setProviderId(providerId);
		interactionAnalytic.setUserId(loggedUserId);
		interactionAnalytic.setBatchPullDate(batchPullDate);
		return interactionAnalytic;
	}
	/**
	 * used in interactionHistory.jsf
	 * On Row Expension of Get Batch Interaction Data  
	 * @param event
	 */
	public void onRowExpensionInteractionHistory(ToggleEvent event) {
		logger.info("On  RowExpensionInteractionHistory Method Fired ");
		BatchPatientMedsHistory batchPatientMedsHistory=(BatchPatientMedsHistory) event.getData();
		logger.info("Selected Drug Id "+batchPatientMedsHistory.getDrugId());
		interactionHistory=convertToDrugInteractionObj(batchPatientMedsHistory);	// Convert Data of batch Patient med History data to interaction history object 
	}
   /**
    * used in interactionHistory.jsf page 
    * Convert batchPatient Med data to Drug Interaction overview data 
    * @param batchPatientMedsHistory
    * @return List of Drug Interaction overview of Data 
    */
	private List<DrugInteractionOverview> convertToDrugInteractionObj(
			BatchPatientMedsHistory batchPatientMedsHistory) {
		List<DrugInteractionOverview>drugInteractionOverviews=new ArrayList<DrugInteractionOverview>();
		List<DrugInteractionOverview>drugInteractions=convertDrugInteractions(batchPatientMedsHistory.getDrugInteractions()); // Convert Drug Interaction to Drug Interaction overview 
		List<DrugInteractionOverview>diseaseInteractions=convertDiseaseInteractions(batchPatientMedsHistory.getDiseaseInteractions()); // Convert Drug Disease Interaction to Drug Interaction overview 
		/*List<DrugInteractionOverview>allergyInteractions=convertAllergyInteractions(batchPatientMedsHistory.getAllergyInteractions());*/
		drugInteractionOverviews.addAll(drugInteractions); // add drug interaction  value in drug interaction overview list 
		drugInteractionOverviews.addAll(diseaseInteractions); // add drug disease interaction value in drug intraction overview list 
		/*drugInteractionOverviews.addAll(allergyInteractions);*/
		
		return drugInteractionOverviews;
	}


  /**
   * 
   * @param allergyInteractions
   * @return
   */
	private List<DrugInteractionOverview> convertAllergyInteractions(
			List<BatchAllergyInteraction> allergyInteractions) {
		
		return null;
	}


 /**
  * used in interactionHistory.jsf
  * Take the value of Disease Intearction data and convert and set it in form of Drug Interaction Overview Object List 
  * @param diseaseInteractions
  * @return
  */
	private List<DrugInteractionOverview> convertDiseaseInteractions(
			List<BatchDiseaseInteraction> diseaseInteractions) {
		List<DrugInteractionOverview>diseaseInteractionsData=new ArrayList<DrugInteractionOverview>();
		for(BatchDiseaseInteraction drugDiseaseDetail:diseaseInteractions){
			DrugInteractionOverview drugInteractionOverview =new DrugInteractionOverview();
			drugInteractionOverview.setClinicalEffects("");
			drugInteractionOverview.setConflict(drugDiseaseDetail.getDirectCondition());
			drugInteractionOverview.setIssueType(DRUG_DISEASE_INTERACTION);

			drugInteractionOverview.setPatientManagement(drugDiseaseDetail.getSeverityLevelText());
			if(StringUtils.equals(drugDiseaseDetail.getSeverityLevelShortText(), DRUG_SEVERITY_LEVEL_SEARCH_STR_DISEASE)){
				drugInteractionOverview.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				drugInteractionOverview.setSeverityLevelToolTip(drugDiseaseDetail.getSeverityLevelShortText());
			}else if((StringUtils.equals(drugDiseaseDetail.getSeverityLevelShortText(), DRUG_SEVERITY_LEVEL_SEARCH_STR_RELATIVE))||
					(StringUtils.equals(drugDiseaseDetail.getSeverityLevelShortText(), DRUG_SEVERITY_LEVEL_SEARCH_STR_CONTRADICATION))){
				drugInteractionOverview.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				drugInteractionOverview.setSeverityLevelToolTip(drugDiseaseDetail.getSeverityLevelShortText());
			}
			diseaseInteractionsData.add(drugInteractionOverview);
		}
		return diseaseInteractionsData;
	}


   /**
    * Take the value from Drug Interaction data list and convert and set these value in Drug Interaction Overview Object 
    * @param drugInteractions
    * @return List of Drug Interaction Overview 
    */
	private List<DrugInteractionOverview> convertDrugInteractions(
			List<BatchDrugInteraction> drugInteractions) {
		List<DrugInteractionOverview>drugInteractionsData=new ArrayList<DrugInteractionOverview>();
		for(BatchDrugInteraction drugInteractionData:drugInteractions){
			DrugInteractionOverview drugInteractionOverview=new DrugInteractionOverview();
			drugInteractionOverview.setClinicalEffects(drugInteractionData.getClinicalEffects());
			drugInteractionOverview.setPatientManagement(drugInteractionData.getPatientManagement());
			drugInteractionOverview.setIssueType(DRUG_DRUG_INTERECTION);
			drugInteractionOverview.setDrugId(drugInteractionData.getDrug1Name());
			drugInteractionOverview.setConflict(drugInteractionData.getDrug2Name());
			
			drugInteractionOverview.setSeverityLevel(drugInteractionData.getSeverityLevel());
			drugInteractionOverview.setSeverityLevelToolTip(DRUG_SEVERITY_TOOLTIP);//Need to modify
			drugInteractionsData.add(drugInteractionOverview);
		}
		return drugInteractionsData;
	}



	public void setPatientMedicineService(
			IPatientMedicineService patientMedicineService) {
		this.patientMedicineService = patientMedicineService;
	}

	public NcDrugDetailWSBean getDrugDetailWSBean() {
		return drugDetailWSBean;
	}

	public void setDrugDetailWSBean(NcDrugDetailWSBean drugDetailWSBean) {
		this.drugDetailWSBean = drugDetailWSBean;
	}

	public NcUpdate1WSBean getNcUpdate1WSBean() {
		return ncUpdate1WSBean;
	}

	public void setNcUpdate1WSBean(NcUpdate1WSBean ncUpdate1WSBean) {
		this.ncUpdate1WSBean = ncUpdate1WSBean;
	}


/**
 * used in adherenceHistory.jsf and interactionHistory.jsf
 * Get the adherence history data and highest severity level data and drug drug ,drug disease and drug allergy interaction data  
 * @return List of Batch Interaction history data 
 */
	public List<BatchInteractionAnalytic> getInteractionHistoryData() {
		if(interactionHistoryData==null){
			int patientId=new ContextUtil().getPatientId();
			int providerId=new ContextUtil().getProviderId();
			interactionHistoryData=patientMedicineService.getInteractionHistoryData(patientId,providerId); // Batch Intearction analytics data based on patientId and providerId 
			for(BatchInteractionAnalytic batchInteractionAnalytic:interactionHistoryData){
				for(BatchPatientMedsHistory batchPatientMedsHistory:batchInteractionAnalytic.getBatchPatientMedsHistories()){
					Set<Integer>severityLevels=new TreeSet<Integer>();
					severityLevels=getMaximumSeverityLevels(batchPatientMedsHistory); // Get Severity Level Data from Batch Patient Med History List 
					if(severityLevels!=null && !severityLevels.isEmpty()){
					int[]severeLevels=Ints.toArray(severityLevels);
					String highestSevereLevels=String.valueOf(severeLevels[0]); // Get Highest Severity Level Data 
					logger.info("Highest Severeity levels "+highestSevereLevels);
					batchPatientMedsHistory.setSeverityLevel(highestSevereLevels); // Set Highest Severity level in batchPatientMedHistory object   
					}else{
						batchPatientMedsHistory.setSeverityLevel("3");
					}
				}
			}
		}
		return interactionHistoryData;
	}


  /**
   * Get the Severity Level from batch patient med history data 
   * @param batchPatientMedsHistory
   * @return Set of Max Severiity level data 
   */
	public Set<Integer> getMaximumSeverityLevels(
			BatchPatientMedsHistory batchPatientMedsHistory) {
		Set<Integer>severityLevels=new TreeSet<Integer>();
		if(batchPatientMedsHistory!=null){
		if(batchPatientMedsHistory.getDrugInteractions()!=null){
		for(BatchDrugInteraction drugInteraction:batchPatientMedsHistory.getDrugInteractions()){
			if(StringUtils.isNotBlank(drugInteraction.getSeverityLevel()) && !StringUtils.isAlpha(drugInteraction.getSeverityLevel())){
				severityLevels.add(Integer.valueOf(drugInteraction.getSeverityLevel())); // Severity Interaction data for Drug Intearction data 
			}
		}
		}
		if(batchPatientMedsHistory.getDiseaseInteractions()!=null){
			for(BatchDiseaseInteraction batchDiseaseInteraction:batchPatientMedsHistory.getDiseaseInteractions()){
				if(StringUtils.isNotBlank(batchDiseaseInteraction.getSeverityLevel()) && !StringUtils.isAlpha(batchDiseaseInteraction.getSeverityLevel())){
					severityLevels.add(Integer.valueOf(batchDiseaseInteraction.getSeverityLevel())); // Severity Interaction data for Disease interaction data 
				}
			}
		}
		}
		return severityLevels;
	}



	public void setInteractionHistoryData(
			List<BatchInteractionAnalytic> interactionHistoryData) {
		this.interactionHistoryData = interactionHistoryData;
	}



	public void setPatientService(IPatientService patientService) {
		this.patientService = patientService;
	}



	public List<DrugInteractionOverview> getInteractionHistory() {
		return interactionHistory;
	}



	public void setInteractionHistory(
			List<DrugInteractionOverview> interactionHistory) {
		this.interactionHistory = interactionHistory;
	}
	
	
	

}
