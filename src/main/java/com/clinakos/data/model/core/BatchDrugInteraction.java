/**
 * 
 */
package com.clinakos.data.model.core;

/**
 * @author IDC-0004
 *
 */
public class BatchDrugInteraction {
	
	private int id;
	private double drug1Id;
	private double drug2Id;
	private String interactionText;
	private String severityLevel;
	private int interactionType;
	private String clinicalEffects;
	private String patientManagement;
	private String drug1Name;
	private String drug2Name;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getDrug1Id() {
		return drug1Id;
	}
	public void setDrug1Id(double drug1Id) {
		this.drug1Id = drug1Id;
	}
	public double getDrug2Id() {
		return drug2Id;
	}
	public void setDrug2Id(double drug2Id) {
		this.drug2Id = drug2Id;
	}
	public String getInteractionText() {
		return interactionText;
	}
	public void setInteractionText(String interactionText) {
		this.interactionText = interactionText;
	}
	public String getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	public int getInteractionType() {
		return interactionType;
	}
	public void setInteractionType(int interactionType) {
		this.interactionType = interactionType;
	}
	public String getClinicalEffects() {
		return clinicalEffects;
	}
	public void setClinicalEffects(String clinicalEffects) {
		this.clinicalEffects = clinicalEffects;
	}
	public String getPatientManagement() {
		return patientManagement;
	}
	public void setPatientManagement(String patientManagement) {
		this.patientManagement = patientManagement;
	}
	public String getDrug1Name() {
		return drug1Name;
	}
	public void setDrug1Name(String drug1Name) {
		this.drug1Name = drug1Name;
	}
	public String getDrug2Name() {
		return drug2Name;
	}
	public void setDrug2Name(String drug2Name) {
		this.drug2Name = drug2Name;
	}
	
	

}
