package com.clinakos.data.model.core;

public class PatientPBMDrugHistoryResult {
	private int id;
	private int patientId;
	private String pbmXmlInBase64DecodeResult;
	private String doctorFirstName;
	private String doctorLastName;
	private String drugInfo;
	private String drugQuantity;
	private String fillDate;
	private String drugNdc;
	private String drugId;
	private String healthPlanName;
	private String pharmacyName;
	private String pharmacyContactNumber;
	private String daysSupply;
	private String sig;
	private String drugHistoryTransactionGuid;
	private String doctorContactNumber;
	private String source;
	private String patientPbmEligblityGuid;
	private boolean status=true;
	private boolean drugHistoryExist; //@author : saurabh, please don't change/update the values stored in it, used batch data pull process.
	private int pulledBy; //@author : saurabh, please don't change/update the values stored in it, used batch data pull process.
	
	
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
	 * @return the pbmXmlInBase64DecodeResult
	 */
	public String getPbmXmlInBase64DecodeResult() {
		return pbmXmlInBase64DecodeResult;
	}
	/**
	 * @param pbmXmlInBase64DecodeResult the pbmXmlInBase64DecodeResult to set
	 */
	public void setPbmXmlInBase64DecodeResult(String pbmXmlInBase64DecodeResult) {
		this.pbmXmlInBase64DecodeResult = pbmXmlInBase64DecodeResult;
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
	 * @return the doctorFirstName
	 */
	public String getDoctorFirstName() {
		return doctorFirstName;
	}
	/**
	 * @param doctorFirstName the doctorFirstName to set
	 */
	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}
	/**
	 * @return the doctorLastName
	 */
	public String getDoctorLastName() {
		return doctorLastName;
	}
	/**
	 * @param doctorLastName the doctorLastName to set
	 */
	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}
	/**
	 * @return the drugInfo
	 */
	public String getDrugInfo() {
		return drugInfo;
	}
	/**
	 * @param drugInfo the drugInfo to set
	 */
	public void setDrugInfo(String drugInfo) {
		this.drugInfo = drugInfo;
	}
	/**
	 * @return the drugQuantity
	 */
	public String getDrugQuantity() {
		return drugQuantity;
	}
	/**
	 * @param drugQuantity the drugQuantity to set
	 */
	public void setDrugQuantity(String drugQuantity) {
		this.drugQuantity = drugQuantity;
	}
	/**
	 * @return the fillDate
	 */
	public String getFillDate() {
		return fillDate;
	}
	/**
	 * @param fillDate the fillDate to set
	 */
	public void setFillDate(String fillDate) {
		this.fillDate = fillDate;
	}
	/**
	 * @return the drugNdc
	 */
	public String getDrugNdc() {
		return drugNdc;
	}
	/**
	 * @param drugNdc the drugNdc to set
	 */
	public void setDrugNdc(String drugNdc) {
		this.drugNdc = drugNdc;
	}
	/**
	 * @return the drugId
	 */
	public String getDrugId() {
		return drugId;
	}
	/**
	 * @param drugId the drugId to set
	 */
	public void setDrugId(String drugId) {
		this.drugId = drugId;
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
	/**
	 * @return the pharmacyName
	 */
	public String getPharmacyName() {
		return pharmacyName;
	}
	/**
	 * @param pharmacyName the pharmacyName to set
	 */
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	/**
	 * @return the pharmacyContactNumber
	 */
	public String getPharmacyContactNumber() {
		return pharmacyContactNumber;
	}
	/**
	 * @param pharmacyContactNumber the pharmacyContactNumber to set
	 */
	public void setPharmacyContactNumber(String pharmacyContactNumber) {
		this.pharmacyContactNumber = pharmacyContactNumber;
	}
	/**
	 * @return the daysSupply
	 */
	public String getDaysSupply() {
		return daysSupply;
	}
	/**
	 * @param daysSupply the daysSupply to set
	 */
	public void setDaysSupply(String daysSupply) {
		this.daysSupply = daysSupply;
	}
	/**
	 * @return the sig
	 */
	public String getSig() {
		return sig;
	}
	/**
	 * @param sig the sig to set
	 */
	public void setSig(String sig) {
		this.sig = sig;
	}
	/**
	 * @return the drugHistoryTransactionGuid
	 */
	public String getDrugHistoryTransactionGuid() {
		return drugHistoryTransactionGuid;
	}
	/**
	 * @param drugHistoryTransactionGuid the drugHistoryTransactionGuid to set
	 */
	public void setDrugHistoryTransactionGuid(String drugHistoryTransactionGuid) {
		this.drugHistoryTransactionGuid = drugHistoryTransactionGuid;
	}
	/**
	 * @return the doctorContactNumber
	 */
	public String getDoctorContactNumber() {
		return doctorContactNumber;
	}
	/**
	 * @param doctorContactNumber the doctorContactNumber to set
	 */
	public void setDoctorContactNumber(String doctorContactNumber) {
		this.doctorContactNumber = doctorContactNumber;
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
	public boolean isDrugHistoryExist() {
		return drugHistoryExist;
	}
	public void setDrugHistoryExist(boolean drugHistoryExist) {
		this.drugHistoryExist = drugHistoryExist;
	}
	public int getPulledBy() {
		return pulledBy;
	}
	public void setPulledBy(int pulledBy) {
		this.pulledBy = pulledBy;
	}
	
}
