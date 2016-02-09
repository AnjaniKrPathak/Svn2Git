package com.clinakos.data.model.patient;

public class LabOrderObservationDetail {
 
	private int id;
	private String orderObservationId;
	private String observationValueType;
	private String observationIdentifier;
	private String observationText;
	private String observationCodingSystemName;
	private String observationSubId;
	private String observationValues;
	private String units;
	private String referanceRange;
	private String abnormalFlag;
	private String observResultStatus;
	
	private String dateLastObsNormalValues;
	private String userDefienedAccessChecks;
	private String dateTimeOfObservation;
	private String producersId;
	private String producersText;
	private String producersCodingSystem;
	private String responsibleObserverName;
	private String observationMethodId;
	
	private String observationMethodText;
	private String observationMethodNameOfCodingSystem;
	private String patientId;
	private String probablity;
	private String natureOfAbnormalTest;
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the orderObservationId
	 */
	public String getOrderObservationId() {
		return orderObservationId;
	}
	/**
	 * @param orderObservationId the orderObservationId to set
	 */
	public void setOrderObservationId(String orderObservationId) {
		this.orderObservationId = orderObservationId;
	}
	/**
	 * @return the observationValueType
	 */
	public String getObservationValueType() {
		return observationValueType;
	}
	/**
	 * @param observationValueType the observationValueType to set
	 */
	public void setObservationValueType(String observationValueType) {
		this.observationValueType = observationValueType;
	}
	/**
	 * @return the observationIdentifier
	 */
	public String getObservationIdentifier() {
		return observationIdentifier;
	}
	/**
	 * @param observationIdentifier the observationIdentifier to set
	 */
	public void setObservationIdentifier(String observationIdentifier) {
		this.observationIdentifier = observationIdentifier;
	}
	/**
	 * @return the observationText
	 */
	public String getObservationText() {
		return observationText;
	}
	/**
	 * @param observationText the observationText to set
	 */
	public void setObservationText(String observationText) {
		this.observationText = observationText;
	}
	/**
	 * @return the observationCodingSystemName
	 */
	public String getObservationCodingSystemName() {
		return observationCodingSystemName;
	}
	/**
	 * @param observationCodingSystemName the observationCodingSystemName to set
	 */
	public void setObservationCodingSystemName(String observationCodingSystemName) {
		this.observationCodingSystemName = observationCodingSystemName;
	}
	/**
	 * @return the observationSubId
	 */
	public String getObservationSubId() {
		return observationSubId;
	}
	/**
	 * @param observationSubId the observationSubId to set
	 */
	public void setObservationSubId(String observationSubId) {
		this.observationSubId = observationSubId;
	}
	/**
	 * @return the observationValues
	 */
	public String getObservationValues() {
		return observationValues;
	}
	/**
	 * @param observationValues the observationValues to set
	 */
	public void setObservationValues(String observationValues) {
		this.observationValues = observationValues;
	}
	/**
	 * @return the units
	 */
	public String getUnits() {
		return units;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(String units) {
		this.units = units;
	}
	/**
	 * @return the referanceRange
	 */
	public String getReferanceRange() {
		return referanceRange;
	}
	/**
	 * @param referanceRange the referanceRange to set
	 */
	public void setReferanceRange(String referanceRange) {
		this.referanceRange = referanceRange;
	}
	/**
	 * @return the abnormalFlag
	 */
	public String getAbnormalFlag() {
		return abnormalFlag;
	}
	/**
	 * @param abnormalFlag the abnormalFlag to set
	 */
	public void setAbnormalFlag(String abnormalFlag) {
		this.abnormalFlag = abnormalFlag;
	}
	/**
	 * @return the observResultStatus
	 */
	public String getObservResultStatus() {
		return observResultStatus;
	}
	/**
	 * @param observResultStatus the observResultStatus to set
	 */
	public void setObservResultStatus(String observResultStatus) {
		this.observResultStatus = observResultStatus;
	}
	/**
	 * @return the dateLastObsNormalValues
	 */
	public String getDateLastObsNormalValues() {
		return dateLastObsNormalValues;
	}
	/**
	 * @param dateLastObsNormalValues the dateLastObsNormalValues to set
	 */
	public void setDateLastObsNormalValues(String dateLastObsNormalValues) {
		this.dateLastObsNormalValues = dateLastObsNormalValues;
	}
	/**
	 * @return the userDefienedAccessChecks
	 */
	public String getUserDefienedAccessChecks() {
		return userDefienedAccessChecks;
	}
	/**
	 * @param userDefienedAccessChecks the userDefienedAccessChecks to set
	 */
	public void setUserDefienedAccessChecks(String userDefienedAccessChecks) {
		this.userDefienedAccessChecks = userDefienedAccessChecks;
	}
	/**
	 * @return the dateTimeOfObservation
	 */
	public String getDateTimeOfObservation() {
		return dateTimeOfObservation;
	}
	/**
	 * @param dateTimeOfObservation the dateTimeOfObservation to set
	 */
	public void setDateTimeOfObservation(String dateTimeOfObservation) {
		this.dateTimeOfObservation = dateTimeOfObservation;
	}
	/**
	 * @return the producersId
	 */
	public String getProducersId() {
		return producersId;
	}
	/**
	 * @param producersId the producersId to set
	 */
	public void setProducersId(String producersId) {
		this.producersId = producersId;
	}
	/**
	 * @return the producersText
	 */
	public String getProducersText() {
		return producersText;
	}
	/**
	 * @param producersText the producersText to set
	 */
	public void setProducersText(String producersText) {
		this.producersText = producersText;
	}
	/**
	 * @return the producersCodingSystem
	 */
	public String getProducersCodingSystem() {
		return producersCodingSystem;
	}
	/**
	 * @param producersCodingSystem the producersCodingSystem to set
	 */
	public void setProducersCodingSystem(String producersCodingSystem) {
		this.producersCodingSystem = producersCodingSystem;
	}
	/**
	 * @return the responsibleObserverName
	 */
	public String getResponsibleObserverName() {
		return responsibleObserverName;
	}
	/**
	 * @param responsibleObserverName the responsibleObserverName to set
	 */
	public void setResponsibleObserverName(String responsibleObserverName) {
		this.responsibleObserverName = responsibleObserverName;
	}
	/**
	 * @return the observationMethodId
	 */
	public String getObservationMethodId() {
		return observationMethodId;
	}
	/**
	 * @param observationMethodId the observationMethodId to set
	 */
	public void setObservationMethodId(String observationMethodId) {
		this.observationMethodId = observationMethodId;
	}
	/**
	 * @return the observationMethodText
	 */
	public String getObservationMethodText() {
		return observationMethodText;
	}
	/**
	 * @param observationMethodText the observationMethodText to set
	 */
	public void setObservationMethodText(String observationMethodText) {
		this.observationMethodText = observationMethodText;
	}
	/**
	 * @return the observationMethodNameOfCodingSystem
	 */
	public String getObservationMethodNameOfCodingSystem() {
		return observationMethodNameOfCodingSystem;
	}
	/**
	 * @param observationMethodNameOfCodingSystem the observationMethodNameOfCodingSystem to set
	 */
	public void setObservationMethodNameOfCodingSystem(
			String observationMethodNameOfCodingSystem) {
		this.observationMethodNameOfCodingSystem = observationMethodNameOfCodingSystem;
	}
	/**
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the probablity
	 */
	public String getProbablity() {
		return probablity;
	}
	/**
	 * @param probablity the probablity to set
	 */
	public void setProbablity(String probablity) {
		this.probablity = probablity;
	}
	/**
	 * @return the natureOfAbnormalTest
	 */
	public String getNatureOfAbnormalTest() {
		return natureOfAbnormalTest;
	}
	/**
	 * @param natureOfAbnormalTest the natureOfAbnormalTest to set
	 */
	public void setNatureOfAbnormalTest(String natureOfAbnormalTest) {
		this.natureOfAbnormalTest = natureOfAbnormalTest;
	}
	
}
