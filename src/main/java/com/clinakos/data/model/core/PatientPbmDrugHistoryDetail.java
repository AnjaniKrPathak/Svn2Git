package com.clinakos.data.model.core;

import java.util.Date;

public class PatientPbmDrugHistoryDetail {
	private int id;
	private int patientId;
	private String patientXmlResult;
	private String patientPbmEligblityGuid;
	private String pbmDrugHistoryXmlResponse;
	private String pbmEligblityXmlResponse;
	private Date requestDate;
	private boolean status=true;
	private String pharmacyBenefit;
	private String mailOrderBenefit;
	private String source;
	private String pbm;
	private String healthPlanName;
	private int pulledBy; //@author : saurabh, please don't change/update the values stored in it, used batch data pull process.
	private boolean pbmExist; //@author : saurabh, please don't change/update the values stored in it, used batch data pull process.
	private boolean drugHistoryExist; //@author : saurabh, please don't change/update the values stored in it, used batch data pull process.
	
	
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
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
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the patientXmlResult
	 */
	public String getPatientXmlResult() {
		return patientXmlResult;
	}
	/**
	 * @param patientXmlResult the patientXmlResult to set
	 */
	public void setPatientXmlResult(String patientXmlResult) {
		this.patientXmlResult = patientXmlResult;
	}
	/**
	 * @return the patientPbmEligblityGuid
	 */
	public String getPatientPbmEligblityGuid() {
		return patientPbmEligblityGuid;
	}
	/**
	 * @param patientPbmEligblityGuid the patientPbmEligblityGuid to set
	 */
	public void setPatientPbmEligblityGuid(String patientPbmEligblityGuid) {
		this.patientPbmEligblityGuid = patientPbmEligblityGuid;
	}
	/**
	 * @return the pbmDrugHistoryXmlResponse
	 */
	public String getPbmDrugHistoryXmlResponse() {
		return pbmDrugHistoryXmlResponse;
	}
	/**
	 * @param pbmDrugHistoryXmlResponse the pbmDrugHistoryXmlResponse to set
	 */
	public void setPbmDrugHistoryXmlResponse(String pbmDrugHistoryXmlResponse) {
		this.pbmDrugHistoryXmlResponse = pbmDrugHistoryXmlResponse;
	}
	/**
	 * @return the pbmEligblityXmlResponse
	 */
	public String getPbmEligblityXmlResponse() {
		return pbmEligblityXmlResponse;
	}
	/**
	 * @param pbmEligblityXmlResponse the pbmEligblityXmlResponse to set
	 */
	public void setPbmEligblityXmlResponse(String pbmEligblityXmlResponse) {
		this.pbmEligblityXmlResponse = pbmEligblityXmlResponse;
	}
	/**
	 * @return the requestDate
	 */
	public Date getRequestDate() {
		return requestDate;
	}
	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * @return the pharmacyBenefit
	 */
	public String getPharmacyBenefit() {
		return pharmacyBenefit;
	}
	/**
	 * @param pharmacyBenefit the pharmacyBenefit to set
	 */
	public void setPharmacyBenefit(String pharmacyBenefit) {
		this.pharmacyBenefit = pharmacyBenefit;
	}
	/**
	 * @return the mailOrderBenefit
	 */
	public String getMailOrderBenefit() {
		return mailOrderBenefit;
	}
	/**
	 * @param mailOrderBenefit the mailOrderBenefit to set
	 */
	public void setMailOrderBenefit(String mailOrderBenefit) {
		this.mailOrderBenefit = mailOrderBenefit;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the pbm
	 */
	public String getPbm() {
		return pbm;
	}
	/**
	 * @param pbm the pbm to set
	 */
	public void setPbm(String pbm) {
		this.pbm = pbm;
	}
	/**
	 * @return the healthPlanName
	 */
	public String getHealthPlanName() {
		return healthPlanName;
	}
	/**
	 * @param healthPlanName the healthPlanName to set
	 */
	public void setHealthPlanName(String healthPlanName) {
		this.healthPlanName = healthPlanName;
	}
	public int getPulledBy() {
		return pulledBy;
	}
	public void setPulledBy(int pulledBy) {
		this.pulledBy = pulledBy;
	}
	public boolean isPbmExist() {
		return pbmExist;
	}
	public void setPbmExist(boolean pbmExist) {
		this.pbmExist = pbmExist;
	}
	public boolean isDrugHistoryExist() {
		return drugHistoryExist;
	}
	public void setDrugHistoryExist(boolean drugHistoryExist) {
		this.drugHistoryExist = drugHistoryExist;
	}
	

}
