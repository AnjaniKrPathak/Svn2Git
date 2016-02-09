/**
 * 
 */
package com.clinakos.data.model.patient;



/**
 * @author LI-M-0004
 *
 */
public class DrugDrugInteractionData {
	
	private int id;
    private String mechanismOfAction;
    private String discussion;
    private String clinicalEffects;
    private String severityLevel;
    private String patientManagement;
    private String predisposingFactors;
    private String references;
    private String monographTitle;
    private String drug1;
    private String drug1ID;   
    private String drug1Type;   
    private String drug2;    
    private String drug2ID;    
    private String drug2Type;
    private String performance;
    private int masterDrugDbId;
    private boolean checkBox;
    private boolean status;
    private boolean uiCheckBox;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMechanismOfAction() {
		return mechanismOfAction;
	}
	public void setMechanismOfAction(String mechanismOfAction) {
		this.mechanismOfAction = mechanismOfAction;
	}
	public String getDiscussion() {
		return discussion;
	}
	public void setDiscussion(String discussion) {
		this.discussion = discussion;
	}
	public String getClinicalEffects() {
		return clinicalEffects;
	}
	public void setClinicalEffects(String clinicalEffects) {
		this.clinicalEffects = clinicalEffects;
	}
	public String getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	public String getPatientManagement() {
		return patientManagement;
	}
	public void setPatientManagement(String patientManagement) {
		this.patientManagement = patientManagement;
	}
	public String getPredisposingFactors() {
		return predisposingFactors;
	}
	public void setPredisposingFactors(String predisposingFactors) {
		this.predisposingFactors = predisposingFactors;
	}
	public String getReferences() {
		return references;
	}
	public void setReferences(String references) {
		this.references = references;
	}
	public String getMonographTitle() {
		return monographTitle;
	}
	public void setMonographTitle(String monographTitle) {
		this.monographTitle = monographTitle;
	}
	public String getDrug1() {
		return drug1;
	}
	public void setDrug1(String drug1) {
		this.drug1 = drug1;
	}
	public String getDrug1ID() {
		return drug1ID;
	}
	public void setDrug1ID(String drug1id) {
		drug1ID = drug1id;
	}
	public String getDrug1Type() {
		return drug1Type;
	}
	public void setDrug1Type(String drug1Type) {
		this.drug1Type = drug1Type;
	}
	public String getDrug2() {
		return drug2;
	}
	public void setDrug2(String drug2) {
		this.drug2 = drug2;
	}
	public String getDrug2ID() {
		return drug2ID;
	}
	public void setDrug2ID(String drug2id) {
		drug2ID = drug2id;
	}
	public String getDrug2Type() {
		return drug2Type;
	}
	public void setDrug2Type(String drug2Type) {
		this.drug2Type = drug2Type;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	public boolean isCheckBox() {
		return checkBox;
	}
	public void setCheckBox(boolean checkBox) {
		this.checkBox = checkBox;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isUiCheckBox() {
		return uiCheckBox;
	}
	public void setUiCheckBox(boolean uiCheckBox) {
		this.uiCheckBox = uiCheckBox;
	}
	public int getMasterDrugDbId() {
		return masterDrugDbId;
	}
	public void setMasterDrugDbId(int masterDrugDbId) {
		this.masterDrugDbId = masterDrugDbId;
	}
    
    
    
    
}
