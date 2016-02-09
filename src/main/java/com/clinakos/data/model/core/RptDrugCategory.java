/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;

/**
 * @author li0008
 *
 */
public class RptDrugCategory {
	private int id;
	private int providerId;
	private String DrugCategory;
	private int totalDrug;
	private Date reportDateForDrug;
	private int drugClassificationId;
	private String drugName;
	private int totalPatient;
	
	
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
	 * @return the drugCategory
	 */
	public String getDrugCategory() {
		return DrugCategory;
	}
	/**
	 * @param drugCategory the drugCategory to set
	 */
	public void setDrugCategory(String drugCategory) {
		DrugCategory = drugCategory;
	}
	/**
	 * @return the totalDrug
	 */
	public int getTotalDrug() {
		return totalDrug;
	}
	/**
	 * @param totalDrug the totalDrug to set
	 */
	public void setTotalDrug(int totalDrug) {
		this.totalDrug = totalDrug;
	}
	/**
	 * @return the reportDateForDrug
	 */
	public Date getReportDateForDrug() {
		return reportDateForDrug;
	}
	/**
	 * @param reportDateForDrug the reportDateForDrug to set
	 */
	public void setReportDateForDrug(Date reportDateForDrug) {
		this.reportDateForDrug = reportDateForDrug;
	}
	/**
	 * @return the drugClassificationId
	 */
	public int getDrugClassificationId() {
		return drugClassificationId;
	}
	/**
	 * @param drugClassificationId the drugClassificationId to set
	 */
	public void setDrugClassificationId(int drugClassificationId) {
		this.drugClassificationId = drugClassificationId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getTotalPatient() {
		return totalPatient;
	}
	public void setTotalPatient(int totalPatient) {
		this.totalPatient = totalPatient;
	}
	
	

}
