/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;

/**
 * @author li0008
 *
 */
public class RptSpecialDrugCategory {
	private int id;
	private int providerId;
	private String drugCategory;
	private int totalDrug;
	private Date reportDateForSpecialDrug;
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
	 * @return the reportDateForSpecialDrug
	 */
	public Date getReportDateForSpecialDrug() {
		return reportDateForSpecialDrug;
	}
	/**
	 * @param reportDateForSpecialDrug the reportDateForSpecialDrug to set
	 */
	public void setReportDateForSpecialDrug(Date reportDateForSpecialDrug) {
		this.reportDateForSpecialDrug = reportDateForSpecialDrug;
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
	/**
	 * @return the drugCategory
	 */
	public String getDrugCategory() {
		return drugCategory;
	}
	/**
	 * @param drugCategory the drugCategory to set
	 */
	public void setDrugCategory(String drugCategory) {
		this.drugCategory = drugCategory;
	}

}
