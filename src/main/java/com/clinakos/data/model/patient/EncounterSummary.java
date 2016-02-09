package com.clinakos.data.model.patient;

import java.util.Date;

public class EncounterSummary {
	private int encounterId;
	private int patientId;
	private String reasonForEncounter;
	private Date encounterDate;
	private int doctorId;
	private int providerId;
	private int clinicId;
	private String tenPointReviewOfSytemCompleted;
	private String ifYes;
	private String patientNotes;
	private String patientEducation;
	private int encounterNo;
	private Date startDate;
	private Date endDate;
	
	/**
	 * @return the encounterId
	 */
	public int getEncounterId() {
		return encounterId;
	}
	/**
	 * @param encounterId the encounterId to set
	 */
	public void setEncounterId(int encounterId) {
		this.encounterId = encounterId;
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
	 * @return the reasonForEncounter
	 */
	public String getReasonForEncounter() {
		return reasonForEncounter;
	}
	/**
	 * @param reasonForEncounter the reasonForEncounter to set
	 */
	public void setReasonForEncounter(String reasonForEncounter) {
		this.reasonForEncounter = reasonForEncounter;
	}
	/**
	 * @return the encounterDate
	 */
	public Date getEncounterDate() {
		return encounterDate;
	}
	/**
	 * @param encounterDate the encounterDate to set
	 */
	public void setEncounterDate(Date encounterDate) {
		this.encounterDate = encounterDate;
	}
	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}
	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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
	 * @return the ifYes
	 */
	public String getIfYes() {
		return ifYes;
	}
	/**
	 * @param ifYes the ifYes to set
	 */
	public void setIfYes(String ifYes) {
		this.ifYes = ifYes;
	}
	/**
	 * @return the clinicId
	 */
	public int getClinicId() {
		return clinicId;
	}
	/**
	 * @param clinicId the clinicId to set
	 */
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	/**
	 * @return the patientNotes
	 */
	public String getPatientNotes() {
		return patientNotes;
	}
	/**
	 * @param patientNotes the patientNotes to set
	 */
	public void setPatientNotes(String patientNotes) {
		this.patientNotes = patientNotes;
	}
	/**
	 * @return the patientEducation
	 */
	public String getPatientEducation() {
		return patientEducation;
	}
	/**
	 * @param patientEducation the patientEducation to set
	 */
	public void setPatientEducation(String patientEducation) {
		this.patientEducation = patientEducation;
	}
	/**
	 * @return the tenPointReviewOfSytemCompleted
	 */
	public String getTenPointReviewOfSytemCompleted() {
		return tenPointReviewOfSytemCompleted;
	}
	/**
	 * @param tenPointReviewOfSytemCompleted the tenPointReviewOfSytemCompleted to set
	 */
	public void setTenPointReviewOfSytemCompleted(
			String tenPointReviewOfSytemCompleted) {
		this.tenPointReviewOfSytemCompleted = tenPointReviewOfSytemCompleted;
	}
	/**
	 * @return the encounterNo
	 */
	public int getEncounterNo() {
		return encounterNo;
	}
	/**
	 * @param encounterNo the encounterNo to set
	 */
	public void setEncounterNo(int encounterNo) {
		this.encounterNo = encounterNo;
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
	

}
