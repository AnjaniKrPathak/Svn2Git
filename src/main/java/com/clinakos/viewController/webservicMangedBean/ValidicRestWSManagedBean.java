package com.clinakos.viewController.webservicMangedBean;


import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
































import javax.faces.application.FacesMessage;

import org.apache.commons.lang.StringUtils;




import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.primefaces.context.RequestContext;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.model.patient.ClinakosValidicDevices;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.service.IUserService;
import com.clinakos.service.IValidicRestService;
import com.validic.App;
import com.validic.UserOrganation;
import com.validic.UserResponse;
import com.validic.UserRestResponse;
import com.validic.ValidicBiometricmeasurement;
import com.validic.ValidicBiometricsMeasurementResponse;
import com.validic.ValidicDiabetesMeasurements;
import com.validic.ValidicDiabitesResponse;
import com.validic.ValidicFitnessActivity;
import com.validic.ValidicFitnessActivityResponse;
import com.validic.ValidicIntegratedAppResponse;
import com.validic.ValidicNutrition;
import com.validic.ValidicSleep;
import com.validic.ValidicTobaccoCessation;
import com.validic.ValidicTobaccoCessationResponse;
import com.validic.ValidicNutritionResponse;
import com.validic.ValidicSleepResponse;
import com.validic.ValidicRoutineActivity;
import com.validic.ValidicRoutineRespose;
import com.validic.ValidicUserCredential;
import com.validic.ValidicUserProfile;
import com.validic.ValidicUserRestRequest;
import com.validic.ValidicWeight;
import com.validic.ValidicWeightResponse;

public class ValidicRestWSManagedBean {

	
	private IValidicRestService validicRestService;
	Properties properties = null;
	ValidicUserCredential validicUserCredential = null;
	private IUserService userService;
	private List<ValidicWeight> validicPatientWeightList;
	private ValidicWeight validicWeight;
	private Date startDate=null;;
	private Date endDate=null;
	private List<String> validicWeightSourceNameList=null;
	private String sourceName;
	private List<ValidicDiabetesMeasurements> validicPatientDiabetesMeasurementList;
	private List<String> validicDiabitesMeasurementSourceNameList;
	private List<ValidicBiometricmeasurement> validicBiometricMeasurementDataList;
	private ValidicBiometricmeasurement validicBiometricmeasurement;
	private List<String> validicBiometricMeasurementSourceNameList;
	private List<ValidicNutrition> validicPatientNutritionsDataList;
	private ValidicNutrition validicNutrition;
	private List<String> validicNutriotionSourceNameList;
	private List<ValidicFitnessActivity> validicPatientFitnessActivityDataList;
	private ValidicFitnessActivity fitnessActivity;
	private List<String> validicFitnessActivitySourceNameList;
	private List<ValidicRoutineActivity> validicPatientRoutineActivityDataList;
	private List<ValidicSleep> validicPatientSleepDataList;
	private ValidicSleep validicSleep;
	private List<String> validicSleepSourceNameList;
	private List<ValidicTobaccoCessation> validicPatientTobaccoDataList;
	private ValidicTobaccoCessation tobaccoCessation;
	private List<String> tobaccoCessationSourceNameList;
	private ValidicRoutineActivity validicRoutineActivity;
	private List<String> validicRoutineActivitySourceNameList;
 	
	
	
	private final String DEVICE_PAGE="go_to_device_details";
	private final String DEVICE_DETAILS_API="device_details_url";
	private final String USER_FITNESS_API="user_fitness_data_url";
	private final String USER_DIABETES_API="user_diabetes_measurement_url";
	private final String USER_BIOMETRICS_API="user_biometrics_measurement_url";
	private final String USER_ROUTINE_API="user_routine_url";
	private final String USER_NUTRITION_API="user_nutrition_url";
	private final String USER_SLEEP_API="user_sleep_url";
	private final String USER_WEIGHT_API="user_weight_url";
	private final String USER_TOBACCO_CESSATION_URL="user_tobacco_cessation_url";
	
	/**
	 * @return the validicRestService
	 */
	public IValidicRestService getValidicRestService() {
		return validicRestService;
	}

	/**
	 * @param validicRestService
	 *            the validicRestService to set
	 */
	public void setValidicRestService(IValidicRestService validicRestService) {
		this.validicRestService = validicRestService;
	}

	/**
	 * Test Get Rest API method
	 * 
	 * @throws MalformedURLException
	 */
	public void testRestAPIUrl() throws MalformedURLException {

		/*
		 * URL url=new URL(
		 * "https://api.validic.com/v1/organizations/5453935ee815b4d6ed0000a4.json?access_token=fc7bb80b8ec7ec96a9067975552e543555467163a99f2797f3ccadcf95b1336f"
		 * );
		 */
		try {
			System.out.println("Url Method started now :::::;");
			String url = getProperties().getProperty("Organization_Overview");
			url = url.replace("{ORGANIZATION_ID}",
					getProperties().getProperty("Organization_Id"));
			url = url.replace("{ORGANIZATION_ACCESS_TOKEN}", getProperties()
					.getProperty("Organization_Access_Token"));
			System.out.println("url:::::::::::::" + url);
			RestTemplate restTemplate = new RestTemplate();
			UserOrganation userOrganation = restTemplate.getForObject(url,
					UserOrganation.class);
			System.out
					.println("userOrganation.getOrganization().getName()::::::::: "
							+ userOrganation.getOrganization().getName()
							+ "   Message :::::  "
							+ userOrganation.getSummary().getStatus());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * Test Post Rest API Request methods
	 * 
	 * @throws IOException
	 * 
	 */
	public void provisioningUsersInValidic() throws IOException, NullPointerException {
		int providerId=new ContextUtil().getProviderId();
		boolean isConnectedDevices = true;
		List<UserLoginDetail> userLoginDetailList = userService.getPatientsHavingDevices(providerId,isConnectedDevices);
	//	List<UserLoginDetail> userLoginDetailList=userService.getAllPatientDetailList();
		List<UserResponse>validicPatients = new ArrayList<UserResponse>();
		for(UserLoginDetail loginDetail:userLoginDetailList){
			System.out.println("User Id "+loginDetail.getUserId()+"User name "+loginDetail.getGender()+"User Birth year"+loginDetail.getDateOfBirth());
		
		
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(
				new MappingJacksonHttpMessageConverter());
		restTemplate.getMessageConverters().add(
				new StringHttpMessageConverter());
		String url = getProperties().getProperty("Provisioning_Users_Url");
		url = url.replace("{ORGANIZATION_ID}",
				getProperties().getProperty("Organization_Id"));
		

		ValidicUserRestRequest userRestRequest = new ValidicUserRestRequest();
		com.validic.User user = new com.validic.User();
		user.setUid("Clinakos"+loginDetail.getFirstName()+loginDetail.getUserId());
		/*user.setUid(loginDetail.getId().toString());*/
		
		/*ValidicUserProfile userProfile = new ValidicUserProfile();
		//userProfile.setBirth_year(loginDetail.getDateOfBirth().toString());
		userProfile.setBirth_year("1988");
		userProfile.setCountry(loginDetail.getCountry());
		userProfile.setGender(loginDetail.getGender());
		userProfile.setHeight(Double.valueOf(loginDetail.getHeight()));
		userProfile.setLocation(loginDetail.getCity());
		userProfile.setWeight(Double.valueOf(loginDetail.getWeight()));
		user.setProfile(userProfile);
		*/
		userRestRequest.setUser(user);
		userRestRequest.setAccess_token(getProperties().getProperty(
				"Organization_Access_Token"));
		UserRestResponse userRestResponse=null;
		try{
		try{
		 userRestResponse = restTemplate.postForObject(url,
				userRestRequest, UserRestResponse.class);
		
		}catch(HttpClientErrorException e){
			e.printStackTrace();
		}
		
	/*	System.out.println("userRestResponse.getMessage()   "
				+ userRestResponse.getMessage() + "User uid ::::::::::"
				+ userRestResponse.getUser().getProfile().getBirth_year()
				+ "Application ::::");*/
		//if(userRestResponse.getCode()=="200"){
		if(userRestResponse!=null){
		UserResponse validicUserCredential = userRestResponse.getUser();
		validicUserCredential.setOrganizationId(getProperties().getProperty(
				"Organization_Id"));
		validicUserCredential.setOrganizationAccessToken(getProperties()
				.getProperty("Organization_Access_Token"));
		validicUserCredential.setPatientId(loginDetail.getUserId());
		validicUserCredential.setProviderId(providerId);
		validicRestService.saveValidicUserCredential(validicUserCredential);
		validicPatients.add(validicUserCredential);
		}
		//}
		}catch(Exception e){
		/*	System.out.println("Response Code "+userRestResponse.getCode()+"\n message "+userRestResponse.getMessage());
			if(userRestResponse.getCode() == "409"){
				
			}*/
			e.printStackTrace();
		}
		}
		int patientaddedToValidic=0;
		if(userLoginDetailList!=null){
			patientaddedToValidic = validicPatients.size();
		}
		//FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, " ", "Number of patients wants to connect to devices "+patientWantsDevicesToConnect);
		
		FacesMessage message2 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Validic API Results ", "Number of patients added to validic : "+patientaddedToValidic);
		//FacesMessage message3= new FacesMessage(FacesMessage.SEVERITY_INFO, " ", "Number of patients already added to validic "+patientWantsDevicesToConnect);
		
		RequestContext.getCurrentInstance().showMessageInDialog(message2);
	}

	/**
	 * 
	 * Test Data for validic Device Integration test.
	 * Note:It will be removed after testing
	 * @return
	 */
	private List<UserLoginDetail> getDefaultPatientData() {
		List<UserLoginDetail>userDataObj=new ArrayList<UserLoginDetail>();
		UserLoginDetail userDetail_1=new UserLoginDetail();
		UserLoginDetail userDetail_2=new UserLoginDetail();
		UserLoginDetail userDetail_3=new UserLoginDetail();
		UserLoginDetail userDetail_4=new UserLoginDetail();
		UserLoginDetail userDetail_5=new UserLoginDetail();
		UserLoginDetail userDetail_6=new UserLoginDetail();
		
		userDetail_1.setId(1001);
		//userDetail_1.setDateOfBirth(new Date());
		userDetail_1.setCountry("USA");
		userDetail_1.setGender("M");
		userDetail_1.setHeight("165");
		userDetail_1.setWeight("58");
		userDetail_1.setCity("NC");
		
		userDetail_2.setId(1002);
		userDetail_2.setCountry("USA");
		userDetail_2.setGender("M");
		userDetail_2.setHeight("175");
		userDetail_2.setWeight("68");
		userDetail_2.setCity("CA");
		
		userDetail_3.setId(1003);
		userDetail_3.setCountry("USA");
		userDetail_3.setGender("F");
		userDetail_3.setHeight("145");
		userDetail_3.setWeight("53");
		userDetail_3.setCity("CA");
		
		userDetail_4.setId(1004);
		userDetail_4.setCountry("USA");
		userDetail_4.setGender("F");
		userDetail_4.setHeight("135");
		userDetail_4.setWeight("53");
		userDetail_4.setCity("CA");
		
		userDetail_5.setId(1005);
		userDetail_5.setCountry("USA");
		userDetail_5.setGender("M");
		userDetail_5.setHeight("185");
		userDetail_5.setWeight("95");
		userDetail_5.setCity("CA");
		
		//userDetail_6.setId(1006);
		
		userDataObj.add(userDetail_1);
		userDataObj.add(userDetail_2);
		userDataObj.add(userDetail_3);
		userDataObj.add(userDetail_4);
		userDataObj.add(userDetail_5);
	//	userDataObj.add(userDetail_6);
		return userDataObj;
		
	}

	public void appApiTest() throws IOException {
		String url = getProperties().getProperty("APP_Url");
		url = url.replace("{ORGANIZATION_ID}",
				getProperties().getProperty("Organization_Id"));
		url = url.replace("{ORGANIZATION_ACCESS_TOKEN}", getProperties()
				.getProperty("Organization_Access_Token"));

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(
				new MappingJacksonHttpMessageConverter());
		restTemplate.getMessageConverters().add(
				new StringHttpMessageConverter());
		ValidicIntegratedAppResponse integratedAppResponse = restTemplate
				.getForObject(
						"https://api.validic.com/v1/organizations/5453935ee815b4d6ed0000a4/apps.json?authentication_token=gBQXmpKq3PxuyXN9ibso&access_token=fc7bb80b8ec7ec96a9067975552e543555467163a99f2797f3ccadcf95b1336f",
						ValidicIntegratedAppResponse.class);
		System.out.println("Integrated APP::::::::"
				+ integratedAppResponse.getApps().size());
		for (App app : integratedAppResponse.getApps()) {
			System.out.println(app.getName() + " app syn url "
					+ app.getSynced() + " un syn url " + app.getUnsync_url()
					+ "logo url " + app.getLogo_url());
		}

	}

	/**
	 * @return the properties
	 * @throws IOException
	 */
	public Properties getProperties() throws IOException {
		if (properties == null) {
			properties = new Properties();
		}
		properties.load(ValidicRestWSManagedBean.class.getClassLoader()
				.getResourceAsStream(
						"/com/clinakos/properties/validicRestUrl.properties"));
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * @return the validicUserCredential
	 */
	public ValidicUserCredential getValidicUserCredential() {
		if (validicUserCredential == null) {
			validicUserCredential = new ValidicUserCredential();
		}
		return validicUserCredential;
	}

	/**
	 * @param validicUserCredential
	 *            the validicUserCredential to set
	 */
	public void setValidicUserCredential(
			ValidicUserCredential validicUserCredential) {
		this.validicUserCredential = validicUserCredential;
	}

	/**
	 * @throws IOException
	 *             Get Validic Diabites Data and Save in Clinakos Database
	 * 
	 */
	public void getValidicDiabitesMeasurementData() throws IOException {
		String url = getProperties().getProperty(
				"Organization_Diabites_Data_Url");
		url = url.replace("{ORGANIZATION_ID}",
				getProperties().getProperty("Organization_Id"));
		url = url.replace("{ORGANIZATION_ACCESS_TOKEN}", getProperties()
				.getProperty("Organization_Access_Token"));

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(
				new MappingJacksonHttpMessageConverter());
		restTemplate.getMessageConverters().add(
				new StringHttpMessageConverter());
		ValidicDiabitesResponse diabitesResponse = restTemplate.getForObject(
				url, ValidicDiabitesResponse.class);

		for (ValidicDiabetesMeasurements diabetesMeasurements : diabitesResponse
				.getDiabetes()) {
			System.out.println("Blood Glucose "
					+ diabetesMeasurements.getBlood_glucose() + "Source ANme "
					+ diabetesMeasurements.getSource_name());
		}

		validicRestService.saveValidicDibitesMeasurementData(diabitesResponse
				.getDiabetes());
	}

	/**
	 * Get Validic Fitness Activity Data Save Fitness Data in Clinakos Database
	 * 
	 * @throws IOException
	 */
	public void getValidicFitnessActivityData() throws IOException {
		String url = getProperties().getProperty(
				"Organization_Fitness_Data_Url");
		url = url.replace("{ORGANIZATION_ID}",
				getProperties().getProperty("Organization_Id"));
		url = url.replace("{ORGANIZATION_ACCESS_TOKEN}", getProperties()
				.getProperty("Organization_Access_Token"));
		RestTemplate restTemplate = new RestTemplate();
		ValidicFitnessActivityResponse fitnessActivityResponse = restTemplate
				.getForObject(url, ValidicFitnessActivityResponse.class);
		for (ValidicFitnessActivity activity : fitnessActivityResponse
				.getFitness()) {
			System.out.println("Source Name " + activity.getSource_name()
					+ "Duration :::" + activity.getDuration());
		}

		validicRestService.saveValidicFitnessActivity(fitnessActivityResponse
				.getFitness());
	}

	/**
	 * Get Validic Biometrics Data
	 * 
	 * @throws IOException
	 */
	public void getValidicBiometricsMeasurementData() throws IOException {
		String url = getProperties().getProperty(
				"Organization_Biometrics_Data_Url");
		url = url.replace("{ORGANIZATION_ID}",
				getProperties().getProperty("Organization_Id"));
		url = url.replace("{ORGANIZATION_ACCESS_TOKEN}", getProperties()
				.getProperty("Organization_Access_Token"));
		RestTemplate restTemplate = new RestTemplate();
		ValidicBiometricsMeasurementResponse biometricsMeasurementResponse = restTemplate
				.getForObject(url, ValidicBiometricsMeasurementResponse.class);
		System.out.println();
		for (ValidicBiometricmeasurement biometricmeasurement : biometricsMeasurementResponse
				.getBiometrics()) {
			System.out.println("Diastolic :::::::::"
					+ biometricmeasurement.getDiastolic());
		}

		validicRestService
				.saveValidicBiometricsMeasurementData(biometricsMeasurementResponse
						.getBiometrics());
	}

	/**
	 * Get Validic Nutrition Data
	 * 
	 * @throws IOException
	 * 
	 */
	public void getValidicNutritionData() throws IOException {

		String url = getProperties().getProperty(
				"Organization_Nutrition_Data_Url");
		url = url.replace("{ORGANIZATION_ID}",
				getProperties().getProperty("Organization_Id"));
		url = url.replace("{ORGANIZATION_ACCESS_TOKEN}", getProperties()
				.getProperty("Organization_Access_Token"));
		RestTemplate restTemplate = new RestTemplate();
		ValidicNutritionResponse nutritionResponse = restTemplate.getForObject(
				url, ValidicNutritionResponse.class);
		System.out.println(nutritionResponse.getSummary().getStatus());
		validicRestService.saveValidicNutritionData(nutritionResponse.getNutrition());

	}

	/**
	 * Get Validic Sleep Data
	 * 
	 * @throws IOException
	 * 
	 */
	public void getValidicSleepData() throws IOException {
		String url = getProperties().getProperty("Organization_Sleep_Data_Url");
		url = url.replace("{ORGANIZATION_ID}",
				getProperties().getProperty("Organization_Id"));
		url = url.replace("{ORGANIZATION_ACCESS_TOKEN}", getProperties()
				.getProperty("Organization_Access_Token"));
		RestTemplate restTemplate = new RestTemplate();
		ValidicSleepResponse sleepResponse = restTemplate.getForObject(url,
				ValidicSleepResponse.class);
		System.out.println("Status :::::::::;"
				+ sleepResponse.getSummary().getStatus());
		

		validicRestService.saveValidicSleepData(sleepResponse.getSleep());

	}
	

	//@author:vinod
		public void getValidicWeightData(){
			try {
				String url= getProperties().getProperty("Weight_Bm_Url");
				url= url.replace("{ORGANIZATION_ID}", getProperties().getProperty("Organization_Id"));
				url= url.replace("{ORGANIZATION_ACCESS_TOKEN}", getProperties().getProperty("Organization_Access_Token"));
				
				RestTemplate restTemplate = new RestTemplate();
				restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
				restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
				ValidicWeightResponse weightResponse =restTemplate.getForObject(url, ValidicWeightResponse.class);
				System.out.println("Status::::::::::::"+weightResponse.getSummary().getStatus());
				//saving validicData into DB
				   validicRestService.saveValidicWeightDetails(weightResponse.getWeight());
				for(ValidicWeight weight:weightResponse.getWeight()){
				System.out.println("weight ::::"+weight.getWeight()+" dataId::::::::"+weight.getDataId());
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		//@author:vinod
	 public void getValidicRoutineApiData(){
		 try {
			 System.out.println("routine api starting:::::::::::");
			 String url = getProperties().getProperty("Org_Routine_Api");
			 url=url.replace("{ORGANIZATION_ID}", getProperties().getProperty("Organization_Id"));
			 url=url.replace("{ORGANIZATION_ACCESS_TOKEN}", getProperties().getProperty("Organization_Access_Token"));
			// "https://api.validic.com/v1/organizations/5453935ee815b4d6ed0000a4/routine.json?access_token=fc7bb80b8ec7ec96a9067975552e543555467163a99f2797f3ccadcf95b1336f"
			 RestTemplate restTemplate = new RestTemplate();
			 restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
			 restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
			 //String sort = "{\"price\":\"desc\"}";
			 ValidicRoutineRespose routineResponse = restTemplate.getForObject(url, ValidicRoutineRespose.class);
			 validicRestService.saveValidicRoutineActivity(routineResponse.getRoutine());
			 for(ValidicRoutineActivity routine:routineResponse.getRoutine()){
				 System.out.println("col::::::::burned:::::::::"+routine.getCalories_burned());
			 }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	
	 /**
	  * Get Validic Tobacco Cessation Data 
	 * @throws IOException 
	  * 
	  */
	 public void getValidicTobaccoCessationData() throws IOException{
		String url=getProperties().getProperty("Organization_Tobacco_Cessation_Data_Url"); 
		url=url.replace("{ORGANIZATION_ID}", getProperties().getProperty("Organization_Id"));
		url=url.replace("{ORGANIZATION_ACCESS_TOKEN}", getProperties().getProperty("Organization_Access_Token"));
		RestTemplate restTemplate=new RestTemplate();
		ValidicTobaccoCessationResponse tobaccoCessationResponse=restTemplate.getForObject(url, ValidicTobaccoCessationResponse.class);
		System.out.println("Status ::::::"+tobaccoCessationResponse.getSummary().getStatus());
		/*List<ValidicTobaccoCessation> tobaccoCessation=new ArrayList<ValidicTobaccoCessation>();
		ValidicTobaccoCessation cessation=new ValidicTobaccoCessation();
		cessation.set_id("51552cd8fded0807c4000031");
		cessation.setTimestamp("2013-03-10T07:12:16+00:00");
		cessation.setCigarettes_allowed(2);
		cessation.setCravings(12);
		cessation.setLast_smoked("2013-03-10T05:55:36+00:00");
		cessation.setUser_id("546b375684626b2532000003");
		tobaccoCessation.add(cessation);*/
		
		validicRestService.saveTobaccoCessationData(tobaccoCessationResponse.getTobacco_cessation());
		
	 }
	 
	 /**
	  * Call All the validic api and fetch all data 
	  * 
	  */
	 public void getAllValidicApiData(){
		 try {
			 System.out.println("Call All data pulling method ......");
			getValidicDiabitesMeasurementData();
			getValidicFitnessActivityData();
			getValidicBiometricsMeasurementData();
			getValidicNutritionData();
			getValidicWeightData();
			getValidicRoutineApiData();
			getValidicTobaccoCessationData();
			getValidicSleepData();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the validicPatientWeightList
	 */
	public List<ValidicWeight> getValidicPatientWeightList() {
		if(validicPatientWeightList==null){
			
			int patientId=new ContextUtil().getPatientId();
			if(new ContextUtil().getPatientId()!=0){
				
				validicPatientWeightList=validicRestService.getValidicPatientWeightData(patientId);
			}
			
				if(validicWeight.getStartDate() !=null && validicWeight.getEndDate()!=null  && validicWeight.getSource_name()!=null){
					validicPatientWeightList=validicRestService.getValidicPatientWeightData(patientId,validicWeight.getStartDate(),validicWeight.getEndDate(),validicWeight.getSource_name());
				}
				
			
			
		}
		return validicPatientWeightList;
	}

	/**
	 * @param validicPatientWeightList the validicPatientWeightList to set
	 */
	public void setValidicPatientWeightList(
			List<ValidicWeight> validicPatientWeightList) {
		this.validicPatientWeightList = validicPatientWeightList;
	}

	/**
	 * @return the validicWeight
	 */
	public ValidicWeight getValidicWeight() {
		if(validicWeight==null){
			validicWeight=new ValidicWeight();
		}
		return validicWeight;
	}

	/**
	 * @param validicWeight the validicWeight to set
	 */
	public void setValidicWeight(ValidicWeight validicWeight) {
		this.validicWeight = validicWeight;
	}
	
	/**
	 * Get ValidiC Weight Data With Filter 
	 * 
	 */
	public List<ValidicWeight> validicWeightDataWithFilter(){
		
		validicPatientWeightList=validicRestService.getValidicPatientWeightData(new ContextUtil().getPatientId(),validicWeight.getStartDate(),validicWeight.getEndDate(),validicWeight.getSource_name());
		return validicPatientWeightList;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

	/**
	 * @return the validicWeightSourceNameList
	 */
	public List<String> getValidicWeightSourceNameList() {
		if(validicWeightSourceNameList==null){
			validicWeightSourceNameList=new ArrayList<String>();
		}
		validicWeightSourceNameList=validicRestService.getValidicWeightSourceNameData(new ContextUtil().getPatientId());
		return validicWeightSourceNameList;
	}

	/**
	 * @param validicWeightSourceNameList the validicWeightSourceNameList to set
	 */
	public void setValidicWeightSourceNameList(
			List<String> validicWeightSourceNameList) {
		this.validicWeightSourceNameList = validicWeightSourceNameList;
	}

	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		if(sourceName==null){
			sourceName=new String();
		}
		return sourceName;
	}

	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	 * @return the validicPatientDiabetesMeasurementList
	 */
	public List<ValidicDiabetesMeasurements> getValidicPatientDiabetesMeasurementList() {
		if(validicPatientDiabetesMeasurementList==null){
			validicPatientDiabetesMeasurementList=new ArrayList<ValidicDiabetesMeasurements>();
		}
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		if(new ContextUtil().getPatientId()!=0){
			validicPatientDiabetesMeasurementList=validicRestService.getValidicPatientDiabetesMeasurementData(patientId);
		}
			
		
		if(getStartDate()!=null && getEndDate()!=null && getSourceName()!=null){
			validicPatientDiabetesMeasurementList=validicRestService.getValidicPatientDiabetesMeasurementData(patientId,getStartDate(),getEndDate(),getSourceName());
		}
		
		
		return validicPatientDiabetesMeasurementList;
	}

	/**
	 * @param validicPatientDiabetesMeasurementList the validicPatientDiabetesMeasurementList to set
	 */
	public void setValidicPatientDiabetesMeasurementList(
			List<ValidicDiabetesMeasurements> validicPatientDiabetesMeasurementList) {
		this.validicPatientDiabetesMeasurementList = validicPatientDiabetesMeasurementList;
	}

	/**
	 * @return the validicDiabitesMeasurementSourceNameList
	 */
	public List<String> getValidicDiabitesMeasurementSourceNameList() {
		if(validicDiabitesMeasurementSourceNameList==null){
			validicDiabitesMeasurementSourceNameList=new ArrayList<String>();
		}
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		
			validicDiabitesMeasurementSourceNameList=validicRestService.validicDiabitesMeasurementSourceNameData(patientId);
		
		
		return validicDiabitesMeasurementSourceNameList;
	}

	/**
	 * @param validicDiabitesMeasurementSourceNameList the validicDiabitesMeasurementSourceNameList to set
	 */
	public void setValidicDiabitesMeasurementSourceNameList(
			List<String> validicDiabitesMeasurementSourceNameList) {
		this.validicDiabitesMeasurementSourceNameList = validicDiabitesMeasurementSourceNameList;
	}
	
	/**
	 * Get Validic Diabetes Measurement Data With Filter 
	 */
	public void getValidicDiabetesMeasurentDataWithFilter(){
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		validicPatientDiabetesMeasurementList=validicRestService.getValidicPatientDiabetesMeasurementData(patientId,getStartDate(),getEndDate(),getSourceName());
		System.out.println("In bean Size of measurement List"+validicDiabitesMeasurementSourceNameList.size());
		
	}

	/**
	 * @return the validicBiometricMeasurementDataList
	 */
	public List<ValidicBiometricmeasurement> getValidicBiometricMeasurementDataList() {
		if(validicBiometricMeasurementDataList==null){
			validicBiometricMeasurementDataList=new ArrayList<ValidicBiometricmeasurement>();
		}
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		validicBiometricMeasurementDataList=validicRestService.getValidicBiometricMeasurementData(patientId);
		if(validicBiometricmeasurement.getStartDate()!=null && validicBiometricmeasurement.getEndDate()!=null && validicBiometricmeasurement.getSource_name()!=null){
			validicBiometricMeasurementDataList=validicRestService.getValidicBiometricMeasurementData(patientId,validicBiometricmeasurement.getStartDate(),validicBiometricmeasurement.getEndDate(),validicBiometricmeasurement.getSource_name());
		}
		return validicBiometricMeasurementDataList;
	}

	/**
	 * @param validicBiometricMeasurementDataList the validicBiometricMeasurementDataList to set
	 */
	public void setValidicBiometricMeasurementDataList(
			List<ValidicBiometricmeasurement> validicBiometricMeasurementDataList) {
		this.validicBiometricMeasurementDataList = validicBiometricMeasurementDataList;
	}

	/**
	 * @return the validicBiometricmeasurement
	 */
	public ValidicBiometricmeasurement getValidicBiometricmeasurement() {
		if(validicBiometricmeasurement==null){
			validicBiometricmeasurement=new ValidicBiometricmeasurement();
		}
		return validicBiometricmeasurement;
	}

	/**
	 * @param validicBiometricmeasurement the validicBiometricmeasurement to set
	 */
	public void setValidicBiometricmeasurement(
			ValidicBiometricmeasurement validicBiometricmeasurement) {
		this.validicBiometricmeasurement = validicBiometricmeasurement;
	}
	/**
	 * Validic Biometric Measurement Data 
	 * @return
	 */
	public List<ValidicBiometricmeasurement> getFilterValidicBiometricMeasurementData(){
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		validicBiometricMeasurementDataList=validicRestService.getValidicBiometricMeasurementData(patientId,validicBiometricmeasurement.getStartDate(),validicBiometricmeasurement.getEndDate(),validicBiometricmeasurement.getSource_name());
		return validicBiometricMeasurementDataList;
		
	}

	/**
	 * @return the validicBiometricMeasurementSourceNameList
	 */
	public List<String> getValidicBiometricMeasurementSourceNameList() {
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		validicBiometricMeasurementSourceNameList=validicRestService.getValidicBiometricMeasurementSourceNameData(patientId);
		return validicBiometricMeasurementSourceNameList;
	}

	/**
	 * @param validicBiometricMeasurementSourceNameList the validicBiometricMeasurementSourceNameList to set
	 */
	public void setValidicBiometricMeasurementSourceNameList(
			List<String> validicBiometricMeasurementSourceNameList) {
		this.validicBiometricMeasurementSourceNameList = validicBiometricMeasurementSourceNameList;
	}

	/**
	 * @return the validicPatientNutritionsDataList
	 */
	public List<ValidicNutrition> getValidicPatientNutritionsDataList() {
		if(validicPatientNutritionsDataList==null){
			validicPatientNutritionsDataList=new ArrayList<ValidicNutrition>();
		}
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		validicPatientNutritionsDataList=validicRestService.getValidicPatientNutritionsData(patientId);
       if(validicNutrition.getStartDate()!=null && validicNutrition.getEndDate() !=null && validicNutrition.getSource_name()!=null){
    	   validicPatientNutritionsDataList=validicRestService.getValidicPatientNutritionsData(patientId,validicNutrition.getStartDate(),validicNutrition.getEndDate(),validicNutrition.getSource_name());
		}
		return validicPatientNutritionsDataList;
	}

	/**
	 * @param validicPatientNutritionsDataList the validicPatientNutritionsDataList to set
	 */
	public void setValidicPatientNutritionsDataList(
			List<ValidicNutrition> validicPatientNutritionsDataList) {
		this.validicPatientNutritionsDataList = validicPatientNutritionsDataList;
	}
	
	/**
	 * Patient Nutrition Data After Filteration 
	 * @return
	 */
	public List<ValidicNutrition> getValidicPatientNutrionDataWithFilter(){
		
		
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		validicPatientNutritionsDataList=validicRestService.getValidicPatientNutritionsData(patientId,validicNutrition.getStartDate(),validicNutrition.getEndDate(),validicNutrition.getSource_name());
		
		return validicPatientNutritionsDataList;
		
	}

	/**
	 * @return the validicNutrition
	 */
	public ValidicNutrition getValidicNutrition() {
		if(validicNutrition==null){
			validicNutrition=new ValidicNutrition();
		}
		return validicNutrition;
	}

	/**
	 * @param validicNutrition the validicNutrition to set
	 */
	public void setValidicNutrition(ValidicNutrition validicNutrition) {
		this.validicNutrition = validicNutrition;
	}

	/**
	 * @return the validicNutriotionSourceNameList
	 */
	public List<String> getValidicNutriotionSourceNameList() {
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		validicNutriotionSourceNameList=validicRestService.getValidicNutritionSourceNameData(patientId);
		return validicNutriotionSourceNameList;
	}

	/**
	 * @param validicNutriotionSourceNameList the validicNutriotionSourceNameList to set
	 */
	public void setValidicNutriotionSourceNameList(
			List<String> validicNutriotionSourceNameList) {
		this.validicNutriotionSourceNameList = validicNutriotionSourceNameList;
	}

	/**
	 * @return the validicPatientFitnessActivityDataList
	 */
	public List<ValidicFitnessActivity> getValidicPatientFitnessActivityDataList() {
		if(validicPatientFitnessActivityDataList==null){
			validicPatientFitnessActivityDataList=new ArrayList<ValidicFitnessActivity>();
		}
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		validicPatientFitnessActivityDataList=validicRestService.getValidicPatientFitnessActivityData(patientId);
		if(fitnessActivity.getStartDate()!=null && fitnessActivity.getEndDate()!=null && fitnessActivity.getSource_name()!=null){
			validicPatientFitnessActivityDataList=validicRestService.getValidicPatientFitnessActivityData(patientId,fitnessActivity.getStartDate(),fitnessActivity.getEndDate(),fitnessActivity.getSource_name());
		}
		return validicPatientFitnessActivityDataList;
	}

	/**
	 * @param validicPatientFitnessActivityDataList the validicPatientFitnessActivityDataList to set
	 */
	public void setValidicPatientFitnessActivityDataList(
			List<ValidicFitnessActivity> validicPatientFitnessActivityDataList) {
		this.validicPatientFitnessActivityDataList = validicPatientFitnessActivityDataList;
	}

	/**
	 * @return the fitnessActivity
	 */
	public ValidicFitnessActivity getFitnessActivity() {
		if(fitnessActivity==null){
			fitnessActivity=new ValidicFitnessActivity();
		}
		return fitnessActivity;
	}

	/**
	 * @param fitnessActivity the fitnessActivity to set
	 */
	public void setFitnessActivity(ValidicFitnessActivity fitnessActivity) {
		this.fitnessActivity = fitnessActivity;
	}
	
	/**
	 * 
	 * 
	 */
	public List<ValidicFitnessActivity> getValidicPatientFitnessActivityDataWithFilter(){
		
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		validicPatientFitnessActivityDataList=validicRestService.getValidicPatientFitnessActivityData(patientId,fitnessActivity.getStartDate(),fitnessActivity.getEndDate(),fitnessActivity.getSource_name());
		
		return validicPatientFitnessActivityDataList;
		
	}

	/**
	 * @return the validicFitnessActivitySourceNameList
	 */
	public List<String> getValidicFitnessActivitySourceNameList() {
		if(validicFitnessActivitySourceNameList==null){
			validicFitnessActivitySourceNameList=new ArrayList<String>();
		}
		validicFitnessActivitySourceNameList=validicRestService.getValidicFitnessActivitySourceName(Integer.toString(new ContextUtil().getPatientId()));
		return validicFitnessActivitySourceNameList;
	}

	/**
	 * @param validicFitnessActivitySourceNameList the validicFitnessActivitySourceNameList to set
	 */
	public void setValidicFitnessActivitySourceNameList(
			List<String> validicFitnessActivitySourceNameList) {
		this.validicFitnessActivitySourceNameList = validicFitnessActivitySourceNameList;
	}

	/**
	 * @return the validicPatientRoutineActivityDataList
	 */
	public List<ValidicRoutineActivity> getValidicPatientRoutineActivityDataList() {
		if(validicPatientRoutineActivityDataList==null){
			validicPatientRoutineActivityDataList=new ArrayList<ValidicRoutineActivity>();
		}
		validicPatientRoutineActivityDataList=validicRestService.getValidicPatientRoutineActivityData(Integer.toString(new ContextUtil().getPatientId()));
		if(validicRoutineActivity.getStartDate() !=null && validicRoutineActivity.getEndDate()!=null && validicRoutineActivity.getSource_name()!=null){
			validicPatientRoutineActivityDataList=validicRestService.getValidicPatientRoutineActivityData(Integer.toString(new ContextUtil().getPatientId()),validicRoutineActivity.getStartDate(),validicRoutineActivity.getEndDate(),validicRoutineActivity.getSource_name());
		}
		return validicPatientRoutineActivityDataList;
	}

	/**
	 * @param validicPatientRoutineActivityDataList the validicPatientRoutineActivityDataList to set
	 */
	public void setValidicPatientRoutineActivityDataList(
			List<ValidicRoutineActivity> validicPatientRoutineActivityDataList) {
		this.validicPatientRoutineActivityDataList = validicPatientRoutineActivityDataList;
	}

	/**
	 * @return the validicPatientSleepDataList
	 */
	public List<ValidicSleep> getValidicPatientSleepDataList() {
		if(validicPatientSleepDataList==null){
			validicPatientSleepDataList=new ArrayList<ValidicSleep>();
		}
		validicPatientSleepDataList=validicRestService.validicPatientSleepData(Integer.toString(new ContextUtil().getPatientId()));
		if(validicSleep.getStartDate()!=null && validicSleep.getEndDate()!=null && validicSleep.getSource_name()!=null){
			validicPatientSleepDataList=validicRestService.validicPatientSleepData(Integer.toString(new ContextUtil().getPatientId()),validicSleep.getStartDate(),validicSleep.getEndDate(),validicSleep.getSource_name());
		}
		return validicPatientSleepDataList;
	}

	/**
	 * @param validicPatientSleepDataList the validicPatientSleepDataList to set
	 */
	public void setValidicPatientSleepDataList(
			List<ValidicSleep> validicPatientSleepDataList) {
		this.validicPatientSleepDataList = validicPatientSleepDataList;
	}

	/**
	 * @return the validicSleep
	 */
	public ValidicSleep getValidicSleep() {
		if(validicSleep==null){
			validicSleep=new ValidicSleep();
		}
		return validicSleep;
	}

	/**
	 * @param validicSleep the validicSleep to set
	 */
	public void setValidicSleep(ValidicSleep validicSleep) {
		this.validicSleep = validicSleep;
	}

	/**
	 * @return the validicSleepSourceNameList
	 */
	public List<String> getValidicSleepSourceNameList() {
		if(validicSleepSourceNameList==null){
			validicSleepSourceNameList=new ArrayList<String>();
		}
		validicSleepSourceNameList=validicRestService.getValidicSleepSourceNameData(Integer.toString(new ContextUtil().getPatientId()));
		
		return validicSleepSourceNameList;
	}

	/**
	 * @param validicSleepSourceNameList the validicSleepSourceNameList to set
	 */
	public void setValidicSleepSourceNameList(
			List<String> validicSleepSourceNameList) {
		this.validicSleepSourceNameList = validicSleepSourceNameList;
	}
	
	/**
	 * Get Sleep Data With Filter 
	 * 
	 */
	public List<ValidicSleep> getValidicPatientSleepDataWithFilter(){
		
		validicPatientSleepDataList=validicRestService.validicPatientSleepData(Integer.toString(new ContextUtil().getPatientId()),validicSleep.getStartDate(),validicSleep.getEndDate(),validicSleep.getSource_name());
		
		return validicPatientSleepDataList;
		
	}

	/**
	 * @return the validicPatientTobaccoDataList
	 */
	public List<ValidicTobaccoCessation> getValidicPatientTobaccoDataList() {
		if(validicPatientTobaccoDataList==null){
			validicPatientTobaccoDataList=new ArrayList<ValidicTobaccoCessation>();
		}
		validicPatientTobaccoDataList=validicRestService.getValidicPatientTobaccoData(Integer.toString(new ContextUtil().getPatientId()));
		if(tobaccoCessation.getStartDate()!=null && tobaccoCessation.getEndDate()!=null && tobaccoCessation.getSource_name()!=null){
			validicPatientTobaccoDataList=validicRestService.getValidicPatientTobaccoData(Integer.toString(new ContextUtil().getPatientId()),tobaccoCessation.getStartDate(),tobaccoCessation.getEndDate(),tobaccoCessation.getSource_name());
		}
		return validicPatientTobaccoDataList;
	}

	/**
	 * @param validicPatientTobaccoDataList the validicPatientTobaccoDataList to set
	 */
	public void setValidicPatientTobaccoDataList(
			List<ValidicTobaccoCessation> validicPatientTobaccoDataList) {
		this.validicPatientTobaccoDataList = validicPatientTobaccoDataList;
	}

	/**
	 * @return the tobaccoCessation
	 */
	public ValidicTobaccoCessation getTobaccoCessation() {
		if(tobaccoCessation==null){
			tobaccoCessation=new ValidicTobaccoCessation();
		}
		return tobaccoCessation;
	}

	/**
	 * @param tobaccoCessation the tobaccoCessation to set
	 */
	public void setTobaccoCessation(ValidicTobaccoCessation tobaccoCessation) {
		this.tobaccoCessation = tobaccoCessation;
	}
	/**
	 * 
	 * @return
	 */
	public List<ValidicTobaccoCessation> getValidicPatientTobaccoDataWithFilter(){
		validicPatientTobaccoDataList=validicRestService.getValidicPatientTobaccoData(Integer.toString(new ContextUtil().getPatientId()),tobaccoCessation.getStartDate(),tobaccoCessation.getEndDate(),tobaccoCessation.getSource_name());
		return validicPatientTobaccoDataList;
		
	}

	/**
	 * @return the tobaccoCessationSourceNameList
	 */
	public List<String> getTobaccoCessationSourceNameList() {
		if(tobaccoCessationSourceNameList==null){
			tobaccoCessationSourceNameList=new ArrayList<String>();
		}
		tobaccoCessationSourceNameList=validicRestService.getTobaccoCessationSourceNameData(Integer.toString(new ContextUtil().getPatientId()));
		return tobaccoCessationSourceNameList;
	}

	/**
	 * @param tobaccoCessationSourceNameList the tobaccoCessationSourceNameList to set
	 */
	public void setTobaccoCessationSourceNameList(
			List<String> tobaccoCessationSourceNameList) {
		this.tobaccoCessationSourceNameList = tobaccoCessationSourceNameList;
	}

	/**
	 * @return the validicRoutineActivity
	 */
	public ValidicRoutineActivity getValidicRoutineActivity() {
		if(validicRoutineActivity==null){
			validicRoutineActivity=new ValidicRoutineActivity();
		}
		return validicRoutineActivity;
	}

	/**
	 * @param validicRoutineActivity the validicRoutineActivity to set
	 */
	public void setValidicRoutineActivity(
			ValidicRoutineActivity validicRoutineActivity) {
		this.validicRoutineActivity = validicRoutineActivity;
	}
	
	/**
	 * Get Validic Routine Activity Data with Filter  
	 * @return
	 */
	public List<ValidicRoutineActivity> getvalidicRoutineActivityDataWithFilter(){
		
		validicPatientRoutineActivityDataList=validicRestService.getValidicPatientRoutineActivityData(Integer.toString(new ContextUtil().getPatientId()),validicRoutineActivity.getStartDate(),validicRoutineActivity.getEndDate(),validicRoutineActivity.getSource_name());
		return validicPatientRoutineActivityDataList;
		
	}

	/**
	 * @return the validicRoutineActivitySourceNameList
	 */
	public List<String> getValidicRoutineActivitySourceNameList() {
		if(validicRoutineActivitySourceNameList==null){
			validicRoutineActivitySourceNameList=new ArrayList<String>();
		}
		validicRoutineActivitySourceNameList=validicRestService.getValidicRoutineActivitySourceNameData(Integer.toString(new ContextUtil().getPatientId()));
		return validicRoutineActivitySourceNameList;
	}

	/**
	 * @param validicRoutineActivitySourceNameList the validicRoutineActivitySourceNameList to set
	 */
	public void setValidicRoutineActivitySourceNameList(
			List<String> validicRoutineActivitySourceNameList) {
		this.validicRoutineActivitySourceNameList = validicRoutineActivitySourceNameList;
	}
	
	/**
	 * Velocity test 
	 * @throws Exception 
	 * 
	 */
	public void velocityTest() throws Exception{
		try {
			Velocity.init();
		} catch (Exception e) {
			System.out.println("Problem initializing Velocity : " + e );
			return;
		}
		VelocityContext context=new VelocityContext();
		context.put("name", "Velocity");
		context.put("project", "MTM Report");
	StringWriter w=new StringWriter();
		
		try {
			Velocity.mergeTemplate("src/main/resources/com/clinakos/template/helloWorldVelocity.vm", context, w);
		} catch (Exception e) {
			System.out.println("problem in merging template "+e);
		}
		System.out.println("template "+ w);
	}
	
	
	
	/**
	 * Dyanmic Report Genration 
	 * 
	 */
	public void dyanmciReportGenration(){
		
		
	}
	
	public String deviceResponse=new String();
	/**
	 * Following Method Load devices specific to Patient
	 * 
	 * @return
	 */
	public String loadPatientDevices(){
		//Get the clinakos validic data for user 
		int patientId=new ContextUtil().getPatientId();
		int providerId=new ContextUtil().getProviderId();
		userFitnessActivities=new ArrayList<ValidicFitnessActivity>();
		userFitnessActivities=validicRestService.getClinakosValidicFitnessData(patientId,null);
		
		userDiabetesMeasurements=new ArrayList<ValidicDiabetesMeasurements>();
		userDiabetesMeasurements=validicRestService.getValidicPatientDiabetesMeasurementData(String.valueOf(patientId));
		return DEVICE_PAGE;
	}
	
	
	

	/**
	 * 
	 * To Create validic Device URL for specific user
	 * @return String : Validic Device URL
	 * @throws IOException 
	 */
	public String getDeviceResponse()  {
		int patientId=new ContextUtil().getPatientId();
		int providerId=new ContextUtil().getProviderId();
		UserResponse userObj=validicRestService.getValidicUser(patientId,providerId);
		if(userObj!=null){	
		try {
			deviceResponse=getDeviceAppURL(userObj);
			System.out.println("Device URL "+deviceResponse+" based on USER organization Id "+userObj.getOrganizationId()+" User Access Token "+userObj.getAccess_token());
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
		return deviceResponse;
	}
	
	/**
	 * 
	 * Prepare the Validic Device URL based on User Obj
	 * @param userObj
	 * @return
	 * @throws IOException
	 */
	private String getDeviceAppURL(UserResponse userObj) throws IOException {
		String url=new String();
		try {
			url = getProperties().getProperty(DEVICE_DETAILS_API);
		} catch (IOException e) {
			e.printStackTrace();
		}
		url = url.replace("{ORGANIZATION_ID}",
				userObj.getOrganizationId());
		url=url.replace("{USER_ACCESS_TOKEN}",userObj.getAccess_token());
		return url;
	}

	public ClinakosValidicDevices validicDevices=new ClinakosValidicDevices();
	
	
	
	/**
	 * Trigerred From UI for Getting Latest validic data for particular User
	 * 
	 */
	public void latestValidicData(){
		System.out.println("{--}{--}{--} lOADING LATEST VALIDIC DATA {--}{--}{--}" +validicDevices.toString());
		int patientId=new ContextUtil().getPatientId();
		int providerId=new ContextUtil().getProviderId();
		UserResponse userObj=validicRestService.getValidicUser(patientId,providerId);
		if(userObj!=null){
		if(validicDevices.isFitness()){
		List<ValidicFitnessActivity>validicUserFitnessActivities=getLatestFitnessActivities(userObj);
		List<ValidicFitnessActivity>clinakosValidicFitnessData=new ArrayList<ValidicFitnessActivity>();
		try{
		clinakosValidicFitnessData=validicRestService.getClinakosValidicFitnessData(patientId,userObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(clinakosValidicFitnessData!=null && validicUserFitnessActivities!=null){
		boolean isFitnessSaved=syncToClinakosFitness(clinakosValidicFitnessData,validicUserFitnessActivities,patientId,providerId);
		userFitnessActivities=new ArrayList<ValidicFitnessActivity>();
		if(isFitnessSaved){
			
			userFitnessActivities.addAll(validicUserFitnessActivities);
			userFitnessActivities.addAll(clinakosValidicFitnessData);
		}else{
			userFitnessActivities.addAll(clinakosValidicFitnessData);
		}
		}
		}
		
		else if(validicDevices.isDiabetes()){
		List<ValidicDiabetesMeasurements>validicUserDiabetesMeasurements=getLatestUserDiabetesMeasurements(userObj);
		List<ValidicDiabetesMeasurements>userDiabetesData=new ArrayList<ValidicDiabetesMeasurements>();
		try{
			userDiabetesData=validicRestService.getValidicPatientDiabetesMeasurementData(String.valueOf(patientId));
		}catch(Exception e){
			e.printStackTrace();
		}
		if(userDiabetesData!=null && validicUserDiabetesMeasurements!=null){
			boolean isDiabetesSaved=syncToClinakosDiabetes(userDiabetesData,validicUserDiabetesMeasurements,patientId,providerId);
			userDiabetesMeasurements=new ArrayList<ValidicDiabetesMeasurements>();
			if(isDiabetesSaved){
				userDiabetesMeasurements.addAll(validicUserDiabetesMeasurements);
				userDiabetesMeasurements.addAll(userDiabetesData);
			}else{
				userDiabetesMeasurements.addAll(userDiabetesData);
			}
	
		}
		}
		
		else if(validicDevices.isBiometric()){
		List<ValidicBiometricmeasurement>validicUserBiometricMeasurements=getLatestBiometricMeasurements(userObj);
		List<ValidicBiometricmeasurement>userBiometricData=new ArrayList<ValidicBiometricmeasurement>();
		try{
			userBiometricData=validicRestService.getValidicBiometricMeasurementData(String.valueOf(patientId));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		if(userBiometricData!=null && validicUserBiometricMeasurements!=null){
			boolean isBiometricSaved=syncToClinakosBiometric(userBiometricData,validicUserBiometricMeasurements,patientId,providerId);
			userBiometricMeasurements=new ArrayList<ValidicBiometricmeasurement>();
			if(isBiometricSaved){
				userBiometricMeasurements.addAll(validicUserBiometricMeasurements);
				userBiometricMeasurements.addAll(userBiometricData);
			}else{
				userBiometricMeasurements.addAll(userBiometricData);
			}
		}
		
		}
		else if(validicDevices.isRoutine()){
		List<ValidicRoutineActivity>validicUserRoutineActivities=getLatestRoutineActivities(userObj);
		List<ValidicRoutineActivity>userRoutineActivitiesData=new ArrayList<ValidicRoutineActivity>();
		try{
			userRoutineActivitiesData=validicRestService.getValidicPatientRoutineActivityData(String.valueOf(patientId));
		}catch(Exception e){
			e.printStackTrace();
		}
		if(userRoutineActivitiesData!=null && validicUserRoutineActivities!=null){
			boolean isRoutineActivitySaved=syncToClinakosRoutineActivities(userRoutineActivitiesData,validicUserRoutineActivities,patientId,providerId);
			userRoutineActivities=new ArrayList<ValidicRoutineActivity>();
			if(isRoutineActivitySaved){
				userRoutineActivities.addAll(validicUserRoutineActivities);
				userRoutineActivities.addAll(userRoutineActivitiesData);
			}else{
				userRoutineActivities.addAll(userRoutineActivitiesData);
			}
		}
		}
		else if(validicDevices.isNutrition()){
			List<ValidicNutrition>validicUserNutritions=getLatestNutritions(userObj);
			List<ValidicNutrition>userNutritionData=new ArrayList<ValidicNutrition>();
			try{
				userNutritionData=validicRestService.getValidicPatientNutritionsData(String.valueOf(patientId));
			}catch(Exception e){
				e.printStackTrace();
			}
			if(userNutritionData !=null && validicUserNutritions!=null){
				boolean isNutritionSaved=syncToClinakosNutrition(userNutritionData,validicUserNutritions,patientId,providerId);
				userNutritions=new ArrayList<ValidicNutrition>();
				if(isNutritionSaved){
					userNutritions.addAll(validicUserNutritions);
					userNutritions.addAll(userNutritionData);
				}else{
					userNutritions.addAll(userNutritionData);
				}
			}
			}
			
			else if(validicDevices.isSleep()){
			List<ValidicSleep>validicUserSleepActivities=getLatestSleepActivities(userObj);
			List<ValidicSleep>userSleepData=new ArrayList<ValidicSleep>();
			try{
				userSleepData=validicRestService.validicPatientSleepData(String.valueOf(patientId));
			}catch(Exception e){
				e.printStackTrace();
			}
			if(userSleepData!=null && validicUserSleepActivities!=null){
				boolean isSleepActivitySaved=syncToClinakosSleepActivity(userSleepData,validicUserSleepActivities,patientId,providerId);
				userSleepActivities=new ArrayList<ValidicSleep>();
				if(isSleepActivitySaved){
					userSleepActivities.addAll(validicUserSleepActivities);
					userSleepActivities.addAll(userSleepData);
				}else{
					userSleepActivities.addAll(userSleepData);
				}
			}
			}
			
			else if(validicDevices.isWeight()){
			List<ValidicWeight>validicUserWeightActivities=getLatestWeightActivities(userObj);
			List<ValidicWeight>clinakosUserWeightData=new ArrayList<ValidicWeight>();
			try{
				clinakosUserWeightData=validicRestService.getValidicPatientWeightData(patientId);
			}catch(Exception e){
				e.printStackTrace();
			}
			if(clinakosUserWeightData!=null && validicUserWeightActivities!=null){
				boolean isWeightActivitySaved=syncToClinakosWeightActivity(clinakosUserWeightData,validicUserWeightActivities,patientId,providerId);
				userWeightActivities=new ArrayList<ValidicWeight>();
				if(isWeightActivitySaved){
					userWeightActivities.addAll(validicUserWeightActivities);
					userWeightActivities.addAll(clinakosUserWeightData);
				}else{
					userWeightActivities.addAll(clinakosUserWeightData);
				}
			}
			}
			
			else if(validicDevices.isTobacco()){
			List<ValidicTobaccoCessation>validicUserTobaccoCessationActivities=getLatestTobaccoCessationActivities(userObj);
			List<ValidicTobaccoCessation>clinakosTobaccoCessationData=new ArrayList<ValidicTobaccoCessation>();
			try{
				clinakosTobaccoCessationData=validicRestService.getValidicPatientTobaccoData(String.valueOf(patientId));
			}catch(Exception e){
				e.printStackTrace();
			}
			if(clinakosTobaccoCessationData!=null && validicUserTobaccoCessationActivities!=null){
				boolean isTobaccoCessationDataSaved=syncToClinakosTobaccoCessationActivity(clinakosTobaccoCessationData,validicUserTobaccoCessationActivities,patientId,providerId);
				userTobaccoCessationActivities=new ArrayList<ValidicTobaccoCessation>();
				if(isTobaccoCessationDataSaved){
					userTobaccoCessationActivities.addAll(validicUserTobaccoCessationActivities);
					userTobaccoCessationActivities.addAll(clinakosTobaccoCessationData);
				}else{
					userTobaccoCessationActivities.addAll(clinakosTobaccoCessationData);
				}
			}
			}
		}
		
	
		validicDevices=new ClinakosValidicDevices();
	}
	
	/**
	 * Save the validic Tobacco Cessation activity data to clinakos database
	 * @param clinakosTobaccoCessationData
	 * @param userTobaccoCessationActivities2
	 * @param patientId
	 * @param providerId
	 * @return
	 */
	public boolean syncToClinakosTobaccoCessationActivity(
			List<ValidicTobaccoCessation> clinakosTobaccoCessationData,
			List<ValidicTobaccoCessation> userTobaccoCessationActivities2,
			int patientId, int providerId) {
		boolean isTobaccoCessationDataSaved=false;
		if(clinakosTobaccoCessationData!=null){
			if(clinakosTobaccoCessationData.size()==0){
				try{
				validicRestService.saveToClinakosTobaccoCessationActivity(userTobaccoCessationActivities2,patientId,providerId);
				isTobaccoCessationDataSaved=true;
				}catch(Exception e){
					e.printStackTrace();
					isTobaccoCessationDataSaved=false;
				}
				
			}else{
				List<ValidicTobaccoCessation>latestTobaccoData=new ArrayList<ValidicTobaccoCessation>();
				Map<String,ValidicTobaccoCessation>cTobaccoMap=new HashMap<String, ValidicTobaccoCessation>();
				cTobaccoMap=prepareTobaccoMap(clinakosTobaccoCessationData);
				for(ValidicTobaccoCessation vTobaccoData:userTobaccoCessationActivities2){
					ValidicTobaccoCessation cTobaccoData=cTobaccoMap.get(vTobaccoData.get_id());
					if(cTobaccoData==null){
						latestTobaccoData.add(vTobaccoData);
					}
				}
				try{
					if(latestTobaccoData!=null && latestTobaccoData.size()>0){
					validicRestService.saveToClinakosTobaccoCessationActivity(latestTobaccoData,patientId,providerId);
					isTobaccoCessationDataSaved=true;
					}else{
						isTobaccoCessationDataSaved=false;
					}
					}catch(Exception e){
						e.printStackTrace();
						isTobaccoCessationDataSaved=false;
					}
			}
		}
		return isTobaccoCessationDataSaved;
	}

	private Map<String, ValidicTobaccoCessation> prepareTobaccoMap(
			List<ValidicTobaccoCessation> clinakosTobaccoCessationData) {
		Map<String,ValidicTobaccoCessation>vTobaccoMap=new HashMap<String, ValidicTobaccoCessation>();
		for(ValidicTobaccoCessation vTobaccoActivity:clinakosTobaccoCessationData){
			vTobaccoMap.put(vTobaccoActivity.get_id(), vTobaccoActivity);
		}
		return vTobaccoMap;
	}

	/**
	 * 
	 * Save the validic Weight activity data to clinakos database
	 * @param clinakosUserWeightData
	 * @param userWeightActivities2
	 * @param patientId
	 * @param providerId
	 * @return
	 */
	public boolean syncToClinakosWeightActivity(
			List<ValidicWeight> clinakosUserWeightData,
			List<ValidicWeight> userWeightActivities2, int patientId,
			int providerId) {
		boolean isWeightDataSaved=false;
		if(clinakosUserWeightData!=null){
			if(clinakosUserWeightData.size()==0){
				try{
				validicRestService.saveToClinakosWeightActivity(userWeightActivities2,patientId,providerId);
				isWeightDataSaved=true;
				}catch(Exception e){
					e.printStackTrace();
					isWeightDataSaved=false;
				}
				
			}else{
				List<ValidicWeight>latestWeightData=new ArrayList<ValidicWeight>();
				Map<String,ValidicWeight>cWeightMap=new HashMap<String, ValidicWeight>();
				cWeightMap=prepareWeightMap(clinakosUserWeightData);
				for(ValidicWeight vWeightData:userWeightActivities2){
					ValidicWeight cWeightData=cWeightMap.get(vWeightData.get_id());
					if(cWeightData==null){
						latestWeightData.add(vWeightData);
					}
				}
				try{
					if(latestWeightData!=null && latestWeightData.size()>0){
					validicRestService.saveToClinakosWeightActivity(latestWeightData,patientId,providerId);
					isWeightDataSaved=true;
					}else{
						isWeightDataSaved=false;
					}
					}catch(Exception e){
						e.printStackTrace();
						isWeightDataSaved=false;
					}
			}
		}
		return isWeightDataSaved;
	}

	private Map<String, ValidicWeight> prepareWeightMap(
			List<ValidicWeight> clinakosUserWeightData) {
		Map<String,ValidicWeight>vWeightMap=new HashMap<String, ValidicWeight>();
		for(ValidicWeight vWeightActivity:clinakosUserWeightData){
			vWeightMap.put(vWeightActivity.get_id(), vWeightActivity);
		}
		return vWeightMap;
	}

	/**
	 * 
	 * Getting Latest User Tobacco Cessation Activities
	 * @param userObj
	 * @return
	 */
	public List<ValidicTobaccoCessation> getLatestTobaccoCessationActivities(
			UserResponse userObj) {
		List<ValidicTobaccoCessation>userTobaccoCessations=new ArrayList<ValidicTobaccoCessation>();
		String userTobaccoCessationAPI_url=getUserValidicActivityURL(userObj,USER_TOBACCO_CESSATION_URL);
		if(StringUtils.isNotBlank(userTobaccoCessationAPI_url)){
			RestTemplate restTemplate = new RestTemplate();
			try{
			ValidicTobaccoCessationResponse userTobaccoActivityResponse = restTemplate
					.getForObject(userTobaccoCessationAPI_url, ValidicTobaccoCessationResponse.class);
			userTobaccoCessations=userTobaccoActivityResponse.getTobacco_cessation();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return userTobaccoCessations;
	}

	/**
	 * Getting User Latest weight activities
	 * @param userObj
	 * @return
	 */
	public List<ValidicWeight> getLatestWeightActivities(UserResponse userObj) {
		List<ValidicWeight>userWeightMeasurements=new ArrayList<ValidicWeight>();
		String userWeightAPI_url=getUserValidicActivityURL(userObj,USER_WEIGHT_API);
		if(StringUtils.isNotBlank(userWeightAPI_url)){
			RestTemplate restTemplate = new RestTemplate();
			try{
			ValidicWeightResponse userWeightActivityResponse = restTemplate
					.getForObject(userWeightAPI_url, ValidicWeightResponse.class);
			userWeightMeasurements=userWeightActivityResponse.getWeight();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return userWeightMeasurements;
	}

	/**
	 * Save the validic Sleep activity data to clinakos database
	 * @param userSleepData
	 * @param userSleepActivities2
	 * @param patientId
	 * @param providerId
	 * @return
	 */
	public boolean syncToClinakosSleepActivity(
			List<ValidicSleep> userSleepData,
			List<ValidicSleep> userSleepActivities2, int patientId,
			int providerId) {
		boolean isSleepDataSaved=false;
		if(userSleepData!=null){
			if(userSleepData.size()==0){
				try{
				validicRestService.saveToClinakosSleepActivity(userSleepActivities2,patientId,providerId);
				isSleepDataSaved=true;
				}catch(Exception e){
					e.printStackTrace();
					isSleepDataSaved=false;
				}
				
			}else{
				List<ValidicSleep>latestSleepData=new ArrayList<ValidicSleep>();
				Map<String,ValidicSleep>cSleepMap=new HashMap<String, ValidicSleep>();
				cSleepMap=prepareSleepMap(userSleepData);
				for(ValidicSleep vSleepData:userSleepActivities2){
					ValidicSleep cSleepData=cSleepMap.get(vSleepData.get_id());
					if(cSleepData==null){
						latestSleepData.add(vSleepData);
					}
				}
				try{
					if(latestSleepData!=null && latestSleepData.size()>0){
					validicRestService.saveToClinakosSleepActivity(latestSleepData,patientId,providerId);
					isSleepDataSaved=true;
					}else{
						isSleepDataSaved=false;
					}
					}catch(Exception e){
						e.printStackTrace();
						isSleepDataSaved=false;
					}
			}
		}
		return isSleepDataSaved;
	}

	private Map<String, ValidicSleep> prepareSleepMap(
			List<ValidicSleep> userSleepData) {
		Map<String,ValidicSleep>vSleepMap=new HashMap<String, ValidicSleep>();
		for(ValidicSleep vSleepActivity:userSleepData){
			vSleepMap.put(vSleepActivity.get_id(), vSleepActivity);
		}
		return vSleepMap;
	}

	/**
	 * 
	 * Save the validic Nutrition activity data to clinakos database
	 * @param userNutritionData
	 * @param userNutritions2
	 * @param patientId
	 * @param providerId
	 * @return
	 */
	public boolean syncToClinakosNutrition(
			List<ValidicNutrition> userNutritionData,
			List<ValidicNutrition> userNutritions2, int patientId,
			int providerId) {
		boolean isNutritionDataSaved=false;
		if(userNutritionData!=null){
			if(userNutritionData.size()==0){
				try{
				validicRestService.saveToClinakosNutritionActivity(userNutritions2,patientId,providerId);
				isNutritionDataSaved=true;
				}catch(Exception e){
					e.printStackTrace();
					isNutritionDataSaved=false;
				}
				
			}else{
				List<ValidicNutrition>latestNutritionData=new ArrayList<ValidicNutrition>();
				Map<String,ValidicNutrition>cNutritionMap=new HashMap<String, ValidicNutrition>();
				cNutritionMap=prepareNutritionMap(userNutritionData);
				for(ValidicNutrition vNutritionData:userNutritions2){
					ValidicNutrition cNutritionData=cNutritionMap.get(vNutritionData.get_id());
					if(cNutritionData==null){
						latestNutritionData.add(vNutritionData);
					}
				}
				try{
					if(latestNutritionData!=null && latestNutritionData.size()>0){
					validicRestService.saveToClinakosNutritionActivity(latestNutritionData,patientId,providerId);
					isNutritionDataSaved=true;
					}else{
						isNutritionDataSaved=false;
					}
					}catch(Exception e){
						e.printStackTrace();
						isNutritionDataSaved=false;
					}
			}
		}
		return isNutritionDataSaved;
	}

	private Map<String, ValidicNutrition> prepareNutritionMap(
			List<ValidicNutrition> userNutritionData) {
		Map<String,ValidicNutrition>vNutritionMap=new HashMap<String, ValidicNutrition>();
		for(ValidicNutrition vNutritionActivity:userNutritionData){
			vNutritionMap.put(vNutritionActivity.get_id(), vNutritionActivity);
		}
		return vNutritionMap;
	}

	/**
	 * 
	 * Save the validic Routine activity data to clinakos database
	 * @param userRoutineActivitiesData
	 * @param userRoutineActivities2
	 * @param patientId
	 * @param providerId
	 * @return
	 */
	public boolean syncToClinakosRoutineActivities(
			List<ValidicRoutineActivity> userRoutineActivitiesData,
			List<ValidicRoutineActivity> userRoutineActivities2, int patientId,
			int providerId) {
		boolean isRoutineDataSaved=false;
		if(userRoutineActivitiesData!=null){
			if(userRoutineActivitiesData.size()==0){
				try{
				validicRestService.saveToClinakosRoutineActivity(userRoutineActivities2,patientId,providerId);
				isRoutineDataSaved=true;
				}catch(Exception e){
					e.printStackTrace();
					isRoutineDataSaved=false;
				}
				
			}else{
				List<ValidicRoutineActivity>latestRoutineData=new ArrayList<ValidicRoutineActivity>();
				Map<String,ValidicRoutineActivity>cRoutineMap=new HashMap<String, ValidicRoutineActivity>();
				cRoutineMap=preparevRoutineMap(userRoutineActivitiesData);
				for(ValidicRoutineActivity vRoutineData:userRoutineActivities2){
					ValidicRoutineActivity cRoutineData=cRoutineMap.get(vRoutineData.get_id());
					if(cRoutineData==null){
						latestRoutineData.add(vRoutineData);
					}
				}
				try{
					if(latestRoutineData!=null && latestRoutineData.size()>0){
					validicRestService.saveToClinakosRoutineActivity(latestRoutineData,patientId,providerId);
					isRoutineDataSaved=true;
					}else{
						isRoutineDataSaved=false;
					}
					}catch(Exception e){
						e.printStackTrace();
						isRoutineDataSaved=false;
					}
			}
		}
		return isRoutineDataSaved;
	}

	private Map<String, ValidicRoutineActivity> preparevRoutineMap(
			List<ValidicRoutineActivity> userRoutineActivitiesData) {
		Map<String,ValidicRoutineActivity>vRoutineMap=new HashMap<String, ValidicRoutineActivity>();
		for(ValidicRoutineActivity vRoutineActivity:userRoutineActivitiesData){
			vRoutineMap.put(vRoutineActivity.get_id(), vRoutineActivity);
		}
		return vRoutineMap;
	}

	/**
	 * 
	 * Save the validic Biometric data to clinakos database
	 * @param userBiometricData
	 * @param userBiometricMeasurements2
	 * @param patientId
	 * @param providerId
	 * @return
	 * @author IDC-0004
	 */
	public boolean syncToClinakosBiometric(
			List<ValidicBiometricmeasurement> userBiometricData,
			List<ValidicBiometricmeasurement> userBiometricMeasurements2,
			int patientId, int providerId) {
		boolean isBiometricDataSaved=false;
		if(userBiometricData!=null){
			if(userBiometricData.size()==0){
				try{
				validicRestService.saveToClinakosBiometricActivity(userBiometricMeasurements2,patientId,providerId);
				isBiometricDataSaved=true;
				}catch(Exception e){
					e.printStackTrace();
					isBiometricDataSaved=false;
				}
				
			}else{
				List<ValidicBiometricmeasurement>latestBiometricData=new ArrayList<ValidicBiometricmeasurement>();
				Map<String,ValidicBiometricmeasurement>cBiometricMap=new HashMap<String, ValidicBiometricmeasurement>();
				cBiometricMap=prepareVbiometricMap(userBiometricData);
				for(ValidicBiometricmeasurement vBiometricmeasurementData:userBiometricMeasurements2){
					ValidicBiometricmeasurement cBiometricData=cBiometricMap.get(vBiometricmeasurementData.get_id());
					if(cBiometricData==null){
						latestBiometricData.add(vBiometricmeasurementData);
					}
				}
				try{
					if(latestBiometricData!=null && latestBiometricData.size()>0){
					validicRestService.saveToClinakosBiometricActivity(latestBiometricData,patientId,providerId);
					isBiometricDataSaved=true;
					}else{
						isBiometricDataSaved=false;
					}
					}catch(Exception e){
						e.printStackTrace();
						isBiometricDataSaved=false;
					}
			}
		}
		return isBiometricDataSaved;
	}

	private Map<String, ValidicBiometricmeasurement> prepareVbiometricMap(
			List<ValidicBiometricmeasurement> userBiometricData) {
		Map<String,ValidicBiometricmeasurement>vBiometricMap=new HashMap<String, ValidicBiometricmeasurement>();
		for(ValidicBiometricmeasurement vBiometricActivity:userBiometricData){
			vBiometricMap.put(vBiometricActivity.get_id(), vBiometricActivity);
		}
		return vBiometricMap;
	}

	/**
	 * 
	 * Getting Latest Sleep Activities of User
	 * @param userObj
	 * @return
	 */
	public List<ValidicSleep> getLatestSleepActivities(UserResponse userObj) {
		List<ValidicSleep>userSleepMeasurements=new ArrayList<ValidicSleep>();
		String userSleepAPI_url=getUserValidicActivityURL(userObj,USER_SLEEP_API);
		if(StringUtils.isNotBlank(userSleepAPI_url)){
			RestTemplate restTemplate = new RestTemplate();
			try{
			ValidicSleepResponse userSleepActivityResponse = restTemplate
					.getForObject(userSleepAPI_url, ValidicSleepResponse.class);
			userSleepMeasurements=userSleepActivityResponse.getSleep();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return userSleepMeasurements;
	}

	/**
	 * 
	 * Getting Latest Nutrition Data of User
	 * @param userObj
	 * @return
	 */
	public List<ValidicNutrition> getLatestNutritions(UserResponse userObj) {
		List<ValidicNutrition>userNutritionMeasurements=new ArrayList<ValidicNutrition>();
		String userNutritionAPI_url=getUserValidicActivityURL(userObj,USER_NUTRITION_API);
		if(StringUtils.isNotBlank(userNutritionAPI_url)){
			RestTemplate restTemplate = new RestTemplate();
			try{
			ValidicNutritionResponse userNutritionResponse = restTemplate
					.getForObject(userNutritionAPI_url, ValidicNutritionResponse.class);
			userNutritionMeasurements=userNutritionResponse.getNutrition();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return userNutritionMeasurements;
	}

	/**
	 * 
	 * Getting Latest Routine Activities of User
	 * @param userObj
	 * @return
	 */
	public List<ValidicRoutineActivity> getLatestRoutineActivities(
			UserResponse userObj) {
		List<ValidicRoutineActivity>userRoutineMeasurements=new ArrayList<ValidicRoutineActivity>();
		String userRoutineAPI_url=getUserValidicActivityURL(userObj,USER_ROUTINE_API);
		if(StringUtils.isNotBlank(userRoutineAPI_url)){
			RestTemplate restTemplate = new RestTemplate();
			try{
			ValidicRoutineRespose userRoutineResponse = restTemplate
					.getForObject(userRoutineAPI_url, ValidicRoutineRespose.class);
			userRoutineMeasurements=userRoutineResponse.getRoutine();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return userRoutineMeasurements;
	}

	/**
	 * 
	 * Getting Latest Biometric Meausurements of User
	 * @param userObj
	 * @return
	 */
	public List<ValidicBiometricmeasurement> getLatestBiometricMeasurements(
			UserResponse userObj) {
		List<ValidicBiometricmeasurement>userBiometricMeasurements=new ArrayList<ValidicBiometricmeasurement>();
		String userBiometricAPI_url=getUserValidicActivityURL(userObj,USER_BIOMETRICS_API);
		if(StringUtils.isNotBlank(userBiometricAPI_url)){
			RestTemplate restTemplate = new RestTemplate();
			try{
			ValidicBiometricsMeasurementResponse userBiometricResponse = restTemplate
					.getForObject(userBiometricAPI_url, ValidicBiometricsMeasurementResponse.class);
			userBiometricMeasurements=userBiometricResponse.getBiometrics();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return userBiometricMeasurements;
	}

	public boolean syncToClinakosDiabetes(
			List<ValidicDiabetesMeasurements> clinakosDiabetesData,
			List<ValidicDiabetesMeasurements> userDiabetesMeasurements2,
			int patientId, int providerId) {
		boolean isDiabetesSaved=false;
		if(clinakosDiabetesData!=null){
			if(clinakosDiabetesData.size()==0){
				try{
					validicRestService.saveToClinakosActivityDiabetes(userDiabetesMeasurements2,patientId,providerId);
					isDiabetesSaved=true;
				}catch(Exception e){
					e.printStackTrace();
					isDiabetesSaved=false;
				}
			}else{
				List<ValidicDiabetesMeasurements>latestDiabetesData=new ArrayList<ValidicDiabetesMeasurements>();
				Map<String,ValidicDiabetesMeasurements>cDiabetesMap=new HashMap<String, ValidicDiabetesMeasurements>();
				cDiabetesMap=prepareVdiabetesMap(clinakosDiabetesData);
				for(ValidicDiabetesMeasurements vDiabetesData:userDiabetesMeasurements2){
					ValidicDiabetesMeasurements cDiabetesData=cDiabetesMap.get(vDiabetesData.get_id());
					if(cDiabetesData==null){
						latestDiabetesData.add(vDiabetesData);
					}
				}
				try{
					if(latestDiabetesData!=null && latestDiabetesData.size()>0){
					validicRestService.saveToClinakosActivityDiabetes(latestDiabetesData,patientId,providerId);
					isDiabetesSaved=true;
					}else{
						isDiabetesSaved=false;
					}
					}catch(Exception e){
						e.printStackTrace();
						isDiabetesSaved=false;
					}

			}
		}
		return isDiabetesSaved;
	}

	private Map<String, ValidicDiabetesMeasurements> prepareVdiabetesMap(
			List<ValidicDiabetesMeasurements> clinakosDiabetesData) {
		Map<String,ValidicDiabetesMeasurements>vDiabetesMap=new HashMap<String, ValidicDiabetesMeasurements>();
		for(ValidicDiabetesMeasurements vDiabetesActivity:clinakosDiabetesData){
			vDiabetesMap.put(vDiabetesActivity.get_id(), vDiabetesActivity);
		}
		return vDiabetesMap;
	}

	public List<ValidicDiabetesMeasurements> getLatestUserDiabetesMeasurements(
			UserResponse userObj) {
		List<ValidicDiabetesMeasurements>userDiabetesMeasurements=new ArrayList<ValidicDiabetesMeasurements>();
		String userDiabetesAPI_url=getUserValidicActivityURL(userObj,USER_DIABETES_API);
		if(StringUtils.isNotBlank(userDiabetesAPI_url)){
			RestTemplate restTemplate = new RestTemplate();
			try{
			ValidicDiabitesResponse userDiabetesActivityResponse = restTemplate
					.getForObject(userDiabetesAPI_url, ValidicDiabitesResponse.class);
			userDiabetesMeasurements=userDiabetesActivityResponse.getDiabetes();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return userDiabetesMeasurements;
		
	}

	public boolean syncToClinakosFitness(
			List<ValidicFitnessActivity> clinakosValidicFitnessData, List<ValidicFitnessActivity> userFitnessActivities2, int patientId, int providerId) {
		boolean isFitnessSaved=false;
		if(clinakosValidicFitnessData!=null){
			if(clinakosValidicFitnessData.size()==0){
				try{
				validicRestService.saveToClinakosActivityFitness(userFitnessActivities2,patientId,providerId);
				isFitnessSaved=true;
				}catch(Exception e){
					e.printStackTrace();
					isFitnessSaved=false;
				}
				
			}else{
				List<ValidicFitnessActivity>latestFitnessData=new ArrayList<ValidicFitnessActivity>();
				Map<String,ValidicFitnessActivity>cFitnessMap=new HashMap<String, ValidicFitnessActivity>();
				cFitnessMap=prepareVfitnessMap(clinakosValidicFitnessData);
				for(ValidicFitnessActivity vFitnessData:userFitnessActivities2){
					ValidicFitnessActivity cFitnessData=cFitnessMap.get(vFitnessData.get_id());
					if(cFitnessData==null){
						latestFitnessData.add(vFitnessData);
					}
				}
				try{
					if(latestFitnessData!=null && latestFitnessData.size()>0){
					validicRestService.saveToClinakosActivityFitness(latestFitnessData,patientId,providerId);
					isFitnessSaved=true;
					}else{
						isFitnessSaved=false;
					}
					}catch(Exception e){
						e.printStackTrace();
						isFitnessSaved=false;
					}
			}
		}
		return isFitnessSaved;
		
	}

	private Map<String, ValidicFitnessActivity> prepareVfitnessMap(
			List<ValidicFitnessActivity> userFitnessActivities2) {
		Map<String,ValidicFitnessActivity>vFitnessMap=new HashMap<String, ValidicFitnessActivity>();
		for(ValidicFitnessActivity vFitnessActivity:userFitnessActivities2){
			vFitnessMap.put(vFitnessActivity.get_id(), vFitnessActivity);
		}
		return vFitnessMap;
	}

	/**
	 * Latest Fitness Activity Data for User
	 * @param userObj
	 * @return
	 */
	public List<ValidicFitnessActivity> getLatestFitnessActivities(
			UserResponse userObj) {
		List<ValidicFitnessActivity>userFitness=new ArrayList<ValidicFitnessActivity>();
		String userFitnessAPI_url=getUserValidicActivityURL(userObj,USER_FITNESS_API);
		if(StringUtils.isNotBlank(userFitnessAPI_url)){
			RestTemplate restTemplate = new RestTemplate();
			try{
			ValidicFitnessActivityResponse fitnessActivityResponse = restTemplate
					.getForObject(userFitnessAPI_url, ValidicFitnessActivityResponse.class);
			//if(fitnessActivityResponse.getSummary().getMessage().equalsIgnoreCase("ok")){
				userFitness=fitnessActivityResponse.getFitness();
			//}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return userFitness;
	}

	/**
	 * 
	 * Return User Fitness Validic REST API URL
	 * @param userObj
	 * @param activity 
	 * @return
	 */
	private String getUserValidicActivityURL(UserResponse userObj, String activity) {
		String url=new 	String();
		System.out.println("Activity URL "+activity);
		try {
			url = getProperties().getProperty(activity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		url = url.replace("{ORGANIZATION_ID}",
				userObj.getOrganizationId());
		url=url.replace("{USER_ID}",userObj.get_id());
		url=url.replace("{ORGANIZATION_ACCESS_TOKEN}", userObj.getOrganizationAccessToken());
		System.out.println("Validid User URL "+url);
		return url;
	}

	private List<ValidicFitnessActivity>userFitnessActivities;
	private List<ValidicDiabetesMeasurements>userDiabetesMeasurements;
	private List<ValidicBiometricmeasurement>userBiometricMeasurements;
	private List<ValidicRoutineActivity>userRoutineActivities;
	private List<ValidicNutrition>userNutritions;
	private List<ValidicSleep>userSleepActivities;
	private List<ValidicWeight>userWeightActivities;
	private List<ValidicTobaccoCessation>userTobaccoCessationActivities;
	
	public void loadDeviceApps(){
		System.out.println("Load Device Apps method fired");
		this.deviceResponse= getDeviceResponse();
		RequestContext context=RequestContext.getCurrentInstance();
		context.addCallbackParam("url_val", deviceResponse);
	}

	
	public void setDeviceResponse(String deviceResponse) {
		this.deviceResponse = deviceResponse;
	}
	

	public List<ValidicFitnessActivity> getUserFitnessActivities() {
		return userFitnessActivities;
	}

	public void setUserFitnessActivities(
			List<ValidicFitnessActivity> userFitnessActivities) {
		this.userFitnessActivities = userFitnessActivities;
	}

	public List<ValidicDiabetesMeasurements> getUserDiabetesMeasurements() {
		return userDiabetesMeasurements;
	}

	public void setUserDiabetesMeasurements(
			List<ValidicDiabetesMeasurements> userDiabetesMeasurements) {
		this.userDiabetesMeasurements = userDiabetesMeasurements;
	}

	public List<ValidicBiometricmeasurement> getUserBiometricMeasurements() {
		return userBiometricMeasurements;
	}

	public void setUserBiometricMeasurements(
			List<ValidicBiometricmeasurement> userBiometricMeasurements) {
		this.userBiometricMeasurements = userBiometricMeasurements;
	}

	public List<ValidicRoutineActivity> getUserRoutineActivities() {
		return userRoutineActivities;
	}

	public void setUserRoutineActivities(
			List<ValidicRoutineActivity> userRoutineActivities) {
		this.userRoutineActivities = userRoutineActivities;
	}

	public List<ValidicNutrition> getUserNutritions() {
		return userNutritions;
	}

	public void setUserNutritions(List<ValidicNutrition> userNutritions) {
		this.userNutritions = userNutritions;
	}

	public List<ValidicSleep> getUserSleepActivities() {
		return userSleepActivities;
	}

	public void setUserSleepActivities(List<ValidicSleep> userSleepActivities) {
		this.userSleepActivities = userSleepActivities;
	}

	public List<ValidicWeight> getUserWeightActivities() {
		return userWeightActivities;
	}

	public void setUserWeightActivities(List<ValidicWeight> userWeightActivities) {
		this.userWeightActivities = userWeightActivities;
	}

	public List<ValidicTobaccoCessation> getUserTobaccoCessationActivities() {
		return userTobaccoCessationActivities;
	}

	public void setUserTobaccoCessationActivities(
			List<ValidicTobaccoCessation> userTobaccoCessationActivities) {
		this.userTobaccoCessationActivities = userTobaccoCessationActivities;
	}

	public ClinakosValidicDevices getValidicDevices() {
		return validicDevices;
	}

	public void setValidicDevices(ClinakosValidicDevices validicDevices) {
		this.validicDevices = validicDevices;
	}
	
}