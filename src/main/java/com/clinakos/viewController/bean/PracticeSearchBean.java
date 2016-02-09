/**
 * 
 */
package com.clinakos.viewController.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.primefaces.event.TabChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.model.core.AuditPatientDeactivation;
import com.clinakos.data.model.core.PracticeSearchModel;
import com.clinakos.data.model.core.PracticeSearchResultSet;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.rules.PsychopharmMessage;
import com.clinakos.service.IUserService;

/**
 * @author Nagaraj
 * 
 * Bean used to search all practices
 */
public class PracticeSearchBean implements Serializable{

	public static final Logger logger = Logger.getLogger("PracticeSearchBean.class");
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private IUserService userService;
	
	private List<UserLoginDetail> patientData;
	
	private static final String MANAGE_PATIENT_PAGE = "go_to_manage_patient"; 
	
	private UserLoginDetail[] selectedPatients;
	
	private List<UserLoginDetail>deactivatedPatients;//This list will provide deactivated patients for the provider
	
	
	private PracticeSearchModel searchObj=new PracticeSearchModel();
	
	/**
	 * Search the query based on Diagnosis or Drugs/Drug Id
	 */
	public void doPracticeSearch(){
		logger.info("{--}{--}{--} Doing Practice Search {--}{--}{--} "+searchObj.toString());
		boolean isSearchFieldsNotEmpty = validateSearchFields(searchObj);

		if(!isSearchFieldsNotEmpty){
		String drugs = searchObj.getDrugs();
		String diagnosis = searchObj.getDiagnosis();
		String lab = searchObj.getLab();
		Date fromDate = searchObj.getFromDateSearch();
		Date toDate = searchObj.getToDateSearch();
		List<PracticeSearchResultSet> drugResultSet = null;
		List<PracticeSearchResultSet> diagnosisResultSet = null;
		List<PracticeSearchResultSet> labResultSet = null;
		if(StringUtils.isNotBlank(drugs)){
			if(StringUtils.isAlpha(drugs)){
				drugResultSet = userService.findAllPracticesDrugByName(drugs);
			}else if(StringUtils.isNumeric(drugs)){
				Double drugId = Double.valueOf(drugs);
				drugResultSet = userService.findAllPracticesDrugById(drugId);
			}
		}
		if(StringUtils.isNotBlank(diagnosis)){
			if(StringUtils.isAlpha(diagnosis)){
				diagnosisResultSet = userService.findAllPracticesDiagnosis(diagnosis);
			}else if(StringUtils.isNumeric(diagnosis)){
				diagnosisResultSet = userService.findAllPracticesDiagnosisById(diagnosis);
			}
		}
		if(StringUtils.isNotBlank(lab)){
			if(StringUtils.isAlpha(lab)){
				labResultSet = userService.findAllPracticesLabByName(lab);
			}else{
				labResultSet = userService.findAllPracticesLabById(lab);
			}
		}
		Map<Integer,List<PracticeSearchResultSet>> resultMap = new HashMap<Integer, List<PracticeSearchResultSet>>();
		if(fromDate!=null && toDate!=null){
			
			if(drugResultSet!=null||diagnosisResultSet!=null||labResultSet!=null){
				if(drugResultSet!=null){
					resultMap =getResultSetMap(drugResultSet,resultMap,fromDate,toDate);
					logger.info("In Drug Result Map "+resultMap.size());
				}
				if(diagnosisResultSet!=null){
					resultMap = getResultSetMap(diagnosisResultSet,resultMap,fromDate,toDate);
					logger.info("In Diagnosis Result Map "+resultMap.size());
				}if(labResultSet!=null){
					resultMap = getResultSetMap(labResultSet,resultMap,fromDate,toDate);
					logger.info("In Lab Result Map "+resultMap.size());
				}
				logger.info("Total  Result Map size "+resultMap.size());
				searchObj.setPracticeSearchResultSet(getUI_ResultSet(resultMap));
			}else{
				//Get data from from date and to date;
				List<PracticeSearchResultSet>practiceSearchResultSets = userService.findPracticeDataBasedOnDate(fromDate,toDate);
				searchObj.setPracticeSearchResultSet(practiceSearchResultSets);
			}
		}else{
			if(drugResultSet!=null){
				resultMap = getResultMapWithoutDate(drugResultSet,resultMap);
			}
			if(diagnosisResultSet!=null){
				resultMap = getResultMapWithoutDate(diagnosisResultSet,resultMap);
			}
			if(labResultSet!=null){
				resultMap = getResultMapWithoutDate(labResultSet,resultMap);
			}
			searchObj.setPracticeSearchResultSet(getUI_ResultSet(resultMap));
		}
		}else{
			//Show Message to User
			searchObj.setPracticeSearchResultSet(new ArrayList<PracticeSearchResultSet>());
		}
		
		logger.info("{--}{--}{--} Completed Practice Search {--}{--}{--} ");
	}
	
	
	
	private Map<Integer, List<PracticeSearchResultSet>> getResultMapWithoutDate(
			List<PracticeSearchResultSet> resultSet,
			Map<Integer, List<PracticeSearchResultSet>> resultMap) {
		for(PracticeSearchResultSet result: resultSet){
				if(!resultMap.containsKey(result.getPatientId())){
					List<PracticeSearchResultSet> practiceSearchResultSets = new ArrayList<PracticeSearchResultSet>();
					practiceSearchResultSets.add(result);
					resultMap.put(result.getPatientId(), practiceSearchResultSets);
				}
			
		}
		return resultMap;
		
	}



	private List<PracticeSearchResultSet> getUI_ResultSet(
			Map<Integer, List<PracticeSearchResultSet>> resultMap) {
		List<PracticeSearchResultSet>resultSet = new ArrayList<PracticeSearchResultSet>();
		if(resultMap!=null && !resultMap.isEmpty()){
			for(Map.Entry<Integer,List<PracticeSearchResultSet>> entry : resultMap.entrySet()){
				List<PracticeSearchResultSet>pResultSet = entry.getValue();
				resultSet.addAll(pResultSet);
			}
		}
		return resultSet;
	}



	private Map<Integer, List<PracticeSearchResultSet>> getResultSetMap(
			List<PracticeSearchResultSet> resultSet,
			Map<Integer, List<PracticeSearchResultSet>> resultMap, Date fromDate, Date toDate) {
		
		LocalDate jFromDate=new DateTime(fromDate).toLocalDate();
		LocalDate jToDate=new DateTime(toDate).toLocalDate();
		for(PracticeSearchResultSet result: resultSet){
			LocalDate oDate=new DateTime(result.getDate()).toLocalDate();
			if(oDate.isAfter(jFromDate) && oDate.isBefore(jToDate)){
				if(!resultMap.containsKey(result.getPatientId())){
					List<PracticeSearchResultSet> practiceSearchResultSets = new ArrayList<PracticeSearchResultSet>();
					practiceSearchResultSets.add(result);
					resultMap.put(result.getPatientId(), practiceSearchResultSets);
				}
			}
		}
		return resultMap;
	}



	private List<PracticeSearchResultSet> getSearchResult(
			List<PracticeSearchResultSet> diagnosisResultSet, Map<Integer, PracticeSearchResultSet> practiceSearchMap) {
		List<PracticeSearchResultSet>uiSearchResultSet = new ArrayList<PracticeSearchResultSet>();
		for(PracticeSearchResultSet practResultSet : diagnosisResultSet){
			int patientId = practResultSet.getPatientId();
			PracticeSearchResultSet practiceMapObj = practiceSearchMap.get(patientId);
			if(practiceMapObj!=null){
			if(patientId == practiceMapObj.getPatientId()){
				uiSearchResultSet.add(practResultSet);
			}
			}
		}
		return uiSearchResultSet;
	}



	private Map<Integer, PracticeSearchResultSet> getPracticeMap(
			List<PracticeSearchResultSet> drugIdResultSet) {
		Map<Integer,PracticeSearchResultSet> practiceSearchMap  = new HashMap<Integer, PracticeSearchResultSet>();
		for(PracticeSearchResultSet praResultSet : drugIdResultSet){
			practiceSearchMap.put(praResultSet.getPatientId(), praResultSet);
		}
		return practiceSearchMap;
	}



	/**
	 * Validate User Input fields
	 * @param searchObj2
	 * @return
	 */
	private boolean validateSearchFields(PracticeSearchModel searchObj2) {
		if(StringUtils.isBlank(searchObj2.getDiagnosis()) && StringUtils.isBlank(searchObj2.getDrugs()) && StringUtils.isNotBlank(searchObj2.getLab())
				&& searchObj2.getFromDateSearch()!=null && searchObj2.getToDateSearch()!=null){
		return true;
		}else{
			return false;
		}
	}


	public void doResetSearch(){
		logger.info("{--}{--}{--} Resetting Search {--}{--}{--}");
		searchObj = new PracticeSearchModel();
		try{
		if(searchObj.getPracticeSearchResultSet()!=null){
			searchObj.getPracticeSearchResultSet().clear();
		}else{
			searchObj.setPracticeSearchResultSet(new ArrayList<PracticeSearchResultSet>());
		}
		}catch(Exception e){
			logger.info("Something went Wrong while Resetting "+e.getMessage());
			e.printStackTrace();
		}
		logger.info("{--}{--}{--} Completed Resetting Search {--}{--}{--}");
	}
	
	/**
	 * It will Go to admin | manage patient page will list all patient details of that provider who are active
	 * @return
	 */

	public String resetPatientData(){
		refreshPatientList();
		
		return MANAGE_PATIENT_PAGE;
	}
	
	/**
	 * List used to show active patients in UI 
	 * @return
	 */
	public List<UserLoginDetail> getPatientData() {
		if(patientData == null ){
			patientData = new ArrayList<UserLoginDetail>();
			try{
				int providerId = new ContextUtil().getProviderId();
				patientData = userService.getAllPatientDataforProvider(providerId);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return patientData;
	}
	
	/**
	 * When Deactivation button is clicked from UI for selected Patients
	 */
	public void doPatientDeactivation(){
		logger.info(" doPatientDeactivation method Fired : Selected patients size "+selectedPatients.length);
		List<AuditPatientDeactivation>auditPatientDeactiveRecords = new ArrayList<AuditPatientDeactivation>();
		Date deactivatedDate = new Date();
		int loginId = new ContextUtil().getLoginId();
		String loggedUserFirstName = new ContextUtil().getLoggerFirstName();
		String loggedUserLastName = new ContextUtil().getLoggerLastName(); 
		List<UserLoginDetail>deactivatedPatientsDetails = new ArrayList<UserLoginDetail>();
		if(selectedPatients.length> 0){
			for(UserLoginDetail userDetailObj : selectedPatients){
				boolean isSuccessInsertOperation=false;
				boolean isSuccessDeleteOperation=false;
				/**
				 * Step 1: Get all data for selected patient and insert above data into deactivated tables  by using procedure
				 * 
				 * Step 2: delete all data from original table
				 * 
				 * Step 3: Record the user who deleted the record
				 * 
				 * 
				 */
				// Step 1: Get all data for selected patient and insert above data into deactivated tables  by using procedure
				try{
				isSuccessInsertOperation = userService.insertPatientAllDataToDeactivation(userDetailObj.getProviderId(),userDetailObj.getUserId());
				}catch(Exception e){
					isSuccessInsertOperation = false;
					e.printStackTrace();
				}
				
			//	if(success in inserting data in deactivated tables) then
				if(isSuccessInsertOperation){
				try{
				isSuccessDeleteOperation = userService.deletePatientAllData(userDetailObj.getProviderId(),userDetailObj.getUserId());
				}catch(Exception e){
					isSuccessInsertOperation = false;
					e.printStackTrace();
				}
				}
				
				//Step 3: Record the user who deleted the record
				if(isSuccessDeleteOperation){
				AuditPatientDeactivation auditObj = userService.prepareDeactivationAuditObj(userDetailObj.getUserId(),userDetailObj.getProviderId(),deactivatedDate,loginId);
				auditPatientDeactiveRecords.add(auditObj);
				UserLoginDetail userLoginDetail = prepareDeactivatedPatientObj(userDetailObj,loggedUserFirstName,loggedUserLastName,deactivatedDate);
				deactivatedPatientsDetails.add(userLoginDetail);
				}
			}
			boolean isSuccessFullyAudited = false;
			if(!auditPatientDeactiveRecords.isEmpty()){
				//save bulk audit records
				try{
				isSuccessFullyAudited = userService.saveAuditForPatientDeactivation(auditPatientDeactiveRecords);
				//Then refresh deactivated patient list and active patient list
				//First refresh active patients
			//	boolean isActivePatientsRefreshed = RefreshActivePatients(patientData,deactivatedPatientsDetails);
				
			/*	for(UserLoginDetail deactivedPatients:deactivatedPatientsDetails){
					for(UserLoginDetail activePatients:patientData){
						
					}
				}*/
				
				refreshPatientList();
				
				}catch(Exception e){
					isSuccessFullyAudited = false;
					e.printStackTrace();
				}
				logger.info("is Audit Records Successfully Saved "+isSuccessFullyAudited);
			}
			
		}else{
			//Pls make sure user selects atleast one patient
		}
	}
	




	private void refreshPatientList() {
		patientData = null;
		getPatientData();
		deactivatedPatients = null;
		getDeactivatedPatients();
		
	}



	private UserLoginDetail prepareDeactivatedPatientObj(
			UserLoginDetail userDetailObj, String loggedUserFirstName, String loggedUserLastName, Date deactivatedDate) {
		UserLoginDetail userLoginDetail =new UserLoginDetail();
		userLoginDetail.setUserId(userDetailObj.getUserId());
		userLoginDetail.setFirstName(userDetailObj.getFirstName());
		userLoginDetail.setLastName(userDetailObj.getLastName());
		userLoginDetail.setUserFirstName(loggedUserFirstName);
		userLoginDetail.setUserLastName(loggedUserLastName);
		userLoginDetail.setProviderId(userDetailObj.getProviderId());
		userLoginDetail.setDeactivatedDate(deactivatedDate);
		return userLoginDetail;
	}



	/**
	 * Triggered from Manage Patient UI.
	 * Whenever user changes tab from Active patients to Deactivated Patients and vice versa
	 * @param event
	 */
	
	public void onTabChange(TabChangeEvent event) {
		
        /*FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);*/
		
		logger.info("Tab changed :: Active Tab is Now "+event.getTab().getTitle());
		logger.info("Tab Data :: "+event.getData().toString());
    }
	
	
	
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public PracticeSearchModel getSearchObj() {
		return searchObj;
	}

	public void setSearchObj(PracticeSearchModel searchObj) {
		this.searchObj = searchObj;
	}



	public void setPatientData(List<UserLoginDetail> patientData) {
		this.patientData = patientData;
	}



	public UserLoginDetail[] getSelectedPatients() {
		return selectedPatients;
	}



	public void setSelectedPatients(UserLoginDetail[] selectedPatients) {
		this.selectedPatients = selectedPatients;
	}



	public List<UserLoginDetail> getDeactivatedPatients() {
		if(deactivatedPatients == null){
			deactivatedPatients = new ArrayList<UserLoginDetail>();
			int providerId = new ContextUtil().getProviderId();
			try{
				deactivatedPatients = userService.getDeactivatedPatients(providerId);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return deactivatedPatients;
	}



	public void setDeactivatedPatients(List<UserLoginDetail> deactivatedPatients) {
		this.deactivatedPatients = deactivatedPatients;
	}



	
	
	
	
	
}
