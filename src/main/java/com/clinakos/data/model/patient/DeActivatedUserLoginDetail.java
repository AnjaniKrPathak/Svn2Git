/**
 * 
 */
package com.clinakos.data.model.patient;

import java.util.Date;
import java.util.List;

/**
 * Used for only Deactivated Patients
 * @author IDC-0004
 *
 */
public class DeActivatedUserLoginDetail {

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
	private int providerId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
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
	public String getMaskSociaSecurityNumber() {
		return maskSociaSecurityNumber;
	}
	public void setMaskSociaSecurityNumber(String maskSociaSecurityNumber) {
		this.maskSociaSecurityNumber = maskSociaSecurityNumber;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
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
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getLoginTimeZone() {
		return loginTimeZone;
	}
	public void setLoginTimeZone(String loginTimeZone) {
		this.loginTimeZone = loginTimeZone;
	}
	public String getMiddleName() {
		return middleName;
	}
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getExtensionPhone() {
		return extensionPhone;
	}
	public void setExtensionPhone(String extensionPhone) {
		this.extensionPhone = extensionPhone;
	}
	public char getPatientConsent() {
		return patientConsent;
	}
	public void setPatientConsent(char patientConsent) {
		this.patientConsent = patientConsent;
	}
	public Date getConsentWithdrawnDate() {
		return consentWithdrawnDate;
	}
	public void setConsentWithdrawnDate(Date consentWithdrawnDate) {
		this.consentWithdrawnDate = consentWithdrawnDate;
	}
	public Date getConsentGrantDate() {
		return consentGrantDate;
	}
	public void setConsentGrantDate(Date consentGrantDate) {
		this.consentGrantDate = consentGrantDate;
	}
	public Date getPatientBatchCreatedDate() {
		return patientBatchCreatedDate;
	}
	public void setPatientBatchCreatedDate(Date patientBatchCreatedDate) {
		this.patientBatchCreatedDate = patientBatchCreatedDate;
	}
	public int getPatientBatchNo() {
		return patientBatchNo;
	}
	public void setPatientBatchNo(int patientBatchNo) {
		this.patientBatchNo = patientBatchNo;
	}
	public boolean isPbmDrugHistoryDetailStatus() {
		return pbmDrugHistoryDetailStatus;
	}
	public void setPbmDrugHistoryDetailStatus(boolean pbmDrugHistoryDetailStatus) {
		this.pbmDrugHistoryDetailStatus = pbmDrugHistoryDetailStatus;
	}
	public boolean isPbmDrugHistoryResultStatus() {
		return pbmDrugHistoryResultStatus;
	}
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
	public List<PatientMedicationData> getPatientMedicationDataList() {
		return patientMedicationDataList;
	}
	public void setPatientMedicationDataList(
			List<PatientMedicationData> patientMedicationDataList) {
		this.patientMedicationDataList = patientMedicationDataList;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getSystolic() {
		return systolic;
	}
	public void setSystolic(String systolic) {
		this.systolic = systolic;
	}
	public String getDiastolic() {
		return diastolic;
	}
	public void setDiastolic(String diastolic) {
		this.diastolic = diastolic;
	}
	public String getBmiRange() {
		return bmiRange;
	}
	public void setBmiRange(String bmiRange) {
		this.bmiRange = bmiRange;
	}
	public String getLastReconciledDate() {
		return lastReconciledDate;
	}
	public void setLastReconciledDate(String lastReconciledDate) {
		this.lastReconciledDate = lastReconciledDate;
	}
	public String getDocFName() {
		return docFName;
	}
	public void setDocFName(String docFName) {
		this.docFName = docFName;
	}
	public String getDocLastName() {
		return docLastName;
	}
	public void setDocLastName(String docLastName) {
		this.docLastName = docLastName;
	}
	public String getDocMidName() {
		return docMidName;
	}
	public void setDocMidName(String docMidName) {
		this.docMidName = docMidName;
	}
	public String getDocStreet() {
		return docStreet;
	}
	public void setDocStreet(String docStreet) {
		this.docStreet = docStreet;
	}
	public String getDocCity() {
		return docCity;
	}
	public void setDocCity(String docCity) {
		this.docCity = docCity;
	}
	public String getDocPhoneNo() {
		return docPhoneNo;
	}
	public void setDocPhoneNo(String docPhoneNo) {
		this.docPhoneNo = docPhoneNo;
	}
	public List<PatientDiagnosesDetails> getPatDiagnosisList() {
		return patDiagnosisList;
	}
	public void setPatDiagnosisList(List<PatientDiagnosesDetails> patDiagnosisList) {
		this.patDiagnosisList = patDiagnosisList;
	}
	public List<SendMessageEditRx> getPatSendMessageEditRxList() {
		return patSendMessageEditRxList;
	}
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
	public List<ProcedureResultHistory> getPatLabReslutList() {
		return patLabReslutList;
	}
	public void setPatLabReslutList(List<ProcedureResultHistory> patLabReslutList) {
		this.patLabReslutList = patLabReslutList;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	
	
}
