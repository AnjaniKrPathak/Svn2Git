package com.clinakos.data.model.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.SelectableDataModel;

import com.clinakos.data.model.core.FormularyDetail;



public class UserLoginDetail implements SelectableDataModel<UserLoginDetail> {
	//private static final long serialVersionUID = -7783038885323203850L;
	
	private Integer id;
	//private LoginSecurity loginDetailId;
	private int userId;
	private String firstName;
	private String lastName;
	private String socialSecurityNumber;
	private String maskSociaSecurityNumber;
	private Date dateOfBirth;
	//private String height;
	//private String weight;
	private String email;
	private String gender;
	private String phoneNumber;
	//private String insuranceNumber;
	//private String insuranceCompanyName;
	private String dlNumber;
	private byte[] image;
	private int createdBy;
	private Date createdDate;
	private int modifyBy;
	private Date modifyDate;
	private boolean status;
	private int age;
	private String doorNo;
	private String street;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private String race;
	private String loginTimeZone;
	
	private String middleName;
	
	private boolean checkIntegration;
	private String searchKey;
	private String dob;
	
	private String mobileNumber;
	private String bloodGroup;
	private String martailStatus;
	private Double result;
	private String medicine_stage;
	
	private String userName;
	private String extensionPhone;
	
	private char patientConsent;
	private Date consentWithdrawnDate;
	private Date consentGrantDate;
	
	private Date patientBatchCreatedDate;//Added By Anjani for Patient Batch Date added in System
	private int patientBatchNo ;//Added By Anjani Batch Date Batch No Used only for fetching data 
	private boolean pbmDrugHistoryDetailStatus;
	private boolean pbmDrugHistoryResultStatus;
	private String ethnicity;
	private String language;
	//Added By Anjani for Patient Batch Medicine Report
	private List<PatientMedicationData> patientMedicationDataList=null;
	private String weight;
	private String height;
	private String pulse;
	private String temperature;
	private String systolic;
	private String diastolic;
	private String bmiRange;
	private String lastReconciledDate;
	private String docFName;
	private String docLastName;
	private String docMidName;
	private String docStreet;
	private String docCity;
	private String docPhoneNo;
	private List<PatientDiagnosesDetails> patDiagnosisList=null;
	private List<SendMessageEditRx> patSendMessageEditRxList=null;
	
	private boolean isConnectedToDevices;//This flag indicates whether Patient is connected to validic or not.Default :False indicates Patient is not connected to Validic.
	
	private List<ProcedureResultHistory> patLabReslutList=null;

	private List<DrugInteractionOverview> drugInteractionOverviewList;
	
	

	private int providerId;
	private String userFirstName;
	private String userLastName;
	private Date deactivatedDate;
	

	/*public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/*
	public LoginSecurity getLoginDetailId() {
		return loginDetailId;
	}
	public void setLoginDetailId(LoginSecurity loginDetailId) {
		this.loginDetailId = loginDetailId;
	}*/
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/*public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}*/
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/*public String getInsuranceNumber() {
		return insuranceNumber;
	}
	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}
	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}
	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}*/
	public String getDlNumber() {
		return dlNumber;
	}
	public void setDlNumber(String dlNumber) {
		this.dlNumber = dlNumber;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getMaskSociaSecurityNumber() {
		return maskSociaSecurityNumber;
	}
	public void setMaskSociaSecurityNumber(String maskSociaSecurityNumber) {
		this.maskSociaSecurityNumber = maskSociaSecurityNumber;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	

	public boolean isCheckIntegration() {
		return checkIntegration;
	}
	public void setCheckIntegration(boolean checkIntegration) {
		this.checkIntegration = checkIntegration;
	}
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getLoginTimeZone() {
		return loginTimeZone;
	}
	public void setLoginTimeZone(String loginTimeZone) {
		this.loginTimeZone = loginTimeZone;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	/*public Object getRowData(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object getRowKey(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}*/
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getMartailStatus() {
		return martailStatus;
	}
	public void setMartailStatus(String martailStatus) {
		this.martailStatus = martailStatus;
	}

	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	public String getMedicine_stage() {
		return medicine_stage;
	}
	public void setMedicine_stage(String medicine_stage) {
		this.medicine_stage = medicine_stage;
	}
	public String getExtensionPhone() {
		return extensionPhone;
	}
	public void setExtensionPhone(String extensionPhone) {
		this.extensionPhone = extensionPhone;
	}
	
	private String key;
	final String keyValue="akos";
	public String getKey() {
		return key;
	}
	public void setKey(String keyValue) {
		//key="akos";
		this.key = "akos";
	}
	/**
	 * @return the patientConsent
	 */
	public char getPatientConsent() {
		return patientConsent;
	}
	/**
	 * @param patientConsent the patientConsent to set
	 */
	public void setPatientConsent(char patientConsent) {
		this.patientConsent = patientConsent;
	}
	/**
	 * @return the consentWithdrawnDate
	 */
	public Date getConsentWithdrawnDate() {
		return consentWithdrawnDate;
	}
	/**
	 * @param consentWithdrawnDate the consentWithdrawnDate to set
	 */
	public void setConsentWithdrawnDate(Date consentWithdrawnDate) {
		this.consentWithdrawnDate = consentWithdrawnDate;
	}
	/**
	 * @return the consentGrantDate
	 */
	public Date getConsentGrantDate() {
		return consentGrantDate;
	}
	/**
	 * @param consentGrantDate the consentGrantDate to set
	 */
	public void setConsentGrantDate(Date consentGrantDate) {
		this.consentGrantDate = consentGrantDate;
	}
	/**
	 * For Multiple Selection
	 *//*
	
	public UserLoginDetail getRowData(String arg0) {
		
		return null;
	}

	public Object getRowKey(UserLoginDetail arg0) {
		
		return null;
	}*/
	
	/**
	 * @return the patientBatchCreatedDate
	 */
	public Date getPatientBatchCreatedDate() {
		return patientBatchCreatedDate;
	}
	/**
	 * @param patientBatchCreatedDate the patientBatchCreatedDate to set
	 */
	public void setPatientBatchCreatedDate(Date patientBatchCreatedDate) {
		this.patientBatchCreatedDate = patientBatchCreatedDate;
	}
	/**
	 * @return the patientBatchNo
	 */
	public int getPatientBatchNo() {
		return patientBatchNo;
	}
	/**
	 * @param patientBatchNo the patientBatchNo to set
	 */
	public void setPatientBatchNo(int patientBatchNo) {
		this.patientBatchNo = patientBatchNo;
	}
	@Override
	public UserLoginDetail getRowData(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getRowKey(UserLoginDetail arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return the pbmDrugHistoryDetailStatus
	 */
	public boolean isPbmDrugHistoryDetailStatus() {
		return pbmDrugHistoryDetailStatus;
	}
	/**
	 * @param pbmDrugHistoryDetailStatus the pbmDrugHistoryDetailStatus to set
	 */
	public void setPbmDrugHistoryDetailStatus(boolean pbmDrugHistoryDetailStatus) {
		this.pbmDrugHistoryDetailStatus = pbmDrugHistoryDetailStatus;
	}
	/**
	 * @return the pbmDrugHistoryResultStatus
	 */
	public boolean isPbmDrugHistoryResultStatus() {
		return pbmDrugHistoryResultStatus;
	}
	/**
	 * @param pbmDrugHistoryResultStatus the pbmDrugHistoryResultStatus to set
	 */
	public void setPbmDrugHistoryResultStatus(boolean pbmDrugHistoryResultStatus) {
		this.pbmDrugHistoryResultStatus = pbmDrugHistoryResultStatus;
	}

	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the patientMedicationDataList
	 */
	public List<PatientMedicationData> getPatientMedicationDataList() {
		if(patientMedicationDataList==null){
			patientMedicationDataList=new ArrayList<PatientMedicationData>();
		}
		return patientMedicationDataList;
	}
	/**
	 * @param patientMedicationDataList the patientMedicationDataList to set
	 */
	public void setPatientMedicationDataList(
			List<PatientMedicationData> patientMedicationDataList) {
		this.patientMedicationDataList = patientMedicationDataList;
	}
	
	/**
	 * @return the height
	 */
	public String getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(String height) {
		this.height = height;
	}
	
	
	/**
	 * @return the bmiRange
	 */
	public String getBmiRange() {
		return bmiRange;
	}
	/**
	 * @param bmiRange the bmiRange to set
	 */
	public void setBmiRange(String bmiRange) {
		this.bmiRange = bmiRange;
	}
	
	/**
	 * @return the docFName
	 */
	public String getDocFName() {
		return docFName;
	}
	/**
	 * @param docFName the docFName to set
	 */
	public void setDocFName(String docFName) {
		this.docFName = docFName;
	}
	/**
	 * @return the docLastName
	 */
	public String getDocLastName() {
		return docLastName;
	}
	/**
	 * @param docLastName the docLastName to set
	 */
	public void setDocLastName(String docLastName) {
		this.docLastName = docLastName;
	}
	/**
	 * @return the docMidName
	 */
	public String getDocMidName() {
		return docMidName;
	}
	/**
	 * @param docMidName the docMidName to set
	 */
	public void setDocMidName(String docMidName) {
		this.docMidName = docMidName;
	}
	/**
	 * @return the docStreet
	 */
	public String getDocStreet() {
		return docStreet;
	}
	/**
	 * @param docStreet the docStreet to set
	 */
	public void setDocStreet(String docStreet) {
		this.docStreet = docStreet;
	}
	/**
	 * @return the docCity
	 */
	public String getDocCity() {
		return docCity;
	}
	/**
	 * @param docCity the docCity to set
	 */
	public void setDocCity(String docCity) {
		this.docCity = docCity;
	}
	/**
	 * @return the docPhoneNo
	 */
	public String getDocPhoneNo() {
		return docPhoneNo;
	}
	/**
	 * @param docPhoneNo the docPhoneNo to set
	 */
	public void setDocPhoneNo(String docPhoneNo) {
		this.docPhoneNo = docPhoneNo;
	}
	/**
	 * @return the keyValue
	 */
	public String getKeyValue() {
		return keyValue;
	}
	/**
	 * @return the patDiagnosisList
	 */
	public List<PatientDiagnosesDetails> getPatDiagnosisList() {
		if(patDiagnosisList==null){
			patDiagnosisList=new ArrayList<PatientDiagnosesDetails>();
		}
		return patDiagnosisList;
	}
	/**
	 * @param patDiagnosisList the patDiagnosisList to set
	 */
	public void setPatDiagnosisList(List<PatientDiagnosesDetails> patDiagnosisList) {
		this.patDiagnosisList = patDiagnosisList;
	}
	/**
	 * @return the lastReconciledDate
	 */
	public String getLastReconciledDate() {
		return lastReconciledDate;
	}
	/**
	 * @param lastReconciledDate the lastReconciledDate to set
	 */
	public void setLastReconciledDate(String lastReconciledDate) {
		this.lastReconciledDate = lastReconciledDate;
	}
	/**
	 * @return the pulse
	 */
	public String getPulse() {
		return pulse;
	}
	/**
	 * @param pulse the pulse to set
	 */
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	/**
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}
	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	/**
	 * @return the systolic
	 */
	public String getSystolic() {
		return systolic;
	}
	/**
	 * @param systolic the systolic to set
	 */
	public void setSystolic(String systolic) {
		this.systolic = systolic;
	}
	/**
	 * @return the diastolic
	 */
	public String getDiastolic() {
		return diastolic;
	}
	/**
	 * @param diastolic the diastolic to set
	 */
	public void setDiastolic(String diastolic) {
		this.diastolic = diastolic;
	}
	/**
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/**
	 * @return the patSendMessageEditRxList
	 */
	public List<SendMessageEditRx> getPatSendMessageEditRxList() {
		if(patSendMessageEditRxList==null){
			patSendMessageEditRxList=new ArrayList<SendMessageEditRx>();
		}
		return patSendMessageEditRxList;
	}
	/**
	 * @param patSendMessageEditRxList the patSendMessageEditRxList to set
	 */
	public void setPatSendMessageEditRxList(
			List<SendMessageEditRx> patSendMessageEditRxList) {
		this.patSendMessageEditRxList = patSendMessageEditRxList;
	}
	
	public boolean isConnectedToDevices() {
		return isConnectedToDevices;
	}
	public void setConnectedToDevices(boolean isConnectedToDevices) {
		this.isConnectedToDevices = isConnectedToDevices;
	}
	/**
	 * @return the patLabReslutList
	 */
	public List<ProcedureResultHistory> getPatLabReslutList() {
		if(patLabReslutList==null){
			patLabReslutList=new ArrayList<ProcedureResultHistory>();
		}
		return patLabReslutList;
	}
	/**
	 * @param patLabReslutList the patLabReslutList to set
	 */
	public void setPatLabReslutList(List<ProcedureResultHistory> patLabReslutList) {
		this.patLabReslutList = patLabReslutList;
	}

	/**
	 * @return the drugInteractionOverviewList
	 */
	public List<DrugInteractionOverview> getDrugInteractionOverviewList() {
		if(drugInteractionOverviewList==null){
			drugInteractionOverviewList=new ArrayList<DrugInteractionOverview>();
		}
		return drugInteractionOverviewList;
	}
	/**
	 * @param drugInteractionOverviewList the drugInteractionOverviewList to set
	 */
	public void setDrugInteractionOverviewList(
			List<DrugInteractionOverview> drugInteractionOverviewList) {
		this.drugInteractionOverviewList = drugInteractionOverviewList;
	}

	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public Date getDeactivatedDate() {
		return deactivatedDate;
	}
	public void setDeactivatedDate(Date deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}

	
	
	
	
	
	


}
