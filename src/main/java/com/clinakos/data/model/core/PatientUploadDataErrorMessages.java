package com.clinakos.data.model.core;

public class PatientUploadDataErrorMessages {
	private int id;
	private String sourceEmrPatientId;
	private String errorMessage;
	private int providerId;
	private int adminId;
	
	
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
	 * @return the sourceEmrPatientId
	 */
	public String getSourceEmrPatientId() {
		return sourceEmrPatientId;
	}
	/**
	 * @param sourceEmrPatientId the sourceEmrPatientId to set
	 */
	public void setSourceEmrPatientId(String sourceEmrPatientId) {
		this.sourceEmrPatientId = sourceEmrPatientId;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the providerId
	 */
	public int getProviderId() {
		return providerId;
	}
	/**
	 * @param providerId the providerId to set
	 */
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	/**
	 * @return the adminId
	 */
	public int getAdminId() {
		return adminId;
	}
	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

}
