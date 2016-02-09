package com.clinakos.data.model.core;

public class EmdeonLabResult {
	private int id;
	private String labResultValue;
	private String refrenceRange;
	private String comments;
	private String labName;
	private String labUnit;
	private String patientId;
	private String labResultCode;
	private String loincCode;
	private String labResultStatus;

	

	/**
	 * @return the refrenceRange
	 */
	public String getRefrenceRange() {
		return refrenceRange;
	}

	/**
	 * @param refrenceRange
	 *            the refrenceRange to set
	 */
	public void setRefrenceRange(String refrenceRange) {
		this.refrenceRange = refrenceRange;
	}

	

	/**
	 * @return the labName
	 */
	public String getLabName() {
		return labName;
	}

	/**
	 * @param labName
	 *            the labName to set
	 */
	public void setLabName(String labName) {
		this.labName = labName;
	}

	/**
	 * @return the labUnit
	 */
	public String getLabUnit() {
		return labUnit;
	}

	/**
	 * @param labUnit
	 *            the labUnit to set
	 */
	public void setLabUnit(String labUnit) {
		this.labUnit = labUnit;
	}

	/**
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId
	 *            the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
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
	 * @return the labResultCode
	 */
	public String getLabResultCode() {
		return labResultCode;
	}

	/**
	 * @param labResultCode the labResultCode to set
	 */
	public void setLabResultCode(String labResultCode) {
		this.labResultCode = labResultCode;
	}

	/**
	 * @return the loincCode
	 */
	public String getLoincCode() {
		return loincCode;
	}

	/**
	 * @param loincCode the loincCode to set
	 */
	public void setLoincCode(String loincCode) {
		this.loincCode = loincCode;
	}

	/**
	 * @return the labResultStatus
	 */
	public String getLabResultStatus() {
		return labResultStatus;
	}

	/**
	 * @param labResultStatus the labResultStatus to set
	 */
	public void setLabResultStatus(String labResultStatus) {
		this.labResultStatus = labResultStatus;
	}

	/**
	 * @return the labResultValue
	 */
	public String getLabResultValue() {
		return labResultValue;
	}

	/**
	 * @param labResultValue the labResultValue to set
	 */
	public void setLabResultValue(String labResultValue) {
		this.labResultValue = labResultValue;
	}

}
