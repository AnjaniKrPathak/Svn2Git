/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;

/**
 * @author li0008
 *
 */
public class RptGenericDrugCategory {
	
	private int id;
	private int providerId;
	private String DrugCategory;
	private int totalDrug;
	private Date reportDateForGenericDrug;
	private int drugClassificationId;
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
	 * @return the reportDateForGenericDrug
	 */
	public Date getReportDateForGenericDrug() {
		return reportDateForGenericDrug;
	}
	/**
	 * @param reportDateForGenericDrug the reportDateForGenericDrug to set
	 */
	public void setReportDateForGenericDrug(Date reportDateForGenericDrug) {
		this.reportDateForGenericDrug = reportDateForGenericDrug;
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

}
