/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;
import java.util.List;

import com.clinakos.data.model.patient.DrugInteractionOverview;

/**
 * @author IDC-0004
 *
 */
public class BatchPatientMedsHistory {

	
	private int id;
	private double drugId;
	private double drugNameId;
	private int adherenceScore;//same as compliance score
	private String formularyText;
	private int formularyStatus;
	private String drugName;
	
	private List<BatchDrugInteraction>drugInteractions;
	
	private List<BatchDiseaseInteraction>diseaseInteractions;
	
	private List<BatchAllergyInteraction>allergyInteractions;
	
	//following fields for UI purpose
	private Date batchPullDate;
	private int totalInteractions;
	private List<DrugInteractionOverview>allIntertactions;
	private String severityLevel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	

	public String getFormularyText() {
		return formularyText;
	}

	public void setFormularyText(String formularyText) {
		this.formularyText = formularyText;
	}
	

	public List<BatchDrugInteraction> getDrugInteractions() {
		return drugInteractions;
	}

	public void setDrugInteractions(List<BatchDrugInteraction> drugInteractions) {
		this.drugInteractions = drugInteractions;
	}

	public List<BatchDiseaseInteraction> getDiseaseInteractions() {
		return diseaseInteractions;
	}

	public void setDiseaseInteractions(
			List<BatchDiseaseInteraction> diseaseInteractions) {
		this.diseaseInteractions = diseaseInteractions;
	}

	public List<BatchAllergyInteraction> getAllergyInteractions() {
		return allergyInteractions;
	}

	public void setAllergyInteractions(
			List<BatchAllergyInteraction> allergyInteractions) {
		this.allergyInteractions = allergyInteractions;
	}

	

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public Date getBatchPullDate() {
		return batchPullDate;
	}

	public void setBatchPullDate(Date batchPullDate) {
		this.batchPullDate = batchPullDate;
	}

	public int getTotalInteractions() {
		return totalInteractions;
	}

	public void setTotalInteractions(int totalInteractions) {
		this.totalInteractions = totalInteractions;
	}

	public List<DrugInteractionOverview> getAllIntertactions() {
		return allIntertactions;
	}

	public void setAllIntertactions(List<DrugInteractionOverview> allIntertactions) {
		this.allIntertactions = allIntertactions;
	}

	public int getAdherenceScore() {
		return adherenceScore;
	}

	public void setAdherenceScore(int adherenceScore) {
		this.adherenceScore = adherenceScore;
	}

	@Override
	public String toString() {
		return "BatchPatientMedsHistory [id=" + id + ", drugId=" + drugId
				+ ", drugNameId=" + drugNameId + ", adherenceScore="
				+ adherenceScore + ", formularyText=" + formularyText
				+ ", drugName=" + drugName + "]";
	}

	public String getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}

	public int getFormularyStatus() {
		return formularyStatus;
	}

	public void setFormularyStatus(int formularyStatus) {
		this.formularyStatus = formularyStatus;
	}
	
}
