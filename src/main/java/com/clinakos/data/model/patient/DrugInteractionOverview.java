package com.clinakos.data.model.patient;

public class DrugInteractionOverview {
	
	private int id;
	private String drugId;
	private String severityLevel;
	private String issueType;
	private String conflict;
	private String drugName;
    private String clinicalEffects;
	private String patientManagement;
	private String severityLevelToolTip;
	
	private double drugIdVal;
	
	public String getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
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
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getIssueType() {
		return issueType;
	}
	public void setConflict(String conflict) {
		this.conflict = conflict;
	}
	public String getConflict() {
		return conflict;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public String getDrugId() {
		return drugId;
	}
	public String getSeverityLevelToolTip() {
		return severityLevelToolTip;
	}
	public void setSeverityLevelToolTip(String severityLevelToolTip) {
		this.severityLevelToolTip = severityLevelToolTip;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drugId == null) ? 0 : drugId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DrugInteractionOverview other = (DrugInteractionOverview) obj;
	        if((drugId.equals(other.getDrugId())||drugId.equals(other.getConflict())) && (conflict.equals(other.getDrugId()) || conflict.equals(other.getConflict()))){
	        	return true;
	        }else{
	        	return false;
	        }
	}
	public double getDrugIdVal() {
		return drugIdVal;
	}
	public void setDrugIdVal(double drugIdVal) {
		this.drugIdVal = drugIdVal;
	}
	

}
