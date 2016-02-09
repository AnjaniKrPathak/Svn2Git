/**
 * 
 */
package com.clinakos.data.model.core;

/**
 * @author IDC-0004
 * BatchInteractionData object will capture details of drugs like interaction text,severity level,interaction type and formulary status from webservices
 */
public class BatchInteractionData {

	private int id;
	private double drugId;
	private double drugNameId;
	private String interactionText;
	private String severityLevel;
	private int interactionType;
	private String formularyStatus; 
	
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
	public String getFormularyStatus() {
		return formularyStatus;
	}
	public void setFormularyStatus(String formularyStatus) {
		this.formularyStatus = formularyStatus;
	}
	
	
	
}
