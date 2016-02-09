/**
 * 
 */
package com.clinakos.viewController.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;












import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.TabChangeEvent;
import org.springframework.web.client.RestTemplate;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.model.patient.ClinakosValidicDevices;
import com.clinakos.data.model.patient.ValidicUsersModel;
import com.clinakos.service.IValidicRestService;
import com.clinakos.viewController.webservicMangedBean.ValidicRestWSManagedBean;
import com.validic.Summary;
import com.validic.UserResponse;
import com.validic.ValidicAppResponse;
import com.validic.ValidicBatchTabs;
import com.validic.ValidicBiometricmeasurement;
import com.validic.ValidicDiabetesMeasurements;
import com.validic.ValidicFitnessActivity;
import com.validic.ValidicNutrition;
import com.validic.ValidicRoutineActivity;
import com.validic.ValidicSleep;
import com.validic.ValidicTobaccoCessation;
import com.validic.ValidicWeight;
import com.validic.ValidicWeightResponse;

import static com.clinakos.common.util.ClinakosConstant.*;

/**
 * @author IDC-0004
 *	BatchDevicesBean is used in admin Role for syncing the data with validic 
 *  for users who already connected to validic apps
 */
public class BatchDevicesBean implements Serializable{
	
	public static final Logger logger = Logger.getLogger("BatchDevicesBean.class");

	private static final long serialVersionUID = 1L;

	private IValidicRestService validicRestService;
	
	private List<ValidicUsersModel>vFitnessUsers;
	private List<ValidicUsersModel>vNutritionUsers;
	private List<ValidicUsersModel>vRoutineUsers;
	private List<ValidicUsersModel>vDiabetesUsers;
	private List<ValidicUsersModel>vBiometricUsers;
	private List<ValidicUsersModel>vSleepUsers;
	private List<ValidicUsersModel>vWeightUsers;
	
	public List<ValidicUsersModel>selectedUsers;
	
	
	private final String REFRESH_URL = "user_refresh_url";
	
	private final String ORGANISATION_ID_LABEL = "Organization_Id";
	private final String ORGANISTAION_ACCESS_TOKEN_LABEL = "Organization_Access_Token";
	
	private String organisationId;
	private String organisationAcessToken; 
	
	Properties properties = null;//Loading Validic device API's
	private List<ValidicBatchTabs> vTabList;
	private List<ValidicUsersModel>validicUsers;
	int tabNumber;
	public boolean initTab;
	
	/**
	 * Users who connected to respective apps will be synced and new data will saved to clinakos database if any available.
	 * Two validic webservice will be made:First for refresh/sync webservice call and then getting data from validic 
	 * @return
	 */
	public void batchRefreshAPI(){
		
		logger.info(" Users selected === > "+selectedUsers.size());
		Map<String,String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	  int tabNo = Integer.valueOf(params.get("tabNo"));
		logger.info("Submitted & Tab Number "+tabNo);
		
		/**
		 * Step 1: Itereate Over Users selected list and make sync/webservice call
		 * 
		 * Step 2: then make call to latest fitness Json to validicRestServiceBean
		 * 
		 */
		
		ClinakosValidicDevices validicDevices =prepareClinakosValidicDevices(tabNo);
		int updatedDataCounter=0;//tells about the clinakos db is updated to validic data
		int notUpadatedDataCounter =0;//tells about already upto date with validic
		int providerId = new ContextUtil().getProviderId();
		for(ValidicUsersModel user:selectedUsers){
			int userId = user.getClinakosUserId();
			logger.info("User Id "+userId);
		
			Set<String>appSource = user.getUserAppMap().get(userId);
			
			logger.info("APP SIZE "+appSource.size());
			boolean isRefreshAPI_success = false;
	
			for(String appName : appSource){
				String refresh_URL = getRefreshURL(user.getUserAccessToken(),appName);
				if(StringUtils.isNotBlank(refresh_URL)){
					RestTemplate restTemplate = new RestTemplate();
					try{
						ValidicAppResponse  valAppResponse =	
					 restTemplate
							.getForObject(refresh_URL, ValidicAppResponse.class);
					Summary summary =valAppResponse.getSummary();
					if(summary.getMessage().equalsIgnoreCase("ok")){
						logger.info("VALIDIC api Successfully made refresh API Call "+summary.getResults());
						isRefreshAPI_success = true;
						
					}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			if(isRefreshAPI_success){
			
				logger.info("Now System getting validic new data and syncing the data to clinakos");
				/*FacesContext context = FacesContext	.getCurrentInstance();
				ValidicRestWSManagedBean validicRestManagedBean=(ValidicRestWSManagedBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"validicRestManagedBean");*/
				
				boolean isSavedSuccessfully = syncDataToClinakos(validicDevices,userId,providerId,user.getUserAccessToken(),getOrganisationId(),getOrganisationAcessToken(),user.getValidicUserId());
				
				logger.info("is Saved Successfully "+isSavedSuccessfully);
				if(isSavedSuccessfully){
					updatedDataCounter = updatedDataCounter+1;
				}else{
					notUpadatedDataCounter=notUpadatedDataCounter+1;
				}
			}
			
			
		}
		
		String msg = "Data updated for : "+updatedDataCounter+" patients ";
		RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "Validic Result ", msg));
		
		
	}

	
	private ClinakosValidicDevices prepareClinakosValidicDevices(int tabNumber) {
		ClinakosValidicDevices validicDevices = new ClinakosValidicDevices();
		if(tabNumber == VALIDIC_TAB_1){//Fitness
			validicDevices.setFitness(true);
			
		}else if(tabNumber == VALIDIC_TAB_2){
			validicDevices.setNutrition(true);
			
		}else if(tabNumber == VALIDIC_TAB_3){
			validicDevices.setWeight(true);
			
		} else if(tabNumber == VALIDIC_TAB_4){
			validicDevices.setSleep(true);
			
		}else if(tabNumber == VALIDIC_TAB_5){
			validicDevices.setBiometric(true);
			
		}else if(tabNumber == VALIDIC_TAB_6){
			
			validicDevices.setDiabetes(true);
		}else if(tabNumber == VALIDIC_TAB_7){
			validicDevices.setTobacco(true);
			
		}
		return validicDevices;
	}


	private boolean syncDataToClinakos(ClinakosValidicDevices validicDevices, int patientId,
			int providerId, String userAccessToken, String organisationId2,
			String organisationAcessToken2,String validicUserId) {
		FacesContext context = FacesContext	.getCurrentInstance();
		ValidicRestWSManagedBean validicRestManagedBean=(ValidicRestWSManagedBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"validicRestManagedBean");
		UserResponse userObj = new UserResponse();
		userObj.setAccess_token(userAccessToken);
		userObj.setOrganizationAccessToken(organisationAcessToken2);
		userObj.setOrganizationId(organisationId2);
		userObj.setPatientId(patientId);
		userObj.setProviderId(providerId);
		userObj.set_id(validicUserId);
		boolean isSaved=false;
		if(userObj!=null){
			if(validicDevices.isFitness()){
			List<ValidicFitnessActivity>validicUserFitnessActivities=validicRestManagedBean.getLatestFitnessActivities(userObj);
			List<ValidicFitnessActivity>clinakosValidicFitnessData=new ArrayList<ValidicFitnessActivity>();
			try{
			clinakosValidicFitnessData=validicRestService.getClinakosValidicFitnessData(patientId,userObj);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(clinakosValidicFitnessData!=null && validicUserFitnessActivities!=null){
			boolean isFitnessSaved=validicRestManagedBean.syncToClinakosFitness(clinakosValidicFitnessData,validicUserFitnessActivities,patientId,providerId);
			isSaved = isFitnessSaved;
			}
			}
			
			else if(validicDevices.isDiabetes()){
			List<ValidicDiabetesMeasurements>validicUserDiabetesMeasurements=validicRestManagedBean.getLatestUserDiabetesMeasurements(userObj);
			List<ValidicDiabetesMeasurements>userDiabetesData=new ArrayList<ValidicDiabetesMeasurements>();
			try{
				userDiabetesData=validicRestService.getValidicPatientDiabetesMeasurementData(String.valueOf(patientId));
			}catch(Exception e){
				e.printStackTrace();
			}
			if(userDiabetesData!=null && validicUserDiabetesMeasurements!=null){
				boolean isDiabetesSaved=validicRestManagedBean.syncToClinakosDiabetes(userDiabetesData,validicUserDiabetesMeasurements,patientId,providerId);
				isSaved = isDiabetesSaved;
		
			}
			}
			
			else if(validicDevices.isBiometric()){
			List<ValidicBiometricmeasurement>validicUserBiometricMeasurements=validicRestManagedBean.getLatestBiometricMeasurements(userObj);
			List<ValidicBiometricmeasurement>userBiometricData=new ArrayList<ValidicBiometricmeasurement>();
			try{
				userBiometricData=validicRestService.getValidicBiometricMeasurementData(String.valueOf(patientId));
				
			}catch(Exception e){
				e.printStackTrace();
			}
			if(userBiometricData!=null && validicUserBiometricMeasurements!=null){
				boolean isBiometricSaved=validicRestManagedBean.syncToClinakosBiometric(userBiometricData,validicUserBiometricMeasurements,patientId,providerId);
				isSaved = isBiometricSaved;
			}
			
			}
			else if(validicDevices.isRoutine()){
			List<ValidicRoutineActivity>validicUserRoutineActivities=validicRestManagedBean.getLatestRoutineActivities(userObj);
			List<ValidicRoutineActivity>userRoutineActivitiesData=new ArrayList<ValidicRoutineActivity>();
			try{
				userRoutineActivitiesData=validicRestService.getValidicPatientRoutineActivityData(String.valueOf(patientId));
			}catch(Exception e){
				e.printStackTrace();
			}
			if(userRoutineActivitiesData!=null && validicUserRoutineActivities!=null){
				boolean isRoutineActivitySaved=validicRestManagedBean.syncToClinakosRoutineActivities(userRoutineActivitiesData,validicUserRoutineActivities,patientId,providerId);
				isSaved = isRoutineActivitySaved;
			}
			}
			else if(validicDevices.isNutrition()){
				List<ValidicNutrition>validicUserNutritions=validicRestManagedBean.getLatestNutritions(userObj);
				List<ValidicNutrition>userNutritionData=new ArrayList<ValidicNutrition>();
				try{
					userNutritionData=validicRestService.getValidicPatientNutritionsData(String.valueOf(patientId));
				}catch(Exception e){
					e.printStackTrace();
				}
				if(userNutritionData !=null && validicUserNutritions!=null){
					boolean isNutritionSaved=validicRestManagedBean.syncToClinakosNutrition(userNutritionData,validicUserNutritions,patientId,providerId);
					isSaved = isNutritionSaved;
								}
				}
				
				else if(validicDevices.isSleep()){
				List<ValidicSleep>validicUserSleepActivities=validicRestManagedBean.getLatestSleepActivities(userObj);
				List<ValidicSleep>userSleepData=new ArrayList<ValidicSleep>();
				try{
					userSleepData=validicRestService.validicPatientSleepData(String.valueOf(patientId));
				}catch(Exception e){
					e.printStackTrace();
				}
				if(userSleepData!=null && validicUserSleepActivities!=null){
					boolean isSleepActivitySaved=validicRestManagedBean.syncToClinakosSleepActivity(userSleepData,validicUserSleepActivities,patientId,providerId);
					isSaved = isSleepActivitySaved;
					
				}
				}
				
				else if(validicDevices.isWeight()){
				List<ValidicWeight>validicUserWeightActivities=validicRestManagedBean.getLatestWeightActivities(userObj);
				List<ValidicWeight>clinakosUserWeightData=new ArrayList<ValidicWeight>();
				try{
					clinakosUserWeightData=validicRestService.getValidicPatientWeightData(patientId);
				}catch(Exception e){
					e.printStackTrace();
				}
				if(clinakosUserWeightData!=null && validicUserWeightActivities!=null){
					boolean isWeightActivitySaved=validicRestManagedBean.syncToClinakosWeightActivity(clinakosUserWeightData,validicUserWeightActivities,patientId,providerId);
					isSaved = isWeightActivitySaved;
					
				}
				}
				
				else if(validicDevices.isTobacco()){
				List<ValidicTobaccoCessation>validicUserTobaccoCessationActivities=validicRestManagedBean.getLatestTobaccoCessationActivities(userObj);
				List<ValidicTobaccoCessation>clinakosTobaccoCessationData=new ArrayList<ValidicTobaccoCessation>();
				try{
					clinakosTobaccoCessationData=validicRestService.getValidicPatientTobaccoData(String.valueOf(patientId));
				}catch(Exception e){
					e.printStackTrace();
				}
				if(clinakosTobaccoCessationData!=null && validicUserTobaccoCessationActivities!=null){
					boolean isTobaccoCessationDataSaved=validicRestManagedBean.syncToClinakosTobaccoCessationActivity(clinakosTobaccoCessationData,validicUserTobaccoCessationActivities,patientId,providerId);
					isSaved = isTobaccoCessationDataSaved;
					
				}
				}
			}
		return isSaved;
	}


	
	



	/**
	 * 
	 * get the refresh URL for the app name 
	 * @param userAccessToken
	 * @param appName
	 * @return
	 */
	private String getRefreshURL(String userAccessToken, String appName) {
		String url = new String();
		url = getProperties().getProperty(REFRESH_URL);
		logger.info("Refresh URL params Organisatoiion ID "+getOrganisationId()+" source Name "+appName+" user acesss token "+userAccessToken);
		url=url.replace("{ORGANIZATION_ID}", getOrganisationId());
		url=url.replace("{SOURCE_NAME}", appName);
		url=url.replace("{USER_ID}",userAccessToken);
		logger.info("URL is "+url);
		return url;
	}

	public IValidicRestService getValidicRestService() {
		return validicRestService;
	}

	public void setValidicRestService(IValidicRestService validicRestService) {
		this.validicRestService = validicRestService;
	}

	public List<ValidicUsersModel> getvFitnessUsers() {
		if(vFitnessUsers == null){
			vFitnessUsers = new ArrayList<ValidicUsersModel>();
			
			try{
				int providerId = new ContextUtil().getProviderId();
				List<ValidicUsersModel> fitnessUsers= validicRestService.get_vFitnessUsers(providerId);
				vFitnessUsers = validicRestService.getUniqueUsers(fitnessUsers);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return vFitnessUsers;
	}

	public void setvFitnessUsers(List<ValidicUsersModel> vFitnessUsers) {
		this.vFitnessUsers = vFitnessUsers;
	}

	public List<ValidicUsersModel> getvNutritionUsers() {
		if(vNutritionUsers == null){
			vNutritionUsers = new ArrayList<ValidicUsersModel>();
		
			try{
				int providerId = new ContextUtil().getProviderId();
				List<ValidicUsersModel>nutritionUsers = validicRestService.get_vNutritionUsers(providerId);
				vNutritionUsers = validicRestService.getUniqueUsers(nutritionUsers);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return vNutritionUsers;
	}

	public void setvNutritionUsers(List<ValidicUsersModel> vNutritionUsers) {
		this.vNutritionUsers = vNutritionUsers;
	}

	public List<ValidicUsersModel> getvRoutineUsers() {
		if(vRoutineUsers == null){
			vRoutineUsers = new ArrayList<ValidicUsersModel>();
			
			try{
				int providerId = new ContextUtil().getProviderId();
				List<ValidicUsersModel>routineUsers = validicRestService.get_vRoutineUsers(providerId);
				vRoutineUsers = validicRestService.getUniqueUsers(routineUsers);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return vRoutineUsers;
	}

	public void setvRoutineUsers(List<ValidicUsersModel> vRoutineUsers) {
		this.vRoutineUsers = vRoutineUsers;
	}

	public List<ValidicUsersModel> getvDiabetesUsers() {
		if(vDiabetesUsers == null){
			vDiabetesUsers = new ArrayList<ValidicUsersModel>();
			
			try{
				int providerId = new ContextUtil().getProviderId();
				List<ValidicUsersModel>diabetesUsers = validicRestService.get_vDiabetesUsers(providerId);
				vDiabetesUsers = validicRestService.getUniqueUsers(diabetesUsers);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return vDiabetesUsers;
	}

	public void setvDiabetesUsers(List<ValidicUsersModel> vDiabetesUsers) {
		this.vDiabetesUsers = vDiabetesUsers;
	}

	public List<ValidicUsersModel> getvBiometricUsers() {
		if(vBiometricUsers == null){
			vBiometricUsers = new ArrayList<ValidicUsersModel>();
			try{
				int providerId = new ContextUtil().getProviderId();
				List<ValidicUsersModel>biometricUsers = validicRestService.get_vBiometricUsers(providerId);
				vBiometricUsers = validicRestService.getUniqueUsers(biometricUsers);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return vBiometricUsers;
	}

	public void setvBiometricUsers(List<ValidicUsersModel> vBiometricUsers) {
		this.vBiometricUsers = vBiometricUsers;
	}

	public List<ValidicUsersModel> getvSleepUsers() {
		if(vSleepUsers == null){
			vSleepUsers = new ArrayList<ValidicUsersModel>();
			try{
				int providerId = new ContextUtil().getProviderId();
				List<ValidicUsersModel>sleepUsers = validicRestService.get_vSleepUsers(providerId);
				vSleepUsers = validicRestService.getUniqueUsers(sleepUsers);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return vSleepUsers;
	}

	public void setvSleepUsers(List<ValidicUsersModel> vSleepUsers) {
		this.vSleepUsers = vSleepUsers;
	}

	public List<ValidicUsersModel> getvWeightUsers() {
		if(vWeightUsers == null){
			vWeightUsers = new ArrayList<ValidicUsersModel>();
			try{
				int providerId = new ContextUtil().getProviderId();
				List<ValidicUsersModel>weightUsers = validicRestService.get_vWeightUsers(providerId);
				vWeightUsers = validicRestService.getUniqueUsers(weightUsers);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return vWeightUsers;
	}

	public void setvWeightUsers(List<ValidicUsersModel> vWeightUsers) {
		this.vWeightUsers = vWeightUsers;
	}

	public List<ValidicUsersModel> getSelectedUsers() {
		return selectedUsers;
	}

	public void setSelectedUsers(List<ValidicUsersModel> selectedUsers) {
		this.selectedUsers = selectedUsers;
	}

	public Properties getProperties() {
		if(properties == null){
			properties = new Properties();
			try {
				properties.load(BatchDevicesBean.class.getClassLoader()
						.getResourceAsStream(
								"/com/clinakos/properties/validicRestUrl.properties"));
				setOrganisationId((String) properties.get(ORGANISATION_ID_LABEL));
				setOrganisationAcessToken((String) properties.get(ORGANISTAION_ACCESS_TOKEN_LABEL));
			} catch (IOException e) {
				logger.error("Not able To load the file "+e);
				e.printStackTrace();
			}
		}
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(String organisationId) {
		this.organisationId = organisationId;
	}

	public String getOrganisationAcessToken() {
		return organisationAcessToken;
	}

	public void setOrganisationAcessToken(String organisationAcessToken) {
		this.organisationAcessToken = organisationAcessToken;
	}


	public List<ValidicBatchTabs> getvTabList() {
		if(vTabList == null){
			vTabList = new ArrayList<ValidicBatchTabs>();
			vTabList.add(new ValidicBatchTabs(VALIDIC_TAB_1, VALIDIC_FITNESS_LABEL));
			vTabList.add(new ValidicBatchTabs(VALIDIC_TAB_2, VALIDIC_NUTRITION_LABEL));
			vTabList.add(new ValidicBatchTabs(VALIDIC_TAB_3, VALIDIC_WEIGHT_LABEL));
			vTabList.add(new ValidicBatchTabs(VALIDIC_TAB_4, VALIDIC_SLEEP_LABEL));
			vTabList.add(new ValidicBatchTabs(VALIDIC_TAB_5, VALIDIC_BIOMETRIC_LABEL));
			vTabList.add(new ValidicBatchTabs(VALIDIC_TAB_6, VALIDIC_DIABETES_LABEL));
			vTabList.add(new ValidicBatchTabs(VALIDIC_TAB_7, VALIDIC_TOBACCO_LABEL));
		}
		return vTabList;
	}


	public void setvTabList(List<ValidicBatchTabs> vTabList) {
		this.vTabList = vTabList;
	}



	public void onTabChange(TabChangeEvent event){
		logger.info(" On TabChange Event called ");
		
		ValidicBatchTabs selectedTab = (ValidicBatchTabs) event.getData();
		logger.info("Selected Tab Number "+selectedTab.getTabNumber()+" Tab Name "+selectedTab.getTabLabel());
		validicUsers = new ArrayList<ValidicUsersModel>();
		int tabNumber = selectedTab.getTabNumber();
		initializeTab(tabNumber);
		
		
	}


	public List<ValidicUsersModel> getValidicUsers() {
		return validicUsers;
	}


	public void setValidicUsers(List<ValidicUsersModel> validicUsers) {
		this.validicUsers = validicUsers;
	}


	public int getTabNumber() {
		return tabNumber;
	}


	public void setTabNumber(int tabNumber) {
		this.tabNumber = tabNumber;
	}


	public boolean isInitTab() {
		initializeTab(getvTabList().get(0).getTabNumber());
		return initTab;
	}


	


	public void initializeTab(int tabNumber) {
		if(tabNumber == VALIDIC_TAB_1){
		//	logger.info("Loading "+selectedTab.getTabLabel()+" data");
			validicUsers =getvFitnessUsers();
			
		}else if(tabNumber == VALIDIC_TAB_2){
		//	logger.info("Loading "+selectedTab.getTabLabel()+" data");
			validicUsers = getvNutritionUsers();
			
		}else if(tabNumber == VALIDIC_TAB_3){
		//	logger.info("Loading "+selectedTab.getTabLabel()+" data");
			validicUsers = getvWeightUsers();
			
		} else if(tabNumber == VALIDIC_TAB_4){
		//	logger.info("Loading "+selectedTab.getTabLabel()+" data");
			validicUsers = getvSleepUsers();
			
		}else if(tabNumber == VALIDIC_TAB_5){
		//	logger.info("Loading "+selectedTab.getTabLabel()+" data");
			validicUsers =getvBiometricUsers();
			
		}else if(tabNumber == VALIDIC_TAB_6){
		//	logger.info("Loading "+selectedTab.getTabLabel()+" data");
			validicUsers = getvDiabetesUsers();
			
		}else if(tabNumber == VALIDIC_TAB_7){
		//	logger.info("Loading "+selectedTab.getTabLabel()+" data");
			
		}
		
	}


	public void setInitTab(boolean initTab) {
		this.initTab = initTab;
	}


	
	
	
}
