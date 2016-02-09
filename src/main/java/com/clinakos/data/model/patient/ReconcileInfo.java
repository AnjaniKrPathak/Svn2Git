/**
 * 
 */
package com.clinakos.data.model.patient;

import java.util.Date;

/**
 * @author IDC-0004
 *
 *	CreatedBy:Nagaraj
 *
 *	Purpose:ReconcileInfo object mapped to reconcile_info table in database
 *	when saving reconcile med it will be saved status as true
 *	when reconcile med is moved from current patient medication data to history,Then status is set to false
 *	Dao layer is written in such way that,it will select max lastReconciledDate and status true based on patient Id	
 *
 *	It will even act as history table Thus reducing insert,update and delete operation when saving reconcile Med
 *
 */
public class ReconcileInfo {
	
	private int id;
	private int patientId;
	private double drugId;
	private double drugNameId;
	private int providerId;
	private int clinicProviderId;
	private int lastReconciledBy;// ====>doctorId:who did reconcile
	private Date lastReconciledDate;//====>on which date reconcile was done
	
	private String lastReconciledDateStr;//for UI purpose
	private String lastReconciledByName;//for UI purpose
	private boolean status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
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
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getClinicProviderId() {
		return clinicProviderId;
	}
	public void setClinicProviderId(int clinicProviderId) {
		this.clinicProviderId = clinicProviderId;
	}
	public int getLastReconciledBy() {
		return lastReconciledBy;
	}
	public void setLastReconciledBy(int lastReconciledBy) {
		this.lastReconciledBy = lastReconciledBy;
	}
	public Date getLastReconciledDate() {
		return lastReconciledDate;
	}
	public void setLastReconciledDate(Date lastReconciledDate) {
		this.lastReconciledDate = lastReconciledDate;
	}
	public String getLastReconciledDateStr() {
		return lastReconciledDateStr;
	}
	public void setLastReconciledDateStr(String lastReconciledDateStr) {
		this.lastReconciledDateStr = lastReconciledDateStr;
	}
	public String getLastReconciledByName() {
		return lastReconciledByName;
	}
	public void setLastReconciledByName(String lastReconciledByName) {
		this.lastReconciledByName = lastReconciledByName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
