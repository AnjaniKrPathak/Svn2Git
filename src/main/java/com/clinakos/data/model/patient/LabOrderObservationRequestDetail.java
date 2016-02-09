package com.clinakos.data.model.patient;

import java.util.Date;

public class LabOrderObservationRequestDetail {
	private int id;
	private String observationRequestId;
	private String placerOrderNumber;
	private String fillerOrderNumber;
	private String universalServiceIdentifierId;
	private String universalServiceText;
	private String universalServiceCodingSystem;
	private String priority;
	private String requestedDateTime;
	private String observationDateTime;
	private String observationEndDateTime;
	private String collectionVolumeQuantity;
	private String collectionIdentifier;
	private String speciemenActionCode;
	private String releventClinicalInformation;
	private String speciemenRecivedDateTime;
	private String speciemenSourceCode;
	private String specimenSourceAdditives;
	private String speciemenSourceText;
	private String orderingProvider;
	private String patientId;
	
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
	 * @return the observationRequestId
	 */
	public String getObservationRequestId() {
		return observationRequestId;
	}
	/**
	 * @param observationRequestId the observationRequestId to set
	 */
	public void setObservationRequestId(String observationRequestId) {
		this.observationRequestId = observationRequestId;
	}
	/**
	 * @return the placerOrderNumber
	 */
	public String getPlacerOrderNumber() {
		return placerOrderNumber;
	}
	/**
	 * @param placerOrderNumber the placerOrderNumber to set
	 */
	public void setPlacerOrderNumber(String placerOrderNumber) {
		this.placerOrderNumber = placerOrderNumber;
	}
	/**
	 * @return the fillerOrderNumber
	 */
	public String getFillerOrderNumber() {
		return fillerOrderNumber;
	}
	/**
	 * @param fillerOrderNumber the fillerOrderNumber to set
	 */
	public void setFillerOrderNumber(String fillerOrderNumber) {
		this.fillerOrderNumber = fillerOrderNumber;
	}
	/**
	 * @return the universalServiceIdentifierId
	 */
	public String getUniversalServiceIdentifierId() {
		return universalServiceIdentifierId;
	}
	/**
	 * @param universalServiceIdentifierId the universalServiceIdentifierId to set
	 */
	public void setUniversalServiceIdentifierId(String universalServiceIdentifierId) {
		this.universalServiceIdentifierId = universalServiceIdentifierId;
	}
	/**
	 * @return the universalServiceText
	 */
	public String getUniversalServiceText() {
		return universalServiceText;
	}
	/**
	 * @param universalServiceText the universalServiceText to set
	 */
	public void setUniversalServiceText(String universalServiceText) {
		this.universalServiceText = universalServiceText;
	}
	/**
	 * @return the universalServiceCodingSystem
	 */
	public String getUniversalServiceCodingSystem() {
		return universalServiceCodingSystem;
	}
	/**
	 * @param universalServiceCodingSystem the universalServiceCodingSystem to set
	 */
	public void setUniversalServiceCodingSystem(String universalServiceCodingSystem) {
		this.universalServiceCodingSystem = universalServiceCodingSystem;
	}
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}
	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	/**
	 * @return the requestedDateTime
	 */
	public String getRequestedDateTime() {
		return requestedDateTime;
	}
	/**
	 * @param requestedDateTime the requestedDateTime to set
	 */
	public void setRequestedDateTime(String requestedDateTime) {
		this.requestedDateTime = requestedDateTime;
	}
	/**
	 * @return the observationDateTime
	 */
	public String getObservationDateTime() {
		return observationDateTime;
	}
	/**
	 * @param observationDateTime the observationDateTime to set
	 */
	public void setObservationDateTime(String observationDateTime) {
		this.observationDateTime = observationDateTime;
	}
	/**
	 * @return the observationEndDateTime
	 */
	public String getObservationEndDateTime() {
		return observationEndDateTime;
	}
	/**
	 * @param observationEndDateTime the observationEndDateTime to set
	 */
	public void setObservationEndDateTime(String observationEndDateTime) {
		this.observationEndDateTime = observationEndDateTime;
	}
	/**
	 * @return the collectionVolumeQuantity
	 */
	public String getCollectionVolumeQuantity() {
		return collectionVolumeQuantity;
	}
	/**
	 * @param collectionVolumeQuantity the collectionVolumeQuantity to set
	 */
	public void setCollectionVolumeQuantity(String collectionVolumeQuantity) {
		this.collectionVolumeQuantity = collectionVolumeQuantity;
	}
	/**
	 * @return the collectionIdentifier
	 */
	public String getCollectionIdentifier() {
		return collectionIdentifier;
	}
	/**
	 * @param collectionIdentifier the collectionIdentifier to set
	 */
	public void setCollectionIdentifier(String collectionIdentifier) {
		this.collectionIdentifier = collectionIdentifier;
	}
	/**
	 * @return the speciemenActionCode
	 */
	public String getSpeciemenActionCode() {
		return speciemenActionCode;
	}
	/**
	 * @param speciemenActionCode the speciemenActionCode to set
	 */
	public void setSpeciemenActionCode(String speciemenActionCode) {
		this.speciemenActionCode = speciemenActionCode;
	}
	/**
	 * @return the releventClinicalInformation
	 */
	public String getReleventClinicalInformation() {
		return releventClinicalInformation;
	}
	/**
	 * @param releventClinicalInformation the releventClinicalInformation to set
	 */
	public void setReleventClinicalInformation(String releventClinicalInformation) {
		this.releventClinicalInformation = releventClinicalInformation;
	}
	/**
	 * @return the speciemenRecivedDateTime
	 */
	public String getSpeciemenRecivedDateTime() {
		return speciemenRecivedDateTime;
	}
	/**
	 * @param specimenRecivedDateTime the speciemenRecivedDateTime to set
	 */
	public void setSpeciemenRecivedDateTime(String specimenRecivedDateTime) {
		this.speciemenRecivedDateTime = specimenRecivedDateTime;
	}
	/**
	 * @return the speciemenSourceCode
	 */
	public String getSpeciemenSourceCode() {
		return speciemenSourceCode;
	}
	/**
	 * @param speciemenSourceCode the speciemenSourceCode to set
	 */
	public void setSpeciemenSourceCode(String speciemenSourceCode) {
		this.speciemenSourceCode = speciemenSourceCode;
	}
	/**
	 * @return the specimenSourceAdditives
	 */
	public String getSpecimenSourceAdditives() {
		return specimenSourceAdditives;
	}
	/**
	 * @param specimenSourceAdditives the specimenSourceAdditives to set
	 */
	public void setSpecimenSourceAdditives(String specimenSourceAdditives) {
		this.specimenSourceAdditives = specimenSourceAdditives;
	}
	/**
	 * @return the speciemenSourceText
	 */
	public String getSpeciemenSourceText() {
		return speciemenSourceText;
	}
	/**
	 * @param speciemenSourceText the speciemenSourceText to set
	 */
	public void setSpeciemenSourceText(String speciemenSourceText) {
		this.speciemenSourceText = speciemenSourceText;
	}
	/**
	 * @return the orderingProvider
	 */
	public String getOrderingProvider() {
		return orderingProvider;
	}
	/**
	 * @param orderingProvider the orderingProvider to set
	 */
	public void setOrderingProvider(String orderingProvider) {
		this.orderingProvider = orderingProvider;
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
	
	
	
	
	

}
