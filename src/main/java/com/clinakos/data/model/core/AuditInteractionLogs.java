/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;

/**
 * @author IDC-0004
 * Object used to capture interaction logs data during batch data pull process
 * and it will be saved to audit_interaction_logs table
 *
 */
public class AuditInteractionLogs {

	private int id;
	private int userId;
	private int patientId;
	private double drugId;
	private double drugNameId;
	private int interactionType;
	private Date dateOfErrorMessage;
	private String errorMessage;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public Date getDateOfErrorMessage() {
		return dateOfErrorMessage;
	}
	public void setDateOfErrorMessage(Date dateOfErrorMessage) {
		this.dateOfErrorMessage = dateOfErrorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getInteractionType() {
		return interactionType;
	}
	public void setInteractionType(int interactionType) {
		this.interactionType = interactionType;
	}
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	public double getDrugNameId() {
		return drugNameId;
	}
	public void setDrugNameId(double drugNameId) {
		this.drugNameId = drugNameId;
	}
	
	
}
